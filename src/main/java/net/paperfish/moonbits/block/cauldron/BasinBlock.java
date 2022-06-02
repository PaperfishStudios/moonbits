package net.paperfish.moonbits.block.cauldron;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItems;

public class BasinBlock extends AbstractBasinBlock {
    public BasinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);
        Item item = held.getItem();
        if (held.isOf(Items.POTION) && PotionUtil.getPotion(held) == Potions.WATER) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(held, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, MBBlocks.WATER_BASIN.getDefaultState()); // WATER BASIN
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        else if (held.isOf(Items.HONEY_BOTTLE)) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(held, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, MBBlocks.HONEY_BASIN.getDefaultState()); // HONEY BASIN
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        else if (held.isOf(MBItems.SYRUP_BOTTLE)) {
            player.setStackInHand(hand, ItemUsage.exchangeStack(held, player, new ItemStack(Items.GLASS_BOTTLE)));
            player.incrementStat(Stats.USE_CAULDRON);
            player.incrementStat(Stats.USED.getOrCreateStat(item));
            world.setBlockState(pos, MBBlocks.SYRUP_BASIN.getDefaultState()); // SYRUP BASIN
            world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    protected boolean canBeFilledByDripstone(Fluid fluid) {
        return true;
    }

    protected void fillFromDripstone(BlockState state, World world, BlockPos pos, Fluid fluid) {
        if (fluid == Fluids.WATER) {
            world.setBlockState(pos, MBBlocks.WATER_BASIN.getDefaultState());
            world.syncWorldEvent(WorldEvents.POINTED_DRIPSTONE_DRIPS_WATER_INTO_CAULDRON, pos, 0);
            world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
        }
    }
}
