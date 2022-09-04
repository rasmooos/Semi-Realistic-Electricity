package rasmoos.semirealisticelectricity.screen.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import rasmoos.semirealisticelectricity.blockentites.FluidCompactorEntity;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;
import rasmoos.semirealisticelectricity.network.ModNetworkHandler;
import rasmoos.semirealisticelectricity.network.SyncItemToServer;
import rasmoos.semirealisticelectricity.screen.slot.ModRenderSlot;
import rasmoos.semirealisticelectricity.screen.slot.ModResultSlot;

public class FluidCompactorMenu extends MachineMenu {
    public FluidCompactorMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, (MachineBlockEntity) inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public FluidCompactorMenu(int pContainerId, Inventory inv, MachineBlockEntity entity, ContainerData data) {
        super(ModMenuTypes.FLUID_COMPACTOR_MENU.get(), pContainerId, inv, entity, data);
    }

    @Override
    public void addSlots(Inventory inventory) {
        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);

        entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
            addSlot(new ModResultSlot(iItemHandler, 0, 74, 33));
            addSlot(new ModRenderSlot(iItemHandler, 1, 74, 59));
        });

        addDataSlots(data);
    }

    public ItemStack getCraftItem() {
        return ((FluidCompactorEntity) entity).getCraftItem();
    }

    public void setCraftType(FluidCompactorEntity.CraftType craftType) {
        data.set(2, craftType.ordinal());
    }

    public void cycleCraftType() {
        int craftType = data.get(2);
        int index = (craftType + 1) % (FluidCompactorEntity.CraftType.values().length - 1);

        FluidCompactorEntity blockEntity = (FluidCompactorEntity) level.getBlockEntity(entity.getBlockPos());
        blockEntity.setCraftType(index);
        ModNetworkHandler.sendToServer(new SyncItemToServer(entity.getBlockPos(), blockEntity.getCraftType()));
    }

    public void cycleCraftTypeBack() {
        int craftType = data.get(2);
        int index = (craftType - 1) % (FluidCompactorEntity.CraftType.values().length - 1);

        index += index < 0 ? FluidCompactorEntity.CraftType.values().length - 1 : 0;

        FluidCompactorEntity blockEntity = (FluidCompactorEntity) level.getBlockEntity(entity.getBlockPos());
        blockEntity.setCraftType(index);
        ModNetworkHandler.sendToServer(new SyncItemToServer(entity.getBlockPos(), blockEntity.getCraftType()));
    }
}
