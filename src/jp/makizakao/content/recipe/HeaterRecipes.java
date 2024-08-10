package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.builder.RecipeBuilder;
import mindustry.content.Items;
import multicraft.Recipe;

public class HeaterRecipes {
    public static final Seq<Recipe> ELECTRIC_HEATER_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().power(0.2f)
                    .output().heat(1f)
                    .craftTime(0f)
                    .build()
    );
    public static final  Seq<Recipe> COAL_HEATER_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(Items.coal, 1)
                    .output().heat(4f)
                    .craftTime(60f)
                    .build()
    );
}
