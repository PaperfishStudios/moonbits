package net.moonteam.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.MBData;
import net.moonteam.moonbits.MBParticles;

import java.util.Random;

public class HangingJacarandaBlock extends AbstractPlantStemBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public HangingJacarandaBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1D);
    }

    @Override
    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }

    @Override
    protected Block getPlant() {
        return MBBlocks.HANGING_JACARANDA_LEAVES_PLANT;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return blockState.isOf(this.getStem()) || blockState.isOf(this.getPlant()) || Block.isFaceFullSquare(blockState.getCollisionShape(world, pos), Direction.DOWN);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(15) == 1) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            double d = (double) i + random.nextDouble();
            double e = (double) j + 0.7D;
            double f = (double) k + random.nextDouble();
            world.addParticle(MBParticles.FALLING_JACARANDA, d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }
}