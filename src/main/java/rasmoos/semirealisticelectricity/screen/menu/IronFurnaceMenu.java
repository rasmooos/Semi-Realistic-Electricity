package rasmoos.semirealisticelectricity.screen.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import rasmoos.semirealisticelectricity.blockentites.BaseGuiBlockEntity;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.screen.slot.ModFuelSlot;
import rasmoos.semirealisticelectricity.screen.slot.ModResultSlot;

public class IronFurnaceMenu extends MachineMenu {
    public IronFurnaceMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, (MachineBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    public IronFurnaceMenu(int pContainerId, Inventory inv, MachineBlockEntity entity, ContainerData data) {
        super(ModMenuTypes.IRON_FURNACE_MENU.get(), pContainerId, inv, entity, data);
    }

    @Override
    public void addSlots(Inventory inventory) {
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        this.entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler, 0, 56, 17));
            this.addSlot(new ModFuelSlot(handler, 1, 56, 53));
            this.addSlot(new ModResultSlot(handler, 2, 116, 35));
        });

        addDataSlots(data);
    }

    public boolean isBurning() {
        return data.get(2) > 0;
    }

    public int getScaledFuel() {
        int progress = data.get(2);
        int maxProgress = data.get(3);

        int progressArrowSize = 14;

        int result = progress * progressArrowSize / maxProgress;

        return maxProgress != 0 && progress != 0 ? Mth.clamp(result, 1, maxProgress) : 0;
    }
}
