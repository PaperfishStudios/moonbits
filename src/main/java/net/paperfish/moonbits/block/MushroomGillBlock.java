package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.MBParticles;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MushroomGillBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;
    protected static final VoxelShape NORTH_WALL_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 12.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_WALL_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 4.0D);
    protected static final VoxelShape EAST_WALL_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 4.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_WALL_SHAPE = Block.createCuboidShape(12.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape CEILING_SHAPE = Block.createCuboidShape(2.0, 8.0, 2.0, 14.0, 16.0, 14.0);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(FACING);
    }

    public MushroomGillBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.UP));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        Direction[] var3 = ctx.getPlacementDirections();
        BlockState blockState;
        for (Direction direction : var3) {
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = this.getDefaultState().with(FACING, Direction.UP);
            } else {
                blockState = this.getDefaultState().with(FACING, direction.getOpposite());
            }
            if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                return blockState;
            }
        }

        return null;
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos;
        Direction direction = state.get(FACING);
        if (state.get(FACING) == Direction.UP) {
            blockPos = pos.up();
        } else {
            blockPos = pos.offset(direction.getOpposite());
        }
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(15) == 1) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            double d = (double) i + random.nextDouble();
            double e = (double) j + 0.7D;
            double f = (double) k + random.nextDouble();
            switch (state.get(FACING)) {
                case UP -> world.addParticle(MBParticles.FALLING_SPORES, d, e, f, random.nextDouble() - 0.5, 0.0D, random.nextDouble() - 0.5);
                case NORTH -> world.addParticle(MBParticles.FALLING_SPORES, d, e, k + 0.9D, random.nextDouble() - 0.5, 0.0D, random.nextDouble() / 2);
                case SOUTH -> world.addParticle(MBParticles.FALLING_SPORES, d, e, k + 0.1D, random.nextDouble() - 0.5, 0.0D, -(random.nextDouble() / 2));
                case EAST -> world.addParticle(MBParticles.FALLING_SPORES, i + 0.1D, e, f, -(random.nextDouble() / 2), 0.0D, random.nextDouble() - 0.5);
                case WEST -> world.addParticle(MBParticles.FALLING_SPORES, i + 0.9D, e, f, random.nextDouble() / 2, 0.0D, random.nextDouble() - 0.5);
            }
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {
            return direction == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
        }
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
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
            default -> CEILING_SHAPE;
        };
    }
}
