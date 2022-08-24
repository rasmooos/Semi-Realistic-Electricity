package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blockentites.CrusherBlockEntity;
import rasmoos.semirealisticelectricity.screen.renderer.EnergyInfoArea;
import rasmoos.semirealisticelectricity.screen.renderer.FluidStackRenderer;
import rasmoos.semirealisticelectricity.screen.renderer.ProgressBarRenderer;

import java.util.Optional;

public class CrusherScreen extends MachineScreen<CrusherMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/crusher.png");

    private ProgressBarRenderer progressBarRenderer;

    public CrusherScreen(CrusherMenu menuType, Inventory inventory, Component component) {
        super(menuType, inventory, component, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
        addFluidRenderer(new FluidStackRenderer(x + 8, y + 17, menu.getFluidTankCapacity(0), true, 19, 48));
        addFluidRenderer(new FluidStackRenderer(x + 29, y + 17, menu.getFluidTankCapacity(1), true, 19, 48));



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
