package io.github.sdxqw.lux.client.ui.screen;

import io.github.sdxqw.lux.client.util.RenderUtils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.awt.*;

@Getter
public class UIDraggableComponent {

    public final int width;
    public final int height;
    private int x;
    private int y;
    private int lastX;
    private int lastY;
    private boolean draggingModule;

    public UIDraggableComponent(int x, int y, int widthIn, int heightIn) {
        this.x = x;
        this.y = y;
        this.width = widthIn;
        this.height = heightIn;
    }

    public boolean isDraggingModule(int mouseX, int mouseY) {
        boolean hovered = mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight();
        if (hovered) {
            RenderUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidth() + 3, this.getHeight() + 2, new Color(0, 204, 255, 152).getRGB());
        } else
            RenderUtils.drawHollowRect(this.getX() - 2, this.getY() - 2, this.getWidth() + 3, this.getHeight() + 2, new Color(170, 170, 170, 100).getRGB());
        if (this.draggingModule) {
            this.x = mouseX + this.lastX;
            this.y = mouseY + this.lastY;
            this.setBounds();
            if (!Mouse.isButtonDown(0)) this.draggingModule = false;
        }
        boolean mouseOverX = (mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth());
        boolean mouseOverY = (mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight());
        if (mouseOverX && mouseOverY && !this.draggingModule && Mouse.isButtonDown(0)) {
            this.lastX = x - mouseX;
            this.lastY = y - mouseY;
            this.draggingModule = true;
        }
        return draggingModule;
    }

    public void setBounds() {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        if (this.x < 4)
            this.x = 4;

        if (this.y < 5)
            this.y = 5;

        if (this.x + this.width > res.getScaledWidth() - 5)
            this.x = res.getScaledWidth() - this.width - 5;

        if (this.y + this.height > res.getScaledHeight() - 4)
            this.y = res.getScaledHeight() - this.height - 4;
    }

}
