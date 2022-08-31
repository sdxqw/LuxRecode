package io.github.sdxqw.lux.client.ui.screen;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Collections;
import java.util.List;

public class UiScreen extends GuiScreen {
    public final List<UiComponent> components = Lists.newArrayList();

    @Getter
    public ScaledResolution sr;

    public void initComponent(int mouseX, int mouseY, boolean shouldRender) {
    }

    public void renderScreen(int mouseX, int mouseY, boolean shouldRender) {
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
        renderScreen(mouseX, mouseY, mc.theWorld != null);
        components.forEach(e -> {
            if (e.isVisible()) e.drawComponent(mouseX, mouseY, mc.theWorld != null);
        });
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
