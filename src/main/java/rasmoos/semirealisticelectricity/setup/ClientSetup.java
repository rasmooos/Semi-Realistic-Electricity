package rasmoos.semirealisticelectricity.setup;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rasmoos.semirealisticelectricity.ClientBinding;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blockentites.ModBlockEntities;
import rasmoos.semirealisticelectricity.blockentites.renderers.FluidCompactorRenderer;
import rasmoos.semirealisticelectricity.screen.*;
import rasmoos.semirealisticelectricity.screen.menu.ModMenuTypes;

@Mod.EventBusSubscriber(modid = SemiRealisticElectricity.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    public static void init(final FMLClientSetupEvent event) {
        ClientBinding.registerKeyBindings();

        MenuScreens.register(ModMenuTypes.IRON_FURNACE_MENU.get(), IronFurnaceScreen::new);
        MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
        MenuScreens.register(ModMenuTypes.FLUID_COMPACTOR_MENU.get(), FluidCompactorScreen::new);
        MenuScreens.register(ModMenuTypes.ELECTROSTATIC_SEPARATOR_MENU.get(), ElectrostaticSeparatorScreen::new);
        MenuScreens.register(ModMenuTypes.ELECTRIC_FURNACE_MENU.get(), ElectricFurnaceScreen::new);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.FLUID_COMPACTOR_ENTITY.get(), FluidCompactorRenderer::new);
    }
}
