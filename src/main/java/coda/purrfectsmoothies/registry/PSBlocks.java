package coda.purrfectsmoothies.registry;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.common.blocks.BlenderBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class PSBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PurrfectSmoothies.MOD_ID);

    public static final RegistryObject<Block> BLENDER = register("blender", () -> new BlenderBlock(BlockBehaviour.Properties.of(Material.METAL).strength(1.5F)));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return register(name, block, new Item.Properties().tab(PurrfectSmoothies.GROUP));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, Item.Properties itemProperties) {
        return register(name, block, BlockItem::new, itemProperties);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, BiFunction<Block, Item.Properties, BlockItem> item, Item.Properties itemProperties) {
        final RegistryObject<T> registryObject = BLOCKS.register(name, block);
        if (itemProperties != null)
            PSItems.ITEMS.register(name, () -> item == null ? new BlockItem(registryObject.get(), itemProperties) : item.apply(registryObject.get(), itemProperties));
        return registryObject;
    }
}
