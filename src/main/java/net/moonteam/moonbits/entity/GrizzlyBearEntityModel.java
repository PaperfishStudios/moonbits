package net.moonteam.moonbits.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.entity.Entity;

public class GrizzlyBearEntityModel<T extends Entity> extends QuadrupedEntityModel<T> {
//    protected GrizzlyBearEntityModel(ModelPart root, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, int childBodyYOffset) {
//        super(root, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
//    }

    public GrizzlyBearEntityModel(ModelPart root) {
        super(root, false, 10.0F, 4.0F, 2.0F, 2.0F, 24);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-4.5F, -13.0F, -10.0F, 11.0F, 9.0F, 11.0F)
                        .uv(33, 0).cuboid("nose", -1.5F, -8.0F, -14.0F, 5.0F, 4.0F, 4.0F)
                        .uv(51, 0).cuboid("left_ear", -6.5F, -15.0F, -5.0F, 4.0F, 5.0F, 2.0F)
                        .uv(51, 7).cuboid("right_ear", 4.5F, -15.0F, -5.0F, 4.0F, 5.0F, 2.0F),
                ModelTransform.pivot(0, 16.0F, -13.0F));

        modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
                        .uv(0, 20).cuboid("floof", -8.5F, -7.0F, -13.5F, 19.0F, 14.0F, 15.0F)
                        .uv(68, 19).cuboid("hind", -7.5F, -6.0F, 1.5F, 17.0F, 12.0F, 13.0F),
                ModelTransform.of(0.0F, 9.0F, 0.0F, 0, 0.0F, 0.0F));

        // leggys
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(68, 0).cuboid(-2.5F, 0.0F, -1.5F, 5.0F, 11.0F, 5.0F);
        modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(-7.0F, 13.0F, 10.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder, ModelTransform.pivot(9.0F, 13.0F, 10.0F));
        modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(-7.0F, 13.0F, -9.0F));
        modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder, ModelTransform.pivot(9.0F, 13.0F, -9.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    public ModelPart getHead() {
        return this.head;
    }
}
