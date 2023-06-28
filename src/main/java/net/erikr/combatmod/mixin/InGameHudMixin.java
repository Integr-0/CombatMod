package net.erikr.combatmod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.gui.Gui;
import net.erikr.combatmod.gui.Menu;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.erikr.combatmod.gui.Gui.mc;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow @Nullable private Text overlayMessage;
    @Shadow private int overlayRemaining;
    @Shadow private int scaledHeight;
    @Shadow private int scaledWidth;
    @Shadow @Final private MinecraftClient client;
    @Shadow private boolean overlayTinted;
    @Shadow public abstract TextRenderer getTextRenderer();
    @Shadow protected abstract void drawTextBackground(DrawContext context, TextRenderer textRenderer, int yOffset, int width, int color);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        //Repositioning Actionbar Title
        if (SharedVariables.RenderCustomHotbarHud) {
            if (overlayMessage != null) {
                SharedVariables.OverlayMessage = overlayMessage;
            }
            if(SharedVariables.OverlayRemaining == 0) {
                SharedVariables.OverlayMessage = overlayMessage;
            }

            SharedVariables.OverlayRemaining = overlayRemaining;
            SharedVariables.OverlayTinted = overlayTinted;
            overlayMessage = null;
            int m;
            int n;
            float h;
            int l;

            if (SharedVariables.OverlayMessage != null && SharedVariables.OverlayRemaining > 0) {
                client.getProfiler().push("overlayMessage");
                h = (float)SharedVariables.OverlayRemaining - tickDelta;
                l = (int)(h * 255.0F / 20.0F);
                if (l > 255) {
                    l = 255;
                }

                if (l > 8) {
                    context.getMatrices().push();
                    context.getMatrices().translate((float)(scaledWidth / 2), (float)(scaledHeight - 68), 0.0F);
                    int k = 16777215;
                    if (SharedVariables.OverlayTinted) {
                        k = MathHelper.hsvToRgb(h / 50.0F, 0.7F, 0.6F) & 16777215;
                    }

                    m = l << 24 & -16777216;
                    n = mc.textRenderer.getWidth(SharedVariables.OverlayMessage);
                    drawTextBackground(context, mc.textRenderer, -4, n, 16777215 | m);
                    context.drawTextWithShadow(mc.textRenderer, SharedVariables.OverlayMessage, -n / 2, -14, k | m);
                    context.getMatrices().pop();
                }

                client.getProfiler().pop();
            }
        }

        //Rendering the Hud
        Gui.renderHud(context);
    }

    //Cancelling Rendering the Health Bar
    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    public void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }

    //Cancelling Rendering Status Bars
    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    public void renderStatusBars(DrawContext context, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }

    //Cancelling Rendering Status Bars
    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    public void renderHeldItemTooltip(DrawContext context, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }
}
