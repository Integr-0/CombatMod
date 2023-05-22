package net.erikr.combatmod.mixin;

import net.erikr.combatmod.CombatModClient;
import net.erikr.combatmod.SharedVariables;
import net.erikr.combatmod.anticheatsys.Detector;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.erikr.combatmod.gui.Gui.mc;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    //onTick
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo ci) {
        if (mc.player != null) {
            CombatModClient.INSTANCE.onTick();
            Detector.INSTANCE.onTick();
        }
    }

    //Outlining Entities
    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    private void outlineEntities(Entity entity, CallbackInfoReturnable<Boolean> ci) {
        if (mc.player != null && mc.player.canSee(entity) && SharedVariables.RenderPlayerInspector) {
            if (entity.getType() != EntityType.ARMOR_STAND && entity.getType() != EntityType.ITEM && entity.getType() != EntityType.ITEM_FRAME && entity.getType() != EntityType.GLOW_ITEM_FRAME) {
                ci.setReturnValue(true);
            } else ci.setReturnValue(false);

        }
    }
}
