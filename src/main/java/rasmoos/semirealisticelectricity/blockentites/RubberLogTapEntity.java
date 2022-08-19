package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rasmoos.semirealisticelectricity.blocks.RubberLogTap;
import rasmoos.semirealisticelectricity.setup.Registration;

import java.util.Random;

public class RubberLogTapEntity extends BlockEntity {

    private static final float pGrowBack = 0.01f;

    private boolean firstPlace;
    private int value;

    public RubberLogTapEntity(BlockPos blockPos, BlockState blockState) {
        super(Registration.RUBBER_LOG_ENTITY.get(), blockPos, blockState);
    }

    public void tick() {
        if(level.isClientSide) return;


        if(!firstPlace) {
            value = getRubberValue(0.5f);
            firstPlace = true;
        }

        if(value >= 5 && value <= 8) {
            Random random = new Random();
            if(random.nextFloat() < pGrowBack) {
                value -= 4;
            }
        }

        int oldValue = getBlockState().getValue(RubberLogTap.RUBBER_TAP);

        if(oldValue != value) {
            BlockState oldState = getBlockState();
            BlockState newState = getBlockState().setValue(RubberLogTap.RUBBER_TAP, value);

            level.setBlockAndUpdate(getBlockPos(), getBlockState().setValue(RubberLogTap.RUBBER_TAP, value));
            level.sendBlockUpdated(getBlockPos(), oldState, newState, 0);
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("tap_value", value);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag parentNBTTagCompound) {
        super.saveAdditional(parentNBTTagCompound);

        parentNBTTagCompound.putBoolean("firstPlace", firstPlace);
        parentNBTTagCompound.putInt("tap_value", value);
    }

    @Override
    public void load(CompoundTag parentNBTTagCompound) {
        super.load(parentNBTTagCompound);

        firstPlace = parentNBTTagCompound.getBoolean("firstPlace");
        value = parentNBTTagCompound.getInt("tap_value");
    }

    @Override
    public void onChunkUnloaded() {
        super.onChunkUnloaded();
    }

    public static int getRubberValue(float pRubber) {
        Random random = new Random();

        if(random.nextFloat() < pRubber) {
            return random.nextInt(4) + 1;
        }

        return 0;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
