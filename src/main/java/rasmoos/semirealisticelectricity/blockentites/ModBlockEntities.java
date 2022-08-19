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
}
