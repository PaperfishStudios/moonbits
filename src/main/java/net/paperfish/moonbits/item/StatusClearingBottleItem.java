package net.paperfish.moonbits.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class StatusClearingBottleItem extends HoneyBottleItem {
	public final StatusEffect effect;

	public StatusClearingBottleItem(StatusEffect effect, Settings settings) {
		super(settings);
		this.effect = effect;
	}
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		super.finishUsing(stack, world, user);
		if (user instanceof ServerPlayerEntity serverPlayerEntity) {
			Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
			serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
		}

		if (!world.isClient) {
			user.removeStatusEffect(effect);
		}

		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
				ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
				PlayerEntity playerEntity = (PlayerEntity)user;
				if (!playerEntity.getInventory().insertStack(itemStack)) {
					playerEntity.dropItem(itemStack, false);
				}
			}

			return stack;
		}
	}
}
