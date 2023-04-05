package net.erikr.combatmod.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.gui.Gui;
import net.erikr.combatmod.gui.Menu;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
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
    @Shadow protected abstract void drawTextBackground(MatrixStack matrices, TextRenderer textRenderer, int yOffset, int width, int color);

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
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
                    matrices.push();
                    matrices.translate((double)(scaledWidth / 2), (double)(scaledHeight - 68), 0.0);
                    RenderSystem.enableBlend();
                    RenderSystem.defaultBlendFunc();
                    int k = 16777215;
                    if (SharedVariables.OverlayTinted) {
                        k = MathHelper.hsvToRgb(h / 50.0F, 0.7F, 0.6F) & 16777215;
                    }
                    m = l << 24 & -16777216;
                    n = getTextRenderer().getWidth(SharedVariables.OverlayMessage);
                    drawTextBackground(matrices, getTextRenderer(), -4, n, 16777215 | m);
                    getTextRenderer().drawWithShadow(matrices, SharedVariables.OverlayMessage, (float)(-n / 2), -14.0F, k | m);
                    RenderSystem.disableBlend();
                    matrices.pop();
                }
                this.client.getProfiler().pop();
            }
        }

        //Rendering the Hud
        Gui.renderHud(matrices);
    }

    //Cancelling Rendering the Health Bar
    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    public void renderHealthBar(MatrixStack matrices, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }

    //Cancelling Rendering Status Bars
    @Inject(method = "renderStatusBars", at = @At("HEAD"), cancellable = true)
    public void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }

    //Cancelling Rendering Status Bars
    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    public void renderHeldItemTooltip(MatrixStack matrices, CallbackInfo ci) {
        if(SharedVariables.RenderCustomHotbarHud) {
            ci.cancel();
        }
    }
}
