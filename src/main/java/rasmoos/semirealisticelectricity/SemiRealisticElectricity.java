package rasmoos.semirealisticelectricity;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import rasmoos.semirealisticelectricity.blockentites.ModBlockEntities;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.setup.ClientSetup;
import rasmoos.semirealisticelectricity.setup.ModSetup;
import rasmoos.semirealisticelectricity.world.ModConfiguredFeatures;
import rasmoos.semirealisticelectricity.world.ModPlacedFeatures;

@Mod(SemiRealisticElectricity.MOD_ID)
public class SemiRealisticElectricity {
    public static final String MOD_ID = "semirealisticelectricity";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SemiRealisticElectricity() {

        register();

        // Register the commonSetup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
        if(FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final void register() {
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
