package net.moonteam.moonbits.mixin;

import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BeeEntity.class)
public interface BeeEntityAccessor {
    @Accessor
    int getTicksUntilCanPollinate();
    @Accessor
    void setTicksUntilCanPollinate(int ticksUntilCanPollinate);

    @Accessor
    BlockPos getFlowerPos();
    @Accessor
    void setFlowerPos(BlockPos flowerPos);

    @Invoker
    boolean callIsFlowers(BlockPos pos);

    @Invoker
    void callSetHasNectar(boolean hasNectar);
}
