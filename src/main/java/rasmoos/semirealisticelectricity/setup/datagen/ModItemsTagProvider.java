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
    public static final TagKey<Item> BRONZE_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/bronze"));
    public static final TagKey<Item> ALUMINIUM_INGOT = ItemTags.create(new ResourceLocation("forge", "ingots/aluminium"));

    public static final TagKey<Item> TIN_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/tin"));
    public static final TagKey<Item> COBALT_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/cobalt"));
    public static final TagKey<Item> MAGNETITE_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/magnetite"));
    public static final TagKey<Item> LITHIUM_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/lithium"));
    public static final TagKey<Item> COPPER_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/copper"));
    public static final TagKey<Item> BRONZE_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/bronze"));
    public static final TagKey<Item> LEPIDOLTIE_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/lepidolite"));
    public static final TagKey<Item> ALUMINIUM_DUSTS = ItemTags.create(new ResourceLocation("forge", "dusts/aluminium"));

    public static final TagKey<Item> TIN_NUGGETS = ItemTags.create(new ResourceLocation("forge", "nuggets/tin"));
    public static final TagKey<Item> MAGNETITE_NUGGETS = ItemTags.create(new ResourceLocation("forge", "nuggets/magnetite"));
    public static final TagKey<Item> COBALT_NUGGETS = ItemTags.create(new ResourceLocation("forge", "nuggets/cobalt"));
    public static final TagKey<Item> BRONZE_NUGGETS = ItemTags.create(new ResourceLocation("forge", "nuggets/bronze"));
    public static final TagKey<Item> ALUMINIUM_NUGGETS = ItemTags.create(new ResourceLocation("forge", "nuggets/aluminium"));


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
        tag(COPPER_DUSTS).add(ModItems.COPPER_DUST.get());
        tag(BRONZE_DUSTS).add(ModItems.BRONZE_DUST.get());
        tag(LEPIDOLTIE_DUSTS).add(ModItems.LEPIDOLITE_DUST.get());
        tag(ALUMINIUM_DUSTS).add(ModItems.ALUMINIUM_DUST.get());

        tag(TIN_INGOT).add(ModItems.TIN_INGOT.get());
        tag(COBALT_INGOT).add(ModItems.COBALT_INGOT.get());
        tag(MAGNETITE_INGOT).add(ModItems.MAGNETITE_INGOT.get());
        tag(BRONZE_INGOT).add(ModItems.BRONZE_INGOT.get());
        tag(ALUMINIUM_INGOT).add(ModItems.ALUMINIUM_INGOT.get());

        tag(TIN_NUGGETS).add(ModItems.TIN_NUGGET.get());
        tag(MAGNETITE_NUGGETS).add(ModItems.MAGNETITE_NUGGET.get());
        tag(COBALT_NUGGETS).add(ModItems.COBALT_NUGGET.get());
        tag(BRONZE_NUGGETS).add(ModItems.BRONZE_NUGGET.get());
        tag(ALUMINIUM_NUGGETS).add(ModItems.ALUMINIUM_NUGGET.get());
    }
}
