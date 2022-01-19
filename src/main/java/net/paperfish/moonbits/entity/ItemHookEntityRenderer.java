package net.paperfish.moonbits.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.paperfish.moonbits.MBEntities;
import net.paperfish.moonbits.MoonbitsMain;

@Environment(value= EnvType.CLIENT)
public class ItemHookEntityRenderer<T extends ItemHookEntity> extends EntityRenderer<T> {
    private static final Identifier HOOK = new Identifier("moonbits", "block/item_hook");
    //private static final Identifier GLOW_HOOK = new Identifier("moonbits", "glow_item_hook");
    private static final ModelIdentifier ITEM_HOOK = new ModelIdentifier(new Identifier(MoonbitsMain.MOD_ID, "item_hook"), "");
    private static final ModelIdentifier GLOW_HOOK = new ModelIdentifier(new Identifier(MoonbitsMain.MOD_ID, "glow_item_hook"), "");
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final ItemRenderer itemRenderer;

    public ItemHookEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(T itemHookEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(itemHookEntity, f, g, matrixStack, vertexConsumerProvider, i);
        matrixStack.push();
        Direction direction = itemHookEntity.getHorizontalFacing();
        Vec3d vec3d = this.getPositionOffset(itemHookEntity, g);
        matrixStack.translate(-vec3d.getX(), -vec3d.getY(), -vec3d.getZ());
        double d = 0.46875;
        matrixStack.translate((double)direction.getOffsetX() * 0.46875, (double)direction.getOffsetY() * 0.46875, (double)direction.getOffsetZ() * 0.46875);
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(itemHookEntity.getPitch()));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0f - itemHookEntity.getYaw()));
        boolean bl = itemHookEntity.isInvisible();
        ItemStack itemStack = itemHookEntity.getHeldItemStack();
        if (itemStack.isEmpty()) {
            BlockRenderManager blockRenderManager = this.client.getBlockRenderManager();
            BakedModelManager bakedModelManager = blockRenderManager.getModels().getModelManager();
            ModelIdentifier modelIdentifier = this.getModelId(itemHookEntity, itemStack);
            matrixStack.push();
            matrixStack.translate(-0.5, -0.5, -0.5);
            blockRenderManager.getModelRenderer().render(matrixStack.peek(), vertexConsumerProvider.getBuffer(TexturedRenderLayers.getEntitySolid()), null, bakedModelManager.getModel(modelIdentifier), 1.0f, 1.0f, 1.0f, i, OverlayTexture.DEFAULT_UV);
            matrixStack.pop();
        }
        else {
            boolean blockRenderManager = itemStack.isOf(Items.FILLED_MAP);
            matrixStack.translate(0.0, 0.0, 0.4375);
            int bakedModelManager = blockRenderManager ? itemHookEntity.getRotation() % 4 * 2 : itemHookEntity.getRotation();
            matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion((float)bakedModelManager * 360.0f / 8.0f));
            if (blockRenderManager) {
                matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0f));
                float modelIdentifier = 0.0078125f;
                matrixStack.scale(0.0078125f, 0.0078125f, 0.0078125f);
                matrixStack.translate(-64.0, -64.0, 0.0);
                Integer integer = FilledMapItem.getMapId(itemStack);
                MapState mapState = FilledMapItem.getMapState(integer, itemHookEntity.world);
                matrixStack.translate(0.0, 0.0, -1.0);
                if (mapState != null) {
                    int j = this.getLight(itemHookEntity, LightmapTextureManager.MAX_SKY_LIGHT_COORDINATE | 0xD2, i);
                    this.client.gameRenderer.getMapRenderer().draw(matrixStack, vertexConsumerProvider, integer, mapState, true, j);
                }
            } else {
                int modelIdentifier = this.getLight(itemHookEntity, LightmapTextureManager.MAX_LIGHT_COORDINATE, i);
                matrixStack.scale(0.5f, 0.5f, 0.5f);
                this.itemRenderer.renderItem(itemStack, ModelTransformation.Mode.FIXED, modelIdentifier, OverlayTexture.DEFAULT_UV, matrixStack, vertexConsumerProvider, itemHookEntity.getId());
            }
        }
        matrixStack.pop();
    }

    @Override
    protected int getBlockLight(T itemHookEntity, BlockPos blockPos) {
        if (itemHookEntity.getType() == MBEntities.GLOW_ITEM_HOOK_ENTITY) {
            return Math.max(5, super.getBlockLight(itemHookEntity, blockPos));
        }
        return super.getBlockLight(itemHookEntity, blockPos);
    }

    private int getLight(T itemFrame, int glowLight, int regularLight) {
        return itemFrame.getType() == MBEntities.GLOW_ITEM_HOOK_ENTITY ? glowLight : regularLight;
    }

    private ModelIdentifier getModelId(T entity, ItemStack stack) {
        boolean bl = entity.getType() == MBEntities.GLOW_ITEM_HOOK_ENTITY;
        return bl ? GLOW_HOOK : ITEM_HOOK;
    }

    @Override
    public Vec3d getPositionOffset(T itemHookEntity, float f) {
        return new Vec3d((float) itemHookEntity.getHorizontalFacing().getOffsetX() * 0.3f, -0.25, (float) itemHookEntity.getHorizontalFacing().getOffsetZ() * 0.3f);
    }

    @Override
    public Identifier getTexture(T itemHookEntity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }

    @Override
    protected boolean hasLabel(T itemHookEntity) {
        if (!MinecraftClient.isHudEnabled() || itemHookEntity.getHeldItemStack().isEmpty() || !itemHookEntity.getHeldItemStack().hasCustomName() || this.dispatcher.targetedEntity != itemHookEntity) {
            return false;
        }
        double d = this.dispatcher.getSquaredDistanceToCamera(itemHookEntity);
        float f = itemHookEntity.isSneaky() ? 32.0f : 64.0f;
        return d < (double)(f * f);
    }

    @Override
    protected void renderLabelIfPresent(T itemHookEntity, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.renderLabelIfPresent(itemHookEntity, itemHookEntity.getHeldItemStack().getName(), matrixStack, vertexConsumerProvider, i);
    }
}
