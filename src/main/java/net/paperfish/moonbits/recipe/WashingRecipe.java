package net.paperfish.moonbits.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.Moonbits;

import javax.annotation.Nullable;

public class WashingRecipe implements Recipe<Inventory> {
    //public static final WashingSerializer SERIALIZER = registerSerializer(new WashingSerializer());
    //public static RecipeType<WashingRecipe> TYPE = registerType();

    private final Ingredient input;
    private final ItemStack output;
    private final Block cauldron;
    protected final Identifier id;

    public WashingRecipe(Identifier identifier, Ingredient input, ItemStack output, Block cauldron) {
        this.id = identifier;
        this.input = input;
        this.output = output;
        this.cauldron = cauldron;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.input.test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int i, int j) {
        return false;
    }

    public Ingredient getInput() {
        return this.input;
    }

    public Block getCauldron() {
        return cauldron;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MBData.WASHING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return MBData.WASHING_RECIPE_TYPE;
    }

    public static class WashingSerializer implements RecipeSerializer<WashingRecipe> {

        public WashingSerializer() {

        }

        public static final WashingSerializer INSTANCE = new WashingSerializer();
        public static final Identifier ID = new Identifier(Moonbits.MODID, "washing");

        @Override
        public WashingRecipe read(Identifier recipeId, JsonObject json) {

            final JsonElement inputElement = JsonHelper.hasArray(json, "ingredient") ?
                    JsonHelper.getArray(json, "ingredient") : JsonHelper.getObject(json, "ingredient");
            final Ingredient input = Ingredient.fromJson(inputElement);
            final Block cauldron = Registry.BLOCK.get(new Identifier(JsonHelper.getString(json, "cauldron")));
            final ItemStack output = new ItemStack(Registry.ITEM.get(new Identifier(JsonHelper.getString(json, "result"))));

            return new WashingRecipe(recipeId, input, output, cauldron);
        }
        @Override
        public void write(PacketByteBuf buffer, WashingRecipe recipe) {
            recipe.input.write(buffer);
            buffer.writeIdentifier(Registry.BLOCK.getId(recipe.getCauldron()));
            buffer.writeItemStack(recipe.output);
        }

        @Nullable
        @Override
        public WashingRecipe read(Identifier recipeId, PacketByteBuf buffer) {

            final Ingredient input = Ingredient.fromPacket(buffer);
            final Block cauldron = Registry.BLOCK.get(buffer.readIdentifier());
            final ItemStack output = buffer.readItemStack();

            return new WashingRecipe(recipeId, input, output, cauldron);
        }
    }

//    public static class Type implements RecipeType<WashingRecipe> {
//        private Type() {}
//        public static final Type INSTANCE = new Type();
//        @Override
//        public String toString() {
//            return Moonbits.MOD_ID.concat(":washing");
//        }
//
//        public <C extends Inventory> Optional<WashingRecipe> find(C inv, World world) {
//            return world.getRecipeManager()
//                    .getFirstMatch (MBData.WASHING_RECIPE_TYPE, inv, world);
//        }
//    }
//
//    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(S serializer) {
//        return Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Moonbits.MOD_ID, "washing"), serializer);
//    }
//    public static <T extends Recipe<?>> RecipeType<T> registerType() {
//        return Registry.register(Registry.RECIPE_TYPE, new Identifier(Moonbits.MOD_ID, "washing"), new RecipeType<T>() {
//            public String toString() {
//                return Moonbits.MOD_ID.concat(":washing");
//            }
//        });
//    }
//
//    public static void registerRecipe(){
//        //registerSerializer(SERIALIZER);
//        //registerType();
//    }
}
