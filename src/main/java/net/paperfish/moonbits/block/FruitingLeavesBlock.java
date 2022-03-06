package net.paperfish.moonbits.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlocks;

@SuppressWarnings("deprecation")
public class FruitingLeavesBlock extends LeavesBlock {
    //public Block next = MBBlocks.BUDDING_OAK_LEAVES;

    public FruitingLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            Block.dropStack(world, dropFromFullBlock(pos, world), new ItemStack(Items.APPLE));
            world.playSound(null, pos, SoundEvents.BLOCK_CAVE_VINES_PICK_BERRIES, SoundCategory.BLOCKS, 1F, 1.0F);
            //world.setBlockState(pos, MBBlocks.BUDDING_OAK_LEAVES.getDefaultState());
        }
        return ActionResult.SUCCESS;
    }

    public static BlockPos dropFromFullBlock(BlockPos block, World world) {
        if (world.getBlockState(block).isSideSolidFullSquare(world, block.up(), Direction.DOWN)) {
            return block.up();
        }
        else if (world.getBlockState(block).isSideSolidFullSquare(world, block.north(), Direction.SOUTH)) {
            return block.north();
        }
        else if (world.getBlockState(block).isSideSolidFullSquare(world, block.south(), Direction.NORTH)) {
            return block.south();
        }
        else if (world.getBlockState(block).isSideSolidFullSquare(world, block.east(), Direction.WEST)) {
            return block.east();
        }
        else if (world.getBlockState(block).isSideSolidFullSquare(world, block.west(), Direction.EAST)) {
            return block.west();
        }
        else if (world.getBlockState(block).isSideSolidFullSquare(world, block.down(), Direction.UP)) {
            return block.down();
        }
        return block.up();
    }
}
