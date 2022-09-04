package rasmoos.semirealisticelectricity.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blockentites.IronFurnaceBlockEntity;

public class IronFurnaceBlock extends MachineBlock {

    public IronFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new IronFurnaceBlockEntity(blockPos, blockState);
    }

    public void animateTick(BlockState p_221253_, Level p_221254_, BlockPos p_221255_, RandomSource p_221256_) {
        if (p_221253_.getValue(LIT)) {
            double d0 = (double)p_221255_.getX() + 0.5D;
            double d1 = (double)p_221255_.getY();
            double d2 = (double)p_221255_.getZ() + 0.5D;
            if (p_221256_.nextDouble() < 0.1D) {
                p_221254_.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = p_221253_.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = p_221256_.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = p_221256_.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            p_221254_.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            p_221254_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
