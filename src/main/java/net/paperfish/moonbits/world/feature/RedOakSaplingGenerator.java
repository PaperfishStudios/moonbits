package net.paperfish.moonbits.world.feature;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RedOakSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    protected ConfiguredFeature<?, ?> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.RED_OAK_BEES_005 : MBTreeFeatures.RED_OAK;
    }

    @Override
    protected ConfiguredFeature<?, ?> getLargeTreeFeature(Random random) {
        return MBTreeFeatures.BIG_RED_OAK;
    }
}
