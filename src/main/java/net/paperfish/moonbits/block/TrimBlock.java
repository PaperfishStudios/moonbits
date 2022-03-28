package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TrimBlock extends Block {
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;
    public static final BooleanProperty ATTACHED = Properties.ATTACHED;

    public static final VoxelShape TRIM_X = Block.createCuboidShape(0, 4, 4, 16, 10, 12);
    public static final VoxelShape TRIM_Z = Block.createCuboidShape(4, 4, 0, 12, 10, 16);
    public static final VoxelShape POST = Block.createCuboidShape(5, 0, 5, 11, 4, 11);

    public TrimBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, ATTACHED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos().down();
        boolean attached = Block.sideCoversSmallSquare(world, pos, Direction.UP);
        return getDefaultState().with(AXIS, ctx.getPlayerFacing().rotateYClockwise().getAxis()).with(ATTACHED, attached);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = state.get(AXIS) == Direction.Axis.X ? TRIM_X : TRIM_Z;
        if (state.get(ATTACHED)) {
            shape = VoxelShapes.union(shape, POST);
        }
        return shape;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean attached = Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
        return state.with(ATTACHED, attached);
    }
}
