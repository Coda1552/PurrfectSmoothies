package coda.purrfectsmoothies.registry;

import coda.purrfectsmoothies.PurrfectSmoothies;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PSSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, PurrfectSmoothies.MOD_ID);

    public static final RegistryObject<SoundEvent> BLENDER_BLEND = register("blender_blend", "block");

    private static RegistryObject<SoundEvent> register(String name, String dir) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(PurrfectSmoothies.MOD_ID, dir + "." + name)));
    }
}
