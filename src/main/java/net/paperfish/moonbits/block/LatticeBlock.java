package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LatticeBlock extends PaneBlock {
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }

    public LatticeBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.Z));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        BlockState northBlock = blockView.getBlockState(blockPos.north());
        BlockState southBlock = blockView.getBlockState(blockPos.south());
        BlockState westBlock = blockView.getBlockState(blockPos.west());
        BlockState eastBlock = blockView.getBlockState(blockPos.east());
        BlockState pane = getDefaultState()
                .with(NORTH, northBlock.getBlock() instanceof LatticeBlock)
                .with(SOUTH, southBlock.getBlock() instanceof LatticeBlock)
                .with(WEST, westBlock.getBlock() instanceof LatticeBlock)
                .with(EAST, eastBlock.getBlock() instanceof LatticeBlock)
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        Direction.Axis axis = Direction.Axis.Z;
        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis() != Direction.Axis.Y) {
                axis = direction.getAxis();
                break;
            }
        }
//        if (axis == Direction.Axis.X && pane.get(NORTH) != pane.get(SOUTH)) {
//            axis = Direction.Axis.Z;
//        }
//        else if (axis == Direction.Axis.Z && pane.get(EAST) != pane.get(WEST)) {
//            axis = Direction.Axis.X;
//        }

        if (pane.get(NORTH) != pane.get(SOUTH) && pane.get(EAST) != pane.get(WEST) ||
                (axis == Direction.Axis.X && pane.get(NORTH) != pane.get(SOUTH)) ||
                (axis == Direction.Axis.Z && pane.get(EAST) != pane.get(WEST))) {
            return pane.with(AXIS, axis); // if it is a corner piece or a T-section, dont force the axis stuff
        }

        BlockState state = (axis == Direction.Axis.X) ?
                pane.with(NORTH, true).with(SOUTH, true) :
                pane.with(EAST, true).with(WEST, true);

        return state.with(AXIS, axis);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getAxis().isHorizontal()) {
            BlockState northBlock = world.getBlockState(pos.north());
            BlockState southBlock = world.getBlockState(pos.south());
            BlockState westBlock = world.getBlockState(pos.west());
            BlockState eastBlock = world.getBlockState(pos.east());
            BlockState result = state
                    .with(NORTH, northBlock.getBlock() instanceof LatticeBlock)
                    .with(SOUTH, southBlock.getBlock() instanceof LatticeBlock)
                    .with(WEST, westBlock.getBlock() instanceof LatticeBlock)
                    .with(EAST, eastBlock.getBlock() instanceof LatticeBlock);

            if ((result.get(NORTH) != result.get(SOUTH) && result.get(EAST) != result.get(WEST)) ||
                    (state.get(AXIS) == Direction.Axis.X && result.get(NORTH) != result.get(SOUTH)) ||
                    (state.get(AXIS) == Direction.Axis.Z && result.get(EAST) != result.get(WEST))) {
                return result; // if it is a corner piece or a T-section, dont bother with the axis locker thingy
            }

            return (state.get(AXIS) == Direction.Axis.X) ?
                    result.with(NORTH, true).with(SOUTH, true) :
                    result.with(EAST, true).with(WEST, true);
        }
        return state;
    }
}
