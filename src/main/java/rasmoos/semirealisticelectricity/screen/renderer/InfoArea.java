package rasmoos.semirealisticelectricity.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.Rect2i;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  Details can be found in the license file in the root folder of this project
 */
public abstract class InfoArea extends GuiComponent {
    protected final Rect2i area;

    protected InfoArea(Rect2i area) {
        this.area = area;
    }

    public abstract void draw(PoseStack transform);

    public Rect2i getArea() {
        return area;
    }

    public int getX() {
        return getArea().getX();
    }

    public int getY() {
        return getArea().getY();
    }

    public int getWidth() {
        return getArea().getWidth();
    }

    public int getHeight() {
        return getArea().getHeight();
    }
}
