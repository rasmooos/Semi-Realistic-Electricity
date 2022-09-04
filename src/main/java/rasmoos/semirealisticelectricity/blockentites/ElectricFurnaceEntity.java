package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.screen.menu.ElectricFurnaceMenu;

import java.util.Map;
import java.util.Optional;

public class ElectricFurnaceEntity extends MachineBlockEntity {

    public ElectricFurnaceEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.ELECTRIC_FURNACE_ENTITY.get(), blockPos, blockState, ModBlocks.ELECTRIC_FURNACE.get());
    }

    @Override
    public int getNumberOfSlots() {
        return 3;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new ElectricFurnaceMenu(pContainerId, pInventory, this, data);
    }

    @Override
    public boolean hasRecipe() {
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
    public void craftItem() {
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
        return Component.translatable("electric_furnace.name");
    }
}
