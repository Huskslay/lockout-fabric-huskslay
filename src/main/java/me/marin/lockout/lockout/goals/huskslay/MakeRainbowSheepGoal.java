package me.marin.lockout.lockout.goals.huskslay;

import me.marin.lockout.Constants;
import me.marin.lockout.lockout.Goal;
import me.marin.lockout.lockout.texture.TextureProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MakeRainbowSheepGoal extends Goal implements TextureProvider {

    public MakeRainbowSheepGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Make a Rainbow Sheep with jeb_ Name Tag";
    }

    @Override
    public ItemStack getTextureItemStack() {
        return null;
    }

    private static final Identifier TEXTURE = new Identifier(Constants.NAMESPACE, "textures/custom/huskslay/rainbow_sheep.png");
    @Override
    public Identifier getTextureIdentifier() {
        return TEXTURE;
    }

}