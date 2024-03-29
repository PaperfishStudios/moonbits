package net.paperfish.moonbits.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MBBlockFamilies {
    private static final Map<Block, MBBlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

	public static final MBBlockFamily OAK_BOARDS = register(MBBlocks.OAK_BOARDS)
			.slab(MBBlocks.OAK_MOSAIC_SLAB).stairs(MBBlocks.OAK_MOSAIC_STAIRS)
			.build();
    public static final MBBlockFamily OAK = register(Blocks.OAK_PLANKS).slab(Blocks.OAK_SLAB)
            .planterBox(MBBlocks.OAK_PLANTER_BOX)
            .chiseled(MBBlocks.OAK_BOARDS).stonecut(MBBlocks.OAK_PANEL)
            .column(MBBlocks.OAK_PILLAR, MBBlocks.CARVED_OAK)
            .stonecut(List.of(Blocks.OAK_SLAB, Blocks.OAK_STAIRS, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE,
					Blocks.OAK_SIGN, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_TRAPDOOR))
			.child(OAK_BOARDS)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
	public static final MBBlockFamily SPRUCE_BOARDS = register(MBBlocks.SPRUCE_BOARDS)
			.slab(MBBlocks.SPRUCE_MOSAIC_SLAB).stairs(MBBlocks.SPRUCE_MOSAIC_STAIRS)
			.build();
    public static final MBBlockFamily SPRUCE = register(Blocks.SPRUCE_PLANKS).slab(Blocks.SPRUCE_SLAB)
//            .bookshelf(MBBlocks.SPRUCE_BOOKSHELF)
            .planterBox(MBBlocks.SPRUCE_PLANTER_BOX)
            .chiseled(MBBlocks.SPRUCE_BOARDS).stonecut(MBBlocks.SPRUCE_PANEL)
            .column(MBBlocks.SPRUCE_PILLAR, MBBlocks.CARVED_SPRUCE)
            .stonecut(List.of(Blocks.SPRUCE_SLAB, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE,
					Blocks.SPRUCE_SIGN, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_TRAPDOOR))
			.child(SPRUCE_BOARDS)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
	public static final MBBlockFamily BIRCH_BOARDS = register(MBBlocks.BIRCH_BOARDS)
			.slab(MBBlocks.BIRCH_MOSAIC_SLAB).stairs(MBBlocks.BIRCH_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily BIRCH = register(Blocks.BIRCH_PLANKS).slab(Blocks.BIRCH_SLAB)
//            .bookshelf(MBBlocks.BIRCH_BOOKSHELF)
			.planterBox(MBBlocks.BIRCH_PLANTER_BOX)
			.chiseled(MBBlocks.BIRCH_BOARDS).stonecut(MBBlocks.BIRCH_PANEL)
			.column(MBBlocks.BIRCH_PILLAR, MBBlocks.CARVED_BIRCH)
			.stonecut(List.of(Blocks.BIRCH_SLAB, Blocks.BIRCH_STAIRS, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE,
					Blocks.BIRCH_SIGN, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_TRAPDOOR))
			.child(BIRCH_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();
	public static final MBBlockFamily JUNGLE_BOARDS = register(MBBlocks.JUNGLE_BOARDS)
			.slab(MBBlocks.JUNGLE_MOSAIC_SLAB).stairs(MBBlocks.JUNGLE_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily JUNGLE = register(Blocks.JUNGLE_PLANKS).slab(Blocks.JUNGLE_SLAB)
//            .bookshelf(MBBlocks.JUNGLE_BOOKSHELF)
			.planterBox(MBBlocks.JUNGLE_PLANTER_BOX)
			.chiseled(MBBlocks.JUNGLE_BOARDS).stonecut(MBBlocks.JUNGLE_PANEL)
			.column(MBBlocks.JUNGLE_PILLAR, MBBlocks.CARVED_JUNGLE)
			.stonecut(List.of(Blocks.JUNGLE_SLAB, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE,
					Blocks.JUNGLE_SIGN, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_TRAPDOOR))
			.child(JUNGLE_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();
	public static final MBBlockFamily ACACIA_BOARDS = register(MBBlocks.ACACIA_BOARDS)
			.slab(MBBlocks.ACACIA_MOSAIC_SLAB).stairs(MBBlocks.ACACIA_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily ACACIA = register(Blocks.ACACIA_PLANKS).slab(Blocks.ACACIA_SLAB)
//            .bookshelf(MBBlocks.ACACIA_BOOKSHELF)
			.planterBox(MBBlocks.ACACIA_PLANTER_BOX)
			.chiseled(MBBlocks.ACACIA_BOARDS).stonecut(MBBlocks.ACACIA_PANEL)
			.column(MBBlocks.ACACIA_PILLAR, MBBlocks.CARVED_ACACIA)
			.stonecut(List.of(Blocks.ACACIA_SLAB, Blocks.ACACIA_STAIRS, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE,
					Blocks.ACACIA_SIGN, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_TRAPDOOR))
			.child(ACACIA_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();
	public static final MBBlockFamily DARK_OAK_BOARDS = register(MBBlocks.DARK_OAK_BOARDS)
			.slab(MBBlocks.DARK_OAK_MOSAIC_SLAB).stairs(MBBlocks.DARK_OAK_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily DARK_OAK = register(Blocks.DARK_OAK_PLANKS).slab(Blocks.DARK_OAK_SLAB)
//            .bookshelf(MBBlocks.DARK_OAK_BOOKSHELF)
			.planterBox(MBBlocks.DARK_OAK_PLANTER_BOX)
			.chiseled(MBBlocks.DARK_OAK_BOARDS).stonecut(MBBlocks.DARK_OAK_PANEL)
			.column(MBBlocks.DARK_OAK_PILLAR, MBBlocks.CARVED_DARK_OAK)
			.stonecut(List.of(Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE,
					Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_TRAPDOOR))
			.child(DARK_OAK_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();
	public static final MBBlockFamily MANGROVE_BOARDS = register(MBBlocks.MANGROVE_BOARDS)
			.slab(MBBlocks.MANGROVE_MOSAIC_SLAB).stairs(MBBlocks.MANGROVE_MOSAIC_STAIRS)
			.build();
    public static final MBBlockFamily MANGROVE = register(Blocks.MANGROVE_PLANKS).slab(Blocks.MANGROVE_SLAB)
//            .bookshelf(MBBlocks.MANGROVE_BOOKSHELF)
            .planterBox(MBBlocks.MANGROVE_PLANTER_BOX)
            .chiseled(MBBlocks.MANGROVE_BOARDS).stonecut(MBBlocks.MANGROVE_PANEL)
            .column(MBBlocks.MANGROVE_PILLAR, MBBlocks.CARVED_MANGROVE)
            .stonecut(List.of(Blocks.MANGROVE_SLAB, Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_FENCE, Blocks.MANGROVE_FENCE_GATE,
					Blocks.MANGROVE_SIGN, Blocks.MANGROVE_PRESSURE_PLATE, Blocks.MANGROVE_TRAPDOOR))
			.child(MANGROVE_BOARDS)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
	public static final MBBlockFamily MANGROVE_WEAVE = register(MBBlocks.MANGROVE_WEAVE)
			.slab(MBBlocks.MANGROVE_WEAVE_SLAB).stairs(MBBlocks.MANGROVE_WEAVE_STAIRS).build();

	public static final MBBlockFamily CRIMSON_BOARDS = register(MBBlocks.CRIMSON_BOARDS)
			.slab(MBBlocks.CRIMSON_MOSAIC_SLAB).stairs(MBBlocks.CRIMSON_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily CRIMSON = register(Blocks.CRIMSON_PLANKS).slab(Blocks.CRIMSON_SLAB)
//            .bookshelf(MBBlocks.CRIMSON_BOOKSHELF)
			.netherPlanter(MBBlocks.CRIMSON_PLANTER_BOX)
			.chiseled(MBBlocks.CRIMSON_BOARDS).stonecut(MBBlocks.CRIMSON_PANEL)
			.column(MBBlocks.CRIMSON_PILLAR, MBBlocks.CARVED_CRIMSON)
			.stonecut(List.of(Blocks.CRIMSON_SLAB, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE,
					Blocks.CRIMSON_SIGN, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_TRAPDOOR))
			.child(CRIMSON_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();
	public static final MBBlockFamily WARPED_BOARDS = register(MBBlocks.WARPED_BOARDS)
			.slab(MBBlocks.WARPED_MOSAIC_SLAB).stairs(MBBlocks.WARPED_MOSAIC_STAIRS)
			.build();
	public static final MBBlockFamily WARPED = register(Blocks.WARPED_PLANKS).slab(Blocks.WARPED_SLAB)
//            .bookshelf(MBBlocks.WARPED_BOOKSHELF)
			.netherPlanter(MBBlocks.WARPED_PLANTER_BOX)
			.chiseled(MBBlocks.WARPED_BOARDS).stonecut(MBBlocks.WARPED_PANEL)
			.column(MBBlocks.WARPED_PILLAR, MBBlocks.CARVED_WARPED)
			.stonecut(List.of(Blocks.WARPED_SLAB, Blocks.WARPED_STAIRS, Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE,
					Blocks.WARPED_SIGN, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_TRAPDOOR))
			.child(WARPED_BOARDS)
			.group("wooden")
			.unlockCriterionName("has_planks")
			.build();


	public static final MBBlockFamily LAMPROOT_BOARDS = register(MBBlocks.LAMPROOT_BOARDS)
			.slab(MBBlocks.LAMPROOT_MOSAIC_SLAB).stairs(MBBlocks.LAMPROOT_MOSAIC_STAIRS)
			.build();
    public static final MBBlockFamily LAMPROOT = register(MBBlocks.LAMPROOT_PLANKS)
            .slab(MBBlocks.LAMPROOT_SLAB).stairs(MBBlocks.LAMPROOT_STAIRS)
            .fence(MBBlocks.LAMPROOT_FENCE).fenceGate(MBBlocks.LAMPROOT_FENCE_GATE)
            .door(MBBlocks.LAMPROOT_DOOR).trapdoor(MBBlocks.LAMPROOT_TRAPDOOR)
            .sign(MBBlocks.LAMPROOT_SIGN, MBBlocks.LAMPROOT_WALL_SIGN)
            .pressurePlate(MBBlocks.LAMPROOT_PRESSURE_PLATE).button(MBBlocks.LAMPROOT_BUTTON)
//            .bookshelf(MBBlocks.LAMPROOT_BOOKSHELF)
            .planterBox(MBBlocks.LAMPROOT_PLANTER_BOX)
            .chiseled(MBBlocks.LAMPROOT_BOARDS).stonecut(MBBlocks.LAMPROOT_PANEL)
			.child(LAMPROOT_BOARDS)
            .column(MBBlocks.LAMPROOT_PILLAR, MBBlocks.CARVED_LAMPROOT)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
	public static final MBBlockFamily CEDAR_BOARDS = register(MBBlocks.CEDAR_BOARDS)
			.slab(MBBlocks.CEDAR_MOSAIC_SLAB).stairs(MBBlocks.CEDAR_MOSAIC_STAIRS)
			.build();
    public static final MBBlockFamily CEDAR = register(MBBlocks.CEDAR_PLANKS)
            .slab(MBBlocks.CEDAR_SLAB).stairs(MBBlocks.CEDAR_STAIRS)
            .fence(MBBlocks.CEDAR_FENCE).fenceGate(MBBlocks.CEDAR_FENCE_GATE)
            .door(MBBlocks.CEDAR_DOOR).trapdoor(MBBlocks.CEDAR_TRAPDOOR)
            .sign(MBBlocks.CEDAR_SIGN, MBBlocks.CEDAR_WALL_SIGN)
            .pressurePlate(MBBlocks.CEDAR_PRESSURE_PLATE).button(MBBlocks.CEDAR_BUTTON)
//            .bookshelf(MBBlocks.CEDAR_BOOKSHELF)
            .planterBox(MBBlocks.CEDAR_PLANTER_BOX)
            .chiseled(MBBlocks.CEDAR_BOARDS).stonecut(MBBlocks.CEDAR_PANEL)
			.child(CEDAR_BOARDS)
            .column(MBBlocks.CEDAR_PILLAR, MBBlocks.CARVED_CEDAR)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();


	public static final MBBlockFamily FUZZ_BRICKS = register(MBBlocks.FUZZ_BRICKS)
			.slab(MBBlocks.FUZZ_BRICK_SLAB).stairs(MBBlocks.FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily FUZZ_BOARDS = register(MBBlocks.FUZZ_BOARDS).polished(MBBlocks.FUZZ_BRICKS).child(FUZZ_BRICKS)
			.slab(MBBlocks.FUZZ_BOARD_SLAB).stairs(MBBlocks.FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily FUZZ_BLOCK = register(MBBlocks.FUZZ_BLOCK).polished(MBBlocks.FUZZ_BOARDS).child(FUZZ_BOARDS)
			.slab(MBBlocks.FUZZ_BLOCK_SLAB).stairs(MBBlocks.FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily RED_FUZZ_BRICKS = register(MBBlocks.RED_FUZZ_BRICKS)
			.slab(MBBlocks.RED_FUZZ_BRICK_SLAB).stairs(MBBlocks.RED_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.RED_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily RED_FUZZ_BOARDS = register(MBBlocks.RED_FUZZ_BOARDS).polished(MBBlocks.RED_FUZZ_BRICKS).child(RED_FUZZ_BRICKS)
			.slab(MBBlocks.RED_FUZZ_BOARD_SLAB).stairs(MBBlocks.RED_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily RED_FUZZ_BLOCK = register(MBBlocks.RED_FUZZ_BLOCK).polished(MBBlocks.RED_FUZZ_BOARDS).child(RED_FUZZ_BOARDS)
			.slab(MBBlocks.RED_FUZZ_BLOCK_SLAB).stairs(MBBlocks.RED_FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily BROWN_FUZZ_BRICKS = register(MBBlocks.BROWN_FUZZ_BRICKS)
			.slab(MBBlocks.BROWN_FUZZ_BRICK_SLAB).stairs(MBBlocks.BROWN_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.BROWN_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily BROWN_FUZZ_BOARDS = register(MBBlocks.BROWN_FUZZ_BOARDS).polished(MBBlocks.BROWN_FUZZ_BRICKS).child(BROWN_FUZZ_BRICKS)
			.slab(MBBlocks.BROWN_FUZZ_BOARD_SLAB).stairs(MBBlocks.BROWN_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily BROWN_FUZZ_BLOCK = register(MBBlocks.BROWN_FUZZ_BLOCK).polished(MBBlocks.BROWN_FUZZ_BOARDS).child(BROWN_FUZZ_BOARDS)
			.slab(MBBlocks.BROWN_FUZZ_BLOCK_SLAB).stairs(MBBlocks.BROWN_FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily SAFFRON_FUZZ_BRICKS = register(MBBlocks.SAFFRON_FUZZ_BRICKS)
			.slab(MBBlocks.SAFFRON_FUZZ_BRICK_SLAB).stairs(MBBlocks.SAFFRON_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.SAFFRON_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily SAFFRON_FUZZ_BOARDS = register(MBBlocks.SAFFRON_FUZZ_BOARDS).polished(MBBlocks.SAFFRON_FUZZ_BRICKS).child(SAFFRON_FUZZ_BRICKS)
			.slab(MBBlocks.SAFFRON_FUZZ_BOARD_SLAB).stairs(MBBlocks.SAFFRON_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily SAFFRON_FUZZ_BLOCK = register(MBBlocks.SAFFRON_FUZZ_BLOCK).polished(MBBlocks.SAFFRON_FUZZ_BOARDS).child(SAFFRON_FUZZ_BOARDS)
			.slab(MBBlocks.SAFFRON_FUZZ_BLOCK_SLAB).stairs(MBBlocks.SAFFRON_FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily BONNET_FUZZ_BRICKS = register(MBBlocks.BONNET_FUZZ_BRICKS)
			.slab(MBBlocks.BONNET_FUZZ_BRICK_SLAB).stairs(MBBlocks.BONNET_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.BONNET_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily BONNET_FUZZ_BOARDS = register(MBBlocks.BONNET_FUZZ_BOARDS).polished(MBBlocks.BONNET_FUZZ_BRICKS).child(BONNET_FUZZ_BRICKS)
			.slab(MBBlocks.BONNET_FUZZ_BOARD_SLAB).stairs(MBBlocks.BONNET_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily BONNET_FUZZ_BLOCK = register(MBBlocks.BONNET_FUZZ_BLOCK).polished(MBBlocks.BONNET_FUZZ_BOARDS).child(BONNET_FUZZ_BOARDS)
			.slab(MBBlocks.BONNET_FUZZ_BLOCK_SLAB).stairs(MBBlocks.BONNET_FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily AMITY_FUZZ_BRICKS = register(MBBlocks.AMITY_FUZZ_BRICKS)
			.slab(MBBlocks.AMITY_FUZZ_BRICK_SLAB).stairs(MBBlocks.AMITY_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.AMITY_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily AMITY_FUZZ_BOARDS = register(MBBlocks.AMITY_FUZZ_BOARDS).polished(MBBlocks.AMITY_FUZZ_BRICKS).child(AMITY_FUZZ_BRICKS)
			.slab(MBBlocks.AMITY_FUZZ_BOARD_SLAB).stairs(MBBlocks.AMITY_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily AMITY_FUZZ_BLOCK = register(MBBlocks.AMITY_FUZZ_BLOCK).polished(MBBlocks.AMITY_FUZZ_BOARDS).child(AMITY_FUZZ_BOARDS)
			.slab(MBBlocks.AMITY_FUZZ_BLOCK_SLAB).stairs(MBBlocks.AMITY_FUZZ_BLOCK_STAIRS).build();

	public static final MBBlockFamily INKY_FUZZ_BRICKS = register(MBBlocks.INKY_FUZZ_BRICKS)
			.slab(MBBlocks.INKY_FUZZ_BRICK_SLAB).stairs(MBBlocks.INKY_FUZZ_BRICK_STAIRS)
			.wall(MBBlocks.INKY_FUZZ_BRICK_WALL).build();
	public static final MBBlockFamily INKY_FUZZ_BOARDS = register(MBBlocks.INKY_FUZZ_BOARDS).polished(MBBlocks.INKY_FUZZ_BRICKS).child(INKY_FUZZ_BRICKS)
			.slab(MBBlocks.INKY_FUZZ_BOARD_SLAB).stairs(MBBlocks.INKY_FUZZ_BOARD_STAIRS).build();
	public static final MBBlockFamily INKY_FUZZ_BLOCK = register(MBBlocks.INKY_FUZZ_BLOCK).polished(MBBlocks.INKY_FUZZ_BOARDS).child(INKY_FUZZ_BOARDS)
			.slab(MBBlocks.INKY_FUZZ_BLOCK_SLAB).stairs(MBBlocks.INKY_FUZZ_BLOCK_STAIRS).build();

    public static final MBBlockFamily DIRT_BRICKS = register(MBBlocks.DIRT_BRICKS)
            .slab(MBBlocks.DIRT_BRICK_SLAB).stairs(MBBlocks.DIRT_BRICK_STAIRS).wall(MBBlocks.DIRT_BRICK_WALL).build();
    public static final MBBlockFamily PACKED_DIRT = register(MBBlocks.PACKED_DIRT).child(DIRT_BRICKS)
            .slab(MBBlocks.PACKED_DIRT_SLAB).stairs(MBBlocks.PACKED_DIRT_STAIRS).wall(MBBlocks.PACKED_DIRT_WALL)
			.polished(MBBlocks.DIRT_BRICKS).build();
    public static final MBBlockFamily DIRT_MB = register(Blocks.DIRT).polished(MBBlocks.PACKED_DIRT).build();

    public static final MBBlockFamily PEAT_BRICKS = register(MBBlocks.PEAT_BRICKS)
            .slab(MBBlocks.PEAT_BRICK_SLAB).stairs(MBBlocks.PEAT_BRICK_STAIRS)
            .wall(MBBlocks.PEAT_BRICK_WALL).build();
	public static final MBBlockFamily SMOOTH_FLINT = register(MBBlocks.SMOOTH_FLINT)
			.slab(MBBlocks.SMOOTH_FLINT_SLAB).stairs(MBBlocks.SMOOTH_FLINT_STAIRS)
			.stonecut(MBBlocks.FLINT_PILLAR).build();

	public static final MBBlockFamily PERMAFROST_BRICKS = register(MBBlocks.PERMAFROST_BRICKS)
			.slab(MBBlocks.PERMAFROST_BRICK_SLAB).stairs(MBBlocks.PERMAFROST_BRICK_STAIRS)
			.wall(MBBlocks.PERMAFROST_BRICK_WALL).build();
	public static final MBBlockFamily PERMAFROST = register(MBBlocks.PERMAFROST)
			.slab(MBBlocks.PERMAFROST_SLAB).stairs(MBBlocks.PERMAFROST_STAIRS)
			.wall(MBBlocks.PERMAFROST_WALL).polished(MBBlocks.PERMAFROST_BRICKS).child(PERMAFROST_BRICKS).build();

	public static final MBBlockFamily SNOW_BRICKS = register(MBBlocks.SNOW_BRICKS)
			.slab(MBBlocks.SNOW_BRICK_SLAB).stairs(MBBlocks.SNOW_BRICK_STAIRS)
			.wall(MBBlocks.SNOW_BRICK_WALL).build();

	public static final MBBlockFamily BLUE_ICE = register(Blocks.BLUE_ICE)
			.polished(MBBlocks.BLUE_ICE_BRICKS).build();
	public static final MBBlockFamily PACKED_ICE = register(Blocks.PACKED_ICE)
			.polished(MBBlocks.PACKED_ICE_BRICKS).build();
	public static final MBBlockFamily ICE = register(Blocks.ICE)
			.polished(Blocks.PACKED_ICE).build();


    public static final MBBlockFamily GRASS_TURF = register(MBBlocks.GRASS_TURF)
            .slab(MBBlocks.GRASS_TURF_SLAB).stairs(MBBlocks.GRASS_TURF_STAIRS)
            .carpet(MBBlocks.GRASS_CARPET).noGenerateModels().build();
    public static final MBBlockFamily MYCELIUM_TURF = register(MBBlocks.MYCELIUM_TURF)
            .slab(MBBlocks.MYCELIUM_TURF_SLAB).stairs(MBBlocks.MYCELIUM_TURF_STAIRS)
            .carpet(MBBlocks.MYCELIUM_CARPET).build();

    public static final MBBlockFamily CRIMSON_NYLIUM_TURF = register(MBBlocks.CRIMSON_NYLIUM_TURF)
            .slab(MBBlocks.CRIMSON_NYLIUM_TURF_SLAB).stairs(MBBlocks.CRIMSON_NYLIUM_TURF_STAIRS)
            .carpet(MBBlocks.CRIMSON_NYLIUM_CARPET).build();
    public static final MBBlockFamily WARPED_NYLIUM_TURF = register(MBBlocks.WARPED_NYLIUM_TURF)
            .slab(MBBlocks.WARPED_NYLIUM_TURF_SLAB).stairs(MBBlocks.WARPED_NYLIUM_TURF_STAIRS)
            .carpet(MBBlocks.WARPED_NYLIUM_CARPET).build();

	public static final MBBlockFamily FIBER_THATCH = register(MBBlocks.FIBER_THATCH)
			.slab(MBBlocks.FIBER_THATCH_SLAB).stairs(MBBlocks.FIBER_THATCH_STAIRS).build();

    public static final MBBlockFamily CRACKED_CHERT_TILES = register(MBBlocks.CRACKED_CHERT_TILES)
            .slab(MBBlocks.CRACKED_CHERT_TILE_SLAB).stairs(MBBlocks.CRACKED_CHERT_TILE_STAIRS).wall(MBBlocks.CRACKED_CHERT_TILE_WALL).build();
    public static final MBBlockFamily CHERT_TILES = register(MBBlocks.CHERT_TILES)
            .slab(MBBlocks.CHERT_TILE_SLAB).stairs(MBBlocks.CHERT_TILE_STAIRS).wall(MBBlocks.CHERT_TILE_WALL)
            .cracked(MBBlocks.CRACKED_CHERT_TILES).child(CRACKED_CHERT_TILES).build();

    public static final MBBlockFamily CRACKED_CHERT_BRICKS = register(MBBlocks.CRACKED_CHERT_BRICKS)
            .slab(MBBlocks.CRACKED_CHERT_BRICK_SLAB).stairs(MBBlocks.CRACKED_CHERT_BRICK_STAIRS).wall(MBBlocks.CRACKED_CHERT_BRICK_WALL).build();
    public static final MBBlockFamily CHERT_BRICKS = register(MBBlocks.CHERT_BRICKS)
            .slab(MBBlocks.CHERT_BRICK_SLAB).stairs(MBBlocks.CHERT_BRICK_STAIRS).wall(MBBlocks.CHERT_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_CHERT_BRICKS).polished(MBBlocks.CHERT_TILES).child(CRACKED_CHERT_BRICKS).child(CHERT_TILES).build();

    public static final MBBlockFamily POLISHED_CHERT = register(MBBlocks.POLISHED_CHERT)
            .slab(MBBlocks.POLISHED_CHERT_SLAB).stairs(MBBlocks.POLISHED_CHERT_STAIRS).wall(MBBlocks.POLISHED_CHERT_WALL)
            .polished(MBBlocks.CHERT_BRICKS).child(CHERT_BRICKS).build();
    public static final MBBlockFamily COBBLED_CHERT = register(MBBlocks.COBBLED_CHERT)
            .slab(MBBlocks.COBBLED_CHERT_SLAB).stairs(MBBlocks.COBBLED_CHERT_STAIRS).wall(MBBlocks.COBBLED_CHERT_WALL).build();
    public static final MBBlockFamily CHERT = register(MBBlocks.CHERT)
            .slab(MBBlocks.CHERT_SLAB).stairs(MBBlocks.CHERT_STAIRS).wall(MBBlocks.CHERT_WALL).child(COBBLED_CHERT)
            .polished(MBBlocks.POLISHED_CHERT).cut(MBBlocks.CUT_CHERT).chiseled(MBBlocks.CHISELED_CHERT).pillar(MBBlocks.CHERT_PILLAR).child(POLISHED_CHERT).build();

	public static final MBBlockFamily CLAYSTONE_BRICKS = register(MBBlocks.CLAYSTONE_BRICKS)
			.slab(MBBlocks.CLAYSTONE_BRICK_SLAB).stairs(MBBlocks.CLAYSTONE_BRICK_STAIRS).wall(MBBlocks.CLAYSTONE_BRICK_WALL)
			.build();
	public static final MBBlockFamily CLAYSTONE = register(MBBlocks.CLAYSTONE)
			.slab(MBBlocks.CLAYSTONE_SLAB).stairs(MBBlocks.CLAYSTONE_STAIRS).wall(MBBlocks.CLAYSTONE_WALL)
			.polished(MBBlocks.CLAYSTONE_BRICKS).child(CLAYSTONE_BRICKS).build();

	public static final MBBlockFamily POLISHED_JASPER = register(MBBlocks.POLISHED_JASPER)
			.slab(MBBlocks.POLISHED_JASPER_SLAB).stairs(MBBlocks.POLISHED_JASPER_STAIRS).wall(MBBlocks.POLISHED_JASPER_WALL)
			.build();
	public static final MBBlockFamily JASPER = register(MBBlocks.JASPER)
			.slab(MBBlocks.JASPER_SLAB).stairs(MBBlocks.JASPER_STAIRS).wall(MBBlocks.JASPER_WALL)
			.polished(MBBlocks.POLISHED_JASPER).child(POLISHED_JASPER).build();

	public static final MBBlockFamily POLISHED_AZURITE = register(MBBlocks.POLISHED_AZURITE)
			.slab(MBBlocks.POLISHED_AZURITE_SLAB).stairs(MBBlocks.POLISHED_AZURITE_STAIRS).wall(MBBlocks.POLISHED_AZURITE_WALL)
			.build();
	public static final MBBlockFamily AZURITE = register(MBBlocks.AZURITE)
			.slab(MBBlocks.AZURITE_SLAB).stairs(MBBlocks.AZURITE_STAIRS).wall(MBBlocks.AZURITE_WALL)
			.polished(MBBlocks.POLISHED_AZURITE).child(POLISHED_AZURITE).build();


    public static final MBBlockFamily CRACKED_STONE_TILES = register(MBBlocks.CRACKED_STONE_TILES)
            .slab(MBBlocks.CRACKED_STONE_TILE_SLAB).stairs(MBBlocks.CRACKED_STONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_STONE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_STONE_TILES = register(MBBlocks.MOSSY_STONE_TILES)
            .slab(MBBlocks.MOSSY_STONE_TILE_SLAB).stairs(MBBlocks.MOSSY_STONE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_STONE_TILE_WALL).build();
    public static final MBBlockFamily STONE_TILES = register(MBBlocks.STONE_TILES)
            .slab(MBBlocks.STONE_TILE_SLAB).stairs(MBBlocks.STONE_TILE_STAIRS)
            .wall(MBBlocks.STONE_TILE_WALL).cracked(MBBlocks.CRACKED_STONE_TILES).child(CRACKED_STONE_TILES).build();
    public static final MBBlockFamily STONE_BRICKS_MB = register(Blocks.STONE_BRICKS).slab(Blocks.STONE_BRICK_SLAB)
            .chiseled(Blocks.CHISELED_STONE_BRICKS).pillar(MBBlocks.STONE_PILLAR).polished(MBBlocks.STONE_TILES).child(STONE_TILES).build();
    public static final MBBlockFamily SMOOTH_STONE_MB = register(Blocks.SMOOTH_STONE).stairs(MBBlocks.SMOOTH_STONE_STAIRS).wall(MBBlocks.SMOOTH_STONE_WALL).build();
    public static final MBBlockFamily STONE_MB = register(Blocks.STONE).child(SMOOTH_STONE_MB).child(STONE_BRICKS_MB).build();


    public static final MBBlockFamily CRACKED_ANDESITE_TILES = register(MBBlocks.CRACKED_ANDESITE_TILES)
            .slab(MBBlocks.CRACKED_ANDESITE_TILE_SLAB).stairs(MBBlocks.CRACKED_ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_ANDESITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_ANDESITE_TILES = register(MBBlocks.MOSSY_ANDESITE_TILES)
            .slab(MBBlocks.MOSSY_ANDESITE_TILE_SLAB).stairs(MBBlocks.MOSSY_ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_ANDESITE_TILE_WALL).build();
    public static final MBBlockFamily ANDESITE_TILES = register(MBBlocks.ANDESITE_TILES)
            .slab(MBBlocks.ANDESITE_TILE_SLAB).stairs(MBBlocks.ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.ANDESITE_TILE_WALL).cracked(MBBlocks.CRACKED_ANDESITE_TILES).child(CRACKED_ANDESITE_TILES).build();
    public static final MBBlockFamily CRACKED_ANDESITE_BRICKS = register(MBBlocks.CRACKED_ANDESITE_BRICKS)
            .slab(MBBlocks.CRACKED_ANDESITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_ANDESITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_ANDESITE_BRICKS = register(MBBlocks.MOSSY_ANDESITE_BRICKS)
            .slab(MBBlocks.MOSSY_ANDESITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_ANDESITE_BRICK_WALL).build();
    public static final MBBlockFamily ANDESITE_BRICKS = register(MBBlocks.ANDESITE_BRICKS)
            .slab(MBBlocks.ANDESITE_BRICK_SLAB).stairs(MBBlocks.ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.ANDESITE_BRICK_WALL).cracked(MBBlocks.CRACKED_ANDESITE_BRICKS).child(CRACKED_ANDESITE_BRICKS).child(ANDESITE_TILES)
            .polished(MBBlocks.ANDESITE_TILES).build();
    public static final MBBlockFamily POLISHED_ANDESITE_MB = register(Blocks.POLISHED_ANDESITE).polished(MBBlocks.ANDESITE_BRICKS).child(ANDESITE_BRICKS).build();
    public static final MBBlockFamily COBBLED_ANDESITE = register(MBBlocks.COBBLED_ANDESITE)
            .slab(MBBlocks.COBBLED_ANDESITE_SLAB).stairs(MBBlocks.COBBLED_ANDESITE_STAIRS)
            .wall(MBBlocks.COBBLED_ANDESITE_WALL).build();
    public static final MBBlockFamily ANDESITE_MB = register(Blocks.ANDESITE).slab(Blocks.ANDESITE_SLAB)
            .chiseled(MBBlocks.CHISELED_ANDESITE).pillar(MBBlocks.ANDESITE_PILLAR).child(COBBLED_ANDESITE).child(POLISHED_ANDESITE_MB).build();

    public static final MBBlockFamily CRACKED_DIORITE_TILES = register(MBBlocks.CRACKED_DIORITE_TILES)
            .slab(MBBlocks.CRACKED_DIORITE_TILE_SLAB).stairs(MBBlocks.CRACKED_DIORITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_DIORITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_DIORITE_TILES = register(MBBlocks.MOSSY_DIORITE_TILES)
            .slab(MBBlocks.MOSSY_DIORITE_TILE_SLAB).stairs(MBBlocks.MOSSY_DIORITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_DIORITE_TILE_WALL).build();
    public static final MBBlockFamily DIORITE_TILES = register(MBBlocks.DIORITE_TILES)
            .slab(MBBlocks.DIORITE_TILE_SLAB).stairs(MBBlocks.DIORITE_TILE_STAIRS)
            .wall(MBBlocks.DIORITE_TILE_WALL).cracked(MBBlocks.CRACKED_DIORITE_TILES).child(CRACKED_DIORITE_TILES).build();
    public static final MBBlockFamily CRACKED_DIORITE_BRICKS = register(MBBlocks.CRACKED_DIORITE_BRICKS)
            .slab(MBBlocks.CRACKED_DIORITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_DIORITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_DIORITE_BRICKS = register(MBBlocks.MOSSY_DIORITE_BRICKS)
            .slab(MBBlocks.MOSSY_DIORITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_DIORITE_BRICK_WALL).build();
    public static final MBBlockFamily DIORITE_BRICKS = register(MBBlocks.DIORITE_BRICKS)
            .slab(MBBlocks.DIORITE_BRICK_SLAB).stairs(MBBlocks.DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.DIORITE_BRICK_WALL).cracked(MBBlocks.CRACKED_DIORITE_BRICKS).child(CRACKED_DIORITE_BRICKS).child(DIORITE_TILES)
            .polished(MBBlocks.DIORITE_TILES).build();
    public static final MBBlockFamily POLISHED_DIORITE_MB = register(Blocks.POLISHED_DIORITE).polished(MBBlocks.DIORITE_BRICKS).child(DIORITE_BRICKS).build();
    public static final MBBlockFamily COBBLED_DIORITE = register(MBBlocks.COBBLED_DIORITE)
            .slab(MBBlocks.COBBLED_DIORITE_SLAB).stairs(MBBlocks.COBBLED_DIORITE_STAIRS)
            .wall(MBBlocks.COBBLED_DIORITE_WALL).build();
    public static final MBBlockFamily DIORITE_MB = register(Blocks.DIORITE).slab(Blocks.DIORITE_SLAB)
            .chiseled(MBBlocks.CHISELED_DIORITE).pillar(MBBlocks.DIORITE_PILLAR).child(COBBLED_DIORITE).child(POLISHED_DIORITE_MB).build();

    public static final MBBlockFamily CRACKED_GRANITE_TILES = register(MBBlocks.CRACKED_GRANITE_TILES)
            .slab(MBBlocks.CRACKED_GRANITE_TILE_SLAB).stairs(MBBlocks.CRACKED_GRANITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_GRANITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_GRANITE_TILES = register(MBBlocks.MOSSY_GRANITE_TILES)
            .slab(MBBlocks.MOSSY_GRANITE_TILE_SLAB).stairs(MBBlocks.MOSSY_GRANITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_GRANITE_TILE_WALL).build();
    public static final MBBlockFamily GRANITE_TILES = register(MBBlocks.GRANITE_TILES)
            .slab(MBBlocks.GRANITE_TILE_SLAB).stairs(MBBlocks.GRANITE_TILE_STAIRS)
            .wall(MBBlocks.GRANITE_TILE_WALL).cracked(MBBlocks.CRACKED_GRANITE_TILES).child(CRACKED_GRANITE_TILES).build();
    public static final MBBlockFamily CRACKED_GRANITE_BRICKS = register(MBBlocks.CRACKED_GRANITE_BRICKS)
            .slab(MBBlocks.CRACKED_GRANITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_GRANITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_GRANITE_BRICKS = register(MBBlocks.MOSSY_GRANITE_BRICKS)
            .slab(MBBlocks.MOSSY_GRANITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_GRANITE_BRICK_WALL).build();
    public static final MBBlockFamily GRANITE_BRICKS = register(MBBlocks.GRANITE_BRICKS)
            .slab(MBBlocks.GRANITE_BRICK_SLAB).stairs(MBBlocks.GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.GRANITE_BRICK_WALL).cracked(MBBlocks.CRACKED_GRANITE_BRICKS).child(CRACKED_GRANITE_BRICKS).child(GRANITE_TILES)
            .polished(MBBlocks.GRANITE_TILES).build();
    public static final MBBlockFamily POLISHED_GRANITE_MB = register(Blocks.POLISHED_GRANITE).polished(MBBlocks.GRANITE_BRICKS).child(GRANITE_BRICKS).build();
    public static final MBBlockFamily COBBLED_GRANITE = register(MBBlocks.COBBLED_GRANITE)
            .slab(MBBlocks.COBBLED_GRANITE_SLAB).stairs(MBBlocks.COBBLED_GRANITE_STAIRS)
            .wall(MBBlocks.COBBLED_GRANITE_WALL).build();
    public static final MBBlockFamily GRANITE_MB = register(Blocks.GRANITE).slab(Blocks.GRANITE_SLAB)
            .chiseled(MBBlocks.CHISELED_GRANITE).pillar(MBBlocks.GRANITE_PILLAR).child(COBBLED_GRANITE).child(POLISHED_GRANITE_MB).build();


    public static final MBBlockFamily CRACKED_SANDSTONE_TILES = register(MBBlocks.CRACKED_SANDSTONE_TILES)
            .slab(MBBlocks.CRACKED_SANDSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_SANDSTONE_TILE_WALL).build();
    public static final MBBlockFamily SANDSTONE_TILES = register(MBBlocks.SANDSTONE_TILES)
            .slab(MBBlocks.SANDSTONE_TILE_SLAB).stairs(MBBlocks.SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.SANDSTONE_TILE_WALL).child(CRACKED_SANDSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_SANDSTONE_BRICKS = register(MBBlocks.CRACKED_SANDSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_SANDSTONE_BRICK_WALL).build();
    public static final MBBlockFamily SANDSTONE_BRICKS = register(MBBlocks.SANDSTONE_BRICKS)
            .slab(MBBlocks.SANDSTONE_BRICK_SLAB).stairs(MBBlocks.SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.SANDSTONE_BRICK_WALL).cracked(MBBlocks.CRACKED_SANDSTONE_BRICKS)
            .polished(MBBlocks.SANDSTONE_TILES).child(CRACKED_SANDSTONE_BRICKS).child(SANDSTONE_TILES).build();
    public static final MBBlockFamily CUT_SANDSTONE_MB = register(Blocks.CUT_SANDSTONE).polished(MBBlocks.SANDSTONE_BRICKS).child(SANDSTONE_BRICKS).build();
    public static final MBBlockFamily SANDSTONE_MB = register(Blocks.SANDSTONE).slab(Blocks.SANDSTONE_SLAB)
            .chiseled(Blocks.CHISELED_SANDSTONE).pillar(MBBlocks.SANDSTONE_PILLAR).child(SANDSTONE_BRICKS).build();

    public static final MBBlockFamily CRACKED_RED_SANDSTONE_TILES = register(MBBlocks.CRACKED_RED_SANDSTONE_TILES)
            .slab(MBBlocks.CRACKED_RED_SANDSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_RED_SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_RED_SANDSTONE_TILE_WALL).build();
    public static final MBBlockFamily RED_SANDSTONE_TILES = register(MBBlocks.RED_SANDSTONE_TILES)
            .slab(MBBlocks.RED_SANDSTONE_TILE_SLAB).stairs(MBBlocks.RED_SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.RED_SANDSTONE_TILE_WALL).child(CRACKED_RED_SANDSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_RED_SANDSTONE_BRICKS = register(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_WALL).build();
    public static final MBBlockFamily RED_SANDSTONE_BRICKS = register(MBBlocks.RED_SANDSTONE_BRICKS)
            .slab(MBBlocks.RED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.RED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.RED_SANDSTONE_BRICK_WALL).cracked(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)
            .polished(MBBlocks.RED_SANDSTONE_TILES).child(CRACKED_RED_SANDSTONE_BRICKS).child(RED_SANDSTONE_TILES).build();
    public static final MBBlockFamily CUT_RED_SANDSTONE_MB = register(Blocks.CUT_RED_SANDSTONE).polished(MBBlocks.RED_SANDSTONE_BRICKS).child(RED_SANDSTONE_BRICKS).build();
    public static final MBBlockFamily RED_SANDSTONE_MB = register(Blocks.RED_SANDSTONE).slab(Blocks.RED_SANDSTONE_SLAB)
            .chiseled(Blocks.CHISELED_RED_SANDSTONE).pillar(MBBlocks.RED_SANDSTONE_PILLAR).child(RED_SANDSTONE_BRICKS).build();


    public static final MBBlockFamily CRACKED_TUFF_TILES = register(MBBlocks.CRACKED_TUFF_TILES)
            .slab(MBBlocks.CRACKED_TUFF_TILE_SLAB).stairs(MBBlocks.CRACKED_TUFF_TILE_STAIRS).wall(MBBlocks.CRACKED_TUFF_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_TUFF_TILES = register(MBBlocks.MOSSY_TUFF_TILES)
            .slab(MBBlocks.MOSSY_TUFF_TILE_SLAB).stairs(MBBlocks.MOSSY_TUFF_TILE_STAIRS).wall(MBBlocks.MOSSY_TUFF_TILE_WALL).build();
    public static final MBBlockFamily TUFF_TILES = register(MBBlocks.TUFF_TILES)
            .slab(MBBlocks.TUFF_TILE_SLAB).stairs(MBBlocks.TUFF_TILE_STAIRS).wall(MBBlocks.TUFF_TILE_WALL)
            .cracked(MBBlocks.CRACKED_TUFF_TILES).mossy(MBBlocks.MOSSY_TUFF_TILES).child(CRACKED_TUFF_TILES).build();

    public static final MBBlockFamily CRACKED_TUFF_BRICKS = register(MBBlocks.CRACKED_TUFF_BRICKS)
            .slab(MBBlocks.CRACKED_TUFF_BRICK_SLAB).stairs(MBBlocks.CRACKED_TUFF_BRICK_STAIRS).wall(MBBlocks.CRACKED_TUFF_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_TUFF_BRICKS = register(MBBlocks.MOSSY_TUFF_BRICKS)
            .slab(MBBlocks.MOSSY_TUFF_BRICK_SLAB).stairs(MBBlocks.MOSSY_TUFF_BRICK_STAIRS).wall(MBBlocks.MOSSY_TUFF_BRICK_WALL).build();
    public static final MBBlockFamily TUFF_BRICKS = register(MBBlocks.TUFF_BRICKS)
            .slab(MBBlocks.TUFF_BRICK_SLAB).stairs(MBBlocks.TUFF_BRICK_STAIRS).wall(MBBlocks.TUFF_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_TUFF_BRICKS).mossy(MBBlocks.MOSSY_TUFF_BRICKS).polished(MBBlocks.TUFF_TILES).child(CRACKED_TUFF_BRICKS).child(TUFF_TILES).build();

    public static final MBBlockFamily POLISHED_TUFF = register(MBBlocks.POLISHED_TUFF)
            .slab(MBBlocks.POLISHED_TUFF_SLAB).stairs(MBBlocks.POLISHED_TUFF_STAIRS).wall(MBBlocks.POLISHED_TUFF_WALL)
            .polished(MBBlocks.TUFF_BRICKS).child(TUFF_BRICKS).build();
    public static final MBBlockFamily TUFF_MB = register(Blocks.TUFF)
            .slab(MBBlocks.TUFF_SLAB).stairs(MBBlocks.TUFF_STAIRS).wall(MBBlocks.TUFF_WALL)
            .polished(MBBlocks.POLISHED_TUFF).chiseled(MBBlocks.CHISELED_TUFF).pillar(MBBlocks.TUFF_PILLAR).child(POLISHED_TUFF).build();

    public static final MBBlockFamily CRACKED_CALCITE_TILES = register(MBBlocks.CRACKED_CALCITE_TILES)
            .slab(MBBlocks.CRACKED_CALCITE_TILE_SLAB).stairs(MBBlocks.CRACKED_CALCITE_TILE_STAIRS).wall(MBBlocks.CRACKED_CALCITE_TILE_WALL).build();
    public static final MBBlockFamily CALCITE_TILES = register(MBBlocks.CALCITE_TILES)
            .slab(MBBlocks.CALCITE_TILE_SLAB).stairs(MBBlocks.CALCITE_TILE_STAIRS).wall(MBBlocks.CALCITE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_CALCITE_TILES).child(CRACKED_CALCITE_TILES).build();

    public static final MBBlockFamily CRACKED_CALCITE_BRICKS = register(MBBlocks.CRACKED_CALCITE_BRICKS)
            .slab(MBBlocks.CRACKED_CALCITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_CALCITE_BRICK_STAIRS).wall(MBBlocks.CRACKED_CALCITE_BRICK_WALL).build();
    public static final MBBlockFamily CALCITE_BRICKS = register(MBBlocks.CALCITE_BRICKS)
            .slab(MBBlocks.CALCITE_BRICK_SLAB).stairs(MBBlocks.CALCITE_BRICK_STAIRS).wall(MBBlocks.CALCITE_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_CALCITE_BRICKS).polished(MBBlocks.CALCITE_TILES).child(CRACKED_CALCITE_BRICKS).child(CALCITE_TILES).build();

    public static final MBBlockFamily POLISHED_CALCITE = register(MBBlocks.POLISHED_CALCITE)
            .slab(MBBlocks.POLISHED_CALCITE_SLAB).stairs(MBBlocks.POLISHED_CALCITE_STAIRS).wall(MBBlocks.POLISHED_CALCITE_WALL)
            .polished(MBBlocks.CALCITE_BRICKS).child(CALCITE_BRICKS).build();
    public static final MBBlockFamily CALCITE_MB = register(Blocks.CALCITE)
            .slab(MBBlocks.CALCITE_SLAB).stairs(MBBlocks.CALCITE_STAIRS).wall(MBBlocks.CALCITE_WALL)
            .polished(MBBlocks.POLISHED_CALCITE).chiseled(MBBlocks.CHISELED_CALCITE).pillar(MBBlocks.CALCITE_PILLAR).child(POLISHED_CALCITE).build();

    public static final MBBlockFamily CRACKED_DRIPSTONE_TILES = register(MBBlocks.CRACKED_DRIPSTONE_TILES)
            .slab(MBBlocks.CRACKED_DRIPSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_DRIPSTONE_TILE_STAIRS).wall(MBBlocks.CRACKED_DRIPSTONE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_DRIPSTONE_TILES = register(MBBlocks.MOSSY_DRIPSTONE_TILES)
            .slab(MBBlocks.MOSSY_DRIPSTONE_TILE_SLAB).stairs(MBBlocks.MOSSY_DRIPSTONE_TILE_STAIRS).wall(MBBlocks.MOSSY_DRIPSTONE_TILE_WALL).build();
    public static final MBBlockFamily DRIPSTONE_TILES = register(MBBlocks.DRIPSTONE_TILES)
            .slab(MBBlocks.DRIPSTONE_TILE_SLAB).stairs(MBBlocks.DRIPSTONE_TILE_STAIRS).wall(MBBlocks.DRIPSTONE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_DRIPSTONE_TILES).mossy(MBBlocks.MOSSY_DRIPSTONE_TILES).child(CRACKED_DRIPSTONE_TILES).build();

    public static final MBBlockFamily CRACKED_DRIPSTONE_BRICKS = register(MBBlocks.CRACKED_DRIPSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.CRACKED_DRIPSTONE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_DRIPSTONE_BRICKS = register(MBBlocks.MOSSY_DRIPSTONE_BRICKS)
            .slab(MBBlocks.MOSSY_DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.MOSSY_DRIPSTONE_BRICK_WALL).build();
    public static final MBBlockFamily DRIPSTONE_BRICKS = register(MBBlocks.DRIPSTONE_BRICKS)
            .slab(MBBlocks.DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.DRIPSTONE_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_DRIPSTONE_BRICKS).mossy(MBBlocks.MOSSY_DRIPSTONE_BRICKS).polished(MBBlocks.DRIPSTONE_TILES).child(CRACKED_DRIPSTONE_BRICKS).child(DRIPSTONE_TILES).build();

    public static final MBBlockFamily POLISHED_DRIPSTONE = register(MBBlocks.POLISHED_DRIPSTONE)
            .slab(MBBlocks.POLISHED_DRIPSTONE_SLAB).stairs(MBBlocks.POLISHED_DRIPSTONE_STAIRS).wall(MBBlocks.POLISHED_DRIPSTONE_WALL)
            .polished(MBBlocks.DRIPSTONE_BRICKS).child(DRIPSTONE_BRICKS).build();
    public static final MBBlockFamily DRIPSTONE_MB = register(Blocks.DRIPSTONE_BLOCK)
            .slab(MBBlocks.DRIPSTONE_SLAB).stairs(MBBlocks.DRIPSTONE_STAIRS).wall(MBBlocks.DRIPSTONE_WALL)
            .polished(MBBlocks.POLISHED_DRIPSTONE).chiseled(MBBlocks.CHISELED_DRIPSTONE).pillar(MBBlocks.DRIPSTONE_PILLAR).child(POLISHED_DRIPSTONE).build();

    public static final MBBlockFamily MOSSY_COBBLED_DEEPSLATE = register(MBBlocks.MOSSY_COBBLED_DEEPSLATE).slab(MBBlocks.MOSSY_COBBLED_DEEPSLATE_SLAB)
            .stairs(MBBlocks.MOSSY_COBBLED_DEEPSLATE_STAIRS).wall(MBBlocks.MOSSY_COBBLED_DEEPSLATE_WALL).build();
    public static final MBBlockFamily DEEPSLATE_BRICKS_MB = register(Blocks.DEEPSLATE_BRICKS)
            .mossy(MBBlocks.MOSSY_DEEPSLATE_BRICKS).build();
    public static final MBBlockFamily MOSSY_DEEPSLATE_BRICKS = register(MBBlocks.MOSSY_DEEPSLATE_BRICKS)
            .slab(MBBlocks.MOSSY_DEEPSLATE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS).wall(MBBlocks.MOSSY_DEEPSLATE_BRICK_WALL).build();
    public static final MBBlockFamily DEEPSLATE_TILES_MB = register(Blocks.DEEPSLATE_TILES)
            .mossy(MBBlocks.MOSSY_DEEPSLATE_TILES).build();
    public static final MBBlockFamily MOSSY_DEEPSLATE_TILES = register(MBBlocks.MOSSY_DEEPSLATE_TILES)
            .slab(MBBlocks.MOSSY_DEEPSLATE_TILE_SLAB).stairs(MBBlocks.MOSSY_DEEPSLATE_TILE_STAIRS).wall(MBBlocks.MOSSY_DEEPSLATE_TILE_WALL).build();
    public static final MBBlockFamily C_DEEPSLATE_MB = register(Blocks.COBBLED_DEEPSLATE).slab(Blocks.COBBLED_DEEPSLATE_SLAB)
            .mossy(MBBlocks.MOSSY_COBBLED_DEEPSLATE).chiseled(Blocks.CHISELED_DEEPSLATE).pillar(MBBlocks.DEEPSLATE_PILLAR).build();
    public static final MBBlockFamily SMOOTH_DEEPSLATE = register(MBBlocks.SMOOTH_DEEPSLATE).slab(MBBlocks.SMOOTH_DEEPSLATE_SLAB)
            .stairs(MBBlocks.SMOOTH_DEEPSLATE_STAIRS).wall(MBBlocks.SMOOTH_DEEPSLATE_WALL).build();
    public static final MBBlockFamily DEEPSLATE_MB = register(Blocks.DEEPSLATE).child(C_DEEPSLATE_MB).child(BlockFamilies.COBBLED_DEEPSLATE).build();

	public static final MBBlockFamily GILDED_BLACKSTONE_BRICKS = register(MBBlocks.GILDED_BLACKSTONE_BRICKS)
			.slab(MBBlocks.GILDED_BLACKSTONE_BRICK_SLAB).stairs(MBBlocks.GILDED_BLACKSTONE_BRICK_STAIRS).wall(MBBlocks.GILDED_BLACKSTONE_BRICK_WALL).build();
	public static final MBBlockFamily SMOOTH_BLACKSTONE = register(MBBlocks.SMOOTH_BLACKSTONE).slab(MBBlocks.SMOOTH_BLACKSTONE_SLAB)
			.stairs(MBBlocks.SMOOTH_BLACKSTONE_STAIRS).wall(MBBlocks.SMOOTH_BLACKSTONE_WALL).build();
	public static final MBBlockFamily POLISHED_BLACKSTONE_MB = register(Blocks.POLISHED_BLACKSTONE)
			.slab(Blocks.POLISHED_BLACKSTONE_SLAB).chiseled(Blocks.CHISELED_POLISHED_BLACKSTONE)
			.pillar(MBBlocks.BLACKSTONE_PILLAR).build();

    public static final MBBlockFamily CRACKED_PRISMARINE_TILES = register(MBBlocks.CRACKED_PRISMARINE_TILES)
            .slab(MBBlocks.CRACKED_PRISMARINE_TILE_SLAB).stairs(MBBlocks.CRACKED_PRISMARINE_TILE_STAIRS).wall(MBBlocks.CRACKED_PRISMARINE_TILE_WALL).build();
    public static final MBBlockFamily PRISMARINE_TILES = register(MBBlocks.PRISMARINE_TILES).child(CRACKED_PRISMARINE_TILES)
            .slab(MBBlocks.PRISMARINE_TILE_SLAB).stairs(MBBlocks.PRISMARINE_TILE_STAIRS).wall(MBBlocks.PRISMARINE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_PRISMARINE_TILES).build();

    public static final MBBlockFamily CRACKED_PRISMARINE_BRICKS = register(MBBlocks.CRACKED_PRISMARINE_BRICKS)
            .slab(MBBlocks.CRACKED_PRISMARINE_BRICK_SLAB).stairs(MBBlocks.CRACKED_PRISMARINE_BRICK_STAIRS).wall(MBBlocks.CRACKED_PRISMARINE_BRICK_WALL).build();
    public static final MBBlockFamily PRISMARINE_BRICKS_MB = register(Blocks.PRISMARINE_BRICKS)
            .cracked(MBBlocks.CRACKED_PRISMARINE_BRICKS).child(CRACKED_PRISMARINE_BRICKS).child(PRISMARINE_TILES)
            .polished(MBBlocks.PRISMARINE_TILES).build();

    public static final MBBlockFamily POLISHED_PRISMARINE = register(MBBlocks.POLISHED_PRISMARINE).child(CRACKED_PRISMARINE_BRICKS)
            .slab(MBBlocks.POLISHED_PRISMARINE_SLAB).stairs(MBBlocks.POLISHED_PRISMARINE_STAIRS).wall(MBBlocks.POLISHED_PRISMARINE_WALL)
            .polished(Blocks.PRISMARINE_BRICKS).build();
    public static final MBBlockFamily SMOOTH_PRISMARINE = register(MBBlocks.SMOOTH_PRISMARINE)
            .slab(MBBlocks.SMOOTH_PRISMARINE_SLAB).stairs(MBBlocks.SMOOTH_PRISMARINE_STAIRS).wall(MBBlocks.SMOOTH_PRISMARINE_WALL)
            .cut(MBBlocks.CUT_PRISMARINE).build();
    public static final MBBlockFamily PRISMARINE_MB = register(Blocks.PRISMARINE).slab(Blocks.PRISMARINE_SLAB)
            .chiseled(MBBlocks.CHISELED_PRISMARINE).pillar(MBBlocks.PRISMARINE_PILLAR).child(SMOOTH_PRISMARINE).child(POLISHED_PRISMARINE)
            .polished(MBBlocks.POLISHED_PRISMARINE).cracked(MBBlocks.SMOOTH_PRISMARINE).build();

    public static final MBBlockFamily S_BASALT_MB = register(Blocks.SMOOTH_BASALT)
            .slab(MBBlocks.SMOOTH_BASALT_SLAB).stairs(MBBlocks.SMOOTH_BASALT_STAIRS).wall(MBBlocks.SMOOTH_BASALT_WALL).build();
    public static final MBBlockFamily P_BASALT_MB = register(Blocks.POLISHED_BASALT).polished(MBBlocks.BASALT_BRICKS).stonecut(MBBlocks.CHISELED_BASALT).build();
    public static final MBBlockFamily BASALT_BRICKS = register(MBBlocks.BASALT_BRICKS)
            .slab(MBBlocks.BASALT_BRICK_SLAB).stairs(MBBlocks.BASALT_BRICK_STAIRS).wall(MBBlocks.BASALT_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_BASALT_BRICKS).polished(MBBlocks.BASALT_TILES).build();
    public static final MBBlockFamily CRACKED_BASALT_BRICKS = register(MBBlocks.CRACKED_BASALT_BRICKS)
            .slab(MBBlocks.CRACKED_BASALT_BRICK_SLAB).stairs(MBBlocks.CRACKED_BASALT_BRICK_STAIRS).wall(MBBlocks.CRACKED_BASALT_BRICK_WALL).build();
    public static final MBBlockFamily BASALT_TILES = register(MBBlocks.BASALT_TILES)
            .slab(MBBlocks.BASALT_TILE_SLAB).stairs(MBBlocks.BASALT_TILE_STAIRS).wall(MBBlocks.BASALT_TILE_WALL)
            .cracked(MBBlocks.CRACKED_BASALT_TILES).build();
    public static final MBBlockFamily CRACKED_BASALT_TILES = register(MBBlocks.CRACKED_BASALT_TILES)
            .slab(MBBlocks.CRACKED_BASALT_TILE_SLAB).stairs(MBBlocks.CRACKED_BASALT_TILE_STAIRS).wall(MBBlocks.CRACKED_BASALT_TILE_WALL).build();

    public static MBBlockFamily.Builder register(Block baseBlock) {
        MBBlockFamily.Builder builder = new MBBlockFamily.Builder(baseBlock);
        MBBlockFamily blockFamily = BASE_BLOCKS_TO_FAMILIES.put(baseBlock, builder.build());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + Registry.BLOCK.getId(baseBlock));
        }
        return builder;
    }

    public static Stream<MBBlockFamily> getFamilies() {
        return BASE_BLOCKS_TO_FAMILIES.values().stream();
    }

}
