package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class GunpowderBarrelBlock extends Block {
    public static final BooleanProperty SIGNAL_FIRE = Properties.SIGNAL_FIRE;

    public GunpowderBarrelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SIGNAL_FIRE, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SIGNAL_FIRE);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) {
            return state.with(SIGNAL_FIRE, neighborState.isOf(Blocks.FIRE));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(SIGNAL_FIRE)) {
            if (random.nextFloat() < 0.11f) {
                for (int i = 0; i < random.nextInt(2) + 2; ++i) {
                    world.addImportantParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true,
                            (double) pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                            (double) pos.getY() + random.nextDouble() + random.nextDouble(),
                            (double) pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double) (random.nextBoolean() ? 1 : -1),
                            0.0, 0.07, 0.0);
                }
            }
        }
    }
}
