package net.paperfish.moonbits.data;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.*;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.*;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class MBLootTableProvider extends FabricBlockLootTableProvider {
    public List<Block> generatedBlocks = new ArrayList<>();
	public static final LootCondition.Builder WITH_SILK_TOUCH = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))));
	public static final LootCondition.Builder WITHOUT_SILK_TOUCH = WITH_SILK_TOUCH.invert();
	public static final LootCondition.Builder WITH_SHEARS = MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS));
	public static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
	public static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();
	public static final Set<Item> EXPLOSION_IMMUNE = Stream.of(Blocks.DRAGON_EGG, Blocks.BEACON, Blocks.CONDUIT, Blocks.SKELETON_SKULL, Blocks.WITHER_SKELETON_SKULL, Blocks.PLAYER_HEAD, Blocks.ZOMBIE_HEAD, Blocks.CREEPER_HEAD, Blocks.DRAGON_HEAD, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX).map(ItemConvertible::asItem).collect(ImmutableSet.toImmutableSet());
	public static final float[] SAPLING_DROP_CHANCE = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};
	public static final float[] JUNGLE_SAPLING_DROP_CHANCE = new float[]{0.025f, 0.027777778f, 0.03125f, 0.041666668f, 0.1f};
	public static final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f};
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

        addDrop(MBBlocks.KILN, BlockLootTableGenerator::nameableContainerDrops);

        addDrop(MBBlocks.WHITE_CUSHION);
        addDrop(MBBlocks.LIGHT_GRAY_CUSHION);
        addDrop(MBBlocks.GRAY_CUSHION);
        addDrop(MBBlocks.BLACK_CUSHION);
        addDrop(MBBlocks.GREEN_CUSHION);
        addDrop(MBBlocks.LIME_CUSHION);
        addDrop(MBBlocks.YELLOW_CUSHION);
        addDrop(MBBlocks.ORANGE_CUSHION);
        addDrop(MBBlocks.BROWN_CUSHION);
        addDrop(MBBlocks.RED_CUSHION);
        addDrop(MBBlocks.PINK_CUSHION);
        addDrop(MBBlocks.MAGENTA_CUSHION);
        addDrop(MBBlocks.PURPLE_CUSHION);
        addDrop(MBBlocks.LIGHT_BLUE_CUSHION);
        addDrop(MBBlocks.CYAN_CUSHION);
        addDrop(MBBlocks.BLUE_CUSHION);

        addDrop(MBBlocks.LAMPROOT_LOG);
        addDrop(MBBlocks.LAMPROOT_WOOD);
        addDrop(MBBlocks.STRIPPED_LAMPROOT_LOG);
        addDrop(MBBlocks.STRIPPED_LAMPROOT_WOOD);

        addDrop(MBBlocks.CEDAR_LOG);
        addDrop(MBBlocks.CEDAR_WOOD);
        addDrop(MBBlocks.STRIPPED_CEDAR_LOG);
        addDrop(MBBlocks.STRIPPED_CEDAR_WOOD);
        addDrop(MBBlocks.CEDAR_LEAVES, (Block l) -> BlockLootTableGenerator.leavesDrop(l, MBBlocks.CEDAR_SAPLING, SAPLING_DROP_CHANCE));
        addDrop(MBBlocks.CEDAR_SAPLING);

        addDrop(MBBlocks.TINY_BARREL_CACTUS);
        addDrop(MBBlocks.SMALL_BARREL_CACTUS);
        addDrop(MBBlocks.BARREL_CACTUS);
        addDrop(MBBlocks.LARGE_BARREL_CACTUS);

        addDrop(MBBlocks.TREE_TAP);
        addDrop(MBBlocks.SYRUP_BLOCK);
		addDrop(MBBlocks.SAP_BLOCK);
		addDrop(MBBlocks.RESIN_BLOCK);

        addDrop(MBBlocks.REGOLITH, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block,
                BlockLootTableGenerator.addSurvivesExplosionCondition(block, ItemEntry.builder(block)
                        .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.25f, 0.14285715f, 0.1f, 0f)).alternatively(ItemEntry.builder(MBBlocks.PEBBLES)))));
        addDrop(MBBlocks.PEAT_MOSS, (Block block) -> BlockLootTableGenerator.dropsWithSilkTouch(block,
                BlockLootTableGenerator.applyExplosionDecay(block, (ItemEntry.builder(MBItems.PEAT)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f))))
                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
                        .apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(1, 4))))));

        addDrop(MBBlocks.PEAT_BLOCK);
        addDrop(MBBlocks.DEEP_ROOTED_SOIL, (Block block) -> oreDrops(block, MBItems.PEAT, 1f, 2f));
		addDrop(MBBlocks.FLINT_DEPOSIT, (Block block) -> oreDrops(block, Items.FLINT, 1f, 1f));
        addDrop(MBBlocks.MYCELIAL_DIRT, (Block block) -> oreDrops(block, Items.CLAY_BALL, 3f, 5f));
        addDrop(MBBlocks.DECOMPOSING_DIRT);
        addDrop(MBBlocks.FUZZ_BLOCK);

		addDrop(MBBlocks.FLINT_BLOCK);

//        MBEvents.WAX_OFF.forEach((waxed, base) -> addDrop(waxed));

        addDrop(MBBlocks.PERMAFROST);

		addDrop(MBBlocks.BEARD_MOSS_BLOCK);
		addDrop(MBBlocks.BEARD_MOSS_CARPET);

        addDrop(Blocks.ICE, (Block block) -> oreDrops(block, MBItems.ICE_CHUNK, 1f, 2f));

        addDrop(MBBlocks.SNOW_BRICKS);
        addDrop(MBBlocks.PACKED_ICE_BRICKS);
		addDrop(MBBlocks.BLUE_ICE_BRICKS);

		addDrop(MBBlocks.HELIODOR_ROD);
		addDrop(MBBlocks.LARIMAR_ROD);

        addDrop(MBBlocks.CHERT_COAL_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.COAL));
        addDrop(MBBlocks.CHERT_GOLD_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.RAW_GOLD));
        addDrop(MBBlocks.CHERT_COPPER_ORE, BlockLootTableGenerator::copperOreDrops);
        addDrop(MBBlocks.CHERT_REDSTONE_ORE, BlockLootTableGenerator::redstoneOreDrops);
        addDrop(MBBlocks.CHERT_LAPIS_ORE, BlockLootTableGenerator::lapisOreDrops);
		addDrop(MBBlocks.CHERT_DIAMOND_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.DIAMOND));

        addDrop(MBBlocks.BANDED_IRON, (Block block) -> BlockLootTableGenerator.oreDrops(block, Items.RAW_IRON));
        addDrop(MBBlocks.MAGNETITE_ORE, (Block block) -> BlockLootTableGenerator.oreDrops(block, MBItems.MAGNETITE));
        addDrop(MBBlocks.MAGNETITE_BLOCK);

		addDrop(MBBlocks.COPPER_OXIDE_LANTERN);
		addDrop(MBBlocks.COPPER_OXIDE_CAMPFIRE, block -> dropsWithSilkTouch(
						block, addSurvivesExplosionCondition(block, ItemEntry.builder(MBItems.COPPER_OXIDE).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F))))
				));

        addDrop(MBBlocks.PAVED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.CRACKED_PAVED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.PAVED_RED_SANDSTONE_BRICKS);
        addDrop(MBBlocks.CRACKED_PAVED_RED_SANDSTONE_BRICKS);

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
        addDrop(MBBlocks.FROSTY_HEATHER);
        addDrop(MBBlocks.SUNSET_HEATHER);
        addDrop(MBBlocks.TWILIGHT_HEATHER);
        addDrop(MBBlocks.LUPINE, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));
		addDrop(MBBlocks.YUCCA, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, TallPlantBlock.HALF, DoubleBlockHalf.LOWER));

        addDrop(MBBlocks.DESERT_BRUSH, (Block block) -> grassDrops(block, Items.MELON_SEEDS, 1f, 1f));
        addDrop(MBBlocks.TALL_DESERT_BRUSH, (Block block) -> BlockLootTableGenerator.tallGrassDrops(block, MBBlocks.DESERT_BRUSH));

        addDrop(MBBlocks.MARIGOLD);

		addDrop(MBBlocks.DESERT_VASE, LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						// junk
						.with(ItemEntry.builder(Items.BONE).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
						.with(ItemEntry.builder(Items.ROTTEN_FLESH).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
						.with(ItemEntry.builder(MBItems.BURLAP).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
						.with(ItemEntry.builder(Items.STRING).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
						.with(ItemEntry.builder(Blocks.SAND).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 8.0F))))
						// gear
						.with(ItemEntry.builder(Items.GUNPOWDER).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
						.with(ItemEntry.builder(Items.TORCH).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 8.0F))))
						.with(ItemEntry.builder(Items.HONEY_BOTTLE).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
						.with(ItemEntry.builder(MBItems.FIBER).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0F, 9.0F))))
						.with(ItemEntry.builder(Items.ARROW).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 14.0F))))
						// valuables
						.with(ItemEntry.builder(Items.EMERALD).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
						.with(ItemEntry.builder(Items.GOLD_NUGGET).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0F, 12.0F))))
						.with(ItemEntry.builder(Items.GOLD_NUGGET).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(10.0F, 18.0F))))
						.with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
						.with(ItemEntry.builder(Items.LAPIS_LAZULI).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 9.0F))))
						.with(ItemEntry.builder(Items.POTION).weight(1).apply(SetPotionFunction.builder(Potions.HEALING)))
						.with(ItemEntry.builder(Items.POTION).weight(1).apply(SetPotionFunction.builder(Potions.HARMING)))
				)
		);

		addDrop(MBBlocks.MUD_VESSEL, LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						// junk
						.with(ItemEntry.builder(Items.WHEAT).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
						.with(ItemEntry.builder(Items.CLAY_BALL).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
						.with(ItemEntry.builder(MBItems.SAP).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
						.with(ItemEntry.builder(MBItems.SOURSOB).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 4.0F))))
						.with(ItemEntry.builder(Items.STRING).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 6.0F))))
						// gear
						.with(ItemEntry.builder(Items.GUNPOWDER).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 3.0F))))
						.with(ItemEntry.builder(Items.ARROW).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5.0F, 14.0F))))
						// valuables
						.with(ItemEntry.builder(Items.EMERALD).weight(3).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 5.0F))))
				)
		);

		addDrop(MBBlocks.RABBIT_MOUND, LootTable.builder()
				.pool(LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						// common stuff
						.with(ItemEntry.builder(Items.CARROT).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))))
						.with(ItemEntry.builder(Items.CARROT).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
						.with(ItemEntry.builder(MBBlocks.PEBBLES).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
						.with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(6).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
						.with(ItemEntry.builder(Items.CLAY_BALL).weight(10).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F))))
						.with(EmptyEntry.builder())
						// valuables
						.with(ItemEntry.builder(Items.GOLDEN_CARROT).weight(1))
						.with(ItemEntry.builder(Items.RABBIT_FOOT).weight(2))
				)
		);

		addDrop(MBBlocks.BRITTLEBUSH_FLOWERS, blockx -> dropsWithSilkTouch(
				blockx,
				applyExplosionDecay(
						blockx,
						ItemEntry.builder(MBItems.BRITTLEBUSH)
								.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))
								.apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
								.apply(LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(0, 2)))
				)
		));
		addDrop(MBBlocks.BRITTLEBUSH_LEAVES, (Block l) -> hardyLeavesDrop(l, MBItems.BRITTLEBUSH, SAPLING_DROP_CHANCE));

		addDrop(MBBlocks.OCOTILLO);
		addDrop(MBBlocks.FLOWERING_OCOTILLO);

//        BlockStatePropertyLootCondition.Builder peanutbuilder = BlockStatePropertyLootCondition.builder(MBBlocks.PEANUT_CROP)
//                .properties(StatePredicate.Builder.create().exactMatch(PeanutCropBlock.AGE, 7));
//        this.addDrop(MBBlocks.PEANUT_CROP, BlockLootTableGenerator.applyExplosionDecay(MBBlocks.PEANUT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(MBItems.PEANUT)))
//                .pool(LootPool.builder().conditionally(peanutbuilder).with(ItemEntry.builder(MBItems.PEANUT)
//                        .apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3))))));

        addDrop(MBBlocks.SOURSOBS, MBItems.SOURSOB);
        addDrop(MBBlocks.SAFFRON_MUSHROOM);

        addDrop(MBBlocks.RED_MUSHROOM_CAP);
        addDrop(MBBlocks.BROWN_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_MUSHROOM_CAP);
        addDrop(MBBlocks.SAFFRON_GILLS, BlockLootTableGenerator::dropsWithShears);
        addDrop(MBBlocks.MUSHROOM_STEM);
        addDrop(MBBlocks.STRIPPED_MUSHROOM_STEM);
        addDrop(MBBlocks.MUSHROOM_HYPHAE);
        addDrop(MBBlocks.STRIPPED_MUSHROOM_HYPHAE);

//        addDrop(MBBlocks.RED_MUSHCLAY, (Block block) -> BlockLootTableGenerator.drops(block, MBItems.RED_MUSHBLEND, ConstantLootNumberProvider.create(4.0f)));
//        addDrop(MBBlocks.BROWN_MUSHCLAY, (Block block) -> BlockLootTableGenerator.drops(block, MBItems.BROWN_MUSHBLEND, ConstantLootNumberProvider.create(4.0f)));
//        addDrop(MBBlocks.SAFFRON_MUSHCLAY, (Block block) -> BlockLootTableGenerator.drops(block, MBItems.SAFFRON_MUSHBLEND, ConstantLootNumberProvider.create(4.0f)));
//
//        addDrop(MBBlocks.RED_MUSH_LAMP);
//        addDrop(MBBlocks.BROWN_MUSH_LAMP);
//        addDrop(MBBlocks.SAFFRON_MUSH_LAMP);

        addDrop(MBBlocks.LAMPROOT_BULB);
        addDrop(MBBlocks.CAVEBLOOM_FLOWERS, (Block block) -> BlockLootTableGenerator.m_gsfzuvjv(block, WITH_SHEARS));
        addDrop(MBBlocks.CAVEBLOOM_VINE, (Block block) -> BlockLootTableGenerator.m_gsfzuvjv(block, WITH_SHEARS));

//        addDrop(MBBlocks.FUR_BLOCK);
//        addDrop(MBBlocks.FUR_CARPET);
        addDrop(MBBlocks.BEDROLL, (Block block) -> BlockLootTableGenerator.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));

		addDrop(MBBlocks.BEAM, MBLootTableProvider::beamDrops);

		addDrop(MBBlocks.CHISELED_PACKED_MUD);

        addDrop(MBBlocks.APPLE_CRATE);
        addDrop(MBBlocks.CARROT_CRATE);
        addDrop(MBBlocks.POTATO_CRATE);
        addDrop(MBBlocks.BEETROOT_CRATE);

        addDrop(MBBlocks.EGG_BASKET);
        addDrop(MBBlocks.COCOA_SACK);
        addDrop(MBBlocks.GLISTERING_MELON_BLOCK);
        addDrop(MBBlocks.SWEET_BERRY_BASKET);
        addDrop(MBBlocks.GLOW_BERRY_BASKET);

//        addDrop(MBItems.SWEET_BERRY_PITS);
//        addDrop(MBItems.GLOW_BERRY_PITS);

        addDrop(MBBlocks.SUGAR_CUBE);
        addDrop(MBBlocks.PACKED_GLOWSTONE);
        addDrop(MBBlocks.GUNPOWDER_CRATE);

        addDrop(MBBlocks.SUGAR_CANE_BUNDLE);
        addDrop(MBBlocks.BAMBOO_BUNDLE);
		addDrop(MBBlocks.OCOTILLO_BUNDLE);
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

    public static LootTable.Builder hardyLeavesDrop(Block leaves, Item drop, float ... chance) {
        return BlockLootTableGenerator.dropsWithSilkTouchOrShears(leaves, (
                BlockLootTableGenerator.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)))
                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, chance)))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS)
                        .with(BlockLootTableGenerator.applyExplosionDecay(leaves, ItemEntry.builder(Items.STICK)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))))
                                .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, LEAVES_STICK_DROP_CHANCE))));
    }

	public static LootTable.Builder beamDrops(Block drop) {
		return LootTable.builder()
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
							ItemEntry.builder(drop)
								.apply(
									SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
										.conditionally(BlockStatePropertyLootCondition.builder(drop)
										.properties(StatePredicate.Builder.create().exactMatch(BeamBlock.BOTTOM_STATE, BeamStates.X)))
								)
						)
						.with(
							ItemEntry.builder(drop)
								.apply(
									SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
										.conditionally(BlockStatePropertyLootCondition.builder(drop)
										.properties(StatePredicate.Builder.create().exactMatch(BeamBlock.BOTTOM_STATE, BeamStates.Z)))
								)
						)
						.with(
							ItemEntry.builder(drop)
								.apply(
									SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
										.conditionally(BlockStatePropertyLootCondition.builder(drop)
										.properties(StatePredicate.Builder.create().exactMatch(BeamBlock.TOP_STATE, BeamStates.X)))
								)
						)
						.with(
							ItemEntry.builder(drop)
								.apply(
									SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))
										.conditionally(BlockStatePropertyLootCondition.builder(drop)
										.properties(StatePredicate.Builder.create().exactMatch(BeamBlock.TOP_STATE, BeamStates.Z)))
								)
						)
			);
	}
}
