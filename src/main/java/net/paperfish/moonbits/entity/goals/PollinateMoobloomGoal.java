package net.paperfish.moonbits.entity.goals;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.paperfish.moonbits.MoonbitsMain;
import net.paperfish.moonbits.entity.MoobloomEntity;
import net.paperfish.moonbits.mixin.BeeEntityAccessor;
import net.paperfish.moonbits.mixin.EntityAccessor;
import net.paperfish.moonbits.mixin.MobEntityAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Optional;

public class PollinateMoobloomGoal extends Goal {
    public final BeeEntity bee;
    private static final int field_30300 = 400;
    private static final int field_30301 = 20;
    private static final int field_30302 = 60;
    //private final Predicate<LivingEntity> moobloomPredicate;
    private final TargetPredicate moobloomPredicate = TargetPredicate.createNonAttackable().setPredicate(MoobloomEntity -> true).setBaseMaxDistance(5);
    private static final double field_30303 = 0.1;
    private static final int field_30304 = 25;
    private static final float field_30305 = 0.35f;
    private static final float field_30306 = 0.6f;
    private static final float field_30307 = 0.33333334f;
    private int pollinationTicks;
    private int lastPollinationTick;
    private boolean running;
    @Nullable
    private Vec3d nextTarget;
    private int ticks;
    private static final int field_30308 = 600;

    @Nullable
    public MoobloomEntity targetMoobloom;

    public PollinateMoobloomGoal(Entity bee) {
        this.bee = (BeeEntity)bee;
        //this.moobloomPredicate = entity -> entity instanceof MoobloomEntity;
        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        return this.canBeeStart() && !bee.hasAngerTime();
    }

    public boolean canBeeStart() {
        if (((BeeEntityAccessor)bee).getTicksUntilCanPollinate() > 0) {
            return false;
        }
        if (bee.hasNectar()) {
            return false;
        }
        if (bee.world.isRaining()) {
            return false;
        }
        Optional<BlockPos> optional = this.getFlower();
        if (optional.isPresent()) {
            ((BeeEntityAccessor)bee).setFlowerPos(optional.get());
            BlockPos flower = ((BeeEntityAccessor)bee).getFlowerPos();
            ((MobEntityAccessor)bee).getNavigation().startMovingTo((double)flower.getX() + 0.5, (double)flower.getY() + 0.5, (double)flower.getZ() + 0.5, 1.2f);
            return true;
        }
        ((BeeEntityAccessor)bee).setTicksUntilCanPollinate(MathHelper.nextInt(((EntityAccessor)bee).getRandom(), 20, 60));
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return this.canBeeContinue() && !bee.hasAngerTime();
    }

    public boolean canBeeContinue() {
        if (!this.running) {
            return false;
        }
        if (!bee.hasFlower()) {
            return false;
        }
        if (bee.world.isRaining()) {
            return false;
        }
        if (this.completedPollination()) {
            return ((EntityAccessor)bee).getRandom().nextFloat() < 0.2f;
        }
        if (bee.age % 20 == 0 && !((BeeEntityAccessor)bee).callIsFlowers(((BeeEntityAccessor)bee).getFlowerPos())) {
            ((BeeEntityAccessor)bee).setFlowerPos(null);
            targetMoobloom = null;
            return false;
        }
        return true;
    }

    private boolean completedPollination() {
        return this.pollinationTicks > 400;
    }

    boolean isRunning() {
        return this.running;
    }

    void cancel() {
        this.running = false;
    }

    @Override
    public void start() {
        this.pollinationTicks = 0;
        this.ticks = 0;
        this.lastPollinationTick = 0;
        this.running = true;
        bee.resetPollinationTicks();
    }

    @Override
    public void stop() {
        if (this.completedPollination()) {
            ((BeeEntityAccessor)bee).callSetHasNectar(true);
            if (targetMoobloom != null) {
                targetMoobloom.recievePollen();
            }
        }
        this.running = false;
        ((MobEntityAccessor)bee).getNavigation().stop();
        ((BeeEntityAccessor)bee).setTicksUntilCanPollinate(200);
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        ++this.ticks;
        if (this.ticks > 600) {
            ((BeeEntityAccessor)bee).setFlowerPos(null);
            targetMoobloom = null;
            return;
        }
        Vec3d vec3d = Vec3d.ofBottomCenter(((BeeEntityAccessor)bee).getFlowerPos()).add(0.0, 0.6f, 0.0);
        if (vec3d.distanceTo(bee.getPos()) > 1.0) {
            this.nextTarget = vec3d;
            this.moveToNextTarget();
            return;
        }
        if (this.nextTarget == null) {
            this.nextTarget = vec3d;
        }
        boolean bl = bee.getPos().distanceTo(this.nextTarget) <= 0.1;
        boolean bl2 = true;
        if (!bl && this.ticks > 600) {
            ((BeeEntityAccessor)bee).setFlowerPos(null);
            targetMoobloom = null;
            return;
        }
        if (bl) {
            boolean bl3;
            boolean bl4 = bl3 = ((EntityAccessor)bee).getRandom().nextInt(25) == 0;
            if (bl3) {
                this.nextTarget = new Vec3d(vec3d.getX() + (double)this.getRandomOffset(), vec3d.getY(), vec3d.getZ() + (double)this.getRandomOffset());
                ((MobEntityAccessor)bee).getNavigation().stop();
            } else {
                bl2 = false;
            }
            bee.getLookControl().lookAt(vec3d.getX(), vec3d.getY(), vec3d.getZ());
        }
        if (bl2) {
            this.moveToNextTarget();
        }
        ++this.pollinationTicks;
        if (((EntityAccessor)bee).getRandom().nextFloat() < 0.05f && this.pollinationTicks > this.lastPollinationTick + 60) {
            this.lastPollinationTick = this.pollinationTicks;
            bee.playSound(SoundEvents.ENTITY_BEE_POLLINATE, 1.0f, 1.0f);
        }
    }

    private void moveToNextTarget() {
        bee.getMoveControl().moveTo(this.nextTarget.getX(), this.nextTarget.getY(), this.nextTarget.getZ(), 0.35f);
    }

    private float getRandomOffset() {
        return (((EntityAccessor)bee).getRandom().nextFloat() * 2.0f - 1.0f) * 0.33333334f;
    }

    private Optional<BlockPos> getFlower() {
        return this.findFlower(this.moobloomPredicate, 5.0);
    }

    private Optional<BlockPos> findFlower(TargetPredicate predicate, double searchDistance) {
        BlockPos blockPos = bee.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        MoobloomEntity a = bee.world.getClosestEntity(bee.world.getEntitiesByClass(MoobloomEntity.class, bee.getBoundingBox().expand(5.0D, 4.0D, 5.0D), (livingEntity) -> true),
                moobloomPredicate, bee, bee.getX(), bee.getEyeY(), bee.getZ());

        if (a != null) {
            mutable.set(a.getBlockPos().add(0, 2.1, 0));
        }

        if (blockPos.isWithinDistance(mutable, searchDistance)) {
            assert a != null;
            MoonbitsMain.LOGGER.info("bee found: " + a.getMoobloomType().toString());
            bee.setFlowerPos(mutable);
            targetMoobloom = a;
            return Optional.of(mutable);
        }
        return Optional.empty();
    }
}
