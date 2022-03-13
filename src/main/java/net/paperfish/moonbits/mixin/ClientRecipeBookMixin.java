package net.paperfish.moonbits.mixin;

import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.paperfish.moonbits.MBItemGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {

    @Inject(method="getGroupForRecipe", at=@At("HEAD"), cancellable = true)
    private static void onGetGroup(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        RecipeType<?> recipeType = recipe.getType();
        if (recipeType == RecipeType.CRAFTING) {
            ItemStack itemStack = recipe.getOutput();
            ItemGroup itemGroup = itemStack.getItem().getGroup();
            if (itemGroup == MBItemGroup.CONSTRUCTION) {
                cir.setReturnValue(RecipeBookGroup.CRAFTING_BUILDING_BLOCKS);
            }
            else if (itemGroup == MBItemGroup.MB_MISC) {
                cir.setReturnValue(RecipeBookGroup.CRAFTING_EQUIPMENT);
            }
        }
    }
}
