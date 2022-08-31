package io.github.sdxqw.lux.client.module;

import com.google.common.collect.Lists;
import io.github.sdxqw.lux.client.module.settings.Settings;
import io.github.sdxqw.lux.client.ui.screen.UiDraggableComponent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

@Getter
public class ModuleBase {

    private final Minecraft mc;
    private final String name;
    private final ResourceLocation icon;
    private final List<Settings> settings;
    protected UiDraggableComponent component;
    @Setter
    private boolean enabled;

    public ModuleBase() {
        this.name = this.getClass().getAnnotation(ModuleInfo.class).name();
        this.icon = new ResourceLocation("lux/icons/mods/" + this.getClass().getAnnotation(ModuleInfo.class).icon() + ".png");
        this.enabled = this.getClass().getAnnotation(ModuleInfo.class).enabled();
        this.settings = Lists.newArrayList();
        this.mc = Minecraft.getMinecraft();
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    protected void addSettings(Settings... settings) {
        getSettings().addAll(Arrays.asList(settings));
    }

    protected void initComponent() {
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
        if (isEnabled()) {
            onDisable();
            setEnabled(false);
        } else {
            onEnable();
            setEnabled(true);
        }
    }
}
