package net.paperfish.moonbits.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {
    // replace all the checks for air with a check for transparent blocks
    @Redirect(method = "playNote", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    private boolean transparentBlocks(BlockState instance, World world, BlockPos pos) {
        return !world.getBlockState(pos.up()).isFullCube(world, pos);
    }
}
