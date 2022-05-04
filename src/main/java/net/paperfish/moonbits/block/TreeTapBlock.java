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
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBParticles;
import net.paperfish.moonbits.block.cauldron.HoneyCauldronBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class TreeTapBlock extends HorizontalFacingBlock {
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;

    public static final VoxelShape NORTH = (VoxelShapes.union(
            Block.createCuboidShape(9, 8, 0, 11, 10, 8),
            Block.createCuboidShape(5, 6, 0, 11, 8, 8),
            Block.createCuboidShape(5,8, 0, 7, 10, 8)));
    public static final VoxelShape SOUTH = (VoxelShapes.union(
            Block.createCuboidShape(9, 8, 8, 11, 10, 16),
            Block.createCuboidShape(5, 6, 8, 11, 8, 16),
            Block.createCuboidShape(5,8, 8, 7, 10, 16)));
    public static final VoxelShape EAST = (VoxelShapes.union(
            Block.createCuboidShape(8, 8, 9, 16, 10, 11),
            Block.createCuboidShape(8, 6, 5, 16, 8, 11),
            Block.createCuboidShape(8,8, 5, 16, 10, 7)));
    public static final VoxelShape WEST = (VoxelShapes.union(
            Block.createCuboidShape(0, 8, 9, 8, 10, 11),
            Block.createCuboidShape(0, 6, 5, 8, 8, 11),
            Block.createCuboidShape(0,8, 5, 8, 10, 7)));

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

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return switch (state.get(FACING)) {
            case SOUTH -> Block.createCuboidShape(5, 6, 8, 11, 10, 16);
            case EAST -> Block.createCuboidShape(8, 6, 5, 16, 10, 11);
            case WEST -> Block.createCuboidShape(0, 6, 5, 8, 10, 11);
            default -> Block.createCuboidShape(5, 6, 0, 11, 10, 8);
        };
    }

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
            float xOffset = state.get(FACING).getAxis() == Direction.Axis.X ? 0.5f + (-state.get(FACING).getOffsetX() / 32.0f) : 0.5f;
            float zOffset = state.get(FACING).getAxis() == Direction.Axis.Z ? 0.5f + (-state.get(FACING).getOffsetZ() / 32.0f) : 0.5f;
            world.addParticle(MBParticles.DRIPPING_SYRUP, pos.getX() + xOffset, pos.getY() + (6/16f), pos.getZ() + zOffset, 0.0, 0.0, 0.0);
        }
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        for (Direction direction : ctx.getPlacementDirections()) {
            if (!direction.getAxis().isHorizontal()) continue;
            BlockState connection = world.getBlockState(pos.offset(direction));
            if (!ctx.shouldCancelInteraction() && connection.getBlock() instanceof TreeTapBlock && direction.getAxis() == connection.get(FACING).getAxis()) {
                return blockState.with(FACING, connection.get(FACING).getOpposite());
            }
            return blockState.with(FACING, direction).with(ATTACHED, connection.isOf(Blocks.BIRCH_LOG));
        }
        return null;
    }
}
