package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;

import java.util.Random;

public class PricklyPearBlock extends PlantBlock implements Fertilizable {
    public static final BooleanProperty CUT = BooleanProperty.of("cut");
    public static final IntProperty AGE = Properties.AGE_1;

    public PricklyPearBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(CUT, false).with(AGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CUT, AGE);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(MBBlockTags.SANDY_SOILS) || super.canPlantOnTop(floor, world, pos);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(CUT)) {
            grow(world, random, pos, state);
        }
        super.randomTick(state, world, pos, random);
    }


    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (!state.get(CUT) && random.nextInt(8) == 0) {
            if (state.get(AGE) == 0) {
                world.setBlockState(pos, state.with(AGE, 1));
            } else {
                TallPricklyPearBlock tallPlantBlock = (TallPricklyPearBlock) MBBlocks.TALL_PRICKLY_PEAR_CACTUS;
                if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
                    TallPricklyPearBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
                }
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(CUT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (heldItem.isOf(Items.SHEARS) && !state.get(CUT)) {
            if (!world.isClient()) {
                heldItem.damage(1, world.random, (ServerPlayerEntity) player);
            }
            world.setBlockState(pos, state.with(CUT, true));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
