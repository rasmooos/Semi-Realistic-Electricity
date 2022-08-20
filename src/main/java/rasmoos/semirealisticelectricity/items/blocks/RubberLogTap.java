package rasmoos.semirealisticelectricity.items.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blockentites.RubberLogTapEntity;

public class RubberLogTap extends FlammableRotatedPilarBlock implements EntityBlock {

    public static final IntegerProperty RUBBER_TAP = IntegerProperty.create("rubber_tap", 0, 8);

    public RubberLogTap(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new RubberLogTapEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> blockStateBuilder) {
        super.createBlockStateDefinition(blockStateBuilder);
        blockStateBuilder.add(RUBBER_TAP);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> tBlockEntityType) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof RubberLogTapEntity rubberLogTapEntity)
                rubberLogTapEntity.tick();
        };
    }


}
