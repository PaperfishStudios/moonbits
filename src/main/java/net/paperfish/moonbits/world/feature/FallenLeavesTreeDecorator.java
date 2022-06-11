package net.paperfish.moonbits.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import java.util.List;
import net.minecraft.util.math.random.Random;
import java.util.function.BiConsumer;

public class FallenLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<FallenLeavesTreeDecorator> CODEC;
    private final BlockStateProvider provider;

    public FallenLeavesTreeDecorator(BlockStateProvider provider) {
        this.provider = provider;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return MBTreeFeatures.FALLEN_LEAVES_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        TestableWorld world = generator.getWorld();
        generator.getLeavesPositions().stream().filter((pos) -> generator.getWorld().testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir)).forEach(pos -> {
            BlockPos blockPos = pos.down();
            // while the block below blockPos is still air, shift blockPos down by 1
            while(world.testBlockState(blockPos.down(), AbstractBlock.AbstractBlockState::isAir) && blockPos.isWithinDistance(pos, 10)) {
                blockPos = blockPos.down();
            }
            // once you've reached the last air block, place the fallen leaves.
            BlockState a = this.provider.getBlockState(random, pos);
            if (a.canPlaceAt((WorldView) world, blockPos) && random.nextInt(8) == 0) {
                generator.replace(blockPos, a);
            }
        });
    }

//    @Override
//    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
//        if (!leavesPositions.isEmpty() && !logPositions.isEmpty()) {
//            int floorLevel = ((BlockPos)logPositions.get(0)).getY();
//            // make a list of every leaf block with air below it, then do a for loop w/ each one
//            leavesPositions.stream().filter((pos) -> {
//                return world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir);
//            }).forEach((pos) -> {
//                BlockPos blockPos = pos.down();
//                // while the block below blockPos is still air, shift blockPos down by 1
//                while(world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir) && blockPos.isWithinDistance(pos, 10)) {
//                    blockPos = blockPos.down();
//                }
//                // once you've reached the last air block, place the fallen leaves.
//                BlockState a = this.provider.getBlockState(random, pos);
//                if (a.canPlaceAt((WorldView) world, blockPos)) {
//                    replacer.accept(blockPos, a);
//                }
//            });
//        }
//    }

    static {
        CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("provider").xmap(FallenLeavesTreeDecorator::new, (decorator) -> decorator.provider).codec();
    }
}
