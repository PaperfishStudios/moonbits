package net.paperfish.moonbits.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.paperfish.moonbits.Moonbits;

import java.util.List;

public class MBPlacedVegFeatures {
    public static void registerFeatures() {
        // pebbles
        RegistryKey<PlacedFeature> p_pebbles = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_pebbles"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_pebbles.getValue(),
                MBVegetationFeatures.PEBBLES.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // wild carrots
        RegistryKey<PlacedFeature> wild_carrot_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_wild_carrots"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, wild_carrot_patch.getValue(),
                MBVegetationFeatures.WILD_CARROT_PATCH.withPlacement(
                        CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // wild potatoes
        RegistryKey<PlacedFeature> wild_potato_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_wild_potatoes"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, wild_potato_patch.getValue(),
                MBVegetationFeatures.WILD_POTATO_PATCH.withPlacement(
                        CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // sea beets
        RegistryKey<PlacedFeature> sea_beets_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_sea_beets"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, sea_beets_patch.getValue(),
                MBVegetationFeatures.SEA_BEET_PATCH.withPlacement(
                        CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // coarse dirt floor
        RegistryKey<PlacedFeature> p_coarse_dirt_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_coarse_dirt_patch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_coarse_dirt_patch.getValue(),
                MBVegetationFeatures.COARSE_DIRT_PATCH.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));
        RegistryKey<PlacedFeature> p_coarse_dirt_floor = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_coarse_dirt_floor"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_coarse_dirt_floor.getValue(),
                MBVegetationFeatures.COARSE_DIRT_PATCH.withPlacement(
                        CountPlacementModifier.of(32), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));
        // podzol floor
        RegistryKey<PlacedFeature> p_podzol_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_podzol_patch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_podzol_patch.getValue(),
                MBVegetationFeatures.PODZOL_PATCH.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));
        // leafbed floor
        RegistryKey<PlacedFeature> p_leafbed_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_leafbed_patch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_leafbed_patch.getValue(),
                MBVegetationFeatures.LEAFBED_PATCH.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));


        // buttercup patch!
        RegistryKey<PlacedFeature> buttercup_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_buttercups"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, buttercup_patch.getValue(),
                MBVegetationFeatures.BUTTERCUP_PATCH.withPlacement(
                        CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        // forget-me-not patch!
        RegistryKey<PlacedFeature> forgetmenot_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_forgetmenot"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, forgetmenot_patch.getValue(),
                MBVegetationFeatures.FORGETMENOT_PATCH.withPlacement(
                        CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // golden forest trees
        RegistryKey<PlacedFeature> p_gbf_trees = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_gbf_trees"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_gbf_trees.getValue(),
                MBVegetationFeatures.MBFF_TREES.withPlacement(modifiers(PlacedFeatures.createCountExtraModifier(6, 0.1F, 1))));

        // flower forest trees
        RegistryKey<PlacedFeature> p_mbff_trees = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_mbff_trees"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_mbff_trees.getValue(),
                MBVegetationFeatures.MBFF_TREES.withPlacement(modifiers(PlacedFeatures.createCountExtraModifier(6, 0.1F, 1))));
        // flower forest funky flower pattern
        RegistryKey<PlacedFeature> p_mbff_flowers = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_mbff_flowers"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_mbff_flowers,
                MBVegetationFeatures.MBFF_FLOWERS.withPlacement(
                        CountPlacementModifier.of(7), RarityFilterPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // rose bushes
        RegistryKey<PlacedFeature> p_rose_bushes = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_rose_bushes"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_rose_bushes.getValue(),
                MBVegetationFeatures.ROSE_BUSH.withPlacement(
                        CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()
                ));
        // sunflowers
        RegistryKey<PlacedFeature> p_sunflowers = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_sunflowers"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_sunflowers.getValue(),
                MBVegetationFeatures.SUNFLOWERS.withPlacement(
                        CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()
                ));

        // hyacinths
        RegistryKey<PlacedFeature> p_hyacinths = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_hyacinths"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_hyacinths.getValue(),
                MBVegetationFeatures.HYACINTHS.withPlacement(
                        CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()
                ));

        // pumpkins
        RegistryKey<PlacedFeature> p_pumpkins = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_pumpkins"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_pumpkins.getValue(), MBVegetationFeatures.PUMPKIN_PATCH.withPlacement(
                CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        // toadstools
        RegistryKey<PlacedFeature> p_toadstools = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_toadstools"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_toadstools.getValue(), MBVegetationFeatures.TOADSTOOLS.withPlacement(
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        // autumn flowers
        RegistryKey<PlacedFeature> p_autumn_flowers = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_autumn_flowers"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_autumn_flowers.getValue(), MBVegetationFeatures.AUTUMN_FLOWERS.withPlacement(
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        // golden birch trees
        RegistryKey<PlacedFeature> p_golden_birch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_gb_0002"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_golden_birch.getValue(), MBTreeFeatures.GOLDEN_BIRCH_BEES_0002.withPlacement(
                modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING)));

        RegistryKey<PlacedFeature> p_super_golden_birch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_sgb_0002"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_super_golden_birch.getValue(), MBTreeFeatures.SUPER_GOLDEN_BIRCH_BEES_0002.withPlacement(
                modifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), Blocks.BIRCH_SAPLING)));

        // fallen trees
        RegistryKey<PlacedFeature> p_fallen_oak = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_fallen_oak"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_fallen_oak.getValue(), MBTreeFeatures.FALLEN_OAK.withPlacement(
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        RegistryKey<PlacedFeature> p_fallen_birch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_fallen_birch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_fallen_birch.getValue(), MBTreeFeatures.FALLEN_BIRCH.withPlacement(
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        RegistryKey<PlacedFeature> p_fallen_spruce = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_fallen_spruce"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_fallen_spruce.getValue(), MBTreeFeatures.FALLEN_SPRUCE.withPlacement(
                CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // meadow flowers
        RegistryKey<PlacedFeature> p_meadow_flowers = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_meadow_flowers"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_meadow_flowers.getValue(),
                MBVegetationFeatures.MB_MEADOW_FLOWERS.withPlacement(
                        SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));

        // -------------------------------------------------------------------------------------------------------------------------------------------------------------

        BiomeModifications.addFeature(BiomeSelectors.categories(
                Biome.Category.FOREST
        ), GenerationStep.Feature.LOCAL_MODIFICATIONS, p_pebbles);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.GROVE
        ), GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(
                BiomeKeys.BEACH, BiomeKeys.STONY_SHORE
        ), GenerationStep.Feature.VEGETAL_DECORATION, sea_beets_patch);

        // forest modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FOREST), (c) -> {
                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_oak);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_potato_patch);
                });

        // taiga modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_taiga"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.TAIGA), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_spruce);
                });

        // flower forest modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_flower_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), (c) -> {
                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, buttercup_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_hyacinths);
                })
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
                        (c)-> {
                            c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_FLOWER_FOREST);
                            c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_mbff_trees);

                            c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
                            c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_mbff_flowers);
                        }
                );

        // meadow modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_meadow"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_MEADOW);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_meadow_flowers);
                });

        // birch forest modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_birch_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c) -> {
                    c.getEffects().setGrassColor(0xB4C63C);
                    c.getEffects().setSkyColor(0xb1c5e6);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_rose_bushes);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_sunflowers);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, forgetmenot_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_pumpkins);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_toadstools);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_leafbed_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_birch);
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c)-> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_autumn_flowers);

                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BIRCH);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_gbf_trees);
                });

        // tall birch forest modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_tall_birch_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
                    c.getEffects().setGrassColor(0xB4C63C);
                    c.getEffects().setSkyColor(0xb1c5e6);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_rose_bushes);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_sunflowers);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, forgetmenot_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_pumpkins);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_toadstools);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, wild_carrot_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_podzol_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_leafbed_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_fallen_birch);
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c)-> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_autumn_flowers);

                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_super_golden_birch);
                });

        // river modifications
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_rivers"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.RIVER), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, p_coarse_dirt_floor);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, p_pebbles);
                });
    }

    public static List<PlacementModifier> modifiers(PlacementModifier modifier) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
    }
    public static List<PlacementModifier> modifiersWithWouldSurvive(PlacementModifier modifier, Block block) {
        return List.of(modifier, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of(), BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(block.getDefaultState(), BlockPos.ORIGIN)));
    }
}
