package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Holder;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;
import net.paperfish.moonbits.registry.MBBlocks;

public class MBPlacedTreeFeatures {
//    public static final RegistryEntry<PlacedFeature> JUNIPER = MBPlacedFeatures.register("p_juniper",
//            MBTreeFeatures.JUNIPER, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
//    public static final RegistryEntry<PlacedFeature> JUNIPER_BUSH = MBPlacedFeatures.register("p_juniper_bush",
//            MBTreeFeatures.JUNIPER_BUSH, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
    public static final Holder<PlacedFeature> CEDAR = MBPlacedFeatures.register("p_cedar",
            MBTreeFeatures.CEDAR, PlacedFeatureUtil.createWouldSurvivePlacementModifier(MBBlocks.CEDAR_SAPLING));

    public static void init() {}
}
