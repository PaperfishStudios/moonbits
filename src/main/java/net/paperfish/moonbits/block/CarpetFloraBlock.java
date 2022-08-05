package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;

public class CarpetFloraBlock extends PlantBlock {
	protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
	protected static final VoxelShape CLOVER_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0);
    public CarpetFloraBlock(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState floor = world.getBlockState(pos.down());
        if (state.isOf(MBBlocks.CLOVER) && floor.isOf(Blocks.CLAY)) {
            return true;
        }
        return floor.isIn(BlockTags.DIRT) || floor.isIn(MBBlockTags.TOUGH_DIRT) ||
                floor.isIn(MBBlockTags.PLANTER_BOXES) || floor.isOf(Blocks.FARMLAND);
    }
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Vec3d vec3d = state.getModelOffset(world, pos);
		if (state.isOf(MBBlocks.CLOVER)) {
			return CLOVER_SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
		}
		else {
			return SHAPE;
		}
	}

//    @Override
//    public AbstractBlock.OffsetType getOffsetType() {
//        return AbstractBlock.OffsetType.XZ;
//    }
}
