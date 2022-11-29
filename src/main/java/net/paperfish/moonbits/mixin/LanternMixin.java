package net.paperfish.moonbits.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Direction;
import net.paperfish.moonbits.registry.MBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LanternBlock.class)
public class LanternMixin {

    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    public void wallPlacement(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        if (ctx.getSide().getAxis() != Direction.Axis.Y) {
            for (Direction direction : ctx.getPlacementDirections()) {
				ItemStack item = ctx.getStack();
				if (direction.getAxis() != Direction.Axis.Y) {
					if (item.isOf(Items.LANTERN)) {
						cir.setReturnValue(MBBlocks.WALL_LANTERN.getPlacementState(ctx));
					}
					else if (item.isOf(Items.SOUL_LANTERN)) {
						cir.setReturnValue(MBBlocks.WALL_SOUL_LANTERN.getPlacementState(ctx));
					}
					else if (item.isOf(MBBlocks.COPPER_OXIDE_LANTERN.asItem())) {
						cir.setReturnValue(MBBlocks.WALL_COPPER_OXIDE_LANTERN.getPlacementState(ctx));
					}
                }
            }
        }
    }
}
