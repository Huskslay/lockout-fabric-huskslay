package me.marin.lockout.lockout.goals.advancement.unique;

import me.marin.lockout.lockout.interfaces.GetUniqueAdvancementsGoal;
import net.minecraft.util.Identifier;

import static me.marin.lockout.Constants.NAMESPACE;

public class Get30UniqueAdvancementsGoal extends GetUniqueAdvancementsGoal {

    public Get30UniqueAdvancementsGoal(String id, String data) {
        super(id, data);
    }

    @Override
    public String getGoalName() {
        return "Get 30 Advancements";
    }

    @Override
    public int getAmount() {
        return 30;
    }

    private static final Identifier TEXTURE = new Identifier(NAMESPACE, "textures/custom/30_advancements.png");
    @Override
    public Identifier getTextureIdentifier() {
        return TEXTURE;
    }
    
}
