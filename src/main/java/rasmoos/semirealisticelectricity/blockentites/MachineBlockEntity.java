package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.blocks.FluidCompactor;
import rasmoos.semirealisticelectricity.blocks.MachineBlock;
import rasmoos.semirealisticelectricity.network.ModNetworkHandler;
import rasmoos.semirealisticelectricity.network.SyncEnergyToClient;
import rasmoos.semirealisticelectricity.network.SyncFluidToClient;
import rasmoos.semirealisticelectricity.network.SyncItemToClient;
import rasmoos.semirealisticelectricity.util.SemiRealisticEnergyStorage;

import java.util.Map;

public abstract class MachineBlockEntity extends BaseGuiBlockEntity implements IFluidHandlingBlockEntity, IEnergyHandlingBlockEntity {

    protected static final int BASE_ENERGY_PER_TICK = 5;

    protected final NonNullList<FluidTank> fluidTanks;
    protected final SemiRealisticEnergyStorage energyStorage;
    protected LazyOptional<IEnergyStorage> lazyEnergyHandler;
    private LazyOptional<IFluidHandler>[] lazyFluidHandlers;

    protected final ContainerData data;
    private MachineBlock baseBlock;
    protected int progress;
    protected int maxProgress;
    protected int energyPerOperation;


    public MachineBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState, MachineBlock baseBlock) {
        super(blockEntityType, blockPos, blockState, baseBlock);

        fluidTanks = createFluidTanks();
        energyStorage = createEnergyStorage();

        data = getContainerData();

        lazyEnergyHandler = LazyOptional.empty();
        lazyFluidHandlers = new LazyOptional[fluidTanks.size()];

        for(int i = 0; i < fluidTanks.size(); i++) {
            lazyFluidHandlers[i] = LazyOptional.empty();
        }

        this.baseBlock = baseBlock;
        progress = 0;
        maxProgress = 100;
        energyPerOperation = 10;
    }

    @Override
    public void tick() {
        if(level.isClientSide) {
            return;
        }

        energyStorage.extractEnergy(BASE_ENERGY_PER_TICK, false);

        if(hasRecipe() && hasEnoughEnergy()) {
            progress++;
            energyStorage.extractEnergy(energyPerOperation, false);
            setChanged(level, getBlockPos(), getBlockState());
            if(progress > maxProgress) {
                craftItem();
            }
        } else {
            resetProgress();
            setChanged(level, getBlockPos(), getBlockState());
        }

        if(!getBlockState().getValue(FluidCompactor.LIT) && progress > 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(MachineBlock.LIT, true));
        } else if(getBlockState().getValue(FluidCompactor.LIT) && progress == 0) {
            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(MachineBlock.LIT, false));
        }
    }

    protected boolean hasEnoughEnergy() {
        return energyStorage.getEnergyStored() >= energyPerOperation;
    }

    public abstract boolean hasRecipe();
    public abstract void craftItem();

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(side == null) {
                return super.getCapability(cap, side);
            }
            var directionWrappedItemHandlerMap = getDirectionWrappedItemHandlerMap();

            if(directionWrappedItemHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(FluidCompactor.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedItemHandlerMap.get(side).cast();
                }

                return switch(localDir) {
                    default -> directionWrappedItemHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedItemHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedItemHandlerMap.get(side).cast();
                    case WEST -> directionWrappedItemHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        if(cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            if(side == null) {
                return super.getCapability(cap, side);
            }
            var directionWrappedFluidHandlerMap = getDirectionWrappedFluidHandlerMap();

            if(directionWrappedFluidHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(FluidCompactor.FACING);

                if(side == Direction.UP || side == Direction.DOWN) {
                    return directionWrappedFluidHandlerMap.get(side).cast();
                }

                return switch(localDir) {
                    default -> directionWrappedFluidHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedFluidHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedFluidHandlerMap.get(side).cast();
                    case WEST -> directionWrappedFluidHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();

        lazyEnergyHandler = LazyOptional.of(() -> energyStorage);
        for(int i = 0; i < fluidTanks.size(); i++) {
            int finalI = i;
            lazyFluidHandlers[i] = LazyOptional.of(() -> fluidTanks.get(finalI));
        }
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();

        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putInt("energy", energyStorage.getEnergyStored());
        tag.putInt("progress", progress);

        for(int i = 0; i < fluidTanks.size(); i++) {
            FluidStack fluid = fluidTanks.get(i).getFluid();

            tag.putString(i + ".FluidName", ForgeRegistries.FLUIDS.getKey(fluid.getFluid()).toString());
            tag.putInt(i + ".Amount", fluid.getAmount());
        }

        super.saveAdditional(tag);
    }

    @Override
    public ItemStackHandler getItemHandler() {
        return new ItemStackHandler(getNumberOfSlots()) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                if(!level.isClientSide) {
                    ModNetworkHandler.sendToClients(new SyncItemToClient(itemHandler, getBlockPos()));
                }
            }
        };
    }

    @Override
    public void load(CompoundTag nbt) {
        energyStorage.setEnergy(nbt.getInt("energy"));
        progress = nbt.getInt("progress");

        for(int i = 0; i < fluidTanks.size(); i++) {

            FluidStack stack;



            ResourceLocation fluidName = new ResourceLocation(nbt.getString(i + ".FluidName"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(fluidName);
            if (fluid == null)
            {
                stack =  FluidStack.EMPTY;
            } else {
                stack = new FluidStack(fluid, nbt.getInt(i + ".Amount"));
            }


            fluidTanks.get(i).setFluid(stack);
        }

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
    public void setFluid(int tank, FluidStack fluid) {
        fluidTanks.get(tank).setFluid(fluid);
    }

    @Override
    public FluidStack getFluid(int tank) {
        return fluidTanks.get(tank).getFluid();
    }

    public NonNullList<FluidTank> createFluidTanks() {
        int[] size = getFluidTankCapacity();

        NonNullList<FluidTank> result = NonNullList.create();

        for(int i = 0; i < size.length; i++) {
            int finalI = i;
            result.add(new FluidTank(size[finalI]) {
                @Override
                protected void onContentsChanged() {
                    setChanged();
                    if(!level.isClientSide)
                        ModNetworkHandler.sendToClients(new SyncFluidToClient(finalI, fluid, worldPosition));
                }
            });
        }

        return result;
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

    protected void resetProgress() {
        progress = 0;
    }

    public abstract int[] getFluidTankCapacity();
    public abstract Map<Direction, LazyOptional<WrappedItemHandler>> getDirectionWrappedItemHandlerMap();
    public abstract Map<Direction, LazyOptional<WrappedFluidHandler>> getDirectionWrappedFluidHandlerMap();

    /**
     * capacity, maxtransfer
     * @return
     */
    public Tuple<Integer, Integer> getEnergyStorageCapacity() {
        return new Tuple<>(60000, 200);
    }

    public ContainerData getContainerData() {
        return new ContainerData() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> MachineBlockEntity.this.progress;
                    case 1 -> MachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> MachineBlockEntity.this.progress = value;
                    case 1 -> MachineBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

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
