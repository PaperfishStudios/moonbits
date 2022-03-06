package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipesProvider;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.server.RecipeProvider;
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
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.*;
import net.paperfish.moonbits.recipe.WashingRecipeJsonFactory;
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

    private static Map<MBBlockFamily.Variant, BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder>> VARIANT_FACTORIES = new HashMap<>();

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
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.BUTTON, (output, input) -> RecipeProvider.createTransmutationRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.CHISELED, (output, input) -> RecipeProvider.createChiseledBlockRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.CUT, (output, input) -> RecipeProvider.createCutCopperRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.DOOR, (output, input) -> RecipeProvider.createDoorRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.FENCE, (output, input) -> RecipeProvider.createFenceRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.FENCE_GATE, (output, input) -> RecipeProvider.createFenceGateRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.SIGN, (output, input) -> RecipeProvider.createSignRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.SLAB, (output, input) -> RecipeProvider.createSlabRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.STAIRS, (output, input) -> RecipeProvider.createStairsRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.PRESSURE_PLATE, (output, input) -> RecipeProvider.createPressurePlateRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.POLISHED, (output, input) -> RecipeProvider.createCondensingRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.TRAPDOOR, (output, input) -> RecipeProvider.createTrapdoorRecipe(output, Ingredient.ofItems(input)));
        VARIANT_FACTORIES.put(MBBlockFamily.Variant.WALL, (output, input) -> RecipeProvider.getWallRecipe(output, Ingredient.ofItems(input)));


        TRANSMUTE.put(List.of(MBBlocks.MARIGOLD, Items.ORANGE_DYE), 1);
        TRANSMUTE.put(List.of(MBBlocks.BUTTERCUP, Items.YELLOW_DYE), 1);
        TRANSMUTE.put(List.of(MBBlocks.FORGETMENOT, Items.LIGHT_BLUE_DYE), 1);

        TRANSMUTE.put(List.of(MBBlocks.LIGHT_BLUE_HYACINTH, Items.LIGHT_BLUE_DYE), 2);
        TRANSMUTE.put(List.of(MBBlocks.PINK_HYACINTH, Items.PINK_DYE), 2);
        TRANSMUTE.put(List.of(MBBlocks.WHITE_HYACINTH, Items.WHITE_DYE), 2);
        TRANSMUTE.put(List.of(MBBlocks.RED_HYACINTH, Items.RED_DYE), 2);

        TRANSMUTE.put(List.of(MBBlocks.SAFFRON_MUSHROOM, Items.ORANGE_DYE), 1);

        TRANSMUTE.put(List.of(Items.SWEET_BERRIES, MBItems.SWEET_BERRY_PITS), 1);
        TRANSMUTE.put(List.of(Items.GLOW_BERRIES, MBItems.GLOW_BERRY_PITS), 1);

        TRANSMUTE.put(List.of(Items.IRON_NUGGET, MBItems.ITEM_HOOK), 1);

        COMPACT.putAll(Map.of(
                MBBlocks.PEBBLES, Blocks.COBBLESTONE,
                MBItems.GRASS_TUFT, MBBlocks.GRASS_TURF,
                Items.CRIMSON_ROOTS, MBBlocks.CRIMSON_NYLIUM_TURF,
                Items.WARPED_ROOTS, MBBlocks.WARPED_NYLIUM_TURF,
                MBBlocks.COBBLED_DIORITE, Blocks.DIORITE,
                MBBlocks.COBBLED_GRANITE, Blocks.GRANITE
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


        STORAGE.put(Items.APPLE, MBBlocks.APPLE_CRATE);
        STORAGE.put(Items.CARROT, MBBlocks.CARROT_CRATE);
        STORAGE.put(Items.POTATO, MBBlocks.POTATO_CRATE);
        STORAGE.put(Items.BEETROOT, MBBlocks.BEETROOT_CRATE);

        STORAGE.put(MBItems.PEPPER, MBBlocks.PEPPER_CRATE);

        STORAGE.put(Items.EGG, MBBlocks.EGG_BASKET);
        STORAGE.put(Items.COCOA_BEANS, MBBlocks.COCOA_SACK);
        STORAGE.put(Items.SWEET_BERRIES, MBBlocks.SWEET_BERRY_BASKET);
        STORAGE.put(Items.GLOW_BERRIES, MBBlocks.GLOW_BERRY_BASKET);
        STORAGE.put(Items.SUGAR_CANE, MBBlocks.SUGAR_CANE_BUNDLE);
        STORAGE.put(Items.BAMBOO, MBBlocks.BAMBOO_BUNDLE);
        STORAGE.put(Items.KELP, MBBlocks.KELP_BLOCK);
        STORAGE.put(Items.GLISTERING_MELON_SLICE, MBBlocks.GLISTERING_MELON_BLOCK);
        STORAGE.put(Items.NETHER_WART, MBBlocks.NETHER_WART_BUNDLE);
        STORAGE.put(Items.SCUTE, MBBlocks.SCUTE_BLOCK);
        STORAGE.put(Items.STRING, MBBlocks.SPOOL);
        STORAGE.put(Items.PAPER, MBBlocks.PAPER_BUNDLE);
        STORAGE.put(Items.STICK, MBBlocks.STICK_STACK);
        STORAGE.put(Items.CHARCOAL, MBBlocks.CHARCOAL_LOG);
        STORAGE.put(Items.ROTTEN_FLESH, MBBlocks.ROTTEN_FLESH_BLOCK);
        STORAGE.put(Items.BONE, MBBlocks.BONE_BUNDLE);
        STORAGE.put(Items.SPIDER_EYE, MBBlocks.SPIDER_EYE_BLOCK);
        STORAGE.put(Items.PHANTOM_MEMBRANE, MBBlocks.PHANTOM_MEMBRANE_BLOCK);
        STORAGE.put(Items.BLAZE_ROD, MBBlocks.BLAZE_ROD_BUNDLE);
        STORAGE.put(Items.ENDER_PEARL, MBBlocks.ENDER_PEARL_BLOCK);
        STORAGE.put(MBItems.PEAT, MBBlocks.PEAT_BLOCK);
        STORAGE.put(MBItems.COPPER_NUGGET, Items.COPPER_INGOT);


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
                    RecipeProvider.offerStonecuttingRecipe(exporter, output, in, 2);
                }
                else {
                    RecipeProvider.offerStonecuttingRecipe(exporter, output, in);
                }
            });
        });

        COMPACT.forEach((in, out) -> condense(exporter, in, out));
        POLISH.forEach((polished, brick) -> bricksRecipe(exporter, polished, brick));
        STORAGE.forEach((item, storage) -> compact(exporter, item, storage));

        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.JUNIPER_LOG, MBBlocks.STRIPPED_JUNIPER_LOG);
        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.JUNIPER_WOOD, MBBlocks.STRIPPED_JUNIPER_WOOD);
        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.CEDAR_LOG, MBBlocks.STRIPPED_CEDAR_LOG);
        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.CEDAR_WOOD, MBBlocks.STRIPPED_CEDAR_WOOD);

        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.MUSHROOM_STEM, MBBlocks.STRIPPED_MUSHROOM_STEM);
        RecipeProvider.offerBarkBlockRecipe(exporter, MBBlocks.MUSHROOM_HYPHAE, MBBlocks.STRIPPED_MUSHROOM_HYPHAE);

        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.SANDSTONE_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, MBBlocks.SANDSTONE_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_SANDSTONE_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_BRICKS);
        RecipeProvider.offerStonecuttingRecipe(exporter, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_RED_SANDSTONE_BRICKS);

        firing(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 0.1f, DEFAULT_FIRE_TIME);
        smelting(exporter, MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 0.1f, DEFAULT_SMELT_TIME);
        firing(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 0.1f, DEFAULT_FIRE_TIME);
        smelting(exporter, MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 0.1f, DEFAULT_SMELT_TIME);

        ShapedRecipeJsonBuilder.create(MBBlocks.PAVED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipeProvider.hasItem(MBBlocks.SANDSTONE_BRICKS), RecipeProvider.conditionsFromItem(MBBlocks.SANDSTONE_BRICKS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipeProvider.hasItem(MBBlocks.CRACKED_SANDSTONE_BRICKS), RecipeProvider.conditionsFromItem(MBBlocks.CRACKED_SANDSTONE_BRICKS)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(MBBlocks.PAVED_RED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.RED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipeProvider.hasItem(MBBlocks.RED_SANDSTONE_BRICKS), RecipeProvider.conditionsFromItem(MBBlocks.RED_SANDSTONE_BRICKS)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS, 1)
                .input('#', MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB)
                .pattern("#").pattern("#")
                .criterion(RecipeProvider.hasItem(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS), RecipeProvider.conditionsFromItem(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)).offerTo(exporter);

        crossRecipe(exporter, Items.RED_MUSHROOM, Items.BONE_MEAL, MBItems.RED_MUSHBLEND, 4);
        crossRecipe(exporter, Items.BROWN_MUSHROOM, Items.BONE_MEAL, MBItems.BROWN_MUSHBLEND, 4);
        crossRecipe(exporter, MBBlocks.SAFFRON_MUSHROOM, Items.BONE_MEAL, MBItems.SAFFRON_MUSHBLEND, 4);
        crossRecipe(exporter, MBBlocks.SMALL_TOADSTOOLS, Items.BONE_MEAL, MBItems.TOADSTOOL_MUSHBLEND, 4);
        firing(exporter, MBItems.RED_MUSHBLEND, MBBlocks.RED_MUSH_BLOCK, 0.3f, DEFAULT_FIRE_TIME);
        firing(exporter, MBItems.BROWN_MUSHBLEND, MBBlocks.BROWN_MUSH_BLOCK, 0.3f, DEFAULT_FIRE_TIME);
        firing(exporter, MBItems.SAFFRON_MUSHBLEND, MBBlocks.TOADSTOOL_MUSH_BLOCK, 0.3f, DEFAULT_FIRE_TIME);
        firing(exporter, MBItems.TOADSTOOL_MUSHBLEND, MBBlocks.SAFFRON_MUSH_BLOCK, 0.3f, DEFAULT_FIRE_TIME);
        insetRecipe(exporter, MBBlocks.RED_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.RED_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.BROWN_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.BROWN_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.TOADSTOOL_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.TOADSTOOL_MUSH_LAMP);
        insetRecipe(exporter, MBBlocks.SAFFRON_MUSH_BLOCK, MBBlocks.LAMPROOT, MBBlocks.SAFFRON_MUSH_LAMP);

        ShapedRecipeJsonBuilder.create(Items.TORCH, 2)
                .input('P', MBItems.PEAT).input('S', Items.STICK)
                .pattern("P").pattern("S")
                .criterion(RecipeProvider.hasItem(MBItems.PEAT), RecipeProvider.conditionsFromItem(MBItems.PEAT)).offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(MBItems.GLOW_ITEM_HOOK)
                .input(MBItems.ITEM_HOOK).input(Items.GLOW_INK_SAC)
                .criterion(RecipeProvider.hasItem(Items.GLOW_INK_SAC), RecipeProvider.conditionsFromItem(Items.GLOW_INK_SAC)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBItems.WRENCH)
                .input('C', Items.COPPER_INGOT).input('S', Items.STICK)
                .pattern(" C ").pattern(" SC").pattern("S  ")
                .criterion(RecipeProvider.hasItem(Items.COPPER_INGOT), RecipeProvider.conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.ROPE_LADDER, 3)
                .input('#', Items.STRING).input('S', Items.STICK)
                .pattern("# #").pattern("SSS").pattern("# #")
                .criterion(RecipeProvider.hasItem(Items.STRING), RecipeProvider.conditionsFromItem(Items.STRING)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.IRON_LADDER, 3)
                .input('i', Items.IRON_NUGGET)
                .pattern("i i").pattern("iii").pattern("i i")
                .criterion(RecipeProvider.hasItem(Items.IRON_INGOT), RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.BEDROLL)
                .input('F', MBItems.FUR).input('L', Items.LEATHER).input('W', ItemTags.WOOL)
                .pattern("FFW").pattern("LLL")
                .criterion(RecipeProvider.hasItem(MBItems.FUR), RecipeProvider.conditionsFromItem(MBItems.FUR)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.LEATHER_SEAT)
                .input('L', Items.LEATHER).input('P', ItemTags.PLANKS)
                .pattern("LLL").pattern("PPP")
                .criterion(RecipeProvider.hasItem(Items.LEATHER), RecipeProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);

        campfire(exporter, Items.SWEET_BERRIES, MBItems.ROASTED_BERRIES, 0.1f,50);
        condense(exporter, MBItems.PUMPKIN_SLICE, Items.PUMPKIN);
        condense(exporter, MBItems.LETTUCE_LEAF, MBBlocks.LETTUCE_BLOCK);

        ShapelessRecipeJsonBuilder.create(Items.MUSHROOM_STEW)
                .input(MBItemTags.EDIBLE_MUSHROOMS).input(MBItemTags.EDIBLE_MUSHROOMS).input(Items.BOWL)
                .criterion("has_mushroom", RecipeProvider.conditionsFromTag(MBItemTags.EDIBLE_MUSHROOMS))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(MBItems.LETTUCE_WRAP)
                .input(MBItems.LETTUCE_LEAF).input(MBItems.LETTUCE_LEAF).input(Items.COOKED_CHICKEN)
                .criterion("has_lettuce", RecipeProvider.conditionsFromItem(MBItems.LETTUCE_LEAF))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(MBItems.GLOW_BERRY_TART, 8)
                .input('G', Items.GLOW_BERRIES).input('E', Items.EGG).input('W', Items.WHEAT).input('S', Items.SUGAR)
                .pattern("GEG").pattern("WSW")
                .criterion(RecipeProvider.hasItem(Items.GLOW_BERRIES), RecipeProvider.conditionsFromItem(Items.GLOW_BERRIES)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBItems.SALAD)
                .input('L', MBItems.LETTUCE_LEAF).input('C', Items.CARROT).input('B', Items.BEETROOT).input('U', Items.BOWL)
                .pattern("LLL").pattern("CLB").pattern(" U ")
                .criterion(RecipeProvider.hasItem(MBItems.LETTUCE_LEAF), RecipeProvider.conditionsFromItem(MBItems.LETTUCE_LEAF)).offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(Items.NAME_TAG)
                .input(Items.PAPER).input(Items.STRING).input(Items.INK_SAC)
                .criterion("tame_animal", TameAnimalCriterion.Conditions.any()).offerTo(exporter);

        campfire(exporter, Items.STICK, Items.TORCH, 0.1f, 600);
        campfire(exporter, Items.WET_SPONGE, Items.SPONGE, 0f, 600);

        ShapedRecipeJsonBuilder.create(Blocks.PODZOL, 4).input('D', Blocks.DIRT).input('G', Blocks.SAND)
                .pattern("DG").pattern("GD")
                .criterion("has_sand", RecipeProvider.conditionsFromItem(Blocks.SAND)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(MBBlocks.LEAFBED, 4).input('D', Blocks.DIRT).input('G', ItemTags.LEAVES)
                .pattern("DG").pattern("GD")
                .criterion("has_leaves", RecipeProvider.conditionsFromItem(Items.SHEARS)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(Items.SADDLE).input('#', Items.LEATHER).input('S', Items.STRING).input('I', Items.IRON_INGOT)
                .pattern("###").pattern("#S#").pattern(" I ")
                .criterion("has_leather", RecipeProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);

        ShapedRecipeJsonBuilder.create(Items.LEATHER_HORSE_ARMOR).input('#', Items.LEATHER).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_leather", RecipeProvider.conditionsFromItem(Items.LEATHER)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Items.IRON_HORSE_ARMOR).input('#', Items.IRON_INGOT).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_iron", RecipeProvider.conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Items.GOLDEN_HORSE_ARMOR).input('#', Items.GOLD_INGOT).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_gold", RecipeProvider.conditionsFromItem(Items.GOLD_INGOT)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(Items.DIAMOND_HORSE_ARMOR).input('#', Items.DIAMOND).input('W', ItemTags.WOOL)
                .pattern("  #").pattern("###").pattern("#W#")
                .criterion("has_diamond", RecipeProvider.conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

    }

    public static void generateFamily(Consumer<RecipeJsonProvider> exporter, MBBlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            BiFunction<ItemConvertible, ItemConvertible, CraftingRecipeJsonBuilder> biFunction = VARIANT_FACTORIES.get(variant);
            Block inputItem = getVariantRecipeInput(family, variant);
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) || Objects.equals(Registry.BLOCK.getId(inputItem).getNamespace(), Moonbits.MOD_ID)) { // should hopefully only make the recipe if its a new block..?
                if (biFunction != null) {
                    CraftingRecipeJsonBuilder factory = biFunction.apply(block, inputItem);
                    family.getGroup().ifPresent(group -> factory.group(group + (variant == MBBlockFamily.Variant.CUT ? "" : "_" + variant.getName())));
                    factory.criterion(family.getUnlockCriterionName().orElseGet(() -> RecipeProvider.hasItem(inputItem)), RecipeProvider.conditionsFromItem(inputItem));
                    factory.offerTo(exporter);
                    offerStonecuttingRecipe(exporter, family.getVariant(variant), inputItem);
                }
                else if (variant == MBBlockFamily.Variant.CRACKED) {
                    RecipeProvider.offerCrackingRecipe(exporter, block, inputItem);
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
        ShapelessRecipeJsonBuilder.create(output, count).input(input)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.convertBetween(output, input));
    }
    public static void condense(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.convertBetween(output, input));
    }
    public static void compact(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible compacted) {
        ShapelessRecipeJsonBuilder.create(input, 9).input(compacted)
                .criterion(RecipeProvider.hasItem(compacted), RecipeProvider.conditionsFromItem(compacted)).offerTo(exporter, RecipeProvider.convertBetween(compacted, input));
        ShapedRecipeJsonBuilder.create(compacted).input('#', input).pattern("###").pattern("###").pattern("###")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void crossRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible inputB, ItemConvertible output, int count) {
        ShapedRecipeJsonBuilder.create(output, count).input('#', input).input('B', inputB).pattern("#B").pattern("B#")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void bricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 4).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void slabRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 6).input('#', input).pattern("###")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input, 2);
    }
    public static void stairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 4).input('#', input).pattern("#  ").pattern("## ").pattern("###")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void wallRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 6).input('#', input).pattern("###").pattern("###")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void insetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible base, ItemConvertible insert, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 4)
                .input('#', base).input('i', insert)
                .pattern(" # ").pattern("#i#").pattern(" # ")
                .criterion(RecipeProvider.hasItem(insert), RecipeProvider.conditionsFromItem(insert))
                .offerTo(exporter);

    }

    public static void smelting(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.SMELTING)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "smelting_" + RecipeProvider.getItemPath(output));
    }
    public static void blasting(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.BLASTING)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "blasting_" + RecipeProvider.getItemPath(output));
    }
    public static void smoking(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.SMOKING)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "smoking_" + RecipeProvider.getItemPath(output));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, (CookingRecipeSerializer<?>) MBData.KILN_RECIPE_SERIALIZER)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "firing_" + RecipeProvider.getItemPath(output));
    }
    public static void firing(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible output, float experience, int cookingTime, String criterion) {
        CookingRecipeJsonBuilder.create(Ingredient.fromTag(input), output, experience, cookingTime, (CookingRecipeSerializer<?>) MBData.KILN_RECIPE_SERIALIZER)
                .criterion(criterion, RecipeProvider.conditionsFromTag(input))
                .offerTo(exporter, "firing_" + RecipeProvider.getItemPath(output));
    }
    public static void campfire(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "campfire_" + RecipeProvider.getItemPath(output));
    }
    public static void washing(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, Block cauldron) {
        WashingRecipeJsonFactory.create(Ingredient.ofItems(input.asItem()), output.asItem(), cauldron)
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, "washing_" + RecipeProvider.getItemPath(output));
    }

    public static void planksRecipe(Consumer<RecipeJsonProvider> exporter, TagKey<Item> input, ItemConvertible output) {
        ShapelessRecipeJsonBuilder.create(output, 4).input(input).group("planks")
                .criterion("has_logs", RecipeProvider.conditionsFromTag(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, (ItemConvertible) input.registry());
    }
    public static void barkRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 3).input('#', input).pattern("##").pattern("##").group("bark")
                .criterion("has_log", RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void boatRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output).input('#', input).pattern("# #").pattern("###").group("boat")
                .criterion("in_water", RecipeProvider.requireEnteringFluid(Blocks.WATER))
                .offerTo(exporter);
    }
    public static void bookshelf(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output).input('#', input).input('B', Items.BOOK).pattern("###").pattern("BBB").pattern("###")
                .criterion(RecipeProvider.hasItem(Items.BOOK), RecipeProvider.conditionsFromItem(Items.BOOK)).group("bookshelves")
                .offerTo(exporter);
    }
    public static void planterBox(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output).input('#', input).input('P', MBItems.PEAT).pattern("# #").pattern("#P#").pattern("###")
                .criterion(RecipeProvider.hasItem(MBItems.PEAT), RecipeProvider.conditionsFromItem(MBItems.PEAT)).group("planter_boxes")
                .offerTo(exporter);
    }

    public static void doorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 3).input('#', input).pattern("##").pattern("##").pattern("##")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void trapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 2).input('#', input).pattern("###").pattern("###")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void fenceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 3).input('W', input).input('#', Items.STICK).pattern("W#W").pattern("W#W")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void fenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output).input('#', Items.STICK).input('W', input).pattern("#W#").pattern("#W#")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }
    public static void pressurePlateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output).input('#', input).pattern("##")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void signRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(output, 3).group("sign").input('#', input).input('X', Items.STICK).pattern("###").pattern("###").pattern(" X ")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void carpetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 3).input('#', input).pattern("##").group("carpet")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void pillar(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonBuilder.create(output, 2).input('#', input).pattern("#").pattern("#")
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
        RecipeProvider.offerStonecuttingRecipe(exporter, output, input);
    }

    public static void mossy(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapelessRecipeJsonBuilder.create(output, 1).input(input).input(Blocks.VINE)
                .criterion("has_vines", RecipeProvider.conditionsFromItem(Blocks.VINE))
                .offerTo(exporter);
    }
}
