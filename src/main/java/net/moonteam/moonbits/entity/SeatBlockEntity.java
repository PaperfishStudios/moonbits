package net.moonteam.moonbits.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.*;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.moonteam.moonbits.MBEntities;
import net.moonteam.moonbits.block.SeatBlock;
import net.moonteam.moonbits.entity.EntityPacketUtils;
import net.moonteam.moonbits.entity.MoobloomEntity;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;

public class SeatBlockEntity extends Entity {

    public SeatBlockEntity(EntityType<? extends SeatBlockEntity> type, World world) {
        super(type, world);
        this.inanimate = true;
        this.setInvisible(true);
    }
    public SeatBlockEntity(World world, double x, double y, double z) {
        this(MBEntities.SEAT_BLOCK_ENTITY, world);
        this.setPos(x, y, z);
    }

    protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height;
    }

//    @Override
//    public void setPosition(double x, double y, double z) {
//        super.setPosition(x, y, z);
//        Box bb = super.getBoundingBox();
//        Vec3d diff = new Vec3d(x, y, z).subtract(bb.getCenter());
//        setBoundingBox(bb.offset(diff));
//    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    // when a player right-clicks and there's no one in the seat, they sit down i think
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        } else if (this.hasPassengers()) {
            return ActionResult.PASS;
        } else if (!this.world.isClient) {
            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            return ActionResult.SUCCESS;
        }
    }

    @Override
    public void tick() {
        if (this.world.isClient)  {
            return;
        }
        BlockState blook = this.world.getBlockState(getBlockPos());
        if (blook.getBlock() instanceof SeatBlock) {
            return;
        }
        this.removeAllPassengers();
        this.discard();
    }

    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        super.updatePassengerForDismount(passenger);
        Vec3d pos = passenger.getPos();
        return new Vec3d(pos.x, pos.y + 0.85f, pos.z);
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new MBS2CSpawnPacket(this);
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        //super.move(movementType, movement);
    }
}
