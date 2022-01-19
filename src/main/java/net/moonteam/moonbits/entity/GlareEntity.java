package net.moonteam.moonbits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.NoWaterTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.moonteam.moonbits.MBData;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class GlareEntity extends PassiveEntity implements IAnimatable {
    private static final TrackedData<Byte> GLARE_FLAGS = DataTracker.registerData(GlareEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final int NEAR_TARGET_FLAG = 2;
    public static final TrackedData<Boolean> TANTRUM = DataTracker.registerData(GlareEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    int lastAnim = 0; // testing animation stuffs
    private AnimationFactory factory = new AnimationFactory(this);
    private TantrumGoal tantrumGoal;
    private MoveToDarknessGoal moveToDarknessGoal;
    final int scanRange = 32;
    private float currentPitch;
    private float lastPitch;
    @Nullable
    BlockPos targetPos;
    private int ticksInsideWater;

    public GlareEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
        //this.lookControl = new GlareLookControl(this);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0f);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0f);
        this.setPathfindingPenalty(PathNodeType.COCOA, -1.0f);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0f);
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).isAir()) {
            return 10.0f;
        }
        return 0.0f;
    }

    protected void initGoals() {
        goalSelector.add(1, new TemptGoal(this, 1.25, Ingredient.fromTag(MBData.GLARE_LIKES), false));
        this.tantrumGoal = new TantrumGoal();
        goalSelector.add(2, this.tantrumGoal);
        this.moveToDarknessGoal = new MoveToDarknessGoal();
        goalSelector.add(3, this.moveToDarknessGoal);
        goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        goalSelector.add(9, new SwimGoal(this));
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GLARE_FLAGS, (byte)0);
        this.dataTracker.startTracking(TANTRUM, false);
    }
    private void setGlareFlag(int bit, boolean value) {
        if (value) {
            this.dataTracker.set(GLARE_FLAGS, (byte)(this.dataTracker.get(GLARE_FLAGS) | bit));
        } else {
            this.dataTracker.set(GLARE_FLAGS, (byte)(this.dataTracker.get(GLARE_FLAGS) & ~bit));
        }
    }

    private boolean getGlareFlag(int location) {
        return (this.dataTracker.get(GLARE_FLAGS) & location) != 0;
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0D)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void tick() {
        super.tick();
        this.updateBodyPitch();
    }

    @Override
    protected void mobTick() {
        this.ticksInsideWater = this.isInsideWaterOrBubbleColumn() ? ++this.ticksInsideWater : 0;
        if (this.ticksInsideWater > 20) {
            this.damage(DamageSource.DROWN, 1.0f);
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    void startMovingTo(BlockPos pos) {
        Vec3d vec3d2;
        Vec3d vec3d = Vec3d.ofBottomCenter(pos);
        int i = 0;
        BlockPos blockPos = this.getBlockPos();
        int j = (int)vec3d.y - blockPos.getY();
        if (j > 2) {
            i = 4;
        } else if (j < -2) {
            i = -4;
        }
        int k = 6;
        int l = 8;
        int m = blockPos.getManhattanDistance(pos);
        if (m < 15) {
            k = m / 2;
            l = m / 2;
        }
        if ((vec3d2 = NoWaterTargeting.find(this, k, l, i, vec3d, 0.3141592741012573)) == null) {
            return;
        }
        this.navigation.setRangeMultiplier(0.5f);
        this.navigation.startMovingTo(vec3d2.x, vec3d2.y, vec3d2.z, 1.0);
    }

    public float getBodyPitch(float tickDelta) {
        return MathHelper.lerp(tickDelta, this.lastPitch, this.currentPitch);
    }

    private void updateBodyPitch() {
        this.lastPitch = this.currentPitch;
        this.currentPitch = this.isNearTarget() ? Math.min(1.0f, this.currentPitch + 0.2f) : Math.max(0.0f, this.currentPitch - 0.24f);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world){

            @Override
            public boolean isValidPosition(BlockPos pos) {
                return !this.world.getBlockState(pos.down()).isAir();
            }

            @Override
            public void tick() {
                super.tick();
            }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(false);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Nullable
    public BlockPos getTargetPos() {
        return this.targetPos;
    }

    public boolean hasTarget() {
        return this.targetPos != null;
    }

    public void setTargetPos(BlockPos pos) {
        this.targetPos = pos;
    }

    public boolean getTantrum() {
        return this.dataTracker.get(TANTRUM);
    }

    public void setTantrum(boolean bool) {
        this.dataTracker.set(TANTRUM, bool);
    }

    private boolean isNearTarget() {
        return this.getGlareFlag(NEAR_TARGET_FLAG);
    }

    private void setNearTarget(boolean nearTarget) {
        this.setGlareFlag(NEAR_TARGET_FLAG, nearTarget);
    }

    boolean isTooFar(BlockPos pos) {
        return !this.isWithinDistance(pos, scanRange);
    }

    boolean isWithinDistance(BlockPos pos, int distance) {
        return pos.isWithinDistance(this.getBlockPos(), distance);
    }

    boolean isDark(BlockPos pos) {
        if (pos == null) {
            return false;
        }
        return this.world.getLightLevel(pos) == 0;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if (this.getTantrum()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glare.shiver", true));
        }
        else if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glare.move", false));
            lastAnim = 1;
        }
        else if (lastAnim == 1) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glare.stop", false));
            lastAnim = 0;
        }
        else if (event.getController().getAnimationState() == AnimationState.Stopped) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.glare.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    class GlareLookControl extends LookControl {
        GlareLookControl(MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            if (GlareEntity.this.tantrumGoal.isRunning()) {
                return;
            }
            super.tick();
        }

        @Override
        protected boolean shouldStayHorizontal() {
            return !GlareEntity.this.tantrumGoal.isRunning();
        }
    }

    public class MoveToDarknessGoal extends Goal {
        private static final int MAX_NAV_TICKS = 600;
        int ticks;

        MoveToDarknessGoal() {
            this.ticks = GlareEntity.this.world.random.nextInt(10);
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            assert GlareEntity.this.targetPos != null;
            return !GlareEntity.this.hasPositionTarget() && GlareEntity.this.isDark(GlareEntity.this.targetPos) && !GlareEntity.this.isWithinDistance(GlareEntity.this.targetPos, 2);
        }

        @Override
        public boolean shouldContinue() {
            return this.canStart();
        }

        @Override
        public void start() {
            this.ticks = 0;
            super.start();
        }

        @Override
        public void stop() {
            this.ticks = 0;
            GlareEntity.this.navigation.stop();
            GlareEntity.this.navigation.resetRangeMultiplier();
        }

        @Override
        public void tick() {
            if (GlareEntity.this.targetPos == null) {
                return;
            }
            ++this.ticks;
            if (this.ticks > this.getTickCount(MAX_NAV_TICKS)) {
                GlareEntity.this.targetPos = null;
                return;
            }
            if (GlareEntity.this.navigation.isFollowingPath()) {
                return;
            }
            if (GlareEntity.this.isTooFar(GlareEntity.this.targetPos)) {
                GlareEntity.this.targetPos = null;
                return;
            }
            GlareEntity.this.startMovingTo(GlareEntity.this.targetPos);
        }
    }

    class TantrumGoal extends Goal {
        private static final int field_30300 = 400;
        private static final int field_30301 = 20;
        private static final int field_30302 = 60;
        private final Predicate<BlockPos> flowerPredicate;
        private static final double field_30303 = 0.1;
        private static final int field_30304 = 25;
        private static final float field_30305 = 0.35f;
        private static final float field_30306 = 0.6f;
        private static final float field_30307 = 0.33333334f;
        private boolean running;
        @Nullable
        private Vec3d nextTarget;
        private int ticks;
        private static final int MAX_NAV_TICKS = 600;
        final double SEARCH_DISTANCE = 5.0;

        TantrumGoal() {
            this.flowerPredicate = pos -> GlareEntity.this.world.getLightLevel(pos) == 0;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        @Override
        public boolean canStart() {
            Optional<BlockPos> optional = this.getDarkness();
            if (optional.isPresent()) {
                GlareEntity.this.targetPos = optional.get();
                GlareEntity.this.navigation.startMovingTo((double)GlareEntity.this.targetPos.getX() + 0.5, (double)GlareEntity.this.targetPos.getY() + 0.5, (double)GlareEntity.this.targetPos.getZ() + 0.5, 1.2f);
                return true;
            }
            return false;
        }

        @Override
        public boolean shouldContinue() {
            if (!this.running) {
                return false;
            }
            if (!GlareEntity.this.hasTarget()) {
                return false;
            }
            if (GlareEntity.this.world.isRaining()) {
                return false;
            }
            if (GlareEntity.this.age % 20 == 0 && !GlareEntity.this.isDark(GlareEntity.this.targetPos)) {
                GlareEntity.this.targetPos = null;
                return false;
            }
            return true;
        }

        boolean isRunning() {
            return this.running;
        }

        void cancel() {
            this.running = false;
        }

        @Override
        public void start() {
            this.ticks = 0;
            this.running = true;
            GlareEntity.this.setTantrum(true);
        }

        @Override
        public void stop() {
            this.running = false;
            GlareEntity.this.navigation.stop();
            GlareEntity.this.setTantrum(false);
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            ++this.ticks;
            if (this.ticks > MAX_NAV_TICKS) {
                GlareEntity.this.targetPos = null;
                return;
            }
            Vec3d vec3d = Vec3d.ofBottomCenter(GlareEntity.this.targetPos).add(0.0, 0.6f, 0.0);
            if (vec3d.distanceTo(GlareEntity.this.getPos()) > 1.0) {
                this.nextTarget = vec3d;
                this.moveToNextTarget();
                return;
            }
            if (this.nextTarget == null) {
                this.nextTarget = vec3d;
            }
            boolean bl = GlareEntity.this.getPos().distanceTo(this.nextTarget) <= 0.1;
            boolean bl2 = true;
            if (!bl && this.ticks > MAX_NAV_TICKS) {
                GlareEntity.this.targetPos = null;
                return;
            }
            if (bl) {
                boolean bl3;
                boolean bl4 = bl3 = GlareEntity.this.random.nextInt(25) == 0;
                if (bl3) {
                    this.nextTarget = new Vec3d(vec3d.getX() + (double)this.getRandomOffset(), vec3d.getY(), vec3d.getZ() + (double)this.getRandomOffset());
                    GlareEntity.this.navigation.stop();
                } else {
                    bl2 = false;
                }
                GlareEntity.this.getLookControl().lookAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());
            }
            if (bl2) {
                this.moveToNextTarget();
            }
        }

        private void moveToNextTarget() {
            GlareEntity.this.getMoveControl().moveTo(this.nextTarget.getX(), this.nextTarget.getY(), this.nextTarget.getZ(), 0.35f);
        }

        private float getRandomOffset() {
            return (GlareEntity.this.random.nextFloat() * 2.0f - 1.0f) * 0.33333334f;
        }

        private Optional<BlockPos> getDarkness() {
            return this.findDarkness();
        }

        private Optional<BlockPos> findDarkness() {
            BlockPos blockPos = GlareEntity.this.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            int i = 0;
            while ((double)i <= SEARCH_DISTANCE) {
                int j = 0;
                while ((double)j < SEARCH_DISTANCE) {
                    int k = 0;
                    while (k <= j) {
                        int l;
                        int n = l = k < j && k > -j ? j : 0;
                        while (l <= j) {
                            mutable.set(blockPos, k, i - 1, l);
                            if (blockPos.isWithinDistance(mutable, SEARCH_DISTANCE) && world.getLightLevel(blockPos) == 0) {
                                return Optional.of(mutable);
                            }
                            l = l > 0 ? -l : 1 - l;
                        }
                        k = k > 0 ? -k : 1 - k;
                    }
                    ++j;
                }
                i = i > 0 ? -i : 1 - i;
            }
            return Optional.empty();
        }
    }
}
