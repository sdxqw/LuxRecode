package io.github.sdxqw.lux.api.mixins;

import io.github.sdxqw.lux.LuxRecode;
import io.github.sdxqw.lux.client.ui.UiSplashScreen;
import io.github.sdxqw.lux.client.util.ReferenceUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixins {

    @Inject(method = "startGame", at = @At(value = "RETURN"))
    public void startupGame(CallbackInfo ci) {
        LuxRecode.getInstance().init();
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    public void shutdownGame(CallbackInfo ci) {
        LuxRecode.getInstance().stop();
    }

    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/Display.setTitle(Ljava/lang/String;)V"))
    public String changeTitle(String newTitle) {
        return ReferenceUtils.getName() + " " + ReferenceUtils.getVersion() + " - " + ReferenceUtils.readCommit();
    }

    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;drawSplashScreen(Lnet/minecraft/client/renderer/texture/TextureManager;)V"))
    private void mixin(Minecraft mc, TextureManager tm) {
        UiSplashScreen.update("Loading");
    }

}
