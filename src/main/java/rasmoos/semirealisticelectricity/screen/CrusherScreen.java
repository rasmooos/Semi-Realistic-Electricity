package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blockentites.CrusherBlockEntity;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.renderer.EnergyInfoArea;

import java.util.Optional;

public class CrusherScreen extends MachineScreen<CrusherMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/crusher.png");

    private EnergyInfoArea energyInfoArea;

    public CrusherScreen(CrusherMenu menuType, Inventory inventory, Component component) {
        super(menuType, inventory, component, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoArea(x + 163, y + 10, ((CrusherBlockEntity) menu.entity).getEnergyStorage(), 5, 64);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;


        if(menu.isCrafting()) {
            blit(pPoseStack, x + 79, y + 38, 176, 11, menu.getScaledProgress(), 9);
        }


        energyInfoArea.draw(pPoseStack);
//        int full = menu.getScaledEnergyLevel();
//        blit(pPoseStack, x + 164, y + 72 - (full), 176, 20, 5, full);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 163, 10, 5, 64)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }
}
