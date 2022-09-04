package rasmoos.semirealisticelectricity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blockentites.ElectrostaticSeparatorEntity;

public class ElectrostaticSeparator extends MachineBlock {
    protected ElectrostaticSeparator(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ElectrostaticSeparatorEntity(blockPos, blockState);
    }
}
