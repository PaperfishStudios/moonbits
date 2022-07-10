package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

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
//
//    // dead. remove later probably
//	public static final BooleanProperty RAISED = BooleanProperty.of("raised");
    // block shapes r unused now bc its a cyoob
//	protected static final VoxelShape MAIN_SHAPE;
//	protected static final VoxelShape RAISED_SHAPE;
//	protected static final VoxelShape NORTH_FRAME_SHAPE;
//	protected static final VoxelShape EAST_FRAME_SHAPE;
//	protected static final VoxelShape SOUTH_FRAME_SHAPE;
//	protected static final VoxelShape WEST_FRAME_SHAPE;
//	protected static final VoxelShape NORTHEAST_FRAME_SHAPE;
//	protected static final VoxelShape NORTHWEST_FRAME_SHAPE;
//	protected static final VoxelShape SOUTHEAST_FRAME_SHAPE;
//	protected static final VoxelShape SOUTHWEST_FRAME_SHAPE;
//    protected static final VoxelShape RAYCAST_SHAPE;
    
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
//        stateManager.add(NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, RAISED);
//    }

    public PlanterBoxBlock(AbstractBlock.Settings settings) {
        super(settings);
//        setDefaultState(
//            ((BlockState)((BlockState)((BlockState)((BlockState)
//            ((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)getStateManager().getDefaultState())
//            .with(NORTH, true)).with(EAST, true)).with(SOUTH, true)).with(WEST, true))
//            .with(NORTHEAST, true)).with(NORTHWEST, true)).with(SOUTHEAST, true)).with(SOUTHWEST, true)).with(RAISED, false)
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
//        // formerly a to do lol, make another check to raise the soil if any attached (NSEW) planter boxes are raised, to prevent weird 2-level stuff
//        //boolean raised = (world.getBlockState(pos.up()).isIn(MBData.PLANTER_BOX_PLANTABLE));
//
//        return ((BlockState)((BlockState)((BlockState)((BlockState)
//        ((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)getStateManager().getDefaultState())
//        .with(NORTH, north)).with(EAST, east)).with(SOUTH, south)).with(WEST, west))
//        .with(NORTHEAST, ne)).with(NORTHWEST, nw)).with(SOUTHEAST, se)).with(SOUTHWEST, sw)).with(RAISED, false);
//    }

//    @Override
//    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
//        if ((world.getBlockState(pos.up()).isIn(MBData.PLANTER_BOX_PLANTABLE))){
//            return RAISED_SHAPE;
//        }
//		return constructShape(state, world, pos);
//	}
//
//    // prevents placing blocks weirdly from the inside of the box
//    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
//        return RAYCAST_SHAPE;
//    }
//
//    public VoxelShape constructShape(BlockState state, BlockView world, BlockPos pos) {
//        VoxelShape constructShape = MAIN_SHAPE;
//        if (!world.getBlockState(pos.north()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, NORTH_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.east()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, EAST_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.south()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, SOUTH_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.west()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, WEST_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.north().east()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, NORTHEAST_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.north().west()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, NORTHWEST_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.south().east()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, SOUTHEAST_FRAME_SHAPE);
//        }
//        if (!world.getBlockState(pos.south().west()).isOf(state.getBlock())){
//            constructShape = VoxelShapes.union(constructShape, SOUTHWEST_FRAME_SHAPE);
//        }
//        return constructShape;
//    }
//
//    static {
//		MAIN_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);
//        RAISED_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
//        NORTH_FRAME_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 1.0D);
//        EAST_FRAME_SHAPE = Block.createCuboidShape(15.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
//        SOUTH_FRAME_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 15.0D, 16.0D, 16.0D, 16.0D);
//        WEST_FRAME_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 0.0D, 1.0D, 16.0D, 16.0D);
//        NORTHEAST_FRAME_SHAPE = Block.createCuboidShape(15.0D, 15.0D, 0.0D, 16.0D, 16.0D, 1.0D);
//        NORTHWEST_FRAME_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 0.0D, 1.0D, 16.0D, 1.0D);
//        SOUTHEAST_FRAME_SHAPE = Block.createCuboidShape(15.0D, 15.0D, 15.0D, 16.0D, 16.0D, 16.0D);
//        SOUTHWEST_FRAME_SHAPE = Block.createCuboidShape(0.0D, 15.0D, 15.0D, 1.0D, 16.0D, 16.0D);
//        RAYCAST_SHAPE = VoxelShapes.fullCube();
//	}
    
}
