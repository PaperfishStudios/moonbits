package net.paperfish.moonbits.mixin;

import net.minecraft.data.client.model.TextureKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TextureKey.class)
public interface TextureKeyAccessor {
    @Invoker
    static TextureKey createTextureKey(String name, @Nullable TextureKey parent) {
        throw new UnsupportedOperationException();
    }
}
