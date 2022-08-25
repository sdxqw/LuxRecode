package io.github.sdxqw.lux.client.ui.screen;

import io.github.sdxqw.lux.client.ui.render.UiButton;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import io.github.sdxqw.lux.client.ui.render.UiIconButton;
import io.github.sdxqw.lux.client.ui.render.UiRenderPictures;
import io.github.sdxqw.lux.client.util.ReferenceUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Collections;

import static org.lwjgl.opengl.GL11.*;

public class UiMainMenu extends UiScreen {

    @Override
    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
        int buttonY = this.height - 23;
        int displayTextX = 5;
        int displayTextY = this.height - 35;
        this.addButton(
                new UiButton(0, sr.getScaledWidth() / 2 - 70, sr.getScaledHeight() / 2 - 10, 140, 18, "Singleplayer"),
                new UiButton(1, sr.getScaledWidth() / 2 - 70, sr.getScaledHeight() / 2 + 12 , 140, 18, "Multiplayer"),
                new UiIconButton(2, 5, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/cart.png", "Store"),
                new UiIconButton(3, 28, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/changelog.png", "Patch Notes"),
                new UiIconButton(4, 51, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/bulb.png", "Lux Settings"),
                new UiIconButton(5, 74, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/cog.png", "Game Settings")
        );
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/panorama/normal/background.png"));
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(-22 + (Mouse.getX() / 90), (Mouse.getY() * -1 / 90), 0, 0, width + 20, height + 18, width + 21f, height + 20f);
        new UiRenderPictures(sr.getScaledWidth() / 2 - 26, sr.getScaledHeight() / 2 - 80, 52, 52, "luxlogo.png").drawPicture();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        UiFontRenderer.getTitleBold().drawCenteredTextScaled(ReferenceUtils.getName().toUpperCase(), sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 28, -1, 0.6);
        String s = "COPYRIGHT MOJANG AB. DO NOT DISTRIBUTE!";
        UiFontRenderer.getText().drawString(s, this.width - UiFontRenderer.getText().getWidth(s) - 5, this.height - 11, new Color(255,255,255).getRGB());
    }

    private void addButton(GuiButton... a) {
        Collections.addAll(buttonList, a);
    }
}
