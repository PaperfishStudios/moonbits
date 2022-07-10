package net.paperfish.moonbits.registry;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.paperfish.moonbits.Moonbits;

public class MBBiomeTags {
    public static final TagKey<Biome> HAS_DIRT_CAVES = TagKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, "has_dirt_caves"));

    public static final TagKey<Biome> SHALLOW_DIRT_CAVES = TagKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, "shallow_dirt_caves"));
    public static final TagKey<Biome> MID_DIRT_CAVES = TagKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, "mid_dirt_caves"));
    public static final TagKey<Biome> DEEP_DIRT_CAVES = TagKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, "deep_dirt_caves"));
}
