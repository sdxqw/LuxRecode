package io.github.sdxqw.lux;

import io.github.sdxqw.lux.api.discord.DiscordRP;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LuxRecode {

    @Getter
    private static final LuxRecode instance = new LuxRecode();
    @Getter
    private static final Logger luxLog = LogManager.getLogger(LuxRecode.class.getName());

    public void init() {
        DiscordRP.getInstance().startDiscordRPC();
    }

    public void stop() {
        DiscordRP.getInstance().shutdownDiscordRPC();
    }
}
