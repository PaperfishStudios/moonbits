package net.paperfish.moonbits.block;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class GiantPuffballEntityRenderer extends GeoBlockRenderer<GiantPuffballBlockEntity> {

	public GiantPuffballEntityRenderer() {
		super(new GiantPuffballModel());
	}
}
