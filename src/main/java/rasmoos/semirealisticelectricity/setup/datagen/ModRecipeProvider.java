package rasmoos.semirealisticelectricity.setup.datagen;

import com.google.common.collect.ImmutableList;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.items.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    protected static final ImmutableList<ItemLike> TIN_SMELTABLES = ImmutableList.of(ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get(),
            ModItems.RAW_TIN.get());
    protected static final ImmutableList<ItemLike> COBALT_SMELTABLES = ImmutableList.of(ModBlocks.COBALT_ORE.get(), ModBlocks.DEEPSLATE_COBALT_ORE.get(),
            ModItems.RAW_COBALT.get());
    protected static final ImmutableList<ItemLike> MAGNETITE_SMELTABLES = ImmutableList.of(ModBlocks.MAGNETITE_ORE.get(), ModBlocks.DEEPSLATE_MAGNETITE_ORE.get(),
            ModItems.RAW_MAGNETITE.get());
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
//        ShapedRecipeBuilder.shaped(Registration.COBALT_INGOT.get())
//                .define('E', Registration.RAW_COBALT.get())
//                .pattern("EEE")
//                .unlockedBy("has_raw_cobalt", inventoryTrigger(ItemPredicate.Builder.item().of(Registration.RAW_COBALT.get()).build()))
//                .save(pFinishedRecipeConsumer);

//        ShapelessRecipeBuilder.shapeless(Registration.RUBBER_PLANKS.get(), 4).requires(Registration.RUBBER_LOG.get())
//                .unlockedBy("has_rubber_log", inventoryTrigger(ItemPredicate.Builder.item().of(Registration.RUBBER_LOG.get()).build()))
//                        .save(pFinishedRecipeConsumer);
//        ShapelessRecipeBuilder.shapeless(Registration.RUBBER_PLANKS.get(), 4).requires(Registration.STRIPPED_RUBBER_LOG.get())
//                .unlockedBy("has_stripped_rubber_log", inventoryTrigger(ItemPredicate.Builder.item().of(Registration.STRIPPED_RUBBER_LOG.get()).build()))
//                .save(pFinishedRecipeConsumer);

        planksFromLogs(pFinishedRecipeConsumer, ModBlocks.RUBBER_PLANKS.get(), ModItemsTagProvider.RUBBER_LOGS);

        oreSmelting(pFinishedRecipeConsumer, TIN_SMELTABLES, ModItems.TIN_INGOT.get(), 0.7F, 200, "tin_ingot");
        oreSmelting(pFinishedRecipeConsumer, COBALT_SMELTABLES, ModItems.COBALT_INGOT.get(), 0.7F, 200, "cobalt_ingot");
        oreSmelting(pFinishedRecipeConsumer, MAGNETITE_SMELTABLES, ModItems.MAGNETITE_INGOT.get(), 0.7F, 200, "magnetite_ingot");

        oreBlasting(pFinishedRecipeConsumer, TIN_SMELTABLES, ModItems.TIN_INGOT.get(), 0.7F, 100, "tin_ingot");
        oreBlasting(pFinishedRecipeConsumer, COBALT_SMELTABLES, ModItems.COBALT_INGOT.get(), 0.7F, 100, "cobalt_ingot");
        oreBlasting(pFinishedRecipeConsumer, MAGNETITE_SMELTABLES, ModItems.MAGNETITE_INGOT.get(), 0.7F, 100, "magnetite_ingot");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_176592_, List<ItemLike> p_176593_, ItemLike p_176594_, float p_176595_, int p_176596_, String p_176597_) {
        oreCooking(p_176592_, RecipeSerializer.SMELTING_RECIPE, p_176593_, p_176594_, p_176595_, p_176596_, p_176597_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_176626_, List<ItemLike> p_176627_, ItemLike p_176628_, float p_176629_, int p_176630_, String p_176631_) {
        oreCooking(p_176626_, RecipeSerializer.BLASTING_RECIPE, p_176627_, p_176628_, p_176629_, p_176630_, p_176631_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_176534_, SimpleCookingSerializer<?> p_176535_, List<ItemLike> p_176536_, ItemLike p_176537_, float p_176538_, int p_176539_, String p_176540_, String p_176541_) {
        for(ItemLike itemlike : p_176536_) {
            //ResourceLocation id = ForgeRegistries.ITEMS.getKey(itemlike.asItem());
            //String hasItemName = "has_" + id.getPath();
            //ResourceLocation id_block = ForgeRegistries.BLOCKS.getKey(block);
            SimpleCookingRecipeBuilder.cooking(Ingredient.of(itemlike), p_176537_, p_176538_, p_176539_, p_176535_).group(p_176540_)
                    .unlockedBy(getHasName(itemlike), inventoryTrigger(ItemPredicate.Builder.item().of(itemlike).build())).
                    save(p_176534_, getItemName(p_176537_) + p_176541_ + "_" + getItemName(itemlike));
            System.out.println(getItemName(p_176537_) + p_176541_ + "_" + getItemName(itemlike));
        }

    }

    protected static String getHasName(ItemLike itemLike) {
        return "has_" + getItemName(itemLike);
    }

    protected static String getItemName(ItemLike itemLike) {
        return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
    }
}
