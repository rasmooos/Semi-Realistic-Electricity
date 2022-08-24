package rasmoos.semirealisticelectricity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import rasmoos.semirealisticelectricity.blockentites.IEnergyHandlingBlockEntity;
import rasmoos.semirealisticelectricity.screen.menu.IEnergyMenu;

import java.util.function.Supplier;

public class SyncEnergyToClient implements IModPacket {

    private final int energy;
    private final BlockPos pos;

    public SyncEnergyToClient(int energy, BlockPos pos) {
        this.energy = energy;
        this.pos = pos;
    }

    public SyncEnergyToClient(FriendlyByteBuf buf) {
        energy = buf.readInt();
        pos = buf.readBlockPos();
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof IEnergyHandlingBlockEntity blockEntity) {
                blockEntity.setEnergyLevel(energy);

                if(Minecraft.getInstance().player.containerMenu instanceof IEnergyMenu menu && menu.getBlockEntity().getBlockPos().equals(pos)) {
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }
}
