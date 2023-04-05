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
        GuiDrawer.drawBox(matrices, WindowWidth/2-185, WindowHeight/2-80, WindowWidth/2+185, WindowHeight/2+75, new Color(255, 255, 255, 255).getRGB());

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
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-50, WindowWidth/2+180, WindowHeight/2-30, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-25, WindowWidth/2+180, WindowHeight/2-5, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2-0, WindowWidth/2+180, WindowHeight/2+20, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");
        //GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2+65, WindowHeight/2+25, WindowWidth/2+180, WindowHeight/2+45, SharedVariables.GuiColor, new Color(151, 151, 151, 255).getRGB(), "Test");

        //Theme
        GuiDrawer.drawButton(matrices, mouseX, mouseY, WindowWidth/2-60, WindowHeight/2+50, WindowWidth/2+60, WindowHeight/2+70, new Color(249, 141, 0, 255).getRGB(), new Color(16, 213, 235, 255).getRGB(), "SwitchTheme");

        if (SharedVariables.EnabledButtons.contains("SwitchTheme")) {
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Theme: Orange", WindowWidth/2-50, WindowHeight/2+57, new Color(255, 255, 255, 255).getRGB());

        } else {
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Theme: Blue", WindowWidth/2-50, WindowHeight/2+57, new Color(255, 255, 255, 255).getRGB());
        }

        //Text
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Hotbar Display", WindowWidth/2-50, WindowHeight/2-68, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Player Inspector", WindowWidth/2-50, WindowHeight/2-43, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Player Info", WindowWidth/2-50, WindowHeight/2-18, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Coordinate Hud", WindowWidth/2-50, WindowHeight/2+7, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Direction Hud", WindowWidth/2-50, WindowHeight/2+32, new Color(255, 255, 255, 255).getRGB());

        GuiDrawer.drawText(matrices, Formatting.BOLD + "Auto Sprint", WindowWidth/2-170, WindowHeight/2-68, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Fullbright", WindowWidth/2-170, WindowHeight/2-43, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "No BreakParticle", WindowWidth/2-170, WindowHeight/2-18, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "Own Nametag", WindowWidth/2-170, WindowHeight/2+7, new Color(255, 255, 255, 255).getRGB());
        GuiDrawer.drawText(matrices, Formatting.BOLD + "No Explosions", WindowWidth/2-170, WindowHeight/2+32, new Color(255, 255, 255, 255).getRGB());

        GuiDrawer.drawText(matrices, Formatting.BOLD + "Keystrokes", WindowWidth/2+75, WindowHeight/2-68, new Color(255, 255, 255, 255).getRGB());
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2-43, new Color(255, 255, 255, 255).getRGB());
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2-18, new Color(255, 255, 255, 255).getRGB());
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2+7, new Color(255, 255, 255, 255).getRGB());
        //GuiDrawer.drawText(matrices, Formatting.BOLD + "Test", WindowWidth/2+75, WindowHeight/2+32, new Color(255, 255, 255, 255).getRGB());

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
