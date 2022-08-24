package rasmoos.semirealisticelectricity.screen;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidMenu {

    void setFluid(int tank, FluidStack fluidStack);
    FluidStack getFluid(int tank);

    int getFluidTankCapacity(int tank);

    BlockEntity getBlockEntity();

}
