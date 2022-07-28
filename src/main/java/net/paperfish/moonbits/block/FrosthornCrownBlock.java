package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import org.jetbrains.annotations.Nullable;

public class FrosthornCrownBlock extends Block implements Fertilizable {
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;
    public static final VoxelShape CROWN_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 12, 14);
    public static final VoxelShape STEM_SHAPE = VoxelShapes.union(
            Block.createCuboidShape(2, 4, 2, 14, 16, 14),
            Block.createCuboidShape(4, 0, 4, 12, 4, 12)
    );

    public FrosthornCrownBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(ATTACHED, false));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        return floor.isOf(MBBlocks.FROSTHORN_STEM) || floor.isIn(BlockTags.DIRT) || floor.isIn(MBBlockTags.PLANTER_BOXES);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ATTACHED);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (random.nextInt(12) == 0 && world.getBlockState(pos.up()).isAir()){
            if (world.getBlockState(pos.down()).isOf(MBBlocks.FROSTHORN_STEM)) {
                world.setBlockState(pos.up(), MBBlocks.FROSTHORN_LEAVES.getDefaultState(), NOTIFY_LISTENERS);
            } else if (state.get(ATTACHED)) {
                world.setBlockState(pos, MBBlocks.FROSTHORN_STEM.getDefaultState(), NOTIFY_LISTENERS);
                world.setBlockState(pos.up(), state.with(ATTACHED, true), NOTIFY_LISTENERS);
            } else {
                world.setBlockState(pos, state.with(ATTACHED, true), NOTIFY_LISTENERS);
            }
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState floor = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        if (floor.isOf(this)) {
            ctx.getWorld().setBlockState(ctx.getBlockPos().down(), MBBlocks.FROSTHORN_STEM.getDefaultState());
        }
        return floor.isOf(MBBlocks.FROSTHORN_STEM) ? getDefaultState().with(ATTACHED, true) : getDefaultState();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        BlockState floor = world.getBlockState(pos.down());
        return floor.isOf(MBBlocks.FROSTHORN_STEM) ? getDefaultState().with(ATTACHED, true) : getDefaultState();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(ATTACHED) ? STEM_SHAPE : CROWN_SHAPE;
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {

    }
}
