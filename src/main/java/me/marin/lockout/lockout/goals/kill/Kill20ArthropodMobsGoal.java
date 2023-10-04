package me.marin.lockout.lockout.goals.kill;

import me.marin.lockout.Constants;
import me.marin.lockout.Lockout;
import me.marin.lockout.LockoutTeam;
import me.marin.lockout.lockout.interfaces.KillSpecificMobsGoal;
import me.marin.lockout.lockout.texture.CycleTexturesProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Kill20ArthropodMobsGoal extends KillSpecificMobsGoal implements CycleTexturesProvider {

    private static final ItemStack ITEM_STACK = Items.WOODEN_SWORD.getDefaultStack();
    static {
        ITEM_STACK.setCount(20);
    }
    private static final List<Identifier> TEXTURES = List.of(
            new Identifier(Constants.NAMESPACE, "textures/custom/arthropod/kill_spider.png"),
            new Identifier(Constants.NAMESPACE, "textures/custom/arthropod/kill_bee.png"),
            new Identifier(Constants.NAMESPACE, "textures/custom/arthropod/kill_cave_spider.png"),
            new Identifier(Constants.NAMESPACE, "textures/custom/arthropod/kill_endermite.png"),
            new Identifier(Constants.NAMESPACE, "textures/custom/arthropod/kill_silverfish.png")
    );
    // arthropods: bee, cave spider, spider, endermite, silverfish
    private static final List<EntityType<?>> ARTHROPOD_MOBS = List.of(
            EntityType.BEE,
            EntityType.CAVE_SPIDER,
            EntityType.SPIDER,
            EntityType.ENDERMITE,
            EntityType.SILVERFISH
    );

    public Kill20ArthropodMobsGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Kill 20 Arthropods";
    }

    @Override
    public ItemStack getTextureItemStack() {
        return ITEM_STACK;
    }

    @Override
    public List<EntityType<?>> getEntityTypes() {
        return ARTHROPOD_MOBS;
    }

    @Override
    public int getAmount() {
        return 20;
    }

    @Override
    public Map<LockoutTeam, Integer> getTrackerMap() {
        return Lockout.getInstance().killedArthropods;
    }

    @Override
    public List<Identifier> getTexturesToDisplay() {
        return TEXTURES;
    }

    @Override
    public boolean renderTexture(DrawContext context, int x, int y, int tick) {
        CycleTexturesProvider.super.renderTexture(context, x, y, tick);
        context.drawItemInSlot(MinecraftClient.getInstance().textRenderer, ITEM_STACK, x, y);
        return true;
    }

    @Override
    public List<String> getTooltip(LockoutTeam team) {
        List<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add("Arthropods killed: " + getTrackerMap().getOrDefault(team, 0) + "/" + getAmount());
        lore.add(" ");

        return lore;
    }
}
