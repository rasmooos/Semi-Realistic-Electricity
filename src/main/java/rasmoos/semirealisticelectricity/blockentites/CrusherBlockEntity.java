package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Tuple;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.screen.menu.CrusherMenu;

import java.util.Optional;

public class CrusherBlockEntity extends MachineBlockEntity {

    public static final int NUM_SLOTS = 3;
    private int progress;
    private int maxProgress;

    public CrusherBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(), blockPos, blockState, ModBlocks.CRUSHER_BLOCK.get());

        progress = 0;
        maxProgress = 72;
    }

    @Override
    public int getNumberOfSlots() {
        return NUM_SLOTS;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CrusherMenu(pContainerId, pInventory, this, data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("crusher.progress", progress);
        tag.putInt("crusher.energy", energyStorage.getEnergyStored());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        progress = nbt.getInt("crusher.progress");
        energyStorage.setEnergy(nbt.getInt("crusher.energy"));
    }

    @Override
    public int[] getFluidTankCapacity() {
        return new int[]{};
    }

    @Override
    public Tuple<Integer, Integer> getEnergyStorageCapacity() {
        return new Tuple<>(60000, 200);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("crusher.name");
    }

    public void tick() {
        super.tick();

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
    }
    private boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= 10;
    }

    private boolean hasRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<CrusherRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private void craftItem() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<CrusherRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            itemHandler.extractItem(0,1, false);

            itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(),
                    itemHandler.getStackInSlot(2).getCount() + 1));

            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(2).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }



    @Override
    public ContainerData getContainerData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> CrusherBlockEntity.this.progress;
                    case 1 -> CrusherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> CrusherBlockEntity.this.progress = value;
                    case 1 -> CrusherBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
}
