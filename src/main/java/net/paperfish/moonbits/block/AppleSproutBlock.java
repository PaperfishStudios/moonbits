package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlocks;

import java.util.Random;

public class AppleSproutBlock extends PlantBlock implements Fertilizable {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0);

    public AppleSproutBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(7) == 0) {
            world.setBlockState(pos, MBBlocks.APPLE_OAK_SAPLING.getDefaultState(), NOTIFY_NEIGHBORS);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, MBBlocks.APPLE_OAK_SAPLING.getDefaultState(), NOTIFY_NEIGHBORS);
    }
}
