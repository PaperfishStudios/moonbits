package net.paperfish.moonbits.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

import java.util.List;

public class MBPlacedVegFeatures {
    public static final Holder<PlacedFeature> PEBBLES = MBPlacedFeatures.register("p_pebbles",
            MBVegetationFeatures.PEBBLES, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PATCH_MYCELIUM = MBPlacedFeatures.register("p_mycelium", MBVegetationFeatures.PATCH_MYCELIUM,
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PATCH_DESERT_BRUSH = MBPlacedFeatures.register("p_desert_brush", MBVegetationFeatures.PATCH_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4), RarityFilterPlacementModifier.create(32), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(120)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> FLOOD_DESERT_BRUSH = MBPlacedFeatures.register("p_flood_desert_brush", MBVegetationFeatures.FLOOD_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(80)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PATCH_COTTONGRASS = MBPlacedFeatures.register("p_cottongrass", MBVegetationFeatures.COTTONGRASS,
            NoiseThresholdCountPlacementModifier.create(-0.9, 3, 6), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // flower patches
    public static final Holder<PlacedFeature> BUTTERCUP_PATCH = MBPlacedFeatures.register("p_buttercups",
            MBVegetationFeatures.BUTTERCUP_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FORGETMENOT_PATCH = MBPlacedFeatures.register("p_forgetmenot",
            MBVegetationFeatures.FORGETMENOT_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> WILDFLOWER_PATCH = MBPlacedFeatures.register("p_wildflower",
            MBVegetationFeatures.WILDFLOWER_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> CLOVER_PATCH = MBPlacedFeatures.register("p_clover",
            MBVegetationFeatures.CLOVER_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> MARIGOLD_PATCH = MBPlacedFeatures.register("p_marigold",
            MBVegetationFeatures.MARIGOLD_PATCH, CountPlacementModifier.create(1), RarityFilterPlacementModifier.create(16), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(108)), BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FLOOD_MARIGOLD_PATCH = MBPlacedFeatures.register("p_flood_marigold",
            MBVegetationFeatures.FLOOD_MARIGOLD_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(78)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> ROSE_BUSH = MBPlacedFeatures.register("p_rose_bushes",
            MBVegetationFeatures.ROSE_BUSH, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> SUNFLOWERS = MBPlacedFeatures.register("p_sunflowers",
            MBVegetationFeatures.SUNFLOWERS, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> HYACINTHS = MBPlacedFeatures.register("p_hyacinths",
            MBVegetationFeatures.HYACINTHS, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // flower sets
    public static final Holder<PlacedFeature> AUTUMN_FLOWERS = MBPlacedFeatures.register("p_autumn_flowers",
            MBVegetationFeatures.AUTUMN_FLOWERS, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> MB_MEADOW_FLOWERS = MBPlacedFeatures.register("p_meadow_flowers",
            MBVegetationFeatures.MB_MEADOW_FLOWERS, InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> MBFF_FLOWERS = MBPlacedFeatures.register("p_mbff_flowers",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.create(7), RarityFilterPlacementModifier.create(2),
            InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> LUPINE = MBPlacedFeatures.register("p_lupine",
            MBVegetationFeatures.LUPINE, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> HEATHER = MBPlacedFeatures.register("p_heather",
            MBVegetationFeatures.HEATHER, CountPlacementModifier.create(6), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // fungi
    public static final Holder<PlacedFeature> PUFFBALLS_PATCH = MBPlacedFeatures.register("p_puffballs",
            MBVegetationFeatures.PUFFBALLS_PATCH, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> TOADSTOOLS = MBPlacedFeatures.register("p_toadstools",
            MBVegetationFeatures.TOADSTOOLS, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // wild crops
    public static final Holder<PlacedFeature> WILD_CARROT_PATCH = MBPlacedFeatures.register("p_wild_carrots",
            MBVegetationFeatures.WILD_CARROT_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> WILD_POTATO_PATCH = MBPlacedFeatures.register("p_wild_potatoes",
            MBVegetationFeatures.WILD_POTATO_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> SEA_BEET_PATCH = MBPlacedFeatures.register("p_sea_beets",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PUMPKIN_PATCH = MBPlacedFeatures.register("p_pumpkins",
            MBVegetationFeatures.PUMPKIN_PATCH, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> BARREL_CACTI = MBPlacedFeatures.register("p_barrel_cacti",
            MBVegetationFeatures.BARREL_CACTI, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(114)), BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FLOOD_BARREL_CACTI = MBPlacedFeatures.register("p_flood_barrel_cacti",
            MBVegetationFeatures.FLOOD_BARREL_CACTI, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(67), YOffset.fixed(72)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> ORE_CRACKED_MUD = MBPlacedFeatures.register("p_cracked_mud",
            MBVegetationFeatures.PATCH_CRACKED_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final Holder<PlacedFeature> ORE_RICH_MUD = MBPlacedFeatures.register("p_rich_mud",
            MBVegetationFeatures.PATCH_RICH_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> ORE_PEAT_MOSS = MBPlacedFeatures.register("p_peat_moss",
            MBVegetationFeatures.PATCH_PEAT_MOSS, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final Holder<PlacedFeature> ORE_PERMAFROST = MBPlacedFeatures.register("p_frost",
            MBVegetationFeatures.TUNDRA_FROST, MBPlacedCaveFeatures.modifiersWithCount(12, HeightRangePlacementModifier.createUniform(YOffset.fixed(32), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> TILL_ROCK = MBPlacedFeatures.register("p_till_rock",
            MBVegetationFeatures.TILL_ROCK, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());


    // trees
    public static final Holder<PlacedFeature> GOLDEN_BIRCH_BEES_0002 = MBPlacedFeatures.register("p_gb_0002",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatureUtil.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));
    public static final Holder<PlacedFeature> SUPER_GOLDEN_BIRCH_BEES_0002 = MBPlacedFeatures.register("p_sgb_0002",
            MBTreeFeatures.SUPER_GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatureUtil.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));

    public static final Holder<PlacedFeature> GBF_TREES = MBPlacedFeatures.register("p_gbf_trees",
            MBVegetationFeatures.GBF_TREES, modifiers(PlacedFeatureUtil.createCountExtraModifier(6, 0.1F, 1)));
    public static final Holder<PlacedFeature> PASTURE_TREES = MBPlacedFeatures.register("p_pasture_trees",
            MBVegetationFeatures.PASTURE_TREES, modifiers(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1)));
    public static final Holder<PlacedFeature> RED_OAK_TREES = MBPlacedFeatures.register("p_red_oak_trees",
            MBVegetationFeatures.RED_OAK_TREES, modifiers(PlacedFeatureUtil.createCountExtraModifier(6, 0.1F, 1)));

    public static final Holder<PlacedFeature> CEDARS = MBPlacedFeatures.register("p_cedars",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatureUtil.createCountExtraModifier(4, 0.1F, 1)));
    public static final Holder<PlacedFeature> PRAIRIE_TREES = MBPlacedFeatures.register("p_prairie",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1)));
	public static final Holder<PlacedFeature> DESERT_CAVES = MBPlacedFeatures.register("p_desert_caves",
			MBVegetationFeatures.CHERT_FLORA,
			List.of(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1),
					InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
					PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance(),
					HeightRangePlacementModifier.createUniform(YOffset.fixed(24), YOffset.fixed(60))
			));
//    public static final Holder<PlacedFeature> JUNIPERS = MBPlacedFeatures.register("p_junipers",
//            MBVegetationFeatures.JUNIPER_TREES,
//            PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1), InSquarePlacementModifier.getInstance(),
//            SurfaceWaterDepthFilterPlacementModifier.of(0), RarityFilterPlacementModifier.of(16),
//            HeightRangePlacementModifier.trapezoid(YOffset.fixed(68), YOffset.fixed(78)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> FALLEN_OAK = MBPlacedFeatures.register("p_fallen_oak",
            MBTreeFeatures.FALLEN_OAK, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FALLEN_BIRCH = MBPlacedFeatures.register("p_fallen_birch",
            MBTreeFeatures.FALLEN_BIRCH, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FALLEN_SPRUCE = MBPlacedFeatures.register("p_fallen_spruce",
            MBTreeFeatures.FALLEN_SPRUCE, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // soil patches
    public static final Holder<PlacedFeature> COARSE_DIRT_PATCH = MBPlacedFeatures.register("p_coarse_dirt_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> PODZOL_PATCH = MBPlacedFeatures.register("p_podzol_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> LEAFBED_PATCH = MBPlacedFeatures.register("p_leafbed_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> COARSE_DIRT_FLOOR = MBPlacedFeatures.register("p_coarse_dirt_floor",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.create(32), InSquarePlacementModifier.getInstance(), PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE, BiomePlacementModifier.getInstance());

    public static void init() {}

	public static List<PlacementModifier> modifiers(PlacementModifier modifier) {
		return List.of(modifier, InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0), PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance());
	}
	public static List<PlacementModifier> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
		return List.of(modifier, InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
				PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance(), BlockPredicateFilterPlacementModifier.create(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)));
	}
}
