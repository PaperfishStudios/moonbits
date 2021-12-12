package net.moonteam.moonbits.entity;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class MoobloomFlowerFeatureRenderer<T extends MoobloomEntity> extends FeatureRenderer<T, MoobloomEntityModel<T>>  {

    public MoobloomFlowerFeatureRenderer(FeatureRendererContext<T, MoobloomEntityModel<T>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T moobloomEntity, float f, float g, float h, float j, float k, float l) {
		if (!moobloomEntity.isBaby()) {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			boolean bl = minecraftClient.hasOutline(moobloomEntity) && moobloomEntity.isInvisible();
			if (!moobloomEntity.isInvisible() || bl) {
				BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();

                // the flower to use texture for here
				BlockState flower = moobloomEntity.getMoobloomType().getDisplayFlower();

				int m = LivingEntityRenderer.getOverlay(moobloomEntity, 0.0F);

                // grab da flower model
				BakedModel bakedModel = blockRenderManager.getModel(flower);

                // back body flower i thinkk?
				matrixStack.push();
				matrixStack.translate(0.10000000298023224D, -0.3499999940395355D, 0.5D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5D, -0.5D, -0.5D);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, flower, m, bakedModel);
				matrixStack.pop();

                // other body flower
				matrixStack.push();
				matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
				matrixStack.translate(-0.10000000149011612D, 0.0D, -0.6000000238418579D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5D, -0.5D, -0.5D);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, flower, m, bakedModel);
				matrixStack.pop();
                
                // test third flower on body bc mc earth has it :O
				matrixStack.push();
				matrixStack.translate(0.20000000298023224D, -0.3499999940395355D, 0.5D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
				matrixStack.translate(0.10000000149011612D, 0.0D, -0.5000000238418579D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0F));
				matrixStack.translate(0.25000000149011612D, 0.0D, -0.2020000238418579D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5D, -0.5D, -0.5D);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, flower, m, bakedModel);
				matrixStack.pop();

                // flower on hed
				matrixStack.push();
				((MoobloomEntityModel)this.getContextModel()).getHead().rotate(matrixStack);
				matrixStack.translate(0.0D, -0.699999988079071D, -0.20000000298023224D);
				matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5D, -0.5D, -0.5D);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockRenderManager, flower, m, bakedModel);
				matrixStack.pop();
			}
		}
	}

	private void renderFlower(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockRenderManager blockRenderManager, BlockState flowerState, int overlay, BakedModel flowerModel) {
		if (renderAsModel) {
			blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), flowerState, flowerModel, 0.0F, 0.0F, 0.0F, light, overlay);
		} else {
			blockRenderManager.renderBlockAsEntity(flowerState, matrices, vertexConsumers, light, overlay);
		}

	}
    
}
