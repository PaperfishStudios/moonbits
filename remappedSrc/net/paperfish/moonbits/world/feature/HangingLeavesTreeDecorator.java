package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import java.util.List;
import net.minecraft.util.math.random.Random;
import java.util.function.BiConsumer;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC;
    private final BlockStateProvider provider;

    public HangingLeavesTreeDecorator(BlockStateProvider provider) {
        this.provider = provider;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return MBTreeFeatures.HANGING_LEAVES_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        TestableWorld world = generator.getWorld();
        List<BlockPos> leavesPositions = generator.getLeavesPositions();

        if (!leavesPositions.isEmpty()) {
            // make a list of every leaf block with air below it, then do a for loop w/ each one
            leavesPositions.stream().filter((pos) -> world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir)).forEach((pos) -> {
                int r = random.nextInt(4);
                BlockPos blockPos = pos.down();
                if (r < 2) {
                    generator.replace(blockPos, this.provider.getBlockState(random, pos));
                }
            });
        }
    }

//    @Override
//    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
//        if (!leavesPositions.isEmpty()) {
//            // make a list of every leaf block with air below it, then do a for loop w/ each one
//            leavesPositions.stream().filter((pos) -> {
//                return world.testBlockState(world, pos.down());
//            }).forEach((pos) -> {
//                int r = random.nextInt(4);
//                BlockPos blockPos = pos.down();
//                if (r < 2) {
//                    replacer.accept(blockPos, this.provider.getBlockState(random, pos));
//                }
//            });
//        }
//    }

    static {
        CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("provider").xmap(HangingLeavesTreeDecorator::new, (decorator) -> decorator.provider).codec();
    }
}
