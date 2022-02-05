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
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final VoxelShape SHAPE;
    private static final BooleanProperty WATERLOGGED;
    private static final BooleanProperty ATTACHED;

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        ATTACHED = Properties.ATTACHED;
        SHAPE = Block.createCuboidShape(0, 12,0,16,16, 16);
    }

    public ShelfBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH).with(ATTACHED, true).with(WATERLOGGED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING, ATTACHED, WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos attached = ctx.getBlockPos().up().offset(ctx.getPlayerFacing());
        boolean att = ctx.getWorld().getBlockState(attached).isSideSolidFullSquare(ctx.getWorld(), attached, ctx.getPlayerFacing().getOpposite());
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(ATTACHED, att);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        //if (direction == state.get(FACING).getOpposite()) {
            if (neighborState.isSideSolidFullSquare(world, pos.offset(state.get(FACING).getOpposite()).up(), direction.getOpposite())) {
                return state.with(ATTACHED, true);
            }
            else {
                return state.with(ATTACHED, false);
            }
        //}
        //return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
