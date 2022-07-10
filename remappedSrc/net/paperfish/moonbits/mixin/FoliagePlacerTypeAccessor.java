package net.paperfish.moonbits.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoliagePlacerType.class)
public interface FoliagePlacerTypeAccessor<P extends FoliagePlacer> {
    @Accessor
    Codec<P> getCodec();

    @Mutable
    @Accessor
    void setCodec(Codec<P> codec);

    @Invoker
    static FoliagePlacerType<FoliagePlacer> callRegister(String id, Codec<FoliagePlacer> codec) {
        throw new UnsupportedOperationException();
    }

    @Invoker
    static FoliagePlacerType createFoliagePlacerType(Codec<FoliagePlacer> codec) {
        throw new UnsupportedOperationException();
    }
}
