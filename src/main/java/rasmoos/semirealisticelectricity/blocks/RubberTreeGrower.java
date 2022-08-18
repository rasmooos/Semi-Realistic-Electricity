package rasmoos.semirealisticelectricity.blocks;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.world.ModConfiguredFeatures;

public class RubberTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean p_222911_) {
        return ModConfiguredFeatures.RUBBER.getHolder().get();
    }
}
