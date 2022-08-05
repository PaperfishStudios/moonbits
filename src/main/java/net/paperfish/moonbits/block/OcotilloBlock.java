package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItems;
import org.jetbrains.annotations.Nullable;

public class OcotilloBlock extends Block {
	public static final IntProperty AGE = Properties.AGE_15;
	public static final VoxelShape SHAPE = Block.createCuboidShape(6, 0, 6, 10, 16, 10);
	public final Stage STAGE;

	public OcotilloBlock(Stage stage, Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(AGE, 0));
		this.STAGE = stage;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return state.get(AGE) < 15 || ((OcotilloBlock)state.getBlock()).STAGE == Stage.BARE;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (state.get(AGE) < 15 && random.nextInt(5) == 0) {
			BlockPos blockPos = pos.up();
			if (world.getBlockState(blockPos).isAir()) {
				world.setBlockState(blockPos, this.age(state, random));
			}
//			else {
//				world.setBlockState(pos, state.with(AGE, 15));
//			}
		}
		if (random.nextInt(8) == 0 && ((OcotilloBlock)state.getBlock()).STAGE == Stage.BARE) {
			world.setBlockState(pos, MBBlocks.FLOWERING_OCOTILLO.getStateWithProperties(state));
		}
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (((OcotilloBlock)state.getBlock()).STAGE == Stage.FLOWERING) {
			ItemStack stack = player.getStackInHand(hand);
			if (stack.isOf(Items.SHEARS)) {
				if (!world.isClient) {
					Direction direction = hit.getSide();
					Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
					world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					world.setBlockState(pos, MBBlocks.OCOTILLO.getStateWithProperties(state), 11);
					ItemEntity itemEntity = new ItemEntity(
							world,
							(double)pos.getX() + 0.5 + (double)direction2.getOffsetX() * 0.25,
							(double)pos.getY() + 0.1,
							(double)pos.getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.25,
							new ItemStack(Items.RED_DYE, 4)
					);
					itemEntity.setVelocity(
							0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02,
							0.05,
							0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02
					);
					world.spawnEntity(itemEntity);
					stack.damage(1, player, playerx -> playerx.sendToolBreakStatus(hand));
					world.emitGameEvent(player, GameEvent.SHEAR, pos);
					player.incrementStat(Stats.USED.getOrCreateStat(Items.SHEARS));
				}

				return ActionResult.success(world.isClient);
			}
		}
		return super.onUse(state, world, pos, player, hand, hit);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState floor = world.getBlockState(pos.down());
		return floor.isIn(MBBlockTags.SANDY_SOILS) || floor.isIn(MBBlockTags.DESERT_PLANTERS) || floor.isIn(MBBlockTags.OCOTILLO);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.DOWN && !canPlaceAt(state, world, pos)) {
			world.scheduleBlockTick(pos, this, 1);
		}
		if (direction == Direction.UP && !neighborState.isIn(MBBlockTags.OCOTILLO)) {
			return this.getRandomGrowthState(world);
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (!state.canPlaceAt(world, pos)) {
			world.breakBlock(pos, true);
		}
	}

	public BlockState getRandomGrowthState(WorldAccess world) {
		return this.getDefaultState().with(AGE, world.getRandom().nextInt(15));
	}

	protected BlockState age(BlockState state, RandomGenerator random) {
		int newAge = state.get(AGE) + (random.nextInt(3)+1);
		return state.with(AGE, Math.min(newAge, 15));
	}

	public enum Stage {
		BARE, FLOWERING
	}
}
