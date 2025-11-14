package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import multicraft.Recipe;

public class DustMixerRecipes {
    public static final Seq<Recipe> DUST_MIXER_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.copperDust, 3, HardItems.tinDust, 1).power(0.05f).complete()
                    .output().items(HardItems.bronzeDust, 4).complete()
                    .craftTime(150f)
                    .build()
    );
}
