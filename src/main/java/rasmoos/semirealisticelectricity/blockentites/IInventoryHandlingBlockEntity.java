package rasmoos.semirealisticelectricity.blockentites;

import net.minecraftforge.items.ItemStackHandler;

public interface IInventoryHandlingBlockEntity {

    void setHandler(ItemStackHandler handler);
    ItemStackHandler getItemStackHandler();

}
