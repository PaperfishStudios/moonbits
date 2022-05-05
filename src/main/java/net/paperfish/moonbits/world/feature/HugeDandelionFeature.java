package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

import java.util.Random;

public class HugeDandelionFeature extends HugeMushroomFeature {
    public HugeDandelionFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        return 2;
    }

    @Override
    protected void generateCap(WorldAccess pLevel, Random pRandom, BlockPos pPos, int pTreeHeight, BlockPos.Mutable pMutablePos, HugeMushroomFeatureConfig pConfig) {
        int yCapBottom = pTreeHeight - 2;
        for (int y = yCapBottom; y <= pTreeHeight; y++) {
            int capRadius = y > yCapBottom ? 2 : 1;

            for (int x = -capRadius; x <= capRadius; x++) {
                for (int z = -capRadius; z <= capRadius; z++) {
                    boolean isXCorner = x == -capRadius || x == capRadius;
                    boolean isZCorner = z == -capRadius || z == capRadius;
                    boolean isXGap = (x == -2 || x == 2) && z == 0;
                    boolean isZGap = (z == -2 || z == 2) && x == 0;
                    boolean isGap = y == pTreeHeight && (isXGap || isZGap);
                    boolean isInnerPos = (x == -1 && z == -1) || (x == -1 && z == 1) || (x == 1 && z == -1) || (x == 1 && z == 1);
                    boolean isInner = isInnerPos && y == yCapBottom;
                    if (isXCorner != isZCorner || y == yCapBottom) {
                        if (!isGap) {
                            pMutablePos.set(pPos, x, isInner ? y + 1 : y, z);
                            if (!pLevel.getBlockState(pMutablePos).isOpaqueFullCube(pLevel, pMutablePos)) {
                                BlockState blockstate = pConfig.capProvider.getBlockState(pRandom, pPos);
                                this.setBlockState(pLevel, pMutablePos, blockstate);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int getHeight(Random pRandom) {
        return pRandom.nextInt(3) + 6;
    }

    @Override
    protected void generateStem(WorldAccess pLevel, Random pRandom, BlockPos pPos, HugeMushroomFeatureConfig pConfig, int pMaxHeight, BlockPos.Mutable pMutablePos) {
        super.generateStem(pLevel, pRandom, pPos, pConfig, pMaxHeight - 1, pMutablePos);
    }

}