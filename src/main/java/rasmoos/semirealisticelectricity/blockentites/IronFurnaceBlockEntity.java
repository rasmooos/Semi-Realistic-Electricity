package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.screen.IronFurnaceMenu;

public class IronFurnaceBlockEntity extends MachineBlockEntity {

    public static final int NUM_SLOTS = 3;

    public IronFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.IRON_FURNACE_ENTITY.get(), pWorldPosition, pBlockState, ModBlocks.IRON_FURNACE_BLOCK.get());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("iron_furnace.name");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new IronFurnaceMenu(pContainerId, pInventory, this);
    }

    public void tick() {
//        if(pLevel.isClientSide()) {
//            return;
//        }
//
//        if(hasRecipe(pBlockEntity) && hasEnoughEnergy(pBlockEntity)) {
//            pBlockEntity.progress++;
//            extractEnergy(pBlockEntity);
//            setChanged(pLevel, pPos, pState);
//            if(pBlockEntity.progress >= pBlockEntity.maxProgress) {
//                craftItem(pBlockEntity);
//            }
//        } else {
//            pBlockEntity.resetProgress();
//            setChanged(pLevel, pPos, pState);
//        }
//
//        if(hasWaterSourceInSlot(pBlockEntity)) {
//            transferItemWaterToWaterTank(pBlockEntity);
//        }
    }

    @Override
    public int getNumberOfSlots() {
        return NUM_SLOTS;
    }
}
