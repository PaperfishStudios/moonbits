package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.paperfish.moonbits.block.MushroomCapBlock;

public class GiantToadstoolFeature extends HugeMushroomFeature {
    public GiantToadstoolFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        return y <= 3 ? 0 : capSize;
    }

    @Override
    protected void generateCap(WorldAccess world, RandomGenerator random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        int radius = config.foliageRadius;
        for (int j = -radius; j <= radius; ++j) {
            for (int k = -radius; k <= radius; ++k) {
                boolean bl6;
                boolean bl = j == -radius;
                boolean bl2 = j == radius;
                boolean bl3 = k == -radius;
                boolean bl4 = k == radius;
                boolean bl5 = bl || bl2;
                boolean bl7 = bl6 = bl3 || bl4;
                if (bl5 && bl6) continue;
                mutable.set(start, j, y, k);
                if (world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) continue;
//                boolean bl72 = bl || bl6 && j == 1 - radius;
//                boolean bl8 = bl2 || bl6 && j == radius - 1;
//                boolean bl9 = bl3 || bl5 && k == 1 - radius;
//                boolean bl10 = bl4 || bl5 && k == radius - 1;
                BlockState blockState = config.capProvider.getBlockState(random, start);
//                if (blockState.contains(MushroomCapBlock.WEST) && blockState.contains(MushroomCapBlock.EAST) && blockState.contains(MushroomCapBlock.NORTH) && blockState.contains(MushroomCapBlock.SOUTH)) {
//                    blockState = blockState.with(MushroomCapBlock.WEST, bl72).with(MushroomCapBlock.EAST, bl8).with(MushroomCapBlock.NORTH, bl9).with(MushroomCapBlock.SOUTH, bl10);
//                }
                this.setBlockState(world, mutable, blockState);
            }
        }
    }
}
