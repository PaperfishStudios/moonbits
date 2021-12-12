package net.moonteam.moonbits.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moonteam.moonbits.entity.MoobloomEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeeEntity.class)
public abstract class BeeFlowerCheckMixin extends AnimalEntity {

    protected BeeFlowerCheckMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isFlowers", at = @At("HEAD"), cancellable = true)
    private void onIsFlowers(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        MoobloomEntity a =  world.getClosestEntity(world.getEntitiesByClass(MoobloomEntity.class, getBoundingBox().expand(5.0D, 4.0D, 5.0D), (livingEntity) -> true),
                TargetPredicate.createNonAttackable().setPredicate(MoobloomEntity -> true).setBaseMaxDistance(5), this , getX(), getEyeY(), getZ());
        if (a != null){
            if (pos == a.getBlockPos()) {
                cir.setReturnValue(true);
            }
        }
    }
}
