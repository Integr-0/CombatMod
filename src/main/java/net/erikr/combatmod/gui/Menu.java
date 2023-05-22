package net.erikr.combatmod.gui;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.awt.*;

import static net.erikr.combatmod.gui.Gui.mc;

public class Menu extends Screen {
    //Menu Name
    protected Menu() {
        super(Text.literal("Menu"));
    }

    //Instancing for Changing Screen
    public static Screen INSTANCE = new Menu();
    private int timer;

    //Rendering
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        //Height + Width
        int WindowHeight = mc.getWindow().getScaledHeight();
        int WindowWidth = mc.getWindow().getScaledWidth();

        //Box
        GuiDrawer.drawBox(matrices, WindowWidth/2-185, WindowHeight/2-80, WindowWidth/2+185, WindowHeight/2+75, SharedVariables.GuiBack);

        //Buttons
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2-75, WindowWidth/2+60, WindowHeight/2-55, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleCustomHotbar");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2-50, WindowWidth/2+60, WindowHeight/2-30, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "TogglePlayerInspector");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2-25, WindowWidth/2+60, WindowHeight/2-5, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "TogglePlayerInfo");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2-0, WindowWidth/2+60, WindowHeight/2+20, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleCoordinateHud");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2+25, WindowWidth/2+60, WindowHeight/2+45, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleDirectionHud");

        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2-75, WindowWidth/2-65, WindowHeight/2-55, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleAutoSprint");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2-50, WindowWidth/2-65, WindowHeight/2-30, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleFullbright");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2-25, WindowWidth/2-65, WindowHeight/2-5, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleBreakingParticles");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2-0, WindowWidth/2-65, WindowHeight/2+20, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleOwnNametag");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2+25, WindowWidth/2-65, WindowHeight/2+45, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleExplosions");

        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-75, WindowWidth/2+180, WindowHeight/2-55, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "ToggleKeystrokes");
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-50, WindowWidth/2+180, WindowHeight/2-30, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Anticheat");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-25, WindowWidth/2+180, WindowHeight/2-5, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-0, WindowWidth/2+180, WindowHeight/2+20, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2+25, WindowWidth/2+180, WindowHeight/2+45, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");

        //Theme
        GuiDrawer.drawCycler(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2+50, WindowWidth/2+60, WindowHeight/2+70, SharedVariables.GuiColor, "SwitchTheme");
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Theme: " + SharedVariables.ThemeColor, WindowWidth/2-50, WindowHeight/2+57, SharedVariables.GuiBack);

        //Mode
        GuiDrawer.drawCycler(matrices, mouseX, mouseY, WindowWidth/2-180, WindowHeight/2+50, WindowWidth/2-65, WindowHeight/2+70, SharedVariables.GuiColor, "SwitchThemeMode");
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Mode: " + SharedVariables.ThemeMode, WindowWidth/2-170, WindowHeight/2+57, SharedVariables.GuiBack);

        //Editor
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2+50, WindowWidth/2+180, WindowHeight/2+70, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "LaunchEditor");
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Gui Editor", WindowWidth/2+75, WindowHeight/2+57, SharedVariables.GuiBack);


        //Text
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Hotbar Display", WindowWidth/2-50, WindowHeight/2-68, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Player Inspector", WindowWidth/2-50, WindowHeight/2-43, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Player Info", WindowWidth/2-50, WindowHeight/2-18, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Coordinate Hud", WindowWidth/2-50, WindowHeight/2+7, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Direction Hud", WindowWidth/2-50, WindowHeight/2+32, SharedVariables.GuiBack);

        GuiDrawer.drawText(matrices, Formatting.BOLD + "Auto Sprint", WindowWidth/2-170, WindowHeight/2-68, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Fullbright", WindowWidth/2-170, WindowHeight/2-43, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "No BreakParticle", WindowWidth/2-170, WindowHeight/2-18, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Own Nametag", WindowWidth/2-170, WindowHeight/2+7, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "No Explosions", WindowWidth/2-170, WindowHeight/2+32, SharedVariables.GuiBack);

        GuiDrawer.drawText(matrices, Formatting.BOLD + "Keystrokes", WindowWidth/2+75, WindowHeight/2-68, SharedVariables.GuiBack);
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Anticheat", WindowWidth/2+75, WindowHeight/2-43, SharedVariables.GuiBack);
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2-18, SharedVariables.GuiBack);
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2+7, SharedVariables.GuiBack);
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2+32, SharedVariables.GuiBack);

        super.render(matrices, mouseX, mouseY, delta);
    }

    //Mouse Clicked Check
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        SharedVariables.MouseDown = true;

        timer = 2;

        return super.mouseClicked(mouseX, mouseY, button);
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
