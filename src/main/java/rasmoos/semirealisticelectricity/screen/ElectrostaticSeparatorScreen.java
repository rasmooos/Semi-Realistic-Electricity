package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.screen.menu.ElectrostaticSeparatorMenu;
import rasmoos.semirealisticelectricity.screen.renderer.ProgressBarRenderer;

public class ElectrostaticSeparatorScreen extends MachineScreen<ElectrostaticSeparatorMenu> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/electrostatic_separator.png");

    private ProgressBarRenderer progressBarRenderer;

    public ElectrostaticSeparatorScreen(ElectrostaticSeparatorMenu menuType, Inventory inventory, Component component) {
        super(menuType, inventory, component, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
        progressBarRenderer = new ProgressBarRenderer(x + 68, y + 38, 176, 11, 22, 9, texture);
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
