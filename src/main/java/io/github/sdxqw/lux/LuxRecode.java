package io.github.sdxqw.lux;

import io.github.nevalackin.homoBus.Listener;
import io.github.nevalackin.homoBus.annotations.EventLink;
import io.github.nevalackin.homoBus.bus.Bus;
import io.github.nevalackin.homoBus.bus.impl.EventBus;
import io.github.sdxqw.lux.api.discord.DiscordRP;
import io.github.sdxqw.lux.api.events.EventTick;
import io.github.sdxqw.lux.api.events.IEvents;
import io.github.sdxqw.lux.client.module.ModuleManager;
import io.github.sdxqw.lux.client.ui.screen.UiHudScreen;
import io.github.sdxqw.lux.client.util.KeyBindingUtils;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
public class LuxRecode {

    @Getter
    private static final LuxRecode instance = new LuxRecode();
    @Getter
    private static final Logger luxLog = LogManager.getLogger(LuxRecode.class.getName());
    private final Bus<IEvents> bus = new EventBus<>();
    @EventLink
    public Listener<EventTick> someTest = event -> {
        if (KeyBindingUtils.OPEN_HUD.isPressed())
            Minecraft.getMinecraft().displayGuiScreen(new UiHudScreen());
    };
    private ModuleManager module;

    public void init() {
        module = new ModuleManager();
        new KeyBindingUtils();
        DiscordRP.getInstance().startDiscordRPC();
        bus.subscribe(instance);
    }

    public void stop() {
        DiscordRP.getInstance().shutdownDiscordRPC();
        bus.unsubscribe(instance);
    }
}
