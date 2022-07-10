package net.paperfish.moonbits.mixin;

import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Items.class)
public class RestackingMixin {

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At(value="HEAD"), cancellable = true)
    private static void onRegister(String id, Item item, CallbackInfoReturnable<Item> cir) {
        if (Objects.equals(id, "blaze_rod")) {
            ((ItemAccessor)item).setGroup(MBItemGroup.DEBUGGING);
            Registry.register(Registry.ITEM, new Identifier("blaze_rod_old"), item);

            BlockItem a = new AliasedBlockItem(MBBlocks.BLAZE_ROD, new Item.Settings().group(ItemGroup.MATERIALS));
            a.appendBlocks(Item.BLOCK_ITEMS, a);
            cir.setReturnValue(Registry.register(Registry.ITEM, new Identifier("blaze_rod"), a));
        }
        if (Objects.equals(id, "sweet_berries")) {
            ((ItemAccessor)item).setGroup(MBItemGroup.DEBUGGING);
            Registry.register(Registry.ITEM, new Identifier("sweet_berries_old"), item);
            cir.setReturnValue(Registry.register(Registry.ITEM, new Identifier("sweet_berries"), new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SWEET_BERRIES))));
        }
        if (Objects.equals(id, "glow_berries")) {
            ((ItemAccessor)item).setGroup(MBItemGroup.DEBUGGING);
            Registry.register(Registry.ITEM, new Identifier("glow_berries_old"), item);
            cir.setReturnValue(Registry.register(Registry.ITEM, new Identifier("glow_berries"), new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.GLOW_BERRIES))));
        }
    }

    @Inject(method="<clinit>", at = @At("TAIL"))
    private static void adjustStackSize(CallbackInfo ci) {
        ((ItemAccessor)Items.MUSHROOM_STEW).setMaxCount(64);
        ((ItemAccessor)Items.RABBIT_STEW).setMaxCount(64);
        ((ItemAccessor)Items.BEETROOT_SOUP).setMaxCount(64);
        ((ItemAccessor)Items.CAKE).setMaxCount(64);

//        ((ItemAccessor)Items.SNOWBALL).setMaxCount(64);
//
//        ((ItemAccessor)Items.OAK_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.SPRUCE_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.BIRCH_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.JUNGLE_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.ACACIA_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.DARK_OAK_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.CRIMSON_SIGN).setMaxCount(64);
//        ((ItemAccessor)Items.WARPED_SIGN).setMaxCount(64);
//
//        ((ItemAccessor)Items.WHITE_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.LIGHT_GRAY_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.GRAY_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.BLACK_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.GREEN_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.LIME_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.YELLOW_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.ORANGE_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.BROWN_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.RED_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.PINK_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.MAGENTA_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.PURPLE_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.LIGHT_BLUE_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.CYAN_BANNER).setMaxCount(64);
//        ((ItemAccessor)Items.BLUE_BANNER).setMaxCount(64);
//
//        ((ItemAccessor)Items.BUCKET).setMaxCount(64);
//
//        ((ItemAccessor)Items.SPYGLASS).setMaxCount(64);
//
//        ((ItemAccessor)Items.MUSIC_DISC_BLOCKS).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_CAT).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_CHIRP).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_FAR).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_MALL).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_MELLOHI).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_WARD).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_WAIT).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_STAL).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_STRAD).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_11).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_13).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_5).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_PIGSTEP).setMaxCount(64);
//        ((ItemAccessor)Items.MUSIC_DISC_OTHERSIDE).setMaxCount(64);
//
//        ((ItemAccessor)Items.SADDLE).setMaxCount(64);
//
//        ((ItemAccessor)Items.LEATHER_HORSE_ARMOR).setMaxCount(64);
//        ((ItemAccessor)Items.IRON_HORSE_ARMOR).setMaxCount(64);
//        ((ItemAccessor)Items.GOLDEN_HORSE_ARMOR).setMaxCount(64);
//        ((ItemAccessor)Items.DIAMOND_HORSE_ARMOR).setMaxCount(64);
    }
}
