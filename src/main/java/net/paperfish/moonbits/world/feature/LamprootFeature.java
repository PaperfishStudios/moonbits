package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;

import java.util.List;

public class LamprootFeature extends Feature<SimpleBlockFeatureConfig> {
    public LamprootFeature(Codec<SimpleBlockFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<SimpleBlockFeatureConfig> context) {
        SimpleBlockFeatureConfig simpleBlockFeatureConfig = context.getConfig();
        StructureWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        BlockState blockState = Blocks.AIR.getDefaultState();
        if (!world.getBlockState(pos.down()).isAir() || !world.getBlockState(pos).isAir()) return false;

        // determine what side its attached to
        if (world.getBlockState(pos.up()).isIn(MBBlockTags.TOUGH_DIRT)) {
            blockState = MBBlocks.LAMPROOT.getDefaultState().with(Properties.FACING, Direction.UP);
        } else {
            List<Direction> list = List.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
            for (Direction direction : list) {
                if (world.getBlockState(pos.offset(direction)).isIn(MBBlockTags.TOUGH_DIRT)) {
                    blockState = MBBlocks.LAMPROOT.getDefaultState().with(Properties.FACING, direction.getOpposite());
                }
            }
        }
        if (blockState.isAir()) {
            return false;
        }

        world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
        return true;
    }
}
