package net.paperfish.moonbits.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.MBParticles;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.screen.KilnScreen;

import java.util.Objects;


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
                MBBlocks.POTTED_BUTTERCUP,
                MBBlocks.FORGETMENOT,
                MBBlocks.POTTED_FORGETMENOT,
                MBBlocks.PUFFBALLS,
                MBBlocks.POTTED_PUFFBALLS,
                MBBlocks.SAFFRON_MUSHROOM,
                MBBlocks.POTTED_SAFFRON_MUSHROOM,
                MBBlocks.SAFFRON_GILLS,
                MBBlocks.TOADSTOOL,
                MBBlocks.SMALL_TOADSTOOLS,
                MBBlocks.POTTED_SMALL_TOADSTOOLS,
                MBBlocks.TOADSTOOL_SHELF,
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
                MBBlocks.POTTED_JACARANDA_SAPLING,
                MBBlocks.HANGING_JACARANDA_LEAVES,
                MBBlocks.HANGING_JACARANDA_LEAVES_PLANT,
                MBBlocks.JACARANDA_DOOR,
                MBBlocks.JACARANDA_TRAPDOOR,
                MBBlocks.HONEY_DOOR,
                MBBlocks.HONEY_TRAPDOOR,
                MBBlocks.APPLE_OAK_SPROUT,
                MBBlocks.POTTED_APPLE_OAK_SPROUT,
                MBBlocks.APPLE_OAK_SAPLING,
                MBBlocks.POTTED_APPLE_OAK_SAPLING,
                MBBlocks.RED_OAK_LEAF_CARPET,
                MBBlocks.RED_OAK_SAPLING,
                MBBlocks.POTTED_RED_OAK_SAPLING,
                MBBlocks.GOLDEN_BIRCH_LEAF_CARPET,
                MBBlocks.GOLDEN_BIRCH_SAPLING,
                MBBlocks.POTTED_GOLDEN_BIRCH_SAPLING,
                MBBlocks.GLASS_DOOR,
                MBBlocks.WALL_LANTERN,
                MBBlocks.WALL_SOUL_LANTERN
        );

        MBData.registerBlockColours();
        MBParticles.registerParticleClient();

        MBEntityType.initEntityClient();

        ScreenRegistry.register(MBData.KILN_SCREEN_HANDLER, KilnScreen::new);

        HudRenderCallback.EVENT.register((((matrixStack, tickDelta) -> {
            compassHud.renderCompass(matrixStack);
        })));
        
    }
    
}
