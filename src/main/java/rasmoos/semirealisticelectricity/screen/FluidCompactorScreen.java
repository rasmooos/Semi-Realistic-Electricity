package rasmoos.semirealisticelectricity.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.menu.FluidCompactorMenu;
import rasmoos.semirealisticelectricity.screen.renderer.FluidStackRenderer;
import rasmoos.semirealisticelectricity.screen.renderer.ItemStackRenderer;
import rasmoos.semirealisticelectricity.screen.renderer.ProgressBarRenderer;

public class FluidCompactorScreen extends MachineScreen<FluidCompactorMenu> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/fluid_compactor.png");
    public static final ResourceLocation BUTTON = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/button.png");

    private ProgressBarRenderer progressBarRendererL;
    private ProgressBarRenderer progressBarRendererR;
    private ItemStackRenderer itemStackRenderer;

    public FluidCompactorScreen(FluidCompactorMenu menuType, Inventory inventory, Component component) {
        super(menuType, inventory, component, TEXTURE);
    }

    @Override
    protected void init() {
        super.init();

        addFluidRenderer(new FluidStackRenderer(x + 13, y + 18, ((MachineBlockEntity) menu.entity).getFluidTankCapacity()[0], true, 19, 48));
        addFluidRenderer(new FluidStackRenderer(x + 132, y + 18, ((MachineBlockEntity) menu.entity).getFluidTankCapacity()[1], true, 19, 48));

        progressBarRendererL = new ProgressBarRenderer(x + 42, y + 36, 176, 0, 22, 9, TEXTURE);
        progressBarRendererR = new ProgressBarRenderer(x + 121, y + 36, 0, 0, 22, 9, TEXTURE);

        itemStackRenderer = new ItemStackRenderer(x + 74, y + 59);

        ImageButton buttonRight = new ImageButton(x + 88, y + 60, 16, 16, 16, 16, 0, BUTTON, 32, 16, (b) -> {
            menu.cycleCraftType();
        });
        addRenderableWidget(buttonRight);

        ImageButton buttonLeft = new ImageButton(x + 60, y + 60, 16, 16, 0, 0, 0, BUTTON, 32, 16, (b) -> {
            menu.cycleCraftTypeBack();
        });
        addRenderableWidget(buttonLeft);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        super.renderBg(pPoseStack, pPartialTick, pMouseX, pMouseY);

        if(menu.isCrafting()) {
            progressBarRendererL.setTextureWidth(menu.getScaledProgress());
            progressBarRendererL.render(pPoseStack);

            int progress = menu.getScaledProgress();
            progressBarRendererR = new ProgressBarRenderer(x + 121 - progress, y + 36, 197 - progress, 0, progress, 9, TEXTURE);
            progressBarRendererR.render(pPoseStack);
        }

        itemStackRenderer.render(pPoseStack, menu.getCraftItem());
    }
}
