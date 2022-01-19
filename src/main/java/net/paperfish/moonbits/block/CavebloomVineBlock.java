package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.MBBlocks;

import java.util.Random;
import java.util.stream.Stream;

public class CavebloomVineBlock extends AbstractLichenBlock implements Fertilizable, Waterloggable {
    private static final BooleanProperty WATERLOGGED;

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    public CavebloomVineBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    public BlockState swapBlock(BlockState state) {
        return MBBlocks.CAVEBLOOM_FLOWERS.getDefaultState()
                .with(Properties.UP, state.get(Properties.UP))
                .with(Properties.DOWN, state.get(Properties.DOWN))
                .with(Properties.NORTH, state.get(Properties.NORTH))
                .with(Properties.SOUTH, state.get(Properties.SOUTH))
                .with(Properties.EAST, state.get(Properties.EAST))
                .with(Properties.WEST, state.get(Properties.WEST));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int randomNum = random.nextInt(25);
        if (randomNum == 0) {
            int i = 5;
            int j = 4;
            for (BlockPos blockPos : BlockPos.iterate(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
                if (!world.getBlockState(blockPos).isOf(this) || --i > 0) continue;
                return;
            }
            grow(world, random, pos, state);
        }
        if (randomNum == 1 && world.getLightLevel(pos) < 12
                && ((world.getBlockState(pos.north()).isOf(this) && world.getBlockState(pos.south()).isOf(this))
                || (world.getBlockState(pos.east()).isOf(this) && world.getBlockState(pos.west()).isOf(this))
                || (world.getBlockState(pos.up()).isOf(this) && world.getBlockState(pos.down()).isOf(this)))
        ) {
            world.setBlockState(pos, swapBlock(state), 0);
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ActionResult.PASS;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.trySpreadRandomly(state, world, pos, random);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.getStack().isOf(MBBlocks.CAVEBLOOMS) || super.canReplace(state, context);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return Stream.of(DIRECTIONS).anyMatch((direction) -> this.canSpread(state, world, pos, direction.getOpposite()));
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
