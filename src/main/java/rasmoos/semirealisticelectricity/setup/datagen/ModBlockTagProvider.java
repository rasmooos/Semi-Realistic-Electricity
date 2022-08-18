package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.Registration;

public class ModBlockTagProvider extends BlockTagsProvider {

    public static final TagKey<Block> RUBBER_LOGS = BlockTags.create(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "rubber_logs"));

    public ModBlockTagProvider(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(RUBBER_LOGS).add(Registration.STRIPPED_RUBBER_LOG.get()).add(Registration.RUBBER_LOG.get());

        tag(BlockTags.LOGS_THAT_BURN).add(Registration.RUBBER_LOG.get()).add(Registration.STRIPPED_RUBBER_LOG.get());
        tag(BlockTags.LOGS).add(Registration.RUBBER_LOG.get()).add(Registration.STRIPPED_RUBBER_LOG.get());
        tag(BlockTags.PLANKS).add(Registration.RUBBER_PLANKS.get());
        tag(BlockTags.LEAVES).add(Registration.RUBBER_LEAVES.get());
    }
}
