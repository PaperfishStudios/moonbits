package net.paperfish.moonbits.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.paperfish.moonbits.registry.MBBiomeTags;
import net.paperfish.moonbits.registry.MBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {

    @Inject(method = "createOverworldSurfaceRule", at = @At("RETURN"), cancellable = true)
    private static void appendMBSurface(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
        MaterialRules.MaterialRule rules = cir.getReturnValue();
        MaterialRules.MaterialCondition shallow_dc = MaterialRules.biome(

        );
        MaterialRules.MaterialCondition notDesert = MaterialRules.not(MaterialRules.biome(
                BiomeKeys.DESERT, BiomeKeys.WINDSWEPT_GRAVELLY_HILLS, BiomeKeys.WINDSWEPT_SAVANNA,
                BiomeKeys.DEEP_FROZEN_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.BEACH, BiomeKeys.STONY_SHORE, BiomeKeys.SNOWY_BEACH,
                BiomeKeys.GROVE, BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.FROZEN_PEAKS, BiomeKeys.JAGGED_PEAKS, BiomeKeys.SNOWY_SLOPES, BiomeKeys.STONY_PEAKS,
                BiomeKeys.RIVER,
                BiomeKeys.FROZEN_RIVER
        ));
        MaterialRules.MaterialRule dirt = MaterialRules.block(Blocks.DIRT.getDefaultState());
        MaterialRules.MaterialRule tough_dirt = MaterialRules.block(MBBlocks.TOUGH_DIRT.getDefaultState());
        MaterialRules.MaterialRule regolith = MaterialRules.block(MBBlocks.REGOLITH.getDefaultState());

        cir.setReturnValue(MaterialRules.sequence(
                rules,
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.DESERT), MaterialRules.block(MBBlocks.CHERT.getDefaultState())),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.SNOWY_SLOPES, BiomeKeys.SNOWY_PLAINS), MaterialRules.condition(
                        MaterialRules.surface(), MaterialRules.condition(
                        MaterialRules.stoneDepth(0, true, 12, VerticalSurfaceType.FLOOR),
                        MaterialRules.block(MBBlocks.PERMAFROST.getDefaultState())))
                ),
                MaterialRules.condition(
                        notDesert,
                        MaterialRules.condition(MaterialRules.surface(),
                                MaterialRules.condition(MaterialRules.stoneDepth(2, true, 12, VerticalSurfaceType.FLOOR), tough_dirt
                                        )
                        )),
                MaterialRules.condition(
                        notDesert,
                        MaterialRules.condition(MaterialRules.surface(),
                                MaterialRules.condition(MaterialRules.stoneDepth(12, true, 4, VerticalSurfaceType.FLOOR), regolith
                                )
                        ))
                )
        );
    }
}
