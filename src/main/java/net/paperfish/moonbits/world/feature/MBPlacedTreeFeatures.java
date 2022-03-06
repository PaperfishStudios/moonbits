package net.paperfish.moonbits.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.Moonbits;

import java.util.List;

public class MBPlacedTreeFeatures {
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH;
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_002;
    public static final RegistryEntry<PlacedFeature> GOLDEN_BIRCH_BEES_005;
    public static final RegistryEntry<PlacedFeature> RED_OAK;
    public static final RegistryEntry<PlacedFeature> RED_OAK_BEES_002;
    public static final RegistryEntry<PlacedFeature> RED_OAK_BEES_005;
    public static final RegistryEntry<PlacedFeature> JUNIPER;
    public static final RegistryEntry<PlacedFeature> CEDAR;

    static {
        GOLDEN_BIRCH = PlacedFeatures.register("p_golden_birch",
                MBTreeFeatures.GOLDEN_BIRCH, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));
        GOLDEN_BIRCH_BEES_002 = PlacedFeatures.register("p_golden_birch_002",
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));
        GOLDEN_BIRCH_BEES_005 = PlacedFeatures.register("p_golden_birch_005",
                MBTreeFeatures.GOLDEN_BIRCH_BEES_005, PlacedFeatures.wouldSurvive(MBBlocks.GOLDEN_BIRCH_SAPLING));

        RED_OAK = PlacedFeatures.register("p_red_oak",
                MBTreeFeatures.GOLDEN_BIRCH, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));
        RED_OAK_BEES_002 = PlacedFeatures.register("p_red_oak_002",
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));
        RED_OAK_BEES_005 = PlacedFeatures.register("p_red_oak_005",
                MBTreeFeatures.GOLDEN_BIRCH_BEES_005, PlacedFeatures.wouldSurvive(MBBlocks.RED_OAK_SAPLING));

        JUNIPER = PlacedFeatures.register("p_juniper",
                MBTreeFeatures.GOLDEN_BIRCH, PlacedFeatures.wouldSurvive(MBBlocks.JUNIPER_SAPLING));
        CEDAR = PlacedFeatures.register("p_cedar",
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002, PlacedFeatures.wouldSurvive(MBBlocks.CEDAR_SAPLING));
    }

    public static void init() {}
}
