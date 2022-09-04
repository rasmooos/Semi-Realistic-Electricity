package rasmoos.semirealisticelectricity.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.common.plugins.vanilla.cooking.AbstractCookingCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.screen.CrusherScreen;
import rasmoos.semirealisticelectricity.screen.ElectricFurnaceScreen;

import static rasmoos.semirealisticelectricity.integration.ModRecipeTypes.ELECTRIC_SMELT;

public class ElectricFurnaceRecipeCategory extends AbstractCookingCategory<SmeltingRecipe> {
    private IDrawableAnimated progressBar;
    private final IDrawable background;
    public ElectricFurnaceRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, ModBlocks.ELECTRIC_FURNACE.get(), "jei.integration.semirealisticelectricity.category.electric_smelting", 120);
        this.background = guiHelper.createDrawable(ElectricFurnaceScreen.TEXTURE, 41, 12, 105, 63);
        progressBar = guiHelper.createAnimatedDrawable(guiHelper.createDrawable(ElectricFurnaceScreen.TEXTURE, 176, 11, 22, 9), 72,
                IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public RecipeType<SmeltingRecipe> getRecipeType() {
        return ELECTRIC_SMELT;
    }

    public void draw(SmeltingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        progressBar.draw(poseStack, 79 - 41, 38 - 12);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SmeltingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56 - 41, 17 - 12).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116 - 41, 35 - 12).addItemStack(recipe.getResultItem());
    }
}