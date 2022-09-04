package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.recipe.ElectrostaticSeparatorRecipe;
import rasmoos.semirealisticelectricity.screen.menu.ElectrostaticSeparatorMenu;

import java.util.Map;
import java.util.Optional;

public class ElectrostaticSeparatorEntity extends MachineBlockEntity {

    public ElectrostaticSeparatorEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.ELECTROSTATIC_SEPARATOR_ENTITY.get(), blockPos, blockState, ModBlocks.ELECTROSTATIC_SEPARATOR.get());
    }

    public void tick() {
        super.tick();

        if(level.isClientSide)
            return;

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

        Optional<ElectrostaticSeparatorRecipe> match = level.getRecipeManager()
                .getRecipeFor(ElectrostaticSeparatorRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(2, inventory)
                && canInsertItemIntoOutputSlot(2, inventory, match.get().getMainOutput())
                && canInsertAmountIntoOutputSlot(3, inventory)
                && canInsertItemIntoOutputSlot(3, inventory, match.get().getSecondaryOutput());
    }

    private void craftItem() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<ElectrostaticSeparatorRecipe> match = level.getRecipeManager()
                .getRecipeFor(ElectrostaticSeparatorRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            itemHandler.extractItem(0,1, false);

            itemHandler.setStackInSlot(2, new ItemStack(match.get().getMainOutput().getItem(),
                    itemHandler.getStackInSlot(2).getCount() + 1));
            itemHandler.setStackInSlot(3, new ItemStack(match.get().getSecondaryOutput().getItem(),
                    itemHandler.getStackInSlot(3).getCount() + 1));

            resetProgress();
        }
    }

    private boolean canInsertItemIntoOutputSlot(int slot, SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(slot).getItem() == output.getItem() || inventory.getItem(2).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(int slot, SimpleContainer inventory) {
        return inventory.getItem(slot).getMaxStackSize() > inventory.getItem(2).getCount();
    }

    @Override
    public int getNumberOfSlots() {
        return 4;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new ElectrostaticSeparatorMenu(pContainerId, pInventory, this, data);
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
    public Component getDisplayName() {
        return Component.translatable("electrostatic_separator.name");
    }
}
