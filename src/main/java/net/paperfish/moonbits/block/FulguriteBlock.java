package net.paperfish.moonbits.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TransparentBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class FulguriteBlock extends TransparentBlock {

	public FulguriteBlock(AbstractBlock.Settings settings) {
		super(settings);
	}
    
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
		return 1.0F;
	}

	public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
		return true;
	}
}
