package net.paperfish.moonbits.recipe;

import com.github.aws404.booking_it.RecipeBookAdder;
import net.minecraft.item.ItemGroup;
import net.paperfish.moonbits.MBItemGroup;

import java.util.List;

public class MBRecipeBook implements RecipeBookAdder {
    @Override
    public List<RecipeCategoryOptions> getCategories() {
        return List.of(
                RecipeBookAdder.builder("FIRING")
                        .addSearch()
                        .addGroup("BLOCKS", recipe -> recipe instanceof KilnRecipe, "minecraft:clay_ball", "minecraft:brick")
                        .build(),
                RecipeBookAdder.builder("COOKING")
                        .addGroup("DISHES", recipe -> recipe instanceof CookingRecipe, "minecraft:clay_ball", "minecraft:brick")
                        .build(),
                RecipeBookAdder.builder("WASHING")
                        .addGroup("ALL", recipe -> recipe instanceof WashingRecipe, "minecraft:water_bucket", "minecraft:cauldron")
                        .build()
        );
    }
}
