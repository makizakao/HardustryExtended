package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.content.HardLiquids;
import jp.makizakao.world.builder.SmeltRecipeBuilder;
import mindustry.content.Liquids;
import multicraft.Recipe;

public class SteamBoilerRecipes {
    public static final Seq<Recipe> STEAM_BOILER_TIER_1 = Seq.with(
            SmeltRecipeBuilder
                    .input().fluids(Liquids.water, 1f).heat(3f).temperature(100f)
                    .output().fluids(HardLiquids.steam, 3f)
                    .craftTime(60f)
                    .build()
    );
}
