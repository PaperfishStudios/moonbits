package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("deprecation")
@Mixin(BlockItem.class)
public abstract class BlockItemMixin extends Item {
	public BlockItemMixin(Settings settings) {
		super(settings);
	}

	@Inject(method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
	public void blockBerryPlanting(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir){
		ItemStack item = context.getPlayer().getStackInHand(context.getHand());
		if (item.isIn(MBItemTags.BLOCK_PLACEMENT)) {
			cir.setReturnValue(ActionResult.FAIL);
		}
	}
}
