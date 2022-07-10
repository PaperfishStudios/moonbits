package net.paperfish.moonbits.world.feature;

import com.mojang.datafixers.Products;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import net.minecraft.util.math.random.Random;
import java.util.function.BiConsumer;

public class CedarFoliagePlacer extends FoliagePlacer {
    public static final Codec<CedarFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> fillFoliagePlacerFields(instance).apply(instance, CedarFoliagePlacer::new));
    protected final int height;

//    protected static <P extends CedarFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> createCodec(RecordCodecBuilder.Instance<P> builder) {
//        return BlobFoliagePlacer.fillFoliagePlacerFields(builder).and((Codec.intRange(0, 16).fieldOf("height")).forGetter(placer -> placer.height));
//    }

    public CedarFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
        this.height = 3;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return MBTreeFeatures.CEDAR_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, -trunkHeight+1, treeNode.isGiantTrunk());
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 0, offset+1, treeNode.isGiantTrunk());
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 0, offset, treeNode.isGiantTrunk());
//        FoliagePlacer.placeFoliageBlock(world, replacer, random, config, mutable);
//        FoliagePlacer.placeFoliageBlock(world, replacer, random, config, mutable);
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, offset-1, treeNode.isGiantTrunk());
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, offset-2, treeNode.isGiantTrunk());
        for (int i = (offset - 3); i >= -trunkHeight+2; --i) {
            //int j = Math.min(Math.max(radius + treeNode.getFoliageRadius() - 1 - i / 3, 0), 2);
            this.generateSquare(world, replacer, random, config, treeNode.getCenter().north(), 1, i, treeNode.isGiantTrunk());
            this.generateSquare(world, replacer, random, config, treeNode.getCenter().south(), 1, i, treeNode.isGiantTrunk());
            this.generateSquare(world, replacer, random, config, treeNode.getCenter().east(), 1, i, treeNode.isGiantTrunk());
            this.generateSquare(world, replacer, random, config, treeNode.getCenter().west(), 1, i, treeNode.isGiantTrunk());
        }
//        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 2, offset-2, treeNode.isGiantTrunk());
//        for (int i = offset--; i >= offset - (trunkHeight - 3); --i) {
//            int j = Math.min(Math.max(radius + treeNode.getFoliageRadius() - 1 - i / 3, 0), 2);
//            this.generateSquare(world, replacer, random, config, treeNode.getCenter(), j, i, treeNode.isGiantTrunk());
//        }
//        for (int i = offset--; i >= offset - (trunkHeight - 7); --i) {
//            int j = Math.min(Math.max(radius + treeNode.getFoliageRadius() - 1 - i / 2, 0), 3);
//            this.generateSquare(world, replacer, random, config, treeNode.getCenter().north(), 2, i - 3, treeNode.isGiantTrunk());
//            this.generateSquare(world, replacer, random, config, treeNode.getCenter().south(), 2, i - 3, treeNode.isGiantTrunk());
//            this.generateSquare(world, replacer, random, config, treeNode.getCenter().east(), 2, i - 3, treeNode.isGiantTrunk());
//            this.generateSquare(world, replacer, random, config, treeNode.getCenter().west(), 2, i - 3, treeNode.isGiantTrunk());
//        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.height;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y==1 || radius == 0) {
            return false;
        }
        return dx == radius && dz == radius && (random.nextInt(4) != 0 || y == 0);
    }
}
