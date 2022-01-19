package net.moonteam.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MBDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        MBBlockTagProvider blockTags = dataGenerator.addProvider(MBBlockTagProvider::new);
        dataGenerator.addProvider(blockTags);
        dataGenerator.addProvider(new MBItemTagProvider(dataGenerator, blockTags));
        dataGenerator.addProvider(MBRecipeProvider::new);
    }
}
