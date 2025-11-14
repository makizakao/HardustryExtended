package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.SmeltRecipe;
import multicraft.Recipe;

public class FurnaceRecipes {
    public static final Seq<Recipe> FURNACE_TIER_1 = Seq.with(
            SmeltRecipe.of()
                    .input().items(HardItems.copperDust, 2).heat(3f).temperature(120f).complete()
                    .output().items(HardItems.copperIngot, 1).complete()
                    .craftTime(90)
                    .build(),
            SmeltRecipe.of()
                    .input().items(HardItems.leadDust, 2).heat(2f).temperature(70f).complete()
                    .output().items(HardItems.leadIngot, 1).complete()
                    .craftTime(90)
                    .build(),
            SmeltRecipe.of()
                    .input().items(HardItems.galenaDust, 2).heat(3f).temperature(120f).complete()
                    .output().items(HardItems.leadIngot, 1).complete()
                    .craftTime(90)
                    .build(),
            SmeltRecipe.of()
                    .input().items(HardItems.tealliteDust, 2).heat(2f).temperature(70f).complete()
                    .output().items(HardItems.tinIngot, 1).complete()
                    .craftTime(90)
                    .build(),
            SmeltRecipe.of()
                    .input().items(HardItems.tinDust, 2).heat(2f).temperature(70f).complete()
                    .output().items(HardItems.tinIngot, 1).complete()
                    .craftTime(90)
                    .build(),
            SmeltRecipe.of()
                    .input().items(HardItems.bronzeDust, 2).heat(2f).temperature(95f).complete()
                    .output().items(HardItems.bronzeIngot, 1).complete()
                    .craftTime(90)
                    .build()
    );
}
