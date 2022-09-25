package net.paperfish.moonbits.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.HolderSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Range;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
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
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.OcotilloBlock;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.block.PebbleBlock;
import net.paperfish.moonbits.world.feature.PebbleFeature;
import net.paperfish.moonbits.world.feature.SoursobPatchFeature;

import java.util.List;

public class MBVegetationFeatures {
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_CARROT_PATCH =
            MBConfiguredFeatures.register("patch_carrots", Feature.RANDOM_PATCH, createPatch(64, 7, 2, MBBlocks.WILD_CARROTS));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> WILD_POTATO_PATCH =
            MBConfiguredFeatures.register("patch_potatoes", Feature.RANDOM_PATCH, createPatch(64, 7, 2, MBBlocks.WILD_POTATOES));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SEA_BEET_PATCH =
            MBConfiguredFeatures.register("patch_beets", Feature.RANDOM_PATCH, createPatch(64, 7, 2, MBBlocks.SEA_BEETS));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_MYCELIUM =
            MBConfiguredFeatures.register("patch_mycelium", Feature.RANDOM_PATCH, createPatch(32, BlockStateProvider.of(MBBlocks.MYCELIUM_ROOTS)));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> SOURSOB_PATCH =
            MBConfiguredFeatures.register("patch_soursobs", Feature.RANDOM_PATCH, createPatch(96, MBBlocks.SOURSOBS,
					List.of(MBBlocks.TOUGH_DIRT, MBBlocks.REGOLITH, Blocks.CLAY, Blocks.MOSS_BLOCK)
			));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> CLOVER_PATCH =
            MBConfiguredFeatures.register("patch_clover", Feature.RANDOM_PATCH, createPatch(16, MBBlocks.CLOVER));

	public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> DESERT_VASE_PATCH =
			MBConfiguredFeatures.register("patch_desert_vase", Feature.RANDOM_PATCH,
					createPatch(16, BlockStateProvider.of(MBBlocks.DESERT_VASE), List.of(MBBlocks.CHERT, Blocks.SANDSTONE, MBBlocks.SANDY_SOIL, Blocks.DEEPSLATE)));

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PEBBLES = MBConfiguredFeatures.register("mb_pebbles",
            Feature.RANDOM_PATCH, createPatch(1, 12, 2, new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                    .add(MBBlocks.PEBBLES.getDefaultState(), 3)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 2), 6)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 3), 4)
                    .add(MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, 4), 1)
                    .build())));


    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_DESERT_BRUSH =
            MBConfiguredFeatures.register("patch_desert_brush", Feature.RANDOM_PATCH,
					createPatch(12, 3, 2, BlockStateProvider.of(MBBlocks.DESERT_BRUSH)));
    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> FLOOD_DESERT_BRUSH =
            MBConfiguredFeatures.register("flood_desert_brush", Feature.RANDOM_PATCH,
					createPatch(24, 4, 2, BlockStateProvider.of(MBBlocks.DESERT_BRUSH)));

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
	public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> OCOTILLO_PATCH = MBConfiguredFeatures.register(
			"ocotillo_patch",
			Feature.RANDOM_PATCH,
			ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
					10,
					PlacedFeatureUtil.placedInline(
							Feature.BLOCK_COLUMN,
							BlockColumnFeatureConfig.create(BiasedToBottomIntProvider.create(2, 5),
									new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
									.add(MBBlocks.OCOTILLO.getDefaultState().with(OcotilloBlock.AGE, 12), 4)
									.add(MBBlocks.OCOTILLO.getDefaultState().with(OcotilloBlock.AGE, 15), 6)
									.add(MBBlocks.FLOWERING_OCOTILLO.getDefaultState().with(OcotilloBlock.AGE, 12), 2)
									.add(MBBlocks.FLOWERING_OCOTILLO.getDefaultState().with(OcotilloBlock.AGE, 15), 4)
									.build())
							),
							BlockPredicateFilterPlacementModifier.create(
									BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.wouldSurvive(MBBlocks.OCOTILLO.getDefaultState(), BlockPos.ORIGIN))
							)
					)
			)
	);

    public static final Holder<ConfiguredFeature<RandomPatchFeatureConfig, ?>> COTTONGRASS =
            MBConfiguredFeatures.register("patch_cottongrass", Feature.RANDOM_PATCH, createPatch(12,
					BlockStateProvider.of(MBBlocks.COTTONGRASS)));
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
	public static final List<OreFeatureConfig.Target> SANDY_SOIL = List.of(
			OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.SANDSTONE), MBBlocks.SANDY_SOIL.getDefaultState()),
			OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.CHERT), MBBlocks.SANDY_SOIL.getDefaultState()),
			OreFeatureConfig.createTarget(new BlockMatchRuleTest(MBBlocks.COBBLED_CHERT), MBBlocks.SANDY_SOIL.getDefaultState())
	);
	public static final Holder<ConfiguredFeature<OreFeatureConfig, ?>> PATCH_SANDY_SOIL = MBConfiguredFeatures.register(
			"patch_sandy_soil", Feature.ORE, new OreFeatureConfig(SANDY_SOIL, 64));

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

    public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> BADLANDS_TREES =
			MBConfiguredFeatures.register("badlands_trees", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(
                            new WeightedPlacedFeature(TreePlacedFeatures.OAK_BEES_0002, 0.1F),
                            new WeightedPlacedFeature(MBPlacedTreeFeatures.CEDAR, 0.4F)),
                            TreePlacedFeatures.OAK_BEES_002
                    ));

	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> CHERT_FLORA =
			MBConfiguredFeatures.register("chert_flora", Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(List.of(
					new WeightedPlacedFeature(MBPlacedTreeFeatures.HARDY_BUSH, 0.1F),
					new WeightedPlacedFeature(MBPlacedTreeFeatures.PARASOL_FERN, 0.4F)),
					MBPlacedTreeFeatures.PARASOL_FERN
			));
	public static final Holder<ConfiguredFeature<RandomFeatureConfig, ?>> HARDY_BUSH =
			MBConfiguredFeatures.register("hardy_badlands", Feature.RANDOM_SELECTOR,
			new RandomFeatureConfig(List.of(
					new WeightedPlacedFeature(MBPlacedTreeFeatures.HARDY_BUSH, 0.1F),
					new WeightedPlacedFeature(MBPlacedTreeFeatures.CEDAR, 0.4F)),
					MBPlacedTreeFeatures.HARDY_BUSH
			));

    private static RandomPatchFeatureConfig createPatch(Block block) {
        return ConfiguredFeatureUtil.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(BlockStateProvider.of(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, Block block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(block)));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, Block block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(BlockStateProvider.of(block))));
    }
    private static RandomPatchFeatureConfig createPatch(int tries, int xzSpread, int ySpread, BlockStateProvider block) {
        return new RandomPatchFeatureConfig(tries, xzSpread, ySpread, PlacedFeatureUtil.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(block)));
    }
    public static RandomPatchFeatureConfig createPatch(int tries, BlockStateProvider block, List<Block> validGround) {
        return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.filtered(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(block), createBlockPredicate(validGround)));
    }
	public static RandomPatchFeatureConfig createPatch(int tries, Block block, List<Block> validGround) {
		return new RandomPatchFeatureConfig(tries, 7, 3, PlacedFeatureUtil.filtered(Feature.SIMPLE_BLOCK,
				new SimpleBlockFeatureConfig(BlockStateProvider.of(block)), createBlockPredicate(validGround)));
	}
    private static BlockPredicate createBlockPredicate(List<Block> validGround) {
        return !validGround.isEmpty() ? BlockPredicate.bothOf(BlockPredicate.IS_AIR,
				BlockPredicate.method_38881(new BlockPos(0, -1, 0), validGround)) : BlockPredicate.IS_AIR;
    }
}
