package rasmoos.semirealisticelectricity;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientBinding {


    public static void registerKeyBindings() {

    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {

    }
}
