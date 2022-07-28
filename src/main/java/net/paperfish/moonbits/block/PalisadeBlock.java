package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.WallShape;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PalisadeBlock extends Block implements Waterloggable {
    public static final BooleanProperty NORTH = Properties.NORTH;
    public static final BooleanProperty SOUTH = Properties.SOUTH;
    public static final BooleanProperty EAST = Properties.EAST;
    public static final BooleanProperty WEST = Properties.WEST;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    private static final VoxelShape POST_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(4.0, 0.0, 0.0, 12.0, 16.0, 4.0);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(4.0, 0.0, 12.0, 12.0, 16.0, 16.0);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(12.0, 0.0, 4.0, 16.0, 16.0, 12.0);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0.0, 0.0, 4.0, 4.0, 16.0, 12.0);

    public PalisadeBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        BlockPos north = pos.north();
        BlockPos east = pos.east();
        BlockPos south = pos.south();
        BlockPos west = pos.west();

        BlockState northState = world.getBlockState(north);
        BlockState eastState = world.getBlockState(east);
        BlockState southState = world.getBlockState(south);
        BlockState westState = world.getBlockState(west);

        boolean bl = this.shouldConnectTo(northState, northState.isSideSolidFullSquare(world, north, Direction.SOUTH), Direction.SOUTH);
        boolean bl2 = this.shouldConnectTo(eastState, eastState.isSideSolidFullSquare(world, east, Direction.WEST), Direction.WEST);
        boolean bl3 = this.shouldConnectTo(southState, southState.isSideSolidFullSquare(world, south, Direction.NORTH), Direction.NORTH);
        boolean bl4 = this.shouldConnectTo(westState, westState.isSideSolidFullSquare(world, west, Direction.EAST), Direction.EAST);
        return this.getDefaultState()
                .with(NORTH, bl)
                .with(EAST, bl2)
                .with(SOUTH, bl3)
                .with(WEST, bl4)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == Direction.NORTH) {
            return state.with(NORTH, this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, Direction.SOUTH), Direction.SOUTH));
        }
        if (direction == Direction.EAST) {
            return state.with(EAST, this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, Direction.WEST), Direction.WEST));
        }
        if (direction == Direction.SOUTH) {
            return state.with(SOUTH, this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, Direction.NORTH), Direction.NORTH));
        }
        if (direction == Direction.WEST) {
            return state.with(WEST, this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, Direction.EAST), Direction.EAST));
        }
        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = POST_SHAPE;
        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, NORTH_SHAPE);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, SOUTH_SHAPE);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, EAST_SHAPE);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, WEST_SHAPE);
        }
        return shape;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private boolean shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side) {
        Block block = state.getBlock();
        boolean bl = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
        return block instanceof PalisadeBlock || !WallBlock.cannotConnect(state) && faceFullSquare || block instanceof PaneBlock || bl;
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180 -> {
                return state.with(NORTH, state.get(SOUTH)).with(EAST, state.get(WEST)).with(SOUTH, state.get(NORTH)).with(WEST, state.get(EAST));
            }
            case COUNTERCLOCKWISE_90 -> {
                return state.with(NORTH, state.get(EAST)).with(EAST, state.get(SOUTH)).with(SOUTH, state.get(WEST)).with(WEST, state.get(NORTH));
            }
            case CLOCKWISE_90 -> {
                return state.with(NORTH, state.get(WEST)).with(EAST, state.get(NORTH)).with(SOUTH, state.get(EAST)).with(WEST, state.get(SOUTH));
            }
        }
        return state;
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT -> {
                return state.with(NORTH, state.get(SOUTH)).with(SOUTH, state.get(NORTH));
            }
            case FRONT_BACK -> {
                return state.with(EAST, state.get(WEST)).with(WEST, state.get(EAST));
            }
        }
        return super.mirror(state, mirror);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return !state.get(WATERLOGGED);
    }
}
