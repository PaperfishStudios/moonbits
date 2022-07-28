package net.paperfish.moonbits.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.dimension.AreaHelper;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AreaHelper.class)
public class AreaHelperMixin {
    private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> state.isIn(MBBlockTags.NETHER_PORTAL_FRAME_BLOCKS);

    @Final
    @Shadow
    private WorldAccess world;

    @Inject(method = "getWidth(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)I", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractBlock$ContextPredicate;test(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z",
            ordinal = 0
    ), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    public void obsiWidthCheck(BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir, BlockPos.Mutable mutable, int i, BlockState blockState) {
        // this one returns the width of the complete portal
        if (!blockState.isIn(MBBlockTags.NETHER_PORTAL_FRAME_BLOCKS)) {
            cir.setReturnValue(0);
        }
    }
    @Inject(method = "getWidth(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)I", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractBlock$ContextPredicate;test(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z",
            ordinal = 1
    ), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    public void obsiWidthBrokenCheck(BlockPos pos, Direction direction, CallbackInfoReturnable<Integer> cir, BlockPos.Mutable mutable, int i, BlockState blockState, BlockState blockState2) {
        // this one is for if the frame shape is broken
        if (!blockState2.isIn(MBBlockTags.NETHER_PORTAL_FRAME_BLOCKS)) {
            cir.setReturnValue(0);
        }
    }
    @Inject(method = "method_30491", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractBlock$ContextPredicate;test(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z",
            ordinal = 0
    ), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    public void obsiHeightBroken(BlockPos.Mutable mutable, int i, CallbackInfoReturnable<Boolean> cir, int j, BlockPos.Mutable mutable2) {
        // this one is for if the frame shape is broken
        if (!world.getBlockState(mutable2).isIn(MBBlockTags.NETHER_PORTAL_FRAME_BLOCKS)) {
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "method_30490", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractBlock$ContextPredicate;test(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z"
    ), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    public void obsiHeight(BlockPos.Mutable mutable, CallbackInfoReturnable<Integer> cir, int i) {
        // this one is for if the frame shape is broken
        if (!world.getBlockState(mutable).isIn(MBBlockTags.NETHER_PORTAL_FRAME_BLOCKS)) {
            cir.setReturnValue(i);
        }
    }
}
