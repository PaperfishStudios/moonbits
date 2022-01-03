package net.moonteam.moonbits.world.feature;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.moonteam.moonbits.MoonbitsMain;

import java.util.List;
import java.util.function.Predicate;

public class MBPlacedCaveFeatures {
    public static void registerFeatures() {
        // regolith transition
        RegistryKey<PlacedFeature> t_regolith = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "t_regolith"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, t_regolith.getValue(),
                MBCaveFeatures.T_REGOLITH.withPlacement(
                        modifiersWithRarity(16, HeightRangePlacementModifier.uniform(YOffset.fixed(42), YOffset.fixed(50)))
                )
        );
        // regolith clumps
        RegistryKey<PlacedFeature> p_regolith = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_regolith"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_regolith.getValue(),
                MBCaveFeatures.ORE_REGOLITH.withPlacement(
                        modifiersWithCount(2, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60)))
                )
        );

        // peat deposits
        RegistryKey<PlacedFeature> p_peat = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_peat"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_peat.getValue(),
                MBCaveFeatures.ORE_PEAT.withPlacement(
                        modifiersWithCount(24, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(70)))
                )
        );
        // clay deposits
        RegistryKey<PlacedFeature> p_clay = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_clay"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_clay.getValue(),
                MBCaveFeatures.ORE_CLAY_DEPOSIT.withPlacement(
                        modifiersWithCount(32, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(70)))
                )
        );
        // gold deposits
        RegistryKey<PlacedFeature> p_gold = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_gold"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_gold.getValue(),
                MBCaveFeatures.ORE_GOLD_DEPOSIT.withPlacement(
                        modifiersWithCount(16, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(65)))
                )
        );
        // copper deposits
        RegistryKey<PlacedFeature> p_copper = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_copper"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_copper.getValue(),
                MBCaveFeatures.ORE_COPPER_DEPOSIT.withPlacement(
                        modifiersWithCount(28, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(65)))
                )
        );
        // tough grass patch
        RegistryKey<PlacedFeature> p_tg_patch = RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(MoonbitsMain.MOD_ID, "p_tg_patch"));
        Registry.register(BuiltinRegistries.PLACED_FEATURE, p_tg_patch.getValue(),
                MBCaveFeatures.TOUGH_GRASS.withPlacement(
                        CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));


        // ----------------------------------------------------------------------------------------------------------------------------------------------

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, p_regolith);

        BiomeModifications.create(new Identifier(MoonbitsMain.MOD_ID,"moonbits_deposits"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.excludeByKey(BiomeKeys.DESERT, BiomeKeys.DEEP_FROZEN_OCEAN,
                        BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.FROZEN_OCEAN,
                        BiomeKeys.BEACH, BiomeKeys.SNOWY_BEACH
                ), (c) -> {
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.RAW_GENERATION, t_regolith);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_peat);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_clay);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_gold);
                    c.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, p_copper);
                    //c.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, p_tg_patch);
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
