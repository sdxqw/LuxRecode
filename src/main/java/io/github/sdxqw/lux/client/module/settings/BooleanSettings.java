package io.github.sdxqw.lux.client.module.settings;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BooleanSettings extends Settings {

    protected boolean enabled;

    public BooleanSettings(String name, boolean enabled) {
        super(name);
        this.enabled = enabled;
    }
}
