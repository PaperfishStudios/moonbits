package net.paperfish.moonbits.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin extends PassiveEntity {

    protected AnimalEntityMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "breed", at = @At("TAIL"))
    public void onBreed(ServerWorld world, AnimalEntity other, CallbackInfo ci) {
        if (other instanceof PigEntity || other instanceof RabbitEntity) {
            int litter = world.getRandom().nextInt(other instanceof PigEntity ? 3 : 5) + 1;
            for (int i = 0; i < litter; i++) {
                PassiveEntity passiveEntity = this.createChild(world, other);
                assert passiveEntity != null;
                passiveEntity.setBaby(true);
                passiveEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0.0f, 0.0f);
                world.spawnEntityAndPassengers(passiveEntity);
            }
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
