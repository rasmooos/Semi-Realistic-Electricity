package rasmoos.semirealisticelectricity;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import rasmoos.semirealisticelectricity.setup.ClientSetup;
import rasmoos.semirealisticelectricity.setup.ModSetup;
import rasmoos.semirealisticelectricity.setup.Registration;

@Mod(SemiRealisticElectricity.MOD_ID)
public class SemiRealisticElectricity {
    public static final String MOD_ID = "semirealisticelectricity";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SemiRealisticElectricity() {

        Registration.register();

        // Register the commonSetup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        if(FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
