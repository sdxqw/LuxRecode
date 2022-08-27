package io.github.sdxqw.lux.client.module.ui;

import io.github.sdxqw.lux.client.module.ModuleBase;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import io.github.sdxqw.lux.client.ui.render.UiRenderPictures;
import io.github.sdxqw.lux.client.util.RenderUtils;
import lombok.AccessLevel;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.Color;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glEnable;

@Getter
public class UiButtonMod {

    private final int x;
    protected int y;
    private final int width;
    private final int height;
    private final ModuleBase module;

    @Getter(value = AccessLevel.NONE)
    private int hoverFade = 0;

    public UiButtonMod(int x, int y, int width, int height, ModuleBase module) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.module = module;
    }

    public void drawButton(int mouseX, int mouseY) {
        if (this.isHovered(mouseX, mouseY))
            hoverFade += 8;
        else if (hoverFade > 0)
            hoverFade -= 5;

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        RenderUtils.drawRoundedRect(this.x, this.y, this.x + this.width, this.y + this.height, 4, new Color(0, 0, 0, 100).getRGB());
        RenderUtils.drawRoundedOutline(this.x, this.y, this.x + this.width, this.y + this.height, 4, 2, getEnabledColor().getRGB());

        Color hover = new Color(this.getEnabledColor().getRed(), this.getEnabledColor().getGreen(), this.getEnabledColor().getBlue(), hoverFade);
        RenderUtils.drawRoundedRect(this.x, this.y, this.x + this.width, this.y + this.height, 4, hover.getRGB());
        glEnable(GL_BLEND);
        UiFontRenderer.getText().drawCenteredTextScaled(this.getModule().getName().toUpperCase(), this.x + (this.width >> 1), this.y + 40, new Color(255, 255, 255, 150).getRGB(), 1F);
        if (!getModule().getIcon().getResourcePath().equals(""))
            new UiRenderPictures(x + 20, y + 6, 32, 32, getModule().getIcon()).drawPicture(0.6f);
    }

    private Color getEnabledColor() {
        if (getModule().isEnabled()) return new Color(101, 220, 138, 255);
        return new Color(248, 92, 92, 255);
    }

    public void onClick(int mouseX, int mouseY, int button) {
        if(this.isHovered(mouseX, mouseY) && button == 0) {
            getModule().toggleModules();
        }
    }

    private boolean isHovered(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        // check if inside of ui list
        return (mouseX >= sr.getScaledWidth() / 2 - 175 &&
                mouseY >= sr.getScaledHeight() / 2 - 95 &&
                mouseX < sr.getScaledWidth() / 2 + 175 &&
                mouseY < sr.getScaledHeight() / 2 + 95)
                &&
                // check if hovered
                (mouseX >= x &&
                mouseY >= y &&
                mouseX < x + width &&
                mouseY < y + height);
    }

}
