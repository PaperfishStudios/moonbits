package net.paperfish.moonbits.registry;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.item.extended.MBAxeItem;
import net.paperfish.moonbits.item.extended.MBHoeItem;
import net.paperfish.moonbits.item.extended.MBPickaxeItem;
import net.paperfish.moonbits.entity.MBBoatTypes;
import net.paperfish.moonbits.item.*;
import net.paperfish.moonbits.item.extended.MBToolMaterials;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MBItems {
	public static List<Item> MB_ITEMS = new ArrayList<>();
	public static List<Item> MB_TOOLS = new ArrayList<>();
	public static List<Item> MB_EGGS = new ArrayList<>();



	public static final Item LAMPROOT_BOAT = addItem("lamproot_boat",
			new BoatItem(false, MBBoatTypes.LAMPROOT, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	public static final Item LAMPROOT_CHEST_BOAT = addItem("lamproot_chest_boat",
			new BoatItem(true, MBBoatTypes.LAMPROOT, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	public static final Item CEDAR_BOAT = addItem("cedar_boat",
			new BoatItem(false, MBBoatTypes.CEDAR, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));
	public static final Item CEDAR_CHEST_BOAT = addItem("cedar_chest_boat",
			new BoatItem(true, MBBoatTypes.CEDAR, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION)));

	// Glass Shards
	public static final Item GLASS_SHARD = addItem("glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item WHITE_GLASS_SHARD = addItem("white_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item LIGHT_GRAY_GLASS_SHARD = addItem("light_gray_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item GRAY_GLASS_SHARD = addItem("gray_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item BLACK_GLASS_SHARD = addItem("black_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item BROWN_GLASS_SHARD = addItem("brown_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item RED_GLASS_SHARD = addItem("red_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item ORANGE_GLASS_SHARD = addItem("orange_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item YELLOW_GLASS_SHARD = addItem("yellow_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item LIME_GLASS_SHARD = addItem("lime_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item GREEN_GLASS_SHARD = addItem("green_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item CYAN_GLASS_SHARD = addItem("cyan_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item LIGHT_BLUE_GLASS_SHARD = addItem("light_blue_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item BLUE_GLASS_SHARD = addItem("blue_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item PURPLE_GLASS_SHARD = addItem("purple_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item MAGENTA_GLASS_SHARD = addItem("magenta_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item PINK_GLASS_SHARD = addItem("pink_glass_shard", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	// TODO: Carrot (Music Disc)

	// Foodstuffs/Consumables
	public static final Item MILK_BOTTLE = addItem("milk_bottle",
			new MilkBottleItem(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));

	public static final Item SWEET_BERRY_PITS = addItem("sweet_berry_pits", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item GLOW_BERRY_PITS = addItem("glow_berry_pits", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item ROASTED_BERRIES = addItem("roasted_berries",
			new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build())));

	public static final Item PRICKLY_PEAR = addItem("prickly_pear", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(1).saturationModifier(0.3f).build())));
	public static final Item COOKED_PRICKLY_PEAR = addItem("cooked_prickly_pear", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.6f).build())));

	//	public static final Item PEANUT = new AliasedBlockItem(MBBlocks.PEANUT_CROP, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));
	public static final Item ROASTED_PEANUTS = addItem("roasted_peanuts", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.6f).build())));
	public static final Item HONEY_PEANUTS = addItem("honey_peanuts", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.6f).build())));
	public static final Item PEANUT_BRITTLE = addItem("peanut_brittle", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.3f).build())));
	public static final Item DOG_TREAT = addItem("dog_treat", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));

	public static final Item PUMPKIN_SLICE = addItem("pumpkin_slice", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.3f).build())));

	public static final Item SOURSOB = addItem("soursob", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(3).saturationModifier(0.3f).build())));
	public static final Item SYRUP_BOTTLE = addItem("syrup_bottle", new StatusClearingBottleItem(StatusEffects.SLOWNESS,
			new QuiltItemSettings().food(FoodComponents.HONEY_BOTTLE).recipeRemainder(Items.GLASS_BOTTLE).maxCount(16).group(MBItemGroup.MB_FOOD)));

	//	public static final Item PEPPER_SEEDS = new AliasedBlockItem(MBBlocks.PEPPER_CROP, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));
	public static final Item PEPPER = addItem("pepper", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(2).saturationModifier(0.3f).build())));
	public static final Item STUFFED_PEPPER = addItem("stuffed_pepper", new Item(new QuiltItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(6).saturationModifier(1.0f).build())));
	public static final Item CHILI = addItem("chili", new MushroomStewItem(new QuiltItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16)
			.food((new FoodComponent.Builder()).hunger(7).saturationModifier(0.8f).build())));

	// TODO: Death Cap
	// TODO: Ruby Heart (Gift 2 hearts to a mob)

	// Crafting Materials
	public static final Item GRASS_TUFT = addItem("grass_tuft", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	public static final Item SAP = addItem("sap", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item RESIN = addItem("resin", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	public static final Item BRITTLEBUSH = addItem("brittlebush", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item ICE_CHUNK = addItem("ice_chunk", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	public static final Item FIBER = addItem("fiber", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item BURLAP = addItem("burlap", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item MONSTER_HIDE = addItem("monster_hide", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	// Ores & Minerals
    public static final Item PEAT = addItem("peat", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	// TODO: What's the root called?
	// TODO: Fuzz Spores should get their own item class. I'm thinking they might work similar to bone meal
	public static final Item FUZZ_SPORES = addItem("fuzz_spores", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	// TODO: Truffle

	public static final Item MAGNETITE = addItem("magnetite", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item RUBY = addItem("ruby", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item HELIODOR = addItem("heliodor", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));
	public static final Item LARIMAR = addItem("larimar", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	public static final Item COPPER_NUGGET = addItem("copper_nugget", new Item(new QuiltItemSettings().group(MBItemGroup.MB_MISC)));

	// Tools and Weapons
	public static final Item FLINT_SWORD = addTool("flint_sword", new SwordItem(MBToolMaterials.FLINT, 3, -2.2F, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item FLINT_SHOVEL = addTool("flint_shovel", new ShovelItem(MBToolMaterials.FLINT, 2.5F, -2.6F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item FLINT_PICKAXE = addTool("flint_pickaxe", new MBPickaxeItem(MBToolMaterials.FLINT, 2, -2.8F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item FLINT_AXE = addTool("flint_axe", new MBAxeItem(MBToolMaterials.FLINT, 5.5F, -2.2F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item FLINT_HOE = addTool("flint_hoe", new MBHoeItem(MBToolMaterials.FLINT, 0, -1.0F, new Item.Settings().group(ItemGroup.TOOLS)));

	public static final Item WRENCH = addTool("wrench", new WrenchItem(new QuiltItemSettings().group(ItemGroup.TOOLS)));

	// TODO: Cottontail
	// TODO: Frog Cap

	// TODO: Jasper Helmet

	// TODO: Dynamite

	public static final Item ITEM_HOOK = addItem("item_hook", new ItemHookItem(MBEntities.ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR)));
	public static final Item GLOW_ITEM_HOOK = addItem("glow_item_hook", new ItemHookItem(MBEntities.GLOW_ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR)));

    public static final Item BABY_TURTLE_BUCKET = addItem("baby_turtle_bucket",
			new EntityBucketItem(EntityType.TURTLE, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
	(new Item.Settings()).maxCount(1).group(MBItemGroup.MB_MISC)));
    public static final Item BABY_STRIDER_BUCKET = addItem("baby_strider_bucket",
			new EntityBucketItem(EntityType.STRIDER, Fluids.LAVA, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
	(new Item.Settings()).maxCount(1).group(MBItemGroup.MB_MISC)));


	// Spawn eggs
	public static final Item GRIZZLY_SPAWN_EGG = addEgg("grizzly_bear_spawn_egg",
			new SpawnEggItem(MBEntities.GRIZZLY_BEAR, 5782045, 15707403, new Item.Settings().group(MBItemGroup.MB_MISC)));
	public static final Item GRASSHAT_SPAWN_EGG = addEgg("grasshat_spawn_egg",
			new SpawnEggItem(MBEntities.GRASSHAT, 7686462, 1485323, new Item.Settings().group(MBItemGroup.MB_MISC)));

	public static <T extends Item> T add(String id, T item) {
		return Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, id), item);
	}

	public static <T extends Item> T addItem(String id, T item) {
		MB_ITEMS.add(item);
		return add(id, item);
	}
	public static <T extends Item> T addTool(String id, T item) {
		MB_TOOLS.add(item);
		return add(id, item);
	}
	public static <T extends Item> T addEgg(String id, T item) {
		MB_EGGS.add(item);
		return add(id, item);
	}

	public static void registerItems(){
		for (Map.Entry<String, Block> pair : MBBlocks.BLOCK_ITEMS.entrySet()) {
			add(pair.getKey(), new BlockItem(pair.getValue(), new Item.Settings().group(MBItemGroup.CONSTRUCTION)));
		}
//		addItem("roasted_berries", ROASTED_BERRIES);
//		addItem("pumpkin_slice", PUMPKIN_SLICE);
//		addItem("ice_cubes", ICE_CHUNK);
//		addItem("frosthorn_seed", FROSTHORN_SEED);
//		addItem("pepper", PEPPER);
//		addItem("stuffed_pepper", STUFFED_PEPPER);
//		addItem("chili", CHILI);
//
//		addTool("flint_sword", FLINT_SWORD);
//		addTool("flint_shovel", FLINT_SHOVEL);
//		addTool("flint_pickaxe", FLINT_PICKAXE);
//		addTool("flint_axe", FLINT_AXE);
//		addTool("flint_hoe", FLINT_HOE);
//
//		addItem("prickly_pear", PRICKLY_PEAR);
//		addItem("cooked_prickly_pear", COOKED_PRICKLY_PEAR);
//
//		addItem("hardy_berry", HARDY_BERRY);
//		addItem("hardy_stem", HARDY_STEM);
//
//		addItem("brittlebush", BRITTLEBUSH);
//
////		add("peanut", PEANUT);
//		addItem("roasted_peanuts", ROASTED_PEANUTS);
////		addItem("peanut_chicken", PEANUT_CHICKEN);
//		addItem("honey_roasted_peanuts", HONEY_PEANUTS);
//		addItem("peanut_brittle", PEANUT_BRITTLE);
//		addItem("dog_treat", DOG_TREAT);
//
////		addItem("glow_berry_tart", GLOW_BERRY_TART);
////		addItem("honey_bun", HONEY_BUN);
////		addItem("brownie", BROWNIE);
////		addItem("fruit_pie", FRUIT_PIE);
////		addItem("porridge", PORRIDGE);
////		addItem("chicken_soup", CHICKEN_SOUP);
////		addItem("hearty_stew", HEARTY_STEW);
////		addItem("foraged_salad", FORAGED_SALAD);
////		addItem("vegetable_stir_fry", VEG_STIRFRY);
//
//		addItem("milk_bottle", MILK_BOTTLE);
//		addItem("syrup_bottle", SYRUP_BOTTLE);
//		addItem("sap", SAP);
//		addItem("resin", RESIN);
//
//		addItem("soursob", SOURSOB);
//
//		//addItem("apple_seeds", APPLE_SEEDS);
//
//		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "sweet_berry_pits"), SWEET_BERRY_PITS);
//		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "sweet_berry_pits"), new BlockItem(SWEET_BERRY_PITS, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));
//		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "glow_berry_pits"), GLOW_BERRY_PITS);
//		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "glow_berry_pits"), new BlockItem(GLOW_BERRY_PITS, new QuiltItemSettings().group(MBItemGroup.MB_FOOD)));
//
//		addItem("grass_tuft", GRASS_TUFT);
//		addItem("item_hook", ITEM_HOOK);
//		addItem("glow_item_hook", GLOW_ITEM_HOOK);
//
//		addItem("lamproot_boat", LAMPROOT_BOAT);
//		addItem("lamproot_chest_boat", LAMPROOT_CHEST_BOAT);
//		addItem("cedar_boat", CEDAR_BOAT);
//		addItem("cedar_chest_boat", CEDAR_CHEST_BOAT);
//
//		addTool("wrench", WRENCH);
//
//		addItem("baby_turtle_bucket", BABY_TURTLE_BUCKET);
//		addItem("baby_strider_bucket", BABY_STRIDER_BUCKET);
//
//		addItem("peat", PEAT);
//		addItem("raw_tin", RAW_TIN);
//		addItem("tin_ingot", TIN_INGOT);
//		addItem("tin_nugget", TIN_NUGGET);
//		addItem("magnetite", MAGNETITE);
//		addItem("copper_nugget", COPPER_NUGGET);
//		addItem("fur", FUR);
//		addItem("parasol_fiber", FIBER);
//		addItem("burlap", BURLAP);
//		addItem("monster_hide", MONSTER_HIDE);
//
//		addItem("glass_shard", GLASS_SHARD);
//		addItem("white_glass_shard", WHITE_GLASS_SHARD);
//		addItem("orange_glass_shard", ORANGE_GLASS_SHARD);
//		addItem("magenta_glass_shard", MAGENTA_GLASS_SHARD);
//		addItem("light_blue_glass_shard", LIGHT_BLUE_GLASS_SHARD);
//		addItem("yellow_glass_shard", YELLOW_GLASS_SHARD);
//		addItem("lime_glass_shard", LIME_GLASS_SHARD);
//		addItem("pink_glass_shard", PINK_GLASS_SHARD);
//		addItem("gray_glass_shard", GRAY_GLASS_SHARD);
//		addItem("light_gray_glass_shard", LIGHT_GRAY_GLASS_SHARD);
//		addItem("cyan_glass_shard", CYAN_GLASS_SHARD);
//		addItem("purple_glass_shard", PURPLE_GLASS_SHARD);
//		addItem("blue_glass_shard", BLUE_GLASS_SHARD);
//		addItem("brown_glass_shard", BROWN_GLASS_SHARD);
//		addItem("green_glass_shard", GREEN_GLASS_SHARD);
//		addItem("red_glass_shard", RED_GLASS_SHARD);
//		addItem("black_glass_shard", BLACK_GLASS_SHARD);
//
//		addEgg("grizzly_bear_spawn_egg", GRIZZLY_SPAWN_EGG);
//		addEgg("grasshat_spawn_egg", GRASSHAT_SPAWN_EGG);
	}
}
