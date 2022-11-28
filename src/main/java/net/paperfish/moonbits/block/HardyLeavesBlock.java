package net.paperfish.moonbits.block;

import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBBlocks;
import com.google.common.collect.BiMap;
import net.paperfish.moonbits.registry.MBEvents;
import net.paperfish.moonbits.registry.MBItems;

public class HardyLeavesBlock extends LeavesBlock {
    public Progress progress;

    public HardyLeavesBlock(Progress progress, Settings settings) {
        super(settings);
        this.progress = progress;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (!state.get(LeavesBlock.PERSISTENT) && progress != Progress.FRUITING && random.nextInt(9) == 0) {
            Block grow = MBEvents.GROWING.get(state.getBlock());
            if (grow != null) {
                world.setBlockState(pos, grow.getStateWithProperties(state));
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.get(PERSISTENT) && player.getStackInHand(hand).isOf(Items.SHEARS)){
            world.setBlockState(pos, state.with(PERSISTENT, true));
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
            if (!player.isCreative()) player.getStackInHand(hand).damage(1, player, p -> p.sendToolBreakStatus(hand));
            return ActionResult.success(world.isClient());
        }
        else if (!state.get(PERSISTENT) && progress == Progress.FRUITING) {
            Block plucked = revertToBase(state);
            if (plucked != null) {
//                HardyLeavesBlock.dropStack(world, pos, new ItemStack(MBItems.HARDY_BERRY, world.random.nextInt(3)+1));
                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                BlockState blockState = plucked.getStateWithProperties(state);
                world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.create(player, blockState));
                return ActionResult.success(world.isClient());
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public Block revertToBase(BlockState state) {
        Block pluck = MBEvents.PLUCK.get(state.getBlock());
        Block newState = null;
        while (pluck != null) { // go aaaaall the way back
            newState = pluck;
            pluck = MBEvents.PLUCK.get(newState);
        }
        return newState;
    }

    public enum Progress {
        EMPTY,
        FLOWERING,
        FRUITING
    }
}
