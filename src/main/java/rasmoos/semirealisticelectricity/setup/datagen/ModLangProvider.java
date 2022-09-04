package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;

public class ModLangProvider extends LanguageProvider {
    
    public ModLangProvider(DataGenerator gen) {
        super(gen, SemiRealisticElectricity.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addItems();
        addBlocks();
        addGui();
        addMisc();
    }

    private void addItems() {
        add(ModItems.RAW_TIN.get(), "Raw Tin");
        add(ModItems.RAW_COBALT.get(), "Raw Cobalt");
        add(ModItems.RAW_MAGNETITE.get(), "Raw Magnetite");
        add(ModItems.RAW_LEPIDOLITE.get(), "Raw Lepidolite");

        add(ModItems.TIN_INGOT.get(), "Tin Ingot");
        add(ModItems.COBALT_INGOT.get(), "Cobalt Ingot");
        add(ModItems.MAGNETITE_INGOT.get(), "Magnetite Ingot");
        add(ModItems.BRONZE_INGOT.get(), "Bronze Ingot");
        add(ModItems.ALUMINIUM_INGOT.get(), "Aluminium Ingot");

        add(ModItems.TIN_DUST.get(), "Tin dust");
        add(ModItems.COBALT_DUST.get(), "Cobalt dust");
        add(ModItems.MAGNETITE_DUST.get(), "Magnetite dust");
        add(ModItems.LITHIUM_DUST.get(), "Lithium dust");
        add(ModItems.COPPER_DUST.get(), "Copper dust");
        add(ModItems.BRONZE_DUST.get(), "Bronze dust");
        add(ModItems.LEPIDOLITE_DUST.get(), "Lepidolite dust");
        add(ModItems.ALUMINIUM_DUST.get(), "Aluminium dust");

        add(ModItems.TIN_NUGGET.get(), "Tin nugget");
        add(ModItems.MAGNETITE_NUGGET.get(), "Magnetite nugget");
        add(ModItems.COBALT_NUGGET.get(), "Cobalt nugget");
        add(ModItems.BRONZE_NUGGET.get(), "Bronze nugget");
        add(ModItems.ALUMINIUM_NUGGET.get(), "Aluminium nugget");


        add(ModItems.RUBBER_TAP.get(), "Rubber Tap");
        add(ModItems.RUBBER_RESIN.get(), "Rubber Resin");
    }

    private void addBlocks() {
        add(ModBlocks.TIN_ORE.get(), "Tin Ore");
        add(ModBlocks.COBALT_ORE.get(), "Cobalt Ore");
        add(ModBlocks.MAGNETITE_ORE.get(), "Magnetite Ore");
        add(ModBlocks.LEPIDOLITE_ORE.get(), "Lepidolite Ore");

        add(ModBlocks.DEEPSLATE_TIN_ORE.get(), "Deepslate Tin Ore");
        add(ModBlocks.DEEPSLATE_COBALT_ORE.get(), "Deepslate Cobalt Ore");
        add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), "Deepslate Magnetite Ore");
        add(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get(), "Deepslate Lepidolite Ore");

        add(ModBlocks.RUBBER_LEAVES.get(), "Rubber Leaves");
        add(ModBlocks.RUBBER_SAPLING.get(), "Rubber Sapling");
        add(ModBlocks.RUBBER_PLANKS.get(), "Rubber Planks");
        add(ModBlocks.RUBBER_LOG.get(), "Rubber Log");
        add(ModBlocks.STRIPPED_RUBBER_LOG.get(), "Stripped Rubber Log");
        add(ModBlocks.RUBBER_LOG_TAP.get(), "Rubber Log Tap");

        add(ModBlocks.IRON_FURNACE_BLOCK.get(), "Iron Furnace");
        add(ModBlocks.CRUSHER_BLOCK.get(), "Crusher");
        add(ModBlocks.FLUID_COMPACTOR.get(), "Fluid Compactor");
        add(ModBlocks.ELECTROSTATIC_SEPARATOR.get(), "Electrostatic Separator");
    }

    private void addGui() {
        add("crusher.name", "Crusher");
        add("iron_furnace.name", "Iron Furnace");
        add("fluid_compactor.name", "Fluid Compactor");
        add("electrostatic_separator.name", "Electrostatic Separator");
    }

    private void addMisc() {
        add("itemGroup.semirealisticelectricity", "Semi Realistic Electricity");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.amount.with.capacity", "%s / %s mB");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.amount", "%s mB");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.empty", "Empty");
        add("jei.integration.semirealisticelectricity.category.iron_smelting", "Smelting");
        add("jei.integration.semirealisticelectricity.category.crushing", "Crushing");
        add("jei.integration.semirealisticelectricity.category.fluid_compacting", "Fluid Compacting");
        add("jei.integration.semirealisticelectricity.category.separating", "Electrostatic Separation");
    }
}
