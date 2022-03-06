package net.paperfish.moonbits;

import net.fabricmc.api.ModInitializer;
import net.paperfish.moonbits.world.feature.BiomeAdditions;
import com.mojang.logging.LogUtils;
import net.paperfish.moonbits.world.feature.MBPlacedCaveFeatures;
import net.paperfish.moonbits.world.feature.MBPlacedTreeFeatures;
import net.paperfish.moonbits.world.feature.MBPlacedVegFeatures;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class Moonbits implements ModInitializer {
	public static final String MOD_ID = "moonbits"; //todo: replace the plaintext instances of this with the id, so we dont accidentally make typos
	public static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void onInitialize() {
		// register and initialise the Stuff uwu
		//GeckoLibMod.DISABLE_IN_DEV = true;
		GeckoLib.initialize();

		MBPlacedVegFeatures.init();
		MBPlacedCaveFeatures.init();
		MBPlacedTreeFeatures.init();
		MBItemGroup.initialize(); // workaround for a bug where they dont initialize properly in datagen

		MBEvents.initEvents();
		MBParticles.registerParticles();

		BiomeAdditions.registerFeatures();

		MBSounds.initSounds();
		MBBlocks.registerBlocks();
		MBItems.registerItems();
		MBEntities.registerEntities();

		MBData.registerData();
	}
}
