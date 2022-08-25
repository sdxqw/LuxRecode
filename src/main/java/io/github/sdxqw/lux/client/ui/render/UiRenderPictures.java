package io.github.sdxqw.lux.client.ui.render;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

@Getter
public class UiRenderPictures {
    public static final Minecraft mc = Minecraft.getMinecraft();

    private final ResourceLocation logoLocation;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public UiRenderPictures(int x, int y, int width, int height, String logoLocation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.logoLocation = new ResourceLocation("lux/" + logoLocation);
    }

    public void drawPicture() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        mc.getTextureManager().bindTexture(getLogoLocation());
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(getX(), getY(), 0.0f, 0.0f, getWidth(), getHeight(), getWidth(), getHeight());
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
    }
}
