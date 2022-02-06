package net.paperfish.moonbits;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.mixin.content.registry.AxeItemAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.recipe.KilnRecipe;
import net.paperfish.moonbits.screen.KilnScreenHandler;

import java.util.Map;

public class MBData {
    // for initializing anything that isnt big enough for its own class i guess

	public static final Map<Block, Block> STRIPPED_BLOCKS;

	// used to remove 1 seed from the drop table of crops when harvested by right-click
	public static final Tag<Item> SEEDS_ROOTS = TagFactory.ITEM.create(new Identifier("moonbits", "seeds_roots"));
	// items that will attract glares
	public static final Tag<Item> GLARE_LIKES = TagFactory.ITEM.create(new Identifier("moonbits", "glare_likes"));

	public static final RecipeType<KilnRecipe> KILN_RECIPE_TYPE;
	public static final RecipeSerializer<KilnRecipe> KILN_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;

	static {
			STRIPPED_BLOCKS = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItemAccessor.getStrippedBlocks())
				.put(MBBlocks.JACARANDA_LOG, MBBlocks.STRIPPED_JACARANDA_LOG)
				.put(MBBlocks.JACARANDA_WOOD, MBBlocks.STRIPPED_JACARANDA_WOOD)
				.build();

			KILN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MOD_ID, "kiln"), new RecipeType<KilnRecipe>() {
				@Override
				public String toString() {return "kiln";}
			});
		KILN_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MOD_ID, "kiln"), new CookingRecipeSerializer<>(KilnRecipe::new, 200));
		KILN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Moonbits.MOD_ID, "kiln"), KilnScreenHandler::new);
	}


	// this one's client side btw
	public static void registerBlockColours() {
		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
			return provider == null ? -1 : provider.getColor(state, view, pos, tintIndex);},
				MBBlocks.TOUGH_GRASS,
				MBBlocks.GRASS_TURF,
				MBBlocks.GRASS_TURF_STAIRS,
				MBBlocks.GRASS_TURF_SLAB,
				MBBlocks.GRASS_CARPET
		);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.GRASS);
			return provider == null ? -1 : provider.getColor(stack, tintIndex);},
				MBBlocks.TOUGH_GRASS,
				MBBlocks.GRASS_TURF,
				MBBlocks.GRASS_TURF_STAIRS,
				MBBlocks.GRASS_TURF_SLAB,
				MBBlocks.GRASS_CARPET
		);

		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.OAK_LEAVES);
			return provider == null ? -1 : provider.getColor(state, view, pos, tintIndex);},
				MBBlocks.BUDDING_OAK_LEAVES,
				MBBlocks.FLOWERING_OAK_LEAVES,
				MBBlocks.FRUITING_OAK_LEAVES,
				MBBlocks.JACARANDA_LEAVES,
				MBBlocks.JACARANDA_LEAF_CARPET,
				MBBlocks.HANGING_JACARANDA_LEAVES,
				MBBlocks.HANGING_JACARANDA_LEAVES_PLANT
		);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.OAK_LEAVES);
			return provider == null ? -1 : provider.getColor(stack, tintIndex);},
				MBBlocks.BUDDING_OAK_LEAVES,
				MBBlocks.FLOWERING_OAK_LEAVES,
				MBBlocks.FRUITING_OAK_LEAVES,
				MBBlocks.JACARANDA_LEAVES,
				MBBlocks.JACARANDA_LEAF_CARPET
		);
	}

    public static void registerFlammable() {
		FlammableBlockRegistry.getDefaultInstance().add(Blocks.COBWEB, 60, 20);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.OAK_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BIRCH_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SPRUCE_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNGLE_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.ACACIA_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.DARK_OAK_PANEL, 5, 20);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BIRCH_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SPRUCE_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNGLE_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.ACACIA_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.DARK_OAK_BOOKSHELF, 30, 20);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JACARANDA_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.GOLDEN_BIRCH_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SWEET_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.GLOW_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BUTTERCUP, 60, 100);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PAPER_BUNDLE, 60, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STICK_STACK, 30, 60);
	}
	public static void registerFuel() {
		FuelRegistry.INSTANCE.add(Blocks.WARPED_ROOTS, 200);
		FuelRegistry.INSTANCE.add(Blocks.CRIMSON_ROOTS, 200);
		FuelRegistry.INSTANCE.add(Blocks.NETHER_SPROUTS, 200);
		FuelRegistry.INSTANCE.add(Blocks.HANGING_ROOTS, 100);

		FuelRegistry.INSTANCE.add(MBBlocks.ROPE_LADDER, 300);
		
		FuelRegistry.INSTANCE.add(MBItems.PEAT, 800);
		FuelRegistry.INSTANCE.add(MBBlocks.PEAT_BLOCK, 8000);
		FuelRegistry.INSTANCE.add(MBBlocks.PEAT_BRICKS, 4000);
		FuelRegistry.INSTANCE.add(MBBlocks.PEAT_BRICK_SLAB, 2000);
		FuelRegistry.INSTANCE.add(MBBlocks.PEAT_BRICK_STAIRS, 4000);
		FuelRegistry.INSTANCE.add(MBBlocks.PEAT_BRICK_WALL, 4000);

		FuelRegistry.INSTANCE.add(MBBlocks.BIRCH_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.SPRUCE_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.JUNGLE_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.ACACIA_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.DARK_OAK_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.JACARANDA_BOOKSHELF, 300);

		FuelRegistry.INSTANCE.add(MBBlocks.BAMBOO_BUNDLE, 500);
		FuelRegistry.INSTANCE.add(MBBlocks.STICK_STACK, 1000);
		FuelRegistry.INSTANCE.add(MBBlocks.CHARCOAL_LOG, 16000);
		FuelRegistry.INSTANCE.add(MBBlocks.BLAZE_ROD_BUNDLE, 24000);
	}

	public static void registerStrippedBlocks() {
		AxeItemAccessor.setStrippedBlocks(STRIPPED_BLOCKS);
	}
}
