package rasmoos.semirealisticelectricity.screen.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.slot.ModResultSlot;

public class ElectrostaticSeparatorMenu extends MachineMenu {
    public ElectrostaticSeparatorMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, (MachineBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public ElectrostaticSeparatorMenu(int pContainerId, Inventory inv, MachineBlockEntity entity, ContainerData data) {
        super(ModMenuTypes.ELECTROSTATIC_SEPARATOR_MENU.get(), pContainerId, inv, entity, data);
    }

    @Override
    public void addSlots(Inventory inventory) {
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 45, 17));
            this.addSlot(new SlotItemHandler(handler, 1, 45, 53));
            this.addSlot(new ModResultSlot(handler, 2, 97, 35));
            this.addSlot(new ModResultSlot(handler, 3, 118, 35));
        });

        addDataSlots(data);
    }
}
