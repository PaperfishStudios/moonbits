package net.paperfish.moonbits;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class MBItemTags {
    public static final Tag.Identified<Item> JUNIPER_LOGS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "juniper_logs"));
    public static final Tag.Identified<Item> CEDAR_LOGS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "cedar_logs"));

    public static final Tag.Identified<Item> BEAR_LIKES = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "bear_likes"));
    public static final Tag.Identified<Item> BEAR_EDIBLE = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "bear_edible"));
    // used to remove 1 seed from the drop table of crops when harvested by right-click
    public static final Tag<Item> SEEDS_ROOTS = TagFactory.ITEM.create(new Identifier("moonbits", "seeds_roots"));
    // items that will attract glares
    public static final Tag<Item> GLARE_LIKES = TagFactory.ITEM.create(new Identifier("moonbits", "glare_likes"));

    // mushrooms that can go into food
    public static final Tag.Identified<Item> EDIBLE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Item> UNSAFE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final Tag.Identified<Item> MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "mushrooms"));

    public static void register() {

    }
}
