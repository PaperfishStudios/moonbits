package net.paperfish.moonbits.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.paperfish.moonbits.registry.MBItems;
import net.paperfish.moonbits.entity.MBBoatTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatDropsMixin {
    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void asItem(CallbackInfoReturnable<Item> ci) {
        if (((BoatEntity)(Object)this).getBoatType() == MBBoatTypes.LAMPROOT) {
            ci.setReturnValue(MBItems.JUNIPER_BOAT);
        }
        if (((BoatEntity)(Object)this).getBoatType() == MBBoatTypes.CEDAR) {
            ci.setReturnValue(MBItems.CEDAR_BOAT);
        }
    }
}
