package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WildCropBlock extends PlantBlock {
    protected static final float field_31235 = 6.0F;
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    
    public WildCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

    public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.XZ;
	}
}
