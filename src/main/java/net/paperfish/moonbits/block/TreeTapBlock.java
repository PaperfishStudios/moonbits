package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBEvents;
import net.paperfish.moonbits.registry.MBParticles;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TreeTapBlock extends AbstractTreeTapBlock {
	public static final IntProperty FILL_LEVEL = IntProperty.of("fill_level", 0, 3);

    public TreeTapBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(ATTACHED, false).with(FILL_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, ATTACHED, FILL_LEVEL);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(ATTACHED);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (world.random.nextInt(8) == 0 && state.get(ATTACHED)) {
			int level = state.get(FILL_LEVEL);
			if (level < 3) {
				world.setBlockState(pos, state.with(FILL_LEVEL, level+1));
			}
			else {
				world.setBlockState(pos, getFilled(state, world, pos));
			}
        }
    }
	public BlockState getFilled(BlockState state, ServerWorld world, BlockPos pos) {
		BlockState log = world.getBlockState(pos.offset(state.get(FACING).getOpposite()));
		if (!isTappable(log)) {
			return state.with(ATTACHED, false).with(FILL_LEVEL, 0);
		}
		return MBEvents.TAPPABLE.get(log.getBlock()).getDefaultState().with(FACING, state.get(FACING)).with(ATTACHED, true);
	}

}
