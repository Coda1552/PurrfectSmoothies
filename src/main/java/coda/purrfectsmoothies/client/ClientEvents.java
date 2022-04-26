package coda.purrfectsmoothies.client;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.client.render.BlenderBlockEntityRenderer;
import coda.purrfectsmoothies.registry.PSBlockEntities;
import coda.purrfectsmoothies.registry.PSBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PurrfectSmoothies.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(PSBlocks.BLENDER.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(PSBlockEntities.BLENDER.get(), BlenderBlockEntityRenderer::new);
    }
}