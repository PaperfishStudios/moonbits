package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlocks;

public class FrosthornLeavesBlock extends PlantBlock {
    public final VoxelShape SHAPE = Block.createCuboidShape(6, 0, 6, 10, 6, 10);
    public FrosthornLeavesBlock(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        if (floor.isOf(MBBlocks.FROSTHORN_CROWN)) {
            return floor.get(Properties.ATTACHED);
        }
        return super.canPlaceAt(state, world, pos);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (random.nextInt(25) == 0){
            world.setBlockState(pos, MBBlocks.FROSTHORN_FRUIT.getDefaultState(), NOTIFY_LISTENERS);
        }
    }
}
