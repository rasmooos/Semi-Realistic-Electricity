package rasmoos.semirealisticelectricity.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec2;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;
import rasmoos.semirealisticelectricity.screen.FluidCompactorScreen;

import java.util.ArrayList;
import java.util.List;

public abstract class MachineRecipeCategory<T extends Recipe<SimpleContainer>>  implements IRecipeCategory<T> {

    private final IGuiHelper helper;
    private final IDrawable background;
    private final IDrawable energyBar;
    private final IDrawable icon;
    private final RecipeType recipeType;

    private List<Tuple<IDrawableAnimated, Vec2>> progressBars = new ArrayList<>();

    public MachineRecipeCategory(IGuiHelper helper, ResourceLocation texture, int u, int v, int width, int height, Block block, RecipeType<?> recipeType) {
        this.helper = helper;
        this.background = helper.createDrawable(texture, u, v, width, height);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(block));
        this.recipeType = recipeType;

        energyBar = helper.createDrawable(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/energybar2.png"),
                200, 20, 7, 66);
        System.out.println(energyBar.getWidth());
    }

    @Override
    public void draw(T recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
        for(var t : progressBars) {
            t.getA().draw(stack, (int) t.getB().x, (int) t.getB().y);
        }
//        energyBar.draw(stack, background.getWidth() - 10, 4);
    }

    public void addProgressBar(ResourceLocation texture, int x, int y, int width, int height, int speed,
                               IDrawableAnimated.StartDirection direction, boolean inverted, int renderX, int renderY) {

        IDrawableAnimated progressBar = helper.createAnimatedDrawable(helper.createDrawable(texture, x, y, width, height),
                speed, direction, inverted);
        Vec2 renderPos = new Vec2(renderX, renderY);

        progressBars.add(new Tuple<>(progressBar, renderPos));
    }

    @Override
    public RecipeType<T> getRecipeType() {
        return recipeType;
    }

    @Override
    public abstract Component getTitle();

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public abstract void setRecipe(IRecipeLayoutBuilder builder, T fluidCompactorRecipe, IFocusGroup iFocusGroup);
}