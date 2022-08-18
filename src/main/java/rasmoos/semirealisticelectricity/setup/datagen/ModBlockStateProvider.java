package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.Registration;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SemiRealisticElectricity.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.COBALT_ORE.get());
        simpleBlock(Registration.DEEPSLATE_COBALT_ORE.get());
        simpleBlock(Registration.TIN_ORE.get());
        simpleBlock(Registration.DEEPSLATE_TIN_ORE.get());
        simpleBlock(Registration.MAGNETITE_ORE.get());
        simpleBlock(Registration.DEEPSLATE_MAGNETITE_ORE.get());
        simpleBlock(Registration.LEPIDOLITE_ORE.get());
        simpleBlock(Registration.DEEPSLATE_LEPIDOLITE_ORE.get());

        logBlock(Registration.RUBBER_LOG.get());
        logBlock(Registration.STRIPPED_RUBBER_LOG.get());

        simpleBlock(Registration.RUBBER_PLANKS.get());
    }
}
