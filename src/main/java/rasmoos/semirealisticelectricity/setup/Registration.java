package rasmoos.semirealisticelectricity.setup;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.world.WorldGeneration;

import java.util.function.Supplier;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SemiRealisticElectricity.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SemiRealisticElectricity.MOD_ID);
    //private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SemiRealisticElectricity.MOD_ID);
    //private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.,SemiRealisticElectricity.MOD_ID);



    public static final void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        WorldGeneration.register(FMLJavaModLoadingContext.get().getModEventBus());
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

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result, tab);
        return result;
    }

    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}


