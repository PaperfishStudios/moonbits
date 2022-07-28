package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.jetbrains.annotations.Nullable;

public class OmniRootsBlock extends PlantBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    protected static final VoxelShape NORTH_WALL_SHAPE = Block.createCuboidShape(2.0D, 4.0D, 12.0D, 14.0D, 12.0D, 16.0D);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.createCuboidShape(2.0D, 4.0D, 0.0D, 14.0D, 12.0D, 4.0D);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.createCuboidShape(0.0D, 4.0D, 2.0D, 4.0D, 12.0D, 14.0D);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.createCuboidShape(12.0D, 4.0D, 2.0D, 16.0D, 12.0D, 14.0D);
    public static final VoxelShape FLOOR_SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);
    public static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(2.0, 8.0, 2.0, 14.0, 16.0, 14.0);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }

    public OmniRootsBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] var3 = ctx.getPlacementDirections();
        BlockState blockState;
        for (Direction direction : var3) {
            blockState = this.getDefaultState().with(FACING, direction.getOpposite());
            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }

        return null;
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
		if (direction == Direction.UP && blockState.isIn(MBBlockTags.PLANTER_BOXES)) {
			return true;
		}
        return blockState.isSideSolidFullSquare(world, blockPos, direction) && canPlantOnTop(blockState, world, pos);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.AIR && !this.collidable || super.canPathfindThrough(state, world, pos, type);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case EAST -> EAST_WALL_SHAPE;
            case WEST -> WEST_WALL_SHAPE;
            case SOUTH -> SOUTH_WALL_SHAPE;
            case NORTH -> NORTH_WALL_SHAPE;
            case DOWN -> CEILING_SHAPE;
            default -> FLOOR_SHAPE;
        };
    }
}
