package me.marin.lockout.mixin.server;

import me.marin.lockout.Lockout;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.goals.misc.UseBrushOnSuspiciousBlock;
import me.marin.lockout.server.LockoutServer;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushableBlockEntity.class)
public class BrushableBlockEntityMixin {

    @Inject(method = "finishBrushing", at = @At("HEAD"))
    public void finishBrushing(PlayerEntity player, CallbackInfo ci) {
        World world = player.getWorld();
        if (world.isClient) return;
        Lockout lockout = LockoutServer.lockout;
        if (!Lockout.isLockoutRunning(lockout)) return;

        for (Goal goal : lockout.getBoard().getGoals()) {
            if (goal == null) continue;
            if (goal.isCompleted()) continue;

            if (goal instanceof UseBrushOnSuspiciousBlock) {
                lockout.completeGoal(goal, player);
            }
        }
    }

}
