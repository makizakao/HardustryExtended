package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.world.builder.RecipeBuilder;
import multicraft.Recipe;

public class HeaterRecipes {
    public static final Seq<Recipe> ELECTRIC_HEATER_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().power(0.2f)
                    .output().heat(1f)
                    .craftTime(0f)
                    .build()
    );
}
