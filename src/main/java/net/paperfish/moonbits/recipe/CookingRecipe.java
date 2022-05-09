package net.paperfish.moonbits.recipe;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBData;

public class CookingRecipe implements Recipe<Inventory> {
    public static final int INPUT_SLOTS = 4;

    public final Identifier id;
    public final String group;
    public final DefaultedList<Ingredient> ingredientList;
    public final ItemStack output;
    //public final ItemStack bowl;
    //public final float experience;
    //public final int cookTime;
    //public final int priority;

    public CookingRecipe(Identifier id, String group, DefaultedList<Ingredient> ingredientList, ItemStack output) {
        this.id = id;
        this.group = group;
        this.ingredientList = ingredientList;
        this.output = output;

//        if (!bowl.isEmpty()) {
//            this.bowl = bowl;
//        } else {
//            this.bowl = ItemStack.EMPTY;
//        }
//
//        this.experience = experience;
//        this.cookTime = cookTime;
//        this.priority = priority;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        RecipeMatcher recipeMatcher = new RecipeMatcher();
        int i = 0;
        for (int j = 0; j < INPUT_SLOTS; ++j) {
            ItemStack itemStack = inventory.getStack(j);
            if (itemStack.isEmpty()) continue;
            ++i;
            recipeMatcher.addInput(itemStack, 1);
        }
        return i == this.ingredientList.size() && recipeMatcher.match(this, null);
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= this.ingredientList.size();
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MBData.COOKING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return MBData.COOKING_RECIPE_TYPE;
    }
}
