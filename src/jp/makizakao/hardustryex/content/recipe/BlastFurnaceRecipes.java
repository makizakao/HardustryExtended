package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import multicraft.Recipe;

public class BlastFurnaceRecipes {
    public static final Seq<Recipe> BLAST_FURNACE_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.ironIngot, 2, HardItems.coke, 1).heat(15f).complete()
                    .output().items(HardItems.steelIngot, 1).complete()
                    .craftTime(3600f)
                    .build()
    );
}
