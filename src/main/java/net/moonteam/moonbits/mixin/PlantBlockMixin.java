package net.moonteam.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.moonteam.moonbits.MBData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    public void plantableCheck(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if (floor.isIn(MBData.PLANTER_BOXES)) {
            cir.setReturnValue(true);
        }
        if (floor.isIn(MBData.SOIL_NON_REPLACEABLE) && floor.isSideSolidFullSquare(world, pos, Direction.UP)) {
            cir.setReturnValue(true);
        }
    }
}
