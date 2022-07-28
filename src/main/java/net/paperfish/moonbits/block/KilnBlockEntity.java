package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBData;
import net.paperfish.moonbits.screen.KilnScreenHandler;

public class KilnBlockEntity extends AbstractFurnaceBlockEntity {
    public KilnBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.KILN_BLOCK_ENTITY, pos, state, MBData.KILN_RECIPE_TYPE);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("block.moonbits.kiln");
    }

    protected int getFuelTime(ItemStack itemStack) {
        return super.getFuelTime(itemStack) / 2;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new KilnScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
