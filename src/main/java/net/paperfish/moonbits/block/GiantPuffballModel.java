package net.paperfish.moonbits.block;

import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantPuffballModel extends AnimatedGeoModel<GiantPuffballBlockEntity> {
	@Override
	public Identifier getModelResource(GiantPuffballBlockEntity object) {
		return new Identifier(Moonbits.MODID, "geo/block/puffball.geo.json");
	}

	@Override
	public Identifier getTextureResource(GiantPuffballBlockEntity object) {
		return new Identifier(Moonbits.MODID, "textures/block/giant_puffball.png");
	}

	@Override
	public Identifier getAnimationResource(GiantPuffballBlockEntity animatable) {
		return new Identifier(Moonbits.MODID, "animations/block/puffball.animation.json");
	}
}
