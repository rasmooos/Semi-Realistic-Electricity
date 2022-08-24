package rasmoos.semirealisticelectricity.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rasmoos.semirealisticelectricity.ClientBinding;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.screen.CrusherScreen;
import rasmoos.semirealisticelectricity.screen.FluidCompactorScreen;
import rasmoos.semirealisticelectricity.screen.IronFurnaceScreen;
import rasmoos.semirealisticelectricity.screen.menu.ModMenuTypes;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        ClientBinding.registerKeyBindings();

        MenuScreens.register(ModMenuTypes.IRON_FURNACE_MENU.get(), IronFurnaceScreen::new);
        MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
        MenuScreens.register(ModMenuTypes.FLUID_COMPACTOR_MENU.get(), FluidCompactorScreen::new);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event){

    }
}
