package net.moonteam.moonbits.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moonteam.moonbits.MBBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Objects;

@Mixin(Items.class)
public class RestackingMixin {

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At(value="HEAD"), cancellable = true)
    private static void onRegister(String id, Item item, CallbackInfoReturnable<Item> cir) {
        if (Objects.equals(id, "blaze_rod")) {
            Item a = (Item)new AliasedBlockItem(MBBlocks.BLAZE_ROD, new Item.Settings().group(ItemGroup.MATERIALS));
            ((BlockItem)a).appendBlocks(Item.BLOCK_ITEMS, a);
            cir.setReturnValue((Item)Registry.register(Registry.ITEM, new Identifier("blaze_rod"), a));
        }
        if (Objects.equals(id, "sweet_berries")) {
            cir.setReturnValue((Item)Registry.register(Registry.ITEM, new Identifier("sweet_berries"), new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SWEET_BERRIES))));
        }
        if (Objects.equals(id, "glow_berries")) {
            cir.setReturnValue((Item)Registry.register(Registry.ITEM, new Identifier("glow_berries"), new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.GLOW_BERRIES))));
        }
    }

    @Inject(method="<clinit>", at = @At("TAIL"))
    private static void adjustStackSize(CallbackInfo ci) {
        ((ItemAccessor)Items.MUSHROOM_STEW).setMaxCount(16);
        ((ItemAccessor)Items.RABBIT_STEW).setMaxCount(16);
        ((ItemAccessor)Items.BEETROOT_SOUP).setMaxCount(16);

        ((ItemAccessor)Items.SNOWBALL).setMaxCount(64);

        ((ItemAccessor)Items.OAK_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.SPRUCE_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.BIRCH_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.JUNGLE_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.ACACIA_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.DARK_OAK_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.CRIMSON_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.WARPED_SIGN).setMaxCount(64);
        ((ItemAccessor)Items.BUCKET).setMaxCount(64);

        ((ItemAccessor)Items.SPYGLASS).setMaxCount(64);
    }
}