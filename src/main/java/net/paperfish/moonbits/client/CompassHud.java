package net.paperfish.moonbits.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ClientBossBar;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.item.Items;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.UUID;

import static java.lang.Character.toUpperCase;
import static java.lang.Math.round;

public class CompassHud extends DrawableHelper {
    private static final Identifier COMPASS_BAR = new Identifier("moonbits", "textures/gui/compass_hud.png");
    private static final int BAR_WIDTH = 256;
    private static final int BAR_HEIGHT = 15;
    private static final int BAR_XSLICE = 182;
    private final MinecraftClient client;

    public CompassHud(MinecraftClient client) {
        this.client = client;
    }

    public void renderCompass(MatrixStack matrixStack) {
            MinecraftClient client = MinecraftClient.getInstance();
            ClientPlayerEntity player = client.player;
            int screenHeight = client.getWindow().getScaledHeight();
            int screenWidth = client.getWindow().getScaledWidth();
            int centreX = screenWidth / 2;
            boolean hasCompass = false;
            for (int i = 0; i < 9; i++) {
                assert player != null;
                if (player.getInventory().getStack(i).isOf(Items.COMPASS)){
                    hasCompass = true;
                }
            }
            if (hasCompass) {
                BlockPos blockPos = player.getBlockPos();
                String facing = String.valueOf(toUpperCase(player.getHorizontalFacing().getName().charAt(0)));
                String coords = String.format("(%d, %d)", blockPos.getX(), blockPos.getZ());
                String fullInfo = String.format("%s (%d, %d)", facing, blockPos.getX(), blockPos.getZ());
                int a = (client.textRenderer.getWidth(fullInfo) / 2) + 1;
                int yPos;
                int location = 1; // temp

                if (location == 0) {
                    if (player.isCreative()) {
                        // if ur in creative, render right above hotbar
                        yPos = screenHeight - 34;
                    }
                    else {
                        if (player.isSubmergedIn(FluidTags.WATER) || (player.getAir() < player.getMaxAir())) {
                            // if the air meter is displayed, push coords up above that
                            yPos = screenHeight - 60;
                        } else {
                            // put the coords above the hunger bar
                            yPos = screenHeight - 50;
                        }
                    }
                    client.textRenderer.drawWithShadow(matrixStack, fullInfo, (centreX + 90) - client.textRenderer.getWidth(fullInfo), yPos, 0xffffff, false);
                }
                if (location == 1) {
                    yPos = 5;
                    client.textRenderer.drawWithShadow(matrixStack, coords, centreX - (client.textRenderer.getWidth(coords) >> 1), yPos, 0xffffff, false);

                    int k = screenWidth / 2 - 91;
                    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                    RenderSystem.setShaderTexture(0, COMPASS_BAR);
                    this.renderCompassBar(matrixStack, k, 16, player);
                }
                if (location == 2) {
                    client.textRenderer.drawWithShadow(matrixStack, fullInfo, 4, 4, 0xffffff, false);
                }
            }
        }

    public void renderCompassBar(MatrixStack matrices, int x, int y, ClientPlayerEntity player) {
        // get player yaw as a percentage
        float yawPercent = (player.getYaw() / 360);
        // calculate start point of the uv by converting the percentage to pixels
        int uvStart = round(yawPercent * BAR_WIDTH) + 1;
        this.drawTexture(matrices, x, y, 0, 16, BAR_XSLICE, 5);
        this.drawTexture(matrices, x + 1, y, uvStart, 0, BAR_XSLICE - 2, BAR_HEIGHT);
    }
}
