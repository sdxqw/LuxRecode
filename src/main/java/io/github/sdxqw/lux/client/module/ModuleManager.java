package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.module.features.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ModuleManager {

    protected List<ModuleBase> mods = new ArrayList<>();

    public ModuleManager() {
        addModule(
                new FPSMod()
        );
    }

    private void addModule(ModuleBase... modules) {
        mods.addAll(Arrays.asList(modules));
    }

    public void renderHooks() {
        mods.forEach(mod -> {
            if (mod.isEnabled() && mod instanceof ModuleRender) ((ModuleRender) mod).drawInGame();
        });
    }
}
