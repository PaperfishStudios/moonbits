package net.paperfish.moonbits.entity;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrizzlyBearEntityRenderer extends GeoEntityRenderer<GrizzlyBearEntity> {
    private static final Identifier BEAR =  new Identifier("moonbits", "textures/entity/bear/grizz.png");
    private static final Identifier HONEY =  new Identifier("moonbits", "textures/entity/bear/grizz_hunny.png");
    private static final Identifier WINNIE =  new Identifier("moonbits", "textures/entity/bear/winnie.png");
    private static final Identifier W_HUNNY =  new Identifier("moonbits", "textures/entity/bear/winnie_hunny.png");

    public GrizzlyBearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrizzlyBearEntityModel());
        this.shadowRadius = 0.7f;
    }

    @Override
    public void renderEarly(GrizzlyBearEntity bear, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
                            VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
                            float partialTicks) {
        super.renderEarly(bear, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        if (bear.isBaby()) {
            stackIn.scale(0.5F, 0.5F, 0.5F);
        }
        stackIn.scale(1.0F, 1.0F, 1.0F);
    }

    @Override
    public Identifier getTexture(GrizzlyBearEntity entity) {
        return BEAR;
    }
}
