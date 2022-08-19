package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBEvents;

public class FilledTreeTapBlock extends AbstractTreeTapBlock {
	public final boolean bottle;

	public FilledTreeTapBlock(boolean bottle, Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState().with(ATTACHED, false));
		this.bottle = bottle;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, ATTACHED);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack itemStack = player.getStackInHand(hand);
		Item result = MBEvents.TAP_RESULT.get(state.getBlock());
		if (bottle && player.getStackInHand(hand).isOf(Items.GLASS_BOTTLE)) {
			Item item = itemStack.getItem();
			itemStack.decrement(1);
			world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			if (itemStack.isEmpty()) {
				player.setStackInHand(hand, new ItemStack(result));
			} else if (!player.getInventory().insertStack(new ItemStack(result))) {
				player.dropItem(new ItemStack(result), false);
			}
			world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
			if (!world.isClient()) {
				player.incrementStat(Stats.USED.getOrCreateStat(item));
			}

			world.setBlockState(pos, MBBlocks.TREE_TAP.getStateWithProperties(state).with(TreeTapBlock.FILL_LEVEL, 0));

			return ActionResult.success(world.isClient());
		}
		else if (!bottle) {
			world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			dropStack(world, pos, new ItemStack(result, 3));

			world.setBlockState(pos, MBBlocks.TREE_TAP.getStateWithProperties(state).with(TreeTapBlock.FILL_LEVEL, 0));

			return ActionResult.success(world.isClient());
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}

	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		return new ItemStack(MBBlocks.TREE_TAP);
	}
}
