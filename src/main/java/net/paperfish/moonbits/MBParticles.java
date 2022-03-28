package net.paperfish.moonbits;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MBParticles {
    public static final DefaultParticleType FALLING_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_OAK_LEAF = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_WATTLE = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_SPORES = FabricParticleTypes.simple();
    
    public static final DefaultParticleType DRIPPING_SYRUP = FabricParticleTypes.simple();
    public static final DefaultParticleType FALLING_SYRUP = FabricParticleTypes.simple();
    public static final DefaultParticleType LANDING_SYRUP = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "falling_leaf"), FALLING_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "falling_oak_leaf"), FALLING_OAK_LEAF);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "falling_wattle"), FALLING_WATTLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "falling_spores"), FALLING_SPORES);

        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "dripping_syrup"), DRIPPING_SYRUP);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "falling_syrup"), FALLING_SYRUP);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Moonbits.MODID, "landing_syrup"), LANDING_SYRUP);
    }


}
