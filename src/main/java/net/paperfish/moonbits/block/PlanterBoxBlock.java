package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.WorldAccess;

public class PlanterBoxBlock extends Block {
	public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public PlanterBoxBlock(AbstractBlock.Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(ACTIVE, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(ACTIVE);
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return state.get(ACTIVE);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		BlockState up = world.getBlockState(pos.up());
		if (up.getBlock() instanceof Fertilizable) {
			((Fertilizable) up.getBlock()).grow(world, random, pos.up(), up);
		}

		super.randomTick(state, world, pos, random);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP) {
			boolean active = neighborState.getBlock() instanceof Fertilizable;
			return state.with(ACTIVE, active);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
}
