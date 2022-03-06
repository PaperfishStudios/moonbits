package net.paperfish.moonbits.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlockTags;
import net.paperfish.moonbits.block.SnowyBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FernBlock.class)
public class FernGrassMixin extends Block implements BlockEntityProvider {
    public FernGrassMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstruct(Settings settings, CallbackInfo ci) {
        this.setDefaultState(getStateManager().getDefaultState().with(Properties.SNOWY, false));
    }
    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.SNOWY);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(Blocks.SNOW)) {
            return this.getDefaultState().with(Properties.SNOWY, true);
        }
        return this.getDefaultState().with(Properties.SNOWY, false);
    }
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        if (state.get(Properties.SNOWY)) {
            world.setBlockState(pos, Blocks.SNOW.getDefaultState());
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if (!state.isIn(MBBlockTags.SNOWABLE_PLANTS)) {
            return null;
        }
        return state.get(Properties.SNOWY) ? new SnowyBlockEntity(pos, state) : null;
    }
}
