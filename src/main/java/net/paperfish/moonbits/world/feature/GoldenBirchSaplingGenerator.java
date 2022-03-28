package net.paperfish.moonbits.world.feature;

import java.util.Random;

import net.minecraft.util.registry.RegistryEntry;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

public class GoldenBirchSaplingGenerator extends SaplingGenerator {

    public GoldenBirchSaplingGenerator() {
    }

    @Override
    public RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.GOLDEN_BIRCH_BEES_005 : MBTreeFeatures.GOLDEN_BIRCH;
    }

}
