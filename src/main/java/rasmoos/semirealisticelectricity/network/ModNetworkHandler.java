package rasmoos.semirealisticelectricity.network;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

public class ModNetworkHandler {

    private static SimpleChannel INSTANCE;

    private static int paketId = 0;

    private static int id() {
        return paketId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SyncEnergyToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncEnergyToClient::new)
                .encoder(SyncEnergyToClient::toBytes)
                .consumerMainThread(SyncEnergyToClient::handle)
                .add();

        net.messageBuilder(SyncFluidToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncFluidToClient::new)
                .encoder(SyncFluidToClient::toBytes)
                .consumerMainThread(SyncFluidToClient::handle)
                .add();

        net.messageBuilder(SyncItemToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncItemToClient::new)
                .encoder(SyncItemToClient::toBytes)
                .consumerMainThread(SyncItemToClient::handle)
                .add();
    }

    public static <MSG> void sendTo(MSG msg, Connection connection, NetworkDirection direction) {
        INSTANCE.sendTo(msg, connection, direction);
    }
    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
