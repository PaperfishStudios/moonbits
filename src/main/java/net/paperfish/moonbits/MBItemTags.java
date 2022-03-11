package net.paperfish.moonbits;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MBItemTags {
    public static final TagKey<Item> JUNIPER_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "juniper_logs"));
    public static final TagKey<Item> CEDAR_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "cedar_logs"));
    public static final TagKey<Item> ASPEN_TRUNKS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "aspen_trunks"));

    public static final TagKey<Item> BEAR_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "bear_likes"));
    public static final TagKey<Item> BEAR_EDIBLE = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "bear_edible"));
    // used to remove 1 seed from the drop table of crops when harvested by right-click
    public static final TagKey<Item> SEEDS_ROOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "seeds_roots"));
    // items that will attract glares
    public static final TagKey<Item> GLARE_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "glare_likes"));

    // mushrooms that can go into food
    public static final TagKey<Item> EDIBLE_MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Item> UNSAFE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final TagKey<Item> MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MOD_ID, "mushrooms"));

    public static final TagKey<Item> AXES = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "axes"));

    public static void register() {

    }
}
