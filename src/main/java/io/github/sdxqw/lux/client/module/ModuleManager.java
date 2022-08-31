package io.github.sdxqw.lux.client.module;

import com.google.common.collect.Lists;
import io.github.sdxqw.lux.client.module.features.TestMod;
import io.github.sdxqw.lux.client.module.features.TestModNormal;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Getter
public class ModuleManager {

    protected List<ModuleBase> mods = Lists.newArrayList();
    protected String toCheck = "io.github.sdxqw.lux.client.module.features";
    private final Set<Class<?>> classes;

    public ModuleManager() {
        Reflections reflection = new Reflections(toCheck);
        classes = reflection.getTypesAnnotatedWith(ModuleInfo.class);
        addModule(
                new TestMod(),
                new TestModNormal()
        );
    }

    private void addModule(ModuleBase... modules) {
        mods.addAll(Arrays.asList(modules));
    }

    public void renderHooks() {
        mods.forEach(mod -> {
            if (mod.isEnabled() && mod instanceof IModule) ((IModule) mod).drawInGame();
        });
    }
}
