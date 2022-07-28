package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.paperfish.moonbits.registry.MBBlocks;

public class MBPlacedTreeFeatures {
    public static final Holder<PlacedFeature> GOLDEN_BIRCH = MBPlacedFeatures.register("p_golden_birch",
            MBTreeFeatures.GOLDEN_BIRCH, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final Holder<PlacedFeature> GOLDEN_BIRCH_BEES_002 = MBPlacedFeatures.register("p_golden_birch_002",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_002, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final Holder<PlacedFeature> GOLDEN_BIRCH_BEES_005 = MBPlacedFeatures.register("p_golden_birch_005",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_005, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final Holder<PlacedFeature> RED_OAK = MBPlacedFeatures.register("p_red_oak",
            MBTreeFeatures.RED_OAK, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.RED_OAK_SAPLING));
    public static final Holder<PlacedFeature> RED_OAK_BEES_002 = MBPlacedFeatures.register("p_red_oak_002",
            MBTreeFeatures.RED_OAK_BEES_002, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.RED_OAK_SAPLING));
    public static final Holder<PlacedFeature> RED_OAK_BEES_005 = MBPlacedFeatures.register("p_red_oak_005",
            MBTreeFeatures.RED_OAK_BEES_005, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.RED_OAK_SAPLING));
    public static final Holder<PlacedFeature> BIG_RED_OAK = MBPlacedFeatures.register("big_red_oak",
            MBTreeFeatures.BIG_RED_OAK, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.RED_OAK_SAPLING));
//    public static final RegistryEntry<PlacedFeature> JUNIPER = MBPlacedFeatures.register("p_juniper",
//            MBTreeFeatures.JUNIPER, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
//    public static final RegistryEntry<PlacedFeature> JUNIPER_BUSH = MBPlacedFeatures.register("p_juniper_bush",
//            MBTreeFeatures.JUNIPER_BUSH, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
    public static final Holder<PlacedFeature> CEDAR = MBPlacedFeatures.register("p_cedar",
            MBTreeFeatures.CEDAR, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.CEDAR_SAPLING));
	public static final Holder<PlacedFeature> HARDY_BUSH = MBPlacedFeatures.register("p_hardy_bush",
			MBTreeFeatures.HARDY_BUSH, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.HARDY_SPROUT));

    public static void init() {}
}
