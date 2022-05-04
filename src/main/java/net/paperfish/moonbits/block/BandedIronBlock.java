package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.registry.MBBlockTags;
import org.jetbrains.annotations.Nullable;

public class BandedIronBlock extends Block {
    public static BooleanProperty NORTH = Properties.NORTH;
    public static BooleanProperty EAST = Properties.EAST;
    public static BooleanProperty SOUTH = Properties.SOUTH;
    public static BooleanProperty WEST = Properties.WEST;

    public BandedIronBlock(Settings settings) {
        super(settings);
        setDefaultState(super.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        boolean north = ctx.getWorld().getBlockState(pos.north()).isIn(MBBlockTags.BANDED_IRON);
        boolean east = ctx.getWorld().getBlockState(pos.east()).isIn(MBBlockTags.BANDED_IRON);
        boolean south = ctx.getWorld().getBlockState(pos.south()).isIn(MBBlockTags.BANDED_IRON);
        boolean west = ctx.getWorld().getBlockState(pos.west()).isIn(MBBlockTags.BANDED_IRON);
        return getDefaultState().with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        boolean north = world.getBlockState(pos.north()).isIn(MBBlockTags.BANDED_IRON);
        boolean east = world.getBlockState(pos.east()).isIn(MBBlockTags.BANDED_IRON);
        boolean south = world.getBlockState(pos.south()).isIn(MBBlockTags.BANDED_IRON);
        boolean west = world.getBlockState(pos.west()).isIn(MBBlockTags.BANDED_IRON);
        return getDefaultState().with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
    }
}
