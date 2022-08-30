package io.github.sdxqw.lux.client.module.features;

import io.github.sdxqw.lux.client.module.ModuleRender;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class TestMod extends ModuleRender {

    public TestMod() {
        super("Test mod", "test");
        initComponent(60, 60);
    }

    @Override
    public void drawInGame() {
        Gui.drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight() + 4, new Color(0, 0, 0, 120).getRGB());
        UiFontRenderer.getText().drawCenteredString(getName(), this.getX() + (this.getWidth() >> 1), (float) (this.getY() + (this.getHeight() - UiFontRenderer.getText().FONT_HEIGHT + 4) / 2), -1);
    }

    @Override
    public void drawOnScreen(int mouseX, int mouseY) {
        UiFontRenderer.getText().drawCenteredString(getName(), this.getX() + (this.getWidth() >> 1), (float) (this.getY() + (this.getHeight() - UiFontRenderer.getText().FONT_HEIGHT + 4) / 2), -1);
    }

    @Override
    public int getWidth() {
        return UiFontRenderer.getText().getFont().getWidth(getName());
    }

    @Override
    public int getHeight() {
        return UiFontRenderer.getText().FONT_HEIGHT;
    }
}
