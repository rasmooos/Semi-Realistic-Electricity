package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.screen.menu.CrusherMenu;
import rasmoos.semirealisticelectricity.screen.renderer.ProgressBarRenderer;

public class CrusherScreen extends MachineScreen<CrusherMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/crusher.png");

    private ProgressBarRenderer progressBarRenderer;

    public CrusherScreen(CrusherMenu menuType, Inventory inventory, Component component) {
        super(menuType, inventory, component, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
//        addFluidRenderer(new FluidStackRenderer(x + 8, y + 17, menu.getFluidTankCapacity(0), true, 19, 48));
//        addFluidRenderer(new FluidStackRenderer(x + 29, y + 17, menu.getFluidTankCapacity(1), true, 19, 48));



        progressBarRenderer = new ProgressBarRenderer(x + 79, y + 38, 176, 11, 22, 9, texture);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);

        if(menu.isCrafting()) {

            progressBarRenderer.setTextureWidth(menu.getScaledProgress());
            progressBarRenderer.render(pPoseStack);
        }
    }
}
