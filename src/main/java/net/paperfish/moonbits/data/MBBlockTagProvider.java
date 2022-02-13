package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.MBBlockTags;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.CustomStairsBlock;
import net.paperfish.moonbits.registry.MBBlockFamilies;

import java.util.Objects;

public class MBBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MBBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        MBBlockFamilies.ACACIA.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.BIRCH.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.CRIMSON.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.CRIMSON.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.NON_FLAMMABLE_WOOD).add(block));
        MBBlockFamilies.JUNGLE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.OAK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.DARK_OAK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.SPRUCE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.WARPED.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.WARPED.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.NON_FLAMMABLE_WOOD).add(block));

        MBBlockFamilies.JUNIPER.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.CEDAR.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.HONEY.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.HONEY.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.NON_FLAMMABLE_WOOD).add(block));

        MBBlockFamilies.DIRT_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.TOUGH_DIRT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.DIRT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.PEAT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.GRASS_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.CRIMSON_NYLIUM_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.WARPED_NYLIUM_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));

        MBBlockFamilies.RED_MUSH_BLOCK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.RED_MUSH_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.BROWN_MUSH_BLOCK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.BROWN_MUSH_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.TOADSTOOL_MUSH_BLOCK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.TOADSTOOL_MUSH_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SAFFRON_MUSH_BLOCK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SAFFRON_MUSH_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.HONEYCOMB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.HONEYCOMB_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.HONEYCOMB_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.STONE_BRICKS_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SMOOTH_STONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.COBBLED_ANDESITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.ANDESITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_ANDESITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.ANDESITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_ANDESITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_ANDESITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.ANDESITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_ANDESITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_ANDESITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.COBBLED_DIORITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.ANDESITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_DIORITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DIORITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DIORITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DIORITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DIORITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DIORITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DIORITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.COBBLED_GRANITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.GRANITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_GRANITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.GRANITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_GRANITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_GRANITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.GRANITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_GRANITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_GRANITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.SANDSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CUT_SANDSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SANDSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SANDSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_SANDSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_SANDSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.RED_SANDSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CUT_RED_SANDSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.RED_SANDSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.RED_SANDSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_RED_SANDSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_RED_SANDSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.TUFF_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_TUFF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.TUFF_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_TUFF_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_TUFF_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.TUFF_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_TUFF_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_TUFF_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.CALCITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_CALCITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CALCITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_CALCITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_CALCITE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CALCITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_CALCITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_CALCITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.DRIPSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_DRIPSTONE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.C_DEEPSLATE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_COBBLED_DEEPSLATE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DEEPSLATE_BRICKS_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DEEPSLATE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DEEPSLATE_TILES_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DEEPSLATE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.PRISMARINE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SMOOTH_PRISMARINE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_PRISMARINE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.PRISMARINE_BRICKS_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_PRISMARINE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.PRISMARINE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_PRISMARINE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.S_BASALT_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.P_BASALT_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.BASALT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_BASALT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.BASALT_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_BASALT_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(
                MBBlocks.LEAFBED,
                MBBlocks.TOUGH_GRASS,
                MBBlocks.REGOLITH,
                MBBlocks.CLAY_DEPOSIT,
                MBBlocks.COPPER_DEPOSIT,
                MBBlocks.GOLD_DEPOSIT,
                MBBlocks.PEAT_DEPOSIT,
                MBBlocks.PEAT_BLOCK
        );
        this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                MBBlocks.ROPE_LADDER,

                MBBlocks.TOADSTOOL,
                MBBlocks.RED_MUSHROOM_CAP,
                MBBlocks.BROWN_MUSHROOM_CAP,
                MBBlocks.SAFFRON_MUSHROOM_CAP,
                MBBlocks.GIANT_TOADSTOOL_CAP,
                MBBlocks.MUSHROOM_STEM,
                MBBlocks.TOADSTOOL_SHELF,

                MBBlocks.APPLE_CRATE,
                MBBlocks.CARROT_CRATE,
                MBBlocks.POTATO_CRATE,
                MBBlocks.BEETROOT_CRATE,
                MBBlocks.BAMBOO_BUNDLE,
                MBBlocks.GLISTERING_MELON_BLOCK,
                MBBlocks.SUGAR_CANE_BUNDLE,
                MBBlocks.STICK_STACK,
                MBBlocks.CHARCOAL_LOG,

                MBBlocks.LEATHER_SEAT,
                MBBlocks.WHITE_LEATHER_SEAT,
                MBBlocks.LIGHT_GRAY_LEATHER_SEAT,
                MBBlocks.GRAY_LEATHER_SEAT,
                MBBlocks.BLACK_LEATHER_SEAT,
                MBBlocks.GREEN_LEATHER_SEAT,
                MBBlocks.LIME_LEATHER_SEAT,
                MBBlocks.YELLOW_LEATHER_SEAT,
                MBBlocks.ORANGE_LEATHER_SEAT,
                MBBlocks.BROWN_LEATHER_SEAT,
                MBBlocks.RED_LEATHER_SEAT,
                MBBlocks.PINK_LEATHER_SEAT,
                MBBlocks.MAGENTA_LEATHER_SEAT,
                MBBlocks.PURPLE_LEATHER_SEAT,
                MBBlocks.LIGHT_BLUE_LEATHER_SEAT,
                MBBlocks.CYAN_LEATHER_SEAT,
                MBBlocks.BLUE_LEATHER_SEAT
        );
        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(
                MBBlocks.TOADSTOOL,
                MBBlocks.RED_MUSHROOM_CAP,
                MBBlocks.BROWN_MUSHROOM_CAP,
                MBBlocks.SAFFRON_MUSHROOM_CAP,
                MBBlocks.GIANT_TOADSTOOL_CAP,
                MBBlocks.MUSHROOM_STEM,
                MBBlocks.TOADSTOOL_SHELF,

                MBBlocks.EGG_BASKET,
                MBBlocks.COCOA_SACK,
                MBBlocks.SWEET_BERRY_BASKET,
                MBBlocks.GLOW_BERRY_BASKET,
                MBBlocks.SUGAR_CANE_BUNDLE,
                MBBlocks.KELP_BLOCK,
                MBBlocks.NETHER_WART_BUNDLE,
                MBBlocks.SPOOL,
                MBBlocks.PAPER_BUNDLE,
                MBBlocks.ROTTEN_FLESH_BLOCK,
                MBBlocks.SPIDER_EYE_BLOCK,
                MBBlocks.PHANTOM_MEMBRANE_BLOCK
        );
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                MBBlocks.REGOLITH,
                MBBlocks.IRON_LADDER,

                MBBlocks.RED_MUSH_LAMP,
                MBBlocks.BROWN_MUSH_LAMP,
                MBBlocks.TOADSTOOL_MUSH_LAMP,
                MBBlocks.SAFFRON_MUSH_LAMP,

                Blocks.PISTON,
                Blocks.STICKY_PISTON,
                Blocks.GLOWSTONE,
                Blocks.GLASS,
                Blocks.WHITE_STAINED_GLASS,
                Blocks.LIGHT_GRAY_STAINED_GLASS,
                Blocks.GRAY_STAINED_GLASS,
                Blocks.BLACK_STAINED_GLASS,
                Blocks.GREEN_STAINED_GLASS,
                Blocks.LIME_STAINED_GLASS,
                Blocks.YELLOW_STAINED_GLASS,
                Blocks.ORANGE_STAINED_GLASS,
                Blocks.BROWN_STAINED_GLASS,
                Blocks.RED_STAINED_GLASS,
                Blocks.PINK_STAINED_GLASS,
                Blocks.MAGENTA_STAINED_GLASS,
                Blocks.PURPLE_STAINED_GLASS,
                Blocks.LIGHT_BLUE_STAINED_GLASS,
                Blocks.CYAN_STAINED_GLASS,
                Blocks.BLUE_STAINED_GLASS,
                Blocks.GLASS_PANE,
                Blocks.WHITE_STAINED_GLASS_PANE,
                Blocks.LIGHT_GRAY_STAINED_GLASS_PANE,
                Blocks.GRAY_STAINED_GLASS_PANE,
                Blocks.BLACK_STAINED_GLASS_PANE,
                Blocks.GREEN_STAINED_GLASS_PANE,
                Blocks.LIME_STAINED_GLASS_PANE,
                Blocks.YELLOW_STAINED_GLASS_PANE,
                Blocks.ORANGE_STAINED_GLASS_PANE,
                Blocks.BROWN_STAINED_GLASS_PANE,
                Blocks.RED_STAINED_GLASS_PANE,
                Blocks.PINK_STAINED_GLASS_PANE,
                Blocks.MAGENTA_STAINED_GLASS_PANE,
                Blocks.PURPLE_STAINED_GLASS_PANE,
                Blocks.LIGHT_BLUE_STAINED_GLASS_PANE,
                Blocks.CYAN_STAINED_GLASS_PANE,
                Blocks.BLUE_STAINED_GLASS_PANE
        );

        this.getOrCreateTagBuilder(MBBlockTags.JUNIPER_LOGS).add(
                MBBlocks.JUNIPER_LOG,
                MBBlocks.JUNIPER_WOOD,
                MBBlocks.STRIPPED_JUNIPER_LOG,
                MBBlocks.STRIPPED_JUNIPER_WOOD
        );
        this.getOrCreateTagBuilder(MBBlockTags.CEDAR_LOGS).add(
                MBBlocks.CEDAR_LOG,
                MBBlocks.CEDAR_WOOD,
                MBBlocks.STRIPPED_CEDAR_LOG,
                MBBlocks.STRIPPED_CEDAR_WOOD
        );
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(MBBlockTags.JUNIPER_LOGS).addTag(MBBlockTags.CEDAR_LOGS);

        this.getOrCreateTagBuilder(BlockTags.WOOL).add(
                MBBlocks.FUR_BLOCK
        );

        this.getOrCreateTagBuilder(BlockTags.PLANKS).add(
                MBBlocks.JUNIPER_PLANKS,
                MBBlocks.CEDAR_PLANKS,
                MBBlocks.HONEY_PLANKS
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(
                MBBlocks.JUNIPER_BUTTON,
                MBBlocks.CEDAR_BUTTON,
                MBBlocks.HONEY_BUTTON
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(
                MBBlocks.JUNIPER_PRESSURE_PLATE,
                MBBlocks.CEDAR_PRESSURE_PLATE,
                MBBlocks.HONEY_PRESSURE_PLATE
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(
                MBBlocks.JUNIPER_DOOR,
                MBBlocks.CEDAR_DOOR,
                MBBlocks.HONEY_DOOR
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(
                MBBlocks.JUNIPER_TRAPDOOR,
                MBBlocks.CEDAR_TRAPDOOR,
                MBBlocks.HONEY_TRAPDOOR
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(
                MBBlocks.JUNIPER_STAIRS,
                MBBlocks.CEDAR_STAIRS,
                MBBlocks.HONEY_STAIRS
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(
                MBBlocks.JUNIPER_SLAB,
                MBBlocks.CEDAR_SLAB,
                MBBlocks.HONEY_SLAB
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(
                MBBlocks.JUNIPER_FENCE,
                MBBlocks.CEDAR_FENCE,
                MBBlocks.HONEY_FENCE
        );
        this.getOrCreateTagBuilder(MBBlockTags.BOOKSHELVES).add(
                Blocks.BOOKSHELF,
                MBBlocks.SPRUCE_BOOKSHELF,
                MBBlocks.BIRCH_BOOKSHELF,
                MBBlocks.JUNGLE_BOOKSHELF,
                MBBlocks.ACACIA_BOOKSHELF,
                MBBlocks.DARK_OAK_BOOKSHELF,
                MBBlocks.CRIMSON_BOOKSHELF,
                MBBlocks.WARPED_BOOKSHELF,
                MBBlocks.HONEY_BOOKSHELF,
                MBBlocks.JUNIPER_BOOKSHELF,
                MBBlocks.CEDAR_BOOKSHELF
        );
        this.getOrCreateTagBuilder(MBBlockTags.PLANTER_BOXES).add(
                MBBlocks.OAK_PLANTER_BOX,
                MBBlocks.SPRUCE_PLANTER_BOX,
                MBBlocks.BIRCH_PLANTER_BOX,
                MBBlocks.JUNGLE_PLANTER_BOX,
                MBBlocks.ACACIA_PLANTER_BOX,
                MBBlocks.DARK_OAK_PLANTER_BOX,
                MBBlocks.CRIMSON_PLANTER_BOX,
                MBBlocks.WARPED_PLANTER_BOX,
                MBBlocks.HONEY_PLANTER_BOX,
                MBBlocks.JUNIPER_PLANTER_BOX,
                MBBlocks.CEDAR_PLANTER_BOX
        );
        this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(
                MBBlocks.JUNIPER_SIGN,
                MBBlocks.CEDAR_SIGN,
                MBBlocks.HONEY_SIGN
        );
        this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(
                MBBlocks.JUNIPER_WALL_SIGN,
                MBBlocks.CEDAR_WALL_SIGN,
                MBBlocks.HONEY_WALL_SIGN
        );
        this.getOrCreateTagBuilder(BlockTags.DOORS).add(
                MBBlocks.GLASS_DOOR
        );
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS).add(
                MBBlocks.JUNIPER_SAPLING,
                MBBlocks.CEDAR_SAPLING,
                MBBlocks.APPLE_OAK_SAPLING,
                MBBlocks.GOLDEN_BIRCH_SAPLING,
                MBBlocks.RED_OAK_SAPLING
        );
        this.getOrCreateTagBuilder(BlockTags.LEAVES).add(
                MBBlocks.GOLDEN_BIRCH_LEAVES,
                MBBlocks.RED_OAK_LEAVES,
                MBBlocks.BUDDING_OAK_LEAVES,
                MBBlocks.FLOWERING_OAK_LEAVES,
                MBBlocks.FRUITING_OAK_LEAVES,
                MBBlocks.JUNIPER_LEAVES,
                MBBlocks.CEDAR_LEAVES
        );

        this.getOrCreateTagBuilder(MBBlockTags.SOIL_NON_REPLACEABLE).add(
                MBBlocks.GRASS_TURF,
                MBBlocks.GRASS_TURF_STAIRS,
                MBBlocks.GRASS_TURF_SLAB,
                MBBlocks.TOUGH_GRASS,
                MBBlocks.TOUGH_DIRT,
                MBBlocks.DIRT_BRICKS,
                MBBlocks.PEAT_BLOCK,
                MBBlocks.PEAT_BRICKS
        );
        this.getOrCreateTagBuilder(MBBlockTags.TOUGH_DIRT).add(
                Blocks.COARSE_DIRT,
                MBBlocks.TOUGH_DIRT,
                MBBlocks.REGOLITH
        );
        this.getOrCreateTagBuilder(MBBlockTags.VALID_SEATS).add(
                MBBlocks.LEATHER_SEAT,
                MBBlocks.WHITE_LEATHER_SEAT,
                MBBlocks.LIGHT_GRAY_LEATHER_SEAT,
                MBBlocks.GRAY_LEATHER_SEAT,
                MBBlocks.BLACK_LEATHER_SEAT,
                MBBlocks.GREEN_LEATHER_SEAT,
                MBBlocks.LIME_LEATHER_SEAT,
                MBBlocks.YELLOW_LEATHER_SEAT,
                MBBlocks.ORANGE_LEATHER_SEAT,
                MBBlocks.BROWN_LEATHER_SEAT,
                MBBlocks.RED_LEATHER_SEAT,
                MBBlocks.PINK_LEATHER_SEAT,
                MBBlocks.MAGENTA_LEATHER_SEAT,
                MBBlocks.PURPLE_LEATHER_SEAT,
                MBBlocks.LIGHT_BLUE_LEATHER_SEAT,
                MBBlocks.CYAN_LEATHER_SEAT,
                MBBlocks.BLUE_LEATHER_SEAT,
                MBBlocks.TOADSTOOL
        );

        this.getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(
                MBBlocks.WHITE_HYACINTH,
                MBBlocks.LIGHT_BLUE_HYACINTH,
                MBBlocks.PINK_HYACINTH,
                MBBlocks.RED_HYACINTH
        );
        this.getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                MBBlocks.BUTTERCUP,
                MBBlocks.FORGETMENOT
        );

        this.getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(
                MBBlocks.LEAFBED,
                MBBlocks.CRIMSON_NYLIUM_TURF,
                MBBlocks.WARPED_NYLIUM_TURF
        );

        this.getOrCreateTagBuilder(MBBlockTags.EDIBLE_MUSHROOMS).add(
                Blocks.BROWN_MUSHROOM,
                MBBlocks.SAFFRON_MUSHROOM,
                MBBlocks.PUFFBALLS
        );
        this.getOrCreateTagBuilder(MBBlockTags.MUSHROOMS).add(
                Blocks.RED_MUSHROOM,
                MBBlocks.TOADSTOOL
        ).addTag(MBBlockTags.EDIBLE_MUSHROOMS);

        Registry.BLOCK.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID)) {
                if (block instanceof CustomStairsBlock && block.getDefaultState().getMaterial() != Material.WOOD) {
                    this.getOrCreateTagBuilder(BlockTags.STAIRS).add(block);
                }
                if (block instanceof SlabBlock && block.getDefaultState().getMaterial() != Material.WOOD) {
                    this.getOrCreateTagBuilder(BlockTags.SLABS).add(block);
                }
                if (block instanceof WallBlock) {
                    this.getOrCreateTagBuilder(BlockTags.WALLS).add(block);
                }
                if (block instanceof FlowerPotBlock) {
                    this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(block);
                }
            }
        });
    }
}
