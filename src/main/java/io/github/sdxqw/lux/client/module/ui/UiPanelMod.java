package io.github.sdxqw.lux.client.module.ui;

import io.github.sdxqw.lux.LuxRecode;
import io.github.sdxqw.lux.client.module.ModuleBase;
import io.github.sdxqw.lux.client.ui.render.UiButton;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import io.github.sdxqw.lux.client.ui.render.UiRenderPictures;
import io.github.sdxqw.lux.client.ui.screen.UiScreen;
import io.github.sdxqw.lux.client.util.RenderUtils;
import io.github.sdxqw.lux.client.util.ScissorsUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glEnable;

public class UiPanelMod extends UiScreen {

    private final List<UiButtonMod> modButtons = new ArrayList<>();
    protected int scrollAmount = 0;

    @Override
    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
        this.buttonList.add(new UiButton(0, sr.getScaledWidth() / 2 + 160, sr.getScaledHeight() / 2 - 115, 15, 15, "X", false));
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
        drawModMenu();
        int xAdd = 0;
        int xFactor = 80;
        int yAdd = 0;
        int spots = 0;
        while ((spots * xFactor) < (180)) {
            spots++;
        }
        modButtons.clear();
        for (ModuleBase module : LuxRecode.getInstance().getModule().getMods()) {
            if (xAdd == (spots * xFactor) && xAdd != 0) {
                xAdd = 0;
                yAdd += 80;
            }
            modButtons.add(new UiButtonMod(sr.getScaledWidth() / 2 - 60 + xAdd, sr.getScaledHeight() / 2 - 90 + yAdd + scrollAmount, 70, 65, module));
            xAdd += xFactor;
        }
        int wheel = Mouse.getDWheel();
        for (UiButtonMod e : modButtons) {
            GL11.glPushMatrix();
            ScissorsUtils.enable();
            ScissorsUtils.select(sr.getScaledWidth() / 2 - 175, sr.getScaledHeight() / 2 - 95, sr.getScaledWidth() / 2 + 175, sr.getScaledHeight() / 2 + 95);
            e.drawButton();
            if (wheel < 0) {
                e.y -= 16;
            } else if (wheel > 0) {
                e.y += 16;
            }
            ScissorsUtils.disable();
            GL11.glPopMatrix();
        }
    }

    public void drawModMenu() {
        RenderUtils.drawRoundedRect((sr.getScaledWidth() >> 1) - 65, (sr.getScaledHeight() >> 1) - 95, (sr.getScaledWidth() >> 1) + 175, (sr.getScaledHeight() >> 1) + 60, 5, new Color(255, 255, 255, 80).getRGB());
        RenderUtils.drawRoundedRect((sr.getScaledWidth() >> 1) - 175, (sr.getScaledHeight() >> 1) - 95, (sr.getScaledWidth() >> 1) - 70, (sr.getScaledHeight() >> 1) + 60, 5, new Color(255, 255, 255, 80).getRGB());
        RenderUtils.drawRoundedRect((sr.getScaledWidth() >> 1) - 180, (sr.getScaledHeight() >> 1) - 120, (sr.getScaledWidth() >> 1) + 180, (sr.getScaledHeight() >> 1) + 65, 5, new Color(0, 0, 0, 100).getRGB());

        new UiRenderPictures(sr.getScaledWidth() / 2 - 175, sr.getScaledHeight() / 2 - 115, 15, 15, "icons/web/websocket.png").drawPicture();

        glEnable(GL_BLEND);
        UiFontRenderer.getTitleBold().drawString("LUX", sr.getScaledWidth() / 2 - 158, sr.getScaledHeight() / 2 - 118, -1);
        UiFontRenderer.getTitleThin().drawString("RECODE", sr.getScaledWidth() / 2 - 128, sr.getScaledHeight() / 2 - 118, -1);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (UiButtonMod m : modButtons) {
            m.onClick(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Integer.signum(Mouse.getEventDWheel());
        scrollAmount += (10 * i);
        if (scrollAmount > 0) scrollAmount = 0;
        if (scrollAmount < -150) scrollAmount = -150;
    }
}
