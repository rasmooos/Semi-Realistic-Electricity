package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.Registration;

public class ModBlockModelProvider extends BlockModelProvider {
    public ModBlockModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        simpleBlock(Registration.COBALT_ORE.get());
//        simpleBlock(Registration.DEEPSLATE_COBALT_ORE.get());
//        simpleBlock(Registration.TIN_ORE.get());
//        simpleBlock(Registration.DEEPSLATE_TIN_ORE.get());
//        simpleBlock(Registration.MAGNETITE_ORE.get());
//        simpleBlock(Registration.DEEPSLATE_MAGNETITE_ORE.get());
//        simpleBlock(Registration.LEPIDOLITE_ORE.get());
//        simpleBlock(Registration.DEEPSLATE_LEPIDOLITE_ORE.get());
//        simpleBlock(Registration.RUBBER_PLANKS.get());


    }

    private BlockModelBuilder simpleBlock(Block block) {
        ResourceLocation id_block = ForgeRegistries.BLOCKS.getKey(block);

        return cubeAll(id_block.getPath(), new ResourceLocation(SemiRealisticElectricity.MOD_ID,
                "block/" + id_block.getPath()));
    }

    private BlockModelBuilder complexBlock(Block block) {
        ResourceLocation id_block = ForgeRegistries.BLOCKS.getKey(block);
        return withExistingParent(id_block.getPath(), new ResourceLocation(SemiRealisticElectricity.MOD_ID,
                "block/" + id_block.getPath()));
    }
}
