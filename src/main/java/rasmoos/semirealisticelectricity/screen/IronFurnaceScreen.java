package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

public class IronFurnaceScreen extends MachineScreen<IronFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SemiRealisticElectricity.MOD_ID, "textures/gui/iron_furnace.png");

    public IronFurnaceScreen(IronFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, TEXTURE);
    }
}
