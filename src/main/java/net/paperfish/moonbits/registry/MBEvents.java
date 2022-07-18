package net.paperfish.moonbits.registry;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.mixin.PigSaddleAccess;
import net.paperfish.moonbits.mixin.StriderSaddleAccess;
import net.paperfish.moonbits.recipe.WashingHandler;

import net.minecraft.util.math.random.Random;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.HashMap;
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
            .build();

    // maps for blocks that grow into other blocks (eg hardy berry leaves and barrel cacti)
    public static BiMap<Block, Block> GROWING = new ImmutableBiMap.Builder<Block, Block>()
            .put(MBBlocks.HARDY_LEAVES, MBBlocks.FLOWERING_HARDY_LEAVES)
            .put(MBBlocks.FLOWERING_HARDY_LEAVES, MBBlocks.FRUITING_HARDY_LEAVES)
            .put(MBBlocks.TINY_BARREL_CACTUS, MBBlocks.SMALL_BARREL_CACTUS)
            .put(MBBlocks.SMALL_BARREL_CACTUS, MBBlocks.BARREL_CACTUS)
            .put(MBBlocks.BARREL_CACTUS, MBBlocks.LARGE_BARREL_CACTUS)
            .build();
    public static BiMap<Block, Block> PLUCK = GROWING.inverse();

    public static void initEvents() {
        // events when u use a thing on a block go here!
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (player.isSpectator())
                return ActionResult.PASS; // spectators cant interact
            
            BlockPos targetPos = hitResult.getBlockPos();
            BlockState targetBlock = world.getBlockState(targetPos);
            ItemStack heldItem = player.getStackInHand(hand);

            if(targetBlock.getBlock() instanceof AbstractCauldronBlock && !world.isClient) {
                if (player.shouldCancelInteraction()) {
                    return ActionResult.PASS;
                }
                else {
                    Moonbits.LOGGER.info("washing recipe triggered");
                    return WashingHandler.washItem(heldItem, targetBlock, (ServerPlayerEntity) player, hand, (ServerWorld) world);
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

            if (targetBlock.isOf(Blocks.DIRT) && heldItem.getItem() == MBBlocks.MYCELIUM_ROOTS.asItem()) {
                world.setBlockState(targetPos, Blocks.MYCELIUM.getDefaultState());
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }

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
            if (targetBlock.isOf(Blocks.STICKY_PISTON) && heldItem.isIn(MBItemTags.AXES)) {
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
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Emitter.of(player, waxed));
                    world.syncWorldEvent(player, WorldEvents.BLOCK_WAXED, targetPos, 0);
                    return ActionResult.success(world.isClient);
                }
            }
            if (heldItem.isIn(MBItemTags.AXES)) {
                boolean success = false;
                if (WAX_OFF.containsKey(targetBlock.getBlock())) {
                    world.playSound(player, targetPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, targetPos, 0);

                    if (player instanceof ServerPlayerEntity) {
                        Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, targetPos, heldItem);
                    }

                    BlockState unwaxed = WAX_OFF.get(targetBlock.getBlock()).getStateWithProperties(targetBlock);
                    world.setBlockState(targetPos, unwaxed, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Emitter.of(player, unwaxed));
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
                    world.emitGameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Emitter.of(player, unwaxed));
                    success = true;
                }
                if (success) {
                    if (!player.isCreative()) heldItem.damage(1, player, p -> p.sendToolBreakStatus(hand));
                    return ActionResult.success(world.isClient);
                }
            }
            if (heldItem.isOf(Items.SHEARS)) {
                boolean success = false;
                if (targetBlock.isOf(Blocks.GRASS_BLOCK) || targetBlock.isOf(MBBlocks.TOUGH_GRASS)) {
                    world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.TOUGH_GRASS) ? MBBlocks.TOUGH_DIRT.getDefaultState() : Blocks.DIRT.getDefaultState());
                    Block.dropStack(world, dropFromFullBlock(targetPos, world), new ItemStack(MBItems.GRASS_TUFT));
                    success = true;
                }
                if (targetBlock.isOf(Blocks.MYCELIUM)) {
                    world.setBlockState(targetPos, Blocks.DIRT.getDefaultState());
                    Block.dropStack(world, dropFromFullBlock(targetPos, world), new ItemStack(MBBlocks.MYCELIUM_ROOTS));
                    success = true;
                }
                else if (targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) || targetBlock.isOf(MBBlocks.GLOW_BERRY_HEDGE)) {
                    Moonbits.LOGGER.info("sheared berry hedge");
                    world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) ? MBBlocks.PLUCKED_SWEET_BERRY_HEDGE.getDefaultState() : MBBlocks.PLUCKED_GLOW_BERRY_HEDGE.getDefaultState());
                    Block.dropStack(world, dropFromFullBlock(targetPos, world),
                            new ItemStack(targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) ? Items.SWEET_BERRIES : Items.GLOW_BERRIES, world.random.nextInt(3)+1));
                    success = true;
                }
                if (success) {
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
                    if (!player.isCreative()) heldItem.damage(1, world.getRandom(), null);
                    return ActionResult.SUCCESS;
                }
            }
            if (heldItem.isOf(Items.BONE_MEAL)) {
                boolean success = false;
                if (targetBlock.isOf(Blocks.DIRT) || targetBlock.isOf(MBBlocks.TOUGH_DIRT)) {
                    if (!world.getBlockState(targetPos.up()).isSideSolidFullSquare(world, targetPos.up(), Direction.DOWN)) {
                        for (BlockPos blockPos : BlockPos.iterate(targetPos.add(-1, -1, -1), targetPos.add(1, 1, 1))) {
                            BlockState blockState = world.getBlockState(blockPos);
                            if (blockState.isOf(Blocks.GRASS_BLOCK) || blockState.isOf(MBBlocks.TOUGH_GRASS)) {
                                success = true;
                                world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.TOUGH_DIRT) ? MBBlocks.TOUGH_GRASS.getDefaultState() : Blocks.GRASS_BLOCK.getDefaultState());
                                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, targetPos.up(), 0);
                                break;
                            }
                        }
                    }
                }
                else if (targetBlock.isOf(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE) || targetBlock.isOf(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE)) {
                    Moonbits.LOGGER.info("bonemealed berry hedge");
                    world.setBlockState(targetPos, targetBlock.isOf(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE) ? MBBlocks.SWEET_BERRY_HEDGE.getDefaultState() : MBBlocks.GLOW_BERRY_HEDGE.getDefaultState());
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, targetPos, 0);
                    success = true;
                }
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
                        .with(ItemEntry.builder(MBItems.BURLAP)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1)).build())
                        .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0f, 1.0f)));

                table.pool(poolBuilder);
            }
        });
    }

    public static BlockPos dropFromFullBlock(BlockPos block, World world) {
        if (world.getBlockState(block.up()).isSideSolidFullSquare(world, block.up(), Direction.DOWN)) {
            return block.up();
        }
        else if (world.getBlockState(block.north()).isSideSolidFullSquare(world, block.north(), Direction.SOUTH)) {
            return block.north();
        }
        else if (world.getBlockState(block.south()).isSideSolidFullSquare(world, block.south(), Direction.NORTH)) {
            return block.south();
        }
        else if (world.getBlockState(block.east()).isSideSolidFullSquare(world, block.east(), Direction.WEST)) {
            return block.east();
        }
        else if (world.getBlockState(block.west()).isSideSolidFullSquare(world, block.west(), Direction.EAST)) {
            return block.west();
        }
        else if (world.getBlockState(block.down()).isSideSolidFullSquare(world, block.down(), Direction.UP)) {
            return block.down();
        }
        return block.up();
    }
}
