package net.paperfish.moonbits.mixin;

import net.paperfish.moonbits.MBBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

    // replace all the checks for air with a check for transparent blocks, allowing for blocks like carpets to be placed between the table and bookshelves
	@Redirect(method = "method_17411", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isAir(Lnet/minecraft/util/math/BlockPos;)Z"))
	private boolean transparentBlocks(World world, BlockPos pos) {
		System.out.println(!world.getBlockState(pos).isFullCube(world, pos));
		return !world.getBlockState(pos).isFullCube(world, pos);
		//return world.getBlockState(pos).isIn(MBData.BOOKSHELVES);
	}

    // replace all the checks for bookshelf with a check for a tag including all valid blocks for an enchanting table setup
	@Redirect(method = "method_17411", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean enchantingBlocks(BlockState state, Block block) {
		// the visual side of this only checks for bookshelves at the moment! if you wanna change that make sure to change the other mixin
		return state.isIn(MBBlockTags.VALID_ENCHANTERS);
	}
	
}
