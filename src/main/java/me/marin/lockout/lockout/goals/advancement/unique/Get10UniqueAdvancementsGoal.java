package me.marin.lockout.lockout.goals.advancement.unique;

import me.marin.lockout.lockout.interfaces.GetUniqueAdvancementsGoal;
import net.minecraft.util.Identifier;

import static me.marin.lockout.Constants.NAMESPACE;

public class Get10UniqueAdvancementsGoal extends GetUniqueAdvancementsGoal {

    public Get10UniqueAdvancementsGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Get 10 Advancements";
    }

    @Override
    public int getAmount() {
        return 10;
    }

    private static final Identifier TEXTURE = new Identifier(NAMESPACE, "textures/custom/10_advancements.png");
    @Override
    public Identifier getTextureIdentifier() {
        return TEXTURE;
    }
    
}
