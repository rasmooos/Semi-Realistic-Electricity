package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;

public abstract class MachineMenu extends BaseGuiMenu {
    public MachineMenu(MenuType<?> menuType, int pContainerId, Inventory inv, FriendlyByteBuf extraData, ContainerData data) {
        super(menuType, pContainerId, inv, extraData, data);
    }

    public MachineMenu(MenuType<?> menuType, int pContainerId, Inventory inv, MachineBlockEntity entity, ContainerData data) {
        super(menuType, pContainerId, inv, entity, data);
    }
}
