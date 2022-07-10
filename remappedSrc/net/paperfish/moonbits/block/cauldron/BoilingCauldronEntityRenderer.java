package net.paperfish.moonbits.block.cauldron;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;

@Environment(value= EnvType.CLIENT)
public class BoilingCauldronEntityRenderer implements BlockEntityRenderer<BoilingCauldronEntity> {
    private static final float SCALE = 0.375f;

    public BoilingCauldronEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(BoilingCauldronEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        DefaultedList<ItemStack> defaultedList = entity.getItemsBeingCooked();
        int k = (int)entity.getPos().asLong();
        for (int l = 0; l < defaultedList.size(); ++l) {
            ItemStack itemStack = defaultedList.get(l);
            if (itemStack == ItemStack.EMPTY) continue;
            matrices.push();
            matrices.translate(0.5, 0.44921875, 0.5);
            Direction direction2 = Direction.fromHorizontal(l % 4);
            float g = -direction2.asRotation();
            matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(g));
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0f));
            matrices.translate(-0.3125, -0.3125, 0.0);
            matrices.scale(SCALE, SCALE, SCALE);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, k + l);
            matrices.pop();
        }
    }
}
