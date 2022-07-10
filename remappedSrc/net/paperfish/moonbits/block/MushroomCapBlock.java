package net.paperfish.moonbits.block;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.paperfish.moonbits.registry.MBItemTags;

import java.util.Map;

public class MushroomCapBlock extends Block {
    public static final BooleanProperty NORTH = ConnectingBlock.NORTH;
    public static final BooleanProperty EAST = ConnectingBlock.EAST;
    public static final BooleanProperty SOUTH = ConnectingBlock.SOUTH;
    public static final BooleanProperty WEST = ConnectingBlock.WEST;
    public static final BooleanProperty UP = ConnectingBlock.UP;
    public static final BooleanProperty DOWN = ConnectingBlock.DOWN;
    private static final Map<Direction, BooleanProperty> FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES;

    public MushroomCapBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(NORTH, true).with(EAST, true).with(SOUTH, true).with(WEST, true).with(UP, true).with(DOWN, true));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Direction direction = hit.getSide();
        BlockPos blockPos = hit.getBlockPos();
        ItemStack heldItem = player.getStackInHand(hand);
        // if player is holding an axe and the side ur facing is not shaved
        if (heldItem.isIn(MBItemTags.AXES) && state.get(FACING_PROPERTIES.get(direction))) {
            world.setBlockState(pos, state.with(FACING_PROPERTIES.get(direction), false));
            if (!world.isClient) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, heldItem);
            }
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!player.isCreative()) {
                heldItem.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            return ActionResult.success(world.isClient);
        }
        // if player is holding bonemeal and the side ur facing is shaved
        if (heldItem.isOf(Items.BONE_MEAL) && !state.get(FACING_PROPERTIES.get(direction))) {
            world.setBlockState(pos, state.with(FACING_PROPERTIES.get(direction), true));
            if (!world.isClient) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, heldItem);
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, pos, 0);
            }
            if (!player.isCreative()) {
                heldItem.decrement(1);
            }
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (state
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.NORTH)), state.get(NORTH)))
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.SOUTH)), state.get(SOUTH))
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.EAST)), state.get(EAST))
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.WEST)), state.get(WEST))
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.UP)), state.get(UP))
                .with(FACING_PROPERTIES.get(rotation.rotate(Direction.DOWN)), state.get(DOWN));
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return (state
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.NORTH)), state.get(NORTH)))
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.SOUTH)), state.get(SOUTH))
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.EAST)), state.get(EAST))
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.WEST)), state.get(WEST))
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.UP)), state.get(UP))
                .with(FACING_PROPERTIES.get(mirror.apply(Direction.DOWN)), state.get(DOWN));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
    }
}
