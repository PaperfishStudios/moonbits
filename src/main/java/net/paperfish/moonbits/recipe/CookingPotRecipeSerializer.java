package net.paperfish.moonbits.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class CookingPotRecipeSerializer implements RecipeSerializer<CookingRecipe> {
    private final int cookingTime;

    public CookingPotRecipeSerializer(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public CookingRecipe read(Identifier id, JsonObject jsonObject) {
        String string = JsonHelper.getString(jsonObject, "group", "");

        DefaultedList<Ingredient> ingredients = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
        if (ingredients.isEmpty()) {
            throw new JsonParseException("No ingredients for cooking recipe");
        }
        if (ingredients.size() > 4) {
            throw new JsonParseException("Too many ingredients for cooking recipe");
        }

//        String string2 = JsonHelper.getString(jsonObject, "result");
//        Identifier identifier2 = new Identifier(string2);
        ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));

        String string3 = JsonHelper.getString(jsonObject, "bowl");
        Identifier identifier3 = new Identifier(string3);
        ItemStack bowlStack = new ItemStack(Registry.ITEM.getOrEmpty(identifier3).orElseThrow(() -> new IllegalStateException("Item: " + string3 + " does not exist")));

        float xp = JsonHelper.getFloat(jsonObject, "exp", 0.0f);
        int cookTime = JsonHelper.getInt(jsonObject, "cook_time", this.cookingTime);
        int priority = JsonHelper.getInt(jsonObject, "priority", 0);

        return new CookingRecipe(id, string, ingredients, output, bowlStack, xp, cookTime, priority);
    }

    private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        for (int i = 0; i < json.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(json.get(i));
            if (ingredient.isEmpty()) continue;
            defaultedList.add(ingredient);
        }
        return defaultedList;
    }

    @Override
    public CookingRecipe read(Identifier id, PacketByteBuf packetByteBuf) {
        String string = packetByteBuf.readString();
        int i = packetByteBuf.readVarInt();
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);
        for (int j = 0; j < defaultedList.size(); ++j) {
            defaultedList.set(j, Ingredient.fromPacket(packetByteBuf));
        }
        ItemStack output = packetByteBuf.readItemStack();
        ItemStack bowlStack = packetByteBuf.readItemStack();
        int xp = packetByteBuf.readVarInt();
        int cookTime = packetByteBuf.readVarInt();
        int priority = packetByteBuf.readVarInt();
        return new CookingRecipe(id, string, defaultedList, output, bowlStack, xp, cookTime, priority);
    }

    @Override
    public void write(PacketByteBuf buffer, CookingRecipe recipe) {
        buffer.writeString(recipe.group);
        buffer.writeVarInt(recipe.ingredientList.size());
        for (Ingredient ingredient : recipe.ingredientList) {
            ingredient.write(buffer);
        }
        buffer.writeItemStack(recipe.output);
        buffer.writeItemStack(recipe.bowl);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookTime);
        buffer.writeVarInt(recipe.priority);
    }
}
