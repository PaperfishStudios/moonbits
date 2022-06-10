package net.paperfish.moonbits.world;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.paperfish.moonbits.registry.MBEntities;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.world.gen.MBPlacedCaveFeatures;
import net.paperfish.moonbits.world.gen.MBPlacedVegFeatures;
import org.jetbrains.annotations.Nullable;

public class MBBiomes {
    public static final RegistryKey<Biome> GOLDEN_FOREST = createBiomeKey("golden_forest");
    public static final RegistryKey<Biome> TALL_GOLDEN_FOREST = createBiomeKey("tall_golden_forest");
    public static final RegistryKey<Biome> PASTURE = createBiomeKey("pasture");

    public static final RegistryKey<Biome> STEPPE = createBiomeKey("steppe");
    public static final RegistryKey<Biome> PRAIRIE = createBiomeKey("prairie");


    public static RegistryKey<Biome> createBiomeKey(String id) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, id));
    }

    public static void registerBiomes() {
        register(GOLDEN_FOREST, goldenForest(false));
        register(TALL_GOLDEN_FOREST, goldenForest(true));
        register(PASTURE, pasture());
        register(STEPPE, steppe());
        register(PRAIRIE, prairie());
    }
    private static void register(RegistryKey<Biome> key, Biome biome) {
        BuiltinRegistries.add(BuiltinRegistries.BIOME, key, biome);
    }

    public static Biome goldenForest(boolean tall) {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);

        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);

//        addDefaultDeposits(builder);

        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.LEAFBED_PATCH);
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_BIRCH);


        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.ROSE_BUSH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FORGETMENOT_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.TOADSTOOLS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUFFBALLS_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH);

        if (tall) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.SUPER_GOLDEN_BIRCH_BEES_0002);
        }
        else {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.GBF_TREES);
        }

        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder2);
        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 3, 6));
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(MBEntities.GRIZZLY_BEAR, 4, 2, 3));

        return createBiome(Biome.Precipitation.RAIN, 0.6f, 0.6f,
                4159204, 329011, builder2, builder, 0xb1c5e6, 0xB4C63C, MusicType.GAME);
    }
    public static Biome pasture() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);

        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_FOREST);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);

//        addDefaultDeposits(builder);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.AUTUMN_FLOWERS);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUFFBALLS_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_CARROT_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PASTURE_TREES);

        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder2);
        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 5, 8));

        return createBiome(Biome.Precipitation.RAIN, 0.6f, 0.6f,
                4159204, 329011, builder2, builder, 0xb1c5e6, 0xB4C63C, MusicType.GAME);
    }
//    public static Biome temperateForest() {
//        GenerationSettings.Builder builder = new GenerationSettings.Builder();
//        addBasicFeatures(builder);
//
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
//
//        DefaultBiomeFeatures.addDefaultOres(builder);
//        addDefaultDeposits(builder);
//        DefaultBiomeFeatures.addDefaultDisks(builder);
//
//        // TODO: aspen trees go here
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.GBF_TREES);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_FOREST);
//
//        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.LEAFBED_PATCH);
//        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.PODZOL_PATCH);
//        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.FALLEN_BIRCH);
//        DefaultBiomeFeatures.addMossyRocks(builder);
//        DefaultBiomeFeatures.addLargeFerns(builder);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUFFBALLS_PATCH);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.WILD_POTATO_PATCH);
//
//        DefaultBiomeFeatures.addDefaultMushrooms(builder);
//        DefaultBiomeFeatures.addDefaultVegetation(builder);
//        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
//        DefaultBiomeFeatures.addFarmAnimals(builder2);
//        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
//        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(MBEntities.GRIZZLY_BEAR, 3, 2, 3));
//
//        return createBiome(Biome.Precipitation.RAIN, Biome.Category.FOREST, 0.6f, 0.6f,
//                4159204, 329011, builder2, builder, 0xb1c5e6, 0xB4C63C, MusicType.GAME);
//    }

    public static Biome steppe() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addFossils(builder);

        addBasicFeatures(builder);

        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addDefaultGrass(builder);
        DefaultBiomeFeatures.addDesertDeadBushes(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDesertVegetation(builder);

        addDesertOres(builder);
//        desertMineables(builder);

        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.PEBBLES);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MBPlacedVegFeatures.ORE_CRACKED_MUD);
        builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.COARSE_DIRT_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_DESERT_BRUSH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_MARIGOLD_PATCH);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_BARREL_CACTI);

        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        lushDesertMobs(builder2);
        return createBiome(Biome.Precipitation.NONE, 2.0f, 0.0f,
                0x3a6fd9, 329011, builder2, builder, getSkyColor(2.0f), MusicType.GAME);
    }
    public static Biome prairie() {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);

        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);

        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_WARM);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);

//        addDefaultDeposits(builder);


        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PUMPKIN_PATCH);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PRAIRIE_TREES);
//        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.FLOOD_MARIGOLD_PATCH);

        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder2);
        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 5, 8));

        return createBiome(Biome.Precipitation.RAIN, 0.6f, 0.6f,
                4159204, 329011, builder2, builder, getSkyColor(0.8f), 0xd6db44, MusicType.GAME);
    }

    public static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    public static void addDefaultDeposits(GenerationSettings.Builder builder) {
        addDefaultDeposits(builder, false, false, false);
    }
    public static void addDefaultDeposits(GenerationSettings.Builder builder, boolean largePeat, boolean largeClay, boolean largeGold) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largePeat ? MBPlacedCaveFeatures.ORE_PEAT_HIGH : MBPlacedCaveFeatures.ORE_PEAT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largeClay ? MBPlacedCaveFeatures.LUSH_CLAY_DEPOSIT : MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_CLAY_DEPOSIT_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largeGold ? MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT_HIGH : MBPlacedCaveFeatures.ORE_GOLD_DEPOSIT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT);
    }
    public static void addRYSStuff(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.TOUGH_GRASS_PATCH);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.LAMPROOT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS);
    }
    public static void addDesertOres(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_COAL_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_GOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_GOLD_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_REDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_REDSTONE_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_LAPIS);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_LAPIS_BURIED);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.CHERT_COPPER);
    }
    public static void desertMineables(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIRT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRAVEL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRANITE_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GRANITE_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_DIORITE_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COBBLECHERT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_ANDESITE_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_SANDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_TUFF);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.GLOW_LICHEN);
    }
    public static void lushDesertMobs(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 3, 8));
        DefaultBiomeFeatures.addCaveMobs(builder);
        DefaultBiomeFeatures.addMonsters(builder, 19, 1, 100, false);
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4));
    }

    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBiome(precipitation, temperature, downfall, 4159204, 329011, spawnSettings, generationSettings, music);
    }

    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall)
                .effects(new BiomeEffects.Builder()
                        .waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463)
                        .skyColor(getSkyColor(temperature))
                        .moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, int skyColor, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall)
                .effects(new BiomeEffects.Builder()
                        .waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463)
                        .skyColor(skyColor)
                        .moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, int skyColor, int grassColor, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall)
                .effects(new BiomeEffects.Builder()
                        .waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463)
                        .skyColor(skyColor).foliageColor(grassColor).grassColor(grassColor)
                        .moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    protected static int getSkyColor(float temperature) {
        float f = temperature;
        f /= 3.0f;
        f = MathHelper.clamp(f, -1.0f, 1.0f);
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }
}
