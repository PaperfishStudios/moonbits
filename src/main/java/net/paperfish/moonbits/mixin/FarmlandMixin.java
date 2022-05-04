package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FarmlandBlock.class)
public class FarmlandMixin {

    @Inject(method = "canPlaceAt", at=@At("HEAD"), cancellable = true)
    public void onPlacementCheck(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.up()).isOf(MBBlocks.LETTUCE_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
