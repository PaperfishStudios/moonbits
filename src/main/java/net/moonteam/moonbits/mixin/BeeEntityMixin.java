package net.moonteam.moonbits.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.moonteam.moonbits.entity.MoobloomEntity;
import net.moonteam.moonbits.entity.goals.PollinateMoobloomGoal;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BeeEntity.class)
public class BeeEntityMixin extends AnimalEntity {
    PollinateMoobloomGoal pollinateMoobloomGoal;

    protected BeeEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "initGoals()V")
    protected void initGoals(CallbackInfo ci) {
        pollinateMoobloomGoal = new PollinateMoobloomGoal(this);
        goalSelector.add(4, pollinateMoobloomGoal);
        //goalSelector.add(6, new ActiveTargetGoal<>(this, MoobloomEntity.class, 10, true, false, null));
    }

    @Shadow
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
