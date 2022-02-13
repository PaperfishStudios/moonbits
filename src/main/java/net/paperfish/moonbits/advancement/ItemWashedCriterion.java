package net.paperfish.moonbits.advancement;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class ItemWashedCriterion extends AbstractCriterion<ItemWashedCriterion.Conditions> {
    static final Identifier ID = new Identifier("item_washed");

    @Override
    protected Conditions conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        ItemPredicate[] itemPredicates = ItemPredicate.deserializeAll(obj.get("items"));
        return new Conditions(playerPredicate, itemPredicates);
    }

    public void trigger(ServerPlayerEntity player, PlayerInventory inventory, ItemStack stack) {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int l = 0; l < inventory.size(); ++l) {
            ItemStack itemStack = inventory.getStack(l);
            if (itemStack.isEmpty()) {
                ++j;
                continue;
            }
            ++k;
            if (itemStack.getCount() < itemStack.getMaxCount()) continue;
            ++i;
        }
        this.trigger(player, inventory, stack, i, j, k);
    }

    private void trigger(ServerPlayerEntity player, PlayerInventory inventory, ItemStack stack, int full, int empty, int occupied) {
        this.trigger(player, conditions -> conditions.matches(inventory, stack, full, empty, occupied));
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public static class Conditions
            extends AbstractCriterionConditions {
        private final ItemPredicate[] items;

        public Conditions(EntityPredicate.Extended player, ItemPredicate[] items) {
            super(ID, player);
            this.items = items;
        }

        public static Conditions items(ItemPredicate ... items) {
            return new Conditions(EntityPredicate.Extended.EMPTY, items);
        }

        public static Conditions items(ItemConvertible... items) {
            ItemPredicate[] itemPredicates = new ItemPredicate[items.length];
            for (int i = 0; i < items.length; ++i) {
                itemPredicates[i] = new ItemPredicate(null, ImmutableSet.of(items[i].asItem()), NumberRange.IntRange.ANY, NumberRange.IntRange.ANY, EnchantmentPredicate.ARRAY_OF_ANY, EnchantmentPredicate.ARRAY_OF_ANY, null, NbtPredicate.ANY);
            }
            return Conditions.items(itemPredicates);
        }

        @Override
        public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer) {
            JsonObject jsonObject = super.toJson(predicateSerializer);
            if (this.items.length > 0) {
                JsonArray jsonArray = new JsonArray();
                for (ItemPredicate itemPredicate : this.items) {
                    jsonArray.add(itemPredicate.toJson());
                }
                jsonObject.add("items", jsonArray);
            }
            return jsonObject;
        }

        public boolean matches(PlayerInventory inventory, ItemStack stack, int full, int empty, int occupied) {
            int i = this.items.length;
            if (i == 0) {
                return true;
            }
            if (i == 1) {
                return !stack.isEmpty() && this.items[0].test(stack);
            }
            ObjectArrayList<ItemPredicate> list = new ObjectArrayList<>(this.items);
            int j = inventory.size();
            for (int k = 0; k < j; ++k) {
                if (list.isEmpty()) {
                    return true;
                }
                ItemStack itemStack = inventory.getStack(k);
                if (itemStack.isEmpty()) continue;
                list.removeIf(item -> item.test(itemStack));
            }
            return list.isEmpty();
        }
    }
}
