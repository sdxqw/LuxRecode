package io.github.sdxqw.lux.client.ui.screen;

import io.github.sdxqw.lux.LuxRecode;
import io.github.sdxqw.lux.client.ui.render.*;
import io.github.sdxqw.lux.client.util.ReferenceUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.net.URI;
import java.util.Collections;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;

public class UiMainMenu extends UiScreen {

    @Override
    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
        int buttonY = this.height - 23;
        int displayTextX = 5;
        int displayTextY = this.height - 35;
        this.addButton(
                new UiButton(0, sr.getScaledWidth() / 2 - 70, sr.getScaledHeight() / 2 - 10, 140, 18, "Singleplayer", true),
                new UiButton(1, sr.getScaledWidth() / 2 - 70, sr.getScaledHeight() / 2 + 12, 140, 18, "Multiplayer", true),
                new UiIconButton(2, 5, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/cart.png", "Store", true),
                new UiIconButton(3, 28, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/changelog.png", "Patch Notes", true),
                new UiIconButton(4, 51, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/bulb.png", "Lux Settings", true),
                new UiIconButton(5, 74, buttonY, 20, 20, displayTextX, displayTextY, "main_menu/cog.png", "Game Settings", true),
                new UiIconButton(6, this.width - 23, 5, 20, 20, this.width - 21, 28, "close.png", "Quit", true),
                new UiAccountButton(7, this.width - 128, 5)
        );
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
        mc.getTextureManager().bindTexture(new ResourceLocation("lux/panorama/normal/background.png"));
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        Gui.drawModalRectWithCustomSizedTexture(-22 + (Mouse.getX() / 90), (Mouse.getY() * -1 / 90), 0, 0, width + 20, height + 18, width + 21f, height + 20f);
        new UiRenderPictures(sr.getScaledWidth() / 2 - 26, sr.getScaledHeight() / 2 - 84, 52, 52, "luxlogo.png").drawPicture();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        UiFontRenderer.getTitleBold().drawCenteredTextScaled(ReferenceUtils.getName().toUpperCase(), sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 32, -1, 1);
        String s = "COPYRIGHT MOJANG AB. DO NOT DISTRIBUTE!";
        UiFontRenderer.getText().drawString(s, this.width - UiFontRenderer.getText().getWidth(s) - 5, this.height - 11, new Color(255, 255, 255, 100).getRGB());
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;

            case 1:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;

            // Store
            case 2:
                String url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (Exception e) {
                    System.err.println("Could not open url: " + url);
                }
                break;

            // Patch Notes
            case 3:
                break;

            // Lux Settings
            case 4:
                break;

            case 5:
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;

            case 6:
                mc.shutdown();
                break;
            default:
                LuxRecode.getLuxLog().log(Level.INFO, "This button doesnt exists");
        }
    }

    private void addButton(GuiButton... a) {
        Collections.addAll(buttonList, a);
    }
}
