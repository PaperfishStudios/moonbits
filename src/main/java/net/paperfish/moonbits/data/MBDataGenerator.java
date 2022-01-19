package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class MBDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        FabricTagProvider.BlockTagProvider blockTags = new MBBlockTagProvider(dataGenerator);
        dataGenerator.addProvider(blockTags);
        dataGenerator.addProvider(new MBItemTagProvider(dataGenerator, blockTags));
        dataGenerator.addProvider(MBRecipeProvider::new);
    }
}
