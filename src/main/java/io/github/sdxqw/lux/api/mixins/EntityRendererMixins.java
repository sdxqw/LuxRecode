package io.github.sdxqw.lux.api.mixins;

import io.github.sdxqw.lux.LuxRecode;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixins {

    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiScreen;drawScreen(IIF)V", shift = At.Shift.AFTER))
    private void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo callbackInfo) {
        LuxRecode.getInstance().getNotifications().renderNotification();
    }

}
