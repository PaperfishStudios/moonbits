package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PebbleBlock extends Block implements Waterloggable {
    public static final int MAX_PEBBLES = 4;
    public static final IntProperty PEBBLES;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape ONE_PEBBLE_SHAPE;
    protected static final VoxelShape TWO_PEBBLES_SHAPE;
    protected static final VoxelShape THREE_PEBBLES_SHAPE;
    protected static final VoxelShape FOUR_PEBBLES_SHAPE;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(PEBBLES, WATERLOGGED);
    }

    public PebbleBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(PEBBLES, 1).with(WATERLOGGED, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(PEBBLES, Math.min(4, blockState.get(PEBBLES) + 1));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean bl = fluidState.getFluid() == Fluids.WATER;
            return super.getPlacementState(ctx).with(WATERLOGGED, bl);
        }
    }
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && state.get(PEBBLES) < 4 || super.canReplace(state, context);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == Direction.DOWN && !canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(world, pos);
        return switch (state.get(PEBBLES)) {
            case 2 -> TWO_PEBBLES_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
            case 3 -> THREE_PEBBLES_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
            case 4 -> FOUR_PEBBLES_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
            default -> ONE_PEBBLE_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
        };
    }

    static {
        PEBBLES = IntProperty.of("pebbles", 1, 4);
        WATERLOGGED = Properties.WATERLOGGED;
        ONE_PEBBLE_SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);
        TWO_PEBBLES_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 3.0D, 13.0D);
        THREE_PEBBLES_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
        FOUR_PEBBLES_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 3.0D, 14.0D);
    }
}
