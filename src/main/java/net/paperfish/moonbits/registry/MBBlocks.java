package net.paperfish.moonbits.registry;

import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.block.DirtMoundBlock;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;
import net.paperfish.moonbits.block.extended.*;
import net.paperfish.moonbits.mixin.SignTypeAccessor;
import net.paperfish.moonbits.world.feature.*;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@SuppressWarnings("unused")
public class MBBlocks {
	public static Map<String, Block> BLOCK_ITEMS = new LinkedHashMap<>();

	// VANILLA WOODS
	public static final Block OAK_BOARDS = createWithItem("oak_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_MOSAIC_STAIRS = createWithItem("oak_mosaic_stairs", new MBStairsBlock(OAK_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(OAK_BOARDS)));
	public static final Block OAK_MOSAIC_SLAB = createWithItem("oak_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(OAK_BOARDS)));

	public static final Block OAK_PANEL = createWithItem("oak_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_OAK = createWithItem("carved_oak_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_PILLAR = createWithItem("oak_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block OAK_PLANTER_BOX = createWithItem("oak_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block SPRUCE_BOARDS = createWithItem("spruce_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_MOSAIC_STAIRS = createWithItem("spruce_mosaic_stairs", new MBStairsBlock(SPRUCE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(SPRUCE_BOARDS)));
	public static final Block SPRUCE_MOSAIC_SLAB = createWithItem("spruce_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(SPRUCE_BOARDS)));

	public static final Block SPRUCE_PANEL = createWithItem("spruce_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_SPRUCE = createWithItem("carved_spruce_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_PILLAR = createWithItem("spruce_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block SPRUCE_PLANTER_BOX = createWithItem("spruce_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block BIRCH_BOARDS = createWithItem("birch_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_MOSAIC_STAIRS = createWithItem("birch_mosaic_stairs", new MBStairsBlock(BIRCH_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(BIRCH_BOARDS)));
	public static final Block BIRCH_MOSAIC_SLAB = createWithItem("birch_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(BIRCH_BOARDS)));

	public static final Block BIRCH_PANEL = createWithItem("birch_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_BIRCH = createWithItem("carved_birch_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_PILLAR = createWithItem("birch_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block BIRCH_PLANTER_BOX = createWithItem("birch_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block JUNGLE_BOARDS = createWithItem("jungle_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_MOSAIC_STAIRS = createWithItem("jungle_mosaic_stairs", new MBStairsBlock(JUNGLE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(JUNGLE_BOARDS)));
	public static final Block JUNGLE_MOSAIC_SLAB = createWithItem("jungle_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(JUNGLE_BOARDS)));

	public static final Block JUNGLE_PANEL = createWithItem("jungle_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_JUNGLE = createWithItem("carved_jungle_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_PILLAR = createWithItem("jungle_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block JUNGLE_PLANTER_BOX = createWithItem("jungle_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block ACACIA_BOARDS = createWithItem("acacia_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_MOSAIC_STAIRS = createWithItem("acacia_mosaic_stairs", new MBStairsBlock(ACACIA_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(ACACIA_BOARDS)));
	public static final Block ACACIA_MOSAIC_SLAB = createWithItem("acacia_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(ACACIA_BOARDS)));

	public static final Block ACACIA_PANEL = createWithItem("acacia_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_ACACIA = createWithItem("carved_acacia_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_PILLAR = createWithItem("acacia_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block ACACIA_PLANTER_BOX = createWithItem("acacia_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block DARK_OAK_BOARDS = createWithItem("dark_oak_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_MOSAIC_STAIRS = createWithItem("dark_oak_mosaic_stairs", new MBStairsBlock(DARK_OAK_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(DARK_OAK_BOARDS)));
	public static final Block DARK_OAK_MOSAIC_SLAB = createWithItem("dark_oak_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(DARK_OAK_BOARDS)));

	public static final Block DARK_OAK_PANEL = createWithItem("dark_oak_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_DARK_OAK = createWithItem("carved_dark_oak_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_PILLAR = createWithItem("dark_oak_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block DARK_OAK_PLANTER_BOX = createWithItem("dark_oak_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block MANGROVE_BOARDS = createWithItem("mangrove_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_MOSAIC_STAIRS = createWithItem("mangrove_mosaic_stairs", new MBStairsBlock(MANGROVE_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(MANGROVE_BOARDS)));
	public static final Block MANGROVE_MOSAIC_SLAB = createWithItem("mangrove_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(MANGROVE_BOARDS)));

	public static final Block MANGROVE_PANEL = createWithItem("mangrove_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_MANGROVE = createWithItem("carved_mangrove_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_PILLAR = createWithItem("mangrove_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block MANGROVE_PLANTER_BOX = createWithItem("mangrove_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block MANGROVE_WEAVE = createWithItem("mangrove_weave", new Block(AbstractBlock.Settings.copy(Blocks.MANGROVE_ROOTS).strength(1.0f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block MANGROVE_WEAVE_STAIRS = createWithItem("mangrove_weave_stairs", new MBStairsBlock(MANGROVE_WEAVE.getDefaultState(), AbstractBlock.Settings.copy(MANGROVE_WEAVE)));
	public static final Block MANGROVE_WEAVE_SLAB = createWithItem("mangrove_weave_slab", new SlabBlock(AbstractBlock.Settings.copy(MANGROVE_WEAVE)));

	public static final Block CRIMSON_BOARDS = createWithItem("crimson_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_MOSAIC_STAIRS = createWithItem("crimson_mosaic_stairs", new MBStairsBlock(CRIMSON_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(CRIMSON_BOARDS)));
	public static final Block CRIMSON_MOSAIC_SLAB = createWithItem("crimson_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(CRIMSON_BOARDS)));

	public static final Block CRIMSON_PANEL = createWithItem("crimson_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_CRIMSON = createWithItem("carved_crimson_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_PILLAR = createWithItem("crimson_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CRIMSON_PLANTER_BOX = createWithItem("crimson_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block WARPED_BOARDS = createWithItem("warped_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_MOSAIC_STAIRS = createWithItem("warped_mosaic_stairs", new MBStairsBlock(WARPED_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(WARPED_BOARDS)));
	public static final Block WARPED_MOSAIC_SLAB = createWithItem("warped_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(WARPED_BOARDS)));

	public static final Block WARPED_PANEL = createWithItem("warped_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_WARPED = createWithItem("carved_warped_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_PILLAR = createWithItem("warped_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WARPED_PLANTER_BOX = createWithItem("warped_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	// LAMPROOT
	public static final Block LAMPROOT_LOG = createWithItem("lamproot_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_WOOD = createWithItem("lamproot_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_LAMPROOT_LOG = createWithItem("stripped_lamproot_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_LAMPROOT_WOOD = createWithItem("stripped_lamproot_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block LAMPROOT_PLANKS = createWithItem("lamproot_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
	public static final Block LAMPROOT_STAIRS = createWithItem("lamproot_stairs", new MBStairsBlock(LAMPROOT_PLANKS.getDefaultState(),
			QuiltBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_SLAB = createWithItem("lamproot_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

	public static final Block LAMPROOT_FENCE = createWithItem("lamproot_fence", new FenceBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS)));
	public static final Block LAMPROOT_FENCE_GATE = createWithItem("lamproot_fence_gate", new FenceGateBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS)));
	public static final Block LAMPROOT_DOOR = createWithItem("lamproot_door", new MBDoorBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).nonOpaque()));
	public static final Block LAMPROOT_TRAPDOOR = createWithItem("lamproot_trapdoor", new MBTrapdoorBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).nonOpaque()));

	public static final Block LAMPROOT_BUTTON = createWithItem("lamproot_button", new MBWoodenButtonBlock(QuiltBlockSettings.copy(LAMPROOT_PLANKS).noCollision()));
	public static final Block LAMPROOT_PRESSURE_PLATE = createWithItem("lamproot_pressure_plate",
			new MBPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, QuiltBlockSettings.copy(LAMPROOT_PLANKS).noCollision()));

	public static final Block LAMPROOT_BOARDS = createWithItem("lamproot_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_MOSAIC_STAIRS = createWithItem("lamproot_mosaic_stairs", new MBStairsBlock(LAMPROOT_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(LAMPROOT_BOARDS)));
	public static final Block LAMPROOT_MOSAIC_SLAB = createWithItem("lamproot_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(LAMPROOT_BOARDS)));

	public static final Block LAMPROOT_PANEL = createWithItem("lamproot_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_LAMPROOT = createWithItem("carved_lamproot_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LAMPROOT_PILLAR = createWithItem("lamproot_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block LAMPROOT_BULB = createWithItem("lamproot_bulb",
			new LamprootBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance((state) -> 7)));

	public static final Block LAMPROOT_PLANTER_BOX = createWithItem("lamproot_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final SignType LAMPROOT_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("lamproot"));
	public static final Block LAMPROOT_SIGN = createWithItem("lamproot_sign",new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), LAMPROOT_SIGN_TYPE));
	public static final Block LAMPROOT_WALL_SIGN = registerBlock("lamproot_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(LAMPROOT_SIGN), LAMPROOT_SIGN_TYPE));

	// CEDAR
	public static final Block CEDAR_LOG = createWithItem("cedar_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_WOOD = createWithItem("cedar_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_CEDAR_LOG = createWithItem("stripped_cedar_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block STRIPPED_CEDAR_WOOD = createWithItem("stripped_cedar_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block CEDAR_PLANKS = createWithItem("cedar_planks", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
	public static final Block CEDAR_STAIRS = createWithItem("cedar_stairs", new MBStairsBlock(CEDAR_PLANKS.getDefaultState(),
			QuiltBlockSettings.of(Material.WOOD).hardness(0.6f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_SLAB = createWithItem("cedar_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB)));

	public static final Block CEDAR_FENCE = createWithItem("cedar_fence", new FenceBlock(QuiltBlockSettings.copy(CEDAR_PLANKS)));
	public static final Block CEDAR_FENCE_GATE = createWithItem("cedar_fence_gate", new FenceGateBlock(QuiltBlockSettings.copy(CEDAR_PLANKS)));
	public static final Block CEDAR_DOOR = createWithItem("cedar_door", new MBDoorBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).nonOpaque()));
	public static final Block CEDAR_TRAPDOOR = createWithItem("cedar_trapdoor", new MBTrapdoorBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).nonOpaque()));

	public static final Block CEDAR_BUTTON = createWithItem("cedar_button", new MBWoodenButtonBlock(QuiltBlockSettings.copy(CEDAR_PLANKS).noCollision()));
	public static final Block CEDAR_PRESSURE_PLATE = createWithItem("cedar_pressure_plate",
			new MBPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, QuiltBlockSettings.copy(CEDAR_PLANKS).noCollision()));

	public static final Block CEDAR_BOARDS = createWithItem("cedar_mosaic", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_MOSAIC_STAIRS = createWithItem("cedar_mosaic_stairs", new MBStairsBlock(CEDAR_BOARDS.getDefaultState(), AbstractBlock.Settings.copy(CEDAR_BOARDS)));
	public static final Block CEDAR_MOSAIC_SLAB = createWithItem("cedar_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(CEDAR_BOARDS)));

	public static final Block CEDAR_PANEL = createWithItem("cedar_panel", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARVED_CEDAR = createWithItem("carved_cedar_wood", new Block(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));
	public static final Block CEDAR_PILLAR = createWithItem("cedar_pillar", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final Block CEDAR_LEAVES = createWithItem("cedar_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).sounds(BlockSoundGroup.GRASS)));
	public static final Block CEDAR_SAPLING = createWithItem("cedar_sapling", new MBSaplingBlock(new CedarSaplingGenerator(), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
	public static final Block POTTED_CEDAR_SAPLING = registerBlock("potted_cedar_sapling",
			new FlowerPotBlock(CEDAR_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	public static final Block CEDAR_PLANTER_BOX = createWithItem("cedar_planter_box", new PlanterBoxBlock(AbstractBlock.Settings.of(Material.WOOD).strength(1.5f).sounds(BlockSoundGroup.WOOD)));

	public static final SignType CEDAR_SIGN_TYPE = SignTypeAccessor.registerNew(SignTypeAccessor.newSignType("cedar"));
	public static final Block CEDAR_SIGN = createWithItem("cedar_sign", new SignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), CEDAR_SIGN_TYPE));
	public static final Block CEDAR_WALL_SIGN = registerBlock("cedar_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_PURPLE)
			.noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CEDAR_SIGN), CEDAR_SIGN_TYPE));

	// VANILLA STONES
	public static final Block CHISELED_PACKED_MUD = createWithItem("chiseled_packed_mud", new Block(QuiltBlockSettings.copy(Blocks.PACKED_MUD)));

	// STONE
	public static final Block STONE_PILLAR = createWithItem("stone_brick_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block SMOOTH_STONE_STAIRS = createWithItem("smooth_stone_stairs", new MBStairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
	public static final Block SMOOTH_STONE_WALL = createWithItem("smooth_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block STONE_TILES = createWithItem("stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block STONE_TILE_STAIRS = createWithItem("stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block STONE_TILE_SLAB = createWithItem("stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block STONE_TILE_WALL = createWithItem("stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block CRACKED_STONE_TILES = createWithItem("cracked_stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_STONE_TILE_STAIRS = createWithItem("cracked_stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block CRACKED_STONE_TILE_SLAB = createWithItem("cracked_stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block CRACKED_STONE_TILE_WALL = createWithItem("cracked_stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	public static final Block MOSSY_STONE_TILES = createWithItem("mossy_stone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_STONE_TILE_STAIRS = createWithItem("mossy_stone_tile_stairs", new MBStairsBlock(STONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block MOSSY_STONE_TILE_SLAB = createWithItem("mossy_stone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE)));
	public static final Block MOSSY_STONE_TILE_WALL = createWithItem("mossy_stone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

	// DEEPSLATE
	public static final Block SMOOTH_DEEPSLATE = createWithItem("smooth_deepslate", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_STAIRS = createWithItem("smooth_deepslate_stairs",
			new MBStairsBlock(SMOOTH_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_SLAB = createWithItem("smooth_deepslate_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block SMOOTH_DEEPSLATE_WALL = createWithItem("smooth_deepslate_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_COBBLED_DEEPSLATE = createWithItem("mossy_cobbled_deepslate", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_STAIRS = createWithItem("mossy_cobbled_deepslate_stairs",
			new MBStairsBlock(MOSSY_COBBLED_DEEPSLATE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_SLAB = createWithItem("mossy_cobbled_deepslate_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_COBBLED_DEEPSLATE_WALL = createWithItem("mossy_cobbled_deepslate_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_DEEPSLATE_BRICKS = createWithItem("mossy_deepslate_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_STAIRS = createWithItem("mossy_deepslate_brick_stairs",
			new MBStairsBlock(MOSSY_DEEPSLATE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_SLAB = createWithItem("mossy_deepslate_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_BRICK_WALL = createWithItem("mossy_deepslate_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block DEEPSLATE_PILLAR = createWithItem("deepslate_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	public static final Block MOSSY_DEEPSLATE_TILES = createWithItem("mossy_deepslate_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_STAIRS = createWithItem("mossy_deepslate_tile_stairs",
			new MBStairsBlock(MOSSY_DEEPSLATE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_SLAB = createWithItem("mossy_deepslate_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));
	public static final Block MOSSY_DEEPSLATE_TILE_WALL = createWithItem("mossy_deepslate_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE)));

	// BLACKSTONE
	public static final Block SMOOTH_BLACKSTONE = createWithItem("smooth_blackstone", new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_STAIRS = createWithItem("smooth_blackstone_stairs",
			new MBStairsBlock(SMOOTH_BLACKSTONE.getDefaultState(),AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_SLAB = createWithItem("smooth_blackstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block SMOOTH_BLACKSTONE_WALL = createWithItem("smooth_blackstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	public static final Block GILDED_BLACKSTONE_BRICKS = createWithItem("gilded_blackstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_STAIRS = createWithItem("gilded_blackstone_brick_stairs",
			new MBStairsBlock(GILDED_BLACKSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_SLAB = createWithItem("gilded_blackstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));
	public static final Block GILDED_BLACKSTONE_BRICK_WALL = createWithItem("gilded_blackstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	public static final Block BLACKSTONE_PILLAR = createWithItem("blackstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.BLACKSTONE)));

	// ANDESITE
	public static final Block COBBLED_ANDESITE = createWithItem("cobbled_andesite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_ANDESITE_STAIRS = createWithItem("cobbled_andesite_stairs",
			new MBStairsBlock(COBBLED_ANDESITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_ANDESITE_SLAB = createWithItem("cobbled_andesite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block COBBLED_ANDESITE_WALL = createWithItem("cobbled_andesite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_BRICKS = createWithItem("andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_BRICK_STAIRS = createWithItem("andesite_brick_stairs",
			new MBStairsBlock(ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_BRICK_SLAB = createWithItem("andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block ANDESITE_BRICK_WALL = createWithItem("andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_ANDESITE_BRICKS = createWithItem("cracked_andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_BRICK_STAIRS = createWithItem("cracked_andesite_brick_stairs",
			new MBStairsBlock(CRACKED_ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_BRICK_SLAB = createWithItem("cracked_andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block CRACKED_ANDESITE_BRICK_WALL = createWithItem("cracked_andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_ANDESITE_BRICKS = createWithItem("mossy_andesite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_BRICK_STAIRS = createWithItem("mossy_andesite_brick_stairs",
			new MBStairsBlock(MOSSY_ANDESITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_BRICK_SLAB = createWithItem("mossy_andesite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block MOSSY_ANDESITE_BRICK_WALL = createWithItem("mossy_andesite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_PILLAR = createWithItem("andesite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_ANDESITE = createWithItem("chiseled_andesite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block ANDESITE_TILES = createWithItem("andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_TILE_STAIRS = createWithItem("andesite_tile_stairs",
			new MBStairsBlock(ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block ANDESITE_TILE_SLAB = createWithItem("andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block ANDESITE_TILE_WALL = createWithItem("andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_ANDESITE_TILES = createWithItem("cracked_andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_TILE_STAIRS = createWithItem("cracked_andesite_tile_stairs",
			new MBStairsBlock(CRACKED_ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_ANDESITE_TILE_SLAB = createWithItem("cracked_andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block CRACKED_ANDESITE_TILE_WALL = createWithItem("cracked_andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_ANDESITE_TILES = createWithItem("mossy_andesite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_TILE_STAIRS = createWithItem("mossy_andesite_tile_stairs",
			new MBStairsBlock(MOSSY_ANDESITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_ANDESITE_TILE_SLAB = createWithItem("mossy_andesite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.ANDESITE)));
	public static final Block MOSSY_ANDESITE_TILE_WALL = createWithItem("mossy_andesite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// DIORITE
	public static final Block COBBLED_DIORITE = createWithItem("cobbled_diorite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_DIORITE_STAIRS = createWithItem("cobbled_diorite_stairs",
			new MBStairsBlock(COBBLED_DIORITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_DIORITE_SLAB = createWithItem("cobbled_diorite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block COBBLED_DIORITE_WALL = createWithItem("cobbled_diorite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_BRICKS = createWithItem("diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_BRICK_STAIRS = createWithItem("diorite_brick_stairs",
			new MBStairsBlock(DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_BRICK_SLAB = createWithItem("diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block DIORITE_BRICK_WALL = createWithItem("diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_DIORITE_BRICKS = createWithItem("cracked_diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_BRICK_STAIRS = createWithItem("cracked_diorite_brick_stairs",
			new MBStairsBlock(CRACKED_DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_BRICK_SLAB = createWithItem("cracked_diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block CRACKED_DIORITE_BRICK_WALL = createWithItem("cracked_diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_DIORITE_BRICKS = createWithItem("mossy_diorite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_BRICK_STAIRS = createWithItem("mossy_diorite_brick_stairs",
			new MBStairsBlock(MOSSY_DIORITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_BRICK_SLAB = createWithItem("mossy_diorite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block MOSSY_DIORITE_BRICK_WALL = createWithItem("mossy_diorite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_PILLAR = createWithItem("diorite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_DIORITE = createWithItem("chiseled_diorite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block DIORITE_TILES = createWithItem("diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_TILE_STAIRS = createWithItem("diorite_tile_stairs",
			new MBStairsBlock(DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block DIORITE_TILE_SLAB = createWithItem("diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block DIORITE_TILE_WALL = createWithItem("diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_DIORITE_TILES = createWithItem("cracked_diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_TILE_STAIRS = createWithItem("cracked_diorite_tile_stairs",
			new MBStairsBlock(CRACKED_DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_DIORITE_TILE_SLAB = createWithItem("cracked_diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block CRACKED_DIORITE_TILE_WALL = createWithItem("cracked_diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_DIORITE_TILES = createWithItem("mossy_diorite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_TILE_STAIRS = createWithItem("mossy_diorite_tile_stairs",
			new MBStairsBlock(MOSSY_DIORITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_DIORITE_TILE_SLAB = createWithItem("mossy_diorite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIORITE)));
	public static final Block MOSSY_DIORITE_TILE_WALL = createWithItem("mossy_diorite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// GRANITE
	public static final Block COBBLED_GRANITE = createWithItem("cobbled_granite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_GRANITE_STAIRS = createWithItem("cobbled_granite_stairs",
			new MBStairsBlock(COBBLED_GRANITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block COBBLED_GRANITE_SLAB = createWithItem("cobbled_granite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block COBBLED_GRANITE_WALL = createWithItem("cobbled_granite_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_BRICKS = createWithItem("granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_BRICK_STAIRS = createWithItem("granite_brick_stairs",
			new MBStairsBlock(GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_BRICK_SLAB = createWithItem("granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block GRANITE_BRICK_WALL = createWithItem("granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_GRANITE_BRICKS = createWithItem("cracked_granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_BRICK_STAIRS = createWithItem("cracked_granite_brick_stairs",
			new MBStairsBlock(CRACKED_GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_BRICK_SLAB = createWithItem("cracked_granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block CRACKED_GRANITE_BRICK_WALL = createWithItem("cracked_granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_GRANITE_BRICKS = createWithItem("mossy_granite_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_BRICK_STAIRS = createWithItem("mossy_granite_brick_stairs",
			new MBStairsBlock(MOSSY_GRANITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_BRICK_SLAB = createWithItem("mossy_granite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block MOSSY_GRANITE_BRICK_WALL = createWithItem("mossy_granite_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_PILLAR = createWithItem("granite_pillar", new PillarBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CHISELED_GRANITE = createWithItem("chiseled_granite", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block GRANITE_TILES = createWithItem("granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_TILE_STAIRS = createWithItem("granite_tile_stairs",
			new MBStairsBlock(GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block GRANITE_TILE_SLAB = createWithItem("granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block GRANITE_TILE_WALL = createWithItem("granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block CRACKED_GRANITE_TILES = createWithItem("cracked_granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_TILE_STAIRS = createWithItem("cracked_granite_tile_stairs",
			new MBStairsBlock(CRACKED_GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_GRANITE_TILE_SLAB = createWithItem("cracked_granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block CRACKED_GRANITE_TILE_WALL = createWithItem("cracked_granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	public static final Block MOSSY_GRANITE_TILES = createWithItem("mossy_granite_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_TILE_STAIRS = createWithItem("mossy_granite_tile_stairs",
			new MBStairsBlock(MOSSY_GRANITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_GRANITE_TILE_SLAB = createWithItem("mossy_granite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.GRANITE)));
	public static final Block MOSSY_GRANITE_TILE_WALL = createWithItem("mossy_granite_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));

	// SANDSTONE
	public static final Block SANDSTONE_BRICKS = createWithItem("sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_BRICK_STAIRS = createWithItem("sandstone_brick_stairs",
			new MBStairsBlock(SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_BRICK_SLAB = createWithItem("sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block SANDSTONE_BRICK_WALL = createWithItem("sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block PAVED_SANDSTONE_BRICKS = createWithItem("paved_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_SANDSTONE_BRICKS = createWithItem("cracked_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_BRICK_STAIRS = createWithItem("cracked_sandstone_brick_stairs",
			new MBStairsBlock(CRACKED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_BRICK_SLAB = createWithItem("cracked_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block CRACKED_SANDSTONE_BRICK_WALL = createWithItem("cracked_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_PAVED_SANDSTONE_BRICKS = createWithItem("cracked_paved_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block SANDSTONE_TILES = createWithItem("sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_TILE_STAIRS = createWithItem("sandstone_tile_stairs",
			new MBStairsBlock(SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block SANDSTONE_TILE_SLAB = createWithItem("sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block SANDSTONE_TILE_WALL = createWithItem("sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_SANDSTONE_TILES = createWithItem("cracked_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_TILE_STAIRS = createWithItem("cracked_sandstone_tile_stairs",
			new MBStairsBlock(CRACKED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_SANDSTONE_TILE_SLAB = createWithItem("cracked_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	public static final Block CRACKED_SANDSTONE_TILE_WALL = createWithItem("cracked_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block SANDSTONE_PILLAR = createWithItem("sandstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.SANDSTONE)));
	// RED SANDSTONE
	public static final Block RED_SANDSTONE_BRICKS = createWithItem("red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_BRICK_STAIRS = createWithItem("red_sandstone_brick_stairs",
			new MBStairsBlock(RED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_BRICK_SLAB = createWithItem("red_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block RED_SANDSTONE_BRICK_WALL = createWithItem("red_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block PAVED_RED_SANDSTONE_BRICKS = createWithItem("paved_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_RED_SANDSTONE_BRICKS = createWithItem("cracked_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_STAIRS = createWithItem("cracked_red_sandstone_brick_stairs",
			new MBStairsBlock(CRACKED_RED_SANDSTONE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_SLAB = createWithItem("cracked_red_sandstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block CRACKED_RED_SANDSTONE_BRICK_WALL = createWithItem("cracked_red_sandstone_brick_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_PAVED_RED_SANDSTONE_BRICKS = createWithItem("cracked_paved_red_sandstone_bricks", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block RED_SANDSTONE_TILES = createWithItem("red_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_TILE_STAIRS = createWithItem("red_sandstone_tile_stairs",
			new MBStairsBlock(RED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block RED_SANDSTONE_TILE_SLAB = createWithItem("red_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block RED_SANDSTONE_TILE_WALL = createWithItem("red_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block CRACKED_RED_SANDSTONE_TILES = createWithItem("cracked_red_sandstone_tiles", new Block(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_STAIRS = createWithItem("cracked_red_sandstone_tile_stairs",
			new MBStairsBlock(CRACKED_RED_SANDSTONE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_SLAB = createWithItem("cracked_red_sandstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));
	public static final Block CRACKED_RED_SANDSTONE_TILE_WALL = createWithItem("cracked_red_sandstone_tile_wall", new WallBlock(QuiltBlockSettings.of(Material.STONE).hardness(0.8f)));

	public static final Block RED_SANDSTONE_PILLAR = createWithItem("red_sandstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.RED_SANDSTONE)));

	// TUFF
	public static final Block TUFF_STAIRS = createWithItem("tuff_stairs", new MBStairsBlock(Blocks.TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_SLAB = createWithItem("tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_WALL = createWithItem("tuff_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block POLISHED_TUFF = createWithItem("polished_tuff", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_STAIRS = createWithItem("polished_tuff_stairs",
			new MBStairsBlock(POLISHED_TUFF.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_SLAB = createWithItem("polished_tuff_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block POLISHED_TUFF_WALL = createWithItem("polished_tuff_wall", new WallBlock((AbstractBlock.Settings.copy(Blocks.TUFF))));

	public static final Block TUFF_BRICKS = createWithItem("tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_STAIRS = createWithItem("tuff_brick_stairs",
			new MBStairsBlock(TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_SLAB = createWithItem("tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_BRICK_WALL = createWithItem("tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block CRACKED_TUFF_BRICKS = createWithItem("cracked_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_STAIRS = createWithItem("cracked_tuff_brick_stairs",
			new MBStairsBlock(CRACKED_TUFF_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_SLAB = createWithItem("cracked_tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_BRICK_WALL = createWithItem("cracked_tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block MOSSY_TUFF_BRICKS = createWithItem("mossy_tuff_bricks", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_BRICK_STAIRS = createWithItem("mossy_tuff_brick_stairs",
			new MBStairsBlock(MOSSY_TUFF_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_TUFF_BRICK_SLAB = createWithItem("mossy_tuff_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_BRICK_WALL = createWithItem("mossy_tuff_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block TUFF_PILLAR = createWithItem("tuff_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CHISELED_TUFF = createWithItem("chiseled_tuff", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block TUFF_TILES = createWithItem("tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_TILE_STAIRS = createWithItem("tuff_tile_stairs",
			new MBStairsBlock(TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block TUFF_TILE_SLAB = createWithItem("tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block TUFF_TILE_WALL = createWithItem("tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block CRACKED_TUFF_TILES = createWithItem("cracked_tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_TILE_STAIRS = createWithItem("cracked_tuff_tile_stairs",
			new MBStairsBlock(CRACKED_TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_TUFF_TILE_SLAB = createWithItem("cracked_tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block CRACKED_TUFF_TILE_WALL = createWithItem("cracked_tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	public static final Block MOSSY_TUFF_TILES = createWithItem("mossy_tuff_tiles", new Block(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_TILE_STAIRS = createWithItem("mossy_tuff_tile_stairs",
			new MBStairsBlock(MOSSY_TUFF_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block MOSSY_TUFF_TILE_SLAB = createWithItem("mossy_tuff_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));
	public static final Block MOSSY_TUFF_TILE_WALL = createWithItem("mossy_tuff_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.TUFF)));

	// CALCITE
	public static final Block CALCITE_STAIRS = createWithItem("calcite_stairs", new MBStairsBlock(Blocks.CALCITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_SLAB = createWithItem("calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_WALL = createWithItem("calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block POLISHED_CALCITE = createWithItem("polished_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block POLISHED_CALCITE_STAIRS = createWithItem("polished_calcite_stairs",
			new MBStairsBlock(POLISHED_CALCITE.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block POLISHED_CALCITE_SLAB = createWithItem("polished_calcite_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block POLISHED_CALCITE_WALL = createWithItem("polished_calcite_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_BRICKS = createWithItem("calcite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_BRICK_STAIRS = createWithItem("calcite_brick_stairs",
			new MBStairsBlock(CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_BRICK_SLAB = createWithItem("calcite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_BRICK_WALL = createWithItem("calcite_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CRACKED_CALCITE_BRICKS = createWithItem("cracked_calcite_bricks", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_BRICK_STAIRS = createWithItem("cracked_calcite_brick_stairs",
			new MBStairsBlock(CRACKED_CALCITE_BRICKS.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_CALCITE_BRICK_SLAB = createWithItem("cracked_calcite_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_BRICK_WALL = createWithItem("cracked_calcite_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_PILLAR = createWithItem("calcite_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CHISELED_CALCITE = createWithItem("chiseled_calcite", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CALCITE_TILES = createWithItem("calcite_tiles", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_TILE_STAIRS = createWithItem("calcite_tile_stairs",
			new MBStairsBlock(CALCITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CALCITE_TILE_SLAB = createWithItem("calcite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CALCITE_TILE_WALL = createWithItem("calcite_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	public static final Block CRACKED_CALCITE_TILES = createWithItem("cracked_calcite_tiles", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_TILE_STAIRS = createWithItem("cracked_calcite_tile_stairs",
			new MBStairsBlock(CRACKED_CALCITE_TILES.getDefaultState(), QuiltBlockSettings.of(Material.STONE).hardness(1.5f)));
	public static final Block CRACKED_CALCITE_TILE_SLAB = createWithItem("cracked_calcite_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CRACKED_CALCITE_TILE_WALL = createWithItem("cracked_calcite_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.CALCITE)));

	// DRIPSTONE
	public static final Block DRIPSTONE_STAIRS = createWithItem("dripstone_stairs", new MBStairsBlock(Blocks.DRIPSTONE_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_SLAB = createWithItem("dripstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_WALL = createWithItem("dripstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block POLISHED_DRIPSTONE = createWithItem("polished_dripstone", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_STAIRS = createWithItem("polished_dripstone_stairs",
			new MBStairsBlock(POLISHED_DRIPSTONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_SLAB = createWithItem("polished_dripstone_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block POLISHED_DRIPSTONE_WALL = createWithItem("polished_dripstone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_BRICKS = createWithItem("dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_STAIRS = createWithItem("dripstone_brick_stairs",
			new MBStairsBlock(DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_SLAB = createWithItem("dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_BRICK_WALL = createWithItem("dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block CRACKED_DRIPSTONE_BRICKS = createWithItem("cracked_dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_STAIRS = createWithItem("cracked_dripstone_brick_stairs",
			new MBStairsBlock(CRACKED_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_SLAB = createWithItem("cracked_dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_BRICK_WALL = createWithItem("cracked_dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block MOSSY_DRIPSTONE_BRICKS = createWithItem("mossy_dripstone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_STAIRS = createWithItem("mossy_dripstone_brick_stairs",
			new MBStairsBlock(MOSSY_DRIPSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_SLAB = createWithItem("mossy_dripstone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_BRICK_WALL = createWithItem("mossy_dripstone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_PILLAR = createWithItem("dripstone_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CHISELED_DRIPSTONE = createWithItem("chiseled_dripstone", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block DRIPSTONE_TILES = createWithItem("dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_STAIRS = createWithItem("dripstone_tile_stairs",
			new MBStairsBlock(DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_SLAB = createWithItem("dripstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block DRIPSTONE_TILE_WALL = createWithItem("dripstone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block CRACKED_DRIPSTONE_TILES = createWithItem("cracked_dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_STAIRS = createWithItem("cracked_dripstone_tiles_stairs",
			new MBStairsBlock(CRACKED_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_SLAB = createWithItem("cracked_dripstone_tiles_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block CRACKED_DRIPSTONE_TILE_WALL = createWithItem("cracked_dripstone_tiles_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	public static final Block MOSSY_DRIPSTONE_TILES = createWithItem("mossy_dripstone_tiles", new Block(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_STAIRS = createWithItem("mossy_dripstone_tile_stairs",
			new MBStairsBlock(MOSSY_DRIPSTONE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_SLAB = createWithItem("mossy_dripstone_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));
	public static final Block MOSSY_DRIPSTONE_TILE_WALL = createWithItem("mossy_dripstone_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DRIPSTONE_BLOCK)));

	// PRISMARINE
	public static final Block SMOOTH_PRISMARINE = createWithItem("smooth_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_STAIRS = createWithItem("smooth_prismarine_stairs",
			new MBStairsBlock(SMOOTH_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_SLAB = createWithItem("smooth_prismarine_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block SMOOTH_PRISMARINE_WALL = createWithItem("smooth_prismarine_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block POLISHED_PRISMARINE = createWithItem("polished_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_STAIRS = createWithItem("polished_prismarine_stairs",
			new MBStairsBlock(POLISHED_PRISMARINE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_SLAB = createWithItem("polished_prismarine_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block POLISHED_PRISMARINE_WALL = createWithItem("polished_prismarine_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block CRACKED_PRISMARINE_BRICKS = createWithItem("cracked_prismarine_bricks", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_STAIRS = createWithItem("cracked_prismarine_brick_stairs",
			new MBStairsBlock(CRACKED_PRISMARINE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_SLAB = createWithItem("cracked_prismarine_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_BRICK_WALL = createWithItem("cracked_prismarine_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block PRISMARINE_PILLAR = createWithItem("prismarine_pillar", new PillarBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CHISELED_PRISMARINE = createWithItem("chiseled_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CUT_PRISMARINE = createWithItem("cut_prismarine", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block PRISMARINE_TILES = createWithItem("prismarine_tiles", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_STAIRS = createWithItem("prismarine_tile_stairs",
			new MBStairsBlock(PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_SLAB = createWithItem("prismarine_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block PRISMARINE_TILE_WALL = createWithItem("prismarine_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	public static final Block CRACKED_PRISMARINE_TILES = createWithItem("cracked_prismarine_tiles", new Block(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_STAIRS = createWithItem("cracked_prismarine_tile_stairs",
			new MBStairsBlock(CRACKED_PRISMARINE_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_SLAB = createWithItem("cracked_prismarine_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));
	public static final Block CRACKED_PRISMARINE_TILE_WALL = createWithItem("cracked_prismarine_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.PRISMARINE)));

	// BASALT
	public static final Block BASALT_BRICKS = createWithItem("basalt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_STAIRS = createWithItem("basalt_brick_stairs",
			new MBStairsBlock(BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_SLAB = createWithItem("basalt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_BRICK_WALL = createWithItem("basalt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CRACKED_BASALT_BRICKS = createWithItem("cracked_basalt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_STAIRS = createWithItem("cracked_basalt_brick_stairs",
			new MBStairsBlock(CRACKED_BASALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_SLAB = createWithItem("cracked_basalt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_BRICK_WALL = createWithItem("cracked_basalt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block BASALT_TILES = createWithItem("basalt_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_STAIRS = createWithItem("basalt_tile_stairs",
			new MBStairsBlock(BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_SLAB = createWithItem("basalt_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block BASALT_TILE_WALL = createWithItem("basalt_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CRACKED_BASALT_TILES = createWithItem("cracked_basalt_tiles", new Block(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_STAIRS = createWithItem("cracked_basalt_tile_stairs",
			new MBStairsBlock(CRACKED_BASALT_TILES.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_SLAB = createWithItem("cracked_basalt_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block CRACKED_BASALT_TILE_WALL = createWithItem("cracked_basalt_tile_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block CHISELED_BASALT = createWithItem("chiseled_basalt", new PillarBlock(AbstractBlock.Settings.copy(Blocks.BASALT)));

	public static final Block SMOOTH_BASALT_STAIRS = createWithItem("smooth_basalt_stairs", new MBStairsBlock(Blocks.SMOOTH_BASALT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.BASALT)));
	public static final Block SMOOTH_BASALT_SLAB = createWithItem("smooth_basalt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));
	public static final Block SMOOTH_BASALT_WALL = createWithItem("smooth_basalt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_BASALT)));

	// CHERT
	public static final Block CHERT = createWithItem("chert", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.PALE_YELLOW)));
	public static final Block CHERT_STAIRS = createWithItem("chert_stairs", new MBStairsBlock(CHERT.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_SLAB = createWithItem("chert_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_WALL = createWithItem("chert_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block COBBLED_CHERT = createWithItem("cobbled_chert", new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE).mapColor(MapColor.PALE_YELLOW)));
	public static final Block COBBLED_CHERT_STAIRS = createWithItem("cobbled_chert_stairs", new MBStairsBlock(COBBLED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(COBBLED_CHERT)));
	public static final Block COBBLED_CHERT_SLAB = createWithItem("cobbled_chert_slab", new SlabBlock(AbstractBlock.Settings.copy(COBBLED_CHERT)));
	public static final Block COBBLED_CHERT_WALL = createWithItem("cobbled_chert_wall", new WallBlock(AbstractBlock.Settings.copy(COBBLED_CHERT)));

	public static final Block POLISHED_CHERT = createWithItem("polished_chert", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_STAIRS = createWithItem("polished_chert_stairs", new MBStairsBlock(POLISHED_CHERT.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_SLAB = createWithItem("polished_chert_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block POLISHED_CHERT_WALL = createWithItem("polished_chert_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_BRICKS = createWithItem("chert_bricks", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_STAIRS = createWithItem("chert_brick_stairs", new MBStairsBlock(CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_SLAB = createWithItem("chert_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_BRICK_WALL = createWithItem("chert_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CRACKED_CHERT_BRICKS = createWithItem("cracked_chert_bricks", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_STAIRS = createWithItem("cracked_chert_brick_stairs", new MBStairsBlock(CRACKED_CHERT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_SLAB = createWithItem("cracked_chert_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_BRICK_WALL = createWithItem("cracked_chert_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_PILLAR = createWithItem("chert_pillar", new PillarBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CUT_CHERT = createWithItem("cut_chert", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHISELED_CHERT = createWithItem("chiseled_chert", new Block(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_TILES = createWithItem("chert_tiles", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_STAIRS = createWithItem("chert_tile_stairs", new MBStairsBlock(CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_SLAB = createWithItem("chert_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CHERT_TILE_WALL = createWithItem("chert_tile_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CRACKED_CHERT_TILES = createWithItem("cracked_chert_tiles", new Block(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_STAIRS = createWithItem("cracked_chert_tile_stairs", new MBStairsBlock(CRACKED_CHERT_TILES.getDefaultState(), AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_SLAB = createWithItem("cracked_chert_tile_slab", new SlabBlock(AbstractBlock.Settings.copy(CHERT)));
	public static final Block CRACKED_CHERT_TILE_WALL = createWithItem("cracked_chert_tile_wall", new WallBlock(AbstractBlock.Settings.copy(CHERT)));

	public static final Block CHERT_COAL_ORE = createWithItem("chert_coal_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COAL_ORE), UniformIntProvider.create(0, 2)));
	public static final Block CHERT_COPPER_ORE = createWithItem("chert_copper_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE)));
	public static final Block CHERT_GOLD_ORE = createWithItem("chert_gold_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE)));
	public static final Block CHERT_DIAMOND_ORE = createWithItem("chert_diamond_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE), UniformIntProvider.create(3, 7)));
	public static final Block CHERT_REDSTONE_ORE = createWithItem("chert_redstone_ore", new RedstoneOreBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_ORE)));
	public static final Block CHERT_LAPIS_ORE = createWithItem("chert_lapis_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.LAPIS_ORE), UniformIntProvider.create(2, 5)));

	public static final Block BANDED_IRON = createWithItem("banded_iron", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE)));
	public static final Block MAGNETITE_ORE = createWithItem("magnetite_ore", new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE), UniformIntProvider.create(2, 5)));
	public static final Block MAGNETITE_BLOCK = createWithItem("magnetite_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));

	// JASPER
	// TODO: Jasper main block (the lil nose thing)
	public static final Block JASPER = createWithItem("jasper", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_ORANGE)));
	public static final Block JASPER_STAIRS = createWithItem("jasper_stairs", new MBStairsBlock(JASPER.getDefaultState(), AbstractBlock.Settings.copy(JASPER)));
	public static final Block JASPER_SLAB = createWithItem("jasper_slab", new SlabBlock(AbstractBlock.Settings.copy(JASPER)));
	public static final Block JASPER_WALL = createWithItem("jasper_wall", new WallBlock(AbstractBlock.Settings.copy(JASPER)));

	public static final Block POLISHED_JASPER = createWithItem("polished_jasper", new Block(AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_STAIRS = createWithItem("polished_jasper_stairs", new MBStairsBlock(POLISHED_JASPER.getDefaultState(), AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_SLAB = createWithItem("polished_jasper_slab", new SlabBlock(AbstractBlock.Settings.copy(JASPER)));
	public static final Block POLISHED_JASPER_WALL = createWithItem("polished_jasper_wall", new WallBlock(AbstractBlock.Settings.copy(JASPER)));

	public static final Block HELIODOR_ROD = createWithItem("heliodor_rod",
			new PillarBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.YELLOW).nonOpaque()));

	// CLAYSTONE
	public static final Block CLAYSTONE = createWithItem("claystone", new Block(AbstractBlock.Settings.copy(Blocks.CALCITE)));
	public static final Block CLAYSTONE_STAIRS = createWithItem("claystone_stairs", new MBStairsBlock(CLAYSTONE.getDefaultState(), AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_SLAB = createWithItem("claystone_slab", new SlabBlock(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_WALL = createWithItem("claystone_wall", new WallBlock(AbstractBlock.Settings.copy(CLAYSTONE)));

	public static final Block CLAYSTONE_BRICKS = createWithItem("claystone_bricks", new Block(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_STAIRS = createWithItem("claystone_brick_stairs", new MBStairsBlock(CLAYSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_SLAB = createWithItem("claystone_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(CLAYSTONE)));
	public static final Block CLAYSTONE_BRICK_WALL = createWithItem("claystone_brick_wall", new WallBlock(AbstractBlock.Settings.copy(CLAYSTONE)));

	// TODO: Cracklerock

	// AZURITE
	// TODO: Azurite main block
	public static final Block AZURITE = createWithItem("azurite", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.BLUE)));
	public static final Block AZURITE_STAIRS = createWithItem("azurite_stairs", new MBStairsBlock(AZURITE.getDefaultState(), AbstractBlock.Settings.copy(AZURITE)));
	public static final Block AZURITE_SLAB = createWithItem("azurite_slab", new SlabBlock(AbstractBlock.Settings.copy(AZURITE)));
	public static final Block AZURITE_WALL = createWithItem("azurite_wall", new WallBlock(AbstractBlock.Settings.copy(AZURITE)));

	public static final Block POLISHED_AZURITE = createWithItem("polished_azurite", new Block(AbstractBlock.Settings.copy(AZURITE)));
	public static final Block POLISHED_AZURITE_STAIRS = createWithItem("polished_azurite_stairs", new MBStairsBlock(POLISHED_AZURITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_AZURITE)));
	public static final Block POLISHED_AZURITE_SLAB = createWithItem("polished_azurite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_AZURITE)));
	public static final Block POLISHED_AZURITE_WALL = createWithItem("polished_azurite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_AZURITE)));

	public static final Block LARIMAR_ROD = createWithItem("larimar_rod",
			new PillarBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.DIAMOND_BLUE).nonOpaque()));

	// TODO: Boost ore, whatever that's called

	// METAL
	// Todo: Copper Blocks (Door, Trapdoor, maybe do bars and that other tiled one?)
	public static final Block COPPER_OXIDE_LANTERN = createWithItem("copper_oxide_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
	public static final Block COPPER_OXIDE_CAMPFIRE = createWithItem("copper_oxide_campfire", new CampfireBlock(true, 1, AbstractBlock.Settings.copy(Blocks.CAMPFIRE)));

	// SOIL
	public static final Block PACKED_DIRT = createWithItem("packed_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_STAIRS = createWithItem("packed_dirt_stairs", new MBStairsBlock(PACKED_DIRT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_SLAB = createWithItem("packed_dirt_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PACKED_DIRT_WALL = createWithItem("packed_dirt_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block DIRT_BRICKS = createWithItem("dirt_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_STAIRS = createWithItem("dirt_brick_stairs", new MBStairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_SLAB = createWithItem("dirt_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DIRT_BRICK_WALL = createWithItem("dirt_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block REGOLITH = createWithItem("regolith", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

	// TURF
	public static final Block GRASS_TURF = createWithItem("grass_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_TURF_STAIRS = createWithItem("grass_turf_stairs", new GrassTurfStairsBlock(GRASS_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_TURF_SLAB = createWithItem("grass_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.6f).sounds(BlockSoundGroup.GRASS)));
	public static final Block GRASS_CARPET = createWithItem("grass_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.1F).sounds(BlockSoundGroup.GRASS)));

	public static final Block MYCELIUM_TURF = createWithItem("mycelium_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_TURF_STAIRS = createWithItem("mycelium_turf_stairs", new GrassTurfStairsBlock(MYCELIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_TURF_SLAB = createWithItem("mycelium_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block MYCELIUM_CARPET = createWithItem("mycelium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.PURPLE).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block CRIMSON_NYLIUM_TURF = createWithItem("crimson_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_TURF_STAIRS = createWithItem("crimson_turf_stairs", new GrassTurfStairsBlock(CRIMSON_NYLIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_TURF_SLAB = createWithItem("crimson_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.DULL_RED).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block CRIMSON_NYLIUM_CARPET = createWithItem("crimson_nylium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.DULL_RED).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block WARPED_NYLIUM_TURF = createWithItem("warped_turf",
			new GrassTurfBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_TURF_STAIRS = createWithItem("warped_turf_stairs", new GrassTurfStairsBlock(WARPED_NYLIUM_TURF.getDefaultState(),
			QuiltBlockSettings.of(Material.SOLID_ORGANIC).hardness(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_TURF_SLAB = createWithItem("warped_turf_slab",
			new GrassTurfSlabBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.TEAL).strength(0.6f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block WARPED_NYLIUM_CARPET = createWithItem("warped_nylium_carpet",
			new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TEAL).strength(0.1F).sounds(BlockSoundGroup.NYLIUM)));

	// HOT THINGS
	// TODO: Sandflow

	// COLD THINGS
	public static final Block PERMAFROST = createWithItem("permafrost", new Block(AbstractBlock.Settings.copy(Blocks.DIRT).mapColor(MapColor.LIGHT_GRAY)));
	public static final Block PERMAFROST_STAIRS = createWithItem("permafrost_stairs", new MBStairsBlock(PACKED_DIRT.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_SLAB = createWithItem("permafrost_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_WALL = createWithItem("permafrost_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block PERMAFROST_BRICKS = createWithItem("permafrost_bricks", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_STAIRS = createWithItem("permafrost_brick_stairs", new MBStairsBlock(DIRT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_SLAB = createWithItem("permafrost_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PERMAFROST_BRICK_WALL = createWithItem("permafrost_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block SNOW_BRICKS = createWithItem("snow_bricks", new Block(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_STAIRS = createWithItem("snow_brick_stairs", new MBStairsBlock(SNOW_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_SLAB = createWithItem("snow_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));
	public static final Block SNOW_BRICK_WALL = createWithItem("snow_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SNOW_BLOCK)));

	// TODO: Icicle?
	public static final Block PACKED_ICE_BRICKS = createWithItem("packed_ice_bricks", new Block(AbstractBlock.Settings.copy(Blocks.PACKED_ICE)));
	public static final Block BLUE_ICE_BRICKS = createWithItem("blue_ice_bricks", new Block(AbstractBlock.Settings.copy(Blocks.BLUE_ICE)));
	// TODO: Black Ice

	public static final Block BEARD_MOSS_BLOCK = createWithItem("beard_moss_block", new Block(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK)));
	public static final Block BEARD_MOSS_CARPET = createWithItem("beard_moss_carpet", new CarpetBlock(AbstractBlock.Settings.copy(Blocks.MOSS_CARPET)));
	// TODO: Hanging & Wall Beard Moss

	// DIRT "ORES"
	public static final Block FLINT_DEPOSIT = createWithItem("flint_deposit", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PEAT_MOSS = createWithItem("peat_moss", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DEEP_ROOTED_SOIL = createWithItem("deep_rooted_soil", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));

	public static final Block MYCELIAL_DIRT = createWithItem("mycelial_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block DECOMPOSING_DIRT = createWithItem("decomposing_dirt", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block FUZZ_BLOCK = createWithItem("fuzz_block", new Block(AbstractBlock.Settings.copy(Blocks.DIRT).strength(1.0f, 3f).mapColor(MapColor.RAW_IRON_PINK)));

	public static final Block FUZZ_BRICKS = createWithItem("fuzz_bricks", new Block(AbstractBlock.Settings.copy(FUZZ_BLOCK)));
	public static final Block FUZZ_BRICK_STAIRS = createWithItem("fuzz_brick_stairs", new MBStairsBlock(FUZZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(FUZZ_BRICKS)));
	public static final Block FUZZ_BRICK_SLAB = createWithItem("fuzz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(FUZZ_BRICKS)));
	public static final Block FUZZ_BRICK_WALL = createWithItem("fuzz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(FUZZ_BRICKS)));

	public static final Block FLINT_BLOCK = createWithItem("flint_block", new Block(AbstractBlock.Settings.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BLACK)));
	public static final Block SMOOTH_FLINT = createWithItem("smooth_flint", new Block(AbstractBlock.Settings.copy(FLINT_BLOCK)));
	public static final Block SMOOTH_FLINT_STAIRS = createWithItem("smooth_flint_stairs", new MBStairsBlock(SMOOTH_FLINT.getDefaultState(), AbstractBlock.Settings.copy(SMOOTH_FLINT)));
	public static final Block SMOOTH_FLINT_SLAB = createWithItem("smooth_flint_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOOTH_FLINT)));
	public static final Block FLINT_PILLAR = createWithItem("flint_pillar", new PillarBlock(AbstractBlock.Settings.copy(SMOOTH_FLINT)));

	public static final Block PEAT_BLOCK = createWithItem("peat_block", new Block(AbstractBlock.Settings.copy(Blocks.DIRT)));
	public static final Block PEAT_BRICKS = createWithItem("peat_bricks", new Block(AbstractBlock.Settings.copy(PEAT_BLOCK)));
	public static final Block PEAT_BRICK_STAIRS = createWithItem("peat_brick_stairs", new MBStairsBlock(PEAT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(PEAT_BRICKS)));
	public static final Block PEAT_BRICK_SLAB = createWithItem("peat_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(PEAT_BRICKS)));
	public static final Block PEAT_BRICK_WALL = createWithItem("peat_brick_wall", new WallBlock(AbstractBlock.Settings.copy(PEAT_BRICKS)));

	// MANUFACTURED BLOCKS HERE I GUESS?
	public static final Block FIBER_THATCH = createWithItem("fiber_thatch", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_LIGHT_GRAY)
			.strength(0.7f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block FIBER_THATCH_STAIRS = createWithItem("fiber_thatch_stairs", new MBStairsBlock(FIBER_THATCH.getDefaultState(), AbstractBlock.Settings.copy(FIBER_THATCH)));
	public static final Block FIBER_THATCH_SLAB = createWithItem("fiber_thatch_slab", new SlabBlock(AbstractBlock.Settings.copy(FIBER_THATCH)));

	// PLANTS
	public static final Block BEACHGRASS = createWithItem("beachgrass", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_BEACHGRASS = createWithItem("tall_beachgrass", new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));

	public static final Block DESERT_BRUSH = createWithItem("desert_brush", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_DESERT_BRUSH = createWithItem("tall_desert_brush", new SandyTallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));

	public static final Block COTTONGRASS = createWithItem("cottongrass", new MBGrassPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XYZ)));
	public static final Block TALL_COTTONGRASS = createWithItem("tall_cottongrass", new TallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

	public static final Block SOURSOBS = createWithItem("soursobs", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_SOURSOBS = registerBlock("potted_soursobs", new FlowerPotBlock(SOURSOBS, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
	// small flowers
	public static final Block MARIGOLD = createWithItem("marigold", new SandyFlowerBlock(StatusEffects.POISON, 12, AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_MARIGOLD = registerBlock("potted_marigold", new FlowerPotBlock(MARIGOLD, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	public static final Block FROSTY_HEATHER = createWithItem("frosty_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_FROSTY_HEATHER = registerBlock("potted_frosty_heather", new FlowerPotBlock(FROSTY_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	public static final Block SUNSET_HEATHER = createWithItem("sunset_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_SUNSET_HEATHER = registerBlock("potted_sunset_heather", new FlowerPotBlock(SUNSET_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	public static final Block TWILIGHT_HEATHER = createWithItem("twilight_heather", new CarpetFloraBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.WET_GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block POTTED_TWILIGHT_HEATHER = registerBlock("potted_twilight_heather", new FlowerPotBlock(TWILIGHT_HEATHER, AbstractBlock.Settings.of(Material.DECORATION)
			.breakInstantly().nonOpaque()));
	// 2-tall
	public static final Block YUCCA = createWithItem("yucca", new TallFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
	public static final Block LUPINE = createWithItem("lupine", new TallFlowerBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));

	// crops
	public static final Block WILD_CARROTS = createWithItem("wild_carrots", new WildCropBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block WILD_POTATOES = createWithItem("wild_potatoes", new WildCropBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	public static final Block SEA_BEETS = createWithItem("sea_beets", new SandyPlantBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ)));
	// TODO: Peanuts

	// unique
	public static final Block CAVEBLOOM_FLOWERS = createWithItem("cavebloom_flowers", new CavebloomFlowerBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS)));
	public static final Block CAVEBLOOM_VINE = registerBlock("cavebloom_vine", new CavebloomVineBlock(AbstractBlock.Settings.of(Material.PLANT)
			.noCollision().breakInstantly().ticksRandomly().sounds(BlockSoundGroup.GRASS)));

	public static final Block TINY_BARREL_CACTUS = createWithItem("tiny_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.TINY, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block SMALL_BARREL_CACTUS = createWithItem("small_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.SMALL, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block BARREL_CACTUS = createWithItem("barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.MEDIUM, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block LARGE_BARREL_CACTUS = createWithItem("large_barrel_cactus", new BarrelCactusBlock(BarrelCactusBlock.Size.LARGE, QuiltBlockSettings.of(Material.CACTUS).strength(0.5f).sounds(BlockSoundGroup.WOOL)));

	// TODO: Hardy Fern blocks
	//public static final Block PARASOL_PUP = new PupBlock(() -> MBTreeFeatures.PARASOL_FERN, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));
	// TODO: fiber weed thing idk what to call it

	public static final Block BRITTLEBUSH_LEAVES = createWithItem("brittlebush_leaves", new BrittlebushLeavesBlock(AbstractBlock.Settings.copy(Blocks.AZALEA).ticksRandomly()));
	public static final Block BRITTLEBUSH_FLOWERS = createWithItem("brittlebush_flowers",
			new BrittlebushFlowersBlock(StatusEffects.WEAKNESS, 3, AbstractBlock.Settings.copy(Blocks.AZALEA).noCollision()));

	// TODO: Prickly Pear

	public static final Block OCOTILLO = createWithItem("ocotillo", new OcotilloBlock(OcotilloBlock.Stage.BARE, AbstractBlock.Settings.copy(Blocks.AZALEA)));
	public static final Block FLOWERING_OCOTILLO = createWithItem("flowering_ocotillo", new OcotilloBlock(OcotilloBlock.Stage.FLOWERING, AbstractBlock.Settings.copy(Blocks.AZALEA)));

	// SHROOMS
	public static final Block SAFFRON_MUSHROOM = createWithItem("saffron_mushroom",
			new MushroomPlantBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).noCollision().ticksRandomly().breakInstantly()
					.sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ).postProcess((state, world, pos) -> true), () -> MBTreeFeatures.SAFFRON_MUSHROOM));
	public static final Block POTTED_SAFFRON_MUSHROOM = registerBlock("potted_saffron_mushroom",
			new FlowerPotBlock(SAFFRON_MUSHROOM, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));

	// TODO: Fuzz Shrooms

	public static final Block RED_MUSHROOM_CAP = createWithItem("red_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.RED).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block BROWN_MUSHROOM_CAP = createWithItem("brown_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.BROWN).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block SAFFRON_MUSHROOM_CAP = createWithItem("saffron_mushroom_cap",
			new MushroomCapBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));

	public static final Block MUSHROOM_STEM = createWithItem("mushroom_stem",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block MUSHROOM_HYPHAE = createWithItem("mushroom_hyphae",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.OFF_WHITE).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block STRIPPED_MUSHROOM_STEM = createWithItem("stripped_mushroom_stem",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));
	public static final Block STRIPPED_MUSHROOM_HYPHAE = createWithItem("stripped_mushroom_hyphae",
			new PillarBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.PALE_YELLOW).strength(0.6F).sounds(BlockSoundGroup.NETHER_STEM)));

	public static final Block SAFFRON_GILLS = createWithItem("saffron_gills",
			new MushroomGillBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.TERRACOTTA_ORANGE).strength(0.6F).sounds(BlockSoundGroup.GRASS).breakInstantly().nonOpaque().noCollision()));

	// OTHER LAND STUFF
	public static final Block PEBBLES = createWithItem("pebbles", new PebbleBlock(AbstractBlock.Settings.of(Material.STONE).noCollision().breakInstantly().sounds(BlockSoundGroup.TUFF)));

	// MANUFACTURED?
	public static final Block BEAM = createWithItem("beam", new BeamBlock(QuiltBlockSettings.of(Material.WOOD).strength(0.6f).sounds(BlockSoundGroup.WOOD)));

	// STORAGE BLOCKS
	public static final Block APPLE_CRATE = createWithItem("apple_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block CARROT_CRATE = createWithItem("carrot_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block POTATO_CRATE = createWithItem("potato_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));
	public static final Block BEETROOT_CRATE = createWithItem("beetroot_crate", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));

	public static final Block EGG_BASKET = createWithItem("egg_basket", new Block(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(MBSounds.SACK)));
	public static final Block COCOA_SACK = createWithItem("cocoa_sack", new Block(QuiltBlockSettings.of(Material.WOOL).strength(2.0F,3.0F).sounds(MBSounds.SACK)));

	public static final Block GLISTERING_MELON_BLOCK = createWithItem("glistering_melon_block",
			new Block(QuiltBlockSettings.of(Material.GOURD).hardness(1.0F).sounds(BlockSoundGroup.WOOD).luminance((state) -> 12)));

	public static final Block SWEET_BERRY_BASKET = createWithItem("sweet_berry_basket", new Block(QuiltBlockSettings.of(Material.WOOD).strength(0.5F).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block GLOW_BERRY_BASKET = createWithItem("glow_berry_basket",
			new Block(QuiltBlockSettings.of(Material.WOOD).strength(0.5F).sounds(BlockSoundGroup.NYLIUM).luminance((state) -> 12)));

	public static final Block SUGAR_CANE_BUNDLE = createWithItem("sugar_cane_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block BAMBOO_BUNDLE = createWithItem("bamboo_bundle", new PillarBlock(QuiltBlockSettings.of(Material.BAMBOO).hardness(1.0f).sounds(BlockSoundGroup.SCAFFOLDING)));
	public static final Block OCOTILLO_BUNDLE = createWithItem("ocotillo_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(1.0f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block CHORUS_BUNDLE = createWithItem("chorus_bundle", new PillarBlock(QuiltBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(BlockSoundGroup.NYLIUM)));

	public static final Block KELP_BLOCK = createWithItem("kelp_block",
			new Block(QuiltBlockSettings.of(Material.SOLID_ORGANIC).strength(0.5F, 2.5F).sounds(BlockSoundGroup.WET_GRASS)));

	public static final Block NETHER_WART_SACK = createWithItem("nether_wart_sack", new Block(QuiltBlockSettings.of(Material.NETHER_SHOOTS).hardness(1.0f).sounds(BlockSoundGroup.WART_BLOCK)));

	public static final Block SUGAR_CUBE = createWithItem("sugar_cube", new FallingBlock(QuiltBlockSettings.of(Material.SOIL).strength(2.0F,3.0F).sounds(BlockSoundGroup.SAND)));
	public static final Block PACKED_GLOWSTONE = createWithItem("packed_glowstone",
			new Block(QuiltBlockSettings.of(Material.GLASS).strength(2.0F,3.0F).sounds(BlockSoundGroup.GLASS).luminance((state) -> 15)));
	public static final Block GUNPOWDER_CRATE = createWithItem("gunpowder_crate",
			new GunpowderBarrelBlock(QuiltBlockSettings.of(Material.WOOD).strength(2.0F,3.0F).sounds(BlockSoundGroup.WOOD)));

	public static final Block SAP_BLOCK = createWithItem("sap_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK)));
	public static final Block SYRUP_BLOCK = createWithItem("syrup_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK).dynamicBounds()));
	public static final Block RESIN_BLOCK = createWithItem("resin_block", new SyrupBlock(AbstractBlock.Settings.copy(Blocks.HONEY_BLOCK)));

	public static final Block SPOOL = createWithItem("spool", new PillarBlock(QuiltBlockSettings.of(Material.WOOL).hardness(0.8f).sounds(BlockSoundGroup.WOOL)));
	public static final Block PAPER_BUNDLE = createWithItem("paper_bundle", new PapersBlock(QuiltBlockSettings.of(Material.WOOL).hardness(0.5f).sounds(BlockSoundGroup.WOOL)));
	public static final Block STICK_STACK = createWithItem("stick_bundle", new PillarBlock(QuiltBlockSettings.of(Material.WOOD).hardness(0.5f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block CHARCOAL_LOG = createWithItem("charcoal_log",
			new PillarBlock(QuiltBlockSettings.of(Material.WOOD).strength(1.2f, 0.8f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));

	public static final Block SCUTE_BLOCK = createWithItem("scute_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.STONE)));

	public static final Block ROTTEN_FLESH_BLOCK = createWithItem("rotten_flesh_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES)));
	public static final Block BONE_BUNDLE = createWithItem("bone_bundle", new PillarBlock(QuiltBlockSettings.of(Material.AGGREGATE).hardness(1f).sounds(BlockSoundGroup.MANGROVE_ROOTS)));
	public static final Block SPIDER_EYE_BLOCK = createWithItem("spider_eye_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(0.8f).sounds(BlockSoundGroup.WEEPING_VINES)));
	public static final Block PHANTOM_MEMBRANE_BLOCK = createWithItem("phantom_membrane_block", new Block(QuiltBlockSettings.of(Material.ORGANIC_PRODUCT).hardness(1f).sounds(BlockSoundGroup.NYLIUM)));
	public static final Block ENDER_PEARL_BLOCK = createWithItem("ender_pearl_block", new Block(QuiltBlockSettings.of(Material.STONE).hardness(1f).sounds(BlockSoundGroup.COPPER)));

	public static final Block BLAZE_ROD = registerBlock("blaze_rod_placed",
			new MBRodBlock(QuiltBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15)));
	public static final Block BLAZE_ROD_BUNDLE = createWithItem("blaze_rod_bundle",
			new PillarBlock(QuiltBlockSettings.of(Material.METAL).hardness(1f).sounds(BlockSoundGroup.COPPER).luminance((state) -> 15)));

	// FUNCTIONAL BLOCKS
	public static final Block ROPE_LADDER = createWithItem("rope_ladder",
			new RopeLadderBlock(AbstractBlock.Settings.of(Material.DECORATION).strength(0.4F).sounds(BlockSoundGroup.LADDER).nonOpaque()));

	public static final Block WALL_LANTERN = registerBlock("wall_lantern",
			new WallLanternBlock(Blocks.LANTERN, AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
					.luminance(state -> 15).nonOpaque()));
	public static final Block WALL_SOUL_LANTERN = registerBlock("wall_soul_lantern",
			new WallLanternBlock(Blocks.SOUL_LANTERN, AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
					.luminance(state -> 10).nonOpaque()));
	public static final Block WALL_COPPER_OXIDE_LANTERN = registerBlock("wall_copper_oxide_lantern",
			new WallLanternBlock(COPPER_OXIDE_LANTERN, AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN)
					.luminance(state -> 15).nonOpaque()));

	public static final Block BEDROLL = createWithItem("bedroll", new BedrollBlock(DyeColor.BROWN,
			AbstractBlock.Settings.of(Material.WOOL, MapColor.BROWN).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
	// TODO: Bedroll item in MBItems..?

	// SEATS
	public static final Block WHITE_CUSHION = createWithItem("white_cushion", new SeatBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED).strength(1.2f).mapColor(MapColor.WHITE).nonOpaque()));
	public static final Block LIGHT_GRAY_CUSHION = createWithItem("light_gray_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIGHT_GRAY)));
	public static final Block GRAY_CUSHION = createWithItem("gray_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.GRAY)));
	public static final Block BLACK_CUSHION = createWithItem("black_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BLACK)));
	public static final Block BROWN_CUSHION = createWithItem("brown_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BROWN)));
	public static final Block RED_CUSHION = createWithItem("red_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.RED)));
	public static final Block ORANGE_CUSHION = createWithItem("orange_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.ORANGE)));
	public static final Block YELLOW_CUSHION = createWithItem("yellow_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.YELLOW)));
	public static final Block LIME_CUSHION = createWithItem("lime_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIME)));
	public static final Block GREEN_CUSHION = createWithItem("green_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.GREEN)));
	public static final Block CYAN_CUSHION = createWithItem("cyan_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.CYAN)));
	public static final Block LIGHT_BLUE_CUSHION = createWithItem("light_blue_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.LIGHT_BLUE)));
	public static final Block BLUE_CUSHION = createWithItem("blue_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.BLUE)));
	public static final Block PURPLE_CUSHION = createWithItem("purple_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.PURPLE)));
	public static final Block MAGENTA_CUSHION = createWithItem("magenta_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.MAGENTA)));
	public static final Block PINK_CUSHION = createWithItem("pink_cushion", new SeatBlock(AbstractBlock.Settings.copy(WHITE_CUSHION).mapColor(MapColor.PINK)));

	public static final Block KILN = createWithItem("kiln", new KilnBlock(AbstractBlock.Settings.of(Material.STONE, MapColor.TERRACOTTA_ORANGE).strength(1.2f).sounds(BlockSoundGroup.STONE)));

	// TAPS
	public static final Block TREE_TAP = createWithItem("tree_tap", new TreeTapBlock(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)));
	public static final Block SAP_TREE_TAP = registerBlock("sap_tree_tap", new FilledTreeTapBlock(false, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	public static final Block SYRUP_TREE_TAP = registerBlock("syrup_tree_tap", new FilledTreeTapBlock(true, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	public static final Block RESIN_TREE_TAP = registerBlock("resin_tree_tap", new FilledTreeTapBlock(false, AbstractBlock.Settings.copy(TREE_TAP).dropsLike(TREE_TAP)));
	// TODO: Pitch Tap?

	// TODO: Incenses

	// TODO: Rock (the loot one) + Outcrops (deepslate ver.)

	// LOOT POTS
	public static final Block RABBIT_MOUND = createWithItem("rabbit_mound", new DirtMoundBlock(AbstractBlock.Settings.copy(Blocks.DIRT).strength(0.3f)));
	public static final Block DESERT_VASE = createWithItem("desert_vase", new VaseBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).sounds(MBSounds.CERAMIC)));
	//	public static final Block DESERT_VASE_REPLICA = new VaseBlock(AbstractBlock.Settings.copy(Blocks.TERRACOTTA).sounds(MBSounds.CERAMIC));
//	public static final Block UNFIRED_DESERT_VASE = new UnfiredVaseBlock(DESERT_VASE_REPLICA, AbstractBlock.Settings.copy(Blocks.CLAY));
	public static final Block MUD_VESSEL = createWithItem("mud_vessel", new VaseBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD).sounds(MBSounds.CERAMIC)));
//	public static final Block MUD_VESSEL_REPLICA = new VaseBlock(AbstractBlock.Settings.copy(Blocks.PACKED_MUD).sounds(MBSounds.CERAMIC));

	// TODO: Rabbit Idol

	// TODO: Magnet


	public static void init() {}

	public static <T extends Block> T createWithItem(String block_id, T block) {
		BLOCK_ITEMS.put(block_id, block);
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
//		block = Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
//		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new BlockItem(block, new Item.Settings().group(MBItemGroup.CONSTRUCTION)));
//		return createWithItem(block_id, block, new Item.Settings().group(MBItemGroup.CONSTRUCTION), BlockItem::new);
	}
	public static <T extends Block> T createWithItem(String block_id, T block, Item.Settings settings, BiFunction<T, Item.Settings, BlockItem> factory) {
		registerItem(block_id, factory.apply(registerBlock(block_id, block), settings));
		return block;
	}
	private static <T extends Item> T registerItem(String name, T item) {
		return Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, name), item);
	}
	public static <T extends Block> T registerBlock(String block_id, T block) {
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
	}

	public static Block createWithItem(String block_id, Block block, ItemGroup group) {
		Registry.register(Registry.ITEM, new Identifier(Moonbits.MODID, block_id), new BlockItem(block, new Item.Settings().group(group)));
		return Registry.register(Registry.BLOCK, new Identifier(Moonbits.MODID, block_id), block);
	}
}
