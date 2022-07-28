package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.paperfish.moonbits.Moonbits;

public class MBConfiguredFeatures {
//    public static RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> register(String id, Feature<DefaultFeatureConfig> feature) {
//        return register(id, feature, FeatureConfig.DEFAULT);
//    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return register(id, new ConfiguredFeature<>(feature, config));
        //return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Moonbits.MODID, id), new ConfiguredFeature<>(feature, config));
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, ConfiguredFeature<FC, ?> pConfiguredFeature) {
        return method_40360(BuiltinRegistries.CONFIGURED_FEATURE, id, pConfiguredFeature);
        //return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Moonbits.MODID, id), new ConfiguredFeature<>(feature, config));
    }

    public static <V extends T, T> Holder<V> method_40360(Registry<T> registry, String id, V value) {
		Holder<T> registryEntry = BuiltinRegistries.register(registry, new Identifier(Moonbits.MODID, id), value);
        return (Holder<V>) registryEntry;
    }
}
