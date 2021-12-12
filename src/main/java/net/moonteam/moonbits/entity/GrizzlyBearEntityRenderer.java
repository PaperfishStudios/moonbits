package net.moonteam.moonbits.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.moonteam.moonbits.client.MBEntityType;

public class GrizzlyBearEntityRenderer extends MobEntityRenderer<GrizzlyBearEntity, GrizzlyBearEntityModel<GrizzlyBearEntity>> {
    private static final Identifier BEAR =  new Identifier("moonbits", "textures/entity/bear/grizz.png");
    private static final Identifier HONEY =  new Identifier("moonbits", "textures/entity/bear/grizz_hunny.png");
    private static final Identifier WINNIE =  new Identifier("moonbits", "textures/entity/bear/winnie.png");
    private static final Identifier W_HUNNY =  new Identifier("moonbits", "textures/entity/bear/winnie_hunny.png");

    public GrizzlyBearEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrizzlyBearEntityModel<>(context.getPart(MBEntityType.MODEL_GRIZZLY_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTexture(GrizzlyBearEntity entity) {
        return BEAR;
    }
}
