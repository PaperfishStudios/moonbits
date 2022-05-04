package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;

public class FrosthornStemBlock extends Block {
    public final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);

    public FrosthornStemBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        return floor.isOf(this) || floor.isIn(BlockTags.DIRT) || floor.isIn(MBBlockTags.SOIL_NON_REPLACEABLE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
