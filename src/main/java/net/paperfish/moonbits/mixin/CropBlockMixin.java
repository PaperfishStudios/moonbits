package net.paperfish.moonbits.mixin;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@SuppressWarnings("deprecation")
@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock implements Fertilizable {

    protected CropBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow public abstract boolean isMature(BlockState blockState);
    @Shadow
    public abstract BlockState withAge(int age);

	@Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
	public void hoeSelecting(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir){
		if (!this.isMature(state) && context instanceof EntityShapeContext) {
			Entity player = ((EntityShapeContext) context).getEntity();
			if (player instanceof LivingEntity) {
				if (((LivingEntity) player).getMainHandStack().isIn(ConventionalItemTags.HOES)) {
					cir.setReturnValue(VoxelShapes.empty());
				}
			}
		}
	}

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    public void plantableCheck(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if (floor.isIn(MBBlockTags.PLANTER_BOXES)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "randomTick", at = @At(value = "HEAD"), cancellable = true)
    private void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random, CallbackInfo ci) {
        int i = this.getAge(state);
        if (world.getBlockState(pos.down()).isIn(MBBlockTags.PLANTER_BOXES)) {
            if (world.getBaseLightLevel(pos, 0) >= 9 && i < this.getMaxAge() && random.nextInt(2) == 0) {
                world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                ci.cancel();
            }
        }
    }

    @Shadow
    public int getMaxAge() {
        return 7;
    }

    @Shadow
    protected abstract int getAge(BlockState state);

}
