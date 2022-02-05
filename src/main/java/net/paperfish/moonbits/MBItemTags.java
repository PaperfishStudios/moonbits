package net.paperfish.moonbits;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class MBItemTags {
    public static final Tag.Identified<Item> JACARANDA_LOGS = TagFactory.ITEM.create(new Identifier(Moonbits.MOD_ID, "jacaranda_logs"));

    public static void register() {

    }
}
