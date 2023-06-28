package net.erikr.combatmod.gui;

import net.erikr.combatmod.CombatModClient;
import net.erikr.combatmod.SharedVariables;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TextColor;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

import static net.erikr.combatmod.gui.Gui.mc;

public class GuiDrawer {

    //Drawing a Text
    public static void drawText(DrawContext context, String text, int x, int y, int Color) {
        context.drawText(mc.textRenderer, text, x, y, Color, false);
    }

    //Drawing a Custom Box with rounded Corners
    public static void drawBox(DrawContext context, int x1, int y1, int x2, int y2, int Color) {

        context.fill(x1+5, y1, x2-5, y2, Color);
        context.fill(x1, y1+5, x2, y2-5, Color);

        context.fill(x1+3, y1+1, x2-3, y2-1, Color);
        context.fill(x1+1, y1+3, x2-1, y2-3, Color);

        context.fill(x1+2, y1+2, x2-2, y2-2, Color);
        context.fill(x1+2, y1+2, x2-2, y2-2, Color);

    }

    public static void drawBoxMovable(DrawContext context, int mouseX, int mouseY, int Color, String name, String label) {
        int sizeX = SharedVariables.ButtonPoses.get(name+"x2") - SharedVariables.ButtonPoses.get(name+"x1");
        int sizeY = SharedVariables.ButtonPoses.get(name+"y2") - SharedVariables.ButtonPoses.get(name+"y1");

        int offsetX = sizeX/2;
        int offsetY = sizeY/2;

        if (SharedVariables.MouseHeld && SharedVariables.dragging == name || SharedVariables.MouseHeld && SharedVariables.dragging == null && mouseX > SharedVariables.ButtonPoses.get(name+"x1") && mouseX < SharedVariables.ButtonPoses.get(name+"x2") && mouseY > SharedVariables.ButtonPoses.get(name+"y1") && mouseY < SharedVariables.ButtonPoses.get(name+"y2")) {
            SharedVariables.dragging = name;
            SharedVariables.ButtonPoses.put(name+"x1", mouseX-offsetX);
            SharedVariables.ButtonPoses.put(name+"y1", mouseY-offsetY);
            SharedVariables.ButtonPoses.put(name+"x2", mouseX+offsetX);
            SharedVariables.ButtonPoses.put(name+"y2", mouseY+offsetY);
        }

        context.fill(SharedVariables.ButtonPoses.get(name+"x1")+5, SharedVariables.ButtonPoses.get(name+"y1"), SharedVariables.ButtonPoses.get(name+"x2")-5, SharedVariables.ButtonPoses.get(name+"y2"), Color);
        context.fill(SharedVariables.ButtonPoses.get(name+"x1"), SharedVariables.ButtonPoses.get(name+"y1")+5, SharedVariables.ButtonPoses.get(name+"x2"), SharedVariables.ButtonPoses.get(name+"y2")-5, Color);

        context.fill(SharedVariables.ButtonPoses.get(name+"x1")+3, SharedVariables.ButtonPoses.get(name+"y1")+1, SharedVariables.ButtonPoses.get(name+"x2")-3, SharedVariables.ButtonPoses.get(name+"y2")-1, Color);
        context.fill(SharedVariables.ButtonPoses.get(name+"x1")+1, SharedVariables.ButtonPoses.get(name+"y1")+3, SharedVariables.ButtonPoses.get(name+"x2")-1, SharedVariables.ButtonPoses.get(name+"y2")-3, Color);

        context.fill(SharedVariables.ButtonPoses.get(name+"x1")+2, SharedVariables.ButtonPoses.get(name+"y1")+2, SharedVariables.ButtonPoses.get(name+"x2")-2, SharedVariables.ButtonPoses.get(name+"y2")-2, Color);
        context.fill(SharedVariables.ButtonPoses.get(name+"x1")+2, SharedVariables.ButtonPoses.get(name+"y1")+2, SharedVariables.ButtonPoses.get(name+"x2")-2, SharedVariables.ButtonPoses.get(name+"y2")-2, Color);

        drawText(context, label, SharedVariables.ButtonPoses.get(name+"x1")+4, SharedVariables.ButtonPoses.get(name+"y1")+4, SharedVariables.GuiColor);
    }

    public static void drawCycler(DrawContext context,  int mouseX, int mouseY, int x1, int y1, int x2, int y2, int Color, String CyclerId) {
        if (mouseX > x1 && mouseX < x2 && mouseY > y1 && mouseY < y2) {
            if (SharedVariables.MouseDown) {
                SharedVariables.MouseDown = false;
                CombatModClient.INSTANCE.onCycle(CyclerId);

            }
        }
        context.fill(x1+5, y1, x2-5, y2, Color);
        context.fill(x1, y1+5, x2, y2-5, Color);

        context.fill(x1+3, y1+1, x2-3, y2-1, Color);
        context.fill(x1+1, y1+3, x2-1, y2-3, Color);

        context.fill(x1+2, y1+2, x2-2, y2-2, Color);
        context.fill(x1+2, y1+2, x2-2, y2-2, Color);
    }

    //Drawing a Custom Button with rounded Corners
    public static void drawButton(DrawContext context, int mouseX, int mouseY, int x1, int y1, int x2, int y2, int ColorEnabled, int ColorDisabled, String ButtonId) {
        if (mouseX > x1 && mouseX < x2 && mouseY > y1 && mouseY < y2) {
            if (SharedVariables.MouseDown) {
                SharedVariables.MouseDown = false;
                if (SharedVariables.EnabledButtons.contains(ButtonId)) {
                    SharedVariables.EnabledButtons.remove(ButtonId);
                    CombatModClient.INSTANCE.onButtonDisable(ButtonId);
                }
                else {
                    SharedVariables.EnabledButtons.add(ButtonId);
                    CombatModClient.INSTANCE.onButtonEnable(ButtonId);
                }

            }
        }

        //Enabled
        if (SharedVariables.EnabledButtons.contains(ButtonId)) {
            context.fill(x1+5, y1, x2-5, y2, ColorEnabled);
            context.fill(x1, y1+5, x2, y2-5, ColorEnabled);

            context.fill(x1+3, y1+1, x2-3, y2-1, ColorEnabled);
            context.fill(x1+1, y1+3, x2-1, y2-3, ColorEnabled);

            context.fill(x1+2, y1+2, x2-2, y2-2, ColorEnabled);
            context.fill(x1+2, y1+2, x2-2, y2-2, ColorEnabled);
        } else {
            //Disabled
            context.fill(x1+5, y1, x2-5, y2, ColorDisabled);
            context.fill(x1, y1+5, x2, y2-5, ColorDisabled);

            context.fill(x1+3, y1+1, x2-3, y2-1, ColorDisabled);
            context.fill(x1+1, y1+3, x2-1, y2-3, ColorDisabled);

            context.fill(x1+2, y1+2, x2-2, y2-2, ColorDisabled);
            context.fill(x1+2, y1+2, x2-2, y2-2, ColorDisabled);
        }
    }
}
