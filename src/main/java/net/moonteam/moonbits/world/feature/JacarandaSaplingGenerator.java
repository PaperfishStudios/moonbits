package net.moonteam.moonbits.world.feature;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class JacarandaSaplingGenerator extends SaplingGenerator {

    public JacarandaSaplingGenerator() {
    }

    @Nullable
    @Override
    public ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.JACARANDA_BEES_005 : MBTreeFeatures.JACARANDA;
    }
}
