package coda.purrfectsmoothies.registry;

import coda.purrfectsmoothies.PurrfectSmoothies;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PurrfectSmoothies.MOD_ID);

    public static final RegistryObject<Item> BURLY_BERRY = ITEMS.register("burly_berry", () -> new Item(new Item.Properties().tab(PurrfectSmoothies.GROUP).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
}
