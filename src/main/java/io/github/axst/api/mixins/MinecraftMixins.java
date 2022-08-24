package io.github.axst.api.mixins;

import io.github.axst.LuxRecode;
import io.github.axst.client.Reference;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixins {

    @Inject(method = "startGame", at = @At(value = "RETURN"))
    public void injectStartGame(CallbackInfo ci) {
        LuxRecode.getInstance().init();
    }

    @ModifyArg(method = "createDisplay", at = @At(value = "INVOKE", target = "org/lwjgl/opengl/Display.setTitle(Ljava/lang/String;)V"))
    public String changeTitle(String newTitle) {
        return Reference.getName() + " " + Reference.getVersion() + " - " + Reference.readCommit();
    }

}
