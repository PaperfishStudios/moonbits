package net.paperfish.moonbits;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;

public class DirtMoundBlock extends Block {
	public static final VoxelShape SHAPE = VoxelShapes.union(
			Block.createCuboidShape(2, 0, 2, 14, 4, 14),
			Block.createCuboidShape(4, 4, 4, 12, 7, 12)
	);

	public DirtMoundBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient() && world.getRandom().nextInt(16) == 0) {
			RabbitEntity bnuuy = EntityType.RABBIT.create(world);
			int i = chooseType(world, pos);
			if (bnuuy != null){
				bnuuy.setRabbitType(i);
				bnuuy.refreshPositionAndAngles(pos, 0, 0);
				world.spawnEntity(bnuuy);
			}
		}
		super.onBreak(world, pos, state, player);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return world.getBlockState(pos.down()).isIn(BlockTags.DIRT);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.DOWN && !neighborState.isIn(BlockTags.DIRT)) {
			return Blocks.AIR.getDefaultState();
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	private int chooseType(WorldAccess world, BlockPos pos) {
		Holder<Biome> holder = world.getBiome(pos);
		int i = world.getRandom().nextInt(100);
		if (holder.value().getPrecipitation() == Biome.Precipitation.SNOW) {
			return i < 80 ? 1 : 3;
		} else if (holder.hasTag(BiomeTags.ONLY_ALLOWS_SNOW_AND_GOLD_RABBITS)) {
			return 4;
		} else {
			return i < 50 ? 0 : (i < 90 ? 5 : 2);
		}
	}
}
