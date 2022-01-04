package net.moonteam.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.carver.Carver;
import net.moonteam.moonbits.MBData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Carver.class)
public class CarverMixin {

    @Inject(method="canAlwaysCarveBlock", at=@At("HEAD"), cancellable = true)
    public void alwaysCarveCheck(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(MBData.TOUGH_DIRT)) {
            cir.setReturnValue(true);
        }
    }
}
