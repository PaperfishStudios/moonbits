package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.entity.SeatBlockEntity;
import net.paperfish.moonbits.registry.MBBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SeatBlock extends Block {
//    public static final EnumProperty<SlabType> TYPE;
    public static SeatBlockEntity entity;
    private static final TargetPredicate seatPredicate = TargetPredicate.createNonAttackable().setPredicate(SeatBlockEntity -> true).setBaseMaxDistance(5);
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0);
	protected static final VoxelShape TOADSTOOL = VoxelShapes.union(
			Block.createCuboidShape(0.0, 4.0, 0.0, 16.0, 8.0, 16.0),
			Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0)
	);

	public SeatBlock(Settings settings) {
        super(settings);
    }

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//		if (state.isOf(MBBlocks.TOADSTOOL_SEAT)) return TOADSTOOL;
		return SHAPE;
	}

	@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (isNull(entity)) {
//            createEntity(world, pos, state);
//        }
//        if (player.shouldCancelInteraction()) {
//            return ActionResult.PASS;
//        } else if (entity.hasPassengers()) {
//            return ActionResult.PASS;
//        } else if (!world.isClient) {
//            return player.startRiding(entity) ? ActionResult.CONSUME : ActionResult.PASS;
//        } else {
//            return ActionResult.SUCCESS;
//        }
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
        }
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        createEntity(world, pos, state, player);
        return ActionResult.SUCCESS;
    }

    // no replacey :3
//    public boolean canReplace(BlockState state, ItemPlacementContext context) {
//        return false;
//    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
//        createEntity(world, pos, state);
    }

    public SeatBlockEntity getEntity() {
            return entity;
    }

    public void setEntity(SeatBlockEntity ba) {
            entity = ba;
    }

    public static void createEntity(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            SeatBlockEntity entity;
//            if (state.get(TYPE) == SlabType.BOTTOM) {
                entity = new SeatBlockEntity(world, pos.getX() + .5f, pos.getY(), pos.getZ() + .5f);
                //entity.setPosition(pos.getX() + .5f, pos.getY() + 1f, pos.getZ() + .5f);
                Moonbits.LOGGER.info("created seat entity at " + pos);
//            } else {
//                entity = new SeatBlockEntity(world, pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f);
//                //entity.setPosition(pos.getX() + .5f, pos.getY() + .5f, pos.getZ() + .5f);
//                Moonbits.LOGGER.info("created the seat entity on the top half");
//            }
            world.spawnEntity(entity);
            player.startRiding(entity, true);
        }
    }

//    @Override
//    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
//        //super.onBroken(world, pos, state);
////        if (!isNull(entity)) {
////            entity.discard();
////        }
//    }

//    static {
//        TYPE = Properties.SLAB_TYPE;
//    }

}
