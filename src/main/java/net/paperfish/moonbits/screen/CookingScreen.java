package net.paperfish.moonbits.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.paperfish.moonbits.Moonbits;

@Environment(value= EnvType.CLIENT)
public class CookingScreen extends HandledScreen<CookingScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Moonbits.MODID, "textures/gui/container/cooker.png");

    public CookingScreen(CookingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        int m;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);

//        boolean needsBowl = this.handler.getBowl();
//        int l = MathHelper.clamp((18 * k + 20 - 1) / 20, 0, 18);
//        if (l > 0) {
//            this.drawTexture(matrices, i + 60, j + 44, 176, 29, l, 4);
//        }

        if ((m = this.handler.getPrepTime()) > 0) {
            this.drawTexture(matrices, i + 98, j + 34, 176, 14, m + 1, 16);
        }
    }
}
