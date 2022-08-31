package io.github.sdxqw.lux.client.module;

import com.google.common.collect.Lists;
import io.github.sdxqw.lux.LuxRecode;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.List;
import java.util.Set;

@Getter
public class ModuleManager {

    private final Set<Class<?>> classes;
    protected List<ModuleBase> mods = Lists.newArrayList();
    protected String toCheck = "io.github.sdxqw.lux.client.module.features";

    @SneakyThrows
    public ModuleManager() {
        Reflections reflection = new Reflections(toCheck);
        classes = reflection.getTypesAnnotatedWith(ModuleInfo.class);
        for (Class<?> e : classes) {
            mods.add((ModuleBase) e.newInstance());
            LuxRecode.getInstance().getBus().subscribe(e.newInstance());
        }
    }

    public void renderHooks() {
        mods.forEach(mod -> {
            if (mod.isEnabled() && mod instanceof IModule) ((IModule) mod).drawInGame();
        });
    }
}
