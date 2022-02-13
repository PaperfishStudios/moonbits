package net.paperfish.moonbits;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import java.util.Map;

public class MBBlockTags {

    // tags go here
    public static final Tag.Identified<Block> JACARANDA_LOGS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "jacaranda_logs"));

    // mushrooms that can go into food
    public static final Tag.Identified<Block> EDIBLE_MUSHROOMS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Block> UNSAFE_MUSHROOMS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final Tag.Identified<Block> MUSHROOMS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "mushrooms"));

	// tag containing all mb bookshelves
    public static final Tag.Identified<Block> BOOKSHELVES = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "bookshelves"));
    // fabric common tag containing all bookshelves
	public static final Tag.Identified<Block> C_BOOKSHELVES = TagFactory.BLOCK.create(new Identifier("c", "bookshelves"));
    // blocks that increase the enchanting power of a table
    public static final Tag.Identified<Block> VALID_ENCHANTERS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "valid_enchanting_blocks"));
    // tag containing every planter variant
    public static final Tag.Identified<Block> PLANTER_BOXES = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "planter_boxes"));
    // used for tough dirt & other similar blocks that work as dirt but cannot be replaced by tree growth n such
	public static final Tag.Identified<Block> SOIL_NON_REPLACEABLE = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "soil_non_replaceable"));
    // tag for which blocks can be replaced by deposits in worldgen (tough dirt ores)
	public static final Tag.Identified<Block> TOUGH_DIRT = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "tough_soils"));
    // tag for seat blocks
    public static final Tag.Identified<Block> VALID_SEATS = TagFactory.BLOCK.create(new Identifier(Moonbits.MOD_ID, "valid_seats"));

    public static void register() {

    }
}
