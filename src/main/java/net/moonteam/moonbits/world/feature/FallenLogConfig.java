package net.moonteam.moonbits.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.data.report.BlockListProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record FallenLogConfig(IntProvider size, BlockStateProvider block) implements FeatureConfig {
    public static final Codec<FallenLogConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("size").forGetter(FallenLogConfig::size),
            BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(FallenLogConfig::block)
    ).apply(instance, instance.stable(FallenLogConfig::new)));
}
