package io.github.sdxqw.lux.client.module;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

@Getter
public class ModuleBase {

    private final Minecraft mc = Minecraft.getMinecraft();
    private final String name;
    @Setter
    private boolean enabled;

    protected ModuleBase(String name) {
        this.name = name;
        enabled = true;
    }

    public void toggleModules() {
        setEnabled(!isEnabled());
    }
}
