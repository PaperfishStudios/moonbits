package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class DimWeatheringStairsBlock extends StairsBlock implements DimensionalWeathering {
    public final Block overworld;
    public final Block nether;
    public final Block end;
    public DimWeatheringStairsBlock(Block overworld, Block nether, Block end, BlockState base, Settings settings) {
        super(base, settings);
        this.overworld = overworld;
        this.nether = nether;
        this.end = end;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state, ServerWorld world) {
        if (world.getDimension().piglinSafe()) {
            return Optional.of(nether.getStateWithProperties(state));
        }
        else if (!world.getDimension().bedWorks()) {
            return Optional.of(end.getStateWithProperties(state));
        }
        else {
            return Optional.of(overworld.getStateWithProperties(state));
        }
    }

    @Override
    public float getDegradationChanceMultiplier() {
        return 1.0f;
    }
}
