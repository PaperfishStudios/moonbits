package net.paperfish.moonbits.data;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItems;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;
import net.paperfish.moonbits.registry.MBBlockFamilies;
import net.paperfish.moonbits.registry.MBBlockFamily;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class MBLootTableProvider extends FabricBlockLootTableProvider {
    public List<Block> generatedBlocks = new ArrayList<>();
    private static final LootCondition.Builder WITH_SILK_TOUCH = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))));
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH = WITH_SILK_TOUCH.invert();
    private static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS));
    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
    private static final Set<Item> EXPLOSION_IMMUNE = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemConvertible::asItem).collect(ImmutableSet.toImmutableSet());
    private static final float[] SAPLING_DROP_CHANCE = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};
    private static final float[] JUNGLE_SAPLING_DROP_CHANCE = new float[]{0.025f, 0.027777778f, 0.03125f, 0.041666668f, 0.1f};
    private static final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f};
    protected MBLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        Registry.BLOCK.forEach(block -> {
            if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID)) {
                if (block instanceof FlowerPotBlock) {
                    addPottedPlantDrop(block);
                }
            }
        });

        addDrop(MBBlocks.ROPE_LADDER);
        addDrop(MBBlocks.IRON_LADDER);
        addDrop(MBBlocks.KILN, BlockLootTableGenerator::nameableContainerDrops);
        addDrop(MBBlocks.COOKING_POT, BlockLootTableGenerator::nameableContainerDrops);
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

        addDrop(MBBlocks.LAMPROOT_LOG);
        addDrop(MBBlocks.LAMPROOT_WOOD);
        addDrop(MBBlocks.STRIPPED_LAMPROOT_LOG);
        addDrop(MBBlocks.STRIPPED_LAMPROOT_WOOD);
//        addDrop(MBBlocks.JUNIPER_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.JUNIPER_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.LAMPROOT_SAPLING);

        addDrop(MBBlocks.CEDAR_LOG);
        addDrop(MBBlocks.CEDAR_WOOD);
        addDrop(MBBlocks.STRIPPED_CEDAR_LOG);
        addDrop(MBBlocks.STRIPPED_CEDAR_WOOD);
        addDrop(MBBlocks.CEDAR_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.CEDAR_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.CEDAR_SAPLING);

        addDrop(MBBlocks.BARREL_CACTUS, (Block l) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with((ItemEntry.builder(l))
                        .apply(CopyStateFunction.builder(l).addProperty(BarrelCactusBlock.LEVEL)))));

        addDrop(MBBlocks.GOLDEN_BIRCH_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.GOLDEN_BIRCH_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.GOLDEN_BIRCH_SAPLING);

        addDrop(MBBlocks.RED_OAK_LEAVES, (Block l) -> BlockLootTableGenerator.oakLeavesDrop(l, MBBlocks.RED_OAK_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.RED_OAK_LEAF_CARPET, MBLootTableProvider::leafCarpet);
        addDrop(MBBlocks.RED_OAK_SAPLING);

//        addDrop(MBBlocks.ASPEN_TRUNK);
//        addDrop(MBBlocks.STRIPPED_ASPEN_TRUNK);
//        //addDrop(MBBlocks.ASPEN_PLANKS);
//        //addDrop(MBBlocks.ASPEN_SLAB, BlockLootTableGenerator::slabDrops);
//        //addDrop(MBBlocks.ASPEN_STAIRS);
//        addDrop(MBBlocks.ASPEN_TRIM);
//        addDrop(MBBlocks.ASPEN_LATTICE);
//        addDrop(MBBlocks.ASPEN_WINDOW);
//        addDrop(MBBlocks.ASPEN_LANTERN);
//        addDrop(MBBlocks.ASPEN_SOUL_LANTERN);
//        addDrop(MBBlocks.ASPEN_PALISADE);
//        addDrop(MBBlocks.STRIPPED_ASPEN_PALISADE);
//        addDrop(MBBlocks.ASPEN_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.ASPEN_SAPLING, SAPLING_DROP_CHANCE));
//        addDrop(MBBlocks.ASPEN_LEAF_CARPET, MBLootTableProvider::leafCarpet);
//        addDrop(MBBlocks.ASPEN_SAPLING);

        addDrop(MBBlocks.BOILING_CAULDRON, Items.CAULDRON);

        addDrop(MBBlocks.HONEY_CAULDRON, Items.CAULDRON);
        addDrop(MBBlocks.SYRUP_CAULDRON, Items.CAULDRON);
        addDrop(MBBlocks.TREE_TAP);
        addDrop(MBBlocks.SYRUP_BLOCK);

        addDrop(MBBlocks.FLOWERING_ACACIA_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, Blocks.ACACIA_SAPLING, SAPLING_DROP_CHANCE));

        addDrop(MBBlocks.LEAFBED, (Block block) -> BlockLootTableGenerator.drops(block, Blocks.DIRT));
        addDrop(MBBlocks.TOUGH_GRASS, (Block block) -> BlockLootTableGenerator.drops(block, MBBlocks.TOUGH_DIRT));
        addDrop(MBBlocks.SUBSTRATE, (Block block) -> BlockLootTableGenerator.drops(block, MBBlocks.TOUGH_DIRT));
        addDrop(MBBlocks.REGOLITH, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block,
                BlockLootTableGenerator.addSurvivesExplosionCondition(block, ItemEntry.builder(block)
                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.25f, 0.14285715f, 0.1f, 0f)).alternatively(ItemEntry.builder(MBBlocks.PEBBLES)))));
        addDrop(MBBlocks.PEAT_MOSS, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block,
                BlockLootTableGenerator.applyExplosionDecay(block, (ItemEntry.builder(MBItems.PEAT)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                        .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1, 4))))));

        addDrop(MBBlocks.PEAT_BLOCK);
        addDrop(MBBlocks.PEAT_DEPOSIT, (Block block) -> oreDrops(block, MBItems.PEAT, 1f, 2f));
        addDrop(MBBlocks.CLAY_DEPOSIT, (Block block) -> oreDrops(block, Items.CLAY_BALL, 2f, 4f));
        addDrop(MBBlocks.GOLD_DEPOSIT, (Block block) -> oreDrops(block, Items.GOLD_NUGGET, 1f, 4f));
        addDrop(MBBlocks.COPPER_DEPOSIT, (Block block) -> oreDrops(block, MBItems.COPPER_NUGGET, 2f, 5f));

        addDrop(MBBlocks.TIN_DEPOSIT, (Block block) -> oreDrops(block, MBItems.TIN_NUGGET, 2f, 5f));
        addDrop(MBBlocks.FROST_TIN_DEPOSIT, (Block block) -> oreDrops(block, MBItems.TIN_NUGGET, 2f, 5f));
        addDrop(MBBlocks.TIN_ORE, (Block block) -> oreDrops(block, MBItems.RAW_TIN, 2, 4));
        addDrop(MBBlocks.DEEPSLATE_TIN_ORE, (Block block) -> oreDrops(block, MBItems.RAW_TIN, 2, 4));
        addDrop(MBBlocks.CHERT_TIN_ORE, (Block block) -> oreDrops(block, MBItems.RAW_TIN, 2, 4));
        addDrop(MBBlocks.RAW_TIN_BLOCK);
        addDrop(MBBlocks.TIN_BLOCK);
        addDrop(MBBlocks.TIN_PILLAR);
        addDrop(MBBlocks.TIN_TRAPDOOR);
        addDrop(MBBlocks.TIN_DOOR, BlockLootTableGenerator::addDoorDrop);

        addDrop(MBBlocks.PERMAFROST);
        addDrop(MBBlocks.FROST_PEAT, (Block block) -> oreDrops(block, MBItems.PEAT, 1f, 2f));
        addDrop(MBBlocks.FROST_CLAY, (Block block) -> oreDrops(block, Items.CLAY_BALL, 2f, 4f));
        addDrop(MBBlocks.FROST_GOLD, (Block block) -> oreDrops(block, Items.GOLD_NUGGET, 1f, 4f));
        addDrop(MBBlocks.FROST_COPPER, (Block block) -> oreDrops(block, MBItems.COPPER_NUGGET, 2f, 5f));

        addDrop(Blocks.ICE, (Block block) -> oreDrops(block, MBItems.ICE_CUBES, 1f, 2f));

        addDrop(MBBlocks.SNOW_BRICKS);
        addDrop(MBBlocks.ICE_BRICKS);
        addDrop(MBBlocks.PACKED_ICE_BRICKS);

        addDrop(MBBlocks.CHERT_COAL_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.COAL));
        addDrop(MBBlocks.CHERT_GOLD_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.RAW_GOLD));
        addDrop(MBBlocks.CHERT_COPPER_ORE, BlockLootTableGenerator::copperOreDrops);
        addDrop(MBBlocks.CHERT_REDSTONE_ORE, BlockLootTableGenerator::redstoneOreDrops);
        addDrop(MBBlocks.CHERT_LAPIS_ORE, BlockLootTableGenerator::lapisOreDrops);

        addDrop(MBBlocks.REDSTONE_CLUSTER, (Block block) -> oreDrops(block, Items.REDSTONE, 2f, 5f));
        addDrop(MBBlocks.LARGE_REDSTONE_BUD, (Block block) -> oreDrops(block, Items.REDSTONE, 1f, 3f));
        addDrop(MBBlocks.MEDIUM_REDSTONE_BUD, (Block block) -> oreDrops(block, Items.REDSTONE, 0f, 2f));
        addDrop(MBBlocks.SMALL_REDSTONE_BUD, (Block block) -> oreDrops(block, Items.REDSTONE, 0f, 1f));

        addDrop(MBBlocks.BANDED_IRON, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.RAW_IRON));
        addDrop(MBBlocks.HEMATITE_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, MBItems.HEMATITE));
        addDrop(MBBlocks.HEMATITE_BLOCK);

        addDrop(MBBlocks.CRACKED_MUD);
        addDrop(MBBlocks.RICH_MUD);
        addDrop(MBBlocks.MUD_GOLD_DEPOSIT, (Block block) -> oreDrops(block, Items.GOLD_NUGGET, 1f, 4f));

        addDrop(MBBlocks.PAVED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.PAVED_RED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS);

        addDrop(MBBlocks.CANVAS);
        addDrop(MBBlocks.FRAMED_CANVAS);

        addDrop(MBBlocks.PEBBLES, (Block block) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with(BlockLootTableGenerator.applyExplosionDecay(MBBlocks.PEBBLES,
                        (ItemEntry.builder(block).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))
                                .conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 2)))))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(3.0f)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 3))))
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0f)).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(PebbleBlock.PEBBLES, 4))))))));

        addDrop(MBBlocks.WILD_CARROTS, (Block block) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with(BlockLootTableGenerator.applyExplosionDecay(block, (ItemEntry.builder(Items.CARROT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))))));
        addDrop(MBBlocks.WILD_POTATOES, (Block block) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with(BlockLootTableGenerator.applyExplosionDecay(block, (ItemEntry.builder(Items.POTATO))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))))));
        addDrop(MBBlocks.SEA_BEETS, (Block block) -> LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                .with(BlockLootTableGenerator.applyExplosionDecay(block, (ItemEntry.builder(Items.BEETROOT_SEEDS))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))))));

        addDrop(MBBlocks.BEACHGRASS, (Block block) -> grassDrops(block, Items.WHEAT_SEEDS, 1f, 1f));
        addDrop(MBBlocks.TALL_BEACHGRASS, (Block block) -> BlockLootTableGenerator.tallGrassDrops(block, MBBlocks.BEACHGRASS));

        addDrop(MBBlocks.COTTONGRASS, (Block block) -> grassDrops(block, Items.WHEAT_SEEDS, 1f, 1f));
        addDrop(MBBlocks.TALL_COTTONGRASS, (Block block) -> BlockLootTableGenerator.tallGrassDrops(block, MBBlocks.COTTONGRASS));
        addDrop(MBBlocks.WHITE_HEATHER);
        addDrop(MBBlocks.RED_HEATHER);
        addDrop(MBBlocks.ORANGE_HEATHER);
        addDrop(MBBlocks.PURPLE_HEATHER);
        addDrop(MBBlocks.LUPINE, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

        addDrop(MBBlocks.FROSTHORN_CROWN);
        addDrop(MBBlocks.FROSTHORN_STEM, (Block block) -> grassDrops(block, MBItems.FROSTHORN_SEED, 1f, 1f));
        addDrop(MBBlocks.FROSTHORN_LEAVES, dropsWithShears(MBBlocks.FROSTHORN_LEAVES));
        addDrop(MBBlocks.FROSTHORN_FRUIT);

        addDrop(MBBlocks.DESERT_BRUSH, (Block block) -> grassDrops(block, MBItems.PEPPER_SEEDS, 1f, 1f));
        addDrop(MBBlocks.TALL_DESERT_BRUSH, (Block block) -> BlockLootTableGenerator.tallGrassDrops(block, MBBlocks.DESERT_BRUSH));

        addDrop(MBBlocks.MARIGOLD);

        BlockStatePropertyLootCondition.Builder peanutbuilder = BlockStatePropertyLootCondition.builder(MBBlocks.PEANUT_CROP)
                .properties(StatePredicate.Builder.create().exactMatch(PeanutCropBlock.AGE, 7));
        this.addDrop(MBBlocks.PEANUT_CROP, BlockLootTableGenerator.applyExplosionDecay(MBBlocks.PEANUT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(MBItems.PEANUT)))
                .pool(LootPool.builder().conditionally(peanutbuilder).with(ItemEntry.builder(MBItems.PEANUT)
                        .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3))))));

        addDrop(MBBlocks.PEPPER_CROP, BlockLootTableGenerator.cropDrops(MBBlocks.PEPPER_CROP, MBItems.PEPPER, MBItems.PEPPER_SEEDS,
                BlockStatePropertyLootCondition.builder(MBBlocks.PEPPER_CROP).properties(StatePredicate.Builder.create().exactMatch(PepperCropBlock.AGE, 7))));

        addDrop(MBBlocks.PRICKLY_PEAR_CACTUS, dropsWithShears(MBBlocks.PRICKLY_PEAR_CACTUS));
        addDrop(MBBlocks.TALL_PRICKLY_PEAR_CACTUS, dropsWithShears(MBBlocks.PRICKLY_PEAR_CACTUS,
                (ItemEntry.builder(MBBlocks.PRICKLY_PEAR_CACTUS).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2))))));

        addDrop(MBBlocks.BUTTERCUP);
        addDrop(MBBlocks.FORGETMENOT);
        addDrop(MBBlocks.PINK_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.WHITE_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.LIGHT_BLUE_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.RED_HYACINTH, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
        addDrop(MBBlocks.WILDFLOWERS);
        addDrop(MBBlocks.CLOVER);
        addDrop(MBBlocks.PUFFBALLS);
        addDrop(MBBlocks.SAFFRON_MUSHROOM);
        addDrop(MBBlocks.TOADSTOOL);
        addDrop(MBBlocks.SMALL_TOADSTOOLS);

        addDrop(MBBlocks.MYCELIUM_ROOTS, BlockLootTableGenerator::dropsWithShears);
        addDrop(MBBlocks.RED_MUSHROOM_CAP);
        addDrop(MBBlocks.BROWN_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_GILLS, BlockLootTableGenerator::dropsWithShears);
        addDrop(MBBlocks.GIANT_TOADSTOOL_CAP);
        addDrop(MBBlocks.MUSHROOM_STEM);
        addDrop(MBBlocks.STRIPPED_MUSHROOM_STEM);
        addDrop(MBBlocks.MUSHROOM_HYPHAE);
        addDrop(MBBlocks.STRIPPED_MUSHROOM_HYPHAE);

        //addDrop(MBBlocks.RED_MUSH_BRICKS);
        //addDrop(MBBlocks.BROWN_MUSH_BRICKS);
        addDrop(MBBlocks.RED_MUSH_LAMP);
        addDrop(MBBlocks.BROWN_MUSH_LAMP);
        addDrop(MBBlocks.SAFFRON_MUSH_LAMP);
        addDrop(MBBlocks.TOADSTOOL_MUSH_LAMP);

        addDrop(MBBlocks.LAMPROOT_BULB);
        addDrop(MBBlocks.CAVEBLOOM_FLOWERS, (Block block) -> BlockLootTableGenerator.multifaceGrowthDrops(block, WITH_SHEARS));
        addDrop(MBBlocks.CAVEBLOOM_VINE, (Block block) -> BlockLootTableGenerator.multifaceGrowthDrops(block, WITH_SHEARS));

        addDrop(MBBlocks.FUR_BLOCK);
        addDrop(MBBlocks.FUR_CARPET);
        addDrop(MBBlocks.BEDROLL, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));

        addDrop(MBBlocks.APPLE_CRATE);
        addDrop(MBBlocks.CARROT_CRATE);
        addDrop(MBBlocks.POTATO_CRATE);
        addDrop(MBBlocks.BEETROOT_CRATE);
        addDrop(MBBlocks.PEPPER_CRATE);

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

        addDrop(MBBlocks.SUGAR_CUBE);
        addDrop(MBBlocks.PACKED_GLOWSTONE);
        addDrop(MBBlocks.GUNPOWDER_CRATE);

        addDrop(MBBlocks.SUGAR_CANE_BUNDLE);
        addDrop(MBBlocks.BAMBOO_BUNDLE);
        addDrop(MBBlocks.KELP_BLOCK);
        addDrop(MBBlocks.NETHER_WART_SACK);
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

        addDrop(MBBlocks.CHORUS_BUNDLE);

        addDrop(MBBlocks.BLAZE_ROD, (Block block) -> BlockLootTableGenerator.drops(block, Items.BLAZE_ROD));
        addDrop(MBBlocks.GLASS_DOOR, BlockLootTableGenerator::addDoorDrop);

        addDrop(MBBlocks.WALL_LANTERN, Items.LANTERN);
        addDrop(MBBlocks.WALL_SOUL_LANTERN, Items.SOUL_LANTERN);

        MBBlockFamilies.getFamilies()
                .forEach(family -> {
                    Block baseBlock = family.getBaseBlock();
                    if (Objects.equals(Registry.BLOCK.getId(baseBlock).getNamespace(), Moonbits.MODID) && !this.generatedBlocks.contains(baseBlock)) {
                        if (baseBlock == MBBlocks.CHERT) {
                            addDrop(baseBlock, MBBlocks.COBBLED_CHERT);
                        }
                        else {
                            addDrop(baseBlock);
                        }
                        this.generatedBlocks.add(family.getBaseBlock());
                    }
                    family.getVariants().forEach((variant, block) -> {
                        if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID) && !this.generatedBlocks.contains(block)) {
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
                            else if (block == MBBlocks.CHERT) {
                                addDrop(block, MBBlocks.COBBLED_CHERT);
                            }
                            else {
                                addDrop(block);
                            }
                        }
                    });
                    family.cuttable.forEach(block -> {
                        if (Objects.equals(Registry.BLOCK.getId(block).getNamespace(), Moonbits.MODID) && !this.generatedBlocks.contains(block)) {
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
    public static LootTable.Builder grassDrops(Block dropWithShears, ItemConvertible item, float min, float max) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, BlockLootTableGenerator.applyExplosionDecay(dropWithShears,
                (ItemEntry.builder(item).conditionally(RandomChanceLootCondition.builder(0.125f)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2)));
    }
    public static LootTable.Builder oreDrops(Block ore, ItemConvertible item, float min, float max) {
        return BlockLootTableGenerator.dropsWithSilkTouch(ore, BlockLootTableGenerator.applyExplosionDecay(ore,
                (ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}
