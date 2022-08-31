package io.github.sdxqw.lux.client.ui.screen.notification;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class NotificationHandler {

    protected List<UiNotification> notifications;

    public NotificationHandler() {
        notifications = Lists.newArrayList();
    }

    public void renderNotification() {
        Iterator<UiNotification> e = notifications.listIterator();
        while (e.hasNext()) {
            UiNotification k = e.next();
            k.drawNotification();
            if (!k.isLiving()) {
                e.remove();
            }
        }
    }

    public void sendNotification(UiNotification notification) {
        notifications.add(notification);
    }
}
