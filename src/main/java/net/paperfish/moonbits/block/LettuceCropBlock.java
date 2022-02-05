package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBItems;

import java.util.Random;

public class LettuceCropBlock extends CropBlock {
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)};

    public LettuceCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int i = this.getAge(state) + this.getGrowthAmount(world);
        if (i >= this.getMaxAge()) {
            world.setBlockState(pos, MBBlocks.LETTUCE_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
        }
        else {
            world.setBlockState(pos, this.withAge(i), Block.NOTIFY_LISTENERS);
        }
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        float moisture = CropBlock.getAvailableMoisture(this, world, pos);
        int age = this.getAge(state);
        if (world.getBaseLightLevel(pos, 0) >= 9 && age < this.getMaxAge() && random.nextInt((int)(25.0f / moisture) + 1) == 0) {
            if (age + 1 >= this.getMaxAge()) {
                world.setBlockState(pos, MBBlocks.LETTUCE_BLOCK.getDefaultState(), Block.NOTIFY_LISTENERS);
            }
            else {
                world.setBlockState(pos, this.withAge(age + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return MBItems.LETTUCE_SEEDS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }
}
