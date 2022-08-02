package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.paperfish.moonbits.block.ParasolLeafBlock;
import net.paperfish.moonbits.registry.MBBlocks;

public class ParasolFernFeature extends HugeMushroomFeature {
	public ParasolFernFeature(Codec<HugeMushroomFeatureConfig> codec) {
		super(codec);
	}

	@Override
	protected int getCapSize(int i, int j, int capSize, int y) {
		return 2;
	}

	@Override
	protected void generateCap(WorldAccess pLevel, RandomGenerator pRandom, BlockPos pPos, int pTreeHeight, BlockPos.Mutable pMutablePos, HugeMushroomFeatureConfig pConfig) {
//		int yCapBottom = pTreeHeight - 2;
		pMutablePos.set(pMutablePos.offset(Direction.NORTH, 2));
		pMutablePos.set(pMutablePos.offset(Direction.WEST, 2));
		pMutablePos.set(pMutablePos.offset(Direction.DOWN));
		this.setBlockState(pLevel, pMutablePos, MBBlocks.PARASOL_FERN_CROWN.getDefaultState());
		for (Direction direction : Direction.Type.HORIZONTAL) {
			BlockPos leafPos = pMutablePos.offset(direction);
			if (!pLevel.getBlockState(leafPos).isOpaqueFullCube(pLevel, leafPos)) {
				BlockState blockstate = MBBlocks.PARASOL_LEAF.getDefaultState().with(ParasolLeafBlock.FACING, direction);
				this.setBlockState(pLevel, leafPos, blockstate);
			}
		}

//		for (int y = yCapBottom; y <= pTreeHeight; y++) {
//			int capRadius = y > yCapBottom ? 2 : 1;
//
//			for (int x = -capRadius; x <= capRadius; x++) {
//				for (int z = -capRadius; z <= capRadius; z++) {
//					boolean isXCorner = x == -capRadius || x == capRadius;
//					boolean isZCorner = z == -capRadius || z == capRadius;
//					boolean isXGap = (x == -2 || x == 2) && z == 0;
//					boolean isZGap = (z == -2 || z == 2) && x == 0;
//					boolean isGap = y == pTreeHeight && (isXGap || isZGap);
//					boolean isInnerPos = (x == -1 && z == -1) || (x == -1 && z == 1) || (x == 1 && z == -1) || (x == 1 && z == 1);
//					boolean isInner = isInnerPos && y == yCapBottom;
//					if (isXCorner != isZCorner || y == yCapBottom) {
//						if (!isGap) {
//							pMutablePos.set(pPos, x, isInner ? y + 1 : y, z);
//							if (!pLevel.getBlockState(pMutablePos).isOpaqueFullCube(pLevel, pMutablePos)) {
//								BlockState blockstate = pConfig.capProvider.getBlockState(pRandom, pPos);
//								this.setBlockState(pLevel, pMutablePos, blockstate);
//							}
//						}
//					}
//				}
//			}
//		}
	}

	@Override
	protected int getHeight(RandomGenerator pRandom) {
		return pRandom.nextInt(3) + 3;
	}

	@Override
	protected void generateStem(WorldAccess pLevel, RandomGenerator pRandom, BlockPos pPos, HugeMushroomFeatureConfig pConfig, int pMaxHeight, BlockPos.Mutable pMutablePos) {
		super.generateStem(pLevel, pRandom, pPos, pConfig, pMaxHeight - 1, pMutablePos);
	}
}
