package net.erikr.combatmod;

import jdk.jshell.Snippet;
import net.erikr.combatmod.configsys.ConfigSystem;
import net.erikr.combatmod.gui.Gui;
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

        ConfigSystem.createConfig();
        ConfigSystem.fixConfig();

        if (ConfigSystem.enabledButtonGetter(6).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleCustomHotbar");
        }
        if (ConfigSystem.enabledButtonGetter(7).equals("True")) {
            SharedVariables.EnabledButtons.add("TogglePlayerInspector");
        }
        if (ConfigSystem.enabledButtonGetter(8).equals("True")) {
            SharedVariables.EnabledButtons.add("TogglePlayerInfo");
        }
        if (ConfigSystem.enabledButtonGetter(9).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleCoordinateHud");
        }
        if (ConfigSystem.enabledButtonGetter(10).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleDirectionHud");
        }
        if (ConfigSystem.enabledButtonGetter(11).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleAutoSprint");
        }
        if (ConfigSystem.enabledButtonGetter(12).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleFullbright");
        }
        if (ConfigSystem.enabledButtonGetter(13).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleBreakingParticles");
        }
        if (ConfigSystem.enabledButtonGetter(14).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleOwnNametag");
        }
        if (ConfigSystem.enabledButtonGetter(15).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleExplosions");
        }
        if (ConfigSystem.enabledButtonGetter(16).equals("True")) {
            SharedVariables.EnabledButtons.add("ToggleKeystrokes");
        }

        if (ConfigSystem.getInited().equals("True")) {
            SharedVariables.ButtonPoses.put("chudx1", Integer.valueOf(ConfigSystem.buttonPoseGetter(20)));
            SharedVariables.ButtonPoses.put("chudy1", Integer.valueOf(ConfigSystem.buttonPoseGetter(21)));
            SharedVariables.ButtonPoses.put("chudx2", Integer.valueOf(ConfigSystem.buttonPoseGetter(22)));
            SharedVariables.ButtonPoses.put("chudy2", Integer.valueOf(ConfigSystem.buttonPoseGetter(23)));

            SharedVariables.ButtonPoses.put("dhudx1", Integer.valueOf(ConfigSystem.buttonPoseGetter(24)));
            SharedVariables.ButtonPoses.put("dhudy1", Integer.valueOf(ConfigSystem.buttonPoseGetter(25)));
            SharedVariables.ButtonPoses.put("dhudx2", Integer.valueOf(ConfigSystem.buttonPoseGetter(26)));
            SharedVariables.ButtonPoses.put("dhudy2", Integer.valueOf(ConfigSystem.buttonPoseGetter(27)));

            SharedVariables.ButtonPoses.put("pinsx1", Integer.valueOf(ConfigSystem.buttonPoseGetter(28)));
            SharedVariables.ButtonPoses.put("pinsy1", Integer.valueOf(ConfigSystem.buttonPoseGetter(29)));
            SharedVariables.ButtonPoses.put("pinsx2", Integer.valueOf(ConfigSystem.buttonPoseGetter(30)));
            SharedVariables.ButtonPoses.put("pinsy2", Integer.valueOf(ConfigSystem.buttonPoseGetter(31)));

            SharedVariables.ButtonPoses.put("pinfox1", Integer.valueOf(ConfigSystem.buttonPoseGetter(32)));
            SharedVariables.ButtonPoses.put("pinfoy1", Integer.valueOf(ConfigSystem.buttonPoseGetter(33)));
            SharedVariables.ButtonPoses.put("pinfox2", Integer.valueOf(ConfigSystem.buttonPoseGetter(34)));
            SharedVariables.ButtonPoses.put("pinfoy2", Integer.valueOf(ConfigSystem.buttonPoseGetter(35)));

            SharedVariables.ButtonPoses.put("keyx1", Integer.valueOf(ConfigSystem.buttonPoseGetter(36)));
            SharedVariables.ButtonPoses.put("keyy1", Integer.valueOf(ConfigSystem.buttonPoseGetter(37)));
            SharedVariables.ButtonPoses.put("keyx2", Integer.valueOf(ConfigSystem.buttonPoseGetter(38)));
            SharedVariables.ButtonPoses.put("keyy2", Integer.valueOf(ConfigSystem.buttonPoseGetter(39)));
        }

        SharedVariables.ThemeColor = ConfigSystem.getThemeColor();

        SharedVariables.ThemeMode = ConfigSystem.getThemeMode();
    }

    public void onTick() {
        if (ConfigSystem.getInited().equals("True")&& mc.currentScreen != null && mc.currentScreen.equals(ScreenEditor.INSTANCE)) {
            ConfigSystem.printButtonPose("  chudx1", SharedVariables.ButtonPoses.get("chudx1"), 20);
            ConfigSystem.printButtonPose("  chudy1", SharedVariables.ButtonPoses.get("chudy1"), 21);
            ConfigSystem.printButtonPose("  chudx2", SharedVariables.ButtonPoses.get("chudx2"), 22);
            ConfigSystem.printButtonPose("  chudy2", SharedVariables.ButtonPoses.get("chudy2"), 23);
            ConfigSystem.printButtonPose("  dhudx1", SharedVariables.ButtonPoses.get("dhudx1"), 24);
            ConfigSystem.printButtonPose("  dhudy1", SharedVariables.ButtonPoses.get("dhudy1"), 25);
            ConfigSystem.printButtonPose("  dhudx2", SharedVariables.ButtonPoses.get("dhudx2"), 26);
            ConfigSystem.printButtonPose("  dhudy2", SharedVariables.ButtonPoses.get("dhudy2"), 27);
            ConfigSystem.printButtonPose("  pinsx1", SharedVariables.ButtonPoses.get("pinsx1"), 28);
            ConfigSystem.printButtonPose("  pinsy1", SharedVariables.ButtonPoses.get("pinsy1"), 29);
            ConfigSystem.printButtonPose("  pinsx2", SharedVariables.ButtonPoses.get("pinsx2"), 30);
            ConfigSystem.printButtonPose("  pinsy2", SharedVariables.ButtonPoses.get("pinsy2"), 31);
            ConfigSystem.printButtonPose("  pinfox1", SharedVariables.ButtonPoses.get("pinfox1"), 32);
            ConfigSystem.printButtonPose("  pinfoy1", SharedVariables.ButtonPoses.get("pinfoy1"), 33);
            ConfigSystem.printButtonPose("  pinfox2", SharedVariables.ButtonPoses.get("pinfox2"), 34);
            ConfigSystem.printButtonPose("  pinfoy2", SharedVariables.ButtonPoses.get("pinfoy2"), 35);
            ConfigSystem.printButtonPose("  keyx1", SharedVariables.ButtonPoses.get("keyx1"), 36);
            ConfigSystem.printButtonPose("  keyy1", SharedVariables.ButtonPoses.get("keyy1"), 37);
            ConfigSystem.printButtonPose("  keyx2", SharedVariables.ButtonPoses.get("keyx2"), 38);
            ConfigSystem.printButtonPose("  keyy2", SharedVariables.ButtonPoses.get("keyy2"), 39);
        }


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
        if (SharedVariables.EnabledButtons.contains("ToggleBreakingParticles") && !SharedVariables.NoBreakingParticles || !SharedVariables.EnabledButtons.contains("ToggleBreakingParticles") && SharedVariables.NoBreakingParticles) {
            if (SharedVariables.EnabledButtons.contains("ToggleBreakingParticles")) {
                SharedVariables.NoBreakingParticles = true;
                ConfigSystem.writeLine("   ToggleBreakingParticles = True", 13);
            } else {
                SharedVariables.NoBreakingParticles = false;
                ConfigSystem.writeLine("   ToggleBreakingParticles = False", 13);
            }
        }
        if (SharedVariables.EnabledButtons.contains("ToggleExplosions") && !SharedVariables.NoExplosionParticles || !SharedVariables.EnabledButtons.contains("ToggleExplosions") && SharedVariables.NoExplosionParticles) {
            if (SharedVariables.EnabledButtons.contains("ToggleExplosions")) {
                SharedVariables.NoExplosionParticles = true;
                ConfigSystem.writeLine("   ToggleExplosions = True", 15);
            } else {
                SharedVariables.NoExplosionParticles = false;
                ConfigSystem.writeLine("   ToggleExplosions = False", 15);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleOwnNametag") && !SharedVariables.OwnNametag || !SharedVariables.EnabledButtons.contains("ToggleOwnNametag") && SharedVariables.OwnNametag) {
            if (SharedVariables.EnabledButtons.contains("ToggleOwnNametag")) {
                SharedVariables.OwnNametag = true;
                ConfigSystem.writeLine("   ToggleOwnNametag = True", 14);
            } else {
                SharedVariables.OwnNametag = false;
                ConfigSystem.writeLine("   ToggleOwnNametag = False", 14);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleFullbright") && !SharedVariables.Fullbright || !SharedVariables.EnabledButtons.contains("ToggleFullbright") && SharedVariables.Fullbright) {
            if (SharedVariables.EnabledButtons.contains("ToggleFullbright")) {
                SharedVariables.Fullbright = true;
                ConfigSystem.writeLine("   ToggleFullbright = True", 12);
            } else {
                SharedVariables.Fullbright = false;
                ConfigSystem.writeLine("   ToggleFullbright = False", 12);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleCustomHotbar") && !SharedVariables.RenderCustomHotbarHud || !SharedVariables.EnabledButtons.contains("ToggleCustomHotbar") && SharedVariables.RenderCustomHotbarHud) {
            if (SharedVariables.EnabledButtons.contains("ToggleCustomHotbar")) {
                SharedVariables.RenderCustomHotbarHud = true;
                ConfigSystem.writeLine("   ToggleCustomHotbar = True", 6);
            } else {
                SharedVariables.RenderCustomHotbarHud = false;
                ConfigSystem.writeLine("   ToggleCustomHotbar = False", 6);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleAutoSprint") && !SharedVariables.AutoSprint || !SharedVariables.EnabledButtons.contains("ToggleAutoSprint") && SharedVariables.AutoSprint) {
            if (SharedVariables.EnabledButtons.contains("ToggleAutoSprint")) {
                SharedVariables.AutoSprint = true;
                ConfigSystem.writeLine("   ToggleAutoSprint = True", 11);
            } else {
                SharedVariables.AutoSprint = false;
                ConfigSystem.writeLine("   ToggleAutoSprint = False", 11);
            }
        }


        if (SharedVariables.EnabledButtons.contains("TogglePlayerInspector") && !SharedVariables.RenderPlayerInspector || !SharedVariables.EnabledButtons.contains("TogglePlayerInspector") && SharedVariables.RenderPlayerInspector) {
            if (SharedVariables.EnabledButtons.contains("TogglePlayerInspector")) {
                SharedVariables.RenderPlayerInspector = true;
                ConfigSystem.writeLine("   TogglePlayerInspector = True", 7);
            } else {
                SharedVariables.RenderPlayerInspector = false;
                ConfigSystem.writeLine("   TogglePlayerInspector = False", 7);
            }
        }

        if (SharedVariables.EnabledButtons.contains("TogglePlayerInfo") && !SharedVariables.RenderPlayerInfo || !SharedVariables.EnabledButtons.contains("TogglePlayerInfo") && SharedVariables.RenderPlayerInfo) {
            if (SharedVariables.EnabledButtons.contains("TogglePlayerInfo")) {
                SharedVariables.RenderPlayerInfo = true;
                ConfigSystem.writeLine("   TogglePlayerInfo = True", 8);
            } else {
                SharedVariables.RenderPlayerInfo = false;
                ConfigSystem.writeLine("   TogglePlayerInfo = False", 8);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleCoordinateHud") && !SharedVariables.RenderCoordinateHud || !SharedVariables.EnabledButtons.contains("ToggleCoordinateHud") && SharedVariables.RenderCoordinateHud) {
            if (SharedVariables.EnabledButtons.contains("ToggleCoordinateHud")) {
                SharedVariables.RenderCoordinateHud = true;
                ConfigSystem.writeLine("   ToggleCoordinateHud = True", 9);
            } else {
                SharedVariables.RenderCoordinateHud = false;
                ConfigSystem.writeLine("   ToggleCoordinateHud = False", 9);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleDirectionHud") && !SharedVariables.RenderDirectionHud || !SharedVariables.EnabledButtons.contains("ToggleDirectionHud") && SharedVariables.RenderDirectionHud) {
            if (SharedVariables.EnabledButtons.contains("ToggleDirectionHud")) {
                SharedVariables.RenderDirectionHud = true;
                ConfigSystem.writeLine("   ToggleDirectionHud = True", 10);
            } else {
                SharedVariables.RenderDirectionHud = false;
                ConfigSystem.writeLine("   ToggleDirectionHud = False", 10);
            }
        }

        if (SharedVariables.EnabledButtons.contains("ToggleKeystrokes") && !SharedVariables.Keystrokes || !SharedVariables.EnabledButtons.contains("ToggleKeystrokes") && SharedVariables.Keystrokes) {
            if (SharedVariables.EnabledButtons.contains("ToggleKeystrokes")) {
                SharedVariables.Keystrokes = true;
                ConfigSystem.writeLine("   ToggleKeystrokes = True", 16);
            } else {
                SharedVariables.Keystrokes = false;
                ConfigSystem.writeLine("   ToggleKeystrokes = False", 16);
            }
        }


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

        if (ButtonId.equals("ResetEditor")) {
            int WindowWidth = mc.getWindow().getScaledWidth();

            SharedVariables.ButtonPoses.put("chudx1", 4);
            SharedVariables.ButtonPoses.put("chudy1", 4);
            SharedVariables.ButtonPoses.put("chudx2", 70);
            SharedVariables.ButtonPoses.put("chudy2", 44);
            SharedVariables.ButtonPoses.put("dhudx1", 4);
            SharedVariables.ButtonPoses.put("dhudy1", 46);
            SharedVariables.ButtonPoses.put("dhudx2", 110);
            SharedVariables.ButtonPoses.put("dhudy2", 65);
            SharedVariables.ButtonPoses.put("pinsx1", WindowWidth-175);
            SharedVariables.ButtonPoses.put("pinsy1", 4);
            SharedVariables.ButtonPoses.put("pinsx2", WindowWidth-95);
            SharedVariables.ButtonPoses.put("pinsy2", 44);
            SharedVariables.ButtonPoses.put("pinfox1", WindowWidth-90);
            SharedVariables.ButtonPoses.put("pinfoy1", 4);
            SharedVariables.ButtonPoses.put("pinfox2", WindowWidth-10);
            SharedVariables.ButtonPoses.put("pinfoy2", 44);
            SharedVariables.ButtonPoses.put("keyx1", 4);
            SharedVariables.ButtonPoses.put("keyy1", 90);
            SharedVariables.ButtonPoses.put("keyx2", 86);
            SharedVariables.ButtonPoses.put("keyy2", 170);

            SharedVariables.EnabledButtons.remove("ResetEditor");
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

            ConfigSystem.writeLine("ThemeColor = "+SharedVariables.ThemeColor, 1);
        }

        if (CyclerId.equals("SwitchThemeMode")) {
            if (SharedVariables.ThemeMode.equals("Light")) {
                SharedVariables.ThemeMode = "Dark";
            } else if (SharedVariables.ThemeMode.equals("Dark")) {
                SharedVariables.ThemeMode = "Light";
            }

            ConfigSystem.writeLine("ThemeMode = "+SharedVariables.ThemeMode, 2);
        }
    }

    public static float[] getRainbowColor() {
        float x = System.currentTimeMillis() % 2000 / 1000F;

        float[] rainbow = new float[3];

        rainbow[0] = 0.5F + 0.5F * MathHelper.sin(x * (float)Math.PI);
        rainbow[1] = 0.5F + 0.5F * MathHelper.sin((x + 4F / 3F) * (float)Math.PI);
        rainbow[2] = 0.5F + 0.5F * MathHelper.sin((x + 8F / 3F) * (float)Math.PI);

        return rainbow;
    }
}
