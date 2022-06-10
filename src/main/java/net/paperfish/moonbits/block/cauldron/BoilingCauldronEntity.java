package net.paperfish.moonbits.block.cauldron;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.data.MBRecipeProvider;
import net.paperfish.moonbits.recipe.CookingRecipe;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBData;
import net.paperfish.moonbits.registry.MBItems;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import net.minecraft.util.math.random.Random;

public class BoilingCauldronEntity extends BlockEntity {
    private final DefaultedList<ItemStack> itemsBeingCooked = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private int cookTime = 0;
    // time that the cooking is done
    private int cookEndTime = 0;

    public BoilingCauldronEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.BOILING_CAULDRON_ENTITY, pos, state);
    }
    public static void tick(World world, BlockPos pos, BlockState state, BoilingCauldronEntity cauldron) {
        boolean bl = true;
        for (ItemStack item : cauldron.itemsBeingCooked) {
            if (item.isEmpty()) {
                bl = false;
                break;
            }
        }
        if (bl) {
            // if both are 0 or its done cooking
            if (cauldron.cookTime >= cauldron.cookEndTime) {
                Inventory a = new SimpleInventory(4);
                for (int i = 0; i < 4; i++) {
                    a.setStack(i, cauldron.itemsBeingCooked.get(i));
                }

                // if both 0 (hasnt started - start cooking)
                if (cauldron.cookEndTime == 0) {
                    cauldron.cookEndTime = 200;
                }
                // if done cooking
                else {
//                    List<CookingRecipe> resippy = world.getRecipeManager().getAllMatches(MBData.COOKING_RECIPE_TYPE, a, world).stream().sorted(Comparator.comparing(o -> o.priority)).toList();
                    ItemStack b = world.getRecipeManager().getFirstMatch(MBData.COOKING_RECIPE_TYPE, a, world).map(recipe -> recipe.output).orElse(new ItemStack(MBItems.PEAT));
                    cauldron.cookTime = 0;
                    cauldron.cookEndTime = 0;
                    cauldron.itemsBeingCooked.clear();
                    if (b.isOf(MBItems.PEAT)) {
                        // set cauldron to slop cauldron (PEAT IS TEMPORARY ADD A SLOP ITEM OR SOMETHING
                        Moonbits.LOGGER.info("slop");
                    }
                    else {
//                        ItemStack b = resippy.get(0).output;
                        // set cauldron to soup cauldron filled with result
//                        Moonbits.LOGGER.info("priority: " + resippy.get(0).priority);
                        Moonbits.LOGGER.info("soup! " + b.getTranslationKey());
                    }
                }
            }
            else {
                cauldron.cookTime++; // count cookin time
                Moonbits.LOGGER.info("cooking: " + cauldron.cookTime);
            }

            BoilingCauldronEntity.markDirty(world, pos, state);
        }
    }
    public static void clientTick(World world, BlockPos pos, BlockState state, BoilingCauldronEntity cauldron) {
        int i;
        Random random = world.random;
        if (random.nextFloat() < 0.11f) {
            for (i = 0; i < random.nextInt(2) + 2; ++i) {
                BoilingCauldronBlock.spawnSmokeParticle(world, pos);
            }
        }
        for (int j = 0; j < cauldron.itemsBeingCooked.size(); ++j) {
            if (cauldron.itemsBeingCooked.get(j).isEmpty() || !(random.nextFloat() < 0.2f)) continue;
            Direction direction = Direction.fromHorizontal(Math.floorMod(j, 4));
            float f = 0.3125f;
            double d = (double)pos.getX() + 0.5 - (double)((float)direction.getOffsetX() * 0.3125f) + (double)((float)direction.rotateYClockwise().getOffsetX() * 0.3125f);
            double e = (double)pos.getY() + 0.5;
            double g = (double)pos.getZ() + 0.5 - (double)((float)direction.getOffsetZ() * 0.3125f) + (double)((float)direction.rotateYClockwise().getOffsetZ() * 0.3125f);
            for (int k = 0; k < 4; ++k) {
                world.addParticle(ParticleTypes.SMOKE, d, e, g, 0.0, 5.0E-4, 0.0);
            }
        }
    }
    public boolean addItem(ItemStack item) {
        for (int i = 0; i < this.itemsBeingCooked.size(); ++i) {
            ItemStack itemStack = this.itemsBeingCooked.get(i);
            if (!itemStack.isEmpty()) continue;
            this.itemsBeingCooked.set(i, item.split(1));
            return true;
        }
        return false;
    }
    public DefaultedList<ItemStack> getItemsBeingCooked() {
        return this.itemsBeingCooked;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemsBeingCooked, true);
        nbt.putInt("CookTime", this.cookTime);
        nbt.putInt("EndTime", this.cookEndTime);
        super.writeNbt(nbt);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.itemsBeingCooked.clear();
        Inventories.readNbt(nbt, this.itemsBeingCooked);
        this.cookTime = nbt.getShort("CookTime");
        this.cookEndTime = nbt.getShort("EndTime");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
