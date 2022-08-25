package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.module.misc.Category;
import io.github.sdxqw.lux.client.module.misc.IRenderScreen;
import io.github.sdxqw.lux.client.ui.screen.UIDraggableComponent;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Module implements IRenderScreen {

    private final String name;
    private final Category category;
    @Setter
    private boolean enabled;
    private UIDraggableComponent component;

    protected Module(String name, Category category) {
        this.name = name;
        this.category = category;
        enabled = true;
    }

    public void initComponent(int x, int y) {
        component = new UIDraggableComponent(x, y, getWidth(), getHeight());
    }

    protected int getX() {
        return component.getX();
    }

    protected int getY() {
        return component.getY();
    }

}
