package rasmoos.semirealisticelectricity.setup;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.FlammableLeaveBlock;
import rasmoos.semirealisticelectricity.blocks.FlammableRotatedPilarBlock;
import rasmoos.semirealisticelectricity.blocks.RubberLog;
import rasmoos.semirealisticelectricity.blocks.RubberTreeGrower;
import rasmoos.semirealisticelectricity.world.ModConfiguredFeatures;
import rasmoos.semirealisticelectricity.world.ModPlacedFeatures;

import java.util.function.Supplier;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SemiRealisticElectricity.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SemiRealisticElectricity.MOD_ID);
//    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SemiRealisticElectricity.MOD_ID);


    //private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.,SemiRealisticElectricity.MOD_ID);


    public static final void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
//        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModPlacedFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    // ITEMS
    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> RAW_LEPIDOLITE = ITEMS.register("raw_lepidolite", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> LITHIUM_DUST = ITEMS.register("lithium_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));


    public static final RegistryObject<Item> RAW_MAGNETITE = ITEMS.register("raw_magnetite", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MAGNETITE_INGOT = ITEMS.register("magnetite_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));


    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    // BLOCKS
    public static final RegistryObject<Block> MAGNETITE_ORE = registerBlock("magnetite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<Block> DEEPSLATE_MAGNETITE_ORE = registerBlock("deepslate_magnetite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> COBALT_ORE = registerBlock("cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(3, 7)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate_cobalt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE), UniformInt.of(3, 7)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> LEPIDOLITE_ORE = registerBlock("lepidolite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(3, 7)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<Block> DEEPSLATE_LEPIDOLITE_ORE = registerBlock("deepslate_lepidolite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE), UniformInt.of(3, 7)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)), ModSetup.ITEM_GROUP);
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<RotatedPillarBlock> RUBBER_LOG = registerBlock("rubber_log",
            () -> new RubberLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_RUBBER_LOG = registerBlock("stripped_rubber_log",
            () -> new FlammableRotatedPilarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> RUBBER_PLANKS = registerBlock("rubber_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }, ModSetup.ITEM_GROUP);

    public static final RegistryObject<Block> RUBBER_LEAVES = registerBlock("rubber_leaves",
            () -> new FlammableLeaveBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noOcclusion()), ModSetup.ITEM_GROUP);
    public static final RegistryObject<Block> RUBBER_SAPLING = registerBlock("rubber_sapling",
            () -> new SaplingBlock(new RubberTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion()), ModSetup.ITEM_GROUP);

//    public static final RegistryObject<Block> GEM_CUTTING_STATION = registerBlock("gem_cutting_station",
//            () -> new GemCuttingStation(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)), ModSetup.ITEM_GROUP);

//    public static final RegistryObject<BlockEntityType<GemCuttingStationBlockEntity>> GEM_CUTTING_STATION_BLOCK_ENTITY =
//            BLOCK_ENTITIES.register("gem_cutting_station_block_entity", () ->
//                    BlockEntityType.Builder.of(GemCuttingStationBlockEntity::new, Registration.GEM_CUTTING_STATION.get()).build(null));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result, tab);
        return result;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}


