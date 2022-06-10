package net.paperfish.moonbits.block.cauldron;

import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import net.minecraft.util.math.random.Random;

public class BoilingCauldronBlock extends AbstractCauldronBlock implements BlockEntityProvider {
    public BoilingCauldronBlock(Settings settings, Map<Item, CauldronBehavior> behaviorMap) {
        super(settings, behaviorMap);
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    public static void spawnSmokeParticle(World world, BlockPos pos) {
        Random random = world.getRandom();
        DefaultParticleType defaultParticleType = ParticleTypes.CAMPFIRE_COSY_SMOKE;
        world.addImportantParticle(defaultParticleType, true,
                (double)pos.getX() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                (double)pos.getY() + random.nextDouble() + random.nextDouble(),
                (double)pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (double)(random.nextBoolean() ? 1 : -1),
                0.0, 0.07, 0.0);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        BoilingCauldronEntity campfireBlockEntity;
        Optional<CampfireCookingRecipe> optional;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BoilingCauldronEntity && itemStack.isIn(MBItemTags.COOKING_FILLER)) {
            campfireBlockEntity = (BoilingCauldronEntity) blockEntity;
            if (!world.isClient && campfireBlockEntity.addItem(player.getAbilities().creativeMode ? itemStack.copy() : itemStack)) {
                //player.incrementStat(Stats.INTERACT_WITH_CAMPFIRE);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BoilingCauldronEntity(pos, state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) {
            return checkType(type, MBBlocks.BOILING_CAULDRON_ENTITY, BoilingCauldronEntity::clientTick);
        }
        return checkType(type, MBBlocks.BOILING_CAULDRON_ENTITY, BoilingCauldronEntity::tick);
    }
    @Nullable
    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }
}
