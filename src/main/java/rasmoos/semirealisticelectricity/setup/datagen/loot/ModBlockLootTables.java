package rasmoos.semirealisticelectricity.setup.datagen.loot;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.setup.Registration;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};


    @Override
    protected void addTables() {
        add(Registration.TIN_ORE.get(), (block) -> createOreDrop(Registration.TIN_ORE.get(), Registration.RAW_TIN.get()));
        add(Registration.DEEPSLATE_TIN_ORE.get(), (block) -> createOreDrop(Registration.DEEPSLATE_TIN_ORE.get(), Registration.RAW_TIN.get()));
        add(Registration.MAGNETITE_ORE.get(), (block) -> createOreDrop(Registration.MAGNETITE_ORE.get(), Registration.RAW_MAGNETITE.get()));
        add(Registration.DEEPSLATE_MAGNETITE_ORE.get(), (block) -> createOreDrop(Registration.DEEPSLATE_MAGNETITE_ORE.get(), Registration.RAW_MAGNETITE.get()));
        add(Registration.LEPIDOLITE_ORE.get(), (block) -> createOreDrop(Registration.LEPIDOLITE_ORE.get(), Registration.RAW_LEPIDOLITE.get()));
        add(Registration.DEEPSLATE_LEPIDOLITE_ORE.get(), (block) -> createOreDrop(Registration.DEEPSLATE_LEPIDOLITE_ORE.get(), Registration.RAW_LEPIDOLITE.get()));
        add(Registration.COBALT_ORE.get(), (block) -> createOreDrop(Registration.COBALT_ORE.get(), Registration.RAW_COBALT.get()));
        add(Registration.DEEPSLATE_COBALT_ORE.get(), (block) -> createOreDrop(Registration.DEEPSLATE_COBALT_ORE.get(), Registration.RAW_COBALT.get()));

        add(Registration.RUBBER_LEAVES.get(), (block) -> createLeavesDrops(block, Registration.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(Registration.RUBBER_SAPLING.get());


        dropSelf(Registration.RUBBER_LOG.get());
        dropSelf(Registration.STRIPPED_RUBBER_LOG.get());
        dropSelf(Registration.RUBBER_PLANKS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
