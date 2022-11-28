package net.paperfish.moonbits.block;

import net.minecraft.util.StringIdentifiable;

public enum BeamStates implements StringIdentifiable {
	X("x"),
	Z("z"),
	NONE("none");

	private final String name;

	private BeamStates(String string2) {
		this.name = string2;
	}

	public String toString() {
		return this.name;
	}

	@Override
	public String asString() {
		return this.name;
	}
}
