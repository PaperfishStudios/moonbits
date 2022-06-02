package net.paperfish.moonbits.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;
import net.paperfish.moonbits.block.cauldron.*;
import net.paperfish.moonbits.block.extended.*;
import net.paperfish.moonbits.mixin.SignTypeAccessor;
import net.paperfish.moonbits.world.feature.*;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

public class MBBlocks {

	// FUNCTIONAL BLOCKS
	public static final Block ROPE_LADDER = new RopeLadderBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(0.4F).sounds(BlockSoundGroup.LADDER).nonOpaque());
	public static final Block IRON_LADDER = new IronLadderBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).sounds(BlockSoundGroup.LADDER).nonOpaque());
	public static final Block WALL_LANTERN = new WallLanternBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
			.luminance(state -> 15).nonOpaque());
	public static final Block WALL_SOUL_LANTERN = new WallLanternBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
			.luminance(state -> 10).nonOpaque());
	public static final Block KILN = new KilnBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_ORANGE).strength(1.2f).sounds(BlockSoundGroup.STONE));
	public static BlockEntityType<KilnBlockEntity> KILN_BLOCK_ENTITY;

	public static final Block BOILING_CAULDRON = new BoilingCauldronBlock(AbstractBlock.Settings.copy(Blocks.WATER_CAULDRON).dropsLike(Blocks.CAULDRON), MBCauldronBehaviour.BOILING_CAULDRON_BEHAVIOR);
	public static BlockEntityType<BoilingCauldronEntity> BOILING_CAULDRON_ENTITY;

	public static final Block COOKING_POT = new CookingPotBlock(AbstractBlock.Settings.of(Material.METAL, MapColor.ORANGE).strength(1.2f).sounds(BlockSoundGroup.COPPER));
	public static BlockEntityType<CookingPotBlockEntity> COOKING_POT_ENTITY;

	public static final Block LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block WHITE_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block LIGHT_GRAY_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block GRAY_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block BLACK_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block GREEN_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block LIME_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block YELLOW_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block ORANGE_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block BROWN_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block RED_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block PINK_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block MAGENTA_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block PURPLE_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block LIGHT_BLUE_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block CYAN_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());
	public static final Block BLUE_LEATHER_SEAT = new SeatBlock(AbstractBlock.Settings.copy(LEATHER_SEAT).strength(1.2f).sounds(BlockSoundGroup.WOOD).nonOpaque());

	// GRASSES
	public static final Block GRASS_TURF = new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS));
	public static final Block GRASS_TURF_STAIRS = new GrassTurfStairsBlock(GRASS_TURF.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.GRASS));
	public static final Block GRASS_TURF_SLAB = new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS));
	public static final Block GRASS_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.1F).sounds(BlockSoundGroup.GRASS));

	public static final Block MYCELIUM_TURF = new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block MYCELIUM_TURF_STAIRS = new GrassTurfStairsBlock(MYCELIUM_TURF.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block MYCELIUM_TURF_SLAB = new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block MYCELIUM_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.1F).sounds(BlockSoundGroup.NYLIUM));

	public static final Block CRIMSON_NYLIUM_TURF = new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block CRIMSON_NYLIUM_TURF_STAIRS = new GrassTurfStairsBlock(CRIMSON_NYLIUM_TURF.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block CRIMSON_NYLIUM_TURF_SLAB = new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block CRIMSON_NYLIUM_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.DULL_RED).strength(0.1F).sounds(BlockSoundGroup.NYLIUM));

	public static final Block WARPED_NYLIUM_TURF = new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block WARPED_NYLIUM_TURF_STAIRS = new GrassTurfStairsBlock(WARPED_NYLIUM_TURF.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block WARPED_NYLIUM_TURF_SLAB = new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block WARPED_NYLIUM_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TEAL).strength(0.1F).sounds(BlockSoundGroup.NYLIUM));

	// FORAGING
	public static final Block WILD_CARROTS = new WildCropBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block WILD_POTATOES = new WildCropBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block SEA_BEETS = new SandyPlantBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block BEACHGRASS = new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block TALL_BEACHGRASS = new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block PEBBLES = new PebbleBlock(AbstractBlock.Settings.of(Material.STONE).noCollision().breakInstantly().sounds(BlockSoundGroup.TUFF));

	public static final Block MYCELIUM_ROOTS = new OmniRootsBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	// WOOD
	public static final Block OAK_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block BIRCH_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block SPRUCE_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CRIMSON_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block WARPED_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	// BOOKSHELVES
	public static final Block BIRCH_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block SPRUCE_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CRIMSON_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block WARPED_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	// CARVED
	public static final Block CARVED_OAK = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_BIRCH = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_SPRUCE = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_JUNGLE = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_ACACIA = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_DARK_OAK = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_MANGROVE = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_CRIMSON = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_WARPED = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	// PILLAR
	public static final Block OAK_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block BIRCH_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block SPRUCE_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CRIMSON_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block WARPED_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	// BOARDS
	public static final Block OAK_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block BIRCH_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block SPRUCE_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CRIMSON_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block WARPED_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));

	// AUTUMN VIBES
	public static final Block GOLDEN_BIRCH_LEAVES = new ParticleLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), MBParticles.FALLING_LEAF);
	public static final Block GOLDEN_BIRCH_LEAF_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).breakInstantly().nonOpaque().noCollision());
	public static final Block GOLDEN_BIRCH_SAPLING =new MBSaplingBlock(new GoldenBirchSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	public static final Block POTTED_GOLDEN_BIRCH_SAPLING = new FlowerPotBlock(GOLDEN_BIRCH_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block RED_OAK_LEAVES = new ParticleLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), MBParticles.FALLING_OAK_LEAF);
	public static final Block RED_OAK_LEAF_CARPET = new LeafCarpetBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).breakInstantly().nonOpaque().noCollision());
	public static final Block RED_OAK_SAPLING =new MBSaplingBlock(new RedOakSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	public static final Block POTTED_RED_OAK_SAPLING = new FlowerPotBlock(RED_OAK_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block PUFFBALLS = new PuffballsBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block POTTED_PUFFBALLS = new FlowerPotBlock(PUFFBALLS, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block SAFFRON_MUSHROOM = new MushroomPlantBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).noCollision().ticksRandomly().breakInstantly()
			.sounds(BlockSoundGroup.GRASS).postProcess((state, world, pos) -> true), () -> MBTreeFeatures.SAFFRON_MUSHROOM);
	public static final Block POTTED_SAFFRON_MUSHROOM = new FlowerPotBlock(SAFFRON_MUSHROOM, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
	public static final Block TOADSTOOL = new ToadstoolBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_RED).strength(0.5F).sounds(BlockSoundGroup.SWEET_BERRY_BUSH));
	public static final Block SMALL_TOADSTOOLS = new SmallToadstoolBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_RED).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block POTTED_SMALL_TOADSTOOLS = new FlowerPotBlock(SMALL_TOADSTOOLS, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block RED_MUSH_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_STAIRS = new MBStairsBlock(RED_MUSH_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_BRICK_STAIRS = new MBStairsBlock(RED_MUSH_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block RED_MUSH_LAMP = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 15));

	public static final Block BROWN_MUSH_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_STAIRS = new MBStairsBlock(BROWN_MUSH_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_BRICK_STAIRS = new MBStairsBlock(BROWN_MUSH_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block BROWN_MUSH_LAMP = new Block(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 15));

	public static final Block RED_MUSHROOM_CAP = new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.RED).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block BROWN_MUSHROOM_CAP = new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.BROWN).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block SAFFRON_MUSHROOM_CAP = new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block SAFFRON_GILLS = new MushroomGillBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.GRASS)
			.breakInstantly().nonOpaque().noCollision());
	public static final Block GIANT_TOADSTOOL_CAP = new GiantToadstoolBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_RED).strength(0.5F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block MUSHROOM_STEM = new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block STRIPPED_MUSHROOM_STEM = new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block MUSHROOM_HYPHAE = new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));
	public static final Block STRIPPED_MUSHROOM_HYPHAE = new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM));

	public static final Block SAFFRON_MUSH_BLOCK = new Block(AbstractBlock.Settings.copy(SAFFRON_MUSHROOM_CAP).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_STAIRS = new MBStairsBlock(SAFFRON_MUSH_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_SLAB = new SlabBlock(AbstractBlock.Settings.copy(SAFFRON_MUSHROOM_CAP).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_BRICKS = new Block(AbstractBlock.Settings.copy(SAFFRON_MUSHROOM_CAP).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_BRICK_STAIRS = new MBStairsBlock(SAFFRON_MUSH_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(SAFFRON_MUSHROOM_CAP).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block SAFFRON_MUSH_LAMP = new Block(AbstractBlock.Settings.copy(SAFFRON_MUSHROOM_CAP).strength(1.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 15));

	public static final Block TOADSTOOL_MUSH_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_STAIRS = new MBStairsBlock(TOADSTOOL_MUSH_BLOCK.getDefaultState(), FabricBlockSettings.of(Material.WOOD).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_BRICK_STAIRS = new MBStairsBlock(TOADSTOOL_MUSH_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).hardness(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE));
	public static final Block TOADSTOOL_MUSH_LAMP = new Block(AbstractBlock.Settings.copy(Blocks.RED_MUSHROOM_BLOCK).strength(1.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 15));

	public static final Block LEAFBED = new MBSnowyBlock(AbstractBlock.Settings.of(Material.SOIL, MapColor.ORANGE).strength(0.5F).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());
	public static final Block BEDROLL = new BedrollBlock(DyeColor.BROWN, AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque());
	public static BlockEntityType<BedrollBlockEntity> BEDROLL_BLOCK_ENTITY;
	public static final Block FUR_BLOCK = new Block(AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOL));
	public static final Block FUR_CARPET = new CarpetBlock(AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque());

	public static final Block HONEY_CAULDRON = new HoneyCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON), MBCauldronBehaviour.HONEY_CAULDRON_BEHAVIOR);
	public static final Block SYRUP_CAULDRON = new HoneyCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON), MBCauldronBehaviour.SYRUP_CAULDRON_BEHAVIOR);

	public static final Block BASIN = new BasinBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER));
	public static final Block WATER_BASIN = new FluidBasinBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER), Items.POTION);
	public static final Block HONEY_BASIN = new FluidBasinBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER), Items.HONEY_BOTTLE);
	public static final Block SYRUP_BASIN = new FluidBasinBlock(AbstractBlock.Settings.copy(Blocks.CUT_COPPER), MBItems.SYRUP_BOTTLE);

	public static final Block SYRUP_BLOCK = new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK).dynamicBounds());

	public static final Block TREE_TAP = new TreeTapBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK));

	// SNOW DAY

	public static final Block JUNIPER_PLANKS = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
	public static final Block JUNIPER_STAIRS = new MBStairsBlock(JUNIPER_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB));
	public static final Block JUNIPER_LOG = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_WOOD = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block STRIPPED_JUNIPER_LOG = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block STRIPPED_JUNIPER_WOOD = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_LEAVES = new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.GRASS));
	public static final Block JUNIPER_SAPLING =new MBSaplingBlock(new JuniperSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	public static final Block POTTED_JUNIPER_SAPLING = new FlowerPotBlock(JUNIPER_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
	public static final Block JUNIPER_FENCE = new FenceBlock(FabricBlockSettings.copy(JUNIPER_PLANKS));
	public static final Block JUNIPER_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(JUNIPER_PLANKS));
	public static final Block JUNIPER_DOOR = new MBDoorBlock(FabricBlockSettings.copy(JUNIPER_PLANKS).nonOpaque());
	public static final Block JUNIPER_TRAPDOOR = new MBTrapdoorBlock(FabricBlockSettings.copy(JUNIPER_PLANKS).nonOpaque());
	public static final Block JUNIPER_BUTTON = new MBWoodenButtonBlock(FabricBlockSettings.copy(JUNIPER_PLANKS).noCollision());
	public static final Block JUNIPER_PRESSURE_PLATE = new MBPressurePlateBlock((ActivationRule.EVERYTHING), (FabricBlockSettings.copy(JUNIPER_PLANKS)).noCollision());
	public static final Block JUNIPER_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_JUNIPER = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNIPER_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));

	public static final SignType JUNIPER_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("juniper"));
	public static final Block JUNIPER_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), JUNIPER_SIGN_TYPE);
	public static final Block JUNIPER_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(JUNIPER_SIGN), JUNIPER_SIGN_TYPE);

	public static final Block COTTONGRASS = new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block TALL_COTTONGRASS = new TallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block WHITE_HEATHER = new FlowerBlock(StatusEffects.RESISTANCE, 12, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block RED_HEATHER = new FlowerBlock(StatusEffects.RESISTANCE, 3, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block ORANGE_HEATHER = new FlowerBlock(StatusEffects.RESISTANCE, 3, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block PURPLE_HEATHER = new FlowerBlock(StatusEffects.RESISTANCE, 3, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block LUPINE = new TallFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	public static final Block FROSTHORN_CROWN = new FrosthornCrownBlock(AbstractBlock.Settings.copy(Blocks.MELON).ticksRandomly());
	public static final Block FROSTHORN_STEM = new FrosthornStemBlock(AbstractBlock.Settings.copy(Blocks.MELON));
	public static final Block FROSTHORN_LEAVES = new FrosthornLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).ticksRandomly().noCollision().nonOpaque().breakInstantly());
	public static final Block FROSTHORN_FRUIT = new FrosthornFruitBlock(AbstractBlock.Settings.copy(Blocks.MELON).breakInstantly());

	public static final Block PERMAFROST = new MBSnowyBlock(AbstractBlock.Settings.copy(Blocks.DIRT));
	public static final Block FROST_PEAT = new Block(AbstractBlock.Settings.copy(PERMAFROST));
	public static final Block FROST_GOLD = new Block(AbstractBlock.Settings.copy(PERMAFROST));
	public static final Block FROST_COPPER = new Block(AbstractBlock.Settings.copy(PERMAFROST));
	public static final Block FROST_CLAY = new Block(AbstractBlock.Settings.copy(PERMAFROST));

	public static final Block TILL = new MBSnowyBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block TILL_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_STAIRS = new MBStairsBlock(TILL.getDefaultState(), AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_WALL = new WallBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block POLISHED_TILL = new Block(AbstractBlock.Settings.copy(TILL));
	public static final Block POLISHED_TILL_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block POLISHED_TILL_STAIRS = new MBStairsBlock(POLISHED_TILL.getDefaultState(), AbstractBlock.Settings.copy(TILL));
	public static final Block POLISHED_TILL_WALL = new WallBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block CHISELED_TILL = new Block(AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_BRICKS = new Block(AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_BRICK_STAIRS = new MBStairsBlock(TILL_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(TILL));
	public static final Block TILL_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block FROSTY_TILL_BRICKS = new Block(AbstractBlock.Settings.copy(TILL));
	public static final Block FROSTY_TILL_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TILL));
	public static final Block FROSTY_TILL_BRICK_STAIRS = new MBStairsBlock(FROSTY_TILL_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(TILL));
	public static final Block FROSTY_TILL_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(TILL));

	public static final Block SNOW_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK));
	public static final Block ICE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.ICE));
	public static final Block PACKED_ICE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.PACKED_ICE));

	// WARM RECEPTION
	public static final Block CEDAR_PLANKS = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
	public static final Block CEDAR_STAIRS = new MBStairsBlock(CEDAR_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB));
	public static final Block CEDAR_LOG = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_WOOD = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block STRIPPED_CEDAR_LOG = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block STRIPPED_CEDAR_WOOD = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_LEAVES = new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.GRASS));
	public static final Block CEDAR_SAPLING =new MBSaplingBlock(new CedarSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	public static final Block POTTED_CEDAR_SAPLING = new FlowerPotBlock(CEDAR_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
	public static final Block CEDAR_FENCE = new FenceBlock(FabricBlockSettings.copy(CEDAR_PLANKS));
	public static final Block CEDAR_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(CEDAR_PLANKS));
	public static final Block CEDAR_DOOR = new MBDoorBlock(FabricBlockSettings.copy(CEDAR_PLANKS).nonOpaque());
	public static final Block CEDAR_TRAPDOOR = new MBTrapdoorBlock(FabricBlockSettings.copy(CEDAR_PLANKS).nonOpaque());
	public static final Block CEDAR_BUTTON = new MBWoodenButtonBlock(FabricBlockSettings.copy(CEDAR_PLANKS).noCollision());
	public static final Block CEDAR_PRESSURE_PLATE = new MBPressurePlateBlock((ActivationRule.EVERYTHING), (FabricBlockSettings.copy(CEDAR_PLANKS)).noCollision());
	public static final Block CEDAR_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_CEDAR = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CEDAR_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));

	public static final SignType CEDAR_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("cedar"));
	public static final Block CEDAR_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), CEDAR_SIGN_TYPE);
	public static final Block CEDAR_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_ORANGE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CEDAR_SIGN), CEDAR_SIGN_TYPE);

	public static final Block PRICKLY_PEAR_CACTUS = new PricklyPearBlock(FabricBlockSettings.of(Material.CACTUS).breakInstantly().noCollision().ticksRandomly().nonOpaque().sounds(BlockSoundGroup.GRASS));
	public static final Block TALL_PRICKLY_PEAR_CACTUS = new TallPricklyPearBlock(FabricBlockSettings.of(Material.CACTUS).breakInstantly().noCollision().ticksRandomly().nonOpaque().sounds(BlockSoundGroup.GRASS));

	public static final Block BARREL_CACTUS = new BarrelCactusBlock(FabricBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL));
	public static final Block DESERT_BRUSH = new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block TALL_DESERT_BRUSH = new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block MARIGOLD = new SandyFlowerBlock(StatusEffects.POISON, 12, AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block POTTED_MARIGOLD = new FlowerPotBlock(MARIGOLD, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block CANVAS = new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).mapColor(MapColor.PALE_YELLOW));
	public static final Block FRAMED_CANVAS = new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).mapColor(MapColor.PALE_YELLOW));

	// CHERT
	public static final Block CHERT = new Block(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block CHERT_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block CHERT_STAIRS = new MBStairsBlock(CHERT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block CHERT_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE));

	public static final Block COBBLED_CHERT = new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
	public static final Block COBBLED_CHERT_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
	public static final Block COBBLED_CHERT_STAIRS = new MBStairsBlock(COBBLED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.COBBLESTONE));
	public static final Block COBBLED_CHERT_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.COBBLESTONE));

	public static final Block POLISHED_CHERT = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block POLISHED_CHERT_SLAB = new SlabBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block POLISHED_CHERT_STAIRS = new MBStairsBlock(POLISHED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(CHERT));
	public static final Block POLISHED_CHERT_WALL = new WallBlock(AbstractBlock.Settings.copy(CHERT));

	public static final Block CHERT_BRICKS = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_BRICK_STAIRS = new MBStairsBlock(CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(CHERT));

	public static final Block CRACKED_CHERT_BRICKS = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_BRICK_STAIRS = new MBStairsBlock(CRACKED_CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(CHERT));

	public static final Block CHERT_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block CUT_CHERT = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block CHISELED_CHERT = new Block(AbstractBlock.Settings.copy(CHERT));

	public static final Block CHERT_TILES = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_TILE_STAIRS = new MBStairsBlock(CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT));
	public static final Block CHERT_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(CHERT));

	public static final Block CRACKED_CHERT_TILES = new Block(AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_TILE_STAIRS = new MBStairsBlock(CRACKED_CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT));
	public static final Block CRACKED_CHERT_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(CHERT));

	public static final Block CHERT_COAL_ORE = new Block(AbstractBlock.Settings.copy(Blocks.COAL_ORE));
	public static final Block CHERT_GOLD_ORE = new Block(AbstractBlock.Settings.copy(Blocks.GOLD_ORE));
	public static final Block CHERT_COPPER_ORE = new Block(AbstractBlock.Settings.copy(Blocks.COPPER_ORE));
	public static final Block CHERT_REDSTONE_ORE = new RedstoneOreBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_ORE));
	public static final Block CHERT_LAPIS_ORE = new Block(AbstractBlock.Settings.copy(Blocks.LAPIS_ORE));
	public static final Block BANDED_IRON = new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE));
	public static final Block MAGNETITE_ORE = new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE));
	public static final Block MAGNETITE_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.IRON_ORE));

	public static final Block REDSTONE_CLUSTER = new RedstoneClusterBlock(7, 3, AbstractBlock.Settings.of(Material.STONE).mapColor(MapColor.BRIGHT_RED)
			.nonOpaque().ticksRandomly().sounds(BlockSoundGroup.AMETHYST_CLUSTER).strength(1.5f).luminance(state -> state.get(RedstoneClusterBlock.LIT) ? 10 : 5));
	public static final Block LARGE_REDSTONE_BUD = new RedstoneClusterBlock(5, 3, AbstractBlock.Settings.copy(REDSTONE_CLUSTER)
			.sounds(BlockSoundGroup.MEDIUM_AMETHYST_BUD).luminance(state -> state.get(RedstoneClusterBlock.LIT) ? 9 : 4), REDSTONE_CLUSTER);
	public static final Block MEDIUM_REDSTONE_BUD = new RedstoneClusterBlock(4, 3, AbstractBlock.Settings.copy(REDSTONE_CLUSTER)
			.sounds(BlockSoundGroup.LARGE_AMETHYST_BUD).luminance(state -> state.get(RedstoneClusterBlock.LIT) ? 7 : 2), LARGE_REDSTONE_BUD);
	public static final Block SMALL_REDSTONE_BUD = new RedstoneClusterBlock(3, 4, AbstractBlock.Settings.copy(REDSTONE_CLUSTER)
			.sounds(BlockSoundGroup.SMALL_AMETHYST_BUD).luminance(state -> state.get(RedstoneClusterBlock.LIT) ? 6 : 1), MEDIUM_REDSTONE_BUD);

	public static final Block FLOWERING_ACACIA_LEAVES = new ParticleLeavesBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_LEAVES).mapColor(MapColor.PALE_YELLOW), MBParticles.FALLING_WATTLE);

	public static final Block CRACKED_MUD = new PillarBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).mapColor(MapColor.PALE_YELLOW));

	public static final Block RICH_MUD = new Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.BROWN));
	public static final Block MUD_GOLD_DEPOSIT = new Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.BROWN));

	public static final Block MUDSTONE = new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block MUDSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(MUDSTONE));
	public static final Block MUDSTONE_STAIRS = new MBStairsBlock(MUDSTONE.getDefaultState(), AbstractBlock.Settings.copy(MUDSTONE));
	public static final Block MUDSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(MUDSTONE));

	public static final Block SMOOTH_MUDSTONE = new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA));
	public static final Block SMOOTH_MUDSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_MUDSTONE));
	public static final Block SMOOTH_MUDSTONE_STAIRS = new MBStairsBlock(SMOOTH_MUDSTONE.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_MUDSTONE));
	public static final Block SMOOTH_MUDSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(SMOOTH_MUDSTONE));

	public static final Block MUDSTONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block MUDSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(MUDSTONE_BRICKS));
	public static final Block MUDSTONE_BRICK_STAIRS = new MBStairsBlock(MUDSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MUDSTONE_BRICKS));
	public static final Block MUDSTONE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(MUDSTONE_BRICKS));

	public static final Block CUT_MUDSTONE = new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).mapColor(MapColor.TERRACOTTA_BROWN));
	public static final Block CHISELED_MUDSTONE = new Block(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).mapColor(MapColor.TERRACOTTA_BROWN));

	// FLOWER FOREST

	public static final Block WHITE_HYACINTH = new TallFlowerBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block PINK_HYACINTH = new TallFlowerBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block LIGHT_BLUE_HYACINTH = new TallFlowerBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

	// - HONEY CONTENT LMAO
	public static final Block HONEY_PLANKS = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
	public static final Block HONEY_STAIRS = new MBStairsBlock(HONEY_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD));
	public static final Block HONEY_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB));
	public static final Block HONEY_FENCE = new FenceBlock(FabricBlockSettings.copy(HONEY_PLANKS));
	public static final Block HONEY_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copy(HONEY_PLANKS));
	public static final Block HONEY_DOOR = new MBDoorBlock(FabricBlockSettings.copy(HONEY_PLANKS).nonOpaque());
	public static final Block HONEY_TRAPDOOR = new MBTrapdoorBlock(FabricBlockSettings.copy(HONEY_PLANKS).nonOpaque());
	public static final Block HONEY_BUTTON = new MBWoodenButtonBlock(FabricBlockSettings.copy(HONEY_PLANKS).noCollision());
	public static final Block HONEY_PRESSURE_PLATE = new MBPressurePlateBlock((ActivationRule.EVERYTHING), (FabricBlockSettings.copy(HONEY_PLANKS)).noCollision());
	public static final Block HONEY_BOOKSHELF = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block HONEY_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block HONEY_PANEL = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CARVED_HONEY = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block HONEY_PILLAR = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block HONEY_BOARDS = new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));

	public static final SignType HONEY_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("honey"));
	public static final Block HONEY_SIGN = new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.PALE_GREEN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), HONEY_SIGN_TYPE);
	public static final Block HONEY_WALL_SIGN = new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.PALE_GREEN).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(HONEY_SIGN), HONEY_SIGN_TYPE);

	public static final Block HONEYCOMB_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_STAIRS = new MBStairsBlock(Blocks.HONEYCOMB_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_BRICK_STAIRS = new MBStairsBlock(HONEYCOMB_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block CHISELED_HONEYCOMB_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block POLISHED_HONEYCOMB = new Block(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_TILES = new Block(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_TILE_STAIRS = new MBStairsBlock(HONEYCOMB_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));
	public static final Block HONEYCOMB_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.HONEYCOMB_BLOCK));

	// DIRT CAVES
	public static final Block TOUGH_DIRT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block TOUGH_DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block TOUGH_DIRT_STAIRS = new MBStairsBlock(TOUGH_DIRT.getDefaultState(), FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.GRAVEL));
    public static final Block DIRT_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.TUFF));
    public static final Block DIRT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.TUFF));
    public static final Block DIRT_BRICK_STAIRS = new MBStairsBlock(DIRT_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.TUFF));
	public static final Block SUBSTRATE = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.GRAVEL));

	public static final Block TOUGH_GRASS = new MBSnowyBlock(FabricBlockSettings.of(Material.SOIL).hardness(1.0f).sounds(BlockSoundGroup.GRAVEL).ticksRandomly());

	public static final Block PEAT_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block CLAY_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block GOLD_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block COPPER_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));

	public static final Block TIN_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block FROST_TIN_DEPOSIT = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));
	public static final Block TIN_ORE = new Block(FabricBlockSettings.copy(Blocks.COAL_ORE));
	public static final Block DEEPSLATE_TIN_ORE = new Block(FabricBlockSettings.copy(Blocks.DEEPSLATE_COAL_ORE));
	public static final Block CHERT_TIN_ORE = new Block(FabricBlockSettings.copy(CHERT_COAL_ORE));
	public static final Block RAW_TIN_BLOCK = new Block(FabricBlockSettings.copy(Blocks.RAW_COPPER_BLOCK));
	public static final Block TIN_BLOCK = new Block(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Block CUT_TIN = new Block(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Block CUT_TIN_SLAB = new SlabBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Block CUT_TIN_STAIRS = new MBStairsBlock(CUT_TIN.getDefaultState(), FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Block TIN_PILLAR = new PillarBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK));
	public static final Block TIN_DOOR = new MBDoorBlock(FabricBlockSettings.copy(Blocks.IRON_DOOR).nonOpaque());
	public static final Block TIN_TRAPDOOR = new MBTrapdoorBlock(FabricBlockSettings.copy(Blocks.IRON_TRAPDOOR).nonOpaque());
	
	public static final Block REGOLITH = new Block(FabricBlockSettings.of(Material.AGGREGATE).hardness(1.5f).sounds(BlockSoundGroup.GRAVEL));

	public static final Block PEAT_MOSS = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.2f).sounds(BlockSoundGroup.GRAVEL));

	public static final Block PEAT_BLOCK = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.5f).sounds(BlockSoundGroup.TUFF));
	public static final Block PEAT_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL).hardness(1.5f).sounds(BlockSoundGroup.STONE));
	public static final Block PEAT_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).hardness(1.5f).sounds(BlockSoundGroup.STONE));
	public static final Block PEAT_BRICK_STAIRS = new MBStairsBlock(PEAT_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.SOIL).hardness(1.5f));
	public static final Block PEAT_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.SOIL).hardness(1.5f).sounds(BlockSoundGroup.STONE));

	public static final Block PEANUT_CROP = new PeanutCropBlock(FabricBlockSettings.of(Material.PLANT, MapColor.PALE_GREEN).ticksRandomly().breakInstantly()
			.nonOpaque().noCollision().sounds(BlockSoundGroup.GRASS));

	public static final Block PEPPER_CROP = new PepperCropBlock(FabricBlockSettings.of(Material.PLANT, MapColor.PALE_GREEN).ticksRandomly().breakInstantly()
			.nonOpaque().noCollision().sounds(BlockSoundGroup.GRASS));

	public static final Block WILDFLOWERS = new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block POTTED_WILDFLOWERS = new FlowerPotBlock(WILDFLOWERS, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());
	public static final Block CLOVER = new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().nonOpaque().breakInstantly().sounds(BlockSoundGroup.GRASS));
	public static final Block POTTED_CLOVER = new FlowerPotBlock(CLOVER, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque());

	public static final Block LAMPROOT = new LamprootBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance((state) -> 7));
	public static final Block CAVEBLOOM_FLOWERS = new CavebloomFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS));
	public static final Block CAVEBLOOM_VINE = new CavebloomVineBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS));
	public static final Item CAVEBLOOMS = new BlockItem(CAVEBLOOM_FLOWERS, new Item.Settings().group(MBItemGroup.DECOR));

	// - PLANTER BOXES
	public static final Block OAK_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block BIRCH_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block SPRUCE_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block JUNGLE_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block ACACIA_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block DARK_OAK_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block MANGROVE_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block CRIMSON_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));
	public static final Block WARPED_PLANTER_BOX = new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD));

	// MISC.
	public static final Block GLASS_DOOR = new MBDoorBlock(FabricBlockSettings.copy(Blocks.GLASS));

	// STONE
    public static final Block STONE_PILLAR = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));

	public static final Block SMOOTH_STONE_STAIRS = new MBStairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE));
	public static final Block SMOOTH_STONE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE));

	public static final Block STONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_STONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_STONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block STONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block STONE_TILE_STAIRS = new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block STONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	
	public static final Block CRACKED_STONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block CRACKED_STONE_TILE_STAIRS = new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block CRACKED_STONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	
	public static final Block MOSSY_STONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block MOSSY_STONE_TILE_STAIRS = new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE));
	public static final Block MOSSY_STONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE));
	
	// ANDESITE
	public static final Block COBBLED_ANDESITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_ANDESITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block COBBLED_ANDESITE_STAIRS = new MBStairsBlock(COBBLED_ANDESITE.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_ANDESITE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block ANDESITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block ANDESITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block ANDESITE_BRICK_STAIRS = new MBStairsBlock(ANDESITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block ANDESITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_ANDESITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_ANDESITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block CRACKED_ANDESITE_BRICK_STAIRS = new MBStairsBlock(CRACKED_ANDESITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_ANDESITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_ANDESITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_ANDESITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block MOSSY_ANDESITE_BRICK_STAIRS = new MBStairsBlock(MOSSY_ANDESITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_ANDESITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block ANDESITE_PILLAR = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CHISELED_ANDESITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block ANDESITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block ANDESITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block ANDESITE_TILE_STAIRS = new MBStairsBlock(ANDESITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block ANDESITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_ANDESITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_ANDESITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block CRACKED_ANDESITE_TILE_STAIRS = new MBStairsBlock(CRACKED_ANDESITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_ANDESITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_ANDESITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_ANDESITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE));
	public static final Block MOSSY_ANDESITE_TILE_STAIRS = new MBStairsBlock(MOSSY_ANDESITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_ANDESITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	// DIORITE
	public static final Block COBBLED_DIORITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_DIORITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block COBBLED_DIORITE_STAIRS = new MBStairsBlock(COBBLED_DIORITE.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_DIORITE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block DIORITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block DIORITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block DIORITE_BRICK_STAIRS = new MBStairsBlock(DIORITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block DIORITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_DIORITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_DIORITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block CRACKED_DIORITE_BRICK_STAIRS = new MBStairsBlock(CRACKED_DIORITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_DIORITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_DIORITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_DIORITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block MOSSY_DIORITE_BRICK_STAIRS = new MBStairsBlock(MOSSY_DIORITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_DIORITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block DIORITE_PILLAR = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CHISELED_DIORITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block DIORITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block DIORITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block DIORITE_TILE_STAIRS = new MBStairsBlock(DIORITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block DIORITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_DIORITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_DIORITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block CRACKED_DIORITE_TILE_STAIRS = new MBStairsBlock(CRACKED_DIORITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_DIORITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_DIORITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_DIORITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE));
	public static final Block MOSSY_DIORITE_TILE_STAIRS = new MBStairsBlock(MOSSY_DIORITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_DIORITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));

	// GRANITE
	public static final Block COBBLED_GRANITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_GRANITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block COBBLED_GRANITE_STAIRS = new MBStairsBlock(COBBLED_GRANITE.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block COBBLED_GRANITE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block GRANITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block GRANITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block GRANITE_BRICK_STAIRS = new MBStairsBlock(GRANITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block GRANITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_GRANITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_GRANITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block CRACKED_GRANITE_BRICK_STAIRS = new MBStairsBlock(CRACKED_GRANITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_GRANITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_GRANITE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_GRANITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block MOSSY_GRANITE_BRICK_STAIRS = new MBStairsBlock(MOSSY_GRANITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_GRANITE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block GRANITE_PILLAR = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CHISELED_GRANITE = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block GRANITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block GRANITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block GRANITE_TILE_STAIRS = new MBStairsBlock(GRANITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block GRANITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block CRACKED_GRANITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_GRANITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block CRACKED_GRANITE_TILE_STAIRS = new MBStairsBlock(CRACKED_GRANITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_GRANITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	
	public static final Block MOSSY_GRANITE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_GRANITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE));
	public static final Block MOSSY_GRANITE_TILE_STAIRS = new MBStairsBlock(MOSSY_GRANITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_GRANITE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(1.5f));

	// SANDSTONE
	public static final Block SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block SANDSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE));
	public static final Block SANDSTONE_BRICK_STAIRS = new MBStairsBlock(SANDSTONE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block SANDSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block PAVED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	
	public static final Block CRACKED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_SANDSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE));
	public static final Block CRACKED_SANDSTONE_BRICK_STAIRS = new MBStairsBlock(CRACKED_SANDSTONE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_SANDSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_PAVED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));

	public static final Block SANDSTONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block SANDSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE));
	public static final Block SANDSTONE_TILE_STAIRS = new MBStairsBlock(SANDSTONE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block SANDSTONE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	
	public static final Block CRACKED_SANDSTONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_SANDSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE));
	public static final Block CRACKED_SANDSTONE_TILE_STAIRS = new MBStairsBlock(CRACKED_SANDSTONE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_SANDSTONE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));

	public static final Block SANDSTONE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE));
	// RED SANDSTONE
	public static final Block RED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block RED_SANDSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE));
	public static final Block RED_SANDSTONE_BRICK_STAIRS = new MBStairsBlock(RED_SANDSTONE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block RED_SANDSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block PAVED_RED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	
	public static final Block CRACKED_RED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_STAIRS = new MBStairsBlock(CRACKED_RED_SANDSTONE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_PAVED_RED_SANDSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));

	public static final Block RED_SANDSTONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block RED_SANDSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE));
	public static final Block RED_SANDSTONE_TILE_STAIRS = new MBStairsBlock(RED_SANDSTONE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block RED_SANDSTONE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	
	public static final Block CRACKED_RED_SANDSTONE_TILES = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_RED_SANDSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE));
	public static final Block CRACKED_RED_SANDSTONE_TILE_STAIRS = new MBStairsBlock(CRACKED_RED_SANDSTONE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(0.8f));
	public static final Block CRACKED_RED_SANDSTONE_TILE_WALL = new WallBlock(FabricBlockSettings.of(Material.STONE).hardness(0.8f));

	public static final Block RED_SANDSTONE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE));

	// TUFF
	public static final Block TUFF_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_STAIRS = new MBStairsBlock(Blocks.TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));

	public static final Block POLISHED_TUFF = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block POLISHED_TUFF_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block POLISHED_TUFF_STAIRS = new MBStairsBlock(POLISHED_TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block POLISHED_TUFF_WALL = new WallBlock((AbstractBlock.Settings.copy(Blocks.TUFF)));
	
	public static final Block TUFF_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_BRICK_STAIRS = new MBStairsBlock(TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block CRACKED_TUFF_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CRACKED_TUFF_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CRACKED_TUFF_BRICK_STAIRS = new MBStairsBlock(CRACKED_TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CRACKED_TUFF_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block MOSSY_TUFF_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block MOSSY_TUFF_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block MOSSY_TUFF_BRICK_STAIRS = new MBStairsBlock(MOSSY_TUFF_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_TUFF_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block TUFF_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CHISELED_TUFF = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block TUFF_TILES = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block TUFF_TILE_STAIRS = new MBStairsBlock(TUFF_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block TUFF_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block CRACKED_TUFF_TILES = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CRACKED_TUFF_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block CRACKED_TUFF_TILE_STAIRS = new MBStairsBlock(CRACKED_TUFF_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_TUFF_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	
	public static final Block MOSSY_TUFF_TILES = new Block(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block MOSSY_TUFF_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF));
	public static final Block MOSSY_TUFF_TILE_STAIRS = new MBStairsBlock(MOSSY_TUFF_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block MOSSY_TUFF_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF));

	// CALCITE
	public static final Block CALCITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CALCITE_STAIRS = new MBStairsBlock(Blocks.CALCITE.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CALCITE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block POLISHED_CALCITE = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block POLISHED_CALCITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block POLISHED_CALCITE_STAIRS = new MBStairsBlock(POLISHED_CALCITE.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block POLISHED_CALCITE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block CALCITE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CALCITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CALCITE_BRICK_STAIRS = new MBStairsBlock(CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CALCITE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block CRACKED_CALCITE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CRACKED_CALCITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CRACKED_CALCITE_BRICK_STAIRS = new MBStairsBlock(CRACKED_CALCITE_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_CALCITE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block CALCITE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CHISELED_CALCITE = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block CALCITE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CALCITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CALCITE_TILE_STAIRS = new MBStairsBlock(CALCITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CALCITE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	
	public static final Block CRACKED_CALCITE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CRACKED_CALCITE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));
	public static final Block CRACKED_CALCITE_TILE_STAIRS = new MBStairsBlock(CRACKED_CALCITE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.5f));
	public static final Block CRACKED_CALCITE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE));

	// DRIPSTONE
	public static final Block DRIPSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_STAIRS = new MBStairsBlock(Blocks.DRIPSTONE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block POLISHED_DRIPSTONE = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block POLISHED_DRIPSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block POLISHED_DRIPSTONE_STAIRS = new MBStairsBlock(POLISHED_DRIPSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block POLISHED_DRIPSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block DRIPSTONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_BRICK_STAIRS = new MBStairsBlock(DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block CRACKED_DRIPSTONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_BRICK_STAIRS = new MBStairsBlock(CRACKED_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block MOSSY_DRIPSTONE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_BRICK_STAIRS = new MBStairsBlock(MOSSY_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block DRIPSTONE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CHISELED_DRIPSTONE = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block DRIPSTONE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_TILE_STAIRS = new MBStairsBlock(DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block DRIPSTONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block CRACKED_DRIPSTONE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_TILE_STAIRS = new MBStairsBlock(CRACKED_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block CRACKED_DRIPSTONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	
	public static final Block MOSSY_DRIPSTONE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_TILE_STAIRS = new MBStairsBlock(MOSSY_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));
	public static final Block MOSSY_DRIPSTONE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK));

	// DEEPSLATE
	public static final Block SMOOTH_DEEPSLATE = new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block SMOOTH_DEEPSLATE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block SMOOTH_DEEPSLATE_STAIRS = new MBStairsBlock(SMOOTH_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block SMOOTH_DEEPSLATE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));

	public static final Block MOSSY_COBBLED_DEEPSLATE = new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_COBBLED_DEEPSLATE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_COBBLED_DEEPSLATE_STAIRS = new MBStairsBlock(MOSSY_COBBLED_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_COBBLED_DEEPSLATE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));

	public static final Block MOSSY_DEEPSLATE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_BRICK_STAIRS = new MBStairsBlock(MOSSY_DEEPSLATE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	
	public static final Block DEEPSLATE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	
	public static final Block MOSSY_DEEPSLATE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_TILE_STAIRS = new MBStairsBlock(MOSSY_DEEPSLATE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE));
	public static final Block MOSSY_DEEPSLATE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE));

	// PRISMARINE
	public static final Block SMOOTH_PRISMARINE = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block SMOOTH_PRISMARINE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block SMOOTH_PRISMARINE_STAIRS = new MBStairsBlock(SMOOTH_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block SMOOTH_PRISMARINE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	
	public static final Block POLISHED_PRISMARINE = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block POLISHED_PRISMARINE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block POLISHED_PRISMARINE_STAIRS = new MBStairsBlock(POLISHED_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block POLISHED_PRISMARINE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	
	public static final Block CRACKED_PRISMARINE_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_BRICK_STAIRS = new MBStairsBlock(CRACKED_PRISMARINE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	
	public static final Block PRISMARINE_PILLAR = new PillarBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CHISELED_PRISMARINE = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CUT_PRISMARINE = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	
	public static final Block PRISMARINE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block PRISMARINE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block PRISMARINE_TILE_STAIRS = new MBStairsBlock(PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block PRISMARINE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	
	public static final Block CRACKED_PRISMARINE_TILES = new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_TILE_STAIRS = new MBStairsBlock(CRACKED_PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE));
	public static final Block CRACKED_PRISMARINE_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE));

	// BASALT
	public static final Block BASALT_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_BRICK_STAIRS = new MBStairsBlock(BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	
	public static final Block CRACKED_BASALT_BRICKS = new Block(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_BRICK_STAIRS = new MBStairsBlock(CRACKED_BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	
	public static final Block BASALT_TILES = new Block(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_TILE_STAIRS = new MBStairsBlock(BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block BASALT_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	
	public static final Block CRACKED_BASALT_TILES = new Block(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_TILE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_TILE_STAIRS = new MBStairsBlock(CRACKED_BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block CRACKED_BASALT_TILE_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT));

	public static final Block CHISELED_BASALT = new PillarBlock(AbstractBlock.Settings.copy(Blocks.BASALT));

	public static final Block SMOOTH_BASALT_SLAB = new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT));
	public static final Block SMOOTH_BASALT_STAIRS = new MBStairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT));
	public static final Block SMOOTH_BASALT_WALL = new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT));

	// STORAGE BLOCKS
	public static final Block APPLE_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));
	public static final Block CARROT_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));
	public static final Block POTATO_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));
	public static final Block BEETROOT_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));

	public static final Block PEPPER_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));
	
	public static final Block EGG_BASKET = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(MBSounds.SACK));
	public static final Block COCOA_SACK = new Block(FabricBlockSettings.of(Material.WOOL).strength(2.0F,3.0F).sounds(MBSounds.SACK));

	public static final Block GLISTERING_MELON_BLOCK = new Block(FabricBlockSettings.of(Material.GOURD).hardness(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 12));

	public static final Block SWEET_BERRY_BASKET = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.5F).sounds(BlockSoundGroup.NYLIUM));
	public static final Block GLOW_BERRY_BASKET = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.5F).sounds(BlockSoundGroup.NYLIUM).luminance((state) -> 12));

	public static final Block SWEET_BERRY_HEDGE = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.AZALEA_LEAVES));
	public static final Block GLOW_BERRY_HEDGE = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.AZALEA_LEAVES).luminance((state) -> 12));
	public static final Block PLUCKED_SWEET_BERRY_HEDGE = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.AZALEA_LEAVES));
	public static final Block PLUCKED_GLOW_BERRY_HEDGE = new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).sounds(BlockSoundGroup.AZALEA_LEAVES));

	public static final Block SUGAR_CANE_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(MBSounds.BUNDLE));
	public static final Block BAMBOO_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.BAMBOO).hardness(1.0f).sounds(MBSounds.BUNDLE));
	public static final Block KELP_BLOCK = new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F, 2.5F).sounds(BlockSoundGroup.WET_GRASS));
	
	public static final Block NETHER_WART_SACK = new PillarBlock(FabricBlockSettings.of(Material.NETHER_SHOOTS).hardness(1.0f).sounds(BlockSoundGroup.WART_BLOCK));

	public static final Block SUGAR_CUBE = new Block(FabricBlockSettings.of(Material.SOIL).strength(2.0F,3.0F).sounds(BlockSoundGroup.SAND));
	public static final Block PACKED_GLOWSTONE = new Block(FabricBlockSettings.of(Material.GLASS).strength(2.0F,3.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> 15));
	public static final Block GUNPOWDER_CRATE = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD));

	public static final Block SPOOL = new PillarBlock(FabricBlockSettings.of(Material.WOOL).hardness(0.8f).sounds(BlockSoundGroup.WOOL));
	public static final Block PAPER_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.WOOL).hardness(0.5f).sounds(BlockSoundGroup.WOOL));
	public static final Block STICK_STACK = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(MBSounds.BUNDLE));
	public static final Block CHARCOAL_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(1.2f, 0.8f).sounds(MBSounds.BUNDLE));

	public static final Block SCUTE_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.STONE));

	public static final Block ROTTEN_FLESH_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES));
	public static final Block BONE_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(MBSounds.BUNDLE));
	public static final Block SPIDER_EYE_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES));
	public static final Block PHANTOM_MEMBRANE_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(1f).sounds(BlockSoundGroup.NYLIUM));
	public static final Block BLAZE_ROD = new MBRodBlock(FabricBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15));
	public static final Block BLAZE_ROD_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15));
	public static final Block ENDER_PEARL_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).hardness(1f).sounds(BlockSoundGroup.COPPER));
	public static final Block CHORUS_BUNDLE = new PillarBlock(FabricBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(BlockSoundGroup.NYLIUM));

	public static void createBlock(String block_id, Block block, ItemGroup group) {
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new BlockItem(block, new Item.Settings().group(group)));
	}
	public static void fluidPlaceableBlock(String block_id, Block block, ItemGroup group) {
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new LilyPadItem(block, new Item.Settings().group(group)));
	}
    
    public static void registerBlocks(){
		// - Bedroll setup, registers the block entity too :b
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "bedroll"), BEDROLL);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "bedroll"),
				(BlockItem)(new BedItem(BEDROLL, (new Item.Settings()).maxCount(1).group(MBItemGroup.MB_MISC))));
		BEDROLL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Moonbits.MODID, "bedroll_block_entity"),
				FabricBlockEntityTypeBuilder.create(BedrollBlockEntity::new, BEDROLL).build(null));


		createBlock("frosthorn_fruit", FROSTHORN_FRUIT, MBItemGroup.MB_MISC);

		// DIRT CAVES
		createBlock("tough_dirt", TOUGH_DIRT, MBItemGroup.CONSTRUCTION);
		createBlock("tough_dirt_slab", TOUGH_DIRT_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("tough_dirt_stairs", TOUGH_DIRT_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("tough_grass", TOUGH_GRASS, MBItemGroup.CONSTRUCTION);
		createBlock("leafbed", LEAFBED, MBItemGroup.CONSTRUCTION);

        createBlock("dirt_bricks", DIRT_BRICKS, MBItemGroup.CONSTRUCTION);
        createBlock("dirt_brick_slab", DIRT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
        createBlock("dirt_brick_stairs", DIRT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);

		createBlock("substrate", SUBSTRATE, MBItemGroup.CONSTRUCTION);

		createBlock("regolith", REGOLITH, MBItemGroup.CONSTRUCTION);
		createBlock("permafrost", PERMAFROST, MBItemGroup.CONSTRUCTION);
		createBlock("rich_mud", RICH_MUD, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_mud", CRACKED_MUD, MBItemGroup.CONSTRUCTION);
		createBlock("peat_moss", PEAT_MOSS, MBItemGroup.CONSTRUCTION);

		createBlock("snow_bricks", SNOW_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("ice_bricks", ICE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("packed_ice_bricks", PACKED_ICE_BRICKS, MBItemGroup.CONSTRUCTION);

		createBlock("mudstone", MUDSTONE, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_slab", MUDSTONE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_stairs", MUDSTONE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_wall", MUDSTONE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("smooth_mudstone", SMOOTH_MUDSTONE, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_mudstone_slab",SMOOTH_MUDSTONE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_mudstone_stairs", SMOOTH_MUDSTONE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_mudstone_wall", SMOOTH_MUDSTONE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mudstone_bricks", MUDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_brick_slab", MUDSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_brick_stairs", MUDSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mudstone_brick_wall", MUDSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cut_mudstone", CUT_MUDSTONE, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_mudstone", CHISELED_MUDSTONE, MBItemGroup.CONSTRUCTION);

		createBlock("till", TILL, MBItemGroup.CONSTRUCTION);
		createBlock("till_slab", TILL_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("till_stairs", TILL_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("till_wall", TILL_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("polished_till", POLISHED_TILL, MBItemGroup.CONSTRUCTION);
		createBlock("polished_till_slab", POLISHED_TILL_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_till_stairs", POLISHED_TILL_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_till_wall", POLISHED_TILL_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_till", CHISELED_TILL, MBItemGroup.CONSTRUCTION);
		createBlock("till_bricks", TILL_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("till_brick_slab", TILL_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("till_brick_stairs", TILL_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("till_brick_wall", TILL_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("frosty_till_bricks", FROSTY_TILL_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("frosty_till_brick_slab", FROSTY_TILL_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("frosty_till_brick_stairs", FROSTY_TILL_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("frosty_till_brick_wall", FROSTY_TILL_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		// CHERT
		createBlock("chert", CHERT, MBItemGroup.CONSTRUCTION);
		createBlock("chert_slab", CHERT_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("chert_stairs", CHERT_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("chert_wall", CHERT_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cobbled_chert", COBBLED_CHERT, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_chert_slab", COBBLED_CHERT_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_chert_stairs", COBBLED_CHERT_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_chert_wall", COBBLED_CHERT_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("polished_chert", POLISHED_CHERT, MBItemGroup.CONSTRUCTION);
		createBlock("polished_chert_slab", POLISHED_CHERT_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_chert_stairs", POLISHED_CHERT_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_chert_wall", POLISHED_CHERT_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("chert_bricks", CHERT_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("chert_brick_slab", CHERT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("chert_brick_stairs", CHERT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("chert_brick_wall", CHERT_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_chert_bricks", CRACKED_CHERT_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_brick_slab", CRACKED_CHERT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_brick_stairs", CRACKED_CHERT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_brick_wall", CRACKED_CHERT_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("chert_pillar", CHERT_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_chert", CHISELED_CHERT, MBItemGroup.CONSTRUCTION);
		createBlock("cut_chert", CUT_CHERT, MBItemGroup.CONSTRUCTION);

		createBlock("chert_tiles", CHERT_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("chert_tile_slab", CHERT_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("chert_tile_stairs", CHERT_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("chert_tile_wall", CHERT_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_chert_tiles", CRACKED_CHERT_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_tile_slab", CRACKED_CHERT_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_tile_stairs", CRACKED_CHERT_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_chert_tile_wall", CRACKED_CHERT_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("peat_deposit", PEAT_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("frost_peat_deposit", FROST_PEAT, MBItemGroup.CONSTRUCTION);
		createBlock("clay_deposit", CLAY_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("frost_clay_deposit", FROST_CLAY, MBItemGroup.CONSTRUCTION);
		createBlock("gold_deposit", GOLD_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("frost_gold_deposit", FROST_GOLD, MBItemGroup.CONSTRUCTION);
		createBlock("mud_gold_deposit", MUD_GOLD_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("copper_deposit", COPPER_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("frost_copper_deposit", FROST_COPPER, MBItemGroup.CONSTRUCTION);

		createBlock("tin_deposit", TIN_DEPOSIT, MBItemGroup.CONSTRUCTION);
		createBlock("frost_tin_deposit", FROST_TIN_DEPOSIT, MBItemGroup.CONSTRUCTION);

		createBlock("tin_ore", TIN_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("deepslate_tin_ore", DEEPSLATE_TIN_ORE, MBItemGroup.CONSTRUCTION);

		createBlock("chert_coal_ore", CHERT_COAL_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("chert_gold_ore", CHERT_GOLD_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("chert_copper_ore", CHERT_COPPER_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("chert_redstone_ore", CHERT_REDSTONE_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("chert_lapis_ore", CHERT_LAPIS_ORE, MBItemGroup.CONSTRUCTION);

		createBlock("chert_tin_ore", CHERT_TIN_ORE, MBItemGroup.CONSTRUCTION);

		createBlock("banded_iron", BANDED_IRON, MBItemGroup.CONSTRUCTION);
		createBlock("magnetite_ore", MAGNETITE_ORE, MBItemGroup.CONSTRUCTION);
		createBlock("magnetite_block", MAGNETITE_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("raw_tin_block", RAW_TIN_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("tin_block", TIN_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("cut_tin", CUT_TIN, MBItemGroup.CONSTRUCTION);
		createBlock("cut_tin_slab", CUT_TIN_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cut_tin_stairs", CUT_TIN_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("tin_pillar", TIN_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("peat_block", PEAT_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("peat_bricks", PEAT_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("peat_brick_slab", PEAT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("peat_brick_stairs", PEAT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("peat_brick_wall", PEAT_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		// - JUNIPER WOOD
		createBlock("juniper_planks", JUNIPER_PLANKS, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_stairs", JUNIPER_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_slab", JUNIPER_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_log", JUNIPER_LOG, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_wood", JUNIPER_WOOD, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_juniper_log", STRIPPED_JUNIPER_LOG, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_juniper_wood", STRIPPED_JUNIPER_WOOD, MBItemGroup.CONSTRUCTION);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_juniper_sapling"), POTTED_JUNIPER_SAPLING);
		createBlock("juniper_fence", JUNIPER_FENCE, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_fence_gate", JUNIPER_FENCE_GATE, ItemGroup.REDSTONE);
		createBlock("juniper_door", JUNIPER_DOOR, ItemGroup.REDSTONE);
		createBlock("juniper_trapdoor", JUNIPER_TRAPDOOR, ItemGroup.REDSTONE);
		createBlock("juniper_button", JUNIPER_BUTTON, ItemGroup.REDSTONE);
		createBlock("juniper_pressure_plate", JUNIPER_PRESSURE_PLATE, ItemGroup.REDSTONE);
		// - CEDAR WOOD
		createBlock("cedar_planks", CEDAR_PLANKS, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_stairs", CEDAR_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_slab", CEDAR_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_log", CEDAR_LOG, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_wood", CEDAR_WOOD, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_cedar_log", STRIPPED_CEDAR_LOG, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_cedar_wood", STRIPPED_CEDAR_WOOD, MBItemGroup.CONSTRUCTION);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_cedar_sapling"), POTTED_CEDAR_SAPLING);
		createBlock("cedar_fence", CEDAR_FENCE, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_fence_gate", CEDAR_FENCE_GATE, ItemGroup.REDSTONE);
		createBlock("cedar_door", CEDAR_DOOR, ItemGroup.REDSTONE);
		createBlock("cedar_trapdoor", CEDAR_TRAPDOOR, ItemGroup.REDSTONE);
		createBlock("cedar_button", CEDAR_BUTTON, ItemGroup.REDSTONE);
		createBlock("cedar_pressure_plate", CEDAR_PRESSURE_PLATE, ItemGroup.REDSTONE);

		createBlock("honey_planks", HONEY_PLANKS, MBItemGroup.CONSTRUCTION);
		createBlock("honey_stairs", HONEY_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("honey_slab", HONEY_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("honey_fence", HONEY_FENCE, MBItemGroup.CONSTRUCTION);
		createBlock("honey_fence_gate", HONEY_FENCE_GATE, ItemGroup.REDSTONE);
		createBlock("honey_door", HONEY_DOOR, ItemGroup.REDSTONE);
		createBlock("honey_trapdoor", HONEY_TRAPDOOR, ItemGroup.REDSTONE);
		createBlock("honey_button", HONEY_BUTTON, ItemGroup.REDSTONE);
		createBlock("honey_pressure_plate", HONEY_PRESSURE_PLATE, ItemGroup.REDSTONE);
		createBlock("honey_boards", HONEY_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("honey_panel", HONEY_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("carved_honey_wood", CARVED_HONEY, MBItemGroup.CONSTRUCTION);
		createBlock("honey_pillar", HONEY_PILLAR, MBItemGroup.CONSTRUCTION);


		createBlock("tin_door", TIN_DOOR, ItemGroup.REDSTONE);
		createBlock("tin_trapdoor", TIN_TRAPDOOR, ItemGroup.REDSTONE);

		// WOOD
		// - BOARDS
		createBlock("oak_boards", OAK_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("spruce_boards", SPRUCE_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("birch_boards", BIRCH_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("jungle_boards", JUNGLE_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("acacia_boards", ACACIA_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("dark_oak_boards", DARK_OAK_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("mangrove_boards", MANGROVE_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_boards", JUNIPER_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_boards", CEDAR_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("crimson_boards", CRIMSON_BOARDS, MBItemGroup.CONSTRUCTION);
		createBlock("warped_boards", WARPED_BOARDS, MBItemGroup.CONSTRUCTION);
		// - PANELS
		createBlock("oak_panel", OAK_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("spruce_panel", SPRUCE_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("birch_panel", BIRCH_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("jungle_panel", JUNGLE_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("acacia_panel", ACACIA_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("dark_oak_panel", DARK_OAK_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("mangrove_panel", MANGROVE_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_panel", JUNIPER_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_panel", CEDAR_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("crimson_panel", CRIMSON_PANEL, MBItemGroup.CONSTRUCTION);
		createBlock("warped_panel", WARPED_PANEL, MBItemGroup.CONSTRUCTION);
		// - CARVED
		createBlock("carved_oak_wood", CARVED_OAK, MBItemGroup.CONSTRUCTION);
		createBlock("carved_spruce_wood", CARVED_SPRUCE, MBItemGroup.CONSTRUCTION);
		createBlock("carved_birch_wood", CARVED_BIRCH, MBItemGroup.CONSTRUCTION);
		createBlock("carved_jungle_wood", CARVED_JUNGLE, MBItemGroup.CONSTRUCTION);
		createBlock("carved_acacia_wood", CARVED_ACACIA, MBItemGroup.CONSTRUCTION);
		createBlock("carved_dark_oak_wood", CARVED_DARK_OAK, MBItemGroup.CONSTRUCTION);
		createBlock("carved_mangrove_wood", CARVED_MANGROVE, MBItemGroup.CONSTRUCTION);
		createBlock("carved_juniper_wood", CARVED_JUNIPER, MBItemGroup.CONSTRUCTION);
		createBlock("carved_cedar_wood", CARVED_CEDAR, MBItemGroup.CONSTRUCTION);
		createBlock("carved_crimson_hyphae", CARVED_CRIMSON, MBItemGroup.CONSTRUCTION);
		createBlock("carved_warped_hyphae", CARVED_WARPED, MBItemGroup.CONSTRUCTION);
		// - PILLARS
		createBlock("oak_pillar", OAK_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("spruce_pillar", SPRUCE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("birch_pillar", BIRCH_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("jungle_pillar", JUNGLE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("acacia_pillar", ACACIA_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("dark_oak_pillar", DARK_OAK_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("mangrove_pillar", MANGROVE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("juniper_pillar", JUNIPER_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("cedar_pillar", CEDAR_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("crimson_pillar", CRIMSON_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("warped_pillar", WARPED_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("red_mushroom_cap", RED_MUSHROOM_CAP, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mushroom_cap", BROWN_MUSHROOM_CAP, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mushroom_cap", SAFFRON_MUSHROOM_CAP, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_gills", SAFFRON_GILLS, MBItemGroup.CONSTRUCTION);
		createBlock("giant_toadstool_cap", GIANT_TOADSTOOL_CAP, MBItemGroup.CONSTRUCTION);

		createBlock("mushroom_stem", MUSHROOM_STEM, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_mushroom_stem", STRIPPED_MUSHROOM_STEM, MBItemGroup.CONSTRUCTION);
		createBlock("mushroom_hyphae", MUSHROOM_HYPHAE, MBItemGroup.CONSTRUCTION);
		createBlock("stripped_mushroom_hyphae", STRIPPED_MUSHROOM_HYPHAE, MBItemGroup.CONSTRUCTION);

		createBlock("honeycomb_slab", HONEYCOMB_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_stairs", HONEYCOMB_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_wall", HONEYCOMB_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_bricks", HONEYCOMB_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_brick_slab", HONEYCOMB_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_brick_stairs", HONEYCOMB_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_brick_wall", HONEYCOMB_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_honeycomb", CHISELED_HONEYCOMB_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_honeycomb", POLISHED_HONEYCOMB, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_tiles", HONEYCOMB_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_tile_slab", HONEYCOMB_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_tile_stairs", HONEYCOMB_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_tile_wall", HONEYCOMB_TILE_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("honeycomb_pillar", HONEYCOMB_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("red_mush_block", RED_MUSH_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_stairs", RED_MUSH_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_slab", RED_MUSH_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_bricks", RED_MUSH_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_brick_stairs", RED_MUSH_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_brick_slab", RED_MUSH_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("red_mush_lamp", RED_MUSH_LAMP, MBItemGroup.CONSTRUCTION);

		createBlock("brown_mush_block", BROWN_MUSH_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_stairs", BROWN_MUSH_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_slab", BROWN_MUSH_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_bricks", BROWN_MUSH_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_brick_stairs", BROWN_MUSH_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_brick_slab", BROWN_MUSH_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("brown_mush_lamp", BROWN_MUSH_LAMP, MBItemGroup.CONSTRUCTION);

		createBlock("toadstool_mush_block", TOADSTOOL_MUSH_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_stairs", TOADSTOOL_MUSH_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_slab", TOADSTOOL_MUSH_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_bricks", TOADSTOOL_MUSH_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_brick_stairs", TOADSTOOL_MUSH_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_brick_slab", TOADSTOOL_MUSH_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("toadstool_mush_lamp", TOADSTOOL_MUSH_LAMP, MBItemGroup.CONSTRUCTION);

		createBlock("saffron_mush_block", SAFFRON_MUSH_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_stairs", SAFFRON_MUSH_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_slab", SAFFRON_MUSH_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_bricks", SAFFRON_MUSH_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_brick_stairs", SAFFRON_MUSH_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_brick_slab", SAFFRON_MUSH_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("saffron_mush_lamp", SAFFRON_MUSH_LAMP, MBItemGroup.CONSTRUCTION);

		createBlock("fur_block", FUR_BLOCK, MBItemGroup.CONSTRUCTION);
		createBlock("fur_carpet", FUR_CARPET, MBItemGroup.CONSTRUCTION);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "honey_cauldron"), HONEY_CAULDRON);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "syrup_cauldron"), SYRUP_CAULDRON);

		createBlock("canvas", CANVAS, MBItemGroup.CONSTRUCTION);
		createBlock("framed_canvas", FRAMED_CANVAS, MBItemGroup.CONSTRUCTION);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "peanut_crop"), PEANUT_CROP);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "pepper_crop"), PEPPER_CROP);

		// GLASS SHARDS/FULGURITE
		createBlock("glass_door", GLASS_DOOR, ItemGroup.REDSTONE);

		createBlock("juniper_leaves", JUNIPER_LEAVES, MBItemGroup.DECOR);
		createBlock("cedar_leaves", CEDAR_LEAVES, MBItemGroup.DECOR);

		createBlock("golden_birch_leaves", GOLDEN_BIRCH_LEAVES, MBItemGroup.DECOR);
		createBlock("red_oak_leaves", RED_OAK_LEAVES, MBItemGroup.DECOR);
		createBlock("juniper_sapling", JUNIPER_SAPLING, MBItemGroup.DECOR);
		createBlock("cedar_sapling", CEDAR_SAPLING, MBItemGroup.DECOR);

		createBlock("flowering_acacia_leaves", FLOWERING_ACACIA_LEAVES, MBItemGroup.DECOR);

		createBlock("golden_birch_sapling", GOLDEN_BIRCH_SAPLING, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_golden_birch_sapling"), POTTED_GOLDEN_BIRCH_SAPLING);
		createBlock("red_oak_sapling", RED_OAK_SAPLING, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_red_oak_sapling"), POTTED_RED_OAK_SAPLING);

		fluidPlaceableBlock("golden_birch_leaf_carpet", GOLDEN_BIRCH_LEAF_CARPET, MBItemGroup.DECOR);
		fluidPlaceableBlock("red_oak_leaf_carpet", RED_OAK_LEAF_CARPET, MBItemGroup.DECOR);

		createBlock("grass_turf", GRASS_TURF, MBItemGroup.DECOR);
		createBlock("grass_turf_stairs", GRASS_TURF_STAIRS, MBItemGroup.DECOR);
		createBlock("grass_turf_slab", GRASS_TURF_SLAB, MBItemGroup.DECOR);
		createBlock("grass_carpet", GRASS_CARPET, MBItemGroup.DECOR);

		createBlock("mycelium_turf", MYCELIUM_TURF, MBItemGroup.DECOR);
		createBlock("mycelium_turf_stairs", MYCELIUM_TURF_STAIRS, MBItemGroup.DECOR);
		createBlock("mycelium_turf_slab", MYCELIUM_TURF_SLAB, MBItemGroup.DECOR);
		createBlock("mycelium_carpet", MYCELIUM_CARPET, MBItemGroup.DECOR);

		createBlock("crimson_nylium_turf", CRIMSON_NYLIUM_TURF, MBItemGroup.DECOR);
		createBlock("crimson_nylium_turf_stairs",CRIMSON_NYLIUM_TURF_STAIRS, MBItemGroup.DECOR);
		createBlock("crimson_nylium_turf_slab", CRIMSON_NYLIUM_TURF_SLAB, MBItemGroup.DECOR);
		createBlock("crimson_nylium_carpet", CRIMSON_NYLIUM_CARPET, MBItemGroup.DECOR);
		createBlock("warped_nylium_turf", WARPED_NYLIUM_TURF, MBItemGroup.DECOR);
		createBlock("warped_nylium_turf_stairs", WARPED_NYLIUM_TURF_STAIRS, MBItemGroup.DECOR);
		createBlock("warped_nylium_turf_slab", WARPED_NYLIUM_TURF_SLAB, MBItemGroup.DECOR);
		createBlock("warped_nylium_carpet", WARPED_NYLIUM_CARPET, MBItemGroup.DECOR);

		// PLANTS
		createBlock("pebbles", PEBBLES, MBItemGroup.DECOR);

		createBlock("beachgrass", BEACHGRASS, MBItemGroup.DECOR);
		createBlock("cottongrass", COTTONGRASS, MBItemGroup.DECOR);
		createBlock("desert_brush", DESERT_BRUSH, MBItemGroup.DECOR);
		createBlock("tall_beachgrass", TALL_BEACHGRASS, MBItemGroup.DECOR);
		createBlock("tall_cottongrass", TALL_COTTONGRASS, MBItemGroup.DECOR);
		createBlock("tall_desert_brush", TALL_DESERT_BRUSH, MBItemGroup.DECOR);

		createBlock("mycelium_roots", MYCELIUM_ROOTS, MBItemGroup.DECOR);

		// FLOWERS
		createBlock("marigold", MARIGOLD, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_marigold"), POTTED_MARIGOLD);

		createBlock("white_heather", WHITE_HEATHER, MBItemGroup.DECOR);
		createBlock("red_heather", RED_HEATHER, MBItemGroup.DECOR);
		createBlock("orange_heather", ORANGE_HEATHER, MBItemGroup.DECOR);
		createBlock("purple_heather", PURPLE_HEATHER, MBItemGroup.DECOR);

		createBlock("lupine", LUPINE, MBItemGroup.DECOR);

		createBlock("white_hyacinth", WHITE_HYACINTH, MBItemGroup.DECOR);
		createBlock("pink_hyacinth", PINK_HYACINTH, MBItemGroup.DECOR);
		createBlock("light_blue_hyacinth", LIGHT_BLUE_HYACINTH, MBItemGroup.DECOR);

		createBlock("wildflowers", WILDFLOWERS, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_wildflowers"), POTTED_WILDFLOWERS);
		createBlock("clover", CLOVER, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_clover"), POTTED_CLOVER);

		// shroms
		createBlock("puffballs", PUFFBALLS, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_puffballs"), POTTED_PUFFBALLS);

		createBlock("saffron_mushroom", SAFFRON_MUSHROOM, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_saffron_mushroom"), POTTED_SAFFRON_MUSHROOM);
		createBlock("small_toadstools", SMALL_TOADSTOOLS, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "potted_small_toadstools"), POTTED_SMALL_TOADSTOOLS);
		createBlock("toadstool", TOADSTOOL, MBItemGroup.DECOR);

		createBlock("wild_carrots", WILD_CARROTS, MBItemGroup.DECOR);
		createBlock("wild_potatoes", WILD_POTATOES, MBItemGroup.DECOR);
		createBlock("sea_beets", SEA_BEETS, MBItemGroup.DECOR);

		createBlock("frosthorn_crown", FROSTHORN_CROWN, MBItemGroup.DECOR);
		createBlock("frosthorn_stem", FROSTHORN_STEM, MBItemGroup.DECOR);
		createBlock("frosthorn_leaves", FROSTHORN_LEAVES, MBItemGroup.DECOR);

		createBlock("prickly_pear_cactus", PRICKLY_PEAR_CACTUS, MBItemGroup.DECOR);
		createBlock("tall_prickly_pear_cactus", TALL_PRICKLY_PEAR_CACTUS, MBItemGroup.DECOR);

		createBlock("barrel_cactus", BARREL_CACTUS, MBItemGroup.DECOR);

		createBlock("lamproot", LAMPROOT, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "cavebloom_flowers"), CAVEBLOOM_FLOWERS);
		// so that the item has the right id (not important probably but dw)
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "cavebloom_vine"), CAVEBLOOM_VINE);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "caveblooms"), CAVEBLOOMS);

		// - BOOKSHELVES
		createBlock("spruce_bookshelf", SPRUCE_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("birch_bookshelf", BIRCH_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("jungle_bookshelf", JUNGLE_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("acacia_bookshelf", ACACIA_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("dark_oak_bookshelf", DARK_OAK_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("mangrove_bookshelf", MANGROVE_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("juniper_bookshelf", JUNIPER_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("cedar_bookshelf", CEDAR_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("honey_bookshelf", HONEY_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("crimson_bookshelf", CRIMSON_BOOKSHELF, MBItemGroup.DECOR);
		createBlock("warped_bookshelf", WARPED_BOOKSHELF, MBItemGroup.DECOR);
		// - PLANTER BOXES
		createBlock("oak_planter_box", OAK_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("spruce_planter_box", SPRUCE_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("birch_planter_box", BIRCH_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("jungle_planter_box", JUNGLE_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("acacia_planter_box", ACACIA_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("dark_oak_planter_box", DARK_OAK_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("mangrove_planter_box", MANGROVE_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("juniper_planter_box", JUNIPER_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("cedar_planter_box", CEDAR_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("honey_planter_box", HONEY_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("crimson_planter_box", CRIMSON_PLANTER_BOX, MBItemGroup.DECOR);
		createBlock("warped_planter_box", WARPED_PLANTER_BOX, MBItemGroup.DECOR);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "juniper_sign"), JUNIPER_SIGN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "juniper_wall_sign"), JUNIPER_WALL_SIGN);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "juniper_sign"),
				(Item)(new SignItem(new Item.Settings().group(ItemGroup.DECORATIONS), JUNIPER_SIGN, JUNIPER_WALL_SIGN)));

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "cedar_sign"), CEDAR_SIGN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "cedar_wall_sign"), CEDAR_WALL_SIGN);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "cedar_sign"),
				(Item)(new SignItem(new Item.Settings().group(ItemGroup.DECORATIONS), CEDAR_SIGN, CEDAR_WALL_SIGN)));

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "honey_sign"), HONEY_SIGN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "honey_wall_sign"), HONEY_WALL_SIGN);
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, "honey_sign"),
				(Item)(new SignItem(new Item.Settings().group(ItemGroup.DECORATIONS), HONEY_SIGN, HONEY_WALL_SIGN)));

		createBlock("kiln", KILN, MBItemGroup.DECOR);
		KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Moonbits.MODID, "kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(KilnBlockEntity::new, KILN).build(null));

		createBlock("boiling_cauldron", BOILING_CAULDRON, MBItemGroup.DECOR);
		BOILING_CAULDRON_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Moonbits.MODID, "boiling_cauldron_entity"),
				FabricBlockEntityTypeBuilder.create(BoilingCauldronEntity::new, BOILING_CAULDRON).build(null));

		createBlock("cooking_pot", COOKING_POT, MBItemGroup.DECOR);
		COOKING_POT_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Moonbits.MODID, "cooking_pot_entity"),
				FabricBlockEntityTypeBuilder.create(CookingPotBlockEntity::new, COOKING_POT).build(null));

		createBlock("tree_tap", TREE_TAP, MBItemGroup.DECOR);

		createBlock("basin", BASIN, MBItemGroup.DECOR);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "water_basin"), WATER_BASIN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "honey_basin"), HONEY_BASIN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "syrup_basin"), SYRUP_BASIN);

		createBlock("syrup_block", SYRUP_BLOCK, ItemGroup.REDSTONE);

		createBlock("rope_ladder", ROPE_LADDER, MBItemGroup.DECOR);
		createBlock("iron_ladder", IRON_LADDER, MBItemGroup.DECOR);

		createBlock("leather_seat", LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("white_seat", WHITE_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("orange_seat", ORANGE_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("magenta_seat", MAGENTA_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("light_blue_seat", LIGHT_BLUE_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("yellow_seat", YELLOW_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("lime_seat", LIME_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("pink_seat", PINK_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("gray_seat", GRAY_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("light_gray_seat", LIGHT_GRAY_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("cyan_seat", CYAN_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("purple_seat", PURPLE_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("blue_seat", BLUE_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("brown_seat", BROWN_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("green_seat", GREEN_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("red_seat", RED_LEATHER_SEAT, MBItemGroup.DECOR);
		createBlock("black_seat", BLACK_LEATHER_SEAT, MBItemGroup.DECOR);

		createBlock("redstone_cluster", REDSTONE_CLUSTER, MBItemGroup.DECOR);
		createBlock("large_redstone_bud", LARGE_REDSTONE_BUD, MBItemGroup.DECOR);
		createBlock("medium_redstone_bud", MEDIUM_REDSTONE_BUD, MBItemGroup.DECOR);
		createBlock("small_redstone_bud", SMALL_REDSTONE_BUD, MBItemGroup.DECOR);


		// STONE
		createBlock("stone_pillar", STONE_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("smooth_stone_stairs", SMOOTH_STONE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_stone_wall", SMOOTH_STONE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("stone_tiles", STONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("stone_tile_slab", STONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("stone_tile_stairs", STONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("stone_tile_wall", STONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_stone_tiles", CRACKED_STONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_stone_tile_slab", CRACKED_STONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_stone_tile_stairs", CRACKED_STONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_stone_tile_wall", CRACKED_STONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_stone_tiles", MOSSY_STONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_stone_tile_slab", MOSSY_STONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_stone_tile_stairs", MOSSY_STONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_stone_tile_wall", MOSSY_STONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// ANDESITE
		createBlock("cobbled_andesite", COBBLED_ANDESITE, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_andesite_slab", COBBLED_ANDESITE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_andesite_stairs", COBBLED_ANDESITE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_andesite_wall", COBBLED_ANDESITE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("andesite_bricks", ANDESITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_brick_slab", ANDESITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_brick_stairs", ANDESITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_brick_wall", ANDESITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_andesite_bricks", CRACKED_ANDESITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_brick_slab", CRACKED_ANDESITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_brick_stairs", CRACKED_ANDESITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_brick_wall", CRACKED_ANDESITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_andesite_bricks", MOSSY_ANDESITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_brick_slab", MOSSY_ANDESITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_brick_stairs", MOSSY_ANDESITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_brick_wall", MOSSY_ANDESITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("andesite_pillar", ANDESITE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_andesite", CHISELED_ANDESITE, MBItemGroup.CONSTRUCTION);

		createBlock("andesite_tiles", ANDESITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_tile_slab", ANDESITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_tile_stairs", ANDESITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("andesite_tile_wall", ANDESITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_andesite_tiles", CRACKED_ANDESITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_tile_slab", CRACKED_ANDESITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_tile_stairs", CRACKED_ANDESITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_andesite_tile_wall", CRACKED_ANDESITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_andesite_tiles", MOSSY_ANDESITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_tile_slab", MOSSY_ANDESITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_tile_stairs", MOSSY_ANDESITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_andesite_tile_wall", MOSSY_ANDESITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// DIORITE
		createBlock("cobbled_diorite", COBBLED_DIORITE, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_diorite_slab", COBBLED_DIORITE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_diorite_stairs", COBBLED_DIORITE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_diorite_wall", COBBLED_DIORITE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("diorite_bricks", DIORITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_brick_slab", DIORITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_brick_stairs", DIORITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_brick_wall", DIORITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_diorite_bricks", CRACKED_DIORITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_brick_slab", CRACKED_DIORITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_brick_stairs", CRACKED_DIORITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_brick_wall", CRACKED_DIORITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_diorite_bricks", MOSSY_DIORITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_brick_slab", MOSSY_DIORITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_brick_stairs", MOSSY_DIORITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_brick_wall", MOSSY_DIORITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("diorite_pillar", DIORITE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_diorite", CHISELED_DIORITE, MBItemGroup.CONSTRUCTION);

		createBlock("diorite_tiles", DIORITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_tile_slab", DIORITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_tile_stairs", DIORITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("diorite_tile_wall", DIORITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_diorite_tiles", CRACKED_DIORITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_tile_slab", CRACKED_DIORITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_tile_stairs", CRACKED_DIORITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_diorite_tile_wall", CRACKED_DIORITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_diorite_tiles", MOSSY_DIORITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_tile_slab", MOSSY_DIORITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_tile_stairs", MOSSY_DIORITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_diorite_tile_wall", MOSSY_DIORITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// GRANITE
		createBlock("cobbled_granite", COBBLED_GRANITE, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_granite_slab", COBBLED_GRANITE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_granite_stairs", COBBLED_GRANITE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cobbled_granite_wall", COBBLED_GRANITE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("granite_bricks", GRANITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("granite_brick_slab", GRANITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("granite_brick_stairs", GRANITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("granite_brick_wall", GRANITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_granite_bricks", CRACKED_GRANITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_brick_slab", CRACKED_GRANITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_brick_stairs", CRACKED_GRANITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_brick_wall", CRACKED_GRANITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_granite_bricks", MOSSY_GRANITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_brick_slab", MOSSY_GRANITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_brick_stairs", MOSSY_GRANITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_brick_wall", MOSSY_GRANITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("granite_pillar", GRANITE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_granite", CHISELED_GRANITE, MBItemGroup.CONSTRUCTION);

		createBlock("granite_tiles", GRANITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("granite_tile_slab", GRANITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("granite_tile_stairs", GRANITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("granite_tile_wall", GRANITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_granite_tiles", CRACKED_GRANITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_tile_slab", CRACKED_GRANITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_tile_stairs", CRACKED_GRANITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_granite_tile_wall", CRACKED_GRANITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_granite_tiles", MOSSY_GRANITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_tile_slab", MOSSY_GRANITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_tile_stairs", MOSSY_GRANITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_granite_tile_wall", MOSSY_GRANITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// SANDSTONE
		createBlock("sandstone_bricks", SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_brick_slab", SANDSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_brick_stairs", SANDSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_brick_wall", SANDSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("paved_sandstone_bricks", PAVED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_sandstone_bricks", CRACKED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_brick_slab", CRACKED_SANDSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_brick_stairs", CRACKED_SANDSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_brick_wall", CRACKED_SANDSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_paved_sandstone_bricks", CRACKED_PAVED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);

		createBlock("sandstone_pillar", SANDSTONE_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("sandstone_tiles", SANDSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_tile_slab", SANDSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_tile_stairs", SANDSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("sandstone_tile_wall", SANDSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_sandstone_tiles", CRACKED_SANDSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_tile_slab", CRACKED_SANDSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_tile_stairs", CRACKED_SANDSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_sandstone_tile_wall", CRACKED_SANDSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);
		// RED SANDSTONE
		createBlock("red_sandstone_bricks", RED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_brick_slab", RED_SANDSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_brick_stairs", RED_SANDSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_brick_wall", RED_SANDSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("paved_red_sandstone_bricks", PAVED_RED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_red_sandstone_bricks", CRACKED_RED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_brick_slab", CRACKED_RED_SANDSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_brick_stairs", CRACKED_RED_SANDSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_brick_wall", CRACKED_RED_SANDSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_paved_red_sandstone_bricks", CRACKED_PAVED_RED_SANDSTONE_BRICKS, MBItemGroup.CONSTRUCTION);

		createBlock("red_sandstone_pillar", RED_SANDSTONE_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("red_sandstone_tiles", RED_SANDSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_tile_slab", RED_SANDSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_tile_stairs", RED_SANDSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("red_sandstone_tile_wall", RED_SANDSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_red_sandstone_tiles", CRACKED_RED_SANDSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_tile_slab", CRACKED_RED_SANDSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_tile_stairs", CRACKED_RED_SANDSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_red_sandstone_tile_wall", CRACKED_RED_SANDSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// TUFF
		createBlock("tuff_slab", TUFF_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_stairs", TUFF_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_wall", TUFF_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("polished_tuff", POLISHED_TUFF, MBItemGroup.CONSTRUCTION);
		createBlock("polished_tuff_slab", POLISHED_TUFF_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_tuff_stairs", POLISHED_TUFF_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_tuff_wall", POLISHED_TUFF_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("tuff_bricks", TUFF_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_brick_slab", TUFF_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_brick_stairs", TUFF_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_brick_wall", TUFF_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_tuff_bricks", CRACKED_TUFF_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_brick_slab", CRACKED_TUFF_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_brick_stairs", CRACKED_TUFF_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_brick_wall", CRACKED_TUFF_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_tuff_bricks", MOSSY_TUFF_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_brick_slab", MOSSY_TUFF_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_brick_stairs", MOSSY_TUFF_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_brick_wall", MOSSY_TUFF_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("tuff_pillar", TUFF_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_tuff", CHISELED_TUFF, MBItemGroup.CONSTRUCTION);

		createBlock("tuff_tiles", TUFF_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_tile_slab", TUFF_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_tile_stairs", TUFF_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("tuff_tile_wall", TUFF_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_tuff_tiles", CRACKED_TUFF_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_tile_slab", CRACKED_TUFF_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_tile_stairs", CRACKED_TUFF_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_tuff_tile_wall", CRACKED_TUFF_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_tuff_tiles", MOSSY_TUFF_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_tile_slab", MOSSY_TUFF_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_tile_stairs", MOSSY_TUFF_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_tuff_tile_wall", MOSSY_TUFF_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// CALCITE
		createBlock("calcite_slab", CALCITE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_stairs", CALCITE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_wall", CALCITE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("polished_calcite", POLISHED_CALCITE, MBItemGroup.CONSTRUCTION);
		createBlock("polished_calcite_slab", POLISHED_CALCITE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_calcite_stairs", POLISHED_CALCITE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_calcite_wall", POLISHED_CALCITE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("calcite_bricks", CALCITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_brick_slab", CALCITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_brick_stairs", CALCITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_brick_wall", CALCITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_calcite_bricks", CRACKED_CALCITE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_brick_slab", CRACKED_CALCITE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_brick_stairs", CRACKED_CALCITE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_brick_wall", CRACKED_CALCITE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("calcite_pillar", CALCITE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_calcite", CHISELED_CALCITE, MBItemGroup.CONSTRUCTION);

		createBlock("calcite_tiles", CALCITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_tile_slab", CALCITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_tile_stairs", CALCITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("calcite_tile_wall", CALCITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_calcite_tiles", CRACKED_CALCITE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_tile_slab", CRACKED_CALCITE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_tile_stairs", CRACKED_CALCITE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_calcite_tile_wall", CRACKED_CALCITE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// DRIPSTONE
		createBlock("dripstone_slab", DRIPSTONE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_stairs", DRIPSTONE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_wall", DRIPSTONE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("polished_dripstone", POLISHED_DRIPSTONE, MBItemGroup.CONSTRUCTION);
		createBlock("polished_dripstone_slab", POLISHED_DRIPSTONE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_dripstone_stairs", POLISHED_DRIPSTONE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_dripstone_wall", POLISHED_DRIPSTONE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("dripstone_bricks", DRIPSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_brick_slab", DRIPSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_brick_stairs", DRIPSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_brick_wall", DRIPSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_dripstone_bricks", CRACKED_DRIPSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_brick_slab", CRACKED_DRIPSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_brick_stairs", CRACKED_DRIPSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_brick_wall", CRACKED_DRIPSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_dripstone_bricks", MOSSY_DRIPSTONE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_brick_slab", MOSSY_DRIPSTONE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_brick_stairs", MOSSY_DRIPSTONE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_brick_wall", MOSSY_DRIPSTONE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("dripstone_pillar", DRIPSTONE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_dripstone", CHISELED_DRIPSTONE, MBItemGroup.CONSTRUCTION);

		createBlock("dripstone_tiles", DRIPSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_tile_slab", DRIPSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_tile_stairs", DRIPSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("dripstone_tile_wall", DRIPSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_dripstone_tiles", CRACKED_DRIPSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_tile_slab", CRACKED_DRIPSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_tile_stairs", CRACKED_DRIPSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_dripstone_tile_wall", CRACKED_DRIPSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_dripstone_tiles", MOSSY_DRIPSTONE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_tile_slab", MOSSY_DRIPSTONE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_tile_stairs", MOSSY_DRIPSTONE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_dripstone_tile_wall", MOSSY_DRIPSTONE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// DEEPSLATE
		createBlock("smooth_deepslate", SMOOTH_DEEPSLATE, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_deepslate_slab", SMOOTH_DEEPSLATE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_deepslate_stairs", SMOOTH_DEEPSLATE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_deepslate_wall", SMOOTH_DEEPSLATE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_cobbled_deepslate", MOSSY_COBBLED_DEEPSLATE, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_cobbled_deepslate_slab", MOSSY_COBBLED_DEEPSLATE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_cobbled_deepslate_stairs", MOSSY_COBBLED_DEEPSLATE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_cobbled_deepslate_wall", MOSSY_COBBLED_DEEPSLATE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_deepslate_bricks", MOSSY_DEEPSLATE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_brick_slab", MOSSY_DEEPSLATE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_brick_stairs", MOSSY_DEEPSLATE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_brick_wall", MOSSY_DEEPSLATE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("deepslate_pillar", DEEPSLATE_PILLAR, MBItemGroup.CONSTRUCTION);

		createBlock("mossy_deepslate_tiles", MOSSY_DEEPSLATE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_tile_slab", MOSSY_DEEPSLATE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_tile_stairs", MOSSY_DEEPSLATE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("mossy_deepslate_tile_wall", MOSSY_DEEPSLATE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// PRISMARINE
		createBlock("smooth_prismarine", SMOOTH_PRISMARINE, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_prismarine_slab", SMOOTH_PRISMARINE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_prismarine_stairs", SMOOTH_PRISMARINE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_prismarine_wall", SMOOTH_PRISMARINE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("polished_prismarine", POLISHED_PRISMARINE, MBItemGroup.CONSTRUCTION);
		createBlock("polished_prismarine_slab", POLISHED_PRISMARINE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("polished_prismarine_stairs", POLISHED_PRISMARINE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("polished_prismarine_wall", POLISHED_PRISMARINE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_prismarine_bricks", CRACKED_PRISMARINE_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_brick_slab", CRACKED_PRISMARINE_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_brick_stairs", CRACKED_PRISMARINE_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_brick_wall", CRACKED_PRISMARINE_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("prismarine_pillar", PRISMARINE_PILLAR, MBItemGroup.CONSTRUCTION);
		createBlock("chiseled_prismarine", CHISELED_PRISMARINE, MBItemGroup.CONSTRUCTION);
		createBlock("cut_prismarine", CUT_PRISMARINE, MBItemGroup.CONSTRUCTION);

		createBlock("prismarine_tiles", PRISMARINE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("prismarine_tile_slab", PRISMARINE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("prismarine_tile_stairs", PRISMARINE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("prismarine_tile_wall", PRISMARINE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_prismarine_tiles", CRACKED_PRISMARINE_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_tile_slab", CRACKED_PRISMARINE_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_tile_stairs", CRACKED_PRISMARINE_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_prismarine_tile_wall", CRACKED_PRISMARINE_TILE_WALL, MBItemGroup.CONSTRUCTION);

		// BASALT
		createBlock("basalt_bricks", BASALT_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_brick_slab", BASALT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_brick_stairs", BASALT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_brick_wall", BASALT_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_basalt_bricks", CRACKED_BASALT_BRICKS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_brick_slab", CRACKED_BASALT_BRICK_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_brick_stairs", CRACKED_BASALT_BRICK_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_brick_wall", CRACKED_BASALT_BRICK_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("basalt_tiles", BASALT_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_tile_slab", BASALT_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_tile_stairs", BASALT_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("basalt_tile_wall", BASALT_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("cracked_basalt_tiles", CRACKED_BASALT_TILES, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_tile_slab", CRACKED_BASALT_TILE_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_tile_stairs", CRACKED_BASALT_TILE_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("cracked_basalt_tile_wall", CRACKED_BASALT_TILE_WALL, MBItemGroup.CONSTRUCTION);

		createBlock("chiseled_basalt", CHISELED_BASALT, MBItemGroup.CONSTRUCTION);

		createBlock("smooth_basalt_slab", SMOOTH_BASALT_SLAB, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_basalt_stairs", SMOOTH_BASALT_STAIRS, MBItemGroup.CONSTRUCTION);
		createBlock("smooth_basalt_wall", SMOOTH_BASALT_WALL, MBItemGroup.CONSTRUCTION);

		// STORAGE BLOCKS
		createBlock("apple_crate", APPLE_CRATE, MBItemGroup.DECOR);
		createBlock("carrot_crate", CARROT_CRATE, MBItemGroup.DECOR);
		createBlock("potato_crate", POTATO_CRATE, MBItemGroup.DECOR);
		createBlock("beetroot_crate", BEETROOT_CRATE, MBItemGroup.DECOR);

		createBlock("pepper_crate", PEPPER_CRATE, MBItemGroup.DECOR);

		createBlock("egg_basket", EGG_BASKET, MBItemGroup.DECOR);
		createBlock("cocoa_sack", COCOA_SACK, MBItemGroup.DECOR);

		createBlock("glistering_melon_block", GLISTERING_MELON_BLOCK, MBItemGroup.DECOR);

		createBlock("sweet_berry_basket", SWEET_BERRY_BASKET, MBItemGroup.DECOR);
		createBlock("glow_berry_basket", GLOW_BERRY_BASKET, MBItemGroup.DECOR);

		createBlock("sweet_berry_hedge", SWEET_BERRY_HEDGE, MBItemGroup.DECOR);
		createBlock("glow_berry_hedge", GLOW_BERRY_HEDGE, MBItemGroup.DECOR);
		createBlock("plucked_sweet_berry_hedge", PLUCKED_SWEET_BERRY_HEDGE, MBItemGroup.DECOR);
		createBlock("plucked_glow_berry_hedge", PLUCKED_GLOW_BERRY_HEDGE, MBItemGroup.DECOR);

		createBlock("sugar_cane_bundle", SUGAR_CANE_BUNDLE, MBItemGroup.DECOR);
		createBlock("bamboo_bundle", BAMBOO_BUNDLE, MBItemGroup.DECOR);
		createBlock("kelp_block", KELP_BLOCK, MBItemGroup.DECOR);

		createBlock("sugar_cube", SUGAR_CUBE, MBItemGroup.DECOR);

		createBlock("packed_glowstone", PACKED_GLOWSTONE, MBItemGroup.DECOR);

		createBlock("nether_wart_bundle", NETHER_WART_SACK, MBItemGroup.DECOR);

		createBlock("spool", SPOOL, MBItemGroup.DECOR);
		createBlock("paper_bundle", PAPER_BUNDLE, MBItemGroup.DECOR);
		createBlock("stick_stack", STICK_STACK, MBItemGroup.DECOR);
		createBlock("charcoal_log", CHARCOAL_LOG, MBItemGroup.DECOR);

		createBlock("scute_block", SCUTE_BLOCK, MBItemGroup.DECOR);

		createBlock("rotten_flesh_block", ROTTEN_FLESH_BLOCK, MBItemGroup.DECOR);
		createBlock("bone_bundle", BONE_BUNDLE, MBItemGroup.DECOR);
		createBlock("gunpowder_crate", GUNPOWDER_CRATE, MBItemGroup.DECOR);
		createBlock("spider_eye_block", SPIDER_EYE_BLOCK, MBItemGroup.DECOR);
		createBlock("phantom_membrane_block", PHANTOM_MEMBRANE_BLOCK, MBItemGroup.DECOR);

		createBlock("blaze_rod_bundle", BLAZE_ROD_BUNDLE, MBItemGroup.DECOR);
		createBlock("ender_pearl_block", ENDER_PEARL_BLOCK, MBItemGroup.DECOR);
		createBlock("chorus_bundle", CHORUS_BUNDLE, MBItemGroup.DECOR);

		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "blaze_rod"), BLAZE_ROD);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "wall_lantern"), WALL_LANTERN);
		Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, "wall_soul_lantern"), WALL_SOUL_LANTERN);

	}

	

}