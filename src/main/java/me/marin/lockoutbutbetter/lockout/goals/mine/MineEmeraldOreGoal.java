package me.marin.lockoutbutbetter.lockout.goals.mine;

import me.marin.lockoutbutbetter.lockout.interfaces.MineBlockGoal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.List;
import java.util.Set;

public class MineEmeraldOreGoal extends MineBlockGoal {

    private static final List<Item> ITEMS = List.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE);
    private static final Item ITEM = Items.EMERALD_ORE;

    public MineEmeraldOreGoal(String id) {
        super(id);
    }

    @Override
    public String getGoalName() {
        return "Mine Emerald Ore";
    }

    @Override
    public ItemStack getTextureItemStack() {
        return ITEM.getDefaultStack();
    }

    @Override
    public List<Item> getItems() {
        return ITEMS;
    }
}
