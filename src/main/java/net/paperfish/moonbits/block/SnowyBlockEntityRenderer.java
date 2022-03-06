package net.paperfish.moonbits.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.paperfish.moonbits.Moonbits;

import java.util.Random;

public class SnowyBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private static final ModelIdentifier SNOW_LAYER = new ModelIdentifier(new Identifier("snow"), "layers=1");
    private final MinecraftClient client = MinecraftClient.getInstance();

    public SnowyBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrixStack.push();
        //matrixStack.translate(-0.5, 0.0, -0.5);

        BlockRenderManager blockRenderManager = this.client.getBlockRenderManager();
        BakedModelManager bakedModelManager = blockRenderManager.getModels().getModelManager();

        blockRenderManager.getModelRenderer().render(matrixStack.peek(), vertexConsumers.getBuffer(TexturedRenderLayers.getEntitySolid()), null,
                bakedModelManager.getModel(SNOW_LAYER), 1.0f, 1.0f, 1.0f, light, OverlayTexture.DEFAULT_UV);

        matrixStack.pop();
    }
}
