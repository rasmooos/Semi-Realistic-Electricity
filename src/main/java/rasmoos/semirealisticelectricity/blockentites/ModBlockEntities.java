package rasmoos.semirealisticelectricity.blockentites;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rasmoos.semirealisticelectricity.SemiRealisticElectricity;
import rasmoos.semirealisticelectricity.blocks.ModBlocks;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SemiRealisticElectricity.MOD_ID);

    public static final RegistryObject<BlockEntityType<RubberLogTapEntity>> RUBBER_LOG_ENTITY =
            BLOCK_ENTITIES.register("rubber_log_entity",
                    () -> BlockEntityType.Builder.of(RubberLogTapEntity::new, ModBlocks.RUBBER_LOG_TAP.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronFurnaceBlockEntity>> IRON_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("iron_furnace_entity",
                    () -> BlockEntityType.Builder.of(IronFurnaceBlockEntity::new, ModBlocks.IRON_FURNACE_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrusherBlockEntity>> CRUSHER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crusher_block_entity",
                    () -> BlockEntityType.Builder.of(CrusherBlockEntity::new, ModBlocks.CRUSHER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<FluidCompactorEntity>> FLUID_COMPACTOR_ENTITY =
            BLOCK_ENTITIES.register("fluid_compactor_entity",
                    () -> BlockEntityType.Builder.of(FluidCompactorEntity::new, ModBlocks.FLUID_COMPACTOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<ElectrostaticSeparatorEntity>> ELECTROSTATIC_SEPARATOR_ENTITY =
            BLOCK_ENTITIES.register("electrostatic_separator_entity",
                    () -> BlockEntityType.Builder.of(ElectrostaticSeparatorEntity::new, ModBlocks.ELECTROSTATIC_SEPARATOR.get()).build(null));
}
