package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import org.jetbrains.annotations.Nullable;

public class MBItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MBItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {

    }
}
