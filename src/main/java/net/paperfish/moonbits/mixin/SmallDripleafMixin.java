package net.paperfish.moonbits.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.block.SnowyBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(SmallDripleafBlock.class)
public class SmallDripleafMixin extends TallPlantBlock implements BlockEntityProvider {
    private static final BooleanProperty SNOWY = Properties.SNOWY;

    public SmallDripleafMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(Settings settings, CallbackInfo ci) {
        this.setDefaultState(getStateManager().getDefaultState().with(TallPlantBlock.HALF, DoubleBlockHalf.LOWER).with(SNOWY, false));
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    public void onAppendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SNOWY);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return (state.get(SNOWY) && state.get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER) ? new SnowyBlockEntity(pos, state) : null;
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    private void onGetNeighbour(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        DoubleBlockHalf doubleBlockHalf = state.get(TallPlantBlock.HALF);
        if (doubleBlockHalf == DoubleBlockHalf.LOWER && (direction == Direction.UP) && neighborState.isOf(this)) {
            cir.setReturnValue(state.with(SNOWY, neighborState.get(Properties.SNOWY)));
        }
    }

    @Inject(method = "getPlacementState", at = @At(value = "RETURN", ordinal = 1), cancellable = true)
    private void whenPlaced(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        if ((blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx)) && world.getBlockState(blockPos).isOf(Blocks.SNOW)) {
            cir.setReturnValue(Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.SNOWY, true));
        }
    }
}
