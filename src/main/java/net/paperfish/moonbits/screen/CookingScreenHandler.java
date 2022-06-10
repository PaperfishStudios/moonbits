package net.paperfish.moonbits.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBData;

public class CookingScreenHandler extends ScreenHandler {
    private final World world;
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    private final Slot bowlSlot;
    private final Slot outputSlot;

    public CookingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(2));
    }

    public CookingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(MBData.COOKING_SCREEN_HANDLER, syncId);
        this.world = playerInventory.player.world;

        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;

        this.addSlot(new IngredientSlot(inventory, 0, 21, 28));
        this.addSlot(new IngredientSlot(inventory, 1, 39, 28));
        this.addSlot(new IngredientSlot(inventory, 2, 58, 28));
        this.addSlot(new IngredientSlot(inventory, 3, 75, 28));
        this.bowlSlot = this.addSlot(new BowlSlot(inventory, 4, 48, 51));
        this.outputSlot = this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 5, 135, 35));
        this.addProperties(propertyDelegate);

        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public int getBowl() {
        return this.propertyDelegate.get(1);
    }

    public int getPrepTime() {
        return this.propertyDelegate.get(0);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    static class IngredientSlot
            extends Slot {
        public IngredientSlot(Inventory inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return true;
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack stack) {
//            Potion potion = PotionUtil.getPotion(stack);
//            if (player instanceof ServerPlayerEntity) {
//                Criteria.BREWED_POTION.trigger((ServerPlayerEntity)player, potion);
//            }
            super.onTakeItem(player, stack);
        }
    }

    static class BowlSlot
            extends Slot {
        public BowlSlot(Inventory inventory, int i, int j, int k) {
            super(inventory, i, j, k);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return matches(stack);
        }

        @Override
        public int getMaxItemCount() {
            return 64;
        }

        public static boolean matches(ItemStack stack) {
            return stack.isOf(Items.BOWL);
        }
    }
}
