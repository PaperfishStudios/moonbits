package net.paperfish.moonbits.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreeperEntity.class)
public class CreeperMixin extends HostileEntity {

    protected CreeperMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "explode", at = @At(value = "FIELD", target = "net/minecraft/world/explosion/Explosion$DestructionType.DESTROY : Lnet/minecraft/world/explosion/Explosion$DestructionType;"))
    public Explosion.DestructionType safeExplode(Explosion.DestructionType old) {
        return old == Explosion.DestructionType.DESTROY ? Explosion.DestructionType.BREAK : Explosion.DestructionType.NONE;
    }
}
