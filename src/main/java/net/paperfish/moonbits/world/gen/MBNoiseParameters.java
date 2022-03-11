package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class MBNoiseParameters {
    public static final RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> DIRT_CAVES = RegistryKey.of(Registry.NOISE_WORLDGEN, new Identifier("dirt_cave"));

    public static void init() {
        register(DIRT_CAVES, -3, 1.0, 1.0, 1.0, 1.0);
    }

    public static void register(RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> noise, int firstOctave, double firstAmplitude, double ... amplitudes) {
        BuiltinRegistries.add(BuiltinRegistries.NOISE_PARAMETERS, noise, new DoublePerlinNoiseSampler.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
    }
}
