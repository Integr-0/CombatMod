package net.erikr.combatmod.mixin;

import net.erikr.combatmod.CombatModClient;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.gui.GuiDrawer;
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
    public void onRender(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        GuiDrawer.drawBox(matrices, 4, 4, 70, 44, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "CombatMod", 10, 10, SharedVariables.GuiColor);
        GuiDrawer.drawText(matrices, "by Erik_R", 10, 20, SharedVariables.GuiColor);
        GuiDrawer.drawText(matrices, "v " + SharedVariables.Version, 10, 30, SharedVariables.GuiColor);
    }
}
