package net.paperfish.moonbits.block;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;

import net.minecraft.util.math.random.Random;
import net.paperfish.moonbits.registry.MBEvents;

public class BarrelCactusBlock extends Block {
    // property that determines how many buckets are in the cactus
    public final Size SIZE;
//    public static final IntProperty LEVEL = IntProperty.of("level", 1, 4);

    public static final VoxelShape TINY = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    public static final VoxelShape SMALL = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
    public static final VoxelShape MEDIUM = Block.createCuboidShape(2, 0, 2, 14, 12, 14);
    public static final VoxelShape LARGE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);

    public BarrelCactusBlock(Size size, Settings settings) {
        super(settings);
//        this.setDefaultState(stateManager.getDefaultState().with(LEVEL, 1));
        this.SIZE = size;
    }

//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        super.appendProperties(builder);
//        builder.add(LEVEL);
//    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return switch (SIZE) {
            default -> 0;
            case SMALL -> 1;
            case MEDIUM -> 2;
            case LARGE -> 3;
        };
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (SIZE) {
            default -> TINY;
            case SMALL -> SMALL;
            case MEDIUM -> MEDIUM;
            case LARGE -> LARGE;
        };
    }

//    @Override
//    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        if (!world.isClient && !player.isCreative() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
//            ItemStack itemStack = new ItemStack(this);
//            int i = state.get(LEVEL);
//            if (i > 0) {
//                NbtCompound nbtCompound;
//                nbtCompound = new NbtCompound();
//                nbtCompound.putInt(LEVEL.getName(), state.get(LEVEL));
//                itemStack.setSubNbt("BlockStateTag", nbtCompound);
//                ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
//                itemEntity.setToDefaultPickupDelay();
//                world.spawnEntity(itemEntity);
//            }
//        }
//        super.onBreak(world, pos, state, player);
//    }
//
//    @Override
//    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
//        ItemStack itemStack = super.getPickStack(world, pos, state);
//        if (state.get(LEVEL) > 0) {
//            NbtCompound nbtCompound = new NbtCompound();
//            nbtCompound.putInt(LEVEL.getName(), state.get(LEVEL));
//            itemStack.setSubNbt("BlockStateTag", nbtCompound);
//        }
//        return itemStack;
//    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.shouldCancelInteraction()) {
            // empty bucket into cactus
            if (player.getStackInHand(hand).isOf(Items.WATER_BUCKET) && SIZE.ordinal() < 3) {
                if (advance(state) != null) {
                    if (!player.isCreative()) {
                        player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    }
                    player.playSound(SoundEvents.ITEM_BUCKET_EMPTY, 1.0f, 1.0f);
                    player.incrementStat(Stats.USED.getOrCreateStat(Items.WATER_BUCKET));
                    world.setBlockState(pos, advance(state).getStateWithProperties(state));
                    return ActionResult.SUCCESS;
                }
            }
            // fill bucket from cactus
            else if (player.getStackInHand(hand).isOf(Items.BUCKET) && SIZE.ordinal() > 0) {
                if (revert(state) != null) {
                    if (!player.isCreative()) {
                        player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
                    }
                    if (!world.isClient) {
                        Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)player, player.getStackInHand(hand));
                    }
                    player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0f, 1.0f);
                    player.incrementStat(Stats.USED.getOrCreateStat(Items.BUCKET));
                    world.setBlockState(pos, revert(state).getStateWithProperties(state));
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (SIZE == Size.LARGE) {
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
        if (entity.getType() == EntityType.ITEM && !world.getBlockState(pos.down()).isIn(BlockTags.SOUL_SPEED_BLOCKS)) {
            return; // items only get destroyed if cactus on soulsand
        }
        entity.damage(DamageSource.CACTUS, 1.0f);
    }
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    public Block advance(BlockState state) {
        return MBEvents.GROWING.get(state.getBlock());
    }
    public Block revert(BlockState state) {
        return MBEvents.PLUCK.get(state.getBlock());
    }

    public enum Size {
        TINY, SMALL, MEDIUM, LARGE
    }
}
