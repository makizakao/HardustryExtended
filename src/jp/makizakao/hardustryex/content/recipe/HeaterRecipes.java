package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import mindustry.content.Items;
import multicraft.Recipe;

public class HeaterRecipes {
    public static final Seq<Recipe> ELECTRIC_HEATER_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().power(0.2f).complete()
                    .output().heat(1f).complete()
                    .craftTime(0f)
                    .build()
    );
    public static final  Seq<Recipe> COAL_HEATER_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(Items.coal, 1).complete()
                    .output().heat(4f).complete()
                    .craftTime(60f)
                    .build()
    );
}
