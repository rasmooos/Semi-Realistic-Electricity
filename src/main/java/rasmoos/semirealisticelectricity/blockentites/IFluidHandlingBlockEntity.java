package rasmoos.semirealisticelectricity.blockentites;

import net.minecraftforge.fluids.FluidStack;

public interface IFluidHandlingBlockEntity {

    void setFluid(FluidStack fluid);
    FluidStack getFluid();

}
