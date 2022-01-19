package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipesProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.RecipesProvider;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBItems;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MBRecipeProvider extends FabricRecipesProvider {
    public MBRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public static final Map<ItemConvertible, ItemConvertible> SMELTING = Map.of(
            MBBlocks.COBBLED_ANDESITE, Blocks.ANDESITE,
            MBBlocks.COBBLED_DIORITE, Blocks.DIORITE,
            MBBlocks.COBBLED_GRANITE, Blocks.GRANITE,
            MBBlocks.COBBLED_DRIPSTONE, Blocks.DRIPSTONE_BLOCK
    );
    // includes smelting automatically btw
    public static final Map<ItemConvertible, ItemConvertible> BLASTING = Map.of(
            MBBlocks.POLISHED_CALCITE, MBBlocks.CALCITE_BRICKS,
            MBBlocks.POLISHED_DRIPSTONE, MBBlocks.DRIPSTONE_BRICKS
    );
    // includes smelting automatically btw
    public static final Map<ItemConvertible, ItemConvertible> COOKING = Map.of(
            MBBlocks.POLISHED_CALCITE, MBBlocks.CALCITE_BRICKS,
            MBBlocks.POLISHED_DRIPSTONE, MBBlocks.DRIPSTONE_BRICKS
    );

    public static final Map<ItemConvertible, ItemConvertible> POLISH = new ImmutableMap.Builder<ItemConvertible, ItemConvertible>().putAll(Map.of(
            Blocks.HONEYCOMB_BLOCK, MBBlocks.POLISHED_HONEYCOMB,
            MBBlocks.POLISHED_HONEYCOMB, MBBlocks.HONEYCOMB_BRICKS
    )).putAll(Map.of(
            Blocks.STONE_BRICKS, MBBlocks.STONE_TILES,
            Blocks.POLISHED_ANDESITE, MBBlocks.ANDESITE_BRICKS,
            MBBlocks.ANDESITE_BRICKS, MBBlocks.ANDESITE_TILES,
            Blocks.POLISHED_DIORITE, MBBlocks.DIORITE_BRICKS,
            MBBlocks.DIORITE_BRICKS, MBBlocks.DIORITE_TILES,
            Blocks.POLISHED_GRANITE, MBBlocks.GRANITE_BRICKS,
            MBBlocks.GRANITE_BRICKS, MBBlocks.GRANITE_TILES
    )).putAll(Map.of(
            Blocks.SANDSTONE, MBBlocks.SANDSTONE_BRICKS,
            MBBlocks.SANDSTONE_BRICKS, MBBlocks.SANDSTONE_TILES,
            Blocks.RED_SANDSTONE, MBBlocks.RED_SANDSTONE_BRICKS,
            MBBlocks.RED_SANDSTONE_BRICKS, MBBlocks.RED_SANDSTONE_TILES,
            Blocks.PRISMARINE, MBBlocks.POLISHED_PRISMARINE,
            MBBlocks.POLISHED_PRISMARINE, Blocks.PRISMARINE_BRICKS,
            Blocks.PRISMARINE_BRICKS, MBBlocks.PRISMARINE_TILES
    )).putAll(Map.of(
            Blocks.TUFF, MBBlocks.POLISHED_TUFF,
            MBBlocks.POLISHED_TUFF, MBBlocks.TUFF_BRICKS,
            MBBlocks.TUFF_BRICKS, MBBlocks.TUFF_TILES,
            Blocks.CALCITE, MBBlocks.POLISHED_CALCITE,
            MBBlocks.POLISHED_CALCITE, MBBlocks.CALCITE_BRICKS,
            MBBlocks.CALCITE_BRICKS, MBBlocks.CALCITE_TILES,
            Blocks.DRIPSTONE_BLOCK, MBBlocks.POLISHED_DRIPSTONE,
            MBBlocks.POLISHED_DRIPSTONE, MBBlocks.DRIPSTONE_BRICKS,
            MBBlocks.DRIPSTONE_BRICKS, MBBlocks.DRIPSTONE_TILES
    )).putAll(Map.of(
            Blocks.SMOOTH_BASALT, MBBlocks.BASALT_BRICKS,
            MBBlocks.BASALT_BRICKS, MBBlocks.BASALT_TILES
    )).build();

    public static final Map<Tag.Identified<Block>, List<ItemConvertible>> VANILLA_WOODS = new ImmutableMap.Builder<Tag.Identified<Block>, List<ItemConvertible>>().putAll(Map.of(
            BlockTags.OAK_LOGS, List.of(Blocks.OAK_PLANKS,
                    MBBlocks.OAK_BOARDS, MBBlocks.OAK_PANEL, Blocks.BOOKSHELF, MBBlocks.CARVED_OAK, MBBlocks.OAK_PILLAR, MBBlocks.OAK_PLANTER_BOX),
            BlockTags.SPRUCE_LOGS, List.of(Blocks.SPRUCE_PLANKS,
                    MBBlocks.SPRUCE_BOARDS, MBBlocks.SPRUCE_PANEL, MBBlocks.SPRUCE_BOOKSHELF, MBBlocks.CARVED_SPRUCE, MBBlocks.SPRUCE_PILLAR, MBBlocks.SPRUCE_PLANTER_BOX),
            BlockTags.BIRCH_LOGS, List.of(Blocks.BIRCH_PLANKS,
                    MBBlocks.BIRCH_BOARDS, MBBlocks.BIRCH_PANEL, MBBlocks.BIRCH_BOOKSHELF, MBBlocks.CARVED_BIRCH, MBBlocks.BIRCH_PILLAR, MBBlocks.BIRCH_PLANTER_BOX)
    )).putAll(Map.of(
    )).build();
    public static final Map<ItemConvertible, List<ItemConvertible>> WOOD_SET = new ImmutableMap.Builder<ItemConvertible, List<ItemConvertible>>().putAll(Map.of(
            MBBlocks.TOUGH_DIRT, List.of(MBBlocks.TOUGH_DIRT_SLAB, MBBlocks.TOUGH_DIRT_STAIRS),
            MBBlocks.DIRT_BRICKS, List.of(MBBlocks.DIRT_BRICK_SLAB, MBBlocks.DIRT_BRICK_STAIRS),
            MBBlocks.PEAT_BRICKS, List.of(MBBlocks.PEAT_BRICK_SLAB, MBBlocks.PEAT_BRICK_STAIRS, MBBlocks.PEAT_BRICK_WALL)
    )).putAll(Map.of(
    )).build();

    public static final Map<ItemConvertible, List<ItemConvertible>> PARTS_SET = new ImmutableMap.Builder<ItemConvertible, List<ItemConvertible>>().putAll(Map.of(
            MBBlocks.TOUGH_DIRT, List.of(MBBlocks.TOUGH_DIRT_SLAB, MBBlocks.TOUGH_DIRT_STAIRS),
            MBBlocks.DIRT_BRICKS, List.of(MBBlocks.DIRT_BRICK_SLAB, MBBlocks.DIRT_BRICK_STAIRS),
            MBBlocks.PEAT_BRICKS, List.of(MBBlocks.PEAT_BRICK_SLAB, MBBlocks.PEAT_BRICK_STAIRS, MBBlocks.PEAT_BRICK_WALL)
    )).putAll(Map.of(
            Blocks.HONEYCOMB_BLOCK, List.of(MBBlocks.HONEYCOMB_SLAB, MBBlocks.HONEYCOMB_STAIRS, MBBlocks.HONEYCOMB_WALL),
            MBBlocks.HONEYCOMB_BRICKS, List.of(MBBlocks.HONEYCOMB_BRICK_SLAB, MBBlocks.HONEYCOMB_BRICK_STAIRS, MBBlocks.HONEYCOMB_BRICK_WALL),
            MBBlocks.HONEYCOMB_TILES, List.of(MBBlocks.HONEYCOMB_TILE_SLAB, MBBlocks.HONEYCOMB_TILE_STAIRS, MBBlocks.HONEYCOMB_TILE_WALL)
    )).putAll(Map.of(
            MBBlocks.STONE_TILES, List.of(MBBlocks.STONE_TILE_SLAB, MBBlocks.STONE_TILE_STAIRS, MBBlocks.STONE_TILE_WALL),
            MBBlocks.CRACKED_STONE_TILES, List.of(MBBlocks.CRACKED_STONE_TILE_SLAB, MBBlocks.CRACKED_STONE_TILE_STAIRS, MBBlocks.CRACKED_STONE_TILE_WALL),
            MBBlocks.MOSSY_STONE_TILES, List.of(MBBlocks.MOSSY_STONE_TILE_SLAB, MBBlocks.MOSSY_STONE_TILE_STAIRS, MBBlocks.MOSSY_STONE_TILE_WALL),
            MBBlocks.ANDESITE_BRICKS, List.of(MBBlocks.ANDESITE_BRICK_SLAB, MBBlocks.ANDESITE_BRICK_STAIRS, MBBlocks.ANDESITE_BRICK_WALL),
            MBBlocks.CRACKED_ANDESITE_BRICKS, List.of(MBBlocks.CRACKED_ANDESITE_BRICK_SLAB, MBBlocks.CRACKED_ANDESITE_BRICK_STAIRS, MBBlocks.CRACKED_ANDESITE_BRICK_WALL),
            MBBlocks.MOSSY_ANDESITE_BRICKS, List.of(MBBlocks.MOSSY_ANDESITE_BRICK_SLAB, MBBlocks.MOSSY_ANDESITE_BRICK_STAIRS, MBBlocks.MOSSY_ANDESITE_BRICK_WALL),
            MBBlocks.ANDESITE_TILES, List.of(MBBlocks.ANDESITE_TILE_SLAB, MBBlocks.ANDESITE_TILE_STAIRS, MBBlocks.ANDESITE_TILE_WALL),
            MBBlocks.CRACKED_ANDESITE_TILES, List.of(MBBlocks.CRACKED_ANDESITE_TILE_SLAB, MBBlocks.CRACKED_ANDESITE_TILE_STAIRS, MBBlocks.CRACKED_ANDESITE_TILE_WALL),
            MBBlocks.MOSSY_ANDESITE_TILES, List.of(MBBlocks.MOSSY_ANDESITE_TILE_SLAB, MBBlocks.MOSSY_ANDESITE_TILE_STAIRS, MBBlocks.MOSSY_ANDESITE_TILE_WALL)
    )).putAll(Map.of(
            MBBlocks.DIORITE_BRICKS, List.of(MBBlocks.DIORITE_BRICK_SLAB, MBBlocks.DIORITE_BRICK_STAIRS, MBBlocks.DIORITE_BRICK_WALL),
            MBBlocks.CRACKED_DIORITE_BRICKS, List.of(MBBlocks.CRACKED_DIORITE_BRICK_SLAB, MBBlocks.CRACKED_DIORITE_BRICK_STAIRS, MBBlocks.CRACKED_DIORITE_BRICK_WALL),
            MBBlocks.MOSSY_DIORITE_BRICKS, List.of(MBBlocks.MOSSY_DIORITE_BRICK_SLAB, MBBlocks.MOSSY_DIORITE_BRICK_STAIRS, MBBlocks.MOSSY_DIORITE_BRICK_WALL),
            MBBlocks.DIORITE_TILES, List.of(MBBlocks.DIORITE_TILE_SLAB, MBBlocks.DIORITE_TILE_STAIRS, MBBlocks.DIORITE_TILE_WALL),
            MBBlocks.CRACKED_DIORITE_TILES, List.of(MBBlocks.CRACKED_DIORITE_TILE_SLAB, MBBlocks.CRACKED_DIORITE_TILE_STAIRS, MBBlocks.CRACKED_DIORITE_TILE_WALL),
            MBBlocks.MOSSY_DIORITE_TILES, List.of(MBBlocks.MOSSY_DIORITE_TILE_SLAB, MBBlocks.MOSSY_DIORITE_TILE_STAIRS, MBBlocks.MOSSY_DIORITE_TILE_WALL)
    )).putAll(Map.of(
            MBBlocks.GRANITE_BRICKS, List.of(MBBlocks.GRANITE_BRICK_SLAB, MBBlocks.GRANITE_BRICK_STAIRS, MBBlocks.GRANITE_BRICK_WALL),
            MBBlocks.CRACKED_GRANITE_BRICKS, List.of(MBBlocks.CRACKED_GRANITE_BRICK_SLAB, MBBlocks.CRACKED_GRANITE_BRICK_STAIRS, MBBlocks.CRACKED_GRANITE_BRICK_WALL),
            MBBlocks.MOSSY_GRANITE_BRICKS, List.of(MBBlocks.MOSSY_GRANITE_BRICK_SLAB, MBBlocks.MOSSY_GRANITE_BRICK_STAIRS, MBBlocks.MOSSY_GRANITE_BRICK_WALL),
            MBBlocks.GRANITE_TILES, List.of(MBBlocks.GRANITE_TILE_SLAB, MBBlocks.GRANITE_TILE_STAIRS, MBBlocks.GRANITE_TILE_WALL),
            MBBlocks.CRACKED_GRANITE_TILES, List.of(MBBlocks.CRACKED_GRANITE_TILE_SLAB, MBBlocks.CRACKED_GRANITE_TILE_STAIRS, MBBlocks.CRACKED_GRANITE_TILE_WALL),
            MBBlocks.MOSSY_GRANITE_TILES, List.of(MBBlocks.MOSSY_GRANITE_TILE_SLAB, MBBlocks.MOSSY_GRANITE_TILE_STAIRS, MBBlocks.MOSSY_GRANITE_TILE_WALL)
    )).putAll(Map.of(
            MBBlocks.SANDSTONE_BRICKS, List.of(MBBlocks.SANDSTONE_BRICK_SLAB, MBBlocks.SANDSTONE_BRICK_STAIRS, MBBlocks.SANDSTONE_BRICK_WALL),
            MBBlocks.CRACKED_SANDSTONE_BRICKS, List.of(MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB, MBBlocks.CRACKED_SANDSTONE_BRICK_STAIRS, MBBlocks.CRACKED_SANDSTONE_BRICK_WALL),
            MBBlocks.SANDSTONE_TILES, List.of(MBBlocks.SANDSTONE_TILE_SLAB, MBBlocks.SANDSTONE_TILE_STAIRS, MBBlocks.SANDSTONE_TILE_WALL),
            MBBlocks.CRACKED_SANDSTONE_TILES, List.of(MBBlocks.CRACKED_SANDSTONE_TILE_SLAB, MBBlocks.CRACKED_SANDSTONE_TILE_STAIRS, MBBlocks.CRACKED_SANDSTONE_TILE_WALL),
            MBBlocks.RED_SANDSTONE_BRICKS, List.of(MBBlocks.RED_SANDSTONE_BRICK_SLAB, MBBlocks.RED_SANDSTONE_BRICK_STAIRS, MBBlocks.RED_SANDSTONE_BRICK_WALL),
            MBBlocks.CRACKED_RED_SANDSTONE_BRICKS, List.of(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB, MBBlocks.CRACKED_RED_SANDSTONE_BRICK_STAIRS, MBBlocks.CRACKED_RED_SANDSTONE_BRICK_WALL),
            MBBlocks.RED_SANDSTONE_TILES, List.of(MBBlocks.RED_SANDSTONE_TILE_SLAB, MBBlocks.RED_SANDSTONE_TILE_STAIRS, MBBlocks.RED_SANDSTONE_TILE_WALL),
            MBBlocks.CRACKED_RED_SANDSTONE_TILES, List.of(MBBlocks.CRACKED_RED_SANDSTONE_TILE_SLAB, MBBlocks.CRACKED_RED_SANDSTONE_TILE_STAIRS, MBBlocks.CRACKED_RED_SANDSTONE_TILE_WALL)
    )).putAll(Map.of(
            MBBlocks.POLISHED_PRISMARINE, List.of(MBBlocks.POLISHED_PRISMARINE_SLAB, MBBlocks.POLISHED_PRISMARINE_STAIRS, MBBlocks.POLISHED_PRISMARINE_WALL),
            MBBlocks.PRISMARINE_TILES, List.of(MBBlocks.PRISMARINE_TILE_SLAB, MBBlocks.PRISMARINE_TILE_STAIRS, MBBlocks.PRISMARINE_TILE_WALL)
    )).build();

    // item, storage block
    public static final Map<ItemConvertible, ItemConvertible> STORAGE = new ImmutableMap.Builder<ItemConvertible, ItemConvertible>().putAll(Map.of(
            Items.APPLE, MBBlocks.APPLE_CRATE,
            Items.CARROT, MBBlocks.CARROT_CRATE,
            Items.POTATO, MBBlocks.POTATO_CRATE,
            Items.BEETROOT, MBBlocks.BEETROOT_CRATE,
            Items.EGG, MBBlocks.EGG_BASKET,
            Items.COCOA_BEANS, MBBlocks.COCOA_SACK,
            Items.SWEET_BERRIES, MBBlocks.SWEET_BERRY_BASKET,
            Items.GLOW_BERRIES, MBBlocks.GLOW_BERRY_BASKET
    )).putAll(Map.of(
            Items.SUGAR_CANE, MBBlocks.SUGAR_CANE_BUNDLE,
            Items.BAMBOO, MBBlocks.BAMBOO_BUNDLE,
            Items.KELP, MBBlocks.KELP_BLOCK,
            Items.GLISTERING_MELON_SLICE, MBBlocks.GLISTERING_MELON_BLOCK,
            Items.NETHER_WART, MBBlocks.NETHER_WART_BUNDLE,
            Items.SCUTE, MBBlocks.SCUTE_BLOCK
    )).putAll(Map.of(
            Items.STRING, MBBlocks.SPOOL,
            Items.PAPER, MBBlocks.PAPER_BUNDLE,
            Items.STICK, MBBlocks.STICK_STACK,
            Items.CHARCOAL, MBBlocks.CHARCOAL_LOG
    )).putAll(Map.of(
            Items.ROTTEN_FLESH, MBBlocks.ROTTEN_FLESH_BLOCK,
            Items.BONE, MBBlocks.BONE_BUNDLE,
            Items.SPIDER_EYE, MBBlocks.SPIDER_EYE_BLOCK,
            Items.PHANTOM_MEMBRANE, MBBlocks.PHANTOM_MEMBRANE_BLOCK,
            Items.BLAZE_ROD, MBBlocks.BLAZE_ROD_BUNDLE,
            Items.ENDER_PEARL, MBBlocks.ENDER_PEARL_BLOCK
    )).putAll(Map.of(
            MBItems.PEAT, MBBlocks.PEAT_BLOCK
    )).build();

    public static final int DEFAULT_SMELT_TIME = 200;
    public static final int DEFAULT_BLAST_TIME = DEFAULT_SMELT_TIME / 2;
    public static final int DEFAULT_SMOKE_TIME = DEFAULT_BLAST_TIME;
    public static final int DEFAULT_CAMPFIRE_TIME = DEFAULT_SMELT_TIME * 3;

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        POLISH.forEach((polished, brick) -> bricksRecipe(exporter, polished, brick));

        PARTS_SET.forEach((base, list) -> {
            if (list.size() == 2) {
                slabRecipe(exporter, base, list.get(0));
                stairsRecipe(exporter, base, list.get(1));
            }
            else if (list.size() == 3) {
                slabRecipe(exporter, base, list.get(0));
                stairsRecipe(exporter, base, list.get(1));
                wallRecipe(exporter, base, list.get(2));
            }
        });

        STORAGE.forEach((item, storage) -> compact(exporter, item, storage));

        campfire(exporter, Items.SWEET_BERRIES, MBItems.ROASTED_BERRIES, 0,50);
    }

    // 1:1, 2x2 & 3x3 recipes
    public static void transmute(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapelessRecipeJsonFactory.create(output).input(input)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void condense(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void compact(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible compacted) {
        ShapelessRecipeJsonFactory.create(input, 9).input(compacted)
                .criterion(RecipesProvider.hasItem(compacted), RecipesProvider.conditionsFromItem(compacted)).offerTo(exporter);
        ShapedRecipeJsonFactory.create(compacted).input('#', input).pattern("###").pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void bricksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 4).input('S', input).pattern("SS").pattern("SS")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void slabRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 6).input('#', input).pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void stairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 4).input('#', input).pattern("#  ").pattern("## ").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void wallRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 6).input('#', input).pattern("###").pattern("###")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
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
    public static void campfire(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output, float experience, int cookingTime) {
        CookingRecipeJsonFactory.create(Ingredient.ofItems(input), output, experience, cookingTime, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter, "campfire_" + RecipesProvider.getItemPath(output));
    }

    public static void planksRecipe(Consumer<RecipeJsonProvider> exporter, Tag<Item> input, ItemConvertible output) {
        ShapelessRecipeJsonFactory.create(output, 4).input(input).group("planks")
                .criterion("has_logs", RecipesProvider.conditionsFromTag(input))
                .offerTo(exporter);
    }
    public static void barkRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible input, ItemConvertible output) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").pattern("##").group("bark")
                .criterion("has_log", RecipesProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }
    public static void boatRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output).input('#', input).pattern("# #").pattern("###").group("boat")
                .criterion("in_water", RecipesProvider.requireEnteringFluid(Blocks.WATER))
                .offerTo(exporter);
    }

    public static void doorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, Ingredient input) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").pattern("##").pattern("##")
                .offerTo(exporter);
    }
    public static void trapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output, 2).input('#', input).pattern("###").pattern("###")
                .offerTo(exporter);
    }
    public static void fenceRecpie(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, Ingredient input) {
        ShapedRecipeJsonFactory.create(output, 3).input('W', input).input('#', Items.STICK).pattern("W#W").pattern("W#W")
                .offerTo(exporter);
    }
    public static void fenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, Ingredient input) {
        ShapedRecipeJsonFactory.create(output).input('#', Items.STICK).input('W', input).pattern("#W#").pattern("#W#")
                .offerTo(exporter);
    }
    public static void pressurePlateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output).input('#', input).pattern("##")
                .offerTo(exporter);
    }
    public static void signRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output, 3).group("sign").input('#', input).input('X', Items.STICK).pattern("###").pattern("###").pattern(" X ")
                .offerTo(exporter);
    }
    public static void carpetRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonFactory.create(output, 3).input('#', input).pattern("##").group("carpet")
                .criterion(RecipesProvider.hasItem(input), RecipesProvider.conditionsFromItem(input)).offerTo(exporter);
    }
}
