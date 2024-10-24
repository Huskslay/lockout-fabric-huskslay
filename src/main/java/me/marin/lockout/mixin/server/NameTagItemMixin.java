package me.marin.lockout.mixin.server;

import me.marin.lockout.Lockout;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.server.LockoutServer;
import me.marin.lockout.lockout.goals.huskslay.MakeRainbowSheepGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.NameTagItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NameTagItem.class)
public class NameTagItemMixin {

   @Inject(method = "useOnEntity", at = @At("RETURN"))
   public void useNameTag(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
      World world = player.getWorld();
      if (world.isClient) return;
      Lockout lockout = LockoutServer.lockout;
      if (!Lockout.isLockoutRunning(lockout)) return;

      for (Goal goal : lockout.getBoard().getGoals()) {
         if (goal == null) continue;
         if (goal.isCompleted()) continue;

         if (!(goal instanceof MakeRainbowSheepGoal)) continue;

         if (cir.getReturnValue() != ActionResult.CONSUME) return;
         if (!(entity instanceof SheepEntity)) return;

         NbtCompound comp = stack.getNbt();
         if (comp == null) return;
         String txt = comp.getCompound("display").getString("Name");
         if (txt.equals("{\"text\":\"jeb_\"}")) lockout.completeGoal(goal, player);
      }

   }

}