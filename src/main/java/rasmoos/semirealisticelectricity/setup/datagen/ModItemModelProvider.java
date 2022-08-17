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
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.Registration;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(Registration.RAW_COBALT.get());
        simpleItem(Registration.RAW_LEPIDOLITE.get());
        simpleItem(Registration.RAW_MAGNETITE.get());
        simpleItem(Registration.RAW_TIN.get());

        simpleItem(Registration.COBALT_INGOT.get());
        simpleItem(Registration.LITHIUM_DUST.get());
        simpleItem(Registration.MAGNETITE_INGOT.get());
        simpleItem(Registration.TIN_INGOT.get());


        for (RegistryObject<Item> entry : Registration.ITEMS.getEntries()) {
            if (entry.get() instanceof BlockItem blockItem) {
                block(blockItem);
            }
        }
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
