package net.paperfish.moonbits.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallLanternBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final float field_31285 = 2.5f;
    public static final VoxelShape NORTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 1.0, 2.0, 11.0, 8.0, 8.0),
            Block.createCuboidShape(6.0, 8.0, 3.0, 10.0, 10.0, 7.0),
            Block.createCuboidShape(7.0, 13.0, 0.0, 9.0, 15.0, 16.0)
    );
    public static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(5.0, 1.0, 8.0, 11.0, 8.0, 14.0),
            Block.createCuboidShape(6.0, 8.0, 9.0, 10.0, 10.0, 13.0),
            Block.createCuboidShape(7.0, 13.0, 0.0, 9.0, 15.0, 16.0)
    );
    public static final VoxelShape EAST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(8.0, 1.0, 5.0, 14.00, 8.0, 11.0),
            Block.createCuboidShape(9.0, 8.0, 6.0, 13.0, 10.0, 10.0),
            Block.createCuboidShape(0.0, 13.0, 7.0, 16.0, 15.0, 9.0)
    );
    public static final VoxelShape WEST_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2.0, 1.0, 5.0, 8.0, 8.0, 11.0),
            Block.createCuboidShape(3.0, 8.0, 6.0, 7.0, 10.0, 10.0),
            Block.createCuboidShape(0.0, 13.0, 7.0, 16.0, 15.0, 9.0)
    );
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.NORTH, NORTH_SHAPE,
            Direction.SOUTH, SOUTH_SHAPE,
            Direction.WEST, WEST_SHAPE,
            Direction.EAST, EAST_SHAPE
    ));

    public WallLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public String getTranslationKey() {
        return Blocks.LANTERN.getTranslationKey();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.get(FACING));
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
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] directions = ctx.getPlacementDirections();
        BlockState blockState = this.getDefaultState();
        World worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        for (Direction direction : directions) {
            Direction opposite = direction.getOpposite();
            if (!direction.getAxis().isHorizontal() || !(blockState = blockState.with(FACING, opposite)).canPlaceAt(worldView, blockPos)) continue;
            return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
