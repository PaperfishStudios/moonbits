package net.paperfish.moonbits.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@Mixin(SugarCaneBlock.class)
public abstract class SugarCaneMixin extends Block implements Fertilizable{
    @Final
	@Mutable
	@Shadow
	public static IntProperty AGE;

    public SugarCaneMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        if (world.getBlockState(pos.up()).isAir()) {
			int i;
			for(i = 1; world.getBlockState(pos.down(i)).isOf(this); ++i) {
			}
            // if the sugar cane is < 3 blocks tall it can be bonemealed
			if (i < 3) {
                return true;
			}
		}
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int newAge = (Integer)state.get(AGE) + MathHelper.nextInt(world.random, 4, 11);
		if (newAge >= 15) {
			world.setBlockState(pos.up(), this.getDefaultState());
			world.setBlockState(pos, (BlockState)state.with(AGE, 0), Block.NO_REDRAW);
        }
        else {
            world.setBlockState(pos, (BlockState)state.with(AGE, newAge), Block.NO_REDRAW);
        }
        
    }
    
}
