package net.paperfish.moonbits.block;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Degradable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Supplier;

public interface DimensionalWeathering {
    int influenceZone = 4;

    Optional<BlockState> getDegradationResult(BlockState state, ServerWorld world);

    float getDegradationChanceMultiplier();

    default void tickDegradation(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.05688889f) {
            this.tryDegrade(state, world, pos, random);
        }
    }

//    public T getDegradationLevel();

    default public void tryDegrade(BlockState state2, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos;
//        int i = ((Enum)this.getDegradationLevel()).ordinal();
        int j = 0;
        int k = 0;
        Iterator<BlockPos> iterator = BlockPos.iterateOutwards(pos, influenceZone, influenceZone, influenceZone).iterator();
        while (iterator.hasNext() && (blockPos = iterator.next()).getManhattanDistance(pos) <= influenceZone) {
            Block block;
            if (blockPos.equals(pos) || !(world.getBlockState(blockPos).getBlock() instanceof DimensionalWeathering)) continue;
//            T enum_ = ((DimensionalWeathering) block).getDegradationLevel();
//            if (this.getDegradationLevel().getClass() != enum_.getClass()) continue;
//            int m = ((Enum)enum_).ordinal();
//            if (m < i) {
//                return;
//            }
//            if (m > i) {
//                ++k;
//                continue;
//            }
            ++j;
        }
        float f = (float)(k + 1) / (float)(k + j + 1);
        float g = f * f * this.getDegradationChanceMultiplier();
        if (random.nextFloat() < g) {
            this.getDegradationResult(state2, world).ifPresent(state -> world.setBlockState(pos, state));
        }
    }

}
