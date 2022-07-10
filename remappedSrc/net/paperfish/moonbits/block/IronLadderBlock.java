package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.math.random.Random;

public class IronLadderBlock extends Block implements Waterloggable {
   public static final DirectionProperty FACING;
   public static final BooleanProperty WATERLOGGED;
   public static int supportDistance = 10;
   public static final IntProperty DISTANCE = IntProperty.of("distance", 1, supportDistance);
   protected static final VoxelShape EAST_SHAPE;
   protected static final VoxelShape WEST_SHAPE;
   protected static final VoxelShape SOUTH_SHAPE;
   protected static final VoxelShape NORTH_SHAPE;

   //private static final BlockSoundGroup SOUND_GROUP = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_METAL_BREAK, SoundEvents.BLOCK_LADDER_STEP, SoundEvents.BLOCK_METAL_PLACE, SoundEvents.BLOCK_METAL_HIT, SoundEvents.BLOCK_LADDER_FALL);

   public IronLadderBlock(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false).with(DISTANCE, 1));
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return switch (state.get(FACING)) {
         case DOWN, UP -> null;
         case NORTH -> NORTH_SHAPE;
         case SOUTH -> SOUTH_SHAPE;
         case WEST -> WEST_SHAPE;
         case EAST -> EAST_SHAPE;
      };
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
      Direction direction = state.get(FACING);
      BlockPos behindPos = pos.offset(direction.getOpposite());
      BlockState behind = world.getBlockState(behindPos);
      BlockState above = world.getBlockState(pos.up());
      return state.get(DISTANCE) < supportDistance || (above.isOf(this) && above.get(FACING) == state.get(FACING));
      //return this.hasSupport(world, pos, direction, state, Direction.UP) || this.hasSupport(world, pos, direction, state, Direction.DOWN);
   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      if (direction == state.get(FACING).getOpposite() || direction.getAxis() == Direction.Axis.Y) {
         int i = getDistanceFromBlock(neighborState, world, neighborPos, direction.getOpposite()) + 1;
         if (i != 1 || state.get(DISTANCE) != i) {
            world.createAndScheduleBlockTick(pos, this, 1);
         }
      }
      if (!state.canPlaceAt(world, pos)) {
         return Blocks.AIR.getDefaultState();
      }
      else {
         if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
         }

         return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
      }
   }
   private static int getDistanceFromBlock(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
      if (state.isSideSolidFullSquare(world, pos, direction)) {
         return 0;
      }
      if (state.getBlock() instanceof IronLadderBlock) {
         return state.get(DISTANCE);
      }
      return supportDistance;
   }
   private static BlockState updateDistanceFromBlock(BlockState state, WorldAccess world, BlockPos pos) {
      int i = 7;
      BlockPos.Mutable mutable = new BlockPos.Mutable();
      for (Direction direction : Direction.values()) {
         mutable.set(pos, direction);
         i = Math.min(i, getDistanceFromBlock(world.getBlockState(mutable), world, pos, direction) + 1);
         if (i == 1) break;
      }
      return state.with(DISTANCE, i);
   }
   @Override
   public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      world.setBlockState(pos, updateDistanceFromBlock(state, world, pos), Block.NOTIFY_ALL);
   }

   @Override
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult traceResult) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() == this.asItem()) {
         if (placeChain(state, world, pos, player)){
            if(!player.isCreative())
               stack.decrement(1);
            world.playSound(null, pos, SoundEvents.BLOCK_LADDER_PLACE, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return ActionResult.SUCCESS;
         }
		}
		return ActionResult.PASS;
	}

   public boolean placeChain(BlockState state, World world, BlockPos pos, PlayerEntity player){
      BlockPos nextPos = pos;
      BlockState nextBlock = world.getBlockState(nextPos);
      Direction direction = state.get(FACING);
      BlockState above = world.getBlockState(pos.up());
      BlockState below = world.getBlockState(pos.down());
      int supportDir;
      if (above.isOf(this) && below.isOf(this)) {
         // above support - below support should equal either 1 or -1
         supportDir = world.getBlockState(pos.up()).get(DISTANCE) - world.getBlockState(pos.down()).get(DISTANCE);
      }
      else if (below.isAir()) {
            supportDir = -1;
      }
      else if (above.isAir()) {
            supportDir = 1;
      }
      else {
         supportDir = 0;
      }
      if (supportDir == 1 || supportDir == -1) {
         while (nextBlock == state && nextBlock.get(DISTANCE) < supportDistance - 1){
            nextPos = nextPos.offset(Direction.Axis.Y, supportDir);
            nextBlock = world.getBlockState(nextPos);
            if (nextBlock.getBlock() == Blocks.AIR) {
               world.setBlockState(nextPos, state, Block.NOTIFY_ALL);
               return true;
            }
         }
      }
//      else if (this.hasSupport(world, pos, direction, state, Direction.DOWN)) {
//         while (nextBlock == state && nextBlock.get(DISTANCE) < supportDistance - 1){
//            nextPos = nextPos.up();
//            nextBlock = world.getBlockState(nextPos);
//            if (nextBlock.getBlock() == Blocks.AIR) {
//               world.setBlockState(nextPos, state, Block.NOTIFY_ALL);
//               return true;
//            }
//         }
//      }
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
      Direction[] directions = ctx.getPlacementDirections();

      for (Direction direction : directions) {
         if (direction.getAxis().isHorizontal()) {
            // i think this part checks for the block behind it?
            blockState2 = blockState2.with(FACING, direction.getOpposite());
            if (blockState2.canPlaceAt(worldView, blockPos)) {
               return blockState2.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
            }
         }
      }

      return null;
   }

   public BlockState rotate(BlockState state, BlockRotation rotation) {
      return state.with(FACING, rotation.rotate(state.get(FACING)));
   }

   public BlockState mirror(BlockState state, BlockMirror mirror) {
      return state.rotate(mirror.getRotation(state.get(FACING)));
   }

   protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(FACING, WATERLOGGED, DISTANCE);
   }

   public FluidState getFluidState(BlockState state) {
      return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
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
