package net.paperfish.moonbits.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.HolderSet;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Range;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.BlockPredicateFilterPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.block.BarrelCactusBlock;
import net.paperfish.moonbits.block.PebbleBlock;
import net.paperfish.moonbits.world.feature.PebbleFeature;

import java.util.List;

public class MBVegetationFeatures {
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_CARROT_PATCH =
            MBConfiguredFeatures.register("patch_carrots", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.WILD_CARROTS));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_POTATO_PATCH =
            MBConfiguredFeatures.register("patch_potatoes", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.WILD_POTATOES));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SEA_BEET_PATCH =
            MBConfiguredFeatures.register("patch_beets", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.SEA_BEETS));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_MYCELIUM =
            MBConfiguredFeatures.register("patch_mycelium", Feature.RANDOM_PATCH, createPatch(32, BlockStateProvider.of(MBBlocks.MYCELIUM_ROOTS)));

    public static final DataPool<BlockState> TOADSTOOL_LIST = new DataPool.Builder<BlockState>()
            .add(MBBlocks.TOADSTOOL.getDefaultState(), 1)
            .add(MBBlocks.SMALL_TOADSTOOLS.getDefaultState(), 4)
            .build();
    public static final DataPool<BlockState> AUTUMN_FLOWER_LIST = new DataPool.Builder<BlockState>()
            .add(Blocks.OXEYE_DAISY.getDefaultState(), 4)
            .add(Blocks.DANDELION.getDefaultState(), 2)
            .add(Blocks.CORNFLOWER.getDefaultState(), 1)
            .add(MBBlocks.FORGETMENOT.getDefaultState(), 2)
            .add(Blocks.POPPY.getDefaultState(), 1)
            .build();
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BUTTERCUP_PATCH =
            MBConfiguredFeatures.register("patch_buttercups", Feature.RANDOM_PATCH, createPatch(32, MBBlocks.BUTTERCUP));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FORGETMENOT_PATCH =
            MBConfiguredFeatures.register("patch_forgetmenot", Feature.RANDOM_PATCH, createPatch(16, MBBlocks.FORGETMENOT));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILDFLOWER_PATCH =
            MBConfiguredFeatures.register("patch_wildflower", Feature.RANDOM_PATCH, createPatch(16, MBBlocks.WILDFLOWERS));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> CLOVER_PATCH =
            MBConfiguredFeatures.register("patch_clover", Feature.RANDOM_PATCH, createPatch(16, MBBlocks.CLOVER));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PUFFBALLS_PATCH =
            MBConfiguredFeatures.register("patch_puffballs", Feature.RANDOM_PATCH, createPatch(8, MBBlocks.PUFFBALLS));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> TOADSTOOLS =
            MBConfiguredFeatures.register("toadstools", Feature.RANDOM_PATCH, createPatch(3, new WeightedBlockStateProvider(TOADSTOOL_LIST)));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> AUTUMN_FLOWERS =
            MBConfiguredFeatures.register("autumn_flowers", Feature.RANDOM_PATCH, createPatch(12, new WeightedBlockStateProvider(AUTUMN_FLOWER_LIST)));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ROSE_BUSH =
            MBConfiguredFeatures.register("rose_bush", Feature.RANDOM_PATCH, createPatch(8, Blocks.ROSE_BUSH));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SUNFLOWERS =
            MBConfiguredFeatures.register("mb_sunflowers", Feature.RANDOM_PATCH, createPatch(6, Blocks.SUNFLOWER));

    public static final Holder<ConfiguredFeature<SimpleRandomFeatureConfig, ?>> HYACINTHS = MBConfiguredFeatures.register("hyacinths", Feature.SIMPLE_RANDOM_SELECTOR,
            new SimpleRandomFeatureConfig(HolderSet.createDirect(
            		PlacedFeatureUtil.placedInline(Feature.RANDOM_PATCH, (ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                    	new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.WHITE_HYACINTH)))))),
					PlacedFeatureUtil.placedInline(Feature.RANDOM_PATCH, (ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                    	new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.PINK_HYACINTH)))))),
					PlacedFeatureUtil.placedInline(Feature.RANDOM_PATCH, (ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                    	new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.LIGHT_BLUE_HYACINTH)))))),
					PlacedFeatureUtil.placedInline(Feature.RANDOM_PATCH, (ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                    	new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.RED_HYACINTH))))))
    )));

    public static final Feature<CountConfig> PEBBLE_FEATURE = Registry.register(Registry.FEATURE, "pebble_feature", new PebbleFeature(CountConfig.CODEC));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PUMPKIN_PATCH = MBConfiguredFeatures.register("mb_pumpkin_patch", Feature.RANDOM_PATCH,
            createPatch(8, BlockStateProvider.of(Blocks.PUMPKIN), List.of(Blocks.GRASS_BLOCK)));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEBBLES = MBConfiguredFeatures.register("mb_pebbles",
            Feature.RANDOM_PATCH, createPatch(1, 12, 2, new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                    .add(MBBlocks.PEBBLES.getDefaultState(), 3)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 2), 6)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 3), 4)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 4), 1)
                    .build())));
    public static final Holder<ConfiguredFeature<GeodeFeatureConfig, ?>> BOULDER = MBConfiguredFeatures.register("stone_boulder", Feature.GEODE,
            new GeodeFeatureConfig(new GeodeLayerConfig(
                    BlockStateProvider.of(Blocks.STONE),
                    BlockStateProvider.of(Blocks.STONE),
                    BlockStateProvider.of(Blocks.STONE),
                    BlockStateProvider.of(Blocks.STONE),
                    BlockStateProvider.of(Blocks.STONE),
                    List.of(Blocks.STONE.getDefaultState()),
                    BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerThicknessConfig(0.5, 1, 1.25, 1.96),
                    new GeodeCrackConfig(0, 0, 0),
                    0.35, 0.083, true,
                    UniformIntProvider.create(4, 6), UniformIntProvider.create(3, 4), UniformIntProvider.create(1, 2), -16, 16, 0.05, 1));


    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DESERT_BRUSH =
            MBConfiguredFeatures.register("patch_desert_brush", Feature.RANDOM_PATCH, createPatch(12, 3, 2, BlockStateProvider.of(MBBlocks.DESERT_BRUSH)));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FLOOD_DESERT_BRUSH =
            MBConfiguredFeatures.register("flood_desert_brush", Feature.RANDOM_PATCH, createPatch(24, 4, 2, BlockStateProvider.of(MBBlocks.DESERT_BRUSH)));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MARIGOLD_PATCH = MBConfiguredFeatures.register(
            "patch_marigold", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.MARIGOLD));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FLOOD_MARIGOLD_PATCH = MBConfiguredFeatures.register(
            "flood_marigold", Feature.RANDOM_PATCH, createPatch(8, MBBlocks.MARIGOLD));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BARREL_CACTI = MBConfiguredFeatures.register(
            "barrel_cacti", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(6, 6, 2,
					PlacedFeatureUtil.placedInline(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(MBBlocks.TINY_BARREL_CACTUS.getDefaultState(), 4)
                            .add(MBBlocks.SMALL_BARREL_CACTUS.getDefaultState(), 6)
                            .add(MBBlocks.BARREL_CACTUS.getDefaultState(), 1)
                            .build())), BlockPredicateFilterPlacementModifier.create(
							BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.wouldSurvive(MBBlocks.BARREL_CACTUS.getDefaultState(), BlockPos.ORIGIN))
					))));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FLOOD_BARREL_CACTI = MBConfiguredFeatures.register(
            "flood_cacti", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(12, 6, 2,
					PlacedFeatureUtil.placedInline(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(MBBlocks.TINY_BARREL_CACTUS.getDefaultState(), 4)
                            .add(MBBlocks.SMALL_BARREL_CACTUS.getDefaultState(), 6)
                            .add(MBBlocks.BARREL_CACTUS.getDefaultState(), 1)
                            .build())), BlockPredicateFilterPlacementModifier.create(
							BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.wouldSurvive(MBBlocks.BARREL_CACTUS.getDefaultState(), BlockPos.ORIGIN))
					))));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> COTTONGRASS =
            MBConfiguredFeatures.register("patch_cottongrass", Feature.RANDOM_PATCH, createPatch(12, BlockStateProvider.of(MBBlocks.COTTONGRASS)));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LUPINE = MBConfiguredFeatures.register(
            "patch_lupine", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.LUPINE));

    public static final List<BlockState> HEATHER_LIST = List.of(
            MBBlocks.PURPLE_HEATHER.getDefaultState(),
            MBBlocks.PURPLE_HEATHER.getDefaultState(),
            MBBlocks.PURPLE_HEATHER.getDefaultState(),
            MBBlocks.PURPLE_HEATHER.getDefaultState(),
            MBBlocks.RED_HEATHER.getDefaultState(),
            MBBlocks.RED_HEATHER.getDefaultState(),
            MBBlocks.RED_HEATHER.getDefaultState(),
            MBBlocks.RED_HEATHER.getDefaultState(),
            MBBlocks.ORANGE_HEATHER.getDefaultState(),
            MBBlocks.ORANGE_HEATHER.getDefaultState(),
            MBBlocks.ORANGE_HEATHER.getDefaultState(),
            MBBlocks.WHITE_HEATHER.getDefaultState()
            );
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> HEATHER = MBConfiguredFeatures.register(
            "heather", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(32, 2, 2,
					PlacedFeatureUtil.placedInline(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L,
                            new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.020433334F, HEATHER_LIST)),
							BlockPredicateFilterPlacementModifier.create(
									BlockPredicate.bothOf(BlockPredicate.IS_AIR,
											BlockPredicate.wouldSurvive(MBBlocks.PURPLE_HEATHER.getDefaultState(), BlockPos.ORIGIN))
							))));
//            new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
//                            .add(MBBlocks.PURPLE_HEATHER.getDefaultState(), 8)
//                            .add(MBBlocks.RED_HEATHER.getDefaultState(), 5)
//                            .add(MBBlocks.ORANGE_HEATHER.getDefaultState(), 6)
//                            .add(MBBlocks.WHITE_HEATHER.getDefaultState(), 1)
//                            .build())))));

    public static final List<OreFeatureConfig.Target> PERMAFROST = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.PERMAFROST.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.PERMAFROST.getDefaultState())
    );
    public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> TUNDRA_FROST = MBConfiguredFeatures.register(
            "tundra_frost", Feature.ORE, new OreFeatureConfig(PERMAFROST, 48));

    public static final Holder<ConfiguredFeature<SingleStateFeatureConfig, ?>> TILL_ROCK = MBConfiguredFeatures.register(
            "till_rock", Feature.FOREST_ROCK, new SingleStateFeatureConfig(MBBlocks.TILL.getDefaultState()));

    public static final List<OreFeatureConfig.Target> CRACKED_MUD = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.CRACKED_MUD.getDefaultState())
    );
    public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_CRACKED_MUD = MBConfiguredFeatures.register(
            "patch_cracked_mud", Feature.ORE, new OreFeatureConfig(CRACKED_MUD, 32));

    public static final List<OreFeatureConfig.Target> RICH_MUD = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.RICH_MUD.getDefaultState())
    );
    public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_RICH_MUD = MBConfiguredFeatures.register(
            "patch_rich_mud", Feature.ORE, new OreFeatureConfig(RICH_MUD, 32));

    public static final List<OreFeatureConfig.Target> PEAT_MOSS = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.PEAT_MOSS.getDefaultState())
    );
    public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_PEAT_MOSS = MBConfiguredFeatures.register(
            "patch_peat_moss", Feature.ORE, new OreFeatureConfig(PEAT_MOSS, 14));

    public static final Holder<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> COARSE_DIRT_PATCH = MBConfiguredFeatures.register("coarse_dirt_floor", Feature.NETHERRACK_REPLACE_BLOBS,
            new ReplaceBlobsFeatureConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    Blocks.COARSE_DIRT.getDefaultState(),
                    UniformIntProvider.create(2, 4)
            ));
    public static final Holder<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> PODZOL_PATCH = MBConfiguredFeatures.register("podzol_floor", Feature.NETHERRACK_REPLACE_BLOBS,
            new ReplaceBlobsFeatureConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    Blocks.PODZOL.getDefaultState(),
                    UniformIntProvider.create(2, 5)
            ));
    public static final Holder<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> LEAFBED_PATCH = MBConfiguredFeatures.register("leafbed_floor", Feature.NETHERRACK_REPLACE_BLOBS,
            new ReplaceBlobsFeatureConfig(
                    Blocks.GRASS_BLOCK.getDefaultState(),
                    MBBlocks.LEAFBED.getDefaultState(),
                    UniformIntProvider.create(3, 5)
            ));

    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> GBF_TREES = MBConfiguredFeatures.register("gbf_trees", Feature.RANDOM_SELECTOR,
            new RandomFeatureConfig(List.of(
                    new WeightedPlacedFeature(TreePlacedFeatures.OAK_BEES_002, 0.025f),
                    new WeightedPlacedFeature(PlacedFeatureUtil.placedInline(MBTreeFeatures.GIANT_TOADSTOOL), 0.1F),
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.4F)),
                    MBPlacedTreeFeatures.GOLDEN_BIRCH_BEES_005
            ));
    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> PASTURE_TREES = MBConfiguredFeatures.register("pas_trees", Feature.RANDOM_SELECTOR,
            new RandomFeatureConfig(List.of(
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.RED_OAK_BEES_002, 0.1F),
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.4F)),
                    MBPlacedTreeFeatures.GOLDEN_BIRCH_BEES_005
            ));
//    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> RED_OAK_TREES = MBConfiguredFeatures.register("red_oak_trees", Feature.RANDOM_SELECTOR,
//            new RandomFeatureConfig(List.of(
//                    new RandomFeatureEntry(MBPlacedTreeFeatures.RED_OAK_BEES_002, 0.1F),
//                    new RandomFeatureEntry(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.4F)),
//                    MBPlacedTreeFeatures.BIG_RED_OAK
//            ));
    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> RED_OAK_TREES = MBConfiguredFeatures.register("red_oak_trees", Feature.RANDOM_SELECTOR,
            new RandomFeatureConfig(List.of(
                    new WeightedPlacedFeature(PlacedFeatureUtil.placedInline(MBTreeFeatures.HUGE_BROWN_MUSHROOM), 0.025f),
                    new WeightedPlacedFeature(PlacedFeatureUtil.placedInline(MBTreeFeatures.HUGE_RED_MUSHROOM), 0.05f),
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.BIG_RED_OAK, 0.6666667f),
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.GOLDEN_BIRCH, 0.2f),
                    new WeightedPlacedFeature(MBPlacedTreeFeatures.RED_OAK_BEES_002, 0.1f)),
                    MBPlacedTreeFeatures.RED_OAK
            ));


    //    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> JUNIPER_TREES = MBConfiguredFeatures.register("juniper_trees", Feature.RANDOM_SELECTOR,
//                    new RandomFeatureConfig(List.of(
//                            new RandomFeatureEntry(MBPlacedTreeFeatures.JUNIPER_BUSH, 0.1F),
//                            new RandomFeatureEntry(MBPlacedTreeFeatures.JUNIPER, 0.4F)),
//                            MBPlacedTreeFeatures.JUNIPER
//                    ));
    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> BADLANDS_TREES = MBConfiguredFeatures.register("badlands_trees", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(
                            new WeightedPlacedFeature(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.1F),
                            new WeightedPlacedFeature(MBPlacedTreeFeatures.CEDAR, 0.4F)),
                            TreePlacedFeatures.OAK_BEES_002
                    ));

	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> CHERT_FLORA = MBConfiguredFeatures.register("chert_flora", Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(List.of(
					new WeightedPlacedFeature(MBPlacedTreeFeatures.HARDY_BUSH, 0.1F),
					new WeightedPlacedFeature(MBPlacedTreeFeatures.PARASOL_FERN, 0.4F)),
					MBPlacedTreeFeatures.PARASOL_FERN
			));

//    public static final List<BlockState> MBFF_FLOWER_LIST = List.of(
//            Blocks.DANDELION.getDefaultState(),
//            Blocks.POPPY.getDefaultState(),
//            MBBlocks.FORGETMENOT.getDefaultState(),
//            Blocks.ALLIUM.getDefaultState(),
//            Blocks.AZURE_BLUET.getDefaultState(),
//            Blocks.RED_TULIP.getDefaultState(),
//            Blocks.ORANGE_TULIP.getDefaultState(),
//            Blocks.WHITE_TULIP.getDefaultState(),
//            Blocks.PINK_TULIP.getDefaultState(),
//            Blocks.OXEYE_DAISY.getDefaultState(),
//            Blocks.CORNFLOWER.getDefaultState(),
//            MBBlocks.BUTTERCUP.getDefaultState(),
//            Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
//            MBBlocks.BUTTERCUP.getDefaultState()
//    );
//    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MBFF_FLOWERS = MBConfiguredFeatures.register("mbff_flowers", Feature.FLOWER, new RandomPatchFeatureConfig(96, 6, 2,
//            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L,
//                    new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.020833334F, MBFF_FLOWER_LIST))
//            ))
//    );
    public static final List<BlockState> MB_MEADOW_FLOWER_LIST = List.of(
            Blocks.TALL_GRASS.getDefaultState(),
            Blocks.ALLIUM.getDefaultState(),
            Blocks.POPPY.getDefaultState(),
            Blocks.AZURE_BLUET.getDefaultState(),
            Blocks.DANDELION.getDefaultState(),
            Blocks.CORNFLOWER.getDefaultState(),
            Blocks.OXEYE_DAISY.getDefaultState(),
            MBBlocks.BUTTERCUP.getDefaultState(),
            MBBlocks.FORGETMENOT.getDefaultState(),
            Blocks.GRASS.getDefaultState()
    );
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MB_MEADOW_FLOWERS = MBConfiguredFeatures.register("mb_meadow_flowers", Feature.FLOWER, new RandomPatchFeatureConfig(96, 6, 2,
			PlacedFeatureUtil.placedInline(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(new Range<>(1, 3),
                    new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0), 1.0f, 2345L,
                    new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0), 1.0f, MB_MEADOW_FLOWER_LIST)
            ))
    ));

    private static RandomPatchFeatureConfig createPatch(Block block) {
        return ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, Block block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
    public static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block, List<Block> validGround) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block), createBlockPredicate(validGround)));
    }
    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        return !validGround.isEmpty() ? BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.method_38881(new BlockPos(0, -1, 0), validGround)) : BlockPredicate.IS_AIR;
    }
}
