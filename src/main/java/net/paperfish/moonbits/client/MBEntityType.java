package net.paperfish.moonbits.client;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.registry.MBEntities;
import net.paperfish.moonbits.entity.*;

@Environment(EnvType.CLIENT)
public class MBEntityType {

    public static void initEntityClient(){
        // entity renderer registering must be done client-side

        EntityRendererRegistry.register(MBEntities.GRIZZLY_BEAR, GrizzlyBearEntityRenderer::new);
        EntityRendererRegistry.register(MBEntities.GRASSHAT, GrasshatEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.SEAT_BLOCK_ENTITY, EmptyEntityRenderer::new);

        EntityRendererRegistry.register(MBEntities.ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);
        EntityRendererRegistry.register(MBEntities.GLOW_ITEM_HOOK_ENTITY, ItemHookEntityRenderer::new);

//        BlockEntityRendererRegistry.register(MBBlocks.BOILING_CAULDRON_ENTITY, BoilingCauldronEntityRenderer::new);

        ModelLoadingRegistry.INSTANCE.registerModelProvider((manager, out) -> {
            out.accept(new ModelIdentifier(new Identifier("moonbits", "item_hook"),""));
            out.accept(new ModelIdentifier(new Identifier("moonbits", "glow_item_hook"),""));
        });
    }
}
