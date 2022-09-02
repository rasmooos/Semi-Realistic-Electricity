package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class WrappedFluidHandler implements IFluidHandler {

    protected NonNullList<FluidTank> tanks;
    private final Predicate<Integer> extract;
    private final BiPredicate<Integer, FluidStack> insert;

    public WrappedFluidHandler(NonNullList<FluidTank> tanks, Predicate<Integer> extract, BiPredicate<Integer, FluidStack> insert) {
        this.tanks = tanks;
        this.extract = extract;
        this.insert = insert;
    }

    @Override
    public int getTanks() {
        return tanks.size();
    }

    @Override
    public @NotNull FluidStack getFluidInTank(int tank) {
        validateTankIndex(tank);
        return tanks.get(tank).getFluidInTank(tank);
    }

    @Override
    public int getTankCapacity(int tank) {
        validateTankIndex(tank);
        return tanks.get(tank).getTankCapacity(tank);
    }

    @Override
    public boolean isFluidValid(int tank, @NotNull FluidStack stack) {
        validateTankIndex(tank);
        return tanks.get(tank).isFluidValid(tank, stack);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        for(int i = 0; i < tanks.size(); i++) {
            FluidTank tank = tanks.get(i);
            int fill = tank.fill(resource, FluidAction.SIMULATE);
            if(fill == 0) continue;

            return insert.test(i, resource) ? tank.fill(resource, FluidAction.EXECUTE) : 0;
        }

        return 0;
    }

    @Override
    public @NotNull FluidStack drain(FluidStack resource, FluidAction action) {

        for(FluidTank tank : tanks) {
            FluidStack drain = tank.drain(resource, FluidAction.SIMULATE);
            if(drain.isEmpty()) continue;

            return tank.drain(resource, FluidAction.EXECUTE);
        }

        return FluidStack.EMPTY;
    }

    @Override
    public @NotNull FluidStack drain(int maxDrain, FluidAction action) {
        for(int i = 0; i < tanks.size(); i++) {
            FluidTank tank = tanks.get(i);
            FluidStack drain = tank.drain(maxDrain, FluidAction.SIMULATE);
            if(drain.isEmpty()) continue;

            return extract.test(i) ? tank.drain(maxDrain, FluidAction.EXECUTE) : FluidStack.EMPTY;
        }

        return FluidStack.EMPTY;
    }

    protected void validateTankIndex(int tank) {
        if (tank < 0 || tank >= tanks.size())
            throw new RuntimeException("Slot " + tank + " not in valid range - [0," + tanks.size() + ")");
    }
}
