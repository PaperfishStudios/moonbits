package net.paperfish.moonbits.data;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.WallShape;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateSupplier;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.MultipartBlockStateSupplier;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.data.client.When;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.item.Item;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.paperfish.moonbits.registry.*;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;
import net.paperfish.moonbits.block.cauldron.HoneyCauldronBlock;
import net.paperfish.moonbits.mixin.TextureKeyAccessor;
import net.paperfish.moonbits.mixin.TexturedModelAccessor;

import java.util.*;
import java.util.function.Function;

public class MBModelProvider extends FabricModelProvider {

    public static final Model WALL_PLANT = blockFromVanilla("glow_lichen", TextureKey.ALL);

    public static final Model PALISADE_POST = block("palisade/palisade_post", "_post", TextureKey.SIDE, TextureKey.END);
    public static final Model PALISADE_SIDE = block("palisade/palisade_side", "_side", TextureKey.SIDE, TextureKey.END);
    public static final Model PALISADE_INVENTORY = block("palisade/palisade_inventory", "_inventory", TextureKey.SIDE, TextureKey.END);

    public static final Model BARS_CAP = block("bars/bars_cap", "_cap", TextureKey.SIDE, TextureKey.END);
    public static final Model BARS_CAP_ALT = block("bars/bars_cap_alt", "_cap_alt", TextureKey.SIDE, TextureKey.END);
    public static final Model BARS_POST = block("bars/bars_post", "_post", TextureKey.SIDE, TextureKey.END);
    public static final Model BARS_POST_ENDS = block("bars/bars_post_ends", "_post_ends", TextureKey.SIDE, TextureKey.END);
    public static final Model BARS_SIDE = block("bars/bars_side", "_side", TextureKey.SIDE, TextureKey.END);
    public static final Model BARS_SIDE_ALT = block("bars/bars_side_alt", "_side_alt", TextureKey.SIDE, TextureKey.END);

    public static final Model T_HONEY_CAULDRON_1 = block("cauldron/t_honey_cauldron_level1",
            TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.PARTICLE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final Model T_HONEY_CAULDRON_2 = block("cauldron/t_honey_cauldron_level2",
            TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.PARTICLE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final Model T_HONEY_CAULDRON_3 = block("cauldron/t_honey_cauldron_level3",
            TextureKey.CONTENT, TextureKey.INSIDE, TextureKey.PARTICLE, TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);

    public static final TextureKey POST = TextureKeyAccessor.createTextureKey("post", TextureKey.SIDE);
    public static final Model TRIM = block("template_trim", TextureKey.TOP, POST);
    public static final Model TRIM_HANGING = block("template_trim_hanging", TextureKey.TOP, POST);

    public static final TexturedModel.Factory CUBE_BOTTOM_TOP = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, Models.CUBE_BOTTOM_TOP);
    public static final TexturedModel.Factory CUBE_COLUMN = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideEnd, Models.CUBE_COLUMN);
    public static final TextureKey OVERLAY = TextureKeyAccessor.createTextureKey("overlay", TextureKey.SIDE);
    public static final Model GRASS_BLOCK = blockFromVanilla("grass_block", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE, OVERLAY);
    public static final TexturedModel.Factory TINTED_GRASSLIKE = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, GRASS_BLOCK);

    public static final Model WALL_LANTERN = block("template_wall_lantern", TextureKey.LANTERN);
    public static final TexturedModel.Factory WALL_LANTERN_F = TexturedModelAccessor.callMakeFactory(TextureMap::lantern, WALL_LANTERN);

    public static final Model TINTED_CUBE = block("tinted/tinted_cube", TextureKey.ALL);
    public static final TexturedModel.Factory TINTED_BLOCK = TexturedModelAccessor.callMakeFactory(TextureMap::all, TINTED_CUBE);
    public static final Model TINTED_SLAB = block("tinted/tint_slab", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_SLAB_TOP = block("tinted/tint_slab_top", "_top", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final TexturedModel.Factory TINTED_SLAB_F = TexturedModelAccessor.callMakeFactory(TextureMap::all, TINTED_SLAB);
    public static final TexturedModel.Factory TINTED_SLAB_TOP_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_SLAB_TOP);
    public static final Model TINTED_STAIRS = block("tinted/tint_stairs", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_INNER_STAIRS = block("tinted/tint_stairs_inner", "_inner", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final Model TINTED_OUTER_STAIRS = block("tinted/tint_stairs_outer", "_outer", TextureKey.BOTTOM, TextureKey.TOP, TextureKey.SIDE);
    public static final TexturedModel.Factory TINTED_STAIRS_F = TexturedModelAccessor.callMakeFactory(TextureMap::all, TINTED_STAIRS);
    public static final TexturedModel.Factory TINTED_STAIRS_INNER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_INNER_STAIRS);
    public static final TexturedModel.Factory TINTED_STAIRS_OUTER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::grasslike, TINTED_OUTER_STAIRS);
    public static final Model TINTED_CARPET = block("tinted/tint_carpet", TextureKey.WOOL);
    public static final TexturedModel.Factory TINTED_CARPET_F = TexturedModelAccessor.callMakeFactory(TextureMap::wool, TINTED_CARPET);

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
        generator.registerParentedItemModel(MBBlocks.KILN, new Identifier(Moonbits.MODID, "block/kiln"));

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

        flowerPotPlant(MBBlocks.GOLDEN_BIRCH_SAPLING, MBBlocks.POTTED_GOLDEN_BIRCH_SAPLING, TintType.NOT_TINTED, generator);
        generator.registerSingleton(MBBlocks.GOLDEN_BIRCH_LEAVES, TexturedModel.LEAVES);
        leafCarpet(MBBlocks.GOLDEN_BIRCH_LEAVES, MBBlocks.GOLDEN_BIRCH_LEAF_CARPET, generator);

        flowerPotPlant(MBBlocks.RED_OAK_SAPLING, MBBlocks.POTTED_RED_OAK_SAPLING, TintType.NOT_TINTED, generator);
        generator.registerSingleton(MBBlocks.RED_OAK_LEAVES, TexturedModel.LEAVES);
        leafCarpet(MBBlocks.RED_OAK_LEAVES, MBBlocks.RED_OAK_LEAF_CARPET, generator);

        log(MBBlocks.LAMPROOT_LOG, MBBlocks.LAMPROOT_WOOD, generator);
        log(MBBlocks.STRIPPED_LAMPROOT_LOG, MBBlocks.STRIPPED_LAMPROOT_WOOD, generator);
        flowerPotPlant(MBBlocks.LAMPROOT_SAPLING, MBBlocks.POTTED_LAMPROOT_SAPLING, TintType.NOT_TINTED, generator);
//        generator.registerSingleton(MBBlocks.JUNIPER_LEAVES, TexturedModel.LEAVES);

        log(MBBlocks.CEDAR_LOG, MBBlocks.CEDAR_WOOD, generator);
        log(MBBlocks.STRIPPED_CEDAR_LOG, MBBlocks.STRIPPED_CEDAR_WOOD, generator);
        flowerPotPlant(MBBlocks.CEDAR_SAPLING, MBBlocks.POTTED_CEDAR_SAPLING, TintType.NOT_TINTED, generator);
        generator.registerSingleton(MBBlocks.CEDAR_LEAVES, TexturedModel.LEAVES);

        barrelCactus(generator);

        floweringAcacia(generator);

        // foragin :3
        tintableCross(MBBlocks.WILD_POTATOES, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.WILD_CARROTS, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.SEA_BEETS, TintType.NOT_TINTED, generator);

        omniCross(MBBlocks.MYCELIUM_ROOTS, generator, true);

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.HONEY_CAULDRON)
                .coordinate(BlockStateVariantMap.create(HoneyCauldronBlock.LEVEL)
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_1
                                .upload(MBBlocks.HONEY_CAULDRON, "_level1", TextureMap.cauldron(TextureMap.getSubId(Blocks.HONEY_BLOCK, "_top")), generator.modelCollector)))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_2
                                .upload(MBBlocks.HONEY_CAULDRON, "_level2", TextureMap.cauldron(TextureMap.getSubId(Blocks.HONEY_BLOCK, "_top")), generator.modelCollector)))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_3
                                .upload(MBBlocks.HONEY_CAULDRON, "_level3", TextureMap.cauldron(TextureMap.getSubId(Blocks.HONEY_BLOCK, "_top")), generator.modelCollector)))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_FULL
                                .upload(MBBlocks.HONEY_CAULDRON, "_full", TextureMap.cauldron(TextureMap.getSubId(Blocks.HONEY_BLOCK, "_top")), generator.modelCollector)))));
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.SYRUP_CAULDRON)
                .coordinate(BlockStateVariantMap.create(HoneyCauldronBlock.LEVEL)
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_1
                                .upload(MBBlocks.SYRUP_CAULDRON, "_level1", TextureMap.cauldron(TextureMap.getId(MBBlocks.SYRUP_BLOCK)), generator.modelCollector)))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_2
                                .upload(MBBlocks.SYRUP_CAULDRON, "_level2", TextureMap.cauldron(TextureMap.getId(MBBlocks.SYRUP_BLOCK)), generator.modelCollector)))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, T_HONEY_CAULDRON_3
                                .upload(MBBlocks.SYRUP_CAULDRON, "_level3", TextureMap.cauldron(TextureMap.getId(MBBlocks.SYRUP_BLOCK)), generator.modelCollector)))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, Models.TEMPLATE_CAULDRON_FULL
                                .upload(MBBlocks.SYRUP_CAULDRON, "_full", TextureMap.cauldron(TextureMap.getId(MBBlocks.SYRUP_BLOCK)), generator.modelCollector)))));

        generator.registerItemModel(MBBlocks.TREE_TAP.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.TREE_TAP,
                BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/tree_tap")))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));

//        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.COOKING_POT, new Identifier(Moonbits.MODID, "block/cooking_pot")));
////        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.COOKING_POT,
////                        BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/cooking_pot")))
////                .coordinate(BlockStateModelGenerator.createAxisRotatedVariantMap()));
//        generator.registerItemModel(MBBlocks.COOKING_POT.asItem());

        generator.registerSimpleCubeAll(MBBlocks.SYRUP_BLOCK);


        // ground
        topSoil(MBBlocks.LEAFBED, Blocks.DIRT, generator);
        topSoil(MBBlocks.SUBSTRATE, MBBlocks.TOUGH_DIRT, generator);
        toughGrass(generator);
        turf(generator);

        generator.registerSimpleCubeAll(MBBlocks.REGOLITH);
        generator.registerSimpleCubeAll(MBBlocks.PEAT_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.PEAT_MOSS);
        generator.registerSimpleCubeAll(MBBlocks.PEAT_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.CLAY_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.GOLD_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.COPPER_DEPOSIT);

        generator.registerSimpleCubeAll(MBBlocks.TIN_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.FROST_TIN_DEPOSIT);
        generator.registerSimpleCubeAll(MBBlocks.TIN_ORE);
        generator.registerSimpleCubeAll(MBBlocks.DEEPSLATE_TIN_ORE);
        generator.registerSimpleCubeAll(MBBlocks.CHERT_TIN_ORE);
        generator.registerSimpleCubeAll(MBBlocks.RAW_TIN_BLOCK);

        generator.registerSimpleCubeAll(MBBlocks.TIN_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.OXIDIZED_TIN_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.BLACKENED_TIN_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.PESTERED_TIN_BLOCK);
        generator.registerParented(MBBlocks.TIN_BLOCK, MBBlocks.WAXED_TIN_BLOCK);
        generator.registerParented(MBBlocks.OXIDIZED_TIN_BLOCK, MBBlocks.WAXED_OXIDIZED_TIN_BLOCK);
        generator.registerParented(MBBlocks.BLACKENED_TIN_BLOCK, MBBlocks.WAXED_BLACKENED_TIN_BLOCK);
        generator.registerParented(MBBlocks.PESTERED_TIN_BLOCK, MBBlocks.WAXED_PESTERED_TIN_BLOCK);

        generator.registerParented(MBBlocks.CUT_TIN, MBBlocks.WAXED_CUT_TIN);
        generator.registerParented(MBBlocks.OXIDIZED_CUT_TIN, MBBlocks.WAXED_OXIDIZED_CUT_TIN);
        generator.registerParented(MBBlocks.BLACKENED_CUT_TIN, MBBlocks.WAXED_BLACKENED_CUT_TIN);
        generator.registerParented(MBBlocks.PESTERED_CUT_TIN, MBBlocks.WAXED_PESTERED_CUT_TIN);

        stairs(MBBlocks.WAXED_CUT_TIN_STAIRS, MBBlocks.CUT_TIN, generator);
        stairs(MBBlocks.WAXED_OXIDIZED_CUT_TIN_STAIRS, MBBlocks.OXIDIZED_CUT_TIN, generator);
        stairs(MBBlocks.WAXED_BLACKENED_CUT_TIN_STAIRS, MBBlocks.BLACKENED_CUT_TIN, generator);
        stairs(MBBlocks.WAXED_PESTERED_CUT_TIN_STAIRS, MBBlocks.PESTERED_CUT_TIN, generator);

        slab(MBBlocks.WAXED_CUT_TIN_SLAB, MBBlocks.CUT_TIN, generator);
        slab(MBBlocks.WAXED_OXIDIZED_CUT_TIN_SLAB, MBBlocks.OXIDIZED_CUT_TIN, generator);
        slab(MBBlocks.WAXED_BLACKENED_CUT_TIN_SLAB, MBBlocks.BLACKENED_CUT_TIN, generator);
        slab(MBBlocks.WAXED_PESTERED_CUT_TIN_SLAB, MBBlocks.PESTERED_CUT_TIN, generator);

        generator.registerAxisRotated(MBBlocks.TIN_PILLAR, CUBE_COLUMN);

        generator.registerDoor(MBBlocks.TIN_DOOR);
        generator.registerOrientableTrapdoor(MBBlocks.TIN_TRAPDOOR);

        snowyBlock(MBBlocks.PERMAFROST, generator);
        generator.registerSimpleCubeAll(MBBlocks.FROST_PEAT);
        generator.registerSimpleCubeAll(MBBlocks.FROST_CLAY);
        generator.registerSimpleCubeAll(MBBlocks.FROST_GOLD);
        generator.registerSimpleCubeAll(MBBlocks.FROST_COPPER);

        generator.registerSimpleCubeAll(MBBlocks.SNOW_BRICKS);
        generator.registerSimpleCubeAll(MBBlocks.ICE_BRICKS);
        generator.registerSimpleCubeAll(MBBlocks.PACKED_ICE_BRICKS);

        pebbles(generator);

        prickly_pear(generator);

        generator.registerSimpleCubeAll(MBBlocks.CHERT_COAL_ORE);
        generator.registerSimpleCubeAll(MBBlocks.CHERT_GOLD_ORE);
        generator.registerSimpleCubeAll(MBBlocks.CHERT_COPPER_ORE);
        generator.registerSimpleCubeAll(MBBlocks.CHERT_REDSTONE_ORE);
        generator.registerSimpleCubeAll(MBBlocks.CHERT_LAPIS_ORE);

        redstoneCluster(MBBlocks.REDSTONE_CLUSTER, generator);
        redstoneCluster(MBBlocks.LARGE_REDSTONE_BUD, generator);
        redstoneCluster(MBBlocks.MEDIUM_REDSTONE_BUD, generator);
        redstoneCluster(MBBlocks.SMALL_REDSTONE_BUD, generator);

        cubeTopBottomSpec(MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.SANDSTONE_BRICKS, generator);
        cubeTopBottomSpec(MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_SANDSTONE_BRICKS, generator);
        cubeTopBottomSpec(MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_BRICKS, generator);
        cubeTopBottomSpec(MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_RED_SANDSTONE_BRICKS, generator);
        generator.registerSimpleCubeAll(MBBlocks.BANDED_IRON);
        generator.registerSimpleCubeAll(MBBlocks.HEMATITE_ORE);
        generator.registerSimpleCubeAll(MBBlocks.HEMATITE_BLOCK);

        generator.registerCrop(MBBlocks.PEANUT_CROP, Properties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);
        generator.registerCrop(MBBlocks.PEPPER_CROP, Properties.AGE_7, 0, 0, 1, 1, 2, 2, 2, 3);

        generator.registerAxisRotated(MBBlocks.CRACKED_MUD, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.RICH_MUD);
        generator.registerSimpleCubeAll(MBBlocks.MUD_GOLD_DEPOSIT);

        tintableCross(MBBlocks.BEACHGRASS, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.TALL_BEACHGRASS, TintType.NOT_TINTED, generator);

        tintableCross(MBBlocks.COTTONGRASS, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.TALL_COTTONGRASS, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.WHITE_HEATHER, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.RED_HEATHER, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.ORANGE_HEATHER, TintType.NOT_TINTED, generator);
        tintableCross(MBBlocks.PURPLE_HEATHER, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.LUPINE, TintType.NOT_TINTED, generator);
        frosthorn(generator);

        tintableCross(MBBlocks.DESERT_BRUSH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.TALL_DESERT_BRUSH, TintType.NOT_TINTED, generator);
        flowerPotPlant(MBBlocks.MARIGOLD, MBBlocks.POTTED_MARIGOLD, TintType.NOT_TINTED, generator);

        generator.registerRod(MBBlocks.PARASOL_FERN_STEM);
        generator.registerSingleton(MBBlocks.PARASOL_FERN_CROWN, CUBE_BOTTOM_TOP);
//        generator.registerSimpleCubeAll(MBBlocks.PARASOL_FERN_FIBER);
        generator.registerSimpleCubeAll(MBBlocks.HARDY_LEAVES);
        generator.registerSimpleCubeAll(MBBlocks.FLOWERING_HARDY_LEAVES);
        generator.registerSimpleCubeAll(MBBlocks.FRUITING_HARDY_LEAVES);

        generator.registerSimpleCubeAll(MBBlocks.CANVAS);
        generator.registerSimpleCubeAll(MBBlocks.FRAMED_CANVAS);

        // flowers n fungi :D
        flowerPotPlant(MBBlocks.BUTTERCUP, MBBlocks.POTTED_BUTTERCUP, TintType.NOT_TINTED, generator);
        flowerPotPlant(MBBlocks.FORGETMENOT, MBBlocks.POTTED_FORGETMENOT, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.WHITE_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.PINK_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.LIGHT_BLUE_HYACINTH, TintType.NOT_TINTED, generator);
        doubleBlock(MBBlocks.RED_HYACINTH, TintType.NOT_TINTED, generator);

        cappedCross(MBBlocks.WILDFLOWERS, generator);
        cappedCross(MBBlocks.CLOVER, generator);
        pottedBlock(MBBlocks.WILDFLOWERS, MBBlocks.POTTED_WILDFLOWERS, generator);
        pottedBlock(MBBlocks.CLOVER, MBBlocks.POTTED_CLOVER, generator);

        cappedCross(MBBlocks.PUFFBALLS, TintType.NOT_TINTED, generator, true);
        pottedBlock(MBBlocks.PUFFBALLS, MBBlocks.POTTED_PUFFBALLS, generator);
        flowerPotPlant(MBBlocks.SAFFRON_MUSHROOM, MBBlocks.POTTED_SAFFRON_MUSHROOM, TintType.NOT_TINTED, generator);
        flowerPotPlant(MBBlocks.SMALL_TOADSTOOLS, MBBlocks.POTTED_SMALL_TOADSTOOLS, TintType.NOT_TINTED, generator);
        toadstool(generator);

        generator.registerCoralFan(MBBlocks.OYSTER_MUSHROOMS, MBBlocks.SHELF_OYSTER_MUSHROOMS);
        pottedBlock(MBBlocks.OYSTER_MUSHROOMS, MBBlocks.POTTED_OYSTER_MUSHROOMS, generator);

        redBrownMushrooms(generator);
        generator.registerMushroomBlock(MBBlocks.SAFFRON_MUSHROOM_CAP);
        gills(generator);
        giantToadstoolCap(generator);
        generator.registerAxisRotated(MBBlocks.GIANT_TOADSTOOL_STEM, CUBE_COLUMN);
        log(MBBlocks.MUSHROOM_STEM, MBBlocks.MUSHROOM_HYPHAE, generator);
        log(MBBlocks.STRIPPED_MUSHROOM_STEM, MBBlocks.STRIPPED_MUSHROOM_HYPHAE, generator);

        generator.registerSimpleCubeAll(MBBlocks.RED_MUSHCLAY);
        generator.registerSimpleCubeAll(MBBlocks.BROWN_MUSHCLAY);
        generator.registerSimpleCubeAll(MBBlocks.TOADSTOOL_MUSHCLAY);
        generator.registerSimpleCubeAll(MBBlocks.SAFFRON_MUSHCLAY);
        generator.registerSimpleCubeAll(MBBlocks.RED_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.BROWN_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.TOADSTOOL_MUSH_LAMP);
        generator.registerSimpleCubeAll(MBBlocks.SAFFRON_MUSH_LAMP);

        lamproot(generator);
        wallPlant(MBBlocks.CAVEBLOOM_FLOWERS, generator);
        wallPlant(MBBlocks.CAVEBLOOM_VINE, generator);

        // mob-related blocks n stuff
        bedroll(generator);
        generator.registerWoolAndCarpet(MBBlocks.FUR_BLOCK, MBBlocks.FUR_CARPET);

        // storage blocks
        generator.registerSingleton(MBBlocks.APPLE_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.CARROT_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.POTATO_CRATE, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.BEETROOT_CRATE, CUBE_BOTTOM_TOP);

        generator.registerSingleton(MBBlocks.PEPPER_CRATE, CUBE_BOTTOM_TOP);

        generator.registerSingleton(MBBlocks.EGG_BASKET, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.COCOA_SACK, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.GLISTERING_MELON_BLOCK, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.SWEET_BERRY_BASKET, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.GLOW_BERRY_BASKET, CUBE_BOTTOM_TOP);
        generator.registerSingleton(MBBlocks.HARDY_BERRY_BASKET, CUBE_BOTTOM_TOP);

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

        generator.registerSingleton(MBBlocks.NETHER_WART_SACK, CUBE_BOTTOM_TOP);
        generator.registerAxisRotated(MBBlocks.SPOOL, CUBE_COLUMN);

//        generator.registerAxisRotated(MBBlocks.PAPER_BUNDLE, CUBE_COLUMN);

        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.PAPER_BUNDLE)
                .coordinate(BlockStateVariantMap.create(PapersBlock.POWERED)
                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, Models.CUBE_COLUMN
                                .upload(MBBlocks.PAPER_BUNDLE,
                                        TextureMap.sideEnd(MBBlocks.PAPER_BUNDLE),
                                        generator.modelCollector)))
                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, Models.SLAB
                                .upload(MBBlocks.PAPER_BUNDLE, "_compressed",
                                        TextureMap.sideTopBottom(MBBlocks.PAPER_BUNDLE).put(TextureKey.BOTTOM, TextureMap.getSubId(MBBlocks.PAPER_BUNDLE, "_top")),
                                        generator.modelCollector)))
                ));

        generator.registerAxisRotated(MBBlocks.STICK_STACK, CUBE_COLUMN);
        generator.registerAxisRotated(MBBlocks.CHARCOAL_LOG, CUBE_COLUMN);

        generator.registerSimpleCubeAll(MBBlocks.SUGAR_CUBE);
        generator.registerSimpleCubeAll(MBBlocks.PACKED_GLOWSTONE);
        generator.registerSingleton(MBBlocks.GUNPOWDER_CRATE, CUBE_BOTTOM_TOP);
        generator.registerAxisRotated(MBBlocks.CHORUS_BUNDLE, CUBE_COLUMN);

        generator.registerSimpleCubeAll(MBBlocks.SCUTE_BLOCK);

        generator.registerSimpleCubeAll(MBBlocks.ROTTEN_FLESH_BLOCK);
        generator.registerAxisRotated(MBBlocks.BONE_BUNDLE, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.SPIDER_EYE_BLOCK);
        generator.registerSimpleCubeAll(MBBlocks.PHANTOM_MEMBRANE_BLOCK);

        generator.registerAxisRotated(MBBlocks.BLAZE_ROD_BUNDLE, CUBE_COLUMN);
        generator.registerSimpleCubeAll(MBBlocks.ENDER_PEARL_BLOCK);

        blazeRod(generator);
        wallLantern(MBBlocks.WALL_LANTERN, Blocks.LANTERN, generator);
        wallLantern(MBBlocks.WALL_SOUL_LANTERN, Blocks.SOUL_LANTERN, generator);


        for (Item mbItem : MBItems.MB_EGGS) {
            generator.registerParentedItemModel(mbItem, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(MBItems.SWEET_BERRY_PITS.asItem(), Models.GENERATED);
        generator.register(MBItems.GLOW_BERRY_PITS.asItem(), Models.GENERATED);

        for (Item mbItem : MBItems.MB_ITEMS) {
            generator.register(mbItem, Models.GENERATED);
        }
        for (Item mbItem : MBItems.MB_TOOLS) {
            generator.register(mbItem, Models.HANDHELD);
        }
    }

    public static void generateFamily(BlockStateModelGenerator generator, MBBlockFamily family, MBModelProvider self) {
        Block base = family.getBaseBlock();
        if (Objects.equals(Registry.BLOCK.getId(base).getNamespace(), Moonbits.MODID) && !self.generatedBlocks.contains(base)) {
            if (base == MBBlocks.TILL) {
                snowyBlock(base, generator);
            }
            else {
                generator.registerSimpleCubeAll(base);
            }
        }
        self.generatedBlocks.add(family.getBaseBlock());

        family.getVariants().forEach((variant, block) -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID) && !self.generatedBlocks.contains(block)) { // should hopefully only make the model/blockstates if its a new block..?
                self.generatedBlocks.add(block); // this is the worst thing i have ever done but lets see if it works :b
                if (variant == MBBlockFamily.Variant.PILLAR) {
                    generator.registerAxisRotated(block, CUBE_COLUMN);
                }
                else if (variant == MBBlockFamily.Variant.COLUMN) {
                    if (block == MBBlocks.SPRUCE_PILLAR) {
                        columnHori(block, generator);
                    }
                    else {
                        column(block, generator);
                    }
                }
                else if (variant == MBBlockFamily.Variant.CUT) {
                    if (block == MBBlocks.CUT_MUDSTONE) {
                        generator.registerSingleton(MBBlocks.CUT_MUDSTONE, CUBE_BOTTOM_TOP);
                    }
                    else {
                        sideEnd(block, generator);
                    }
                }
                else if (variant == MBBlockFamily.Variant.BOOKSHELF) {
                    bookshelf(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.PLANTER_BOX) {
                    generator.registerSingleton(block, CUBE_BOTTOM_TOP);
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
                    if (block == MBBlocks.SMOOTH_STONE_WALL || block == MBBlocks.SMOOTH_DEEPSLATE_WALL) {
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
                    if (block == MBBlocks.SMOOTH_DEEPSLATE_SLAB) {
                        cutSlab(block, family.getBaseBlock(), generator);
                    }
                    else {
                        slab(block, family.getBaseBlock(), generator);
                    }
                }
                // special cases :b
                else if (block == MBBlocks.CHISELED_TUFF || block == MBBlocks.CHISELED_MUDSTONE) {
                    sideEnd(block, generator);
                }
                else if (block == MBBlocks.CHISELED_BASALT) {
                    generator.registerAxisRotated(block, TexturedModel.CUBE_COLUMN);
                }
                else if (block == MBBlocks.CHISELED_CHERT) {
                    TextureMap texture = TextureMap.sideEnd(TextureMap.getId(block), TextureMap.getSubId(MBBlocks.CUT_CHERT, "_top"));
                    Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
                    generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
                }
                else if (variant != MBBlockFamily.Variant.WALL_SIGN){
                    generator.registerSimpleCubeAll(block);
                }
            }
        });
        family.cuttable.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID) && !self.generatedBlocks.contains(block)) {
                self.generatedBlocks.add(block);
                if (block == MBBlocks.CHISELED_BASALT)
                    generator.registerAxisRotated(block, TexturedModel.CUBE_COLUMN);
                else generator.registerSimpleCubeAll(block);
            }
        });
    }
    // hardcoded ones
    public static void toughGrass (BlockStateModelGenerator generator) {
        Identifier identifier = TextureMap.getId(MBBlocks.TOUGH_DIRT);
        Identifier tough_grass = TINTED_GRASSLIKE.get(MBBlocks.TOUGH_GRASS).textures(texture ->  texture.put(TextureKey.BOTTOM, identifier)).upload(MBBlocks.TOUGH_GRASS, generator.modelCollector);
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
        Identifier identifier2 = Models.SLAB.upload(MBBlocks.GIANT_TOADSTOOL_CAP, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(MBBlocks.GIANT_TOADSTOOL_CAP, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(MBBlocks.GIANT_TOADSTOOL_CAP, identifier2, identifier3, identifier));
    }
    public static void pebbles(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.PEBBLES.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.PEBBLES).coordinate(BlockStateVariantMap.create(PebbleBlock.PEBBLES)
                .register(1, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MODID, "block/pebble"))))
                .register(2, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MODID, "block/two_pebbles"))))
                .register(3, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MODID, "block/three_pebbles"))))
                .register(4, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(Moonbits.MODID, "block/four_pebbles"))))
        ));
    }
    public static void barrelCactus(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.TINY_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_tiny")));
        generator.registerParentedItemModel(MBBlocks.TINY_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_tiny"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.SMALL_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_small")));
        generator.registerParentedItemModel(MBBlocks.SMALL_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_small"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_medium")));
        generator.registerParentedItemModel(MBBlocks.BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_medium"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.LARGE_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_large")));
        generator.registerParentedItemModel(MBBlocks.LARGE_BARREL_CACTUS, new Identifier(Moonbits.MODID, "block/barrel_cactus_large"));
    }
    public static void blazeRod(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.BLAZE_ROD).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod")).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod")).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/blaze_rod"))
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void toadstool(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.TOADSTOOL.asItem());
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.TOADSTOOL, new Identifier(Moonbits.MODID, "block/toadstool_cap")));
        generator.registerItemModel(MBBlocks.TOADSTOOL_STEM.asItem());
        Identifier stem = new Identifier(Moonbits.MODID, "block/toadstool_stem");
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.TOADSTOOL_STEM).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, stem))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, stem))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, stem).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, stem).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, stem)
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, stem)
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void lamproot(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.LAMPROOT_BULB.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.LAMPROOT_BULB).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot_horizontal")))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/lamproot_horizontal")).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void gills(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.SAFFRON_GILLS);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.SAFFRON_GILLS).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills")))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills")))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills_wall")))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/saffron_gills_wall")).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        ));
    }
    public static void frosthorn(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.FROSTHORN_CROWN).coordinate(BlockStateVariantMap.create(Properties.ATTACHED)
                .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/frosthorn_crown")))
                .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/frosthorn_crown_stem")))
        ));
        generator.registerParentedItemModel(MBBlocks.FROSTHORN_CROWN, new Identifier(Moonbits.MODID, "block/frosthorn_crown"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FROSTHORN_STEM, new Identifier(Moonbits.MODID, "block/frosthorn_stem")));
        generator.registerParentedItemModel(MBBlocks.FROSTHORN_STEM, new Identifier(Moonbits.MODID, "block/frosthorn_stem"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FROSTHORN_LEAVES, new Identifier(Moonbits.MODID, "block/frosthorn_leaves")));
        generator.registerItemModel(MBBlocks.FROSTHORN_LEAVES);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FROSTHORN_FRUIT, new Identifier(Moonbits.MODID, "block/frosthorn_fruit")));
        generator.registerItemModel(MBBlocks.FROSTHORN_FRUIT.asItem());
    }
    public static void cubeTopBottomSpec(Block block, Block bottom, BlockStateModelGenerator generator) {
        TextureMap tex2 = new TextureMap().put(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.SIDE, TextureMap.getId(block))
                .put(TextureKey.BOTTOM, TextureMap.getId(bottom));
        Identifier identifier2 = Models.CUBE_BOTTOM_TOP.upload(block, tex2, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier2));
    }
    public static void bandedIron(Block block, BlockStateModelGenerator generator) {
        Identifier id = TexturedModel.CUBE_ALL.upload(block, "_inventory", generator.modelCollector);
        generator.registerParentedItemModel(block, id);

        Identifier connected = new Identifier(Moonbits.MODID, "block/" + Registry.BLOCK.getId(block).getPath());
        Identifier single = new Identifier(Moonbits.MODID, "block/" + Registry.BLOCK.getId(block).getPath() + "_single");

        Identifier identifier = Models.TEMPLATE_SINGLE_FACE.upload(block, "_top", TextureMap.texture(new Identifier(Moonbits.MODID, "block/cut_chert_top")), generator.modelCollector);

        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.X, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier)
                        .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))

                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, connected))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, connected)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, connected)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, connected)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))

                .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, single))
                .with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, single)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, single)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, single)
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true)));
    }
    public static void bedroll(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.BEDROLL.asItem());
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.BEDROLL).coordinate(BlockStateVariantMap.create(Properties.BED_PART, Properties.HORIZONTAL_FACING)
                .register(BedPart.HEAD, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_head")))
                .register(BedPart.HEAD, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(BedPart.HEAD, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(BedPart.HEAD, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_head"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(BedPart.FOOT, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_foot")))
                .register(BedPart.FOOT, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(BedPart.FOOT, Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .register(BedPart.FOOT, Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(Moonbits.MODID, "block/bedroll_foot"))
                        .put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));
    }
    public static void floweringAcacia(BlockStateModelGenerator generator) {
        generator.registerParentedItemModel(MBBlocks.FLOWERING_ACACIA_LEAVES, new Identifier(Moonbits.MODID, "block/flowering_acacia_leaves"));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(MBBlocks.FLOWERING_ACACIA_LEAVES, new Identifier(Moonbits.MODID, "block/flowering_acacia_leaves")));

    }
    public final void redBrownMushrooms(BlockStateModelGenerator generator) {
        Identifier identifier = Models.TEMPLATE_SINGLE_FACE.upload(MBBlocks.RED_MUSHROOM_CAP, TextureMap.texture(Blocks.RED_MUSHROOM_BLOCK), generator.modelCollector);
        Identifier identifier2 = ModelIds.getMinecraftNamespacedBlock("mushroom_block_inside");
        shroomStates(generator, MBBlocks.RED_MUSHROOM_CAP, identifier, identifier2);
        generator.registerParentedItemModel(MBBlocks.RED_MUSHROOM_CAP, TexturedModel.CUBE_ALL.upload(Blocks.RED_MUSHROOM_BLOCK, "_inventory", generator.modelCollector));

        Identifier identifier3 = Models.TEMPLATE_SINGLE_FACE.upload(MBBlocks.BROWN_MUSHROOM_CAP, TextureMap.texture(Blocks.BROWN_MUSHROOM_BLOCK), generator.modelCollector);
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
    public final void redstoneCluster(Block block, BlockStateModelGenerator generator) {
        generator.registerItemModel(block);
        Identifier lit = Models.CROSS.upload(block, TextureMap.cross(block), generator.modelCollector);
        Identifier unlit = Models.CROSS.upload(block, "_unlit", TextureMap.of(TextureKey.CROSS, TextureMap.getSubId(block, "_unlit")), generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(
                BlockStateVariantMap.create(Properties.FACING, RedstoneClusterBlock.LIT)
                    .register(Direction.DOWN, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R180))
                    .register(Direction.UP, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit))
                        .register(Direction.NORTH, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90))
                    .register(Direction.SOUTH, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                    .register(Direction.WEST, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                    .register(Direction.EAST, true, BlockStateVariant.create().put(VariantSettings.MODEL, lit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))

                    .register(Direction.DOWN, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R180))
                    .register(Direction.UP, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit))
                    .register(Direction.NORTH, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90))
                    .register(Direction.SOUTH, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                    .register(Direction.WEST, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                    .register(Direction.EAST, false, BlockStateVariant.create().put(VariantSettings.MODEL, unlit)
                            .put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
        ));

    }

    public static void wallPlant(Block block, BlockStateModelGenerator generator) {
        generator.registerItemModel(block.asItem());
        Identifier identifier = ModelIds.getBlockModelId(block);
        TextureMap texture = TextureMap.all(block);
        //Identifier identifier2 = WALL_PLANT.upload(block, texture, generator.modelCollector);

        MultipartBlockStateSupplier multipartBlockStateSupplier = MultipartBlockStateSupplier.create(block);
        When.PropertyCondition propertyCondition2 = Util.make(When.create(), propertyCondition -> BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS.stream().map(Pair::getFirst).forEach(property -> {
            if (block.getDefaultState().contains(property)) {
                propertyCondition.set(property, false);
            }
        }));
        for (Pair<BooleanProperty, Function<Identifier, BlockStateVariant>> pair : BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS) {
            BooleanProperty booleanProperty = pair.getFirst();
            Function<Identifier, BlockStateVariant> function = pair.getSecond();
            if (!block.getDefaultState().contains(booleanProperty)) continue;
            multipartBlockStateSupplier.with((When)When.create().set(booleanProperty, true), function.apply(identifier));
            multipartBlockStateSupplier.with((When)propertyCondition2, function.apply(identifier));
        }
        generator.blockStateCollector.accept(multipartBlockStateSupplier);
    }

    public static void topSoil (Block block, Block bottom, BlockStateModelGenerator generator) {
        Identifier identifier = TextureMap.getId(bottom);
        Identifier identifier2 = TexturedModel.CUBE_BOTTOM_TOP.get(block).textures(texture -> {
            texture.put(TextureKey.BOTTOM, identifier);
            texture.put(TextureKey.SIDE, TextureMap.getId(block));
        }).upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(block, identifier2));
    }
    public static void seatBlock(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.of(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.BOTTOM, TextureMap.getSubId(MBBlocks.LEATHER_SEAT, "_bottom"));
        Identifier identifier = Models.SLAB.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(block, texture, generator.modelCollector);
        Identifier identifier3 = TexturedModel.CUBE_BOTTOM_TOP.get(block)
                .textures(tex -> tex.put(TextureKey.SIDE, TextureMap.getSubId(block, "_double"))
                        .put(TextureKey.BOTTOM, TextureMap.getSubId(MBBlocks.LEATHER_SEAT, "_bottom")))
                .upload(block, "_double", generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(block, identifier, identifier2, identifier3));
    }

    public static void log(Block logBlock, Block woodBlock, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideAndEndForTop(logBlock);
        Identifier identifier = Models.CUBE_COLUMN.upload(logBlock, texture, generator.modelCollector);
        Identifier identifier2 = Models.CUBE_COLUMN_HORIZONTAL.upload(logBlock, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(logBlock, identifier, identifier2));

        TextureMap texture2 = texture.copyAndAdd(TextureKey.END, texture.getTexture(TextureKey.SIDE));
        Identifier identifier3 = Models.CUBE_COLUMN.upload(woodBlock, texture2, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(woodBlock, identifier3));
    }

    public final void vanillaPlantSnowy(Block block, BlockStateModelGenerator generator) {
        Identifier identifier = new Identifier("block/" + Registry.BLOCK.getId(block).getPath());
        Identifier snow = new Identifier("block/snow_height2");

        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .with(When.create().set(Properties.SNOWY, true), BlockStateVariant.create().put(VariantSettings.MODEL, snow))
        );
    }

    public static void prickly_pear(BlockStateModelGenerator generator) {
        generator.registerItemModel(MBBlocks.PRICKLY_PEAR_CACTUS, "_0");

        Identifier id1 = Models.CROSS.upload(MBBlocks.PRICKLY_PEAR_CACTUS, "_0",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.PRICKLY_PEAR_CACTUS, "_0")), generator.modelCollector);
        Identifier id2 = Models.CROSS.upload(MBBlocks.PRICKLY_PEAR_CACTUS, "_1",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.PRICKLY_PEAR_CACTUS, "_1")), generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.PRICKLY_PEAR_CACTUS).coordinate(BlockStateVariantMap.create(Properties.AGE_1)
                .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, id1))
                .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, id2))
        ));

        Identifier idt1 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_0",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_0")), generator.modelCollector);
        Identifier idt2 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_1",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_1")), generator.modelCollector);
        Identifier idt3 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_2",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_bottom_2")), generator.modelCollector);
        Identifier idt4 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_0",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_0")), generator.modelCollector);
        Identifier idt5 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_1",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_1")), generator.modelCollector);
        Identifier idt6 = Models.CROSS.upload(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_2",
                TextureMap.cross(TextureMap.getSubId(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, "_top_2")), generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(MBBlocks.TALL_PRICKLY_PEAR_CACTUS)
                .coordinate(BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF, Properties.AGE_2)
                .register(DoubleBlockHalf.LOWER, 0, BlockStateVariant.create().put(VariantSettings.MODEL, idt1))
                .register(DoubleBlockHalf.LOWER, 1, BlockStateVariant.create().put(VariantSettings.MODEL, idt2))
                .register(DoubleBlockHalf.LOWER, 2, BlockStateVariant.create().put(VariantSettings.MODEL, idt3))
                .register(DoubleBlockHalf.UPPER, 0, BlockStateVariant.create().put(VariantSettings.MODEL, idt4))
                .register(DoubleBlockHalf.UPPER, 1, BlockStateVariant.create().put(VariantSettings.MODEL, idt5))
                .register(DoubleBlockHalf.UPPER, 2, BlockStateVariant.create().put(VariantSettings.MODEL, idt6))
        ));
    }

    public static void flowerPotPlant(Block plantBlock, Block flowerPotBlock, TintType tintType, BlockStateModelGenerator generator) {
        flowerPotPlant(plantBlock, flowerPotBlock, tintType, generator, true);
    }
    public static void flowerPotPlant(Block plantBlock, Block flowerPotBlock, TintType tintType, BlockStateModelGenerator generator, Boolean genItem) {
        tintableCross(plantBlock, tintType, generator, genItem);
        TextureMap texture = TextureMap.plant(plantBlock);
        Identifier identifier = tintType.getFlowerPotCrossModel().upload(flowerPotBlock, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(flowerPotBlock, identifier));
    }
    public static void pottedBlock(Block root, Block pottedRoot, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.plant(TextureMap.getSubId(root, "_pot"));
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
        TextureMap texture = TextureMap.cross(block);
        Identifier identifier = tintType.getCrossModel().upload(block, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
//        Identifier snow = new Identifier("block/snow_height2"); // for the snowy variant
//
//        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
//                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
//                .with(When.create().set(Properties.SNOWY, true), BlockStateVariant.create().put(VariantSettings.MODEL, snow))
//        );
    }
    public static void cappedCross(Block block, BlockStateModelGenerator generator) {
        cappedCross(block, TintType.NOT_TINTED, generator, true);
    }
    public static void cappedCross(Block block, TintType tintType, BlockStateModelGenerator generator, Boolean genItem) {
        if (genItem) {
            generator.registerItemModel(block);
        }
        Identifier identifier = CAPPED_CROSS_F.get(block).upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(block, identifier));
    }

    public static void omniCross(Block block, BlockStateModelGenerator generator, Boolean genItem) {
        if (genItem) {
            generator.registerItemModel(block);
        }
        TextureMap texture = TextureMap.cross(block);
        Identifier identifier = Models.CROSS.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.CORAL_WALL_FAN.upload(block, "_wall", TextureMap.of(TextureKey.FAN,
                new Identifier(Moonbits.MODID, "block/" + Registry.BLOCK.getId(block).getPath() + "_wall")), generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(Properties.FACING)
                .register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.X, VariantSettings.Rotation.R180))
                .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R270))
        ));
    }

    public static void snowyBlock(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.all(block).put(TextureKey.ALL, TextureMap.getId(block));
        TextureMap texture2 = TextureMap.sideTopBottom(block).put(TextureKey.TOP, TextureMap.getId(Blocks.SNOW_BLOCK))
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_snowy"))
                .put(TextureKey.BOTTOM, TextureMap.getId(block));
        Identifier identifier = Models.CUBE_ALL.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.CUBE_BOTTOM_TOP.upload(block, "_snowy", texture2, generator.modelCollector);
        generator.registerParentedItemModel(block, identifier);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateVariantMap.create(Properties.SNOWY)
                .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
        ));
    }

    public void thinLog(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(block).put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.END, TextureMap.getSubId(block, "_top"));
        Identifier identifier = PALISADE_POST.upload(block, texture, generator.modelCollector);
        generator.registerParentedItemModel(block, identifier);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(block, identifier));
    }

    public void palisade(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(block).put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.END, TextureMap.getSubId(block, "_top"));
        Identifier identifier = PALISADE_POST.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = PALISADE_SIDE.upload(block, texture, generator.modelCollector);
        Identifier identifier3 = PALISADE_INVENTORY.upload(block, texture, generator.modelCollector);

        generator.registerParentedItemModel(block, identifier3);
        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .with(When.create().set(Properties.NORTH, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                .with(When.create().set(Properties.EAST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.SOUTH, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.UVLOCK, true))
                .with(When.create().set(Properties.WEST, true),
                        BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)
                                .put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.UVLOCK, true))
        );
    }

    public void pane(Block block, BlockStateModelGenerator generator) {
        TextureMap textureMap = TextureMap.paneAndTopForEdge(block, block);
        TextureMap textureMap2 = TextureMap.paneAndTopForEdge(block, block).put(TextureKey.EDGE, TextureMap.getSubId(block, "_top_a"));
        Identifier identifier = Models.TEMPLATE_GLASS_PANE_POST.upload(block, textureMap, generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_GLASS_PANE_SIDE.upload(block, textureMap, generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_GLASS_PANE_SIDE_ALT.upload(block, block instanceof LatticeBlock ? textureMap2 : textureMap, generator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_GLASS_PANE_NOSIDE.upload(block, textureMap, generator.modelCollector);
        Identifier identifier5 = Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT.upload(block, textureMap, generator.modelCollector);
        Item item = block.asItem();
        Models.GENERATED.upload(ModelIds.getItemModelId(item), TextureMap.layer0(block), generator.modelCollector);
        generator.blockStateCollector.accept(MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .with(When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2))
                .with(When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3))
                .with(When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4))
                .with(When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5))
                .with(When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier5).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R270)));
    }

    public static void doubleBlock(Block doubleBlock, TintType tintType, BlockStateModelGenerator generator) {
        generator.registerItemModel(doubleBlock, "_top");
        Identifier identifier = generator.createSubModel(doubleBlock, "_top", tintType.getCrossModel(), TextureMap::cross);
        Identifier identifier2 = generator.createSubModel(doubleBlock, "_bottom", tintType.getCrossModel(), TextureMap::cross);
        generator.registerDoubleBlock(doubleBlock, identifier, identifier2);
    }

    private static void column(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(TextureMap.getId(block), TextureMap.getSubId(block, "_top"));
        Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.CUBE_COLUMN_HORIZONTAL.upload(block, texture, generator.modelCollector);

        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(block, identifier, identifier2));
    }
    private static void columnHori(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(TextureMap.getId(block), TextureMap.getSubId(block, "_top"));
        TextureMap texture2 = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.END, TextureMap.getSubId(block, "_side_top"));
        Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
        Identifier identifier2 = Models.CUBE_COLUMN_HORIZONTAL.upload(block, texture2, generator.modelCollector);

        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(block, identifier, identifier2));
    }

    private static void sideEnd(Block block, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(TextureMap.getId(block), TextureMap.getSubId(block, "_top"));
        Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private static void bookshelf(Block bookshelf, Block base, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.sideEnd(TextureMap.getId(bookshelf), TextureMap.getId(base));
        Identifier identifier = Models.CUBE_COLUMN.upload(bookshelf, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(bookshelf, identifier));
    }

    private static void slab(Block slab, Block base, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(base);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier2 = Models.SLAB.upload(slab, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(slab, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier2, identifier3, identifier));
    }
    private static void cutSlab(Block slab, Block base, BlockStateModelGenerator generator) {
        TextureMap textureMap = TextureMap.all(base);
        TextureMap textureMap2 = TextureMap.sideEnd(TextureMap.getSubId(slab, "_side"), textureMap.getTexture(TextureKey.TOP));
        Identifier identifier = Models.SLAB.upload(slab, textureMap2, generator.modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(slab, textureMap2, generator.modelCollector);
        Identifier identifier3 = Models.CUBE_COLUMN.uploadWithoutVariant(slab, "_double", textureMap2, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier, identifier2, identifier3));
    }

    private static void stairs(Block stairs, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.INNER_STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = Models.STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = Models.OUTER_STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, identifier, identifier2, identifier3));
    }

    public static void trim(Block trim, BlockStateModelGenerator generator) {
        TextureMap texture = new TextureMap().put(TextureKey.TOP, TextureMap.getId(trim)).put(POST, TextureMap.getSubId(trim, "_post"));
        Identifier id = TRIM.upload(trim, texture, generator.modelCollector);
        Identifier id2 = TRIM_HANGING.upload(trim, "_hanging", texture, generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(trim, BlockStateVariant.create())
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_AXIS, Properties.ATTACHED)
                        .register(Direction.Axis.X, true, BlockStateVariant.create().put(VariantSettings.MODEL, id))
                        .register(Direction.Axis.Z, true, BlockStateVariant.create().put(VariantSettings.MODEL, id).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                        .register(Direction.Axis.X, false, BlockStateVariant.create().put(VariantSettings.MODEL, id2))
                        .register(Direction.Axis.Z, false, BlockStateVariant.create().put(VariantSettings.MODEL, id2).put(VariantSettings.Y, VariantSettings.Rotation.R90))));
    }

    private static void tintedBlock(Block block, BlockStateModelGenerator generator) {
        Identifier id = TINTED_BLOCK.upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, id));
    }
    private static void tintedSlab(Block slab, Block base, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(base);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier2 = TINTED_SLAB.upload(slab, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = TINTED_SLAB_TOP.upload(slab, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier2, identifier3, identifier));
    }
    private static void tintedStairs(Block stairs, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = TINTED_INNER_STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = TINTED_STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = TINTED_OUTER_STAIRS.upload(stairs, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, identifier, identifier2, identifier3));
    }
    public static void tintedCarpet(Block base, Block carpet, BlockStateModelGenerator generator) {
        Identifier identifier = TINTED_CARPET_F.get(base).upload(carpet, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(carpet, identifier));
    }
    public final void wallLantern(Block lantern, Block base, BlockStateModelGenerator generator) {
        //Texture texture = Texture.lantern(base);
        Identifier identifier = WALL_LANTERN.upload(lantern, TextureMap.lantern(base), generator.modelCollector);
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
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.registerParentedItemModel(fenceBlock, identifier3);
    }
    private static void fenceGate(Block fenceGateBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4));
    }
    public static void wall(Block wallBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_WALL_POST.upload(wallBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, identifier, identifier2, identifier3));
        Identifier identifier4 = Models.WALL_INVENTORY.upload(wallBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.registerParentedItemModel(wallBlock, identifier4);
    }
    public static void smoothStoneWall(Block wallBlock, Block base, BlockStateModelGenerator generator) {
        String a = Registry.BLOCK.getId(base).getPath();
        Identifier identifier = new Identifier(Moonbits.MODID, "block/" + a + "_wall_post");
        Identifier identifier2 = new Identifier(Moonbits.MODID, "block/" + a + "_wall_side");
        Identifier identifierB = new Identifier(Moonbits.MODID, "block/" + a + "_wall_side_b");
        Identifier identifier3 = new Identifier(Moonbits.MODID, "block/" + a + "_wall_side_tall");
        generator.blockStateCollector.accept(smoothStoneWallBlockState(wallBlock, identifier, identifier2, identifierB, identifier3));
        Identifier identifier4 = new Identifier(Moonbits.MODID, "block/" + a + "_wall_inventory");
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
        Identifier identifier = Models.PARTICLE.upload(signBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, identifier));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSign, identifier));
        generator.registerItemModel(signBlock.asItem());
        generator.excludeFromSimpleItemModelGeneration(wallSign);
    }
    public static void button(Block buttonBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.BUTTON.upload(buttonBlock, texturedModel.getTextures(), generator.modelCollector);
        Identifier identifier2 = Models.BUTTON_PRESSED.upload(buttonBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, identifier, identifier2));
        Identifier identifier3 = Models.BUTTON_INVENTORY.upload(buttonBlock, texturedModel.getTextures(), generator.modelCollector);
        generator.registerParentedItemModel(buttonBlock, identifier3);
    }
    public static void pressurePlate(Block pressurePlateBlock, Block base, BlockStateModelGenerator generator) {
        TextureMap texture = TextureMap.all(base);
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
//    public static void carpetFlora(Block carpet, BlockStateModelGenerator generator) {
//        generator.registerItemModel(carpet);
//        Identifier identifier = CAPPED_CROSS_F.get(carpet).upload(carpet, generator.modelCollector);
//        generator.blockStateCollector.accept(BlockStateModelGenerator.createBlockStateWithRandomHorizontalRotations(carpet, identifier));
//    }
//    public static void planterBox(Block planterBox, BlockStateModelGenerator generator) {
//        TexturedModel texturedModel = PLANTER_BOX_F.get(planterBox);
//        Identifier identifier = PLANTER_BOX.upload(planterBox, texturedModel.getTextures(), generator.modelCollector);
//
//        TexturedModel texturedModel2 = PLANTER_BOX_INNER_F.get(planterBox);
//        TextureMap tex2 = new TextureMap().put(INNER, TextureMap.getSubId(planterBox, "_inner")).put(TextureKey.SIDE, TextureMap.getId(planterBox)).put(TextureKey.TOP, TextureMap.getSubId(planterBox, "_top"))
//                .put(TextureKey.BOTTOM, TextureMap.getSubId(planterBox, "_bottom"));
//        Identifier identifier2 = PLANTER_BOX_INNER.upload(planterBox, "_inner", texturedModel2.getTextures(), generator.modelCollector);
//
//        TextureMap tex3 = new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(planterBox, "_edge")).put(TextureKey.TOP, TextureMap.getSubId(planterBox, "_top"))
//                .put(TextureKey.BOTTOM, TextureMap.getSubId(planterBox, "_bottom"));
//        Identifier identifier3 = PLANTER_BOX_OUTER.upload(planterBox, "_outer", tex3, generator.modelCollector);
//
//        TextureMap tex4 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(planterBox)).put(TextureKey.TOP, TextureMap.getSubId(planterBox, "_side"))
//                .put(TextureKey.BOTTOM, TextureMap.getSubId(planterBox, "_bottom"));
//        Identifier identifier4 = PLANTER_BOX_SIDE.upload(planterBox, "_side", tex4, generator.modelCollector);
//
//        TextureMap tex5 = new TextureMap().put(TextureKey.SIDE, TextureMap.getId(planterBox)).put(TextureKey.TOP, TextureMap.getSubId(planterBox, "_side_b"))
//                .put(TextureKey.BOTTOM, TextureMap.getSubId(planterBox, "_bottom"));
//        Identifier identifier5 = PLANTER_BOX_SIDE_B.upload(planterBox, "_side_b", tex5, generator.modelCollector);
//
//        generator.blockStateCollector.accept(createPlanterBoxState(planterBox, identifier, identifier2, identifier3, identifier4, identifier5));
//
//        TexturedModel texturedModel6 = PLANTER_BOX_INVENTORY_F.get(planterBox);
//        Identifier identifier6 = PLANTER_BOX_INVENTORY.upload(planterBox, "_inventory", texturedModel6.getTextures(), generator.modelCollector);
//        generator.registerParentedItemModel(planterBox, identifier6);
//    }
//    public static BlockStateSupplier createPlanterBoxState(Block block, Identifier regular, Identifier inner, Identifier outer, Identifier side, Identifier sideB) {
//        return MultipartBlockStateSupplier.create(block)
//                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
//                        .put(VariantSettings.UVLOCK, true))
//                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
//                        .put(VariantSettings.UVLOCK, true))
//                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
//                        .put(VariantSettings.UVLOCK, true))
//                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
//                        .put(VariantSettings.UVLOCK, true))
//                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
//                        .put(VariantSettings.UVLOCK, true))
//
//                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
//                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
//                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
//                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
//                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
//
//                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
//                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
//                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
//                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
//                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
//
//                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
//                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
//                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
//                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
//                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
//                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
//                ;
//    }

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

    public static TextureMap leaf(Block block) {
        return new TextureMap().put(LEAF, TextureMap.getId(block));
    }
    public static TextureMap capCross(Block block) {
        return new TextureMap().put(TextureKey.CROSS, TextureMap.getSubId(block, "_stem")).put(CAP, TextureMap.getId(block));
    }
    public static TextureMap grasslike(Block block) {
        return new TextureMap().put(TextureKey.BOTTOM, TextureMap.getId(Blocks.DIRT)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.SIDE, TextureMap.getId(block)).put(OVERLAY, TextureMap.getSubId(block, "_overlay"));
    }
    public static TextureMap column(Block block, Block end) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_side")).put(TextureKey.END, TextureMap.getId(end));
    }
    public static TextureMap sideEnd(Block block) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.END, TextureMap.getSubId(block, "_top"));
    }
    public static TextureMap sideBottom(Block block) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.END, TextureMap.getSubId(block, "_bottom"));
    }
    public static TextureMap sideTopBottom(Block block) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
    }
    public static TextureMap sideTopBottom(Block block, String topSuffix) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(TextureKey.TOP, TextureMap.getSubId(block, topSuffix)).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
    }
    public static TextureMap edgeTopBottom(Block block) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getSubId(block, "_edge")).put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
    }
    public static TextureMap pbInner(Block block) {
        return new TextureMap().put(TextureKey.SIDE, TextureMap.getId(block)).put(INNER, TextureMap.getSubId(block, "_inner"))
                .put(TextureKey.TOP, TextureMap.getSubId(block, "_top")).put(TextureKey.BOTTOM, TextureMap.getSubId(block, "_bottom"));
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
