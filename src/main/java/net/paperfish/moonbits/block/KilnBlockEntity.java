package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBData;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.screen.KilnScreenHandler;
import org.jetbrains.annotations.Nullable;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.KILN_BLOCK_ENTITY, pos, state, MBData.KILN_RECIPE_TYPE);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.moonbits.kiln");
    }

    protected int getFuelTime(ItemStack itemStack) {
        return super.getFuelTime(itemStack) / 2;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
