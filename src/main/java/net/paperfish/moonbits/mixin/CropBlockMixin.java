package net.paperfish.moonbits.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlockTags;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.MBItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@SuppressWarnings("deprecation")
@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock {

    protected CropBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow public abstract boolean isMature(BlockState blockState);
    @Shadow
    public abstract BlockState withAge(int age);

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    public void plantableCheck(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if (floor.isIn(MBBlockTags.PLANTER_BOXES)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getAvailableMoisture", at = @At(value = "HEAD"), cancellable = true)
    private static void onGetMoisture(Block block, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (world.getBlockState(pos.down()).isIn(MBBlockTags.PLANTER_BOXES)) {
            cir.setReturnValue(10.0f);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.isMature(state)) {
            if (!world.isClient) {
                Block.getDroppedStacks(state, (ServerWorld) world, pos, null).forEach((stack) -> {
                    // if its the thing that u replant, drop 1 less than usual
                    if (stack.isIn(MBItemTags.SEEDS_ROOTS)) {
                        stack.setCount(stack.getCount() - 1);
                    }
                    Block.dropStack(world, pos, stack);
                });
                world.playSound(null, pos, SoundEvents.BLOCK_CROP_BREAK, SoundCategory.BLOCKS, 1F, 1.0F);
                world.setBlockState(pos, this.withAge(0));
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

//    @Inject(method = "getAvailableMoisture", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"),
//            locals = LocalCapture.CAPTURE_FAILSOFT)
//    private static void onGetMoisture(Block block, BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir, float f, BlockPos blockPos, int i, int j, float g, BlockState blockState) {
//        if (world.getBlockState(pos.down()).isIn(MBData.PLANTER_BOXES)) {
//            g = 3.0f;
//        }
//    }
}
