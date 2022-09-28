package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlocks;

public class VaseBlock extends FallingBlock {
	public static final VoxelShape SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 12, 14),
			Block.createCuboidShape(4, 12, 4, 12, 15, 12)
	);
	public static final VoxelShape MUD_SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 10, 14),
			Block.createCuboidShape(4, 10, 4, 12, 13, 12)
	);

	public VaseBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		if (state.isOf(MBBlocks.MUD_VESSEL) || state.isOf(MBBlocks.MUD_VESSEL_REPLICA)) return MUD_SHAPE;
		return SHAPE;
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (world.getBlockState(pos.down()).isOf(Blocks.FIRE)) return;
		world.scheduleBlockTick(pos, this, this.getFallDelay());
	}
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (world.getBlockState(pos.down()).isOf(Blocks.FIRE)) return state;
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	@Override
	public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
		if (world.getBlockState(pos).isOf(Blocks.FIRE)) return;
		world.breakBlock(pos, true);
//		world.removeBlock(pos, false);
//		world.syncWorldEvent(null, 2001, pos, Block.getRawIdFromState(fallingBlockState));
	}
}
