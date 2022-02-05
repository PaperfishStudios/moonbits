package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.block.MushroomCapBlock;
import net.paperfish.moonbits.world.feature.MBTreeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {
    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/world/gen/feature/ConfiguredFeature;)Lnet/minecraft/world/gen/feature/ConfiguredFeature;", at = @At(value="HEAD"), cancellable = true)
    private static void onRegister(String id, ConfiguredFeature<?, ?> configuredFeature, CallbackInfoReturnable<ConfiguredFeature<?, ?>> cir) {
        if (Objects.equals(id, "huge_brown_mushroom")) {
            cir.setReturnValue(MBTreeFeatures.HUGE_BROWN_MUSHROOM);
        }
        if (Objects.equals(id, "huge_red_mushroom")) {
            cir.setReturnValue(MBTreeFeatures.HUGE_RED_MUSHROOM);
        }
    }
}
