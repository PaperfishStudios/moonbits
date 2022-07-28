package net.paperfish.moonbits.world.feature;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

public class RedOakSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
        return bees ? MBTreeFeatures.RED_OAK_BEES_005 : MBTreeFeatures.RED_OAK;
    }

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(RandomGenerator random) {
        return MBTreeFeatures.BIG_RED_OAK;
    }
}
