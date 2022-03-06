package net.paperfish.moonbits.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlockTags;
import net.paperfish.moonbits.block.SnowyBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin extends Block {
    private final BooleanProperty SNOWLOGGED = Properties.SNOWY;

    public PlantBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.SNOWY);
        //super.appendProperties(builder);
    }

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    public void plantableCheck(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if (floor.isIn(MBBlockTags.PLANTER_BOXES)) {
            cir.setReturnValue(true);
        }
        if (floor.isIn(MBBlockTags.SOIL_NON_REPLACEABLE) && floor.isSideSolidFullSquare(world, pos, Direction.UP)) {
            cir.setReturnValue(true);
        }
    }
}
