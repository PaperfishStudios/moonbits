package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockStateDefinitionProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.WallShape;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBItems;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.PebbleBlock;
import net.paperfish.moonbits.block.PlanterBoxBlock;
import net.paperfish.moonbits.block.ToadstoolBlock;
import net.paperfish.moonbits.mixin.TextureKeyAccessor;
import net.paperfish.moonbits.mixin.TexturedModelAccessor;
import net.paperfish.moonbits.registry.MBBlockFamilies;
import net.paperfish.moonbits.registry.MBBlockFamily;

import java.util.*;

public class MBModelProvider extends FabricBlockStateDefinitionProvider {

    public static final Model WALL_PLANT = blockFromVanilla("glow_lichen", TextureKey.ALL);
    public static final TexturedModel.Factory CUBE_BOTTOM_TOP = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, Models.CUBE_BOTTOM_TOP);
    public static final TexturedModel.Factory CUBE_COLUMN = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideEnd, Models.CUBE_COLUMN);
    public static final TextureKey OVERLAY = TextureKeyAccessor.createTextureKey("overlay", TextureKey.SIDE);
    public static final Model GRASS_BLOCK = blockFromVanilla("grass_block", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, OVERLAY);
    public static final TexturedModel.Factory TINTED_GRASSLIKE = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, GRASS_BLOCK);

    public static final Model WALL_LANTERN = block("template_wall_lantern", TextureKey.LANTERN);
    public static final TexturedModel.Factory WALL_LANTERN_F = TexturedModelAccessor.callMakeFactory(Texture::lantern, WALL_LANTERN);

    public static final Model TINTED_CUBE = block("tinted/tinted_cube", TextureKey.ALL);
    public static final TexturedModel.Factory TINTED_BLOCK = TexturedModelAccessor.callMakeFactory(Texture::all, TINTED_CUBE);
    public static final Model TINTED_SLAB = block("tinted/tint_slab", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_SLAB_TOP = block("tinted/tint_slab_top", "_top", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final TexturedModel.Factory TINTED_SLAB_F = TexturedModelAccessor.callMakeFactory(Texture::all, TINTED_SLAB);
    public static final TexturedModel.Factory TINTED_SLAB_TOP_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_SLAB_TOP);
    public static final Model TINTED_STAIRS = block("tinted/tint_stairs", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_INNER_STAIRS = block("tinted/tint_stairs_inner", "_inner", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_OUTER_STAIRS = block("tinted/tint_stairs_outer", "_outer", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final TexturedModel.Factory TINTED_STAIRS_F = TexturedModelAccessor.callMakeFactory(Texture::all, TINTED_STAIRS);
    public static final TexturedModel.Factory TINTED_STAIRS_INNER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_INNER_STAIRS);
    public static final TexturedModel.Factory TINTED_STAIRS_OUTER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_OUTER_STAIRS);
    public static final Model TINTED_CARPET = block("tinted/tint_carpet", TextureKey.WOOL);
    public static final TexturedModel.Factory TINTED_CARPET_F = TexturedModelAccessor.callMakeFactory(Texture::wool, TINTED_CARPET);

    public static final TextureKey LEAF = TextureKeyAccessor.createTextureKey("leaf", TextureKey.WOOL);
    public static final Model LEAF_CARPET = block("leaf_carpet", LEAF);
    public static final TexturedModel.Factory LEAF_CARPET_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::leaf, LEAF_CARPET);

    public static final TextureKey CAP = TextureKeyAccessor.createTextureKey("cap", TextureKey.TOP);
    public static final Model CAPPED_CROSS = block("capped_cross", TextureKey.CROSS, CAP);
    public static final TexturedModel.Factory CAPPED_CROSS_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::capCross, CAPPED_CROSS);

    public static final TextureKey INNER = TextureKeyAccessor.createTextureKey("inner", TextureKey.END);
    public static final Model PLANTER_BOX = block("planter_box/planter_box", TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_INNER = block("planter_box/planter_box_inner", TextureKey.TOP, INNER, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_INNER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::pbInner, PLANTER_BOX);
    public static final Model PLANTER_BOX_OUTER = block("planter_box/planter_box_outer", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_OUTER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::edgeTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_SIDE = block("planter_box/planter_box_side", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_SIDE_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_SIDE_B = block("planter_box/planter_box_side_b", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_SIDE_B_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_INVENTORY = block("planter_box/planter_box_inventory", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_INVENTORY_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::edgeTopBottom, PLANTER_BOX);
    public static final Model SPAWN_EGG = new Model(Optional.of(new Identifier("minecraft", "item/template_spawn_egg")), Optional.empty());

    public List<Block> generatedBlocks = new ArrayList<>();

    public MBModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // TODO: work out how to do this junk :/
        MBBlockFamilies.getFamilies().filter(MBBlockFamily::shouldGenerateModels)
                .forEach(family -> generateFamily(generator, family, this));

        // utility blocks :>
        generator.registerNorthDefaultHorizontalRotation(MBBlocks.ROPE_LADDER);
        generator.registerItemModel(MBBlocks.ROPE_LADDER);
        generator.registerNorthDefaultHorizontalRotation(MBBlocks.IRON_LADDER);
        generator.registerItemModel(MBBlocks.IRON_LADDER);
        generator.registerCooker(MBBlocks.KILN, TexturedModel.ORIENTABLE_WITH_BOTTOM);
        generator.registerItemModel(MBBlocks.KILN);

        seatBlock(MBBlocks.LEATHER_SEAT, generator);
        seatBlock(MBBlocks.WHITE_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.LIGHT_GRAY_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.GRAY_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.BLACK_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.GREEN_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.LIME_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.YELLOW_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.ORANGE_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.BROWN_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.RED_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.PINK_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.MAGENTA_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.PURPLE_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.LIGHT_BLUE_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.CYAN_LEATHER_SEAT, generator);
        seatBlock(MBBlocks.BLUE_LEATHER_SEAT, generator);

        generator.registerDoor(MBBlocks.GLASS_DOOR);

        // trese
        generator.registerParentedItemModel(MBBlocks.BUDDING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/budding_oak_leaves"));
        generator.registerParentedItemModel(MBBlocks.FLOWERING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/flowering_oak_leaves"));
        generator.registerParentedItemModel(MBBlocks.FRUITING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/fruiting_oak_leaves"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.BUDDING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/budding_oak_leaves")));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FLOWERING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/flowering_oak_leaves")));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FRUITING_OAK_LEAVES, new Identifier(Moonbits.MOD_ID, "blocks/fruiting_oak_leaves")));
        flowerPotPlant(MBBlocks.APPLE_OAK_SPROUT, MBBlocks.POTTED_APPLE_OAK_SPROUT, TintType.NOT_TINTED, generator, false);
        flowerPotPlant(MBBlocks.APPLE_OAK_SAPLING, MBBlocks.POTTED_APPLE_OAK_SAPLING, TintType.NOT_TINTED, generator);

        flowerPotPlant(MBBlocks.GOLDEN_BIRCH_SAPLING, MBBlocks.POTTED_GOLDEN_BIRCH_SAPLING, TintType.NOT_TINTED, generator);
        generator.registerSingleton(MBBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES);
        leafCarpet(MBBlocks.GOLDEN_BIRCH_LEAVES, MBBlocks.GOLDEN_BIRCH_LEAF_CARPET, generator);

        flowerPotPlant(MBBlocks.RED_OAK_SAPLING, MBBlocks.POTTED_RED_OAK_SAPLING, TintType.NOT_TINTED, generator);
        generator.registerSingleton(MBBlocks.RED_OAK_LEAVES, TexturedModel.LEAVES);
        leafCarpet(MBBlocks.RED_OAK_LEAVES, MBBlocks.RED_OAK_LEAF_CARPET, generator);

        log(MBBlocks.JACARANDA_LOG, MBBlocks.JACARANDA_WOOD, generator);
        log(MBBlocks.STRIPPED_JACARANDA_LOG, MBBlocks.STRIPPED_JACARANDA_WOOD, generator);
        flowerPotPlant(MBBlocks.JACARANDA_SAPLING, MBBlocks.POTTED_JACARANDA_SAPLING, TintType.NOT_TINTED, generator);
        jacaranda(generator);

        // foragin :3
        tintableCross(MBBlocks.WILD_POTATOES, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.WILD_CARROTS, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.SEA_BEETS, TintType.NOT_TINTED, generator);

        generator.registerCrop(MBBlocks.LETTUCE_CROP, Properties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        generator.registerSingleton(MBBlocks.LETTUCE_BLOCK, CUBE_BOTTOM_TOP);

        // ground
        topSoil(MBBlocks.LEAFBED, Blocks.DIRT, generator);
        toughGrass(generator);
        turf(generator);

        generator.registerSimpleCubeAll(MBBlocks.REGOLITH);
        generator.registerSimpleCubeAll(MBBlocks.PEAT_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.PEAT_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.CLAY_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.GOLD_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.COPPER_DEPOSIT);

        pebbles(generator);
        generator.registerSimpleCubeAll(MBBlocks.FOSSIL);

        // flowers n fungi :D
        flowerPotPlant(MBBlocks.BUTTERCUP, MBBlocks.POTTED_BUTTERCUP, TintType.NOT_TINTED, generator);
        flowerPotPlant(MBBlocks.FORGETMENOT, MBBlocks.POTTED_FORGETMENOT, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.WHITE_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.PINK_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.LIGHT_BLUE_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.RED_HYACINTH, TintType.NOT_TINTED, generator);

        cappedCross(MBBlocks.PUFFBALLS, TintType.NOT_TINTED, generator, true);
        pottedBlock(MBBlocks.PUFFBALLS, MBBlocks.POTTED_PUFFBALLS, generator);
        flowerPotPlant(MBBlocks.SAFFRON_MUSHROOM, MBBlocks.POTTED_SAFFRON_MUSHROOM, TintType.NOT_TINTED, generator);
        flowerPotPlant(MBBlocks.SMALL_TOADSTOOLS, MBBlocks.POTTED_SMALL_TOADSTOOLS, TintType.NOT_TINTED, generator);
        toadstool(generator);
//        generator.registerSimpleCubeAll(MBBlocks.RED_MUSH_BRICKS);
//        generator.registerSimpleCubeAll(MBBlocks.BROWN_MUSH_BRICKS);

        redBrownMushrooms(generator);
        generator.registerMushroomBlock(MBBlocks.SAFFRON_MUSHROOM_CAP);
        gills(generator);
        giantToadstoolCap(generator);
        generator.registerAxisRotated(MBBlocks.MUSHROOM_STEM, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.RED_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.BROWN_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.TOADSTOOL_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.SAFFRON_MUSH_LAMP);

        tintableCross(MBBlocks.MINI_LILY, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_OXEYE, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_BLUET, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_DANDELION, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_POPPY, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_ORCHID, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_CORNFLOWER, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_ALLIUM, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_TULIP_W, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_TULIP_P, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_TULIP_O, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_TULIP_R, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.MINI_FORGETMENOT, TintType.NOT_TINTED, generator);

        lamproot(generator);
        wallPlant(MBBlocks.CAVEBLOOM_FLOWERS, generator);
        wallPlant(MBBlocks.CAVEBLOOM_VINE, generator);

        // mob-related blocks n stuff
        bedroll(generator);
        generator.registerCarpet(MBBlocks.FUR_BLOCK, MBBlocks.FUR_CARPET);

        // storage blocks
        generator.registerSingleton(MBBlocks.APPLE_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.CARROT_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.POTATO_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.BEETROOT_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.EGG_BASKET, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.COCOA_SACK, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.GLISTERING_MELON_BLOCK, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.SWEET_BERRY_BASKET, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.GLOW_BERRY_BASKET, CUBE_BOTTOM_TOP);

        generator.registerSingleton(MBItems.SWEET_BERRY_PITS, TexturedModel.PARTICLE);
        generator.excludeFromSimpleItemModelGeneration(MBItems.SWEET_BERRY_PITS);
        generator.registerSingleton(MBItems.GLOW_BERRY_PITS, TexturedModel.PARTICLE);
        generator.excludeFromSimpleItemModelGeneration(MBItems.GLOW_BERRY_PITS);
        generator.registerSimpleCubeAll(MBBlocks.SWEET_BERRY_HEDGE);
        generator.registerSimpleCubeAll(MBBlocks.GLOW_BERRY_HEDGE);
        generator.registerSimpleCubeAll(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE);
        generator.registerSimpleCubeAll(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE);

        generator.registerAxisRotated(MBBlocks.SUGAR_CANE_BUNDLE, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.BAMBOO_BUNDLE, CUBE_COLUMN);
        generator.registerStateWithModelReference(MBBlocks.KELP_BLOCK, Blocks.DRIED_KELP_BLOCK);

        generator.registerAxisRotated(MBBlocks.NETHER_WART_BUNDLE, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.SPOOL, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.PAPER_BUNDLE, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.STICK_STACK, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.CHARCOAL_LOG, CUBE_COLUMN);

        generator.registerSimpleCubeAll(MBBlocks.SCUTE_BLOCK);

        generator.registerSimpleCubeAll(MBBlocks.ROTTEN_FLESH_BLOCK);
        generator.registerAxisRotated(MBBlocks.BONE_BUNDLE, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.SPIDER_EYE_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.PHANTOM_MEMBRANE_BLOCK);
        //generator.registerAxisRotated(MBBlocks.BLAZE_ROD, ModelIds.getBlockModelId(Blocks.CHAIN));
        generator.registerAxisRotated(MBBlocks.BLAZE_ROD_BUNDLE, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.ENDER_PEARL_BLOCK);

        generator.registerSimpleCubeAll(MBBlocks.QUARTZ_SHARD_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.AMETHYST_SHARD_BLOCK);

        blazeRod(generator);
        wallLantern(MBBlocks.WALL_LANTERN, Blocks.LANTERN, generator);
        wallLantern(MBBlocks.WALL_SOUL_LANTERN, Blocks.SOUL_LANTERN, generator);

        // these are just dummy bc i dont actually care to make generator stuff for em right now
        generator.registerSingleton(MBBlocks.AZALEA_STEM, TexturedModel.PARTICLE);

        for (Item mbItem : MBItems.MB_EGGS) {
            generator.registerParentedItemModel(mbItem, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(MBItems.SWEET_BERRY_PITS.asItem(), Models.GENERATED);
        generator.register(MBItems.GLOW_BERRY_PITS.asItem(), Models.GENERATED);
        //generator.register(MBBlocks.BEDROLL.asItem(), Models.GENERATED);
        //generator.register(MBBlocks.PEBBLES.asItem(), Models.GENERATED);

        for (Item mbItem : MBItems.MB_ITEMS) {
            generator.register(mbItem, Models.GENERATED);
        }
        for (Item mbItem : MBItems.MB_TOOLS) {
            generator.register(mbItem, Models.HANDHELD);
        }
    }

    public static void generateFamily(BlockStateModelGenerator generator, MBBlockFamily family, MBModelProvider self) {
        if (Objects.equals(Registry.BLOCK.getId(family.getBaseBlock()).getNamespace(), Moonbits.MOD_ID) && !self.generatedBlocks.contains(family.getBaseBlock())) {
            generator.registerSimpleCubeAll(family.getBaseBlock());
        }
        self.generatedBlocks.add(family.getBaseBlock());

        family.getVariants().forEach((variant, block) -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) && !self.generatedBlocks.contains(block)) { // should hopefully only make the model/blockstates if its a new block..?
                self.generatedBlocks.add(block); // this is the worst thing i have ever done but lets see if it works :b
                if (variant == MBBlockFamily.Variant.PILLAR) {
                    generator.registerAxisRotated(block, CUBE_COLUMN);
                }
                else if (variant == MBBlockFamily.Variant.COLUMN) {
                    column(block, family.getVariant(MBBlockFamily.Variant.CARVED), generator);
                }
                else if (variant == MBBlockFamily.Variant.CUT) {
                    sideEnd(block, generator);
                }
                else if (variant == MBBlockFamily.Variant.BOOKSHELF) {
                    bookshelf(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.PLANTER_BOX) {
                    planterBox(block, generator);
                }
                else if (variant == MBBlockFamily.Variant.DOOR) {
                    generator.registerDoor(block);
                }
                else if (variant == MBBlockFamily.Variant.TRAPDOOR) {
                    generator.registerOrientableTrapdoor(block);
                }
                else if (variant == MBBlockFamily.Variant.BUTTON) {
                    button(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.PRESSURE_PLATE) {
                    pressurePlate(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.SIGN) {
                    sign(block, family.getVariant(MBBlockFamily.Variant.WALL_SIGN), family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.CARPET) {
                    carpet(family.getBaseBlock(), block, generator);
                }
                else if (variant == MBBlockFamily.Variant.FENCE) {
                    fence(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.FENCE_GATE) {
                    fenceGate(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.WALL) {
                    if (block == MBBlocks.SMOOTH_STONE_WALL) {
                        smoothStoneWall(block, family.getBaseBlock(), generator);
                    }
                    else {
                        wall(block, family.getBaseBlock(), generator);
                    }
                }
                else if (variant == MBBlockFamily.Variant.STAIRS) {
                    stairs(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.SLAB) {
                    slab(block, family.getBaseBlock(), generator);
                }
                // special cases :b
                else if (block == MBBlocks.CHISELED_TUFF) {
                    sideEnd(block, generator);
                }
                else if (block == MBBlocks.CHISELED_BASALT) {
                    generator.registerAxisRotated(block, TexturedModel.CUBE_COLUMN);
                }
                else if (variant != MBBlockFamily.Variant.WALL_SIGN){
                    generator.registerSimpleCubeAll(block);
                }
            }
        });
        family.cuttable.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) && !self.generatedBlocks.contains(block)) {
                self.generatedBlocks.add(block);
                generator.registerSimpleCubeAll(block);
            }
        });
    }
    // hardcoded ones
    public static void toughGrass (BlockStateModelGenerator generator) {
        Identifier identifier = Texture.getId(MBBlocks.TOUGH_DIRT);
        Identifier tough_grass = TINTED_GRASSLIKE.get(MBBlocks.TOUGH_GRASS).texture(texture ->  texture.put(TextureKey.BOTTOM, identifier)).upload(MBBlocks.TOUGH_GRASS, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(MBBlocks.TOUGH_GRASS, tough_grass));
    }
    public static void turf(BlockStateModelGenerator generator) {
        tintedBlock(MBBlocks.GRASS_TURF, generator);
        tintedSlab(MBBlocks.GRASS_TURF_SLAB, MBBlocks.GRASS_TURF, generator);
        tintedStairs(MBBlocks.GRASS_TURF_STAIRS, MBBlocks.GRASS_TURF, generator);
        tintedCarpet(MBBlocks.GRASS_TURF, MBBlocks.GRASS_CARPET, generator);
    }
    private static void giantToadstoolCap(BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(MBBlocks.GIANT_TOADSTOOL_CAP);
        TexturedModel texturedModel = CUBE_BOTTOM_TOP.get(MBBlocks.GIANT_TOADSTOOL_CAP);
        Identifier identifier2 = Models.SLAB.upload(MBBlocks.GIANT_TOADSTOOL_CAP, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(MBBlocks.GIANT_TOADSTOOL_CAP, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(MBBlocks.GIANT_TOADSTOOL_CAP, identifier2, identifier3, identifier));
    }
    public static void pebbles(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.PEBBLES.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.PEBBLES).coordinate(BlockStateVariantMap.create(PebbleBlock.PEBBLES)
                .register(1, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MOD_ID, "block/pebble"))))
                .register(2, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MOD_ID, "block/two_pebbles"))))
                .register(3, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MOD_ID, "block/three_pebbles"))))
                .register(4, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MOD_ID, "block/four_pebbles"))))
        ));
    }
    public static void blazeRod(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.BLAZE_ROD).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod")).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod")).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/blaze_rod"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void toadstool(BlockStateModelGenerator generator) {
        generator.registerParentedItemModel(MBBlocks.TOADSTOOL.asItem(), new Identifier(Moonbits.MOD_ID, "block/toadstool_cap"));
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.TOADSTOOL).coordinate(BlockStateVariantMap.create(ToadstoolBlock.CAP)
                .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_cap")))
                .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_stem")))
        ));
        generator.registerParentedItemModel(MBBlocks.TOADSTOOL_SHELF.asItem(), new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf"));
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.TOADSTOOL_SHELF).coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, Properties.ATTACHED)
                .register(Direction.NORTH, true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf")))
                .register(Direction.SOUTH, true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .register(Direction.EAST, true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .register(Direction.WEST, true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
                .register(Direction.NORTH, false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf_detached")))
                .register(Direction.SOUTH, false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf_detached")))
                .register(Direction.EAST, false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf_detached")))
                .register(Direction.WEST, false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/toadstool_shelf_detached")))
        ));
    }
    public static void lamproot(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.LAMPROOT.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.LAMPROOT).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot_horizontal")))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void gills(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.SAFFRON_GILLS);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.SAFFRON_GILLS).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills_wall")))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        ));
    }
    public static void bedroll(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.BEDROLL.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.BEDROLL).coordinate(BlockStateVariantMap.create(Properties.BED_PART, Properties.HORIZONTAL_FACING)
                .register(BedPart.HEAD, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_head")))
                .register(BedPart.HEAD, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(BedPart.HEAD, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(BedPart.HEAD, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(BedPart.FOOT, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_foot")))
                .register(BedPart.FOOT, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(BedPart.FOOT, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(BedPart.FOOT, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MOD_ID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void jacaranda(BlockStateModelGenerator generator) {
        generator.registerParentedItemModel(MBBlocks.JACARANDA_LEAVES, new Identifier(Moonbits.MOD_ID, "block/jacaranda_leaves"));
        generator.registerParentedItemModel(MBBlocks.JACARANDA_LEAF_CARPET, new Identifier(Moonbits.MOD_ID, "block/jacaranda_leaf_carpet"));
        generator.registerItemModel(MBBlocks.HANGING_JACARANDA_LEAVES);
        generator.registerItemModel(MBBlocks.HANGING_JACARANDA_LEAVES_PLANT);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.JACARANDA_LEAVES, new Identifier(Moonbits.MOD_ID, "block/jacaranda_leaves")));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.JACARANDA_LEAF_CARPET, new Identifier(Moonbits.MOD_ID, "block/jacaranda_leaf_carpet")));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.HANGING_JACARANDA_LEAVES, new Identifier(Moonbits.MOD_ID, "block/hanging_jacaranda_leaves")));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.HANGING_JACARANDA_LEAVES_PLANT, new Identifier(Moonbits.MOD_ID, "block/hanging_jacaranda_leaves_plant")));
    }
    public final void redBrownMushrooms(BlockStateModelGenerator generator) {
        Identifier identifier = Models.TEMPLATE_SINGLE_FACE.upload(MBBlocks.RED_MUSHROOM_CAP, Texture.texture(Blocks.RED_MUSHROOM_BLOCK), generator.modelCollector);
        Identifier identifier2 = ModelIds.getMinecraftNamespacedBlock("mushroom_block_inside");
        shroomStates(generator, MBBlocks.RED_MUSHROOM_CAP, identifier, identifier2);
        generator.registerParentedItemModel(MBBlocks.RED_MUSHROOM_CAP, TexturedModel.CUBE_ALL.upload(Blocks.RED_MUSHROOM_BLOCK, "_inventory", generator.modelCollector));

        Identifier identifier3 = Models.TEMPLATE_SINGLE_FACE.upload(MBBlocks.BROWN_MUSHROOM_CAP, Texture.texture(Blocks.BROWN_MUSHROOM_BLOCK), generator.modelCollector);
        Identifier identifier4 = ModelIds.getMinecraftNamespacedBlock("mushroom_block_inside");
        shroomStates(generator, MBBlocks.BROWN_MUSHROOM_CAP, identifier3, identifier4);
        generator.registerParentedItemModel(MBBlocks.BROWN_MUSHROOM_CAP, TexturedModel.CUBE_ALL.upload(Blocks.BROWN_MUSHROOM_BLOCK, "_inventory", generator.modelCollector));
    }
    public static void shroomStates(BlockStateModelGenerator generator, Block block, Identifier identifier, Identifier identifier2) {
        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.DOWN, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                .with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.WEST, false), BlockStateVariant.create()
                        .put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R270)
                        .put(VariantSettings.UVLOCK, false)).with(When.create().set(Properties.UP, false), BlockStateVariant.create()
                        .put(VariantSettings.MODEL, identifier2).put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, false))
                .with(When.create().set(Properties.DOWN, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, false)));
    }

    public static void wallPlant(Block block, BlockStateModelGenerator generator) {
        generator.registerItemModel(block);
        Identifier identifier = ModelIds.getBlockModelId(block);
        Texture texture = Texture.all(block);
        //Identifier identifier2 = WALL_PLANT.upload(block, texture, generator.modelCollector);

        MultipartBlockStateSupplier multipartBlockStateSupplier = MultipartBlockStateSupplier.create(block);
        When.PropertyCondition propertyCondition2 = Util.make(When.create(), propertyCondition -> BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS.forEach((property, function) -> {
            if (block.getDefaultState().contains(property)) {
                propertyCondition.set(property, false);
            }
        }));
        BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS.forEach((property, function) -> {
            if (block.getDefaultState().contains(property)) {
                multipartBlockStateSupplier.with(When.create().set(property, true), function.apply(identifier));
                multipartBlockStateSupplier.with(propertyCondition2, function.apply(identifier));
            }
        });
        generator.blockStateCollector.accept(multipartBlockStateSupplier);
    }

    public static void topSoil (Block block, Block bottom, BlockStateModelGenerator generator) {
        Identifier identifier = Texture.getId(bottom);
        Identifier identifier2 = TexturedModel.CUBE_BOTTOM_TOP.get(block).texture(texture -> {
            texture.put(TextureKey.BOTTOM, identifier);
            texture.put(TextureKey.SIDE, Texture.getId(block));
        }).upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(block, identifier2));
    }
    public static void seatBlock(Block block, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideTopBottom(block).put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.BOTTOM, Texture.getSubId(MBBlocks.LEATHER_SEAT, "_bottom"));
        Identifier identifier = Models.SLAB.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(block, texture, generator.modelCollector);
        Identifier identifier3 = TexturedModel.CUBE_BOTTOM_TOP.get(block).texture(tex -> tex.put(TextureKey.SIDE, Texture.getSubId(block, "_double")))
                .upload(block, "_double", generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(block, identifier, identifier2, identifier3));
    }

    public static void log(Block logBlock, Block woodBlock, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideAndEndForTop(logBlock);
        Identifier identifier = Models.CUBE_COLUMN.upload(logBlock, texture, generator.modelCollector);
        Identifier identifier2 = Models.CUBE_COLUMN_HORIZONTAL.upload(logBlock, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(logBlock, identifier, identifier2));

        Texture texture2 = texture.copyAndAdd(TextureKey.END, texture.getTexture(TextureKey.SIDE));
        Identifier identifier3 = Models.CUBE_COLUMN.upload(woodBlock, texture2, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(woodBlock, identifier3));
    }

    public static void flowerPotPlant(Block plantBlock, Block flowerPotBlock, TintType tintType, BlockStateModelGenerator generator) {
        flowerPotPlant(plantBlock, flowerPotBlock, tintType, generator, true);
    }
    public static void flowerPotPlant(Block plantBlock, Block flowerPotBlock, TintType tintType, BlockStateModelGenerator generator, Boolean genItem) {
        tintableCross(plantBlock, tintType, generator, genItem);
        Texture texture = Texture.plant(plantBlock);
        Identifier identifier = tintType.getFlowerPotCrossModel().upload(flowerPotBlock, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(flowerPotBlock, identifier));
    }
    public static void pottedBlock(Block root, Block pottedRoot, BlockStateModelGenerator generator) {
        Texture texture = Texture.plant(Texture.getSubId(root, "_pot"));
        Identifier identifier = TintType.NOT_TINTED.getFlowerPotCrossModel().upload(pottedRoot, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(pottedRoot, identifier));
    }
    public static void tintableCross(Block block, TintType tintType, BlockStateModelGenerator generator) {
        tintableCross(block, tintType, generator, true);
    }
    public static void tintableCross(Block block, TintType tintType, BlockStateModelGenerator generator, Boolean genItem) {
        if (genItem) {
            generator.registerItemModel(block);
        }
        Texture texture = Texture.cross(block);
        Identifier identifier = tintType.getCrossModel().upload(block, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }
    public static void cappedCross(Block block, TintType tintType, BlockStateModelGenerator generator, Boolean genItem) {
        if (genItem) {
            generator.registerItemModel(block);
        }
        TexturedModel texturedModel = CAPPED_CROSS_F.get(block);
        Identifier identifier = CAPPED_CROSS.upload(block, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }
    public static void doubleBlock(Block doubleBlock, TintType tintType, BlockStateModelGenerator generator) {
        generator.registerItemModel(doubleBlock, "_top");
        Identifier identifier = generator.createSubModel(doubleBlock, "_top", tintType.getCrossModel(), Texture::cross);
        Identifier identifier2 = generator.createSubModel(doubleBlock, "_bottom", tintType.getCrossModel(), Texture::cross);
        generator.registerDoubleBlock(doubleBlock, identifier, identifier2);
    }
    public static void registerPlantPart(Block plant, Block plantStem, TintType tintType, BlockStateModelGenerator generator) {
        tintableCross(plant, tintType, generator, false);
        tintableCross(plantStem, tintType, generator, false);
    }

    private static void column(Block column, Block end, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(column), Texture.getId(end));
        Identifier identifier = Models.CUBE_COLUMN.upload(column, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(column, identifier));
    }

    private static void sideEnd(Block block, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(block), Texture.getSubId(block, "_top"));
        Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private static void bookshelf(Block bookshelf, Block base, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(bookshelf), Texture.getId(base));
        Identifier identifier = Models.CUBE_COLUMN.upload(bookshelf, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(bookshelf, identifier));
    }

    private static void slab(Block slab, Block base, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(base);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier2 = Models.SLAB.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier2, identifier3, identifier));
    }

    private static void stairs(Block stairs, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.INNER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.OUTER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, identifier, identifier2, identifier3));
    }

    private static void tintedBlock(Block block, BlockStateModelGenerator generator) {
        Identifier id = TINTED_BLOCK.upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, id));
    }
    private static void tintedSlab(Block slab, Block base, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(base);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier2 = TINTED_SLAB.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = TINTED_SLAB_TOP.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier2, identifier3, identifier));
    }
    private static void tintedStairs(Block stairs, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = TINTED_INNER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = TINTED_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = TINTED_OUTER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, identifier, identifier2, identifier3));
    }
    public static void tintedCarpet(Block base, Block carpet, BlockStateModelGenerator generator) {
        Identifier identifier = TINTED_CARPET_F.get(base).upload(carpet, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(carpet, identifier));
    }
    public final void wallLantern(Block lantern, Block base, BlockStateModelGenerator generator) {
        //Texture texture = Texture.lantern(base);
        Identifier identifier = WALL_LANTERN.upload(lantern, Texture.lantern(base), generator.modelCollector);
        //Identifier identifier2 = WALL_LANTERN_F.upload(base, generator.modelCollector);
        //generator.registerItemModel(lantern.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(lantern).coordinate(BlockStateVariantMap.create(HorizontalFacingBlock.FACING)
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        ));
    }

    private static void fence(Block fenceBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(fenceBlock, identifier3);
    }
    private static void fenceGate(Block fenceGateBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4));
    }
    public static void wall(Block wallBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_WALL_POST.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, identifier, identifier2, identifier3));
        Identifier identifier4 = Models.WALL_INVENTORY.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(wallBlock, identifier4);
    }
    public static void smoothStoneWall(Block wallBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = new Identifier(Moonbits.MOD_ID, "block/smooth_stone_wall_post");
        Identifier identifier2 = new Identifier(Moonbits.MOD_ID, "block/smooth_stone_wall_side");
        Identifier identifierB = new Identifier(Moonbits.MOD_ID, "block/smooth_stone_wall_side_b");
        Identifier identifier3 = new Identifier(Moonbits.MOD_ID, "block/smooth_stone_wall_side_tall");
        generator.blockStateCollector.accept(smoothStoneWallBlockState(wallBlock, identifier, identifier2, identifierB, identifier3));
        Identifier identifier4 = new Identifier(Moonbits.MOD_ID, "block/smooth_stone_wall_inventory");
        generator.registerParentedItemModel(wallBlock, identifier4);
    }
    public static BlockStateSupplier smoothStoneWallBlockState(Block wallBlock, Identifier postModelId, Identifier lowSideModelId, Identifier lowSideModelIdB, Identifier tallSideModelId) {
        return MultipartBlockStateSupplier.create(wallBlock)
                .with(When.create().set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, postModelId))
                .with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.LOW), BlockStateVariant.create().put(VariantSettings.MODEL, lowSideModelIdB)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.LOW), BlockStateVariant.create().put(VariantSettings.MODEL, lowSideModelId)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.LOW), BlockStateVariant.create().put(VariantSettings.MODEL, lowSideModelIdB)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.LOW), BlockStateVariant.create().put(VariantSettings.MODEL, lowSideModelId)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.NORTH_WALL_SHAPE, WallShape.TALL), BlockStateVariant.create().put(VariantSettings.MODEL, tallSideModelId)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.EAST_WALL_SHAPE, WallShape.TALL), BlockStateVariant.create().put(VariantSettings.MODEL, tallSideModelId)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH_WALL_SHAPE, WallShape.TALL), BlockStateVariant.create().put(VariantSettings.MODEL, tallSideModelId)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST_WALL_SHAPE, WallShape.TALL), BlockStateVariant.create().put(VariantSettings.MODEL, tallSideModelId)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true));
    }
    public static void sign(Block signBlock, Block wallSign, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.PARTICLE.upload(signBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, identifier));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSign, identifier));
        generator.registerItemModel(signBlock.asItem());
        generator.excludeFromSimpleItemModelGeneration(wallSign);
    }
    public static void button(Block buttonBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.BUTTON.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.BUTTON_PRESSED.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, identifier, identifier2));
        Identifier identifier3 = Models.BUTTON_INVENTORY.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(buttonBlock, identifier3);
    }
    public static void pressurePlate(Block pressurePlateBlock, Block base, BlockStateModelGenerator generator) {
        Texture texture = Texture.all(base);
        Identifier identifier = Models.PRESSURE_PLATE_UP.upload(pressurePlateBlock, texture, generator.modelCollector);
        Identifier identifier2 = Models.PRESSURE_PLATE_DOWN.upload(pressurePlateBlock, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(pressurePlateBlock, identifier, identifier2));
    }
    public static void carpet(Block base, Block carpet, BlockStateModelGenerator generator) {
        Identifier identifier = TexturedModel.CARPET.get(base).upload(carpet, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(carpet, identifier));
    }
    public static void leafCarpet(Block base, Block carpet, BlockStateModelGenerator generator) {
        Identifier identifier = LEAF_CARPET_F.get(base).upload(carpet, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(carpet, identifier));
    }
    public static void planterBox(Block planterBox, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = PLANTER_BOX_F.get(planterBox);
        Identifier identifier = PLANTER_BOX.upload(planterBox, texturedModel.getTexture(), generator.modelCollector);

        TexturedModel texturedModel2 = PLANTER_BOX_INNER_F.get(planterBox);
        Texture tex2 = new Texture().put(INNER, Texture.getSubId(planterBox, "_inner")).put(TextureKey.SIDE, Texture.getId(planterBox)).put(TextureKey.TOP, Texture.getSubId(planterBox, "_top"))
                .put(TextureKey.BOTTOM, Texture.getSubId(planterBox, "_bottom"));
        Identifier identifier2 = PLANTER_BOX_INNER.upload(planterBox, "_inner", texturedModel2.getTexture(), generator.modelCollector);

        Texture tex3 = new Texture().put(TextureKey.SIDE, Texture.getSubId(planterBox, "_edge")).put(TextureKey.TOP, Texture.getSubId(planterBox, "_top"))
                .put(TextureKey.BOTTOM, Texture.getSubId(planterBox, "_bottom"));
        Identifier identifier3 = PLANTER_BOX_OUTER.upload(planterBox, "_outer", tex3, generator.modelCollector);

        Texture tex4 = new Texture().put(TextureKey.SIDE, Texture.getId(planterBox)).put(TextureKey.TOP, Texture.getSubId(planterBox, "_side"))
                .put(TextureKey.BOTTOM, Texture.getSubId(planterBox, "_bottom"));
        Identifier identifier4 = PLANTER_BOX_SIDE.upload(planterBox, "_side", tex4, generator.modelCollector);

        Texture tex5 = new Texture().put(TextureKey.SIDE, Texture.getId(planterBox)).put(TextureKey.TOP, Texture.getSubId(planterBox, "_side_b"))
                .put(TextureKey.BOTTOM, Texture.getSubId(planterBox, "_bottom"));
        Identifier identifier5 = PLANTER_BOX_SIDE_B.upload(planterBox, "_side_b", tex5, generator.modelCollector);

        generator.blockStateCollector.accept(createPlanterBoxState(planterBox, identifier, identifier2, identifier3, identifier4, identifier5));

        TexturedModel texturedModel6 = PLANTER_BOX_INVENTORY_F.get(planterBox);
        Identifier identifier6 = PLANTER_BOX_INVENTORY.upload(planterBox, "_inventory", texturedModel6.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(planterBox, identifier6);
    }
    public static BlockStateSupplier createPlanterBoxState(Block block, Identifier regular, Identifier inner, Identifier outer, Identifier side, Identifier sideB) {
        return MultipartBlockStateSupplier.create(block)
                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true))

                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))

                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))

                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                ;
    }

    private static Model make(TextureKey ... requiredTextures) {
        return new Model(Optional.empty(), Optional.empty(), requiredTextures);
    }

    private static Model block(String parent, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "block/" + parent)), Optional.empty(), requiredTextures);
    }
    private static Model blockFromVanilla(String parent, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("minecraft", "block/" + parent)), Optional.empty(), requiredTextures);
    }

    private static Model item(String parent, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "item/" + parent)), Optional.empty(), requiredTextures);
    }

    private static Model block(String parent, String variant, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "block/" + parent)), Optional.of(variant), requiredTextures);
    }

    public static Texture leaf(Block block) {
        return new Texture().put(LEAF, Texture.getId(block));
    }
    public static Texture capCross(Block block) {
        return new Texture().put(TextureKey.CROSS, Texture.getSubId(block, "_stem")).put(CAP, Texture.getId(block));
    }
    public static Texture grasslike(Block block) {
        return new Texture().put(TextureKey.BOTTOM, Texture.getId(Blocks.DIRT)).put(TextureKey.TOP, Texture.getSubId(block, "_top"))
                .put(TextureKey.SIDE, Texture.getId(block)).put(OVERLAY, Texture.getSubId(block, "_overlay"));
    }
    public static Texture sideEnd(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.END, Texture.getSubId(block, "_top"));
    }
    public static Texture sideBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.END, Texture.getSubId(block, "_bottom"));
    }
    public static Texture sideTopBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
    public static Texture sideTopBottom(Block block, String topSuffix) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.TOP, Texture.getSubId(block, topSuffix)).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
    public static Texture edgeTopBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getSubId(block, "_edge")).put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
    public static Texture pbInner(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(INNER, Texture.getSubId(block, "_inner"))
                .put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }

    enum TintType {
        TINTED,
        NOT_TINTED;


        public Model getCrossModel() {
            return this == TINTED ? Models.TINTED_CROSS : Models.CROSS;
        }

        public Model getFlowerPotCrossModel() {
            return this == TINTED ? Models.TINTED_FLOWER_POT_CROSS : Models.FLOWER_POT_CROSS;
        }
    }
}
