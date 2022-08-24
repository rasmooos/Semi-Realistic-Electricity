package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

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

        add(ModItems.TIN_DUST.get(), "Tin dust");
        add(ModItems.COBALT_DUST.get(), "Cobalt dust");
        add(ModItems.MAGNETITE_DUST.get(), "Magnetite dust");
        add(ModItems.LITHIUM_DUST.get(), "Lithium dust");

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
    }

    private void addGui() {
        add("crusher.name", "Crusher");
        add("iron_furnace.name", "Iron Furnace");
    }

    private void addMisc() {
        add("itemGroup.semirealisticelectricity", "Semi Realistic Electricity");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.amount.with.capacity", "%s / %s mB");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.amount", "%s mB");
        add("jei.integration.semirealisticelectricity.tooltip.liquid.empty", "Empty");
    }
}
