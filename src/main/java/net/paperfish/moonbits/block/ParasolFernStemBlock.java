package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RodBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ParasolFernStemBlock extends RodBlock {
    protected static final float start = 2.0f;
    protected static final float end = 14.0f;
    protected static final VoxelShape Y_SHAPE = Block.createCuboidShape(start, 0.0, start, end, 16.0, end);
    protected static final VoxelShape Z_SHAPE = Block.createCuboidShape(start, start, 0.0, end, end, 16.0);
    protected static final VoxelShape X_SHAPE = Block.createCuboidShape(0.0, start, start, 16.0, end, end);

    public ParasolFernStemBlock(Settings settings) {
        super(settings);
		setDefaultState(getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING).getAxis()) {
            default: {
                return X_SHAPE;
            }
            case Z: {
                return Z_SHAPE;
            }
            case Y:
        }
        return Y_SHAPE;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();
//        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
//        if (blockState.isOf(this) && blockState.get(FACING) == direction) {
//            return this.getDefaultState().with(FACING, direction.getOpposite());
//        }
        return this.getDefaultState().with(FACING, direction);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.NORMAL;
    }
}
