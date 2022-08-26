package io.github.sdxqw.lux.api.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Render.class)
public class RenderMixins<T extends Entity> {

    protected final RenderManager renderManager;

    public RenderMixins(RenderManager renderManager) {
        this.renderManager = renderManager;
    }

    public FontRenderer getFontRendererFromRenderManager() {
        return this.renderManager.getFontRenderer();
    }

    @Inject(method = "renderLivingLabel", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/Tessellator.getWorldRenderer ()Lnet/minecraft/client/renderer/WorldRenderer;", shift = At.Shift.BEFORE))
    public void injectRenderLivingLabel(T e, String str, double x, double y, double z, int maxDistance, CallbackInfo ci) {
        FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
        if (e.getName().equals("sdxq") && str.contains(e.getName())) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("lux/icons/web/websocket.png"));
            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glColor4f(1, 0, 0, 1);
            Gui.drawModalRectWithCustomSizedTexture(-fontrenderer.getStringWidth(e.getDisplayName().getFormattedText()) / 2 - 12, -2, 10, 10, 10, 10, 10, 10);
            GL11.glPopMatrix();
        }
    }
}
