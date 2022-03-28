package net.paperfish.moonbits.entity;

import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GlareEntityModel extends AnimatedGeoModel<GlareEntity> {
    @Override
    public Identifier getModelLocation(GlareEntity glare) {
        return new Identifier(Moonbits.MODID, "geo/entity/glare.geo.json");
    }

    @Override
    public Identifier getTextureLocation(GlareEntity glare) {
        return new Identifier(Moonbits.MODID, "textures/entity/glare/glare.png");
    }

    @Override
    public Identifier getAnimationFileLocation(GlareEntity glare) {
        return new Identifier(Moonbits.MODID, "animations/entity/glare.animation.json");
    }
}
