package net.erikr.combatmod.mixin;

import net.erikr.combatmod.CombatModClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if(CombatModClient.ZoomKey.isPressed()) {
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov * 0.2);
        }
    }
}
