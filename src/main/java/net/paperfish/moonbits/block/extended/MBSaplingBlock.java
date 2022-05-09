package net.paperfish.moonbits.block.extended;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;

public class MBSaplingBlock extends SaplingBlock {

    public MBSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    protected boolean canPlantJuniper(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(MBBlockTags.SANDY_SOILS);
    }
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        if (state.isOf(MBBlocks.JUNIPER_SAPLING) && canPlantJuniper(world.getBlockState(blockPos), world, blockPos)) {
            return true;
        }
        return super.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }
    
}
