package net.moonteam.moonbits.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ItemFrameEntityRenderer;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.util.Identifier;

public class ItemHookEntityRenderer<T extends ItemFrameEntity> extends ItemFrameEntityRenderer<T> {
    private static final ModelIdentifier NORMAL_FRAME = new ModelIdentifier(new Identifier("moonbits", "textures/entity/item_hook/item_hook.png"), "map=false");
    private static final ModelIdentifier MAP_FRAME = new ModelIdentifier(new Identifier("moonbits", "textures/entity/item_hook/item_hook.png"), "map=true");
    private static final ModelIdentifier GLOW_FRAME = new ModelIdentifier(new Identifier("moonbits", "textures/entity/item_hook/glow_item_hook.png"), "map=false");
    private static final ModelIdentifier MAP_GLOW_FRAME = new ModelIdentifier(new Identifier("moonbits", "textures/entity/item_hook/glow_item_hook.png"), "map=true");
    public ItemHookEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }
}
