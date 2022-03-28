package net.paperfish.moonbits.entity;

import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class GrizzlyBearEntityModel extends AnimatedGeoModel<GrizzlyBearEntity> {
    @Override
    public Identifier getModelLocation(GrizzlyBearEntity bear) {
        return new Identifier(Moonbits.MODID, "geo/entity/grizzly_bear.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GrizzlyBearEntity bear) {
        return new Identifier(Moonbits.MODID, "textures/entity/bear/grizz.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GrizzlyBearEntity bear) {
        return new Identifier(Moonbits.MODID, "animations/entity/grizzly_bear.animation.json");
    }

    @Override
    public void setLivingAnimations(GrizzlyBearEntity bear, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(bear, uniqueID, customPredicate);
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        IBone root = this.getAnimationProcessor().getBone("root");
        IBone head = this.getAnimationProcessor().getBone("head");
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));

//        if (bear.isBaby()) {
//            head.setScaleX(2f);
//            head.setScaleY(2f);
//            head.setScaleZ(2f);
//        }
//        else {
//            head.setScaleX(1f);
//            head.setScaleY(1f);
//            head.setScaleZ(1f);
//        }
    }
}
