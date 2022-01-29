package net.paperfish.moonbits.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.paperfish.moonbits.Moonbits;

import java.util.List;

public class MBPlacedCaveFeatures {
    public static void registerFeatures() {
        // regolith transition
        RegistryKey<PlacedFeature> t_regolith = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "t_regolith"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, t_regolith.getValue(),
                MBCaveFeatures.T_REGOLITH.withPlacement(
                        modifiersWithRarity(16, HeightRangePlacementModifier.uniform(YOffset.fixed(42), YOffset.fixed(50)))
                )
        );
        // regolith clumps
        RegistryKey<PlacedFeature> p_regolith = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_regolith"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_regolith.getValue(),
                MBCaveFeatures.ORE_REGOLITH.withPlacement(
                        modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60)))
                )
        );

        // peat deposits
        RegistryKey<PlacedFeature> p_peat = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_peat"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_peat.getValue(),
                MBCaveFeatures.ORE_PEAT.withPlacement(
                        modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(120)))
                )
        );
        // peat deposits
        RegistryKey<PlacedFeature> p_peat_swamp = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_peat_swamp"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_peat_swamp.getValue(),
                MBCaveFeatures.ORE_PEAT_HIGH.withPlacement(
                        modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(120)))
                )
        );
        // clay deposits
        RegistryKey<PlacedFeature> p_clay = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_clay"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_clay.getValue(),
                MBCaveFeatures.ORE_CLAY_DEPOSIT.withPlacement(
                        modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(55), YOffset.fixed(70)))
                )
        );
        // lush clay deposits
        RegistryKey<PlacedFeature> p_lush_clay = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_lush_clay"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_lush_clay.getValue(),
                MBCaveFeatures.LUSH_CLAY_DEPOSIT.withPlacement(
                        modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(53), YOffset.fixed(78)))
                )
        );
        // gold deposits
        RegistryKey<PlacedFeature> p_gold = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_gold"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_gold.getValue(),
                MBCaveFeatures.ORE_GOLD_DEPOSIT.withPlacement(
                        modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128)))
                )
        );
        // buffed gold deposits
        RegistryKey<PlacedFeature> p_gold_high = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_gold_high"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_gold_high.getValue(),
                MBCaveFeatures.ORE_GOLD_DEPOSIT.withPlacement(
                        modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(128)))
                )
        );
        // copper deposits
        RegistryKey<PlacedFeature> p_copper = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_copper"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_copper.getValue(),
                MBCaveFeatures.ORE_COPPER_DEPOSIT.withPlacement(
                        modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(68)))
                )
        );
        // regolith floor
        RegistryKey<PlacedFeature> p_rg_floor = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_rg_floor"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_rg_floor.getValue(),
                MBCaveFeatures.REGOLITH_FLOOR.withPlacement(
                        CountPlacementModifier.of(125), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(52))));
        // tough grass patch
        RegistryKey<PlacedFeature> p_tg_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_tg_patch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_tg_patch.getValue(),
                MBCaveFeatures.TOUGH_GRASS_PATCH.withPlacement(
                        CountPlacementModifier.of(4), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(52), YOffset.fixed(110))));
        // tough grass floor
        RegistryKey<PlacedFeature> p_tg_floor = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_tg_floor"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_tg_floor.getValue(),
                MBCaveFeatures.TOUGH_GRASS_PATCH.withPlacement(
                        CountPlacementModifier.of(12), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(52), YOffset.fixed(110))));
        // tough grass lush ver.
        RegistryKey<PlacedFeature> p_tg_lush = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_tg_lush"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_tg_lush.getValue(),
                MBCaveFeatures.TOUGH_GRASS_LUSH.withPlacement(
                        CountPlacementModifier.of(125), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(48), YOffset.fixed(110))));
        // tough grass vegetation
        RegistryKey<PlacedFeature> p_tg_veg = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_tg_veg"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_tg_veg.getValue(),
                MBCaveFeatures.TG_VEGETATION.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE));
        // lamproot
        RegistryKey<PlacedFeature> p_lamproot = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_lamproot"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_lamproot.getValue(),
                MBCaveFeatures.LAMPROOT.withPlacement(
                        CountPlacementModifier.of(64), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(32), YOffset.fixed(90)),
                                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1))
                        )
        );
        // caveblooms
        RegistryKey<PlacedFeature> p_caveblooms = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(Moonbits.MOD_ID, "p_caveblooms"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_caveblooms.getValue(),
                MBCaveFeatures.CAVEBLOOMS.withPlacement(
                        CountPlacementModifier.of(UniformIntProvider.create(64, 128)), HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(90)),
                        SquarePlacementModifier.of(), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), BiomePlacementModifier.of()
                )
        );



        // ----------------------------------------------------------------------------------------------------------------------------------------------

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, p_regolith);

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
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_rg_floor);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"moonbits_swamps"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.categories(
                        Biome.Category.SWAMP
                ), (c) -> {
                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_rg_floor);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_patch);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat_swamp);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"mb_lush"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(
                        BiomeKeys.LUSH_CAVES
                ), (c) -> {
                    //c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_lush);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_lush_clay);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
                });
        BiomeModifications.create(new Identifier(Moonbits.MOD_ID,"mb_meadow"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(
                        BiomeKeys.MEADOW
                ), (c) -> {
                    c.getGenerationSettings().addCarver(GenerationStep.Carver.AIR, MBCaveFeatures.dirt_cave);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_tg_floor);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_lamproot);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, p_caveblooms);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold_high);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
                });


    }

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModfier) {
        return modifiers(CountPlacementModifier.of(count), heightModfier);
    }

    private static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilterPlacementModifier.of(chance), heightModifier);
    }
}
