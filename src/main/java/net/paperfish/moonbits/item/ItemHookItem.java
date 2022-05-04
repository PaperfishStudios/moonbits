package net.paperfish.moonbits.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DecorationItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.paperfish.moonbits.registry.MBEntities;
import net.paperfish.moonbits.entity.GlowItemHookEntity;
import net.paperfish.moonbits.entity.ItemHookEntity;

public class ItemHookItem extends DecorationItem {
    private final EntityType<? extends AbstractDecorationEntity> entityType;

    public ItemHookItem(EntityType<? extends AbstractDecorationEntity> entityType, Settings settings) {
        super(entityType, settings);
        this.entityType = entityType;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        AbstractDecorationEntity abstractDecorationEntity;
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();
        if (playerEntity != null && !this.canPlaceOn(playerEntity, direction, itemStack, blockPos2)) {
            return ActionResult.FAIL;
        }
        World world = context.getWorld();
        if (this.entityType == MBEntities.ITEM_HOOK_ENTITY) {
            abstractDecorationEntity = new ItemHookEntity(world, blockPos2, direction);
        }
        else if (entityType == MBEntities.GLOW_ITEM_HOOK_ENTITY) {
            abstractDecorationEntity = new GlowItemHookEntity(world, blockPos2, direction);
        }
        else {
            return ActionResult.success(world.isClient);
        }
        NbtCompound nbtCompound = itemStack.getNbt();
        if (nbtCompound != null) {
            EntityType.loadFromEntityNbt(world, playerEntity, abstractDecorationEntity, nbtCompound);
        }
        if (abstractDecorationEntity.canStayAttached()) {
            if (!world.isClient) {
                abstractDecorationEntity.onPlace();
                world.emitGameEvent((Entity)playerEntity, GameEvent.ENTITY_PLACE, blockPos);
                world.spawnEntity(abstractDecorationEntity);
            }
            itemStack.decrement(1);
            return ActionResult.success(world.isClient);
        }
        return ActionResult.CONSUME;
    }

    @Override
    protected boolean canPlaceOn(PlayerEntity player, Direction side, ItemStack stack, BlockPos pos) {
        return !player.world.isOutOfHeightLimit(pos) && player.canPlaceOn(pos, side, stack);
    }
}
