package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.paperfish.moonbits.MBBlockTags;
import net.paperfish.moonbits.MBItemTags;
import org.jetbrains.annotations.Nullable;

public class MBItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public MBItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.PLANKS, ItemTags.PLANKS);
//        copy(BlockTags.STONE_BRICKS, ItemTags.STONE_BRICKS);
        copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
//        copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
//        copy(BlockTags.CARPETS, ItemTags.CARPETS);
        copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
        copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
        copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
        copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
        copy(BlockTags.DOORS, ItemTags.DOORS);
        copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);
//        copy(BlockTags.OAK_LOGS, ItemTags.OAK_LOGS);
//        copy(BlockTags.DARK_OAK_LOGS, ItemTags.DARK_OAK_LOGS);
//        copy(BlockTags.BIRCH_LOGS, ItemTags.BIRCH_LOGS);
//        copy(BlockTags.ACACIA_LOGS, ItemTags.ACACIA_LOGS);
//        copy(BlockTags.SPRUCE_LOGS, ItemTags.SPRUCE_LOGS);
//        copy(BlockTags.JUNGLE_LOGS, ItemTags.JUNGLE_LOGS);
//        copy(BlockTags.CRIMSON_STEMS, ItemTags.CRIMSON_STEMS);
//        copy(BlockTags.WARPED_STEMS, ItemTags.WARPED_STEMS);
        copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
//        copy(BlockTags.LOGS, ItemTags.LOGS);
//        copy(BlockTags.SAND, ItemTags.SAND);
        copy(BlockTags.SLABS, ItemTags.SLABS);
        copy(BlockTags.WALLS, ItemTags.WALLS);
        copy(BlockTags.STAIRS, ItemTags.STAIRS);
//        copy(BlockTags.ANVIL, ItemTags.ANVIL);
//        copy(BlockTags.RAILS, ItemTags.RAILS);
        copy(BlockTags.LEAVES, ItemTags.LEAVES);
        copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
//        copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
        copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);
//        copy(BlockTags.BEDS, ItemTags.BEDS);
//        copy(BlockTags.FENCES, ItemTags.FENCES);
        copy(BlockTags.TALL_FLOWERS, ItemTags.TALL_FLOWERS);
//        copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
//        copy(BlockTags.SOUL_FIRE_BASE_BLOCKS, ItemTags.SOUL_FIRE_BASE_BLOCKS);
//        copy(BlockTags.CANDLES, ItemTags.CANDLES);
//        copy(BlockTags.OCCLUDES_VIBRATION_SIGNALS, ItemTags.OCCLUDES_VIBRATION_SIGNALS);
//        copy(BlockTags.GOLD_ORES, ItemTags.GOLD_ORES);
//        copy(BlockTags.IRON_ORES, ItemTags.IRON_ORES);
//        copy(BlockTags.DIAMOND_ORES, ItemTags.DIAMOND_ORES);
        //copy(BlockTags.REDSTONE_ORES, ItemTags.REDSTONE_ORES);
        //copy(BlockTags.LAPIS_ORES, ItemTags.LAPIS_ORES);
        //copy(BlockTags.COAL_ORES, ItemTags.COAL_ORES);
        //copy(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);
        //copy(BlockTags.COPPER_ORES, ItemTags.COPPER_ORES);
        copy(BlockTags.DIRT, ItemTags.DIRT);
        //copy(BlockTags.TERRACOTTA, ItemTags.TERRACOTTA);
        copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
        copy(BlockTags.NON_FLAMMABLE_WOOD, ItemTags.NON_FLAMMABLE_WOOD);

        copy(MBBlockTags.JACARANDA_LOGS, MBItemTags.JACARANDA_LOGS);
    }
}
