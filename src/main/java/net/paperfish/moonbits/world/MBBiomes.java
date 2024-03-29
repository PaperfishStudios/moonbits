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
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.world.gen.MBPlacedCaveFeatures;
import net.paperfish.moonbits.world.gen.MBPlacedVegFeatures;
import org.jetbrains.annotations.Nullable;

public class MBBiomes {
    public static final RegistryKey<Biome> TUNDRA = createBiomeKey("tundra");


    public static RegistryKey<Biome> createBiomeKey(String id) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, id));
    }

    public static void registerBiomes() {
        register(TUNDRA, tundra());
    }
    private static void register(RegistryKey<Biome> key, Biome biome) {
        BuiltinRegistries.register(BuiltinRegistries.BIOME, key, biome);
    }

	public static Biome tundra() {
		GenerationSettings.Builder builder = new GenerationSettings.Builder();
		addBasicFeatures(builder);

		DefaultBiomeFeatures.addDefaultOres(builder);
		DefaultBiomeFeatures.addDefaultDisks(builder);

		DefaultBiomeFeatures.addLargeFerns(builder);
		DefaultBiomeFeatures.addDefaultVegetation(builder);
		DefaultBiomeFeatures.addMelons(builder);

//        addDefaultDeposits(builder);

//		builder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, MBPlacedVegFeatures.TILL_ROCK);


		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.PATCH_COTTONGRASS);
		builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedVegFeatures.HEATHER);

		SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addFarmAnimals(builder2);
		DefaultBiomeFeatures.addBatsAndMonsters(builder2);
		builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 3, 6));

		return createBiome(Biome.Precipitation.RAIN, 0.6f, 0.6f,
				4159204, 329011, builder2, builder, 0xb1c5e6, 0xB4C63C, MusicType.GAME);
	}

    public static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addUndergroundVariety(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    public static void addDefaultDeposits(GenerationSettings.Builder builder) {
        addDefaultDeposits(builder, false, false, false);
    }
    public static void addDefaultDeposits(GenerationSettings.Builder builder, boolean largePeat, boolean largeClay, boolean largeGold) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largePeat ? MBPlacedCaveFeatures.ORE_PEAT_HIGH : MBPlacedCaveFeatures.ORE_PEAT);
		builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_FLINT_DEPOSIT);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largeClay ? MBPlacedCaveFeatures.LUSH_ROOTED_SOIL : MBPlacedCaveFeatures.ORE_ROOTED_SOIL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_ROOTED_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, largeGold ? MBPlacedCaveFeatures.ORE_FUZZ_HIGH : MBPlacedCaveFeatures.ORE_FUZZ);
//        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_COPPER_DEPOSIT);
    }
    public static void addRYSStuff(GenerationSettings.Builder builder) {
        builder.feature(GenerationStep.Feature.RAW_GENERATION, MBPlacedCaveFeatures.T_REGOLITH);
        builder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, MBPlacedCaveFeatures.REGOLITH_FLOOR);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedCaveFeatures.LAMPROOT);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MBPlacedCaveFeatures.CAVEBLOOMS);
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
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedCaveFeatures.ORE_SAND);
//        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBPlacedVegFeatures.ORE_SANDY_SOIL);
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
