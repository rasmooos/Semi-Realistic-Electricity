package rasmoos.semirealisticelectricity;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import rasmoos.semirealisticelectricity.blockentites.ModBlockEntities;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.recipe.ModRecipes;
import rasmoos.semirealisticelectricity.screen.menu.ModMenuTypes;
import rasmoos.semirealisticelectricity.setup.ClientSetup;
import rasmoos.semirealisticelectricity.setup.ModSetup;
import rasmoos.semirealisticelectricity.world.ModConfiguredFeatures;
import rasmoos.semirealisticelectricity.world.ModPlacedFeatures;

@Mod(SemiRealisticElectricity.MOD_ID)
public class SemiRealisticElectricity {
    public static final String MOD_ID = "semirealisticelectricity";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SemiRealisticElectricity() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        register(eventBus);

        // Register the commonSetup method for modloading
        eventBus.addListener(ModSetup::init);

        if(FMLEnvironment.dist.isClient()) {
            eventBus.addListener(ClientSetup::init);
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final void register(IEventBus eventBus) {

        ModBlocks.BLOCKS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(eventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(eventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(eventBus);
        ModMenuTypes.MENUS.register(eventBus);
        ModRecipes.SERIALIZERS.register(eventBus);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
