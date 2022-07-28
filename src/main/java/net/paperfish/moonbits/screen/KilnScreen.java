package net.paperfish.moonbits.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Set;

@Environment(value=EnvType.CLIENT)
public class KilnScreen extends AbstractFurnaceScreen<KilnScreenHandler> {
    //You can replace the background with whatever you like, just remember there will always be the recipe book button
    private static final Identifier BACKGROUND = new Identifier("textures/gui/container/furnace.png");

    public KilnScreen(KilnScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, new RecipeBook(), inventory, title, BACKGROUND);
    }

    private static class RecipeBook extends AbstractFurnaceRecipeBookScreen {
        private static final Text TOGGLE_SMELTABLE_RECIPES_TEXT = Text.translatable("gui.recipebook.toggleRecipes.smeltable");

        protected Text getToggleCraftableButtonText() {
            return TOGGLE_SMELTABLE_RECIPES_TEXT;
        }

        @Override
        protected Set<Item> getAllowedFuels() {
            return FurnaceBlockEntity.createFuelTimeMap().keySet();
        }


    }
}
