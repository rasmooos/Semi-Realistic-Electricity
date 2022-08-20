package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.slot.ModResultSlot;

public class IronFurnaceMenu extends MachineMenu {
    public IronFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, (MachineBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public IronFurnaceMenu(int pContainerId, Inventory inv, MachineBlockEntity entity) {
        super(ModMenuTypes.IRON_FURNACE_MENU.get(), pContainerId, inv, entity);
    }

    @Override
    public void addSlots(Inventory inventory) {
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 17));
            this.addSlot(new SlotItemHandler(handler, 1, 56, 53));
            this.addSlot(new ModResultSlot(handler, 2, 116, 35));
        });
    }
}
