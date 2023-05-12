package net.erikr.combatmod.gui;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.awt.*;

import static net.erikr.combatmod.gui.Gui.mc;

public class ScreenEditor extends Screen {

    private int timer;

    protected ScreenEditor() {
        super(Text.literal("Editor"));
    }

    public static Screen INSTANCE = new ScreenEditor();

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //Height + Width
        int WindowHeight = mc.getWindow().getScaledHeight();
        int WindowWidth = mc.getWindow().getScaledWidth();

        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-35, WindowHeight/2-15, WindowWidth/2+35, WindowHeight/2+15, SharedVariables.GuiColor, SharedVariables.GuiBack, "ResetEditor");
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Reset Pos", WindowWidth/2-30, WindowHeight/2-4, SharedVariables.GuiColor);

        GuiDrawer.drawBoxMovable(matrices, mouseX, mouseY, SharedVariables.GuiBack, "chud", "Coordinates"); //coordinate hud
        GuiDrawer.drawBoxMovable(matrices, mouseX, mouseY, SharedVariables.GuiBack, "dhud", "Direction"); //direction hud
        GuiDrawer.drawBoxMovable(matrices, mouseX, mouseY, SharedVariables.GuiBack, "pins", "Inspector"); //pinspector
        GuiDrawer.drawBoxMovable(matrices, mouseX, mouseY, SharedVariables.GuiBack, "pinfo", "Info"); //pinfo
        GuiDrawer.drawBoxMovable(matrices, mouseX, mouseY, SharedVariables.GuiBack, "key", "Keystrokes"); //keystrokes
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        SharedVariables.MouseHeld = true;
        SharedVariables.MouseDown = true;

        timer = 2;
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        SharedVariables.MouseHeld = false;
        SharedVariables.dragging = null;

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void tick() {
        if (timer > 0) {
            timer -= 1;
        }
        if (timer == 0) {
            SharedVariables.MouseDown = false;
        }
    }
}
