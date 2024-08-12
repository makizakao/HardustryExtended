package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.builder.SmeltRecipeBuilder;
import multicraft.Recipe;

public class FurnaceRecipes {
    public static final Seq<Recipe> FURNACE_TIER_1 = Seq.with(
            SmeltRecipeBuilder
                    .input().items(HardItems.copperDust, 2).heat(3f).temperature(120f)
                    .output().items(HardItems.copperIngot, 1)
                    .craftTime(90)
                    .build(),
            SmeltRecipeBuilder
                    .input().items(HardItems.leadDust, 2).heat(2f).temperature(70f)
                    .output().items(HardItems.leadIngot, 1)
                    .craftTime(90)
                    .build(),
            SmeltRecipeBuilder
                    .input().items(HardItems.galenaDust, 2).heat(3f).temperature(120f)
                    .output().items(HardItems.leadIngot, 1)
                    .craftTime(90)
                    .build(),
            SmeltRecipeBuilder
                    .input().items(HardItems.tealliteDust, 2).heat(2f).temperature(70f)
                    .output().items(HardItems.tinIngot, 1)
                    .craftTime(90)
                    .build(),
            SmeltRecipeBuilder
                    .input().items(HardItems.tinDust, 2).heat(2f).temperature(70f)
                    .output().items(HardItems.tinIngot, 1)
                    .craftTime(90)
                    .build(),
            SmeltRecipeBuilder
                    .input().items(HardItems.bronzeDust, 2).heat(2f).temperature(95f)
                    .output().items(HardItems.bronzeIngot, 1)
                    .craftTime(90)
                    .build()
    );
}
