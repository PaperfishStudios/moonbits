package net.moonteam.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class LeafCarpetBlock extends CarpetBlock {
    public LeafCarpetBlock(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return !world.isAir(pos.down()) && world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos, Direction.UP);
    }
}
