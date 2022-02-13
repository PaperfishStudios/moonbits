package net.paperfish.moonbits.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.paperfish.moonbits.MBBlocks;

import java.util.List;

public class MBVegetationFeatures {
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> WILD_CARROT_PATCH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> WILD_POTATO_PATCH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> SEA_BEET_PATCH;

    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> BUTTERCUP_PATCH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> FORGETMENOT_PATCH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> PUFFBALLS_PATCH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> TOADSTOOLS;
    public static final DataPool<BlockState> TOADSTOOL_LIST;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> AUTUMN_FLOWERS;
    public static final DataPool<BlockState> AUTUMN_FLOWER_LIST;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> ROSE_BUSH;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> SUNFLOWERS;

    public static final ConfiguredFeature<SimpleRandomFeatureConfig, ?> HYACINTHS;

    public static final Feature<CountConfig> PEBBLE_FEATURE;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> PUMPKIN_PATCH;

    public static final ConfiguredFeature<?, ?> PEBBLES;
    public static final ConfiguredFeature<?, ?> BOULDER;

    public static final ConfiguredFeature<ReplaceBlobsFeatureConfig, ?> COARSE_DIRT_PATCH;
    public static final ConfiguredFeature<ReplaceBlobsFeatureConfig, ?> PODZOL_PATCH;
    public static final ConfiguredFeature<ReplaceBlobsFeatureConfig, ?> LEAFBED_PATCH;

    public static final ConfiguredFeature<RandomFeatureConfig, ?> GBF_TREES;

    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> MBFF_FLOWERS;
    public static final List<BlockState> MBFF_FLOWER_LIST;
    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> MB_MEADOW_FLOWERS;
    public static final List<BlockState> MB_MEADOW_FLOWER_LIST;

    private static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    public static RandomPatchFeatureConfig createPatch(int tries, PlacedFeature feature) {
        return new RandomPatchFeatureConfig(tries, 7, 3, () -> {
            return feature;
        });
    }

    public static RandomPatchFeatureConfig createPatch(int tries, ConfiguredFeature<?, ?> feature, List<Block> validGround) {
        return createPatch(tries, feature.withBlockPredicateFilter(createBlockPredicate(validGround)));
    }

    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        BlockPredicate blockPredicate;
        if (!validGround.isEmpty()) {
            blockPredicate = BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.matchingBlocks(validGround, new BlockPos(0, -1, 0)));
        } else {
            blockPredicate = BlockPredicate.IS_AIR;
        }

        return blockPredicate;
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
                .add(MBBlocks.TOADSTOOL.getDefaultState(), 3)
                .add(MBBlocks.SMALL_TOADSTOOLS.getDefaultState(), 2)
                .build();
        AUTUMN_FLOWER_LIST = new DataPool.Builder<BlockState>()
                .add(Blocks.OXEYE_DAISY.getDefaultState(), 4)
                .add(Blocks.DANDELION.getDefaultState(), 2)
                .add(Blocks.CORNFLOWER.getDefaultState(), 1)
                .add(MBBlocks.FORGETMENOT.getDefaultState(), 2)
                .add(Blocks.POPPY.getDefaultState(), 1)
                .build();

        WILD_CARROT_PATCH = ConfiguredFeatures.register("patch_carrots", Feature.RANDOM_PATCH.configure(createPatch(6, BlockStateProvider.of(MBBlocks.WILD_CARROTS))));
        WILD_POTATO_PATCH = ConfiguredFeatures.register("patch_potatoes", Feature.RANDOM_PATCH.configure(createPatch(6, BlockStateProvider.of(MBBlocks.WILD_POTATOES))));
        SEA_BEET_PATCH = ConfiguredFeatures.register("patch_beets", Feature.RANDOM_PATCH.configure(createPatch(32, BlockStateProvider.of(MBBlocks.SEA_BEETS))));

        BUTTERCUP_PATCH = ConfiguredFeatures.register("patch_buttercups", Feature.RANDOM_PATCH.configure(createPatch(32, BlockStateProvider.of(MBBlocks.BUTTERCUP))));
        FORGETMENOT_PATCH = ConfiguredFeatures.register("patch_forgetmenot", Feature.RANDOM_PATCH.configure(createPatch(16, BlockStateProvider.of(MBBlocks.FORGETMENOT))));
        PUFFBALLS_PATCH = ConfiguredFeatures.register("patch_puffballs", Feature.RANDOM_PATCH.configure(createPatch(8, BlockStateProvider.of(MBBlocks.PUFFBALLS))));
        TOADSTOOLS = ConfiguredFeatures.register("toadstools", Feature.FLOWER.configure(createPatch(3, new WeightedBlockStateProvider(TOADSTOOL_LIST))));
        AUTUMN_FLOWERS = ConfiguredFeatures.register("autumn_flowers", Feature.FLOWER.configure(createPatch(12, new WeightedBlockStateProvider(AUTUMN_FLOWER_LIST))));
        ROSE_BUSH = ConfiguredFeatures.register("rose_bush", Feature.RANDOM_PATCH.configure(createPatch(8, BlockStateProvider.of(Blocks.ROSE_BUSH))));
        SUNFLOWERS = ConfiguredFeatures.register("mb_sunflowers", Feature.RANDOM_PATCH.configure(createPatch(6, BlockStateProvider.of(Blocks.SUNFLOWER))));

        HYACINTHS = ConfiguredFeatures.register("hyacinths", Feature.SIMPLE_RANDOM_SELECTOR.configure(new SimpleRandomFeatureConfig(List.of(
                () -> Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.WHITE_HYACINTH))))).withPlacement(new PlacementModifier[0]),
                () -> Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.PINK_HYACINTH))))).withPlacement(new PlacementModifier[0]),
                () -> Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.LIGHT_BLUE_HYACINTH))))).withPlacement(new PlacementModifier[0]),
                () -> Feature.RANDOM_PATCH.configure(ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK.configure(
                        new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.RED_HYACINTH))))).withPlacement(new PlacementModifier[0]))
        )));

        PUMPKIN_PATCH = ConfiguredFeatures.register("mb_pumpkin_patch", Feature.RANDOM_PATCH.configure(createPatch(8, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.PUMPKIN))), List.of(Blocks.GRASS_BLOCK))));

        PEBBLE_FEATURE = Registry.register(Registry.FEATURE, "pebble_feature", new PebbleFeature(CountConfig.CODEC));
        PEBBLES = ConfiguredFeatures.register("pebbles", PEBBLE_FEATURE.configure(new CountConfig(4)));
        BOULDER = ConfiguredFeatures.register("stone_boulder", Feature.GEODE.configure(
                new GeodeFeatureConfig(new GeodeLayerConfig(
                        BlockStateProvider.of(Blocks.STONE),
                        BlockStateProvider.of(Blocks.STONE),
                        BlockStateProvider.of(Blocks.STONE),
                        BlockStateProvider.of(Blocks.STONE),
                        BlockStateProvider.of(Blocks.STONE),
                        List.of(Blocks.STONE.getDefaultState()),
                        BlockTags.FEATURES_CANNOT_REPLACE.getId(), BlockTags.GEODE_INVALID_BLOCKS.getId()),
                        new GeodeLayerThicknessConfig(0.5, 1, 1.25, 1.96),
                        new GeodeCrackConfig(0, 0, 0),
                        0.35, 0.083, true,
                        UniformIntProvider.create(4, 6), UniformIntProvider.create(3, 4), UniformIntProvider.create(1, 2), -16, 16, 0.05, 1)));

        COARSE_DIRT_PATCH = ConfiguredFeatures.register("coarse_dirt_floor", Feature.NETHERRACK_REPLACE_BLOBS.configure(
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        Blocks.COARSE_DIRT.getDefaultState(),
                        UniformIntProvider.create(2, 4)
                )));
        PODZOL_PATCH = ConfiguredFeatures.register("podzol_floor", Feature.NETHERRACK_REPLACE_BLOBS.configure(
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        Blocks.PODZOL.getDefaultState(),
                        UniformIntProvider.create(2, 5)
                )));
        LEAFBED_PATCH = ConfiguredFeatures.register("leafbed_floor", Feature.NETHERRACK_REPLACE_BLOBS.configure(
                new ReplaceBlobsFeatureConfig(
                        Blocks.GRASS_BLOCK.getDefaultState(),
                        MBBlocks.LEAFBED.getDefaultState(),
                        UniformIntProvider.create(3, 5)
                )));

        GBF_TREES = ConfiguredFeatures.register("gbf_trees", Feature.RANDOM_SELECTOR.configure(
                new RandomFeatureConfig(List.of(
                        new RandomFeatureEntry(TreePlacedFeatures.OAK_BEES_002, 0.1F),
                        new RandomFeatureEntry(MBPlacedTreeFeatures.RED_OAK_BEES_005, 0.4F)),
                        MBPlacedTreeFeatures.GOLDEN_BIRCH_BEES_005
                )));

        MBFF_FLOWERS = ConfiguredFeatures.register("mbff_flowers", Feature.FLOWER.configure(new RandomPatchFeatureConfig(96, 6, 2, () -> {
            return Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2345L,
                            new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D, new double[0]), 0.020833334F, MBFF_FLOWER_LIST)))
                    .withInAirFilter();
        })));
        MB_MEADOW_FLOWERS = ConfiguredFeatures.register("mb_meadow_flowers", Feature.FLOWER.configure(new RandomPatchFeatureConfig(96, 6, 2, () -> {
            return Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(new Range<>(1, 3),
                    new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0D, new double[0]), 1.0F, 2345L,
                    new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0D, new double[0]), 1.0F, MB_MEADOW_FLOWER_LIST)))
                    .withInAirFilter();
        })));
    }
}
