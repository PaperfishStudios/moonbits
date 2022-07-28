package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.paperfish.moonbits.Moonbits;

import java.util.List;

public class MBPlacedFeatures {
    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MODID, id), new PlacedFeature(Holder.upcast(registryEntry), List.copyOf(modifiers)));
    }

    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier ... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }
}
