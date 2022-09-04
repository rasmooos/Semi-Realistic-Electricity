package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Tuple;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.FluidCompactor;
import rasmoos.semirealisticelectricity.blocks.IronFurnaceBlock;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.network.ModNetworkHandler;
import rasmoos.semirealisticelectricity.network.SyncItemToClient;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.screen.menu.IronFurnaceMenu;

import java.util.Map;
import java.util.Optional;

public class IronFurnaceBlockEntity extends MachineBlockEntity {

    public static final int NUM_SLOTS = 3;

    private int progress;
    private int maxProgress;
    private float fuelTime;
    private int maxFuelTime;

    public IronFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.IRON_FURNACE_ENTITY.get(), pWorldPosition, pBlockState, ModBlocks.IRON_FURNACE_BLOCK.get());
        progress = 0;
        maxProgress = 120;
        fuelTime = 0;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("iron_furnace.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new IronFurnaceMenu(pContainerId, pInventory, this, data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("iron_furnace.progress", progress);
        tag.putFloat("iron_furnace.fuelTime", fuelTime);
        tag.putInt("iron_furnace.max_fuelTime", maxFuelTime);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        progress = nbt.getInt("iron_furnace.progress");
        fuelTime = nbt.getFloat("iron_furnace.fuelTime");
        maxFuelTime = nbt.getInt("iron_furnace.max_fuelTime");
    }

    public void tick() {
        super.tick();
        if(level.isClientSide()) {
            return;
        }


        if(!getBlockState().getValue(IronFurnaceBlock.LIT) && fuelTime > 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(IronFurnaceBlock.LIT, true));
        } else if(getBlockState().getValue(IronFurnaceBlock.LIT) && fuelTime <= 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(IronFurnaceBlock.LIT, false));
        }

        boolean hasRecipe = hasRecipe();

        if(fuelTime <= 0) {
            if(ForgeHooks.getBurnTime(itemHandler.getStackInSlot(1), RecipeType.SMELTING) > 0 && hasRecipe) {
                maxFuelTime = ForgeHooks.getBurnTime(itemHandler.getStackInSlot(1), RecipeType.SMELTING);
                fuelTime = maxFuelTime;
                itemHandler.extractItem(1, 1, false);
            } else {
                progress = Math.max(0, --progress);
            }
        }

        if(fuelTime > 0) {
            fuelTime -= 1.2;
        }

        if(hasRecipe && fuelTime > 0) {
            progress++;
            setChanged(level, getBlockPos(), getBlockState());
            if(progress > maxProgress) {
                craftItem();
            }
        } else {
            resetProgress();
            setChanged(level, getBlockPos(), getBlockState());
        }
    }

    private void craftItem() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<SmeltingRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, inventory, level);

        if(match.isPresent()) {
            itemHandler.extractItem(0, 1, false);

            itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    itemHandler.getStackInSlot(2).getCount() + 1));

            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<SmeltingRecipe> match = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    @Override
    public int[] getFluidTankCapacity() {
        return new int[0];
    }

    private final Map<Direction, LazyOptional<WrappedItemHandler>> directionWrappedItemHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 0, (index, stack) -> false)),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (index, stack) -> false)),
                    Direction.WEST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 0, (index, stack) -> false)));
    @Override
    public Map<Direction, LazyOptional<WrappedItemHandler>> getDirectionWrappedItemHandlerMap() {
        return directionWrappedItemHandlerMap;
    }

    @Override
    public Map<Direction, LazyOptional<WrappedFluidHandler>> getDirectionWrappedFluidHandlerMap() {
        return Map.of();
    }

    @Override
    public Tuple<Integer, Integer> getEnergyStorageCapacity() {
        return new Tuple<>(0, 0);
    }

    @Override
    public ContainerData getContainerData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> IronFurnaceBlockEntity.this.progress;
                    case 1 -> IronFurnaceBlockEntity.this.maxProgress;
                    case 2 -> (int) IronFurnaceBlockEntity.this.fuelTime;
                    case 3 -> IronFurnaceBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> IronFurnaceBlockEntity.this.progress = value;
                    case 1 -> IronFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> IronFurnaceBlockEntity.this.fuelTime = value;
                    case 3 -> IronFurnaceBlockEntity.this.maxFuelTime = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public int getNumberOfSlots() {
        return NUM_SLOTS;
    }

    @Override
    public ItemStackHandler getItemHandler() {
        return new ItemStackHandler(getNumberOfSlots()) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                if(!level.isClientSide) {
                    ModNetworkHandler.sendToClients(new SyncItemToClient(itemHandler, getBlockPos()));
                }
            }

            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return true;
            }
        };
    }
}
