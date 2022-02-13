package net.paperfish.moonbits.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;

public class GrassTurfStairsBlock extends StairsBlock implements Fertilizable {	
	
	public GrassTurfStairsBlock(BlockState state, AbstractBlock.Settings settings) {
        super(state, settings);
	}

    @Override
    public boolean isFertilizable(BlockView view, BlockPos pos, BlockState state, boolean isClient) {
		return view.getBlockState(pos.up()).isAir() && state.get(HALF) == BlockHalf.TOP;
	}

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
		BlockState blockState = Blocks.GRASS.getDefaultState();

		label46:
		for(int i = 0; i < 128; ++i) {
			BlockPos blockPos2 = blockPos;

			for(int j = 0; j < i / 16; ++j) {
				blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
				if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
					continue label46;
				}
			}

			BlockState j = world.getBlockState(blockPos2);
			if (j.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
				((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, j);
			}

			if (j.isAir()) {
				PlacedFeature placedFeature;
				if (random.nextInt(8) == 0) {
					List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).getGenerationSettings().getFlowerFeatures();
					if (list.isEmpty()) {
						continue;
					}

					placedFeature = ((RandomPatchFeatureConfig)list.get(0).getConfig()).feature().get();
				} else {
					placedFeature = VegetationPlacedFeatures.GRASS_BONEMEAL;
				}

				placedFeature.generateUnregistered(world, world.getChunkManager().getChunkGenerator(), random, blockPos2);
			}
		}
	}

}
