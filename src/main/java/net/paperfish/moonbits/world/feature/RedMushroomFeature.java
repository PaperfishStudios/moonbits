package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.paperfish.moonbits.block.MushroomCapBlock;

public class RedMushroomFeature extends HugeMushroomFeature {
    public RedMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected void generateCap(WorldAccess world, RandomGenerator random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        for (int i = y - 3; i <= y; ++i) {
            int j = i < y ? config.foliageRadius : config.foliageRadius - 1;
            int k = config.foliageRadius - 2;
            for (int l = -j; l <= j; ++l) {
                for (int m = -j; m <= j; ++m) {
                    boolean bl6;
                    boolean bl = l == -j;
                    boolean bl2 = l == j;
                    boolean bl3 = m == -j;
                    boolean bl4 = m == j;
                    boolean bl5 = bl || bl2;
                    boolean bl7 = bl6 = bl3 || bl4;
                    if (i < y && bl5 == bl6) continue;
                    mutable.set(start, l, i, m);
                    if (world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) continue;
                    BlockState blockState = config.capProvider.getBlockState(random, start);
                    if (blockState.contains(MushroomCapBlock.WEST) && blockState.contains(MushroomCapBlock.EAST) && blockState.contains(MushroomCapBlock.NORTH) && blockState.contains(MushroomCapBlock.SOUTH) && blockState.contains(MushroomCapBlock.UP)) {
                        blockState = blockState
                                .with(MushroomCapBlock.UP, i >= y - 1).with(MushroomCapBlock.WEST, l < -k)
                                .with(MushroomCapBlock.EAST, l > k).with(MushroomCapBlock.NORTH, m < -k).with(MushroomCapBlock.SOUTH, m > k);
                    }
                    this.setBlockState(world, mutable, blockState);
                }
            }
        }
    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        int k = 0;
        if (y < j && y >= j - 3) {
            k = capSize;
        } else if (y == j) {
            k = capSize;
        }
        return k;
    }
}
