package net.paperfish.moonbits.entity;

import net.fabricmc.api.Environment;

import net.fabricmc.api.EnvType;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.client.MBEntityType;

@Environment(EnvType.CLIENT)
public class MoobloomEntityRenderer extends MobEntityRenderer<MoobloomEntity, MoobloomEntityModel<MoobloomEntity>> {
    private static final Identifier BUTTERCUP =  new Identifier("moonbits", "textures/entity/moobloom/moobloom.png");
    private static final Identifier FORGETMENOT =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_forget_me_not.png");
    private static final Identifier DANDELION =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_dandelion.png");
    private static final Identifier POPPY =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_poppy.png");
    private static final Identifier BLUE_ORCHID =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_orchid.png");
    private static final Identifier ALLIUM =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_allium.png");
    private static final Identifier AZURE_BLUET =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_bluet.png");
    private static final Identifier RED_TULIP =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_tulip_r.png");
    private static final Identifier ORANGE_TULIP =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_tulip_o.png");
    private static final Identifier WHITE_TULIP =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_tulip_w.png");
    private static final Identifier PINK_TULIP =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_tulip_p.png");
    private static final Identifier OXEYE_DAISY =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_oxeye.png");
    private static final Identifier CORNFLOWER =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_cornflower.png");
    private static final Identifier LILY_OF_THE_VALLEY =  new Identifier("moonbits", "textures/entity/moobloom/moobloom_lily.png");

    public MoobloomEntityRenderer(EntityRendererFactory.Context context) {
	    super(context, new MoobloomEntityModel<>(context.getPart(MBEntityType.MODEL_MOOBLOOM_LAYER)), 0.7F);
        this.addFeature(new MoobloomFlowerFeatureRenderer<>(this));
    }

    @Override
    public Identifier getTexture(MoobloomEntity entity) {
        return switch (entity.getMoobloomType().name) {
            case ("buttercup") -> BUTTERCUP;
            case ("forget_me_not") -> FORGETMENOT;
            case ("dandelion") -> DANDELION;
            case ("poppy") -> POPPY;
            case ("blue_orchid") -> BLUE_ORCHID;
            case ("allium") -> ALLIUM;
            case ("azure_bluet") -> AZURE_BLUET;
            case ("red_tulip") -> RED_TULIP;
            case ("orange_tulip") -> ORANGE_TULIP;
            case ("white_tulip") -> WHITE_TULIP;
            case ("pink_tulip") -> PINK_TULIP;
            case ("oxeye_daisy") -> OXEYE_DAISY;
            case ("cornflower") -> CORNFLOWER;
            case ("lily_of_the_valley") -> LILY_OF_THE_VALLEY;
            default -> BUTTERCUP;
        };

    }
    
}
