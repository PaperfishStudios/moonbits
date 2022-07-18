package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;

public class SyrupBlock extends Block {
    private static final float field_31216 = 0.083333336f;
    private static final float HORIZONTAL_MOVEMENT_MULTIPLIER = 0.9f;
    private static final float VERTICAL_MOVEMENT_MULTIPLIER = 1.5f;
    private static final float field_31219 = 2.5f;
    private static final VoxelShape FALLING_SHAPE = VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 0.9f, 1.0);
    private static final double field_36189 = 4.0;
    private static final double SMALL_FALL_SOUND_MAX_DISTANCE = 7.0;

    public SyrupBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            return true;
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this) && entity.getVelocity().y <= 0) {
//            boolean bool = world.getBlockState(entity.getBlockPos().up()).isOf(this);
            entity.slowMovement(state, new Vec3d(1.25f, 1, 1.25f));
        }
        //entity.setInPowderSnow(true);
        if (!world.isClient) {
            if (entity.isOnFire() && (world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING) || entity instanceof PlayerEntity) && entity.canModifyAt(world, pos)) {
                world.breakBlock(pos, false);
            }
            entity.setOnFire(false);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if ((double)fallDistance < 4.0 || !(entity instanceof LivingEntity livingEntity)) {
            return;
        }
        LivingEntity.FallSounds fallSounds = livingEntity.getFallSounds();
        SoundEvent soundEvent = (double)fallDistance < 7.0 ? fallSounds.small() : fallSounds.big();
        entity.playSound(soundEvent, 1.0f, 1.0f);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Entity entity;
        if (context instanceof EntityShapeContext && (entity = ((EntityShapeContext)context).getEntity()) != null) {
            if (entity.fallDistance > 2.5f) {
                return FALLING_SHAPE;
            }
            if (context.isAbove(VoxelShapes.fullCube(), pos, false) && world.getBlockState(pos.up()).isOf(this)) {
                return VoxelShapes.fullCube();
            }
            boolean bl = entity instanceof FallingBlockEntity;
            if (bl || canWalkOnSyrup(entity) && context.isAbove(VoxelShapes.fullCube(), pos, false) && !context.isDescending()) {
                return super.getCollisionShape(state, world, pos, context);
            }
        }
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public static boolean canWalkOnSyrup(Entity entity) {
        return entity.getType().isIn(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }
}
