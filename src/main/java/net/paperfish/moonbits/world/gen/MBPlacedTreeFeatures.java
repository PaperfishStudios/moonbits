package net.paperfish.moonbits.world.gen;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.paperfish.moonbits.MBBlocks;

public class MBPlacedTreeFeatures {
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH = MBPlacedFeatures.register("p_golden_birch",
            MBTreeFeatures.GOLDEN_BIRCH, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_002 = MBPlacedFeatures.register("p_golden_birch_002",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_002, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_005 = MBPlacedFeatures.register("p_golden_birch_005",
            MBTreeFeatures.GOLDEN_BIRCH_BEES_005, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));
    public static final RegistryEntry<PlacedFeature> RED_OAK = MBPlacedFeatures.register("p_red_oak",
            MBTreeFeatures.RED_OAK, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));
    public static final RegistryEntry<PlacedFeature> RED_OAK_BEES_002 = MBPlacedFeatures.register("p_red_oak_002",
            MBTreeFeatures.RED_OAK_BEES_002, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));
    public static final RegistryEntry<PlacedFeature> RED_OAK_BEES_005 = MBPlacedFeatures.register("p_red_oak_005",
            MBTreeFeatures.RED_OAK_BEES_005, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));
    public static final RegistryEntry<PlacedFeature> JUNIPER = MBPlacedFeatures.register("p_juniper",
            MBTreeFeatures.JUNIPER, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
    public static final RegistryEntry<PlacedFeature> CEDAR = MBPlacedFeatures.register("p_cedar",
            MBTreeFeatures.CEDAR, PlacedFeatures.wouldSurvive(MBBlocks.CEDAR_SAPLING));

    public static void init() {}
}
