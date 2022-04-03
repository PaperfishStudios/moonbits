package net.paperfish.moonbits.world;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.paperfish.moonbits.Moonbits;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

public class MBBiomeProvider extends Region {
    public MBBiomeProvider() {
        super(new Identifier(Moonbits.MODID, "mb_biomes"), RegionType.OVERWORLD, 4);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
//            builder.replaceBiome(BiomeKeys.BIRCH_FOREST, MBBiomes.GOLDEN_FOREST);
//            builder.replaceBiome(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, MBBiomes.TALL_GOLDEN_FOREST);
//            builder.replaceBiome(BiomeKeys.PLAINS, MBBiomes.PASTURE);
//            builder.replaceBiome(BiomeKeys.DESERT, MBBiomes.FLOOD_PLAINS);
//            builder.replaceBiome(BiomeKeys.ERODED_BADLANDS, MBBiomes.STEPPE);
//            builder.replaceBiome(BiomeKeys.SAVANNA_PLATEAU, MBBiomes.PRAIRIE);
            builder.replaceBiome(BiomeKeys.SNOWY_PLAINS, MBBiomes.TUNDRA);
//            builder.replaceBiome(BiomeKeys.SWAMP, MBBiomes.MIRE);
            List<MultiNoiseUtil.NoiseHypercube> goldenForest = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.NEUTRAL)
                    .humidity(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.NEAR_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_3, ParameterUtils.Erosion.EROSION_4)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.span(ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING))
                    .build();
            goldenForest.forEach(point -> builder.replaceBiome(point, MBBiomes.GOLDEN_FOREST));

            List<MultiNoiseUtil.NoiseHypercube> tallGoldenForest = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.NEUTRAL)
                    .humidity(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.MID_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_3, ParameterUtils.Erosion.EROSION_4)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.span(ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING, ParameterUtils.Weirdness.HIGH_SLICE_NORMAL_ASCENDING))
                    .build();
            tallGoldenForest.forEach(point -> builder.replaceBiome(point, MBBiomes.TALL_GOLDEN_FOREST));

            List<MultiNoiseUtil.NoiseHypercube> pasture = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.COOL, ParameterUtils.Temperature.NEUTRAL)
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.MID_INLAND, ParameterUtils.Continentalness.FAR_INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3, ParameterUtils.Erosion.EROSION_4)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING)
                    .build();
            pasture.forEach(point -> builder.replaceBiome(point, MBBiomes.PASTURE));

            List<MultiNoiseUtil.NoiseHypercube> floodplain = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.HOT)
                    .humidity(ParameterUtils.Humidity.ARID)
                    .continentalness(ParameterUtils.Continentalness.COAST, ParameterUtils.Continentalness.NEAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_4, ParameterUtils.Erosion.EROSION_5)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING)
                    .build();
            floodplain.forEach(point -> builder.replaceBiome(point, MBBiomes.FLOOD_PLAINS));

            List<MultiNoiseUtil.NoiseHypercube> steppe = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.HOT)
                    .humidity(ParameterUtils.Humidity.ARID)
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND, ParameterUtils.Continentalness.INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_4, ParameterUtils.Erosion.EROSION_5)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING)
                    .build();
            steppe.forEach(point -> builder.replaceBiome(point, MBBiomes.STEPPE));

            List<MultiNoiseUtil.NoiseHypercube> prairie = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.HOT)
                    .humidity(ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Humidity.WET)
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_2, ParameterUtils.Erosion.EROSION_3)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_ASCENDING)
                    .build();
            prairie.forEach(point -> builder.replaceBiome(point, MBBiomes.PRAIRIE));

            List<MultiNoiseUtil.NoiseHypercube> mire = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.NEUTRAL, ParameterUtils.Temperature.WARM, ParameterUtils.Temperature.HOT)
                    .humidity(ParameterUtils.Humidity.DRY, ParameterUtils.Humidity.NEUTRAL)
                    .continentalness(ParameterUtils.Continentalness.span(ParameterUtils.Continentalness.NEAR_INLAND, ParameterUtils.Continentalness.INLAND))
                    .erosion(ParameterUtils.Erosion.EROSION_6)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING,
                            ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING)
                    .build();
            mire.forEach(point -> builder.replaceBiome(point, MBBiomes.MIRE));
        });
    }
}
