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
            builder.replaceBiome(BiomeKeys.BIRCH_FOREST, MBBiomes.GOLDEN_FOREST);
            builder.replaceBiome(BiomeKeys.OLD_GROWTH_BIRCH_FOREST, MBBiomes.TALL_GOLDEN_FOREST);
            builder.replaceBiome(BiomeKeys.PLAINS, MBBiomes.PASTURE);
            builder.replaceBiome(BiomeKeys.SAVANNA_PLATEAU, MBBiomes.PRAIRIE);
            builder.replaceBiome(BiomeKeys.SNOWY_PLAINS, MBBiomes.TUNDRA);

            List<MultiNoiseUtil.NoiseHypercube> steppe = new ParameterUtils.ParameterPointListBuilder()
                    .temperature(ParameterUtils.Temperature.HOT)
                    .humidity(ParameterUtils.Humidity.ARID)
                    .continentalness(ParameterUtils.Continentalness.FAR_INLAND, ParameterUtils.Continentalness.INLAND)
                    .erosion(ParameterUtils.Erosion.EROSION_4, ParameterUtils.Erosion.EROSION_5)
                    .depth(ParameterUtils.Depth.SURFACE, ParameterUtils.Depth.FLOOR)
                    .weirdness(ParameterUtils.Weirdness.LOW_SLICE_NORMAL_DESCENDING, ParameterUtils.Weirdness.MID_SLICE_VARIANT_DESCENDING, ParameterUtils.Weirdness.LOW_SLICE_VARIANT_ASCENDING)
                    .build();
            steppe.forEach(point -> builder.replaceBiome(point, MBBiomes.STEPPE));
        });
    }
}
