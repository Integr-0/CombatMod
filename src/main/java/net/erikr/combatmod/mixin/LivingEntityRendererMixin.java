package net.erikr.combatmod.mixin;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntityRenderer.class})
public abstract class LivingEntityRendererMixin {
    //Rendering own Nametag
    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;hasLabel(Lnet/minecraft/entity/LivingEntity;)Z", cancellable = true)
    private void showName(LivingEntity entity, CallbackInfoReturnable<Boolean> ci) {
        if (entity == MinecraftClient.getInstance().cameraEntity && SharedVariables.OwnNametag) {
            ci.setReturnValue(MinecraftClient.isHudEnabled());
        }
    }


}
