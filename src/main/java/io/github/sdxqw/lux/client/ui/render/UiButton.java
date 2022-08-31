package io.github.sdxqw.lux.client.ui.render;

import io.github.sdxqw.lux.client.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

public class UiButton extends GuiButton {

    @Getter
    private double hoverFade = 1.0;
    @Getter
    private final boolean bordered;

    public UiButton(int buttonId, int x, int y, int width, int height, String text, boolean bordered) {
        super(buttonId, x, y, width, height, text);
        this.bordered = bordered;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if(isHovered(mouseX, mouseY)) {
            if(hoverFade < 1.5) hoverFade += 0.05;
        } else if(hoverFade > 1.0) hoverFade -= 0.05;
        if (bordered) {
            Color c = new Color(255, 255, 255, 30);
            Color newC = new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (c.getAlpha() * hoverFade));
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glEnable(GL11.GL_POINT_SMOOTH);
            GL11.glEnable(GL13.GL_MULTISAMPLE);
            RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, newC.getRGB());
            RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 2,  new Color(164, 172, 180, 255).getRGB());
        }
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        UiFontRenderer.getText().drawCenteredString(this.displayString.toUpperCase(), this.xPosition + (this.width >> 1), (float) (this.yPosition + ((this.height - 10) >> 1)), new Color(255,255,255).getRGB());
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }
}
