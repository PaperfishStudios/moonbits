package net.paperfish.moonbits.registry;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;
import net.paperfish.moonbits.block.extended.*;
import net.paperfish.moonbits.mixin.SignTypeAccessor;
import net.paperfish.moonbits.world.feature.CedarSaplingGenerator;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

@SuppressWarnings("unused")
public class MBBlocksNew {

	// VANILLA WOODS
	public static final Block OAK_BOARDS = createBlock("oak_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_MOSAIC_STAIRS = createBlock("oak_mosaic_stairs", new MBStairsBlock(OAK_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(OAK_BOARDS)));
	public static final Block OAK_MOSAIC_SLAB = createBlock("oak_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(OAK_BOARDS)));

	public static final Block OAK_PANEL = createBlock("oak_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_OAK = createBlock("carved_oak_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_PILLAR = createBlock("oak_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_PLANTER_BOX = createBlock("oak_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block SPRUCE_BOARDS = createBlock("spruce_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_MOSAIC_STAIRS = createBlock("spruce_mosaic_stairs", new MBStairsBlock(SPRUCE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(SPRUCE_BOARDS)));
	public static final Block SPRUCE_MOSAIC_SLAB = createBlock("spruce_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(SPRUCE_BOARDS)));

	public static final Block SPRUCE_PANEL = createBlock("spruce_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_SPRUCE = createBlock("carved_spruce_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_PILLAR = createBlock("spruce_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_PLANTER_BOX = createBlock("spruce_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block BIRCH_BOARDS = createBlock("birch_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_MOSAIC_STAIRS = createBlock("birch_mosaic_stairs", new MBStairsBlock(BIRCH_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(BIRCH_BOARDS)));
	public static final Block BIRCH_MOSAIC_SLAB = createBlock("birch_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(BIRCH_BOARDS)));

	public static final Block BIRCH_PANEL = createBlock("birch_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_BIRCH = createBlock("carved_birch_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_PILLAR = createBlock("birch_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_PLANTER_BOX = createBlock("birch_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block JUNGLE_BOARDS = createBlock("jungle_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_MOSAIC_STAIRS = createBlock("jungle_mosaic_stairs", new MBStairsBlock(JUNGLE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(JUNGLE_BOARDS)));
	public static final Block JUNGLE_MOSAIC_SLAB = createBlock("jungle_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(JUNGLE_BOARDS)));

	public static final Block JUNGLE_PANEL = createBlock("jungle_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_JUNGLE = createBlock("carved_jungle_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_PILLAR = createBlock("jungle_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_PLANTER_BOX = createBlock("jungle_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block ACACIA_BOARDS = createBlock("acacia_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_MOSAIC_STAIRS = createBlock("acacia_mosaic_stairs", new MBStairsBlock(ACACIA_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(ACACIA_BOARDS)));
	public static final Block ACACIA_MOSAIC_SLAB = createBlock("acacia_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(ACACIA_BOARDS)));

	public static final Block ACACIA_PANEL = createBlock("acacia_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_ACACIA = createBlock("carved_acacia_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_PILLAR = createBlock("acacia_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_PLANTER_BOX = createBlock("acacia_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block DARK_OAK_BOARDS = createBlock("dark_oak_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_MOSAIC_STAIRS = createBlock("dark_oak_mosaic_stairs", new MBStairsBlock(DARK_OAK_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(DARK_OAK_BOARDS)));
	public static final Block DARK_OAK_MOSAIC_SLAB = createBlock("dark_oak_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(DARK_OAK_BOARDS)));

	public static final Block DARK_OAK_PANEL = createBlock("dark_oak_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_DARK_OAK = createBlock("carved_dark_oak_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_PILLAR = createBlock("dark_oak_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_PLANTER_BOX = createBlock("dark_oak_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block MANGROVE_BOARDS = createBlock("mangrove_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_MOSAIC_STAIRS = createBlock("mangrove_mosaic_stairs", new MBStairsBlock(MANGROVE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(MANGROVE_BOARDS)));
	public static final Block MANGROVE_MOSAIC_SLAB = createBlock("mangrove_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(MANGROVE_BOARDS)));

	public static final Block MANGROVE_PANEL = createBlock("mangrove_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_MANGROVE = createBlock("carved_mangrove_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_PILLAR = createBlock("mangrove_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_PLANTER_BOX = createBlock("mangrove_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block MANGROVE_WEAVE = createBlock("mangrove_weave", new Block(AbstractBlock.Settings.copy(Blocks.MANGROVE_ROOTS).strength(1.0f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block MANGROVE_WEAVE_STAIRS = createBlock("mangrove_weave_stairs", new MBStairsBlock(MANGROVE_WEAVE.getDefaultState(), AbstractBlock.Settings.copy(MANGROVE_WEAVE)));
	public static final Block MANGROVE_WEAVE_SLAB = createBlock("mangrove_weave_slab", new SlabBlock(AbstractBlock.Settings.copy(MANGROVE_WEAVE)));

	public static final Block CRIMSON_BOARDS = createBlock("crimson_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_MOSAIC_STAIRS = createBlock("crimson_mosaic_stairs", new MBStairsBlock(CRIMSON_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(CRIMSON_BOARDS)));
	public static final Block CRIMSON_MOSAIC_SLAB = createBlock("crimson_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(CRIMSON_BOARDS)));

	public static final Block CRIMSON_PANEL = createBlock("crimson_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_CRIMSON = createBlock("carved_crimson_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_PILLAR = createBlock("crimson_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_PLANTER_BOX = createBlock("crimson_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block WARPED_BOARDS = createBlock("warped_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_MOSAIC_STAIRS = createBlock("warped_mosaic_stairs", new MBStairsBlock(WARPED_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(WARPED_BOARDS)));
	public static final Block WARPED_MOSAIC_SLAB = createBlock("warped_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(WARPED_BOARDS)));

	public static final Block WARPED_PANEL = createBlock("warped_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_WARPED = createBlock("carved_warped_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_PILLAR = createBlock("warped_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_PLANTER_BOX = createBlock("warped_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	// LAMPROOT
	public static final Block LAMPROOT_LOG = createBlock("lamproot_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_WOOD = createBlock("lamproot_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_LAMPROOT_LOG = createBlock("stripped_lamproot_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_LAMPROOT_WOOD = createBlock("stripped_lamproot_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block LAMPROOT_PLANKS = createBlock("lamproot_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
	public static final Block LAMPROOT_STAIRS = createBlock("lamproot_stairs", new MBStairsBlock(LAMPROOT_PLANKS.getDefaultState(),
			QuiltBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_SLAB = createBlock("lamproot_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

	public static final Block LAMPROOT_FENCE = createBlock("lamproot_fence", new FenceBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS)));
	public static final Block LAMPROOT_FENCE_GATE = createBlock("lamproot_fence_gate", new FenceGateBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS)));
	public static final Block LAMPROOT_DOOR = createBlock("lamproot_door", new MBDoorBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).nonOpaque()));
	public static final Block LAMPROOT_TRAPDOOR = createBlock("lamproot_trapdoor", new MBTrapdoorBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).nonOpaque()));

	public static final Block LAMPROOT_BUTTON = createBlock("lamproot_button", new MBWoodenButtonBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).noCollision()));
	public static final Block LAMPROOT_PRESSURE_PLATE = createBlock("lamproot_pressure_plate",
			new MBPressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), (QuiltBlockSettings.copy(LAMPROOT_PLANKS)).noCollision()));

	public static final Block LAMPROOT_BOARDS = createBlock("lamproot_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_MOSAIC_STAIRS = createBlock("lamproot_mosaic_stairs", new MBStairsBlock(LAMPROOT_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(LAMPROOT_BOARDS)));
	public static final Block LAMPROOT_MOSAIC_SLAB = createBlock("lamproot_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(LAMPROOT_BOARDS)));

	public static final Block LAMPROOT_PANEL = createBlock("lamproot_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_LAMPROOT = createBlock("carved_lamproot_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_PILLAR = createBlock("lamproot_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block LAMPROOT_BULB = createBlock("lamproot_bulb",
			new LamprootBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance((state) -> 7)));

	public static final Block LAMPROOT_PLANTER_BOX = createBlock("lamproot_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final SignType LAMPROOT_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("lamproot"));
	public static final Block LAMPROOT_SIGN = createBlock("lamproot_sign",new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), LAMPROOT_SIGN_TYPE));
	public static final Block LAMPROOT_WALL_SIGN = itemlessBlock("lamproot_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(LAMPROOT_SIGN), LAMPROOT_SIGN_TYPE));

	// CEDAR
	public static final Block CEDAR_LOG = createBlock("cedar_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_WOOD = createBlock("cedar_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_CEDAR_LOG = createBlock("stripped_cedar_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_CEDAR_WOOD = createBlock("stripped_cedar_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block CEDAR_PLANKS = createBlock("cedar_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
	public static final Block CEDAR_STAIRS = createBlock("cedar_stairs", new MBStairsBlock(CEDAR_PLANKS.getDefaultState(),
			QuiltBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_SLAB = createBlock("cedar_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

	public static final Block CEDAR_FENCE = createBlock("cedar_fence", new FenceBlock(QuiltBlockSettings.copy(CEDAR_PLANKS)));
	public static final Block CEDAR_FENCE_GATE = createBlock("cedar_fence_gate", new FenceGateBlock(QuiltBlockSettings.copy(CEDAR_PLANKS)));
	public static final Block CEDAR_DOOR = createBlock("cedar_door", new MBDoorBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).nonOpaque()));
	public static final Block CEDAR_TRAPDOOR = createBlock("cedar_trapdoor", new MBTrapdoorBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).nonOpaque()));

	public static final Block CEDAR_BUTTON = createBlock("cedar_button", new MBWoodenButtonBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).noCollision()));
	public static final Block CEDAR_PRESSURE_PLATE = createBlock("cedar_pressure_plate",
			new MBPressurePlateBlock((PressurePlateBlock.ActivationRule.EVERYTHING), (QuiltBlockSettings.copy(CEDAR_PLANKS)).noCollision()));

	public static final Block CEDAR_BOARDS = createBlock("cedar_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_MOSAIC_STAIRS = createBlock("cedar_mosaic_stairs", new MBStairsBlock(CEDAR_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(CEDAR_BOARDS)));
	public static final Block CEDAR_MOSAIC_SLAB = createBlock("cedar_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(CEDAR_BOARDS)));

	public static final Block CEDAR_PANEL = createBlock("cedar_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_CEDAR = createBlock("carved_cedar_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_PILLAR = createBlock("cedar_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block CEDAR_LEAVES = createBlock("cedar_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.GRASS)));
	public static final Block CEDAR_SAPLING = createBlock("cedar_sapling", new MBSaplingBlock(new CedarSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
	public static final Block POTTED_CEDAR_SAPLING = itemlessBlock("potted_cedar_sapling",
			new FlowerPotBlock(CEDAR_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	public static final Block CEDAR_PLANTER_BOX = createBlock("cedar_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final SignType CEDAR_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("cedar"));
	public static final Block CEDAR_SIGN = createBlock("cedar_sign", new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), CEDAR_SIGN_TYPE));
	public static final Block CEDAR_WALL_SIGN = itemlessBlock("cedar_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CEDAR_SIGN), CEDAR_SIGN_TYPE));

	// VANILLA STONES
	public static final Block CHISELED_PACKED_MUD = createBlock("chiseled_packed_mud", new Block(QuiltBlockSettings.copy(Blocks.PACKED_MUD)));

	// STONE
	public static final Block STONE_PILLAR = createBlock("stone_brick_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block SMOOTH_STONE_STAIRS = createBlock("smooth_stone_stairs", new MBStairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
	public static final Block SMOOTH_STONE_WALL = createBlock("smooth_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block STONE_TILES = createBlock("stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block STONE_TILE_STAIRS = createBlock("stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block STONE_TILE_SLAB = createBlock("stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block STONE_TILE_WALL = createBlock("stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block CRACKED_STONE_TILES = createBlock("cracked_stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_STONE_TILE_STAIRS = createBlock("cracked_stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block CRACKED_STONE_TILE_SLAB = createBlock("cracked_stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block CRACKED_STONE_TILE_WALL = createBlock("cracked_stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block MOSSY_STONE_TILES = createBlock("mossy_stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_STONE_TILE_STAIRS = createBlock("mossy_stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block MOSSY_STONE_TILE_SLAB = createBlock("mossy_stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block MOSSY_STONE_TILE_WALL = createBlock("mossy_stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	// DEEPSLATE
	public static final Block SMOOTH_DEEPSLATE = createBlock("smooth_deepslate", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_STAIRS = createBlock("smooth_deepslate_stairs",
			new MBStairsBlock(SMOOTH_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_SLAB = createBlock("smooth_deepslate_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_WALL = createBlock("smooth_deepslate_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_COBBLED_DEEPSLATE = createBlock("mossy_cobbled_deepslate", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_STAIRS = createBlock("mossy_cobbled_deepslate_stairs",
			new MBStairsBlock(MOSSY_COBBLED_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_SLAB = createBlock("mossy_cobbled_deepslate_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_WALL = createBlock("mossy_cobbled_deepslate_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_DEEPSLATE_BRICKS = createBlock("mossy_deepslate_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_STAIRS = createBlock("mossy_deepslate_brick_stairs",
			new MBStairsBlock(MOSSY_DEEPSLATE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_SLAB = createBlock("mossy_deepslate_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_WALL = createBlock("mossy_deepslate_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block DEEPSLATE_PILLAR = createBlock("deepslate_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_DEEPSLATE_TILES = createBlock("mossy_deepslate_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_STAIRS = createBlock("mossy_deepslate_tile_stairs",
			new MBStairsBlock(MOSSY_DEEPSLATE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_SLAB = createBlock("mossy_deepslate_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_WALL = createBlock("mossy_deepslate_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	// BLACKSTONE
	public static final Block SMOOTH_BLACKSTONE = createBlock("smooth_blackstone", new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_STAIRS = createBlock("smooth_blackstone_stairs",
			new MBStairsBlock(SMOOTH_BLACKSTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_SLAB = createBlock("smooth_blackstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_WALL = createBlock("smooth_blackstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	public static final Block GILDED_BLACKSTONE_BRICKS = createBlock("gilded_blackstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_STAIRS = createBlock("gilded_blackstone_brick_stairs",
			new MBStairsBlock(GILDED_BLACKSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_SLAB = createBlock("gilded_blackstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_WALL = createBlock("gilded_blackstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	public static final Block BLACKSTONE_PILLAR = createBlock("blackstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	// ANDESITE
	public static final Block COBBLED_ANDESITE = createBlock("cobbled_andesite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_ANDESITE_STAIRS = createBlock("cobbled_andesite_stairs",
			new MBStairsBlock(COBBLED_ANDESITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_ANDESITE_SLAB = createBlock("cobbled_andesite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block COBBLED_ANDESITE_WALL = createBlock("cobbled_andesite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_BRICKS = createBlock("andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_BRICK_STAIRS = createBlock("andesite_brick_stairs",
			new MBStairsBlock(ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_BRICK_SLAB = createBlock("andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block ANDESITE_BRICK_WALL = createBlock("andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_ANDESITE_BRICKS = createBlock("cracked_andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_BRICK_STAIRS = createBlock("cracked_andesite_brick_stairs",
			new MBStairsBlock(CRACKED_ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_BRICK_SLAB = createBlock("cracked_andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block CRACKED_ANDESITE_BRICK_WALL = createBlock("cracked_andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_ANDESITE_BRICKS = createBlock("mossy_andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_BRICK_STAIRS = createBlock("mossy_andesite_brick_stairs",
			new MBStairsBlock(MOSSY_ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_BRICK_SLAB = createBlock("mossy_andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block MOSSY_ANDESITE_BRICK_WALL = createBlock("mossy_andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_PILLAR = createBlock("andesite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_ANDESITE = createBlock("chiseled_andesite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_TILES = createBlock("andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_TILE_STAIRS = createBlock("andesite_tile_stairs",
			new MBStairsBlock(ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_TILE_SLAB = createBlock("andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block ANDESITE_TILE_WALL = createBlock("andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_ANDESITE_TILES = createBlock("cracked_andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_TILE_STAIRS = createBlock("cracked_andesite_tile_stairs",
			new MBStairsBlock(CRACKED_ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_TILE_SLAB = createBlock("cracked_andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block CRACKED_ANDESITE_TILE_WALL = createBlock("cracked_andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_ANDESITE_TILES = createBlock("mossy_andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_TILE_STAIRS = createBlock("mossy_andesite_tile_stairs",
			new MBStairsBlock(MOSSY_ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_TILE_SLAB = createBlock("mossy_andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block MOSSY_ANDESITE_TILE_WALL = createBlock("mossy_andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// DIORITE
	public static final Block COBBLED_DIORITE = createBlock("cobbled_diorite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_DIORITE_STAIRS = createBlock("cobbled_diorite_stairs",
			new MBStairsBlock(COBBLED_DIORITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_DIORITE_SLAB = createBlock("cobbled_diorite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block COBBLED_DIORITE_WALL = createBlock("cobbled_diorite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_BRICKS = createBlock("diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_BRICK_STAIRS = createBlock("diorite_brick_stairs",
			new MBStairsBlock(DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_BRICK_SLAB = createBlock("diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block DIORITE_BRICK_WALL = createBlock("diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_DIORITE_BRICKS = createBlock("cracked_diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_BRICK_STAIRS = createBlock("cracked_diorite_brick_stairs",
			new MBStairsBlock(CRACKED_DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_BRICK_SLAB = createBlock("cracked_diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block CRACKED_DIORITE_BRICK_WALL = createBlock("cracked_diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_DIORITE_BRICKS = createBlock("mossy_diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_BRICK_STAIRS = createBlock("mossy_diorite_brick_stairs",
			new MBStairsBlock(MOSSY_DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_BRICK_SLAB = createBlock("mossy_diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block MOSSY_DIORITE_BRICK_WALL = createBlock("mossy_diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_PILLAR = createBlock("diorite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_DIORITE = createBlock("chiseled_diorite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_TILES = createBlock("diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_TILE_STAIRS = createBlock("diorite_tile_stairs",
			new MBStairsBlock(DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_TILE_SLAB = createBlock("diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block DIORITE_TILE_WALL = createBlock("diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_DIORITE_TILES = createBlock("cracked_diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_TILE_STAIRS = createBlock("cracked_diorite_tile_stairs",
			new MBStairsBlock(CRACKED_DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_TILE_SLAB = createBlock("cracked_diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block CRACKED_DIORITE_TILE_WALL = createBlock("cracked_diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_DIORITE_TILES = createBlock("mossy_diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_TILE_STAIRS = createBlock("mossy_diorite_tile_stairs",
			new MBStairsBlock(MOSSY_DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_TILE_SLAB = createBlock("mossy_diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block MOSSY_DIORITE_TILE_WALL = createBlock("mossy_diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// GRANITE
	public static final Block COBBLED_GRANITE = createBlock("cobbled_granite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_GRANITE_STAIRS = createBlock("cobbled_granite_stairs",
			new MBStairsBlock(COBBLED_GRANITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_GRANITE_SLAB = createBlock("cobbled_granite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block COBBLED_GRANITE_WALL = createBlock("cobbled_granite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_BRICKS = createBlock("granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_BRICK_STAIRS = createBlock("granite_brick_stairs",
			new MBStairsBlock(GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_BRICK_SLAB = createBlock("granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block GRANITE_BRICK_WALL = createBlock("granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_GRANITE_BRICKS = createBlock("cracked_granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_BRICK_STAIRS = createBlock("cracked_granite_brick_stairs",
			new MBStairsBlock(CRACKED_GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_BRICK_SLAB = createBlock("cracked_granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block CRACKED_GRANITE_BRICK_WALL = createBlock("cracked_granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_GRANITE_BRICKS = createBlock("mossy_granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_BRICK_STAIRS = createBlock("mossy_granite_brick_stairs",
			new MBStairsBlock(MOSSY_GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_BRICK_SLAB = createBlock("mossy_granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block MOSSY_GRANITE_BRICK_WALL = createBlock("mossy_granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_PILLAR = createBlock("granite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_GRANITE = createBlock("chiseled_granite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_TILES = createBlock("granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_TILE_STAIRS = createBlock("granite_tile_stairs",
			new MBStairsBlock(GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_TILE_SLAB = createBlock("granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block GRANITE_TILE_WALL = createBlock("granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_GRANITE_TILES = createBlock("cracked_granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_TILE_STAIRS = createBlock("cracked_granite_tile_stairs",
			new MBStairsBlock(CRACKED_GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_TILE_SLAB = createBlock("cracked_granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block CRACKED_GRANITE_TILE_WALL = createBlock("cracked_granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_GRANITE_TILES = createBlock("mossy_granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_TILE_STAIRS = createBlock("mossy_granite_tile_stairs",
			new MBStairsBlock(MOSSY_GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_TILE_SLAB = createBlock("mossy_granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block MOSSY_GRANITE_TILE_WALL = createBlock("mossy_granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// SANDSTONE
	public static final Block SANDSTONE_BRICKS = createBlock("sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_BRICK_STAIRS = createBlock("sandstone_brick_stairs",
			new MBStairsBlock(SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_BRICK_SLAB = createBlock("sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block SANDSTONE_BRICK_WALL = createBlock("sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block PAVED_SANDSTONE_BRICKS = createBlock("paved_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_SANDSTONE_BRICKS = createBlock("cracked_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_BRICK_STAIRS = createBlock("cracked_sandstone_brick_stairs",
			new MBStairsBlock(CRACKED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_BRICK_SLAB = createBlock("cracked_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block CRACKED_SANDSTONE_BRICK_WALL = createBlock("cracked_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_PAVED_SANDSTONE_BRICKS = createBlock("cracked_paved_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block SANDSTONE_TILES = createBlock("sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_TILE_STAIRS = createBlock("sandstone_tile_stairs",
			new MBStairsBlock(SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_TILE_SLAB = createBlock("sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block SANDSTONE_TILE_WALL = createBlock("sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_SANDSTONE_TILES = createBlock("cracked_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_TILE_STAIRS = createBlock("cracked_sandstone_tile_stairs",
			new MBStairsBlock(CRACKED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_TILE_SLAB = createBlock("cracked_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block CRACKED_SANDSTONE_TILE_WALL = createBlock("cracked_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block SANDSTONE_PILLAR = createBlock("sandstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	// RED SANDSTONE
	public static final Block RED_SANDSTONE_BRICKS = createBlock("red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_BRICK_STAIRS = createBlock("red_sandstone_brick_stairs",
			new MBStairsBlock(RED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_BRICK_SLAB = createBlock("red_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block RED_SANDSTONE_BRICK_WALL = createBlock("red_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block PAVED_RED_SANDSTONE_BRICKS = createBlock("paved_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_RED_SANDSTONE_BRICKS = createBlock("cracked_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_STAIRS = createBlock("cracked_red_sandstone_brick_stairs",
			new MBStairsBlock(CRACKED_RED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_SLAB = createBlock("cracked_red_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_WALL = createBlock("cracked_red_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_PAVED_RED_SANDSTONE_BRICKS = createBlock("cracked_paved_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block RED_SANDSTONE_TILES = createBlock("red_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_TILE_STAIRS = createBlock("red_sandstone_tile_stairs",
			new MBStairsBlock(RED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_TILE_SLAB = createBlock("red_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block RED_SANDSTONE_TILE_WALL = createBlock("red_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_RED_SANDSTONE_TILES = createBlock("cracked_red_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_STAIRS = createBlock("cracked_red_sandstone_tile_stairs",
			new MBStairsBlock(CRACKED_RED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_SLAB = createBlock("cracked_red_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_WALL = createBlock("cracked_red_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block RED_SANDSTONE_PILLAR = createBlock("red_sandstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));

	// TUFF
	public static final Block TUFF_STAIRS = createBlock("tuff_stairs", new MBStairsBlock(Blocks.TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_SLAB = createBlock("tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_WALL = createBlock("tuff_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block POLISHED_TUFF = createBlock("polished_tuff", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_STAIRS = createBlock("polished_tuff_stairs",
			new MBStairsBlock(POLISHED_TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_SLAB = createBlock("polished_tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_WALL = createBlock("polished_tuff_wall", new WallBlock((AbstractBlock.Settings.copy(Blocks.TUFF))));

	public static final Block TUFF_BRICKS = createBlock("tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_STAIRS = createBlock("tuff_brick_stairs",
			new MBStairsBlock(TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_SLAB = createBlock("tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_WALL = createBlock("tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block CRACKED_TUFF_BRICKS = createBlock("cracked_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_STAIRS = createBlock("cracked_tuff_brick_stairs",
			new MBStairsBlock(CRACKED_TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_SLAB = createBlock("cracked_tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_WALL = createBlock("cracked_tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block MOSSY_TUFF_BRICKS = createBlock("mossy_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_BRICK_STAIRS = createBlock("mossy_tuff_brick_stairs",
			new MBStairsBlock(MOSSY_TUFF_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_TUFF_BRICK_SLAB = createBlock("mossy_tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_BRICK_WALL = createBlock("mossy_tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block TUFF_PILLAR = createBlock("tuff_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CHISELED_TUFF = createBlock("chiseled_tuff", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block TUFF_TILES = createBlock("tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_TILE_STAIRS = createBlock("tuff_tile_stairs",
			new MBStairsBlock(TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block TUFF_TILE_SLAB = createBlock("tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_TILE_WALL = createBlock("tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block CRACKED_TUFF_TILES = createBlock("cracked_tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_TILE_STAIRS = createBlock("cracked_tuff_tile_stairs",
			new MBStairsBlock(CRACKED_TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_TUFF_TILE_SLAB = createBlock("cracked_tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_TILE_WALL = createBlock("cracked_tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block MOSSY_TUFF_TILES = createBlock("mossy_tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_TILE_STAIRS = createBlock("mossy_tuff_tile_stairs",
			new MBStairsBlock(MOSSY_TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_TUFF_TILE_SLAB = createBlock("mossy_tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_TILE_WALL = createBlock("mossy_tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	// CALCITE
	public static final Block CALCITE_STAIRS = createBlock("calcite_stairs", new MBStairsBlock(Blocks.CALCITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_SLAB = createBlock("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_WALL = createBlock("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block POLISHED_CALCITE = createBlock("polished_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block POLISHED_CALCITE_STAIRS = createBlock("polished_calcite_stairs",
			new MBStairsBlock(POLISHED_CALCITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block POLISHED_CALCITE_SLAB = createBlock("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block POLISHED_CALCITE_WALL = createBlock("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_BRICKS = createBlock("calcite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_BRICK_STAIRS = createBlock("calcite_brick_stairs",
			new MBStairsBlock(CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_BRICK_SLAB = createBlock("calcite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_BRICK_WALL = createBlock("calcite_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CRACKED_CALCITE_BRICKS = createBlock("cracked_calcite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_BRICK_STAIRS = createBlock("cracked_calcite_brick_stairs",
			new MBStairsBlock(CRACKED_CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_CALCITE_BRICK_SLAB = createBlock("cracked_calcite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_BRICK_WALL = createBlock("cracked_calcite_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_PILLAR = createBlock("calcite_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CHISELED_CALCITE = createBlock("chiseled_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_TILES = createBlock("calcite_tiles", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_TILE_STAIRS = createBlock("calcite_tile_stairs",
			new MBStairsBlock(CALCITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_TILE_SLAB = createBlock("calcite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_TILE_WALL = createBlock("calcite_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CRACKED_CALCITE_TILES = createBlock("cracked_calcite_tiles", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_TILE_STAIRS = createBlock("cracked_calcite_tile_stairs",
			new MBStairsBlock(CRACKED_CALCITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_CALCITE_TILE_SLAB = createBlock("cracked_calcite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_TILE_WALL = createBlock("cracked_calcite_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	// DRIPSTONE
	public static final Block DRIPSTONE_STAIRS = createBlock("dripstone_stairs", new MBStairsBlock(Blocks.DRIPSTONE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_SLAB = createBlock("dripstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_WALL = createBlock("dripstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block POLISHED_DRIPSTONE = createBlock("polished_dripstone", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_STAIRS = createBlock("polished_dripstone_stairs",
			new MBStairsBlock(POLISHED_DRIPSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_SLAB = createBlock("polished_dripstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_WALL = createBlock("polished_dripstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_BRICKS = createBlock("dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_STAIRS = createBlock("dripstone_brick_stairs",
			new MBStairsBlock(DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_SLAB = createBlock("dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_WALL = createBlock("dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block CRACKED_DRIPSTONE_BRICKS = createBlock("cracked_dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_STAIRS = createBlock("cracked_dripstone_brick_stairs",
			new MBStairsBlock(CRACKED_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_SLAB = createBlock("cracked_dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_WALL = createBlock("cracked_dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block MOSSY_DRIPSTONE_BRICKS = createBlock("mossy_dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_STAIRS = createBlock("mossy_dripstone_brick_stairs",
			new MBStairsBlock(MOSSY_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_SLAB = createBlock("mossy_dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_WALL = createBlock("mossy_dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_PILLAR = createBlock("dripstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CHISELED_DRIPSTONE = createBlock("chiseled_dripstone", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_TILES = createBlock("dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_STAIRS = createBlock("dripstone_tile_stairs",
			new MBStairsBlock(DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_SLAB = createBlock("dripstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_WALL = createBlock("dripstone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block CRACKED_DRIPSTONE_TILES = createBlock("cracked_dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_STAIRS = createBlock("cracked_dripstone_tiles_stairs",
			new MBStairsBlock(CRACKED_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_SLAB = createBlock("cracked_dripstone_tiles_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_WALL = createBlock("cracked_dripstone_tiles_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block MOSSY_DRIPSTONE_TILES = createBlock("mossy_dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_STAIRS = createBlock("mossy_dripstone_tile_stairs",
			new MBStairsBlock(MOSSY_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_SLAB = createBlock("mossy_dripstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_WALL = createBlock("mossy_dripstone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	// PRISMARINE
	public static final Block SMOOTH_PRISMARINE = createBlock("smooth_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_STAIRS = createBlock("smooth_prismarine_stairs",
			new MBStairsBlock(SMOOTH_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_SLAB = createBlock("smooth_prismarine_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_WALL = createBlock("smooth_prismarine_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block POLISHED_PRISMARINE = createBlock("polished_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_STAIRS = createBlock("polished_prismarine_stairs",
			new MBStairsBlock(POLISHED_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_SLAB = createBlock("polished_prismarine_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_WALL = createBlock("polished_prismarine_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block CRACKED_PRISMARINE_BRICKS = createBlock("cracked_prismarine_bricks", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_STAIRS = createBlock("cracked_prismarine_brick_stairs",
			new MBStairsBlock(CRACKED_PRISMARINE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_SLAB = createBlock("cracked_prismarine_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_WALL = createBlock("cracked_prismarine_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block PRISMARINE_PILLAR = createBlock("prismarine_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CHISELED_PRISMARINE = createBlock("chiseled_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CUT_PRISMARINE = createBlock("cut_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block PRISMARINE_TILES = createBlock("prismarine_tiles", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_STAIRS = createBlock("prismarine_tile_stairs",
			new MBStairsBlock(PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_SLAB = createBlock("prismarine_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_WALL = createBlock("prismarine_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block CRACKED_PRISMARINE_TILES = createBlock("cracked_prismarine_tiles", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_STAIRS = createBlock("cracked_prismarine_tile_stairs",
			new MBStairsBlock(CRACKED_PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_SLAB = createBlock("cracked_prismarine_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_WALL = createBlock("cracked_prismarine_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	// BASALT
	public static final Block BASALT_BRICKS = createBlock("basalt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_STAIRS = createBlock("basalt_brick_stairs",
			new MBStairsBlock(BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_SLAB = createBlock("basalt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_WALL = createBlock("basalt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CRACKED_BASALT_BRICKS = createBlock("cracked_basalt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_STAIRS = createBlock("cracked_basalt_brick_stairs",
			new MBStairsBlock(CRACKED_BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_SLAB = createBlock("cracked_basalt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_WALL = createBlock("cracked_basalt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block BASALT_TILES = createBlock("basalt_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_STAIRS = createBlock("basalt_tile_stairs",
			new MBStairsBlock(BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_SLAB = createBlock("basalt_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_WALL = createBlock("basalt_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CRACKED_BASALT_TILES = createBlock("cracked_basalt_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_STAIRS = createBlock("cracked_basalt_tile_stairs",
			new MBStairsBlock(CRACKED_BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_SLAB = createBlock("cracked_basalt_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_WALL = createBlock("cracked_basalt_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CHISELED_BASALT = createBlock("chiseled_basalt", new PillarBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block SMOOTH_BASALT_STAIRS = createBlock("smooth_basalt_stairs", new MBStairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block SMOOTH_BASALT_SLAB = createBlock("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
	public static final Block SMOOTH_BASALT_WALL = createBlock("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

	// CHERT
	public static final Block CHERT = createBlock("chert", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.PALE_YELLOW)));
	public static final Block CHERT_STAIRS = createBlock("chert_stairs", new MBStairsBlock(CHERT.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_SLAB = createBlock("chert_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_WALL = createBlock("chert_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block COBBLED_CHERT = createBlock("cobbled_chert", new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE).mapColor(MapColor.PALE_YELLOW)));
	public static final Block COBBLED_CHERT_STAIRS = createBlock("cobbled_chert_stairs", new MBStairsBlock(COBBLED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(COBBLED_CHERT)));
	public static final Block COBBLED_CHERT_SLAB = createBlock("cobbled_chert_slab", new SlabBlock(AbstractBlock.Settings.copy(COBBLED_CHERT)));
	public static final Block COBBLED_CHERT_WALL = createBlock("cobbled_chert_wall", new WallBlock(AbstractBlock.Settings.copy(COBBLED_CHERT)));

	public static final Block POLISHED_CHERT = createBlock("polished_chert", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_STAIRS = createBlock("polished_chert_stairs", new MBStairsBlock(POLISHED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_SLAB = createBlock("polished_chert_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_WALL = createBlock("polished_chert_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_BRICKS = createBlock("chert_bricks", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_STAIRS = createBlock("chert_brick_stairs", new MBStairsBlock(CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_SLAB = createBlock("chert_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_WALL = createBlock("chert_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CRACKED_CHERT_BRICKS = createBlock("cracked_chert_bricks", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_STAIRS = createBlock("cracked_chert_brick_stairs", new MBStairsBlock(CRACKED_CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_SLAB = createBlock("cracked_chert_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_WALL = createBlock("cracked_chert_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_PILLAR = createBlock("chert_pillar", new PillarBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CUT_CHERT = createBlock("cut_chert", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHISELED_CHERT = createBlock("chiseled_chert", new Block(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_TILES = createBlock("chert_tiles", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_STAIRS = createBlock("chert_tile_stairs", new MBStairsBlock(CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_SLAB = createBlock("chert_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_WALL = createBlock("chert_tile_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CRACKED_CHERT_TILES = createBlock("cracked_chert_tiles", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_STAIRS = createBlock("cracked_chert_tile_stairs", new MBStairsBlock(CRACKED_CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_SLAB = createBlock("cracked_chert_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_WALL = createBlock("cracked_chert_tile_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_COAL_ORE = createBlock("chert_coal_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COAL_ORE), UniformIntProvider.create(0, 2)));
	public static final Block CHERT_COPPER_ORE = createBlock("chert_copper_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE)));
	public static final Block CHERT_TIN_ORE = createBlock("chert_tin_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE)));
	public static final Block CHERT_GOLD_ORE = createBlock("chert_gold_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE)));
	public static final Block CHERT_DIAMOND_ORE = createBlock("chert_diamond_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE), UniformIntProvider.create(3, 7)));
	public static final Block CHERT_REDSTONE_ORE = createBlock("chert_redstone_ore", new RedstoneOreBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_ORE)));
	public static final Block CHERT_LAPIS_ORE = createBlock("chert_lapis_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.LAPIS_ORE), UniformIntProvider.create(2, 5)));

	public static final Block BANDED_IRON = createBlock("banded_iron", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE)));
	public static final Block MAGNETITE_ORE = createBlock("magnetite_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE), UniformIntProvider.create(2, 5)));
	public static final Block MAGNETITE_BLOCK = createBlock("magnetite_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));

	// JASPER
	// TODO: Jasper main block (the lil nose thing)
	public static final Block JASPER = createBlock("jasper", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_ORANGE)));
	public static final Block JASPER_STAIRS = createBlock("jasper_stairs", new MBStairsBlock(JASPER.getDefaultState(), AbstractBlock.Settings.copy(JASPER)));
	public static final Block JASPER_SLAB = createBlock("jasper_slab", new SlabBlock(AbstractBlock.Settings.copy(JASPER)));
	public static final Block JASPER_WALL = createBlock("jasper_wall", new WallBlock(AbstractBlock.Settings.copy(JASPER)));

	public static final Block POLISHED_JASPER = createBlock("polished_jasper", new Block(AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_STAIRS = createBlock("polished_jasper_stairs", new MBStairsBlock(POLISHED_JASPER.getDefaultState(), AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_SLAB = createBlock("polished_jasper_slab", new SlabBlock(AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_WALL = createBlock("polished_jasper_wall", new WallBlock(AbstractBlock.Settings.copy(JASPER)));

	public static final Block HELIODOR_ROD = createBlock("heliodor_rod", new PillarBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.YELLOW)));

	// CLAYSTONE
	public static final Block CLAYSTONE = createBlock("claystone", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CLAYSTONE_STAIRS = createBlock("claystone_stairs", new MBStairsBlock(CLAYSTONE.getDefaultState(), AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_SLAB = createBlock("claystone_slab", new SlabBlock(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_WALL = createBlock("claystone_wall", new WallBlock(AbstractBlock.Settings.copy(CLAYSTONE)));

	public static final Block CLAYSTONE_BRICKS = createBlock("claystone_bricks", new Block(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_STAIRS = createBlock("claystone_brick_stairs", new MBStairsBlock(CLAYSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_SLAB = createBlock("claystone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_WALL = createBlock("claystone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CLAYSTONE)));

	// TODO: Cracklerock

	// AZURITE
	// TODO: Azurite main block
	public static final Block AZURITE = createBlock("azurite", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.BLUE)));
	public static final Block AZURITE_STAIRS = createBlock("azurite_stairs", new MBStairsBlock(AZURITE.getDefaultState(), AbstractBlock.Settings.copy(AZURITE)));
	public static final Block AZURITE_SLAB = createBlock("azurite_slab", new SlabBlock(AbstractBlock.Settings.copy(AZURITE)));
	public static final Block AZURITE_WALL = createBlock("azurite_wall", new WallBlock(AbstractBlock.Settings.copy(AZURITE)));

	public static final Block POLISHED_AZURITE = createBlock("polished_azurite", new Block(AbstractBlock.Settings.copy(AZURITE)));
	public static final Block POLISHED_AZURITE_STAIRS = createBlock("polished_azurite_stairs", new MBStairsBlock(POLISHED_AZURITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_AZURITE)));
	public static final Block POLISHED_AZURITE_SLAB = createBlock("polished_azurite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_AZURITE)));
	public static final Block POLISHED_AZURITE_WALL = createBlock("polished_azurite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_AZURITE)));

	public static final Block LARIMAR_ROD = createBlock("larimar_rod", new PillarBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.DIAMOND_BLUE)));

	// TODO: Boost ore, whatever thats called

	// METAL
	// Todo: Copper Blocks (Door, Trapdoor, maybe do bars and that other tiled one?)
	// TIN
	public static final Block TIN_ORE = createBlock("tin_ore", new ExperienceDroppingBlock(QuiltBlockSettings.copy(Blocks.COPPER_ORE)));
	public static final Block DEEPSLATE_TIN_ORE = createBlock("deepslate_tin_ore", new ExperienceDroppingBlock(QuiltBlockSettings.copy(Blocks.DEEPSLATE_COPPER_ORE)));

	public static final Block RAW_TIN_BLOCK = createBlock("raw_tin_block", new Block(QuiltBlockSettings.copy(Blocks.RAW_COPPER_BLOCK)));

	public static final Block PESTERED_TIN_BLOCK = createBlock("pestered_tin_block", new Block(QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block PESTERED_CUT_TIN = createBlock("pestered_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block PESTERED_CUT_TIN_STAIRS = createBlock("pestered_cut_tin_stairs",
			new MBStairsBlock(PESTERED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block PESTERED_CUT_TIN_SLAB = createBlock("pestered_cut_tin_slab", new SlabBlock(QuiltBlockSettings.copy(Blocks.TUFF)));

	public static final Block PESTERED_TIN_TRAPDOOR = createBlock("pestered_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.TUFF).nonOpaque()));

	public static final Block BLACKENED_TIN_BLOCK = createBlock("blackened_tin_block", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block BLACKENED_CUT_TIN = createBlock("blackened_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block BLACKENED_CUT_TIN_STAIRS = createBlock("blackened_cut_tin_stairs",
			new MBStairsBlock(BLACKENED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block BLACKENED_CUT_TIN_SLAB = createBlock("blackened_cut_tin_slab",
			new SlabBlock(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block BLACKENED_TIN_TRAPDOOR = createBlock("blackened_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.NETHERITE).nonOpaque()));


	public static final Block OXIDIZED_TIN_BLOCK = createBlock("oxidized_tin_block", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block OXIDIZED_CUT_TIN = createBlock("oxidized_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block OXIDIZED_CUT_TIN_STAIRS = createBlock("oxidized_cut_tin_stairs",
			new MBStairsBlock(OXIDIZED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block OXIDIZED_CUT_TIN_SLAB = createBlock("oxidized_cut_tin_slab", new SlabBlock(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));

	public static final Block OXIDIZED_TIN_TRAPDOOR = createBlock("oxidized_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));

	public static final Block TIN_BLOCK = createBlock("tin_block", new DimWeatheringBlock(OXIDIZED_TIN_BLOCK, BLACKENED_TIN_BLOCK, PESTERED_TIN_BLOCK, QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block CUT_TIN = createBlock("cut_tin", new DimWeatheringBlock(OXIDIZED_CUT_TIN, BLACKENED_CUT_TIN, PESTERED_CUT_TIN,
			QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block CUT_TIN_STAIRS = createBlock("cut_tin_stairs", new DimWeatheringStairsBlock(OXIDIZED_CUT_TIN_STAIRS, BLACKENED_CUT_TIN_STAIRS, PESTERED_CUT_TIN_STAIRS,
			CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(CUT_TIN)));
	public static final Block CUT_TIN_SLAB = createBlock("cut_tin_slab", new DimWeatheringSlabBlock(OXIDIZED_CUT_TIN_SLAB, BLACKENED_CUT_TIN_SLAB, PESTERED_CUT_TIN_SLAB,
			QuiltBlockSettings.copy(CUT_TIN)));
	// TODO: Tin Doors
	public static final Block TIN_TRAPDOOR = createBlock("tin_trapdoor", new DimWeatheringTrapdoorBlock(OXIDIZED_TIN_TRAPDOOR, BLACKENED_TIN_TRAPDOOR, PESTERED_TIN_TRAPDOOR,
			QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));

	// WAXED TIN
	public static final Block WAXED_PESTERED_TIN_BLOCK = createBlock("waxed_pestered_tin_block", new Block(QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block WAXED_PESTERED_CUT_TIN = createBlock("waxed_pestered_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block WAXED_PESTERED_CUT_TIN_STAIRS = createBlock("waxed_pestered_cut_tin_stairs",
			new MBStairsBlock(WAXED_PESTERED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block WAXED_PESTERED_CUT_TIN_SLAB = createBlock("waxed_pestered_cut_tin_slab", new SlabBlock(QuiltBlockSettings.copy(Blocks.TUFF)));
	public static final Block WAXED_PESTERED_TIN_DOOR = createBlock("waxed_pestered_tin_door", new MBDoorBlock(QuiltBlockSettings.copy(Blocks.IRON_DOOR).sounds(BlockSoundGroup.TUFF).nonOpaque()));
	public static final Block WAXED_PESTERED_TIN_TRAPDOOR = createBlock("waxed_pestered_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.TUFF).nonOpaque()));

	public static final Block WAXED_BLACKENED_TIN_BLOCK = createBlock("waxed_blackened_tin_block", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block WAXED_BLACKENED_CUT_TIN = createBlock("waxed_blackened_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block WAXED_BLACKENED_CUT_TIN_STAIRS = createBlock("waxed_blackened_cut_tin_stairs",
			new MBStairsBlock(WAXED_BLACKENED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block WAXED_BLACKENED_CUT_TIN_SLAB = createBlock("waxed_blackened_cut_tin_slab",
			new SlabBlock(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK).sounds(BlockSoundGroup.NETHERITE)));
	public static final Block WAXED_BLACKENED_TIN_DOOR = createBlock("waxed_blackened_tin_door",
			new MBDoorBlock(QuiltBlockSettings.copy(Blocks.IRON_DOOR).sounds(BlockSoundGroup.NETHERITE).nonOpaque()));
	public static final Block WAXED_BLACKENED_TIN_TRAPDOOR = createBlock("waxed_blackened_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.NETHERITE).nonOpaque()));

	public static final Block WAXED_OXIDIZED_TIN_BLOCK = createBlock("waxed_oxidized_tin_block", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_OXIDIZED_CUT_TIN = createBlock("waxed_oxidized_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_OXIDIZED_CUT_TIN_STAIRS = createBlock("waxed_oxidized_cut_tin_stairs",
			new MBStairsBlock(WAXED_OXIDIZED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_OXIDIZED_CUT_TIN_SLAB = createBlock("waxed_oxidized_cut_tin_slab", new SlabBlock(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_OXIDISED_TIN_DOOR = createBlock("waxed_oxidized_tin_door", new MBDoorBlock(QuiltBlockSettings.copy(Blocks.IRON_DOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));
	public static final Block WAXED_OXIDIZED_TIN_TRAPDOOR = createBlock("waxed_oxidized_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));

	public static final Block WAXED_TIN_BLOCK = createBlock("waxed_tin_block", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_CUT_TIN = createBlock("waxed_cut_tin", new Block(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_CUT_TIN_STAIRS = createBlock("waxed_cut_tin_stairs", new MBStairsBlock(WAXED_CUT_TIN.getDefaultState(), QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_CUT_TIN_SLAB = createBlock("waxed_cut_tin_slab", new SlabBlock(QuiltBlockSettings.copy(Blocks.COPPER_BLOCK)));
	public static final Block WAXED_TIN_DOOR = createBlock("waxed_tin_door", new MBDoorBlock(QuiltBlockSettings.copy(Blocks.IRON_DOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));
	public static final Block WAXED_TIN_TRAPDOOR = createBlock("waxed_tin_trapdoor",
			new MBTrapdoorBlock(QuiltBlockSettings.copy(Blocks.IRON_TRAPDOOR).sounds(BlockSoundGroup.COPPER).nonOpaque()));


	// SOIL
	public static final Block PACKED_DIRT = createBlock("packed_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_STAIRS = createBlock("packed_dirt_stairs", new MBStairsBlock(PACKED_DIRT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_SLAB = createBlock("packed_dirt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_WALL = createBlock("packed_dirt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block DIRT_BRICKS = createBlock("dirt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_STAIRS = createBlock("dirt_brick_stairs", new MBStairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_SLAB = createBlock("dirt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_WALL = createBlock("dirt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block REGOLITH = createBlock("regolith", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

	// TURF
	public static final Block GRASS_TURF = createBlock("grass_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_TURF_STAIRS = createBlock("grass_turf_stairs", new GrassTurfStairsBlock(GRASS_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_TURF_SLAB = createBlock("grass_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_CARPET = createBlock("grass_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.1F).sounds(BlockSoundGroup.GRASS)));

	public static final Block MYCELIUM_TURF = createBlock("mycelium_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_TURF_STAIRS = createBlock("mycelium_turf_stairs", new GrassTurfStairsBlock(MYCELIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_TURF_SLAB = createBlock("mycelium_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_CARPET = createBlock("mycelium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block CRIMSON_NYLIUM_TURF = createBlock("crimson_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_TURF_STAIRS = createBlock("crimson_turf_stairs", new GrassTurfStairsBlock(CRIMSON_NYLIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_TURF_SLAB = createBlock("crimson_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_CARPET = createBlock("crimson_nylium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.DULL_RED).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block WARPED_NYLIUM_TURF = createBlock("warped_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_TURF_STAIRS = createBlock("warped_turf_stairs", new GrassTurfStairsBlock(WARPED_NYLIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_TURF_SLAB = createBlock("warped_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_CARPET = createBlock("warped_nylium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TEAL).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	// HOT THINGS
	// TODO: Sandflow

	// COLD THINGS
	public static final Block PERMAFROST = createBlock("permafrost", new Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.LIGHT_GRAY)));
	public static final Block PERMAFROST_STAIRS = createBlock("permafrost_stairs", new MBStairsBlock(PACKED_DIRT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_SLAB = createBlock("permafrost_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_WALL = createBlock("permafrost_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block PERMAFROST_BRICKS = createBlock("permafrost_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_STAIRS = createBlock("permafrost_brick_stairs", new MBStairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_SLAB = createBlock("permafrost_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_WALL = createBlock("permafrost_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block SNOW_BRICKS = createBlock("snow_bricks", new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_STAIRS = createBlock("snow_brick_stairs", new MBStairsBlock(SNOW_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_SLAB = createBlock("snow_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_WALL = createBlock("snow_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));

	// TODO: Icicle?
	public static final Block PACKED_ICE_BRICKS = createBlock("packed_ice_bricks", new Block(AbstractBlock.Settings.copy(Blocks.PACKED_ICE)));
	public static final Block BLUE_ICE_BRICKS = createBlock("blue_ice_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BLUE_ICE)));
	// TODO: Black Ice

	public static final Block BEARD_MOSS_BLOCK = createBlock("beard_moss_block", new Block(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK)));
	public static final Block BEARD_MOSS_CARPET = createBlock("beard_moss_carpet", new CarpetBlock(AbstractBlock.Settings.copy(Blocks.MOSS_CARPET)));
	// TODO: Hanging & Wall Beard Moss

	// DIRT "ORES"
	public static final Block FLINT_DEPOSIT = createBlock("flint_deposit", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PEAT_MOSS = createBlock("peat_moss", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DEEP_ROOTED_SOIL = createBlock("deep_rooted_soil", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block MYCELIAL_DIRT = createBlock("mycelial_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DECOMPOSING_DIRT = createBlock("decomposing_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block FUZZ_BLOCK = createBlock("fuzz_block", new Block(AbstractBlock.Settings.copy(Blocks.DIRT).strength(1.0f, 3f).mapColor(MapColor.RAW_IRON_PINK)));

	public static final Block FUZZ_BRICKS = createBlock("fuzz_bricks", new Block(AbstractBlock.Settings.copy(FUZZ_BLOCK)));
	public static final Block FUZZ_BRICK_STAIRS = createBlock("fuzz_brick_stairs", new MBStairsBlock(FUZZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(FUZZ_BRICKS)));
	public static final Block FUZZ_BRICK_SLAB = createBlock("fuzz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(FUZZ_BRICKS)));
	public static final Block FUZZ_BRICK_WALL = createBlock("fuzz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(FUZZ_BRICKS)));

	public static final Block FLINT_BLOCK = createBlock("flint_block", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BLACK)));
	public static final Block SMOOTH_FLINT = createBlock("smooth_flint", new Block(AbstractBlock.Settings.copy(FLINT_BLOCK)));
	public static final Block SMOOTH_FLINT_STAIRS = createBlock("smooth_flint_stairs", new MBStairsBlock(SMOOTH_FLINT.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_FLINT)));
	public static final Block SMOOTH_FLINT_SLAB = createBlock("smooth_flint_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_FLINT)));
	public static final Block FLINT_PILLAR = createBlock("flint_pillar", new Block(AbstractBlock.Settings.copy(SMOOTH_FLINT)));

	public static final Block PEAT_BLOCK = createBlock("peat_moss", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PEAT_BRICKS = createBlock("peat_bricks", new Block(AbstractBlock.Settings.copy(PEAT_BLOCK)));
	public static final Block PEAT_BRICK_STAIRS = createBlock("peat_brick_stairs", new MBStairsBlock(PEAT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PEAT_BRICKS)));
	public static final Block PEAT_BRICK_SLAB = createBlock("peat_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PEAT_BRICKS)));
	public static final Block PEAT_BRICK_WALL = createBlock("peat_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PEAT_BRICKS)));

	// MANUFACTURED BLOCKS HERE I GUESS?
	public static final Block FIBER_THATCH = createBlock("fiber_thatch", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_LIGHT_GRAY)
				.strength(0.7f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block FIBER_THATCH_STAIRS = createBlock("fiber_thatch_stairs", new MBStairsBlock(FIBER_THATCH.getDefaultState(), AbstractBlock.Settings.copy(FIBER_THATCH)));
	public static final Block FIBER_THATCH_SLAB = createBlock("fiber_thatch_slab", new SlabBlock(AbstractBlock.Settings.copy(FIBER_THATCH)));

	// PLANTS
	public static final Block BEACHGRASS = createBlock("beachgrass", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_BEACHGRASS = createBlock("tall_beachgrass", new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));

	public static final Block DESERT_BRUSH = createBlock("desert_brush", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_DESERT_BRUSH = createBlock("tall_desert_brush", new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));

	public static final Block COTTONGRASS = createBlock("cottongrass", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_COTTONGRASS = createBlock("tall_cottongrass", new TallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

	public static final Block SOURSOBS = createBlock("soursobs", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_SOURSOBS = itemlessBlock("potted_soursobs", new FlowerPotBlock(SOURSOBS, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
	// small flowers
	public static final Block MARIGOLD = createBlock("marigold", new SandyFlowerBlock(StatusEffects.POISON, 12, AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_MARIGOLD = itemlessBlock("potted_marigold", new FlowerPotBlock(MARIGOLD, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	public static final Block FROSTY_HEATHER = createBlock("frosty_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_FROSTY_HEATHER = itemlessBlock("potted_frosty_heather", new FlowerPotBlock(FROSTY_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	public static final Block SUNSET_HEATHER = createBlock("sunset_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_SUNSET_HEATHER = itemlessBlock("potted_sunset_heather", new FlowerPotBlock(SUNSET_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	public static final Block TWILIGHT_HEATHER = createBlock("twilight_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_TWILIGHT_HEATHER = itemlessBlock("potted_twilight_heather", new FlowerPotBlock(TWILIGHT_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	// 2-tall
	public static final Block YUCCA = createBlock("yucca", new TallFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block LUPINE = createBlock("lupine", new TallFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

	// crops
	// TODO: Peanuts

	// unique
	public static final Block CAVEBLOOM_FLOWERS = createBlock("cavebloom_flowers", new CavebloomFlowerBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS)));
	public static final Block CAVEBLOOM_VINE = itemlessBlock("cavebloom_vine", new CavebloomVineBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS)));

	public static final Block TINY_BARREL_CACTUS = createBlock("tiny_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.TINY, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block SMALL_BARREL_CACTUS = createBlock("small_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.SMALL, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block BARREL_CACTUS = createBlock("barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.MEDIUM, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block LARGE_BARREL_CACTUS = createBlock("large_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.LARGE, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));

	// TODO: Hardy Fern blocks
	//public static final Block PARASOL_PUP = new PupBlock(() -> MBTreeFeatures.PARASOL_FERN, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	// TODO: fiber weed thing idk what to call it

	public static final Block BRITTLEBUSH_LEAVES = createBlock("brittlebush_leaves", new BrittlebushLeavesBlock(AbstractBlock.Settings.copy(Blocks.AZALEA).ticksRandomly()));
	public static final Block BRITTLEBUSH_FLOWERS = createBlock("brittlebush_flowers",
			new BrittlebushFlowersBlock(StatusEffects.WEAKNESS, 3, AbstractBlock.Settings.copy(Blocks.AZALEA).noCollision()));

	// TODO: Prickly Pear

	public static final Block OCOTILLO = createBlock("ocotillo", new OcotilloBlock(OcotilloBlock.Stage.BARE, AbstractBlock.Settings.copy(Blocks.AZALEA)));
	public static final Block FLOWERING_OCOTILLO = createBlock("flowering_ocotillo", new OcotilloBlock(OcotilloBlock.Stage.FLOWERING, AbstractBlock.Settings.copy(Blocks.AZALEA)));

	// SHROOMS
	public static final Block SAFFRON_MUSHROOM = createBlock("saffron_mushroom",
			new MushroomPlantBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).noCollision().ticksRandomly().breakInstantly()
					.sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ).postProcess((state, world, pos) -> true), () -> MBTreeFeatures.SAFFRON_MUSHROOM));
	public static final Block POTTED_SAFFRON_MUSHROOM = itemlessBlock("potted_saffron_mushroom",
			new FlowerPotBlock(SAFFRON_MUSHROOM, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	// TODO: Fuzz Shrooms

	public static final Block RED_MUSHROOM_CAP = createBlock("red_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.RED).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block BROWN_MUSHROOM_CAP = createBlock("brown_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.BROWN).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block SAFFRON_MUSHROOM_CAP = createBlock("saffron_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));

	public static final Block MUSHROOM_STEM = createBlock("mushroom_stem",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block MUSHROOM_HYPHAE = createBlock("mushroom_hyphae",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block STRIPPED_MUSHROOM_STEM = createBlock("stripped_mushroom_stem",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block STRIPPED_MUSHROOM_HYPHAE = createBlock("stripped_mushroom_hyphae",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));

	public static final Block SAFFRON_GILLS = createBlock("saffron_gills",
			new MushroomGillBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.GRASS).breakInstantly().nonOpaque().noCollision()));

	// OTHER LAND STUFF
	public static final Block PEBBLES = createBlock("pebbles", new PebbleBlock(AbstractBlock.Settings.of(Material.STONE).noCollision().breakInstantly().sounds(BlockSoundGroup.TUFF)));

	// STORAGE BLOCKS
	public static final Block APPLE_CRATE = createBlock("apple_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARROT_CRATE = createBlock("carrot_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block POTATO_CRATE = createBlock("potato_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block BEETROOT_CRATE = createBlock("beetroot_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));

	public static final Block EGG_BASKET = createBlock("egg_basket", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(MBSounds.SACK)));
	public static final Block COCOA_SACK = createBlock("cocoa_sack", new Block(QuiltBlockSettings.of(Material.WOOL).strength(2.0F,3.0F).sounds(MBSounds.SACK)));

	public static final Block GLISTERING_MELON_BLOCK = createBlock("glistering_melon_block",
			new Block(QuiltBlockSettings.of(Material.GOURD).hardness(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 12)));

	public static final Block SWEET_BERRY_BASKET = createBlock("sweet_berry_basket", new Block(QuiltBlockSettings.of(Material.WOOD).strength(0.5F).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block GLOW_BERRY_BASKET = createBlock("glow_berry_basket",
			new Block(QuiltBlockSettings.of(Material.WOOD).strength(0.5F).sounds(BlockSoundGroup.NYLIUM).luminance((state) -> 12)));

	public static final Block SUGAR_CANE_BUNDLE = createBlock("sugar_cane_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block BAMBOO_BUNDLE = createBlock("bamboo_bundle", new PillarBlock(QuiltBlockSettings.of(Material.BAMBOO).hardness(1.0f).sounds(BlockSoundGroup.SCAFFOLDING)));
	public static final Block OCOTILLO_BUNDLE = createBlock("ocotillo_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(1.0f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block CHORUS_BUNDLE = createBlock("chorus_bundle", new PillarBlock(QuiltBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block KELP_BLOCK = createBlock("kelp_block",
			new Block(QuiltBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F, 2.5F).sounds(BlockSoundGroup.WET_GRASS)));

	public static final Block NETHER_WART_SACK = createBlock("nether_wart_sack", new Block(QuiltBlockSettings.of(Material.NETHER_SHOOTS).hardness(1.0f).sounds(BlockSoundGroup.WART_BLOCK)));

	public static final Block SUGAR_CUBE = createBlock("sugar_cube", new FallingBlock(QuiltBlockSettings.of(Material.SOIL).strength(2.0F,3.0F).sounds(BlockSoundGroup.SAND)));
	public static final Block PACKED_GLOWSTONE = createBlock("packed_glowstone",
			new Block(QuiltBlockSettings.of(Material.GLASS).strength(2.0F,3.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> 15)));
	public static final Block GUNPOWDER_CRATE = createBlock("gunpowder_crate",
			new GunpowderBarrelBlock(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));

	public static final Block SAP_BLOCK = createBlock("sap_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK)));
	public static final Block SYRUP_BLOCK = createBlock("syrup_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK).dynamicBounds()));
	public static final Block RESIN_BLOCK = createBlock("resin_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK)));

	public static final Block SPOOL = createBlock("spool", new PillarBlock(QuiltBlockSettings.of(Material.WOOL).hardness(0.8f).sounds(BlockSoundGroup.WOOL)));
	public static final Block PAPER_BUNDLE = createBlock("paper_bundle", new PapersBlock(QuiltBlockSettings.of(Material.WOOL).hardness(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block STICK_STACK = createBlock("stick_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block CHARCOAL_LOG = createBlock("charcoal_log",
			new PillarBlock(QuiltBlockSettings.of(Material.WOOD).strength(1.2f, 0.8f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));

	public static final Block SCUTE_BLOCK = createBlock("scute_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.STONE)));

	public static final Block ROTTEN_FLESH_BLOCK = createBlock("rotten_flesh_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES)));
	public static final Block BONE_BUNDLE = createBlock("bone_bundle", new PillarBlock(QuiltBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block SPIDER_EYE_BLOCK = createBlock("spider_eye_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES)));
	public static final Block PHANTOM_MEMBRANE_BLOCK = createBlock("phantom_membrane_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(1f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block ENDER_PEARL_BLOCK = createBlock("ender_pearl_block", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1f).sounds(BlockSoundGroup.COPPER)));

	public static final Block BLAZE_ROD = itemlessBlock("blaze_rod_placed",
			new MBRodBlock(QuiltBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15)));
	public static final Block BLAZE_ROD_BUNDLE = createBlock("blaze_rod_bundle",
			new PillarBlock(QuiltBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15)));

	// FUNCTIONAL BLOCKS
	public static final Block ROPE_LADDER = createBlock("rope_ladder",
			new RopeLadderBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(0.4F).sounds(BlockSoundGroup.LADDER).nonOpaque()));
	public static final Block TIN_LADDER = createBlock("tin_ladder",
			new IronLadderBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(1.0F).sounds(BlockSoundGroup.LADDER).nonOpaque()));

	public static final Block WALL_LANTERN = itemlessBlock("wall_lantern",
			new WallLanternBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
			.luminance(state -> 15).nonOpaque()));
	public static final Block WALL_SOUL_LANTERN = itemlessBlock("wall_soul_lantern",
			new WallLanternBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
			.luminance(state -> 10).nonOpaque()));

	public static final Block BEDROLL = itemlessBlock("bedroll", new BedrollBlock(DyeColor.BROWN,
			AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
	// TODO: Bedroll item in MBItems

	// SEATS
	public static final Block WHITE_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED).strength(1.2f).mapColor(MapColor.WHITE).nonOpaque());
	public static final Block LIGHT_GRAY_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIGHT_GRAY));
	public static final Block GRAY_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.GRAY));
	public static final Block BLACK_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BLACK));
	public static final Block BROWN_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BROWN));
	public static final Block RED_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.RED));
	public static final Block ORANGE_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.ORANGE));
	public static final Block YELLOW_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.YELLOW));
	public static final Block LIME_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIME));
	public static final Block GREEN_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.GREEN));
	public static final Block CYAN_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.CYAN));
	public static final Block LIGHT_BLUE_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIGHT_BLUE));
	public static final Block BLUE_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BLUE));
	public static final Block PURPLE_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.PURPLE));
	public static final Block MAGENTA_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.MAGENTA));
	public static final Block PINK_CUSHION = new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.PINK));

	public static final Block KILN = createBlock("kiln", new KilnBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_ORANGE).strength(1.2f).sounds(BlockSoundGroup.STONE)));

	// TAPS
	public static final Block TREE_TAP = createBlock("tree_tap", new TreeTapBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
	public static final Block SAP_TREE_TAP = itemlessBlock("sap_tree_tap", new FilledTreeTapBlock(false, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	public static final Block SYRUP_TREE_TAP = itemlessBlock("syrup_tree_tap", new FilledTreeTapBlock(true, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	public static final Block RESIN_TREE_TAP = itemlessBlock("resin_tree_tap", new FilledTreeTapBlock(false, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	// TODO: Pitch Tap?

	// TODO: Incenses

	// TODO: Rock (the loot one) + Outcrops (deepslate ver.)

	// LOOT POTS
	public static final Block RABBIT_MOUND = createBlock("rabbit_mound", new DirtMoundBlock(AbstractBlock.Settings.copy(Blocks.DIRT).strength(0.3f)));
	public static final Block DESERT_VASE = createBlock("desert_vase", new VaseBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).sounds(MBSounds.CERAMIC)));
//	public static final Block DESERT_VASE_REPLICA = new VaseBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).sounds(MBSounds.CERAMIC));
//	public static final Block UNFIRED_DESERT_VASE = new UnfiredVaseBlock(DESERT_VASE_REPLICA, AbstractBlock.Settings.copy(Blocks.CLAY));
	public static final Block MUD_VESSEL = createBlock("mud_vessel", new VaseBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD).sounds(MBSounds.CERAMIC)));
//	public static final Block MUD_VESSEL_REPLICA = new VaseBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD).sounds(MBSounds.CERAMIC));

	// TODO: Rabbit Idol

	// TODO: Magnet


	public static void init() {}

	public static Block createBlock(String block_id, Block block) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new BlockItem(block, new Item.Settings().group(MBItemGroup.DEBUGGING)));
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
	}
	public static Block createBlock(String block_id, Block block, ItemGroup group) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new BlockItem(block, new Item.Settings().group(group)));
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
	}
	public static Block itemlessBlock(String block_id, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
	}
}
