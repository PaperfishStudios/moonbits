package net.paperfish.moonbits.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.block.BarrelCactusBlock;
import net.paperfish.moonbits.block.PebbleBlock;

import java.util.List;

public class MBVegetationFeatures {
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_CARROT_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_POTATO_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SEA_BEET_PATCH;

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_MYCELIUM =
            ConfiguredFeatures.register("patch_mycelium", Feature.RANDOM_PATCH, createPatch(32, BlockStateProvider.of(MBBlocks.MYCELIUM_ROOTS)));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BUTTERCUP_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FORGETMENOT_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PUFFBALLS_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> TOADSTOOLS;
    public static final DataPool<BlockState> TOADSTOOL_LIST;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> AUTUMN_FLOWERS;
    public static final DataPool<BlockState> AUTUMN_FLOWER_LIST;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> ROSE_BUSH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SUNFLOWERS;

    public static final RegistryEntry<ConfiguredFeature<SimpleRandomFeatureConfig, ?>> HYACINTHS;

    public static final Feature<CountConfig> PEBBLE_FEATURE;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PUMPKIN_PATCH;

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEBBLES = ConfiguredFeatures.register("mb_pebbles",
            Feature.RANDOM_PATCH, createPatch(1, 12, 2, new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                    .add(MBBlocks.PEBBLES.getDefaultState(), 3)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 2), 6)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 3), 4)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 4), 1)
                    .build())));
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BOULDER;

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DESERT_BRUSH =
            ConfiguredFeatures.register("patch_desert_brush", Feature.RANDOM_PATCH, createPatch(12, BlockStateProvider.of(MBBlocks.DESERT_BRUSH)));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MARIGOLD_PATCH = ConfiguredFeatures.register(
            "patch_marigold", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.MARIGOLD));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> BARREL_CACTI = ConfiguredFeatures.register(
            "barrel_cacti", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(6, 6, 2,
                    PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(MBBlocks.BARREL_CACTUS.getDefaultState(), 4)
                            .add(MBBlocks.BARREL_CACTUS.getDefaultState().with(BarrelCactusBlock.LEVEL, 2), 6)
                            .add(MBBlocks.BARREL_CACTUS.getDefaultState().with(BarrelCactusBlock.LEVEL, 3), 1)
                            .build())))));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> COTTONGRASS =
            ConfiguredFeatures.register("patch_cottongrass", Feature.RANDOM_PATCH, createPatch(12, BlockStateProvider.of(MBBlocks.COTTONGRASS)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LUPINE = ConfiguredFeatures.register(
            "patch_lupine", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.LUPINE));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> HEATHER = ConfiguredFeatures.register(
            "heather", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(9, 2, 2,
                    PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(MBBlocks.PURPLE_HEATHER.getDefaultState(), 8)
                            .add(MBBlocks.RED_HEATHER.getDefaultState(), 5)
                            .add(MBBlocks.ORANGE_HEATHER.getDefaultState(), 6)
                            .add(MBBlocks.WHITE_HEATHER.getDefaultState(), 1)
                            .build())))));

    public static final List<OreFeatureConfig.Target> PERMAFROST = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.PERMAFROST.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.PERMAFROST.getDefaultState())
    );
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TUNDRA_FROST = ConfiguredFeatures.register(
            "tundra_frost", Feature.ORE, new OreFeatureConfig(PERMAFROST, 48));

    public static final RegistryEntry<ConfiguredFeature<SingleStateFeatureConfig, ?>> TILL_ROCK = ConfiguredFeatures.register(
            "till_rock", Feature.FOREST_ROCK, new SingleStateFeatureConfig(MBBlocks.TILL.getDefaultState()));

    public static final List<OreFeatureConfig.Target> CRACKED_MUD = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.CRACKED_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.CRACKED_MUD.getDefaultState())
    );
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_CRACKED_MUD = ConfiguredFeatures.register(
            "patch_cracked_mud", Feature.ORE, new OreFeatureConfig(CRACKED_MUD, 32));

    public static final List<OreFeatureConfig.Target> RICH_MUD = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.RICH_MUD.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.RICH_MUD.getDefaultState())
    );
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_RICH_MUD = ConfiguredFeatures.register(
            "patch_rich_mud", Feature.ORE, new OreFeatureConfig(RICH_MUD, 32));

    public static final List<OreFeatureConfig.Target> PEAT_MOSS = List.of(
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.GRASS_BLOCK), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.DIRT), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT), MBBlocks.PEAT_MOSS.getDefaultState()),
            OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SAND), MBBlocks.PEAT_MOSS.getDefaultState())
    );
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_PEAT_MOSS = ConfiguredFeatures.register(
            "patch_peat_moss", Feature.ORE, new OreFeatureConfig(PEAT_MOSS, 14));

    public static final RegistryEntry<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> COARSE_DIRT_PATCH;
    public static final RegistryEntry<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> PODZOL_PATCH;
    public static final RegistryEntry<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> LEAFBED_PATCH;

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> GBF_TREES;

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MBFF_FLOWERS;
    public static final List<BlockState> MBFF_FLOWER_LIST;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MB_MEADOW_FLOWERS;
    public static final List<BlockState> MB_MEADOW_FLOWER_LIST;

    private static RandomPatchFeatureConfig createPatch(Block block) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, Block block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }
    public static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block, List<Block> validGround) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block), createBlockPredicate(validGround)));
    }
    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        return !validGround.isEmpty() ? BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.matchingBlocks(validGround, new BlockPos(0, -1, 0))) : BlockPredicate.IS_AIR;
    }

    static {
        // List of flowers that generate as the flower forest's funky flower pattern (currently unchanged but i have it here now ig)
        MBFF_FLOWER_LIST = List.of(
                Blocks.DANDELION.getDefaultState(),
                Blocks.POPPY.getDefaultState(),
                MBBlocks.FORGETMENOT.getDefaultState(),
                Blocks.ALLIUM.getDefaultState(),
                Blocks.AZURE_BLUET.getDefaultState(),
                Blocks.RED_TULIP.getDefaultState(),
                Blocks.ORANGE_TULIP.getDefaultState(),
                Blocks.WHITE_TULIP.getDefaultState(),
                Blocks.PINK_TULIP.getDefaultState(),
                Blocks.OXEYE_DAISY.getDefaultState(),
                Blocks.CORNFLOWER.getDefaultState(),
                MBBlocks.BUTTERCUP.getDefaultState(),
                Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                MBBlocks.BUTTERCUP.getDefaultState()
        );
        MB_MEADOW_FLOWER_LIST = List.of(
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
        TOADSTOOL_LIST = new DataPool.Builder<BlockState>()
                .add(MBBlocks.TOADSTOOL.getDefaultState(), 1)
                .add(MBBlocks.SMALL_TOADSTOOLS.getDefaultState(), 4)
                .build();
        AUTUMN_FLOWER_LIST = new DataPool.Builder<BlockState>()
                .add(Blocks.OXEYE_DAISY.getDefaultState(), 4)
                .add(Blocks.DANDELION.getDefaultState(), 2)
                .add(Blocks.CORNFLOWER.getDefaultState(), 1)
                .add(MBBlocks.FORGETMENOT.getDefaultState(), 2)
                .add(Blocks.POPPY.getDefaultState(), 1)
                .build();

        WILD_CARROT_PATCH = ConfiguredFeatures.register("patch_carrots", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.WILD_CARROTS));
        WILD_POTATO_PATCH = ConfiguredFeatures.register("patch_potatoes", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.WILD_POTATOES));
        SEA_BEET_PATCH = ConfiguredFeatures.register("patch_beets", Feature.RANDOM_PATCH, createPatch(6, MBBlocks.SEA_BEETS));

        BUTTERCUP_PATCH = ConfiguredFeatures.register("patch_buttercups", Feature.RANDOM_PATCH, createPatch(32, MBBlocks.BUTTERCUP));
        FORGETMENOT_PATCH = ConfiguredFeatures.register("patch_forgetmenot", Feature.RANDOM_PATCH, createPatch(16, MBBlocks.FORGETMENOT));
        PUFFBALLS_PATCH = ConfiguredFeatures.register("patch_puffballs", Feature.RANDOM_PATCH, createPatch(8, MBBlocks.PUFFBALLS));
        TOADSTOOLS = ConfiguredFeatures.register("toadstools", Feature.RANDOM_PATCH, createPatch(3, new WeightedBlockStateProvider(TOADSTOOL_LIST)));
        AUTUMN_FLOWERS = ConfiguredFeatures.register("autumn_flowers", Feature.RANDOM_PATCH, createPatch(12, new WeightedBlockStateProvider(AUTUMN_FLOWER_LIST)));
        ROSE_BUSH = ConfiguredFeatures.register("rose_bush", Feature.RANDOM_PATCH, createPatch(8, Blocks.ROSE_BUSH));
        SUNFLOWERS = ConfiguredFeatures.register("mb_sunflowers", Feature.RANDOM_PATCH, createPatch(6, Blocks.SUNFLOWER));

        HYACINTHS = ConfiguredFeatures.register("hyacinths", Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfig(RegistryEntryList.of(
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, (ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.WHITE_HYACINTH)))))),
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, (ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.PINK_HYACINTH)))))),
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, (ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.LIGHT_BLUE_HYACINTH)))))),
                PlacedFeatures.createEntry(Feature.RANDOM_PATCH, (ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, (
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.RED_HYACINTH))))))
        )));

        PUMPKIN_PATCH = ConfiguredFeatures.register("mb_pumpkin_patch", Feature.RANDOM_PATCH,
                createPatch(8, BlockStateProvider.of(Blocks.PUMPKIN), List.of(Blocks.GRASS_BLOCK)));

        PEBBLE_FEATURE = Registry.register(Registry.FEATURE, "pebble_feature", new PebbleFeature(CountConfig.CODEC));
        //PEBBLES = ConfiguredFeatures.register("pebbles", PEBBLE_FEATURE, new CountConfig(4));
        BOULDER = ConfiguredFeatures.register("stone_boulder", Feature.GEODE,
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

        COARSE_DIRT_PATCH = ConfiguredFeatures.register("coarse_dirt_floor", Feature.NETHERRACK_REPLACE_BLOBS,
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        Blocks.COARSE_DIRT.getDefaultState(),
                        UniformIntProvider.create(2, 4)
                ));
        PODZOL_PATCH = ConfiguredFeatures.register("podzol_floor", Feature.NETHERRACK_REPLACE_BLOBS,
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        Blocks.PODZOL.getDefaultState(),
                        UniformIntProvider.create(2, 5)
                ));
        LEAFBED_PATCH = ConfiguredFeatures.register("leafbed_floor", Feature.NETHERRACK_REPLACE_BLOBS,
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        MBBlocks.LEAFBED.getDefaultState(),
                        UniformIntProvider.create(3, 5)
                ));

        GBF_TREES = ConfiguredFeatures.register("gbf_trees", Feature.RANDOM_SELECTOR,
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(TreePlacedFeatures.OAK_BEES_002, 0.1F),
                        new RandomFeatureEntry(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.4F)),
                        MBPlacedTreeFeatures.GOLDEN_BIRCH_BEES_005
                ));

        MBFF_FLOWERS = ConfiguredFeatures.register("mbff_flowers", Feature.FLOWER, new RandomPatchFeatureConfig(96, 6, 2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L,
                            new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D), 0.020833334F, MBFF_FLOWER_LIST))
                ))
        );

        MB_MEADOW_FLOWERS = ConfiguredFeatures.register("mb_meadow_flowers", Feature.FLOWER, new RandomPatchFeatureConfig(96, 6, 2,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(new Range<>(1, 3),
                        new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0), 1.0f, 2345L,
                        new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0), 1.0f,MB_MEADOW_FLOWER_LIST)
                ))
        ));

    }
}
