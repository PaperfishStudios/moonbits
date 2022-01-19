package net.paperfish.moonbits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.paperfish.moonbits.MBBlocks;
import net.paperfish.moonbits.MBEntities;
import net.paperfish.moonbits.MoonbitsMain;

public class MoobloomEntity extends CowEntity {
	private static final TrackedData<String> TYPE;
	private static final Ingredient BREEDING_INGREDIENT;
    public Type babyType;
	public static final String POLLINATION_TIMER_KEY = "PollinationTimer";
	int pollinationTimer;
	public static final int POLLEN_TIME;

    public MoobloomEntity(EntityType<? extends MoobloomEntity> entityType, World world) {
        super(entityType, world);
        babyType = this.getMoobloomType();
		pollinationTimer = 0;
    }

	@Override
	public void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0D));
		this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
		this.goalSelector.add(3, new TemptGoal(this, 1.25D, BREEDING_INGREDIENT, false));
		this.goalSelector.add(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(7, new LookAroundGoal(this));
	}

    public static DefaultAttributeContainer.Builder createCowAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224D);
	}

    protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(TYPE, MoobloomEntity.Type.BUTTERCUP.name);
	}

    private void setType(MoobloomEntity.Type type) {
		this.dataTracker.set(TYPE, type.name);
	}
    public MoobloomEntity.Type getMoobloomType() {
		return MoobloomEntity.Type.fromName((String)this.dataTracker.get(TYPE));
	}

    public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putString("Type", this.getMoobloomType().name);
		nbt.putString("BabyType", babyType.name);
		nbt.putInt(POLLINATION_TIMER_KEY, this.pollinationTimer);
	}

	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.setType(MoobloomEntity.Type.fromName(nbt.getString("Type")));
		babyType = MoobloomEntity.Type.fromName(nbt.getString("BabyType"));
		this.pollinationTimer = nbt.getInt(POLLINATION_TIMER_KEY);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		if (stack.isOf(Items.WITHER_ROSE)){
			// probably also hurt the moobloom bc no
			return false;
		}
		return BREEDING_INGREDIENT.test(stack);
	}

	// called when bee pollinates moobloom :D
	public void recievePollen() {
		pollinationTimer = POLLEN_TIME;
		// add moobloom reaction here
	}

	@Override
	protected void mobTick() {
		if (pollinationTimer > 0) {
			if (pollinationTimer % 20 == 0) { // place flower once a second
				world.setBlockState(getBlockPos(), this.getMoobloomType().flower);
			}
			pollinationTimer--;
		}
		super.mobTick();
	}

    @Override
    public MoobloomEntity createChild(ServerWorld world, PassiveEntity entity) {
        MoobloomEntity moobloomEntity = (MoobloomEntity)MBEntities.MOOBLOOM.create(world);
		moobloomEntity.setType(this.chooseBabyType((MoobloomEntity)entity));
		return moobloomEntity;
    }

    private MoobloomEntity.Type chooseBabyType(MoobloomEntity moobloom) {
		MoobloomEntity.Type parentA = this.babyType;
		MoobloomEntity.Type parentB = moobloom.babyType;
		MoobloomEntity.Type child;

		child = this.random.nextBoolean() ? parentA : parentB;

		return child;
	}

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		boolean canBreed = this.isBreedingItem(itemStack);
        //BlockState currentFlower = this.getMoobloomType().getFlowerState();
        // if ur holding a small flower, the moobloom is NOT baby,                || and ur flower is different from the moobloom's current flower || && !itemStack.isOf(Item.fromBlock(currentFlower.getBlock()))
		if (itemStack.isIn(ItemTags.SMALL_FLOWERS) && !this.isBaby()) {
            babyType = Type.fromFlowerItem(itemStack.getItem());
			MoonbitsMain.LOGGER.info("baby type: " + babyType);
		}
		return super.interactMob(player, hand);
	}

    static {
		TYPE = DataTracker.registerData(MoobloomEntity.class, TrackedDataHandlerRegistry.STRING);
		BREEDING_INGREDIENT = Ingredient.fromTag(ItemTags.SMALL_FLOWERS);
		POLLEN_TIME = 400;
	}
    
    public enum Type {
		BUTTERCUP("buttercup", MBBlocks.BUTTERCUP.getDefaultState(), MBBlocks.BUTTERCUP.getDefaultState()),
		FORGETMENOT("forget_me_not", MBBlocks.FORGETMENOT.getDefaultState(), MBBlocks.MINI_FORGETMENOT.getDefaultState()),
		DANDELION("dandelion", Blocks.DANDELION.getDefaultState(), MBBlocks.MINI_DANDELION.getDefaultState()),
		POPPY("poppy", Blocks.POPPY.getDefaultState(), MBBlocks.MINI_POPPY.getDefaultState()),
		BLUE_ORCHID("blue_orchid", Blocks.BLUE_ORCHID.getDefaultState(), MBBlocks.MINI_ORCHID.getDefaultState()),
		ALLIUM("allium", Blocks.ALLIUM.getDefaultState(), MBBlocks.MINI_ALLIUM.getDefaultState()),
		AZURE_BLUET("azure_bluet", Blocks.AZURE_BLUET.getDefaultState(), MBBlocks.MINI_BLUET.getDefaultState()),
		RED_TULIP("red_tulip", Blocks.RED_TULIP.getDefaultState(), MBBlocks.MINI_TULIP_R.getDefaultState()),
		ORANGE_TULIP("orange_tulip", Blocks.ORANGE_TULIP.getDefaultState(), MBBlocks.MINI_TULIP_O.getDefaultState()),
		WHITE_TULIP("white_tulip", Blocks.WHITE_TULIP.getDefaultState(), MBBlocks.MINI_TULIP_W.getDefaultState()),
		PINK_TULIP("pink_tulip", Blocks.PINK_TULIP.getDefaultState(), MBBlocks.MINI_TULIP_P.getDefaultState()),
		OXEYE_DAISY("oxeye_daisy", Blocks.OXEYE_DAISY.getDefaultState(), MBBlocks.MINI_OXEYE.getDefaultState()),
		CORNFLOWER("cornflower", Blocks.CORNFLOWER.getDefaultState(), MBBlocks.MINI_CORNFLOWER.getDefaultState()),
		LILY_OF_THE_VALLEY("lily_of_the_valley", Blocks.LILY_OF_THE_VALLEY.getDefaultState(), MBBlocks.MINI_LILY.getDefaultState());

		final String name;
		final BlockState flower;
		final BlockState displayFlower;

		private Type(String name, BlockState flower, BlockState displayFlower) {
			this.name = name;
			this.flower = flower;
			this.displayFlower = displayFlower;
		}

		public BlockState getFlowerState() {
			return this.flower;
		}

		public BlockState getDisplayFlower() {
			return this.displayFlower;
		}

        static MoobloomEntity.Type fromFlowerItem(Item flower) {
            MoobloomEntity.Type[] var1 = values();
			int var2 = var1.length;

			for(int i = 0; i < var2; ++i) {
				MoobloomEntity.Type type = var1[i];
				if (type.flower.getBlock().asItem() == flower) {
                    System.out.println(type.flower.getBlock().asItem());
                    System.out.println(flower);
					return type;
				}
			}
            return BUTTERCUP;
        }

		static MoobloomEntity.Type fromName(String name) {
			MoobloomEntity.Type[] var1 = values();
			int var2 = var1.length;

			for(int var3 = 0; var3 < var2; ++var3) {
				MoobloomEntity.Type type = var1[var3];
				if (type.name.equals(name)) {
					return type;
				}
			}

			return BUTTERCUP;
		}
	}
    
}
