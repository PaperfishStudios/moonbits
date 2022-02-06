package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipesProvider;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.*;
import net.paperfish.moonbits.registry.MBBlockFamilies;
import net.paperfish.moonbits.registry.MBBlockFamily;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class MBRecipeProvider extends FabricRecipesProvider {
    public MBRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    private static Map<MBBlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonFactory>> VARIANT_FACTORIES = new HashMap<>();

    // input, output, output amount
    public static final HashMap<List<ItemConvertible>, Integer> TRANSMUTE = new HashMap<>();
    public static final HashMap<ItemConvertible, ItemConvertible> COMPACT = new HashMap<>();

    public static final HashMap<ItemConvertible, ItemConvertible> SMELTING = new HashMap<>();
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

        PARTS_SET.putAll(Map.of(
                MBBlocks.TOUGH_DIRT, List.of(MBBlocks.TOUGH_DIRT_SLAB, MBBlocks.TOUGH_DIRT_STAIRS),
                MBBlocks.DIRT_BRICKS, List.of(MBBlocks.DIRT_BRICK_SLAB, MBBlocks.DIRT_BRICK_STAIRS),
                MBBlocks.PEAT_BRICKS, List.of(MBBlocks.PEAT_BRICK_SLAB, MBBlocks.PEAT_BRICK_STAIRS, MBBlocks.PEAT_BRICK_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.JACARANDA_PLANKS, List.of(MBBlocks.JACARANDA_SLAB, MBBlocks.JACARANDA_STAIRS, MBBlocks.JACARANDA_FENCE, MBBlocks.JACARANDA_FENCE_GATE),
                MBBlocks.HONEY_PLANKS, List.of(MBBlocks.HONEY_SLAB, MBBlocks.HONEY_STAIRS, MBBlocks.HONEY_FENCE, MBBlocks.HONEY_FENCE_GATE)
        )); PARTS_SET.putAll(Map.of(
                Blocks.HONEYCOMB_BLOCK, List.of(MBBlocks.HONEYCOMB_SLAB, MBBlocks.HONEYCOMB_STAIRS, MBBlocks.HONEYCOMB_WALL),
                MBBlocks.HONEYCOMB_BRICKS, List.of(MBBlocks.HONEYCOMB_BRICK_SLAB, MBBlocks.HONEYCOMB_BRICK_STAIRS, MBBlocks.HONEYCOMB_BRICK_WALL),
                MBBlocks.HONEYCOMB_TILES, List.of(MBBlocks.HONEYCOMB_TILE_SLAB, MBBlocks.HONEYCOMB_TILE_STAIRS, MBBlocks.HONEYCOMB_TILE_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.STONE_TILES, List.of(MBBlocks.STONE_TILE_SLAB, MBBlocks.STONE_TILE_STAIRS, MBBlocks.STONE_TILE_WALL),
                MBBlocks.CRACKED_STONE_TILES, List.of(MBBlocks.CRACKED_STONE_TILE_SLAB, MBBlocks.CRACKED_STONE_TILE_STAIRS, MBBlocks.CRACKED_STONE_TILE_WALL),
                MBBlocks.MOSSY_STONE_TILES, List.of(MBBlocks.MOSSY_STONE_TILE_SLAB, MBBlocks.MOSSY_STONE_TILE_STAIRS, MBBlocks.MOSSY_STONE_TILE_WALL),
                MBBlocks.ANDESITE_BRICKS, List.of(MBBlocks.ANDESITE_BRICK_SLAB, MBBlocks.ANDESITE_BRICK_STAIRS, MBBlocks.ANDESITE_BRICK_WALL),
                MBBlocks.CRACKED_ANDESITE_BRICKS, List.of(MBBlocks.CRACKED_ANDESITE_BRICK_SLAB, MBBlocks.CRACKED_ANDESITE_BRICK_STAIRS, MBBlocks.CRACKED_ANDESITE_BRICK_WALL),
                MBBlocks.MOSSY_ANDESITE_BRICKS, List.of(MBBlocks.MOSSY_ANDESITE_BRICK_SLAB, MBBlocks.MOSSY_ANDESITE_BRICK_STAIRS, MBBlocks.MOSSY_ANDESITE_BRICK_WALL),
                MBBlocks.ANDESITE_TILES, List.of(MBBlocks.ANDESITE_TILE_SLAB, MBBlocks.ANDESITE_TILE_STAIRS, MBBlocks.ANDESITE_TILE_WALL),
                MBBlocks.CRACKED_ANDESITE_TILES, List.of(MBBlocks.CRACKED_ANDESITE_TILE_SLAB, MBBlocks.CRACKED_ANDESITE_TILE_STAIRS, MBBlocks.CRACKED_ANDESITE_TILE_WALL),
                MBBlocks.MOSSY_ANDESITE_TILES, List.of(MBBlocks.MOSSY_ANDESITE_TILE_SLAB, MBBlocks.MOSSY_ANDESITE_TILE_STAIRS, MBBlocks.MOSSY_ANDESITE_TILE_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.DIORITE_BRICKS, List.of(MBBlocks.DIORITE_BRICK_SLAB, MBBlocks.DIORITE_BRICK_STAIRS, MBBlocks.DIORITE_BRICK_WALL),
                MBBlocks.CRACKED_DIORITE_BRICKS, List.of(MBBlocks.CRACKED_DIORITE_BRICK_SLAB, MBBlocks.CRACKED_DIORITE_BRICK_STAIRS, MBBlocks.CRACKED_DIORITE_BRICK_WALL),
                MBBlocks.MOSSY_DIORITE_BRICKS, List.of(MBBlocks.MOSSY_DIORITE_BRICK_SLAB, MBBlocks.MOSSY_DIORITE_BRICK_STAIRS, MBBlocks.MOSSY_DIORITE_BRICK_WALL),
                MBBlocks.DIORITE_TILES, List.of(MBBlocks.DIORITE_TILE_SLAB, MBBlocks.DIORITE_TILE_STAIRS, MBBlocks.DIORITE_TILE_WALL),
                MBBlocks.CRACKED_DIORITE_TILES, List.of(MBBlocks.CRACKED_DIORITE_TILE_SLAB, MBBlocks.CRACKED_DIORITE_TILE_STAIRS, MBBlocks.CRACKED_DIORITE_TILE_WALL),
                MBBlocks.MOSSY_DIORITE_TILES, List.of(MBBlocks.MOSSY_DIORITE_TILE_SLAB, MBBlocks.MOSSY_DIORITE_TILE_STAIRS, MBBlocks.MOSSY_DIORITE_TILE_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.GRANITE_BRICKS, List.of(MBBlocks.GRANITE_BRICK_SLAB, MBBlocks.GRANITE_BRICK_STAIRS, MBBlocks.GRANITE_BRICK_WALL),
                MBBlocks.CRACKED_GRANITE_BRICKS, List.of(MBBlocks.CRACKED_GRANITE_BRICK_SLAB, MBBlocks.CRACKED_GRANITE_BRICK_STAIRS, MBBlocks.CRACKED_GRANITE_BRICK_WALL),
                MBBlocks.MOSSY_GRANITE_BRICKS, List.of(MBBlocks.MOSSY_GRANITE_BRICK_SLAB, MBBlocks.MOSSY_GRANITE_BRICK_STAIRS, MBBlocks.MOSSY_GRANITE_BRICK_WALL),
                MBBlocks.GRANITE_TILES, List.of(MBBlocks.GRANITE_TILE_SLAB, MBBlocks.GRANITE_TILE_STAIRS, MBBlocks.GRANITE_TILE_WALL),
                MBBlocks.CRACKED_GRANITE_TILES, List.of(MBBlocks.CRACKED_GRANITE_TILE_SLAB, MBBlocks.CRACKED_GRANITE_TILE_STAIRS, MBBlocks.CRACKED_GRANITE_TILE_WALL),
                MBBlocks.MOSSY_GRANITE_TILES, List.of(MBBlocks.MOSSY_GRANITE_TILE_SLAB, MBBlocks.MOSSY_GRANITE_TILE_STAIRS, MBBlocks.MOSSY_GRANITE_TILE_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.SANDSTONE_BRICKS, List.of(MBBlocks.SANDSTONE_BRICK_SLAB, MBBlocks.SANDSTONE_BRICK_STAIRS, MBBlocks.SANDSTONE_BRICK_WALL),
                MBBlocks.CRACKED_SANDSTONE_BRICKS, List.of(MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB, MBBlocks.CRACKED_SANDSTONE_BRICK_STAIRS, MBBlocks.CRACKED_SANDSTONE_BRICK_WALL),
                MBBlocks.SANDSTONE_TILES, List.of(MBBlocks.SANDSTONE_TILE_SLAB, MBBlocks.SANDSTONE_TILE_STAIRS, MBBlocks.SANDSTONE_TILE_WALL),
                MBBlocks.CRACKED_SANDSTONE_TILES, List.of(MBBlocks.CRACKED_SANDSTONE_TILE_SLAB, MBBlocks.CRACKED_SANDSTONE_TILE_STAIRS, MBBlocks.CRACKED_SANDSTONE_TILE_WALL),
                MBBlocks.RED_SANDSTONE_BRICKS, List.of(MBBlocks.RED_SANDSTONE_BRICK_SLAB, MBBlocks.RED_SANDSTONE_BRICK_STAIRS, MBBlocks.RED_SANDSTONE_BRICK_WALL),
                MBBlocks.CRACKED_RED_SANDSTONE_BRICKS, List.of(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB, MBBlocks.CRACKED_RED_SANDSTONE_BRICK_STAIRS, MBBlocks.CRACKED_RED_SANDSTONE_BRICK_WALL),
                MBBlocks.RED_SANDSTONE_TILES, List.of(MBBlocks.RED_SANDSTONE_TILE_SLAB, MBBlocks.RED_SANDSTONE_TILE_STAIRS, MBBlocks.RED_SANDSTONE_TILE_WALL),
                MBBlocks.CRACKED_RED_SANDSTONE_TILES, List.of(MBBlocks.CRACKED_RED_SANDSTONE_TILE_SLAB, MBBlocks.CRACKED_RED_SANDSTONE_TILE_STAIRS, MBBlocks.CRACKED_RED_SANDSTONE_TILE_WALL)
        )); PARTS_SET.putAll(Map.of(
                MBBlocks.POLISHED_PRISMARINE, List.of(MBBlocks.POLISHED_PRISMARINE_SLAB, MBBlocks.POLISHED_PRISMARINE_STAIRS, MBBlocks.POLISHED_PRISMARINE_WALL),
                MBBlocks.PRISMARINE_TILES, List.of(MBBlocks.PRISMARINE_TILE_SLAB, MBBlocks.PRISMARINE_TILE_STAIRS, MBBlocks.PRISMARINE_TILE_WALL)
        ));

        TRANSMUTE.putAll(Map.of(
                List.of(MBBlocks.BUTTERCUP, Items.YELLOW_DYE), 1,
                List.of(MBBlocks.FORGETMENOT, Items.LIGHT_BLUE_DYE), 1,
                List.of(MBBlocks.LIGHT_BLUE_HYACINTH, Items.LIGHT_BLUE_DYE), 2,
                List.of(MBBlocks.PINK_HYACINTH, Items.PINK_DYE), 2,
                List.of(MBBlocks.WHITE_HYACINTH, Items.WHITE_DYE), 2,
                List.of(MBBlocks.RED_HYACINTH, Items.RED_DYE), 2,
                List.of(MBBlocks.SAFFRON_MUSHROOM, Items.ORANGE_DYE), 1
        )); TRANSMUTE.putAll(Map.of(
                List.of(Items.APPLE, MBItems.APPLE_SEEDS), 1,
                List.of(Items.SWEET_BERRIES, MBItems.SWEET_BERRY_PITS), 1,
                List.of(Items.GLOW_BERRIES, MBItems.GLOW_BERRY_PITS), 1
        )); TRANSMUTE.putAll(Map.of(
                List.of(Items.IRON_NUGGET, MBItems.ITEM_HOOK), 1
        ));

        COMPACT.putAll(Map.of(
                MBBlocks.PEBBLES, Blocks.COBBLESTONE,
                MBItems.GRASS_TUFT, MBBlocks.GRASS_TURF,
                Items.CRIMSON_ROOTS, MBBlocks.CRIMSON_NYLIUM_TURF,
                Items.WARPED_ROOTS, MBBlocks.WARPED_NYLIUM_TURF,
                MBBlocks.COBBLED_DIORITE, Blocks.DIORITE,
                MBBlocks.COBBLED_GRANITE, Blocks.GRANITE,
                MBBlocks.COBBLED_DRIPSTONE, Blocks.DRIPSTONE_BLOCK
        )); COMPACT.putAll(Map.of(
                MBItems.GLASS_SHARD, Blocks.GLASS,
                MBItems.WHITE_GLASS_SHARD, Blocks.WHITE_STAINED_GLASS,
                MBItems.LIGHT_GRAY_GLASS_SHARD, Blocks.LIGHT_GRAY_STAINED_GLASS,
                MBItems.GRAY_GLASS_SHARD, Blocks.GRAY_STAINED_GLASS,
                MBItems.BLACK_GLASS_SHARD, Blocks.BLACK_STAINED_GLASS,
                MBItems.LIME_GLASS_SHARD, Blocks.LIME_STAINED_GLASS,
                MBItems.GREEN_GLASS_SHARD, Blocks.GREEN_STAINED_GLASS,
                MBItems.YELLOW_GLASS_SHARD, Blocks.YELLOW_STAINED_GLASS,
                MBItems.ORANGE_GLASS_SHARD, Blocks.ORANGE_STAINED_GLASS,
                MBItems.BROWN_GLASS_SHARD, Blocks.BROWN_STAINED_GLASS
        )); COMPACT.putAll(Map.of(
                MBItems.RED_GLASS_SHARD, Blocks.RED_STAINED_GLASS,
                MBItems.PINK_GLASS_SHARD, Blocks.PINK_STAINED_GLASS,
                MBItems.MAGENTA_GLASS_SHARD, Blocks.MAGENTA_STAINED_GLASS,
                MBItems.PURPLE_GLASS_SHARD, Blocks.PURPLE_STAINED_GLASS,
                MBItems.LIGHT_BLUE_GLASS_SHARD, Blocks.LIGHT_BLUE_STAINED_GLASS,
                MBItems.CYAN_GLASS_SHARD, Blocks.CYAN_STAINED_GLASS,
                MBItems.BLUE_GLASS_SHARD, Blocks.BLUE_STAINED_GLASS
        ));


        POLISH.putAll(Map.of(
                MBItems.PEAT, MBBlocks.PEAT_BRICKS
        ));

        STORAGE.putAll(Map.of(
                Items.APPLE, MBBlocks.APPLE_CRATE,
                Items.CARROT, MBBlocks.CARROT_CRATE,
                Items.POTATO, MBBlocks.POTATO_CRATE,
                Items.BEETROOT, MBBlocks.BEETROOT_CRATE,
                Items.EGG, MBBlocks.EGG_BASKET,
                Items.COCOA_BEANS, MBBlocks.COCOA_SACK,
                Items.SWEET_BERRIES, MBBlocks.SWEET_BERRY_BASKET,
                Items.GLOW_BERRIES, MBBlocks.GLOW_BERRY_BASKET
        )); STORAGE.putAll(Map.of(
                Items.SUGAR_CANE, MBBlocks.SUGAR_CANE_BUNDLE,
                Items.BAMBOO, MBBlocks.BAMBOO_BUNDLE,
                Items.KELP, MBBlocks.KELP_BLOCK,
                Items.GLISTERING_MELON_SLICE, MBBlocks.GLISTERING_MELON_BLOCK,
                Items.NETHER_WART, MBBlocks.NETHER_WART_BUNDLE,
                Items.SCUTE, MBBlocks.SCUTE_BLOCK
        )); STORAGE.putAll(Map.of(
                Items.STRING, MBBlocks.SPOOL,
                Items.PAPER, MBBlocks.PAPER_BUNDLE,
                Items.STICK, MBBlocks.STICK_STACK,
                Items.CHARCOAL, MBBlocks.CHARCOAL_LOG
        )); STORAGE.putAll(Map.of(
                Items.ROTTEN_FLESH, MBBlocks.ROTTEN_FLESH_BLOCK,
                Items.BONE, MBBlocks.BONE_BUNDLE,
                Items.SPIDER_EYE, MBBlocks.SPIDER_EYE_BLOCK,
                Items.PHANTOM_MEMBRANE, MBBlocks.PHANTOM_MEMBRANE_BLOCK,
                Items.BLAZE_ROD, MBBlocks.BLAZE_ROD_BUNDLE,
                Items.ENDER_PEARL, MBBlocks.ENDER_PEARL_BLOCK
        )); STORAGE.putAll(Map.of(
                MBItems.PEAT, MBBlocks.PEAT_BLOCK,
                MBItems.COPPER_NUGGET, Items.COPPER_INGOT
        ));

        FIRING.put(MBBlocks.COBBLED_ANDESITE, Blocks.ANDESITE);
        FIRING.put(MBBlocks.COBBLED_DIORITE, Blocks.DIORITE);
        FIRING.put(MBBlocks.COBBLED_GRANITE, Blocks.GRANITE);

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
                Blocks.STONE, List.of(Blocks.COBBLESTONE, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_WALL),
                Blocks.DEEPSLATE, List.of(Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_WALL)
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

        FIRING.forEach((in, out) -> firing(exporter, in, out, 0.1f, DEFAULT_FIRE_TIME));
        FIRING.forEach((in, out) -> smelting(exporter, in, out, 0.1f, DEFAULT_SMELT_TIME));

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

        COMPACT.forEach((in, out) -> condense(exporter, in, out));
        POLISH.forEach((polished, brick) -> bricksRecipe(exporter, polished, brick));
        STORAGE.forEach((item, storage) -> compact(exporter, item, storage));

        insetRecipe(exporter, MBBlocks.RED_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.RED_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.BROWN_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.BROWN_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.TOADSTOOL_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.TOADSTOOL_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.SAFFRON_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.SAFFRON_MUSH_LAMP);

        ShapedRecipeJsonFactory.create(Items.TORCH, 2)
                .input('P', MBItems.PEAT).input('S', Items.STICK)
                .pattern("P").pattern("S")
                .criterion(RecipesProvider.hasItem(MBItems.PEAT), RecipesProvider.conditionsFromItem(MBItems.PEAT)).offerTo(exporter);

        ShapelessRecipeJsonFactory.create(MBItems.GLOW_ITEM_HOOK)
                .input(MBItems.ITEM_HOOK).input(Items.GLOW_INK_SAC)
                .criterion(RecipesProvider.hasItem(Items.GLOW_INK_SAC), RecipesProvider.conditionsFromItem(Items.GLOW_INK_SAC)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBItems.WRENCH)
                .input('C', Items.COPPER_INGOT).input('S', Items.STICK)
                .pattern(" C ").pattern(" SC").pattern("S  ")
                .criterion(RecipesProvider.hasItem(Items.COPPER_INGOT), RecipesProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.ROPE_LADDER, 3)
                .input('#', Items.STRING).input('S', Items.STICK)
                .pattern("# #").pattern("SSS").pattern("# #")
                .criterion(RecipesProvider.hasItem(Items.STRING), RecipesProvider.conditionsFromItem(Items.STRING)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.IRON_LADDER, 3)
                .input('i', Items.IRON_NUGGET)
                .pattern("i i").pattern("iii").pattern("i i")
                .criterion(RecipesProvider.hasItem(Items.IRON_INGOT), RecipesProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.BEDROLL)
                .input('F', MBItems.FUR).input('L', Items.LEATHER).input('W', ItemTags.WOOL)
                .pattern("FFW").pattern("LLL")
                .criterion(RecipesProvider.hasItem(MBItems.FUR), RecipesProvider.conditionsFromItem(MBItems.FUR)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.LEATHER_SEAT)
                .input('L', Items.LEATHER).input('P', ItemTags.PLANKS)
                .pattern("LLL").pattern("PPP")
                .criterion(RecipesProvider.hasItem(Items.LEATHER), RecipesProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);

        campfire(exporter, Items.SWEET_BERRIES, MBItems.ROASTED_BERRIES, 0.1f,50);

        ShapedRecipeJsonFactory.create(MBItems.GLOW_BERRY_TART, 8)
                .input('G', Items.GLOW_BERRIES).input('E', Items.EGG).input('W', Items.WHEAT).input('S', Items.SUGAR)
                .pattern("GEG").pattern("WSW")
                .criterion(RecipesProvider.hasItem(Items.GLOW_BERRIES), RecipesProvider.conditionsFromItem(Items.GLOW_BERRIES)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBItems.SALAD)
                .input('L', MBItems.LETTUCE_LEAF).input('C', Items.CARROT).input('B', Items.BEETROOT).input('U', Items.BOWL)
                .pattern("LLL").pattern("CLB").pattern(" U ")
                .criterion(RecipesProvider.hasItem(MBItems.LETTUCE_LEAF), RecipesProvider.conditionsFromItem(MBItems.LETTUCE_LEAF)).offerTo(exporter);

        ShapelessRecipeJsonFactory.create(Items.NAME_TAG)
                .input(Items.PAPER).input(Items.STRING).input(Items.INK_SAC)
                .criterion("tame_animal", TameAnimalCriterion.Conditions.any()).offerTo(exporter);

        campfire(exporter, Items.STICK, Items.TORCH, 0.1f, 600);

        ShapedRecipeJsonFactory.create(Blocks.PODZOL, 4).input('D', Blocks.DIRT).input('G', Blocks.SAND)
                .pattern("DG").pattern("GD")
                .criterion("has_sand", RecipesProvider.conditionsFromItem(Blocks.SAND)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(MBBlocks.LEAFBED, 4).input('D', Blocks.DIRT).input('G', ItemTags.LEAVES)
                .pattern("DG").pattern("GD")
                .criterion("has_leaves", RecipesProvider.conditionsFromItem(Items.SHEARS)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(Items.SADDLE).input('#', Items.LEATHER).input('S', Items.STRING).input('I', Items.IRON_INGOT)
                .pattern("###").pattern("#S#").pattern(" I ")
                .criterion("has_leather", RecipesProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);

        ShapedRecipeJsonFactory.create(Items.LEATHER_HORSE_ARMOR).input('#', Items.LEATHER).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_leather", RecipesProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.IRON_HORSE_ARMOR).input('#', Items.IRON_INGOT).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_iron", RecipesProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.GOLDEN_HORSE_ARMOR).input('#', Items.GOLD_INGOT).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_gold", RecipesProvider.conditionsFromItem(Items.GOLD_INGOT)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(Items.DIAMOND_HORSE_ARMOR).input('#', Items.DIAMOND).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_diamond", RecipesProvider.conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

    }

    public static void generateFamily(Consumer<RecipeJsonProvider> exporter, MBBlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonFactory> biFunction = VARIANT_FACTORIES.get(variant);
            Block inputItem = getVariantRecipeInput(family, variant);
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) || Objects.equals(Registry.BLOCK.getId(inputItem).getNamespace(), Moonbits.MOD_ID)) { // should hopefully only make the recipe if its a new block..?
                if (biFunction != null) {
                    CraftingRecipeJsonFactory factory = biFunction.apply(block, inputItem);
                    family.getGroup().ifPresent(group -> factory.group(group + (variant == MBBlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                    factory.criterion(family.getUnlockCriterionName().orElseGet(() -> RecipesProvider.hasItem(inputItem)), RecipesProvider.conditionsFromItem(inputItem));
                    factory.offerTo(exporter);
                    offerStonecuttingRecipe(exporter, family.getVariant(variant), inputItem);
                }
                else if (variant == MBBlockFamily.Variant.CRACKED) {
                    RecipesProvider.offerCrackingRecipe(exporter, block, inputItem);
                    offerStonecuttingRecipe(exporter, block, inputItem);
                }
                else if (variant == MBBlockFamily.Variant.PILLAR || variant == MBBlockFamily.Variant.COLUMN) {
                    pillar(exporter, inputItem, block);
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
                else if (variant == MBBlockFamily.Variant.CARPET) {
                    carpetRecipe(exporter, inputItem, block);
                }
                else if (variant == MBBlockFamily.Variant.POLISHED) {
                    condense(exporter, inputItem, block);
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
//                else {
//                    offerStonecuttingRecipe(exporter, block, inputItem);
//                }
            }
        });
        family.cuttable.forEach(block -> {
            Block inputItem = family.getBaseBlock();
                offerStonecuttingRecipe(exporter, block, inputItem);
        });
        family.childBlocks.forEach(block -> {
            Block inputItem = family.getBaseBlock();
            offerStonecuttingRecipe(exporter, block, inputItem);
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

    // 1:1, 2x2 & 3x3 recipes
    public static void transmute(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, int count) {
        ShapelessRecipeJsonFactory.create(output, count).input(input)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipesProvider.convertBetween(output, input));
    }
    public static void condense(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipesProvider.convertBetween(output, input));
    }
    public static void compact(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible compacted) {
        ShapelessRecipeJsonFactory.create(input, 9).input(compacted)
                .criterion(RecipesProvider.hasItem(compacted), RecipesProvider.conditionsFromItem(compacted)).offerTo(exporter, RecipesProvider.convertBetween(compacted, input));
        ShapedRecipeJsonFactory.create(compacted).input('#', input).pattern("###").pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
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
                .offerTo(exporter, "smelting_" + RecipesProvider.getItemPath(output));
    }
    public static void blasting(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.BLASTING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, "blasting_" + RecipesProvider.getItemPath(output));
    }
    public static void smoking(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.SMOKING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, "smoking_" + RecipesProvider.getItemPath(output));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, (CookingRecipeSerializer<?>) MBData.KILN_RECIPE_SERIALIZER)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, "firing_" + RecipesProvider.getItemPath(output));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, Tag<Item> input, ItemConvertible output, float experience, int cookingTime, String criterion) {
        CookingRecipeJsonFactory.create(Ingredient.fromTag(input), output, experience, cookingTime, (CookingRecipeSerializer<?>) MBData.KILN_RECIPE_SERIALIZER)
                .criterion(criterion, RecipesProvider.conditionsFromTag(input))
                .offerTo(exporter, "firing_" + RecipesProvider.getItemPath(output));
    }
    public static void campfire(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, "campfire_" + RecipesProvider.getItemPath(output));
    }

    public static void planksRecipe(Consumer<RecipeJsonProvider> exporter, Tag<Item> input, ItemConvertible output) {
        ShapelessRecipeJsonFactory.create(output, 4).input(input).group("planks")
                .criterion("has_logs", RecipesProvider.conditionsFromTag(input))
                .offerTo(exporter);
        RecipesProvider.offerStonecuttingRecipe(exporter, output, (ItemConvertible) input);
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
