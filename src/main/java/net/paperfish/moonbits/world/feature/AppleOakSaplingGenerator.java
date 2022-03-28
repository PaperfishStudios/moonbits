package net.paperfish.moonbits.world.feature;

import java.util.Random;

import net.minecraft.util.registry.RegistryEntry;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

public class AppleOakSaplingGenerator extends SaplingGenerator {

    public AppleOakSaplingGenerator() {
    }

    @Override
    public RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? MBTreeFeatures.APPLE_OAK_BEES_005 : MBTreeFeatures.APPLE_OAK;
    }

}
