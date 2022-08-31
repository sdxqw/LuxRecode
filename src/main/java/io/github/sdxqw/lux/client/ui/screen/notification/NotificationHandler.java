package io.github.sdxqw.lux.client.ui.screen.notification;

import com.google.common.collect.Lists;

import java.util.List;

public class NotificationHandler {

    protected List<UiNotification> notifications;

    public NotificationHandler() {
        notifications = Lists.newArrayList();
    }

    public void renderNotification() {
        notifications.forEach(UiNotification::drawNotification);
        notifications.removeIf(e -> !e.isLiving());
    }

    public void sendNotification(UiNotification notification) {
        notifications.add(notification);
    }
}
