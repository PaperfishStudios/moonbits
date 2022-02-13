package net.paperfish.moonbits;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class MBItemTags {
    public static final Tag.Identified<Item> JACARANDA_LOGS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "jacaranda_logs"));

    public static final Tag.Identified<Item> BEAR_LIKES = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "bear_likes"));
    public static final Tag.Identified<Item> BEAR_EDIBLE = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "bear_edible"));

    // mushrooms that can go into food
    public static final Tag.Identified<Item> EDIBLE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Item> UNSAFE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final Tag.Identified<Item> MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "mushrooms"));

    public static void register() {

    }
}
