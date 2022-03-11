package net.paperfish.moonbits.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlobsFeatureConfig;
import net.paperfish.moonbits.MBBlocks;

public class SurfaceFeatures {
    public static final RegistryEntry<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> CRACKED_MUD_DESERT =
            ConfiguredFeatures.register("cracked_mud_desert", Feature.NETHERRACK_REPLACE_BLOBS,
            new ReplaceBlobsFeatureConfig(
                    Blocks.SAND.getDefaultState(),
                    MBBlocks.CRACKED_MUD.getDefaultState(),
                    UniformIntProvider.create(2, 4)
            ));
    public static final RegistryEntry<ConfiguredFeature<ReplaceBlobsFeatureConfig, ?>> CRACKED_MUD_SAVANNA =
            ConfiguredFeatures.register("cracked_mud_savanna", Feature.NETHERRACK_REPLACE_BLOBS,
                    new ReplaceBlobsFeatureConfig(
                            Blocks.GRASS_BLOCK.getDefaultState(),
                            MBBlocks.CRACKED_MUD.getDefaultState(),
                            UniformIntProvider.create(2, 4)
                    ));
}
