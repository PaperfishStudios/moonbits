package net.paperfish.moonbits.mixin;

import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ConfiguredFeatureUtil.class)
public class ConfiguredFeaturesMixin {
    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/world/gen/feature/Feature;Lnet/minecraft/world/gen/feature/FeatureConfig;)Lnet/minecraft/util/Holder;", at = @At(value="HEAD"), cancellable = true)
    private static void onRegister(String id, Feature<?> feature, FeatureConfig config, CallbackInfoReturnable<Holder<ConfiguredFeature<HugeMushroomFeatureConfig, ?>>> cir) {
        if (Objects.equals(id, "huge_brown_mushroom")) {
            cir.setReturnValue(MBTreeFeatures.HUGE_BROWN_MUSHROOM);
        }
        if (Objects.equals(id, "huge_red_mushroom")) {
            cir.setReturnValue(MBTreeFeatures.HUGE_RED_MUSHROOM);
        }
    }
}
