package net.paperfish.moonbits.registry;

import com.github.aws404.booking_it.BookingIt;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.advancement.ItemWashedCriterion;
import net.paperfish.moonbits.mixin.CriteriaAccessor;
import net.paperfish.moonbits.recipe.CookingPotRecipeSerializer;
import net.paperfish.moonbits.recipe.CookingRecipe;
import net.paperfish.moonbits.recipe.KilnRecipe;
import net.paperfish.moonbits.recipe.WashingRecipe;
import net.paperfish.moonbits.screen.CookingScreenHandler;
import net.paperfish.moonbits.screen.KilnScreenHandler;
import org.quiltmc.qsl.block.content.registry.api.BlockContentRegistries;
import org.quiltmc.qsl.block.content.registry.api.FlammableBlockEntry;

public class MBData {
    // for initializing anything that isnt big enough for its own class i guess

	public static final RecipeBookCategory KILN_BOOK_CATEGORY;

	public static final RecipeType<KilnRecipe> KILN_RECIPE_TYPE;
	public static final CookingRecipeSerializer<KilnRecipe> KILN_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<KilnScreenHandler> KILN_SCREEN_HANDLER;

	public static final RecipeType<CookingRecipe> COOKING_RECIPE_TYPE;
	public static final RecipeSerializer<CookingRecipe> COOKING_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<CookingScreenHandler> COOKING_SCREEN_HANDLER;

	public static final RecipeType<WashingRecipe> WASHING_RECIPE_TYPE;
	public static final RecipeSerializer<WashingRecipe> WASHING_RECIPE_SERIALIZER;

	public static final ItemWashedCriterion ITEM_WASHED = CriteriaAccessor.callRegister(new ItemWashedCriterion());

	// Copper blocks!
	public static final BiMap<Block, Block> WAXING = new ImmutableBiMap.Builder<Block, Block>()
			.put(MBBlocks.COPPER_SHINGLES, MBBlocks.WAXED_COPPER_SHINGLES)
			.put(MBBlocks.EXPOSED_COPPER_SHINGLES, MBBlocks.WAXED_EXPOSED_COPPER_SHINGLES)
			.put(MBBlocks.WEATHERED_COPPER_SHINGLES, MBBlocks.WAXED_WEATHERED_COPPER_SHINGLES)
			.put(MBBlocks.OXIDIZED_COPPER_SHINGLES, MBBlocks.WAXED_OXIDIZED_COPPER_SHINGLES)
			.put(MBBlocks.COPPER_BARS, MBBlocks.WAXED_COPPER_BARS)
			.put(MBBlocks.EXPOSED_COPPER_BARS, MBBlocks.WAXED_EXPOSED_COPPER_BARS)
			.put(MBBlocks.WEATHERED_COPPER_BARS, MBBlocks.WAXED_WEATHERED_COPPER_BARS)
			.put(MBBlocks.OXIDIZED_COPPER_BARS, MBBlocks.WAXED_OXIDIZED_COPPER_BARS)
			.put(MBBlocks.COPPER_LADDER, MBBlocks.WAXED_COPPER_LADDER)
			.put(MBBlocks.EXPOSED_COPPER_LADDER, MBBlocks.WAXED_EXPOSED_COPPER_LADDER)
			.put(MBBlocks.WEATHERED_COPPER_LADDER, MBBlocks.WAXED_WEATHERED_COPPER_LADDER)
			.put(MBBlocks.OXIDIZED_COPPER_LADDER, MBBlocks.WAXED_OXIDIZED_COPPER_LADDER)
			.put(MBBlocks.COPPER_TRAPDOOR, MBBlocks.WAXED_COPPER_TRAPDOOR)
			.put(MBBlocks.EXPOSED_COPPER_TRAPDOOR, MBBlocks.WAXED_EXPOSED_COPPER_TRAPDOOR)
			.put(MBBlocks.WEATHERED_COPPER_TRAPDOOR, MBBlocks.WAXED_WEATHERED_COPPER_TRAPDOOR)
			.put(MBBlocks.OXIDIZED_COPPER_TRAPDOOR, MBBlocks.WAXED_OXIDIZED_COPPER_TRAPDOOR)
			.build();
	public static final BiMap<Block, Block> WAX_OFF = WAXING.inverse();
	public static final BiMap<Block, Block> OXIDIZING = new ImmutableBiMap.Builder<Block, Block>()
			.put(MBBlocks.COPPER_SHINGLES, MBBlocks.EXPOSED_COPPER_SHINGLES)
			.put(MBBlocks.EXPOSED_COPPER_SHINGLES, MBBlocks.WEATHERED_COPPER_SHINGLES)
			.put(MBBlocks.WEATHERED_COPPER_SHINGLES, MBBlocks.OXIDIZED_COPPER_SHINGLES)
			.put(MBBlocks.COPPER_BARS, MBBlocks.EXPOSED_COPPER_BARS)
			.put(MBBlocks.EXPOSED_COPPER_BARS, MBBlocks.WEATHERED_COPPER_BARS)
			.put(MBBlocks.WEATHERED_COPPER_BARS, MBBlocks.OXIDIZED_COPPER_BARS)
			.put(MBBlocks.COPPER_LADDER, MBBlocks.EXPOSED_COPPER_LADDER)
			.put(MBBlocks.EXPOSED_COPPER_LADDER, MBBlocks.WEATHERED_COPPER_LADDER)
			.put(MBBlocks.WEATHERED_COPPER_LADDER, MBBlocks.OXIDIZED_COPPER_LADDER)
			.put(MBBlocks.COPPER_TRAPDOOR, MBBlocks.EXPOSED_COPPER_TRAPDOOR)
			.put(MBBlocks.EXPOSED_COPPER_TRAPDOOR, MBBlocks.WEATHERED_COPPER_TRAPDOOR)
			.put(MBBlocks.WEATHERED_COPPER_TRAPDOOR, MBBlocks.OXIDIZED_COPPER_TRAPDOOR)
			.build();
	public static final BiMap<Block, Block> SCRAPING = OXIDIZING.inverse();

	static {

		KILN_BOOK_CATEGORY = BookingIt.getCategory("FIRING");

		KILN_RECIPE_TYPE = RecipeType.register("moonbits:kiln");
//		KILN_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MODID, "kiln"), new RecipeType<KilnRecipe>() {
//				@Override
//				public String toString() {return Moonbits.MODID.concat("kiln");}
//			});
		KILN_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MODID, "kiln"), new CookingRecipeSerializer<>(KilnRecipe::new, 100));
		KILN_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, (new Identifier(Moonbits.MODID, "kiln")), new ScreenHandlerType<>(KilnScreenHandler::new));
		//KILN_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, (new Identifier(Moonbits.MODID, "kiln")), new ScreenHandlerType<>(KilnScreenHandler::new));

		COOKING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MODID, "cooking"), new RecipeType<CookingRecipe>() {
			@Override
			public String toString() {return Moonbits.MODID.concat("cooking");}
		});
		COOKING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MODID, "cooking"), new CookingPotRecipeSerializer(200));
		COOKING_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, (new Identifier(Moonbits.MODID, "cooking")), new ScreenHandlerType<>(CookingScreenHandler::new));

		WASHING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MODID, "washing"), new RecipeType<WashingRecipe>() {
			@Override
			public String toString() {
				return Moonbits.MODID.concat("washing");
			}
		});
		WASHING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MODID, "washing"), new WashingRecipe.WashingSerializer());

//		DIRT_CAVE_NOISE = createNoise("dirt_caves");
//		registerNoise(z, NoiseParametersKeys.POWDER_SNOW, -6, 1.0, 1.0, 1.0, 1.0);
//		PERMAFROST_NOISE = createNoise("permafrost");
//		SANDY_SOIL_NOISE = createNoise("sandy_soil");
	}

	public static void registerData() {
		OXIDIZING.forEach(OxidizableBlocksRegistry::registerOxidizableBlockPair);
		WAXING.forEach(OxidizableBlocksRegistry::registerWaxableBlockPair);
		// flammable blocks
		BlockContentRegistries.FLAMMABLE_BLOCK.put(Blocks.COBWEB, new FlammableBlockEntry(60, 20));

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.OAK_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BIRCH_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SPRUCE_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNGLE_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.ACACIA_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.DARK_OAK_PANEL, 5, 20);

//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.BIRCH_BOOKSHELF, 30, 20);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SPRUCE_BOOKSHELF, 30, 20);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.JUNGLE_BOOKSHELF, 30, 20);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.ACACIA_BOOKSHELF, 30, 20);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.DARK_OAK_BOOKSHELF, 30, 20);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_LAMPROOT_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_LAMPROOT_WOOD, 5, 5);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_PANEL, 5, 20);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_LEAVES, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_PLANKS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_SLAB, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_STAIRS, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_FENCE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_FENCE_GATE, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_WOOD, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_CEDAR_LOG, 5, 5);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.STRIPPED_CEDAR_WOOD, 5, 5);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_BOOKSHELF, 30, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_PANEL, 5, 20);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.CEDAR_LEAVES, 30, 60);

//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SWEET_BERRY_HEDGE, 30, 60);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE, 30, 60);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.GLOW_BERRY_HEDGE, 30, 60);
//		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE, 30, 60);

		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.LAMPROOT_BULB, 60, 100);
		FlammableBlockRegistry.getDefaultInstance().add(MBBlocks.SOURSOBS, 60, 100);

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

//		FuelRegistry.INSTANCE.add(MBBlocks.BIRCH_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.SPRUCE_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.JUNGLE_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.ACACIA_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.DARK_OAK_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.LAMPROOT_BOOKSHELF, 300);
//		FuelRegistry.INSTANCE.add(MBBlocks.CEDAR_BOOKSHELF, 300);

		FuelRegistry.INSTANCE.add(MBBlocks.BAMBOO_BUNDLE, 500);
		FuelRegistry.INSTANCE.add(MBBlocks.STICK_STACK, 1000);
		FuelRegistry.INSTANCE.add(MBBlocks.CHARCOAL_LOG, 16000);
		FuelRegistry.INSTANCE.add(MBBlocks.BLAZE_ROD_BUNDLE, 24000);

		// register strippable blocks
		StrippableBlockRegistry.register(MBBlocks.CEDAR_LOG, MBBlocks.STRIPPED_CEDAR_LOG);
		StrippableBlockRegistry.register(MBBlocks.CEDAR_WOOD, MBBlocks.STRIPPED_CEDAR_WOOD);
		StrippableBlockRegistry.register(MBBlocks.LAMPROOT_LOG, MBBlocks.STRIPPED_LAMPROOT_LOG);
		StrippableBlockRegistry.register(MBBlocks.LAMPROOT_WOOD, MBBlocks.STRIPPED_LAMPROOT_WOOD);
		StrippableBlockRegistry.register(MBBlocks.MUSHROOM_STEM, MBBlocks.STRIPPED_MUSHROOM_STEM);
		StrippableBlockRegistry.register(MBBlocks.MUSHROOM_HYPHAE, MBBlocks.STRIPPED_MUSHROOM_HYPHAE);

//		TillableBlockRegistry.register(MBBlocks.LEAFBED, HoeItem::canTillFarmland, Blocks.FARMLAND.getDefaultState());
		TillableBlockRegistry.register(Blocks.DIRT_PATH, (a) -> true, Blocks.DIRT.getDefaultState());

		FlattenableBlockRegistry.register(Blocks.FARMLAND, Blocks.DIRT.getDefaultState());


	}

//	public static RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> createNoise(String id) {
//		return RegistryKey.of(Registry.NOISE_KEY, new Identifier(Moonbits.MODID, id));
//	}
//	private static Holder<DoublePerlinNoiseSampler.NoiseParameters> registerNoise(
//			Registry<DoublePerlinNoiseSampler.NoiseParameters> registry,
//			RegistryKey<DoublePerlinNoiseSampler.NoiseParameters> key,
//			int firstOctave, double firstAmplitude, double... amplitudes
//	) {
//		return BuiltinRegistries.register(Registry.NOISE_KEY, key, new DoublePerlinNoiseSampler.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
//	}
}
