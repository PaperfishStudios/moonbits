package net.paperfish.moonbits.block;

import net.minecraft.block.AmethystClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.paperfish.moonbits.registry.MBBlocks;

public class BrittlebushLeavesBlock extends Block {
	public BrittlebushLeavesBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (random.nextInt(5) == 0) {
			Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
			BlockPos blockPos = pos.offset(direction);
			BlockState blockState = world.getBlockState(blockPos);
			if (blockState.isAir()) {
				BlockState blockState2 = MBBlocks.BRITTLEBUSH_FLOWERS.getDefaultState().with(BrittlebushFlowersBlock.FACING, direction);
				world.setBlockState(blockPos, blockState2);
			}
		}
	}
}
