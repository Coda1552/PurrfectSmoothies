package coda.purrfectsmoothies.client.model;

import coda.purrfectsmoothies.PurrfectSmoothies;
import coda.purrfectsmoothies.common.blocks.entities.BlenderBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlenderModel extends AnimatedGeoModel<BlenderBlockEntity> {

	@Override
	public ResourceLocation getAnimationFileLocation(BlenderBlockEntity animatable) {
		return null;
	}

	@Override
	public ResourceLocation getModelLocation(BlenderBlockEntity animatable) {
		return new ResourceLocation(PurrfectSmoothies.MOD_ID, "geo/block/blender.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(BlenderBlockEntity entity) {
		return new ResourceLocation(PurrfectSmoothies.MOD_ID, "textures/block/blender.png");
	}
}