package net.erikr.combatmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.erikr.combatmod.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
    public static void renderHud(MatrixStack matrices) {
        //Height + Width
        int WindowHeight = mc.getWindow().getScaledHeight();
        int WindowWidth = mc.getWindow().getScaledWidth();

        //Position
        if (SharedVariables.RenderCoordinateHud) {
            double x = mc.player.getX();
            double y = mc.player.getY();
            double z = mc.player.getZ();

            String newX = String.valueOf((int) x);
            String newY = String.valueOf((int) y);
            String newZ = String.valueOf((int) z);

            GuiDrawer.drawBox(matrices, 4, 4, 70, 44, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "X: " + Formatting.RESET + newX, 10, 10, SharedVariables.GuiColor);
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Y: " + Formatting.RESET + newY, 10, 20, SharedVariables.GuiColor);
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Z: " + Formatting.RESET + newZ, 10, 30, SharedVariables.GuiColor);
        }

        //Direction
        if (SharedVariables.RenderDirectionHud) {
            Direction dir = mc.player.getMovementDirection();

            GuiDrawer.drawBox(matrices, 4, 46, 110, 65, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Direction: " + Formatting.RESET + dir.toString(), 10, 52, SharedVariables.GuiColor);
        }

        //Player Inspector
        if (SharedVariables.RenderPlayerInspector) {
            for (Entity e : mc.world.getEntities()) {
                if (PlayerStaring(e) && e instanceof PlayerEntity && e != mc.player) {
                    SharedVariables.EntityToInspect = (PlayerEntity) e;
                }
            }

            if (SharedVariables.EntityToInspect != null) {
                GuiDrawer.drawBox(matrices, WindowWidth-175, 4, WindowWidth-95, 44, new Color(255, 255, 255, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "Inspected:", WindowWidth-169, 10, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, "Health: "+ (int) SharedVariables.EntityToInspect.getHealth(), WindowWidth-169, 20, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, "Distance: " + (int) mc.player.distanceTo(SharedVariables.EntityToInspect), WindowWidth-169, 30, SharedVariables.GuiColor);
            }

        }

        //Player Stats
        if (SharedVariables.RenderPlayerInfo) {
            GuiDrawer.drawBox(matrices, WindowWidth-90, 4, WindowWidth-10, 44, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Your Stats:", WindowWidth-84, 10, SharedVariables.GuiColor);
            GuiDrawer.drawText(matrices, "Health: "+ (int) mc.player.getHealth(), WindowWidth-84, 20, SharedVariables.GuiColor);
            GuiDrawer.drawText(matrices, "Defense: "+ mc.player.getArmor(), WindowWidth-84, 30, SharedVariables.GuiColor);
        }

        //Health Bar + Food Bar + Armor Bar + Air Display
        if (SharedVariables.RenderCustomHotbarHud && !mc.player.getAbilities().creativeMode) {
            GuiDrawer.drawBox(matrices, WindowWidth/2-15, WindowHeight-70, WindowWidth/2+15, WindowHeight-35, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Air: ", WindowWidth/2-10, WindowHeight-65, SharedVariables.GuiColor);
            GuiDrawer.drawText(matrices, "" + mc.player.getAir(), WindowWidth/2-10, WindowHeight-55, SharedVariables.GuiColor);

            GuiDrawer.drawBox(matrices, WindowWidth/2+20, WindowHeight-70, WindowWidth/2+100, WindowHeight-35, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+97, WindowHeight-38, new Color(151, 151, 151, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Food: " + Formatting.RESET + mc.player.getHungerManager().getFoodLevel()/2, WindowWidth/2+45, WindowHeight-65, SharedVariables.GuiColor);

            if (mc.player.getHungerManager().getFoodLevel()/2 >= 10) { //10 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+97, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 9) { //9 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+95, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 8) { //8 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+90, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 7) { //7 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+85, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 6) { //6 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+80, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 5) { //5 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+75, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 4) { //4 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+70, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 3) { //3 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+65, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 2) { //2 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+60, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 1) { //1 Food
                GuiDrawer.drawBox(matrices, WindowWidth/2+23, WindowHeight-53, WindowWidth/2+55, WindowHeight-38, SharedVariables.GuiColor);
            }
            if (mc.player.getHungerManager().getFoodLevel()/2 == 0) { //0 Food

            }

            GuiDrawer.drawBox(matrices, WindowWidth/2-100, WindowHeight-70, WindowWidth/2-20, WindowHeight-35, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-23, WindowHeight-38, new Color(151, 151, 151, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Health: " + Formatting.RESET + (int) mc.player.getHealth()/2, WindowWidth/2-95, WindowHeight-65, SharedVariables.GuiColor);

            if ((int) mc.player.getHealth()/2 >= 10) { //10 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-23, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 9) { //9 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-25, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 8) { //8 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-30, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 7) { //7 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-35, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 6) { //6 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-40, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 5) { //5 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-45, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 4) { //4 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-50, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 3) { //3 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-55, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 2) { //2 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-60, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 1) { //1 Hearts
                GuiDrawer.drawBox(matrices, WindowWidth/2-97, WindowHeight-53, WindowWidth/2-65, WindowHeight-38, SharedVariables.GuiColor);
            }
            if ((int) mc.player.getHealth()/2 == 0) { //0 Hearts
            }
        }

        //AutoSprint
        if (mc.inGameHud.getChatHud() == null && SharedVariables.AutoSprint) {
            GuiDrawer.drawBox(matrices, 4, WindowHeight-23, 109, WindowHeight-4, new Color(255, 255, 255, 255).getRGB());
            GuiDrawer.drawText(matrices, Formatting.BOLD + "Sprinting: Auto", 10, WindowHeight-17, SharedVariables.GuiColor);
        }

        //Keystrokes
        if (SharedVariables.Keystrokes) {
            GuiDrawer.drawBox(matrices, 4, 90, 86, 170, new Color(255, 255, 255, 255).getRGB());

            if (mc.options.leftKey.isPressed()) {
                GuiDrawer.drawBox(matrices, 10, 120, 30, 140, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, Formatting.BOLD + "A", 17, 127, new Color(255, 255, 255, 255).getRGB());
            } else {
                GuiDrawer.drawBox(matrices, 10, 120, 30, 140, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "A", 17, 127,new Color(255, 255, 255, 255).getRGB());
            }

            if (mc.options.backKey.isPressed()) {
                GuiDrawer.drawBox(matrices, 35, 120, 55, 140, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, Formatting.BOLD + "S", 42, 127, new Color(255, 255, 255, 255).getRGB());
            } else {
                GuiDrawer.drawBox(matrices, 35, 120, 55, 140, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "S", 42, 127, new Color(255, 255, 255, 255).getRGB());
            }

            if (mc.options.rightKey.isPressed()) {
                GuiDrawer.drawBox(matrices, 60, 120, 80, 140, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, Formatting.BOLD + "D", 67, 127, new Color(255, 255, 255, 255).getRGB());
            } else {
                GuiDrawer.drawBox(matrices, 60, 120, 80, 140, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "D", 67, 127, new Color(255, 255, 255, 255).getRGB());
            }

            if (mc.options.forwardKey.isPressed()) {
                GuiDrawer.drawBox(matrices, 35, 95, 55, 115, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, Formatting.BOLD + "W", 42, 102, new Color(255, 255, 255, 255).getRGB());
            } else {
                GuiDrawer.drawBox(matrices, 35, 95, 55, 115, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "W", 42, 102, new Color(255, 255, 255, 255).getRGB());
            }

            if (mc.options.jumpKey.isPressed()) {
                GuiDrawer.drawBox(matrices, 10, 145, 80, 165, SharedVariables.GuiColor);
                GuiDrawer.drawText(matrices, Formatting.BOLD + "-------", 21, 150, new Color(255, 255, 255, 255).getRGB());
            } else {
                GuiDrawer.drawBox(matrices, 10, 145, 80, 165, new Color(151, 151, 151, 255).getRGB());
                GuiDrawer.drawText(matrices, Formatting.BOLD + "-------", 21, 150, new Color(255, 255, 255, 255).getRGB());
            }
        }
    }
}

