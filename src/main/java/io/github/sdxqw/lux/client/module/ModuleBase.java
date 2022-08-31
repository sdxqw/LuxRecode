package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.ui.screen.UiDraggableComponent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@Getter
public abstract class ModuleBase {

    private final Minecraft mc;
    private final String name;
    private final ResourceLocation icon;
    protected UiDraggableComponent component;
    @Setter
    private boolean enabled;

    public ModuleBase() {
        this.name = this.getClass().getAnnotation(ModuleInfo.class).name();
        this.icon = new ResourceLocation("lux/icons/mods/" + this.getClass().getAnnotation(ModuleInfo.class).icon() + ".png");
        this.enabled = this.getClass().getAnnotation(ModuleInfo.class).enabled();
        mc = Minecraft.getMinecraft();
        if (this instanceof IModule) {
            component = new UiDraggableComponent(this.getClass().getAnnotation(ModuleInfo.class).x(), this.getClass().getAnnotation(ModuleInfo.class).y(), ((IModule) this).getWidth(), ((IModule) this).getHeight());
        }
    }

    public int getX() {
        return component.getX();
    }

    public int getY() {
        return component.getY();
    }

    public void toggleModules() {
        setEnabled(!isEnabled());
    }
}
