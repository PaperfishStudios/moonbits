package net.paperfish.moonbits.mixin;

import net.minecraft.block.Block;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.Texture;
import net.minecraft.data.client.model.TexturedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Function;

@Mixin(TexturedModel.class)
public interface TexturedModelAccessor {
    @Invoker
    static TexturedModel.Factory callMakeFactory(Function<Block, Texture> textureGetter, Model model) {
        throw new UnsupportedOperationException();
    }
}
