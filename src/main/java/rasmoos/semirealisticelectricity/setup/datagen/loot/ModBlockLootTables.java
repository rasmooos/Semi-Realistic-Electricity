package rasmoos.semirealisticelectricity.setup.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.items.ModItems;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};


    @Override
    protected void addTables() {
        add(ModBlocks.TIN_ORE.get(), (block) -> createOreDrop(ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        add(ModBlocks.DEEPSLATE_TIN_ORE.get(), (block) -> createOreDrop(ModBlocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));
        add(ModBlocks.MAGNETITE_ORE.get(), (block) -> createOreDrop(ModBlocks.MAGNETITE_ORE.get(), ModItems.RAW_MAGNETITE.get()));
        add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), (block) -> createOreDrop(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(), ModItems.RAW_MAGNETITE.get()));
        add(ModBlocks.LEPIDOLITE_ORE.get(), (block) -> createOreDrop(ModBlocks.LEPIDOLITE_ORE.get(), ModItems.RAW_LEPIDOLITE.get()));
        add(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get(), (block) -> createOreDrop(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get(), ModItems.RAW_LEPIDOLITE.get()));
        add(ModBlocks.COBALT_ORE.get(), (block) -> createOreDrop(ModBlocks.COBALT_ORE.get(), ModItems.RAW_COBALT.get()));
        add(ModBlocks.DEEPSLATE_COBALT_ORE.get(), (block) -> createOreDrop(ModBlocks.DEEPSLATE_COBALT_ORE.get(), ModItems.RAW_COBALT.get()));

        add(ModBlocks.RUBBER_LEAVES.get(), (block) -> createLeavesDrops(block, ModBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.RUBBER_SAPLING.get());


        dropSelf(ModBlocks.RUBBER_LOG.get());
        dropSelf(ModBlocks.STRIPPED_RUBBER_LOG.get());
        dropSelf(ModBlocks.RUBBER_PLANKS.get());
        dropOther(ModBlocks.RUBBER_LOG_TAP.get(), ModBlocks.RUBBER_LOG.get().asItem());

        dropSelf(ModBlocks.IRON_FURNACE_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
