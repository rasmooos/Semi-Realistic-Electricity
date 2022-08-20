package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MachineScreen<T extends MachineMenu> extends BaseGuiScreen<T> {
    public MachineScreen(T menuType, Inventory inventory, Component component, ResourceLocation texture) {
        super(menuType, inventory, component, texture);
    }
}
