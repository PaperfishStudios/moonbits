package net.paperfish.moonbits.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBEntities;

import java.util.List;

public class PapersBlock extends FallingBlock {
    public static final BooleanProperty POWERED = Properties.POWERED;
    protected static final VoxelShape COMPRESSED_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    protected static final Box BOX = new Box(0.0, 0.0, 0.125, 1.0, 0.75, 0.875);

    public PapersBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return state.get(POWERED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(POWERED))
            return COMPRESSED_SHAPE;
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Entity entity;
        if (context instanceof EntityShapeContext && (entity = ((EntityShapeContext)context).getEntity()) != null && !state.get(POWERED)) {
            if (entity.isSneaking() || entity.getType() == EntityType.ITEM || entity.getType() == EntityType.FALLING_BLOCK || entity.getType().isIn(MBEntities.LIGHTWEIGHT)) {
                return VoxelShapes.fullCube();
            }
        }
        return COMPRESSED_SHAPE;
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) {
            return;
        }
        if (!state.get(POWERED) && getEntities(world, pos) > 0) {
            world.setBlockState(pos, state.with(POWERED, true));
            world.scheduleBlockTick(new BlockPos(pos), this, 2);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        if (state.get(POWERED) && getEntities(world, pos) == 0) {
            world.setBlockState(pos, state.with(POWERED, false));
        }
        else {
            world.scheduleBlockTick(new BlockPos(pos), this, 2);
        }
        super.scheduledTick(state, world, pos, random);
    }

    protected int getEntities(World world, BlockPos pos) {
        Box box = BOX.offset(pos);
        List<LivingEntity> list = world.getNonSpectatingEntities(LivingEntity.class, box);
//        list = world.getNonSpectatingEntities(LivingEntity.class, box);
//        switch (this.type) {
//            case EVERYTHING: {
//                list = world.getOtherEntities(null, box);
//                break;
//            }
//            case MOBS: {
//                list = world.getNonSpectatingEntities(LivingEntity.class, box);
//                break;
//            }
//            default: {
//                return 0;
//            }
//        }
        if (!list.isEmpty()) {
            for (Entity entity : list) {
                if (entity.canAvoidTraps()) continue;
                return 15;
            }
        }
        return 0;
    }
}
