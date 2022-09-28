package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlocks;
import org.jetbrains.annotations.Nullable;

public class UnfiredVaseBlock extends Block {
	public static final VoxelShape SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 12, 14),
			Block.createCuboidShape(4, 12, 4, 12, 15, 12)
	);

	public static Block FIRED_VASE;
	public static final BooleanProperty FIRING = BooleanProperty.of("firing");

	public UnfiredVaseBlock(Block fired, Settings settings) {
		super(settings);
		FIRED_VASE = fired;
		setDefaultState(getDefaultState().with(FIRING, false));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FIRING);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		World world = ctx.getWorld();
		BlockPos pos = ctx.getBlockPos();
		if (world.getBlockState(pos.down()).isOf(Blocks.FIRE)) {
			world.setBlockState(pos, getDefaultState().with(FIRING, true), 3);
			world.scheduleBlockTick(pos, this, 32);
		}
		return super.getPlacementState(ctx);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.DOWN && neighborState.isOf(Blocks.FIRE)) {
			world.setBlockState(pos, state.with(FIRING, true), 3);
			world.scheduleBlockTick(pos, this, 32);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (state.get(FIRING)) {
			world.addParticle(ParticleTypes.SMOKE,
					pos.getX()+random.nextDouble(), pos.getY()+random.nextDouble(), pos.getZ()+random.nextDouble(),
					0.0, 5.0E-4, 0.0);
		}
		super.randomDisplayTick(state, world, pos, random);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (state.get(FIRING) && world.getBlockState(pos.down()).isOf(Blocks.FIRE)) {
			world.setBlockState(pos, FIRED_VASE.getDefaultState());
		}
		super.scheduledTick(state, world, pos, random);
	}
}
