package net.moonteam.moonbits;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moonteam.moonbits.item.*;

public class MBItems {
    
	public static final Item GRASS_TUFT = new Item(new FabricItemSettings().group(MBItemGroup.MATERIALS));
	public static final Item ITEM_HOOK = new ItemHookItem(MBEntities.ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));
	public static final Item GLOW_ITEM_HOOK = new ItemHookItem(MBEntities.GLOW_ITEM_HOOK_ENTITY, (new Item.Settings()).group(MBItemGroup.DECOR));

	public static final Item APPLE_SEEDS = new AliasedBlockItem(MBBlocks.APPLE_OAK_SPROUT, new FabricItemSettings().group(MBItemGroup.MB_FOOD));
	public static final Block SWEET_BERRY_PITS_BLOCK = new SweetBerryPitsBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH));
	public static final Block GLOW_BERRY_PITS_BLOCK = new GlowBerryPitsBlock(AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().noCollision().luminance(CaveVines.getLuminanceSupplier(14)).breakInstantly().sounds(BlockSoundGroup.CAVE_VINES));
	public static final Item ROASTED_BERRIES = new Item(new FabricItemSettings().group(MBItemGroup.MB_FOOD).food((new FoodComponent.Builder()).hunger(4).saturationModifier(0.8F).build()));

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
	
    
	public static void registerItems(){
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "apple_seeds"), APPLE_SEEDS);
		Registry.register(Registry.BLOCK, new Identifier(MoonbitsMain.MOD_ID, "sweet_berry_pits"), SWEET_BERRY_PITS_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "sweet_berry_pits"), new BlockItem(SWEET_BERRY_PITS_BLOCK, new FabricItemSettings().group(MBItemGroup.MB_FOOD)));
		Registry.register(Registry.BLOCK, new Identifier(MoonbitsMain.MOD_ID, "glow_berry_pits"), GLOW_BERRY_PITS_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "glow_berry_pits"), new BlockItem(GLOW_BERRY_PITS_BLOCK, new FabricItemSettings().group(MBItemGroup.MB_FOOD)));
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "roasted_berries"), ROASTED_BERRIES);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "honey_bun"), HONEY_BUN);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "grass_tuft"), GRASS_TUFT);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "item_hook"), ITEM_HOOK);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "glow_item_hook"), GLOW_ITEM_HOOK);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "poppy_crown"), POPPY_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "dandelion_crown"), DANDELION_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "oxeye_daisy_crown"), OXEYE_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "allium_crown"), ALLIUM_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "lily_of_the_valley_crown"), LILY_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "blue_orchid_crown"), ORCHID_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "cornflower_crown"), CORNFLOWER_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "azure_bluet_crown"), AZURE_BLUET_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "tulip_crown"), TULIP_CROWN);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "buttercup_crown"), BUTTERCUP_CROWN);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "forget_me_not_crown"), FORGETMENOT_CROWN);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "wrench"), WRENCH);
		//Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "redstone_"), WRENCH);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "baby_turtle_bucket"), BABY_TURTLE_BUCKET);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "baby_strider_bucket"), BABY_STRIDER_BUCKET);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "glass_shard"), GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "white_glass_shard"), WHITE_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "orange_glass_shard"), ORANGE_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "magenta_glass_shard"), MAGENTA_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "light_blue_glass_shard"), LIGHT_BLUE_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "yellow_glass_shard"), YELLOW_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "lime_glass_shard"), LIME_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "pink_glass_shard"), PINK_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "gray_glass_shard"), GRAY_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "light_gray_glass_shard"), LIGHT_GRAY_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "cyan_glass_shard"), CYAN_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "purple_glass_shard"), PURPLE_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "blue_glass_shard"), BLUE_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "brown_glass_shard"), BROWN_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "green_glass_shard"), GREEN_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "red_glass_shard"), RED_GLASS_SHARD);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "black_glass_shard"), BLACK_GLASS_SHARD);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "peat"), PEAT);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "copper_nugget"), COPPER_NUGGET);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "fur"), FUR);

		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "moobloom_spawn_egg"), MOOBLOOM_SPAWN_EGG);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "grizzly_bear_spawn_egg"), GRIZZLY_SPAWN_EGG);
		Registry.register(Registry.ITEM, new Identifier(MoonbitsMain.MOD_ID, "glare_spawn_egg"), GLARE_SPAWN_EGG);
	} 
}
