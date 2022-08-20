package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.items.ModItems;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.RAW_COBALT.get());
        simpleItem(ModItems.RAW_LEPIDOLITE.get());
        simpleItem(ModItems.RAW_MAGNETITE.get());
        simpleItem(ModItems.RAW_TIN.get());

        simpleItem(ModItems.COBALT_INGOT.get());
        simpleItem(ModItems.LITHIUM_DUST.get());
        simpleItem(ModItems.MAGNETITE_INGOT.get());
        simpleItem(ModItems.TIN_INGOT.get());
        simpleItem(ModItems.RUBBER_TAP.get());
        simpleItem(ModItems.RUBBER_RESIN.get());


        block(ModBlocks.COBALT_ORE.get());
        block(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        block(ModBlocks.MAGNETITE_ORE.get());
        block(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get());
        block(ModBlocks.TIN_ORE.get());
        block(ModBlocks.DEEPSLATE_TIN_ORE.get());
        block(ModBlocks.LEPIDOLITE_ORE.get());
        block(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get());

        block(ModBlocks.RUBBER_PLANKS.get());
        block(ModBlocks.RUBBER_LOG.get());
        block(ModBlocks.STRIPPED_RUBBER_LOG.get());

        block(ModBlocks.IRON_FURNACE_BLOCK.get());
        block(ModBlocks.CRUSHER_BLOCK.get());
    }

    private ItemModelBuilder block(Block block) {
        ResourceLocation id_block = ForgeRegistries.BLOCKS.getKey(block);
        ResourceLocation id_item = ForgeRegistries.ITEMS.getKey(block.asItem());
        return withExistingParent(id_item.getPath(), modid + ":block/" + id_block.getPath());
    }

    protected ItemModelBuilder block(BlockItem blockItem) {
        ResourceLocation id_block = ForgeRegistries.BLOCKS.getKey(blockItem.getBlock());
        ResourceLocation id_item = ForgeRegistries.ITEMS.getKey(blockItem);
        return withExistingParent(id_item.getPath(), modid + ":block/" + id_block.getPath());
    }

    private ItemModelBuilder simpleItem(Item item) {
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(item);
        return withExistingParent(id.getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SemiRealisticElectricity.MOD_ID,"item/" + id.getPath()));
    }


}
