package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.items.blocks.MachineBlock;
import rasmoos.semirealisticelectricity.network.ModNetworkHandler;
import rasmoos.semirealisticelectricity.network.SyncEnergyToClient;
import rasmoos.semirealisticelectricity.network.SyncFluidToClient;
import rasmoos.semirealisticelectricity.util.SemiRealisticEnergyStorage;

public abstract class MachineBlockEntity<BLOCK extends MachineBlock> extends BaseGuiBlockEntity implements IFluidHandlingBlockEntity, IEnergyHandlingBlockEntity {

    protected final FluidTank fluidTank;
    protected final SemiRealisticEnergyStorage energyStorage;

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;

    public MachineBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, Block baseBlock) {
        super(blockEntityType, blockPos, blockState, baseBlock);

        fluidTank = createFluidTank();
        energyStorage = createEnergyStorage();

        data = getContainerData();
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if(this.getBlockState().getValue(BLOCK.FACING).getClockWise() == side) {
                return lazyFluidHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidHandler = LazyOptional.of(() -> fluidTank);
        lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("energy", energyStorage.getEnergyStored());
        tag = fluidTank.writeToNBT(tag);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        energyStorage.setEnergy(nbt.getInt("energy"));
        fluidTank.readFromNBT(nbt);
        super.load(nbt);
    }

    @Override
    public void setEnergyLevel(int level) {
        energyStorage.setEnergy(level);
    }

    public void updateEnergyLevel(int level) {
        energyStorage.setEnergy(level);

        if(!getLevel().isClientSide)
            ModNetworkHandler.sendToClients(new SyncEnergyToClient(energyStorage.getEnergyStored(), worldPosition));
        setChanged(getLevel(), getBlockPos(), getBlockState());
    }

    @Override
    public SemiRealisticEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    @Override
    public void setFluid(FluidStack fluid) {
        fluidTank.setFluid(fluid);
    }

    @Override
    public FluidStack getFluid() {
        return fluidTank.getFluid();
    }

    public FluidTank createFluidTank() {
        return new FluidTank(getFluidTankCapacity()) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if(!level.isClientSide)
                    ModNetworkHandler.sendToClients(new SyncFluidToClient(fluid, worldPosition));
            }
        };
    }

    public SemiRealisticEnergyStorage createEnergyStorage() {
        Tuple<Integer, Integer> capacity = getEnergyStorageCapacity();
        return new SemiRealisticEnergyStorage(capacity.getA(), capacity.getB()) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                if(!level.isClientSide)
                    ModNetworkHandler.sendToClients(new SyncEnergyToClient(energy, worldPosition));
            }
        };
    }

    public abstract int getFluidTankCapacity();

    /**
     * capacity, maxtransfer
     * @return
     */
    public abstract Tuple<Integer, Integer> getEnergyStorageCapacity();

    public abstract ContainerData getContainerData();

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compound = saveWithoutMetadata();
        load(compound);

        return compound;
    }

}
