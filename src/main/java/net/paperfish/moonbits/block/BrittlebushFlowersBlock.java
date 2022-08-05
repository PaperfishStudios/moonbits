package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BrittlebushFlowersBlock extends FlowerBlock {
	public static final DirectionProperty FACING = Properties.FACING;
	int height = 3;
	int width = 4;
	protected final VoxelShape upShape = Block.createCuboidShape(width, 0.0, width, (16 - width), height, (16 - width));
	protected final VoxelShape downShape = Block.createCuboidShape(width, (16 - height), width, (16 - width), 16.0, (16 - width));
	protected final VoxelShape northShape = Block.createCuboidShape(width, width, (16 - height), (16 - width), (16 - width), 16.0);
	protected final VoxelShape southShape = Block.createCuboidShape(width, width, 0.0, (16 - width), (16 - width), height);
	protected final VoxelShape eastShape = Block.createCuboidShape(0.0, width, width, height, (16 - width), (16 - width));
	protected final VoxelShape westShape = Block.createCuboidShape((16 - height), width, width, 16.0, (16 - width), (16 - width));

	public BrittlebushFlowersBlock(StatusEffect statusEffect, int i, Settings settings) {
		super(statusEffect, i, settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = state.get(FACING);
		return switch (direction) {
			case NORTH -> this.northShape;
			case SOUTH -> this.southShape;
			case EAST -> this.eastShape;
			case WEST -> this.westShape;
			case DOWN -> this.downShape;
			case UP -> this.upShape;
		};
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		return direction == state.get(FACING).getOpposite() && !state.canPlaceAt(world, pos)
				? Blocks.AIR.getDefaultState()
				: super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getSide());
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public PistonBehavior getPistonBehavior(BlockState state) {
		return PistonBehavior.DESTROY;
	}
}
