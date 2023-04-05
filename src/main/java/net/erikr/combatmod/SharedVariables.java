package net.erikr.combatmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.HashSet;

public class SharedVariables {
    //Actionbar Info
    public static Text OverlayMessage;
    public static int OverlayRemaining;
    public static boolean OverlayTinted;

    public static String Version = "1.2.3";

    //Toggles for Features
    public static boolean NoBreakingParticles = true;
    public static boolean NoExplosionParticles = true;
    public static boolean OwnNametag = true;
    public static boolean Fullbright = true;
    public static boolean AutoSprint = true;
    public static boolean RenderCustomHotbarHud = true;
    public static boolean RenderPlayerInspector = true;
    public static boolean RenderPlayerInfo = true;
    public static boolean RenderCoordinateHud = true;
    public static boolean RenderDirectionHud = true;
    public static boolean Keystrokes = true;

    //Inspected Entity
    public static PlayerEntity EntityToInspect = null;

    //Gui Coloring
    public static int GuiRed = 249;
    public static int GuiGreen = 141;
    public static int GuiBlue = 0;
    public static int GuiColor = new Color(249, 141, 0).getRGB();

    //Enabled Buttons
    public static HashSet<String> EnabledButtons = new HashSet<>();

    //If mouse is down
    public static boolean MouseDown;

}
