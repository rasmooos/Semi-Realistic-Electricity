package rasmoos.semirealisticelectricity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blockentites.ElectricFurnaceEntity;

public class ElectricFurnace extends MachineBlock {

    protected ElectricFurnace(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ElectricFurnaceEntity(blockPos, blockState);
    }
}
