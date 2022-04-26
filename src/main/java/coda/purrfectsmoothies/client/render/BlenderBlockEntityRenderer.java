package coda.purrfectsmoothies.client.render;

import coda.purrfectsmoothies.client.model.BlenderModel;
import coda.purrfectsmoothies.common.blocks.entities.BlenderBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class BlenderBlockEntityRenderer extends GeoBlockRenderer<BlenderBlockEntity> {
    private static final float[][] TRANSFORMATIONS = new float[][] {
            {0.45f, 0.5f},
            {0.5f, 0.4f},
            {0.55f, 0.3f},
            {0.6f, 0.2f},
            {0.65f, 0.1f},
    };
    private final Minecraft mc = Minecraft.getInstance();

    public BlenderBlockEntityRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new BlenderModel());
    }

    @Override
    public RenderType getRenderType(BlenderBlockEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    // todo - make the items rotate
    @Override
    public void render(BlenderBlockEntity te, float partialTicks, PoseStack ms, MultiBufferSource bufferIn, int packedLightIn) {
        int items = te.countItems(te.getItems());

        for (int i = 0; i < items; i++) {

            final float[] transformation = TRANSFORMATIONS[i];
            final ItemStack stack = te.getItem(i);

            ms.pushPose();
            ms.translate(0.5, transformation[0], 0.5);
//            ms.scale(0.5f, 0.5f, 0.5f);
            ms.mulPose(Vector3f.YN.rotationDegrees(te.blendingTicks * 15));
            ms.mulPose(Vector3f.XN.rotation(transformation[0]));
            //ms.mulPose(Vector3f.XP.rotationDegrees(90));

            BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 0);
            mc.getItemRenderer().render(stack, ItemTransforms.TransformType.GROUND, true, ms, bufferIn, packedLightIn, 0, model);
            ms.popPose();
        }

        ms.translate(0, -0.0125, 0);

        super.render(te, partialTicks, ms, bufferIn, packedLightIn);
    }

}