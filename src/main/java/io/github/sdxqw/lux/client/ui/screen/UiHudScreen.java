package io.github.sdxqw.lux.client.ui.screen;

import io.github.sdxqw.lux.LuxRecode;
import io.github.sdxqw.lux.client.module.IModule;
import io.github.sdxqw.lux.client.module.ModuleBase;
import io.github.sdxqw.lux.client.ui.render.UiButton;
import io.github.sdxqw.lux.client.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Collections;

public class UiHudScreen extends UiScreen {

    private int lastDraggedMod = 0;

    @Override
    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
        addButtons(new UiButton(0, this.width / 2 - 35, this.height / 2 + 20, 60, 20, "CLOSE", false));
    }

    private void addButtons(GuiButton... p) {
        Collections.addAll(buttonList, p);
    }

    @Override
    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
        drawBackground();
        boolean doDrag = true;
        float lineWidth = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor() / 1.4F;
        RenderUtils.drawOutline(3, 3, this.width - 6, this.height - 6, (int) lineWidth, new Color(137, 255, 254, 100).getRGB());
        for (ModuleBase module : LuxRecode.getInstance().getModule().getMods()) {
            if (module.isEnabled() && module instanceof IModule) {
                ((IModule) module).drawOnScreen(mouseX, mouseY);
                RenderUtils.drawOutline(module.getX(), module.getY(), ((IModule) module).getWidth(), ((IModule) module).getHeight(), (int) lineWidth, new Color(137, 255, 254, 100).getRGB());
                if (module.hashCode() == this.lastDraggedMod && module.getComponent().isDraggingModule(mouseX, mouseY)) {
                    doDrag = false;
                }
            }
        }

        for (ModuleBase module : LuxRecode.getInstance().getModule().getMods()) {
            if (doDrag && module.isEnabled() && module instanceof IModule && module.getComponent().isDraggingModule(mouseX, mouseY)) {
                doDrag = false;
                this.lastDraggedMod = module.hashCode();
            }
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation("lux/luxlogo.png"));
        Gui.drawModalRectWithCustomSizedTexture(this.width / 2 - 30, this.height / 2 - 50, 0, 0, 52, 52, 52, 52);
    }

    private void drawBackground() {
        this.drawRectBackground();
    }

    private void drawRectBackground() {
        if (this.mc.theWorld != null) {
            this.drawGradientRect(0, 0, this.width, this.height, (new Color(0, 0, 0, 120)).getRGB(), (new Color(0, 0, 0, 105)).getRGB());
        } else {
            this.drawBackground(0);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
