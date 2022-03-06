package net.paperfish.moonbits.mixin;

import net.minecraft.block.Block;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

@Mixin(TexturedModel.class)
public interface TexturedModelAccessor {
    @Invoker
    static TexturedModel.Factory callMakeFactory(Function<Block, TextureMap> textureGetter, Model model) {
        throw new UnsupportedOperationException();
    }
}
