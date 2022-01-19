package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.block.PebbleBlock;

import java.util.Random;

public class PebbleFeature extends Feature<CountConfig> {
    public PebbleFeature(Codec<CountConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<CountConfig> context) {
        int i = 0;
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        int j = ((CountConfig)context.getConfig()).getCount().get(random);

        for(int k = 0; k < j; ++k) {
            int l = random.nextInt(8) - random.nextInt(8);
            int m = random.nextInt(8) - random.nextInt(8);
            int n = structureWorldAccess.getTopY(Heightmap.Type.WORLD_SURFACE, blockPos.getX() + l, blockPos.getZ() + m);
            BlockPos blockPos2 = new BlockPos(blockPos.getX() + l, n, blockPos.getZ() + m);
            BlockState blockState = (BlockState) MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, random.nextInt(3) + 1);
            BlockState blockStateW = (BlockState) MBBlocks.PEBBLES.getDefaultState().with(PebbleBlock.PEBBLES, random.nextInt(3) + 1).with(Properties.WATERLOGGED, true);
            if (structureWorldAccess.getBlockState(blockPos2).isAir() && blockState.canPlaceAt(structureWorldAccess, blockPos2)) {
                structureWorldAccess.setBlockState(blockPos2, blockState, 2);
                ++i;
            }
            else if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER) && blockStateW.canPlaceAt(structureWorldAccess, blockPos2)) {
                structureWorldAccess.setBlockState(blockPos2, blockStateW, 2);
                ++i;
            }
        }

        return i > 0;
    }
}