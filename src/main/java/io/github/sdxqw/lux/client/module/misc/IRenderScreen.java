package io.github.sdxqw.lux.client.module.misc;

import net.minecraft.client.Minecraft;

public interface IRenderScreen {
    Minecraft mc = Minecraft.getMinecraft();
    void drawInGame();
    void drawOnScreen(int mouseX, int mouseY);
    int getHeight();
    int getWidth();
}
