package net.paperfish.moonbits.world.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.Moonbits;

public class MBPlacedTreeFeatures {
    public static final PlacedFeature GOLDEN_BIRCH;
    public static final PlacedFeature GOLDEN_BIRCH_BEES_002;
    public static final PlacedFeature GOLDEN_BIRCH_BEES_005;
    public static final PlacedFeature RED_OAK;
    public static final PlacedFeature RED_OAK_BEES_002;
    public static final PlacedFeature RED_OAK_BEES_005;
    public static final PlacedFeature JUNIPER;
    public static final PlacedFeature CEDAR;

    static {
        GOLDEN_BIRCH = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_golden_birch"),
                MBTreeFeatures.GOLDEN_BIRCH.withWouldSurviveFilter(MBBlocks.GOLDEN_BIRCH_SAPLING));
        GOLDEN_BIRCH_BEES_002 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_golden_birch_002"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002.withWouldSurviveFilter(MBBlocks.GOLDEN_BIRCH_SAPLING));
        GOLDEN_BIRCH_BEES_005 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_golden_birch_005"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_005.withWouldSurviveFilter(MBBlocks.GOLDEN_BIRCH_SAPLING));

        RED_OAK = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_red_oak"),
                MBTreeFeatures.GOLDEN_BIRCH.withWouldSurviveFilter(MBBlocks.RED_OAK_SAPLING));
        RED_OAK_BEES_002 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_red_oak_002"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002.withWouldSurviveFilter(MBBlocks.RED_OAK_SAPLING));
        RED_OAK_BEES_005 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_red_oak_005"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_005.withWouldSurviveFilter(MBBlocks.RED_OAK_SAPLING));

        JUNIPER = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_juniper"),
                MBTreeFeatures.JUNIPER.withWouldSurviveFilter(MBBlocks.JUNIPER_SAPLING));
        CEDAR = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(Moonbits.MOD_ID, "p_cedar"),
                MBTreeFeatures.CEDAR.withWouldSurviveFilter(MBBlocks.CEDAR_SAPLING));
    }
}
