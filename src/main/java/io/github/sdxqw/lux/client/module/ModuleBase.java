package io.github.sdxqw.lux.client.module;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@Getter
public class ModuleBase {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    private final ResourceLocation icon;
    @Setter
    private boolean enabled;

    protected ModuleBase(String name, String icon) {
        this.name = name;
        this.icon = new ResourceLocation("lux/icons/mods/" + icon + ".png");
        enabled = true;
    }

    public void toggleModules() {
        setEnabled(!isEnabled());
    }
}
