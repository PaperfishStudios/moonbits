package net.moonteam.moonbits.mixin;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.moonteam.moonbits.MBItems;

@Mixin(TurtleEntity.class)
public abstract class TurtleEntityMixin extends AnimalEntity implements Bucketable {
    private static final TrackedData<Boolean> FROM_BUCKET;

	protected TurtleEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    static {
		FROM_BUCKET = DataTracker.registerData(TurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	}

    @Inject(method="initDataTracker", at=@At("RETURN"))
    protected void onInitDataTracker(CallbackInfo ci) {
		this.dataTracker.startTracking(FROM_BUCKET, false);
	}

    //@Inject(method="interactMob", at=@At("HEAD"), cancellable = true)
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return(ActionResult)tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
	}
    
    public boolean isFromBucket() {
		return (Boolean)this.dataTracker.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean fromBucket) {
		this.dataTracker.set(FROM_BUCKET, fromBucket);
	}

    public void copyDataToStack(ItemStack stack) {
        NbtCompound nbtCompound = stack.getOrCreateNbt();
		Bucketable.copyDataToStack(this, stack);
        nbtCompound.putBoolean("IsBaby", isBaby());
        nbtCompound.putInt("Age", getBreedingAge());
	}

	public void copyDataFromNbt(NbtCompound nbt) {
        setBaby(true);
		if (nbt.getInt("Age") < 0)
			setBreedingAge(nbt.getInt("Age"));
		else
			setBreedingAge(-24000);
		Bucketable.copyDataFromNbt(this, nbt);
	}
    
    public ItemStack getBucketItem() {
		return new ItemStack(MBItems.BABY_TURTLE_BUCKET);
	}
    
    public SoundEvent getBucketedSound() {
		return SoundEvents.ITEM_BUCKET_FILL_FISH;
	}

	@Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
	private void onInit(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt, CallbackInfoReturnable<EntityData> info) {
		if (isFromBucket()) {
			info.setReturnValue(super.initialize(world, difficulty, spawnReason, entityData, entityNbt));
		}
	}

    //@Overwrite
    private static <T extends LivingEntity & Bucketable> Optional<ActionResult> tryBucket(PlayerEntity player, Hand hand, T entity) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() == Items.WATER_BUCKET && entity.isAlive() && entity.isBaby()) {
			entity.playSound(((Bucketable)entity).getBucketedSound(), 1.0F, 1.0F);
			ItemStack itemStack2 = ((Bucketable)entity).getBucketItem();
			((Bucketable)entity).copyDataToStack(itemStack2);
			ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, player, itemStack2, false);
			player.setStackInHand(hand, itemStack3);
			World world = entity.world;
			if (!world.isClient) {
				Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)player, itemStack2);
			}

			entity.discard();
			return Optional.of(ActionResult.success(world.isClient));
		} else {
			return Optional.empty();
		}
	}
}
