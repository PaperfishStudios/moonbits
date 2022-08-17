package net.paperfish.moonbits.item.extended;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum MBToolMaterials implements ToolMaterial {
	FLINT(1, 80, 4.0F, 1.0F, 5, () -> Ingredient.ofItems(Items.FLINT));

	private final int miningLevel;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Lazy<Ingredient> repairIngredient;

	MBToolMaterials(int j, int k, float f, float g, int l, Supplier<Ingredient> supplier) {
		this.miningLevel = j;
		this.itemDurability = k;
		this.miningSpeed = f;
		this.attackDamage = g;
		this.enchantability = l;
		this.repairIngredient = new Lazy<>(supplier);
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return this.miningLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
