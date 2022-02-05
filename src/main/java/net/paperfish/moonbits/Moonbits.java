package net.paperfish.moonbits;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.paperfish.moonbits.world.feature.MBPlacedCaveFeatures;
import net.paperfish.moonbits.world.feature.MBPlacedVegFeatures;
import net.paperfish.moonbits.world.feature.MBTreeFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class Moonbits implements ModInitializer {
	public static final String MOD_ID = "moonbits"; //todo: replace the plaintext instances of this with the id, so we dont accidentally make typos
	public static final Logger LOGGER = LogManager.getLogger("moonbits");

	@Override
	public void onInitialize() {
		// register and initialise the Stuff uwu
		GeckoLibMod.DISABLE_IN_DEV = true;
		GeckoLib.initialize();

		MBItemGroup.initialize(); // workaround for a bug where they dont initialize properly in datagen

		MBEvents.initEvents();
		MBParticles.registerParticles();

		MBPlacedCaveFeatures.registerFeatures();
		MBPlacedVegFeatures.registerFeatures();

		MBSounds.initSounds();
		MBBlocks.registerBlocks();
		MBItems.registerItems();
		MBEntities.registerEntities();

		MBData.registerFlammable();
		MBData.registerFuel();
		MBData.registerStrippedBlocks();
		//TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM = MBTreeFeatures.HUGE_BROWN_MUSHROOM;
		//TreeConfiguredFeatures.HUGE_RED_MUSHROOM = MBTreeFeatures.HUGE_RED_MUSHROOM;
	}
}
