package net.paperfish.moonbits.world.feature;

import java.util.Random;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class GoldenBirchSaplingGenerator extends SaplingGenerator {

    public GoldenBirchSaplingGenerator() {
    }

    @Nullable
    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.GOLDEN_BIRCH_BEES_005 : MBTreeFeatures.GOLDEN_BIRCH;
    }

}
