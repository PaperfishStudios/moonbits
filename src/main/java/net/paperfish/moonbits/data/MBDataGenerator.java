package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class MBDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        dataGenerator.addProvider(MBRecipeProvider::new);
        FabricTagProvider.BlockTagProvider blockTags = new MBBlockTagProvider(dataGenerator);
        dataGenerator.addProvider(MBLootTableProvider::new);
        dataGenerator.addProvider(MBModelProvider::new);
//        dataGenerator.addProvider(new MBAdvancementsProvider(dataGenerator));
        dataGenerator.addProvider(blockTags);
        dataGenerator.addProvider(new MBItemTagProvider(dataGenerator, blockTags));
        dataGenerator.addProvider(new MBEntityTagProvider(dataGenerator));
    }
}
