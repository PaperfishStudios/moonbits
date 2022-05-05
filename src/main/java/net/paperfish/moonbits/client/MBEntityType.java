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
import net.paperfish.moonbits.registry.MBEntities;
import net.paperfish.moonbits.entity.*;

@Environment(EnvType.CLIENT)
public class MBEntityType {
    public static final EntityModelLayer MODEL_MOOBLOOM_LAYER = new EntityModelLayer(new Identifier("moonbits", "moobloom"), "main");

    public static void initEntityClient(){
        // entity renderer registering must be done client-side

        EntityRendererRegistry.register(MBEntities.GRIZZLY_BEAR, GrizzlyBearEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.SEAT_BLOCK_ENTITY, EmptyEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);
        EntityRendererRegistry.register(MBEntities.GLOW_ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(new Identifier("moonbits", "item_hook"),""));
            out.accept(new ModelIdentifier(new Identifier("moonbits", "glow_item_hook"),""));
        });
    }
}
