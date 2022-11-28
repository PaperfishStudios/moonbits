package net.paperfish.moonbits.registry;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;

public class MBItemTags {
    public static final TagKey<Item> COBBLESTONE = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "cobblestone"));
    public static final TagKey<Item> STONE = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "stone"));

    public static final TagKey<Item> LAMPROOT_LOGS = register("lamproot_logs");
    public static final TagKey<Item> CEDAR_LOGS = register("cedar_logs");
    public static final TagKey<Item> TOADSTOOL_STEMS = register("toadstool_stems");

	public static final TagKey<Item> SANDY_SOILS = register("sandy_soils");

    public static final TagKey<Item> BEAR_LIKES = register("bear_likes");
    public static final TagKey<Item> BEAR_EDIBLE = register("bear_edible");
    // used to remove 1 seed from the drop table of crops when harvested by right-click
    public static final TagKey<Item> SEEDS_ROOTS = register("seeds_roots");
    // items that will attract glares
    public static final TagKey<Item> GLARE_LIKES = register("glare_likes");

    // mushrooms that can go into food
    public static final TagKey<Item> EDIBLE_MUSHROOMS = register("edible_mushrooms");
    // no-no mushrooms
    //public static final Tag.Identified<Item> UNSAFE_MUSHROOMS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "unsafe_mushrooms");
    // all mushrooms
    public static final TagKey<Item> MUSHROOMS = register("mushrooms");

	// List of items that moonbits will prevent players from placing (used for berries)
    public static final TagKey<Item> BLOCK_PLACEMENT = register("block_placement");

    public static final TagKey<Item> MILK = register("milk");
    public static final TagKey<Item> HONEY = register("honey");
    public static final TagKey<Item> PEANUT = register("peanuts");

    // cooking pot tags
    public static final TagKey<Item> COOKING_FRUITS = register("cooking/fruit");
    public static final TagKey<Item> COOKING_VEG = register("cooking/veg");
    public static final TagKey<Item> COOKING_MEATS = register("cooking/meat");
    public static final TagKey<Item> COOKING_FISH = register("cooking/fish");
    public static final TagKey<Item> COOKING_SWEET = register("cooking/sweet");
    public static final TagKey<Item> COOKING_WILD = register("cooking/wild");
    public static final TagKey<Item> COOKING_MONSTER = register("cooking/monster");
    public static final TagKey<Item> COOKING_FILLER = register("cooking/filler");

    public static TagKey<Item> register(String id) {
		return TagKey.of(Registry.ITEM_KEY, new Identifier(Moonbits.MODID, id));
    }
}
