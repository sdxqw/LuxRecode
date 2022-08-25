package io.github.sdxqw.lux.client.ui.render;

import io.github.sdxqw.lux.client.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glEnable;

public class UiAccountButton extends GuiButton {

    @Getter
    private double hoverFade = 1.0;

    public UiAccountButton(int id, int x, int y) {
        super(id, x, y, 100, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (isHovered(mouseX, mouseY)) {
            if (hoverFade < 1.5) hoverFade += 0.05;
        } else if (hoverFade > 1.0) hoverFade -= 0.05;
        Color c = new Color(255, 255, 255, 30);
        Color newC = new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (c.getAlpha() * hoverFade));
        ResourceLocation headLocation = RenderUtils.getHeadLocation(mc.getSession().getUsername());
        Minecraft.getMinecraft().getTextureManager().bindTexture(headLocation);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(this.xPosition + 3, this.yPosition + 4, 0, 0, 12, 12, 12, 12);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        glEnable(GL11.GL_LINE_SMOOTH);
        glEnable(GL11.GL_POINT_SMOOTH);
        glEnable(GL13.GL_MULTISAMPLE);
        RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, newC.getRGB());
        RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 2,  new Color(164, 172, 180, 255).getRGB());
        glEnable(GL_BLEND);
        UiFontRenderer.getText().drawString(Minecraft.getMinecraft().getSession().getUsername(), this.xPosition + 18, (float) (this.yPosition + ((this.height - 7) >> 1)), new Color(255, 255, 255, 150).getRGB());
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }
}
