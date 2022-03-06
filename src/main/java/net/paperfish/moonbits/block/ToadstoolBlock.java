package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.entity.SeatBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings({"deprecation"})
public class ToadstoolBlock extends Block implements Fertilizable, Waterloggable {
    public static final BooleanProperty CAP;
    private static final BooleanProperty WATERLOGGED;
    public static final VoxelShape CAP_SHAPE;
    public static final VoxelShape STEM_SHAPE;
    public static SeatBlockEntity entity;

    static {
        CAP = BooleanProperty.of("cap");
        WATERLOGGED = Properties.WATERLOGGED;
        CAP_SHAPE = Block.createCuboidShape(0, 12,0,16,16, 16);
        STEM_SHAPE = Block.createCuboidShape(6, 0,6,10,16, 10);
    }

    public ToadstoolBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(CAP, true).with(WATERLOGGED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(CAP, WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape constructShape = STEM_SHAPE;
        if (state.get(CAP)){
            constructShape = VoxelShapes.union(constructShape, CAP_SHAPE);
        }
        return constructShape;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isOf(this) || blockState.isIn(BlockTags.DIRT);
    }

    @Nullable
        @Override
        public BlockState getPlacementState(ItemPlacementContext ctx) {
            if (ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isOf(this)){
                return getDefaultState().with(CAP, false);
            }
            return super.getPlacementState(ctx);
        }

        @Override
        public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
            if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
                return Blocks.AIR.getDefaultState();
            }
            if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction == Direction.UP && neighborState.isOf(this)) {
            return state.with(CAP, false);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(CAP)) {
            if (player.isSneaking()) {
                return ActionResult.PASS; // bc sneaking stops any block-specific actions
            }
            List<SeatBlockEntity> seats = world.getEntitiesByClass(SeatBlockEntity.class, new Box(pos), Objects::nonNull);
            if (!seats.isEmpty()) {
                Moonbits.LOGGER.info("seat exists");
                SeatBlockEntity seat = seats.get(0);
                if (seat.hasPassengers()) {
                    return ActionResult.PASS; // cant sit down if someone's already in the seat
                }
                if (!world.isClient()) {
                    player.startRiding(seat);
                    Moonbits.LOGGER.info("got in existing seat");
                }
                return ActionResult.SUCCESS;
            }
            if (world.isClient()) {
                return ActionResult.SUCCESS;
            }
            createEntity(world, pos, state, player);
        }
        return ActionResult.SUCCESS;
    }
    public static void createEntity(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            SeatBlockEntity entity = new SeatBlockEntity(world, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f);
            Moonbits.LOGGER.info("created the seat entity on a toadstool");
            world.spawnEntity(entity);
            player.startRiding(entity, true);
        }
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
        if (world.getBlockState(blockPos).isAir()) {
            world.setBlockState(blockPos, this.getDefaultState());
        }
    }
}
