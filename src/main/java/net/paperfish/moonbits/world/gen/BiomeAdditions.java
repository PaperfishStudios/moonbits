package net.paperfish.moonbits.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.world.MBBiomes;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class BiomeAdditions {
    public static void registerFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_REGOLITH.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_TIN.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_TIN_UPPER.getKey().get());

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_deposits"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.PLAINS,
                        Biome.Category.SAVANNA,
                        Biome.Category.TAIGA,
                        Biome.Category.JUNGLE,
                        Biome.Category.MUSHROOM,
                        Biome.Category.FOREST
                ), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT_UPPER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_TIN_DEPOSIT.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_dirt_cave"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.PLAINS,
                        Biome.Category.SAVANNA,
                        Biome.Category.TAIGA,
                        Biome.Category.JUNGLE,
                        Biome.Category.MUSHROOM,
                        Biome.Category.FOREST
                ), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.WILDFLOWERS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CLOVERS.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(BiomeTags.IS_FOREST), (c) -> {
                    c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_OAK.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILDFLOWER_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CLOVER_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"mb_lushcave"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CLOVER_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_taigas"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(BiomeTags.IS_TAIGA), (c) -> {
                    //  c.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_PATCH.getKey().get());
                    //c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.PODZOL_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_SPRUCE.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_desert"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.DESERT), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_COAL_LOWER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_GOLD_EXTRA.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_GOLD.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_GOLD_LOWER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_REDSTONE.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_REDSTONE_LOWER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_LAPIS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_LAPIS_BURIED.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_COPPER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_COPPER_LARGE.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.ORE_CRACKED_MUD.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.JUNIPERS.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_DESERT_BRUSH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MARIGOLD_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.BARREL_CACTI.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_DESERT_BRUSH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_MARIGOLD_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_BARREL_CACTI.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.DESERT, MBBiomes.STEPPE), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_ANDESITE_LOWER.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_SANDSTONE.getKey().get());
                    c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_DIORITE_LOWER.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COBBLECHERT.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_savanna"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(Biome.Category.SAVANNA), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.ORE_CRACKED_MUD.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.ORE_RICH_MUD.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MARIGOLD_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_badlands"))
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.categories(Biome.Category.MESA), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BADLANDS.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CEDARS.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_tundra"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.ORE_PERMAFROST.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.TILL_ROCK.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_COTTONGRASS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.HEATHER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.LUPINE.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_GRANITE_LOWER.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_TILL.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_flower_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), (c) -> c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.HYACINTHS.getKey().get()));
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_mushroom"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS), (c) -> c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_MYCELIUM.getKey().get()));
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_swamps"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.SWAMP
                ), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT_HIGH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.ORE_PEAT_MOSS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.ORE_RICH_MUD.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_meadow"))
                // meadow-only cave stuff
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.LUSH_CLAY_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILDFLOWER_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CLOVER_PATCH.getKey().get());
                })
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get()))
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_MEADOW.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MB_MEADOW_FLOWERS.getKey().get());
                });

//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_birch_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
//                })
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c) -> {
//                    c.getEffects().setGrassColor(0xB4C63C);
//                    c.getEffects().setSkyColor(0xb1c5e6);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.LEAFBED_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_BIRCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.ROSE_BUSH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.BUTTERCUP_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FORGETMENOT_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUFFBALLS_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.TOADSTOOLS.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
//                })
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST), (c)-> {
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT.value());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS.getKey().get());
//
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BIRCH.value());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.GBF_TREES.getKey().get());
//                })
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), (c)-> {
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.FLOWER_DEFAULT.value());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS.getKey().get());
//
//                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL.value());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.SUPER_GOLDEN_BIRCH_BEES_0002.getKey().get());
//                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_rivers"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.RIVER), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                });
    }
}
