package rasmoos.semirealisticelectricity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blockentites.FluidCompactorEntity;

public class FluidCompactor extends MachineBlock {

    protected FluidCompactorEntity entity;

    protected FluidCompactor(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        entity = new FluidCompactorEntity(blockPos, blockState);
        return entity;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        entity.onRemove();
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }
}
