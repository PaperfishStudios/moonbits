package net.paperfish.moonbits.block;

import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.paperfish.moonbits.registry.MBBlocks;

public class BedrollBlockEntity extends BlockEntity {
    private DyeColor color;

    public BedrollBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.BEDROLL_BLOCK_ENTITY, pos, state);
        this.color = ((BedBlock)state.getBlock()).getColor();
    }

    public BedrollBlockEntity(BlockPos pos, BlockState state, DyeColor color) {
        super(MBBlocks.BEDROLL_BLOCK_ENTITY, pos, state);
        this.color = color;
    }

    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.of(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
