package net.moonteam.moonbits.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.moonteam.moonbits.MoonbitsMain;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GrizzlyBearEntityModel extends AnimatedGeoModel<GrizzlyBearEntity> {
    @Override
    public Identifier getModelLocation(GrizzlyBearEntity bear) {
        return new Identifier(MoonbitsMain.MOD_ID, "geo/entity/grizzly_bear.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GrizzlyBearEntity bear) {
        return new Identifier(MoonbitsMain.MOD_ID, "textures/entity/bear/grizz.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GrizzlyBearEntity bear) {
        return new Identifier(MoonbitsMain.MOD_ID, "animations/entity/grizzly_bear.animation.json");
    }

    @Override
    public void setLivingAnimations(GrizzlyBearEntity bear, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(bear, uniqueID, customPredicate);
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        IBone head = this.getAnimationProcessor().getBone("head");
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}
