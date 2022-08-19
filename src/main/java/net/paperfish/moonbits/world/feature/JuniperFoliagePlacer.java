package net.paperfish.moonbits.world.feature;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.paperfish.moonbits.world.gen.MBTreeFeatures;

import java.util.function.BiConsumer;

public class JuniperFoliagePlacer extends FoliagePlacer {
    public static final Codec<JuniperFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> fillFoliagePlacerFields(instance).apply(instance, JuniperFoliagePlacer::new));
    protected final int height;

//    protected static <P extends JuniperFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> createCodec(RecordCodecBuilder.Instance<P> builder) {
//        return BlobFoliagePlacer.fillFoliagePlacerFields(builder).and((Codec.intRange(0, 16).fieldOf("height")).forGetter(placer -> placer.height));
//    }

    public JuniperFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
        this.height = 3;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return MBTreeFeatures.JUNIPER_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, RandomGenerator random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 2, -trunkHeight+2, treeNode.isGiantTrunk());
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 0, offset+1, treeNode.isGiantTrunk());
        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 0, offset, treeNode.isGiantTrunk());
//        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, offset-1, treeNode.isGiantTrunk());
//        this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, offset-2, treeNode.isGiantTrunk());
        for (int i = (offset); i >= -trunkHeight+3; --i) {
            if (i > offset-2) {
                this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 1, i, treeNode.isGiantTrunk());
            }
            else if (i > offset-4) {
                this.generateSquare(world, replacer, random, config, treeNode.getCenter(), 2, i, treeNode.isGiantTrunk());
            }
            else {
                this.generateSquare(world, replacer, random, config, treeNode.getCenter().north(), 2, i, treeNode.isGiantTrunk());
                this.generateSquare(world, replacer, random, config, treeNode.getCenter().south(), 2, i, treeNode.isGiantTrunk());
                this.generateSquare(world, replacer, random, config, treeNode.getCenter().east(), 2, i, treeNode.isGiantTrunk());
                this.generateSquare(world, replacer, random, config, treeNode.getCenter().west(), 2, i, treeNode.isGiantTrunk());
            }
        }
    }

    @Override
    public int getRandomHeight(RandomGenerator random, int trunkHeight, TreeFeatureConfig config) {
        return this.height;
    }

    @Override
    protected boolean isInvalidForLeaves(RandomGenerator random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y==1 || radius == 0) {
            return false;
        }
        return dx == radius && dz == radius && (random.nextInt(4) != 0 || y == 0);
    }
}
