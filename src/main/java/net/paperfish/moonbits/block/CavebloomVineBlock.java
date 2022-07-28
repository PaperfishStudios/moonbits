package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlocks;

import java.util.List;
import java.util.stream.Stream;

public class CavebloomVineBlock extends AbstractLichenBlock implements Fertilizable {
	private final LichenSpreadBehavior grower = new LichenSpreadBehavior(this);

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
	}

	public CavebloomVineBlock(Settings settings) {
		super(settings);
	}

	public BlockState swapBlock(BlockState state) {
		return MBBlocks.CAVEBLOOM_FLOWERS.getStateWithProperties(state);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		int randomNum = random.nextInt(25);
		if (randomNum == 0) {
			if (BlockPos.streamOutwards(pos, 2, 2, 2)
					.map(world::getBlockState)
					.map(BlockState::getBlock)
					.filter(List.of(MBBlocks.CAVEBLOOM_VINE, MBBlocks.CAVEBLOOM_FLOWERS)::contains)
					.toList().size() <= 4) { // if there are no more than 8 caveblooms/vines within a 2-block radius
				grow(world, random, pos, state);
			}
		}
		if (randomNum == 1 && world.getLightLevel(pos) < 12) {
			int i = 0;
			for (Direction direction : Direction.values()) { // iterate through each side of the block
				if (world.getBlockState(pos.offset(direction)).isOf(this)) {
					i++;
				}
			}
			if (i >= 4) { // if at least 4 sides are also cavebloom vines, grow flower :>
				world.setBlockState(pos, swapBlock(state), 0);
			}
		}
		super.randomTick(state, world, pos, random);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		return ActionResult.PASS;
	}

	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		// probly need a thing to break when water flows right
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		return !context.getStack().isOf(MBBlocks.CAVEBLOOMS) || super.canReplace(state, context);
	}

	@Override
	public LichenSpreadBehavior getLichenSpreadBehavior() {
		return grower;
	}

	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return Stream.of(DIRECTIONS).anyMatch((direction) -> this.grower.canSpreadInAnyDirection(state, world, pos, direction.getOpposite()));
	}

	@Override
	public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
		this.grower.spreadFromRandomFacingToRandomDirection(state, world, pos, random);
	}

	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		if (type == NavigationType.AIR && !this.collidable) {
			return true;
		}
		return super.canPathfindThrough(state, world, pos, type);
	}
}
