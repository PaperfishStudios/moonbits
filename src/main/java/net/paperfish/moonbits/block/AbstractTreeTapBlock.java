package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBEvents;
import net.paperfish.moonbits.registry.MBParticles;
import org.jetbrains.annotations.Nullable;

public class AbstractTreeTapBlock extends HorizontalFacingBlock {
	public static final BooleanProperty ATTACHED = Properties.ATTACHED;

	public static final VoxelShape NORTH = VoxelShapes.union(
			Block.createCuboidShape(6, 10, 11, 10, 13, 16),
			Block.createCuboidShape(5, 0, 5, 11, 8, 11)
	);
	public static final VoxelShape SOUTH = VoxelShapes.union(
			Block.createCuboidShape(6, 10, 0, 10, 13, 5),
			Block.createCuboidShape(5, 0, 5, 11, 8, 11)
	);
	public static final VoxelShape EAST = VoxelShapes.union(
			Block.createCuboidShape(0, 10, 6, 5, 13, 10),
			Block.createCuboidShape(5, 0, 5, 11, 8, 11)
	);
	public static final VoxelShape WEST = VoxelShapes.union(
			Block.createCuboidShape(11, 10, 6, 16, 13, 10),
			Block.createCuboidShape(5, 0, 5, 11, 8, 11)
	);

	protected AbstractTreeTapBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (state.get(FACING)) {
			case SOUTH -> SOUTH;
			case EAST -> EAST;
			case WEST -> WEST;
			default -> NORTH;
		};
	}

	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, RandomGenerator random) {
		if (state.get(ATTACHED) && random.nextInt(8) == 0) {
			float xOffset = state.get(FACING).getAxis() == Direction.Axis.X ? 0.5f - (state.get(FACING).getOffsetX() / 8.0f) : 0.5f;
			float zOffset = state.get(FACING).getAxis() == Direction.Axis.Z ? 0.5f - (state.get(FACING).getOffsetZ() / 8.0f) : 0.5f;
			world.addParticle(MBParticles.DRIPPING_SYRUP, pos.getX() + xOffset, pos.getY() + (8.5f/16f), pos.getZ() + zOffset, 0.0, 0.0, 0.0);
		}
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSideSolidFullSquare(world, blockPos, direction);
	}
	@Override
	@Nullable
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockState = this.getDefaultState();
		World world = ctx.getWorld();
		BlockPos pos = ctx.getBlockPos();
		for (Direction direction : ctx.getPlacementDirections()) {
			if (!direction.getAxis().isHorizontal() || !(blockState = blockState.with(FACING, direction.getOpposite())).canPlaceAt(world, pos)) continue;
			BlockState connection = world.getBlockState(pos.offset(direction));
			return blockState.with(ATTACHED, isTappable(connection));
		}
		return null;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
			return Blocks.AIR.getDefaultState();
		}
		BlockState connection = world.getBlockState(pos.offset(direction));
		return state.with(ATTACHED, isTappable(connection));
	}

	public boolean isTappable(BlockState state) {
		return MBEvents.TAPPABLE.containsKey(state.getBlock());
	}
}
