package io.github.sdxqw.lux.client.ui;

import io.github.sdxqw.lux.client.util.ReferenceUtils;
import io.github.sdxqw.lux.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class UiSplashScreen {

    private static final int MAX_STEPS = 5;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static int currentStep = 0;
    private static String currentText = "";

    private UiSplashScreen() {
    }

    public static void update(String text) {
        ++currentStep;
        currentText = text;
        renderSplashScreen();
    }

    private static void renderSplashScreen() {
        ScaledResolution res = new ScaledResolution(mc);
        int i = res.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(res.getScaledWidth() * i, res.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, res.getScaledWidth(), res.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        drawScreen(res);

        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(res.getScaledWidth() * i, res.getScaledHeight() * i);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);

        mc.updateDisplay();
    }

    private static void drawScreen(ScaledResolution res) {
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/panorama/normal/background.png"));
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, res.getScaledWidth(), res.getScaledHeight(), res.getScaledWidth(), res.getScaledHeight());
        renderGood();
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/luxlogo.png"));
        renderGood2();
        Gui.drawModalRectWithCustomSizedTexture(res.getScaledWidth() / 2 - 26, res.getScaledHeight() / 2 - 60, 0, 0, 52, 52, 52, 52);

        double progressWidth = ((double) currentStep / MAX_STEPS) * 180;
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        glEnable(GL_POINT_SMOOTH);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        RenderUtils.drawRoundedRect(res.getScaledWidth() / 2 - 90, res.getScaledHeight() / 2 + 20, 180, 10, 3, new Color(255, 255, 255, 30));
        RenderUtils.drawRoundedRect(res.getScaledWidth() / 2 - 90, res.getScaledHeight() / 2 + 19, (int) progressWidth, 12, 3, new Color(255, 255, 255, 30));
        RenderUtils.drawRoundedOutline(res.getScaledWidth() / 2 - 90, res.getScaledHeight() / 2 + 20, res.getScaledWidth() / 2 + 90, res.getScaledHeight() / 2 + 30, 4, res.getScaleFactor(), new Color(255, 255, 255, 150).getRGB());

        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        glEnable(GL_BLEND);
        FontRenderer.getTitleBold().drawCenteredTextScaled(ReferenceUtils.getName().toUpperCase(), res.getScaledWidth() / 2, res.getScaledHeight() / 2 - 8, -1, 0.6F);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        glEnable(GL_BLEND);
        FontRenderer.getText().drawCenteredTextScaled(currentText.toUpperCase(), res.getScaledWidth() / 2, res.getScaledHeight() / 2 + 32, new Color(255, 255, 255, 150).getRGB(), 1.2F);
    }

    private static void renderGood() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    }

    private static void renderGood2() {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
    }

}
