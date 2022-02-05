package net.paperfish.moonbits.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.paperfish.moonbits.MBItemGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Shadow @Final
    protected ItemGroup group;

    @Inject(method = "getGroup", at=@At("HEAD"), cancellable = true)
    public void onGetGroup(CallbackInfoReturnable<ItemGroup> cir) {
        if (this.group == null) {
            cir.setReturnValue(MBItemGroup.DEBUGGING);
        }
    }
}
