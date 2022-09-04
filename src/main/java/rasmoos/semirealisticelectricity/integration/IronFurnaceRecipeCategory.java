package rasmoos.semirealisticelectricity.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.common.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;

import static rasmoos.semirealisticelectricity.integration.ModRecipeTypes.SMELT;

public class IronFurnaceRecipeCategory extends AbstractCookingCategory<SmeltingRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "smelting");

    public IronFurnaceRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.IRON_FURNACE_BLOCK.get(), "jei.integration.semirealisticelectricity.category.iron_smelting", 120);
    }

    @Override
    public RecipeType<SmeltingRecipe> getRecipeType() {
        return SMELT;
    }

    public void draw(SmeltingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        this.animatedFlame.draw(poseStack, 1, 20);
        IDrawableAnimated arrow = this.getArrow(recipe);
        arrow.draw(poseStack, 24, 18);
    }
}
