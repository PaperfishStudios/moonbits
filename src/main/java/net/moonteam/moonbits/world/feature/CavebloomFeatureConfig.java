package net.moonteam.moonbits.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record CavebloomFeatureConfig(IntProvider searchRange, FloatProvider spreadChance, IntProvider flowerSpead) implements FeatureConfig {
    public static final Codec<CavebloomFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("search_range").forGetter(CavebloomFeatureConfig::searchRange),
            FloatProvider.VALUE_CODEC.fieldOf("spread_chance").forGetter(CavebloomFeatureConfig::spreadChance),
            IntProvider.VALUE_CODEC.fieldOf("flower_spread").forGetter(CavebloomFeatureConfig::flowerSpead)
    ).apply(instance, instance.stable(CavebloomFeatureConfig::new)));
}
