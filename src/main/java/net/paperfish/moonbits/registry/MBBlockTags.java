package net.paperfish.moonbits.registry;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;

import java.util.ArrayList;
import java.util.List;

public class MBBlockTags {

    // tags go here
    public static final TagKey<Block> COBBLESTONE = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "cobblestone"));
    public static final TagKey<Block> STONE = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "stone"));

    public static final TagKey<Block> LAMPROOT_LOGS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "lamproot_logs"));
    public static final TagKey<Block> CEDAR_LOGS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "cedar_logs"));
    public static final TagKey<Block> TOADSTOOL_STEMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "toadstool_stems"));

    public static final TagKey<Block> BANDED_IRON = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "banded_iron"));

    // mushrooms that can go into food
    public static final TagKey<Block> EDIBLE_MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "edible_mushrooms"));
    // all mushrooms
    public static final TagKey<Block> MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "mushrooms"));

    // tag containing every planter variant
    public static final TagKey<Block> PLANTER_BOXES = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "planter_boxes"));
	public static final TagKey<Block> NETHER_PLANTERS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "nether_planters"));
	public static final TagKey<Block> DESERT_PLANTERS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "desert_planters"));
    // used for tough dirt & other similar blocks that work as dirt but cannot be replaced by tree growth n such
	public static final TagKey<Block> SOIL_NON_REPLACEABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "soil_non_replaceable"));
    // used for blocks that desert & beach plants can be planted on.
    public static final TagKey<Block> SANDY_SOILS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "sandy_soils"));

	// ocotillo plant blocks
	public static final TagKey<Block> OCOTILLO = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "ocotillo"));

    // tag for which blocks can be replaced by deposits in worldgen (tough dirt ores)
	public static final TagKey<Block> TOUGH_DIRT = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "tough_soils"));
    // tag for seat blocks
    public static final TagKey<Block> VALID_SEATS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "valid_seats"));

    public static void register() {

    }
}
