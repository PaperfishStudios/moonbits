package net.moonteam.moonbits;

import net.fabricmc.api.ModInitializer;
import net.moonteam.moonbits.world.feature.MBPlacedCaveFeatures;
import net.moonteam.moonbits.world.feature.MBPlacedVegFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoonbitsMain implements ModInitializer {
	public static final String MOD_ID = "moonbits"; //todo: replace the plaintext instances of this with the id, so we dont accidentally make typos
	public static final Logger LOGGER = LogManager.getLogger("moonbits");

	@Override
	public void onInitialize() {
		// register and initialise the Stuff uwu
		MBEvents.initEvents();
		MBParticles.registerParticles();

		MBPlacedCaveFeatures.registerFeatures();
		MBPlacedVegFeatures.registerFeatures();

		MBBlocks.registerBlocks();
		MBItems.registerItems();
		MBEntities.registerEntities();

		MBData.registerFlammable();
		MBData.registerFuel();
		MBData.registerStrippedBlocks();
	}
}
