package me.marin.lockoutbutbetter.lockout.goals.obtain;

import me.marin.lockoutbutbetter.lockout.interfaces.ObtainItemsGoal;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.List;
import java.util.Set;

public class ObtainStoneTools extends ObtainItemsGoal {

    private static final List<Item> ITEMS = List.of(Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SWORD, Items.STONE_SHOVEL);

    public ObtainStoneTools(String id) {
        super(id);
    }

    @Override
    public List<Item> getItems() {
        return ITEMS;
    }

    @Override
    public String getGoalName() {
        return "Obtain Stone Tools";
    }

}
