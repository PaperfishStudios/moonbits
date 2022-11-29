package net.paperfish.moonbits.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBData;
import net.paperfish.moonbits.registry.MBParticles;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.particle.DripParticle;
import net.paperfish.moonbits.particle.FallingParticle;
import net.paperfish.moonbits.screen.CookingScreen;
import net.paperfish.moonbits.screen.KilnScreen;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;


@Environment(EnvType.CLIENT)
public class MoonbitsClient implements ClientModInitializer {
    public static final CompassHud compassHud = new CompassHud(MinecraftClient.getInstance());

    @Override
    public void onInitializeClient(ModContainer container) {
        // cutout
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MBBlocks.ROPE_LADDER,

                MBBlocks.SOURSOBS, MBBlocks.POTTED_SOURSOBS,

                MBBlocks.SAFFRON_MUSHROOM, MBBlocks.POTTED_SAFFRON_MUSHROOM, MBBlocks.SAFFRON_GILLS,

                MBBlocks.FROSTY_HEATHER, MBBlocks.SUNSET_HEATHER, MBBlocks.TWILIGHT_HEATHER,
				MBBlocks.POTTED_FROSTY_HEATHER, MBBlocks.POTTED_SUNSET_HEATHER, MBBlocks.POTTED_TWILIGHT_HEATHER,
                MBBlocks.LUPINE, MBBlocks.YUCCA,

                MBBlocks.TINY_BARREL_CACTUS, MBBlocks.SMALL_BARREL_CACTUS, MBBlocks.BARREL_CACTUS, MBBlocks.LARGE_BARREL_CACTUS,
                MBBlocks.MARIGOLD, MBBlocks.POTTED_MARIGOLD,

                MBBlocks.WILD_CARROTS, MBBlocks.WILD_POTATOES, MBBlocks.SEA_BEETS,

                MBBlocks.BEACHGRASS, MBBlocks.TALL_BEACHGRASS,
                MBBlocks.COTTONGRASS, MBBlocks.TALL_COTTONGRASS,
                MBBlocks.DESERT_BRUSH, MBBlocks.TALL_DESERT_BRUSH,
				MBBlocks.BRITTLEBUSH_FLOWERS, MBBlocks.BRITTLEBUSH_LEAVES,
				MBBlocks.OCOTILLO, MBBlocks.FLOWERING_OCOTILLO,

                MBBlocks.LAMPROOT_BULB,

                MBBlocks.LAMPROOT_DOOR, MBBlocks.LAMPROOT_TRAPDOOR,

                MBBlocks.CAVEBLOOM_VINE, MBBlocks.CAVEBLOOM_FLOWERS,

                MBBlocks.CEDAR_DOOR, MBBlocks.CEDAR_TRAPDOOR, MBBlocks.CEDAR_SAPLING, MBBlocks.POTTED_CEDAR_SAPLING,

                MBBlocks.TREE_TAP, MBBlocks.SYRUP_TREE_TAP, MBBlocks.SAP_TREE_TAP, MBBlocks.RESIN_TREE_TAP,

				MBBlocks.COPPER_OXIDE_LANTERN, MBBlocks.COPPER_OXIDE_CAMPFIRE,

                MBBlocks.WALL_LANTERN, MBBlocks.WALL_SOUL_LANTERN, MBBlocks.WALL_COPPER_OXIDE_LANTERN
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                MBBlocks.SYRUP_BLOCK,
				MBBlocks.HELIODOR_ROD, MBBlocks.LARIMAR_ROD
        );

        registerParticleClient();
        dataClient();

        MBEntityType.initEntityClient();

        HandledScreens.register(MBData.KILN_SCREEN_HANDLER, KilnScreen::new);
        HandledScreens.register(MBData.COOKING_SCREEN_HANDLER, CookingScreen::new);

        HudRenderCallback.EVENT.register((((matrixStack, tickDelta) -> compassHud.renderCompass(matrixStack))));

    }

    public static void dataClient() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                    BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
                    return provider == null ? -1 : provider.getColor(state, view, pos, tintIndex);},
                MBBlocks.GRASS_TURF,
                MBBlocks.GRASS_TURF_STAIRS,
                MBBlocks.GRASS_TURF_SLAB,
                MBBlocks.GRASS_CARPET
        );
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.GRASS);
                    return provider == null ? -1 : provider.getColor(stack, tintIndex);},
                MBBlocks.GRASS_TURF,
                MBBlocks.GRASS_TURF_STAIRS,
                MBBlocks.GRASS_TURF_SLAB,
                MBBlocks.GRASS_CARPET
        );

		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x639956, MBBlocks.CEDAR_LEAVES);
//		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x619971, MBBlocks.JUNIPER_LEAVES);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x639956, MBBlocks.CEDAR_LEAVES);
//		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x619971, MBBlocks.JUNIPER_LEAVES);

//		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//					ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.SPRUCE_LEAVES);
//					return provider == null ? -1 : provider.getColor(stack, tintIndex);},
//				MBBlocks.JUNIPER_LEAVES,
//				MBBlocks.CEDAR_LEAVES
//		);

        // item model predicates uwu
//        ModelPredicateProviderRegistrySpecificAccessor.callRegister(MBBlocks.BARREL_CACTUS.asItem(), new Identifier("water_level"), (stack, world, entity, seed) -> {
//            NbtCompound nbtCompound = stack.getSubNbt("BlockStateTag");
//            try {
//                NbtElement nbtElement;
//                if (nbtCompound != null && (nbtElement = nbtCompound.get(BarrelCactusBlock.LEVEL.getName())) != null) {
//                    return (float)Integer.parseInt(nbtElement.asString()) / 4.0f;
//                }
//            }
//            catch (NumberFormatException numberFormatException) {
//                // empty catch block
//            }
//            return 1.0f;
//        });
    }

//    @Environment(EnvType.CLIENT)
    public static void registerParticleClient() {
        // adds the particle texture to the texture atlas
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(Moonbits.MODID, "particle/falling_leaf"));
            registry.register(new Identifier(Moonbits.MODID, "particle/falling_oak_leaf"));
            registry.register(new Identifier(Moonbits.MODID, "particle/falling_wattle"));
            registry.register(new Identifier(Moonbits.MODID, "particle/falling_spores"));
        }));
        ParticleFactoryRegistry.getInstance().register(MBParticles.FALLING_LEAF, FallingParticle.FallingLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(MBParticles.FALLING_OAK_LEAF, FallingParticle.FallingLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(MBParticles.FALLING_WATTLE, FallingParticle.FallingLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(MBParticles.FALLING_SPORES, FallingParticle.FallingSporeFactory::new);

        ParticleFactoryRegistry.getInstance().register(MBParticles.DRIPPING_SYRUP, (a) -> new DripParticle.DrippingHoneyFactory(a, 0.622f, 0.308f, 0.182f));
        ParticleFactoryRegistry.getInstance().register(MBParticles.FALLING_SYRUP, (a) -> new DripParticle.FallingHoneyFactory(a, 0.582f, 0.248f, 0.182f));
        ParticleFactoryRegistry.getInstance().register(MBParticles.LANDING_SYRUP, (a) -> new DripParticle.LandingHoneyFactory(a, 0.522f, 0.208f, 0.182f));

    }

}
