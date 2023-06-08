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
        if (mc.world.getBlockState(e.getBlockPos().add(0, -1, 0)).getBlock() == Blocks.AIR) {
            if (mc.world.getBlockState(e.getBlockPos().add(1, -1, 1)).getBlock() == Blocks.AIR) {
                if (mc.world.getBlockState(e.getBlockPos().add(-1, -1, -1)).getBlock() == Blocks.AIR) {
                    if (mc.world.getBlockState(e.getBlockPos().add(0, -1, 1)).getBlock() == Blocks.AIR) {
                        if (mc.world.getBlockState(e.getBlockPos().add(1, -1, 0)).getBlock() == Blocks.AIR) {
                            if (mc.world.getBlockState(e.getBlockPos().add(0, -1, -1)).getBlock() == Blocks.AIR) {
                                if (mc.world.getBlockState(e.getBlockPos().add(-1, -1, 0)).getBlock() == Blocks.AIR) {
                                    if (mc.world.getBlockState(e.getBlockPos().add(-1, -1, 1)).getBlock() == Blocks.AIR) {
                                        if (mc.world.getBlockState(e.getBlockPos().add(1, -1, -1)).getBlock() == Blocks.AIR) {
                                            if (mc.world.getBlockState(e.getBlockPos().add(0, -2, 0)).getBlock() == Blocks.AIR) {
                                                if (mc.world.getBlockState(e.getBlockPos().add(1, -2, 1)).getBlock() == Blocks.AIR) {
                                                    if (mc.world.getBlockState(e.getBlockPos().add(-1, -2, -1)).getBlock() == Blocks.AIR) {
                                                        if (mc.world.getBlockState(e.getBlockPos().add(0, -2, 1)).getBlock() == Blocks.AIR) {
                                                            if (mc.world.getBlockState(e.getBlockPos().add(1, -2, 0)).getBlock() == Blocks.AIR) {
                                                                if (mc.world.getBlockState(e.getBlockPos().add(0, -2, -1)).getBlock() == Blocks.AIR) {
                                                                    if (mc.world.getBlockState(e.getBlockPos().add(-1, -2, 0)).getBlock() == Blocks.AIR) {
                                                                        if (mc.world.getBlockState(e.getBlockPos().add(-1, -2, 1)).getBlock() == Blocks.AIR) {
                                                                            if (mc.world.getBlockState(e.getBlockPos().add(1, -2, -1)).getBlock() == Blocks.AIR) {
                                                                                if (mc.world.getBlockState(e.getBlockPos().add(0, -3, 0)).getBlock() == Blocks.AIR) {
                                                                                    if (mc.world.getBlockState(e.getBlockPos().add(1, -3, 1)).getBlock() == Blocks.AIR) {
                                                                                        if (mc.world.getBlockState(e.getBlockPos().add(-1, -3, -1)).getBlock() == Blocks.AIR) {
                                                                                            if (mc.world.getBlockState(e.getBlockPos().add(0, -3, 1)).getBlock() == Blocks.AIR) {
                                                                                                if (mc.world.getBlockState(e.getBlockPos().add(1, -3, 0)).getBlock() == Blocks.AIR) {
                                                                                                    if (mc.world.getBlockState(e.getBlockPos().add(0, -3, -1)).getBlock() == Blocks.AIR) {
                                                                                                        if (mc.world.getBlockState(e.getBlockPos().add(-1, -3, 0)).getBlock() == Blocks.AIR) {
                                                                                                            if (mc.world.getBlockState(e.getBlockPos().add(-1, -3, 1)).getBlock() == Blocks.AIR) {
                                                                                                                if (mc.world.getBlockState(e.getBlockPos().add(1, -3, -1)).getBlock() == Blocks.AIR) {
                                                                                                                    return true;
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
