package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBParticles;
import net.paperfish.moonbits.block.cauldron.HoneyCauldronBlock;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.math.random.Random;

public class TreeTapBlock extends HorizontalFacingBlock {
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;

    public static final VoxelShape NORTH = Block.createCuboidShape(6, 6, 12, 10, 9, 16);
    public static final VoxelShape SOUTH = Block.createCuboidShape(6, 6, 0, 10, 9, 4);
    public static final VoxelShape EAST = Block.createCuboidShape(0, 6, 6, 4, 9, 10);
    public static final VoxelShape WEST = Block.createCuboidShape(12, 6, 6, 16, 9, 10);

    public TreeTapBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(ATTACHED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ATTACHED);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(ATTACHED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            default -> NORTH;
        };
    }

//    @Override
//    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
//        return switch (state.get(FACING)) {
//            case SOUTH -> Block.createCuboidShape(5, 6, 8, 11, 10, 16);
//            case EAST -> Block.createCuboidShape(8, 6, 5, 16, 10, 11);
//            case WEST -> Block.createCuboidShape(0, 6, 5, 8, 10, 11);
//            default -> Block.createCuboidShape(5, 6, 0, 11, 10, 8);
//        };
//    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextInt(15) == 0) {
            BlockPos cauldron = pos;
            while ((world.getBlockState(cauldron).isAir() && (pos.getY() - cauldron.getY() < 10)) || pos == cauldron) {
                cauldron = cauldron.down();
                BlockState cState = world.getBlockState(cauldron);
                if (cState.isOf(Blocks.CAULDRON)) {
                    world.setBlockState(cauldron, MBBlocks.SYRUP_CAULDRON.getDefaultState());
                }
                if (cState.isOf(MBBlocks.SYRUP_CAULDRON) && cState.get(HoneyCauldronBlock.LEVEL) < 4) {
                    world.setBlockState(cauldron, cState.with(HoneyCauldronBlock.LEVEL, cState.get(HoneyCauldronBlock.LEVEL) + 1));
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(ATTACHED) && random.nextInt(8) == 0) {
            float xOffset = state.get(FACING).getAxis() == Direction.Axis.X ? 0.5f - (state.get(FACING).getOffsetX() / 8.0f) : 0.5f;
            float zOffset = state.get(FACING).getAxis() == Direction.Axis.Z ? 0.5f - (state.get(FACING).getOffsetZ() / 8.0f) : 0.5f;
            world.addParticle(MBParticles.DRIPPING_SYRUP, pos.getX() + xOffset, pos.getY() + (3.5f/16f), pos.getZ() + zOffset, 0.0, 0.0, 0.0);
        }
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
        BlockState blockState = this.getDefaultState();
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        for (Direction direction : ctx.getPlacementDirections()) {
            if (!direction.getAxis().isHorizontal() || !(blockState = blockState.with(FACING, direction.getOpposite())).canPlaceAt(world, pos)) continue;
            BlockState connection = world.getBlockState(pos.offset(direction));
            return blockState.with(ATTACHED, connection.isOf(Blocks.BIRCH_LOG));
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        BlockState connection = world.getBlockState(pos.offset(direction));
        return state.with(ATTACHED, connection.isOf(Blocks.BIRCH_LOG));
    }
}
