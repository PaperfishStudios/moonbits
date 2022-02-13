package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public class BarrelCactusBlock extends Block {
    // property that determines how many buckets are in the cactus
    public static final IntProperty LEVEL = IntProperty.of("level", 1, 4);

    public static final VoxelShape TINY = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    public static final VoxelShape SMALL = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
    public static final VoxelShape MEDIUM = Block.createCuboidShape(2, 0, 2, 14, 12, 14);
    public static final VoxelShape LARGE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public BarrelCactusBlock(Settings settings) {
        super(settings);
        this.setDefaultState(stateManager.getDefaultState().with(LEVEL, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LEVEL);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(LEVEL)) {
            default -> TINY;
            case 2 -> SMALL;
            case 3 -> MEDIUM;
            case 4 -> LARGE;
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.shouldCancelInteraction()) {
            // empty bucket into cactus
            if (player.getStackInHand(hand).isOf(Items.WATER_BUCKET) && state.get(LEVEL) < 4) {
                if (!player.isCreative()) {
                    player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                }
                world.setBlockState(pos, state.with(LEVEL, state.get(LEVEL)+1));
                return ActionResult.SUCCESS;
            }
            // fill bucket from cactus
            else if (player.getStackInHand(hand).isOf(Items.BUCKET) && state.get(LEVEL) > 1) {
                if (!player.isCreative()) {
                    player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
                }
                world.setBlockState(pos, state.with(LEVEL, state.get(LEVEL)-1));
                return ActionResult.SUCCESS;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(LEVEL) == 4) {
            return true;
        }
        BlockState below = world.getBlockState(pos.down());
        return Block.isFaceFullSquare(below.getCollisionShape(world, pos.down()), Direction.UP);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(DamageSource.CACTUS, 1.0f);
    }
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
