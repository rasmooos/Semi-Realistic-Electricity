package rasmoos.semirealisticelectricity.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.util.FluidUtils;

public class FluidCompactorRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient recipeItem;
    private final FluidStack[] fluids;
    private final int[] useAmount;
    private final int craftTime;

    public FluidCompactorRecipe(ResourceLocation id, ItemStack output, Ingredient recipeItem, FluidStack[] fluids, int[] useAmount, int craftTime) {
        this.id = id;
        this.output = output;
        this.recipeItem = recipeItem;
        this.fluids = fluids;
        this.useAmount = useAmount;
        this.craftTime = craftTime;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return Recipe.super.getIngredients();
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return recipeItem.test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer p_44001_) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public FluidStack[] getFluids() {
        return fluids;
    }

    public int[] getUseAmount() {
        return useAmount;
    }

    public int getCraftTime() {
        return craftTime;
    }

    public static class Type implements RecipeType<FluidCompactorRecipe> {
        private Type() {
        }

        public static final FluidCompactorRecipe.Type INSTANCE = new FluidCompactorRecipe.Type();
        public static final String ID = "fluid_compacting";
    }

    public static class Serializer implements RecipeSerializer<FluidCompactorRecipe> {
        public static final FluidCompactorRecipe.Serializer INSTANCE = new FluidCompactorRecipe.Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(SemiRealisticElectricity.MOD_ID,"fluid_compacting");

        @Override
        public FluidCompactorRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));

            FluidStack fluidA = FluidUtils.readFluid(json.get("fluidA").getAsJsonObject());
            FluidStack fluidB = FluidUtils.readFluid(json.get("fluidB").getAsJsonObject());

            int useA = GsonHelper.getAsInt(json, "useA");
            int useB = GsonHelper.getAsInt(json, "useB");

            int craftTime = GsonHelper.getAsInt(json, "craftTime");

            return new FluidCompactorRecipe(id, result, Ingredient.of(result), new FluidStack[]{fluidA, fluidB}, new int[]{useA, useB}, craftTime);
        }

        @Override
        public FluidCompactorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack output = buf.readItem();

            FluidStack fluidA = buf.readFluidStack();
            FluidStack fluidB = buf.readFluidStack();

            int[] useAmount = buf.readVarIntArray();

            int craftTime = buf.readInt();

            return new FluidCompactorRecipe(id, output, Ingredient.of(output), new FluidStack[]{fluidA, fluidB}, useAmount, craftTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, FluidCompactorRecipe recipe) {
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeFluidStack(recipe.fluids[0]);
            buf.writeFluidStack(recipe.fluids[1]);
            buf.writeVarIntArray(recipe.useAmount);
            buf.writeInt(recipe.craftTime);
        }
    }
}
