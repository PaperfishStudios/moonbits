package net.moonteam.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.moonteam.moonbits.MBBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class CavebloomFlowerBlock extends AbstractLichenBlock implements Fertilizable, Waterloggable  {
    private static final BooleanProperty WATERLOGGED;
    //private static final BooleanProperty BLOOMING;

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(WATERLOGGED);
    }

    public CavebloomFlowerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState state = world.getBlockState(blockPos);
        if (state.isOf(MBBlocks.CAVEBLOOM_VINE)) {
            //return swapBlock(state);
            return Arrays.stream(ctx.getPlacementDirections()).map(direction -> this.withDirection(swapBlock(state), world, blockPos, direction)).filter(Objects::nonNull).findFirst().orElse(null);
        }
        return super.getPlacementState(ctx);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int randomNum = random.nextInt(25);
        if (randomNum == 0 && world.getLightLevel(pos) < 12) {
            int i = 5;
            int radius = 4;
            for (BlockPos blockPos : BlockPos.iterate(pos.add(-radius, -1, -radius), pos.add(radius, 1, radius))) {
                if (!world.getBlockState(blockPos).isOf(this) || --i > 0) continue;
                return;
            }
            grow(world, random, pos, state);
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getMainHandStack().getItem() == Items.SHEARS && !player.shouldCancelInteraction()) {
            world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1f, 1f);
            dropFlowers(state, world, pos);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            world.setBlockState(pos, swapBlock(state), 0);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public BlockState swapBlock(BlockState state) {
        return MBBlocks.CAVEBLOOM_VINE.getDefaultState()
                .with(Properties.UP, state.get(Properties.UP))
                .with(Properties.DOWN, state.get(Properties.DOWN))
                .with(Properties.NORTH, state.get(Properties.NORTH))
                .with(Properties.SOUTH, state.get(Properties.SOUTH))
                .with(Properties.EAST, state.get(Properties.EAST))
                .with(Properties.WEST, state.get(Properties.WEST));
    }

    public void dropFlowers(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, swapBlock(state), 0);
        dropStack(world, pos, new ItemStack(MBBlocks.CAVEBLOOMS));
        world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 0.5F, 0.8F + world.random.nextFloat() * 0.4F);
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.trySpreadRandomly(swapBlock(state), world, pos, random);
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.getStack().isOf(MBBlocks.CAVEBLOOMS) || super.canReplace(state, context);
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return Stream.of(DIRECTIONS).anyMatch((direction) -> this.canSpread(state, world, pos, direction.getOpposite()));
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    static {
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
