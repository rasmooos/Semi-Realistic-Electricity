package rasmoos.semirealisticelectricity.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.setup.ModSetup;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<Item> RAW_TIN = ITEMS.register("raw_tin", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> RAW_LEPIDOLITE = ITEMS.register("raw_lepidolite", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> RAW_MAGNETITE = ITEMS.register("raw_magnetite", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> RAW_COBALT = ITEMS.register("raw_cobalt", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));


    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MAGNETITE_INGOT = ITEMS.register("magnetite_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> TIN_DUST = ITEMS.register("tin_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MAGNETITE_DUST = ITEMS.register("magnetite_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> COBALT_DUST = ITEMS.register("cobalt_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> LITHIUM_DUST = ITEMS.register("lithium_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> COPPER_DUST = ITEMS.register("copper_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> BRONZE_DUST = ITEMS.register("bronze_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> LEPIDOLITE_DUST = ITEMS.register("lepidolite_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ALUMINIUM_DUST = ITEMS.register("aluminium_dust", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> MAGNETITE_NUGGET = ITEMS.register("magnetite_nugget", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> COBALT_NUGGET = ITEMS.register("cobalt_nugget", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> BRONZE_NUGGET = ITEMS.register("bronze_nugget", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> ALUMINIUM_NUGGET = ITEMS.register("aluminium_nugget", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));


    public static final RegistryObject<Item> RUBBER_TAP = ITEMS.register("rubber_tap",
            () -> new RubberTap(new Item.Properties().stacksTo(1).tab(ModSetup.ITEM_GROUP)));

    public static final RegistryObject<Item> RUBBER_RESIN = ITEMS.register("rubber_resin",
            () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    public static String getItemName(ItemLike itemLike) {
        return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
    }
}
