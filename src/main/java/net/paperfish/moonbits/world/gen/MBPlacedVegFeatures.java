package net.paperfish.moonbits.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Holder;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

import java.util.List;

public class MBPlacedVegFeatures {
    public static final Holder<PlacedFeature> PEBBLES = MBPlacedFeatures.register("p_pebbles",
            MBVegetationFeatures.PEBBLES, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

//    public static final Holder<PlacedFeature> PATCH_MYCELIUM = MBPlacedFeatures.register("p_mycelium", MBVegetationFeatures.PATCH_MYCELIUM,
//            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10), InSquarePlacementModifier.getInstance(),
//			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PATCH_DESERT_BRUSH = MBPlacedFeatures.register("p_desert_brush", MBVegetationFeatures.PATCH_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.create(-0.8, 15, 4), RarityFilterPlacementModifier.create(32),
			InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(120)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> FLOOD_DESERT_BRUSH = MBPlacedFeatures.register("p_flood_desert_brush", MBVegetationFeatures.FLOOD_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(80)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> PATCH_COTTONGRASS = MBPlacedFeatures.register("p_cottongrass", MBVegetationFeatures.COTTONGRASS,
            NoiseThresholdCountPlacementModifier.create(-0.9, 3, 6), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // flower patches
    public static final Holder<PlacedFeature> SOURSOB_PATCH = MBPlacedFeatures.register("p_soursobs",
            MBVegetationFeatures.SOURSOB_PATCH, CountPlacementModifier.create(32), RarityFilterPlacementModifier.create(16), InSquarePlacementModifier.getInstance(),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(32), YOffset.fixed(72)), BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> MARIGOLD_PATCH = MBPlacedFeatures.register("p_marigold",
            MBVegetationFeatures.MARIGOLD_PATCH, CountPlacementModifier.create(1), RarityFilterPlacementModifier.create(16),
			InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(108)), BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FLOOD_MARIGOLD_PATCH = MBPlacedFeatures.register("p_flood_marigold",
            MBVegetationFeatures.FLOOD_MARIGOLD_PATCH, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(78)), BiomePlacementModifier.getInstance());

    // flower sets

    public static final Holder<PlacedFeature> LUPINE = MBPlacedFeatures.register("p_lupine",
            MBVegetationFeatures.LUPINE, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> HEATHER = MBPlacedFeatures.register("p_heather",
            MBVegetationFeatures.HEATHER, CountPlacementModifier.create(6), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    // fungi

    // wild crops
    public static final Holder<PlacedFeature> WILD_CARROT_PATCH = MBPlacedFeatures.register("p_wild_carrots",
            MBVegetationFeatures.WILD_CARROT_PATCH, RarityFilterPlacementModifier.create(32), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> WILD_POTATO_PATCH = MBPlacedFeatures.register("p_wild_potatoes",
            MBVegetationFeatures.WILD_POTATO_PATCH, RarityFilterPlacementModifier.create(32), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> SEA_BEET_PATCH = MBPlacedFeatures.register("p_sea_beets",
            MBVegetationFeatures.SEA_BEET_PATCH, RarityFilterPlacementModifier.create(32), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> BARREL_CACTI = MBPlacedFeatures.register("p_barrel_cacti",
            MBVegetationFeatures.BARREL_CACTI, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(114)), BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FLOOD_BARREL_CACTI = MBPlacedFeatures.register("p_flood_barrel_cacti",
            MBVegetationFeatures.FLOOD_BARREL_CACTI, CountPlacementModifier.create(3), InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(67), YOffset.fixed(72)), BiomePlacementModifier.getInstance());

	public static final Holder<PlacedFeature> OCOTILLO_PATCH = MBPlacedFeatures.register("p_ocotillo",
			MBVegetationFeatures.OCOTILLO_PATCH, CountPlacementModifier.create(4), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static final Holder<PlacedFeature> ORE_PEAT_MOSS = MBPlacedFeatures.register("p_peat_moss",
            MBVegetationFeatures.PATCH_PEAT_MOSS, MBPlacedCaveFeatures.commonOrePlacementModifiers(2,
					HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final Holder<PlacedFeature> ORE_PERMAFROST = MBPlacedFeatures.register("p_frost",
            MBVegetationFeatures.TUNDRA_FROST, MBPlacedCaveFeatures.commonOrePlacementModifiers(12,
					HeightRangePlacementModifier.createUniform(YOffset.fixed(32), YOffset.fixed(128))));


    // trees
    public static final Holder<PlacedFeature> CEDARS = MBPlacedFeatures.register("p_cedars",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatureUtil.createCountExtraModifier(4, 0.1F, 1)));
    public static final Holder<PlacedFeature> PRAIRIE_TREES = MBPlacedFeatures.register("p_prairie",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatureUtil.createCountExtraModifier(1, 0.1F, 1)));
//	public static final Holder<PlacedFeature> DESERT_CAVES = MBPlacedFeatures.register("p_desert_caves",
//			MBVegetationFeatures.CHERT_FLORA,
//			List.of(PlacedFeatureUtil.createCountExtraModifier(4, 0.1F, 1),
//					InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
//					PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance(),
//					HeightRangePlacementModifier.createUniform(YOffset.fixed(24), YOffset.fixed(60))
//			));
//	public static final Holder<PlacedFeature> BADLANDS_HARDY = MBPlacedFeatures.register("p_badlands_hardy",
//			MBVegetationFeatures.HARDY_BUSH,
//			List.of(PlacedFeatureUtil.createCountExtraModifier(3, 0.1F, 1),
//					InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
//					PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance(),
//					HeightRangePlacementModifier.createUniform(YOffset.fixed(24), YOffset.fixed(80))
//			));

    public static final Holder<PlacedFeature> FALLEN_OAK = MBPlacedFeatures.register("p_fallen_oak",
            MBTreeFeatures.FALLEN_OAK, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FALLEN_BIRCH = MBPlacedFeatures.register("p_fallen_birch",
            MBTreeFeatures.FALLEN_BIRCH, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());
    public static final Holder<PlacedFeature> FALLEN_SPRUCE = MBPlacedFeatures.register("p_fallen_spruce",
            MBTreeFeatures.FALLEN_SPRUCE, CountPlacementModifier.create(1), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.getInstance());

    public static void init() {}

	public static List<PlacementModifier> modifiers(PlacementModifier modifier) {
		return List.of(modifier, InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
				PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance());
	}
	public static List<PlacementModifier> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
		return List.of(modifier, InSquarePlacementModifier.getInstance(), SurfaceWaterDepthFilterPlacementModifier.create(0),
				PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.getInstance(),
				BlockPredicateFilterPlacementModifier.create(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)));
	}
}
