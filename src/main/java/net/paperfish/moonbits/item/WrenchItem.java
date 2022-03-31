package net.paperfish.moonbits.item;

import net.minecraft.block.*;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.paperfish.moonbits.MBSounds;
import net.paperfish.moonbits.block.LatticeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

import static net.minecraft.util.math.Direction.*;

public class WrenchItem extends Item {
    public WrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        if (!world.isClient && playerEntity != null && !this.use(playerEntity, world.getBlockState(blockPos = context.getBlockPos()), world, blockPos, context.getStack(), context)) {
            return ActionResult.FAIL;
        }
        if (!world.isClient) {
            world.playSound(null, blockPos, MBSounds.WRENCH, SoundCategory.BLOCKS, 0.5f, 1f);
        }
        return ActionResult.success(world.isClient);
    }

    public Direction rotate(Direction dir) {
        return switch(dir) {
            case UP -> NORTH;
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> DOWN;
            case DOWN -> UP;
        };
    }

    private boolean use(PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos, ItemStack stack, ItemUsageContext ctx) {
        Block block = state.getBlock();
        StateManager<Block, BlockState> stateManager = block.getStateManager();
        Collection<Property<?>> collection = stateManager.getProperties();
        if (collection.isEmpty()) {
            return false; // if there r no blockstates that wrenches can change
        }
        if (block instanceof StairsBlock || block instanceof TrapdoorBlock) { // if its stairs, rotate!
            BlockState newState;
            if (player.isSneaking()) {
                newState = state.cycle(Properties.BLOCK_HALF);
            }
            else {
                newState = block.rotate(state, BlockRotation.CLOCKWISE_90);
            }
            world.setBlockState(pos, newState, Block.NOTIFY_ALL);
            return true;
        }
        if (block instanceof HopperBlock || block instanceof DispenserBlock || //block instanceof GlazedTerracottaBlock ||
                block instanceof ObserverBlock || block instanceof AbstractFurnaceBlock || block instanceof BeehiveBlock) { // rotate!
            BlockState newState = block.rotate(state, BlockRotation.CLOCKWISE_90);
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
            return true;
        }
        if ((block instanceof PistonBlock && !state.get(Properties.EXTENDED)) || block instanceof RodBlock) {
            BlockState newState = state.with(Properties.FACING, rotate(state.get(Properties.FACING)));
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
            return true;
        }
        if (block instanceof LatticeBlock) {
            BlockState newState = (state.get(LatticeBlock.AXIS) == Axis.X) ? state.with(LatticeBlock.AXIS, Axis.Z) : state.with(LatticeBlock.AXIS, Axis.X);
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
            return true;
        }
        if (block instanceof PillarBlock) {
            BlockState newState;
            switch (state.get(Properties.AXIS)) {
                case X -> {
                    newState = state.with(PillarBlock.AXIS, Direction.Axis.Y);
                }
                case Y -> {
                    newState = state.with(PillarBlock.AXIS, Direction.Axis.Z);
                }
                case Z -> {
                    newState = state.with(PillarBlock.AXIS, Direction.Axis.X);
                }
                default -> throw new IllegalStateException("Unexpected value: " + state.get(Properties.AXIS));
            }
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
            return true;
        }
        if (block instanceof DoorBlock) { // if its a door, flip da hinge!
            switch (state.get(Properties.DOOR_HINGE)) {
                case LEFT -> {
                    world.setBlockState(pos, state.with(Properties.DOOR_HINGE, DoorHinge.RIGHT), Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
                    return true;
                }
                case RIGHT -> {
                    world.setBlockState(pos, state.with(Properties.DOOR_HINGE, DoorHinge.LEFT), Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
                    return true;
                }
            }
        }
        if (block instanceof SlabBlock) { // if its a slab, flip!
            switch (state.get(Properties.SLAB_TYPE)) {
                case BOTTOM -> {
                    world.setBlockState(pos, state.with(Properties.SLAB_TYPE, SlabType.TOP), Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
                    return true;
                }
                case TOP -> {
                    world.setBlockState(pos, state.with(Properties.SLAB_TYPE, SlabType.BOTTOM), Block.NOTIFY_LISTENERS | Block.NOTIFY_NEIGHBORS);
                    return true;
                }
            }
        }
        return false;
    }

    private static <T extends Comparable<T>> BlockState cycle(BlockState state, Property<T> property, boolean inverse) {
        return state.with(property, cycle(property.getValues(), state.get(property), inverse));
    }

    private static <T> T cycle(Iterable<T> elements, @Nullable T current, boolean inverse) {
        return inverse ? Util.previous(elements, current) : Util.next(elements, current);
    }
}
