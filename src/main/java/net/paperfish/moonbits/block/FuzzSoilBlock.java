package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBEvents;

import java.util.Map;

public class FuzzSoilBlock extends Block {

	public FuzzSoilBlock(Settings settings) {
		super(settings);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		super.randomTick(state, world, pos, random);
		BlockState above = world.getBlockState(pos.up());
		BlockState below = world.getBlockState(pos.down());
		float rand = random.nextFloat();
		if (rand < 0.125f) {
			// decompose!
			if (above.isOf(MBBlocks.FUZZ_SHROOMS) && state.isOf(MBBlocks.FUZZY_DIRT)) {
				world.setBlockState(pos, MBBlocks.DECOMPOSING_DIRT.getDefaultState());
			}
			// decompose more!
			else if (above.isOf(MBBlocks.FUZZ_SHROOMS) && state.isOf(MBBlocks.DECOMPOSING_DIRT)) {
				world.setBlockState(pos, MBBlocks.FUZZ_ROOTS.getDefaultState());
			}
			// fuzz it!
			else if (state.isOf(MBBlocks.DECOMPOSING_DIRT) || state.isOf(MBBlocks.FUZZ_ROOTS)) {
				MBEvents.FUZZ_GROWING.forEach((shroom, result) -> {
					if (above.isOf(shroom)) {
						world.setBlockState(pos, result.getDefaultState());
					}
				});
			}
		}
		else if (rand < 0.25f && below.isIn(BlockTags.CAMPFIRES)) {
			// bake in-world with a campfire!
			world.setBlockState(pos, MBEvents.FUZZ_BAKING.get(state.getBlock()).getDefaultState());
		}
		else if (rand < 0.375f) {
			// this is for spreading the roots to adjacent dirt blocks!
			MBEvents.FUZZ_GROWING.forEach((shroom, result) -> {
				BlockPos place = pos.add(random.nextInt(3)-1, 0, random.nextInt(3)-1);
				if (above.isOf(shroom) && state.isOf(result) && world.getBlockState(place).isOf(Blocks.DIRT)) {
					world.setBlockState(place, state);
				}
			});
		}
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}
}
