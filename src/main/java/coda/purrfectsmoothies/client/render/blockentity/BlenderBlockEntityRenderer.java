package coda.purrfectsmoothies.client.render.blockentity;

import coda.purrfectsmoothies.common.blocks.entities.BlenderBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;

public class BlenderBlockEntityRenderer implements BlockEntityRenderer<BlenderBlockEntity> {
    private static final float[][] TRANSFORMATIONS = new float[][] {
            {0.45f},
            {0.5f},
            {0.55f},
            {0.6f},
            {0.65f},
    };
    private final Minecraft mc = Minecraft.getInstance();

    public BlenderBlockEntityRenderer() {}

    @Override
    public void render(BlenderBlockEntity te, float partialTicks, PoseStack ms, MultiBufferSource bufferIn, int combinedLightIn, int p_112312_) {
        int items = te.countItems(te.getItems());

        for (int i = 0; i < items; i++) {

            final float[] transformation = TRANSFORMATIONS[i];
            final ItemStack stack = te.getItem(i);

            ms.pushPose();
            ms.mulPose(Vector3f.YN.rotationDegrees(te.blendingTicks * 15));
            ms.mulPose(Vector3f.XP.rotationDegrees(90));
            ms.translate(0.5, 0.45, transformation[0] - 1);
            ms.scale(0.65f, 0.65f, 0.65f);

            BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 0);
            mc.getItemRenderer().render(stack, ItemTransforms.TransformType.GROUND, true, ms, bufferIn, combinedLightIn, p_112312_, model);
            ms.popPose();
        }
    }
}