package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

import java.util.Random;

public class HugeDandelionPuffFeature extends HugeMushroomFeature {
    public HugeDandelionPuffFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        return 2;
    }

    @Override
    protected void generateCap(WorldAccess pLevel, Random pRandom, BlockPos pPos, int pTreeHeight, BlockPos.Mutable pMutablePos, HugeMushroomFeatureConfig pConfig) {
        int yCapBottom = pTreeHeight - 4;
        for (int y = yCapBottom; y <= pTreeHeight; y++) {
            int capRadius = y < pTreeHeight && y > yCapBottom ? 2 : 1;

            for (int x = -capRadius; x <= capRadius; x++) {
                for (int z = -capRadius; z <= capRadius; z++) {
                    boolean isXCorner = x == -capRadius || x == capRadius;
                    boolean isZCorner = z == -capRadius || z == capRadius;
                    if (y >= pTreeHeight || isXCorner != isZCorner || y == yCapBottom) {
                        pMutablePos.set(pPos, x, y, z);
                        if (!pLevel.getBlockState(pMutablePos).isOpaqueFullCube(pLevel, pMutablePos)) {
                            BlockState blockstate = pConfig.capProvider.getBlockState(pRandom, pPos);
                            this.setBlockState(pLevel, pMutablePos, blockstate);
                        }
                    }
                }
            }
        }
    }

    @Override
    protected int getHeight(Random pRandom) {
        return pRandom.nextInt(3) + 8;
    }
}
