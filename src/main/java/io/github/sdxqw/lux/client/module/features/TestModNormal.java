package io.github.sdxqw.lux.client.module.features;

import io.github.sdxqw.lux.client.module.ModuleBase;
import io.github.sdxqw.lux.client.module.ModuleInfo;

@ModuleInfo(name = "Test Mod Normal", icon = "test")
public class TestModNormal extends ModuleBase {
    @Override
    public void onEnable() {
        System.out.println("test");
    }

    @Override
    public void onDisable() {
        System.out.println("test 2");
    }
}
