package net.erikr.combatmod;

import jdk.jshell.Snippet;
import net.erikr.combatmod.gui.Menu;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

import static net.erikr.combatmod.gui.Gui.mc;

public class CombatModClient implements ClientModInitializer {
    //Keybinding
    public static KeyBinding OpenMenuKey;

    //Instancing For Special Access
    public static CombatModClient INSTANCE = new CombatModClient();

    public static final Logger LOGGER = LoggerFactory.getLogger("InfReach");

    @Override
    public void onInitializeClient() {
        //Keybinding
        OpenMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.combatmod.openmenu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.combatmod"));

        //Remove when adding Config
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
        //Remove when adding Config
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

        //Theme Switcher
        if (SharedVariables.EnabledButtons.contains("SwitchTheme")) {
            SharedVariables.GuiRed = 249;
            SharedVariables.GuiGreen = 141;
            SharedVariables.GuiBlue = 0;
            SharedVariables.GuiColor = new Color(249, 141, 0, 255).getRGB();
        } else {
            SharedVariables.GuiRed = 16;
            SharedVariables.GuiGreen = 213;
            SharedVariables.GuiBlue = 235;
            SharedVariables.GuiColor = new Color(16, 213, 235, 255).getRGB();
        }
    }

    public void onButtonEnable(String ButtonId) {
        if (ButtonId.equals("TitleScreenSinglePlayer")) {
            //SharedVariables.EnabledButtons.remove("TitleScreenSinglePlayer");
        }
    }

    public void onButtonDisable(String ButtonId) {

    }
}
