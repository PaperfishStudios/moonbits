package net.paperfish.moonbits.entity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class SeatEntityModel<T extends Entity> extends SinglePartEntityModel<T> {
    private final ModelPart root;

    public SeatEntityModel(ModelPart root) {
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("seat", ModelPartBuilder.create()
                .uv(0, 0)
                .cuboid(-8.0F, -4.0F, -8.0F, 16.0F, 8.0F, 16.0F),
                ModelTransform.of(0.0F, 4.0F, 0.0F, 3.1415928F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    public ModelPart getPart() {
        return this.root;
    }
}
