package net.paperfish.moonbits.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.MBParticles;


@Environment(EnvType.CLIENT)
public class MoonbitsClient implements ClientModInitializer {
    public static final CompassHud compassHud = new CompassHud(MinecraftClient.getInstance());

    @Override
    public void onInitializeClient() {
        // cutout
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MBBlocks.ROPE_LADDER,
                MBBlocks.IRON_LADDER,
                MBBlocks.TOUGH_GRASS,
                MBBlocks.BUTTERCUP,
                MBBlocks.FORGETMENOT,
                MBBlocks.TOADSTOOL,
                MBBlocks.SMALL_TOADSTOOLS,
                MBBlocks.WHITE_HYACINTH,
                MBBlocks.PINK_HYACINTH,
                MBBlocks.LIGHT_BLUE_HYACINTH,
                MBBlocks.RED_HYACINTH,
                MBBlocks.WILD_CARROTS,
                MBBlocks.WILD_POTATOES,
                MBBlocks.SEA_BEETS,
                MBBlocks.LAMPROOT,
                MBBlocks.CAVEBLOOM_VINE,
                MBBlocks.CAVEBLOOM_FLOWERS,
                MBBlocks.JACARANDA_LEAF_CARPET,
                MBBlocks.JACARANDA_SAPLING,
                MBBlocks.HANGING_JACARANDA_LEAVES,
                MBBlocks.HANGING_JACARANDA_LEAVES_PLANT,
                MBBlocks.JACARANDA_DOOR,
                MBBlocks.JACARANDA_TRAPDOOR,
                MBBlocks.HONEY_DOOR,
                MBBlocks.HONEY_TRAPDOOR,
                MBBlocks.APPLE_OAK_SPROUT,
                MBBlocks.APPLE_OAK_SAPLING,
                MBBlocks.GOLDEN_BIRCH_LEAF_CARPET,
                MBBlocks.GOLDEN_BIRCH_SAPLING,
                MBBlocks.GLASS_DOOR
        );

        MBData.registerBlockColours();
        MBParticles.registerParticleClient();

        MBEntityType.initEntityClient();

        HudRenderCallback.EVENT.register((((matrixStack, tickDelta) -> {
            compassHud.renderCompass(matrixStack);
        })));
        
    }
    
}
