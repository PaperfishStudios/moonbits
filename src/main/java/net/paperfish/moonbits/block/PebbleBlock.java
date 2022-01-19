package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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
        setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(PEBBLES, 1)).with(WATERLOGGED, false));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return (BlockState)blockState.with(PEBBLES, Math.min(4, (Integer)blockState.get(PEBBLES) + 1));
        } else {
            FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
            boolean bl = fluidState.getFluid() == Fluids.WATER;
            return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl);
        }
    }
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().getItem() == this.asItem() && (Integer) state.get(PEBBLES) < 4 || super.canReplace(state, context);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch((Integer)state.get(PEBBLES)) {
            case 1:
            default:
                return ONE_PEBBLE_SHAPE;
            case 2:
                return TWO_PEBBLES_SHAPE;
            case 3:
                return THREE_PEBBLES_SHAPE;
            case 4:
                return FOUR_PEBBLES_SHAPE;
        }
    }

    static {
        PEBBLES = IntProperty.of("pebbles", 1, 4);
        WATERLOGGED = Properties.WATERLOGGED;
        ONE_PEBBLE_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
        TWO_PEBBLES_SHAPE = Block.createCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
        THREE_PEBBLES_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
        FOUR_PEBBLES_SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
    }
}
