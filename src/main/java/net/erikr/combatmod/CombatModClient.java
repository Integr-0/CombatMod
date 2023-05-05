package net.erikr.combatmod;

import jdk.jshell.Snippet;
import net.erikr.combatmod.gui.Menu;
import net.erikr.combatmod.gui.ScreenEditor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static net.erikr.combatmod.gui.Gui.mc;
public class CombatModClient implements ClientModInitializer {
    //Keybinding
    public static KeyBinding OpenMenuKey;
    public static KeyBinding ZoomKey;

    //Instancing For Special Access
    public static CombatModClient INSTANCE = new CombatModClient();

    public static final Logger LOGGER = LoggerFactory.getLogger("InfReach");

    @Override
    public void onInitializeClient() {
        //Keybinding
        OpenMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.combatmod.openmenu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.combatmod"));
        ZoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.combatmod.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.combatmod"));

        //TODO: Remove when adding Config
        SharedVariables.EnabledButtons.add("ToggleCustomHotbar");
        SharedVariables.EnabledButtons.add("TogglePlayerInspector");
        SharedVariables.EnabledButtons.add("TogglePlayerInfo");
        SharedVariables.EnabledButtons.add("ToggleCoordinateHud");
        SharedVariables.EnabledButtons.add("ToggleDirectionHud");
        SharedVariables.EnabledButtons.add("SwitchTheme");
        SharedVariables.EnabledButtons.add("ToggleAutoSprint");
        SharedVariables.EnabledButtons.add("ToggleFullbright");
        SharedVariables.EnabledButtons.add("ToggleBreakingParticles");
        SharedVariables.EnabledButtons.add("ToggleOwnNametag");
        SharedVariables.EnabledButtons.add("ToggleExplosions");
        SharedVariables.EnabledButtons.add("ToggleKeystrokes");

        SharedVariables.ThemeMode = "Light";
        SharedVariables.ThemeColor = "Orange";
        //TODO: Remove when adding Config
    }

    public void onTick() {
        //AutoSprint
        if (mc.player != null && mc.options.forwardKey.isPressed() && SharedVariables.AutoSprint) {
            mc.options.sprintKey.setPressed(true);
        } else if (SharedVariables.AutoSprint) {
            mc.options.sprintKey.setPressed(false);
        }

        //Opening the Menu
        if (OpenMenuKey.wasPressed()) {
            mc.setScreen(Menu.INSTANCE);
        }

        //Various Config Toggles transferred into SharedVariables
        if (SharedVariables.EnabledButtons.contains("ToggleBreakingParticles")) {
            SharedVariables.NoBreakingParticles = true;
        } else SharedVariables.NoBreakingParticles = false;

        if (SharedVariables.EnabledButtons.contains("ToggleExplosions")) {
            SharedVariables.NoExplosionParticles = true;
        } else SharedVariables.NoExplosionParticles = false;

        if (SharedVariables.EnabledButtons.contains("ToggleOwnNametag")) {
            SharedVariables.OwnNametag = true;
        } else SharedVariables.OwnNametag = false;

        if (SharedVariables.EnabledButtons.contains("ToggleFullbright")) {
            SharedVariables.Fullbright = true;
        } else SharedVariables.Fullbright = false;

        if (SharedVariables.EnabledButtons.contains("ToggleCustomHotbar")) {
            SharedVariables.RenderCustomHotbarHud = true;
        } else SharedVariables.RenderCustomHotbarHud = false;

        if (SharedVariables.EnabledButtons.contains("ToggleAutoSprint")) {
            SharedVariables.AutoSprint = true;
        } else SharedVariables.AutoSprint = false;

        if (SharedVariables.EnabledButtons.contains("TogglePlayerInspector")) {
            SharedVariables.RenderPlayerInspector = true;
        } else SharedVariables.RenderPlayerInspector = false;

        if (SharedVariables.EnabledButtons.contains("TogglePlayerInfo")) {
            SharedVariables.RenderPlayerInfo = true;
        } else SharedVariables.RenderPlayerInfo = false;

        if (SharedVariables.EnabledButtons.contains("ToggleCoordinateHud")) {
            SharedVariables.RenderCoordinateHud = true;
        } else SharedVariables.RenderCoordinateHud = false;

        if (SharedVariables.EnabledButtons.contains("ToggleDirectionHud")) {
            SharedVariables.RenderDirectionHud = true;
        } else SharedVariables.RenderDirectionHud = false;

        if (SharedVariables.EnabledButtons.contains("ToggleKeystrokes")) {
            SharedVariables.Keystrokes = true;
        } else SharedVariables.Keystrokes = false;

        //Mode Switcher
        if (SharedVariables.ThemeMode.equals("Dark")) {
            SharedVariables.GuiBack = new Color(25, 24, 24, 255).getRGB();
        } else if (SharedVariables.ThemeMode.equals("Light")) {
            SharedVariables.GuiBack = new Color(255, 255, 255, 255).getRGB();
        }

        //Theme Switcher
        if (SharedVariables.ThemeColor.equals("Orange")) {
            SharedVariables.GuiColor = new Color(249, 141, 0, 255).getRGB();
        } else if (SharedVariables.ThemeColor.equals("Blue")){
            SharedVariables.GuiColor = new Color(16, 213, 235, 255).getRGB();
        } else if (SharedVariables.ThemeColor.equals("Red")){
            SharedVariables.GuiColor = new Color(244, 3, 31, 255).getRGB();
        } else if (SharedVariables.ThemeColor.equals("Green")){
            SharedVariables.GuiColor = new Color(1, 209, 26, 255).getRGB();
        } else if (SharedVariables.ThemeColor.equals("Yellow")){
            SharedVariables.GuiColor = new Color(235, 231, 16, 255).getRGB();
        } else if (SharedVariables.ThemeColor.equals("Rainbow")){
            SharedVariables.GuiColor = new Color(getRainbowColor()[0], getRainbowColor()[1], getRainbowColor()[2]).getRGB();
        }
    }

    public void onButtonEnable(String ButtonId) {
        if (ButtonId.equals("LaunchEditor")) {
            SharedVariables.EnabledButtons.remove("LaunchEditor");
            mc.setScreen(ScreenEditor.INSTANCE);
        }
    }

    public void onButtonDisable(String ButtonId) {

    }

    public void onCycle(String CyclerId) {
        if (CyclerId.equals("SwitchTheme")) {
            if (SharedVariables.ThemeColor.equals("Rainbow")) {
                SharedVariables.ThemeColor = "Blue";
            } else if (SharedVariables.ThemeColor.equals("Blue")) {
                SharedVariables.ThemeColor = "Red";
            } else if (SharedVariables.ThemeColor.equals("Red")) {
                SharedVariables.ThemeColor = "Green";
            } else if (SharedVariables.ThemeColor.equals("Green")) {
                SharedVariables.ThemeColor = "Yellow";
            } else if (SharedVariables.ThemeColor.equals("Yellow")) {
                SharedVariables.ThemeColor = "Orange";
            } else if (SharedVariables.ThemeColor.equals("Orange")) {
                SharedVariables.ThemeColor = "Rainbow";
            }
        }

        if (CyclerId.equals("SwitchThemeMode")) {
            if (SharedVariables.ThemeMode.equals("Light")) {
                SharedVariables.ThemeMode = "Dark";
            } else if (SharedVariables.ThemeMode.equals("Dark")) {
                SharedVariables.ThemeMode = "Light";
            }
        }
    }

    public static float[] getRainbowColor() {
        float x = System.currentTimeMillis() % 2000 / 1000F;
        float pi = (float)Math.PI;

        float[] rainbow = new float[3];
        rainbow[0] = 0.5F + 0.5F * MathHelper.sin(x * pi);
        rainbow[1] = 0.5F + 0.5F * MathHelper.sin((x + 4F / 3F) * pi);
        rainbow[2] = 0.5F + 0.5F * MathHelper.sin((x + 8F / 3F) * pi);
        return rainbow;
    }
}
