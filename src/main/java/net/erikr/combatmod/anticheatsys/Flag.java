package net.erikr.combatmod.anticheatsys;

import net.erikr.combatmod.CombatModClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.erikr.combatmod.gui.Gui.mc;

public class Flag {
    String name;

    public Flag(String name) {
        this.name = name;
    }

    public void sendAlert(PlayerEntity player) {
        mc.player.sendMessage(Text.literal(Formatting.GOLD+"CombatMod"+Formatting.BLACK+" » " +Formatting.GOLD+"Anticheat"+Formatting.BLACK+" » " +Formatting.GRAY+ player.getGameProfile().getName()+Formatting.RED+" failed "+name+"!"));
    }
}
