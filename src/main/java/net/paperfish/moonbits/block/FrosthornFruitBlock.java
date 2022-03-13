package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.MBBlocks;

public class FrosthornFruitBlock extends Block {
    public final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
    public FrosthornFruitBlock(Settings settings) {
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
        return floor.isSideSolidFullSquare(world, pos.down(), Direction.UP);
    }
}
