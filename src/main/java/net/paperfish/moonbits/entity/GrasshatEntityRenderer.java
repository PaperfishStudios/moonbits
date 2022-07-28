package net.paperfish.moonbits.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrasshatEntityRenderer extends GeoEntityRenderer<GrasshatEntity> {
    private static final Identifier BEAR =  new Identifier("moonbits", "textures/entity/grasshat.png");

    public GrasshatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrasshatEntityModel());
        this.shadowRadius = 0.7f;
    }

//    @Override
//    public void renderEarly(GrasshatEntity bear, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
//                            VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
//                            float partialTicks) {
//        super.renderEarly(bear, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
//        if (bear.isBaby()) {
//            stackIn.scale(0.5F, 0.5F, 0.5F);
//        }
//        stackIn.scale(1.0F, 1.0F, 1.0F);
//    }

    @Override
    public Identifier getTexture(GrasshatEntity entity) {
        return BEAR;
    }
}
