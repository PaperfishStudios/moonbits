package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.*;
import net.paperfish.moonbits.block.FuzzSoilBlock;
import net.paperfish.moonbits.recipe.WashingRecipeJsonFactory;
import net.paperfish.moonbits.registry.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MBRecipeProvider extends FabricRecipeProvider {
    public MBRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private static final Map<MBBlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonFactory>> VARIANT_FACTORIES = new HashMap<>();

    // input, output, output amount
    public static final HashMap<List<ItemConvertible>, Integer> TRANSMUTE = new HashMap<>();
    public static final HashMap<ItemConvertible, ItemConvertible> COMPACT = new HashMap<>();

    public static final HashMap<ItemConvertible, ItemConvertible> SMELTING = new HashMap<>();
	public static final HashMap<ItemConvertible, ItemConvertible> SMOKING = new HashMap<>();
    public static final HashMap<ItemConvertible, ItemConvertible> BLASTING = new HashMap<>();
    public static final HashMap<ItemConvertible, ItemConvertible> FIRING = new HashMap<>();

    // 2x2 > 4x output block
    public static final HashMap<ItemConvertible, ItemConvertible> POLISH = new HashMap<>();

    public static final HashMap<ItemConvertible, ItemConvertible> BOOKSHELVES = new HashMap<>();
    public static final HashMap<ItemConvertible, ItemConvertible> PLANTER_BOXES = new HashMap<>();

    public static final HashMap<ItemConvertible, List<ItemConvertible>> CUTTING = new HashMap<>();

    // stair, slab, wall (auto-includes cutting)
    public static final HashMap<ItemConvertible, List<ItemConvertible>> PARTS_SET = new HashMap<>();

    // item, storage block
    public static final HashMap<ItemConvertible, ItemConvertible> STORAGE = new HashMap<>();

    public static final int DEFAULT_SMELT_TIME = 200;
    public static final int DEFAULT_BLAST_TIME = DEFAULT_SMELT_TIME / 2;
    public static final int DEFAULT_SMOKE_TIME = DEFAULT_BLAST_TIME;
    public static final int DEFAULT_FIRE_TIME = DEFAULT_BLAST_TIME;
    public static final int DEFAULT_CAMPFIRE_TIME = DEFAULT_SMELT_TIME * 3;

    public void addRecipes() {
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.BUTTON, (output, input) -> RecipesProvider.createTransmutationRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.CHISELED, (output, input) -> RecipesProvider.createChiseledBlockRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.CUT, (output, input) -> RecipesProvider.createCutCopperRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.DOOR, (output, input) -> RecipesProvider.createDoorRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.FENCE, (output, input) -> RecipesProvider.createFenceRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.FENCE_GATE, (output, input) -> RecipesProvider.createFenceGateRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.SIGN, (output, input) -> RecipesProvider.createSignRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.SLAB, (output, input) -> RecipesProvider.createSlabRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.STAIRS, (output, input) -> RecipesProvider.createStairsRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.PRESSURE_PLATE, (output, input) -> RecipesProvider.createPressurePlateRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.POLISHED, (output, input) -> RecipesProvider.createCondensingRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.TRAPDOOR, (output, input) -> RecipesProvider.createTrapdoorRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.WALL, (output, input) -> RecipesProvider.getWallRecipe(output, Ingredient.ofItems(input)));

        // 1-1 recipes
        TRANSMUTE.put(List.of(Items.CHARCOAL, Items.BLACK_DYE), 1);

        TRANSMUTE.put(List.of(MBBlocks.MARIGOLD, Items.ORANGE_DYE), 1);
        TRANSMUTE.put(List.of(MBBlocks.SOURSOBS, Items.YELLOW_DYE), 2);
		TRANSMUTE.put(List.of(MBItems.SOURSOB, Items.YELLOW_DYE), 1);

        TRANSMUTE.put(List.of(MBBlocks.FROSTY_HEATHER, Items.WHITE_DYE), 1);
        TRANSMUTE.put(List.of(MBBlocks.SUNSET_HEATHER, Items.ORANGE_DYE), 1);
        TRANSMUTE.put(List.of(MBBlocks.TWILIGHT_HEATHER, Items.PURPLE_DYE), 1);

        TRANSMUTE.put(List.of(MBBlocks.LUPINE, Items.PURPLE_DYE), 2);
		TRANSMUTE.put(List.of(MBBlocks.YUCCA, Items.WHITE_DYE), 2);

        TRANSMUTE.put(List.of(MBBlocks.SAFFRON_MUSHROOM, Items.ORANGE_DYE), 1);
		TRANSMUTE.put(List.of(MBBlocks.BONNET_MUSHROOM, Items.PURPLE_DYE), 1);
		TRANSMUTE.put(List.of(MBBlocks.AMITY_MUSHROOM, Items.PINK_DYE), 1);
		TRANSMUTE.put(List.of(MBBlocks.INKCAP_MUSHROOM, Items.BLACK_DYE), 1);

        TRANSMUTE.put(List.of(Items.SWEET_BERRIES, MBItems.SWEET_BERRY_PITS), 1);
        TRANSMUTE.put(List.of(Items.GLOW_BERRIES, MBItems.GLOW_BERRY_PITS), 1);

        TRANSMUTE.put(List.of(Items.IRON_NUGGET, MBItems.ITEM_HOOK), 1);

        // 2x2 recipes w/ output of one
        COMPACT.put(MBBlocks.PEBBLES, Blocks.COBBLESTONE);
        COMPACT.put(MBItems.PEAT, MBBlocks.PEAT_MOSS);
        COMPACT.put(MBItems.ICE_CHUNK, Blocks.ICE);

        COMPACT.put(MBItems.GRASS_TUFT, MBBlocks.GRASS_TURF);
        COMPACT.put(Items.CRIMSON_ROOTS, MBBlocks.CRIMSON_NYLIUM_TURF);
        COMPACT.put(Items.WARPED_ROOTS, MBBlocks.WARPED_NYLIUM_TURF);

        COMPACT.put(Items.RED_MUSHROOM, MBBlocks.RED_MUSHROOM_CAP);
        COMPACT.put(Items.BROWN_MUSHROOM, MBBlocks.BROWN_MUSHROOM_CAP);
        COMPACT.put(MBBlocks.SAFFRON_MUSHROOM, MBBlocks.SAFFRON_MUSHROOM_CAP);
		COMPACT.put(MBBlocks.BONNET_MUSHROOM, MBBlocks.BONNET_MUSHROOM_CAP);
		COMPACT.put(MBBlocks.AMITY_MUSHROOM, MBBlocks.AMITY_MUSHROOM_CAP);
		COMPACT.put(MBBlocks.INKCAP_MUSHROOM, MBBlocks.INKCAP_MUSHROOM_CAP);

        COMPACT.put(MBItems.GLASS_SHARD, Blocks.GLASS);
        COMPACT.put(MBItems.WHITE_GLASS_SHARD, Blocks.WHITE_STAINED_GLASS);
        COMPACT.put(MBItems.LIGHT_GRAY_GLASS_SHARD, Blocks.LIGHT_GRAY_STAINED_GLASS);
        COMPACT.put(MBItems.GRAY_GLASS_SHARD, Blocks.GRAY_STAINED_GLASS);
        COMPACT.put(MBItems.BLACK_GLASS_SHARD, Blocks.BLACK_STAINED_GLASS);
        COMPACT.put(MBItems.LIME_GLASS_SHARD, Blocks.LIME_STAINED_GLASS);
        COMPACT.put(MBItems.GREEN_GLASS_SHARD, Blocks.GREEN_STAINED_GLASS);
        COMPACT.put(MBItems.YELLOW_GLASS_SHARD, Blocks.YELLOW_STAINED_GLASS);
        COMPACT.put(MBItems.ORANGE_GLASS_SHARD, Blocks.ORANGE_STAINED_GLASS);
        COMPACT.put(MBItems.BROWN_GLASS_SHARD, Blocks.BROWN_STAINED_GLASS);
        COMPACT.put(MBItems.RED_GLASS_SHARD, Blocks.RED_STAINED_GLASS);
        COMPACT.put(MBItems.PINK_GLASS_SHARD, Blocks.PINK_STAINED_GLASS);
        COMPACT.put(MBItems.MAGENTA_GLASS_SHARD, Blocks.MAGENTA_STAINED_GLASS);
        COMPACT.put(MBItems.PURPLE_GLASS_SHARD, Blocks.PURPLE_STAINED_GLASS);
        COMPACT.put(MBItems.LIGHT_BLUE_GLASS_SHARD, Blocks.LIGHT_BLUE_STAINED_GLASS);
        COMPACT.put(MBItems.CYAN_GLASS_SHARD, Blocks.CYAN_STAINED_GLASS);
        COMPACT.put(MBItems.BLUE_GLASS_SHARD, Blocks.BLUE_STAINED_GLASS);

        // 2x2 recipe with an output of 4

        POLISH.put(MBBlocks.PEAT_MOSS, MBBlocks.PEAT_BRICKS);
        POLISH.put(Blocks.SNOW_BLOCK, MBBlocks.SNOW_BRICKS);

		POLISH.put(Blocks.CUT_COPPER, MBBlocks.COPPER_SHINGLES);
		POLISH.put(Blocks.EXPOSED_CUT_COPPER, MBBlocks.EXPOSED_COPPER_SHINGLES);
		POLISH.put(Blocks.WEATHERED_CUT_COPPER, MBBlocks.WEATHERED_COPPER_SHINGLES);
		POLISH.put(Blocks.OXIDIZED_CUT_COPPER, MBBlocks.OXIDIZED_COPPER_SHINGLES);
		POLISH.put(Blocks.WAXED_CUT_COPPER, MBBlocks.WAXED_COPPER_SHINGLES);
		POLISH.put(Blocks.WAXED_EXPOSED_CUT_COPPER, MBBlocks.WAXED_EXPOSED_COPPER_SHINGLES);
		POLISH.put(Blocks.WAXED_WEATHERED_CUT_COPPER, MBBlocks.WAXED_WEATHERED_COPPER_SHINGLES);
		POLISH.put(Blocks.WAXED_OXIDIZED_CUT_COPPER, MBBlocks.WAXED_OXIDIZED_COPPER_SHINGLES);

        // 3x3 unpackable recipe
        STORAGE.put(Items.APPLE, MBBlocks.APPLE_CRATE);
        STORAGE.put(Items.CARROT, MBBlocks.CARROT_CRATE);
        STORAGE.put(Items.POTATO, MBBlocks.POTATO_CRATE);
        STORAGE.put(Items.BEETROOT, MBBlocks.BEETROOT_CRATE);

        STORAGE.put(Items.EGG, MBBlocks.EGG_BASKET);
        STORAGE.put(Items.COCOA_BEANS, MBBlocks.COCOA_SACK);
        STORAGE.put(Items.SWEET_BERRIES, MBBlocks.SWEET_BERRY_BASKET);
        STORAGE.put(Items.GLOW_BERRIES, MBBlocks.GLOW_BERRY_BASKET);

        STORAGE.put(Items.SUGAR_CANE, MBBlocks.SUGAR_CANE_BUNDLE);
        STORAGE.put(Items.BAMBOO, MBBlocks.BAMBOO_BUNDLE);

        STORAGE.put(Items.KELP, MBBlocks.KELP_BLOCK);
        STORAGE.put(Items.GLISTERING_MELON_SLICE, MBBlocks.GLISTERING_MELON_BLOCK);
        STORAGE.put(Items.NETHER_WART, MBBlocks.NETHER_WART_SACK);
        STORAGE.put(Items.SCUTE, MBBlocks.SCUTE_BLOCK);
        STORAGE.put(Items.STRING, MBBlocks.SPOOL);
        STORAGE.put(Items.PAPER, MBBlocks.PAPER_BUNDLE);
        STORAGE.put(Items.STICK, MBBlocks.STICK_STACK);
		STORAGE.put(Items.LEATHER, MBBlocks.BOUND_LEATHER);
        STORAGE.put(Items.CHARCOAL, MBBlocks.CHARCOAL_LOG);
        STORAGE.put(Items.SUGAR, MBBlocks.SUGAR_CUBE);
        STORAGE.put(Items.GLOWSTONE_DUST, MBBlocks.PACKED_GLOWSTONE);
        STORAGE.put(Items.GUNPOWDER, MBBlocks.GUNPOWDER_CRATE);
        STORAGE.put(Items.CHORUS_FRUIT, MBBlocks.CHORUS_BUNDLE);
        STORAGE.put(Items.ROTTEN_FLESH, MBBlocks.ROTTEN_FLESH_BLOCK);
        STORAGE.put(Items.BONE, MBBlocks.BONE_BUNDLE);
        STORAGE.put(Items.SPIDER_EYE, MBBlocks.SPIDER_EYE_BLOCK);
        STORAGE.put(Items.PHANTOM_MEMBRANE, MBBlocks.PHANTOM_MEMBRANE_BLOCK);
        STORAGE.put(Items.BLAZE_ROD, MBBlocks.BLAZE_ROD_BUNDLE);
        STORAGE.put(Items.ENDER_PEARL, MBBlocks.ENDER_PEARL_BLOCK);

        STORAGE.put(MBItems.PEAT, MBBlocks.PEAT_BLOCK);
		STORAGE.put(MBItems.DAWNROOT, MBBlocks.DAWNROOT_BLOCK);

        STORAGE.put(MBItems.COPPER_NUGGET, Items.COPPER_INGOT);

		SMOKING.put(MBItems.DAWNROOT, MBItems.TAPIOCA);

        FIRING.put(MBBlocks.COBBLED_ANDESITE, Blocks.ANDESITE);
        FIRING.put(MBBlocks.COBBLED_DIORITE, Blocks.DIORITE);
        FIRING.put(MBBlocks.COBBLED_GRANITE, Blocks.GRANITE);
        FIRING.put(Blocks.DEEPSLATE, MBBlocks.SMOOTH_DEEPSLATE);

        FIRING.put(MBBlocks.COBBLED_CHERT, MBBlocks.CHERT);

        FIRING.put(MBBlocks.STONE_TILES, MBBlocks.CRACKED_STONE_TILES);
        FIRING.put(MBBlocks.ANDESITE_BRICKS, MBBlocks.CRACKED_ANDESITE_BRICKS);
        FIRING.put(MBBlocks.ANDESITE_TILES, MBBlocks.CRACKED_ANDESITE_TILES);
        FIRING.put(MBBlocks.DIORITE_BRICKS, MBBlocks.CRACKED_DIORITE_BRICKS);
        FIRING.put(MBBlocks.DIORITE_TILES, MBBlocks.CRACKED_DIORITE_TILES);
        FIRING.put(MBBlocks.GRANITE_BRICKS, MBBlocks.CRACKED_GRANITE_BRICKS);
        FIRING.put(MBBlocks.GRANITE_TILES, MBBlocks.CRACKED_GRANITE_TILES);
        FIRING.put(MBBlocks.SANDSTONE_BRICKS, MBBlocks.CRACKED_SANDSTONE_BRICKS);
        FIRING.put(MBBlocks.SANDSTONE_TILES, MBBlocks.CRACKED_SANDSTONE_TILES);
        FIRING.put(MBBlocks.RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_RED_SANDSTONE_BRICKS);
        FIRING.put(MBBlocks.RED_SANDSTONE_TILES, MBBlocks.CRACKED_RED_SANDSTONE_TILES);

        CUTTING.putAll(Map.of(
                Blocks.STONE, List.of(Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_WALL)
                //Blocks.DEEPSLATE, List.of(Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_WALL)
        ));
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        MBItemGroup.initialize();
        addRecipes();

        MBBlockFamilies.getFamilies().filter(MBBlockFamily::shouldGenerateRecipes)
                .forEach(family -> generateFamily(exporter, family));

        TRANSMUTE.forEach((blocks, count) -> transmute(exporter, blocks.get(0), blocks.get(1), count));

        SMELTING.forEach((in, out) -> smelting(exporter, in, out, 0.1f, DEFAULT_SMELT_TIME));

		SMOKING.forEach((in, out) -> smoking(exporter, in, out, 0.1f, DEFAULT_SMOKE_TIME));
		SMOKING.forEach((in, out) -> smelting(exporter, in, out, 0.1f, DEFAULT_SMELT_TIME));

        BLASTING.forEach((in, out) -> blasting(exporter, in, out, 0.1f, DEFAULT_BLAST_TIME));
        BLASTING.forEach((in, out) -> smelting(exporter, in, out, 0.1f, DEFAULT_SMELT_TIME));

        FIRING.forEach((in, out) -> firing(exporter, in, out, 0.1f, DEFAULT_FIRE_TIME));
        FIRING.forEach((in, out) -> smelting(exporter, in, out, 0.1f, DEFAULT_SMELT_TIME));

		// TODO: Waxing recipes r here!
        MBData.WAXING.forEach((input, output) -> ShapelessRecipeJsonFactory.create(output)
                .input(input).input(Items.HONEYCOMB)
                .group(RecipesProvider.getItemPath(output))
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipesProvider.convertBetween(output, Items.HONEYCOMB)));

		MBEvents.FUZZ_BAKING.forEach((input, output) -> {
			firing(exporter, input, output, 0.3f, DEFAULT_FIRE_TIME);
			smelting(exporter, input, output, 0.3f, DEFAULT_SMELT_TIME);
		});

        firing(exporter, Items.CLAY_BALL, Items.BRICK, 0.3f, DEFAULT_FIRE_TIME);
        firing(exporter, ItemTags.LOGS_THAT_BURN, Items.CHARCOAL, 0.15f, DEFAULT_FIRE_TIME, "has_log");
        firing(exporter, Items.CHORUS_FRUIT, Items.POPPED_CHORUS_FRUIT, 0.1f, DEFAULT_FIRE_TIME);
        firing(exporter, ItemTags.SAND, Items.GLASS, 0.15f, DEFAULT_FIRE_TIME, "has_sand");
        firing(exporter, Blocks.SEA_PICKLE, Items.LIME_DYE, 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.CACTUS, Items.GREEN_DYE, 1.0F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.CLAY, Blocks.TERRACOTTA, 0.35F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.NETHERRACK, Items.NETHER_BRICK, 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.WET_SPONGE, Blocks.SPONGE.asItem(), 0.15F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.COBBLESTONE, Blocks.STONE.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.STONE, Blocks.SMOOTH_STONE.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.SANDSTONE, Blocks.SMOOTH_SANDSTONE.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.BLACK_TERRACOTTA, Blocks.BLACK_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.BLUE_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.BROWN_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.CYAN_TERRACOTTA, Blocks.CYAN_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.GRAY_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.GREEN_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.LIME_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.MAGENTA_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.ORANGE_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.PINK_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.PURPLE_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.RED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.WHITE_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.YELLOW_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA.asItem(), 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.BASALT, Blocks.SMOOTH_BASALT, 0.1F, DEFAULT_FIRE_TIME);
        firing(exporter, Blocks.COBBLED_DEEPSLATE, Blocks.DEEPSLATE, 0.1F, DEFAULT_FIRE_TIME);

        washing(exporter, Blocks.WHITE_CONCRETE_POWDER, Blocks.WHITE_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.GRAY_CONCRETE_POWDER, Blocks.GRAY_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.BLACK_CONCRETE_POWDER, Blocks.BLACK_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.GREEN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.LIME_CONCRETE_POWDER, Blocks.LIME_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.YELLOW_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.ORANGE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.BROWN_CONCRETE_POWDER, Blocks.BROWN_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.RED_CONCRETE_POWDER, Blocks.RED_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.PINK_CONCRETE_POWDER, Blocks.PINK_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.PURPLE_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.CYAN_CONCRETE_POWDER, Blocks.CYAN_CONCRETE, Blocks.WATER_CAULDRON);
        washing(exporter, Blocks.BLUE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE, Blocks.WATER_CAULDRON);

        CUTTING.forEach((in, out) -> {
            out.forEach((output) -> {
                if (output instanceof SlabBlock) { // if the output is a slab it makes 2
                    RecipesProvider.offerStonecuttingRecipe(exporter, output, in, 2);
                }
                else {
                    RecipesProvider.offerStonecuttingRecipe(exporter, output, in);
                }
            });
        });

        COMPACT.forEach((in, out) -> condense(exporter, in, out, 1));
        POLISH.forEach((in, out) -> bricksRecipe(exporter, in, out));
        STORAGE.forEach((item, storage) -> compact(exporter, item, storage));

        // WOOD
        // oak logs
        woodStonecut(exporter, ItemTags.OAK_LOGS, Blocks.OAK_PLANKS, 4, "oak_logs");
        woodStonecut(exporter, ItemTags.OAK_LOGS, Blocks.OAK_SLAB, 8, "oak_logs");
        woodStonecut(exporter, ItemTags.OAK_LOGS, Blocks.OAK_STAIRS, 4, "oak_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD);
        reversibleCut(exporter, Blocks.OAK_WOOD, Blocks.OAK_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_OAK_LOG);
        // spruce logs
        woodStonecut(exporter, ItemTags.SPRUCE_LOGS, Blocks.SPRUCE_PLANKS, 4, "spruce_logs");
        woodStonecut(exporter, ItemTags.SPRUCE_LOGS, Blocks.SPRUCE_SLAB, 8, "spruce_logs");
        woodStonecut(exporter, ItemTags.SPRUCE_LOGS, Blocks.SPRUCE_STAIRS, 4, "spruce_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD);
        reversibleCut(exporter, Blocks.SPRUCE_WOOD, Blocks.SPRUCE_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG);
        // birch logs
        woodStonecut(exporter, ItemTags.BIRCH_LOGS, Blocks.BIRCH_PLANKS, 4, "birch_logs");
        woodStonecut(exporter, ItemTags.BIRCH_LOGS, Blocks.BIRCH_SLAB, 8, "birch_logs");
        woodStonecut(exporter, ItemTags.BIRCH_LOGS, Blocks.BIRCH_STAIRS, 4, "birch_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD);
        reversibleCut(exporter, Blocks.BIRCH_WOOD, Blocks.BIRCH_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG);
        // jungle logs
        woodStonecut(exporter, ItemTags.JUNGLE_LOGS, Blocks.JUNGLE_PLANKS, 4, "jungle_logs");
        woodStonecut(exporter, ItemTags.JUNGLE_LOGS, Blocks.JUNGLE_SLAB, 8, "jungle_logs");
        woodStonecut(exporter, ItemTags.JUNGLE_LOGS, Blocks.JUNGLE_STAIRS, 4, "jungle_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD);
        reversibleCut(exporter, Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG);
        // acacia logs
        woodStonecut(exporter, ItemTags.ACACIA_LOGS, Blocks.ACACIA_PLANKS, 4, "acacia_logs");
        woodStonecut(exporter, ItemTags.ACACIA_LOGS, Blocks.ACACIA_SLAB, 8, "acacia_logs");
        woodStonecut(exporter, ItemTags.ACACIA_LOGS, Blocks.ACACIA_STAIRS, 4, "acacia_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD);
        reversibleCut(exporter, Blocks.ACACIA_WOOD, Blocks.ACACIA_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG);
        // dark_oak logs
        woodStonecut(exporter, ItemTags.DARK_OAK_LOGS, Blocks.DARK_OAK_PLANKS, 4, "dark_oak_logs");
        woodStonecut(exporter, ItemTags.DARK_OAK_LOGS, Blocks.DARK_OAK_SLAB, 8, "dark_oak_logs");
        woodStonecut(exporter, ItemTags.DARK_OAK_LOGS, Blocks.DARK_OAK_STAIRS, 4, "dark_oak_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD);
        reversibleCut(exporter, Blocks.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG);
        // mangrove logs
        woodStonecut(exporter, ItemTags.MANGROVE_LOGS, Blocks.MANGROVE_PLANKS, 4, "mangrove_logs");
        woodStonecut(exporter, ItemTags.MANGROVE_LOGS, Blocks.MANGROVE_SLAB, 8, "mangrove_logs");
        woodStonecut(exporter, ItemTags.MANGROVE_LOGS, Blocks.MANGROVE_STAIRS, 4, "mangrove_logs");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD);
        reversibleCut(exporter, Blocks.MANGROVE_WOOD, Blocks.MANGROVE_LOG);
        reversibleCut(exporter, Blocks.STRIPPED_MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_LOG);
        // crimson logs
        woodStonecut(exporter, ItemTags.CRIMSON_STEMS, Blocks.CRIMSON_PLANKS, 4, "crimson_stems");
        woodStonecut(exporter, ItemTags.CRIMSON_STEMS, Blocks.CRIMSON_SLAB, 8, "crimson_stems");
        woodStonecut(exporter, ItemTags.CRIMSON_STEMS, Blocks.CRIMSON_STAIRS, 4, "crimson_stems");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE);
        reversibleCut(exporter, Blocks.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM);
        reversibleCut(exporter, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_STEM);
        // warped stems
        woodStonecut(exporter, ItemTags.WARPED_STEMS, Blocks.WARPED_PLANKS, 4, "warped_stems");
        woodStonecut(exporter, ItemTags.WARPED_STEMS, Blocks.WARPED_SLAB, 8, "warped_stems");
        woodStonecut(exporter, ItemTags.WARPED_STEMS, Blocks.WARPED_STAIRS, 4, "warped_stems");
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM);
        offerStonecuttingRecipe(exporter, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE);
        reversibleCut(exporter, Blocks.WARPED_HYPHAE, Blocks.WARPED_STEM);
        reversibleCut(exporter, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.STRIPPED_WARPED_STEM);
        // juniper logs
        woodStonecut(exporter, MBItemTags.LAMPROOT_LOGS, MBBlocks.LAMPROOT_PLANKS, 4, "lamproot_logs");
        woodStonecut(exporter, MBItemTags.LAMPROOT_LOGS, MBBlocks.LAMPROOT_SLAB, 8, "lamproot_logs");
        woodStonecut(exporter, MBItemTags.LAMPROOT_LOGS, MBBlocks.LAMPROOT_STAIRS, 4, "lamproot_logs");
        offerStonecuttingRecipe(exporter, MBBlocks.STRIPPED_LAMPROOT_LOG, MBBlocks.LAMPROOT_LOG);
        offerStonecuttingRecipe(exporter, MBBlocks.STRIPPED_LAMPROOT_WOOD, MBBlocks.LAMPROOT_WOOD);
        reversibleCut(exporter, MBBlocks.LAMPROOT_WOOD, MBBlocks.LAMPROOT_LOG);
        reversibleCut(exporter, MBBlocks.STRIPPED_LAMPROOT_WOOD, MBBlocks.STRIPPED_LAMPROOT_LOG);
        // cedar logs
        woodStonecut(exporter, MBItemTags.CEDAR_LOGS, MBBlocks.CEDAR_PLANKS, 4, "cedar_logs");
        woodStonecut(exporter, MBItemTags.CEDAR_LOGS, MBBlocks.CEDAR_SLAB, 8, "cedar_logs");
        woodStonecut(exporter, MBItemTags.CEDAR_LOGS, MBBlocks.CEDAR_STAIRS, 4, "cedar_logs");
        offerStonecuttingRecipe(exporter, MBBlocks.STRIPPED_CEDAR_LOG, MBBlocks.CEDAR_LOG);
        offerStonecuttingRecipe(exporter, MBBlocks.STRIPPED_CEDAR_WOOD, MBBlocks.CEDAR_WOOD);
        reversibleCut(exporter, MBBlocks.CEDAR_WOOD, MBBlocks.CEDAR_LOG);
        reversibleCut(exporter, MBBlocks.STRIPPED_CEDAR_WOOD, MBBlocks.STRIPPED_CEDAR_LOG);

        woodStonecut(exporter, ItemTags.LOGS, Items.STICK, 16, "logs");
        woodStonecut(exporter, ItemTags.PLANKS, Items.LADDER, 2, "planks");
        woodStonecut(exporter, ItemTags.PLANKS, Items.BOWL, 4, "planks");


        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.LAMPROOT_WOOD, MBBlocks.LAMPROOT_LOG);
        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.STRIPPED_LAMPROOT_WOOD, MBBlocks.STRIPPED_LAMPROOT_LOG);
        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.CEDAR_WOOD, MBBlocks.CEDAR_LOG);
        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.STRIPPED_CEDAR_WOOD, MBBlocks.STRIPPED_CEDAR_LOG);

        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.MUSHROOM_HYPHAE, MBBlocks.MUSHROOM_STEM);
        RecipesProvider.offerBarkBlockRecipe(exporter, MBBlocks.STRIPPED_MUSHROOM_HYPHAE, MBBlocks.STRIPPED_MUSHROOM_STEM);

        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.SANDSTONE_BRICKS);
        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, MBBlocks.SANDSTONE_BRICKS);
        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_SANDSTONE_BRICKS);
        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_BRICKS);
        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_BRICKS);
        RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_RED_SANDSTONE_BRICKS);

        firing(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 0.1f, DEFAULT_FIRE_TIME);
        smelting(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 0.1f, DEFAULT_SMELT_TIME);
        firing(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 0.1f, DEFAULT_FIRE_TIME);
        smelting(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 0.1f, DEFAULT_SMELT_TIME);

        ShapedRecipeJsonFactory.create(MBBlocks.PAVED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipesProvider.hasItem(MBBlocks.SANDSTONE_BRICKS), RecipesProvider.conditionsFromItem(MBBlocks.SANDSTONE_BRICKS)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipesProvider.hasItem(MBBlocks.CRACKED_SANDSTONE_BRICKS), RecipesProvider.conditionsFromItem(MBBlocks.CRACKED_SANDSTONE_BRICKS)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(MBBlocks.PAVED_RED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.RED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipesProvider.hasItem(MBBlocks.RED_SANDSTONE_BRICKS), RecipesProvider.conditionsFromItem(MBBlocks.RED_SANDSTONE_BRICKS)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipesProvider.hasItem(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS), RecipesProvider.conditionsFromItem(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)).offerTo(exporter);

		RecipesProvider.offerStonecuttingRecipe(exporter, MBBlocks.CHISELED_PACKED_MUD, Blocks.PACKED_MUD);

        ShapedRecipeJsonFactory.create(Items.TORCH, 2)
                .input('P', MBItems.PEAT).input('S', Items.STICK)
                .pattern("P").pattern("S")
                .criterion(RecipesProvider.hasItem(MBItems.PEAT), RecipesProvider.conditionsFromItem(MBItems.PEAT))
				.offerTo(exporter, new Identifier(Moonbits.MODID, "torch_from_peat"));

        ShapelessRecipeJsonFactory.create(MBItems.GLOW_ITEM_HOOK)
                .input(MBItems.ITEM_HOOK).input(Items.GLOW_INK_SAC)
                .criterion(RecipesProvider.hasItem(Items.GLOW_INK_SAC), RecipesProvider.conditionsFromItem(Items.GLOW_INK_SAC)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBItems.WRENCH)
                .input('C', Items.COPPER_INGOT).input('S', Items.STICK)
                .pattern(" C ").pattern(" SC").pattern("S  ")
                .criterion(RecipesProvider.hasItem(Items.COPPER_INGOT), RecipesProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.ROPE_LADDER, 3)
                .input('#', MBItems.FIBER).input('S', Items.STICK)
                .pattern("# #").pattern("SSS").pattern("# #")
                .criterion(RecipesProvider.hasItem(MBItems.FIBER), RecipesProvider.conditionsFromItem(MBItems.FIBER)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(MBBlocks.BEDROLL)
                .input('F', MBItems.MONSTER_HIDE).input('L', Items.LEATHER).m_hadhiznl('W', ItemTags.WOOL)
                .pattern("FFW").pattern("LLL")
                .criterion(RecipesProvider.hasItem(MBItems.MONSTER_HIDE), RecipesProvider.conditionsFromItem(MBItems.MONSTER_HIDE)).offerTo(exporter);
        ShapelessRecipeJsonFactory.create(Items.LEATHER)
                .input(MBItems.MONSTER_HIDE, 2)
                .criterion(RecipesProvider.hasItem(MBItems.MONSTER_HIDE), RecipesProvider.conditionsFromItem(MBItems.MONSTER_HIDE))
                .offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.SADDLE).input('#', MBItems.MONSTER_HIDE).input('I', Items.IRON_INGOT)
                .pattern("###").pattern("I I")
                .criterion(RecipesProvider.hasItem(MBItems.MONSTER_HIDE), RecipesProvider.conditionsFromItem(MBItems.MONSTER_HIDE)).offerTo(exporter);

//        ShapedRecipeJsonFactory.create(MBBlocks.LEATHER_SEAT)
//                .input('L', Items.LEATHER).m_hadhiznl('P', ItemTags.PLANKS)
//                .pattern("LLL").pattern("PPP")
//                .criterion(RecipesProvider.hasItem(Items.LEATHER), RecipesProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(MBBlocks.TREE_TAP)
                .input('I', Items.COPPER_INGOT).input('S', Items.STICK).m_hadhiznl('P', ItemTags.PLANKS)
                .pattern("I I").pattern("SPS")
                .criterion(RecipesProvider.hasItem(Items.COPPER_INGOT), RecipesProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(MBBlocks.SYRUP_BLOCK)
                .input('#', MBItems.SYRUP_BOTTLE)
                .pattern("##").pattern("##")
                .criterion(RecipesProvider.hasItem(MBItems.SYRUP_BOTTLE), RecipesProvider.conditionsFromItem(MBItems.SYRUP_BOTTLE)).offerTo(exporter);
        ShapelessRecipeJsonFactory.create(MBItems.SYRUP_BOTTLE, 4)
                .input(MBBlocks.SYRUP_BLOCK).input(Items.GLASS_BOTTLE, 4)
                .criterion(RecipesProvider.hasItem(MBItems.SYRUP_BOTTLE), RecipesProvider.conditionsFromItem(MBItems.SYRUP_BOTTLE))
                .offerTo(exporter);

		ShapelessRecipeJsonFactory.create(Items.TORCH, 4)
				.input(Items.STICK).input(MBItems.SAP, 3)
				.criterion("has_sap", RecipesProvider.conditionsFromItem(MBItems.SAP))
				.offerTo(exporter, new Identifier(Moonbits.MODID, "torch_from_sap"));

        campfire(exporter, Items.SWEET_BERRIES, MBItems.ROASTED_BERRIES, 0.1f,50);
        condense(exporter, MBItems.PUMPKIN_SLICE, Items.PUMPKIN, 1);

        ShapelessRecipeJsonFactory.create(MBItems.MILK_BOTTLE, 3)
                .input(Items.MILK_BUCKET).input(Items.GLASS_BOTTLE, 3)
                .criterion("has_milk", RecipesProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);
        ShapelessRecipeJsonFactory.create(Items.MILK_BUCKET)
                .input(Items.BUCKET).input(MBItems.MILK_BOTTLE, 3)
                .criterion("has_milk", RecipesProvider.conditionsFromItem(Items.MILK_BUCKET))
                .offerTo(exporter);

        ShapelessRecipeJsonFactory.create(Items.MUSHROOM_STEW)
                .m_jrksubfg(MBItemTags.EDIBLE_MUSHROOMS).m_jrksubfg(MBItemTags.EDIBLE_MUSHROOMS).input(Items.BOWL)
                .criterion("has_mushroom", RecipesProvider.method_10420(MBItemTags.EDIBLE_MUSHROOMS))
                .offerTo(exporter);

//        ShapedRecipeJsonFactory.create(MBItems.GLOW_BERRY_TART, 8)
//                .input('G', Items.GLOW_BERRIES).input('E', Items.EGG).input('W', Items.WHEAT).input('S', Items.SUGAR)
//                .pattern("GEG").pattern("WSW")
//                .criterion(RecipesProvider.hasItem(Items.GLOW_BERRIES), RecipesProvider.conditionsFromItem(Items.GLOW_BERRIES)).offerTo(exporter);

        ShapelessRecipeJsonFactory.create(Items.NAME_TAG)
                .input(Items.PAPER).input(Items.STRING).input(Items.INK_SAC)
                .criterion("tame_animal", TameAnimalCriterion.Conditions.any()).offerTo(exporter);

        ShapelessRecipeJsonFactory.create(Items.BOOK)
                .input(Items.PAPER).input(Items.PAPER).input(Items.PAPER).input(MBItems.BURLAP)
                .criterion("has_cloth", RecipesProvider.conditionsFromItem(MBItems.BURLAP))
				.offerTo(exporter, new Identifier(Moonbits.MODID, "dusty_cloth_book"));

        campfire(exporter, Items.STICK, Items.TORCH, 0.1f, 600);
        campfire(exporter, Items.WET_SPONGE, Items.SPONGE, 0f, 600);


		ShapedRecipeJsonFactory.create(MBItems.FLINT_SWORD).input('#', Items.FLINT).input('S', Items.STICK)
				.pattern("#").pattern("#").pattern("S")
				.criterion("has_flint", RecipesProvider.conditionsFromItem(Items.FLINT)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(MBItems.FLINT_SHOVEL).input('#', Items.FLINT).input('S', Items.STICK)
				.pattern("#").pattern("S").pattern("S")
				.criterion("has_flint", RecipesProvider.conditionsFromItem(Items.FLINT)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(MBItems.FLINT_PICKAXE).input('#', Items.FLINT).input('S', Items.STICK)
				.pattern("###").pattern(" S ").pattern(" S ")
				.criterion("has_flint", RecipesProvider.conditionsFromItem(Items.FLINT)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(MBItems.FLINT_AXE).input('#', Items.FLINT).input('S', Items.STICK)
				.pattern("##").pattern("#S").pattern(" S")
				.criterion("has_flint", RecipesProvider.conditionsFromItem(Items.FLINT)).offerTo(exporter);
		ShapedRecipeJsonFactory.create(MBItems.FLINT_HOE).input('#', Items.FLINT).input('S', Items.STICK)
				.pattern("##").pattern(" S").pattern(" S")
				.criterion("has_flint", RecipesProvider.conditionsFromItem(Items.FLINT)).offerTo(exporter);


        ShapedRecipeJsonFactory.create(Blocks.PODZOL, 4).input('D', Blocks.DIRT).input('G', Blocks.SAND)
                .pattern("DG").pattern("GD")
                .criterion("has_sand", RecipesProvider.conditionsFromItem(Blocks.SAND)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(MBBlocks.PERMAFROST, 4).input('D', Blocks.DIRT).input('G', Items.SNOWBALL)
                .pattern("DG").pattern("GD")
                .criterion("has_snow", RecipesProvider.conditionsFromItem(Items.SNOWBALL)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(Items.LEATHER_HORSE_ARMOR).input('#', Items.LEATHER).input('W', Ingredient.ofTag(ItemTags.WOOL))
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_leather", RecipesProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.IRON_HORSE_ARMOR).input('#', Items.IRON_INGOT).input('W', Ingredient.ofTag(ItemTags.WOOL))
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_iron", RecipesProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.GOLDEN_HORSE_ARMOR).input('#', Items.GOLD_INGOT).input('W', Ingredient.ofTag(ItemTags.WOOL))
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_gold", RecipesProvider.conditionsFromItem(Items.GOLD_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.DIAMOND_HORSE_ARMOR).input('#', Items.DIAMOND).input('W', Ingredient.ofTag(ItemTags.WOOL))
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_diamond", RecipesProvider.conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

        // mixed cobble recipes
        ShapedRecipeJsonFactory.create(Blocks.DISPENSER).input('R', Items.REDSTONE).input('#', Ingredient.ofTag(MBItemTags.COBBLESTONE)).input('X', Items.BOW)
                .pattern("###").pattern("#X#").pattern("#R#").criterion("has_bow", RecipesProvider.conditionsFromItem(Items.BOW))
				.offerTo(exporter, "dispenser_mixed_cobble");

        ShapedRecipeJsonFactory.create(Blocks.DISPENSER).input('#', Items.STICK).input('X', Items.STRING).input('D', Items.DROPPER)
                .pattern(" #X").pattern("#DX").pattern(" #X").criterion("has_string", RecipesProvider.conditionsFromItem(Items.STRING))
				.offerTo(exporter, "alt_dispenser");

        ShapedRecipeJsonFactory.create(Blocks.DROPPER).input('R', Items.REDSTONE).input('#', Ingredient.ofTag(MBItemTags.COBBLESTONE))
                .pattern("###").pattern("# #").pattern("#R#").criterion("has_redstone", RecipesProvider.conditionsFromItem(Items.REDSTONE))
				.offerTo(exporter, "dropper_mixed_cobble");
        ShapedRecipeJsonFactory.create(Blocks.LEVER).input('#', Ingredient.ofTag(MBItemTags.COBBLESTONE)).input('X', Items.STICK)
                .pattern("X").pattern("#").criterion("has_cobblestone", RecipesProvider.method_10420(MBItemTags.COBBLESTONE))
				.offerTo(exporter, "lever_mixed_cobble");
        ShapedRecipeJsonFactory.create(Blocks.OBSERVER).input('Q', Items.QUARTZ).input('R', Items.REDSTONE).input('#', Ingredient.ofTag(MBItemTags.COBBLESTONE))
                .pattern("###").pattern("RRQ").pattern("###").criterion("has_quartz", RecipesProvider.conditionsFromItem(Items.QUARTZ))
				.offerTo(exporter, "observer_mixed_cobble");
        ShapedRecipeJsonFactory.create(Blocks.PISTON).input('R', Items.REDSTONE).input('#', Ingredient.ofTag(MBItemTags.COBBLESTONE))
				.input('T', Ingredient.ofTag(ItemTags.PLANKS)).input('X', Items.IRON_INGOT)
                .pattern("TTT").pattern("#X#").pattern("#R#").criterion("has_redstone", RecipesProvider.conditionsFromItem(Items.REDSTONE))
				.offerTo(exporter, "piston_mixed_cobble");

        ShapedRecipeJsonFactory.create(Blocks.BLAST_FURNACE).input('#', MBBlocks.SMOOTH_DEEPSLATE).input('X', Blocks.FURNACE).input('I', Items.IRON_INGOT)
                .pattern("III").pattern("IXI").pattern("###").criterion("has_smooth_deepslate", RecipesProvider.conditionsFromItem(MBBlocks.SMOOTH_DEEPSLATE))
                .offerTo(exporter, "blast_furnace_deepslate");
    }

    public static void generateFamily(Consumer<RecipeJsonProvider> exporter, MBBlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
			Moonbits.LOGGER.info("generating recipe for: " + block);
            BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonFactory> biFunction = VARIANT_FACTORIES.get(variant);
            Block inputItem = getVariantRecipeInput(family, variant);
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID) || Objects.equals(Registry.BLOCK.getId(inputItem).getNamespace(), Moonbits.MODID)) { // should hopefully only make the recipe if its a new block..?
                if (biFunction != null) {
                    CraftingRecipeJsonFactory factory = biFunction.apply(block, inputItem);
                    family.getGroup().ifPresent(group -> factory.group(group + (variant == MBBlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                    factory.criterion(family.getUnlockCriterionName().orElseGet(() -> RecipesProvider.hasItem(inputItem)), RecipesProvider.conditionsFromItem(inputItem));
                    factory.offerTo(exporter);
                    if (block instanceof SlabBlock) {
                        offerStonecuttingRecipe(exporter, block, family.getBaseBlock(), 2);
                    }
                    else {
                        offerStonecuttingRecipe(exporter, block, family.getBaseBlock());
                    }
                }
                else if (variant == MBBlockFamily.Variant.CRACKED) {
                    RecipesProvider.offerCrackingRecipe(exporter, block, inputItem);
                    offerStonecuttingRecipe(exporter, block, inputItem);
                }
                else if (variant == MBBlockFamily.Variant.PILLAR || variant == MBBlockFamily.Variant.COLUMN) {
                    pillar(exporter, inputItem, block);
                    offerStonecuttingRecipe(exporter, block, family.getBaseBlock());
                }
                else if (variant == MBBlockFamily.Variant.MOSSY) {
                    mossy(exporter, inputItem, block);
                }
                else if (variant == MBBlockFamily.Variant.BOOKSHELF) {
                    bookshelf(exporter, inputItem, block);
                }
                else if (variant == MBBlockFamily.Variant.PLANTER_BOX) {
                    planterBox(exporter, inputItem, block);
                }
				else if (variant == MBBlockFamily.Variant.NETHER_PLANTER) {
					netherPlanter(exporter, inputItem, block);
				}
                else if (variant == MBBlockFamily.Variant.CARPET) {
                    carpetRecipe(exporter, inputItem, block);
                }
                else if (variant == MBBlockFamily.Variant.POLISHED) {
                    condense(exporter, inputItem, block, 4);
                }
                else if (variant == MBBlockFamily.Variant.CHISELED) {
                    offerChiseledBlockRecipe(exporter, inputItem, block);
                    offerStonecuttingRecipe(exporter, block, inputItem);
                }
                else if (variant == MBBlockFamily.Variant.CARVED) {
                    if (block instanceof SlabBlock) {
                        offerStonecuttingRecipe(exporter, block, inputItem, 2);
                    }
                    else {
                        offerStonecuttingRecipe(exporter, block, inputItem);
                    }
                }
            }
        });
        family.cuttable.forEach(block -> {
            Block inputItem = family.getBaseBlock();
//            if (Registry.BLOCK.getId(block).getNamespace().equals(Moonbits.MODID)) {
                if (block instanceof SlabBlock) {
                    offerStonecuttingRecipe(exporter, block, inputItem, 2);
                }
                else {
                    offerStonecuttingRecipe(exporter, block, inputItem);
                }
//            }
        });
        family.childBlocks.forEach(block -> {
            if (Registry.BLOCK.getId(block).getNamespace().equals(Moonbits.MODID)) {
                Block inputItem = family.getBaseBlock();
                if (block instanceof SlabBlock) {
                    offerStonecuttingRecipe(exporter, block, inputItem, 2);
                }
                else {
                    offerStonecuttingRecipe(exporter, block, inputItem);
                }
            }
        });
    }

    public static Block getVariantRecipeInput(MBBlockFamily family, MBBlockFamily.Variant variant) {
        if (variant == MBBlockFamily.Variant.CHISELED) {
            if (!family.getVariants().containsKey(MBBlockFamily.Variant.SLAB)) {
                throw new IllegalStateException("Slab is not defined for the " + family.getBaseBlock().toString() + " family.");
            }
            return family.getVariant(MBBlockFamily.Variant.SLAB);
        }
        if (variant == MBBlockFamily.Variant.PILLAR) {
            if (!family.getVariants().containsKey(MBBlockFamily.Variant.CHISELED)) {
                throw new IllegalStateException("Chiseled block is not defined for the " + family.getBaseBlock().toString() + " family.");
            }
            return family.getVariant(MBBlockFamily.Variant.CHISELED);
        }
        if (variant == MBBlockFamily.Variant.COLUMN) {
            if (!family.getVariants().containsKey(MBBlockFamily.Variant.CARVED)) {
                throw new IllegalStateException("Carved block is not defined for the " + family.getBaseBlock().toString() + " family.");
            }
            return family.getVariant(MBBlockFamily.Variant.CARVED);
        }
        return family.getBaseBlock();
    }

    public static void woodStonecut(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible output, int count, String criteria) {
        SingleItemRecipeJsonFactory.createStonecutting(Ingredient.ofTag(input), output, count)
                .criterion("has_" + criteria, RecipesProvider.method_10420(input)).offerTo(exporter, RecipesProvider.getItemPath(output) + "_from_" + criteria + "_stonecutting");
    }
    public static void reversibleCut(Consumer<RecipeJsonProvider> exporter, ItemConvertible one, ItemConvertible two) {
        offerStonecuttingRecipe(exporter, one, two);
        offerStonecuttingRecipe(exporter, two, one);
    }

    // 1:1, 2x2 & 3x3 recipes
    public static void transmute(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, int count) {
        ShapelessRecipeJsonFactory.create(output, count).input(input)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipesProvider.convertBetween(output, input));
    }
    public static void condense(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, int count) {
        ShapedRecipeJsonFactory.create(output, count).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipesProvider.convertBetween(output, input));
    }
    public static void compact(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible compacted) {
        ShapelessRecipeJsonFactory.create(input, 9).input(compacted)
                .criterion(RecipesProvider.hasItem(compacted), RecipesProvider.conditionsFromItem(compacted)).offerTo(exporter, RecipesProvider.convertBetween(compacted, input));
        ShapedRecipeJsonFactory.create(compacted).input('#', input).pattern("###").pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void crossRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible inputB, ItemConvertible output, int count) {
        ShapedRecipeJsonFactory.create(output, count).input('#', input).input('B', inputB).pattern("#B").pattern("B#")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void bricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 4).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void slabRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 6).input('#', input).pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input, 2);
    }
    public static void stairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 4).input('#', input).pattern("#  ").pattern("## ").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void wallRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 6).input('#', input).pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void paneRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 16).input('#', input).pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input, 3);
    }
    public static void insetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible base, ItemConvertible insert, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 4)
                .input('#', base).input('i', insert)
                .pattern(" # ").pattern("#i#").pattern(" # ")
                .criterion(RecipesProvider.hasItem(insert), RecipesProvider.conditionsFromItem(insert))
                .offerTo(exporter);

    }

    public static void smelting(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.SMELTING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "smelting_" + RecipesProvider.getItemPath(output)));
    }
    public static void blasting(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.BLASTING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "blasting_" + RecipesProvider.getItemPath(output)));
    }
    public static void smoking(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.SMOKING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "smoking_" + RecipesProvider.getItemPath(output)));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, MBData.KILN_RECIPE_SERIALIZER)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "firing_" + RecipesProvider.getItemPath(output)));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible output, float experience, int cookingTime, String criterion) {
        CookingRecipeJsonFactory.create(Ingredient.ofTag(input), output, experience, cookingTime, MBData.KILN_RECIPE_SERIALIZER)
                .criterion(criterion, RecipesProvider.method_10420(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "firing_" + RecipesProvider.getItemPath(output)));
    }
    public static void campfire(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "campfire_" + RecipesProvider.getItemPath(output)));
    }
    public static void washing(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, Block cauldron) {
        WashingRecipeJsonFactory.create(Ingredient.ofItems(input.asItem()), output.asItem(), cauldron)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(Moonbits.MODID, "washing_" + RecipesProvider.getItemPath(output)));
    }

    public static void planksRecipe(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible output) {
        ShapelessRecipeJsonFactory.create(output, 4).input(Ingredient.ofTag(input)).group("planks")
                .criterion("has_logs", RecipesProvider.method_10420(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, (ItemConvertible) input.registry());
    }
    public static void barkRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").pattern("##").group("bark")
                .criterion("has_log", RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void boatRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output).input('#', input).pattern("# #").pattern("###").group("boat")
                .criterion("in_water", RecipesProvider.requireEnteringFluid(Blocks.WATER))
                .offerTo(exporter);
    }
    public static void bookshelf(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output).input('#', input).input('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###")
                .criterion(RecipesProvider.hasItem(Items.BOOK), RecipesProvider.conditionsFromItem(Items.BOOK)).group("bookshelves")
                .offerTo(exporter);
    }
    public static void planterBox(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output).input('#', input).input('P', MBItems.PEAT).pattern("# #").pattern("#P#").pattern("###")
                .criterion(RecipesProvider.hasItem(MBItems.PEAT), RecipesProvider.conditionsFromItem(MBItems.PEAT)).group("planter_boxes")
                .offerTo(exporter);
    }
	public static void netherPlanter(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
		ShapedRecipeJsonFactory.create(output).input('#', input).m_hadhiznl('S', ItemTags.SOUL_FIRE_BASE_BLOCKS).pattern("# #").pattern("#S#").pattern("###")
				.criterion(RecipesProvider.hasItem(Items.SOUL_SAND), RecipesProvider.method_10420(ItemTags.SOUL_FIRE_BASE_BLOCKS)).group("nether_planters")
				.offerTo(exporter);
	}
	public static void desertPlanter(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
		ShapedRecipeJsonFactory.create(output).input('#', input).input('P', MBItems.FIBER).m_hadhiznl('S', MBItemTags.SANDY_SOILS)
				.pattern("#S#").pattern("#P#").pattern("###")
				.criterion(RecipesProvider.hasItem(MBItems.FIBER), RecipesProvider.conditionsFromItem(MBItems.FIBER)).group("desertPlanter")
				.offerTo(exporter);
	}

    public static void doorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").pattern("##").pattern("##")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void trapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 2).input('#', input).pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void fenceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 3).input('W', input).input('#', Items.STICK).pattern("W#W").pattern("W#W")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void fenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output).input('#', Items.STICK).input('W', input).pattern("#W#").pattern("#W#")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void pressurePlateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output).input('#', input).pattern("##")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void signRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output, 3).group("sign").input('#', input).input('X', Items.STICK).pattern("###").pattern("###").pattern(" X ")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void carpetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").group("carpet")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void pillar(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 2).input('#', input).pattern("#").pattern("#")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, input);
    }

    public static void mossy(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapelessRecipeJsonFactory.create(output, 1).input(input).input(Blocks.VINE)
                .criterion("has_vines", RecipesProvider.conditionsFromItem(Blocks.VINE))
                .offerTo(exporter);
    }
}
