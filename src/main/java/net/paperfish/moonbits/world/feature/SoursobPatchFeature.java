package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.paperfish.moonbits.registry.MBBlocks;

public class SoursobPatchFeature extends RandomPatchFeature {
	public SoursobPatchFeature(Codec<RandomPatchFeatureConfig> codec) {
		super(codec);
	}
	@Override
	public boolean place(FeatureContext<RandomPatchFeatureConfig> context) {
		RandomPatchFeatureConfig randomPatchFeatureConfig = context.getConfig();
		RandomGenerator randomGenerator = context.getRandom();
		BlockPos blockPos = context.getOrigin();
		StructureWorldAccess structureWorldAccess = context.getWorld();
		int i = 0;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		int j = randomPatchFeatureConfig.spreadXz() + 1;
		int k = randomPatchFeatureConfig.spreadY() + 1;

		for(int l = 0; l < randomPatchFeatureConfig.tries(); ++l) {
			mutable.set(
					blockPos,
					randomGenerator.nextInt(j) - randomGenerator.nextInt(j),
					randomGenerator.nextInt(k) - randomGenerator.nextInt(k),
					randomGenerator.nextInt(j) - randomGenerator.nextInt(j)
			);
			if (structureWorldAccess.getBlockState(mutable.down()).isIn(BlockTags.BASE_STONE_OVERWORLD)) {
				structureWorldAccess.setBlockState(mutable.toImmutable(), MBBlocks.REGOLITH.getDefaultState(), 3);
			}
			if (randomPatchFeatureConfig.feature().value().place(structureWorldAccess, context.getGenerator(), randomGenerator, mutable)) {
				++i;
			}
		}

		return i > 0;
	}
}
