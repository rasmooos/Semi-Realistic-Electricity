package rasmoos.semirealisticelectricity.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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
import rasmoos.semirealisticelectricity.recipe.FluidCompactorRecipe;
import rasmoos.semirealisticelectricity.screen.FluidCompactorScreen;

import java.util.List;

public class FluidCompactorRecipeCategory extends MachineRecipeCategory<FluidCompactorRecipe> {


    public FluidCompactorRecipeCategory(IGuiHelper helper) {
        super(helper, FluidCompactorScreen.TEXTURE, 6, 5, 166, 76,
                ModBlocks.FLUID_COMPACTOR.get(),
                ModRecipeTypes.FLUID_COMPACTING);

        addProgressBar(FluidCompactorScreen.TEXTURE, 176, 0, 22, 9, 72,
                IDrawableAnimated.StartDirection.LEFT, false, 42 - 6, 36 - 5);

        addProgressBar(FluidCompactorScreen.TEXTURE, 176, 0, 22, 9, 72,
                IDrawableAnimated.StartDirection.RIGHT, false, 100 - 6, 36 - 5);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.integration.semirealisticelectricity.category.fluid_compacting");
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FluidCompactorRecipe fluidCompactorRecipe, IFocusGroup iFocusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 74 - 6, 59 - 5).addIngredients(fluidCompactorRecipe.getRecipeItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 74 - 6, 32 - 5).addItemStack(fluidCompactorRecipe.getResultItem());

        builder.addSlot(RecipeIngredientRole.INPUT, 13 - 6, 18 - 5)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(fluidCompactorRecipe.getFluids()[0], fluidCompactorRecipe.getFluids()[1]))
                .setFluidRenderer(8000, false, 19, 48);

        builder.addSlot(RecipeIngredientRole.INPUT, 132 - 6, 18 - 5)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(fluidCompactorRecipe.getFluids()[1], fluidCompactorRecipe.getFluids()[0]))
                .setFluidRenderer(8000, false, 19, 48);
    }
}
