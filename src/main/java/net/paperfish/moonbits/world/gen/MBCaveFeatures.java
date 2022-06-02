package net.paperfish.moonbits.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.paperfish.moonbits.registry.MBBlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.world.feature.CavebloomFeature;
import net.paperfish.moonbits.world.feature.CavebloomFeatureConfig;
import net.paperfish.moonbits.world.feature.LamprootFeature;

import java.util.List;

public class MBCaveFeatures {
    public static final RuleTest BASE_STONE_OVERWORLD = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
    public static final RuleTest CHERT = new BlockMatchRuleTest(MBBlocks.CHERT);
    public static final RuleTest TOUGH_DIRT = new BlockMatchRuleTest(MBBlocks.TOUGH_DIRT);
    public static final RuleTest COARSE_DIRT = new BlockMatchRuleTest(Blocks.COARSE_DIRT);
    public static final RuleTest REGOLITH = new BlockMatchRuleTest(MBBlocks.REGOLITH);
    public static final RuleTest PERMAFROST = new BlockMatchRuleTest(MBBlocks.PERMAFROST);

    public static final LamprootFeature LAMPROOT_FEATURE =
            Registry.register(Registry.FEATURE, "lamproot_feature", new LamprootFeature(SimpleBlockFeatureConfig.CODEC));
    public static final CavebloomFeature CAVEBLOOM_FEATURE =
            Registry.register(Registry.FEATURE, "cavebloom_feature", new CavebloomFeature(CavebloomFeatureConfig.CODEC));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> T_REGOLITH =
            MBConfiguredFeatures.register("trans_regolith", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.REGOLITH.getDefaultState(), 32));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_REGOLITH =
            MBConfiguredFeatures.register("ore_regolith", Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.REGOLITH.getDefaultState(), 32));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_SANDSTONE = MBConfiguredFeatures.register("or_sanston",
            Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, Blocks.SANDSTONE.getDefaultState(), 48));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COBBLECHERT = MBConfiguredFeatures.register("or_cobblechert",
            Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.COBBLED_CHERT.getDefaultState(), 48));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TILL = MBConfiguredFeatures.register("ore_till",
            Feature.ORE, new OreFeatureConfig(BASE_STONE_OVERWORLD, MBBlocks.TILL.getDefaultState(), 48));

    public static final List<OreFeatureConfig.Target> PEAT_GEN = List.of(
            OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.PEAT_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.PEAT_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(REGOLITH, MBBlocks.PEAT_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(PERMAFROST, MBBlocks.FROST_PEAT.getDefaultState()));
    public static final List<OreFeatureConfig.Target> CLAY_GEN = List.of(
            OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.CLAY_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.CLAY_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(REGOLITH, MBBlocks.CLAY_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(PERMAFROST, MBBlocks.FROST_CLAY.getDefaultState()));
    public static final List<OreFeatureConfig.Target> GOLD_GEN = List.of(
            OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.GOLD_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.GOLD_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(REGOLITH, MBBlocks.GOLD_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(PERMAFROST, MBBlocks.FROST_GOLD.getDefaultState()));
    public static final List<OreFeatureConfig.Target> COPPER_GEN = List.of(
            OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.COPPER_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.COPPER_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(REGOLITH, MBBlocks.COPPER_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(PERMAFROST, MBBlocks.FROST_COPPER.getDefaultState()));
    public static final List<OreFeatureConfig.Target> TIN_GEN = List.of(
            OreFeatureConfig.createTarget(TOUGH_DIRT, MBBlocks.TIN_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(COARSE_DIRT, MBBlocks.TIN_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(REGOLITH, MBBlocks.TIN_DEPOSIT.getDefaultState()),
            OreFeatureConfig.createTarget(PERMAFROST, MBBlocks.FROST_TIN_DEPOSIT.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PEAT =
            MBConfiguredFeatures.register("ore_peat", Feature.ORE, new OreFeatureConfig(PEAT_GEN, 12, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_PEAT_HIGH =
            MBConfiguredFeatures.register("ore_peat_high", Feature.ORE, new OreFeatureConfig(PEAT_GEN, 14, 0.0f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CLAY_DEPOSIT =
            MBConfiguredFeatures.register("ore_clay_deposit", Feature.ORE, new OreFeatureConfig(CLAY_GEN, 8, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> LUSH_CLAY_DEPOSIT =
            MBConfiguredFeatures.register("lush_clay_deposit", Feature.ORE, new OreFeatureConfig(CLAY_GEN, 10, 0.0f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOLD_DEPOSIT =
            MBConfiguredFeatures.register("ore_gold_deposit", Feature.ORE, new OreFeatureConfig(GOLD_GEN, 5, 0.3f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_GOLD_HIGH =
            MBConfiguredFeatures.register("ore_gold_dep_high", Feature.ORE, new OreFeatureConfig(GOLD_GEN, 6, 0.2f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_COPPER_DEPOSIT =
            MBConfiguredFeatures.register("ore_copper_deposit", Feature.ORE, new OreFeatureConfig(COPPER_GEN, 5, 0.3f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TIN_DEPOSIT =
            MBConfiguredFeatures.register("ore_tin_deposit", Feature.ORE, new OreFeatureConfig(TIN_GEN, 6, 0.2f));

    public static final RuleTest CHERT_REPLACE = new BlockMatchRuleTest(MBBlocks.CHERT);
    public static final RuleTest DEEPSLATE = new BlockMatchRuleTest(Blocks.DEEPSLATE);

    public static final List<OreFeatureConfig.Target> CHERT_GOLD = List.of(
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_GOLD_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> CHERT_LAPIS = List.of(
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_LAPIS_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> CHERT_COPPER = List.of(
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_COPPER_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> CHERT_COAL = List.of(
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_COAL_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> CHERT_REDSTONE = List.of(
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_REDSTONE_ORE.getDefaultState()));
    public static final List<OreFeatureConfig.Target> TIN_ORE_GEN = List.of(
            OreFeatureConfig.createTarget(BASE_STONE_OVERWORLD, MBBlocks.TIN_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(CHERT_REPLACE, MBBlocks.CHERT_TIN_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(DEEPSLATE, MBBlocks.DEEPSLATE_TIN_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_TIN = MBConfiguredFeatures.register(
            "ore_tin", Feature.ORE, new OreFeatureConfig(TIN_ORE_GEN, 12, 0.1f));

//    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_COAL = MBConfiguredFeatures.register(
//            "ore_chert_coal", Feature.ORE, new OreFeatureConfig(CHERT_COAL, 17, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_COAL_BURIED = MBConfiguredFeatures.register(
            "ore_chert_coal_buried", Feature.ORE, new OreFeatureConfig(CHERT_COAL, 17, 0.6f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_GOLD = MBConfiguredFeatures.register(
            "ore_chert_gold", Feature.ORE, new OreFeatureConfig(CHERT_GOLD, 9, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_GOLD_BURIED = MBConfiguredFeatures.register(
            "ore_chert_gold_buried", Feature.ORE, new OreFeatureConfig(CHERT_GOLD, 9, 0.6f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_REDSTONE = MBConfiguredFeatures.register(
            "ore_chert_redstone", Feature.ORE, new OreFeatureConfig(CHERT_REDSTONE, 8, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_LAPIS = MBConfiguredFeatures.register(
            "ore_chert_lapis", Feature.ORE, new OreFeatureConfig(CHERT_LAPIS, 7, 0.1f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_LAPIS_BURIED = MBConfiguredFeatures.register(
            "ore_chert_lapis_buried", Feature.ORE, new OreFeatureConfig(CHERT_LAPIS, 7, 1.0f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_COPPER_SMALL = MBConfiguredFeatures.register(
            "ore_chert_copper_small", Feature.ORE, new OreFeatureConfig(CHERT_COPPER, 10, 0.2f));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> ORE_CHERT_COPPER_LARGE = MBConfiguredFeatures.register(
            "ore_chert_copper_large", Feature.ORE, new OreFeatureConfig(CHERT_COPPER, 20, 0.9f));

    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> TG_VEGETATION = MBConfiguredFeatures.register("tg_vegetation", Feature.SIMPLE_BLOCK,
            new SimpleBlockFeatureConfig(
                    new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(Blocks.AIR.getDefaultState(), 70)
                            .add(Blocks.GRASS.getDefaultState(), 20)
                            .add(Blocks.TALL_GRASS.getDefaultState(), 6)
//                            .add(MBBlocks.FORGETMENOT.getDefaultState(), 4)
                    )
            )
    );
    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> SUBSTRATE_VEG = MBConfiguredFeatures.register("sub_vegetation", Feature.SIMPLE_BLOCK,
            new SimpleBlockFeatureConfig(
                    new WeightedBlockStateProvider(new DataPool.Builder<BlockState>()
                            .add(Blocks.AIR.getDefaultState(), 70)
                            .add(Blocks.GRASS.getDefaultState(), 10)
                            .add(MBBlocks.WILDFLOWERS.getDefaultState(), 4)
                            .add(MBBlocks.CLOVER.getDefaultState(), 4)
                    )
            )
    );
    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> TOUGH_GRASS_PATCH =
            MBConfiguredFeatures.register("tg_patch", Feature.VEGETATION_PATCH,
            new VegetationPatchFeatureConfig(
                    MBBlockTags.TOUGH_DIRT,
                    BlockStateProvider.of(MBBlocks.TOUGH_GRASS),
                    PlacedFeatures.createEntry(TG_VEGETATION),
                    VerticalSurfaceType.FLOOR,
                    ConstantIntProvider.create(1),
                    0.0f,
                    5,
                    0.4f,
                    UniformIntProvider.create(3, 6),
                    0.1f)
    );
    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> SUBSTRATE_PATCH =
            MBConfiguredFeatures.register("sub_patch", Feature.VEGETATION_PATCH,
                    new VegetationPatchFeatureConfig(
                            MBBlockTags.TOUGH_DIRT,
                            BlockStateProvider.of(MBBlocks.SUBSTRATE),
                            PlacedFeatures.createEntry(SUBSTRATE_VEG),
                            VerticalSurfaceType.FLOOR,
                            ConstantIntProvider.create(1),
                            0.0f,
                            5,
                            0.4f,
                            UniformIntProvider.create(3, 6),
                            0.1f)
            );
    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> TOUGH_GRASS_LUSH =
            MBConfiguredFeatures.register("tg_lush", Feature.VEGETATION_PATCH,
            new VegetationPatchFeatureConfig(
                    MBBlockTags.TOUGH_DIRT,
                    BlockStateProvider.of(MBBlocks.TOUGH_GRASS),
                    PlacedFeatures.createEntry(UndergroundConfiguredFeatures.MOSS_VEGETATION),
                    VerticalSurfaceType.FLOOR,
                    ConstantIntProvider.create(1),
                    0.0f,
                    5,
                    0.8f,
                    UniformIntProvider.create(4, 7),
                    0.3f)
    );
    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> REGOLITH_FLOOR =
            MBConfiguredFeatures.register("regolith_floor", Feature.VEGETATION_PATCH,
            new VegetationPatchFeatureConfig(
                    MBBlockTags.TOUGH_DIRT,
                    BlockStateProvider.of(MBBlocks.REGOLITH),
                    PlacedFeatures.createEntry(TG_VEGETATION),
                    VerticalSurfaceType.FLOOR,
                    ConstantIntProvider.create(1),
                    0.4f,
                    5,
                    0,
                    UniformIntProvider.create(4, 7),
                    0.3f)
    );

    public static final RegistryEntry<ConfiguredFeature<SimpleBlockFeatureConfig, ?>> LAMPROOT =
            MBConfiguredFeatures.register("lamproot", LAMPROOT_FEATURE, new SimpleBlockFeatureConfig(BlockStateProvider.of(MBBlocks.LAMPROOT)));
    public static final RegistryEntry<ConfiguredFeature<CavebloomFeatureConfig, ?>> CAVEBLOOMS =
            MBConfiguredFeatures.register("caveblooms", CAVEBLOOM_FEATURE,
                    new CavebloomFeatureConfig(UniformIntProvider.create(18, 26), UniformFloatProvider.create(0.6f, 0.9f), UniformIntProvider.create(1, 3)));
//    public static final RegistryEntry<ConfiguredFeature<VegetationPatchFeatureConfig, ?>> MOSS_PATCH_DC =
//            MBConfiguredFeatures.register("moss_patch_dc", Feature.VEGETATION_PATCH,
//                    new VegetationPatchFeatureConfig(MBBlockTags.TOUGH_DIRT, BlockStateProvider.of(Blocks.MOSS_BLOCK),
//                            PlacedFeatures.createEntry(UndergroundConfiguredFeatures.CAVE_VINE_IN_MOSS), VerticalSurfaceType.CEILING,
//                            UniformIntProvider.create(1, 2), 0.0f, 5, 0.08f,
//                            UniformIntProvider.create(4, 7), 0.3f));

}
