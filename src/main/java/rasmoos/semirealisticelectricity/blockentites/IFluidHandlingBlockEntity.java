package rasmoos.semirealisticelectricity.blockentites;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidHandlingBlockEntity {

    void setFluid(int tank, FluidStack fluid);
    FluidStack getFluid(int tank);

}
