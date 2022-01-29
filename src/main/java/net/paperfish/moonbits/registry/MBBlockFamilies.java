package net.paperfish.moonbits.registry;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBItemGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MBBlockFamilies {
    private static final Map<Block, MBBlockFamily> BASE_BLOCKS_TO_FAMILIES = Maps.newHashMap();

    public static final MBBlockFamily ACACIA = register(Blocks.ACACIA_PLANKS)
            .bookshelf(MBBlocks.ACACIA_BOOKSHELF)
            .planterBox(MBBlocks.ACACIA_PLANTER_BOX)
            .stonecut(MBBlocks.ACACIA_BOARDS).stonecut(MBBlocks.ACACIA_PANEL)
            .column(MBBlocks.ACACIA_PILLAR, MBBlocks.CARVED_ACACIA)
            .stonecut(List.of(Blocks.ACACIA_SLAB, Blocks.ACACIA_STAIRS, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_SIGN, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily BIRCH = register(Blocks.BIRCH_PLANKS)
            .bookshelf(MBBlocks.BIRCH_BOOKSHELF)
            .planterBox(MBBlocks.BIRCH_PLANTER_BOX)
            .stonecut(MBBlocks.BIRCH_BOARDS).stonecut(MBBlocks.BIRCH_PANEL)
            .column(MBBlocks.BIRCH_PILLAR, MBBlocks.CARVED_BIRCH)
            .stonecut(List.of(Blocks.BIRCH_SLAB, Blocks.BIRCH_STAIRS, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_SIGN, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily CRIMSON = register(Blocks.CRIMSON_PLANKS)
            .bookshelf(MBBlocks.CRIMSON_BOOKSHELF)
            .planterBox(MBBlocks.CRIMSON_PLANTER_BOX)
            .stonecut(MBBlocks.CRIMSON_BOARDS).stonecut(MBBlocks.CRIMSON_PANEL)
            .column(MBBlocks.CRIMSON_PILLAR, MBBlocks.CARVED_CRIMSON)
            .stonecut(List.of(Blocks.CRIMSON_SLAB, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_FENCE, Blocks.CRIMSON_FENCE_GATE, Blocks.CRIMSON_SIGN, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily JUNGLE = register(Blocks.JUNGLE_PLANKS)
            .bookshelf(MBBlocks.JUNGLE_BOOKSHELF)
            .planterBox(MBBlocks.JUNGLE_PLANTER_BOX)
            .stonecut(MBBlocks.JUNGLE_BOARDS).stonecut(MBBlocks.JUNGLE_PANEL)
            .column(MBBlocks.JUNGLE_PILLAR, MBBlocks.CARVED_JUNGLE)
            .stonecut(List.of(Blocks.JUNGLE_SLAB, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_SIGN, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily OAK = register(Blocks.OAK_PLANKS)
            .planterBox(MBBlocks.OAK_PLANTER_BOX)
            .stonecut(MBBlocks.OAK_BOARDS).stonecut(MBBlocks.OAK_PANEL)
            .column(MBBlocks.OAK_PILLAR, MBBlocks.CARVED_OAK)
            .stonecut(List.of(Blocks.OAK_SLAB, Blocks.OAK_STAIRS, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_SIGN, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily DARK_OAK = register(Blocks.DARK_OAK_PLANKS)
            .bookshelf(MBBlocks.DARK_OAK_BOOKSHELF)
            .planterBox(MBBlocks.DARK_OAK_PLANTER_BOX)
            .stonecut(MBBlocks.DARK_OAK_BOARDS).stonecut(MBBlocks.DARK_OAK_PANEL)
            .column(MBBlocks.DARK_OAK_PILLAR, MBBlocks.CARVED_DARK_OAK)
            .stonecut(List.of(Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily SPRUCE = register(Blocks.SPRUCE_PLANKS)
            .bookshelf(MBBlocks.SPRUCE_BOOKSHELF)
            .planterBox(MBBlocks.SPRUCE_PLANTER_BOX)
            .stonecut(MBBlocks.SPRUCE_BOARDS).stonecut(MBBlocks.SPRUCE_PANEL)
            .column(MBBlocks.SPRUCE_PILLAR, MBBlocks.CARVED_SPRUCE)
            .stonecut(List.of(Blocks.SPRUCE_SLAB, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_SIGN, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();
    public static final MBBlockFamily WARPED = register(Blocks.WARPED_PLANKS)
            .bookshelf(MBBlocks.WARPED_BOOKSHELF)
            .planterBox(MBBlocks.WARPED_PLANTER_BOX)
            .stonecut(MBBlocks.WARPED_BOARDS).stonecut(MBBlocks.WARPED_PANEL)
            .column(MBBlocks.WARPED_PILLAR, MBBlocks.CARVED_WARPED)
            .stonecut(List.of(Blocks.WARPED_SLAB, Blocks.WARPED_STAIRS, Blocks.WARPED_FENCE, Blocks.WARPED_FENCE_GATE, Blocks.WARPED_SIGN, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_TRAPDOOR))
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();


    public static final MBBlockFamily JACARANDA = register(MBBlocks.JACARANDA_PLANKS)
            .slab(MBBlocks.JACARANDA_SLAB).stairs(MBBlocks.JACARANDA_STAIRS)
            .fence(MBBlocks.JACARANDA_FENCE).fenceGate(MBBlocks.JACARANDA_FENCE_GATE)
            .door(MBBlocks.JACARANDA_DOOR).trapdoor(MBBlocks.JACARANDA_TRAPDOOR)
            .sign(MBBlocks.JACARANDA_SIGN, MBBlocks.JACARANDA_WALL_SIGN)
            .pressurePlate(MBBlocks.JACARANDA_PRESSURE_PLATE).button(MBBlocks.JACARANDA_BUTTON)
            .bookshelf(MBBlocks.JACARANDA_BOOKSHELF)
            .planterBox(MBBlocks.JACARANDA_PLANTER_BOX)
            .stonecut(MBBlocks.JACARANDA_BOARDS).stonecut(MBBlocks.JACARANDA_PANEL)
            .column(MBBlocks.JACARANDA_PILLAR, MBBlocks.CARVED_JACARANDA)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

    public static final MBBlockFamily HONEY = register(MBBlocks.HONEY_PLANKS)
            .slab(MBBlocks.HONEY_SLAB).stairs(MBBlocks.HONEY_STAIRS)
            .fence(MBBlocks.HONEY_FENCE).fenceGate(MBBlocks.HONEY_FENCE_GATE)
            .pressurePlate(MBBlocks.HONEY_PRESSURE_PLATE).button(MBBlocks.HONEY_BUTTON)
            .door(MBBlocks.HONEY_DOOR).trapdoor(MBBlocks.HONEY_TRAPDOOR)
            .sign(MBBlocks.HONEY_SIGN, MBBlocks.HONEY_WALL_SIGN)
            .bookshelf(MBBlocks.HONEY_BOOKSHELF)
            .planterBox(MBBlocks.HONEY_PLANTER_BOX)
            .stonecut(MBBlocks.HONEY_BOARDS).stonecut(MBBlocks.HONEY_PANEL)
            .column(MBBlocks.HONEY_PILLAR, MBBlocks.CARVED_HONEY)
            .build();

    public static final MBBlockFamily HONEYCOMB = register(Blocks.HONEYCOMB_BLOCK)
            .slab(MBBlocks.HONEYCOMB_SLAB).stairs(MBBlocks.HONEYCOMB_STAIRS)
            .wall(MBBlocks.HONEYCOMB_WALL)
            .polished(MBBlocks.POLISHED_HONEYCOMB).build();
    public static final MBBlockFamily HONEYCOMB_BRICKS = register(MBBlocks.HONEYCOMB_BRICKS)
            .slab(MBBlocks.HONEYCOMB_BRICK_SLAB).stairs(MBBlocks.HONEYCOMB_BRICK_STAIRS)
            .wall(MBBlocks.HONEYCOMB_BRICK_WALL).chiseled(MBBlocks.CHISELED_HONEYCOMB_BRICKS).pillar(MBBlocks.HONEYCOMB_PILLAR)
            .polished(MBBlocks.HONEYCOMB_TILES).build();
    public static final MBBlockFamily HONEYCOMB_TILES = register(MBBlocks.HONEYCOMB_TILES)
            .slab(MBBlocks.HONEYCOMB_TILE_SLAB).stairs(MBBlocks.HONEYCOMB_TILE_STAIRS)
            .wall(MBBlocks.HONEYCOMB_TILE_WALL).build();

    public static final MBBlockFamily DIRT_MB = register(Blocks.DIRT).polished(MBBlocks.TOUGH_DIRT).build();
    public static final MBBlockFamily TOUGH_DIRT = register(MBBlocks.TOUGH_DIRT)
            .slab(MBBlocks.TOUGH_DIRT_SLAB).stairs(MBBlocks.TOUGH_DIRT_STAIRS).polished(MBBlocks.DIRT_BRICKS).build();
    public static final MBBlockFamily DIRT_BRICKS = register(MBBlocks.DIRT_BRICKS)
            .slab(MBBlocks.DIRT_BRICK_SLAB).stairs(MBBlocks.DIRT_BRICK_STAIRS).build();

    public static final MBBlockFamily PEAT_BRICKS = register(MBBlocks.PEAT_BRICKS)
            .slab(MBBlocks.PEAT_BRICK_SLAB).stairs(MBBlocks.PEAT_BRICK_STAIRS)
            .wall(MBBlocks.PEAT_BRICK_WALL).build();


    public static final MBBlockFamily GRASS_TURF = register(MBBlocks.GRASS_TURF)
            .slab(MBBlocks.GRASS_TURF_SLAB).stairs(MBBlocks.GRASS_TURF_STAIRS)
            .carpet(MBBlocks.GRASS_CARPET).build();

    public static final MBBlockFamily CRIMSON_NYLIUM_TURF = register(MBBlocks.CRIMSON_NYLIUM_TURF)
            .slab(MBBlocks.CRIMSON_NYLIUM_TURF_SLAB).stairs(MBBlocks.CRIMSON_NYLIUM_TURF_STAIRS)
            .carpet(MBBlocks.CRIMSON_NYLIUM_CARPET).build();
    public static final MBBlockFamily WARPED_NYLIUM_TURF = register(MBBlocks.WARPED_NYLIUM_TURF)
            .slab(MBBlocks.WARPED_NYLIUM_TURF_SLAB).stairs(MBBlocks.WARPED_NYLIUM_TURF_STAIRS)
            .carpet(MBBlocks.WARPED_NYLIUM_CARPET).build();


    public static final MBBlockFamily STONE_BRICKS_MB = register(Blocks.STONE_BRICKS).slab(Blocks.STONE_BRICK_SLAB).chiseled(Blocks.CHISELED_STONE_BRICKS).pillar(MBBlocks.STONE_PILLAR).polished(MBBlocks.STONE_TILES).build();
    public static final MBBlockFamily STONE_TILES = register(MBBlocks.STONE_TILES)
            .slab(MBBlocks.STONE_TILE_SLAB).stairs(MBBlocks.STONE_TILE_STAIRS)
            .wall(MBBlocks.STONE_TILE_WALL).cracked(MBBlocks.CRACKED_STONE_TILES).build();
    public static final MBBlockFamily CRACKED_STONE_TILES = register(MBBlocks.CRACKED_STONE_TILES)
            .slab(MBBlocks.CRACKED_STONE_TILE_SLAB).stairs(MBBlocks.CRACKED_STONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_STONE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_STONE_TILES = register(MBBlocks.MOSSY_STONE_TILES)
            .slab(MBBlocks.MOSSY_STONE_TILE_SLAB).stairs(MBBlocks.MOSSY_STONE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_STONE_TILE_WALL).build();
    public static final MBBlockFamily SMOOTH_STONE_MB = register(Blocks.SMOOTH_STONE).stairs(MBBlocks.SMOOTH_STONE_STAIRS).wall(MBBlocks.SMOOTH_STONE_WALL).build();


    public static final MBBlockFamily COBBLED_ANDESITE = register(MBBlocks.COBBLED_ANDESITE)
            .slab(MBBlocks.COBBLED_ANDESITE_SLAB).stairs(MBBlocks.COBBLED_ANDESITE_STAIRS)
            .wall(MBBlocks.COBBLED_ANDESITE_WALL).cracked(Blocks.ANDESITE).build();
    public static final MBBlockFamily ANDESITE_MB = register(Blocks.ANDESITE).slab(Blocks.ANDESITE_SLAB).chiseled(MBBlocks.CHISELED_ANDESITE).pillar(MBBlocks.ANDESITE_PILLAR).build();
    public static final MBBlockFamily POLISHED_ANDESITE_MB = register(Blocks.POLISHED_ANDESITE).polished(MBBlocks.ANDESITE_BRICKS).build();
    public static final MBBlockFamily ANDESITE_BRICKS = register(MBBlocks.ANDESITE_BRICKS)
            .slab(MBBlocks.ANDESITE_BRICK_SLAB).stairs(MBBlocks.ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.ANDESITE_BRICK_WALL).cracked(MBBlocks.CRACKED_ANDESITE_BRICKS)
            .polished(MBBlocks.ANDESITE_TILES).build();
    public static final MBBlockFamily CRACKED_ANDESITE_BRICKS = register(MBBlocks.CRACKED_ANDESITE_BRICKS)
            .slab(MBBlocks.CRACKED_ANDESITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_ANDESITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_ANDESITE_BRICKS = register(MBBlocks.MOSSY_ANDESITE_BRICKS)
            .slab(MBBlocks.MOSSY_ANDESITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_ANDESITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_ANDESITE_BRICK_WALL).build();
    public static final MBBlockFamily ANDESITE_TILES = register(MBBlocks.ANDESITE_TILES)
            .slab(MBBlocks.ANDESITE_TILE_SLAB).stairs(MBBlocks.ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.ANDESITE_TILE_WALL).cracked(MBBlocks.CRACKED_ANDESITE_TILES).build();
    public static final MBBlockFamily CRACKED_ANDESITE_TILES = register(MBBlocks.CRACKED_ANDESITE_TILES)
            .slab(MBBlocks.CRACKED_ANDESITE_TILE_SLAB).stairs(MBBlocks.CRACKED_ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_ANDESITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_ANDESITE_TILES = register(MBBlocks.MOSSY_ANDESITE_TILES)
            .slab(MBBlocks.MOSSY_ANDESITE_TILE_SLAB).stairs(MBBlocks.MOSSY_ANDESITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_ANDESITE_TILE_WALL).build();

    public static final MBBlockFamily COBBLED_DIORITE = register(MBBlocks.COBBLED_DIORITE)
            .slab(MBBlocks.COBBLED_DIORITE_SLAB).stairs(MBBlocks.COBBLED_DIORITE_STAIRS)
            .wall(MBBlocks.COBBLED_DIORITE_WALL).cracked(Blocks.DIORITE).build();
    public static final MBBlockFamily DIORITE_MB = register(Blocks.DIORITE).slab(Blocks.DIORITE_SLAB).chiseled(MBBlocks.CHISELED_DIORITE).pillar(MBBlocks.DIORITE_PILLAR).build();
    public static final MBBlockFamily POLISHED_DIORITE_MB = register(Blocks.POLISHED_DIORITE).polished(MBBlocks.DIORITE_BRICKS).build();
    public static final MBBlockFamily DIORITE_BRICKS = register(MBBlocks.DIORITE_BRICKS)
            .slab(MBBlocks.DIORITE_BRICK_SLAB).stairs(MBBlocks.DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.DIORITE_BRICK_WALL).cracked(MBBlocks.CRACKED_DIORITE_BRICKS)
            .polished(MBBlocks.DIORITE_TILES).build();
    public static final MBBlockFamily CRACKED_DIORITE_BRICKS = register(MBBlocks.CRACKED_DIORITE_BRICKS)
            .slab(MBBlocks.CRACKED_DIORITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_DIORITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_DIORITE_BRICKS = register(MBBlocks.MOSSY_DIORITE_BRICKS)
            .slab(MBBlocks.MOSSY_DIORITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DIORITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_DIORITE_BRICK_WALL).build();
    public static final MBBlockFamily DIORITE_TILES = register(MBBlocks.DIORITE_TILES)
            .slab(MBBlocks.DIORITE_TILE_SLAB).stairs(MBBlocks.DIORITE_TILE_STAIRS)
            .wall(MBBlocks.DIORITE_TILE_WALL).cracked(MBBlocks.CRACKED_DIORITE_TILES).build();
    public static final MBBlockFamily CRACKED_DIORITE_TILES = register(MBBlocks.CRACKED_DIORITE_TILES)
            .slab(MBBlocks.CRACKED_DIORITE_TILE_SLAB).stairs(MBBlocks.CRACKED_DIORITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_DIORITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_DIORITE_TILES = register(MBBlocks.MOSSY_DIORITE_TILES)
            .slab(MBBlocks.MOSSY_DIORITE_TILE_SLAB).stairs(MBBlocks.MOSSY_DIORITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_DIORITE_TILE_WALL).build();

    public static final MBBlockFamily COBBLED_GRANITE = register(MBBlocks.COBBLED_GRANITE)
            .slab(MBBlocks.COBBLED_GRANITE_SLAB).stairs(MBBlocks.COBBLED_GRANITE_STAIRS)
            .wall(MBBlocks.COBBLED_GRANITE_WALL).cracked(Blocks.GRANITE).build();
    public static final MBBlockFamily GRANITE_MB = register(Blocks.GRANITE).slab(Blocks.GRANITE_SLAB).chiseled(MBBlocks.CHISELED_GRANITE).pillar(MBBlocks.GRANITE_PILLAR).build();
    public static final MBBlockFamily POLISHED_GRANITE_MB = register(Blocks.POLISHED_GRANITE).polished(MBBlocks.GRANITE_BRICKS).build();
    public static final MBBlockFamily GRANITE_BRICKS = register(MBBlocks.GRANITE_BRICKS)
            .slab(MBBlocks.GRANITE_BRICK_SLAB).stairs(MBBlocks.GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.GRANITE_BRICK_WALL).cracked(MBBlocks.CRACKED_GRANITE_BRICKS)
            .polished(MBBlocks.GRANITE_TILES).build();
    public static final MBBlockFamily CRACKED_GRANITE_BRICKS = register(MBBlocks.CRACKED_GRANITE_BRICKS)
            .slab(MBBlocks.CRACKED_GRANITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_GRANITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_GRANITE_BRICKS = register(MBBlocks.MOSSY_GRANITE_BRICKS)
            .slab(MBBlocks.MOSSY_GRANITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_GRANITE_BRICK_STAIRS)
            .wall(MBBlocks.MOSSY_GRANITE_BRICK_WALL).build();
    public static final MBBlockFamily GRANITE_TILES = register(MBBlocks.GRANITE_TILES)
            .slab(MBBlocks.GRANITE_TILE_SLAB).stairs(MBBlocks.GRANITE_TILE_STAIRS)
            .wall(MBBlocks.GRANITE_TILE_WALL).cracked(MBBlocks.CRACKED_GRANITE_TILES).build();
    public static final MBBlockFamily CRACKED_GRANITE_TILES = register(MBBlocks.CRACKED_GRANITE_TILES)
            .slab(MBBlocks.CRACKED_GRANITE_TILE_SLAB).stairs(MBBlocks.CRACKED_GRANITE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_GRANITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_GRANITE_TILES = register(MBBlocks.MOSSY_GRANITE_TILES)
            .slab(MBBlocks.MOSSY_GRANITE_TILE_SLAB).stairs(MBBlocks.MOSSY_GRANITE_TILE_STAIRS)
            .wall(MBBlocks.MOSSY_GRANITE_TILE_WALL).build();


    public static final MBBlockFamily SANDSTONE_MB = register(Blocks.SANDSTONE).slab(Blocks.SANDSTONE_SLAB).chiseled(Blocks.CHISELED_SANDSTONE).pillar(MBBlocks.SANDSTONE_PILLAR).build();
    public static final MBBlockFamily CUT_SANDSTONE_MB = register(Blocks.CUT_SANDSTONE).polished(MBBlocks.SANDSTONE_BRICKS).build();
    public static final MBBlockFamily SANDSTONE_BRICKS = register(MBBlocks.SANDSTONE_BRICKS)
            .slab(MBBlocks.SANDSTONE_BRICK_SLAB).stairs(MBBlocks.SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.SANDSTONE_BRICK_WALL).cracked(MBBlocks.CRACKED_SANDSTONE_BRICKS)
            .polished(MBBlocks.SANDSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_SANDSTONE_BRICKS = register(MBBlocks.CRACKED_SANDSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_SANDSTONE_BRICK_WALL).build();
    public static final MBBlockFamily CRACKED_SANDSTONE_TILES = register(MBBlocks.CRACKED_SANDSTONE_TILES)
            .slab(MBBlocks.CRACKED_SANDSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_SANDSTONE_TILE_WALL).build();

    public static final MBBlockFamily RED_SANDSTONE_MB = register(Blocks.RED_SANDSTONE).slab(Blocks.RED_SANDSTONE_SLAB).chiseled(Blocks.CHISELED_RED_SANDSTONE).pillar(MBBlocks.RED_SANDSTONE_PILLAR).build();
    public static final MBBlockFamily CUT_RED_SANDSTONE_MB = register(Blocks.CUT_RED_SANDSTONE).polished(MBBlocks.RED_SANDSTONE_BRICKS).build();
    public static final MBBlockFamily RED_SANDSTONE_BRICKS = register(MBBlocks.RED_SANDSTONE_BRICKS)
            .slab(MBBlocks.RED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.RED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.RED_SANDSTONE_BRICK_WALL).cracked(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)
            .polished(MBBlocks.RED_SANDSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_RED_SANDSTONE_BRICKS = register(MBBlocks.CRACKED_RED_SANDSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_STAIRS)
            .wall(MBBlocks.CRACKED_RED_SANDSTONE_BRICK_WALL).build();
    public static final MBBlockFamily CRACKED_RED_SANDSTONE_TILES = register(MBBlocks.CRACKED_RED_SANDSTONE_TILES)
            .slab(MBBlocks.CRACKED_RED_SANDSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_RED_SANDSTONE_TILE_STAIRS)
            .wall(MBBlocks.CRACKED_RED_SANDSTONE_TILE_WALL).build();


    public static final MBBlockFamily TUFF_MB = register(Blocks.TUFF)
            .slab(MBBlocks.TUFF_SLAB).stairs(MBBlocks.TUFF_STAIRS).wall(MBBlocks.TUFF_WALL)
            .polished(MBBlocks.POLISHED_TUFF).chiseled(MBBlocks.CHISELED_TUFF).pillar(MBBlocks.TUFF_PILLAR).build();
    public static final MBBlockFamily POLISHED_TUFF = register(MBBlocks.POLISHED_TUFF)
            .slab(MBBlocks.POLISHED_TUFF_SLAB).stairs(MBBlocks.POLISHED_TUFF_STAIRS).wall(MBBlocks.POLISHED_TUFF_WALL)
            .polished(MBBlocks.TUFF_BRICKS).build();
    public static final MBBlockFamily TUFF_BRICKS = register(MBBlocks.TUFF_BRICKS)
            .slab(MBBlocks.TUFF_BRICK_SLAB).stairs(MBBlocks.TUFF_BRICK_STAIRS).wall(MBBlocks.TUFF_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_TUFF_BRICKS).mossy(MBBlocks.MOSSY_TUFF_BRICKS).polished(MBBlocks.TUFF_TILES).build();
    public static final MBBlockFamily CRACKED_TUFF_BRICKS = register(MBBlocks.CRACKED_TUFF_BRICKS)
            .slab(MBBlocks.CRACKED_TUFF_BRICK_SLAB).stairs(MBBlocks.CRACKED_TUFF_BRICK_STAIRS).wall(MBBlocks.CRACKED_TUFF_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_TUFF_BRICKS = register(MBBlocks.MOSSY_TUFF_BRICKS)
            .slab(MBBlocks.MOSSY_TUFF_BRICK_SLAB).stairs(MBBlocks.MOSSY_TUFF_BRICK_STAIRS).wall(MBBlocks.MOSSY_TUFF_BRICK_WALL).build();
    public static final MBBlockFamily TUFF_TILES = register(MBBlocks.TUFF_TILES)
            .slab(MBBlocks.TUFF_TILE_SLAB).stairs(MBBlocks.TUFF_TILE_STAIRS).wall(MBBlocks.TUFF_TILE_WALL)
            .cracked(MBBlocks.CRACKED_TUFF_TILES).mossy(MBBlocks.MOSSY_TUFF_TILES).build();
    public static final MBBlockFamily CRACKED_TUFF_TILES = register(MBBlocks.CRACKED_TUFF_TILES)
            .slab(MBBlocks.CRACKED_TUFF_TILE_SLAB).stairs(MBBlocks.CRACKED_TUFF_TILE_STAIRS).wall(MBBlocks.CRACKED_TUFF_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_TUFF_TILES = register(MBBlocks.MOSSY_TUFF_TILES)
            .slab(MBBlocks.MOSSY_TUFF_TILE_SLAB).stairs(MBBlocks.MOSSY_TUFF_TILE_STAIRS).wall(MBBlocks.MOSSY_TUFF_TILE_WALL).build();

    public static final MBBlockFamily CALCITE_MB = register(Blocks.CALCITE)
            .slab(MBBlocks.CALCITE_SLAB).stairs(MBBlocks.CALCITE_STAIRS).wall(MBBlocks.CALCITE_WALL)
            .polished(MBBlocks.POLISHED_CALCITE).chiseled(MBBlocks.CHISELED_CALCITE).pillar(MBBlocks.CALCITE_PILLAR).build();
    public static final MBBlockFamily POLISHED_CALCITE = register(MBBlocks.POLISHED_CALCITE)
            .slab(MBBlocks.POLISHED_CALCITE_SLAB).stairs(MBBlocks.POLISHED_CALCITE_STAIRS).wall(MBBlocks.POLISHED_CALCITE_WALL)
            .polished(MBBlocks.CALCITE_BRICKS).build();
    public static final MBBlockFamily CALCITE_BRICKS = register(MBBlocks.CALCITE_BRICKS)
            .slab(MBBlocks.CALCITE_BRICK_SLAB).stairs(MBBlocks.CALCITE_BRICK_STAIRS).wall(MBBlocks.CALCITE_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_CALCITE_BRICKS).mossy(MBBlocks.MOSSY_CALCITE_BRICKS).polished(MBBlocks.CALCITE_TILES).build();
    public static final MBBlockFamily CRACKED_CALCITE_BRICKS = register(MBBlocks.CRACKED_CALCITE_BRICKS)
            .slab(MBBlocks.CRACKED_CALCITE_BRICK_SLAB).stairs(MBBlocks.CRACKED_CALCITE_BRICK_STAIRS).wall(MBBlocks.CRACKED_CALCITE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_CALCITE_BRICKS = register(MBBlocks.MOSSY_CALCITE_BRICKS)
            .slab(MBBlocks.MOSSY_CALCITE_BRICK_SLAB).stairs(MBBlocks.MOSSY_CALCITE_BRICK_STAIRS).wall(MBBlocks.MOSSY_CALCITE_BRICK_WALL).build();
    public static final MBBlockFamily CALCITE_TILES = register(MBBlocks.CALCITE_TILES)
            .slab(MBBlocks.CALCITE_TILE_SLAB).stairs(MBBlocks.CALCITE_TILE_STAIRS).wall(MBBlocks.CALCITE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_CALCITE_TILES).mossy(MBBlocks.MOSSY_CALCITE_TILES).build();
    public static final MBBlockFamily CRACKED_CALCITE_TILES = register(MBBlocks.CRACKED_CALCITE_TILES)
            .slab(MBBlocks.CRACKED_CALCITE_TILE_SLAB).stairs(MBBlocks.CRACKED_CALCITE_TILE_STAIRS).wall(MBBlocks.CRACKED_CALCITE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_CALCITE_TILES = register(MBBlocks.MOSSY_CALCITE_TILES)
            .slab(MBBlocks.MOSSY_CALCITE_TILE_SLAB).stairs(MBBlocks.MOSSY_CALCITE_TILE_STAIRS).wall(MBBlocks.MOSSY_CALCITE_TILE_WALL).build();

    public static final MBBlockFamily DRIPSTONE_MB = register(Blocks.DRIPSTONE_BLOCK)
            .slab(MBBlocks.DRIPSTONE_SLAB).stairs(MBBlocks.DRIPSTONE_STAIRS).wall(MBBlocks.DRIPSTONE_WALL)
            .polished(MBBlocks.POLISHED_DRIPSTONE).chiseled(MBBlocks.CHISELED_DRIPSTONE).pillar(MBBlocks.DRIPSTONE_PILLAR).build();
    public static final MBBlockFamily POLISHED_DRIPSTONE = register(MBBlocks.POLISHED_DRIPSTONE)
            .slab(MBBlocks.POLISHED_DRIPSTONE_SLAB).stairs(MBBlocks.POLISHED_DRIPSTONE_STAIRS).wall(MBBlocks.POLISHED_DRIPSTONE_WALL)
            .polished(MBBlocks.DRIPSTONE_BRICKS).build();
    public static final MBBlockFamily DRIPSTONE_BRICKS = register(MBBlocks.DRIPSTONE_BRICKS)
            .slab(MBBlocks.DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.DRIPSTONE_BRICK_WALL)
            .cracked(MBBlocks.CRACKED_DRIPSTONE_BRICKS).mossy(MBBlocks.MOSSY_DRIPSTONE_BRICKS).polished(MBBlocks.DRIPSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_DRIPSTONE_BRICKS = register(MBBlocks.CRACKED_DRIPSTONE_BRICKS)
            .slab(MBBlocks.CRACKED_DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.CRACKED_DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.CRACKED_DRIPSTONE_BRICK_WALL).build();
    public static final MBBlockFamily MOSSY_DRIPSTONE_BRICKS = register(MBBlocks.MOSSY_DRIPSTONE_BRICKS)
            .slab(MBBlocks.MOSSY_DRIPSTONE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS).wall(MBBlocks.MOSSY_DRIPSTONE_BRICK_WALL).build();
    public static final MBBlockFamily DRIPSTONE_TILES = register(MBBlocks.DRIPSTONE_TILES)
            .slab(MBBlocks.DRIPSTONE_TILE_SLAB).stairs(MBBlocks.DRIPSTONE_TILE_STAIRS).wall(MBBlocks.DRIPSTONE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_DRIPSTONE_TILES).mossy(MBBlocks.MOSSY_DRIPSTONE_TILES).build();
    public static final MBBlockFamily CRACKED_DRIPSTONE_TILES = register(MBBlocks.CRACKED_DRIPSTONE_TILES)
            .slab(MBBlocks.CRACKED_DRIPSTONE_TILE_SLAB).stairs(MBBlocks.CRACKED_DRIPSTONE_TILE_STAIRS).wall(MBBlocks.CRACKED_DRIPSTONE_TILE_WALL).build();
    public static final MBBlockFamily MOSSY_DRIPSTONE_TILES = register(MBBlocks.MOSSY_DRIPSTONE_TILES)
            .slab(MBBlocks.MOSSY_DRIPSTONE_TILE_SLAB).stairs(MBBlocks.MOSSY_DRIPSTONE_TILE_STAIRS).wall(MBBlocks.MOSSY_DRIPSTONE_TILE_WALL).build();

    public static final MBBlockFamily C_DEEPSLATE_MB = register(Blocks.COBBLED_DEEPSLATE).slab(Blocks.COBBLED_DEEPSLATE_SLAB)
            .mossy(MBBlocks.MOSSY_COBBLED_DEEPSLATE).chiseled(Blocks.CHISELED_DEEPSLATE).pillar(MBBlocks.DEEPSLATE_PILLAR).build();
    public static final MBBlockFamily DEEPSLATE_BRICKS_MB = register(Blocks.DEEPSLATE_BRICKS)
            .mossy(MBBlocks.MOSSY_DEEPSLATE_BRICKS).build();
    public static final MBBlockFamily MOSSY_DEEPSLATE_BRICKS = register(MBBlocks.MOSSY_DEEPSLATE_BRICKS)
            .slab(MBBlocks.MOSSY_DEEPSLATE_BRICK_SLAB).stairs(MBBlocks.MOSSY_DEEPSLATE_BRICK_STAIRS).wall(MBBlocks.MOSSY_DEEPSLATE_BRICK_WALL).build();
    public static final MBBlockFamily DEEPSLATE_TILES_MB = register(Blocks.DEEPSLATE_TILES)
            .mossy(MBBlocks.MOSSY_DEEPSLATE_TILES).build();
    public static final MBBlockFamily MOSSY_DEEPSLATE_TILES = register(MBBlocks.MOSSY_DEEPSLATE_TILES)
            .slab(MBBlocks.MOSSY_DEEPSLATE_TILE_SLAB).stairs(MBBlocks.MOSSY_DEEPSLATE_TILE_STAIRS).wall(MBBlocks.MOSSY_DEEPSLATE_TILE_WALL).build();

    public static final MBBlockFamily PRISMARINE_MB = register(Blocks.PRISMARINE).slab(Blocks.PRISMARINE_SLAB)
            .chiseled(MBBlocks.CHISELED_PRISMARINE).pillar(MBBlocks.PRISMARINE_PILLAR)
            .polished(MBBlocks.POLISHED_PRISMARINE).cracked(MBBlocks.SMOOTH_PRISMARINE).build();
    public static final MBBlockFamily SMOOTH_PRISMARINE = register(MBBlocks.SMOOTH_PRISMARINE)
            .cut(MBBlocks.CUT_PRISMARINE).build();
    public static final MBBlockFamily PRISMARINE_BRICKS_MB = register(Blocks.PRISMARINE_BRICKS)
            .cracked(MBBlocks.CRACKED_PRISMARINE_BRICKS)
            .polished(MBBlocks.PRISMARINE_TILES).build();
    public static final MBBlockFamily CRACKED_PRISMARINE_BRICKS = register(MBBlocks.CRACKED_PRISMARINE_BRICKS)
            .slab(MBBlocks.CRACKED_PRISMARINE_BRICK_SLAB).stairs(MBBlocks.CRACKED_PRISMARINE_BRICK_STAIRS).wall(MBBlocks.CRACKED_PRISMARINE_BRICK_WALL).build();
    public static final MBBlockFamily PRISMARINE_TILES = register(MBBlocks.PRISMARINE_TILES)
            .slab(MBBlocks.PRISMARINE_TILE_SLAB).stairs(MBBlocks.PRISMARINE_TILE_STAIRS).wall(MBBlocks.PRISMARINE_TILE_WALL)
            .cracked(MBBlocks.CRACKED_PRISMARINE_TILES).build();
    public static final MBBlockFamily CRACKED_PRISMARINE_TILES = register(MBBlocks.CRACKED_PRISMARINE_TILES)
            .slab(MBBlocks.CRACKED_PRISMARINE_TILE_SLAB).stairs(MBBlocks.CRACKED_PRISMARINE_TILE_STAIRS).wall(MBBlocks.CRACKED_PRISMARINE_TILE_WALL).build();

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
