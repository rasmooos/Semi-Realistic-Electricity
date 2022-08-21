package rasmoos.semirealisticelectricity.recipe.builder;

import com.google.gson.JsonArray;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.recipe.ModRecipes;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class CrusherRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final int count;
    private final Ingredient ingredient;
    @Nullable
    private String group;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public CrusherRecipeBuilder(ItemLike result, int count, Ingredient ingredient) {
        this.result = result.asItem();
        this.count = count;
        this.ingredient = ingredient;
    }

    public static CrusherRecipeBuilder crush(ItemLike result, ItemLike ingredient) {
        return crush(result, 1, ingredient);
    }

    public static CrusherRecipeBuilder crush(ItemLike result, TagKey<Item> tag) {
        return crush(result, 1, Ingredient.of(tag));
    }

    public static CrusherRecipeBuilder crush(ItemLike result, int amount, TagKey<Item> tag) {
        return crush(result, amount, Ingredient.of(tag));
    }

    public static CrusherRecipeBuilder crush(ItemLike result, int amount, ItemLike ingredient) {
        return new CrusherRecipeBuilder(result, amount, Ingredient.of(ingredient));
    }

    public static CrusherRecipeBuilder crush(ItemLike result, int amount, Ingredient ingredient) {
        return new CrusherRecipeBuilder(result, amount, ingredient);
    }

    public CrusherRecipeBuilder unlockedBy(String name, CriterionTriggerInstance trigger) {
        this.advancement.addCriterion(name, trigger);
        return this;
    }

    @Override
    public CrusherRecipeBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        save(finishedRecipeConsumer, new ResourceLocation(SemiRealisticElectricity.MOD_ID,
                ModItems.getItemName(result) + "_from_crushing"));
    }

    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        ensureValid(location);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new CrusherRecipeBuilder.Result(location, this.result, this.count, group == null ? "" : group, this.ingredient, this.advancement, new ResourceLocation(location.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + location.getPath())));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final Ingredient ingredient;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item result, int count, String group, Ingredient ingredient, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.ingredient = ingredient;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        public void serializeRecipeData(JsonObject object) {
            if (!this.group.isEmpty()) {
                object.addProperty("group", this.group);
            }

            JsonArray jsonarray = new JsonArray();
            jsonarray.add(ingredient.toJson());
            object.add("ingredients", jsonarray);

            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            object.add("result", jsonObject);
        }

        public RecipeSerializer<?> getType() {
            return ModRecipes.CRUSHER_SERIALIZER.get();
        }

        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
        
    }
    
}
