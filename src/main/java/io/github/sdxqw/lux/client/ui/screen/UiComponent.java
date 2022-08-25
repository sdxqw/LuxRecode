package io.github.sdxqw.lux.client.ui.screen;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
public abstract class UiComponent {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Minecraft mc;
    @Setter
    public boolean visible = true;

    protected UiComponent(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        mc = Minecraft.getMinecraft();
    }

    public abstract void drawComponent(int mouseX, int mouseY, boolean shouldRender);
}
