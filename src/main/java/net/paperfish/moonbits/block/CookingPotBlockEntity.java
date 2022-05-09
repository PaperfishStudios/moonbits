package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBData;
import net.paperfish.moonbits.recipe.CookingRecipe;
import net.paperfish.moonbits.screen.CookingScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

public class CookingPotBlockEntity extends LockableContainerBlockEntity implements SidedInventory {
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
    int prepTime;
    int priority;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            switch (index) {
                case 0 -> {
                    return CookingPotBlockEntity.this.prepTime;
                }
                case 1 -> {
                    return CookingPotBlockEntity.this.priority;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> CookingPotBlockEntity.this.prepTime = value;
                case 1 -> CookingPotBlockEntity.this.priority = value;
            }
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public CookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.COOKING_POT_ENTITY, pos, state);
    }
    public CookingPotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CookingPotBlockEntity blockEntity) {
        ItemStack itemStack = blockEntity.inventory.get(4);

        CookingRecipe recipe = canCraft(world, blockEntity, blockEntity.inventory);
        boolean canCook = recipe != null;
        boolean prepping = blockEntity.prepTime > 0;
//        ItemStack itemStack2 = blockEntity.inventory.get(3);
        if (prepping) {
            boolean doneCooking;
            --blockEntity.prepTime;
            doneCooking = blockEntity.prepTime == 0;
            if (doneCooking && canCook) {
                craft(world, pos, blockEntity.inventory, recipe);
                markDirty(world, pos, state);
            } else if (!canCook) {
                blockEntity.prepTime = 0;
                markDirty(world, pos, state);
            }
        } else if (canCook) {
            blockEntity.prepTime = 400;
            markDirty(world, pos, state);
        }
//        boolean[] bls = blockEntity.getSlotsEmpty();
//        if (!Arrays.equals(bls, blockEntity.slotsEmptyLastTick)) {
//            blockEntity.slotsEmptyLastTick = bls;
//            if (!(state.getBlock() instanceof CookingPotBlock)) {
//                return;
//            }
//            world.setBlockState(pos, state, Block.NOTIFY_LISTENERS);
//        }
    }

    private boolean[] getSlotsEmpty() {
        boolean[] bls = new boolean[3];
        for (int i = 0; i < 3; ++i) {
            if (this.inventory.get(i).isEmpty()) continue;
            bls[i] = true;
        }
        return bls;
    }

    @Nullable
    private static CookingRecipe canCraft(World world, CookingPotBlockEntity blockEntity, DefaultedList<ItemStack> slots) {
        DefaultedList<ItemStack> ingredients = DefaultedList.of();
        for (int i = 0; i < 4; i++) {
            if (slots.get(i).isEmpty()) { return null; }
            ingredients.add(slots.get(i));
        }

//        List<CookingRecipe> recipes = world.getRecipeManager().listAllOfType(MBData.COOKING_RECIPE_TYPE).stream()
//                .filter((recipe) -> recipe.matches(blockEntity, world)).sorted(Comparator.comparing(o -> o.priority)).toList();
        List<CookingRecipe> recipes = world.getRecipeManager().getAllMatches(MBData.COOKING_RECIPE_TYPE, blockEntity, world);
        if (recipes.isEmpty()) {
            return null;
        }
//        recipes.sort(Comparator.comparing(o -> o.priority));
        CookingRecipe recipe = recipes.get(0);

        ItemStack output = slots.get(5);
        // if the recipe would put the output stack over the limit, cancel it
        if ((output.getCount() + recipe.output.getCount()) <= output.getMaxCount()) {
            return null;
        }

        // if the recipe needs no bowl or the bowl slot has the right type of container
//        if (recipe.bowl.isEmpty() || (recipe.bowl.isItemEqual(blockEntity.inventory.get(4))) && blockEntity.inventory.get(4).getCount() > recipe.bowl.getCount()) {
//            return recipe;
//        }
        return null;
    }

    private static void craft(World world, BlockPos pos, DefaultedList<ItemStack> slots, CookingRecipe recipe) {
        for (int i = 0; i < 4; ++i) {
            ItemStack itemStack = slots.get(i);
            itemStack.decrement(1);
            if (itemStack.getItem().hasRecipeRemainder()) {
                ItemStack itemStack2 = new ItemStack(itemStack.getItem().getRecipeRemainder());
                if (itemStack.isEmpty()) {
                    itemStack = itemStack2;
                } else {
                    ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), itemStack2);
                }
            }
            slots.set(i, itemStack);
        }
        // take bowl if needed
        ItemStack bowl = slots.get(4);
//        if (!recipe.bowl.isEmpty() && bowl.isItemEqual(recipe.bowl)) {
//            bowl.decrement(recipe.bowl.getCount());
//            slots.set(4, bowl);
//        }

        // craft output
        ItemStack output = slots.get(5);
        if (output.isEmpty()) {
            slots.set(5, recipe.output);
        }
        else if (output.isOf(recipe.output.getItem()) && (output.getCount() + recipe.output.getCount()) <= output.getMaxCount()) {
            slots.set(5, new ItemStack(output.getItem(), output.getCount() + recipe.output.getCount()));
        }
//        slots.set(3, output);
//        world.syncWorldEvent(WorldEvents.BREWING_STAND_BREWS, pos, 0);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.moonbits.cooking_pot");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new CookingScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemStack : this.inventory) {
            if (itemStack.isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        if (slot >= 0 && slot < this.inventory.size()) {
            return this.inventory.get(slot);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.splitStack(this.inventory, slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.inventory.size()) {
            this.inventory.set(slot, stack);
        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        return !(player.squaredDistanceTo((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) > 64.0);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
//        if (slot == 3) {
//            return BrewingRecipeRegistry.isValidIngredient(stack);
//        }
        if (slot == 4) {
            return stack.isOf(Items.BOWL);
        }
        return this.getStack(slot).isEmpty();
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
//        if (side == Direction.UP) {
//            return TOP_SLOTS;
//        }
        if (side == Direction.DOWN) {
            return new int[]{5};
        }
        return new int[]{0, 1, 2, 3, 4};
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return this.isValid(slot, stack);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == 5;
    }

    @Override
    public void clear() {
        this.inventory.clear();
    }
}
