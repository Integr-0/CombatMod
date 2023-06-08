package net.erikr.combatmod.mixin;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "handleAttack", at = @At("HEAD"))
    public void onAttack(Entity attacker, CallbackInfoReturnable<Boolean> cir) {
        SharedVariables.lastAttacker = attacker;
    }
}
