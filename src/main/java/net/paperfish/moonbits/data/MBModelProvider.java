package net.paperfish.moonbits.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockStateDefinitionProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.paperfish.moonbits.MBItems;
import net.paperfish.moonbits.Moonbits;
import net.paperfish.moonbits.block.PlanterBoxBlock;
import net.paperfish.moonbits.mixin.TextureKeyAccessor;
import net.paperfish.moonbits.mixin.TexturedModelAccessor;
import net.paperfish.moonbits.registry.MBBlockFamilies;
import net.paperfish.moonbits.registry.MBBlockFamily;

import java.util.Optional;

public class MBModelProvider extends FabricBlockStateDefinitionProvider {

    public static final TextureKey INNER = TextureKeyAccessor.createTextureKey("inner", TextureKey.END);
    public static final Model PLANTER_BOX = block("planter_box/planter_box", TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_INNER = block("planter_box/planter_box_inner", TextureKey.TOP, INNER, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_INNER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::pbInner, PLANTER_BOX);
    public static final Model PLANTER_BOX_OUTER = block("planter_box/planter_box_outer", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_OUTER_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::edgeTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_SIDE = block("planter_box/planter_box_side", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_SIDE_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_SIDE_B = block("planter_box/planter_box_side_b", TextureKey.END, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_SIDE_B_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::sideTopBottom, PLANTER_BOX);
    public static final Model PLANTER_BOX_INVENTORY = block("planter_box/planter_box_inventory", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.SIDE);
    public static final TexturedModel.Factory PLANTER_BOX_INVENTORY_F = TexturedModelAccessor.callMakeFactory(MBModelProvider::edgeTopBottom, PLANTER_BOX);
    public static final Model SPAWN_EGG = new Model(Optional.of(new Identifier("minecraft", "item/template_spawn_egg")), Optional.empty());

    public MBModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        // TODO: work out how to do this junk :/
        MBBlockFamilies.getFamilies().filter(MBBlockFamily::shouldGenerateModels)
                .forEach(family -> generateFamily(generator, family));

        for (Item mbItem : MBItems.MB_EGGS) {
            generator.registerParentedItemModel(mbItem, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        MBItems.registerItems();
        for (Item mbItem : MBItems.MB_ITEMS) {
            generator.register(mbItem, Models.GENERATED);
        }
        for (Item mbItem : MBItems.MB_TOOLS) {
            generator.register(mbItem, Models.HANDHELD);
        }
    }

    public static void generateFamily(BlockStateModelGenerator generator, MBBlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            if (block.toString().contains(Moonbits.MOD_ID)) { // should hopefully only make the model/blockstates if its a new block..?
                if (variant == MBBlockFamily.Variant.PILLAR) {
                    generator.registerAxisRotated(block, TexturedModel.CUBE_COLUMN);
                }
                else if (variant == MBBlockFamily.Variant.COLUMN) {
                    column(block, family.getVariant(MBBlockFamily.Variant.CARVED), generator);
                }
                else if (variant == MBBlockFamily.Variant.CUT) {
                    sideEnd(block, generator);
                }
                else if (variant == MBBlockFamily.Variant.BOOKSHELF) {
                    bookshelf(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.PLANTER_BOX) {
                    planterBox(block, generator);
                }
                else if (variant == MBBlockFamily.Variant.DOOR) {
                    generator.registerDoor(block);
                }
                else if (variant == MBBlockFamily.Variant.TRAPDOOR) {
                    generator.registerOrientableTrapdoor(block);
                }
                else if (variant == MBBlockFamily.Variant.BUTTON) {
                    button(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.PRESSURE_PLATE) {
                    generator.registerPressurePlate(block, family.getBaseBlock());
                }
                if (variant == MBBlockFamily.Variant.SIGN) {
                    sign(block, family.getVariant(MBBlockFamily.Variant.WALL_SIGN), family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.CARPET) {
                    generator.registerCarpet(family.getBaseBlock(), block);
                }
                else if (variant == MBBlockFamily.Variant.FENCE) {
                    fence(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.FENCE_GATE) {
                    fenceGate(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.WALL) {
                    wall(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.STAIRS) {
                    stairs(block, family.getBaseBlock(), generator);
                }
                else if (variant == MBBlockFamily.Variant.SLAB) {
                    slab(block, family.getBaseBlock(), generator);
                }
                else if (variant != MBBlockFamily.Variant.WALL_SIGN){
                    generator.registerSimpleCubeAll(block);
                }
                generator.registerSimpleCubeAll(family.getBaseBlock());
            }
        });
    }


    private static void column(Block column, Block end, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(column), Texture.getId(end));
        Identifier identifier = Models.CUBE_COLUMN.upload(column, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createAxisRotatedBlockState(column, identifier));
    }

    private static void sideEnd(Block block, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(block), Texture.getSubId(block, "_top"));
        Identifier identifier = Models.CUBE_COLUMN.upload(block, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, identifier));
    }

    private static void bookshelf(Block bookshelf, Block base, BlockStateModelGenerator generator) {
        Texture texture = Texture.sideEnd(Texture.getId(bookshelf), Texture.getId(base));
        Identifier identifier = Models.CUBE_COLUMN.upload(bookshelf, texture, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(bookshelf, identifier));
    }

    private static void slab(Block slab, Block base, BlockStateModelGenerator generator) {
        Identifier identifier = ModelIds.getBlockModelId(base);
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier2 = Models.SLAB.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.SLAB_TOP.upload(slab, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slab, identifier2, identifier3, identifier));
    }

    private static void stairs(Block stairs, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.INNER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.OUTER_STAIRS.upload(stairs, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairs, identifier2, identifier3, identifier));
    }

    private static void fence(Block fenceBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.FENCE_POST.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(fenceBlock, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(fenceBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(fenceBlock, identifier3);
    }
    private static void fenceGate(Block fenceGateBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(fenceGateBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(fenceGateBlock, identifier, identifier2, identifier3, identifier4));
    }
    public static void wall(Block wallBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.TEMPLATE_WALL_POST.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_WALL_SIDE.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_WALL_SIDE_TALL.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, identifier, identifier2, identifier3));
        Identifier identifier4 = Models.WALL_INVENTORY.upload(wallBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(wallBlock, identifier4);
    }
    public static void sign(Block signBlock, Block wallSign, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.PARTICLE.upload(signBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, identifier));
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSign, identifier));
        generator.registerItemModel(signBlock.asItem());
        generator.excludeFromSimpleItemModelGeneration(wallSign);
    }
    public static void button(Block buttonBlock, Block base, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(base);
        Identifier identifier = Models.BUTTON.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        Identifier identifier2 = Models.BUTTON_PRESSED.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(buttonBlock, identifier, identifier2));
        Identifier identifier3 = Models.BUTTON_INVENTORY.upload(buttonBlock, texturedModel.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(buttonBlock, identifier3);
    }
    public static void planterBox(Block planterBox, BlockStateModelGenerator generator) {
        TexturedModel texturedModel = PLANTER_BOX_F.get(planterBox);
        Identifier identifier = PLANTER_BOX.upload(planterBox, texturedModel.getTexture(), generator.modelCollector);
        TexturedModel texturedModel2 = PLANTER_BOX_INNER_F.get(planterBox);
        Identifier identifier2 = PLANTER_BOX_INNER.upload(planterBox, texturedModel2.getTexture(), generator.modelCollector);
        TexturedModel texturedModel3 = PLANTER_BOX_OUTER_F.get(planterBox);
        Identifier identifier3 = PLANTER_BOX_OUTER.upload(planterBox, texturedModel3.getTexture(), generator.modelCollector);
        TexturedModel texturedModel4 = PLANTER_BOX_SIDE_F.get(planterBox);
        Identifier identifier4 = PLANTER_BOX_SIDE.upload(planterBox, texturedModel4.getTexture(), generator.modelCollector);
        TexturedModel texturedModel5 = PLANTER_BOX_SIDE_B_F.get(planterBox);
        Identifier identifier5 = PLANTER_BOX_SIDE_B.upload(planterBox, texturedModel5.getTexture(), generator.modelCollector);
        generator.blockStateCollector.accept(createPlanterBoxState(planterBox, identifier, identifier2, identifier3, identifier4, identifier5));

        TexturedModel texturedModel6 = PLANTER_BOX_INVENTORY_F.get(planterBox);
        Identifier identifier6 = PLANTER_BOX_INVENTORY.upload(planterBox, texturedModel6.getTexture(), generator.modelCollector);
        generator.registerParentedItemModel(planterBox, identifier6);
    }
    public static BlockStateSupplier createPlanterBoxState(Block block, Identifier regular, Identifier inner, Identifier outer, Identifier side, Identifier sideB) {
        return MultipartBlockStateSupplier.create(block)
                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, true).set(PlanterBoxBlock.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true))
                .with(When.create().set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.NORTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true))

                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, true).set(PlanterBoxBlock.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.NORTH, false).set(PlanterBoxBlock.NORTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R90))

                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, true).set(PlanterBoxBlock.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.EAST, false).set(PlanterBoxBlock.SOUTHEAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))

                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, outer)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.WEST, true).set(PlanterBoxBlock.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, side)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, sideB)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, inner)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(PlanterBoxBlock.WEST, false).set(PlanterBoxBlock.SOUTH, false).set(PlanterBoxBlock.SOUTHWEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, regular)
                        .put(VariantSettings.UVLOCK, true).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                ;
    }

    private static Model make(TextureKey ... requiredTextures) {
        return new Model(Optional.empty(), Optional.empty(), requiredTextures);
    }

    private static Model block(String parent, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "block/" + parent)), Optional.empty(), requiredTextures);
    }

    private static Model item(String parent, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "item/" + parent)), Optional.empty(), requiredTextures);
    }

    private static Model block(String parent, String variant, TextureKey ... requiredTextures) {
        return new Model(Optional.of(new Identifier("moonbits", "block/" + parent)), Optional.of(variant), requiredTextures);
    }

    public static Texture sideBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.END, Texture.getSubId(block, "_bottom"));
    }
    public static Texture sideTopBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
    public static Texture edgeTopBottom(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getSubId(block, "_edge")).put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
    public static Texture pbInner(Block block) {
        return new Texture().put(TextureKey.SIDE, Texture.getId(block)).put(INNER, Texture.getSubId(block, "_inner"))
                .put(TextureKey.TOP, Texture.getSubId(block, "_top")).put(TextureKey.BOTTOM, Texture.getSubId(block, "_bottom"));
    }
}
