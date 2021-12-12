package net.moonteam.moonbits.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.passive.PigEntity;

@Mixin(PigEntity.class)
public interface PigSaddleAccess {
    
    @Accessor("saddledComponent") 
    SaddledComponent getSaddledComponent();
}
