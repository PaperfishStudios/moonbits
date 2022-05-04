package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBParticles;

import java.util.Random;

public class HangingAcaciaBlock extends PlantBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    public HangingAcaciaBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    @Override
    protected boolean canPlantOnTop(BlockState blockState, BlockView world, BlockPos pos) {
        return Block.isFaceFullSquare(blockState.getCollisionShape(world, pos), Direction.DOWN);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(15) == 1) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            double d = (double) i + random.nextDouble();
            double e = (double) j + 0.7D;
            double f = (double) k + random.nextDouble();
            world.addParticle(MBParticles.FALLING_WATTLE, d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }
}
