package io.github.sdxqw.lux.api.mixins;

import io.github.sdxqw.lux.client.ui.screen.UiMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class GuiMainMenuMixins {

    @Inject(method = "initGui", at = @At("HEAD"))
    public void initMenu(CallbackInfo ci) {
        Minecraft.getMinecraft().displayGuiScreen(new UiMainMenu());
    }

}
