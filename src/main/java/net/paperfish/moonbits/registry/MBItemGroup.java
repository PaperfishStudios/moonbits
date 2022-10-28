package net.paperfish.moonbits.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.Moonbits;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;

public class MBItemGroup {
    public static final ItemGroup CONSTRUCTION = QuiltItemGroup.createWithIcon(
		new Identifier(Moonbits.MODID, "construction"),
		() -> new ItemStack(MBBlocks.CHERT_BRICKS));

	public static final ItemGroup DECOR = QuiltItemGroup.createWithIcon(
		new Identifier(Moonbits.MODID, "decor"),
		() -> new ItemStack(MBBlocks.LAMPROOT_BULB));

	public static final ItemGroup MB_MISC = QuiltItemGroup.createWithIcon(
		new Identifier(Moonbits.MODID, "misc"),
		() -> new ItemStack(MBItems.PEAT));

	public static final ItemGroup MB_FOOD = QuiltItemGroup.createWithIcon(
		new Identifier(Moonbits.MODID, "food"),
		() -> new ItemStack(MBItems.ROASTED_BERRIES));

	public static final ItemGroup DEBUGGING = QuiltItemGroup.builder(
		new Identifier(Moonbits.MODID, "debug"))
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
