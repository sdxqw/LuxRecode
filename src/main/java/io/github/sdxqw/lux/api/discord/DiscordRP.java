package io.github.sdxqw.lux.api.discord;

import lombok.Getter;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

public class DiscordRP {

    @Getter
    private static final DiscordRP instance = new DiscordRP();

    private final long created;
    private boolean running = true;

    private DiscordRP() {
        created = System.currentTimeMillis();
        new Thread(() -> {
            while (running) DiscordRPC.discordRunCallbacks();
        }).start();
    }

    public void startDiscordRPC() {
        DiscordEventHandlers.Builder builder = new DiscordEventHandlers.Builder();
        builder.setReadyEventHandler(user -> {
            String prefix = "Playing Minecraft 1.8.9";
            if ("hobbyshop".equalsIgnoreCase(user.username)) {
                DiscordRP.getInstance().update(prefix, "HornyShop [Old Owner]");
            } else if ("noah.".equalsIgnoreCase(user.username)) {
                DiscordRP.getInstance().update(prefix, "Noah. [Owner]");
            } else {
                DiscordRP.getInstance().update(prefix, user.username);
            }
        });

        DiscordEventHandlers handlers = builder.build();
        DiscordRPC.discordInitialize("906992886074208256", handlers, true);
    }

    public void shutdownDiscordRPC() {
        getInstance().running = false;
        DiscordRPC.discordShutdown();
    }

    /**
     * Discord Update method.
     *
     * @param firstLine  String first line of the RPC
     * @param secondLine String second line of the RPC
     */
    public void update(String firstLine, String secondLine) {
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
        b.setBigImage("logo", "Lux Client - Playing");
        b.setDetails(firstLine);
        b.setStartTimestamps(getInstance().created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
