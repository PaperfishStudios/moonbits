package net.paperfish.moonbits.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.passive.StriderEntity;

@Mixin(StriderEntity.class)
public interface StriderSaddleAccess {
    
    @Accessor("saddledComponent") 
    SaddledComponent getSaddledComponent();
}
