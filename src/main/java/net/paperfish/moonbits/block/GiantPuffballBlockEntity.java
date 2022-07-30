package net.paperfish.moonbits.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.registry.MBBlocks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GiantPuffballBlockEntity extends BlockEntity implements IAnimatable {
	private AnimationFactory factory = new AnimationFactory(this);

	public GiantPuffballBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MBBlocks.GIANT_PUFFBALL_ENTITY, blockPos, blockState);
	}

	private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		event.getController().transitionLengthTicks = 0;
		World world = event.getAnimatable().getWorld();
		if (world != null && world.getBlockState(pos).isOf(MBBlocks.GIANT_PUFFBALL)) {
			BlockState state = world.getBlockState(pos);
			if (state.get(GiantPuffballBlock.BOUNCE)) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.puffball.bounce", false));
				return PlayState.CONTINUE;
			}
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.puffball.still", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
