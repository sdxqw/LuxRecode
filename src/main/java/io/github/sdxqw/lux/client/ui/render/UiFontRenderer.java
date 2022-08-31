package io.github.sdxqw.lux.client.ui.render;

import io.github.sdxqw.lux.client.fonts.CustomFontRenderer;
import lombok.Getter;

public class UiFontRenderer {
    @Getter
    private static final CustomFontRenderer titleBold = new CustomFontRenderer("title_bold", 20);
    @Getter
    private static final CustomFontRenderer titleThin = new CustomFontRenderer("title_thin", 20);
    @Getter
    private static final CustomFontRenderer text = new CustomFontRenderer("normal", 16);
    @Getter
    private static final CustomFontRenderer textBold = new CustomFontRenderer("normal_bold", 12);
    @Getter
    private static final CustomFontRenderer hud = new CustomFontRenderer("mods", 15);
    private UiFontRenderer() {}
}
