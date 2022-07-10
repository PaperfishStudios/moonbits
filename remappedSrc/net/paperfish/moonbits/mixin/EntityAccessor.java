package net.paperfish.moonbits.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.util.math.random.Random;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor
    Random getRandom();
}
