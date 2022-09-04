package rasmoos.semirealisticelectricity.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.recipe.ElectrostaticSeparatorRecipe;
import rasmoos.semirealisticelectricity.recipe.FluidCompactorRecipe;
import rasmoos.semirealisticelectricity.screen.*;

import java.util.List;
import java.util.Objects;

import static rasmoos.semirealisticelectricity.integration.ModRecipeTypes.*;


@JeiPlugin
public class JEITutorialModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(SemiRealisticElectricity.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrusherRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IronFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ElectricFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FluidCompactorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ElectrostaticSeparatorCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<CrusherRecipe> recipes = rm.getAllRecipesFor(CrusherRecipe.Type.INSTANCE);
        registration.addRecipes(CRUSHING, recipes);

        List<FluidCompactorRecipe> rr = rm.getAllRecipesFor(FluidCompactorRecipe.Type.INSTANCE);
        registration.addRecipes(FLUID_COMPACTING, rr);

        List<SmeltingRecipe> r = rm.getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.SMELTING);
        registration.addRecipes(SMELT, r);

        registration.addRecipes(ELECTRIC_SMELT, r);

        List<ElectrostaticSeparatorRecipe> rRR = rm.getAllRecipesFor(ElectrostaticSeparatorRecipe.Type.INSTANCE);
        registration.addRecipes(SEPARATING, rRR);
    }

    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(IronFurnaceScreen.class, 78, 32, 28, 23, SMELT);
        registration.addRecipeClickArea(CrusherScreen.class, 78, 32, 22, 23, CRUSHING);
        registration.addRecipeClickArea(ElectricFurnaceScreen.class, 78, 32, 22, 23, ELECTRIC_SMELT);
        registration.addRecipeClickArea(FluidCompactorScreen.class, 42, 36, 22, 9, FLUID_COMPACTING);
        registration.addRecipeClickArea(FluidCompactorScreen.class, 100, 36, 22, 9, FLUID_COMPACTING);
        registration.addRecipeClickArea(ElectrostaticSeparatorScreen.class, 68, 38, 22, 9, SEPARATING);
    }

    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.IRON_FURNACE_BLOCK.get()), SMELT);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTRIC_FURNACE.get()), ELECTRIC_SMELT);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.CRUSHER_BLOCK.get()), CRUSHING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLUID_COMPACTOR.get()), FLUID_COMPACTING);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.ELECTROSTATIC_SEPARATOR.get()), SEPARATING);
    }
}
