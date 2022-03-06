package net.paperfish.moonbits.world.feature;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class RedOakSaplingGenerator extends LargeTreeSaplingGenerator {

    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.RED_OAK_BEES_005 : MBTreeFeatures.RED_OAK;
    }

    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return MBTreeFeatures.BIG_RED_OAK;
    }
}
