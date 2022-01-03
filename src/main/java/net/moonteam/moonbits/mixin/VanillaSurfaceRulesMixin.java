package net.moonteam.moonbits.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.moonteam.moonbits.MBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {

    @Inject(method = "createOverworldSurfaceRule", at = @At("RETURN"), cancellable = true)
    private static void promenade$appendEndSurfaces(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
        MaterialRules.MaterialRule rules = cir.getReturnValue();
        MaterialRules.MaterialCondition notDesert = MaterialRules.not(MaterialRules.biome(
                BiomeKeys.DESERT,
                BiomeKeys.DEEP_FROZEN_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN,
                BiomeKeys.OCEAN,
                BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.BEACH,
                BiomeKeys.SNOWY_BEACH
        ));
        MaterialRules.MaterialRule dirt = MaterialRules.block(Blocks.DIRT.getDefaultState());
        MaterialRules.MaterialRule tough_dirt = MaterialRules.block(MBBlocks.TOUGH_DIRT.getDefaultState());
        MaterialRules.MaterialRule regolith = MaterialRules.block(MBBlocks.REGOLITH.getDefaultState());

        cir.setReturnValue(MaterialRules.sequence(
            rules,
            MaterialRules.condition(
                MaterialRules.water(-4, 0),
                MaterialRules.sequence(
                    MaterialRules.condition(
                        notDesert,
                        MaterialRules.condition(
                            MaterialRules.aboveY(YOffset.fixed(55), 0),
                            tough_dirt
                        )
                        //MaterialRules.condition(MaterialRules.stoneDepth(0, true, true, VerticalSurfaceType.FLOOR), tough_dirt)
                    )
                )
            ),
            MaterialRules.condition(
                MaterialRules.aboveY(YOffset.fixed(47), 0),
                MaterialRules.condition(
                    MaterialRules.not(MaterialRules.verticalGradient("tough_dirt", YOffset.fixed(48), YOffset.fixed(55))), tough_dirt
                )
            ),
            MaterialRules.condition(
                    MaterialRules.aboveY(YOffset.fixed(44), 0),
                    MaterialRules.condition(
                            MaterialRules.not(MaterialRules.verticalGradient("regolith", YOffset.fixed(45), YOffset.fixed(50))), regolith
                    )
            )
        ));
    }
}
