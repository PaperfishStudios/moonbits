package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.BlockTags;
import net.paperfish.moonbits.registry.MBBlocks;
import net.paperfish.moonbits.registry.MBEntities;

public class MBEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider{
    public MBEntityTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        this.getOrCreateTagBuilder(MBEntities.LIGHTWEIGHT).add(
                EntityType.CHICKEN, EntityType.BAT,
                EntityType.PARROT, EntityType.BEE,
                EntityType.RABBIT, EntityType.FOX,
                EntityType.FROG,
                EntityType.COD, EntityType.SALMON,
                EntityType.TROPICAL_FISH, EntityType.PUFFERFISH,
                EntityType.SILVERFISH, EntityType.ENDERMITE,
                EntityType.SLIME, EntityType.MAGMA_CUBE,
                EntityType.ALLAY
        );
    }
}
