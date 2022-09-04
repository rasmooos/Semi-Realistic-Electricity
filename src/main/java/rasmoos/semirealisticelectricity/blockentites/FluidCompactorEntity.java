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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.FluidCompactor;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.blocks.RubberLogTap;
import rasmoos.semirealisticelectricity.network.ModNetworkHandler;
import rasmoos.semirealisticelectricity.network.SyncItemToClient;
import rasmoos.semirealisticelectricity.recipe.FluidCompactorRecipe;
import rasmoos.semirealisticelectricity.screen.menu.FluidCompactorMenu;

import java.util.Map;
import java.util.Optional;

public class FluidCompactorEntity extends MachineBlockEntity {

    private int progress;
    private int maxProgress;

    private CraftType craftType;

    public enum CraftType {
        COBBLESTONE,
        STONE,
        OBSIDIAN,
        NONE
    }

    public FluidCompactorEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.FLUID_COMPACTOR_ENTITY.get(), blockPos, blockState, ModBlocks.FLUID_COMPACTOR.get());

        progress = 0;
        maxProgress = 72;
        craftType = CraftType.COBBLESTONE;
    }

    @Override
    public int getNumberOfSlots() {
        return 2;
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
                return false;
            }
        };
    }

    @Override
    public void tick() {
        if(level.isClientSide) {
            return;
        }
        energyStorage.extractEnergy(BASE_ENERGY_PER_TICK, false);

        itemHandler.setStackInSlot(1, getCraftItem());

        if(hasRecipe() && hasEnoughEnergy()) {
            progress++;
            energyStorage.extractEnergy(10, false);
            setChanged(level, getBlockPos(), getBlockState());
            if(progress > maxProgress) {
                craftItem();
            }
        } else {
            resetProgress();
            setChanged(level, getBlockPos(), getBlockState());
        }

        if(!getBlockState().getValue(FluidCompactor.LIT) && progress > 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(FluidCompactor.LIT, true));
        } else if(getBlockState().getValue(FluidCompactor.LIT) && progress == 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(FluidCompactor.LIT, false));
        }
    }

    public void craftItem() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<FluidCompactorRecipe> match = level.getRecipeManager()
                .getRecipeFor(FluidCompactorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            FluidStack[] fluids = match.get().getFluids();

            if(fluidTanks.get(0).getFluid().isFluidEqual(fluids[0]) && fluidTanks.get(1).getFluid().isFluidEqual(fluids[1])) {

                fluidTanks.get(0).drain(match.get().getUseAmount()[0], IFluidHandler.FluidAction.EXECUTE);
                fluidTanks.get(1).drain(match.get().getUseAmount()[1], IFluidHandler.FluidAction.EXECUTE);
            } else {
                fluidTanks.get(1).drain(match.get().getUseAmount()[0], IFluidHandler.FluidAction.EXECUTE);
                fluidTanks.get(0).drain(match.get().getUseAmount()[1], IFluidHandler.FluidAction.EXECUTE);
            }

            itemHandler.setStackInSlot(0, new ItemStack(match.get().getResultItem().getItem(), itemHandler.getStackInSlot(0).getCount() + 1));

            progress = 0;
        }
    }

    public boolean hasRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<FluidCompactorRecipe> match = level.getRecipeManager()
                .getRecipeFor(FluidCompactorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            FluidStack[] fluids = match.get().getFluids();

            boolean fluidChecked = false;

            if(fluidTanks.get(0).getFluid().isFluidEqual(fluids[0]) && fluidTanks.get(1).getFluid().isFluidEqual(fluids[1]) &&
                    fluidTanks.get(0).getFluidAmount() >= fluids[0].getAmount() && fluidTanks.get(1).getFluidAmount() >= fluids[1].getAmount()) {
                fluidChecked = true;
            } else if(fluidTanks.get(1).getFluid().isFluidEqual(fluids[0]) && fluidTanks.get(0).getFluid().isFluidEqual(fluids[1]) &&
                    fluidTanks.get(1).getFluidAmount() >= fluids[0].getAmount() && fluidTanks.get(0).getFluidAmount() >= fluids[1].getAmount()) {
                fluidChecked = true;
            }

            maxProgress = match.get().getCraftTime();

            return fluidChecked && canInsertAmountIntoOutputSlot(inventory)
                    && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
        }
        return false;
    }
    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(0).getItem() == output.getItem() || inventory.getItem(0).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(0).getMaxStackSize() > inventory.getItem(0).getCount();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new FluidCompactorMenu(pContainerId, pInventory, this, data);
    }

    @Override
    public int[] getFluidTankCapacity() {
        return new int[]{32000, 32000};
    }

    private final Map<Direction, LazyOptional<WrappedItemHandler>> directionWrappedItemHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 0, (index, stack) -> false)),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (i) -> i == 0, (index, stack) -> false)),
                    Direction.WEST, LazyOptional.of(() -> new WrappedItemHandler(itemHandler, (index) -> index == 0, (index, stack) -> false)));

    private final Map<Direction, LazyOptional<WrappedFluidHandler>> directionWrappedFluidHandlerMap =
            Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> false)),
                    Direction.UP, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> false)),
                    Direction.NORTH, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> true)),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> true)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> true)),
                    Direction.WEST, LazyOptional.of(() -> new WrappedFluidHandler(fluidTanks, (i) -> true, (i, s) -> true)));

    @Override
    public Map<Direction, LazyOptional<WrappedItemHandler>> getDirectionWrappedItemHandlerMap() {
        return directionWrappedItemHandlerMap;
    }

    @Override
    public Map<Direction, LazyOptional<WrappedFluidHandler>> getDirectionWrappedFluidHandlerMap() {
        return directionWrappedFluidHandlerMap;
    }

    @Override
    public ContainerData getContainerData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> FluidCompactorEntity.this.progress;
                    case 1 -> FluidCompactorEntity.this.maxProgress;
                    case 2 -> craftType.ordinal();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> FluidCompactorEntity.this.progress = value;
                    case 1 -> FluidCompactorEntity.this.maxProgress = value;
                    case 2 -> craftType = CraftType.values()[value];
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("fluid_compactor.name");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("craftType", craftType.ordinal());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        setCraftType(nbt.getInt("craftType"));
    }

    public void onRemove() {
        setCraftType(CraftType.NONE);
        itemHandler.setStackInSlot(1, getCraftItem());
    }

    public void setCraftType(CraftType craftType) {
        this.craftType = craftType;
        progress = 0;
        data.set(2, craftType.ordinal());
    }

    public void setCraftType(int craftType) {
        setCraftType(CraftType.values()[craftType]);
    }

    public CraftType getCraftType() {
        return craftType;
    }

    public ItemStack getCraftItem() {
        return switch(craftType) {
            case STONE -> new ItemStack(Blocks.STONE, 1);
            case OBSIDIAN -> new ItemStack(Blocks.OBSIDIAN, 1);
            case COBBLESTONE -> new ItemStack(Blocks.COBBLESTONE, 1);
            case NONE -> ItemStack.EMPTY;
        };
    }
}
