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
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.util.RenderUtils;

public class BlenderBlockEntityRenderer extends GeoBlockRenderer<BlenderBlockEntity> {
    private static final float[][] TRANSFORMATIONS = new float[][] {
            {0.45f, 0.5f},
            {0.5f, 0.4f},
            {0.55f, 0.3f},
            {0.6f, 0.2f},
            {0.65f, 0.1f},
    };
    private final Minecraft mc = Minecraft.getInstance();
    private MultiBufferSource renderTypeBuffer;
    private BlenderBlockEntity animatable;

    public BlenderBlockEntityRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new BlenderModel());
    }

    @Override
    public RenderType getRenderType(BlenderBlockEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack ms, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        int items = animatable.countItems(animatable.getItems());

        if (bone.name.equals("bone")) {
            for (int i = 0; i < items; i++) {
                final float[] transformation = TRANSFORMATIONS[i];
                final ItemStack stack = animatable.getItem(i);

                ms.pushPose();

                RenderUtils.translate(bone, ms);
                RenderUtils.moveToPivot(bone, ms);
                RenderUtils.rotate(bone, ms);
                RenderUtils.scale(bone, ms);
                RenderUtils.moveBackFromPivot(bone, ms);

                ms.translate(0.0, transformation[0] - 0.05f, 0.0);
                ms.scale(0.5f, 0.5f, 0.5f);
                ms.mulPose(Vector3f.YN.rotationDegrees(animatable.blendingTicks * 15));
                ms.mulPose(Vector3f.XN.rotation(transformation[0]));
                ms.mulPose(Vector3f.XP.rotationDegrees(90));

                mc.getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.GROUND, packedLightIn, packedOverlayIn, ms, renderTypeBuffer, 0);
                RenderType type = getRenderType(animatable, 1F, ms, renderTypeBuffer, null, packedLightIn, getTextureLocation(animatable));
                bufferIn = renderTypeBuffer.getBuffer(type);

                ms.popPose();
            }

            ms.translate(0, -0.0125, 0);

        }
        super.renderRecursively(bone, ms, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void renderLate(BlenderBlockEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        this.renderTypeBuffer = renderTypeBuffer;
        this.animatable = animatable;
    }
}