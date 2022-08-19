package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SemiRealisticElectricity.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.COBALT_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        simpleBlock(ModBlocks.TIN_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_TIN_ORE.get());
        simpleBlock(ModBlocks.MAGNETITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get());
        simpleBlock(ModBlocks.LEPIDOLITE_ORE.get());
        simpleBlock(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get());

        logBlock(ModBlocks.RUBBER_LOG.get());
        logBlock(ModBlocks.STRIPPED_RUBBER_LOG.get());

        simpleBlock(ModBlocks.RUBBER_PLANKS.get());
    }
}
