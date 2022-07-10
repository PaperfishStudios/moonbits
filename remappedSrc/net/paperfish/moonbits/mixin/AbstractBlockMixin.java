package net.paperfish.moonbits.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockMixin {
    @Inject(method = "getModelOffset", at = @At("HEAD"), cancellable = true)
    public void offsetOverride(BlockView world, BlockPos pos, CallbackInfoReturnable<Vec3d> cir){
        if (world.getBlockState(pos.down()).isIn(MBBlockTags.PLANTER_BOXES)) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }
}
