package net.paperfish.moonbits.registry;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.paperfish.moonbits.Moonbits;

public class MBBiomeTags {
    public static final TagKey<Biome> HAS_DIRT_CAVES = TagKey.of(Registry.BIOME_KEY, new Identifier(Moonbits.MODID, "has_dirt_caves"));
}
