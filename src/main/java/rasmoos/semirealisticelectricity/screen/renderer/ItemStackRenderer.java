
// CREDIT: https://github.com/mezz/JustEnoughItems by mezz
// Under MIT-License: https://github.com/mezz/JustEnoughItems/blob/1.18/LICENSE.txt

package rasmoos.semirealisticelectricity.screen.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.List;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.common.platform.IPlatformRenderHelper;
import mezz.jei.common.platform.Services;
import mezz.jei.common.util.ErrorUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class ItemStackRenderer extends InfoArea implements IIngredientRenderer<ItemStack> {
    private static final Logger LOGGER = LogManager.getLogger();



    public ItemStackRenderer(int x, int y) {
        super(new Rect2i(x, y, 16, 16));
    }

    public void render(PoseStack poseStack, @Nullable ItemStack ingredient) {
        if (ingredient != null) {
            poseStack.pushPose();
            poseStack.translate(getX(), getY(), 0);

            PoseStack modelViewStack = RenderSystem.getModelViewStack();
            modelViewStack.pushPose();
            modelViewStack.mulPoseMatrix(poseStack.last().pose());
            RenderSystem.enableDepthTest();
            Minecraft minecraft = Minecraft.getInstance();
            Font font = this.getFontRenderer(minecraft, ingredient);
            ItemRenderer itemRenderer = minecraft.getItemRenderer();
            itemRenderer.renderAndDecorateFakeItem(ingredient, 0, 0);
            itemRenderer.renderGuiItemDecorations(font, ingredient, 0, 0);
            RenderSystem.disableBlend();
            modelViewStack.popPose();
            RenderSystem.applyModelViewMatrix();

            poseStack.popPose();
        }

    }

    public List<Component> getTooltip(ItemStack ingredient, TooltipFlag tooltipFlag) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        try {
            return ingredient.getTooltipLines(player, tooltipFlag);
        } catch (LinkageError | RuntimeException var9) {
            String itemStackInfo = ErrorUtil.getItemStackInfo(ingredient);
            LOGGER.error("Failed to get tooltip: {}", itemStackInfo, var9);
            List<Component> list = new ArrayList();
            MutableComponent crash = Component.translatable("jei.tooltip.error.crash");
            list.add(crash.withStyle(ChatFormatting.RED));
            return list;
        }
    }

    public Font getFontRenderer(Minecraft minecraft, ItemStack ingredient) {
        IPlatformRenderHelper renderHelper = Services.PLATFORM.getRenderHelper();
        return renderHelper.getFontRenderer(minecraft, ingredient);
    }

    @Override
    public void draw(PoseStack transform) {

    }

    public int getWidth() {
        return 16;
    }

    public int getHeight() {
        return 16;
    }
}
