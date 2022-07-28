package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Carver.class)
public class CarverMixin <C extends CarverConfig> {

    @Inject(method="canReplaceBlock", at=@At("HEAD"), cancellable = true)
    public void alwaysCarveCheck(C config, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (state.isIn(MBBlockTags.TOUGH_DIRT)) {
            cir.setReturnValue(true);
        }
    }
}
