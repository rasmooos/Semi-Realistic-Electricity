package rasmoos.semirealisticelectricity.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class SemiRealisticEnergyStorage extends EnergyStorage {
    public SemiRealisticEnergyStorage(int capacity) {
        super(capacity);
    }

    public SemiRealisticEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public SemiRealisticEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public SemiRealisticEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        if(extracted != 0) {
            onEnergyChanged();
        }
        return extracted;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        if(received != 0) {
            onEnergyChanged();
        }
        return received;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        return energy;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract void onEnergyChanged();
}
