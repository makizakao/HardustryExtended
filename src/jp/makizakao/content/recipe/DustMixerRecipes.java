package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.content.HardItems;
import jp.makizakao.builder.RecipeBuilder;
import multicraft.Recipe;

public class DustMixerRecipes {
    public static final Seq<Recipe> DUST_MIXER_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.copperDust, 3, HardItems.tinDust, 1).power(0.05f)
                    .output().items(HardItems.bronzeDust, 4)
                    .craftTime(150f)
                    .build()
    );
}
