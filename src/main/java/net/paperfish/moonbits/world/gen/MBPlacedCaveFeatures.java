package net.paperfish.moonbits.world.gen;

import net.minecraft.util.Holder;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacementModifier;
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil;

import java.util.List;

public class MBPlacedCaveFeatures {
    // regolith transition
    public static final Holder<PlacedFeature> T_REGOLITH = MBPlacedFeatures.register("t_regolith",
            MBCaveFeatures.T_REGOLITH, rareOrePlacementModifiers(8, HeightRangePlacementModifier.createUniform(YOffset.fixed(42), YOffset.fixed(50))));

    // regolith clumps
    public static final Holder<PlacedFeature> ORE_REGOLITH = MBPlacedFeatures.register("p_regolith",
            MBCaveFeatures.ORE_REGOLITH, commonOrePlacementModifiers(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(60))));

	public static final Holder<PlacedFeature> ORE_SAND = PlacedFeatureUtil.register(
			"mb_ore_sand", MBCaveFeatures.ORE_SAND, commonOrePlacementModifiers(7, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(160)))
	);

    public static final Holder<PlacedFeature> ORE_SANDSTONE = MBPlacedFeatures.register("p_sandstone",
            MBCaveFeatures.ORE_SANDSTONE, commonOrePlacementModifiers(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(60))));

    public static final Holder<PlacedFeature> ORE_COBBLECHERT = MBPlacedFeatures.register("p_cobblechert",
            MBCaveFeatures.ORE_COBBLECHERT, commonOrePlacementModifiers(2, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(60))));

    // deposits
    public static final Holder<PlacedFeature> ORE_PEAT = MBPlacedFeatures.register("p_peat",
            MBCaveFeatures.ORE_PEAT, commonOrePlacementModifiers(32, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> ORE_PEAT_HIGH = MBPlacedFeatures.register("p_peat_swamp",
            MBCaveFeatures.ORE_PEAT_HIGH, commonOrePlacementModifiers(36, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));
	public static final Holder<PlacedFeature> ORE_FLINT_DEPOSIT = MBPlacedFeatures.register("p_flint",
			MBCaveFeatures.ORE_FLINT_DEPOSIT, commonOrePlacementModifiers(48, HeightRangePlacementModifier.createUniform(YOffset.fixed(50), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> ORE_ROOTED_SOIL = MBPlacedFeatures.register("p_rooted",
            MBCaveFeatures.ORE_ROOTED_SOIL, commonOrePlacementModifiers(32, HeightRangePlacementModifier.createUniform(YOffset.fixed(55), YOffset.fixed(70))));
    public static final Holder<PlacedFeature> ORE_ROOTED_UPPER = MBPlacedFeatures.register("p_rooted_upper",
            MBCaveFeatures.ORE_ROOTED_SOIL, commonOrePlacementModifiers(26, HeightRangePlacementModifier.createUniform(YOffset.fixed(70), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> LUSH_ROOTED_SOIL = MBPlacedFeatures.register("p_lush_rooted",
            MBCaveFeatures.LUSH_ROOTED_SOIL, commonOrePlacementModifiers(34, HeightRangePlacementModifier.createUniform(YOffset.fixed(53), YOffset.fixed(78))));
    public static final Holder<PlacedFeature> ORE_FUZZ = MBPlacedFeatures.register("p_fuzz",
            MBCaveFeatures.ORE_MYCELIAL_DIRT, commonOrePlacementModifiers(32, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));
    public static final Holder<PlacedFeature> ORE_FUZZ_HIGH = MBPlacedFeatures.register("p_fuzz_high",
            MBCaveFeatures.ORE_MYCELIAL_DIRT, commonOrePlacementModifiers(34, HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.fixed(128))));

    // chert ores
    public static final Holder<PlacedFeature> CHERT_COAL_LOWER = MBPlacedFeatures.register(
            "chert_coal_low", MBCaveFeatures.ORE_CHERT_COAL_BURIED, commonOrePlacementModifiers(20, HeightRangePlacementModifier.trapezoid(YOffset.fixed(0), YOffset.fixed(192))));
    public static final Holder<PlacedFeature> CHERT_GOLD_EXTRA = MBPlacedFeatures.register(
            "chert_gold_ex", MBCaveFeatures.ORE_CHERT_GOLD, commonOrePlacementModifiers(50, HeightRangePlacementModifier.createUniform(YOffset.fixed(32), YOffset.fixed(256))));
    public static final Holder<PlacedFeature> CHERT_GOLD = MBPlacedFeatures.register(
            "chert_gold", MBCaveFeatures.ORE_CHERT_GOLD_BURIED, commonOrePlacementModifiers(4, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-64), YOffset.fixed(32))));
    public static final Holder<PlacedFeature> CHERT_GOLD_LOWER = MBPlacedFeatures.register(
            "chert_gold_low", MBCaveFeatures.ORE_CHERT_GOLD_BURIED, orePlacementModifiers(CountPlacementModifier.create(UniformIntProvider.create(0, 1)), HeightRangePlacementModifier.createUniform(YOffset.fixed(-64), YOffset.fixed(48))));
    public static final Holder<PlacedFeature> CHERT_REDSTONE = MBPlacedFeatures.register(
            "chert_redstone", MBCaveFeatures.ORE_CHERT_REDSTONE, commonOrePlacementModifiers(6, HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(48))));
    public static final Holder<PlacedFeature> CHERT_REDSTONE_LOWER = MBPlacedFeatures.register(
            "chert_redstone_lower", MBCaveFeatures.ORE_CHERT_REDSTONE, commonOrePlacementModifiers(12, HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-32), YOffset.aboveBottom(80))));
    public static final Holder<PlacedFeature> CHERT_LAPIS = MBPlacedFeatures.register(
            "chert_lapis", MBCaveFeatures.ORE_CHERT_LAPIS, commonOrePlacementModifiers(2, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
    public static final Holder<PlacedFeature> CHERT_LAPIS_BURIED = MBPlacedFeatures.register(
            "chert_lapis_buried", MBCaveFeatures.ORE_CHERT_LAPIS_BURIED, commonOrePlacementModifiers(4, HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(64))));
    public static final Holder<PlacedFeature> CHERT_COPPER = MBPlacedFeatures.register(
            "chert_copper", MBCaveFeatures.ORE_CHERT_COPPER_SMALL, commonOrePlacementModifiers(16, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(112))));
    public static final Holder<PlacedFeature> CHERT_COPPER_LARGE = MBPlacedFeatures.register(
            "chert_copper_large", MBCaveFeatures.ORE_CHERT_COPPER_LARGE, commonOrePlacementModifiers(16, HeightRangePlacementModifier.trapezoid(YOffset.fixed(-16), YOffset.fixed(112))));


    // regolith floor
    public static final Holder<PlacedFeature> REGOLITH_FLOOR = MBPlacedFeatures.register("p_rg_floor",
            MBCaveFeatures.REGOLITH_FLOOR, CountPlacementModifier.create(125), InSquarePlacementModifier.getInstance(), HeightRangePlacementModifier.createUniform(YOffset.fixed(40), YOffset.fixed(52)));

    // tough grass patch
    public static final Holder<PlacedFeature> TOUGH_GRASS_PATCH = MBPlacedFeatures.register("p_tg_patch",
            MBCaveFeatures.SUBSTRATE_PATCH, CountPlacementModifier.create(4), InSquarePlacementModifier.getInstance(),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(52), YOffset.fixed(110)));
    public static final Holder<PlacedFeature> TOUGH_GRASS_FLOOR = MBPlacedFeatures.register("p_tg_floor",
            MBCaveFeatures.TOUGH_GRASS_PATCH, CountPlacementModifier.create(8), InSquarePlacementModifier.getInstance(),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(52), YOffset.fixed(110)));
    public static final Holder<PlacedFeature> TOUGH_GRASS_LUSH = MBPlacedFeatures.register("p_tg_lush",
            MBCaveFeatures.TOUGH_GRASS_LUSH, CountPlacementModifier.create(32), InSquarePlacementModifier.getInstance(),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(48), YOffset.fixed(58)));

    // cave vegetation
    public static final Holder<PlacedFeature> TG_VEGETATION = MBPlacedFeatures.register("p_tg_veg",
            MBCaveFeatures.TG_VEGETATION, CountPlacementModifier.create(2), InSquarePlacementModifier.getInstance(),
			PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE);
    public static final Holder<PlacedFeature> LAMPROOT = MBPlacedFeatures.register("p_lamproot",
            MBCaveFeatures.LAMPROOT, CountPlacementModifier.create(64), InSquarePlacementModifier.getInstance(),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(32), YOffset.fixed(90)),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)));
    public static final Holder<PlacedFeature> CAVEBLOOMS = MBPlacedFeatures.register("p_caveblooms",
            MBCaveFeatures.CAVEBLOOMS, CountPlacementModifier.create(UniformIntProvider.create(32, 48)),
			HeightRangePlacementModifier.createUniform(YOffset.fixed(45), YOffset.fixed(65)),
            InSquarePlacementModifier.getInstance(),
			SurfaceRelativeThresholdFilterPlacementModifier.create(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
			BiomePlacementModifier.getInstance());

	public static final Holder<PlacedFeature> DESERT_VASES = MBPlacedFeatures.register("p_desert_vases",
			MBVegetationFeatures.DESERT_VASE_PATCH, CountPlacementModifier.create(UniformIntProvider.create(16, 32)),
			HeightRangePlacementModifier.trapezoid(YOffset.fixed(12), YOffset.fixed(75)),
			InSquarePlacementModifier.getInstance(),
			SurfaceRelativeThresholdFilterPlacementModifier.create(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13),
			BiomePlacementModifier.getInstance());

    public static void init() {}

    private static List<PlacementModifier> orePlacementModifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacementModifier.getInstance(), heightModifier, BiomePlacementModifier.getInstance());
    }

    static List<PlacementModifier> commonOrePlacementModifiers(int count, PlacementModifier heightModfier) {
        return orePlacementModifiers(CountPlacementModifier.create(count), heightModfier);
    }

    private static List<PlacementModifier> rareOrePlacementModifiers(int chance, PlacementModifier heightModifier) {
        return orePlacementModifiers(RarityFilterPlacementModifier.create(chance), heightModifier);
    }
}
