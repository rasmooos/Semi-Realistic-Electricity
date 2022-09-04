package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.screen.menu.IronFurnaceMenu;
import rasmoos.semirealisticelectricity.screen.renderer.ProgressBarRenderer;

public class IronFurnaceScreen extends BaseGuiScreen<IronFurnaceMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/iron_furnace.png");
    private ProgressBarRenderer progressBarRenderer;
    private ProgressBarRenderer fuelBarRenderer;
    public IronFurnaceScreen(IronFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();
        progressBarRenderer = new ProgressBarRenderer(x + 80, y + 35, 176, 14, 22, 17, texture);
        fuelBarRenderer = new ProgressBarRenderer(x + 56, y + 36, 176, 0, 14, 14, texture);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);

        if(menu.isCrafting()) {
            progressBarRenderer.setTextureWidth(menu.getScaledProgress());
            progressBarRenderer.render(pPoseStack);
        }

        if(menu.isBurning()) {
            int fuel = menu.getScaledFuel();
            fuelBarRenderer = new ProgressBarRenderer(x + 56, y + 36 + 14 - fuel, 176, 14 - fuel, 14, fuel, TEXTURE);
            fuelBarRenderer.render(pPoseStack);
        }
    }
}
