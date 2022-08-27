package io.github.sdxqw.lux.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ScissorsUtils {

    private ScissorsUtils() {}

    public static void enable() {
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
    }

    public static void disable() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void select(int x, int y, int width, int height) {
        ScaledResolution r = new ScaledResolution(Minecraft.getMinecraft());
        int scale = r.getScaleFactor();
        int transY = r.getScaledHeight() - y - height;
        GL11.glScissor(x * scale, transY * scale, width * scale, height * scale);
    }

}
