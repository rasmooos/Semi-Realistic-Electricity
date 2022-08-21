package rasmoos.semirealisticelectricity.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IModPacket {

    void toBytes(FriendlyByteBuf buf);
    boolean handle(Supplier<NetworkEvent.Context> contextSupplier);

}
