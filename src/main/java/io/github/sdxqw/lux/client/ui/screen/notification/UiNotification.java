package io.github.sdxqw.lux.client.ui.screen.notification;

import io.github.sdxqw.lux.LuxRecode;
import io.github.sdxqw.lux.client.ui.render.UiFontRenderer;
import lombok.Getter;
import org.apache.logging.log4j.Level;

import java.awt.*;

@Getter
public class UiNotification {

    protected String name;
    protected String description;
    protected Type type;

    protected int defaultTime = 150;

    public UiNotification(String name, String description, Type type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public void drawNotification() {
        String prefix = "Default: ";
        Color color = new Color(12, 22, 54);
        switch (type) {
            case INFO:
                prefix = "INFO: ";
                color = new Color(62, 222, 179, 150);
                break;
            case WARNING:
                prefix = "WARNING: ";
                color = new Color(255, 223, 126, 150);
                break;
            default:
                LuxRecode.getInstance().getLuxLog().log(Level.INFO, "This notification doesnt exists.");
        }

        UiFontRenderer.getText().drawString(prefix + name, 100, 100, color.getRGB());

        --defaultTime;
    }

    public boolean isLiving() {
        return getDefaultTime() > 0;
    }

    public enum Type {
        INFO, WARNING
    }
}
