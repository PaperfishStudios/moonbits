package net.moonteam.moonbits.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.mixin.TreeDecoratorTypeInvoker;

import java.util.List;

public class MBTreeFeatures {
    // beehive decorator probabilities (number indicates %, shift the decimal forward by one)
    public static final BeehiveTreeDecorator BEES_0002;
    public static final BeehiveTreeDecorator BEES_002;
    public static final BeehiveTreeDecorator BEES_005;
    public static final BeehiveTreeDecorator BEES;

    public static final TreeDecoratorType<FallenLeavesTreeDecorator> FALLEN_LEAVES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("fallen_leaves", FallenLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);

    public static final FallenLogFeature FALLEN_LOG;

    // single tree features
    public static final ConfiguredFeature<TreeFeatureConfig, ?> GOLDEN_BIRCH;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> GOLDEN_BIRCH_BEES_0002;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> GOLDEN_BIRCH_BEES_002;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> GOLDEN_BIRCH_BEES_005;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> SUPER_GOLDEN_BIRCH_BEES_0002;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> JACARANDA;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> JACARANDA_BEES_002;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> JACARANDA_BEES_005;

    public static final ConfiguredFeature<FallenLogConfig, ?> FALLEN_OAK;
    public static final ConfiguredFeature<FallenLogConfig, ?> FALLEN_BIRCH;
    public static final ConfiguredFeature<FallenLogConfig, ?> FALLEN_SPRUCE;
    public static final ConfiguredFeature<FallenLogConfig, ?> FALLEN_JACARANDA;

    private static TreeFeatureConfig.Builder treeBuilder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(log), // Trunk block provider
                new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
                BlockStateProvider.of(leaves), // Foliage block provider
                new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
        );
    }

    private static TreeFeatureConfig.Builder goldenBirch() {
        return treeBuilder(Blocks.BIRCH_LOG, MBBlocks.GOLDEN_BIRCH_LEAVES, 4, 2, 0, 2).ignoreVines();
    }
    private static TreeFeatureConfig.Builder superGoldenBirch() {
        return treeBuilder(Blocks.BIRCH_LOG, MBBlocks.GOLDEN_BIRCH_LEAVES, 6, 2, 6, 2).ignoreVines();
    }
    private static TreeFeatureConfig.Builder jacaranda() {
        return treeBuilder(MBBlocks.JACARANDA_LOG, MBBlocks.JACARANDA_LEAVES, 6, 3, 0, 2).ignoreVines();
    }

    static {
        BEES_0002 = new BeehiveTreeDecorator(0.002F);
        BEES_002 = new BeehiveTreeDecorator(0.02F);
        BEES_005 = new BeehiveTreeDecorator(0.05F);
        BEES = new BeehiveTreeDecorator(1.0F);

        FALLEN_LOG = Registry.register(Registry.FEATURE, "fallen_log_feature", new FallenLogFeature(FallenLogConfig.CODEC));

        GOLDEN_BIRCH = ConfiguredFeatures.register("golden_birch", Feature.TREE.configure(goldenBirch().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET))
        )).build()));
        GOLDEN_BIRCH_BEES_0002 = ConfiguredFeatures.register("golden_birch_bees_0002", Feature.TREE.configure(goldenBirch().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET)),
                BEES_0002
        )).build()));
        GOLDEN_BIRCH_BEES_002 = ConfiguredFeatures.register("golden_birch_bees_002", Feature.TREE.configure(goldenBirch().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET)),
                BEES_002
        )).build()));
        GOLDEN_BIRCH_BEES_005 = ConfiguredFeatures.register("golden_birch_bees_005", Feature.TREE.configure(goldenBirch().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET)),
                BEES_005
        )).build()));

        SUPER_GOLDEN_BIRCH_BEES_0002 = ConfiguredFeatures.register("super_golden_birch_bees_002", Feature.TREE.configure(superGoldenBirch().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.GOLDEN_BIRCH_LEAF_CARPET)),
                BEES_0002
        )).build()));

        JACARANDA = ConfiguredFeatures.register("jacaranda", Feature.TREE.configure(jacaranda().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.JACARANDA_LEAF_CARPET)),
                new HangingLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.HANGING_JACARANDA_LEAVES))
        )).build()));
        JACARANDA_BEES_002 = ConfiguredFeatures.register("jacaranda_bees_002", Feature.TREE.configure(jacaranda().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.JACARANDA_LEAF_CARPET)),
                new HangingLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.HANGING_JACARANDA_LEAVES)),
                BEES_002
        )).build()));
        JACARANDA_BEES_005 = ConfiguredFeatures.register("jacaranda_bees_005", Feature.TREE.configure(jacaranda().decorators(List.of(
                new FallenLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.JACARANDA_LEAF_CARPET)),
                new HangingLeavesTreeDecorator(BlockStateProvider.of(MBBlocks.HANGING_JACARANDA_LEAVES)),
                BEES_005
        )).build()));

        FALLEN_OAK = ConfiguredFeatures.register("fallen_oak", FALLEN_LOG.configure(new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()))));
        FALLEN_BIRCH = ConfiguredFeatures.register("fallen_birch", FALLEN_LOG.configure(new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(Blocks.BIRCH_LOG.getDefaultState()))));
        FALLEN_SPRUCE = ConfiguredFeatures.register("fallen_spruce", FALLEN_LOG.configure(new FallenLogConfig(UniformIntProvider.create(4, 6), BlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()))));
        FALLEN_JACARANDA = ConfiguredFeatures.register("fallen_jacaranda", FALLEN_LOG.configure(new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(MBBlocks.JACARANDA_LOG.getDefaultState()))));
    }
}
