package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.paperfish.moonbits.block.extended.MBTrapdoorBlock;

import java.util.Optional;

public class DimWeatheringTrapdoorBlock extends MBTrapdoorBlock implements DimensionalWeathering {
    public final Block overworld;
    public final Block nether;
    public final Block end;
    public DimWeatheringTrapdoorBlock(Block overworld, Block nether, Block end, Settings settings) {
        super(settings);
        this.overworld = overworld;
        this.nether = nether;
        this.end = end;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public Optional<BlockState> getDegradationResult(BlockState state, ServerWorld world) {
        if (world.getDimension().isPiglinSafe()) {
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
