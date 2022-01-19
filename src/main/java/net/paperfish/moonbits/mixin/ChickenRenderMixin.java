package net.paperfish.moonbits.mixin;

import net.minecraft.client.render.entity.ChickenEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.MoonbitsMain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChickenEntityRenderer.class)
public class ChickenRenderMixin {
    private static final Identifier BABY_TEXTURE = new Identifier(MoonbitsMain.MOD_ID, "textures/entity/baby_chick.png");

    @Inject(method = "getTexture*", at = @At("HEAD"), cancellable = true)
    public void onGetTexture(ChickenEntity chick, CallbackInfoReturnable<Identifier> cir) {
        if (chick.isBaby()) {
            cir.setReturnValue(BABY_TEXTURE);
        }
    }

}
