package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.paperfish.moonbits.MBBlocks;

public class SnowyBlockEntity extends BlockEntity {
    public SnowyBlockEntity(BlockPos pos, BlockState state) {
        super(MBBlocks.SNOWY_BLOCK_ENTITY, pos, state);
    }
}
