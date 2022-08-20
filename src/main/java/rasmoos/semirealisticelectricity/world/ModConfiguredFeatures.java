package rasmoos.semirealisticelectricity.world;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBBER = CONFIGURED_FEATURES.register("rubber_tree",
            () -> new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.RUBBER_LOG_TAP.get()),
                    new StraightTrunkPlacer(3, 7, 5),
                    BlockStateProvider.simple(ModBlocks.RUBBER_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
                    new TwoLayersFeatureSize(1, 0, 2)).build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> RUBBER_TREES = CONFIGURED_FEATURES.register("rubber_trees", () ->
            new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(ModPlacedFeatures.RUBBER_CHECKED.getHolder().get(), 0.8f)),
                    ModPlacedFeatures.RUBBER_CHECKED.getHolder().get()))
            );


    public static final Supplier<List<OreConfiguration.TargetBlockState>> TIN_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> COBALT_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.COBALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_COBALT_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MAGNETITE_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.MAGNETITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_MAGNETITE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEPIDOLITE_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.LEPIDOLITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get().defaultBlockState())
    ));

    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE = CONFIGURED_FEATURES.register("tin_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_ORE_TARGET_LIST.get(), 20)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE_SMALL = CONFIGURED_FEATURES.register("tin_ore_small",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(TIN_ORE_TARGET_LIST.get(), 10)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> MAGNETITE_ORE = CONFIGURED_FEATURES.register("magnetite_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MAGNETITE_ORE_TARGET_LIST.get(), 9)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> MAGNETITE_ORE_SMALL = CONFIGURED_FEATURES.register("magnetite_ore_small",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(MAGNETITE_ORE_TARGET_LIST.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> COBALT_ORE_LARGE = CONFIGURED_FEATURES.register("cobalt_ore_large",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(COBALT_ORE_TARGET_LIST.get(), 12, 0.7f)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> COBALT_ORE_SMALL = CONFIGURED_FEATURES.register("cobalt_ore_small",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(COBALT_ORE_TARGET_LIST.get(), 4,0.5f)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> COBALT_ORE_BURIED = CONFIGURED_FEATURES.register("cobalt_ore_buried",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(COBALT_ORE_TARGET_LIST.get(), 8, 1.0f )));

    public static final RegistryObject<ConfiguredFeature<?, ?>> LEPIDOLITE_ORE_LARGE = CONFIGURED_FEATURES.register("lepidolite_ore_large",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEPIDOLITE_ORE_TARGET_LIST.get(), 12, 0.7f)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEPIDOLITE_ORE_SMALL = CONFIGURED_FEATURES.register("lepidolite_ore_small",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEPIDOLITE_ORE_TARGET_LIST.get(), 4,0.5f)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> LEPIDOLITE_ORE_BURIED = CONFIGURED_FEATURES.register("lepidolite_ore_buried",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(LEPIDOLITE_ORE_TARGET_LIST.get(), 8, 1.0f )));
}
