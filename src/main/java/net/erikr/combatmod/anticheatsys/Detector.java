package net.erikr.combatmod.anticheatsys;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.HashMap;

import static net.erikr.combatmod.gui.Gui.mc;

public class Detector {
    public static Detector INSTANCE = new Detector();
    private int timer;
    HashMap<PlayerEntity, Integer> flyTime = new HashMap<>();

    Flag flightCheck = new Flag("Flight");
    Flag reachCheck = new Flag("Reach");

    public void onTick() {
        if (SharedVariables.Anticheat) {
            for (Entity e : mc.world.getEntities()) {
                if (e instanceof PlayerEntity pe && e.distanceTo(mc.player) < 200 /* && e != mc.player*/) {
                    if (timer == 0) {
                        checkForFly(pe);
                        checkForReach(pe);
                        timer = 0;
                    }
                }
            }
        }

        if (timer > 0) {
            timer -= 1;
        }
    }

    public void checkForFly(PlayerEntity e) {
        if (PlayerFlying(e) && !e.getAbilities().creativeMode) {
            if (flyTime.get(e) != null && flyTime.get(e) > 200) {
                flightCheck.sendAlert(e);
                flyTime.put(e, 0);
            } else {
                if (flyTime.get(e) != null) {
                    flyTime.put(e, flyTime.get(e) + 1);
                } else {
                    flyTime.put(e, 1);
                }
            }
        } else {
            if (flyTime.get(e) != null && flyTime.get(e) > 0) {
                flyTime.put(e, flyTime.get(e) - 10);
            }
        }
    }

    public void checkForReach(PlayerEntity e) {
        if (mc.player.hurtTime > 0) {
            if (e.distanceTo(mc.player) > 6 && e == SharedVariables.lastAttacker) {
                reachCheck.sendAlert(e);
            }
        }
    }

    public boolean PlayerFlying(PlayerEntity e) {
        boolean result = true;
        for(int x = -1; x <= 1; x++){
                for(int z = -1; z <= 1; z++){
                    result &= mc.world.getBlockState(e.getBlockPos().add(x, -1, z)).getBlock() == Blocks.AIR;
                }
        }
        return result && e.getVelocity().y > 0;
    }
}
