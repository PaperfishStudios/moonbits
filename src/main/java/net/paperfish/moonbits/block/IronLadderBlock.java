package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class IronLadderBlock extends Block implements Waterloggable {
   public static final DirectionProperty FACING;
   public static final BooleanProperty WATERLOGGED;
   protected static final float field_31106 = 3.0F;
   public int supportDistance = 10;
   protected static final VoxelShape EAST_SHAPE;
   protected static final VoxelShape WEST_SHAPE;
   protected static final VoxelShape SOUTH_SHAPE;
   protected static final VoxelShape NORTH_SHAPE;

   //private static final BlockSoundGroup SOUND_GROUP = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_METAL_BREAK, SoundEvents.BLOCK_LADDER_STEP, SoundEvents.BLOCK_METAL_PLACE, SoundEvents.BLOCK_METAL_HIT, SoundEvents.BLOCK_LADDER_FALL);

   public IronLadderBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(WATERLOGGED, false));
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      switch((Direction)state.get(FACING)) {
      case NORTH:
         return NORTH_SHAPE;
      case SOUTH:
         return SOUTH_SHAPE;
      case WEST:
         return WEST_SHAPE;
      case EAST:
      default:
         return EAST_SHAPE;
      }
   }

   private boolean hasSupport(BlockView world, BlockPos pos, Direction side, BlockState thisState, Direction vertical) {
      BlockState supportBlock = world.getBlockState(pos.offset(side.getOpposite())); // block that this ladder is attached to
      if (supportBlock.isSideSolidFullSquare(world, pos.offset(side.getOpposite()), side)) {
         return true; // should return true if This Ladder is attached to a block
      }
      BlockPos supportPos = pos.offset(vertical); // position of block to check
      BlockState supportLadder = world.getBlockState(supportPos); // block being checked
      // this should loop through checking each block above/below this for [supportDistance] blocks
      for (int i = 0; i < supportDistance; i++){
         if (supportLadder == Blocks.AIR.getDefaultState()){
            return false;
         }
         if (supportLadder.isSideSolidFullSquare(world, supportPos, vertical.getOpposite())) {
            return true; // return true if the block has a full block above or below
         }
         // if the block below is a matching iron ladder
         if (supportLadder == thisState) {
            supportLadder = world.getBlockState(supportPos.offset(side.getOpposite())); // check for the block that support ladder is attached to
            if (supportLadder.isSideSolidFullSquare(world, supportPos.offset(side.getOpposite()), side)) {
               return true; // return true if the block has support anywhere along the chain
            }
         }
         supportPos = supportPos.offset(vertical); // moves the coordinates to check up/down one
         supportLadder = world.getBlockState(supportPos);
      }
      return false;
   }

   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      Direction direction = (Direction)state.get(FACING);
      if (this.hasSupport(world, pos, direction, state, Direction.UP) || this.hasSupport(world, pos, direction, state, Direction.DOWN)) {
         return true; 
      }
      return false;
   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      if (!state.canPlaceAt(world, pos)) {
         return Blocks.AIR.getDefaultState();
      } else {
         if ((Boolean)state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
         }

         return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
      }
   }

   @Override
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult traceResult) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() == this.asItem()) {
         if (placeChain(state, world, pos)){
            if(!player.isCreative())
               stack.decrement(1);
            world.playSound(null, pos, SoundEvents.BLOCK_LADDER_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return ActionResult.SUCCESS;
         }
		}
		return ActionResult.PASS;
	}

   public boolean placeChain(BlockState state, World world, BlockPos pos){
      BlockPos nextPos = pos;
      BlockState nextBlock = world.getBlockState(nextPos);
      Direction direction = (Direction)state.get(FACING);
      if (this.hasSupport(world, pos, direction, state, Direction.UP)) {
         while (nextBlock == state){
            nextPos = nextPos.down();
            nextBlock = world.getBlockState(nextPos);
            if (nextBlock.getBlock() == Blocks.AIR) {
               world.setBlockState(nextPos, state, Block.NOTIFY_ALL);
               return true;
            }
         }
      }
      else if (this.hasSupport(world, pos, direction, state, Direction.DOWN)) {
         while (nextBlock == state){
            nextPos = nextPos.up();
            nextBlock = world.getBlockState(nextPos);
            if (nextBlock.getBlock() == Blocks.AIR) {
               world.setBlockState(nextPos, state, Block.NOTIFY_ALL);
               return true;
            }
         }
      }
      return false;
   }

   @Nullable
   public BlockState getPlacementState(ItemPlacementContext ctx) {
      BlockState blockState2;
      if (!ctx.canReplaceExisting()) {
         blockState2 = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
         if (blockState2.isOf(this) && blockState2.get(FACING) == ctx.getSide()) {
            return null;
         }
      }

      blockState2 = this.getDefaultState();
      WorldView worldView = ctx.getWorld();
      BlockPos blockPos = ctx.getBlockPos();
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      Direction[] var6 = ctx.getPlacementDirections();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Direction direction = var6[var8];
         if (direction.getAxis().isHorizontal()) {
            // i think this part checks for the block behind it?
            blockState2 = (BlockState)blockState2.with(FACING, direction.getOpposite());
            if (blockState2.canPlaceAt(worldView, blockPos)) {
               return (BlockState)blockState2.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
         }
      }

      return null;
   }

   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
   }

   public BlockState mirror(BlockState state, BlockMirror mirror) {
      return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
   }

   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(FACING, WATERLOGGED);
   }

   public FluidState getFluidState(BlockState state) {
      return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
   }

   static {
      FACING = HorizontalFacingBlock.FACING;
      WATERLOGGED = Properties.WATERLOGGED;
      EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
      WEST_SHAPE = Block.createCuboidShape(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
      NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
   }
}
