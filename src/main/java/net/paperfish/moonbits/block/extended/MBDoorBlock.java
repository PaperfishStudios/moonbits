package net.paperfish.moonbits.block.extended;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBSounds;
import org.jetbrains.annotations.Nullable;

public class MBDoorBlock extends DoorBlock {

    public MBDoorBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    private void playOpenCloseSound(World world, BlockPos pos, boolean open) {
        if (!world.isClient) {
            if (this.material == Material.GLASS) {
                world.playSound(null, pos, open ? MBSounds.GLASS_DOOR_OPEN : MBSounds.GLASS_DOOR_CLOSE, SoundCategory.BLOCKS, 1f, 1f);
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.material == Material.GLASS) {
            state = state.cycle(OPEN);
            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
            world.playSound(null, pos, state.get(OPEN) ? MBSounds.GLASS_DOOR_OPEN : MBSounds.GLASS_DOOR_CLOSE, SoundCategory.BLOCKS, 1f, 1f);
            world.emitGameEvent(player, this.isOpen(state) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void setOpen(@Nullable Entity entity, World world, BlockState state, BlockPos pos, boolean open) {
        if (this.material == Material.GLASS) {
            if (!state.isOf(this) || state.get(OPEN) == open) {
                return;
            }
            world.setBlockState(pos, state.with(OPEN, open), Block.NOTIFY_LISTENERS | Block.REDRAW_ON_MAIN_THREAD);
            this.playOpenCloseSound(world, pos, open);
            world.emitGameEvent(entity, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);

        }
        super.setOpen(entity, world, state, pos, open);
    }
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (this.material == Material.GLASS) {
            boolean bl = world.isReceivingRedstonePower(pos) || world.isReceivingRedstonePower(pos.offset(state.get(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN)) || (bl = false);
            if (!this.getDefaultState().isOf(block) && bl != state.get(POWERED)) {
                if (bl != state.get(OPEN)) {
                    this.playOpenCloseSound(world, pos, bl);
                    world.emitGameEvent(bl ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
                }
                world.setBlockState(pos, state.with(POWERED, bl).with(OPEN, bl), Block.NOTIFY_LISTENERS);
            }
        }
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }
}
