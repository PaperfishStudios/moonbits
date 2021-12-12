package net.moonteam.moonbits.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StewItem.class)
public class MushroomStewItemMixin extends Item {

    public MushroomStewItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method="finishUsing", at = @At("HEAD"), cancellable = true)
    public void onFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity && !((PlayerEntity) user).getAbilities().creativeMode) {
            if (stack.getCount() > 1) {
                //stack.decrement(1);
                ((PlayerEntity) user).getInventory().offerOrDrop(new ItemStack(Items.BOWL));
                cir.setReturnValue(stack);
            } else {
                cir.setReturnValue(new ItemStack(Items.BOWL));
            }
        } else {
            cir.setReturnValue(stack);
        }
    }
}
