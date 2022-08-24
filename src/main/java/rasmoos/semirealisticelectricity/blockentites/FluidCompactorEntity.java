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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.FluidCompactorRecipe;
import rasmoos.semirealisticelectricity.screen.menu.FluidCompactorMenu;

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
        craftType = CraftType.OBSIDIAN;
    }

    @Override
    public int getNumberOfSlots() {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();

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
    }

    private void craftItem() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<FluidCompactorRecipe> match = level.getRecipeManager()
                .getRecipeFor(FluidCompactorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            FluidStack[] fluids = match.get().getFluids();

            if(fluidTanks[0].getFluid().isFluidEqual(fluids[0]) && fluidTanks[1].getFluid().isFluidEqual(fluids[1])) {

                fluidTanks[0].drain(match.get().getUseAmount()[0], IFluidHandler.FluidAction.EXECUTE);
                fluidTanks[1].drain(match.get().getUseAmount()[1], IFluidHandler.FluidAction.EXECUTE);
            } else {
                fluidTanks[1].drain(match.get().getUseAmount()[0], IFluidHandler.FluidAction.EXECUTE);
                fluidTanks[0].drain(match.get().getUseAmount()[1], IFluidHandler.FluidAction.EXECUTE);
            }

            itemHandler.setStackInSlot(0, new ItemStack(match.get().getResultItem().getItem(), itemHandler.getStackInSlot(0).getCount() + 1));

            resetProgress();
        }
    }

    private boolean hasRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<FluidCompactorRecipe> match = level.getRecipeManager()
                .getRecipeFor(FluidCompactorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            FluidStack[] fluids = match.get().getFluids();

            boolean fluidChecked = false;

            if(fluidTanks[0].getFluid().isFluidEqual(fluids[0]) && fluidTanks[1].getFluid().isFluidEqual(fluids[1]) &&
                fluidTanks[0].getFluidAmount() >= fluids[0].getAmount() && fluidTanks[1].getFluidAmount() >= fluids[1].getAmount()) {
                fluidChecked = true;
            } else if(fluidTanks[1].getFluid().isFluidEqual(fluids[0]) && fluidTanks[0].getFluid().isFluidEqual(fluids[1]) &&
                        fluidTanks[1].getFluidAmount() >= fluids[0].getAmount() && fluidTanks[0].getFluidAmount() >= fluids[1].getAmount()) {
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

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= 10;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new FluidCompactorMenu(pContainerId, pInventory, this, data);
    }

    @Override
    public int[] getFluidTankCapacity() {
        return new int[]{32000, 32000};
    }

    @Override
    public Tuple<Integer, Integer> getEnergyStorageCapacity() {
        return new Tuple<>(60000, 200);
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
        craftType = CraftType.values()[nbt.getInt("craftType")];
    }

    public void onRemove() {
        setCraftType(CraftType.NONE);
    }

    public void setCraftType(CraftType craftType) {
        this.craftType = craftType;
        itemHandler.setStackInSlot(1, getCraftItem());
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
