package net.paperfish.moonbits;

import net.paperfish.moonbits.block.cauldron.MBCauldronBehaviour;
import net.paperfish.moonbits.registry.*;
import net.paperfish.moonbits.world.MBBiomes;
import net.paperfish.moonbits.world.gen.*;
import com.mojang.logging.LogUtils;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

public class Moonbits implements ModInitializer {
	public static final String MODID = "moonbits"; //todo: replace the plaintext instances of this with the id, so we dont accidentally make typos
	public static final Logger LOGGER = LogUtils.getLogger();

	@Override
	public void onInitialize(ModContainer container) {
		// register and initialise the Stuff uwu
		GeckoLibMod.DISABLE_IN_DEV = true;
		GeckoLib.initialize();

		MBPlacedVegFeatures.init();
		MBPlacedCaveFeatures.init();
		MBPlacedTreeFeatures.init();
		MBItemGroup.initialize(); // workaround for a bug where they dont initialize properly in datagen

		MBEvents.initEvents();
		MBParticles.registerParticles();

		MBSounds.initSounds();
		MBCauldronBehaviour.register();
		MBBlocks.registerBlocks();
		MBItems.registerItems();
		MBEntities.registerEntities();

		MBData.registerData();

		MBBiomes.registerBiomes();
		BiomeAdditions.registerFeatures();
	}
}
