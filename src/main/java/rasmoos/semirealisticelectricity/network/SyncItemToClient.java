package rasmoos.semirealisticelectricity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;
import rasmoos.semirealisticelectricity.blockentites.IInventoryHandlingBlockEntity;
import rasmoos.semirealisticelectricity.screen.menu.BaseGuiMenu;
import rasmoos.semirealisticelectricity.screen.menu.IEnergyMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SyncItemToClient implements IModPacket {

    private final ItemStackHandler handler;
    private final BlockPos pos;

    public SyncItemToClient(ItemStackHandler handler, BlockPos pos) {
        this.handler = handler;
        this.pos = pos;
    }

    public SyncItemToClient(FriendlyByteBuf buf) {
        List<ItemStack> collection = buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        handler = new ItemStackHandler(collection.size());
        for (int i = 0; i < collection.size(); i++) {
            handler.insertItem(i, collection.get(i), false);
        }

        this.pos = buf.readBlockPos();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        Collection<ItemStack> list = new ArrayList<>();
        for(int i = 0; i < handler.getSlots(); i++) {
            list.add(handler.getStackInSlot(i));
        }

        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(pos);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof IInventoryHandlingBlockEntity blockEntity) {
                blockEntity.setHandler(handler);

                if(Minecraft.getInstance().player.containerMenu instanceof BaseGuiMenu menu && menu.getEntity().getBlockPos().equals(pos)) {
                    blockEntity.setHandler(handler);
                }
            }
        });
        return true;
    }
}
