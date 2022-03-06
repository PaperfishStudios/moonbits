package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.SnowyBlock;
import net.minecraft.state.property.Properties;
import net.paperfish.moonbits.MBBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SnowyBlock.class)
public class SnowyBlockMixin {

    @Inject(method = "isSnow", at = @At("HEAD"), cancellable = true)
    private static void checkSnowyPlants(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(MBBlockTags.SNOWABLE_PLANTS) && state.get(Properties.SNOWY)) {
            cir.setReturnValue(true);
        }
    }
}
