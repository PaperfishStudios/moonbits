package net.moonteam.moonbits.block;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import net.moonteam.moonbits.MBBlocks;
import net.moonteam.moonbits.client.MBEntityType;

public class BedrollBlockEntityRenderer implements BlockEntityRenderer<BedrollBlockEntity> {
    private final ModelPart bedHead;
    private final ModelPart bedFoot;
    public static final Identifier ATLAS_TEXTURE = new Identifier("textures/atlas/beds.png");
    private static final Identifier BEDROLL =  new Identifier("moonbits", "textures/entity/bedroll/bedroll.png");

    public BedrollBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.bedHead = ctx.getLayerModelPart(MBEntityType.BEDROLL_HEAD);
        this.bedFoot = ctx.getLayerModelPart(MBEntityType.BEDROLL_FOOT);
    }

    public static TexturedModelData getHeadTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getFootTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 18).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 2.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void render(BedrollBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        SpriteIdentifier spriteIdentifier = new SpriteIdentifier(ATLAS_TEXTURE, BEDROLL);
        World world = entity.getWorld();
        if (world != null) {
            BlockState blockState = entity.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedrollBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(MBBlocks.BEDROLL_BLOCK_ENTITY, BedBlock::getBedPart, BedBlock::getOppositePartDirection, ChestBlock.FACING, blockState, world, entity.getPos(), (worldx, pos) -> {
                return false;
            });
            int k = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever<>())).get(light);
            this.renderPart(matrices, vertexConsumers, blockState.get(BedBlock.PART) == BedPart.HEAD ? this.bedHead : this.bedFoot, (Direction)blockState.get(BedrollBlock.FACING), spriteIdentifier, k, overlay, false);
        } else {
            this.renderPart(matrices, vertexConsumers, this.bedHead, Direction.SOUTH, spriteIdentifier, light, overlay, false);
            this.renderPart(matrices, vertexConsumers, this.bedFoot, Direction.SOUTH, spriteIdentifier, light, overlay, true);
        }
    }

    private void renderPart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ModelPart part, Direction direction, SpriteIdentifier sprite, int light, int overlay, boolean isFoot) {
        matrices.push();
        matrices.translate(0.0D, 0.5625D, isFoot ? -1.0D : 0.0D);
        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
        matrices.translate(0.5D, 0.5D, 0.5D);
        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F + direction.asRotation()));
        matrices.translate(-0.5D, -0.5D, -0.5D);
        VertexConsumer vertexConsumer = sprite.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        part.render(matrices, vertexConsumer, light, overlay);
        matrices.pop();
    }
}
