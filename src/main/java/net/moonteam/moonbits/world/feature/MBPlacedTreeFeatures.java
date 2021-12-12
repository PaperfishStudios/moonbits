package net.moonteam.moonbits.world.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.MoonbitsMain;

public class MBPlacedTreeFeatures {
    public static final PlacedFeature GOLDEN_BIRCH;
    public static final PlacedFeature GOLDEN_BIRCH_BEES_002;
    public static final PlacedFeature GOLDEN_BIRCH_BEES_005;
    public static final PlacedFeature JACARANDA;
    public static final PlacedFeature JACARANDA_BEES_002;
    public static final PlacedFeature JACARANDA_BEES_005;

    static {
        GOLDEN_BIRCH = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_golden_birch"),
                MBTreeFeatures.GOLDEN_BIRCH.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));
        GOLDEN_BIRCH_BEES_002 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_golden_birch_002"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_002.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));
        GOLDEN_BIRCH_BEES_005 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_golden_birch_005"),
                MBTreeFeatures.GOLDEN_BIRCH_BEES_005.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));

        JACARANDA = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_jacaranda"),
                MBTreeFeatures.JACARANDA.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));
        JACARANDA_BEES_002 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_jacaranda_002"),
                MBTreeFeatures.JACARANDA_BEES_002.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));
        JACARANDA_BEES_005 = Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(MoonbitsMain.MOD_ID, "p_jacaranda_005"),
                MBTreeFeatures.JACARANDA_BEES_005.withWouldSurviveFilter(MBBlocks.JACARANDA_SAPLING));
    }
}
