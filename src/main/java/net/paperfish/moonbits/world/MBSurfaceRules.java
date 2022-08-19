package net.paperfish.moonbits.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.SurfaceRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBData;
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

	private static final SurfaceRules.MaterialRule MUD = makeStateRule(Blocks.MUD);

    private static final SurfaceRules.MaterialRule PERMAFROST = makeStateRule(MBBlocks.PERMAFROST);
    private static final SurfaceRules.MaterialRule SANDY_SOIL = makeStateRule(MBBlocks.SANDY_SOIL);

//    public static final SurfaceRules.MaterialRule OVERWORLD_RULES = makeRules();

//    private static final SurfaceRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
//    private static final SurfaceRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
//    private static final SurfaceRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    public static SurfaceRules.MaterialRule makeRules() {
        SurfaceRules.MaterialCondition shallowDC = SurfaceRules.biome(
            BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU
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
		SurfaceRules.MaterialCondition frost = SurfaceRules.biome(
				BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA
		);

        SurfaceRules.MaterialCondition isAtOrAboveWaterLevel = SurfaceRules.water(-1, 0);
		SurfaceRules.MaterialCondition atShore = SurfaceRules.water(1, 2);

        SurfaceRules.MaterialCondition shallowDCDepth = SurfaceRules.aboveY(YOffset.fixed(50), 0);
        SurfaceRules.MaterialCondition midDCDepth = SurfaceRules.aboveY(YOffset.fixed(42), 0);
        SurfaceRules.MaterialCondition deepDCDepth = SurfaceRules.aboveY(YOffset.fixed(35), 0);

        SurfaceRules.MaterialRule grassSurface = SurfaceRules.sequence(SurfaceRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        SurfaceRules.MaterialRule toughSurface = SurfaceRules.sequence(SurfaceRules.condition(isAtOrAboveWaterLevel, TOUGH_GRASS), TOUGH_DIRT);
        SurfaceRules.MaterialRule sandySurface = SurfaceRules.sequence(SurfaceRules.condition(SurfaceRules.ON_CEILING, SANDSTONE), SAND);

		return SurfaceRules.sequence(
				// permafrost zone
				SurfaceRules.condition(frost,
						SurfaceRules.condition(SurfaceRules.noiseThreshold(NoiseParametersKeys.POWDER_SNOW, -0.3, 0.2), PERMAFROST)
				),

				SurfaceRules.condition(SurfaceRules.abovePreliminarySurface(),
						SurfaceRules.condition(SurfaceRules.biome(BiomeKeys.SWAMP),
								SurfaceRules.condition(atShore,
										SurfaceRules.condition(SurfaceRules.not(SurfaceRules.aboveY(YOffset.fixed(65), 1)),
												SurfaceRules.condition(SurfaceRules.stoneDepth(3, true, VerticalSurfaceType.FLOOR), MUD)
										)
								)
						)
				),

				VanillaSurfaceRules.getOverworldRules(),
//                SurfaceRules.condition(SurfaceRules.biome(BiomeKeys.DESERT),
//						SurfaceRules.condition(SurfaceRules.noiseThreshold(NoiseParametersKeys.POWDER_SNOW, -0.3, 0.1), SANDY_SOIL)
//				),
//                SurfaceRules.condition(SurfaceRules.biome(MBBiomes.PRAIRIE), SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, toughSurface)),

//                SurfaceRules.condition(SurfaceRules.surface(), SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, grassSurface)),

				// dirt cave stuff atm
				SurfaceRules.condition(SurfaceRules.abovePreliminarySurface(),
						SurfaceRules.condition(SurfaceRules.noiseThreshold(NoiseParametersKeys.CALCITE, -0.5, 0.6),
						SurfaceRules.sequence(
//                                SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR, grassSurface),
//                                SurfaceRules.condition(SurfaceRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, DIRT),

								SurfaceRules.condition(shallowDC, // rules for shallow dirt cave biomes
										SurfaceRules.sequence(
												SurfaceRules.condition(SurfaceRules.stoneDepth(6, true,
														3, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
												SurfaceRules.condition(SurfaceRules.stoneDepth(9, true,
														2, VerticalSurfaceType.FLOOR), REGOLITH)
										)
								),
								SurfaceRules.condition(midDC, // rules for mid dirt cave biomes
										SurfaceRules.sequence(
												SurfaceRules.condition(SurfaceRules.stoneDepth(14, true,
														4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
												SurfaceRules.condition(SurfaceRules.stoneDepth(17, true,
														2, VerticalSurfaceType.FLOOR), REGOLITH)
										)
								),
								SurfaceRules.condition(deepDC, // rules for deep dirt cave biomes
										SurfaceRules.sequence(
												SurfaceRules.condition(SurfaceRules.stoneDepth(28, true,
														4, VerticalSurfaceType.FLOOR), TOUGH_DIRT),
												SurfaceRules.condition(SurfaceRules.stoneDepth(30, true,
														2, VerticalSurfaceType.FLOOR), REGOLITH)
										)
								)
						)
				)
				),
//				SurfaceRules.condition(SurfaceRules.biome(BiomeKeys.DESERT),
//						SurfaceRules.condition(SurfaceRules.stoneDepth(64, false,
//						16, VerticalSurfaceType.FLOOR), CHERT)),
				SurfaceRules.condition(SurfaceRules.biome(BiomeKeys.DESERT), CHERT)
		);
    }

    private static SurfaceRules.MaterialRule makeStateRule(Block block)
    {
        return SurfaceRules.block(block.getDefaultState());
    }
}
