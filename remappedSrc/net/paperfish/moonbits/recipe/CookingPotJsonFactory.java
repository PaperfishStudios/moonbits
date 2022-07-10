package net.paperfish.moonbits.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.registry.MBData;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class CookingPotJsonFactory implements CraftingRecipeJsonBuilder {
    private final Item output;
    private final int outputCount;
    private final List<Ingredient> inputs = Lists.newArrayList();
    @Nullable
//    private Item bowl;
//    private float experience;
//    private int cookTime;
//    private int priority;

    private final Advancement.Builder advancementBuilder = Advancement.Builder.create();
    @Nullable
    private String group;
    private final RecipeSerializer<CookingRecipe> serializer = MBData.COOKING_RECIPE_SERIALIZER;

    private CookingPotJsonFactory(ItemConvertible output, int outputCount) {
        this.output = output.asItem();
        this.outputCount = outputCount;
    }

    public static CookingPotJsonFactory create(ItemConvertible output) {
        return new CookingPotJsonFactory(output, 1);
    }

    public static CookingPotJsonFactory create(ItemConvertible output, int outputCount) {
        return new CookingPotJsonFactory(output, outputCount);
    }

    public CookingPotJsonFactory input(TagKey<Item> tag) {
        return this.input(Ingredient.fromTag(tag));
    }

    public CookingPotJsonFactory input(ItemConvertible itemProvider) {
        return this.input(itemProvider, 1);
    }

    public CookingPotJsonFactory input(ItemConvertible itemProvider, int size) {
        for (int i = 0; i < size; ++i) {
            this.input(Ingredient.ofItems(itemProvider));
        }
        return this;
    }

    public CookingPotJsonFactory input(Ingredient ingredient) {
        return this.input(ingredient, 1);
    }

    public CookingPotJsonFactory input(Ingredient ingredient, int size) {
        for (int i = 0; i < size; ++i) {
            this.inputs.add(ingredient);
        }
        return this;
    }

//    public CookingPotJsonFactory bowl(Item item) {
//        this.bowl = item;
//        return this;
//    }
//    public CookingPotJsonFactory exp(float f) {
//        this.experience = f;
//        return this;
//    }
//    public CookingPotJsonFactory cookTime(int cookTime) {
//        this.cookTime = cookTime;
//        return this;
//    }
//    public CookingPotJsonFactory priority(int priority) {
//        this.priority = priority;
//        return this;
//    }

    @Override
    public CookingPotJsonFactory criterion(String string, CriterionConditions criterionConditions) {
        this.advancementBuilder.criterion(string, criterionConditions);
        return this;
    }

    @Override
    public CookingPotJsonFactory group(@Nullable String string) {
        this.group = string;
        return this;
    }

    @Override
    public Item getOutputItem() {
        return this.output;
    }

    @Override
    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.validate(recipeId);
        this.advancementBuilder.parent(new Identifier("recipes/root"))
                .criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(CriterionMerger.OR);
        exporter.accept(new CookingRecipeJsonProvider(recipeId,
                this.output, this.outputCount, this.group == null ? "" : this.group, this.inputs, this.advancementBuilder,
                new Identifier(recipeId.getNamespace(), "recipes/" + this.output.getGroup().getName() + "/" + recipeId.getPath())));
    }

    private void validate(Identifier recipeId) {
        if (this.advancementBuilder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

    public static class CookingRecipeJsonProvider
            implements RecipeJsonProvider {
        private final Identifier recipeId;
        private final Item output;
        private final int count;
        private final String group;
        private final List<Ingredient> inputs;
//        private Item bowl;
//        private float experience;
//        private int cookTime;
//        private int priority;
        private final Advancement.Builder advancementBuilder;
        private final Identifier advancementId;

        public CookingRecipeJsonProvider(Identifier recipeId, Item output, int outputCount, String group, List<Ingredient> inputs,
                                         Advancement.Builder advancementBuilder, Identifier advancementId) {
            this.recipeId = recipeId;
            this.output = output;
            this.count = outputCount;
            this.group = group;
            this.inputs = inputs;
//            this.bowl = bowl;
//            this.experience = exp;
//            this.cookTime = cookTime;
//            this.priority = priority;
            this.advancementBuilder = advancementBuilder;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            if (!this.group.isEmpty()) {
                json.addProperty("group", this.group);
            }
            JsonArray jsonArray = new JsonArray();
            for (Ingredient ingredient : this.inputs) {
                jsonArray.add(ingredient.toJson());
            }
            json.add("ingredients", jsonArray);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registry.ITEM.getId(this.output).toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }
            json.add("result", jsonObject);

//            json.addProperty("bowl", Registry.ITEM.getId(this.bowl).toString());
//            json.addProperty("exp", this.experience);
//            json.addProperty("cook_time", this.cookTime);
//            json.addProperty("priority", this.priority);
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return MBData.COOKING_RECIPE_SERIALIZER;
        }

        @Override
        public Identifier getRecipeId() {
            return this.recipeId;
        }

        @Override
        @Nullable
        public JsonObject toAdvancementJson() {
            return this.advancementBuilder.toJson();
        }

        @Override
        @Nullable
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}
