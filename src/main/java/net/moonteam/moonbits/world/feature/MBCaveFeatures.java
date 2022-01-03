package net.moonteam.moonbits.world.feature;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.MBData;

import java.util.List;

public class MBCaveFeatures {
    public static final RuleTest BASE_STONE_OVERWORLD = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
    public static final RuleTest TOUGH_DIRT;
    public static final RuleTest COARSE_DIRT;
    public static final RuleTest REGOLITH;

    public static final ConfiguredFeature<?, ?> T_REGOLITH;
    public static final ConfiguredFeature<?, ?> ORE_REGOLITH;

    public static final ConfiguredFeature<?, ?> ORE_PEAT;
    public static final ConfiguredFeature<?, ?> ORE_CLAY_DEPOSIT;
    public static final ConfiguredFeature<?, ?> ORE_GOLD_DEPOSIT;
    public static final ConfiguredFeature<?, ?> ORE_COPPER_DEPOSIT;

    public static final ConfiguredFeature<ReplaceBlobsFeatureConfig, ?> TOUGH_GRASS;


    static {
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

        ORE_PEAT = ConfiguredFeatures.register("ore_peat", Feature.ORE.configure(new OreFeatureConfig(PEAT_GEN, 12, 0.3f)));
        ORE_CLAY_DEPOSIT = ConfiguredFeatures.register("ore_clay_deposit", Feature.ORE.configure(new OreFeatureConfig(CLAY_GEN, 10, 0.3f)));
        ORE_GOLD_DEPOSIT = ConfiguredFeatures.register("ore_gold_deposit", Feature.ORE.configure(new OreFeatureConfig(GOLD_GEN, 6, 0.5f)));
        ORE_COPPER_DEPOSIT = ConfiguredFeatures.register("ore_copper_deposit", Feature.ORE.configure(new OreFeatureConfig(COPPER_GEN, 8, 0.7f)));

        TOUGH_GRASS = ConfiguredFeatures.register("tg_patch", Feature.NETHERRACK_REPLACE_BLOBS.configure(
                new ReplaceBlobsFeatureConfig(
                        MBBlocks.TOUGH_DIRT.getDefaultState(),
                        MBBlocks.TOUGH_GRASS.getDefaultState(),
                        UniformIntProvider.create(2, 5)
                )));
    }
}
