package net.paperfish.moonbits.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.registry.MBData;

public class KilnRecipe extends AbstractCookingRecipe {
    public KilnRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(MBData.KILN_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }

    public ItemStack createIcon() {
        return new ItemStack(Items.BRICK);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
            return MBData.KILN_RECIPE_SERIALIZER;
    }
}
