package io.github.sdxqw.lux.api.mixins;

import io.github.sdxqw.lux.LuxRecode;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class GuiIngameMixins {

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void runTick(CallbackInfo info) {
        LuxRecode.getInstance().getModule().renderHooks();
    }

}
