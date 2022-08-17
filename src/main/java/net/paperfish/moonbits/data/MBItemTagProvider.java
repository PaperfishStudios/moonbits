package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItemTags;
import net.paperfish.moonbits.registry.MBItems;
import org.jetbrains.annotations.Nullable;

public class MBItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MBItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {
//        getOrCreateTagBuilder(MBItemTags.AXES).add(Items.WOODEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.GOLDEN_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE);

        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
		getOrCreateTagBuilder(ItemTags.PLANKS).add(MBItems.HARDY_STEM);
//        copy(BlockTags.STONE_BRICKS, ItemTags.STONE_BRICKS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
//        copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
//        copy(BlockTags.CARPETS, ItemTags.CARPETS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
//        copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);
//        copy(BlockTags.DARK_OAK_LOGS, ItemTags.DARK_OAK_LOGS);
//        copy(BlockTags.BIRCH_LOGS, ItemTags.BIRCH_LOGS);
//        copy(BlockTags.ACACIA_LOGS, ItemTags.ACACIA_LOGS);
//        copy(BlockTags.SPRUCE_LOGS, ItemTags.SPRUCE_LOGS);
//        copy(BlockTags.JUNGLE_LOGS, ItemTags.JUNGLE_LOGS);
//        copy(BlockTags.CRIMSON_STEMS, ItemTags.CRIMSON_STEMS);
//        copy(BlockTags.WARPED_STEMS, ItemTags.WARPED_STEMS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
//        copy(BlockTags.LOGS, ItemTags.LOGS);
//        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
//        copy(BlockTags.ANVIL, ItemTags.ANVIL);
//        copy(BlockTags.RAILS, ItemTags.RAILS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
//        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
//        copy(BlockTags.BEDS, ItemTags.BEDS);
//        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
//        copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
//        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
//        copy(BlockTags.CANDLES, ItemTags.CANDLES);
//        copy(BlockTags.OCCLUDES_VIBRATION_SIGNALS, ItemTags.OCCLUDES_VIBRATION_SIGNALS);
//        copy(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
//        copy(BlockTags.IRON_ORES, ItemTags.IRON_ORES);
//        copy(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES);
        //copy(BlockTags.REDSTONE_ORES, ItemTags.REDSTONE_ORES);
        //copy(BlockTags.LAPIS_ORES, ItemTags.LAPIS_ORES);
        //copy(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
        //copy(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);
        //copy(BlockTags.COPPER_ORES, ItemTags.COPPER_ORES);
        copy(BlockTags.DIRT, ItemTags.DIRT);
        //copy(BlockTags.TERRACOTTA, ItemTags.TERRACOTTA);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.NON_FLAMMABLE_WOOD, ItemTags.NON_FLAMMABLE_WOOD);

        copy(MBBlockTags.COBBLESTONE, MBItemTags.COBBLESTONE);
        copy(MBBlockTags.STONE, MBItemTags.STONE);
        copy(MBBlockTags.COBBLESTONE, ItemTags.STONE_CRAFTING_MATERIALS);
        copy(MBBlockTags.COBBLESTONE, ItemTags.STONE_TOOL_MATERIALS);

        copy(MBBlockTags.LAMPROOT_LOGS, MBItemTags.LAMPROOT_LOGS);
        copy(MBBlockTags.CEDAR_LOGS, MBItemTags.CEDAR_LOGS);
		copy(MBBlockTags.TOADSTOOL_STEMS, MBItemTags.TOADSTOOL_STEMS);
		getOrCreateTagBuilder(ItemTags.SAND);
		copy(MBBlockTags.SANDY_SOILS, MBItemTags.SANDY_SOILS);
//        copy(MBBlockTags.ASPEN_TRUNKS, MBItemTags.ASPEN_TRUNKS);

        copy(MBBlockTags.EDIBLE_MUSHROOMS, MBItemTags.EDIBLE_MUSHROOMS);
        copy(MBBlockTags.MUSHROOMS, MBItemTags.MUSHROOMS);

        getOrCreateTagBuilder(MBItemTags.BEAR_LIKES).add(Items.SALMON, Items.SALMON_BUCKET, Items.HONEY_BOTTLE, Items.HONEYCOMB);
        getOrCreateTagBuilder(MBItemTags.BEAR_EDIBLE).addTag(MBItemTags.BEAR_LIKES).add(Items.COOKED_SALMON, Items.COD, Items.COOKED_COD, Items.BEEF, Items.COOKED_BEEF);

        // misc item stuffs
        getOrCreateTagBuilder(MBItemTags.MILK).add(Items.MILK_BUCKET, MBItems.MILK_BOTTLE);
        getOrCreateTagBuilder(MBItemTags.HONEY).add(Items.HONEY_BOTTLE, Items.HONEYCOMB);
        getOrCreateTagBuilder(MBItemTags.PEANUT).add(MBItems.PEANUT, MBItems.ROASTED_PEANUTS);

		getOrCreateTagBuilder(ConventionalItemTags.SWORDS).add(
				MBItems.FLINT_SWORD
		);
		getOrCreateTagBuilder(ConventionalItemTags.SHOVELS).add(
				MBItems.FLINT_SHOVEL
		);
		getOrCreateTagBuilder(ConventionalItemTags.PICKAXES).add(
				MBItems.FLINT_PICKAXE
		);
		getOrCreateTagBuilder(ConventionalItemTags.AXES).add(
				MBItems.FLINT_AXE
		);
		getOrCreateTagBuilder(ConventionalItemTags.HOES).add(
				MBItems.FLINT_HOE
		);

        // cooking tags
        getOrCreateTagBuilder(MBItemTags.COOKING_FRUITS).add(
                Items.APPLE, Items.MELON_SLICE,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES,
                Items.CHORUS_FRUIT,
                MBItems.COOKED_PRICKLY_PEAR
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_VEG).add(
                Items.CARROT, Items.BEETROOT,
                Items.PUMPKIN, MBItems.PUMPKIN_SLICE,
                MBItems.PEPPER
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_MEATS).add(
                Items.BEEF, Items.COOKED_BEEF,
                Items.CHICKEN, Items.COOKED_CHICKEN,
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.PORKCHOP, Items.COOKED_PORKCHOP,
                Items.RABBIT, Items.COOKED_RABBIT
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_FISH).add(
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                Items.TROPICAL_FISH
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_SWEET).add(
                Items.SUGAR,
                Items.HONEYCOMB, Items.HONEY_BOTTLE,
                MBItems.SYRUP_BOTTLE
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_WILD).addTag(MBItemTags.EDIBLE_MUSHROOMS).add(
                Items.SWEET_BERRIES, Items.GLOW_BERRIES,
                Items.FERN, Items.DANDELION, Items.SUNFLOWER,
                MBBlocks.LAMPROOT_BULB.asItem(), MBBlocks.CAVEBLOOMS, MBItems.PEANUT
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_MONSTER).add(
                Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.MAGMA_CREAM
        );
        getOrCreateTagBuilder(MBItemTags.COOKING_FILLER)
                .addTag(MBItemTags.COOKING_FRUITS).addTag(MBItemTags.COOKING_VEG)
                .addTag(MBItemTags.COOKING_MEATS).addTag(MBItemTags.COOKING_FISH)
                .addTag(MBItemTags.COOKING_SWEET)
                .addTag(MBItemTags.COOKING_WILD)
                .addTag(MBItemTags.COOKING_MONSTER);
        getOrCreateTagBuilder(MBItemTags.COOKING_FILLER).add(
                Items.KELP, Items.DRIED_KELP,
                Items.WHEAT, Items.POTATO,
                MBItems.PEANUT, MBItems.PEANUT_BRITTLE,
                Items.COCOA_BEANS, Items.EGG,
                Items.STICK, MBItems.GRASS_TUFT
        );
    }
}
