package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.MachineBlock;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

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

        machineBlock(ModBlocks.IRON_FURNACE_BLOCK.get());
        machineBlock(ModBlocks.CRUSHER_BLOCK.get());
    }

    public void logBlock(RotatedPillarBlock block) {
        axisBlock(block, blockTexture(block), extend(blockTexture(block), "_top"));
    }
    public void machineBlock(MachineBlock block, ModelFile off, ModelFile on) {
        getVariantBuilder(block)
                .partialState().with(MachineBlock.FACING, Direction.NORTH).with(MachineBlock.LIT, false)
                .modelForState().modelFile(off).addModel()

                .partialState().with(MachineBlock.FACING, Direction.SOUTH).with(MachineBlock.LIT, false)
                .modelForState().modelFile(off).rotationY(180).addModel()

                .partialState().with(MachineBlock.FACING, Direction.WEST).with(MachineBlock.LIT, false)
                .modelForState().modelFile(off).rotationY(270).addModel()

                .partialState().with(MachineBlock.FACING, Direction.EAST).with(MachineBlock.LIT, false)
                .modelForState().modelFile(off).rotationY(90).addModel()
                ///////////////
                .partialState().with(MachineBlock.FACING, Direction.NORTH).with(MachineBlock.LIT, true)
                .modelForState().modelFile(on).addModel()

                .partialState().with(MachineBlock.FACING, Direction.SOUTH).with(MachineBlock.LIT, true)
                .modelForState().modelFile(on).rotationY(180).addModel()

                .partialState().with(MachineBlock.FACING, Direction.WEST).with(MachineBlock.LIT, true)
                .modelForState().modelFile(on).rotationY(270).addModel()

                .partialState().with(MachineBlock.FACING, Direction.EAST).with(MachineBlock.LIT, true)
                .modelForState().modelFile(on).rotationY(90).addModel();
    }

    public void machineBlock(MachineBlock block, ResourceLocation front, ResourceLocation front_on, ResourceLocation side, ResourceLocation top) {
        machineBlock(block,
                models().orientable(name(block), side, front, top),
                models().orientable(name(block) + "_on", side, front_on, top));
    }

    private void machineBlock(MachineBlock block) {
        machineBlock(block, extend(blockTexture(block), "_front"), extend(blockTexture(block), "_front_on")
                , blockTexture("machine_side"), blockTexture("machine_top"));
    }

    public ResourceLocation blockTexture(String texture) {
        return new ResourceLocation(SemiRealisticElectricity.MOD_ID, ModelProvider.BLOCK_FOLDER + "/" + texture);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
