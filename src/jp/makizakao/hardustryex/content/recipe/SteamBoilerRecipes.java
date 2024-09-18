package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardLiquids;
import jp.makizakao.hardustryex.content.builder.SmeltRecipeBuilder;
import mindustry.content.Liquids;
import multicraft.Recipe;

public class SteamBoilerRecipes {
    public static final Seq<Recipe> STEAM_BOILER_TIER_1 = Seq.with(
            SmeltRecipeBuilder
                    .input().fluids(Liquids.water, 0.25f).heat(12f).temperature(100f)
                    .output().fluids(HardLiquids.steam, 0.75f)
                    .craftTime(60f)
                    .build()
    );
}
