package rasmoos.semirealisticelectricity.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import rasmoos.semirealisticelectricity.blockentites.RubberLogTapEntity;
import rasmoos.semirealisticelectricity.items.blocks.RubberLogTap;

public class RubberTap extends Item {
    public RubberTap(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {

        BlockEntity blockEntity = useOnContext.getLevel().getBlockEntity(useOnContext.getClickedPos());

        if(blockEntity instanceof RubberLogTapEntity entity) {
            int value = blockEntity.getBlockState().getValue(RubberLogTap.RUBBER_TAP);

            if(value >= 1 && value <= 4) {
                entity.setValue(entity.getValue() + 4);
                BlockPos blockPos = entity.getBlockPos();
                useOnContext.getLevel().addFreshEntity(
                        new ItemEntity(useOnContext.getLevel(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(ModItems.RUBBER_RESIN.get(), 1)));
//                useOnContext.getLevel().setBlockAndUpdate(useOnContext.getClickedPos(), blockEntity.getBlockState().setValue(RubberLogTap.RUBBER_TAP, value + 4));
            }
        }

        return super.useOn(useOnContext);
    }
}
