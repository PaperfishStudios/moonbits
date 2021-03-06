package net.paperfish.moonbits.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {

    // replace all the checks for bookshelf with a check for the bookshelf *tag*
    @Redirect(method = "isValidForBookshelf(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private static boolean hookOnContentChanged(BlockState state, Block block) {
		return state.isIn(ConventionalBlockTags.BOOKSHELVES);
	}
    // replace all the checks for air with a check for transparent blocks
    @Redirect(method = "isValidForBookshelf(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isAir(Lnet/minecraft/util/math/BlockPos;)Z"))
	private static boolean transparentBlocks(World world, BlockPos pos) {
		return !world.getBlockState(pos).isFullCube(world, pos);
	}
}
