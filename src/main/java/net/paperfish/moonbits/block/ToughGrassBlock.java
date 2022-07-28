package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SnowyBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.paperfish.moonbits.registry.MBBlocks;

public class ToughGrassBlock extends SnowyBlock {
    protected ToughGrassBlock(Settings settings) {
        super(settings);
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) < 5) {
            return true;
        }
        if (blockState.isOf(Blocks.SNOW_BLOCK)) {
            return true;
        }
        if (blockState.isSideSolidFullSquare(world, blockPos, Direction.DOWN)) {
            return false;
        }
        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, MBBlocks.TOUGH_DIRT.getDefaultState());
        }
    }
}
