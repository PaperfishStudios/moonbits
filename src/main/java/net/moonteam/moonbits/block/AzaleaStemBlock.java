package net.moonteam.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.moonteam.moonbits.MBBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"deprecation"})
public class AzaleaStemBlock extends Block {
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final BooleanProperty NORTH;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty WEST;

    protected static final VoxelShape CORE_SHAPE;
    protected static final VoxelShape UP_SHAPE;
    protected static final VoxelShape DOWN_SHAPE;
    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape EAST_SHAPE;
    protected static final VoxelShape WEST_SHAPE;

    public AzaleaStemBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState((((getStateManager().getDefaultState()
                .with(UP, false)).with(DOWN, false)
                .with(NORTH, false)).with(EAST, false))
                .with(SOUTH, false).with(WEST, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape constructShape = CORE_SHAPE;
        if (state.get(UP)){
            constructShape = VoxelShapes.union(constructShape, UP_SHAPE);
        }
        if (state.get(DOWN)){
            constructShape = VoxelShapes.union(constructShape, DOWN_SHAPE);
        }
        if (state.get(NORTH)){
            constructShape = VoxelShapes.union(constructShape, NORTH_SHAPE);
        }
        if (state.get(SOUTH)){
            constructShape = VoxelShapes.union(constructShape, SOUTH_SHAPE);
        }
        if (state.get(EAST)){
            constructShape = VoxelShapes.union(constructShape, EAST_SHAPE);
        }
        if (state.get(WEST)){
            constructShape = VoxelShapes.union(constructShape, WEST_SHAPE);
        }
        return constructShape;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos hitPos = new BlockPos(ctx.getHitPos());
        BlockState blockState = world.getBlockState(blockPos);
        Direction dir = ctx.getSide();
        switch (dir) {
            case UP -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(UP, true));
                blockState = getDefaultState().with(DOWN, true);
            }
            case DOWN -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(DOWN, true));
                blockState = getDefaultState().with(UP, true);
            }
            case NORTH -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(NORTH, true));
                blockState = getDefaultState().with(SOUTH, true);
            }
            case SOUTH -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(SOUTH, true));
                blockState = getDefaultState().with(NORTH, true);
            }
            case EAST -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(EAST, true));
                blockState = getDefaultState().with(WEST, true);
            }
            case WEST -> {
                if (world.getBlockState(hitPos).isOf(MBBlocks.AZALEA_STEM)) world.setBlockState(hitPos, world.getBlockState(hitPos).with(WEST, true));
                blockState = getDefaultState().with(EAST, true);
            }
        }
        return blockState;
    }

    static {
        UP = Properties.UP;
        DOWN = Properties.DOWN;
        NORTH = Properties.NORTH;
        SOUTH = Properties.SOUTH;
        EAST = Properties.EAST;
        WEST = Properties.WEST;

        CORE_SHAPE = Block.createCuboidShape(4.0, 4.0, 4.0, 12.0, 12.0, 12.0);
        UP_SHAPE = Block.createCuboidShape(4.0, 12.0, 4.0, 12.0, 16.0, 12.0);
        DOWN_SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
        NORTH_SHAPE = Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 4.0);
        SOUTH_SHAPE = Block.createCuboidShape(4.0, 4.0, 12.0, 12.0, 12.0, 16.0);
        EAST_SHAPE = Block.createCuboidShape(12.0, 4.0, 4.0, 16.0, 12.0, 12.0);
        WEST_SHAPE = Block.createCuboidShape(0.0, 4.0, 4.0, 4.0, 12.0, 12.0);
    }
}
