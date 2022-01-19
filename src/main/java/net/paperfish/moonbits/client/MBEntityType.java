package net.paperfish.moonbits.client;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.MBEntities;
import net.paperfish.moonbits.entity.*;

@Environment(EnvType.CLIENT)
public class MBEntityType {
    public static final EntityModelLayer MODEL_MOOBLOOM_LAYER = new EntityModelLayer(new Identifier("moonbits", "moobloom"), "main");

    public static final EntityModelLayer MODEL_GRIZZLY_LAYER = new EntityModelLayer(new Identifier("moonbits", "grizzly_bear"), "main");

    public static final EntityModelLayer MODEL_GLARE = new EntityModelLayer(new Identifier("moonbits", "glare"), "main");

    public static final EntityModelLayer MODEL_SEAT_BLOCK = new EntityModelLayer(new Identifier("moonbits", "seat"), "main");

    // block entities :D
    //public static final EntityModelLayer BEDROLL_HEAD = new EntityModelLayer(new Identifier("moonbits", "bedroll_head"), "head");
    //public static final EntityModelLayer BEDROLL_FOOT = new EntityModelLayer(new Identifier("moonbits", "bedroll_foot"), "foot");

    public static void initEntityClient(){
        // entity renderer registering must be done client-side
        EntityRendererRegistry.register(MBEntities.MOOBLOOM, MoobloomEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_MOOBLOOM_LAYER, MoobloomEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(MBEntities.GRIZZLY_BEAR, GrizzlyBearEntityRenderer::new);
        //EntityModelLayerRegistry.registerModelLayer(MODEL_GRIZZLY_LAYER, GrizzlyBearEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(MBEntities.GLARE, GlareEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.SEAT_BLOCK_ENTITY, EmptyEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);
        EntityRendererRegistry.register(MBEntities.GLOW_ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(new Identifier("moonbits", "item_hook"),""));
            out.accept(new ModelIdentifier(new Identifier("moonbits", "glow_item_hook"),""));
        });

//        EntityRendererRegistry.register(MBEntities.SEAT_BLOCK_ENTITY, SeatEntityRenderer::new);
//        EntityModelLayerRegistry.registerModelLayer(MODEL_SEAT_BLOCK, SeatEntityModel::getTexturedModelData);

    }
}
