package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import rasmoos.semirealisticelectricity.screen.renderer.InfoArea;
import rasmoos.semirealisticelectricity.util.MouseUtil;

public class BaseGuiScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {

    protected final ResourceLocation texture;
    protected int x, y;

    public BaseGuiScreen(T menuType, Inventory inventory, Component component, ResourceLocation texture) {
        super(menuType, inventory, component);

        this.texture = texture;
    }

    @Override
    protected void init() {
        super.init();
        x = (width - imageWidth) / 2;
        y = (height - imageHeight) / 2;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        x = (width - imageWidth) / 2;
        y = (height - imageHeight) / 2;
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int mouseX, int mouseY) {
        super.renderLabels(pPoseStack, mouseX, mouseY);
    }

    public static boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

    public static boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x, y, width, height);
    }

    public static boolean isMouseAboveArea(int pMouseX, int pMouseY, InfoArea infoArea) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, infoArea.getX(), infoArea.getY(), infoArea.getWidth(), infoArea.getHeight());
    }
}
