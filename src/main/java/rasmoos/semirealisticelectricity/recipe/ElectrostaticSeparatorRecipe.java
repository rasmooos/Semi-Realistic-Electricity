package rasmoos.semirealisticelectricity.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

public class ElectrostaticSeparatorRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack mainOutput;
    private final ItemStack secondaryOutput;
    private final Ingredient input;

    public ElectrostaticSeparatorRecipe(ResourceLocation id, ItemStack mainOutput, ItemStack secondaryOutput, Ingredient input) {
        this.id = id;
        this.mainOutput = mainOutput.copy();
        this.secondaryOutput = secondaryOutput.copy();
        this.input = input;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        return input.test(container.getItem(0));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(input);
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return mainOutput;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return mainOutput.copy();
    }

    public ItemStack getMainOutput() {
        return mainOutput;
    }

    public ItemStack getSecondaryOutput() {
        return secondaryOutput;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ElectrostaticSeparatorRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ElectrostaticSeparatorRecipe.Type.INSTANCE;
    }

    public static class Type implements RecipeType<ElectrostaticSeparatorRecipe> {
        private Type() {
        }

        public static final ElectrostaticSeparatorRecipe.Type INSTANCE = new ElectrostaticSeparatorRecipe.Type();
    }

    public static class Serializer implements RecipeSerializer<ElectrostaticSeparatorRecipe> {
        public static final ElectrostaticSeparatorRecipe.Serializer INSTANCE = new ElectrostaticSeparatorRecipe.Serializer();

        @Override
        public ElectrostaticSeparatorRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack mainResult = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "mainResult"));
            ItemStack secondaryResult = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "secondaryResult"));

            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));

            return new ElectrostaticSeparatorRecipe(id, mainResult, secondaryResult, input);
        }

        @Override
        public ElectrostaticSeparatorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient input = Ingredient.fromNetwork(buf);

            ItemStack mainResult = buf.readItem();
            ItemStack secondaryResult = buf.readItem();
            return new ElectrostaticSeparatorRecipe(id, mainResult, secondaryResult, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ElectrostaticSeparatorRecipe recipe) {
            recipe.input.toNetwork(buf);
            buf.writeItemStack(recipe.getMainOutput(), false);
            buf.writeItemStack(recipe.getSecondaryOutput(), false);
        }
    }
}
