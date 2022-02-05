package net.paperfish.moonbits;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.item.*;

import java.util.ArrayList;
import java.util.List;

public class MBItems {
	public static List<Item> MB_ITEMS = new ArrayList<>();
	public static List<Item> MB_TOOLS = new ArrayList<>();
	public static List<Item> MB_EGGS = new ArrayList<>();
    
	public static final Item GRASS_TUFT = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item ITEM_HOOK = new ItemHookItem(MBEntities.ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));
	public static final Item GLOW_ITEM_HOOK = new ItemHookItem(MBEntities.GLOW_ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));

	public static final Item LETTUCE_SEEDS = new AliasedBlockItem(MBBlocks.LETTUCE_CROP, new FabricItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Item LETTUCE_LEAF = new Item(new FabricItemSettings().group(MBItemGroup.MB_FOOD)
			.food((new FoodComponent.Builder()).hunger(1).saturationModifier(0.3f).build()));
	public static final Item SALAD = new StewItem(new FabricItemSettings().group(MBItemGroup.MB_FOOD).maxCount(16).food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.8f)
			.statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1), 1.0f).build()));

	public static final Item APPLE_SEEDS = new AliasedBlockItem(MBBlocks.APPLE_OAK_SPROUT, new FabricItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Block SWEET_BERRY_PITS = new SweetBerryPitsBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH));
	public static final Block GLOW_BERRY_PITS = new GlowBerryPitsBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES));
	public static final Item ROASTED_BERRIES = new Item(new FabricItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build()));

	public static final Item GLOW_BERRY_TART = new Item(new FabricItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.8F)
			.statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400, 1), 1.0f).build()));

	public static final Item HONEY_BUN = new Item(new FabricItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(5).saturationModifier(0.8F).build()));

    public static final Item PEAT = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item FUR = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));


	public static final Item POPPY_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item DANDELION_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item OXEYE_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item ALLIUM_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item LILY_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item ORCHID_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item CORNFLOWER_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item AZURE_BLUET_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item TULIP_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));

	public static final Item BUTTERCUP_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item FORGETMENOT_CROWN = new ArmorItem(MBArmorMaterials.FLOWER_CROWN, EquipmentSlot.HEAD, new Item.Settings().group(MBItemGroup.UTILITY));

	public static final Item WRENCH = new WrenchItem(new FabricItemSettings().group(MBItemGroup.UTILITY));
	
    public static final Item BABY_TURTLE_BUCKET = new EntityBucketItem(EntityType.TURTLE, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
	(new Item.Settings()).maxCount(1).group(MBItemGroup.UTILITY));
    public static final Item BABY_STRIDER_BUCKET = new EntityBucketItem(EntityType.STRIDER, Fluids.LAVA, SoundEvents.ITEM_BUCKET_EMPTY_FISH, 
	(new Item.Settings()).maxCount(1).group(MBItemGroup.UTILITY));
    
	// Glass Shards
	public static final Item GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item WHITE_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item LIGHT_GRAY_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item GRAY_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item BLACK_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item GREEN_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item LIME_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item YELLOW_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item ORANGE_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item BROWN_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item RED_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item PINK_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item MAGENTA_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item PURPLE_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item LIGHT_BLUE_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item CYAN_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item BLUE_GLASS_SHARD = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));


	// Spawn eggs
	public static final Item MOOBLOOM_SPAWN_EGG = new SpawnEggItem(MBEntities.MOOBLOOM, 16771888, 16777215, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item GRIZZLY_SPAWN_EGG = new SpawnEggItem(MBEntities.GRIZZLY_BEAR, 5782045, 15707403, new Item.Settings().group(MBItemGroup.UTILITY));
	public static final Item GLARE_SPAWN_EGG = new SpawnEggItem(MBEntities.GLARE, 5403430, 657930, new Item.Settings().group(MBItemGroup.UTILITY));

	public static void add(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, id), item);
	}
	public static void addItem(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, id), item);
		MB_ITEMS.add(item);
	}

	public static void addTool(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, id), item);
		MB_TOOLS.add(item);
	}
	public static void addEgg(String id, Item item) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, id), item);
		MB_EGGS.add(item);
	}
    
	public static void registerItems(){
		addItem("roasted_berries", ROASTED_BERRIES);
		addItem("glow_berry_tart", GLOW_BERRY_TART);
		addItem("honey_bun", HONEY_BUN);
		addItem("lettuce_leaf", LETTUCE_LEAF);
		addItem("salad", SALAD);

		addItem("apple_seeds", APPLE_SEEDS);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MOD_ID, "sweet_berry_pits"), SWEET_BERRY_PITS);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, "sweet_berry_pits"), new BlockItem(SWEET_BERRY_PITS, new FabricItemSettings().group(MBItemGroup.MB_FOOD)));
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MOD_ID, "glow_berry_pits"), GLOW_BERRY_PITS);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MOD_ID, "glow_berry_pits"), new BlockItem(GLOW_BERRY_PITS, new FabricItemSettings().group(MBItemGroup.MB_FOOD)));

		add("lettuce_seeds", LETTUCE_SEEDS);

		addItem("grass_tuft", GRASS_TUFT);
		addItem("item_hook", ITEM_HOOK);
		addItem("glow_item_hook", GLOW_ITEM_HOOK);

		addItem("poppy_crown", POPPY_CROWN);
		addItem("dandelion_crown", DANDELION_CROWN);
		addItem("oxeye_daisy_crown", OXEYE_CROWN);
		addItem("allium_crown", ALLIUM_CROWN);
		addItem("lily_of_the_valley_crown", LILY_CROWN);
		addItem("blue_orchid_crown", ORCHID_CROWN);
		addItem("cornflower_crown", CORNFLOWER_CROWN);
		addItem("azure_bluet_crown", AZURE_BLUET_CROWN);
		addItem("tulip_crown", TULIP_CROWN);

		addItem("buttercup_crown", BUTTERCUP_CROWN);
		addItem("forget_me_not_crown", FORGETMENOT_CROWN);

		addTool("wrench", WRENCH);
		//Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "redstone_"), WRENCH);

		addItem("baby_turtle_bucket", BABY_TURTLE_BUCKET);
		addItem("baby_strider_bucket", BABY_STRIDER_BUCKET);

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

		addItem("peat", PEAT);
		addItem("copper_nugget", COPPER_NUGGET);
		addItem("fur", FUR);

		addEgg("moobloom_spawn_egg", MOOBLOOM_SPAWN_EGG);
		addEgg("grizzly_bear_spawn_egg", GRIZZLY_SPAWN_EGG);
		addEgg("glare_spawn_egg", GLARE_SPAWN_EGG);
	} 
}
