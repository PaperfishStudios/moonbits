package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.extended.MBStairsBlock;
import net.paperfish.moonbits.registry.MBBlockFamilies;

import java.util.Objects;

public class MBBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public MBBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
		MBBlockFamilies.OAK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
		MBBlockFamilies.SPRUCE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.BIRCH.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
		MBBlockFamilies.JUNGLE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
		MBBlockFamilies.ACACIA.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
		MBBlockFamilies.DARK_OAK.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
		MBBlockFamilies.MANGROVE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));

        MBBlockFamilies.CRIMSON.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.CRIMSON.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.NON_FLAMMABLE_WOOD).add(block));
        MBBlockFamilies.WARPED.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.WARPED.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.NON_FLAMMABLE_WOOD).add(block));

        MBBlockFamilies.LAMPROOT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));
        MBBlockFamilies.CEDAR.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block));

        MBBlockFamilies.DIRT_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.PACKED_DIRT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.DIRT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
		MBBlockFamilies.FUZZ_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.PEAT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
		MBBlockFamilies.SNOW_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.GRASS_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.MYCELIUM_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.CRIMSON_NYLIUM_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));
        MBBlockFamilies.WARPED_NYLIUM_TURF.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block));

        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(MBBlocks.CHERT);
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(MBBlocks.COBBLED_CHERT);
        MBBlockFamilies.CHERT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.COBBLED_CHERT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CHERT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CHERT_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_CHERT_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_CHERT_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_CHERT.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

		MBBlockFamilies.JASPER.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.POLISHED_JASPER.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(MBBlocks.HELIODOR_ROD);
		MBBlockFamilies.AZURITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.POLISHED_AZURITE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(MBBlocks.LARIMAR_ROD);

        MBBlockFamilies.STONE_BRICKS_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_STONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.SMOOTH_STONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

		MBBlockFamilies.C_DEEPSLATE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.MOSSY_COBBLED_DEEPSLATE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.DEEPSLATE_BRICKS_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.MOSSY_DEEPSLATE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.DEEPSLATE_TILES_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		MBBlockFamilies.MOSSY_DEEPSLATE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

		MBBlockFamilies.GILDED_BLACKSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
		this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(MBBlocks.BLACKSTONE_PILLAR);
		MBBlockFamilies.SMOOTH_BLACKSTONE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

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
        MBBlockFamilies.DIORITE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
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
        MBBlockFamilies.CALCITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_CALCITE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

        MBBlockFamilies.DRIPSTONE_MB.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.POLISHED_DRIPSTONE.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DRIPSTONE_BRICKS.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.CRACKED_DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
        MBBlockFamilies.MOSSY_DRIPSTONE_TILES.getVariants().forEach((variant, block) -> this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));

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
                MBBlocks.REGOLITH,
				MBBlocks.FLINT_DEPOSIT,
                MBBlocks.DEEP_ROOTED_SOIL,
                MBBlocks.MYCELIAL_DIRT, MBBlocks.DECOMPOSING_DIRT, MBBlocks.FUZZ_BLOCK,
                MBBlocks.PEAT_MOSS, MBBlocks.PEAT_BLOCK,

				MBBlocks.RABBIT_MOUND,

                MBBlocks.PERMAFROST,

                MBBlocks.SNOW_BRICKS,

                MBBlocks.SUGAR_CUBE
        );
        this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(
                MBBlocks.ROPE_LADDER,

                MBBlocks.RED_MUSHROOM_CAP, MBBlocks.BROWN_MUSHROOM_CAP, MBBlocks.SAFFRON_MUSHROOM_CAP,
                MBBlocks.MUSHROOM_STEM, MBBlocks.MUSHROOM_HYPHAE,
				MBBlocks.STRIPPED_MUSHROOM_STEM, MBBlocks.STRIPPED_MUSHROOM_HYPHAE,

				MBBlocks.BEAM,

                MBBlocks.APPLE_CRATE,
                MBBlocks.CARROT_CRATE,
                MBBlocks.POTATO_CRATE,
                MBBlocks.BEETROOT_CRATE,

                MBBlocks.BAMBOO_BUNDLE,
				MBBlocks.OCOTILLO_BUNDLE,
                MBBlocks.GLISTERING_MELON_BLOCK,
                MBBlocks.SUGAR_CANE_BUNDLE,
                MBBlocks.STICK_STACK,
                MBBlocks.CHARCOAL_LOG,
                MBBlocks.GUNPOWDER_CRATE,

                MBBlocks.WHITE_CUSHION,
                MBBlocks.LIGHT_GRAY_CUSHION,
                MBBlocks.GRAY_CUSHION,
                MBBlocks.BLACK_CUSHION,
                MBBlocks.GREEN_CUSHION,
                MBBlocks.LIME_CUSHION,
                MBBlocks.YELLOW_CUSHION,
                MBBlocks.ORANGE_CUSHION,
                MBBlocks.BROWN_CUSHION,
                MBBlocks.RED_CUSHION,
                MBBlocks.PINK_CUSHION,
                MBBlocks.MAGENTA_CUSHION,
                MBBlocks.PURPLE_CUSHION,
                MBBlocks.LIGHT_BLUE_CUSHION,
                MBBlocks.CYAN_CUSHION,
                MBBlocks.BLUE_CUSHION
        );
        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add(
                MBBlocks.RED_MUSHROOM_CAP, MBBlocks.BROWN_MUSHROOM_CAP, MBBlocks.SAFFRON_MUSHROOM_CAP,

                MBBlocks.TINY_BARREL_CACTUS, MBBlocks.SMALL_BARREL_CACTUS, MBBlocks.BARREL_CACTUS, MBBlocks.LARGE_BARREL_CACTUS,

                MBBlocks.CEDAR_LEAVES,

                MBBlocks.EGG_BASKET,
                MBBlocks.COCOA_SACK,
                MBBlocks.SWEET_BERRY_BASKET,
                MBBlocks.GLOW_BERRY_BASKET,
                MBBlocks.SUGAR_CANE_BUNDLE,
                MBBlocks.KELP_BLOCK,
                MBBlocks.NETHER_WART_SACK,
                MBBlocks.CHORUS_BUNDLE,
                MBBlocks.SPOOL,
                MBBlocks.PAPER_BUNDLE,
                MBBlocks.ROTTEN_FLESH_BLOCK,
                MBBlocks.SPIDER_EYE_BLOCK,
                MBBlocks.PHANTOM_MEMBRANE_BLOCK
        );
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                MBBlocks.REGOLITH,

                MBBlocks.TREE_TAP,
				MBBlocks.SAP_TREE_TAP, MBBlocks.SYRUP_TREE_TAP, MBBlocks.RESIN_TREE_TAP,

				MBBlocks.COPPER_OXIDE_LANTERN, MBBlocks.COPPER_OXIDE_CAMPFIRE,

                MBBlocks.PAVED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS,
                MBBlocks.PAVED_RED_SANDSTONE_BRICKS, MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS,

                MBBlocks.PACKED_ICE_BRICKS, MBBlocks.BLUE_ICE_BRICKS,

                MBBlocks.CHERT_COAL_ORE, MBBlocks.CHERT_COPPER_ORE, MBBlocks.CHERT_GOLD_ORE,
                MBBlocks.CHERT_LAPIS_ORE, MBBlocks.CHERT_REDSTONE_ORE,
				MBBlocks.CHERT_DIAMOND_ORE,
                MBBlocks.BANDED_IRON, MBBlocks.MAGNETITE_ORE,
                MBBlocks.MAGNETITE_BLOCK,

				MBBlocks.DESERT_VASE,
				MBBlocks.MUD_VESSEL,

                MBBlocks.PACKED_GLOWSTONE,

				MBBlocks.WALL_LANTERN, MBBlocks.WALL_SOUL_LANTERN, MBBlocks.WALL_COPPER_OXIDE_LANTERN,

                Blocks.PISTON, Blocks.STICKY_PISTON,
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

        this.getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(
                MBBlocks.CHERT_COAL_ORE,
                MBBlocks.CHERT_COPPER_ORE,
                MBBlocks.CHERT_GOLD_ORE,

                MBBlocks.BANDED_IRON,
                MBBlocks.MAGNETITE_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(
                MBBlocks.CHERT_LAPIS_ORE,
                MBBlocks.CHERT_REDSTONE_ORE,
				MBBlocks.CHERT_DIAMOND_ORE
        );

        this.getOrCreateTagBuilder(MBBlockTags.COBBLESTONE).add(
                Blocks.COBBLESTONE,
                Blocks.COBBLED_DEEPSLATE,
                MBBlocks.COBBLED_ANDESITE,
                MBBlocks.COBBLED_DIORITE,
                MBBlocks.COBBLED_GRANITE,
                MBBlocks.COBBLED_CHERT
        );
        this.getOrCreateTagBuilder(MBBlockTags.STONE).add(
                Blocks.STONE,
                Blocks.ANDESITE,
                Blocks.DIORITE,
                Blocks.GRANITE,
                Blocks.DEEPSLATE,
                MBBlocks.CHERT
        );

        this.getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(
                MBBlocks.ROPE_LADDER
        );

        this.getOrCreateTagBuilder(MBBlockTags.LAMPROOT_LOGS).add(
                MBBlocks.LAMPROOT_LOG,
                MBBlocks.LAMPROOT_WOOD,
                MBBlocks.STRIPPED_LAMPROOT_LOG,
                MBBlocks.STRIPPED_LAMPROOT_WOOD
        );
        this.getOrCreateTagBuilder(MBBlockTags.CEDAR_LOGS).add(
                MBBlocks.CEDAR_LOG,
                MBBlocks.CEDAR_WOOD,
                MBBlocks.STRIPPED_CEDAR_LOG,
                MBBlocks.STRIPPED_CEDAR_WOOD
        );
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(MBBlockTags.CEDAR_LOGS);

//        this.getOrCreateTagBuilder(BlockTags.WOOL).add(
//                MBBlocks.FUR_BLOCK
//        );

        this.getOrCreateTagBuilder(BlockTags.PLANKS).add(
                MBBlocks.LAMPROOT_PLANKS,
                MBBlocks.CEDAR_PLANKS
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(
                MBBlocks.LAMPROOT_BUTTON,
                MBBlocks.CEDAR_BUTTON
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(
                MBBlocks.LAMPROOT_PRESSURE_PLATE,
                MBBlocks.CEDAR_PRESSURE_PLATE
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(
                MBBlocks.LAMPROOT_DOOR,
                MBBlocks.CEDAR_DOOR
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(
                MBBlocks.LAMPROOT_TRAPDOOR,
                MBBlocks.CEDAR_TRAPDOOR
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(
                MBBlocks.LAMPROOT_STAIRS,
                MBBlocks.CEDAR_STAIRS
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(
                MBBlocks.LAMPROOT_SLAB,
                MBBlocks.CEDAR_SLAB
        );
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(
                MBBlocks.LAMPROOT_FENCE,
                MBBlocks.CEDAR_FENCE
        );
//        this.getOrCreateTagBuilder(ConventionalBlockTags.BOOKSHELVES).add(
//                Blocks.BOOKSHELF,
//                MBBlocks.SPRUCE_BOOKSHELF,
//                MBBlocks.BIRCH_BOOKSHELF,
//                MBBlocks.JUNGLE_BOOKSHELF,
//                MBBlocks.ACACIA_BOOKSHELF,
//                MBBlocks.DARK_OAK_BOOKSHELF,
//                MBBlocks.CRIMSON_BOOKSHELF,
//                MBBlocks.WARPED_BOOKSHELF,
//                MBBlocks.HONEY_BOOKSHELF,
//                MBBlocks.LAMPROOT_BOOKSHELF,
//                MBBlocks.CEDAR_BOOKSHELF
//        );
        this.getOrCreateTagBuilder(MBBlockTags.PLANTER_BOXES).add(
                MBBlocks.OAK_PLANTER_BOX,
                MBBlocks.SPRUCE_PLANTER_BOX,
                MBBlocks.BIRCH_PLANTER_BOX,
                MBBlocks.JUNGLE_PLANTER_BOX,
                MBBlocks.ACACIA_PLANTER_BOX,
                MBBlocks.DARK_OAK_PLANTER_BOX,
                MBBlocks.MANGROVE_PLANTER_BOX,
                MBBlocks.LAMPROOT_PLANTER_BOX,
                MBBlocks.CEDAR_PLANTER_BOX
        );
		this.getOrCreateTagBuilder(MBBlockTags.NETHER_PLANTERS).add(
				MBBlocks.CRIMSON_PLANTER_BOX,
				MBBlocks.WARPED_PLANTER_BOX
		);
		this.getOrCreateTagBuilder(MBBlockTags.OCOTILLO).add(
				MBBlocks.OCOTILLO,
				MBBlocks.FLOWERING_OCOTILLO
		);
        this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(
                MBBlocks.LAMPROOT_SIGN,
                MBBlocks.CEDAR_SIGN
        );
        this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(
                MBBlocks.LAMPROOT_WALL_SIGN,
                MBBlocks.CEDAR_WALL_SIGN
        );
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS).add(
                MBBlocks.CEDAR_SAPLING
        );
        this.getOrCreateTagBuilder(BlockTags.LEAVES).add(
                MBBlocks.CEDAR_LEAVES
        );

        this.getOrCreateTagBuilder(BlockTags.DIRT).add(
                MBBlocks.GRASS_TURF,
                MBBlocks.GRASS_TURF_STAIRS,
                MBBlocks.GRASS_TURF_SLAB,
                MBBlocks.MYCELIUM_TURF,
                MBBlocks.MYCELIUM_TURF_STAIRS,
                MBBlocks.MYCELIUM_TURF_SLAB,
				MBBlocks.PACKED_DIRT,
				MBBlocks.PACKED_DIRT_STAIRS,
				MBBlocks.PACKED_DIRT_SLAB,
                MBBlocks.DIRT_BRICKS,
				MBBlocks.DIRT_BRICK_STAIRS,
				MBBlocks.DIRT_BRICK_SLAB,
                MBBlocks.PERMAFROST,
				MBBlocks.PERMAFROST_STAIRS,
				MBBlocks.PERMAFROST_SLAB,
				MBBlocks.PERMAFROST_BRICKS,
				MBBlocks.PERMAFROST_BRICK_STAIRS,
				MBBlocks.PERMAFROST_BRICK_SLAB,
                MBBlocks.PEAT_MOSS,
                MBBlocks.PEAT_BLOCK,
                MBBlocks.PEAT_BRICKS
        );
        this.getOrCreateTagBuilder(BlockTags.SAND);
        this.getOrCreateTagBuilder(MBBlockTags.SANDY_SOILS).addTag(BlockTags.SAND);
//        this.getOrCreateTagBuilder(MBBlockTags.SANDY_SOILS).add(
//                MBBlocks.SANDY_SOIL,
//				MBBlocks.CRACKED_MUD
//        );
        this.getOrCreateTagBuilder(MBBlockTags.TOUGH_DIRT).add(
				MBBlocks.PACKED_DIRT,
				MBBlocks.PACKED_DIRT_STAIRS,
				MBBlocks.PACKED_DIRT_SLAB,
                MBBlocks.REGOLITH
        );
        this.getOrCreateTagBuilder(BlockTags.BASE_STONE_OVERWORLD).add(
                MBBlocks.CHERT
        );

        this.getOrCreateTagBuilder(MBBlockTags.BANDED_IRON).add(
                MBBlocks.BANDED_IRON,
                MBBlocks.MAGNETITE_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.COAL_ORES).add(
                MBBlocks.CHERT_COAL_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.GOLD_ORES).add(
                MBBlocks.CHERT_GOLD_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.COPPER_ORES).add(
                MBBlocks.CHERT_COPPER_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.REDSTONE_ORES).add(
                MBBlocks.CHERT_REDSTONE_ORE
        );
        this.getOrCreateTagBuilder(BlockTags.LAPIS_ORES).add(
                MBBlocks.CHERT_LAPIS_ORE
        );
		this.getOrCreateTagBuilder(BlockTags.DIAMOND_ORES).add(
				MBBlocks.CHERT_DIAMOND_ORE
		);
        this.getOrCreateTagBuilder(BlockTags.IRON_ORES).add(
                MBBlocks.BANDED_IRON
        );
        this.getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS).add(
                MBBlocks.MAGNETITE_BLOCK,

				Blocks.COPPER_BLOCK,
				Blocks.EXPOSED_COPPER,
				Blocks.WEATHERED_COPPER,
				Blocks.OXIDIZED_COPPER,
				Blocks.WAXED_COPPER_BLOCK,
				Blocks.WAXED_EXPOSED_COPPER,
				Blocks.WAXED_WEATHERED_COPPER,
				Blocks.WAXED_OXIDIZED_COPPER
        );
        this.getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD).add(
                MBBlocks.GUNPOWDER_CRATE
        );

        this.getOrCreateTagBuilder(MBBlockTags.VALID_SEATS).add(
                MBBlocks.WHITE_CUSHION,
                MBBlocks.LIGHT_GRAY_CUSHION,
                MBBlocks.GRAY_CUSHION,
                MBBlocks.BLACK_CUSHION,
                MBBlocks.GREEN_CUSHION,
                MBBlocks.LIME_CUSHION,
                MBBlocks.YELLOW_CUSHION,
                MBBlocks.ORANGE_CUSHION,
                MBBlocks.BROWN_CUSHION,
                MBBlocks.RED_CUSHION,
                MBBlocks.PINK_CUSHION,
                MBBlocks.MAGENTA_CUSHION,
                MBBlocks.PURPLE_CUSHION,
                MBBlocks.LIGHT_BLUE_CUSHION,
                MBBlocks.CYAN_CUSHION,
                MBBlocks.BLUE_CUSHION
        );

        this.getOrCreateTagBuilder(BlockTags.TALL_FLOWERS).add(
                MBBlocks.LUPINE,
				MBBlocks.YUCCA
        );
        this.getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(
                MBBlocks.SOURSOBS,
                MBBlocks.MARIGOLD
        );

        this.getOrCreateTagBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(
                MBBlocks.PEAT_MOSS,
                MBBlocks.MYCELIUM_TURF,
                MBBlocks.CRIMSON_NYLIUM_TURF,
                MBBlocks.WARPED_NYLIUM_TURF
        );

        this.getOrCreateTagBuilder(MBBlockTags.EDIBLE_MUSHROOMS).add(
                Blocks.BROWN_MUSHROOM,
                MBBlocks.SAFFRON_MUSHROOM
        );
        this.getOrCreateTagBuilder(MBBlockTags.MUSHROOMS).add(
                Blocks.RED_MUSHROOM
        ).addTag(MBBlockTags.EDIBLE_MUSHROOMS);

        Registry.BLOCK.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID)) {
                if (block instanceof MBStairsBlock && block.getDefaultState().getMaterial() != Material.WOOD) {
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
