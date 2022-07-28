package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.paperfish.moonbits.block.extended.MBRodBlock;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import java.util.Optional;

public class ToadstoolStemBlock extends MBRodBlock implements Fertilizable, Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final double SIZE = 6.0; // point the rod starts at
    public static final VoxelShape Y_SHAPE = Block.createCuboidShape(SIZE, 0.0, SIZE, 16.0 - SIZE, 16.0, 16.0 - SIZE);
    public static final VoxelShape Z_SHAPE = Block.createCuboidShape(SIZE, SIZE, 0.0, 16.0 - SIZE, 16.0 - SIZE, 16.0);
    public static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, SIZE, SIZE, 16.0, 16.0 - SIZE, 16.0 - SIZE);

    public ToadstoolStemBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.UP).with(WATERLOGGED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(WATERLOGGED, FACING);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING).getAxis()) {
            default -> {
                return X_SHAPE;
            }
            case Z -> {
                return Z_SHAPE;
            }
            case Y -> {
                return Y_SHAPE;
            }
        }
//        return Y_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
//        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
//        if (blockState.isOf(this) && blockState.get(FACING) == direction) {
//            return this.getDefaultState().with(FACING, direction.getOpposite());
//        }
        return this.getDefaultState().with(FACING, direction);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        if (direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
//            return Blocks.AIR.getDefaultState();
//        }
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
//        if (direction == Direction.UP && neighborState.isOf(this)) {
//            return state.with(CAP, false);
//        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		BlockState above = world.getBlockState(pos.up());
		return (above.isAir() || above.isOf(MBBlocks.TOADSTOOL)) && state.get(MBRodBlock.FACING) == Direction.UP;
	}

	@Override
	public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
		//if (state.get(MBRodBlock.FACING)!= Direction.UP) return; // if its not upright then cancel

		BlockState above = world.getBlockState(pos.up());
		BlockState below = world.getBlockState(pos.down());
		if (above.isAir()) {
			world.setBlockState(pos, MBBlocks.TOADSTOOL.getDefaultState());
		}
		else if (below.isOf(this)) {
			Optional<BlockPos> optional = BlockLocating.findColumnEnd(world, pos, state.getBlock(), Direction.UP, MBBlocks.TOADSTOOL);
			if (optional.isPresent()) {
				BlockPos blockPos = optional.get();
				BlockPos blockPos2 = blockPos.up();
				world.setBlockState(blockPos, state);
				world.setBlockState(blockPos2, MBBlocks.TOADSTOOL.getDefaultState().with(WATERLOGGED, state.get(WATERLOGGED)));
			}
		}
		else if (above.isOf(MBBlocks.TOADSTOOL) && (below.isIn(BlockTags.DIRT) || below.isIn(MBBlockTags.PLANTER_BOXES))) {
			world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());
			this.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
		}
	}

	public boolean generate(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomGenerator random) {
		Holder<? extends ConfiguredFeature<?, ?>> holder = MBTreeFeatures.GIANT_TOADSTOOL;
		if (holder == null) {
			return false;
		} else {
			ConfiguredFeature<?, ?> configuredFeature = holder.value();
			BlockState blockState = world.getFluidState(pos).getBlockState();
			world.setBlockState(pos, blockState, 4);
			if (configuredFeature.generate(world, chunkGenerator, random, pos)) {
				if (world.getBlockState(pos) == blockState) {
					world.updateListeners(pos, state, blockState, 2);
				}

				return true;
			} else {
				world.setBlockState(pos, state, 4);
				return false;
			}
		}
	}
}
