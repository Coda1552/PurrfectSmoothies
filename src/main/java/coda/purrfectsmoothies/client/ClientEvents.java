package coda.purrfectsmoothies.client;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.client.render.BlenderBlockEntityRenderer;
import coda.purrfectsmoothies.registry.PSBlockEntities;
import coda.purrfectsmoothies.registry.PSBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PurrfectSmoothies.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(PSBlockEntities.BLENDER.get(), BlenderBlockEntityRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(PSBlocks.BLENDER.get(), RenderType.cutout());
    }
}
