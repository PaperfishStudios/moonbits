package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.ComparatorMode;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class BeamBlock extends Block implements Waterloggable {
	public static final EnumProperty<BeamStates> TOP_STATE = EnumProperty.of("top", BeamStates.class);
	public static final EnumProperty<BeamStates> BOTTOM_STATE = EnumProperty.of("bottom", BeamStates.class);
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	protected static final VoxelShape LOWER_X = Block.createCuboidShape(0.0, 0.0, 6.0, 16.0, 4.0, 10.0);
	protected static final VoxelShape LOWER_Z = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 4.0, 16.0);
	protected static final VoxelShape UPPER_X = Block.createCuboidShape(0.0, 12.0, 6.0, 16.0, 16.0, 10.0);
	protected static final VoxelShape UPPER_Z = Block.createCuboidShape(6.0, 12.0, 0.0, 10.0, 16.0, 16.0);

	public BeamBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState()
				.with(TOP_STATE, BeamStates.NONE)
				.with(BOTTOM_STATE, BeamStates.X)
				.with(WATERLOGGED, Boolean.FALSE));
	}
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(TOP_STATE, BOTTOM_STATE, WATERLOGGED);
	}
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		VoxelShape lower = switch (state.get(BOTTOM_STATE)) {
			case X -> LOWER_X;
			case Z -> LOWER_Z;
			case NONE -> VoxelShapes.empty();
		};
		VoxelShape upper = switch (state.get(TOP_STATE)) {
			case X -> UPPER_X;
			case Z -> UPPER_Z;
			case NONE -> VoxelShapes.empty();
		};
		return VoxelShapes.union(lower, upper);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos blockPos = ctx.getBlockPos();
		BlockState blockState = ctx.getWorld().getBlockState(blockPos);
		if (blockState.isOf(this)) {
			Direction direction = ctx.getSide();
			if (direction == Direction.UP) {
				return blockState.with(TOP_STATE, axisToBeam(ctx.getPlayerFacing().rotateYClockwise().getAxis()));
			}
			else if (direction == Direction.DOWN) {
				return blockState.with(BOTTOM_STATE, axisToBeam(ctx.getPlayerFacing().rotateYClockwise().getAxis()));
			}

			return !(ctx.getHitPos().y - (double) blockPos.getY() > 0.5)
					? blockState.with(BOTTOM_STATE, axisToBeam(ctx.getSide().getAxis()))
					: blockState.with(TOP_STATE, axisToBeam(ctx.getSide().getAxis()));
		} else {
			FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
			Direction direction = ctx.getSide();
			if (direction == Direction.DOWN) {
				return this.getDefaultState()
						.with(TOP_STATE, axisToBeam(ctx.getPlayerFacing().rotateYClockwise().getAxis())).with(BOTTOM_STATE, BeamStates.NONE)
						.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
			}
			else if (direction == Direction.UP) {
				return this.getDefaultState()
						.with(BOTTOM_STATE, axisToBeam(ctx.getPlayerFacing().rotateYClockwise().getAxis()))
						.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
			}
			BlockState blockState2 = this.getDefaultState().with(BOTTOM_STATE, axisToBeam(ctx.getSide().getAxis())).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
			return !(ctx.getHitPos().y - (double) blockPos.getY() > 0.5)
					? blockState2
					: blockState2.with(TOP_STATE, axisToBeam(ctx.getSide().getAxis())).with(BOTTOM_STATE, BeamStates.NONE);
		}
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		ItemStack itemStack = context.getStack();
		BeamStates top = state.get(TOP_STATE);
		BeamStates bottom = state.get(BOTTOM_STATE);
		if ((top != BeamStates.NONE && bottom != BeamStates.NONE) || !itemStack.isOf(this.asItem())) {
			return false;
		} else if (context.canReplaceExisting()) {
			boolean bl = context.getHitPos().y - (double)context.getBlockPos().getY() > 0.5;
			Direction direction = context.getSide();
			if (top == BeamStates.NONE) {
				return direction == Direction.UP || bl && direction.getAxis().isHorizontal();
			} else {
				return direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
			}
		} else {
			return true;
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(
			BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos
	) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return state;
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public BeamStates axisToBeam(Direction.Axis axis){
		return switch (axis) {
			case X -> BeamStates.X;
			case Z -> BeamStates.Z;
			case Y -> BeamStates.NONE;
		};
	}
}
