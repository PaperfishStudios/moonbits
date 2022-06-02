package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.Material;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class LeafCarpetBlock extends CarpetBlock {
    public LeafCarpetBlock(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos.down());
        FluidState fluidState2 = world.getFluidState(pos);
        if (fluidState.getFluid() == Fluids.WATER && fluidState2.getFluid() == Fluids.EMPTY) {
            return true;
        }
        return !world.isAir(pos.down()) && world.getBlockState(pos.down()).isSideSolidFullSquare(world, pos, Direction.UP);
    }
}
