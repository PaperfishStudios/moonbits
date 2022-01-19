package net.moonteam.moonbits.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.moonteam.moonbits.client.MBEntityType;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GlareEntityRenderer extends GeoEntityRenderer<GlareEntity> {
    public GlareEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new GlareEntityModel());
        this.shadowRadius = 0.7f;
    }

    @Override
    public RenderLayer getRenderType(GlareEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityCutoutNoCull(textureLocation);
    }
}
