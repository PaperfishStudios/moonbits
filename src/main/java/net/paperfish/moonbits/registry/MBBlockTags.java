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

    public static final TagKey<Block> JUNIPER_LOGS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "juniper_logs"));
    public static final TagKey<Block> CEDAR_LOGS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "cedar_logs"));
    public static final TagKey<Block> ASPEN_TRUNKS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "aspen_trunks"));

    public static final TagKey<Block> BANDED_IRON = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "banded_iron"));

    // mushrooms that can go into food
    public static final TagKey<Block> EDIBLE_MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Block> UNSAFE_MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final TagKey<Block> MUSHROOMS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "mushrooms"));

	// tag containing all mb bookshelves
    public static final TagKey<Block> BOOKSHELVES = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "bookshelves"));
    // fabric common tag containing all bookshelves
	public static final TagKey<Block> C_BOOKSHELVES = TagKey.of(Registry.BLOCK_KEY, new Identifier("c", "bookshelves"));
    // blocks that increase the enchanting power of a table
    public static final TagKey<Block> VALID_ENCHANTERS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "valid_enchanting_blocks"));
    // tag containing every planter variant
    public static final TagKey<Block> PLANTER_BOXES = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "planter_boxes"));
    // used for tough dirt & other similar blocks that work as dirt but cannot be replaced by tree growth n such
	public static final TagKey<Block> SOIL_NON_REPLACEABLE = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "soil_non_replaceable"));
    // used for blocks that desert & beach plants can be planted on.
    public static final TagKey<Block> SANDY_SOILS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "sandy_soils"));
    // tag for which blocks can be replaced by deposits in worldgen (tough dirt ores)
	public static final TagKey<Block> TOUGH_DIRT = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "tough_soils"));
    // tag for seat blocks
    public static final TagKey<Block> VALID_SEATS = TagKey.of(Registry.BLOCK_KEY, new Identifier(Moonbits.MODID, "valid_seats"));

    public static void register() {

    }

    public static Block[] toArray(TagKey<Block> blocks) {
        Block[] array = new Block[0];
        List<Block> list = new ArrayList<>();
        Registry.BLOCK.forEach((block) -> {
            if (block.getDefaultState().isIn(blocks)) {
                list.add(block);
            }
        });
        return list.toArray(array);
    }
}