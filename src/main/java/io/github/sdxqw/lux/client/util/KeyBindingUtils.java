package io.github.sdxqw.lux.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;

public class KeyBindingUtils {

    public static final KeyBinding OPEN_HUD = new KeyBinding("Open Hud", Keyboard.KEY_E, "Lux Recode");

    public KeyBindingUtils() {
        addAll(OPEN_HUD);
    }

    public void addAll(KeyBinding... keyBindings) {
        Minecraft.getMinecraft().gameSettings.keyBindings = ArrayUtils.addAll(Minecraft.getMinecraft().gameSettings.keyBindings, keyBindings);
    }
}
