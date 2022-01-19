package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class GrowingLeavesBlock extends LeavesBlock implements Fertilizable {
    public Block next;

    public GrowingLeavesBlock(Settings settings, Block next) {
        super(settings);
        this.next = next;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9 && random.nextInt(10) == 0 && !state.get(PERSISTENT)) {
            world.setBlockState(pos, next.getDefaultState(), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (random.nextInt(10) == 0 && !state.get(PERSISTENT)) {
            world.setBlockState(pos, next.getDefaultState(), Block.NOTIFY_LISTENERS);
        }
    }
}
