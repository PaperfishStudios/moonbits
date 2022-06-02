package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlanterBoxBlock extends Block {
    // each of these are true if the block in that spot is also a planter box
//	public static final BooleanProperty NORTH = BooleanProperty.of("north");
//	public static final BooleanProperty EAST = BooleanProperty.of("east");
//	public static final BooleanProperty SOUTH = BooleanProperty.of("south");
//	public static final BooleanProperty WEST = BooleanProperty.of("west");
//	public static final BooleanProperty NORTHEAST = BooleanProperty.of("ne");
//	public static final BooleanProperty NORTHWEST = BooleanProperty.of("nw");
//	public static final BooleanProperty SOUTHEAST = BooleanProperty.of("se");
//	public static final BooleanProperty SOUTHWEST = BooleanProperty.of("sw");
    
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
//        stateManager.add(NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
//    }

    public PlanterBoxBlock(AbstractBlock.Settings settings) {
        super(settings);
//        setDefaultState(
//            getStateManager().getDefaultState()
//            .with(NORTH, true).with(EAST, true).with(SOUTH, true).with(WEST, true)
//            .with(NORTHEAST, true).with(NORTHWEST, true).with(SOUTHEAST, true).with(SOUTHWEST, true)
//        );
    }

//    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos){
//        // frame is active on each side only if there is not another planter connected
//        boolean north = !world.getBlockState(pos.north()).isOf(state.getBlock());
//        boolean east = !world.getBlockState(pos.east()).isOf(state.getBlock());
//        boolean south = !world.getBlockState(pos.south()).isOf(state.getBlock());
//        boolean west = !world.getBlockState(pos.west()).isOf(state.getBlock());
//        boolean ne = !world.getBlockState(pos.north().east()).isOf(state.getBlock());
//        boolean nw = !world.getBlockState(pos.north().west()).isOf(state.getBlock());
//        boolean se = !world.getBlockState(pos.south().east()).isOf(state.getBlock());
//        boolean sw = !world.getBlockState(pos.south().west()).isOf(state.getBlock());
//
//        return getStateManager().getDefaultState()
//        .with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west)
//        .with(NORTHEAST, ne).with(NORTHWEST, nw).with(SOUTHEAST, se).with(SOUTHWEST, sw);
//    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState above = world.getBlockState(pos.up());
        if (above.isIn(BlockTags.SMALL_FLOWERS) && random.nextInt(4) == 0) {
            List<Direction> hori = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
            BlockPos placeAt = pos.up().offset( hori.get(random.nextInt(4)) );
            if (above.canPlaceAt(world, placeAt)) {
                world.setBlockState(placeAt, above, NOTIFY_ALL);
                world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, placeAt, 0);
            }
        }
        super.randomTick(state, world, pos, random);
    }
}
