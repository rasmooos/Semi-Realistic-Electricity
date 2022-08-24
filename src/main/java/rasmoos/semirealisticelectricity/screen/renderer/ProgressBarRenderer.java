package rasmoos.semirealisticelectricity.screen.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class ProgressBarRenderer extends GuiComponent {

    private int renderX, renderY;
    private int textureX, textureY, textureWidth, textureHeight;
    private ResourceLocation texture;

    public ProgressBarRenderer(int renderX, int renderY, int textureX, int textureY, int textureWidth, int textureHeight, ResourceLocation texture) {
        this.renderX = renderX;
        this.renderY = renderY;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
    }

    public void render(PoseStack pPoseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);

        blit(pPoseStack, renderX, renderY, textureX, textureY, textureWidth, textureHeight);
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }
}
