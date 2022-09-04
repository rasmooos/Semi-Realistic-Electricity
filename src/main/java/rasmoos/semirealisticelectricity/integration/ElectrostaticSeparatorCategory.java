package rasmoos.semirealisticelectricity.integration;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.Component;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.ElectrostaticSeparatorRecipe;
import rasmoos.semirealisticelectricity.screen.CrusherScreen;
import rasmoos.semirealisticelectricity.screen.ElectrostaticSeparatorScreen;

public class ElectrostaticSeparatorCategory extends MachineRecipeCategory<ElectrostaticSeparatorRecipe> {

    public ElectrostaticSeparatorCategory(IGuiHelper helper) {
        super(helper, ElectrostaticSeparatorScreen.TEXTURE, 36, 15, 106, 58,
                ModBlocks.ELECTROSTATIC_SEPARATOR.get(),
                ModRecipeTypes.SEPARATING);

        addProgressBar(ElectrostaticSeparatorScreen.TEXTURE, 176, 11, 22, 9, 72,
                IDrawableAnimated.StartDirection.LEFT, false, 68 - 36, 38 - 15);
    }

    @Override
    public RecipeType<ElectrostaticSeparatorRecipe> getRecipeType() {
        return ModRecipeTypes.SEPARATING;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.integration.semirealisticelectricity.category.separating");
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ElectrostaticSeparatorRecipe recipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 45 - 36, 17 - 15).addIngredients(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 97 - 36, 35 - 15).addItemStack(recipe.getMainOutput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 118 - 36, 35 - 15).addItemStack(recipe.getSecondaryOutput());
    }
}
