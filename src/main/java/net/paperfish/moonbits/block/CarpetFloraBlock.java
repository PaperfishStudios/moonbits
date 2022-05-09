package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;

public class CarpetFloraBlock extends CarpetBlock {
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
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }
}
