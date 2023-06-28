package net.erikr.combatmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.configsys.ConfigSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class Gui {
    //MinecraftClient
    public static MinecraftClient mc = MinecraftClient.getInstance();

    //Player Staring Check
    public static boolean PlayerStaring(Entity entity) {
        Vec3d rotvec = mc.player.getRotationVec(1.0F).normalize();
        Vec3d vec3d2 = new Vec3d(entity.getX() - mc.player.getX(), entity.getEyeY() - mc.player.getEyeY(), entity.getZ() - mc.player.getZ());

        double d = vec3d2.length();
        vec3d2 = vec3d2.normalize();

        double e = rotvec.dotProduct(vec3d2);

        return e > 1.0D - 0.025D / d ? mc.player.canSee(entity) : false;
    }

    //Rendering Hud
    public static void renderHud(DrawContext context) {
        //Height + Width
        int WindowHeight = mc.getWindow().getScaledHeight();
        int WindowWidth = mc.getWindow().getScaledWidth();

        if (ConfigSystem.getInited().equals("False")) {
            if (ConfigSystem.buttonPoseGetter(20) == "error") {
                ConfigSystem.printButtonPose("  chudx1", 4, 20);
            }
            if (ConfigSystem.buttonPoseGetter(21) == "error") {
                ConfigSystem.printButtonPose("  chudy1", 4, 21);
            }
            if (ConfigSystem.buttonPoseGetter(22) == "error") {
                ConfigSystem.printButtonPose("  chudx2", 70, 22);
            }
            if (ConfigSystem.buttonPoseGetter(23) == "error") {
                ConfigSystem.printButtonPose("  chudy2", 44, 23);
            }
            if (ConfigSystem.buttonPoseGetter(24) == "error") {
                ConfigSystem.printButtonPose("  dhudx1", 4, 24);
            }
            if (ConfigSystem.buttonPoseGetter(25) == "error") {
                ConfigSystem.printButtonPose("  dhudy1", 46, 25);
            }
            if (ConfigSystem.buttonPoseGetter(26) == "error") {
                ConfigSystem.printButtonPose("  dhudx2", 110, 26);
            }
            if (ConfigSystem.buttonPoseGetter(27) == "error") {
                ConfigSystem.printButtonPose("  dhudy2", 65, 27);
            }
            if (ConfigSystem.buttonPoseGetter(28) == "error") {
                ConfigSystem.printButtonPose("  pinsx1", WindowWidth-175, 28);
            }
            if (ConfigSystem.buttonPoseGetter(29) == "error") {
                ConfigSystem.printButtonPose("  pinsy1", 4, 29);
            }
            if (ConfigSystem.buttonPoseGetter(30) == "error") {
                ConfigSystem.printButtonPose("  pinsx2", WindowWidth-95, 30);
            }
            if (ConfigSystem.buttonPoseGetter(31) == "error") {
                ConfigSystem.printButtonPose("  pinsy2", 44, 31);
            }
            if (ConfigSystem.buttonPoseGetter(32) == "error") {
                ConfigSystem.printButtonPose("  pinfox1", WindowWidth-90, 32);
            }
            if (ConfigSystem.buttonPoseGetter(33) == "error") {
                ConfigSystem.printButtonPose("  pinfoy1", 4, 33);
            }
            if (ConfigSystem.buttonPoseGetter(34) == "error") {
                ConfigSystem.printButtonPose("  pinfox2", WindowWidth-10, 34);
            }
            if (ConfigSystem.buttonPoseGetter(35) == "error") {
                ConfigSystem.printButtonPose("  pinfoy2", 44, 35);
            }
            if (ConfigSystem.buttonPoseGetter(36) == "error") {
                ConfigSystem.printButtonPose("  keyx1", 4, 36);
            }
            if (ConfigSystem.buttonPoseGetter(37) == "error") {
                ConfigSystem.printButtonPose("  keyy1", 90, 37);
            }
            if (ConfigSystem.buttonPoseGetter(38) == "error") {
                ConfigSystem.printButtonPose("  keyx2", 86, 38);
            }
            if (ConfigSystem.buttonPoseGetter(39) == "error") {
                ConfigSystem.printButtonPose("  keyy2", 170, 39);
            }
            if (ConfigSystem.decoratorHasError(40, "}")) {
                ConfigSystem.writeLine("}", 40);
            }
            ConfigSystem.writeLine("inited = True", 3);

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

        //Position
        if (SharedVariables.RenderCoordinateHud) {
            double x = mc.player.getX();
            double y = mc.player.getY();
            double z = mc.player.getZ();

            String newX = String.valueOf((int) x);
            String newY = String.valueOf((int) y);
            String newZ = String.valueOf((int) z);

            GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("chudx1"), SharedVariables.ButtonPoses.get("chudy1"), SharedVariables.ButtonPoses.get("chudx2"), SharedVariables.ButtonPoses.get("chudy2"), SharedVariables.GuiBack);
            GuiDrawer.drawText(context, Formatting.BOLD + "X: " + Formatting.RESET + newX, SharedVariables.ButtonPoses.get("chudx1")+6, SharedVariables.ButtonPoses.get("chudy1")+6, SharedVariables.GuiColor);
            GuiDrawer.drawText(context, Formatting.BOLD + "Y: " + Formatting.RESET + newY, SharedVariables.ButtonPoses.get("chudx1")+6, SharedVariables.ButtonPoses.get("chudy1")+16, SharedVariables.GuiColor);
            GuiDrawer.drawText(context, Formatting.BOLD + "Z: " + Formatting.RESET + newZ, SharedVariables.ButtonPoses.get("chudx1")+6, SharedVariables.ButtonPoses.get("chudy1")+26, SharedVariables.GuiColor);
        }

        //Direction
        if (SharedVariables.RenderDirectionHud) {
            Direction dir = mc.player.getMovementDirection();

            GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("dhudx1"), SharedVariables.ButtonPoses.get("dhudy1"), SharedVariables.ButtonPoses.get("dhudx2"), SharedVariables.ButtonPoses.get("dhudy2"), SharedVariables.GuiBack);
            GuiDrawer.drawText(context, Formatting.BOLD + "Direction: " + Formatting.RESET + dir.toString(), SharedVariables.ButtonPoses.get("dhudx1")+6, SharedVariables.ButtonPoses.get("dhudy1")+6, SharedVariables.GuiColor);
        }

        //Player Inspector
        if (SharedVariables.RenderPlayerInspector) {
            for (Entity e : mc.world.getEntities()) {
                if (PlayerStaring(e) && e instanceof PlayerEntity && e != mc.player) {
                    SharedVariables.EntityToInspect = (PlayerEntity) e;
                }
            }

            if (SharedVariables.EntityToInspect != null) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("pinsx1"), SharedVariables.ButtonPoses.get("pinsy1"), SharedVariables.ButtonPoses.get("pinsx2"), SharedVariables.ButtonPoses.get("pinsy2"), SharedVariables.GuiBack);
                GuiDrawer.drawText(context, Formatting.BOLD + "Inspected:", SharedVariables.ButtonPoses.get("pinsx1")+6, SharedVariables.ButtonPoses.get("pinsy1")+6, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, "Health: "+ (int) SharedVariables.EntityToInspect.getHealth(), SharedVariables.ButtonPoses.get("pinsx1")+6, SharedVariables.ButtonPoses.get("pinsy1")+16, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, "Distance: " + (int) mc.player.distanceTo(SharedVariables.EntityToInspect), SharedVariables.ButtonPoses.get("pinsx1")+6, SharedVariables.ButtonPoses.get("pinsy1")+26, SharedVariables.GuiColor);
            }

        }

        //Player Stats
        if (SharedVariables.RenderPlayerInfo) {
            GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("pinfox1"), SharedVariables.ButtonPoses.get("pinfoy1"), SharedVariables.ButtonPoses.get("pinfox2"), SharedVariables.ButtonPoses.get("pinfoy2"), SharedVariables.GuiBack);
            GuiDrawer.drawText(context, Formatting.BOLD + "Your Stats:", SharedVariables.ButtonPoses.get("pinfox1")+6, SharedVariables.ButtonPoses.get("pinfoy1")+6, SharedVariables.GuiColor);
            GuiDrawer.drawText(context, "Health: "+ (int) mc.player.getHealth(), SharedVariables.ButtonPoses.get("pinfox1")+6, SharedVariables.ButtonPoses.get("pinfoy1")+16, SharedVariables.GuiColor);
            GuiDrawer.drawText(context, "Defense: "+ mc.player.getArmor(), SharedVariables.ButtonPoses.get("pinfox1")+6, SharedVariables.ButtonPoses.get("pinfoy1")+26, SharedVariables.GuiColor);
        }

        //Health Bar + Food Bar + Armor Bar + Air Display
        if (SharedVariables.RenderCustomHotbarHud && !mc.player.getAbilities().creativeMode) {
            GuiDrawer.drawBox(context, WindowWidth/2-15, WindowHeight-70, WindowWidth/2+15, WindowHeight-35, SharedVariables.GuiBack);
            GuiDrawer.drawText(context, Formatting.BOLD + "Air: ", WindowWidth/2-10, WindowHeight-65, SharedVariables.GuiColor);
            GuiDrawer.drawText(context, "" + mc.player.getAir(), WindowWidth/2-10, WindowHeight-55, SharedVariables.GuiColor);

            GuiDrawer.drawBox(context, WindowWidth/2+20, WindowHeight-70, WindowWidth/2+100, WindowHeight-35, SharedVariables.GuiBack);
            GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+97, WindowHeight-38, new Color(151, 151, 151, 255).getRGB());
            GuiDrawer.drawText(context, Formatting.BOLD + "Food: " + Formatting.RESET + mc.player.getHungerManager().getFoodLevel()/2, WindowWidth/2+45, WindowHeight-65, SharedVariables.GuiColor);

            if (mc.player.getHungerManager().getFoodLevel()/2 >= 10) { //10 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+97, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 9) { //9 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+95, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 8) { //8 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+90, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 7) { //7 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+85, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 6) { //6 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+80, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 5) { //5 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+75, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 4) { //4 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+70, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 3) { //3 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+65, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 2) { //2 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+60, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 1) { //1 Food
                GuiDrawer.drawBox(context, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+55, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 0) { //0 Food

            }

            GuiDrawer.drawBox(context, WindowWidth/2-100, WindowHeight-70, WindowWidth/2-20, WindowHeight-35, SharedVariables.GuiBack);
            GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-23, WindowHeight-38, new Color(151, 151, 151, 255).getRGB());
            GuiDrawer.drawText(context, Formatting.BOLD + "Health: " + Formatting.RESET + (int) mc.player.getHealth()/2, WindowWidth/2-95, WindowHeight-65, SharedVariables.GuiColor);

            if ((int) mc.player.getHealth()/2 >= 10) { //10 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-23, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 9) { //9 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-25, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 8) { //8 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-30, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 7) { //7 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-35, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 6) { //6 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-40, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 5) { //5 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-45, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 4) { //4 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-50, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 3) { //3 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-55, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 2) { //2 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-60, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 1) { //1 Hearts
                GuiDrawer.drawBox(context, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-65, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 0) { //0 Hearts
            }
        }

        //Keystrokes
        if (SharedVariables.Keystrokes) {
            GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1"), SharedVariables.ButtonPoses.get("keyy1"), SharedVariables.ButtonPoses.get("keyx2"), SharedVariables.ButtonPoses.get("keyy2"), SharedVariables.GuiBack);

            if (mc.options.leftKey.isPressed()) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+6, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-56, SharedVariables.ButtonPoses.get("keyy2")-30, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, Formatting.BOLD + "A", SharedVariables.ButtonPoses.get("keyx1")+13, SharedVariables.ButtonPoses.get("keyy1")+37, SharedVariables.GuiBack);
            } else {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+6, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-56, SharedVariables.ButtonPoses.get("keyy2")-30, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(context, Formatting.BOLD + "A", SharedVariables.ButtonPoses.get("keyx1")+13, SharedVariables.ButtonPoses.get("keyy1")+37,SharedVariables.GuiBack);
            }

            if (mc.options.backKey.isPressed()) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+31, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-31, SharedVariables.ButtonPoses.get("keyy2")-30, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, Formatting.BOLD + "S", SharedVariables.ButtonPoses.get("keyx1")+38, SharedVariables.ButtonPoses.get("keyy1")+37, SharedVariables.GuiBack);
            } else {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+31, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-31, SharedVariables.ButtonPoses.get("keyy2")-30, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(context, Formatting.BOLD + "S", SharedVariables.ButtonPoses.get("keyx1")+38, SharedVariables.ButtonPoses.get("keyy1")+37, SharedVariables.GuiBack);
            }

            if (mc.options.rightKey.isPressed()) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+56, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-6, SharedVariables.ButtonPoses.get("keyy2")-30, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, Formatting.BOLD + "D", SharedVariables.ButtonPoses.get("keyx1")+63, SharedVariables.ButtonPoses.get("keyy1")+37, SharedVariables.GuiBack);
            } else {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+56, SharedVariables.ButtonPoses.get("keyy1")+30, SharedVariables.ButtonPoses.get("keyx2")-6, SharedVariables.ButtonPoses.get("keyy2")-30, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(context, Formatting.BOLD + "D", SharedVariables.ButtonPoses.get("keyx1")+63, SharedVariables.ButtonPoses.get("keyy1")+37, SharedVariables.GuiBack);
            }

            if (mc.options.forwardKey.isPressed()) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+31, SharedVariables.ButtonPoses.get("keyy1")+5, SharedVariables.ButtonPoses.get("keyx2")-31, SharedVariables.ButtonPoses.get("keyy2")-55, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, Formatting.BOLD + "W", SharedVariables.ButtonPoses.get("keyx1")+38, SharedVariables.ButtonPoses.get("keyy1")+12, SharedVariables.GuiBack);
            } else {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+31, SharedVariables.ButtonPoses.get("keyy1")+5, SharedVariables.ButtonPoses.get("keyx2")-31, SharedVariables.ButtonPoses.get("keyy2")-55, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(context, Formatting.BOLD + "W", SharedVariables.ButtonPoses.get("keyx1")+38, SharedVariables.ButtonPoses.get("keyy1")+12, SharedVariables.GuiBack);
            }

            if (mc.options.jumpKey.isPressed()) {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+6, SharedVariables.ButtonPoses.get("keyy1")+55, SharedVariables.ButtonPoses.get("keyx2")-6, SharedVariables.ButtonPoses.get("keyy2")-5, SharedVariables.GuiColor);
                GuiDrawer.drawText(context, Formatting.BOLD + "-------", SharedVariables.ButtonPoses.get("keyx1")+17, SharedVariables.ButtonPoses.get("keyy1")+60, SharedVariables.GuiBack);
            } else {
                GuiDrawer.drawBox(context, SharedVariables.ButtonPoses.get("keyx1")+6, SharedVariables.ButtonPoses.get("keyy1")+55, SharedVariables.ButtonPoses.get("keyx2")-6, SharedVariables.ButtonPoses.get("keyy2")-5, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(context, Formatting.BOLD + "-------", SharedVariables.ButtonPoses.get("keyx1")+17, SharedVariables.ButtonPoses.get("keyy1")+60, SharedVariables.GuiBack);
            }
        }
    }
}

