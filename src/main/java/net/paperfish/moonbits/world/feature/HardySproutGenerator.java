package net.paperfish.moonbits.world.feature;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Holder;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;
import org.jetbrains.annotations.Nullable;

public class HardySproutGenerator extends SaplingGenerator {
	@Nullable
	@Override
	protected Holder<? extends ConfiguredFeature<?, ?>> getTreeFeature(RandomGenerator random, boolean bees) {
		return MBTreeFeatures.HARDY_BUSH;
	}
}
