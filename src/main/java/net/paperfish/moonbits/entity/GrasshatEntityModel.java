package net.paperfish.moonbits.entity;

import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrasshatEntityModel extends AnimatedGeoModel<GrasshatEntity> {
    @Override
    public Identifier getModelResource(GrasshatEntity bear) {
        return new Identifier(Moonbits.MODID, "geo/entity/grasshat.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrasshatEntity bear) {
        return new Identifier(Moonbits.MODID, "textures/entity/grasshat.png");
    }

    @Override
    public Identifier getAnimationResource(GrasshatEntity bear) {
        return new Identifier(Moonbits.MODID, "animations/entity/grasshat.animation.json");
    }
}
