package net.erikr.combatmod.mixin;

import net.erikr.combatmod.CombatModClient;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.gui.GuiDrawer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "render", at = @At("RETURN"))
    public void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        GuiDrawer.drawBox(context, 4, 4, 77, 44, SharedVariables.GuiBack);
        GuiDrawer.drawText(context, Formatting.BOLD + "CombatMod", 10, 10, SharedVariables.GuiColor);
        GuiDrawer.drawText(context, "by Erik_R", 10, 20, SharedVariables.GuiColor);
        GuiDrawer.drawText(context, "v " + SharedVariables.Version, 10, 30, SharedVariables.GuiColor);
    }
}
