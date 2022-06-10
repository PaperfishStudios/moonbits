package net.paperfish.moonbits.world.feature;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import net.minecraft.util.math.random.Random;

public class JuniperSaplingGenerator extends SaplingGenerator {
    @Override
    public RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return MBTreeFeatures.CEDAR;
    }
}
