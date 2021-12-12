package net.moonteam.moonbits.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.MBData;
import net.moonteam.moonbits.MBParticles;
import net.moonteam.moonbits.entity.EntityPacketUtils;
import net.moonteam.moonbits.entity.MBS2CSpawnPacket;

import java.util.UUID;


@Environment(EnvType.CLIENT)
public class MoonbitsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // cutout
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                MBBlocks.ROPE_LADDER,
                MBBlocks.IRON_LADDER,
                MBBlocks.BUTTERCUP,
                MBBlocks.FORGETMENOT,
                MBBlocks.WHITE_HYACINTH,
                MBBlocks.PINK_HYACINTH,
                MBBlocks.LIGHT_BLUE_HYACINTH,
                MBBlocks.RED_HYACINTH,
                MBBlocks.WILD_CARROTS,
                MBBlocks.WILD_POTATOES,
                MBBlocks.SEA_BEETS,
                MBBlocks.LAMPROOT,
                MBBlocks.CAVEBLOOM_VINE,
                MBBlocks.JACARANDA_LEAF_CARPET,
                MBBlocks.JACARANDA_SAPLING,
                MBBlocks.HANGING_JACARANDA_LEAVES,
                MBBlocks.HANGING_JACARANDA_LEAVES_PLANT,
                MBBlocks.JACARANDA_DOOR,
                MBBlocks.JACARANDA_TRAPDOOR,
                MBBlocks.HONEY_DOOR,
                MBBlocks.HONEY_TRAPDOOR,
                MBBlocks.GOLDEN_BIRCH_LEAF_CARPET,
                MBBlocks.GOLDEN_BIRCH_SAPLING,
                MBBlocks.GLASS_DOOR
        );

        MBData.registerBlockColours();
        MBParticles.registerParticleClient();

        MBEntityType.initEntityClient();

        // spawm packet thingy
//        ClientPlayNetworking.registerGlobalReceiver(MBS2CSpawnPacket.SPAWN_PACKET_ID, (client, handler, byteBuf, responseSender) ->
//        {
//            final EntityType<?> type = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
//            final UUID entityUUID = byteBuf.readUuid();
//            final int entityID = byteBuf.readVarInt();
//            final double x = byteBuf.readDouble();
//            final double y = byteBuf.readDouble();
//            final double z = byteBuf.readDouble();
//            final float pitch = (byteBuf.readByte() * 360) / 256.0F;
//            final float yaw = (byteBuf.readByte() * 360) / 256.0F;
//
//            client.execute(() ->
//            {
//                final MinecraftClient mClient = MinecraftClient.getInstance();
//                final ClientWorld world = mClient.world;
//                final Entity entity = type.create(world);
//                if (world != null && entity != null)
//                {
//                    entity.updatePosition(x, y, z);
//                    entity.updateTrackedPosition(x, y, z);
//                    entity.setPitch(pitch);
//                    entity.setYaw(yaw);
//                    entity.setId(entityID);
//                    entity.setUuid(entityUUID);
//                    world.addEntity(entityID, entity);
//                }
//            });
//        });
        
    }
    
}
