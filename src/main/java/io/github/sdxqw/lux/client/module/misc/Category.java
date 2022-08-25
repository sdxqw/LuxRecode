package io.github.sdxqw.lux.client.module.misc;

import lombok.Getter;

public enum Category {

    PVP("pvp");

    @Getter
    private final String category;

    Category(String category) {
        this.category = category;
    }
}
