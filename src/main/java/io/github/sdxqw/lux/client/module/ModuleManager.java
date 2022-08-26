package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.module.features.FPSMod;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ModuleManager {

    protected List<ModuleBase> mods = new ArrayList<>();

    public ModuleManager() {
        addModule(new FPSMod());
    }

    private void addModule(ModuleBase... modules) {
        Collections.addAll(mods, modules);
    }

    public void renderHooks() {
        mods.forEach(mod -> {
            if (mod.isEnabled() && mod instanceof ModuleRender) ((ModuleRender) mod).drawInGame();
        });
    }
}
