package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.screen.menu.IronFurnaceMenu;

public class IronFurnaceScreen extends BaseGuiScreen<IronFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/iron_furnace.png");

    public IronFurnaceScreen(IronFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }
}
