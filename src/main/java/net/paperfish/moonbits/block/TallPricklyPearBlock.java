package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
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
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBItems;

public class TallPricklyPearBlock extends TallPlantBlock implements Fertilizable {
    public static final BooleanProperty CUT = BooleanProperty.of("cut");
    public static final IntProperty AGE = Properties.AGE_2;

    public TallPricklyPearBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(CUT, false).with(AGE, 0).with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CUT, AGE);
        super.appendProperties(builder);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(MBBlockTags.SANDY_SOILS) || floor.isIn(MBBlockTags.DESERT_PLANTERS) || super.canPlantOnTop(floor, world, pos);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = world.getBlockState(pos.down());
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
        }
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
        if (random.nextInt(8) == 0 && state.get(AGE) < 2) {
            world.setBlockState(pos, state.with(AGE, state.get(AGE)+1));

            BlockPos targetPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
            world.setBlockState(targetPos, world.getBlockState(targetPos).with(AGE, state.get(AGE)+1));
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(CUT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        BlockPos targetPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos.down();

        if (heldItem.isOf(Items.SHEARS) && !state.get(CUT)) {
            if (!world.isClient()) {
                heldItem.damage(1, world.random, (ServerPlayerEntity) player);
            }
            world.setBlockState(pos, state.with(CUT, true));

            world.setBlockState(targetPos, world.getBlockState(targetPos).with(CUT, true));
        }
        else if (state.get(AGE) == 2) {
            world.setBlockState(pos, state.with(AGE, 0));
            world.setBlockState(targetPos, world.getBlockState(targetPos).with(AGE, 0));
            if (!world.isClient()) {
                BlockPos dropPos = state.get(HALF) == DoubleBlockHalf.LOWER ? pos.up() : pos;
                dropStack(world, dropPos, new ItemStack(MBItems.PRICKLY_PEAR, world.random.nextInt(1)+1));
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
