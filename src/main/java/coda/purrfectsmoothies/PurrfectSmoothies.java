package coda.purrfectsmoothies;

import coda.purrfectsmoothies.registry.PSBlockEntities;
import coda.purrfectsmoothies.registry.PSBlocks;
import coda.purrfectsmoothies.registry.PSItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PurrfectSmoothies.MOD_ID)
public class PurrfectSmoothies {
    public static final String MOD_ID = "purrfectsmoothies";

    public PurrfectSmoothies() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        PSItems.ITEMS.register(bus);
        PSBlocks.BLOCKS.register(bus);
        PSBlockEntities.BLOCK_ENTITIES.register(bus);
    }

    public static final CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(PSItems.BURLY_BERRY.get());
        }
    };
}
