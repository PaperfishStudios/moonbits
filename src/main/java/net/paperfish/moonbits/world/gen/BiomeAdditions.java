package net.paperfish.moonbits.world.gen;

import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.quiltmc.qsl.worldgen.biome.api.ModificationPhase;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.registry.MBBiomeTags;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class BiomeAdditions {
    public static void registerFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_REGOLITH.getKey().get());

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_deposits"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(MBBiomeTags.HAS_DIRT_CAVES), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT.getKey().get());
					c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_FLINT_DEPOSIT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_ROOTED_SOIL.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_ROOTED_UPPER.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_FUZZ.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_dirt_cave"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(MBBiomeTags.HAS_DIRT_CAVES), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
					c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.SOURSOB_PATCH.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_forests"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(BiomeTags.IS_FOREST), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_OAK.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"mb_lushcave"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_taigas"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(BiomeTags.IS_TAIGA), (c) -> {
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

					c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.DESERT_VASES.getKey().get());

//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_DESERT_BRUSH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MARIGOLD_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.BARREL_CACTI.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_DESERT_BRUSH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_MARIGOLD_PATCH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_BARREL_CACTI.getKey().get());
//					c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.DESERT_CAVES.getKey().get());
                })
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.DESERT), (c) -> {
					c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_DIRT.value());
					c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_SAND.getKey().get());
//					c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_GRAVEL.value());
//					c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedVegFeatures.ORE_SANDY_SOIL.getKey().get());
                    c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_ANDESITE_LOWER.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_SANDSTONE.getKey().get());
                    c.getGenerationSettings().removeBuiltInFeature(OrePlacedFeatures.ORE_DIORITE_LOWER.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COBBLECHERT.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_savanna"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.isIn(BiomeTags.IS_SAVANNA), (c) -> {

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.MARIGOLD_PATCH.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_badlands"))
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.isIn(BiomeTags.IS_BADLANDS), (c) -> {
                    c.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_BADLANDS.value());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CEDARS.getKey().get());
					c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.OCOTILLO_PATCH.getKey().get());
                });

        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_tundra"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.LUPINE.getKey().get());
                });

//        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_flower_forests"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST), (c) ->
//						c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.HYACINTHS.getKey().get()));
//        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_mushroom"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS), (c) ->
//						c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_MYCELIUM.getKey().get()));
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_swamps"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_PEAT_HIGH.getKey().get());
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.ORE_PEAT_MOSS.getKey().get());
                });
        BiomeModifications.create(new Identifier(Moonbits.MODID,"moonbits_meadow"))
                // meadow-only cave stuff
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.MEADOW), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS.getKey().get());

                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.LUSH_ROOTED_SOIL.getKey().get());
//					c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILDFLOWER_PATCH.getKey().get());
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.CLOVER_PATCH.getKey().get());
                });
    }
}
