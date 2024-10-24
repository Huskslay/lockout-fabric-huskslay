package me.marin.lockout.mixin.server;

import me.marin.lockout.Lockout;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.goals.misc.UseGlowInkGoal;
import me.marin.lockout.server.LockoutServer;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlowInkSacItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowInkSacItem.class)
public class GlowInkSacItemMixin {

    @Inject(method="useOnSign", at = @At("RETURN"))
    public void useOnSign(World world, SignBlockEntity signBlockEntity, boolean front, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        World _world = player.getWorld();
        if (_world.isClient) return;
        Lockout lockout = LockoutServer.lockout;
        if (!Lockout.isLockoutRunning(lockout)) return;
        if (!cir.getReturnValue()) return;

        for (Goal goal : lockout.getBoard().getGoals()) {
            if (goal == null) continue;
            if (goal.isCompleted()) continue;

            if (goal instanceof UseGlowInkGoal) {
                lockout.completeGoal(goal, player);
            }
        }

    }

}
