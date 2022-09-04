package rasmoos.semirealisticelectricity.screen.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<MenuType<IronFurnaceMenu>> IRON_FURNACE_MENU = registerMenuType(IronFurnaceMenu::new, "iron_furnace_menu");
    public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU = registerMenuType(CrusherMenu::new, "crusher_menu");
    public static final RegistryObject<MenuType<FluidCompactorMenu>> FLUID_COMPACTOR_MENU = registerMenuType(FluidCompactorMenu::new, "fluid_compactor_menu");
    public static final RegistryObject<MenuType<ElectrostaticSeparatorMenu>> ELECTROSTATIC_SEPARATOR_MENU
            = registerMenuType(ElectrostaticSeparatorMenu::new, "electrostatic_separator_menu");
    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE_MENU
            = registerMenuType(ElectricFurnaceMenu::new, "electric_furnace_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
