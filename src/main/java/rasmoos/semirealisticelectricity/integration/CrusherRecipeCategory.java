package rasmoos.semirealisticelectricity.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.screen.CrusherScreen;

public class CrusherRecipeCategory implements IRecipeCategory<CrusherRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "crushing");

    private final IDrawable background;
    private final IDrawable icon;

    private final IDrawable progressBar;

    public CrusherRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(CrusherScreen.TEXTURE, 0, 0, 175, 80);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRUSHER_BLOCK.get()));
        progressBar = helper.createAnimatedDrawable(helper.createDrawable(CrusherScreen.TEXTURE, 176, 11, 22, 9), 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public RecipeType<CrusherRecipe> getRecipeType() {
        return new RecipeType<>(UID, CrusherRecipe.class);
    }

    public Class<? extends CrusherRecipe> getRecipeClass() {
        return CrusherRecipe.class;
    }

    @Override
    public void draw(CrusherRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        progressBar.draw(stack, 79, 38);
    }

    @Override
    public Component getTitle() {
        return Component.literal("Crusher");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }



    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrusherRecipe crusherRecipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56, 17).addIngredients(crusherRecipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(crusherRecipe.getResultItem());
    }
}
