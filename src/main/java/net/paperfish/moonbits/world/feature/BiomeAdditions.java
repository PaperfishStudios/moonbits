package net.paperfish.moonbits.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.paperfish.moonbits.Moonbits;

public class BiomeAdditions {
    public static void registerFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_REGOLITH.getKey().get());

        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_deposits"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.PLAINS,
                        Biome.Category.FOREST,
                        Biome.Category.JUNGLE,
                        Biome.Category.MUSHROOM,
                        Biome.Category.SAVANNA,
                        Biome.Category.TAIGA
                ), (c) -> {
                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(BiomeTags.IS_FOREST), (c) -> {
                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_OAK.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_taigas"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(BiomeTags.IS_TAIGA), (c) -> {
                    //  c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_PATCH.getKey().get());
                    //c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.PODZOL_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_SPRUCE.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_flower_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.HYACINTHS.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_swamps"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.SWAMP
                ), (c) -> {
                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT_HIGH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_meadow"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
                })
                // meadow-only cave stuff
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.LUSH_CLAY_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_MEADOW.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MB_MEADOW_FLOWERS.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_birch_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
                    //c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.PODZOL_PATCH.getKey().get());
                })
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
                    c.getEffects().setGrassColor(0xB4C63C);
                    c.getEffects().setSkyColor(0xb1c5e6);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.LEAFBED_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_BIRCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.ROSE_BUSH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.BUTTERCUP_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FORGETMENOT_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUFFBALLS_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.TOADSTOOLS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c)-> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS.getKey().get());

                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BIRCH.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.GBF_TREES.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c)-> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS.getKey().get());

                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.SUPER_GOLDEN_BIRCH_BEES_0002.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_rivers"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.RIVER), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                });
    }
}
