package net.paperfish.moonbits.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.world.gen.DirtCarver;

import java.util.List;

public class MBCaveFeatures {
    public static final RuleTest BASE_STONE_OVERWORLD = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
    public static final RuleTest TOUGH_DIRT;
    public static final RuleTest COARSE_DIRT;
    public static final RuleTest REGOLITH;

    public static final LamprootFeature LAMPROOT_FEATURE;
    public static final CavebloomFeature CAVEBLOOM_FEATURE;

    public static final ConfiguredFeature<?, ?> T_REGOLITH;
    public static final ConfiguredFeature<?, ?> ORE_REGOLITH;

    public static final ConfiguredFeature<?, ?> ORE_PEAT;
    public static final ConfiguredFeature<?, ?> ORE_PEAT_HIGH;
    public static final ConfiguredFeature<?, ?> ORE_CLAY_DEPOSIT;
    public static final ConfiguredFeature<?, ?> LUSH_CLAY_DEPOSIT;
    public static final ConfiguredFeature<?, ?> ORE_GOLD_DEPOSIT;
    public static final ConfiguredFeature<?, ?> ORE_GOLD_HIGH;
    public static final ConfiguredFeature<?, ?> ORE_COPPER_DEPOSIT;

    //public static final ConfiguredFeature<ReplaceBlobsFeatureConfig, ?> TOUGH_GRASS;

    public static final RegistryKey<ConfiguredCarver<?>> dirt_cave = RegistryKey.of(Registry.CONFIGURED_CARVER_KEY, new Identifier(Moonbits.MOD_ID, "dirt_cave"));
    public static final Carver<CaveCarverConfig> CAVE = Registry.register(Registry.CARVER, "dirt_caves", new DirtCarver(CaveCarverConfig.CAVE_CODEC));
    public static final ConfiguredCarver<CaveCarverConfig> DIRT_CAVE =
            Registry.register(BuiltinRegistries.CONFIGURED_CARVER,
                    dirt_cave,
                    Carver.CAVE.configure(
                            new CaveCarverConfig(
                                    0.25f,
                                    UniformHeightProvider.create(YOffset.fixed(58), YOffset.fixed(148)),
                                    UniformFloatProvider.create(0.6f, 0.9f),
                                    YOffset.aboveBottom(0),
                                    CarverDebugConfig.create(false, MBBlocks.JACARANDA_BUTTON.getDefaultState()),
                                    UniformFloatProvider.create(1.2f, 1.8f),
                                    UniformFloatProvider.create(0.9f, 1.4f),
                                    UniformFloatProvider.create(-0.6f, 0.4f)
                            )
                    )
            );

    public static final ConfiguredFeature<SimpleBlockFeatureConfig, ?> TG_VEGETATION;
    public static final ConfiguredFeature<VegetationPatchFeatureConfig, ?> TOUGH_GRASS_PATCH;
    public static final ConfiguredFeature<VegetationPatchFeatureConfig, ?> TOUGH_GRASS_LUSH;
    public static final ConfiguredFeature<VegetationPatchFeatureConfig, ?> REGOLITH_FLOOR;
    public static final ConfiguredFeature<SimpleBlockFeatureConfig, ?> LAMPROOT;
    public static final ConfiguredFeature<CavebloomFeatureConfig, ?> CAVEBLOOMS;

    public static final ConfiguredFeature<VegetationPatchFeatureConfig, ?> MOSS_PATCH_DC;

    static {
        LAMPROOT_FEATURE = Registry.register(Registry.FEATURE, "lamproot_feature", new LamprootFeature(SimpleBlockFeatureConfig.CODEC));
        CAVEBLOOM_FEATURE = Registry.register(Registry.FEATURE, "cavebloom_feature", new CavebloomFeature(CavebloomFeatureConfig.CODEC));

        TOUGH_DIRT = new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT);
        COARSE_DIRT = new BlockMatchRuleTest(Blocks.COARSE_DIRT);
        REGOLITH = new BlockMatchRuleTest(MBBlocks.REGOLITH);
        List<OreFeatureConfig.Target> PEAT_GEN = List.of(
                OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.PEAT_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.PEAT_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(REGOLITH, MBBlocks.PEAT_DEPOSIT.getDefaultState()));
        List<OreFeatureConfig.Target> CLAY_GEN = List.of(
                OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.CLAY_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.CLAY_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(REGOLITH, MBBlocks.CLAY_DEPOSIT.getDefaultState()));
        List<OreFeatureConfig.Target> GOLD_GEN = List.of(
                OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.GOLD_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.GOLD_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(REGOLITH, MBBlocks.GOLD_DEPOSIT.getDefaultState()));
        List<OreFeatureConfig.Target> COPPER_GEN = List.of(
                OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.COPPER_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.COPPER_DEPOSIT.getDefaultState()),
                OreFeatureConfig.createTarget(REGOLITH, MBBlocks.COPPER_DEPOSIT.getDefaultState()));

        T_REGOLITH = ConfiguredFeatures.register("trans_regolith", Feature.ORE.configure(new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.REGOLITH.getDefaultState(), 64)));
        ORE_REGOLITH = ConfiguredFeatures.register("ore_regolith", Feature.ORE.configure(new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.REGOLITH.getDefaultState(), 64)));

        ORE_PEAT = ConfiguredFeatures.register("ore_peat", Feature.ORE.configure(new OreFeatureConfig(PEAT_GEN, 12, 0.1f)));
        ORE_PEAT_HIGH = ConfiguredFeatures.register("ore_peat_high", Feature.ORE.configure(new OreFeatureConfig(PEAT_GEN, 14, 0.0f)));
        ORE_CLAY_DEPOSIT = ConfiguredFeatures.register("ore_clay_deposit", Feature.ORE.configure(new OreFeatureConfig(CLAY_GEN, 8, 0.2f)));
        LUSH_CLAY_DEPOSIT = ConfiguredFeatures.register("lush_clay_deposit", Feature.ORE.configure(new OreFeatureConfig(CLAY_GEN, 10, 0.1f)));
        ORE_GOLD_DEPOSIT = ConfiguredFeatures.register("ore_gold_deposit", Feature.ORE.configure(new OreFeatureConfig(GOLD_GEN, 5, 0.5f)));
        ORE_GOLD_HIGH = ConfiguredFeatures.register("ore_gold_high", Feature.ORE.configure(new OreFeatureConfig(GOLD_GEN, 6, 0.4f)));
        ORE_COPPER_DEPOSIT = ConfiguredFeatures.register("ore_copper_deposit", Feature.ORE.configure(new OreFeatureConfig(COPPER_GEN, 5, 0.5f)));

        TG_VEGETATION = ConfiguredFeatures.register("tg_vegetation", Feature.SIMPLE_BLOCK.configure(
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                                .add(Blocks.AIR.getDefaultState(), 50)
                                .add(Blocks.GRASS.getDefaultState(), 20)
                                .add(Blocks.TALL_GRASS.getDefaultState(), 1)
                                .add(MBBlocks.FORGETMENOT.getDefaultState(), 4)
                                .add(MBBlocks.BUTTERCUP.getDefaultState(), 7)
                                .add(Blocks.ORANGE_TULIP.getDefaultState(), 8)
                                .add(Blocks.WHITE_TULIP.getDefaultState(), 12)
                                .add(Blocks.PINK_TULIP.getDefaultState(), 6)
                        )
                )
        ));
        TOUGH_GRASS_PATCH = ConfiguredFeatures.register("tg_patch", Feature.VEGETATION_PATCH.configure(
                new VegetationPatchFeatureConfig(
                        new Identifier(Moonbits.MOD_ID, "tough_soils"),
                        BlockStateProvider.of(MBBlocks.TOUGH_GRASS),
                        TG_VEGETATION::withPlacement,
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0f,
                        5,
                        0.4f,
                        UniformIntProvider.create(4, 7),
                        0.1f)
        ));
        TOUGH_GRASS_LUSH = ConfiguredFeatures.register("tg_lush", Feature.VEGETATION_PATCH.configure(
                new VegetationPatchFeatureConfig(
                        new Identifier(Moonbits.MOD_ID, "tough_soils"),
                        BlockStateProvider.of(MBBlocks.TOUGH_GRASS),
                        UndergroundConfiguredFeatures.MOSS_VEGETATION::withPlacement,
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0f,
                        5,
                        0.8f,
                        UniformIntProvider.create(4, 7),
                        0.3f)
        ));
        REGOLITH_FLOOR = ConfiguredFeatures.register("regolith_floor", Feature.VEGETATION_PATCH.configure(
                new VegetationPatchFeatureConfig(
                        new Identifier(Moonbits.MOD_ID, "tough_soils"),
                        BlockStateProvider.of(MBBlocks.REGOLITH),
                        TG_VEGETATION::withPlacement,
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.4f,
                        5,
                        0,
                        UniformIntProvider.create(4, 7),
                        0.3f)
        ));

        LAMPROOT = ConfiguredFeatures.register("lamproot", LAMPROOT_FEATURE.configure(new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.LAMPROOT))));
        CAVEBLOOMS = ConfiguredFeatures.register("caveblooms", CAVEBLOOM_FEATURE.configure(new CavebloomFeatureConfig(UniformIntProvider.create(18, 26), UniformFloatProvider.create(0.6f, 0.9f), UniformIntProvider.create(1, 3))));
        MOSS_PATCH_DC = ConfiguredFeatures.register("moss_patch_dc", Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(new Identifier(Moonbits.MOD_ID, "tough_soils"), BlockStateProvider.of(Blocks.MOSS_BLOCK), UndergroundConfiguredFeatures.CAVE_VINE_IN_MOSS::withPlacement, VerticalSurfaceType.CEILING, UniformIntProvider.create(1, 2), 0.0f, 5, 0.08f, UniformIntProvider.create(4, 7), 0.3f)));

    }
}
