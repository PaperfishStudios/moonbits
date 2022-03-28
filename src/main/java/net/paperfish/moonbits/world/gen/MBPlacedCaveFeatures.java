package net.paperfish.moonbits.world.gen;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class MBPlacedCaveFeatures {
    // regolith transition
    public static final RegistryEntry<PlacedFeature> T_REGOLITH = PlacedFeatures.register("t_regolith",
            MBCaveFeatures.T_REGOLITH, modifiersWithRarity(8, HeightRangePlacementModifier.uniform(YOffset.fixed(42), YOffset.fixed(50))));

    // regolith clumps
    public static final RegistryEntry<PlacedFeature> ORE_REGOLITH = PlacedFeatures.register("p_regolith",
            MBCaveFeatures.ORE_REGOLITH, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));

    public static final RegistryEntry<PlacedFeature> ORE_SANDSTONE = PlacedFeatures.register("p_sandstone",
            MBCaveFeatures.ORE_SANDSTONE, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));

    public static final RegistryEntry<PlacedFeature> ORE_COBBLECHERT = PlacedFeatures.register("p_cobblechert",
            MBCaveFeatures.ORE_COBBLECHERT, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60))));

    public static final RegistryEntry<PlacedFeature> ORE_TILL = PlacedFeatures.register("p_till",
            MBCaveFeatures.ORE_TILL, modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));

    // deposits
    public static final RegistryEntry<PlacedFeature> ORE_PEAT = PlacedFeatures.register("p_peat",
            MBCaveFeatures.ORE_PEAT, modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_PEAT_HIGH = PlacedFeatures.register("p_peat_swamp",
            MBCaveFeatures.ORE_PEAT_HIGH, modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_CLAY_DEPOSIT = PlacedFeatures.register("p_clay",
            MBCaveFeatures.ORE_CLAY_DEPOSIT, modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(55), YOffset.fixed(70))));
    public static final RegistryEntry<PlacedFeature> ORE_CLAY_DEPOSIT_UPPER = PlacedFeatures.register("p_clay_upper",
            MBCaveFeatures.ORE_CLAY_DEPOSIT, modifiersWithCount(18, HeightRangePlacementModifier.uniform(YOffset.fixed(70), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> LUSH_CLAY_DEPOSIT = PlacedFeatures.register("p_lush_clay",
            MBCaveFeatures.LUSH_CLAY_DEPOSIT, modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(53), YOffset.fixed(78))));
    public static final RegistryEntry<PlacedFeature> ORE_GOLD_DEPOSIT = PlacedFeatures.register("p_gold",
            MBCaveFeatures.ORE_GOLD_DEPOSIT, modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_GOLD_DEPOSIT_HIGH = PlacedFeatures.register("p_gold_high",
            MBCaveFeatures.ORE_GOLD_DEPOSIT, modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final RegistryEntry<PlacedFeature> ORE_COPPER_DEPOSIT = PlacedFeatures.register("p_copper",
            MBCaveFeatures.ORE_COPPER_DEPOSIT, modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(68))));

    // chert ores
    public static final RegistryEntry<PlacedFeature> CHERT_COAL_LOWER = PlacedFeatures.register(
            "chert_coal_low", MBCaveFeatures.ORE_CHERT_COAL_BURIED, modifiersWithCount(20, HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(192))));
    public static final RegistryEntry<PlacedFeature> CHERT_GOLD_EXTRA = PlacedFeatures.register(
            "chert_gold_ex", MBCaveFeatures.ORE_CHERT_GOLD, modifiersWithCount(50, HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(256))));
    public static final RegistryEntry<PlacedFeature> CHERT_GOLD = PlacedFeatures.register(
            "chert_gold", MBCaveFeatures.ORE_CHERT_GOLD_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
    public static final RegistryEntry<PlacedFeature> CHERT_GOLD_LOWER = PlacedFeatures.register(
            "chert_gold_low", MBCaveFeatures.ORE_CHERT_GOLD_BURIED, modifiers(CountPlacementModifier.of(UniformIntProvider.create(0, 1)), HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(48))));
    public static final RegistryEntry<PlacedFeature> CHERT_REDSTONE = PlacedFeatures.register(
            "chert_redstone", MBCaveFeatures.ORE_CHERT_REDSTONE, modifiersWithCount(6, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(48))));
    public static final RegistryEntry<PlacedFeature> CHERT_REDSTONE_LOWER = PlacedFeatures.register(
            "chert_redstone_lower", MBCaveFeatures.ORE_CHERT_REDSTONE, modifiersWithCount(12, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-32), YOffset.aboveBottom(80))));
    public static final RegistryEntry<PlacedFeature> CHERT_LAPIS = PlacedFeatures.register(
            "chert_lapis", MBCaveFeatures.ORE_CHERT_LAPIS, modifiersWithCount(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
    public static final RegistryEntry<PlacedFeature> CHERT_LAPIS_BURIED = PlacedFeatures.register(
            "chert_lapis_buried", MBCaveFeatures.ORE_CHERT_LAPIS_BURIED, modifiersWithCount(4, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
    public static final RegistryEntry<PlacedFeature> CHERT_COPPER = PlacedFeatures.register(
            "chert_copper", MBCaveFeatures.ORE_CHERT_COPPER_SMALL, modifiersWithCount(16, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(112))));
    public static final RegistryEntry<PlacedFeature> CHERT_COPPER_LARGE = PlacedFeatures.register(
            "chert_copper_large", MBCaveFeatures.ORE_CHERT_COPPER_LARGE, modifiersWithCount(16, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(112))));


    // regolith floor
    public static final RegistryEntry<PlacedFeature> REGOLITH_FLOOR = PlacedFeatures.register("p_rg_floor",
            MBCaveFeatures.REGOLITH_FLOOR, CountPlacementModifier.of(125), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(52)));

    // tough grass patch
    public static final RegistryEntry<PlacedFeature> TOUGH_GRASS_PATCH = PlacedFeatures.register("p_tg_patch",
            MBCaveFeatures.TOUGH_GRASS_PATCH, CountPlacementModifier.of(4), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(52), YOffset.fixed(110)));
    public static final RegistryEntry<PlacedFeature> TOUGH_GRASS_FLOOR = PlacedFeatures.register("p_tg_floor",
            MBCaveFeatures.TOUGH_GRASS_PATCH, CountPlacementModifier.of(8), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(52), YOffset.fixed(110)));
    public static final RegistryEntry<PlacedFeature> TOUGH_GRASS_LUSH = PlacedFeatures.register("p_tg_lush",
            MBCaveFeatures.TOUGH_GRASS_LUSH, CountPlacementModifier.of(32), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(48), YOffset.fixed(58)));

    // cave vegetation
    public static final RegistryEntry<PlacedFeature> TG_VEGETATION = PlacedFeatures.register("p_tg_veg",
            MBCaveFeatures.TG_VEGETATION, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE);
    public static final RegistryEntry<PlacedFeature> LAMPROOT = PlacedFeatures.register("p_lamproot",
            MBCaveFeatures.LAMPROOT, CountPlacementModifier.of(64), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(90)),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)));
    public static final RegistryEntry<PlacedFeature> CAVEBLOOMS = PlacedFeatures.register("p_caveblooms",
            MBCaveFeatures.CAVEBLOOMS, CountPlacementModifier.of(UniformIntProvider.create(32, 48)), HeightRangePlacementModifier.uniform(YOffset.fixed(45), YOffset.fixed(65)),
            SquarePlacementModifier.of(), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), BiomePlacementModifier.of());

    public static void init() {}

//    public static void registerFeatures() {
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, p_regolith);
//
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_deposits"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
//                        Biome.Category.PLAINS,
//                        Biome.Category.FOREST,
//                        Biome.Category.JUNGLE,
//                        Biome.Category.MUSHROOM,
//                        Biome.Category.SAVANNA,
//                        Biome.Category.TAIGA
//                ), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_rg_floor);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
//                });
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_swamps"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
//                        Biome.Category.SWAMP
//                ), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_rg_floor);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_patch);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat_swamp);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
//                });
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"mb_lush"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(
//                        BiomeKeys.LUSH_CAVES
//                ), (c) -> {
//                    //c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_lush);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_lush_clay);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
//                });
//        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"mb_meadow"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(
//                        BiomeKeys.MEADOW
//                ), (c) -> {
//                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_floor);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold_high);
//                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
//                });
//
//
//    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModfier) {
        return modifiers(CountPlacementModifier.of(count), heightModfier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
