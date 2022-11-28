package net.paperfish.moonbits.registry;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.data.MBLootTableProvider;
import net.paperfish.moonbits.mixin.PigSaddleAccess;
import net.paperfish.moonbits.mixin.StriderSaddleAccess;
import net.paperfish.moonbits.recipe.WashingHandler;

import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;

public class MBEvents {
    public static final BiMap<Block, Block> WAXING = new ImmutableBiMap.Builder<Block, Block>()
            .put(MBBlocks.TIN_BLOCK, MBBlocks.WAXED_TIN_BLOCK)
            .put(MBBlocks.OXIDIZED_TIN_BLOCK, MBBlocks.WAXED_OXIDIZED_TIN_BLOCK)
            .put(MBBlocks.BLACKENED_TIN_BLOCK, MBBlocks.WAXED_BLACKENED_TIN_BLOCK)
            .put(MBBlocks.PESTERED_TIN_BLOCK, MBBlocks.WAXED_PESTERED_TIN_BLOCK)

            .put(MBBlocks.CUT_TIN, MBBlocks.WAXED_CUT_TIN)
            .put(MBBlocks.OXIDIZED_CUT_TIN, MBBlocks.WAXED_OXIDIZED_CUT_TIN)
            .put(MBBlocks.BLACKENED_CUT_TIN, MBBlocks.WAXED_BLACKENED_CUT_TIN)
            .put(MBBlocks.PESTERED_CUT_TIN, MBBlocks.WAXED_PESTERED_CUT_TIN)

            .put(MBBlocks.CUT_TIN_SLAB, MBBlocks.WAXED_CUT_TIN_SLAB)
            .put(MBBlocks.OXIDIZED_CUT_TIN_SLAB, MBBlocks.WAXED_OXIDIZED_CUT_TIN_SLAB)
            .put(MBBlocks.BLACKENED_CUT_TIN_SLAB, MBBlocks.WAXED_BLACKENED_CUT_TIN_SLAB)
            .put(MBBlocks.PESTERED_CUT_TIN_SLAB, MBBlocks.WAXED_PESTERED_CUT_TIN_SLAB)

            .put(MBBlocks.CUT_TIN_STAIRS, MBBlocks.WAXED_CUT_TIN_STAIRS)
            .put(MBBlocks.OXIDIZED_CUT_TIN_STAIRS, MBBlocks.WAXED_OXIDIZED_CUT_TIN_STAIRS)
            .put(MBBlocks.BLACKENED_CUT_TIN_STAIRS, MBBlocks.WAXED_BLACKENED_CUT_TIN_STAIRS)
            .put(MBBlocks.PESTERED_CUT_TIN_STAIRS, MBBlocks.WAXED_PESTERED_CUT_TIN_STAIRS)

			.put(MBBlocks.TIN_TRAPDOOR, MBBlocks.WAXED_TIN_TRAPDOOR)
			.put(MBBlocks.OXIDIZED_TIN_TRAPDOOR, MBBlocks.WAXED_OXIDIZED_TIN_TRAPDOOR)
			.put(MBBlocks.BLACKENED_TIN_TRAPDOOR, MBBlocks.WAXED_BLACKENED_TIN_TRAPDOOR)
			.put(MBBlocks.PESTERED_TIN_TRAPDOOR, MBBlocks.WAXED_PESTERED_TIN_TRAPDOOR)
            .build();
    public static final BiMap<Block, Block> WAX_OFF = WAXING.inverse();
    public static final Map<Block, Block> SCRAPING = new ImmutableMap.Builder<Block, Block>()
            .put(MBBlocks.OXIDIZED_TIN_BLOCK, MBBlocks.TIN_BLOCK)
            .put(MBBlocks.BLACKENED_TIN_BLOCK, MBBlocks.TIN_BLOCK)

            .put(MBBlocks.OXIDIZED_CUT_TIN, MBBlocks.CUT_TIN)
            .put(MBBlocks.BLACKENED_CUT_TIN, MBBlocks.CUT_TIN)

            .put(MBBlocks.OXIDIZED_CUT_TIN_SLAB, MBBlocks.CUT_TIN_SLAB)
            .put(MBBlocks.BLACKENED_CUT_TIN_SLAB, MBBlocks.CUT_TIN_SLAB)

            .put(MBBlocks.OXIDIZED_CUT_TIN_STAIRS, MBBlocks.CUT_TIN_STAIRS)
            .put(MBBlocks.BLACKENED_CUT_TIN_STAIRS, MBBlocks.CUT_TIN_STAIRS)

			.put(MBBlocks.OXIDIZED_TIN_TRAPDOOR, MBBlocks.TIN_TRAPDOOR)
			.put(MBBlocks.BLACKENED_TIN_TRAPDOOR, MBBlocks.TIN_TRAPDOOR)
            .build();

    // maps for blocks that grow into other blocks (eg hardy berry leaves and barrel cacti)
    public static BiMap<Block, Block> GROWING = new ImmutableBiMap.Builder<Block, Block>()
            .put(MBBlocks.TINY_BARREL_CACTUS, MBBlocks.SMALL_BARREL_CACTUS)
            .put(MBBlocks.SMALL_BARREL_CACTUS, MBBlocks.BARREL_CACTUS)
            .put(MBBlocks.BARREL_CACTUS, MBBlocks.LARGE_BARREL_CACTUS)
            .build();
    public static BiMap<Block, Block> PLUCK = GROWING.inverse();

	public static final Map<Block, Block> TAPPABLE = new ImmutableMap.Builder<Block, Block>()
			.put(Blocks.OAK_LOG, MBBlocks.SAP_TREE_TAP)
			.put(Blocks.SPRUCE_LOG, MBBlocks.SAP_TREE_TAP)
			.put(Blocks.BIRCH_LOG, MBBlocks.SYRUP_TREE_TAP)
			.put(Blocks.JUNGLE_LOG, MBBlocks.SAP_TREE_TAP)
			.put(Blocks.ACACIA_LOG, MBBlocks.SAP_TREE_TAP)
			.put(Blocks.DARK_OAK_LOG, MBBlocks.SAP_TREE_TAP)
			.put(Blocks.MANGROVE_LOG, MBBlocks.SAP_TREE_TAP)
			.put(MBBlocks.LAMPROOT_LOG, MBBlocks.SAP_TREE_TAP)
			.put(MBBlocks.CEDAR_LOG, MBBlocks.RESIN_TREE_TAP)
			.build();
	public static final Map<Block, Item> TAP_RESULT = new ImmutableMap.Builder<Block, Item>()
			.put(MBBlocks.SAP_TREE_TAP, MBItems.SAP)
			.put(MBBlocks.SYRUP_TREE_TAP, MBItems.SYRUP_BOTTLE)
			.put(MBBlocks.RESIN_TREE_TAP, MBItems.RESIN)
			.build();

	public static final Map<Identifier, Item> GLASS_SHARD_LOOT = new ImmutableMap.Builder<Identifier, Item>()
			.put(Blocks.GLASS.getLootTableId(), MBItems.GLASS_SHARD)
			.put(Blocks.ICE.getLootTableId(), MBItems.ICE_CHUNK)
			.put(Blocks.WHITE_STAINED_GLASS.getLootTableId(), MBItems.WHITE_GLASS_SHARD)
			.put(Blocks.LIGHT_GRAY_STAINED_GLASS.getLootTableId(), MBItems.LIGHT_GRAY_GLASS_SHARD)
			.put(Blocks.GRAY_STAINED_GLASS.getLootTableId(), MBItems.GRAY_GLASS_SHARD)
			.put(Blocks.BLACK_STAINED_GLASS.getLootTableId(), MBItems.BLACK_GLASS_SHARD)
			.put(Blocks.GREEN_STAINED_GLASS.getLootTableId(), MBItems.GREEN_GLASS_SHARD)
			.put(Blocks.LIME_STAINED_GLASS.getLootTableId(), MBItems.LIME_GLASS_SHARD)
			.put(Blocks.YELLOW_STAINED_GLASS.getLootTableId(), MBItems.YELLOW_GLASS_SHARD)
			.put(Blocks.ORANGE_STAINED_GLASS.getLootTableId(), MBItems.ORANGE_GLASS_SHARD)
			.put(Blocks.BROWN_STAINED_GLASS.getLootTableId(), MBItems.BROWN_GLASS_SHARD)
			.put(Blocks.RED_STAINED_GLASS.getLootTableId(), MBItems.RED_GLASS_SHARD)
			.put(Blocks.PINK_STAINED_GLASS.getLootTableId(), MBItems.PINK_GLASS_SHARD)
			.put(Blocks.MAGENTA_STAINED_GLASS.getLootTableId(), MBItems.MAGENTA_GLASS_SHARD)
			.put(Blocks.PURPLE_STAINED_GLASS.getLootTableId(), MBItems.PURPLE_GLASS_SHARD)
			.put(Blocks.LIGHT_BLUE_STAINED_GLASS.getLootTableId(), MBItems.LIGHT_BLUE_GLASS_SHARD)
			.put(Blocks.CYAN_STAINED_GLASS.getLootTableId(), MBItems.CYAN_GLASS_SHARD)
			.put(Blocks.BLUE_STAINED_GLASS.getLootTableId(), MBItems.BLUE_GLASS_SHARD)
			.build();
	public static final Map<Identifier, Item> GLASS_SHARD_PANE_LOOT = new ImmutableMap.Builder<Identifier, Item>()
			.put(Blocks.GLASS_PANE.getLootTableId(), MBItems.GLASS_SHARD)
			.put(Blocks.WHITE_STAINED_GLASS_PANE.getLootTableId(), MBItems.WHITE_GLASS_SHARD)
			.put(Blocks.LIGHT_GRAY_STAINED_GLASS_PANE.getLootTableId(), MBItems.LIGHT_GRAY_GLASS_SHARD)
			.put(Blocks.GRAY_STAINED_GLASS_PANE.getLootTableId(), MBItems.GRAY_GLASS_SHARD)
			.put(Blocks.BLACK_STAINED_GLASS_PANE.getLootTableId(), MBItems.BLACK_GLASS_SHARD)
			.put(Blocks.GREEN_STAINED_GLASS_PANE.getLootTableId(), MBItems.GREEN_GLASS_SHARD)
			.put(Blocks.LIME_STAINED_GLASS_PANE.getLootTableId(), MBItems.LIME_GLASS_SHARD)
			.put(Blocks.YELLOW_STAINED_GLASS_PANE.getLootTableId(), MBItems.YELLOW_GLASS_SHARD)
			.put(Blocks.ORANGE_STAINED_GLASS_PANE.getLootTableId(), MBItems.ORANGE_GLASS_SHARD)
			.put(Blocks.BROWN_STAINED_GLASS_PANE.getLootTableId(), MBItems.BROWN_GLASS_SHARD)
			.put(Blocks.RED_STAINED_GLASS_PANE.getLootTableId(), MBItems.RED_GLASS_SHARD)
			.put(Blocks.PINK_STAINED_GLASS_PANE.getLootTableId(), MBItems.PINK_GLASS_SHARD)
			.put(Blocks.MAGENTA_STAINED_GLASS_PANE.getLootTableId(), MBItems.MAGENTA_GLASS_SHARD)
			.put(Blocks.PURPLE_STAINED_GLASS_PANE.getLootTableId(), MBItems.PURPLE_GLASS_SHARD)
			.put(Blocks.LIGHT_BLUE_STAINED_GLASS_PANE.getLootTableId(), MBItems.LIGHT_BLUE_GLASS_SHARD)
			.put(Blocks.CYAN_STAINED_GLASS_PANE.getLootTableId(), MBItems.CYAN_GLASS_SHARD)
			.put(Blocks.BLUE_STAINED_GLASS_PANE.getLootTableId(), MBItems.BLUE_GLASS_SHARD)
			.build();

    public static void initEvents() {
        // events when u use a thing on a block go here!
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (player.isSpectator())
                return ActionResult.PASS; // spectators cant interact

            BlockPos targetPos = hitResult.getBlockPos();
            BlockState targetBlock = world.getBlockState(targetPos);
            ItemStack heldItem = player.getStackInHand(hand);

            if(targetBlock.getBlock() instanceof AbstractCauldronBlock) {
                if (player.shouldCancelInteraction()) {
                    return ActionResult.PASS;
                }
                else {
                    Moonbits.LOGGER.info("washing recipe triggered");
                    return WashingHandler.washItem(heldItem, targetBlock, player, hand, (ServerWorld) world);
                }
            }

            // planting grass
            if (targetBlock.isOf(Blocks.DIRT) && heldItem.getItem() == MBItems.GRASS_TUFT) {
                Moonbits.LOGGER.info("placed grass tuft");
                world.setBlockState(targetPos, Blocks.GRASS_BLOCK.getDefaultState());
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }

//            if (targetBlock.isOf(Blocks.DIRT) && heldItem.getItem() == MBBlocks.MYCELIUM_ROOTS.asItem()) {
//                world.setBlockState(targetPos, Blocks.MYCELIUM.getDefaultState());
//                if(!player.isCreative())
//                    heldItem.decrement(1);
//                world.playSound(null, targetPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
//                return ActionResult.SUCCESS;
//            }

            // applying slime to piston
            if (targetBlock.isOf(Blocks.PISTON) && heldItem.getItem() == Items.SLIME_BALL) {
                world.setBlockState(targetPos, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, targetBlock.get(PistonBlock.FACING)));
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                world.addBlockBreakParticles(targetPos, Blocks.SLIME_BLOCK.getDefaultState());
                return ActionResult.SUCCESS;
            }

            // scraping slime off piston
            if (targetBlock.isOf(Blocks.STICKY_PISTON) && heldItem.isIn(ConventionalItemTags.AXES)) {
                world.setBlockState(targetPos, Blocks.PISTON.getDefaultState().with(PistonBlock.FACING, targetBlock.get(PistonBlock.FACING)));
                if(!player.isCreative())
                    heldItem.damage(1, world.getRandom(), null);
                world.playSound(null, targetPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 0.8F, 1.0F);
                world.playSound(null, targetPos, SoundEvents.BLOCK_HONEY_BLOCK_BREAK, SoundCategory.BLOCKS, 0.3F, 0.3F);
                world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, targetPos, 0);
                return ActionResult.SUCCESS;
            }

            // adding torch to carved pumpkin
            if (targetBlock.isOf(Blocks.CARVED_PUMPKIN) && heldItem.getItem() == Items.TORCH) {
                world.setBlockState(targetPos, Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, targetBlock.get(CarvedPumpkinBlock.FACING)));
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }

            if (heldItem.isOf(Items.HONEYCOMB)) {
                if (WAXING.containsKey(targetBlock.getBlock())) {

                    if (player instanceof ServerPlayerEntity) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, targetPos, heldItem);
                    }
                    if (!player.isCreative()) heldItem.decrement(1);

                    BlockState waxed = WAXING.get(targetBlock.getBlock()).getStateWithProperties(targetBlock);
                    world.setBlockState(targetPos, waxed, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Context.create(player, waxed));
                    world.syncWorldEvent(player, WorldEvents.BLOCK_WAXED, targetPos, 0);
                    return ActionResult.success(world.isClient);
                }
            }
            if (heldItem.isIn(ConventionalItemTags.AXES)) {
                boolean success = false;
                if (WAX_OFF.containsKey(targetBlock.getBlock())) {
                    world.playSound(player, targetPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, targetPos, 0);

                    if (player instanceof ServerPlayerEntity) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, targetPos, heldItem);
                    }

                    BlockState unwaxed = WAX_OFF.get(targetBlock.getBlock()).getStateWithProperties(targetBlock);
                    world.setBlockState(targetPos, unwaxed, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Context.create(player, unwaxed));
                    success = true;
                }
                if (SCRAPING.containsKey(targetBlock.getBlock())) {
                    world.playSound(player, targetPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, targetPos, 0);

                    if (player instanceof ServerPlayerEntity) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, targetPos, heldItem);
                    }

                    BlockState unwaxed = SCRAPING.get(targetBlock.getBlock()).getStateWithProperties(targetBlock);
                    world.setBlockState(targetPos, unwaxed, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Context.create(player, unwaxed));
                    success = true;
                }
                if (success) {
                    if (!player.isCreative()) heldItem.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    return ActionResult.success(world.isClient);
                }
            }
            if (heldItem.isOf(Items.SHEARS)) {
                boolean success = false;
                if (targetBlock.isOf(Blocks.GRASS_BLOCK)) {
                    world.setBlockState(targetPos, Blocks.DIRT.getDefaultState());
                    Block.dropStack(world, new BlockPos(targetPos.getX()+0.5, targetPos.getY()+1.1, targetPos.getZ()+0.5), new ItemStack(MBItems.GRASS_TUFT));
                    success = true;
                }
//                if (targetBlock.isOf(Blocks.MYCELIUM)) {
//                    world.setBlockState(targetPos, Blocks.DIRT.getDefaultState());
//                    Block.dropStack(world, new BlockPos(targetPos.getX()+0.5, targetPos.getY()+1.1, targetPos.getZ()+0.5), new ItemStack(MBBlocks.MYCELIUM_ROOTS));
//                    success = true;
//                }
//                else if (targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) || targetBlock.isOf(MBBlocks.GLOW_BERRY_HEDGE)) {
//                    Moonbits.LOGGER.info("sheared berry hedge");
//                    world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) ? MBBlocks.PLUCKED_SWEET_BERRY_HEDGE.getDefaultState() : MBBlocks.PLUCKED_GLOW_BERRY_HEDGE.getDefaultState());
//                    Block.dropStack(world, dropFromFullBlock(targetPos, world),
//                            new ItemStack(targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) ? Items.SWEET_BERRIES : Items.GLOW_BERRIES, world.random.nextInt(3)+1));
//                    success = true;
//                }
                if (success) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
                    if (!player.isCreative()) heldItem.damage(1, world.getRandom(), null);
                    return ActionResult.SUCCESS;
                }
            }
            if (heldItem.isOf(Items.BONE_MEAL)) {
                boolean success = false;
                if (targetBlock.isOf(Blocks.DIRT)) {
                    if (!world.getBlockState(targetPos.up()).isSideSolidFullSquare(world, targetPos.up(), Direction.DOWN)) {
                        for (BlockPos blockPos : BlockPos.iterate(targetPos.add(-1, -1, -1), targetPos.add(1, 1, 1))) {
                            BlockState blockState = world.getBlockState(blockPos);
                            if (blockState.isOf(Blocks.GRASS_BLOCK)) {
                                success = true;
                                world.setBlockState(targetPos, Blocks.GRASS_BLOCK.getDefaultState());
                                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, targetPos.up(), 0);
                                break;
                            }
                        }
                    }
                }
//                else if (targetBlock.isOf(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE) || targetBlock.isOf(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE)) {
//                    Moonbits.LOGGER.info("bonemealed berry hedge");
//                    world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE) ? MBBlocks.SWEET_BERRY_HEDGE.getDefaultState() : MBBlocks.GLOW_BERRY_HEDGE.getDefaultState());
//                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, targetPos, 0);
//                    success = true;
//                }
                if (success) {
                    if (!player.isCreative()) heldItem.decrement(1);
                    return ActionResult.SUCCESS;
                }
            }

            // pass if nothing happened
            return ActionResult.PASS;
        });

        // events where u right click an entity go here!!
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (player.isSpectator())
                return ActionResult.PASS; // spectators cant interact

            if (player.isSneaking()) {
                if (entity instanceof PigEntity pig) {
                    if (pig.isSaddled() && !pig.hasPassengers()) {
                        ((PigSaddleAccess)pig).getSaddledComponent().setSaddled(false);
                        if(world.isClient)
                            player.playSound(SoundEvents.ENTITY_PIG_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
                        if(!player.isCreative())
                            player.giveItemStack(new ItemStack(Items.SADDLE));
                        return ActionResult.SUCCESS;
                    }
                }
                if (entity instanceof StriderEntity strider) {
                    if (strider.isSaddled() && !strider.hasPassengers()) {
                        ((StriderSaddleAccess)strider).getSaddledComponent().setSaddled(false);
                        if(world.isClient)
                            player.playSound(SoundEvents.ENTITY_STRIDER_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
                        if(!player.isCreative())
                            player.giveItemStack(new ItemStack(Items.SADDLE));
                        return ActionResult.SUCCESS;
                    }
                }
            }
            // pass if nothing happened
            return ActionResult.PASS;
        });

        EntitySleepEvents.ALLOW_SETTING_SPAWN.register((player, sleepingPos) -> {
            return !player.getWorld().getBlockState(sleepingPos).isOf(MBBlocks.BEDROLL);
        });


        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, table, setter) -> {
            if (EntityType.HUSK.getLootTableId().equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(MBItems.BURLAP)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1)))
                        .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)));

                table.pool(poolBuilder);
            }
			if (Blocks.BEETROOTS.getLootTableId().equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1))
						.conditionally(BlockStatePropertyLootCondition.builder(Blocks.BEETROOTS)
								.properties(StatePredicate.Builder.create().exactMatch(BeetrootsBlock.AGE, 3)))
						.with(ItemEntry.builder(Items.BEETROOT))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 3)));

				table.pool(poolBuilder);
			}
			if (GLASS_SHARD_LOOT.containsKey(id)) {
				table.pool((basicDrop(GLASS_SHARD_LOOT.get(id), 1, 3)).conditionally(MBLootTableProvider.WITHOUT_SILK_TOUCH));
			}
			if (GLASS_SHARD_PANE_LOOT.containsKey(id)) {
				table.pool((basicDrop(GLASS_SHARD_PANE_LOOT.get(id), 1)).conditionally(MBLootTableProvider.WITHOUT_SILK_TOUCH));
			}
        });
    }

	public static LootPool.Builder basicDrop(ItemConvertible item, int count) {
		return LootPool.builder()
				.rolls(ConstantLootNumberProvider.create(1))
				.with(ItemEntry.builder(item))
				.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(count)));
	}
	public static LootPool.Builder basicDrop(ItemConvertible item, int min, int max) {
		return LootPool.builder()
				.rolls(ConstantLootNumberProvider.create(1))
				.with(ItemEntry.builder(item))
				.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)));
	}
}
