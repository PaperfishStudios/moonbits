package net.paperfish.moonbits.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemAccessor {
    @Mutable
    @Accessor
    void setMaxCount(int maxCount);

    @Mutable
    @Accessor
    void setGroup(ItemGroup group);
}
