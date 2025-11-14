package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardLiquids;
import jp.makizakao.hardustryex.type.recipe.SmeltRecipe;
import mindustry.content.Liquids;
import multicraft.Recipe;

public class SteamBoilerRecipes {
    public static final Seq<Recipe> STEAM_BOILER_TIER_1 = Seq.with(
            SmeltRecipe.of()
                    .input().fluids(Liquids.water, 0.25f).heat(12f).temperature(100f).complete()
                    .output().fluids(HardLiquids.steam, 0.75f).complete()
                    .craftTime(60f)
                    .build()
    );
}
