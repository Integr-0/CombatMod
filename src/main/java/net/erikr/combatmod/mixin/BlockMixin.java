package net.erikr.combatmod.mixin;

import net.erikr.combatmod.SharedVariables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    //Cancelling Breaking Particles
    @Inject(method = "spawnBreakParticles", at = @At("HEAD"), cancellable = true)
    public void spawnBreakingParticles(World world, PlayerEntity player, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (SharedVariables.NoBreakingParticles) {
            ci.cancel();
        }
    }
}
