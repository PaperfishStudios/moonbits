package net.moonteam.moonbits.world.feature;

import java.util.Random;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class AppleOakSaplingGenerator extends SaplingGenerator {

    public AppleOakSaplingGenerator() {
    }

    @Nullable
    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.APPLE_OAK_BEES_005 : MBTreeFeatures.APPLE_OAK;
    }

}
