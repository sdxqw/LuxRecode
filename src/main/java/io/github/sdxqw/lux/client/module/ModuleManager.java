package io.github.sdxqw.lux.client.module;

import com.google.common.collect.Lists;
import io.github.sdxqw.lux.client.module.features.FPSMod;
import io.github.sdxqw.lux.client.module.features.TestMod;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ModuleManager {

    protected List<ModuleBase> mods = Lists.newArrayList();

    public ModuleManager() {
        addModule(
                new FPSMod(),
                new TestMod()
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
