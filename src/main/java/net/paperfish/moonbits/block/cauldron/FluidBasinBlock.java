package net.paperfish.moonbits.block.cauldron;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBBlocks;

public class FluidBasinBlock extends AbstractBasinBlock {
    public final Item bottle;
    public FluidBasinBlock(Settings settings, Item bottle) {
        super(settings);
        this.bottle = bottle;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);
        if (held.isOf(Items.GLASS_BOTTLE)) {
            Item item = held.getItem();
            if (bottle == Items.POTION) {
                player.setStackInHand(hand, ItemUsage.exchangeStack(held, player, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER)));
            }
            else {
                player.setStackInHand(hand, ItemUsage.exchangeStack(held, player, new ItemStack(bottle)));
            }
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, MBBlocks.BASIN.getDefaultState()); // EMPTY BASIN HERE
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}
