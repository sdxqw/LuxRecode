package io.github.sdxqw.lux.client.module.impl;

import io.github.sdxqw.lux.client.module.misc.Category;
import io.github.sdxqw.lux.client.module.Module;

public class TestRender extends Module {
    public TestRender() {
        super("test", Category.PVP);
        initComponent(100, 100);
    }

    @Override
    public void drawOnScreen(int mouseX, int mouseY) {
        mc.fontRendererObj.drawString(getName(), getX(), getY(), -1);
    }

    @Override
    public void drawInGame() {
        mc.fontRendererObj.drawString(getName(), getX(), getY(), -1);
    }

    @Override
    public int getHeight() {
        return mc.fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public int getWidth() {
        return mc.fontRendererObj.getStringWidth(getName());
    }
}
