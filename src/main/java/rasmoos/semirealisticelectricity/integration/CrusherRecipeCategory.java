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
import rasmoos.semirealisticelectricity.screen.FluidCompactorScreen;

public class CrusherRecipeCategory extends MachineRecipeCategory<CrusherRecipe> {


    public CrusherRecipeCategory(IGuiHelper helper) {
        super(helper, CrusherScreen.TEXTURE, 41, 12, 105, 63,
                ModBlocks.CRUSHER_BLOCK.get(),
                ModRecipeTypes.CRUSHING);

        addProgressBar(CrusherScreen.TEXTURE, 176, 11, 22, 9, 72,
                IDrawableAnimated.StartDirection.LEFT, false, 79 - 41, 38 - 12);
    }

    @Override
    public RecipeType<CrusherRecipe> getRecipeType() {
        return ModRecipeTypes.CRUSHING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.integration.semirealisticelectricity.category.crushing");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrusherRecipe crusherRecipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 56 - 41, 17 - 12).addIngredients(crusherRecipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116 - 41, 35 - 12).addItemStack(crusherRecipe.getResultItem());
    }
}
