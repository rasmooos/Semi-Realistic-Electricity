package rasmoos.semirealisticelectricity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;
import rasmoos.semirealisticelectricity.blockentites.BaseGuiBlockEntity;
import rasmoos.semirealisticelectricity.blockentites.FluidCompactorEntity;
import rasmoos.semirealisticelectricity.blockentites.IInventoryHandlingBlockEntity;
import rasmoos.semirealisticelectricity.network.IModPacket;
import rasmoos.semirealisticelectricity.screen.menu.BaseGuiMenu;
import rasmoos.semirealisticelectricity.screen.menu.IEnergyMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SyncItemToServer implements IModPacket {
    private FluidCompactorEntity.CraftType craftType;
    private final BlockPos pos;

    public SyncItemToServer(BlockPos pos, FluidCompactorEntity.CraftType craftType) {
        this.pos = pos;
        this.craftType = craftType;
    }

    public SyncItemToServer(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        craftType = buf.readEnum(FluidCompactorEntity.CraftType.class);
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeEnum(craftType);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            if(level.getBlockEntity(pos) instanceof FluidCompactorEntity blockEntity) {
                blockEntity.setCraftType(craftType);
//                blockEntity.setHandler(handler);

                if(player.containerMenu instanceof BaseGuiMenu menu && menu.getEntity().getBlockPos().equals(pos)) {
//                    blockEntity.setHandler(handler);
                    blockEntity.setCraftType(craftType);
                }
            }
        });
        return true;
    }
}
