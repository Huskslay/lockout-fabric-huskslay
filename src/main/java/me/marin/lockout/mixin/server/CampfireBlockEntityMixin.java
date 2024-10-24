package me.marin.lockout.mixin.server;

import me.marin.lockout.Lockout;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.goals.misc.FillCampfireWithFoodGoal;
import me.marin.lockout.server.LockoutServer;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {

    @Inject(method = "addItem", at = @At("RETURN"))
    public void addItem(Entity user, ItemStack stack, int cookTime, CallbackInfoReturnable<Boolean> cir) {
        World world = user.getWorld();
        if (world.isClient) return;
        Lockout lockout = LockoutServer.lockout;
        if (!Lockout.isLockoutRunning(lockout)) return;
        if (!(user instanceof PlayerEntity player) || !cir.getReturnValueZ()) return;

        CampfireBlockEntity campfire = (CampfireBlockEntity) (Object) this;

        boolean filled = true;
        for (ItemStack itemStack : campfire.getItemsBeingCooked()) {
            if (itemStack.isEmpty()) {
                filled = false;
                break;
            }
        }
        if (!filled) return;

        for (Goal goal : lockout.getBoard().getGoals()) {
            if (goal == null) continue;
            if (goal.isCompleted()) continue;

            if (goal instanceof FillCampfireWithFoodGoal) {
                lockout.completeGoal(goal, player);
            }
        }
    }

}
