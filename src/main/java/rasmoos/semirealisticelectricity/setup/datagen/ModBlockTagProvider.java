package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

public class ModBlockTagProvider extends BlockTagsProvider {

    public static final TagKey<Block> RUBBER_LOGS = BlockTags.create(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "rubber_logs"));

    public ModBlockTagProvider(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(RUBBER_LOGS).add(ModBlocks.STRIPPED_RUBBER_LOG.get()).add(ModBlocks.RUBBER_LOG.get());

        tag(BlockTags.LOGS_THAT_BURN).add(ModBlocks.RUBBER_LOG.get()).add(ModBlocks.STRIPPED_RUBBER_LOG.get());
        tag(BlockTags.LOGS).add(ModBlocks.RUBBER_LOG.get()).add(ModBlocks.STRIPPED_RUBBER_LOG.get());
        tag(BlockTags.PLANKS).add(ModBlocks.RUBBER_PLANKS.get());
        tag(BlockTags.LEAVES).add(ModBlocks.RUBBER_LEAVES.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.TIN_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.COBALT_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.MAGNETITE_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.LEPIDOLITE_ORE.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.IRON_FURNACE_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.CRUSHER_BLOCK.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.TIN_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.MAGNETITE_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.DEEPSLATE_MAGNETITE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.COBALT_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.DEEPSLATE_COBALT_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.LEPIDOLITE_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.DEEPSLATE_LEPIDOLITE_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.IRON_FURNACE_BLOCK.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.CRUSHER_BLOCK.get());

    }
}
