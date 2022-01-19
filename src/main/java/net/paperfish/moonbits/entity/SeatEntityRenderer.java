package net.paperfish.moonbits.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.client.MBEntityType;

@Environment(EnvType.CLIENT)
public class SeatEntityRenderer extends EntityRenderer<SeatBlockEntity> {
    private static final Identifier DEFAULT =  new Identifier("moonbits", "textures/entity/seat/seat.png");
    private final SeatEntityModel model;

    public SeatEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new SeatEntityModel(ctx.getPart(MBEntityType.MODEL_SEAT_BLOCK));
    }

    @Override
    public void render(SeatBlockEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(model.getLayer(DEFAULT));
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(SeatBlockEntity entity) {
        return DEFAULT;
    }
}
