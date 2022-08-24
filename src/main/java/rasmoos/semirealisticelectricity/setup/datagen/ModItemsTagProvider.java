package rasmoos.semirealisticelectricity.setup.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.items.ModItems;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;

public class ModItemsTagProvider extends ItemTagsProvider {

    public static final TagKey<Item> RUBBER_LOGS = ItemTags.create(new ResourceLocation(SemiRealisticElectricity.MOD_ID, "rubber_logs"));

    public static final TagKey<Item> TIN_ORES = ItemTags.create(new ResourceLocation("forge", "ores/tin"));
    public static final TagKey<Item> COBALT_ORES = ItemTags.create(new ResourceLocation("forge", "ores/cobalt"));
    public static final TagKey<Item> MAGNETITE_ORES = ItemTags.create(new ResourceLocation("forge", "ores/magnetite"));
    public static final TagKey<Item> LEDIDOLITE_ORES = ItemTags.create(new ResourceLocation("forge", "ores/lepidolite"));
    public static final TagKey<Item> RAW_TIN = ItemTags.create(new ResourceLocation("forge", "raw_materials/tin"));
    public static final TagKey<Item> RAW_COBALT = ItemTags.create(new ResourceLocation("forge", "raw_materials/cobalt"));
    public static final TagKey<Item> RAW_MAGNETITE = ItemTags.create(new ResourceLocation("forge", "raw_materials/magnetite"));
    public static final TagKey<Item> RAW_LEPIDOLITE = ItemTags.create(new ResourceLocation("forge", "raw_materials/lepidolite"));
    public static final TagKey<Item> TIN_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/tin"));
    public static final TagKey<Item> COBALT_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/cobalt"));
    public static final TagKey<Item> MAGNETITE_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/magnetite"));

    public static final TagKey<Item> TIN_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/tin"));
    public static final TagKey<Item> COBALT_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/cobalt"));
    public static final TagKey<Item> MAGNETITE_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/magnetite"));
    public static final TagKey<Item> LITHIUM_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/lithium"));


    public ModItemsTagProvider(DataGenerator p_126530_, BlockTagsProvider p_126531_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126530_, p_126531_, SemiRealisticElectricity.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.copy(ModBlockTagProvider.RUBBER_LOGS, RUBBER_LOGS);
        this.copy(ModBlockTagProvider.TIN_ORES, TIN_ORES);
        this.copy(ModBlockTagProvider.COBALT_ORES, COBALT_ORES);
        this.copy(ModBlockTagProvider.MAGNETITE_ORES, MAGNETITE_ORES);
        this.copy(ModBlockTagProvider.LEDIDOLITE_ORES, LEDIDOLITE_ORES);
        this.copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
        this.copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);
        this.copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR);

        tag(Tags.Items.ORES).addTags(TIN_ORES, COBALT_ORES, MAGNETITE_ORES, LEDIDOLITE_ORES);

        tag(ItemTags.PLANKS).add(ModBlocks.RUBBER_PLANKS.get().asItem());
        tag(ItemTags.LOGS).add(ModBlocks.RUBBER_LOG.get().asItem()).add(ModBlocks.STRIPPED_RUBBER_LOG.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.RUBBER_LOG.get().asItem()).add(ModBlocks.STRIPPED_RUBBER_LOG.get().asItem());
        tag(ItemTags.LEAVES).add(ModBlocks.RUBBER_LEAVES.get().asItem());

        tag(RAW_TIN).add(ModItems.RAW_TIN.get());
        tag(RAW_COBALT).add(ModItems.RAW_COBALT.get());
        tag(RAW_MAGNETITE).add(ModItems.RAW_MAGNETITE.get());
        tag(RAW_LEPIDOLITE).add(ModItems.RAW_LEPIDOLITE.get());

        tag(TIN_DUSTS).add(ModItems.TIN_DUST.get());
        tag(COBALT_DUSTS).add(ModItems.COBALT_DUST.get());
        tag(MAGNETITE_DUSTS).add(ModItems.MAGNETITE_DUST.get());
        tag(LITHIUM_DUSTS).add(ModItems.LITHIUM_DUST.get());

        tag(TIN_INGOT).add(ModItems.TIN_INGOT.get());
        tag(COBALT_INGOT).add(ModItems.COBALT_INGOT.get());
        tag(MAGNETITE_INGOT).add(ModItems.MAGNETITE_INGOT.get());
    }
}
