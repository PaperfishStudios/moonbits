package net.paperfish.moonbits.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class FallingParticle extends SpriteBillboardParticle {
    private final Fluid fluid;

    FallingParticle(ClientWorld world, double x, double y, double z, Fluid fluid) {
        super(world, x, y, z);
        this.setBoundingBoxSpacing(0.01F, 0.01F);
        this.gravityStrength = 0.06F;
        this.fluid = fluid;
    }

    protected Fluid getFluid() {
        return this.fluid;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.updateAge();
        if (!this.dead) {
            this.velocityY -= (double)this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            this.updateVelocity();
            if (!this.dead) {
                this.velocityX *= 0.9800000190734863D;
                this.velocityY *= 0.9800000190734863D;
                this.velocityZ *= 0.9800000190734863D;
                BlockPos blockPos = new BlockPos(this.x, this.y, this.z);
                FluidState fluidState = this.world.getFluidState(blockPos);
                if (fluidState.getFluid() == this.fluid && this.y < (double)((float)blockPos.getY() + fluidState.getHeight(this.world, blockPos))) {
                    this.markDead();
                }

            }
        }
    }

    protected void updateAge() {
        if (this.maxAge-- <= 0) {
            this.markDead();
        }

    }

    protected void updateVelocity() {
    }

    @Environment(EnvType.CLIENT)
    public static class FallingLeafFactory implements ParticleFactory<DefaultParticleType> {
        protected final SpriteProvider spriteProvider;
        private final Random random;

        public FallingLeafFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
            this.random = new Random();
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            int j = (int)(64.0F / MathHelper.nextBetween(this.random, 0.1F, 0.9F));
            FallingParticle fallingParticle = new FallingParticle.Falling(clientWorld, d, e, f, Fluids.EMPTY, j);
            fallingParticle.gravityStrength = 0.0005F;
            //fallingParticle.setColor(0.32F, 0.5F, 0.22F);
            fallingParticle.setSprite(this.spriteProvider);
            return fallingParticle;
        }
    }
    @Environment(EnvType.CLIENT)
    public static class FallingSporeFactory implements ParticleFactory<DefaultParticleType> {
        protected final SpriteProvider spriteProvider;
        private final Random random;

        public FallingSporeFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
            this.random = new Random();
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            int j = (int)(64.0F / MathHelper.nextBetween(this.random, 0.1F, 0.9F));
            FallingParticle fallingParticle = new FallingParticle.Falling(clientWorld, d, e, f, Fluids.EMPTY, j);
            fallingParticle.gravityStrength = 0.0005F;
            //fallingParticle.setColor(0.32F, 0.5F, 0.22F);
            fallingParticle.setSprite(this.spriteProvider);
            return fallingParticle;
        }
    }

    @Environment(EnvType.CLIENT)
    private static class Falling extends FallingParticle {
        Falling(ClientWorld clientWorld, double d, double e, double f, Fluid fluid) {
            this(clientWorld, d, e, f, fluid, (int)(64.0D / (Math.random() * 0.8D + 0.2D)));
        }

        Falling(ClientWorld world, double x, double y, double z, Fluid fluid, int maxAge) {
            super(world, x, y, z, fluid);
            this.maxAge = maxAge;
        }

        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
            }

        }

        protected void updateAge() {
            // test
        }
    }
}
