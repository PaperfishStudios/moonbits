package net.paperfish.moonbits.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class MBS2CSpawnPacket extends EntitySpawnS2CPacket implements Packet<ClientPlayPacketListener> {
    public static final Identifier SPAWN_PACKET_ID = new Identifier("moonbits", "spawn_packet");
    public static final double VELOCITY_SCALE = 8000.0D;
    private final int id;
    private final UUID uuid;
    private final double x;
    private final double y;
    private final double z;
    private final int velocityX;
    private final int velocityY;
    private final int velocityZ;
    private final int pitch;
    private final int yaw;
    private final EntityType<?> entityTypeId;
    private final int entityData;
    private final byte headYaw;
    public static final double MAX_ABSOLUTE_VELOCITY = 3.9D;

    public MBS2CSpawnPacket(int id, UUID uuid, double x, double y, double z, float pitch, float yaw, EntityType<?> entityTypeId, int entityData, Vec3d velocity, double headYaw) {
        super(id, uuid, x, y, z, pitch, yaw, entityTypeId, entityData, velocity, headYaw);
        this.id = id;
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = MathHelper.floor(pitch * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(yaw * 256.0F / 360.0F);
        this.headYaw = (byte)MathHelper.floor(headYaw * 256.0 / 360.0);
        this.entityTypeId = entityTypeId;
        this.entityData = entityData;
        this.velocityX = (int)(MathHelper.clamp(velocity.x, -3.9D, 3.9D) * 8000.0D);
        this.velocityY = (int)(MathHelper.clamp(velocity.y, -3.9D, 3.9D) * 8000.0D);
        this.velocityZ = (int)(MathHelper.clamp(velocity.z, -3.9D, 3.9D) * 8000.0D);
    }

    public MBS2CSpawnPacket(Entity entity) {
        this(entity, 0);
    }

    public MBS2CSpawnPacket(Entity entity, int entityData) {
        this(entity.getId(), entity.getUuid(), entity.getX(), entity.getY(), entity.getZ(), entity.getPitch(), entity.getYaw(), entity.getType(), entityData, entity.getVelocity(), entity.getHeadYaw());
    }

    public MBS2CSpawnPacket(Entity entity, EntityType<?> entityType, int data, BlockPos pos) {
        this(entity.getId(), entity.getUuid(), pos.getX(), pos.getY(), pos.getZ(), entity.getPitch(), entity.getYaw(), entityType, data, entity.getVelocity(), entity.getHeadYaw());
    }

    public MBS2CSpawnPacket(PacketByteBuf buf) {
        super(buf);
        this.id = buf.readVarInt();
        this.uuid = buf.readUuid();
        this.entityTypeId = Registry.ENTITY_TYPE.get(buf.readVarInt());
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.pitch = buf.readByte();
        this.yaw = buf.readByte();
        this.headYaw = buf.readByte();
        this.entityData = buf.readInt();
        this.velocityX = buf.readShort();
        this.velocityY = buf.readShort();
        this.velocityZ = buf.readShort();
    }

    public void write(PacketByteBuf buf) {
        buf.writeVarInt(this.id);
        buf.writeUuid(this.uuid);
        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(this.entityTypeId));
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeByte(this.pitch);
        buf.writeByte(this.yaw);
        buf.writeByte(this.headYaw);
        buf.writeInt(this.entityData);
        buf.writeShort(this.velocityX);
        buf.writeShort(this.velocityY);
        buf.writeShort(this.velocityZ);
    }

    public void apply(ClientPlayPacketListener clientPlayPacketListener) {
        clientPlayPacketListener.onEntitySpawn(this);
    }

    @Override
    public boolean isWritingErrorSkippable() {
        return super.isWritingErrorSkippable();
    }

    public int getId() {
        return this.id;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public double getVelocityX() {
        return (double)this.velocityX / 8000.0D;
    }

    public double getVelocityY() {
        return (double)this.velocityY / 8000.0D;
    }

    public double getVelocityZ() {
        return (double)this.velocityZ / 8000.0D;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getHeadYaw() {
        return (float)(this.headYaw * 360) / 256.0f;
    }

    public EntityType<?> getEntityTypeId() {
        return this.entityTypeId;
    }

    public int getEntityData() {
        return this.entityData;
    }
}
