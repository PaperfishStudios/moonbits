package net.paperfish.moonbits.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.SurfaceRules;
import net.paperfish.moonbits.registry.MBBlocks;
import terrablender.worldgen.TBSurfaceRuleData;

public class MBSurfaceRules {
    private static final SurfaceRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static final SurfaceRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);

    private static final SurfaceRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
    private static final SurfaceRules.MaterialRule REGOLITH = makeStateRule(MBBlocks.REGOLITH);
    private static final SurfaceRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
    private static final SurfaceRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    private static final SurfaceRules.MaterialRule PERMAFROST = makeStateRule(MBBlocks.PERMAFROST);
    private static final SurfaceRules.MaterialRule SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);

//    public static final SurfaceRules.MaterialRule OVERWORLD_RULES = makeRules();

//    private static final SurfaceRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
//    private static final SurfaceRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
//    private static final SurfaceRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    public static SurfaceRules.MaterialRule makeRules() {
        SurfaceRules.MaterialCondition shallowDC = SurfaceRules.biome(
            BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.BEACH
        );
        SurfaceRules.MaterialCondition midDC = SurfaceRules.biome(
                BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS,
                BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
                BiomeKeys.MUSHROOM_FIELDS,
                MBBiomes.PASTURE
        );
        SurfaceRules.MaterialCondition deepDC = SurfaceRules.biome(
                BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE,
                BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.WINDSWEPT_FOREST,
                BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.DARK_FOREST,
                MBBiomes.GOLDEN_FOREST, MBBiomes.TALL_GOLDEN_FOREST, MBBiomes.RED_OAK_FOREST
        );

        SurfaceRules.MaterialCondition isAtOrAboveWaterLevel = SurfaceRules.water(-1, 0);

        SurfaceRules.MaterialCondition shallowDCDepth = SurfaceRules.aboveY(YOffset.fixed(50), 0);
        SurfaceRules.MaterialCondition midDCDepth = SurfaceRules.aboveY(YOffset.fixed(42), 0);
        SurfaceRules.MaterialCondition deepDCDepth = SurfaceRules.aboveY(YOffset.fixed(35), 0);

        SurfaceRules.MaterialRule grassSurface = SurfaceRules.sequence(SurfaceRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        SurfaceRules.MaterialRule toughSurface = SurfaceRules.sequence(SurfaceRules.condition(isAtOrAboveWaterLevel, TOUGH_GRASS), TOUGH_DIRT);
        SurfaceRules.MaterialRule sandySurface = SurfaceRules.sequence(SurfaceRules.condition(SurfaceRules.ON_CEILING, SANDSTONE), SAND);

        return SurfaceRules.sequence(
                TBSurfaceRuleData.overworld(),
                SurfaceRules.condition(SurfaceRules.biome(BiomeKeys.DESERT), CHERT),
//                SurfaceRules.condition(SurfaceRules.biome(MBBiomes.PRAIRIE), SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, toughSurface)),

//                SurfaceRules.condition(SurfaceRules.surface(), SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, grassSurface)),

                // dirt cave stuff atm
                SurfaceRules.condition(SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
//                                SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, grassSurface),
//                                SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, DIRT),

                                SurfaceRules.condition(shallowDC, // rules for shallow dirt cave biomes
                                        SurfaceRules.sequence(
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(8, true, 3, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(12, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                ),
                                SurfaceRules.condition(midDC, // rules for mid dirt cave biomes
                                        SurfaceRules.sequence(
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(16, true, 4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(18, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                ),
                                SurfaceRules.condition(deepDC, // rules for deep dirt cave biomes
                                        SurfaceRules.sequence(
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(26, true, 4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                SurfaceRules.condition(SurfaceRules.stoneDepth(28, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                )
                        )
                )
        );
    }

    private static SurfaceRules.MaterialRule makeStateRule(Block block)
    {
        return SurfaceRules.block(block.getDefaultState());
    }
}
