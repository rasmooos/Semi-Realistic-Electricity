package rasmoos.semirealisticelectricity.setup;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rasmoos.semirealisticelectricity.ClientBinding;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        ClientBinding.registerKeyBindings();
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event){

    }
}
