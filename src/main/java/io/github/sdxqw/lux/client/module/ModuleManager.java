package io.github.sdxqw.lux.client.module;

import io.github.sdxqw.lux.client.module.impl.TestRender;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ModuleManager {

    private final List<Module> mod = new ArrayList<>();

    public ModuleManager() {
        addModule(new TestRender());
    }

    public void addModule(Module... module) {
        Collections.addAll(mod, module);
    }

    public void renderModules() {
        mod.forEach(module -> {
            if (module.isEnabled()) module.drawInGame();
        });
    }
}
