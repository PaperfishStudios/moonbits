package net.paperfish.moonbits.item;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Bucketable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BabyBucketItem extends EntityBucketItem {
	private final EntityType<?> entityType;

    public BabyBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
		this.entityType = type;
    }

	public void onEmptied(@Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos) {
		super.onEmptied(player, world, stack, pos);
	}
    
    private void spawnEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
		Entity entity = this.entityType.spawnFromItemStack(world, stack, (PlayerEntity)null, pos, SpawnReason.BUCKET, true, false);
		if (entity instanceof Bucketable bucketable) {
			if (!stack.getNbt().getBoolean("IsBaby")) {
				stack.getOrCreateNbt().putBoolean("IsBaby", true);
				stack.getNbt().putInt("Age", -24000);
			}
			bucketable.copyDataFromNbt(stack.getOrCreateNbt());
			bucketable.setFromBucket(true);
		}

	}
    
}
