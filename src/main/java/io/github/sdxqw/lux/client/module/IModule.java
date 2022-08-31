package io.github.sdxqw.lux.client.module;

public interface IModule {
    void drawInGame();

    void drawOnScreen(int mouseX, int mouseY);

    int getWidth();

    int getHeight();
}
