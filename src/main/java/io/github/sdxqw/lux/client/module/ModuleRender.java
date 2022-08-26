package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.ui.screen.UIDraggableComponent;
import lombok.Getter;

@Getter
public abstract class ModuleRender extends ModuleBase {

    protected UIDraggableComponent component;

    protected ModuleRender(String name) {
        super(name);
    }

    /**
     * Prevent bug with settings, please use this method on your Constructor body.
     * @param x int x of the mod.
     * @param y int y of the mod.
     */
    public void initComponent(int x, int y) {
        component = new UIDraggableComponent(x, y, getWidth(), getHeight());
    }

    public abstract void drawInGame();

    public abstract void drawOnScreen(int mouseX, int mouseY);

    public abstract int getWidth();

    public abstract int getHeight();

    public int getX() {
        return component.getX();
    }

    public int getY() {
        return component.getY();
    }

}
