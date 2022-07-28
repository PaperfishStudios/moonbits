package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.block.extended.MBRodBlock;

public class ToadstoolStemBlock extends MBRodBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final double SIZE = 6.0; // point the rod starts at
    public static final VoxelShape Y_SHAPE = Block.createCuboidShape(SIZE, 0.0, SIZE, 16.0 - SIZE, 16.0, 16.0 - SIZE);
    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(SIZE, SIZE, 0.0, 16.0 - SIZE, 16.0 - SIZE, 16.0);
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, SIZE, SIZE, 16.0, 16.0 - SIZE, 16.0 - SIZE);

    public ToadstoolStemBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(WATERLOGGED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(WATERLOGGED);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING).getAxis()) {
            default -> {
                return X_SHAPE;
            }
            case Z -> {
                return Z_SHAPE;
            }
            case Y -> {
                return Y_SHAPE;
            }
        }
//        return Y_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
//        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
//        if (blockState.isOf(this) && blockState.get(FACING) == direction) {
//            return this.getDefaultState().with(FACING, direction.getOpposite());
//        }
        return this.getDefaultState().with(FACING, direction);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
//            return Blocks.AIR.getDefaultState();
//        }
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
//        if (direction == Direction.UP && neighborState.isOf(this)) {
//            return state.with(CAP, false);
//        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
