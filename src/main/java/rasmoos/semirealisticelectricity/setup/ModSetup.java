package rasmoos.semirealisticelectricity.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("semirealisticelectricity") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MAGNETITE_INGOT.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {

    }

}
