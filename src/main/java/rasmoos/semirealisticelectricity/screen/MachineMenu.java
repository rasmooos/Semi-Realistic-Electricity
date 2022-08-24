package rasmoos.semirealisticelectricity.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import rasmoos.semirealisticelectricity.blockentites.MachineBlockEntity;

public abstract class MachineMenu extends BaseGuiMenu implements IEnergyMenu, IFluidMenu {
    public MachineMenu(MenuType<?> menuType, int pContainerId, Inventory inv, FriendlyByteBuf extraData, ContainerData data) {
        super(menuType, pContainerId, inv, extraData, data);
    }

    public MachineMenu(MenuType<?> menuType, int pContainerId, Inventory inv, MachineBlockEntity entity, ContainerData data) {
        super(menuType, pContainerId, inv, entity, data);
    }

    @Override
    public BlockEntity getBlockEntity() {
        return entity;
    }

    @Override
    public FluidStack getFluid(int tank) {
        return ((MachineBlockEntity<?>) entity).getFluid(tank);
    }

    @Override
    public void setFluid(int tank, FluidStack fluidStack) {
        ((MachineBlockEntity<?>) entity).setFluid(tank, fluidStack);
    }

    @Override
    public int getFluidTankCapacity(int tank) {
        return ((MachineBlockEntity<?>) entity).getFluidTankCapacity()[tank];
    }
}
