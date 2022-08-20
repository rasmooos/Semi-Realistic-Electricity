package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.blocks.ModBlocks;

public class ModItemsTagProvider extends ItemTagsProvider {

    public static final TagKey<Item> RUBBER_LOGS = ItemTags.create(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "rubber_logs"));

    public ModItemsTagProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copy(ModBlockTagProvider.RUBBER_LOGS, RUBBER_LOGS);
//        tag(RUBBER_LOGS).add(Registration.RUBBER_LOG.get().asItem()).add(Registration.STRIPPED_RUBBER_LOG.get().asItem());
        tag(ItemTags.PLANKS).add(ModBlocks.RUBBER_PLANKS.get().asItem());
        tag(ItemTags.LOGS).add(ModBlocks.RUBBER_LOG.get().asItem()).add(ModBlocks.STRIPPED_RUBBER_LOG.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.RUBBER_LOG.get().asItem()).add(ModBlocks.STRIPPED_RUBBER_LOG.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.RUBBER_LEAVES.get().asItem());

    }
}
