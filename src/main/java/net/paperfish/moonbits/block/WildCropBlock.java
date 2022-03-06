package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlockTags;
import org.jetbrains.annotations.Nullable;

public class WildCropBlock extends PlantBlock implements BlockEntityProvider {
    protected static final float field_31235 = 6.0F;
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    
    public WildCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

    public AbstractBlock.OffsetType getOffsetType() {
		return AbstractBlock.OffsetType.XZ;
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
