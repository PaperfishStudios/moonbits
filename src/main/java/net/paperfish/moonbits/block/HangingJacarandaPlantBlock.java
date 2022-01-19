package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBParticles;

import java.util.Random;

public class HangingJacarandaPlantBlock extends AbstractPlantBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public HangingJacarandaPlantBlock(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock)MBBlocks.HANGING_JACARANDA_LEAVES;
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.up());
        return blockState.isOf(this.getStem()) || blockState.isOf(this.getPlant()) || blockState.isSideSolidFullSquare(world, pos.up(), this.growthDirection);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(15) == 1) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            double d = (double) i + random.nextDouble();
            double e = (double) j + 0.7D;
            double f = (double) k + random.nextDouble();
            world.addParticle(MBParticles.FALLING_JACARANDA, d, e, f, 0.0D, 0.0D, 0.0D);
        }
    }
}
