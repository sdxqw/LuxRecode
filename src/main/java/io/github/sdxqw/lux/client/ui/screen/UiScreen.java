package io.github.sdxqw.lux.client.ui.screen;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Getter
public class UiScreen extends GuiScreen {

    protected final List<UiComponent> components = Lists.newArrayList();
    protected ScaledResolution sr;
    protected float scaledWidth;
    protected float scaledHeight;

    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
    }

    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
    }

    public void onClick(int mouseX, int mouseY, int state) {
    }

    @Override
    public void initGui() {
        this.components.clear();
        sr = new ScaledResolution(mc);
        initComponent(mc.mouseHelper.deltaX, mc.mouseHelper.deltaY, mc.theWorld != null);
        super.initGui();
    }

    public void draw(UiComponent... components) {
        Collections.addAll(this.components, components);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        final float scaleFactor = getScaleFactor();
        GL11.glPushMatrix();
        GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
        renderScreen((int) (mouseX / scaleFactor), (int) (mouseY / scaleFactor), mc.theWorld != null);
        components.forEach(e -> {
            if (e.isVisible()) e.drawComponent((int) (mouseX / scaleFactor), (int) (mouseY / scaleFactor), mc.theWorld != null);
        });
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int state) throws IOException {
        final float scaleFactor = getScaleFactor();
        onClick((int) (mouseX/scaleFactor), (int) (mouseY/scaleFactor), state);
        super.mouseClicked(mouseX, mouseY, state);
    }

    public float getScaleFactor() {
        return 1.0f / (getSr().getScaleFactor() * .5f);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
