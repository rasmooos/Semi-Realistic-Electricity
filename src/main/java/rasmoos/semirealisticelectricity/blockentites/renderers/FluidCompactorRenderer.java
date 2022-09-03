package rasmoos.semirealisticelectricity.blockentites.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import rasmoos.semirealisticelectricity.blockentites.FluidCompactorEntity;

public class FluidCompactorRenderer implements BlockEntityRenderer<FluidCompactorEntity> {

    public FluidCompactorRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(FluidCompactorEntity entity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        //TODO: Render fluid tanks on block

//        FluidStack tankLeft = entity.getFluid(0);
//        FluidStack tankRight = entity.getFluid(1);
//        float fluidScaleLeft = tankLeft.getAmount() / ( 1.f * entity.getFluidTankCapacity()[0]);
//        float fluidScaleRight = tankRight.getAmount() / ( 1.f * entity.getFluidTankCapacity()[1]);
    }

    public static TextureAtlasSprite getFluidTexture(@NotNull FluidStack fluidStack) {
        IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation spriteLocation = properties.getStillTexture(fluidStack);

        return getSprite(spriteLocation);
    }

    public static TextureAtlasSprite getSprite(ResourceLocation spriteLocation) {
        return Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(spriteLocation);
    }
}
