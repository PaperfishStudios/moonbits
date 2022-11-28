package net.paperfish.moonbits.registry;

import com.mojang.datafixers.types.Type;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.BedrollBlockEntity;
import net.paperfish.moonbits.block.KilnBlockEntity;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityType;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class MBBlockEntities {
	public static BlockEntityType<BedrollBlockEntity> BEDROLL_BLOCK_ENTITY = create("bedroll_block_entity", QuiltBlockEntityTypeBuilder.create(BedrollBlockEntity::new, MBBlocks.BEDROLL));
	public static BlockEntityType<KilnBlockEntity> KILN_BLOCK_ENTITY = create("kiln_block_entity", QuiltBlockEntityTypeBuilder.create(KilnBlockEntity::new, MBBlocks.KILN));

	public static void init() {
		BlockEntityType.CAMPFIRE.addSupportedBlocks(MBBlocks.COPPER_OXIDE_CAMPFIRE);
	}

	private static <T extends BlockEntity> BlockEntityType<T> create(String id, QuiltBlockEntityTypeBuilder<T> builder) {
//		if (builder.supportedBlocks.isEmpty()) {
//			Moonbits.LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", id);
//		}

		Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Moonbits.MODID, id), builder.build(type));
	}
}
