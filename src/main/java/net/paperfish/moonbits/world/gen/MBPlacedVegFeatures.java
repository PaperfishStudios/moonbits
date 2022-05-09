package net.paperfish.moonbits.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class MBPlacedVegFeatures {
    public static final RegistryEntry<PlacedFeature> PEBBLES = MBPlacedFeatures.register("p_pebbles",
            MBVegetationFeatures.PEBBLES, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_MYCELIUM = MBPlacedFeatures.register("p_mycelium", MBVegetationFeatures.PATCH_MYCELIUM,
            NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_DESERT_BRUSH = MBPlacedFeatures.register("p_desert_brush", MBVegetationFeatures.PATCH_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.of(-0.8, 15, 4), RarityFilterPlacementModifier.of(32), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(120)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> FLOOD_DESERT_BRUSH = MBPlacedFeatures.register("p_flood_desert_brush", MBVegetationFeatures.FLOOD_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(80)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_COTTONGRASS = MBPlacedFeatures.register("p_cottongrass", MBVegetationFeatures.COTTONGRASS,
            NoiseThresholdCountPlacementModifier.of(-0.9, 3, 6), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    // flower patches
    public static final RegistryEntry<PlacedFeature> BUTTERCUP_PATCH = MBPlacedFeatures.register("p_buttercups",
            MBVegetationFeatures.BUTTERCUP_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FORGETMENOT_PATCH = MBPlacedFeatures.register("p_forgetmenot",
            MBVegetationFeatures.FORGETMENOT_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> WILDFLOWER_PATCH = MBPlacedFeatures.register("p_wildflower",
            MBVegetationFeatures.WILDFLOWER_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> CLOVER_PATCH = MBPlacedFeatures.register("p_clover",
            MBVegetationFeatures.CLOVER_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MARIGOLD_PATCH = MBPlacedFeatures.register("p_marigold",
            MBVegetationFeatures.MARIGOLD_PATCH, CountPlacementModifier.of(1), RarityFilterPlacementModifier.of(16), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(108)), BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FLOOD_MARIGOLD_PATCH = MBPlacedFeatures.register("p_flood_marigold",
            MBVegetationFeatures.FLOOD_MARIGOLD_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(78)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ROSE_BUSH = MBPlacedFeatures.register("p_rose_bushes",
            MBVegetationFeatures.ROSE_BUSH, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> SUNFLOWERS = MBPlacedFeatures.register("p_sunflowers",
            MBVegetationFeatures.SUNFLOWERS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> HYACINTHS = MBPlacedFeatures.register("p_hyacinths",
            MBVegetationFeatures.HYACINTHS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // flower sets
    public static final RegistryEntry<PlacedFeature> AUTUMN_FLOWERS = MBPlacedFeatures.register("p_autumn_flowers",
            MBVegetationFeatures.AUTUMN_FLOWERS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MB_MEADOW_FLOWERS = MBPlacedFeatures.register("p_meadow_flowers",
            MBVegetationFeatures.MB_MEADOW_FLOWERS, SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MBFF_FLOWERS = MBPlacedFeatures.register("p_mbff_flowers",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.of(7), RarityFilterPlacementModifier.of(2),
            SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LUPINE = MBPlacedFeatures.register("p_lupine",
            MBVegetationFeatures.LUPINE, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> HEATHER = MBPlacedFeatures.register("p_heather",
            MBVegetationFeatures.HEATHER, CountPlacementModifier.of(6), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // fungi
    public static final RegistryEntry<PlacedFeature> PUFFBALLS_PATCH = MBPlacedFeatures.register("p_puffballs",
            MBVegetationFeatures.PUFFBALLS_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> TOADSTOOLS = MBPlacedFeatures.register("p_toadstools",
            MBVegetationFeatures.TOADSTOOLS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // wild crops
    public static final RegistryEntry<PlacedFeature> WILD_CARROT_PATCH = MBPlacedFeatures.register("p_wild_carrots",
            MBVegetationFeatures.WILD_CARROT_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> WILD_POTATO_PATCH = MBPlacedFeatures.register("p_wild_potatoes",
            MBVegetationFeatures.WILD_POTATO_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> SEA_BEET_PATCH = MBPlacedFeatures.register("p_sea_beets",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PUMPKIN_PATCH = MBPlacedFeatures.register("p_pumpkins",
            MBVegetationFeatures.PUMPKIN_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> BARREL_CACTI = MBPlacedFeatures.register("p_barrel_cacti",
            MBVegetationFeatures.BARREL_CACTI, CountPlacementModifier.of(1), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(60), YOffset.fixed(114)), BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FLOOD_BARREL_CACTI = MBPlacedFeatures.register("p_flood_barrel_cacti",
            MBVegetationFeatures.FLOOD_BARREL_CACTI, CountPlacementModifier.of(3), SquarePlacementModifier.of(),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(67), YOffset.fixed(72)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ORE_CRACKED_MUD = MBPlacedFeatures.register("p_cracked_mud",
            MBVegetationFeatures.PATCH_CRACKED_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final RegistryEntry<PlacedFeature> ORE_RICH_MUD = MBPlacedFeatures.register("p_rich_mud",
            MBVegetationFeatures.PATCH_RICH_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_PEAT_MOSS = MBPlacedFeatures.register("p_peat_moss",
            MBVegetationFeatures.PATCH_PEAT_MOSS, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final RegistryEntry<PlacedFeature> ORE_PERMAFROST = MBPlacedFeatures.register("p_frost",
            MBVegetationFeatures.TUNDRA_FROST, MBPlacedCaveFeatures.modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> TILL_ROCK = MBPlacedFeatures.register("p_till_rock",
            MBVegetationFeatures.TILL_ROCK, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());


    // trees
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_0002 = MBPlacedFeatures.register("p_gb_0002",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));
    public static final RegistryEntry<PlacedFeature> SUPER_GOLDEN_BIRCH_BEES_0002 = MBPlacedFeatures.register("p_sgb_0002",
            MBTreeFeatures.SUPER_GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));

    public static final RegistryEntry<PlacedFeature> GBF_TREES = MBPlacedFeatures.register("p_gbf_trees",
            MBVegetationFeatures.GBF_TREES, modifiers(PlacedFeatures.createCountExtraModifier(6, 0.1F, 1)));
    public static final RegistryEntry<PlacedFeature> PASTURE_TREES = MBPlacedFeatures.register("p_pasture_trees",
            MBVegetationFeatures.PASTURE_TREES, modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 1)));

    public static final RegistryEntry<PlacedFeature> CEDARS = MBPlacedFeatures.register("p_cedars",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatures.createCountExtraModifier(4, 0.1F, 1)));
    public static final RegistryEntry<PlacedFeature> PRAIRIE_TREES = MBPlacedFeatures.register("p_prairie",
            MBVegetationFeatures.BADLANDS_TREES,
            modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 1)));
    public static final RegistryEntry<PlacedFeature> JUNIPERS = MBPlacedFeatures.register("p_junipers",
            MBVegetationFeatures.JUNIPER_TREES,
            PlacedFeatures.createCountExtraModifier(1, 0.1F, 1), SquarePlacementModifier.of(),
            SurfaceWaterDepthFilterPlacementModifier.of(0), RarityFilterPlacementModifier.of(16),
            HeightRangePlacementModifier.trapezoid(YOffset.fixed(68), YOffset.fixed(78)), BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> FALLEN_OAK = MBPlacedFeatures.register("p_fallen_oak",
            MBTreeFeatures.FALLEN_OAK, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FALLEN_BIRCH = MBPlacedFeatures.register("p_fallen_birch",
            MBTreeFeatures.FALLEN_BIRCH, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FALLEN_SPRUCE = MBPlacedFeatures.register("p_fallen_spruce",
            MBTreeFeatures.FALLEN_SPRUCE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // soil patches
    public static final RegistryEntry<PlacedFeature> COARSE_DIRT_PATCH = MBPlacedFeatures.register("p_coarse_dirt_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PODZOL_PATCH = MBPlacedFeatures.register("p_podzol_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> LEAFBED_PATCH = MBPlacedFeatures.register("p_leafbed_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> COARSE_DIRT_FLOOR = MBPlacedFeatures.register("p_coarse_dirt_floor",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());

    public static void init() {}

    public static List<PlacementModifier> modifiers(PlacementModifier modifier) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
    }
    public static List<PlacementModifier> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of(), BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)));
    }
}
