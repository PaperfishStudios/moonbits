package net.paperfish.moonbits;

import com.github.aws404.booking_it.BookingIt;
import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.mixin.content.registry.AxeItemAccessor;
import net.fabricmc.fabric.mixin.object.builder.ModelPredicateProviderRegistrySpecificAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.HoeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.advancement.ItemWashedCriterion;
import net.paperfish.moonbits.block.BarrelCactusBlock;
import net.paperfish.moonbits.mixin.CriteriaAccessor;
import net.paperfish.moonbits.recipe.KilnRecipe;
import net.paperfish.moonbits.recipe.WashingRecipe;
import net.paperfish.moonbits.screen.KilnScreenHandler;

import java.util.Map;

public class MBData {
    // for initializing anything that isnt big enough for its own class i guess

	public static final Map<Block, Block> STRIPPED_BLOCKS;

	public static final RecipeBookCategory KILN_BOOK_CATEGORY;

	public static final RecipeType<KilnRecipe> KILN_RECIPE_TYPE;
	public static final RecipeSerializer<KilnRecipe> KILN_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;

	public static final RecipeType<WashingRecipe> WASHING_RECIPE_TYPE;
	public static final RecipeSerializer<WashingRecipe> WASHING_RECIPE_SERIALIZER;

	public static final ItemWashedCriterion ITEM_WASHED = CriteriaAccessor.callRegister(new ItemWashedCriterion());

	static {
			STRIPPED_BLOCKS = ImmutableMap.<Block, Block>builder()
					.putAll(AxeItemAccessor.getStrippedBlocks())
					.put(MBBlocks.JUNIPER_LOG, MBBlocks.STRIPPED_JUNIPER_LOG)
					.put(MBBlocks.JUNIPER_WOOD, MBBlocks.STRIPPED_JUNIPER_WOOD)
					.put(MBBlocks.CEDAR_LOG, MBBlocks.STRIPPED_CEDAR_LOG)
					.put(MBBlocks.CEDAR_WOOD, MBBlocks.STRIPPED_CEDAR_WOOD)

					.put(MBBlocks.ASPEN_TRUNK, MBBlocks.STRIPPED_ASPEN_TRUNK)
					//.put(MBBlocks.ASPEN_PALISADE, MBBlocks.STRIPPED_ASPEN_PALISADE)

					.put(MBBlocks.MUSHROOM_STEM, MBBlocks.STRIPPED_MUSHROOM_STEM)
					.put(MBBlocks.MUSHROOM_HYPHAE, MBBlocks.STRIPPED_MUSHROOM_HYPHAE)
				.build();

		KILN_BOOK_CATEGORY = BookingIt.getCategory("FIRING");

		KILN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MOD_ID, "kiln"), new RecipeType<KilnRecipe>() {
				@Override
				public String toString() {return Moonbits.MOD_ID.concat("kiln");}
			});
		KILN_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MOD_ID, "kiln"), new CookingRecipeSerializer<>(KilnRecipe::new, 200));
		KILN_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Moonbits.MOD_ID, "kiln"), KilnScreenHandler::new);

		WASHING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MOD_ID, "washing"), new RecipeType<WashingRecipe>() {
			@Override
			public String toString() {
				return Moonbits.MOD_ID.concat("washing");
			}
		});
		WASHING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MOD_ID, "washing"), new WashingRecipe.WashingSerializer());

//		WASHING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MOD_ID, "washing"), new RecipeType<WashingRecipe>() {
//			@Override
//			public String toString() {return "washing";}
//		});
//		WASHING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MOD_ID, "washing"), new WashingRecipe.WashingSerializer());
	}

	// this one's client side btw
	public static void registerClient() {
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
			BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.ACACIA_LEAVES);
			return provider == null ? -1 : provider.getColor(state, view, pos, tintIndex);},
				MBBlocks.FLOWERING_ACACIA_LEAVES,
				MBBlocks.HANGING_FLOWERING_ACACIA_LEAVES,
				MBBlocks.TALL_FLOWERING_ACACIA_LEAVES
		);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.ACACIA_LEAVES);
			return provider == null ? -1 : provider.getColor(stack, tintIndex);},
				MBBlocks.FLOWERING_ACACIA_LEAVES,
				MBBlocks.HANGING_FLOWERING_ACACIA_LEAVES,
				MBBlocks.TALL_FLOWERING_ACACIA_LEAVES
		);

		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
					BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.SPRUCE_LEAVES);
					return provider == null ? -1 : provider.getColor(state, view, pos, tintIndex);},
				MBBlocks.JUNIPER_LEAVES,
				MBBlocks.CEDAR_LEAVES
		);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
					ItemColorProvider provider = ColorProviderRegistry.ITEM.get(Blocks.SPRUCE_LEAVES);
					return provider == null ? -1 : provider.getColor(stack, tintIndex);},
				MBBlocks.JUNIPER_LEAVES,
				MBBlocks.CEDAR_LEAVES
		);

		// item model predicates uwu
		ModelPredicateProviderRegistrySpecificAccessor.callRegister(MBBlocks.BARREL_CACTUS.asItem(), new Identifier("water_level"), (stack, world, entity, seed) -> {
			NbtCompound nbtCompound = stack.getSubNbt("BlockStateTag");
			try {
				NbtElement nbtElement;
				if (nbtCompound != null && (nbtElement = nbtCompound.get(BarrelCactusBlock.LEVEL.getName())) != null) {
					return switch (Integer.parseInt(nbtElement.asString())) {
						default -> 0;
						case 2 -> 1.0f;
						case 3 -> 2.0f;
						case 4 -> 3.0f;
					};
				}
			}
			catch (NumberFormatException numberFormatException) {
				// empty catch block
			}
			return 1.0f;
		});
	}

    public static void registerData() {
		// flammable blocks
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

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_JUNIPER_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_JUNIPER_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNIPER_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_CEDAR_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_CEDAR_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.GOLDEN_BIRCH_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SWEET_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.GLOW_BERRY_HEDGE, 30, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BUTTERCUP, 60, 100);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PAPER_BUNDLE, 60, 60);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STICK_STACK, 30, 60);

		// fuel
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
		FuelRegistry.INSTANCE.add(MBBlocks.JUNIPER_BOOKSHELF, 300);
		FuelRegistry.INSTANCE.add(MBBlocks.CEDAR_BOOKSHELF, 300);

		FuelRegistry.INSTANCE.add(MBBlocks.BAMBOO_BUNDLE, 500);
		FuelRegistry.INSTANCE.add(MBBlocks.STICK_STACK, 1000);
		FuelRegistry.INSTANCE.add(MBBlocks.CHARCOAL_LOG, 16000);
		FuelRegistry.INSTANCE.add(MBBlocks.BLAZE_ROD_BUNDLE, 24000);

		// register strippable blocks
		AxeItemAccessor.setStrippedBlocks(STRIPPED_BLOCKS);

		TillableBlockRegistry.register(MBBlocks.LEAFBED, HoeItem::canTillFarmland, Blocks.FARMLAND.getDefaultState());
		TillableBlockRegistry.register(Blocks.DIRT_PATH, (a) -> true, Blocks.DIRT.getDefaultState());

		FlattenableBlockRegistry.register(Blocks.FARMLAND, Blocks.DIRT.getDefaultState());


	}
}
