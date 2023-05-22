package net.erikr.combatmod.anticheatsys;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.HashMap;

import static net.erikr.combatmod.gui.Gui.mc;

public class Detector {
    public static Detector INSTANCE = new Detector();

    HashMap<PlayerEntity, Integer> flyTime = new HashMap<>();
    HashMap<PlayerEntity, Vec3d> veloStartPos = new HashMap<>();

    Flag flightCheck = new Flag("Flight");
    Flag veloCheckFlag = new Flag("Velocity");

    public void onTick() {
        if (SharedVariables.Anticheat) {
            for (Entity e : mc.world.getEntities()) {
                if (e instanceof PlayerEntity && e.distanceTo(mc.player) < 200 /* && e != mc.player*/) {
                    checkForFly((PlayerEntity) e);
                    checkForVelo((PlayerEntity) e);
                }
            }
        }
    }

    public void checkForFly(PlayerEntity e) {
        if (PlayerFlying(e)) {
            if (flyTime.get(e) != null && flyTime.get(e) > 300) {
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
                flyTime.put(e, flyTime.get(e) - 1);
            }
        }
    }

    public void checkForVelo(PlayerEntity e) {
        if (e.hurtTime > 0) {
            veloStartPos.put(e, e.getPos());
        }
        if (e.hurtTime <= 0 && veloStartPos.get(e) != null) {
            if (e.getPos().distanceTo(veloStartPos.get(e)) <= 0.02 && !e.isOnFire() && !e.isFallFlying()) {
                veloCheckFlag.sendAlert(e);
                veloStartPos.remove(e);
            }
        }
    }

    public boolean PlayerFlying(PlayerEntity e) {
        if (mc.world.getBlockState(e.getBlockPos().subtract(new Vec3i(0, 1, 0))).getBlock() == Blocks.AIR) {
            if (mc.world.getBlockState(e.getBlockPos().subtract(new Vec3i(0, 2, 0))).getBlock() == Blocks.AIR) {
                if (mc.world.getBlockState(e.getBlockPos().subtract(new Vec3i(0, 3, 0))).getBlock() == Blocks.AIR) {
                    return true;
                }
            }
        }
        return false;
    }

}
