package net.paperfish.moonbits.registry;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;

public class MBItemTags {
    public static final TagKey<Item> COBBLESTONE = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "cobblestone"));
    public static final TagKey<Item> STONE = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "stone"));

    public static final TagKey<Item> JUNIPER_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "juniper_logs"));
    public static final TagKey<Item> CEDAR_LOGS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cedar_logs"));
    public static final TagKey<Item> ASPEN_TRUNKS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "aspen_trunks"));

    public static final TagKey<Item> BEAR_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "bear_likes"));
    public static final TagKey<Item> BEAR_EDIBLE = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "bear_edible"));
    // used to remove 1 seed from the drop table of crops when harvested by right-click
    public static final TagKey<Item> SEEDS_ROOTS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "seeds_roots"));
    // items that will attract glares
    public static final TagKey<Item> GLARE_LIKES = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "glare_likes"));

    // mushrooms that can go into food
    public static final TagKey<Item> EDIBLE_MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "edible_mushrooms"));
    // no-no mushrooms
    //public static final Tag.Identified<Item> UNSAFE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms"));
    // all mushrooms
    public static final TagKey<Item> MUSHROOMS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "mushrooms"));

    public static final TagKey<Item> AXES = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "axes"));

    public static final TagKey<Item> MILK = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "milk"));
    public static final TagKey<Item> HONEY = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "honey"));
    public static final TagKey<Item> PEANUT = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "peanuts"));

    // cooking pot tags
    public static final TagKey<Item> COOKING_FRUITS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/fruit"));
    public static final TagKey<Item> COOKING_VEG = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/veg"));
    public static final TagKey<Item> COOKING_MEATS = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/meat"));
    public static final TagKey<Item> COOKING_FISH = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/fish"));
    public static final TagKey<Item> COOKING_SWEET = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/sweet"));
    public static final TagKey<Item> COOKING_WILD = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/wild"));
    public static final TagKey<Item> COOKING_MONSTER = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/monster"));
    public static final TagKey<Item> COOKING_FILLER = TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, "cooking/filler"));

    public static void register() {

    }
}
