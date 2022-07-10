package net.paperfish.moonbits.block.extended;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.paperfish.moonbits.registry.MBBlocks;

import net.minecraft.util.math.random.Random;

public class MBGrassPlantBlock extends FernBlock {
    public MBGrassPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        TallPlantBlock tallPlantBlock = (TallPlantBlock) Blocks.TALL_GRASS;
        if (state.isOf(MBBlocks.BEACHGRASS)) {
            tallPlantBlock = (TallPlantBlock) MBBlocks.TALL_BEACHGRASS;
        }
        else if (state.isOf(MBBlocks.COTTONGRASS)) {
            tallPlantBlock = (TallPlantBlock) MBBlocks.TALL_COTTONGRASS;
        }
        else if (state.isOf(MBBlocks.DESERT_BRUSH)) {
            tallPlantBlock = (TallPlantBlock) MBBlocks.TALL_DESERT_BRUSH;
        }

        if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
            TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
        }
    }

    @Override
    public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        BlockState state = this.getDefaultState();
        if ((state.isOf(MBBlocks.BEACHGRASS) || state.isOf(MBBlocks.DESERT_BRUSH)) && floor.isIn(BlockTags.SAND)) {
            return true;
        }
        return super.canPlantOnTop(floor, world, pos);
    }
}
