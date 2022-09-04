package rasmoos.semirealisticelectricity.integration;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.recipe.CrusherRecipe;
import rasmoos.semirealisticelectricity.recipe.ElectrostaticSeparatorRecipe;
import rasmoos.semirealisticelectricity.recipe.FluidCompactorRecipe;

public class ModRecipeTypes {

    public static final RecipeType<CrusherRecipe> CRUSHING =
            RecipeType.create(SemiRealisticElectricity.MOD_ID, "crushing", CrusherRecipe.class);
    public static final RecipeType<FluidCompactorRecipe> FLUID_COMPACTING =
            RecipeType.create(SemiRealisticElectricity.MOD_ID, "fluid_compacting", FluidCompactorRecipe.class);
    public static final RecipeType<SmeltingRecipe> SMELT = RecipeType.create(SemiRealisticElectricity.MOD_ID, "smelting", SmeltingRecipe.class);
    public static final RecipeType<SmeltingRecipe> ELECTRIC_SMELT =
            RecipeType.create(SemiRealisticElectricity.MOD_ID, "electric_smelting", SmeltingRecipe.class);
    public static final RecipeType<ElectrostaticSeparatorRecipe> SEPARATING =
            RecipeType.create(SemiRealisticElectricity.MOD_ID, "separating", ElectrostaticSeparatorRecipe.class);

}
