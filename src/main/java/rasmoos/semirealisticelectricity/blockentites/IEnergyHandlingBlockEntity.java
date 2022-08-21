package rasmoos.semirealisticelectricity.blockentites;

import net.minecraftforge.energy.IEnergyStorage;

public interface IEnergyHandlingBlockEntity {

    void setEnergyLevel(int level);
    IEnergyStorage getEnergyStorage();

}
