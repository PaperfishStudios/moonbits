package net.paperfish.moonbits.registry;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.entity.MBBoatTypes;
import net.paperfish.moonbits.item.*;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.ArrayList;
import java.util.List;

public class MBItems {
	public static List<Item> MB_ITEMS = new ArrayList<>();
	public static List<Item> MB_TOOLS = new ArrayList<>();
	public static List<Item> MB_EGGS = new ArrayList<>();

	public static final Item GRASS_TUFT = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item MILK_BOTTLE = new MilkBottleItem(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16));
	public static final Item SYRUP_BOTTLE = new MilkBottleItem(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16));
	public static final Item ITEM_HOOK = new ItemHookItem(MBEntities.ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));
	public static final Item GLOW_ITEM_HOOK = new ItemHookItem(MBEntities.GLOW_ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));

	public static final Item PUMPKIN_SLICE = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.3f).build()));

	public static final Item ICE_CUBES = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item FROSTHORN_SEED = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item PEPPER_SEEDS = new AliasedBlockItem(MBBlocks.PEPPER_CROP, new QuiltItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Item PEPPER = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(2).saturationModifier(0.3f).build()));
	public static final Item STUFFED_PEPPER = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(6).saturationModifier(1.0f).build()));
	public static final Item CHILI = new MushroomStewItem(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16)
			.food((new FoodComponent.Builder()).hunger(7).saturationModifier(0.8f).build()));

	public static final Item PRICKLY_PEAR = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(1).saturationModifier(0.3f).build()));
	public static final Item COOKED_PRICKLY_PEAR = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.6f).build()));

	public static final Item HARDY_BERRY = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.6f).build()));
	public static final Item HARDY_BERRY_SEED = new AliasedBlockItem(MBBlocks.HARDY_SPROUT, new QuiltItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Item HARDY_STEM = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD));

	public static final Item PEANUT = new AliasedBlockItem(MBBlocks.PEANUT_CROP, new QuiltItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Item ROASTED_PEANUTS = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.6f).build()));
//	public static final Item PEANUT_CHICKEN = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
//			.food((new FoodComponent.Builder()).hunger(7).saturationModifier(1.0f).build()));
	public static final Item HONEY_PEANUTS = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.6f).build()));
	public static final Item PEANUT_BRITTLE = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.3f).build()));
	public static final Item DOG_TREAT = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD));

	public static final Block SWEET_BERRY_PITS = new SweetBerryPitsBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH));
	public static final Block GLOW_BERRY_PITS = new GlowBerryPitsBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES));
	public static final Item ROASTED_BERRIES = new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build()));

    public static final Item PEAT = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item HEMATITE = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item COPPER_NUGGET = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item RAW_TIN = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item TIN_INGOT = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item TIN_NUGGET = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item FUR = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item PARASOL_FIBER = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BURLAP = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item MONSTER_HIDE = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item TOADSTOOL_CAP = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item RED_MUSHBLEND = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item RED_MUSHBRICK = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BROWN_MUSHBLEND = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BROWN_MUSHBRICK = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item SAFFRON_MUSHBLEND = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item SAFFRON_MUSHBRICK = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item TOADSTOOL_MUSHBLEND = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item TOADSTOOL_MUSHBRICK = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));

	public static final Item WRENCH = new WrenchItem(new QuiltItemSettings().group(ItemGroup.TOOLS));

    public static final Item BABY_TURTLE_BUCKET = new EntityBucketItem(EntityType.TURTLE, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
	(new Item.Settings()).maxCount(1).group(MBItemGroup.MB_MISC));
    public static final Item BABY_STRIDER_BUCKET = new EntityBucketItem(EntityType.STRIDER, Fluids.LAVA, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
	(new Item.Settings()).maxCount(1).group(MBItemGroup.MB_MISC));

	public static final Item LAMPROOT_BOAT = new BoatItem(false, MBBoatTypes.LAMPROOT, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));
	public static final Item LAMPROOT_CHEST_BOAT = new BoatItem(true, MBBoatTypes.LAMPROOT, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));
	public static final Item CEDAR_BOAT = new BoatItem(false, MBBoatTypes.CEDAR, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));
	public static final Item CEDAR_CHEST_BOAT = new BoatItem(true, MBBoatTypes.CEDAR, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));

	// Glass Shards
	public static final Item GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item WHITE_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item LIGHT_GRAY_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item GRAY_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BLACK_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item GREEN_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item LIME_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item YELLOW_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item ORANGE_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BROWN_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item RED_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item PINK_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item MAGENTA_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item PURPLE_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item LIGHT_BLUE_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item CYAN_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));
	public static final Item BLUE_GLASS_SHARD = new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC));


	// Spawn eggs
	public static final Item GRIZZLY_SPAWN_EGG = new SpawnEggItem(MBEntities.GRIZZLY_BEAR, 5782045, 15707403, new Item.Settings().group(MBItemGroup.MB_MISC));
	public static final Item GRASSHAT_SPAWN_EGG = new SpawnEggItem(MBEntities.GRASSHAT, 7686462, 1485323, new Item.Settings().group(MBItemGroup.MB_MISC));

	public static void add(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, id), item);
	}
	public static void addItem(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, id), item);
		MB_ITEMS.add(item);
	}

	public static void addTool(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, id), item);
		MB_TOOLS.add(item);
	}
	public static void addEgg(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, id), item);
		MB_EGGS.add(item);
	}

	public static void registerItems(){
		addItem("roasted_berries", ROASTED_BERRIES);
		addItem("pumpkin_slice", PUMPKIN_SLICE);
		addItem("ice_cubes", ICE_CUBES);
		addItem("frosthorn_seed", FROSTHORN_SEED);
		add("pepper_seeds", PEPPER_SEEDS);
		addItem("pepper", PEPPER);
		addItem("stuffed_pepper", STUFFED_PEPPER);
		addItem("chili", CHILI);

		addItem("prickly_pear", PRICKLY_PEAR);
		addItem("cooked_prickly_pear", COOKED_PRICKLY_PEAR);

		addItem("hardy_berry", HARDY_BERRY);
		add("hardy_berry_seed", HARDY_BERRY_SEED);
		addItem("hardy_stem", HARDY_STEM);

		add("peanut", PEANUT);
		addItem("roasted_peanuts", ROASTED_PEANUTS);
//		addItem("peanut_chicken", PEANUT_CHICKEN);
		addItem("honey_roasted_peanuts", HONEY_PEANUTS);
		addItem("peanut_brittle", PEANUT_BRITTLE);
		addItem("dog_treat", DOG_TREAT);

//		addItem("glow_berry_tart", GLOW_BERRY_TART);
//		addItem("honey_bun", HONEY_BUN);
//		addItem("brownie", BROWNIE);
//		addItem("fruit_pie", FRUIT_PIE);
//		addItem("porridge", PORRIDGE);
//		addItem("chicken_soup", CHICKEN_SOUP);
//		addItem("hearty_stew", HEARTY_STEW);
//		addItem("foraged_salad", FORAGED_SALAD);
//		addItem("vegetable_stir_fry", VEG_STIRFRY);

		addItem("milk_bottle", MILK_BOTTLE);
		addItem("syrup_bottle", SYRUP_BOTTLE);

		//addItem("apple_seeds", APPLE_SEEDS);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "sweet_berry_pits"), SWEET_BERRY_PITS);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "sweet_berry_pits"), new BlockItem(SWEET_BERRY_PITS, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "glow_berry_pits"), GLOW_BERRY_PITS);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "glow_berry_pits"), new BlockItem(GLOW_BERRY_PITS, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));

		addItem("grass_tuft", GRASS_TUFT);
		addItem("item_hook", ITEM_HOOK);
		addItem("glow_item_hook", GLOW_ITEM_HOOK);

		addItem("toadstool_cap", TOADSTOOL_CAP);

		addItem("red_mushblend", RED_MUSHBLEND);
		addItem("red_mushbrick", RED_MUSHBRICK);
		addItem("brown_mushblend", BROWN_MUSHBLEND);
		addItem("brown_mushbrick", BROWN_MUSHBRICK);
		addItem("saffron_mushblend", SAFFRON_MUSHBLEND);
		addItem("saffron_mushbrick", SAFFRON_MUSHBRICK);
		addItem("toadstool_mushblend", TOADSTOOL_MUSHBLEND);
		addItem("toadstool_mushbrick", TOADSTOOL_MUSHBRICK);

		addItem("lamproot_boat", LAMPROOT_BOAT);
		addItem("lamproot_chest_boat", LAMPROOT_CHEST_BOAT);
		addItem("cedar_boat", CEDAR_BOAT);
		addItem("cedar_chest_boat", CEDAR_CHEST_BOAT);

		addTool("wrench", WRENCH);

		addItem("baby_turtle_bucket", BABY_TURTLE_BUCKET);
		addItem("baby_strider_bucket", BABY_STRIDER_BUCKET);

		addItem("peat", PEAT);
		addItem("raw_tin", RAW_TIN);
		addItem("tin_ingot", TIN_INGOT);
		addItem("tin_nugget", TIN_NUGGET);
		addItem("hematite", HEMATITE);
		addItem("copper_nugget", COPPER_NUGGET);
		addItem("fur", FUR);
		addItem("parasol_fiber", PARASOL_FIBER);
		addItem("burlap", BURLAP);
		addItem("monster_hide", MONSTER_HIDE);

		addItem("glass_shard", GLASS_SHARD);
		addItem("white_glass_shard", WHITE_GLASS_SHARD);
		addItem("orange_glass_shard", ORANGE_GLASS_SHARD);
		addItem("magenta_glass_shard", MAGENTA_GLASS_SHARD);
		addItem("light_blue_glass_shard", LIGHT_BLUE_GLASS_SHARD);
		addItem("yellow_glass_shard", YELLOW_GLASS_SHARD);
		addItem("lime_glass_shard", LIME_GLASS_SHARD);
		addItem("pink_glass_shard", PINK_GLASS_SHARD);
		addItem("gray_glass_shard", GRAY_GLASS_SHARD);
		addItem("light_gray_glass_shard", LIGHT_GRAY_GLASS_SHARD);
		addItem("cyan_glass_shard", CYAN_GLASS_SHARD);
		addItem("purple_glass_shard", PURPLE_GLASS_SHARD);
		addItem("blue_glass_shard", BLUE_GLASS_SHARD);
		addItem("brown_glass_shard", BROWN_GLASS_SHARD);
		addItem("green_glass_shard", GREEN_GLASS_SHARD);
		addItem("red_glass_shard", RED_GLASS_SHARD);
		addItem("black_glass_shard", BLACK_GLASS_SHARD);

		addEgg("grizzly_bear_spawn_egg", GRIZZLY_SPAWN_EGG);
		addEgg("grasshat_spawn_egg", GRASSHAT_SPAWN_EGG);
	}
}
