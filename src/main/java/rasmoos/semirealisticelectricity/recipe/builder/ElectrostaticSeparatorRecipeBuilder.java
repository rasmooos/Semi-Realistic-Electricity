package rasmoos.semirealisticelectricity.recipe.builder;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.recipe.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ElectrostaticSeparatorRecipeBuilder implements RecipeBuilder {
    private final Item mainResult;
    private final Item secondaryResult;
    private final int mainCount;
    private final int secondaryCount;
    private final Ingredient ingredient;
    @javax.annotation.Nullable
    private String group;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public ElectrostaticSeparatorRecipeBuilder(ItemLike mainResult, int mainCount, ItemLike secondaryResult, int secondaryCount, Ingredient ingredient) {
        this.mainResult = mainResult.asItem();
        this.secondaryResult = secondaryResult.asItem();
        this.mainCount = mainCount;
        this.secondaryCount = secondaryCount;
        this.ingredient = ingredient;
    }

    public static ElectrostaticSeparatorRecipeBuilder crush(ItemLike mainResult, int mainCount, ItemLike secondaryResult, int secondaryCount, TagKey<Item> tag) {
        return separate(mainResult, mainCount, secondaryResult, secondaryCount, Ingredient.of(tag));
    }

    public static ElectrostaticSeparatorRecipeBuilder separate(ItemLike mainResult, int mainCount, ItemLike secondaryResult, int secondaryCount, ItemLike ingredient) {
        return new ElectrostaticSeparatorRecipeBuilder(mainResult, mainCount, secondaryResult, secondaryCount, Ingredient.of(ingredient));
    }

    public static ElectrostaticSeparatorRecipeBuilder separate(ItemLike mainResult, int mainCount, ItemLike secondaryResult, int secondaryCount, Ingredient ingredient) {
        return new ElectrostaticSeparatorRecipeBuilder(mainResult, mainCount, secondaryResult, secondaryCount, ingredient);
    }

    public ElectrostaticSeparatorRecipeBuilder unlockedBy(String name, CriterionTriggerInstance trigger) {
        this.advancement.addCriterion(name, trigger);
        return this;
    }

    @Override
    public ElectrostaticSeparatorRecipeBuilder group(@javax.annotation.Nullable String group) {
        this.group = group;
        return this;
    }

    public Item getResult() {
        return this.mainResult;
    }

    @Override
    public void save(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        save(finishedRecipeConsumer, new ResourceLocation(SemiRealisticElectricity.MOD_ID,
                ModItems.getItemName(mainResult) + "_from_crushing_" + ModItems.getItemName(ingredient.getItems()[0].getItem())));
    }

    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        ensureValid(location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new ElectrostaticSeparatorRecipeBuilder.Result(location,
                this.mainResult, this.mainCount, this.secondaryResult, this.secondaryCount, group == null ? "" : group, this.ingredient, this.advancement,
                new ResourceLocation(location.getNamespace(), "recipes/" + this.mainResult.getItemCategory().getRecipeFolderName() + "/" + location.getPath())));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item mainResult;
        private final Item secondaryResult;
        private final int mainCount;
        private final int secondaryCount;
        private final String group;
        private final Ingredient ingredient;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item mainResult, int mainCount, Item secondaryResult, int secondaryCount, String group, Ingredient ingredient, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.mainResult = mainResult;
            this.secondaryResult = secondaryResult;
            this.mainCount = mainCount;
            this.secondaryCount = secondaryCount;
            this.group = group;
            this.ingredient = ingredient;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(JsonObject object) {
            if (!this.group.isEmpty()) {
                object.addProperty("group", this.group);
            }

            object.add("ingredient", ingredient.toJson());

            JsonObject main = new JsonObject();

            main.addProperty("item", Registry.ITEM.getKey(this.mainResult).toString());
            if (this.mainCount > 1) {
                main.addProperty("count", this.mainCount);
            }

            object.add("mainResult", main);

            JsonObject sec = new JsonObject();
            sec.addProperty("item", Registry.ITEM.getKey(this.secondaryResult).toString());
            if (this.secondaryCount > 1) {
                sec.addProperty("count", this.secondaryCount);
            }

            object.add("secondaryResult", sec);
        }

        public RecipeSerializer<?> getType() {
            return ModRecipes.ELECTROSTATIC_SEPARATOR_SERIALIZER.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }

    }
}
