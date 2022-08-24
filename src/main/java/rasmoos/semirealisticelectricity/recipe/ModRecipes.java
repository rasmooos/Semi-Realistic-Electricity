package rasmoos.semirealisticelectricity.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CrusherRecipe>> CRUSHER_SERIALIZER = SERIALIZERS.register("crushing",
            () -> CrusherRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<FluidCompactorRecipe>> FLUID_COMPACTING_SERIALIZER = SERIALIZERS.register("fluid_compacting",
            () -> FluidCompactorRecipe.Serializer.INSTANCE);

}
