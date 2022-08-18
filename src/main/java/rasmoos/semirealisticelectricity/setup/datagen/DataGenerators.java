package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(generator, existingFileHelper);

        generator.addProvider(true, blockTagProvider);
        generator.addProvider(true, new ModItemsTagProvider(generator, blockTagProvider, existingFileHelper));
        generator.addProvider(true, new ModRecipeProvider(generator));
        generator.addProvider(true, new ModLootTableProvider(generator));
        generator.addProvider(true, new ModBlockStateProvider(generator, existingFileHelper));
//        generator.addProvider(true, new ModBlockModelProvider(generator, existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(generator, existingFileHelper));
    }

}
