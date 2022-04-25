package coda.purrfectsmoothies.registry;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.common.blocks.entities.BlenderBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, PurrfectSmoothies.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlenderBlockEntity>> BLENDER = BLOCK_ENTITIES.register("blender", () -> BlockEntityType.Builder.of(BlenderBlockEntity::new, PSBlocks.BLENDER.get()).build(null));

}
