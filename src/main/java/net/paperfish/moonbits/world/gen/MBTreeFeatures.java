package net.paperfish.moonbits.world.gen;

import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.Holder;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.paperfish.moonbits.block.HardyLeavesBlock;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.MushroomCapBlock;
import net.paperfish.moonbits.mixin.TreeDecoratorTypeInvoker;
import net.paperfish.moonbits.world.feature.*;

import java.util.List;
import java.util.OptionalInt;

public class MBTreeFeatures {
    // beehive decorator probabilities (number indicates %, shift the decimal forward by one)
    public static final BeehiveTreeDecorator BEES_0002 = new BeehiveTreeDecorator(0.002F);
    public static final BeehiveTreeDecorator BEES_002 = new BeehiveTreeDecorator(0.02F);
    public static final BeehiveTreeDecorator BEES_005 = new BeehiveTreeDecorator(0.05F);
    public static final BeehiveTreeDecorator BEES = new BeehiveTreeDecorator(1.0F);

    public static final TreeDecoratorType<FallenLeavesTreeDecorator> FALLEN_LEAVES_TREE_DECORATOR =
            TreeDecoratorTypeInvoker.callRegister("fallen_leaves", FallenLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<HangingLeavesTreeDecorator> HANGING_LEAVES_TREE_DECORATOR =
            TreeDecoratorTypeInvoker.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);

    public static final FallenLogFeature FALLEN_LOG = Registry.register(Registry.FEATURE, new Identifier(Moonbits.MODID, "fallen_log_feature"), new FallenLogFeature(FallenLogConfig.CODEC));
    public static final RedMushroomFeature RED_MUSHROOM_FEATURE =
            Registry.register(Registry.FEATURE, new Identifier(Moonbits.MODID, "red_mushroom_feature"), new RedMushroomFeature(HugeMushroomFeatureConfig.CODEC));
    public static final BrownMushroomFeature BROWN_MUSHROOM_FEATURE =
            Registry.register(Registry.FEATURE, new Identifier(Moonbits.MODID, "brown_mushroom_feature"), new BrownMushroomFeature(HugeMushroomFeatureConfig.CODEC));
    public static final GiantToadstoolFeature SAFFRON_MUSHROOM_FEATURE =
            Registry.register(Registry.FEATURE, new Identifier(Moonbits.MODID, "saffron_mushroom_feature"), new GiantToadstoolFeature(HugeMushroomFeatureConfig.CODEC));

	public static final ParasolFernFeature PARASOL_FERN_FEATURE =
			Registry.register(Registry.FEATURE, new Identifier(Moonbits.MODID, "parasol_fern_feature"), new ParasolFernFeature(HugeMushroomFeatureConfig.CODEC));


    public static final Holder<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> HUGE_BROWN_MUSHROOM =
            MBConfiguredFeatures.register("mb_brown_mushroom", BROWN_MUSHROOM_FEATURE, new HugeMushroomFeatureConfig(
                    BlockStateProvider.of(MBBlocks.BROWN_MUSHROOM_CAP.getDefaultState().with(MushroomCapBlock.UP, true).with(MushroomCapBlock.DOWN, false)),
                    BlockStateProvider.of(MBBlocks.MUSHROOM_STEM.getDefaultState()), 3));
    public static final Holder<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> HUGE_RED_MUSHROOM =
            MBConfiguredFeatures.register("mb_red_mushroom", RED_MUSHROOM_FEATURE, new HugeMushroomFeatureConfig(
                    BlockStateProvider.of(MBBlocks.RED_MUSHROOM_CAP.getDefaultState().with(MushroomBlock.DOWN, false)),
                    BlockStateProvider.of(MBBlocks.MUSHROOM_STEM.getDefaultState()), 2));
    public static final Holder<ConfiguredFeature<HugeMushroomFeatureConfig, ?>> SAFFRON_MUSHROOM =
            MBConfiguredFeatures.register("mb_saffron_mushroom", BROWN_MUSHROOM_FEATURE, new HugeMushroomFeatureConfig(
                    BlockStateProvider.of(MBBlocks.SAFFRON_MUSHROOM_CAP.getDefaultState().with(MushroomCapBlock.UP, true).with(MushroomCapBlock.DOWN, false)),
                    BlockStateProvider.of(MBBlocks.MUSHROOM_STEM.getDefaultState()), 2));

    public static final FoliagePlacerType<CedarFoliagePlacer> CEDAR_FOLIAGE_PLACER =
            Registry.register(Registry.FOLIAGE_PLACER_TYPE, "cedar_foliage_placer", new FoliagePlacerType<>(CedarFoliagePlacer.CODEC));
    public static final FoliagePlacerType<JuniperFoliagePlacer> JUNIPER_FOLIAGE_PLACER =
            Registry.register(Registry.FOLIAGE_PLACER_TYPE, "juniper_foliage_placer", new FoliagePlacerType<>(JuniperFoliagePlacer.CODEC));

//    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>  JUNIPER = MBConfiguredFeatures.register("juniper", Feature.TREE, juniper().build());
//    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JUNIPER_BUSH = ConfiguredFeatures.register("juniper_bush", Feature.TREE,
//            new TreeFeatureConfig.Builder(BlockStateProvider.of(MBBlocks.JUNIPER_LOG), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.of(MBBlocks.JUNIPER_LEAVES),
//                    new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2), new TwoLayersFeatureSize(0, 0, 0)).build());
    public static final Holder<ConfiguredFeature<TreeFeatureConfig, ?>>  CEDAR = MBConfiguredFeatures.register("cedar", Feature.TREE, cedar().build());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>>  FALLEN_OAK =
            MBConfiguredFeatures.register("fallen_oak", FALLEN_LOG, new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState())));
    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>>  FALLEN_BIRCH =
            MBConfiguredFeatures.register("fallen_birch", FALLEN_LOG, new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(Blocks.BIRCH_LOG.getDefaultState())));
    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>>  FALLEN_SPRUCE =
            MBConfiguredFeatures.register("fallen_spruce", FALLEN_LOG, new FallenLogConfig(UniformIntProvider.create(4, 6), BlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState())));
//    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>>  FALLEN_JUNIPER =
//            MBConfiguredFeatures.register("fallen_juniper", FALLEN_LOG, new FallenLogConfig(UniformIntProvider.create(4, 5), BlockStateProvider.of(MBBlocks.JUNIPER_LOG.getDefaultState())));

    private static TreeFeatureConfig.Builder treeBuilder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(log), // Trunk block provider
                new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
                BlockStateProvider.of(leaves), // Foliage block provider
                new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
        );
    }

    private static TreeFeatureConfig.Builder cedar() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(MBBlocks.CEDAR_LOG), // Trunk block provider
                new StraightTrunkPlacer(7, 3, 0),
                BlockStateProvider.of(MBBlocks.CEDAR_LEAVES), // Foliage block provider
                new CedarFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
        );
    }
//    private static TreeFeatureConfig.Builder juniper() {
//        return new TreeFeatureConfig.Builder(
//                BlockStateProvider.of(MBBlocks.JUNIPER_LOG), // Trunk block provider
//                new StraightTrunkPlacer(8, 4, 0),
//                BlockStateProvider.of(MBBlocks.JUNIPER_LEAVES), // Foliage block provider
//                new JuniperFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
//                new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
//        );
//    }

//    private static TreeFeatureConfig.Builder goldenBirch() {
//        return treeBuilder(Blocks.BIRCH_LOG, MBBlocks.GOLDEN_BIRCH_LEAVES, 5, 2, 0, 2).ignoreVines();
//    }
//    private static TreeFeatureConfig.Builder superGoldenBirch() {
//        return treeBuilder(Blocks.BIRCH_LOG, MBBlocks.GOLDEN_BIRCH_LEAVES, 6, 2, 5, 2).ignoreVines();
//    }
//    private static TreeFeatureConfig.Builder redOak() {
//        return treeBuilder(Blocks.DARK_OAK_LOG, MBBlocks.RED_OAK_LEAVES, 5, 2, 0, 2).ignoreVines();
//    }
}
