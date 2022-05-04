package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlocks;
import org.jetbrains.annotations.Nullable;

public class LettuceBlock extends FallingBlock {
    public static final BooleanProperty PERSISTENT; // just using it to determine whether its player-placed or grown
    static {
        PERSISTENT = Properties.PERSISTENT;
    }

    public LettuceBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(PERSISTENT, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(PERSISTENT);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getStateManager().getDefaultState().with(PERSISTENT, true);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        Block below = world.getBlockState(pos.down()).getBlock();
        if ((below instanceof FarmlandBlock || below instanceof PlanterBoxBlock) && !state.get(PERSISTENT)) {
            world.setBlockState(pos, MBBlocks.LETTUCE_CROP.getDefaultState().with(Properties.AGE_7, 2), Block.NOTIFY_ALL);
        }
        super.onBroken(world, pos, state);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if (fallingBlockEntity.getVelocity().y < -12 && !world.isClient()) {
            Block.getDroppedStacks(fallingBlockState, (ServerWorld) world, pos, null).forEach((stack) -> Block.dropStack(world, pos, stack));
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
        }
        super.onLanding(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }
}
