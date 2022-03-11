package net.paperfish.moonbits.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.paperfish.moonbits.MBData;

public class KilnScreenHandler extends AbstractFurnaceScreenHandler {
    public KilnScreenHandler(int i, PlayerInventory playerInventory) {
        super(MBData.KILN_SCREEN_HANDLER, MBData.KILN_RECIPE_TYPE, MBData.KILN_BOOK_CATEGORY, i, playerInventory);
    }

    public KilnScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(MBData.KILN_SCREEN_HANDLER, MBData.KILN_RECIPE_TYPE, MBData.KILN_BOOK_CATEGORY, i, playerInventory, inventory, propertyDelegate);
    }
}