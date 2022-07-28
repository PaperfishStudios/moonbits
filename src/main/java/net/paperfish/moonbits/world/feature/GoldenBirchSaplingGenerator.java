package net.paperfish.moonbits.world.feature;

import net.minecraft.util.Holder;

import net.minecraft.util.random.RandomGenerator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

public class GoldenBirchSaplingGenerator extends SaplingGenerator {

    public GoldenBirchSaplingGenerator() {
    }

    @Override
    public Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
        return bees ? MBTreeFeatures.GOLDEN_BIRCH_BEES_005 : MBTreeFeatures.GOLDEN_BIRCH;
    }

}
