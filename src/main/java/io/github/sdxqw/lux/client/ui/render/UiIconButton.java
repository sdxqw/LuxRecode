package io.github.sdxqw.lux.client.ui.render;

import io.github.sdxqw.lux.client.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.awt.*;

@Getter
public class UiIconButton extends GuiButton {

    private final ResourceLocation location;
    private double hoverFade = 1.0;
    protected int textX;
    protected int textY;
    protected String text;
    protected boolean bordered;

    public UiIconButton(int buttonId, int x, int y, int widthIn, int heightIn, int textX, int textY, String iconName, String textToDisplay, boolean bordered) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.location = new ResourceLocation("lux/icons/" + iconName);
        this.textX = textX;
        this.textY = textY;
        this.text = textToDisplay;
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
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glEnable(GL11.GL_POINT_SMOOTH);
            RenderUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, newC.getRGB());
            RenderUtils.drawRoundedOutline(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 2,  new Color(164, 172, 180, 255).getRGB());
        }
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        mc.getTextureManager().bindTexture(getLocation());
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1F);
        Gui.drawModalRectWithCustomSizedTexture(this.xPosition + (this.width - 12) / 2, this.yPosition + (this.height - 12) / 2, 0, 0, 12, 12, 12, 12);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();

        if(!this.text.equals("") && isHovered(mouseX, mouseY))
            this.renderDisplayText();
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    private void renderDisplayText() {
        int textWidth = (int) (UiFontRenderer.getText().getWidth(this.text.toUpperCase()));
        int textHeight = 7;

        RenderUtils.drawRoundedRect(this.textX - 2, this.textY - 1, this.textX + textWidth + 3, this.textY + textHeight + 2, 3, new Color(255, 255, 255, 30).getRGB());
        UiFontRenderer.getText().drawString(this.text.toUpperCase(), this.textX, this.textY, new Color(255, 255, 255, 150).getRGB());
    }
}
