package net.paperfish.moonbits.world;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.paperfish.moonbits.registry.MBBlocks;

public class MBSurfaceRules {
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);

    public static final MaterialRules.MaterialRule OVERWORLD_RULES = MaterialRules.sequence(makeRules());

//    private static final MaterialRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
//    private static final MaterialRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
//    private static final MaterialRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialRule CHERT = makeStateRule(MBBlocks.CHERT);
        MaterialRules.MaterialRule TOUGH_DIRT = makeStateRule(MBBlocks.TOUGH_DIRT);
        MaterialRules.MaterialRule TOUGH_GRASS = makeStateRule(MBBlocks.TOUGH_GRASS);

        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        MaterialRules.MaterialRule toughSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, TOUGH_GRASS), TOUGH_DIRT);
        MaterialRules.MaterialRule sandySurface = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDSTONE), SAND);

        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.biome(MBBiomes.FLOOD_PLAINS), sandySurface),
                MaterialRules.condition(MaterialRules.biome(MBBiomes.FLOOD_PLAINS), CHERT),

                MaterialRules.condition(MaterialRules.biome(MBBiomes.PRAIRIE), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, toughSurface)),

                // Default to a grass and dirt surface
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block)
    {
        return MaterialRules.block(block.getDefaultState());
    }
}
