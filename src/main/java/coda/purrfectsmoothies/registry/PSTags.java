package coda.purrfectsmoothies.registry;

import coda.purrfectsmoothies.PurrfectSmoothies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PSTags {
    public static final TagKey<Item> SMOOTHIE_INGREDIENT = itemTag("smoothie_ingredients");

    private static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(PurrfectSmoothies.MOD_ID, path));
    }
}
