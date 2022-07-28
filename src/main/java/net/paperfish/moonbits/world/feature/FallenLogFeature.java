package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FallenLogFeature extends Feature<FallenLogConfig> {
    public FallenLogFeature(Codec<FallenLogConfig> codec) {
        super(codec);
    }

    public boolean place(FeatureContext<FallenLogConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        RandomGenerator random = context.getRandom();
        Direction dir = random.nextInt(2) == 0 ? Direction.NORTH : Direction.EAST;
        FallenLogConfig config = context.getConfig();
        int size = config.size().get(context.getRandom());
        BlockPos blockPos = pos;

        for (int y = 0; y < size; y++) {
            blockPos = blockPos.offset(dir);
            if (!world.getBlockState(blockPos).isAir()) { return false; }
        }
        if (!world.getBlockState(pos.down().offset(dir)).isIn(BlockTags.DIRT) || !world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        }

        blockPos = pos;
        for (int y = 0; y < size; y++) {
            blockPos = blockPos.offset(dir);
            world.setBlockState(blockPos, config.block().getBlockState(random, blockPos).with(Properties.AXIS, dir.getAxis()), 3);
            if (random.nextInt(3) == 0) {
                world.setBlockState(blockPos.up(), Blocks.MOSS_CARPET.getDefaultState(), 3);
            } else if (random.nextInt(16) == 0) {
                world.setBlockState(blockPos.up(), Blocks.BROWN_MUSHROOM.getDefaultState(), 0);
            }
        }

        return true;
    }
}
