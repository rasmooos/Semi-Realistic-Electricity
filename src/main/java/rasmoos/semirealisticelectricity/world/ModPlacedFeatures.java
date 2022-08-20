package rasmoos.semirealisticelectricity.world;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {


    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<PlacedFeature> RUBBER_CHECKED = PLACED_FEATURES.register("rubber_checked", () ->
            new PlacedFeature(ModConfiguredFeatures.RUBBER.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.RUBBER_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> RUBBER_PLACED = PLACED_FEATURES.register("rubber_placed",
            () -> new PlacedFeature(
                    ModConfiguredFeatures.RUBBER_TREES.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(0, 0.05f, 5))));

    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.TIN_ORE_SMALL.getHolder().get(), commonOrePlacement(16 , // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))));
    public static final RegistryObject<PlacedFeature> TIN_ORE_LARGE_PLACED = PLACED_FEATURES.register("tin_ore_large_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.TIN_ORE.getHolder().get(), commonOrePlacement(16 , // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))));

    public static final RegistryObject<PlacedFeature> MAGNETITE_ORE_PLACED = PLACED_FEATURES.register("magnetite_ore_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.MAGNETITE_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)))));
    public static final RegistryObject<PlacedFeature> MAGNETITE_ORE_LARGE_PLACED = PLACED_FEATURES.register("magnetite_ore_large_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.MAGNETITE_ORE.getHolder().get(),
                    commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));

    public static final RegistryObject<PlacedFeature> COBALT_ORE_PLACED = PLACED_FEATURES.register("cobalt_ore_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.COBALT_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> COBALT_ORE_LARGE_PLACED = PLACED_FEATURES.register("cobalt_ore_large_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.COBALT_ORE_LARGE.getHolder().get(),
                    rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> COBALT_ORE_BURIED_PLACED = PLACED_FEATURES.register("cobalt_ore_buried_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.COBALT_ORE_BURIED.getHolder().get(),
                    commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_PLACED = PLACED_FEATURES.register("lepidolite_ore_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.LEPIDOLITE_ORE_SMALL.getHolder().get(),
                    commonOrePlacement(7, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_LARGE_PLACED = PLACED_FEATURES.register("lepidolite_ore_large_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.LEPIDOLITE_ORE_LARGE.getHolder().get(),
                    rareOrePlacement(9, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));
    public static final RegistryObject<PlacedFeature> LEPIDOLITE_ORE_BURIED_PLACED = PLACED_FEATURES.register("lepidolite_ore_buried_placed", () ->
            new PlacedFeature(ModConfiguredFeatures.LEPIDOLITE_ORE_BURIED.getHolder().get(),
                    commonOrePlacement(4, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80)))));

    public static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }


    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

}
