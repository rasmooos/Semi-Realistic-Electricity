package rasmoos.semirealisticelectricity.world;

import com.google.common.base.Suppliers;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.Registration;

import java.util.List;
import java.util.function.Supplier;

public class WorldGeneration {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, SemiRealisticElectricity.MOD_ID);

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SemiRealisticElectricity.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> TIN_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registration.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.DEEPSLATE_TIN_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> COBALT_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registration.COBALT_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.DEEPSLATE_COBALT_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> MAGNETITE_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registration.MAGNETITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.DEEPSLATE_MAGNETITE_ORE.get().defaultBlockState())
    ));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> LEPIDOLITE_ORE_TARGET_LIST = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Registration.LEPIDOLITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Registration.DEEPSLATE_LEPIDOLITE_ORE.get().defaultBlockState())
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

    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore_placed", () ->
            new PlacedFeature(TIN_ORE_SMALL.getHolder().get(), commonOrePlacement(16 , // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))));
    public static final RegistryObject<PlacedFeature> TIN_ORE_LARGE_PLACED = PLACED_FEATURES.register("tin_ore_large_placed", () ->
            new PlacedFeature(TIN_ORE.getHolder().get(), commonOrePlacement(16 , // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))));

    public static final RegistryObject<PlacedFeature> MAGNETITE_ORE_PLACED = PLACED_FEATURES.register("magnetite_ore_placed", () ->
            new PlacedFeature(MAGNETITE_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)))));
    public static final RegistryObject<PlacedFeature> MAGNETITE_ORE_LARGE_PLACED = PLACED_FEATURES.register("magnetite_ore_large_placed", () ->
            new PlacedFeature(MAGNETITE_ORE.getHolder().get(),
                    commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));

    public static final RegistryObject<PlacedFeature> COBALT_ORE_PLACED = PLACED_FEATURES.register("cobalt_ore_placed", () ->
            new PlacedFeature(COBALT_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> COBALT_ORE_LARGE_PLACED = PLACED_FEATURES.register("cobalt_ore_large_placed", () ->
            new PlacedFeature(COBALT_ORE_LARGE.getHolder().get(),
                    rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> COBALT_ORE_BURIED_PLACED = PLACED_FEATURES.register("cobalt_ore_buried_placed", () ->
            new PlacedFeature(COBALT_ORE_BURIED.getHolder().get(),
                    commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_PLACED = PLACED_FEATURES.register("lepidolite_ore_placed", () ->
            new PlacedFeature(LEPIDOLITE_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_LARGE_PLACED = PLACED_FEATURES.register("lepidolite_ore_large_placed", () ->
            new PlacedFeature(LEPIDOLITE_ORE_LARGE.getHolder().get(),
                    rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_BURIED_PLACED = PLACED_FEATURES.register("lepidolite_ore_buried_placed", () ->
            new PlacedFeature(LEPIDOLITE_ORE_BURIED.getHolder().get(),
                    commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));


    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);
    }

}
