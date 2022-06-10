package net.paperfish.moonbits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.*;
import net.paperfish.moonbits.registry.MBEntities;
import net.paperfish.moonbits.registry.MBItemTags;
import net.paperfish.moonbits.registry.MBItems;
import net.paperfish.moonbits.registry.MBSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.UUID;

public class GrizzlyBearEntity extends AnimalEntity implements Angerable, IAnimatable {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID targetUuid;
    private AnimationFactory factory = new AnimationFactory(this);
    public static final TrackedData<Boolean> ATTACKING = DataTracker.registerData(GrizzlyBearEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> WARNING = DataTracker.registerData(GrizzlyBearEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Integer> DEPENDENCE = DataTracker.registerData(GrizzlyBearEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public GrizzlyBearEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new AttackGoal());
        goalSelector.add(2, new FollowPlayerGoal(this, 1.0D, 1.25D));
        goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        goalSelector.add(5, new GrizzlyBearTemptGoal(this, 1.25D, Ingredient.fromTag(MBItemTags.BEAR_LIKES), false));
        goalSelector.add(6, new FollowParentGoal(this, 1.25D));
        goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
        //goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.add(8, new LookAroundGoal(this));
        targetSelector.add(1, new GrizzlyBearRevengeGoal());
        //targetSelector.add(2, new ProtectBabiesGoal());
        targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        targetSelector.add(4, new ActiveTargetGoal<>(this, SalmonEntity.class, 10, true, false, null));
        targetSelector.add(5, new UniversalAngerGoal<>(this, false));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACKING, false);
        this.dataTracker.startTracking(WARNING, false);;
        this.dataTracker.startTracking(DEPENDENCE, 0);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        readAngerFromNbt(this.world, nbt);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        writeAngerToNbt(nbt);
    }

    public static DefaultAttributeContainer.Builder createGrizzAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
    }

    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (entityData == null) {
            entityData = new PassiveData(1.0F);
        }

        return super.initialize(world, difficulty, spawnReason, (EntityData)entityData, entityNbt);
    }

    protected SoundEvent getAmbientSound() {
        if (this.isBaby()) {
            return MBSounds.BABY_GRIZZ_AMBIENT;
        }
        return MBSounds.GRIZZLY_AMBIENT;
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return isBaby() ? EntityDimensions.fixed(0.5F, 0.5F) : EntityDimensions.fixed(1.0F, 0.8F);
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return MBSounds.GRIZZLY_HURT;
    }

    protected SoundEvent getDeathSound() {
        return MBSounds.GRIZZLY_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(MBSounds.GRIZZLY_STEPS, 0.15F, 1.0F);
    }
    protected void playWarningSound() {
        this.playSound(MBSounds.GRIZZLY_WARN, 1.0f, this.getSoundPitch());
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public int getMaxHeadRotation() {
        return 50;
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        Moonbits.LOGGER.info("bear dependence: " + dataTracker.get(DEPENDENCE));
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isIn(MBItemTags.BEAR_LIKES) || (itemStack.isIn(MBItemTags.BEAR_EDIBLE) && dataTracker.get(DEPENDENCE) > 15)) {
            int i = this.getBreedingAge();
            if (!this.world.isClient && i == 0 && this.canEat()) {
                this.eat(player, hand, itemStack);
                if (random.nextBoolean() && dataTracker.get(DEPENDENCE) < 30) {
                    dataTracker.set(DEPENDENCE, dataTracker.get(DEPENDENCE) + 1);
                }
                //this.lovePlayer(player);
                this.emitGameEvent(GameEvent.ENTITY_INTERACT);
                Moonbits.LOGGER.info("dependence after food: " + dataTracker.get(DEPENDENCE));
                return ActionResult.SUCCESS;
            }
        }
        return super.interactMob(player, hand);
    }

    @Nullable
    @Override
    public GrizzlyBearEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return MBEntities.GRIZZLY_BEAR.create(serverWorld);
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return this.isBaby() ? dimensions.height * 0.95F : 1.3F;
    }

    public void chooseRandomAngerTime() {
        setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    public void setAngerTime(int ticks) {
        angerTime = ticks;
    }

    public int getAngerTime() {
        return angerTime;
    }

    public void setAngryAt(@Nullable UUID uuid) {
        targetUuid = uuid;
    }

    @Nullable
    public UUID getAngryAt() {
        return targetUuid;
    }


    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getAttacker();
            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof PersistentProjectileEntity)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.damage(source, amount);
        }
    }

    public boolean tryAttack(Entity target) {
        boolean bl = target.damage(DamageSource.mob(this), (float)((int)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)));
        if (bl) {
            this.dataTracker.set(ATTACKING, true);
            this.applyDamageEffects(this, target);
        }
        return bl;
    }
    public boolean isWarning() {
        return this.dataTracker.get(WARNING);
    }

    public void setWarning(boolean warning) {
        this.dataTracker.set(WARNING, warning);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grizzly.walk", true));
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grizzly.idle", true));
        }
        return PlayState.CONTINUE;
    }
    private <E extends IAnimatable> PlayState attack(AnimationEvent<E> event)
    {
        if (this.isWarning()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grizzly.attack", false));
            //if (event.getController().getAnimationState() == AnimationState.Stopped) {
                //this.dataTracker.set(ATTACKING, false);
            //}
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 2, this::predicate));
        data.addAnimationController(new AnimationController<>(this, "attack", 2, this::attack));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal() {
            super(GrizzlyBearEntity.this, 1.25, true);
        }

        @Override
        protected void attack(LivingEntity target, double squaredDistance) {
            double d = this.getSquaredMaxAttackDistance(target);
            if (squaredDistance <= d && this.isCooledDown()) {
                this.resetCooldown();
                this.mob.tryAttack(target);
                GrizzlyBearEntity.this.setWarning(false);
            } else if (squaredDistance <= d * 2.0) {
                if (this.isCooledDown()) {
                    GrizzlyBearEntity.this.setWarning(false);
                    this.resetCooldown();
                }
                if (this.getCooldown() <= 10) {
                    GrizzlyBearEntity.this.setWarning(true);
                    GrizzlyBearEntity.this.playWarningSound();
                }
            } else {
                this.resetCooldown();
                GrizzlyBearEntity.this.setWarning(false);
            }
        }

        @Override
        public void stop() {
            GrizzlyBearEntity.this.setWarning(false);
            super.stop();
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return 4.0f + entity.getWidth();
        }
    }

    class FollowPlayerGoal extends Goal {
        public static final int field_30209 = 8;
        public static final int field_30210 = 4;
        public static final int field_30211 = 3;
        private final AnimalEntity animal;
        @Nullable
        private AnimalEntity targetPlayer;
        private final double speed;
        private final double speedB;
        private int delay;

        public FollowPlayerGoal(AnimalEntity animal, double speed, double speedB) {
            this.animal = animal;
            this.speed = speed;
            this.speedB = speedB;
        }

        @Override
        public boolean canStart() {
            if (this.animal.getBreedingAge() < 0 || dataTracker.get(DEPENDENCE) < 10) {
                return false;
            }
            List<? extends AnimalEntity> list = this.animal.world.getNonSpectatingEntities(this.animal.getClass(), this.animal.getBoundingBox().expand(8.0, 4.0, 8.0));
            AnimalEntity potentialTarget = null;
            double maxDistance = Double.MAX_VALUE;
            for (AnimalEntity animalEntity2 : list) {
                double e = this.animal.squaredDistanceTo(animalEntity2);
                if (e > maxDistance) continue;
                maxDistance = e;
                potentialTarget = animalEntity2;
            }
            if (potentialTarget == null) {
                return false;
            }
            if (maxDistance < 9.0) {
                return false;
            }
            this.targetPlayer = potentialTarget;
            return true;
        }

        @Override
        public boolean shouldContinue() {
            if (!this.targetPlayer.isAlive()) {
                return false;
            }
            double d = this.animal.squaredDistanceTo(this.targetPlayer);
            return !(d < 9.0) && !(d > 256.0);
        }

        @Override
        public void start() {
            this.delay = 0;
        }

        @Override
        public void stop() {
            this.targetPlayer = null;
        }

        @Override
        public void tick() {
            if (--this.delay > 0) {
                return;
            }
            this.delay = this.getTickCount(10);
            this.animal.getNavigation().startMovingTo(this.targetPlayer, dataTracker.get(DEPENDENCE) < 15 ? this.speed : this.speedB);
        }
    }

    class GrizzlyBearTemptGoal extends TemptGoal {
        private int cooldown;
        private static final TargetPredicate TEMPTING_ENTITY_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility();
        private final TargetPredicate predicate;
        private final TargetPredicate dependant;

        public GrizzlyBearTemptGoal(PathAwareEntity entity, double speed, Ingredient food, boolean canBeScared) {
            super(entity, speed, food, canBeScared);
            this.predicate = TEMPTING_ENTITY_PREDICATE.copy().setPredicate((a) -> a.getMainHandStack().isIn(MBItemTags.BEAR_LIKES) || a.getOffHandStack().isIn(MBItemTags.BEAR_LIKES));
            this.dependant = TEMPTING_ENTITY_PREDICATE.copy().setPredicate((a) -> a.getMainHandStack().isIn(MBItemTags.BEAR_EDIBLE) || a.getOffHandStack().isIn(MBItemTags.BEAR_EDIBLE));
        }

        @Override
        public boolean canStart() {
            if (dataTracker.get(DEPENDENCE) < 1) {
                return false;
            }
            if (this.cooldown > 0) {
                --this.cooldown;
                return false;
            }
            this.closestPlayer = dataTracker.get(DEPENDENCE) > 15 ? this.mob.world.getClosestPlayer(this.dependant, this.mob) : this.mob.world.getClosestPlayer(this.predicate, this.mob);
            return this.closestPlayer != null;
        }

        @Override
        protected boolean canBeScared() {
            return dataTracker.get(DEPENDENCE) < 25;
        }

        @Override
        public void tick() {
            if (dataTracker.get(DEPENDENCE) < 5) {
                this.mob.getLookControl().lookAt(this.closestPlayer, this.mob.getMaxHeadRotation() + 20, this.mob.getMaxLookPitchChange());
            }
            else {
                super.tick();
            }
        }
    }

    class GrizzlyBearRevengeGoal extends RevengeGoal {
        public GrizzlyBearRevengeGoal() {
            super(GrizzlyBearEntity.this);
        }

        @Override
        public void start() {
            super.start();
            if (GrizzlyBearEntity.this.isBaby()) {
                this.callSameTypeForRevenge();
                this.stop();
            }
        }

        @Override
        protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
            if (mob instanceof GrizzlyBearEntity && !mob.isBaby()) {
                super.setMobEntityTarget(mob, target);
            }
        }
    }

    class ProtectBabiesGoal extends ActiveTargetGoal<PlayerEntity> {
        public ProtectBabiesGoal() {
            super(GrizzlyBearEntity.this, PlayerEntity.class, 20, true, true, null);
        }

        @Override
        public boolean canStart() {
            if (GrizzlyBearEntity.this.isBaby()) {
                return false;
            }
            if (super.canStart()) {
                List<GrizzlyBearEntity> list = GrizzlyBearEntity.this.world.getNonSpectatingEntities(GrizzlyBearEntity.class, GrizzlyBearEntity.this.getBoundingBox().expand(8.0, 4.0, 8.0));
                for (GrizzlyBearEntity grizzlyBearEntity : list) {
                    if (!grizzlyBearEntity.isBaby()) continue;
                    return true;
                }
            }
            return false;
        }

        @Override
        protected double getFollowRange() {
            return super.getFollowRange() * 0.5;
        }
    }

    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }
}
