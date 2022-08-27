package io.github.sdxqw.lux.client.module.features;

import io.github.sdxqw.lux.client.module.ModuleRender;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class FPSMod5 extends ModuleRender {

    protected String prefix = "FPS: ";

    public FPSMod5() {
        super("Fps mod", "test");
        initComponent(100, 100);
    }

    @Override
    public void drawInGame() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), new Color(0, 0, 0, 120).getRGB());
        UiFontRenderer.getText().drawCenteredString(prefix + Minecraft.getDebugFPS(), this.getX() + (this.getWidth() >> 1), (float) (this.getY() + (this.getHeight() - UiFontRenderer.getText().FONT_HEIGHT - 1) / 2), -1);
    }

    @Override
    public void drawOnScreen(int mouseX, int mouseY) {
        UiFontRenderer.getText().drawCenteredString(prefix + "1000", this.getX() + (this.getWidth() >> 1), (float) (this.getY() + (this.getHeight() - UiFontRenderer.getText().FONT_HEIGHT - 1) / 2), -1);
    }

    @Override
    public int getWidth() {
        return UiFontRenderer.getText().getFont().getWidth(prefix + "100");
    }

    @Override
    public int getHeight() {
        return UiFontRenderer.getText().FONT_HEIGHT;
    }
}
