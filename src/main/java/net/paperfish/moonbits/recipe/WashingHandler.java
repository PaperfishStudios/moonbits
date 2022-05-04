package net.paperfish.moonbits.recipe;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBData;
import net.paperfish.moonbits.Moonbits;

import java.util.Optional;

public class WashingHandler {
    public static ActionResult washItem(ItemStack input, BlockState cauldron, ServerPlayerEntity player, Hand hand, ServerWorld world) {
        ItemStack recipe = new ItemStack(input.getItem(), 1);
        Moonbits.LOGGER.info("washing input: " + Registry.ITEM.getId(input.getItem()));
        //Block cauldronType = cauldron.getBlock();
        ItemStack output = getResult(recipe, world, cauldron);
        if (output != null) {
            Moonbits.LOGGER.info("washing output: " + Registry.ITEM.getId(output.getItem()));
            output.setCount(input.getCount());
            player.setStackInHand(hand, output);
            //player.setStackInHand(hand, ItemUsage.exchangeStack(input, player, output));
            MBData.ITEM_WASHED.trigger(player, player.getInventory(), output);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public static ItemStack getResult(ItemStack itemIn, World world, BlockState cauldron) {
        Optional<WashingRecipe> recipe = getRecipe(itemIn, world);
        if (recipe.isPresent()) {
            if (cauldron.isOf(recipe.get().getCauldron())) {
                return recipe.map(washingRecipe -> washingRecipe.craft(new SimpleInventory(itemIn))).orElse(null);
            }
            Moonbits.LOGGER.info("recipe present, wrong cauldron!");
        }
        Moonbits.LOGGER.info("no washing result!");
        return null;
    }

    private static Optional<WashingRecipe> getRecipe(ItemStack item, World world) {
        //dummy.setStack(0, item);
        //Moonbits.LOGGER.info(world.getRecipeManager().listAllOfType(MBData.WASHING_RECIPE_TYPE));
        return world.getRecipeManager().getFirstMatch(MBData.WASHING_RECIPE_TYPE, new SimpleInventory(item), world);
    }

    public static class DummyInv extends SimpleInventory {
        public DummyInv() {
            super(1);
        }
    }

    public static DummyInv dummy = new DummyInv();
}
