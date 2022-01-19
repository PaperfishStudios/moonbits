package net.paperfish.moonbits;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.particle.FallingParticle;

public class MBParticles {
    public static final DefaultParticleType FALLING_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_JACARANDA = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MoonbitsMain.MOD_ID, "falling_leaf"), FALLING_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(MoonbitsMain.MOD_ID, "falling_jacaranda"), FALLING_JACARANDA);
    }

    public static void registerParticleClient() {
        // adds the particle texture to the texture atlas
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(MoonbitsMain.MOD_ID, "particle/falling_leaf"));
            registry.register(new Identifier(MoonbitsMain.MOD_ID, "particle/falling_jacaranda"));
        }));
        ParticleFactoryRegistry.getInstance().register(FALLING_LEAF, FallingParticle.FallingLeafFactory::new);
        ParticleFactoryRegistry.getInstance().register(FALLING_JACARANDA, FallingParticle.FallingLeafFactory::new);

    }
}
