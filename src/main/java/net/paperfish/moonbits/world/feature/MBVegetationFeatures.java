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

import java.util.List;

public class MBVegetationFeatures {
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_CARROT_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_POTATO_PATCH;
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SEA_BEET_PATCH;

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

    public static final RegistryEntry<ConfiguredFeature<CountConfig, ?>> PEBBLES;
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> BOULDER;

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

        WILD_CARROT_PATCH = ConfiguredFeatures.register("patch_carrots", Feature.RANDOM_PATCH, createPatch(MBBlocks.WILD_CARROTS));
        WILD_POTATO_PATCH = ConfiguredFeatures.register("patch_potatoes", Feature.RANDOM_PATCH, createPatch(MBBlocks.WILD_POTATOES));
        SEA_BEET_PATCH = ConfiguredFeatures.register("patch_beets", Feature.RANDOM_PATCH, createPatch(MBBlocks.SEA_BEETS));

        BUTTERCUP_PATCH = ConfiguredFeatures.register("patch_buttercups", Feature.RANDOM_PATCH, createPatch(MBBlocks.BUTTERCUP));
        FORGETMENOT_PATCH = ConfiguredFeatures.register("patch_forgetmenot", Feature.RANDOM_PATCH, createPatch(MBBlocks.FORGETMENOT));
        PUFFBALLS_PATCH = ConfiguredFeatures.register("patch_puffballs", Feature.RANDOM_PATCH, createPatch(MBBlocks.PUFFBALLS));
        TOADSTOOLS = ConfiguredFeatures.register("toadstools", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(TOADSTOOL_LIST))));
        AUTUMN_FLOWERS = ConfiguredFeatures.register("autumn_flowers", Feature.RANDOM_PATCH,
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.LARGE_FERN))));
        ROSE_BUSH = ConfiguredFeatures.register("rose_bush", Feature.RANDOM_PATCH, createPatch(Blocks.ROSE_BUSH));
        SUNFLOWERS = ConfiguredFeatures.register("mb_sunflowers", Feature.RANDOM_PATCH, createPatch(Blocks.SUNFLOWER));

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
                ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.PUMPKIN)), List.of(Blocks.GRASS_BLOCK)));

        PEBBLE_FEATURE = Registry.register(Registry.FEATURE, "pebble_feature", new PebbleFeature(CountConfig.CODEC));
        PEBBLES = ConfiguredFeatures.register("pebbles", PEBBLE_FEATURE, new CountConfig(4));
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
