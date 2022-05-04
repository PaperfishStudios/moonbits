package net.paperfish.moonbits.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.MBData;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class WashingRecipeJsonFactory implements CraftingRecipeJsonBuilder {
    private final Item output;
    private final Ingredient input;
    private final Block cauldron;

    private final Advancement.Builder builder = Advancement.Builder.create();
    @Nullable
    private String group;
    private final RecipeSerializer<WashingRecipe> serializer;

    private WashingRecipeJsonFactory(ItemConvertible output, Ingredient input, Block cauldron) {
        this.output = output.asItem();
        this.input = input;
        this.cauldron = cauldron;
        this.serializer = MBData.WASHING_RECIPE_SERIALIZER;
    }

    public static WashingRecipeJsonFactory create(Ingredient ingredient, ItemConvertible itemConvertible, Block cauldron) {
        return new WashingRecipeJsonFactory(itemConvertible, ingredient, cauldron);
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String string, CriterionConditions criterionConditions) {
        this.builder.criterion(string, criterionConditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String string) {
        this.group = string;
        return this;
    }
    public CraftingRecipeJsonBuilder group() {
        return group(this.group);
    }

    @Override
    public Item getOutputItem() {
        return this.output;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> consumer, Identifier recipeId) {
        this.validate(recipeId);
        this.builder.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(CriterionMerger.OR);
        consumer.accept(new WashingRecipeJsonProvider(recipeId, this.group == null ? "" : this.group, this.input, this.output, this.cauldron, this.builder,
                new Identifier(recipeId.getNamespace(), "recipes/" + (this.output.getGroup() == null ? "" : this.output.getGroup().getName()) + "/" + recipeId.getPath()), this.serializer));
    }

    private void validate(Identifier identifier) {
        if (this.builder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + identifier);
        }
    }

    public static class WashingRecipeJsonProvider implements RecipeJsonProvider {
        private final Identifier recipeId;
        private final String group;
        private final Ingredient input;
        private final Item output;
        private final Block cauldron;
        private final Advancement.Builder builder;
        private final Identifier advancementId;
        private final RecipeSerializer<WashingRecipe> serializer;

        public WashingRecipeJsonProvider(Identifier identifier, String string, Ingredient ingredient, Item output, Block cauldron, Advancement.Builder task, Identifier identifier2, RecipeSerializer<WashingRecipe> recipeSerializer) {
            this.recipeId = identifier;
            this.group = string;
            this.input = ingredient;
            this.output = output;
            this.cauldron = cauldron;
            this.builder = task;
            this.advancementId = identifier2;
            this.serializer = recipeSerializer;
        }

        public void serialize(JsonObject jsonObject) {
            if (!this.group.isEmpty()) {
                jsonObject.addProperty("group", this.group);
            }

            jsonObject.add("ingredient", this.input.toJson());
            jsonObject.addProperty("cauldron", Registry.BLOCK.getId(this.cauldron).toString());
            jsonObject.addProperty("result", Registry.ITEM.getId(this.output).toString());
        }

        public RecipeSerializer<?> getSerializer() {
            return this.serializer;
        }

        public Identifier getRecipeId() {
            return this.recipeId;
        }

        @Nullable
        public JsonObject toAdvancementJson() {
            return this.builder.toJson();
        }

        @Nullable
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}
