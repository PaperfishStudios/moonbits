package net.paperfish.moonbits;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.LootFunctionTypes;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProviderTypes;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.paperfish.moonbits.mixin.PigSaddleAccess;
import net.paperfish.moonbits.mixin.StriderSaddleAccess;
import net.paperfish.moonbits.recipe.WashingHandler;

import javax.swing.*;
import java.util.Random;

import static java.lang.Character.toUpperCase;

public class MBEvents {

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

            // shearing grass
            if (targetBlock.isOf(Blocks.GRASS_BLOCK) && heldItem.getItem() == Items.SHEARS) {
                Moonbits.LOGGER.info("sheared grass block");
                world.setBlockState(targetPos, Blocks.DIRT.getDefaultState());
                if(!player.isCreative())
                    heldItem.damage(1, new Random(), null);
                // spawn grass tuft item
                //Block.dropStack(world, targetPos, new ItemStack(MBItems.GRASS_TUFT));
                world.spawnEntity(new ItemEntity(world, targetPos.getX() + .5, targetPos.getY() + 1, targetPos.getZ() + .5, new ItemStack(MBItems.GRASS_TUFT)));
                // play shearing sound
                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
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

            // applying slime to piston
            if (targetBlock.isOf(Blocks.PISTON) && heldItem.getItem() == Items.SLIME_BALL) {
                world.setBlockState(targetPos, Blocks.STICKY_PISTON.getDefaultState().with(PistonBlock.FACING, targetBlock.get(PistonBlock.FACING))); 
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                world.syncWorldEvent(1505, targetPos, 0);
                return ActionResult.SUCCESS;
            }

            // scraping slime off piston
            if (targetBlock.isOf(Blocks.STICKY_PISTON) && heldItem.isIn(MBItemTags.AXES)) {
                world.setBlockState(targetPos, Blocks.PISTON.getDefaultState().with(PistonBlock.FACING, targetBlock.get(PistonBlock.FACING))); 
                if(!player.isCreative())
                    heldItem.damage(1, new Random(), null);
                world.playSound(null, targetPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 0.8F, 1.0F);
                world.playSound(null, targetPos, SoundEvents.BLOCK_HONEY_BLOCK_BREAK, SoundCategory.BLOCKS, 0.3F, 0.3F);
                world.syncWorldEvent(player, 3005, targetPos, 0);
                return ActionResult.SUCCESS;
            }

            // adding torch to carved pumpkin
            if (targetBlock.isOf(Blocks.CARVED_PUMPKIN) && heldItem.getItem() == Items.TORCH) {
                world.setBlockState(targetPos, Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, targetBlock.get(CarvedPumpkinBlock.FACING))); 
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, Blocks.NETHER_BRICKS.getSoundGroup(Blocks.NETHER_BRICKS.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }

            // shearing berry hedges
            if (targetBlock.isOf(MBBlocks.SWEET_BERRY_HEDGE) && heldItem.getItem() == Items.SHEARS) {
                Moonbits.LOGGER.info("sheared berry hedge");
                world.setBlockState(targetPos, MBBlocks.PLUCKED_SWEET_BERRY_HEDGE.getDefaultState());
                if(!player.isCreative())
                    heldItem.damage(1, new Random(), null);
                // spawn items
                if (!world.isClient()) {
                    BlockState a = Blocks.SWEET_BERRY_BUSH.getDefaultState();
                    Block.getDroppedStacks(a.with(SweetBerryBushBlock.AGE, 3), (ServerWorld) world, dropFromFullBlock(targetPos, world), null).forEach((stack) -> {
                        Block.dropStack(world, dropFromFullBlock(targetPos, world), stack);
                    });
                }

                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }
            if (targetBlock.isOf(MBBlocks.GLOW_BERRY_HEDGE) && heldItem.getItem() == Items.SHEARS) {
                Moonbits.LOGGER.info("sheared berry hedge");
                world.setBlockState(targetPos, MBBlocks.PLUCKED_GLOW_BERRY_HEDGE.getDefaultState());
                if(!player.isCreative())
                    heldItem.damage(1, new Random(), null);
                // spawn items
                if (!world.isClient()) {
                    BlockState a = Blocks.CAVE_VINES_PLANT.getDefaultState();
                    Block.getDroppedStacks(a.with(CaveVinesBodyBlock.BERRIES, true), (ServerWorld) world, dropFromFullBlock(targetPos, world), null).forEach((stack) -> {
                        Block.dropStack(world, dropFromFullBlock(targetPos, world), stack);
                    });
                }

                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }
            // regrowing berry hedges
            if (targetBlock.isOf(MBBlocks.PLUCKED_SWEET_BERRY_HEDGE) && heldItem.getItem() == Items.BONE_MEAL) {
                Moonbits.LOGGER.info("bonemealed berry hedge");
                world.setBlockState(targetPos, MBBlocks.SWEET_BERRY_HEDGE.getDefaultState());
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.syncWorldEvent(1505, targetPos, 0);

                world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }
            if (targetBlock.isOf(MBBlocks.PLUCKED_GLOW_BERRY_HEDGE) && heldItem.getItem() == Items.BONE_MEAL) {
                Moonbits.LOGGER.info("bonemealed berry hedge");
                world.setBlockState(targetPos, MBBlocks.GLOW_BERRY_HEDGE.getDefaultState());
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.syncWorldEvent(1505, targetPos, 0);

                world.playSound(null, player.getBlockPos(), SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
            }

            // adding snow to plant
            if (targetBlock.isIn(MBBlockTags.SNOWABLE_PLANTS) && heldItem.getItem() == Items.SNOW && !targetBlock.get(Properties.SNOWY)) {
                world.setBlockState(targetPos, targetBlock.with(Properties.SNOWY, true));
                if(!player.isCreative())
                    heldItem.decrement(1);
                world.playSound(null, targetPos, Blocks.SNOW.getSoundGroup(Blocks.SNOW.getDefaultState()).getPlaceSound(), SoundCategory.BLOCKS, 0.5F, 1.0F);
                return ActionResult.SUCCESS;
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


        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (EntityType.HUSK.getLootTableId().equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(MBItems.DUSTY_CLOTH)).withFunction(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1)).build())
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
