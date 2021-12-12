package net.moonteam.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.moonteam.moonbits.MoonbitsMain;
import net.moonteam.moonbits.entity.MoobloomEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(targets = "net.minecraft.entity.passive.BeeEntity$PollinateGoal")
public abstract class BeeEntityMixin  {
    @Final
    @Shadow
    BeeEntity field_20377;

    private final TargetPredicate moobloomPredicate = TargetPredicate.createNonAttackable().setPredicate(MoobloomEntity -> true).setBaseMaxDistance(5);

    @Inject(method = "findFlower", at = @At("HEAD"), cancellable = true)
    private void onFindFlower(Predicate<BlockState> predicate, double searchDistance, CallbackInfoReturnable<Optional<BlockPos>> cir) {
        BlockPos blockPos = field_20377.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        MoobloomEntity a =  field_20377.world.getClosestEntity(field_20377.world.getEntitiesByClass(MoobloomEntity.class, field_20377.getBoundingBox().expand(5.0D, 4.0D, 5.0D), (livingEntity) -> true),
                moobloomPredicate, field_20377, field_20377.getX(), field_20377.getEyeY(), field_20377.getZ());

        if (a != null) {
            mutable.set(a.getBlockPos());
        }

        if (blockPos.isWithinDistance(mutable, searchDistance)) {
            MoonbitsMain.LOGGER.info("bee found: ", a.getMoobloomType().toString());
            field_20377.setFlowerPos(mutable);
            cir.setReturnValue(Optional.of(mutable));
        }
    }
}
