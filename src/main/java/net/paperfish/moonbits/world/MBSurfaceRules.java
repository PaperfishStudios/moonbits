package net.paperfish.moonbits.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.paperfish.moonbits.registry.MBBlocks;
import terrablender.worldgen.TBSurfaceRuleData;

public class MBSurfaceRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);

    private static final MaterialRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
    private static final MaterialRules.MaterialRule REGOLITH = makeStateRule(MBBlocks.REGOLITH);
    private static final MaterialRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
    private static final MaterialRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    private static final MaterialRules.MaterialRule PERMAFROST = makeStateRule(MBBlocks.PERMAFROST);
    private static final MaterialRules.MaterialRule SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);

//    public static final MaterialRules.MaterialRule OVERWORLD_RULES = makeRules();

//    private static final MaterialRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
//    private static final MaterialRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
//    private static final MaterialRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition shallowDC = MaterialRules.biome(
            BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.BEACH
        );
        MaterialRules.MaterialCondition midDC = MaterialRules.biome(
                BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS,
                BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
                BiomeKeys.MUSHROOM_FIELDS,
                MBBiomes.PASTURE
        );
        MaterialRules.MaterialCondition deepDC = MaterialRules.biome(
                BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.SPARSE_JUNGLE,
                BiomeKeys.FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.WINDSWEPT_FOREST,
                BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.DARK_FOREST,
                MBBiomes.GOLDEN_FOREST, MBBiomes.TALL_GOLDEN_FOREST, MBBiomes.RED_OAK_FOREST
        );

        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialCondition shallowDCDepth = MaterialRules.aboveY(YOffset.fixed(50), 0);
        MaterialRules.MaterialCondition midDCDepth = MaterialRules.aboveY(YOffset.fixed(42), 0);
        MaterialRules.MaterialCondition deepDCDepth = MaterialRules.aboveY(YOffset.fixed(35), 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        MaterialRules.MaterialRule toughSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, TOUGH_GRASS), TOUGH_DIRT);
        MaterialRules.MaterialRule sandySurface = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDSTONE), SAND);

        return MaterialRules.sequence(
                TBSurfaceRuleData.overworld(),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.DESERT), CHERT),
//                MaterialRules.condition(MaterialRules.biome(MBBiomes.PRAIRIE), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, toughSurface)),

//                MaterialRules.condition(MaterialRules.surface(), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)),

                // dirt cave stuff atm
                MaterialRules.condition(MaterialRules.surface(),
                        MaterialRules.sequence(
//                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface),
//                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, DIRT),

                                MaterialRules.condition(shallowDC, // rules for shallow dirt cave biomes
                                        MaterialRules.sequence(
                                                MaterialRules.condition(MaterialRules.stoneDepth(8, true, 3, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                MaterialRules.condition(MaterialRules.stoneDepth(12, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                ),
                                MaterialRules.condition(midDC, // rules for mid dirt cave biomes
                                        MaterialRules.sequence(
                                                MaterialRules.condition(MaterialRules.stoneDepth(16, true, 4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                MaterialRules.condition(MaterialRules.stoneDepth(18, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                ),
                                MaterialRules.condition(deepDC, // rules for deep dirt cave biomes
                                        MaterialRules.sequence(
                                                MaterialRules.condition(MaterialRules.stoneDepth(26, true, 4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
                                                MaterialRules.condition(MaterialRules.stoneDepth(28, true, 2, VerticalSurfaceType.FLOOR), REGOLITH)
                                        )
                                )
                        )
                )
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block)
    {
        return MaterialRules.block(block.getDefaultState());
    }
}
