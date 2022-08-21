package rasmoos.semirealisticelectricity.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;
import rasmoos.semirealisticelectricity.blockentites.IFluidHandlingBlockEntity;
import rasmoos.semirealisticelectricity.screen.IFluidMenu;

import java.util.function.Supplier;

public class SyncFluidToClient implements IModPacket {

    private final FluidStack fluidStack;
    private final BlockPos pos;

    public SyncFluidToClient(FluidStack stack, BlockPos pos) {
        this.fluidStack = stack;
        this.pos = pos;
    }

    public SyncFluidToClient(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof IFluidHandlingBlockEntity blockEntity) {
                blockEntity.setFluid(fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof IFluidMenu menu && menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(fluidStack);
                }
            }
        });
        return true;
    }

}
