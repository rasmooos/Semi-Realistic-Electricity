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
    private final int tank;

    public SyncFluidToClient(FluidStack stack, BlockPos pos) {
        this(0, stack, pos);
    }

    public SyncFluidToClient(int tank, FluidStack stack, BlockPos pos) {
        this.fluidStack = stack;
        this.pos = pos;
        this.tank = tank;
    }

    public SyncFluidToClient(FriendlyByteBuf buf) {
        fluidStack = buf.readFluidStack();
        pos = buf.readBlockPos();
        tank = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
        buf.writeInt(tank);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT YES
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof IFluidHandlingBlockEntity blockEntity) {
                blockEntity.setFluid(tank, fluidStack);

                if(Minecraft.getInstance().player.containerMenu instanceof IFluidMenu menu && menu.getBlockEntity().getBlockPos().equals(pos)) {
                    menu.setFluid(tank, fluidStack);
                }
            }
        });
        return true;
    }

}
