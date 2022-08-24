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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fluids.FluidStack;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.recipe.ModRecipes;
import rasmoos.semirealisticelectricity.util.FluidUtils;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class FluidCompactorBuilder implements RecipeBuilder {

    private final Item result;
    private final int count;
    @Nullable
    private String group;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    private final FluidStack[] fluids;
    private final int[] useAmount;
    private final int craftTime;

    public FluidCompactorBuilder(ItemLike result, int count, FluidStack[] fluids, int[] useAmount, int craftTime) {
        this.result = result.asItem();
        this.count = count;
        this.fluids = fluids;
        this.useAmount = useAmount;
        this.craftTime = craftTime;
    }

    public static FluidCompactorBuilder fluidCompact(ItemLike result, FluidStack fluidA, int useA, FluidStack fluidB, int useB, int craftTime) {
        return fluidCompact(result, 1, fluidA, useA, fluidB, useB, craftTime);
    }

    public static FluidCompactorBuilder fluidCompact(ItemLike result, int amount, FluidStack fluidA, int useA, FluidStack fluidB, int useB, int craftTime) {
        return new FluidCompactorBuilder(result, amount, new FluidStack[] {fluidA, fluidB}, new int[]{useA, useB}, craftTime);
    }


    public FluidCompactorBuilder unlockedBy(String name, CriterionTriggerInstance trigger) {
        this.advancement.addCriterion(name, trigger);
        return this;
    }

    @Override
    public FluidCompactorBuilder group(@Nullable String group) {
        this.group = group;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        save(finishedRecipeConsumer, new ResourceLocation(SemiRealisticElectricity.MOD_ID,
                ModItems.getItemName(result) + "_from_fluid_compacting"));
    }

    public void save(Consumer<FinishedRecipe> recipeConsumer, ResourceLocation location) {
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(location)).rewards(AdvancementRewards.Builder.recipe(location)).requirements(RequirementsStrategy.OR);
        recipeConsumer.accept(new FluidCompactorBuilder.Result(location, this.result, this.count, group == null ? "" : group, fluids, useAmount, craftTime, this.advancement, new ResourceLocation(location.getNamespace(), "recipes/" + this.result.getItemCategory().getRecipeFolderName() + "/" + location.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final int count;
        private final String group;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final FluidStack[] fluids;
        private final int[] useAmount;
        private final int craftTime;

        public Result(ResourceLocation id, Item result, int count, String group, FluidStack[] fluids, int[] useAmount, int craftTime, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.group = group;
            this.fluids = fluids;
            this.advancement = advancement;
            this.advancementId = advancementId;
            this.useAmount = useAmount;
            this.craftTime = craftTime;
        }

        public void serializeRecipeData(JsonObject object) {
            if (!this.group.isEmpty()) {
                object.addProperty("group", this.group);
            }

            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("item", Registry.ITEM.getKey(this.result).toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }

            object.add("result", jsonObject);

            object.add("fluidA", FluidUtils.toJson(fluids[0]));
            object.add("fluidB", FluidUtils.toJson(fluids[1]));

            object.addProperty("useA", useAmount[0]);
            object.addProperty("useB", useAmount[1]);
            object.addProperty("craftTime", craftTime);
        }

        public RecipeSerializer<?> getType() {
            return ModRecipes.FLUID_COMPACTING_SERIALIZER.get();
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
