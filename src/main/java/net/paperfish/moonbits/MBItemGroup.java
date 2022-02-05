package net.paperfish.moonbits;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class MBItemGroup {
    public static final ItemGroup CONSTRUCTION = FabricItemGroupBuilder.build(
		new Identifier("moonbits", "construction"),
		() -> new ItemStack(MBBlocks.COBBLED_DRIPSTONE));
	
	public static final ItemGroup DECOR = FabricItemGroupBuilder.build(
		new Identifier("moonbits", "decor"),
		() -> new ItemStack(MBBlocks.BIRCH_BOOKSHELF));
	
	public static final ItemGroup MATERIALS = FabricItemGroupBuilder.build(
		new Identifier("moonbits", "materials"),
		() -> new ItemStack(MBItems.PEAT));
	
	public static final ItemGroup UTILITY = FabricItemGroupBuilder.build(
		new Identifier("moonbits", "utility"),
		() -> new ItemStack(MBBlocks.ROPE_LADDER));
	
	public static final ItemGroup MB_FOOD = FabricItemGroupBuilder.build(
		new Identifier("moonbits", "food"),
		() -> new ItemStack(MBItems.ROASTED_BERRIES));

	public static final ItemGroup DEBUGGING = FabricItemGroupBuilder.create(
		new Identifier("moonbits", "debug")) 
		.icon(() -> new ItemStack(Items.COMMAND_BLOCK))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(Items.SPAWNER));
			stacks.add(new ItemStack(Items.DRAGON_EGG));
			stacks.add(new ItemStack(Items.COMMAND_BLOCK));
			stacks.add(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
			stacks.add(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
			stacks.add(new ItemStack(Items.COMMAND_BLOCK_MINECART));
			stacks.add(new ItemStack(Items.STRUCTURE_BLOCK));
			stacks.add(new ItemStack(Items.JIGSAW));
			stacks.add(new ItemStack(Items.BARRIER));
			stacks.add(new ItemStack(Items.STRUCTURE_VOID));
			stacks.add(new ItemStack(Items.LIGHT));
			stacks.add(new ItemStack(Items.DEBUG_STICK));
		})
		.build();

	public static void initialize() {

	}
}
