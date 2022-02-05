package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTablesProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.model.TexturedModel;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBItems;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.CustomStairsBlock;
import net.paperfish.moonbits.block.LettuceCropBlock;
import net.paperfish.moonbits.block.PebbleBlock;
import net.paperfish.moonbits.registry.MBBlockFamilies;
import net.paperfish.moonbits.registry.MBBlockFamily;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MBLootTableProvider extends FabricBlockLootTablesProvider {
    public List<Block> generatedBlocks = new ArrayList<>();
    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};
    protected MBLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        Registry.BLOCK.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID)) {
                if (block instanceof FlowerPotBlock) {
                    addPottedPlantDrop(block);
                }
            }
        });

        addDrop(MBBlocks.ROPE_LADDER);
        addDrop(MBBlocks.IRON_LADDER);
        addDrop(MBBlocks.LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.WHITE_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.LIGHT_GRAY_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.GRAY_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.BLACK_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.GREEN_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.LIME_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.YELLOW_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.ORANGE_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.BROWN_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.RED_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.PINK_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.MAGENTA_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.PURPLE_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.LIGHT_BLUE_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.CYAN_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);
        addDrop(MBBlocks.BLUE_LEATHER_SEAT, BlockLootTableGenerator::slabDrops);

        addDrop(MBBlocks.JACARANDA_LOG);
        addDrop(MBBlocks.JACARANDA_WOOD);
        addDrop(MBBlocks.STRIPPED_JACARANDA_LOG);
        addDrop(MBBlocks.STRIPPED_JACARANDA_WOOD);

        addDrop(MBBlocks.BUDDING_OAK_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.APPLE_OAK_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.FLOWERING_OAK_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.APPLE_OAK_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.FRUITING_OAK_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.APPLE_OAK_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.GOLDEN_BIRCH_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.GOLDEN_BIRCH_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.RED_OAK_LEAVES, (Block l) -> BlockLootTableGenerator.oakLeavesDrop(l, MBBlocks.RED_OAK_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.JACARANDA_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.JACARANDA_SAPLING, SAPLING_DROP_CHANCE));

        addDrop(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.RED_OAK_LEAF_CARPET, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.JACARANDA_LEAF_CARPET, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.HANGING_JACARANDA_LEAVES, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.HANGING_JACARANDA_LEAVES_PLANT, MBLootTableProvider::leafCarpet);

        addDrop(MBBlocks.APPLE_OAK_SPROUT, (Block block) -> BlockLootTableGenerator.drops(block, MBItems.APPLE_SEEDS));
        addDrop(MBBlocks.APPLE_OAK_SAPLING);
        addDrop(MBBlocks.GOLDEN_BIRCH_SAPLING);
        addDrop(MBBlocks.RED_OAK_SAPLING);
        addDrop(MBBlocks.JACARANDA_SAPLING);

        addDrop(MBBlocks.LEAFBED, (Block block) -> BlockLootTableGenerator.drops(block, Blocks.DIRT));
        addDrop(MBBlocks.TOUGH_GRASS, (Block block) -> BlockLootTableGenerator.drops(block, MBBlocks.TOUGH_DIRT));
        addDrop(MBBlocks.REGOLITH, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block,
                BlockLootTableGenerator.addSurvivesExplosionCondition(block, ItemEntry.builder(block)
                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.25f, 0.14285715f, 0.1f, 0f)).alternatively(ItemEntry.builder(MBBlocks.PEBBLES)))));
        addDrop(MBBlocks.PEAT_BLOCK);
        addDrop(MBBlocks.PEAT_DEPOSIT, (Block block) -> oreDrops(block, MBItems.PEAT, 1f, 2f));
        addDrop(MBBlocks.CLAY_DEPOSIT, (Block block) -> oreDrops(block, Items.CLAY_BALL, 2f, 4f));
        addDrop(MBBlocks.GOLD_DEPOSIT, (Block block) -> oreDrops(block, Items.GOLD_NUGGET, 1f, 4f));
        addDrop(MBBlocks.COPPER_DEPOSIT, (Block block) -> oreDrops(block, MBItems.COPPER_NUGGET, 2f, 5f));
        addDrop(MBBlocks.FOSSIL, (Block block) -> BlockLootTableGenerator.drops(block, Items.NAUTILUS_SHELL));

        addDrop(MBBlocks.PEBBLES, (Block block) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with(BlockLootTableGenerator.applyExplosionDecay(MBBlocks.PEBBLES,
                        (ItemEntry.builder(block).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
                                .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 2)))))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3.0f)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 3))))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0f)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 4))))))));

        addDrop(MBBlocks.WILD_CARROTS, (Block block) -> oreDrops(block, Items.CARROT, 1f, 2f));
        addDrop(MBBlocks.WILD_POTATOES, (Block block) -> oreDrops(block, Items.POTATO, 1f, 3f));
        addDrop(MBBlocks.SEA_BEETS, (Block block) -> oreDrops(block, Items.BEETROOT_SEEDS, 0f, 2f));

        addDrop(MBBlocks.LETTUCE_CROP, (Block block) -> BlockLootTableGenerator.drops(block, MBItems.LETTUCE_SEEDS));
        addDrop(MBBlocks.LETTUCE_BLOCK, (Block block) -> oreDrops(block, MBItems.LETTUCE_LEAF, 3f, 6f));

        addDrop(MBBlocks.BUTTERCUP);
        addDrop(MBBlocks.FORGETMENOT);
        addDrop(MBBlocks.PINK_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.WHITE_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.LIGHT_BLUE_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.RED_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.PUFFBALLS);
        addDrop(MBBlocks.SAFFRON_MUSHROOM);
        addDrop(MBBlocks.TOADSTOOL);
        addDrop(MBBlocks.SMALL_TOADSTOOLS);

        addDrop(MBBlocks.RED_MUSHROOM_CAP);
        addDrop(MBBlocks.BROWN_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_GILLS, BlockLootTableGenerator::dropsWithShears);
        addDrop(MBBlocks.GIANT_TOADSTOOL_CAP);
        addDrop(MBBlocks.MUSHROOM_STEM);

        addDrop(MBBlocks.TOADSTOOL_SHELF);
        //addDrop(MBBlocks.RED_MUSH_BRICKS);
        //addDrop(MBBlocks.BROWN_MUSH_BRICKS);
        addDrop(MBBlocks.RED_MUSH_LAMP);
        addDrop(MBBlocks.BROWN_MUSH_LAMP);
        addDrop(MBBlocks.SAFFRON_MUSH_LAMP);
        addDrop(MBBlocks.TOADSTOOL_MUSH_LAMP);

        addDrop(MBBlocks.LAMPROOT);
        addDrop(MBBlocks.CAVEBLOOM_FLOWERS, BlockLootTableGenerator::glowLichenDrops);
        addDrop(MBBlocks.CAVEBLOOM_VINE, BlockLootTableGenerator::glowLichenDrops);

        addDrop(MBBlocks.FUR_BLOCK);
        addDrop(MBBlocks.FUR_CARPET);
        addDrop(MBBlocks.BEDROLL, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));

        addDrop(MBBlocks.AZALEA_STEM);

        addDrop(MBBlocks.MINI_LILY);
        addDrop(MBBlocks.MINI_OXEYE);
        addDrop(MBBlocks.MINI_BLUET);
        addDrop(MBBlocks.MINI_DANDELION);
        addDrop(MBBlocks.MINI_POPPY);
        addDrop(MBBlocks.MINI_ORCHID);
        addDrop(MBBlocks.MINI_CORNFLOWER);
        addDrop(MBBlocks.MINI_ALLIUM);
        addDrop(MBBlocks.MINI_TULIP_W);
        addDrop(MBBlocks.MINI_TULIP_P);
        addDrop(MBBlocks.MINI_TULIP_O);
        addDrop(MBBlocks.MINI_TULIP_R);
        addDrop(MBBlocks.MINI_FORGETMENOT);

        addDrop(MBBlocks.APPLE_CRATE);
        addDrop(MBBlocks.CARROT_CRATE);
        addDrop(MBBlocks.POTATO_CRATE);
        addDrop(MBBlocks.BEETROOT_CRATE);
        addDrop(MBBlocks.EGG_BASKET);
        addDrop(MBBlocks.COCOA_SACK);
        addDrop(MBBlocks.GLISTERING_MELON_BLOCK);
        addDrop(MBBlocks.SWEET_BERRY_BASKET);
        addDrop(MBBlocks.GLOW_BERRY_BASKET);
        addDrop(MBBlocks.SWEET_BERRY_HEDGE);
        addDrop(MBBlocks.GLOW_BERRY_HEDGE);
        addDrop(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE);
        addDrop(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE);
        addDrop(MBItems.SWEET_BERRY_PITS);
        addDrop(MBItems.GLOW_BERRY_PITS);

        addDrop(MBBlocks.SUGAR_CANE_BUNDLE);
        addDrop(MBBlocks.BAMBOO_BUNDLE);
        addDrop(MBBlocks.KELP_BLOCK);
        addDrop(MBBlocks.NETHER_WART_BUNDLE);
        addDrop(MBBlocks.SPOOL);
        addDrop(MBBlocks.PAPER_BUNDLE);
        addDrop(MBBlocks.STICK_STACK);
        addDrop(MBBlocks.CHARCOAL_LOG);
        addDrop(MBBlocks.SCUTE_BLOCK);
        addDrop(MBBlocks.ROTTEN_FLESH_BLOCK);
        addDrop(MBBlocks.BONE_BUNDLE);
        addDrop(MBBlocks.SPIDER_EYE_BLOCK);
        addDrop(MBBlocks.PHANTOM_MEMBRANE_BLOCK);
        addDrop(MBBlocks.BLAZE_ROD_BUNDLE);
        addDrop(MBBlocks.ENDER_PEARL_BLOCK);
        addDrop(MBBlocks.QUARTZ_SHARD_BLOCK);
        addDrop(MBBlocks.AMETHYST_SHARD_BLOCK);

        addDrop(MBBlocks.BLAZE_ROD, (Block block) -> BlockLootTableGenerator.drops(block, Items.BLAZE_ROD));
        addDrop(MBBlocks.GLASS_DOOR, BlockLootTableGenerator::addDoorDrop);

        addDrop(MBBlocks.WALL_LANTERN, Items.LANTERN);
        addDrop(MBBlocks.WALL_SOUL_LANTERN, Items.SOUL_LANTERN);

        MBBlockFamilies.getFamilies()
                .forEach(family -> {
                    if (Objects.equals(Registry.BLOCK.getId(family.getBaseBlock()).getNamespace(), Moonbits.MOD_ID) && !this.generatedBlocks.contains(family.getBaseBlock())) {
                        addDrop(family.getBaseBlock());
                        this.generatedBlocks.add(family.getBaseBlock());
                    }
                    family.getVariants().forEach((variant, block) -> {
                        if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) && !this.generatedBlocks.contains(block)) {
                            this.generatedBlocks.add(block);
                            if (variant == MBBlockFamily.Variant.BOOKSHELF) {
                                addDrop(block, (Block l) -> BlockLootTableGenerator.drops(l, Items.BOOK, ConstantLootNumberProvider.create(3.0f)));
                            }
                            else if (variant == MBBlockFamily.Variant.DOOR) {
                                addDrop(block, BlockLootTableGenerator::addDoorDrop);
                            }
                            else if (variant == MBBlockFamily.Variant.SLAB) {
                                addDrop(block, BlockLootTableGenerator::slabDrops);
                            }
                            else {
                                addDrop(block);
                            }
                        }
                    });
                    family.cuttable.forEach(block -> {
                        if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MOD_ID) && !this.generatedBlocks.contains(block)) {
                            this.generatedBlocks.add(block);
                            if (block instanceof SlabBlock) {
                                addDrop(block, BlockLootTableGenerator::slabDrops);
                            }
                            addDrop(block);
                        }
                    });
                });

    }

    public static LootTable.Builder leafCarpet(Block block) {
        return BlockLootTableGenerator.dropsWithSilkTouchOrShears(block,
        BlockLootTableGenerator.applyExplosionDecay(block, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f)));
    }
    public static LootTable.Builder oreDrops(Block ore, ItemConvertible item, float min, float max) {
        return BlockLootTableGenerator.dropsWithSilkTouch(ore, BlockLootTableGenerator.applyExplosionDecay(ore,
                (ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
