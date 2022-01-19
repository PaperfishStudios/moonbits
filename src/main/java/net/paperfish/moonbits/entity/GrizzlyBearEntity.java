package net.paperfish.moonbits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
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
import net.paperfish.moonbits.MBEntities;
import net.paperfish.moonbits.MBSounds;
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
    private static final TrackedData<Boolean> WARNING = DataTracker.registerData(PolarBearEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public GrizzlyBearEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new AttackGoal());
        goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.SALMON), false));
        goalSelector.add(4, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.HONEY_BOTTLE), false));
        goalSelector.add(5, new FollowParentGoal(this, 1.25D));
        goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
        goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
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
        this.dataTracker.startTracking(WARNING, false);
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
//        ItemStack itemStack = player.getStackInHand(hand);
//        if (itemStack.isOf(Items.BUCKET) && !this.isBaby()) {
//            player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
//            ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, Items.MILK_BUCKET.getDefaultStack());
//            player.setStackInHand(hand, itemStack2);
//            return ActionResult.success(this.world.isClient);
//        } else {
//            return super.interactMob(player, hand);
//        }
        return super.interactMob(player, hand);
    }

    @Nullable
    @Override
    public GrizzlyBearEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (GrizzlyBearEntity) MBEntities.GRIZZLY_BEAR.create(serverWorld);
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
    class AttackGoal
            extends MeleeAttackGoal {
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

    class GrizzlyBearRevengeGoal
            extends RevengeGoal {
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

    class ProtectBabiesGoal
            extends ActiveTargetGoal<PlayerEntity> {
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
