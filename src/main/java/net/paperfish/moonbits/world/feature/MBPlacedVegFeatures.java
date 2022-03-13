package net.paperfish.moonbits.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.MiscConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class MBPlacedVegFeatures {
    public static final RegistryEntry<PlacedFeature> PEBBLES = PlacedFeatures.register("p_pebbles",
            MBVegetationFeatures.PEBBLES, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_MYCELIUM = PlacedFeatures.register("p_mycelium", MBVegetationFeatures.PATCH_MYCELIUM,
            NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_DESERT_BRUSH = PlacedFeatures.register("p_desert_brush", MBVegetationFeatures.PATCH_DESERT_BRUSH,
            NoiseThresholdCountPlacementModifier.of(-0.9, 3, 6), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PATCH_COTTONGRASS = PlacedFeatures.register("p_cottongrass", MBVegetationFeatures.COTTONGRASS,
            NoiseThresholdCountPlacementModifier.of(-0.9, 3, 6), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    // flower patches
    public static final RegistryEntry<PlacedFeature> BUTTERCUP_PATCH = PlacedFeatures.register("p_buttercups",
            MBVegetationFeatures.BUTTERCUP_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FORGETMENOT_PATCH = PlacedFeatures.register("p_forgetmenot",
            MBVegetationFeatures.FORGETMENOT_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> MARIGOLD_PATCH = PlacedFeatures.register("p_marigold",
            MBVegetationFeatures.MARIGOLD_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ROSE_BUSH = PlacedFeatures.register("p_rose_bushes",
            MBVegetationFeatures.ROSE_BUSH, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> SUNFLOWERS = PlacedFeatures.register("p_sunflowers",
            MBVegetationFeatures.SUNFLOWERS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> HYACINTHS = PlacedFeatures.register("p_hyacinths",
            MBVegetationFeatures.HYACINTHS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // flower sets
    public static final RegistryEntry<PlacedFeature> AUTUMN_FLOWERS = PlacedFeatures.register("p_autumn_flowers",
            MBVegetationFeatures.AUTUMN_FLOWERS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MB_MEADOW_FLOWERS = PlacedFeatures.register("p_meadow_flowers",
            MBVegetationFeatures.MB_MEADOW_FLOWERS, SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MBFF_FLOWERS = PlacedFeatures.register("p_mbff_flowers",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.of(7), RarityFilterPlacementModifier.of(2),
            SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> LUPINE = PlacedFeatures.register("p_lupine",
            MBVegetationFeatures.LUPINE, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> HEATHER = PlacedFeatures.register("p_heather",
            MBVegetationFeatures.HEATHER, CountPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // fungi
    public static final RegistryEntry<PlacedFeature> PUFFBALLS_PATCH = PlacedFeatures.register("p_puffballs",
            MBVegetationFeatures.PUFFBALLS_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> TOADSTOOLS = PlacedFeatures.register("p_toadstools",
            MBVegetationFeatures.TOADSTOOLS, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // wild crops
    public static final RegistryEntry<PlacedFeature> WILD_CARROT_PATCH = PlacedFeatures.register("p_wild_carrots",
            MBVegetationFeatures.WILD_CARROT_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> WILD_POTATO_PATCH = PlacedFeatures.register("p_wild_potatoes",
            MBVegetationFeatures.WILD_POTATO_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> SEA_BEET_PATCH = PlacedFeatures.register("p_sea_beets",
            MBVegetationFeatures.SEA_BEET_PATCH, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> PUMPKIN_PATCH = PlacedFeatures.register("p_pumpkins",
            MBVegetationFeatures.PUMPKIN_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> BARREL_CACTI = PlacedFeatures.register("p_barrel_cacti",
            MBVegetationFeatures.BARREL_CACTI, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> ORE_CRACKED_MUD = PlacedFeatures.register("p_cracked_mud",
            MBVegetationFeatures.PATCH_CRACKED_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final RegistryEntry<PlacedFeature> ORE_RICH_MUD = PlacedFeatures.register("p_rich_mud",
            MBVegetationFeatures.PATCH_RICH_MUD, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_PEAT_MOSS = PlacedFeatures.register("p_peat_moss",
            MBVegetationFeatures.PATCH_PEAT_MOSS, MBPlacedCaveFeatures.modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    public static final RegistryEntry<PlacedFeature> ORE_PERMAFROST = PlacedFeatures.register("p_frost",
            MBVegetationFeatures.TUNDRA_FROST, MBPlacedCaveFeatures.modifiersWithCount(12, HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> TILL_ROCK = PlacedFeatures.register("p_till_rock",
            MBVegetationFeatures.TILL_ROCK, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());


    // trees
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_0002 = PlacedFeatures.register("p_gb_0002",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));
    public static final RegistryEntry<PlacedFeature> SUPER_GOLDEN_BIRCH_BEES_0002 = PlacedFeatures.register("p_sgb_0002",
            MBTreeFeatures.SUPER_GOLDEN_BIRCH_BEES_0002,
            modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING));

    public static final RegistryEntry<PlacedFeature> GBF_TREES = PlacedFeatures.register("p_gbf_trees",
            MBVegetationFeatures.GBF_TREES, modifiers(PlacedFeatures.createCountExtraModifier(6, 0.1F, 1)));

    public static final RegistryEntry<PlacedFeature> FALLEN_OAK = PlacedFeatures.register("p_fallen_oak",
            MBTreeFeatures.FALLEN_OAK, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FALLEN_BIRCH = PlacedFeatures.register("p_fallen_birch",
            MBTreeFeatures.FALLEN_BIRCH, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> FALLEN_SPRUCE = PlacedFeatures.register("p_fallen_spruce",
            MBTreeFeatures.FALLEN_SPRUCE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    // soil patches
    public static final RegistryEntry<PlacedFeature> COARSE_DIRT_PATCH = PlacedFeatures.register("p_coarse_dirt_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PODZOL_PATCH = PlacedFeatures.register("p_podzol_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> LEAFBED_PATCH = PlacedFeatures.register("p_leafbed_patch",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> COARSE_DIRT_FLOOR = PlacedFeatures.register("p_coarse_dirt_floor",
            MBVegetationFeatures.COARSE_DIRT_PATCH, CountPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of());

    public static void init() {}

//    public static void registerFeatures() {
//        BiomeModifications.addFeature(BiomeSelectors.categories(
//                Biome.Category.FOREST
//        ), GenerationStep.Feature.LOCAL_MODIFICATIONS, p_pebbles);
//
//        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
//                BiomeKeys.GROVE
//        ), GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
//
//        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
//                BiomeKeys.BEACH, BiomeKeys.STONY_SHORE
//        ), GenerationStep.Feature.VEGETAL_DECORATION, sea_beets_patch);
//
//        // forest modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FOREST), (c) -> {
//                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_oak);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_potato_patch);
//                });
//
//        // taiga modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_taiga"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.TAIGA), (c) -> {
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_spruce);
//                });
//
//        // flower forest modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_flower_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), (c) -> {
//                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, buttercup_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_hyacinths);
//                })
//                .add(ModificationPhase.REPLACEMENTS,
//                        BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
//                        (c)-> {
//                            c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
//                            c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_mbff_flowers);
//                        }
//                );
//
//        // meadow modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_meadow"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
//                })
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_MEADOW);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_meadow_flowers);
//                });
//
//        // birch forest modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_birch_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c) -> {
//                    c.getEffects().setGrassColor(0xB4C63C);
//                    c.getEffects().setSkyColor(0xb1c5e6);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_rose_bushes);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_sunflowers);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, forgetmenot_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_pumpkins);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_puffballs);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_toadstools);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_leafbed_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_birch);
//                })
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c)-> {
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_autumn_flowers);
//
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BIRCH);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_gbf_trees);
//                });
//
//        // tall birch forest modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_tall_birch_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
//                    c.getEffects().setGrassColor(0xB4C63C);
//                    c.getEffects().setSkyColor(0xb1c5e6);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_rose_bushes);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_sunflowers);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, forgetmenot_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_pumpkins);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_puffballs);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_toadstools);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_podzol_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_leafbed_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_birch);
//                })
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c)-> {
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_autumn_flowers);
//
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_super_golden_birch);
//                });
//
//        // river modifications
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_rivers"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.RIVER), (c) -> {
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_floor);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, p_pebbles);
//                });
//    }

    public static List<PlacementModifier> modifiers(PlacementModifier modifier) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
    }
    public static List<PlacementModifier> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of(), BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)));
    }
}
