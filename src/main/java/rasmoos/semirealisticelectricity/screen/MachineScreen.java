package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.menu.MachineMenu;
import rasmoos.semirealisticelectricity.screen.renderer.EnergyInfoArea;
import rasmoos.semirealisticelectricity.screen.renderer.FluidStackRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MachineScreen<T extends MachineMenu> extends BaseGuiScreen<T> {

    private EnergyInfoArea energyInfoArea;
    private List<FluidStackRenderer> fluidStackRenderers = new ArrayList<>();

    public MachineScreen(T menuType, Inventory inventory, Component component, ResourceLocation texture) {
        super(menuType, inventory, component, texture);
    }

    @Override
    protected void init() {
        super.init();
        fluidStackRenderers.clear();
        energyInfoArea = new EnergyInfoArea(x + 163, y + 10, ((MachineBlockEntity) menu.entity).getEnergyStorage(), 5, 64);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);

        energyInfoArea.draw(pPoseStack);

        for(int i = 0; i < fluidStackRenderers.size(); i++) {
            FluidStackRenderer fluidRenderer = fluidStackRenderers.get(i);
            fluidRenderer.render(pPoseStack, menu.getFluid(i));
        }
    }


    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        super.renderLabels(pPoseStack, pMouseX, pMouseY);

        if(isMouseAboveArea(pMouseX, pMouseY, energyInfoArea)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
        }

        for(int i = 0; i < fluidStackRenderers.size(); i++) {
            FluidStackRenderer fluidRenderer = fluidStackRenderers.get(i);

            if(isMouseAboveArea(pMouseX, pMouseY, fluidRenderer.getX(), fluidRenderer.getY(), fluidRenderer.getWidth(), fluidRenderer.getHeight())) {
                renderTooltip(pPoseStack, fluidRenderer.getTooltip(menu.getFluid(i), TooltipFlag.Default.NORMAL),Optional.empty(), pMouseX - x, pMouseY - y);
            }
        }


    }

    public void addFluidRenderer(FluidStackRenderer renderer) {
        fluidStackRenderers.add(renderer);
    }
}
