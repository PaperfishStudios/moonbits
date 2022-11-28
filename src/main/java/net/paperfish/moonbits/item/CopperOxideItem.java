package net.paperfish.moonbits.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlocks;

import java.util.Optional;

public class CopperOxideItem extends Item {

	public CopperOxideItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext ctx) {
		World world = ctx.getWorld();
		BlockPos pos = ctx.getBlockPos();
		BlockState state = world.getBlockState(pos);

		if (state.isOf(Blocks.LANTERN) || state.isOf(Blocks.SOUL_LANTERN)) {
			// TODO: Add Oxidize Sound
			world.setBlockState(pos, MBBlocks.COPPER_OXIDE_LANTERN.getStateWithProperties(state));
			ctx.getStack().decrement(1);
			return ActionResult.success(world.isClient);
		}
		if (state.isOf(Blocks.CAMPFIRE) || state.isOf(Blocks.SOUL_CAMPFIRE)) {
			// TODO: Add Oxidize Sound
			world.setBlockState(pos, MBBlocks.COPPER_OXIDE_CAMPFIRE.getStateWithProperties(state));
			ctx.getStack().decrement(1);
			return ActionResult.success(world.isClient);
		}
		// Copper Blocks oxidizing :D
		getResult(state.getBlock()).ifPresent(result -> world.setBlockState(pos, result.getStateWithProperties(state)));
		if (world.getBlockState(pos) != state) {
			// TODO: Add Oxidize Sound
			ctx.getStack().decrement(1);
			return ActionResult.success(world.isClient);
		}

		return super.useOnBlock(ctx);
	}

	static Optional<Block> getResult(Block block) {
		return Optional.ofNullable(Oxidizable.OXIDATION_LEVEL_INCREASES.get().get(block));
	}
}
