package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyBlock.class)
public class SnowyBlockMixin {

    @Inject(method = "isSnow", at = @At("HEAD"), cancellable = true)
    private static void checkSnowyPlants(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isOf(Blocks.SNOW_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
