package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Holder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class PupBlock extends PlantBlock {
	public static final IntProperty AGE = Properties.AGE_3;
	public static final BooleanProperty REPLANTED = Properties.PERSISTENT;
	private final Supplier<Holder<? extends ConfiguredFeature<?, ?>>> feature;

	public PupBlock(Supplier<Holder<? extends ConfiguredFeature<?, ?>>> supplier, Settings settings) {
		super(settings);
		feature = supplier;
		setDefaultState(getDefaultState().with(AGE, 3).with(REPLANTED, true));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(AGE, REPLANTED);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		int age = state.get(AGE);
		if (random.nextInt(4) == 0 && age < 3) {
			world.setBlockState(pos, state.with(AGE, age+1));
		}
		super.randomTick(state, world, pos, random);
	}

	public boolean tryGrowing(ServerWorld world, BlockPos pos, BlockState state, RandomGenerator random) {
		world.removeBlock(pos, false);
		if (this.feature.get().value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
			return true;
		} else {
			world.setBlockState(pos, state, 3);
			return false;
		}
	}

	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	public boolean canGrow(World world, RandomGenerator random, BlockPos pos, BlockState state) {
		return (double)random.nextFloat() < 0.4D && state.get(REPLANTED);
	}

	public void grow(ServerWorld world, RandomGenerator random, BlockPos pos, BlockState state) {
		this.tryGrowing(world, pos, state, random);
	}
}
