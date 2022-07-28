package net.paperfish.moonbits.mixin;

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
import net.minecraft.entity.passive.StriderEntity;
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
import net.paperfish.moonbits.registry.MBItems;

@Mixin(StriderEntity.class)
public abstract class StriderEntityMixin extends AnimalEntity implements Bucketable {
	private static final TrackedData<Boolean> FROM_BUCKET;

    protected StriderEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    static {
		FROM_BUCKET = DataTracker.registerData(StriderEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	}

    @Inject(method="initDataTracker", at=@At("RETURN"))
    protected void onInitDataTracker(CallbackInfo ci) {
		this.dataTracker.startTracking(FROM_BUCKET, false);
	}

    @Inject(method="interactMob", at=@At("HEAD"), cancellable = true)
    public void onInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() == Items.LAVA_BUCKET && this.isAlive() && this.isBaby()) {
            cir.setReturnValue(tryBucket(player, hand, this).orElse(super.interactMob(player, hand)));
        }
	}

    public boolean isFromBucket() {
		return this.dataTracker.get(FROM_BUCKET);
	}

	public void setFromBucket(boolean fromBucket) {
		this.dataTracker.set(FROM_BUCKET, fromBucket);
	}

    public void copyDataToStack(ItemStack stack) {
		Bucketable.copyDataToStack(this, stack);
        NbtCompound nbtCompound = stack.getOrCreateNbt();
        nbtCompound.putBoolean("IsBaby", isBaby());
        nbtCompound.putInt("Age", getBreedingAge());
	}

	public void copyDataFromNbt(NbtCompound nbt) {
		Bucketable.copyDataFromNbt(this, nbt);
        setBaby(true); // maybe this doesnt need to be there?
		if (nbt.contains("Age"))
			setBreedingAge(nbt.getInt("Age"));
		else
			setBreedingAge(-24000);
	}

	@Inject(method="writeCustomDataToNbt", at=@At("RETURN"))
	public void onWriteNbt(NbtCompound nbt, CallbackInfo ci) {
		nbt.putBoolean("FromBucket", this.isFromBucket());
	}
	@Inject(method="readCustomDataFromNbt", at=@At("RETURN"))
	public void onReadNbt(NbtCompound nbt, CallbackInfo ci) {
		this.setFromBucket(nbt.getBoolean("FromBucket"));
	}

    public ItemStack getBucketItem() {
		return new ItemStack(MBItems.BABY_STRIDER_BUCKET);
	}

    public SoundEvent getBucketFillSound() {
		return SoundEvents.ITEM_BUCKET_FILL_FISH;
	}

	@Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
	private void onInit(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt, CallbackInfoReturnable<EntityData> info) {
		if (spawnReason == SpawnReason.BUCKET) {
			info.setReturnValue(entityData);
		}
	}

    //@Overwrite
    private static <T extends LivingEntity & Bucketable> Optional<ActionResult> tryBucket(PlayerEntity player, Hand hand, T entity) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.getItem() == Items.LAVA_BUCKET && entity.isAlive() && entity.isBaby()) {
			entity.playSound(entity.getBucketedSound(), 1.0F, 1.0F);
			ItemStack itemStack2 = entity.getBucketItem();
			entity.copyDataToStack(itemStack2);
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
